package com.van.products.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.van.products.entity.bean.UserModel;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: HomePageController.java<br/>
 * @classDescription: 处理首页请求<br/>
 * @createTime: 2015.6.15
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomePageController extends BaseController<String, UserModel> {
	
	@RequestMapping
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "authz";

	}
	


}
