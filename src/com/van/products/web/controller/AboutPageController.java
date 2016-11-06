package com.van.products.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.van.products.entity.bean.UserModel;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: AboutPageController.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年6月26日
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/about")
public class AboutPageController extends BaseController<String, UserModel> {
	
	
	
	@RequestMapping
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return "main/about";
	}
	
	

}
