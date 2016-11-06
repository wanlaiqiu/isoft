package com.van.framework.filter.struts2bug;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/** 
 * @className: Struts2BugFilter.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月13日
 * @author Van
 */

public class Struts2BugFilter implements Filter {

	@Override
	public void destroy() {
		//pass
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		chain.doFilter(new Struts2BugServletRequestWrapper(request), res);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//pass
	}

}
