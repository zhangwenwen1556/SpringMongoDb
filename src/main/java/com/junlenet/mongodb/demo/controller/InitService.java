package com.junlenet.mongodb.demo.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.UserBo;
import com.junlenet.mongodb.demo.service.UserService;

@Service
public class InitService implements InitializingBean {

	@Autowired
	private UserService userService;

	private Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	
	@Override
	public void afterPropertiesSet() throws Exception {

		// 这里在项目初始化的时候判断是否有管理员，如果有的话就什么都不干，如果没有的话就创建一个管理员
		Query query = Query.query(Criteria.where("userName").is("zhangwenwen"));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		
		UserBo userInfo = userService.findOne(query);
		
		if(userInfo == null){
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
