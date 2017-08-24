package com.junlenet.mongodb.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.junlenet.mongodb.demo.bo.InterceptUrl;
import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.UserBo;

@Repository
public class InterceptDao {
	/**
	 * 操作mongodb的类,可以参考api
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 保存用户信息
	 * 
	 * @param interceptUrl
	 * @return
	 * @author zhangwenwen
	 * 
	 */
	public InterceptUrl save(InterceptUrl interceptUrl) {
		mongoTemplate.save(interceptUrl);
		return interceptUrl;
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
	public Pager selectPage(Pager pager) {
		Query query = new Query();
		query.skip((pager.getPageNum() - 1) * pager.getPageSize());
		query.limit(pager.getPageSize());
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		/*
		 * Criteria criteria = new Criteria(); query.addCriteria(criteria);
		 */
		long total = mongoTemplate.count(query, InterceptUrl.class);
		List<InterceptUrl> users = mongoTemplate.find(query, InterceptUrl.class);
		pager.setResult(users);
		pager.setTotal(total);
		return pager;
	}
	/**
	 * 查询所有的InterceptUrl
	 * @return
	 */
	public List<InterceptUrl> findAll(){
		return mongoTemplate.findAll(InterceptUrl.class);
	}
	
}
