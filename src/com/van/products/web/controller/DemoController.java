package com.van.products.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.van.framework.core.orm.domain.vo.Pagination;
import com.van.products.entity.bean.UserModel;
import com.van.products.web.controller.Abstract.BaseController;

/** 
 * @className: DemoController.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年4月28日
 * @author Van
 */

//@Controller
//@RequestMapping("/demo")
public class DemoController extends BaseController<String, UserModel> {

	private static final String NONE = null;
	
	@RequestMapping("/entrance1")
	public ModelAndView index(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "开元3");
		map.put("key4", "我在jsp页面中，freemarker没有拦截到我");
		ModelAndView mav = new ModelAndView("home","data",map);
		return mav;
	}

	@RequestMapping("/entrance2")
	public String index2(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("map1", "value1");
		map.put("map2", "value2");
		map.put("map3", "开元3");
		map.put("map4", "我在freemarker页面中，被freemarker先拦截");
		
		request.setAttribute("root", map);
		
		return "freemarker";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/process")
	public String process(String name, String age, HttpServletRequest request) {
		System.out.println(name+"++++++++++++++++");
		System.out.println(age+"++++++++++++++++");
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", name);
		map.put("age", age);
		
		request.setAttribute("data", map);
		return "home";
	}
	
	@RequestMapping("/process1")
	public String process(UserModel user, HttpServletRequest request) {
		System.out.println(user.getName()+"++++++++++++++++");
		System.out.println(user.getAge()+"++++++++++++++++");
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", user.getName());
		map.put("age", user.getAge());
		
		request.setAttribute("data", map);
		return "home";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/json")
	public String jsonProcess(UserModel user, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		json.put("user", user.getName());
		json.put("age", user.getAge());
		
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			System.out.println(json.toString(1));
			out.write(json.toString(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request){
		System.out.println(file.getOriginalFilename());
		Long start = System.currentTimeMillis();
		System.out.println(start);
		Map<String, String> map = new HashMap<String, String>();
		if(!file.isEmpty()){
			try {
				InputStream fis = file.getInputStream();
				String dir = "/root/" + new Date().getTime();
				File fileDir = new File(dir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(dir + "/" + file.getOriginalFilename());
				int len = 0;
				byte[] bytes = new byte[1024];
				while((len = fis.read(bytes)) != -1){
					fos.write(bytes, 0, len);
				}
				map.put("path", dir + "/" + file.getOriginalFilename());
				
				fos.flush();
				fos.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println(end);
		map.put("time", String.valueOf(end - start));
		request.setAttribute("data", map);
		
		return "home";
	}
	
	/**
	 * @param requ
	 * @param resp
	 * @return
	 */
	@RequestMapping("/upload2")
	public String upload(HttpServletRequest requ, HttpServletResponse resp) {
		Long start = System.currentTimeMillis();
		System.out.println(start);
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(requ.getServletContext());
		if(multipartResolver.isMultipart(requ)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) requ;
			Iterator<String> it = multiRequest.getFileNames();
			while(it.hasNext()){
				MultipartFile multiFile = multiRequest.getFile(it.next());
				if(null != multiFile){
					
					String path = "/root" + multiFile.getOriginalFilename();
					File file = new File(path);
					try {
						multiFile.transferTo(file);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println(end);
		Map<String, String> map = new HashMap<String, String>();
		map.put("time", String.valueOf(end - start));
		requ.setAttribute("data", map);;
		return "home";
	}
	
	
	@RequestMapping("/queryUser")
	public String queryUser(HttpServletRequest request) throws ParseException{
		
		//UserModel model = this.generalService.getByProperty(this.entityClass, "enumFolk.id", "folk02");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2015-04-29");
			
		//int count = this.generalService.findForListByCriteria(DetachedCriteria.forClass(UserModel.class));
		//System.out.println(count);
//		List<UserModel> lists1 = this.generalService.findForListByCriteria(DetachedCriteria.forClass(UserModel.class)
//																		.createAlias("enumFolk", "folk",JoinType.LEFT_OUTER_JOIN)
//																		.add(Restrictions.eq("folk.nameSpace", "folk"))
//																		.add(Restrictions.eq("birthday", date)));
		/*List<UserModel> lists = this.generalService.loadAll(this.entityClass);
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		if(CollectionUtils.isNotEmpty(lists)){
			for(UserModel model : lists){
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("id", model.getId());
				map.put("name", model.getName());
				map.put("age", model.getAge());
				map.put("birthday", model.getBirthday().toLocaleString());
				map.put("folk", model.getEnumFolk().getName());
				data.add(map);
			}
			
		}*/
		String sql = "select e.*,ec.name as ufolk from user_model e,enum_const ec where e.fk_folk=ec.id and e.age=? and ec.id=? order by e.id";
		String hql = "   select e.id as sid,e.enumFolk.name as ufolk from UserModel e where e.age=? and e.enumFolk.id=?";
		Object[] values = {"26", "folk01"};
		Pagination page = this.generalService.findForPaginationBySQL(sql, values, 1, 2);
		if(null != page && page.getList().size() > 0){
			System.out.println(page.getFirstResult());
			System.out.println(page.getPageNo());
			System.out.println(page.getTotalCount());
			System.out.println(page.getTotalPage());
			System.out.println(page.getPageSize());
			
			//List<Map<String, Object>> data = (List<Map<String, Object>>) page.getList();
			List<Map<String, Object>> data = this.generalService.findForPageListBySQL(sql, values, 0, 2);
			request.setAttribute("datas", data);
		}
		return "/query/freemarker";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser() throws Exception{
		
		/*UserModel user = this.generalService.getById(this.entityClass, "ff8080814cff1adf014cff1b343100009090909");
		user.setName("测试修改987");
		this.generalService.save(user);*/
		/*List<String> ids = new ArrayList<String>();
		ids.add("ff8080814cff1adf014cff2804dc0003");
		int a = this.generalService.deleteByIds(this.entityClass, ids);
		System.out.println(a+"==============");*/
		String hql = "update user_model e set e.birthday=to_date(?,'yyyy-MM-dd') where e.id=?";
		List<String> hqls = new ArrayList<String>();
		List<Object[]> params = new ArrayList<Object[]>();
		hqls.add(hql);
		hqls.add(hql);
		hqls.add(hql);
		params.add(new Object[]{"2013-10-01","ff8080814d08a589014d08a6c6bc0000"});
		params.add(new Object[]{"2013-11-09","ff8080814d035209014d035359be0000"});
		params.add(new Object[]{"1913-12-30","ff8080814cff0f6e014cff0f92be0000"});
		
		this.generalService.executeBySQLGroup(hqls, params);
		
		return "freemarker";
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(){
		
		UserModel user = this.generalService.getById(this.entityClass, "ff8080814cff1adf014cff2804dc0003");;
		user.setName("风雨1230");
		this.generalService.save(user);
		
		
		return "freemarker";
	}

}
