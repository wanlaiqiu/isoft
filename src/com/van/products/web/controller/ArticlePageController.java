package com.van.products.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.van.products.entity.bean.Article;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: ArticlePageController.java<br/>
 * @classDescription: 文章归档<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/article")
public class ArticlePageController extends BaseController<String, Article> {

	@RequestMapping
	public String article(HttpServletRequest request, HttpServletResponse response){
		return "main/article";
	}
}
