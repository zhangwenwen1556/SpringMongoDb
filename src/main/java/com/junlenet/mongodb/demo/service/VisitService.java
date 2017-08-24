package com.junlenet.mongodb.demo.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.Pager;
import com.junlenet.mongodb.demo.bo.VisitLog;
import com.junlenet.mongodb.demo.dao.VisitDao;

@Service
public class VisitService {
	
	@Autowired
	private VisitDao visitDao;
	
	public List<VisitLog> limitNumber(String url, Integer number, String ip){
		Pager pager = new Pager();
		pager.setPageSize(number);
		Pager pagerReulst = visitDao.listLimt(pager, url, ip);
		return pagerReulst.getResult();
	}
	
	public VisitLog insert(String url, String ip){
		VisitLog visitLog = new VisitLog();
		visitLog.setUrl(url);
		visitLog.setId(UUID.randomUUID().toString());
		visitLog.setVisitDate(new Date());;
		visitLog.setIp(ip);
		return visitDao.insert(visitLog);
	}
	
}
