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
	<a href="insertIntercepts"> 添加</a>
	<br> ${users}

	<table width="100%" border="1px" align="center">
		<tr>
			<td>编号</td>
			<td>数据编号</td>
			<td>拦截的url</td>
			<td>拦截次数/分钟</td>
		</tr>
		<c:forEach items="${intercepts }" var="intercept" varStatus="vs">
			<tr>
				<td><s:property value="#vs.index+1" /> ${vs.index+1 }</td>
				<td>${intercept.id}</td>
				<td>${intercept.url }</td>
				<td>${intercept.number }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>