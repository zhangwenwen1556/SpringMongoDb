package com.junlenet.mongodb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.junlenet.mongodb.demo.Thread.User;
import com.junlenet.mongodb.demo.Thread.UserThreadManager;

/**
 * 
 * @author zhangwenwen
 * 消息队列
 *
 */

@Controller
@RequestMapping("/spring")
public class SpringController {

	UserThreadManager userThread = UserThreadManager.newInstance();

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(String id, String name, String sex) {
		User user = new User(id, name, sex);
		userThread.addLogMsg(user);
		return "welcome";
	}

}
