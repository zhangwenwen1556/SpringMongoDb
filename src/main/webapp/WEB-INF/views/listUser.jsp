<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	tr > td {
			text-align:center;	
	}
</style>
</head>
<body>
	this is my list page
	<br>
	<a href="insertPage"> 添加用户</a>
	<a href="/SpringMongoDb/interceptController/index"> 拒绝访问列表</a>
	<br> ${users}

	<table width="100%" border="1px" align="center">
		<tr>
			<td>编号</td>
			<td>数据编号</td>
			<td>登录名称</td>
			<td>密码</td>
			<td>电话</td>
			<td>性别</td>
			<td>日期</td>
		</tr>
		<c:forEach items="${users }" var="user" varStatus="vs">
			<tr>
				<td><s:property value="#vs.index+1" /> ${vs.index+1 }</td>
				<td>${user.userNo}</td>
				<td>${user.userName }</td>
				<td>${user.password }</td>
				<td>${user.phone }</td>
				<td>${user.sex }</td>
				<td><fmt:formatDate value="${user.date}" type="both" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>