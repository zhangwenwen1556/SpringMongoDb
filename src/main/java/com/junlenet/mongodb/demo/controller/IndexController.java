package com.junlenet.mongodb.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.UserBo;
import com.junlenet.mongodb.demo.service.UserService;

/**
 * 
 * @author 张文文
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("indexController")
public class IndexController {

	@Autowired
	private UserService userService;
	
	private Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	
	@RequestMapping("/index")
	public @ResponseBody String index(ModelMap modelMap) {
		String id = UUID.randomUUID().toString();
		UserBo userBo = new UserBo();
		userBo.setPassword("junlenet");
		userBo.setPhone("130279814XX");
		userBo.setSex("男");
		userBo.setUserName("www.junlenet.com");
		userBo.setUserNo(id);
		//userBo = userService.save(userBo);
		Set<String> collections = userService.getCollectionNames();
		for (String str : collections) {
			System.out.println(str);
		}
		Pager pager = userService.selectPage(userBo, new Pager());
		List<UserBo> users = pager.getResult();
		for (UserBo user : users) {
			System.out.println(JSONObject.toJSONString(user));
		}
		return JSON.toJSONString(pager);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Model model,String password,String phone,String sex,String userName){
		String id = UUID.randomUUID().toString();
		UserBo userBo = new UserBo();
		
		userBo.setPassword(md5.encodePassword(password, null));
		userBo.setPhone(phone);
		userBo.setSex(sex);
		userBo.setUserName(userName);
		userBo.setUserNo(id);
		userBo.setDate(new Date());
		userBo = userService.save(userBo);
		return "redirect:listUser";
	}
	
	@RequestMapping(value="/listUser",method=RequestMethod.GET)
	public String listUser(Model model){
		UserBo userBo = new UserBo();
		Pager pager = userService.selectPage(userBo, new Pager());
		List<UserBo> users = pager.getResult();
		model.addAttribute("users", users);
		return "/listUser" ;
	}
	
	@RequestMapping(value="/insertPage",method=RequestMethod.GET)
	public String insertPage(){
		return "/insertPage";
	}
	
	@RequestMapping(value="/testFindOne", method=RequestMethod.GET)
	public @ResponseBody String testFindOne(String name){
		
		Query query = Query.query(Criteria.where("userName").is(name));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		
		UserBo userBo = userService.findOne(query);
		
		return JSON.toJSONString(userBo);
	}

}
