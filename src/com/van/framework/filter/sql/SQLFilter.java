package com.van.framework.filter.sql;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @className: SQLFilter.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月18日
 * @author Van
 */
public class SQLFilter implements Filter {
	
	private static String badStr;
	private static String insStr;
	private static String excludeMethods;
	private static String characterEncoding;
	


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		Enumeration<String> params = request.getParameterNames();
		Boolean flagCheck = true;
		
		String reqURL = request.getRequestURI();
		Boolean checkNoHasMylist = true;
		if(reqURL.toLowerCase().indexOf("mylist") > 0){
			checkNoHasMylist = false;
		}
		
		String method = "";
		if(reqURL.indexOf("_") > 0 && reqURL.indexOf(".") > 0){
			method = reqURL.substring(reqURL.indexOf("_"), reqURL.indexOf("."));
		}
		if(null != method && !"".equals(method)){
			if(method.matches("^(" + excludeMethods + ")$")){
				flagCheck = false;
			}
		}
		
		String sql = "";
		if(flagCheck && checkNoHasMylist){
			while(params.hasMoreElements()){
				String name = params.nextElement();
				String[] value = request.getParameterValues(name);
				for(String str : value){
					sql += (" " + str);
				}
			}
			//含有sql敏感字符的，对敏感字符做转换
			if(SQLFilter.sqlValidate(sql)){
				Map map = request.getParameterMap();
				ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request, this.processParameters(map));
				chain.doFilter(wrapper, response);
			}else{
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.badStr = config.getInitParameter("badStr");
		this.insStr = config.getInitParameter("insStr");
		this.excludeMethods = config.getInitParameter("excludeMethods");
		this.characterEncoding = config.getInitParameter("characterEncoding");
	}

	@Override
	public void destroy() {
		//pass
	}
	
	protected static boolean sqlValidate(String sql) {
		sql = sql.toLowerCase();
		if(sql.replace("\r", " ").replace("\n", " ").matches("^(.*\\s){0,1}(" + badStr + ")(\\s.*){0,1}$")){
			return true;
		}
		return false;
	}
	
	private Map<String, Object> processParameters(Map<String, Object> map) {
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			String val = "";
			String[] badStrs = badStr.split("\\|");
			String[] insStrs = insStr.split("\\|");
			if(value instanceof String[]){
				String[] strs = (String[]) value;
				for(int i = 0, len = strs.length; i < len; i++){
					for(int k = 0, len2 = badStrs.length; k < len2; k++){
						if(strs[i].toLowerCase().replace("\r", " ").replace("\n", " ").matches("^(.*\\s){0,1}(" + badStrs[k] + ")(\\s.*){0,1}$")){
							strs[i] = strs[i].toLowerCase()
											.replace("\r", " ")
											.replace("\n", " ")
											.replaceAll("(.*?\\s){0,1}(" + badStrs[k] + ")(\\s.*?){0,1}",
													"$1" + insStrs[k] + "$3");
						}
						
					}
				}
				rtnMap.put(key, strs);
			}else {
				val = value.toString();
				for(int i = 0, len = badStrs.length; i < len; i++){
					if(val.indexOf(badStrs[i]) != -1){
						val = val.replaceAll(badStrs[i], insStrs[i]);
					}
				}
				rtnMap.put(key, val);
			}
			
		}
		
		return rtnMap;
	}
	
}
