package com.junlenet.mongodb.demo.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.UserBo;

/**
 * 用户DAO
 * 
 * @author zhangwenwen
 * @date 2016年7月7日 下午8:49:18
 */
@Repository
public class UserDao {

	/**
	 * 操作mongodb的类,可以参考api
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 保存用户信息
	 * 
	 * @param userBo
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public UserBo save(UserBo userBo) {
		mongoTemplate.save(userBo);
		return userBo;
	}

	/**
	 * 获取所有集合的名称
	 * 
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public Set<String> getCollectionNames() {
		Set<String> collections = mongoTemplate.getCollectionNames();
		return collections;
	}

	/**
	 * 分页查询数据
	 * 
	 * @param userBo
	 * @param pager
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public Pager selectPage(UserBo userBo, Pager pager) {
		Query query = new Query();
		query.skip((pager.getPageNum() - 1) * pager.getPageSize());
		query.limit(pager.getPageSize());
		query.with(new Sort(Sort.Direction.DESC, "date"));
		/*
		 * Criteria criteria = new Criteria(); query.addCriteria(criteria);
		 */
		long total = mongoTemplate.count(query, UserBo.class);
		List<UserBo> users = mongoTemplate.find(query, UserBo.class);
		pager.setResult(users);
		pager.setTotal(total);
		return pager;
	}
	/**
	 * 条件查询
	 * 
	 * @param query
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public List<UserBo> find(Query query){
		List<UserBo> users = mongoTemplate.find(query, UserBo.class);
		return users;
	}
	
	/**
	 * 条件查询
	 * 
	 * @param query
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public UserBo findOne(Query query){
		UserBo userBo = mongoTemplate.findOne(query, UserBo.class);
		return userBo;
	}

}
