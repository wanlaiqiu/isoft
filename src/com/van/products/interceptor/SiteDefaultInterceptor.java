package com.van.products.interceptor;

import java.time.Clock;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.van.products.entity.bean.Site;
import com.van.products.service.GeneralService;

/** 
 * @className: SiteDefaultInterceptor.java<br/>
 * @classDescription: 全站默认拦截器<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

public class SiteDefaultInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private GeneralService generalService; 
	
	private static Site site;
	
	//页面标题导航语
	private String[][] DOMAIN = {
									{"about", "关于我们"},
									{"article", "文章归档"},
									{"category", "目录分类"},
									{"function", "更多功能"},
									{"contact", "写信给我,您在访问时的疑问"}
								};
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelandview) throws Exception {
		if(null == site){
			DetachedCriteria dc = DetachedCriteria.forClass(Site.class);
			dc.add(Restrictions.eq("id", 1L));
			List<Site> lists = this.generalService.getListByDetachedCriteria(dc);
			if(!CollectionUtils.isEmpty(lists)){
				site = lists.get(0);
			}else{
				throw new RuntimeException("Lost global site arguments");
			}
		}
		String uri = request.getRequestURI();
		if(StringUtils.isNotBlank(uri)){
			if("/".equals(uri)){
				site.setNavigateName("主页");
				site.setNavigateURI("/");
				
			} else if(!uri.startsWith("/public")){
				for(String[] navigate : DOMAIN){
					Pattern pattern = Pattern.compile("\\/" + navigate[0]);
					Matcher matcher = pattern.matcher(uri);
					if(matcher.find()){
						site.setNavigateURI("/" + navigate[0]);
						site.setNavigateName(navigate[1]);
						break;
					}
				}
			}
		}
		site.setTime(String.valueOf(Clock.systemDefaultZone().millis()));
		request.setAttribute("site", site);
	}
}
