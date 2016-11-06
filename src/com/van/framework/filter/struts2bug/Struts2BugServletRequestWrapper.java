package com.van.framework.filter.struts2bug;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/** 
 * @className: Struts2BugServletRequestWrapper.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月13日
 * @author Van
 */

public class Struts2BugServletRequestWrapper extends HttpServletRequestWrapper {
	
	private String acceptedParamNames = "[a-zA-Z0-9\\.\\]\\[_\\$\\-'\\s]+";
	private Pattern acceptedPattern = Pattern.compile(this.acceptedParamNames);

	public Struts2BugServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String[] getParameterValues(String param) {
		
		this.isBugInvade(param);
		
		return super.getParameterValues(param);
	}

	@Override
	public Map<String, String[]> getParameterMap() {

		Map<String, String[]> paramsMap = super.getParameterMap();
		Set<String> keySet = paramsMap.keySet();
		for(Iterator<String> it = keySet.iterator(); it.hasNext();){
			String param = it.next();
			this.isBugInvade(param);
		}
		
		return paramsMap;
	}
	
	@Override
	public String getParameter(String param) {
		
		this.isBugInvade(param);
		String value = super.getParameter(param);
		if(null == value)
			return null;
		
		return value;
	}
	
	private boolean isBugInvade(String param){
		
		Matcher matcher = this.acceptedPattern.matcher(param);
		if(!matcher.matches()){
			throw new RuntimeException("StrutsBug Fixed!");
		}
		
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		String acceptedParamNames = "[a-zA-Z0-9\\.\\]\\[_\\$\\-'\\s]+";
		Pattern acceptedPattern = Pattern.compile(acceptedParamNames);
		
		String str = "asdfasdf$$23423$42'34....-----___asdf__asdfas",
			   str2 = "asdfasdf$$2#3423$4234....-----___asdf__asdfas",
			   str3 = "$get",
			   str4 = "idadfd-erer-sdss",
			   str5 = "bean.id",
			   str6 = "_upload";
		
		System.out.println(str);
		System.out.println(acceptedPattern.matcher(str).matches());
		System.out.println(str2);
		System.out.println(acceptedPattern.matcher(str2).matches());
		System.out.println(str3);
		System.out.println(acceptedPattern.matcher(str3).matches());
		System.out.println(str4);
		System.out.println(acceptedPattern.matcher(str4).matches());
		System.out.println(str5);
		System.out.println(acceptedPattern.matcher(str5).matches());
		System.out.println(str6);
		System.out.println(acceptedPattern.matcher(str6).matches());
		
	}
}
