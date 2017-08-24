package com.junlenet.mongodb.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.InterceptUrl;
import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.dao.InterceptDao;
import com.junlenet.mongodb.demo.interceptor.UrlNumberInterceptor;

@Service
public class InterceptUrlService {
	
	@Autowired
	private InterceptDao interceptDao;
	
	public Pager selectPage(Pager pager) {
		return interceptDao.selectPage(pager);
	}
	
	public InterceptUrl insert(InterceptUrl interceptUrl){
		InterceptUrl insertInterceptUrl = interceptDao.save(interceptUrl);
		// 添加以后更新内存中的map
		UrlNumberInterceptor.interceptUrlMap.put(interceptUrl.getUrl(), insertInterceptUrl);
		return insertInterceptUrl;
	}
	
	public List<InterceptUrl> listAll(){
		return interceptDao.findAll();
	}
}
