package com.van.products.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.van.products.entity.bean.NullEntity;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: FunctionPageController.java<br/>
 * @classDescription: 更多功能<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/function")
public class FunctionPageController extends BaseController<String, NullEntity> {

	
	@RequestMapping
	public String functions(HttpServletRequest request, HttpServletResponse response){
		return "main/function";
	}
}
