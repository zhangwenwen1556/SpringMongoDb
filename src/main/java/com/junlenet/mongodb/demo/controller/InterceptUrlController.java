package com.junlenet.mongodb.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junlenet.mongodb.demo.bo.InterceptUrl;
import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.service.InterceptUrlService;
/**
 * 
 * @author 张文文
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("interceptController")
public class InterceptUrlController {
	
	@Autowired
	private InterceptUrlService interceptUrlService;
	
	@RequestMapping("index")
	public String indexIntercept(Model model){
		Pager pager = interceptUrlService.selectPage(new Pager());
		List<InterceptUrl> interceptUrls = pager.getResult();
		model.addAttribute("intercepts", interceptUrls);
		return "/listIntercept" ;
	}
	
	@RequestMapping(value="insertIntercepts", method=RequestMethod.GET)
	public String insertIntercepts(){
		return "/insertIntercepts";
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(Model model, String url, Integer number){
		Date date = new Date();
		InterceptUrl interceptUrl = new InterceptUrl();
		interceptUrl.setId(UUID.randomUUID().toString());
		interceptUrl.setNumber(number);
		interceptUrl.setUrl(url);
		interceptUrl.setCreateDate(date);
		interceptUrl.setLastUpdateDate(date);
		interceptUrlService.insert(interceptUrl);
		return "redirect:index";
	}
	
	
}
