package com.van.products.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.van.products.entity.bean.EnumConst;
import com.van.products.entity.bean.Message;
import com.van.products.entity.bean.NullEntity;
import com.van.products.entity.bean.UserModel;
import com.van.products.entity.bean.System.SystemUser;
import com.van.products.util.spring.SpringUtil;
import com.van.products.util.thread.AsynchronousExecutorHolder;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: ContactPageController.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/contact")
public class ContactPageController extends BaseController<String, NullEntity> {

	
	@RequestMapping
	public String contact(HttpServletRequest request, HttpServletResponse response){
		return "main/contact";
	}
	
	/**
	 * 给ROOT发送站内信
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sender", method = RequestMethod.POST)
	@ResponseBody
	public String message2Root(HttpServletRequest req, String name, String email, String message ){
		JSONObject json = new JSONObject();
		json.put("status", "n");
		if(StringUtils.isBlank(name)){
			json.put("info", "名称有误，请重新输入");
			return json.toString();
		}
		if(StringUtils.isBlank(email)){
			json.put("info", "电子邮件有误，请重新输入");
			return json.toString();
		}
		if(StringUtils.isBlank(message)){
			json.put("info", "Message有误，请重新输入");
			return json.toString();
		}
		Message msg = new Message();
		String escapeName = HtmlUtils.htmlEscape(name,"UTF-8");
		msg.setSenderName(escapeName);
		String escapeEmail = HtmlUtils.htmlEscape(email, "UTF-8");
		msg.setSenderEmail(escapeEmail);
		String escapeMessage = HtmlUtils.htmlEscape(message, "UTF-8");
		msg.setMessage(escapeMessage);
		try {
			UserModel noLogin = null;
			DetachedCriteria dc = DetachedCriteria.forClass(UserModel.class);
			dc.createAlias("systemUser", "systemUser");
			dc.add(Restrictions.eq("id", "$NOLOGIN"));
			List<UserModel> lists = this.generalService.getListByDetachedCriteria(dc);
			if(!CollectionUtils.isEmpty(lists)){
				noLogin = lists.get(0);
			}else{
				json.put("info", "用户身份错误！");
				return json.toString();
			}
			
			msg.setSender(noLogin);
			msg.setReceiver(new UserModel("#ROOT"));
			msg.setReceiverName("超级管理员");
			//msg.setReceiverEmail("");
			
			String ip = this.ipHelper(req);
			msg.setIp(ip);
			msg.setSendTime(new Date());
			EnumConst enumMessageStatus = this.generalService.getById(EnumConst.class, "message01");
			msg.setEnumMessageStatus(enumMessageStatus);
		
			this.generalService.save(msg);
			this.saveNoLoginUser(noLogin, ip);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("info", "错误，请稍后再试！");
			return json.toString();
		}
		
		json.put("status", "y");
		json.put("info", "感谢您的宝贵意见！");
		return json.toString();
	}
	
	
	/**
	 * 异步线程 刷新游客信息
	 * @param noLoginUser 游客信息
	 * @param ip 游客IP
	 */
	private void saveNoLoginUser(final UserModel noLoginUser, String ip){
		AsynchronousExecutorHolder handler = (AsynchronousExecutorHolder) SpringUtil.getBean("asynchronousExecutorHolder");
		
		handler.execute(() -> {
			
			SystemUser user = noLoginUser.getSystemUser();
			int count = user.getCount();
			String preIp = user.getLastLoginIp();
			Date preDate = user.getLastLoginTime();
			StringBuffer hql = new StringBuffer();
			hql.append("update SystemUser e set ")
				.append("e.count=?,")
				.append("e.preLoginIp=?,")
				.append("e.lastLoginIp=?,")
				.append("e.preLoginTime=?,")
				.append("e.lastLoginTime=? ")
				.append("where e.id=?");
			Object[] conditions = {
					count + 1,
					preIp,
					ip,
					preDate,
					new Date(),
					user.getId()
			};
			this.generalService.executeByHQL(hql.toString(), conditions);
			
		});
		
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request HttpServletRequest
	 * @return ipString
	 */
	private String ipHelper(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(null == ip || ip.length() == 0 || "unknown".equals(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(null == ip || ip.length() == 0 || "unknown".equals(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(null == ip || ip.length() == 0 || "unknown".equals(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
