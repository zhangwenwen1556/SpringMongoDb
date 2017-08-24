package com.junlenet.mongodb.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.UserBo;
import com.junlenet.mongodb.demo.dao.UserDao;

/**
 *
 * @author zhangwenwen
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserBo save(UserBo userBo) {
		userBo = userDao.save(userBo);
		return userBo;
	}

	public Set<String> getCollectionNames() {
		return userDao.getCollectionNames();
	}

	public Pager selectPage(UserBo userBo, Pager pager) {
		return userDao.selectPage(userBo, pager);
	}

	public List<UserBo> findList(Query query){
		return userDao.find(query);
	}
	
	public UserBo findOne(Query query){
		return userDao.findOne(query);
	}
}
