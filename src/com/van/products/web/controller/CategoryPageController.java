package com.van.products.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import com.van.products.entity.bean.Article;
import com.van.products.entity.bean.Content;
import com.van.products.entity.bean.UserModel;
import com.van.products.util.spring.SpringUtil;
import com.van.products.util.thread.AsynchronousExecutorHolder;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: CategoryPageController.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年6月27日
 * @author Van
 */

@Controller
@Scope("prototype")
@RequestMapping("/category")
public class CategoryPageController extends BaseController<String, Content> {

	
	/**
	 * 进入category主页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	public String catagory(HttpServletRequest request, HttpServletResponse response){
		
		//获取文章
		List<Content> contents = new ArrayList<Content>();
		StringBuffer sbf = new StringBuffer();
		sbf.append("select e.id as id,e.index as index,e.title as title,e.shortTitle as shortTitle,e.createTime as ctime,e.keyWords as keywords,")
			.append("e.author.name as author,e.article.name as article,e.count as count ")
			.append(" from Content e ")
			.append("where e.enumContentValid.id=? ")
			.append("and e.deleted=?")
			.append("");
		
		Object[] values = {
				"contentValid02",
				false
		};
		List<Map<String, Object>> lists =  this.generalService.findForPageListByHQL(sbf.toString(), values, 0, 12);
		if(CollectionUtils.isNotEmpty(lists)){
			for(Map<String, Object> map : lists){
				Content content = new Content();
				content.setId(FIXNULL(map.get("id")));
				content.setIndex(FIXNULL(map.get("index")));
				content.setTitle(FIXNULL(map.get("title")));
				content.setShortTitle(FIXNULL(map.get("shortTitle")));
				content.setCreateTime((Date)map.get("ctime"));
				content.setKeyWords(FIXNULL(map.get("keywords")));
				content.setCount(Integer.valueOf(FIXNULL(map.get("count"))));
				//关联用户
				UserModel author = new UserModel();
				author.setName(FIXNULL(map.get("author")));
				content.setAuthor(author);
				//关联文章类型
				Article article = new Article();
				article.setName(FIXNULL(map.get("article")));
				content.setArticle(article);

				contents.add(content);
			}
			request.setAttribute("contents", contents);
			
		}
		return "main/category";
	}
	
	
	/**
	 * 根据index获取文章内容
	 * @param KEYS index
	 * @param request
	 * @return Content
	 */
	@RequestMapping(value = "/load/data", method = RequestMethod.POST)
	public String query(String KEYS, HttpServletRequest request){
		if(StringUtils.isNotBlank(KEYS)){
			Object[] values = {KEYS};
			//获取文章对象
			List<Content> lists = this.generalService.findForListByHQL(this.entityClass, "where e.index=?", values);
			if(CollectionUtils.isNotEmpty(lists)){
				Content content = lists.get(0);
				String contents = content.getContent();
				int pre = content.getCount();
				String id = content.getId();
				Map<String, String> map = new HashMap<String, String>();
				map.put("index", content.getIndex());
				map.put("content", HtmlUtils.htmlUnescape(contents));
				map.put("author", content.getAuthor().getName());
				map.put("time", FIXNULL(content.getCreateTime()));
				map.put("count", FIXNULL(content.getCount()));
				
				request.setAttribute("data", map);
				this.saveContentClickNo(pre,id);
			}
		}
		
		return "data/modal";
	}
	
	/**
	 * 异步线程，修改文章点击次数
	 * @param pre 原点击次数
	 * @param id 文章id
	 */
	private void saveContentClickNo(int pre, String id){
		//获取异步操作对象
		AsynchronousExecutorHolder handler = 
				(AsynchronousExecutorHolder) SpringUtil.getBean("asynchronousExecutorHolder");
		handler.execute(() -> {
			
			String hql = "update Content e set e.count=? where e.id=?";
			Object[] values = {
					pre + 1,
					id
			};
			this.generalService.executeByHQL(hql, values);
		});
	}
	
	
	/**
	 * 适配数据类型
	 * @param obj
	 * @return
	 */
	private static String FIXNULL(Object obj){
		if(null == obj || "".equals(obj)){
			return "";
		}
		if(obj instanceof Date){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(obj);
		}
		return String.valueOf(obj);
	}
	
}
