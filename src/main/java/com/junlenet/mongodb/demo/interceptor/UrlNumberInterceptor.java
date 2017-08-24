package com.junlenet.mongodb.demo.interceptor;

import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.junlenet.mongodb.demo.bo.InterceptUrl;
import com.junlenet.mongodb.demo.bo.VisitLog;
import com.junlenet.mongodb.demo.service.VisitService;

/**
 * 
 * 拦截器
 * @author zhangwenwen
 *
 */
public class UrlNumberInterceptor implements HandlerInterceptor {
	
	// 使用线程安全的LinkedHashSet
	public static Map<String, InterceptUrl> interceptUrlMap = new LinkedHashMap<>();
	
	@Autowired
	private VisitService visitService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestURI = request.getRequestURI();
		String ip = request.getRemoteAddr();
		System.out.println("拦截器Url"+requestURI);
		
		if (interceptUrlMap.containsKey(requestURI)) {
			// 获取访问记录
			Date date = new Date();
			InterceptUrl interceptUrl = interceptUrlMap.get(requestURI);
			// 每分钟访问的次数
			Integer number = interceptUrl.getNumber();
			List<VisitLog> vistLog = visitService.limitNumber(requestURI, number, ip);
			if (vistLog.size()>0 && vistLog.size() == number) {
				// 获取list中时间最久的一条记录，以为在查询的时候按时间排序了，所以直接去第一条
				Date dates = vistLog.get(number-1).getVisitDate();
				// 时间最久的一条距离现在相差多少毫秒
				Long interval = date.getTime() - dates.getTime();
				
				if (60<(interval.intValue()/1000)) {
					// 最远的时间超过1分钟的话，说明一分钟内的访问次数没超过
					visitService.insert(requestURI, ip);
					return true;
				}else{
					// 说明访问次数超过
					PrintWriter writer = response.getWriter();
					response.setCharacterEncoding("utf-8");        
				    response.setContentType("text/html; charset=utf-8");
					writer.write("访问次数超出预支");
					return false;
				}
			} else {
				// 说明一次都没访问过，直接添加一条
				visitService.insert(requestURI, ip);
			}
		}
		return true;
	}

}
