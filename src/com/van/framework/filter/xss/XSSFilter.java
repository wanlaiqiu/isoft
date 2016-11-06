package com.van.framework.filter.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.van.framework.filter.xss.util.WhiteListFileUtil;

/** 
 * @className: XSSFilter.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月15日
 * @author Van
 */
public class XSSFilter implements Filter {
	
	private FilterConfig filterConfig = null;

	@Override
	public void destroy() {
		this.filterConfig = null;
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(null == WhiteListFileUtil.ROOT_PATH){
			WhiteListFileUtil.ROOT_PATH = req.getServletContext().getRealPath("/");
		}
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			String url = request.getRequestURL().toString();
			String escapeUrl = this.escapeHtmlAndScript(url);
			//过滤后，地址是否有改变，即是否有注入的迹象，如果有则直接重定向到过滤后的地址
			if(!url.equals(escapeUrl)){
				response.sendRedirect(escapeUrl);
			}
			
		} catch (Exception e) {
			chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) req), res);
		}
		chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) req), res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		
	}
	
	
	/**
	 * 将尖括号参数编码
	 * @param str
	 * @return
	 */
	public String escapeHtmlAndScript(String str){
		if(StringUtils.isNotEmpty(str)){
			str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
			str = str.replaceAll("'", "&#39;");
			str = str.replaceAll("\"", "");
			str = str.replaceAll("eval\\((.*)\\)", "");
			str = str.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		}
		return str;
	}

}
