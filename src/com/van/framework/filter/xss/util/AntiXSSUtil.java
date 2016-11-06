package com.van.framework.filter.xss.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;

/** 
 * @className: AntiXSSUtil.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月15日
 * @author Van
 */
public class AntiXSSUtil {
	static final Pattern SCRIPT_TAG_PATTERN = Pattern.compile("<script[^>]*>.*</script[^>]*>", Pattern.CASE_INSENSITIVE);

	static final PatternCompiler pc = new Perl5Compiler();
	static final Perl5Matcher matcher = new Perl5Matcher();

	private static ConcurrentMap<String,String> whiteList = null;
	
	public static String antiEditorXSS(String content) {
		if (content == null || "".equals(content)) {
			return "";
		}
		String old = content;
		String ret = toAntiXSS(content);
		while (!ret.equals(old)) {
			old = ret;
			ret = toAntiXSS(ret);
		}
		return ret;
	}
	
	private static String toAntiXSS(String content) {
		try {
			return stripAllowScriptAccess(stripProtocol(stripCssExpression(stripAsciiAndHex(stripEvent(stripScriptTag(content))))));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String stripScriptTag(String content) {
		Matcher m = SCRIPT_TAG_PATTERN.matcher(content);
		content = m.replaceAll("");
		return content;
	}

	private static String stripEvent(String content) throws Exception {
		String[] events = { "onmouseover", "onmouseout", "onmousedown",
				"onmouseup", "onmousemove", "onclick", "ondblclick",
				"onkeypress", "onkeydown", "onkeyup", "ondragstart",
				"onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
				"onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
				"onscroll", "oncontextmenu" };
		for (String event : events) {
			org.apache.oro.text.regex.Pattern p = pc.compile("(<[^>]*)("+ event + ")([^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
			if (null != p)
				content = Util.substitute(matcher, p, new Perl5Substitution("$1" + event.substring(2) + "$3"), content, Util.SUBSTITUTE_ALL);

		}
		return content;
	}

	private static String stripAsciiAndHex(String content) throws Exception {
		// filter &# \00xx
		org.apache.oro.text.regex.Pattern p = pc.compile("(<[^>]*)(&#|\\\\00)([^>]*>)",Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1$3"), content, Util.SUBSTITUTE_ALL);
		
		return content;
	}

	private static String stripCssExpression(String content) throws Exception {
		org.apache.oro.text.regex.Pattern p = pc.compile("(<[^>]*style=.*)/\\*.*\\*/([^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1$2"), content, Util.SUBSTITUTE_ALL);

		p = pc.compile("(<[^>]*style=[^>]+)(expression|javascript|vbscript|-moz-binding)([^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1$3"), content, Util.SUBSTITUTE_ALL);

		p = pc.compile("(<style[^>]*>.*)/\\*.*\\*/(.*</style[^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1$2"), content, Util.SUBSTITUTE_ALL);

		p = pc.compile("(<style[^>]*>[^>]+)(expression|javascript|vbscript|-moz-binding)(.*</style[^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1$3"), content, Util.SUBSTITUTE_ALL);
		
		return content;
	}

	private static String stripProtocol(String content) throws Exception {
		String[] protocols = { "javascript", "alert", "vbscript", "livescript",
				"ms-its", "mhtml", "data", "firefoxurl", "mocha" };
		for (String protocol : protocols) {
			org.apache.oro.text.regex.Pattern p = pc.compile("(<[^>]*)" + protocol + ":([^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
			if (null != p)
				content = Util.substitute(matcher, p, new Perl5Substitution("$1/$2"), content, Util.SUBSTITUTE_ALL);
		}
		return content;
	}

	private static String stripAllowScriptAccess(String content)
			throws Exception {
		org.apache.oro.text.regex.Pattern p = pc.compile("(<[^>]*)AllowScriptAccess([^>]*>)", Perl5Compiler.CASE_INSENSITIVE_MASK);
		if (null != p)
			content = Util.substitute(matcher, p, new Perl5Substitution("$1Allow_Script_Access$2"), content, Util.SUBSTITUTE_ALL);
		return content;
	}
	
	public static Map<String,String> getWhiteListMap() {
		if(null != whiteList){
			return whiteList;
		}		
		return whiteList = WhiteListFileUtil.initWhiteList();
	}
	
	public static String filterLevel(String url,String header,String param){
		try{
			String urlLevel = getLevel(url,param);
			if(null  != urlLevel){
				return urlLevel;
			}
			return getLevel(header,param);
		}catch(Exception e){
			return null;
		}
	}
	
	private static String getLevel(String url,String param){
		Set<String> keySet = AntiXSSUtil.getWhiteListMap().keySet();
		if(null != keySet){
			for (String key : keySet) {
				String whiteUrl = key.split(WhiteListFileUtil.KEY_SPLIT)[0];
				if(url.indexOf(whiteUrl)!=-1) {
					if(param.equalsIgnoreCase(key.split(WhiteListFileUtil.KEY_SPLIT)[1])) {
						return AntiXSSUtil.getWhiteListMap().get(key);
					}
				}
			}
		}
		return null;
	}
	
	public static String escapeHtmlAndScript(String m,boolean filterIp) {
		if (StringUtils.isNotEmpty(m)) {
			m = m.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			m = m.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
			m = m.replaceAll("'", "&#39;");
			m = m.replaceAll("\"", "");
			m = m.replaceAll("eval\\((.*)\\)", "");
			m = m.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			m = m.replaceAll("script", "");
			m = escapeWebDomain(m);
			if(filterIp){
				m = escapeIp(m);
			}
		}
		return m;
	}
	
	private static String escapeWebDomain(String value){
		if(!XSSWebUtil.isWhiteDomain(subParam(value))){
//			String urlReg = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*";
			String urlReg = "^((https?|ftp|news):\\/\\/)?([a-z]([a-z0-9\\-]*[\\.��])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\\/[a-z0-9_\\-\\.~]+)*(\\/([a-z0-9_\\-\\.]*)(\\?[a-z0-9+_\\-\\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$";
			Pattern pattern = Pattern.compile(urlReg);
			Matcher mapcher = pattern.matcher(value);
			return mapcher.replaceAll("/");
		}
		return value;
	}
	
	private static String escapeIp(String value){
		if(!XSSWebUtil.isWhiteIP(value)){
			Pattern ipv4Pattern = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
			Pattern ipv6StdPattern = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
			Pattern ipv6HexCompressedPattern = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
			Matcher ipv4Mapcher = ipv4Pattern.matcher(value);
			value = ipv4Mapcher.replaceAll("/");
			Matcher ipv6StdMapcher = ipv6StdPattern.matcher(value);
			value = ipv6StdMapcher.replaceAll("/");
			Matcher ipv6HexCompressedMapcher = ipv6HexCompressedPattern.matcher(value);
			value = ipv6HexCompressedMapcher.replaceAll("/");
			return value;
		}
		return value;
	}
	
	private static String subParam(String domain){
		if(null == domain){
			return null;
		}
		return domain.split("\\?")[0];
	}

	public static String cleanXSS(String servletPath, String header,String name,String value,boolean filterIp) {
		String filterLvel = filterLevel(servletPath,header,name);
		if(null == filterLvel){
			return escapeHtmlAndScript(value,filterIp);
		}
		if("1".equals(filterLvel)){
			return AntiXSSUtil.antiEditorXSS(value);
		}
		return value;
	}
}
