package com.junlenet.mongodb.demo.init;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.InterceptUrl;
import com.junlenet.mongodb.demo.bo.UserBo;
import com.junlenet.mongodb.demo.interceptor.UrlNumberInterceptor;
import com.junlenet.mongodb.demo.service.InterceptUrlService;
import com.junlenet.mongodb.demo.service.UserService;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private InterceptUrlService interceptUrlService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO 项目启动以后访问的方法
		if (UrlNumberInterceptor.interceptUrlMap.size() == 0) {
			// 将需要拦截的url查询出来放到Map中
			List<InterceptUrl> listInterceptUrl = interceptUrlService.listAll();
			for (InterceptUrl interceptUrl : listInterceptUrl) {
				UrlNumberInterceptor.interceptUrlMap.put(interceptUrl.getUrl(), interceptUrl);
			}
			/*
			如果jdk是1.8的话，可以用这个方式循环
			listInterceptUrl.stream().forEach(interceptUrl->{
				UrlNumberInterceptor.interceptUrlMap.put(interceptUrl.getUrl(), interceptUrl);
			});
			*/
		}
		// 判断是否有用户，如果没有用户的话，创建一个用户
		Query query = Query.query(Criteria.where("userName").is("zhangwenwen"));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		UserBo user = userService.findOne(query);
		if (user == null) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String id = UUID.randomUUID().toString();
			UserBo userBo = new UserBo();
			
			userBo.setPassword(md5.encodePassword("123456", null));
			userBo.setPhone("15072200010");
			userBo.setSex("男");
			userBo.setUserName("zhangwenwen");
			userBo.setUserNo(id);
			userBo.setDate(new Date());
			userBo = userService.save(userBo);
		}
	}

}
