package com.van.framework.filter.xss.util;

import org.apache.commons.lang3.StringUtils;

/** 
 * @className: XSSWebUtil.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月15日
 * @author Van
 */
public class XSSWebUtil {

	private static final String[] WHITE_DOMIANS = new String[1];
	
	private static final String[] WHITE_IPS = new String[0];
	
	public static boolean isWhiteDomain(String domain){
		if(StringUtils.isBlank(domain))
			return true;
		for(String whiteUrl : WHITE_DOMIANS){
			if(domain.indexOf(whiteUrl) != -1){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWhiteIP(String value){
		if(StringUtils.isBlank(value))
			return true;
		for(String whiteIP : WHITE_IPS){
			if(value.indexOf(whiteIP) != -1){
				return true;
			}
		}
		return false;
	}
	
	public static void addDefaultDomain(String domain){
		if(null == WHITE_DOMIANS[0])
			WHITE_DOMIANS[0] = domain;
	}
}
