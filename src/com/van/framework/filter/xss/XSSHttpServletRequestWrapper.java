package com.van.framework.filter.xss;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.van.framework.filter.xss.util.AntiXSSUtil;
import com.van.framework.filter.xss.util.XSSWebUtil;

/** 
 * @className: XSSHttpServletRequestWrapper.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月15日
 * @author Van
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(null == values)
			return null;
		
		int count = values.length;
		String[] encodedValues = new String[count];
		for(int i = 0; i < count; i++){
			encodedValues[i] = this.cleanXSS(values[i], name, true);
		}
			
		return encodedValues;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = super.getParameterMap();
		Set<String> keySet = paramMap.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String[] str = paramMap.get(key);
			for(int i =0 ; i < str.length ; i ++) {
				str[i] = cleanXSS(str[i],key,true) ;
			}
		}
		return paramMap ;
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
        if (null == value) {
             return null;
        }
        return cleanXSS(value,name,true);
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
	    if (value == null)
	         return null;
	    
	    return cleanXSS(value,name,false);
	}
	
	private String cleanXSS(String value,String name,boolean filterIp) {
    	try{
    		XSSWebUtil.addDefaultDomain(this.getServerName());
    		value = AntiXSSUtil.cleanXSS(this.getServletPath(),super.getHeader("Referer"),name,value,filterIp);
    	}catch (Exception e) {
			return value;
		}
    	return value;
    }
}
