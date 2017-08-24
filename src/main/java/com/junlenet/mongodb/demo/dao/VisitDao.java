package com.junlenet.mongodb.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.UserBo;
import com.junlenet.mongodb.demo.bo.VisitLog;

@Repository
public class VisitDao {
	/**
	 * 操作mongodb的类,可以参考api
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Pager listLimt(Pager pager, String url, String ip){
		Query query = new Query();
		if (url != null) {
			query.addCriteria(Criteria.where("url").is(url));
		}
		if (ip != null) {
			query.addCriteria(Criteria.where("ip").is(ip));
		}
		query.skip((pager.getPageNum() - 1) * pager.getPageSize());
		query.limit(pager.getPageSize());
		query.with(new Sort(Sort.Direction.DESC, "visitDate"));
		/*
		 * Criteria criteria = new Criteria(); query.addCriteria(criteria);
		 */
		long total = mongoTemplate.count(query, VisitLog.class);
		List<VisitLog> users = mongoTemplate.find(query, VisitLog.class);
		pager.setResult(users);
		pager.setTotal(total);
		return pager;
	}
	/**
	 * 添加
	 * @param visitLog
	 * @return
	 */
	public VisitLog insert(VisitLog visitLog){
		mongoTemplate.save(visitLog);
		return visitLog;
	}
	
}
