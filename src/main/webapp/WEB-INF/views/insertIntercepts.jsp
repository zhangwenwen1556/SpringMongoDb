<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insert" method="post">
		<table width="100%">
			<tr>
				<td colspan="2" align="center" style="color:red;">拦截的路径是全路径，不带参数。例如:/SpringMongoDb/interceptController/insertIntercepts</td>
			</tr>
			<tr>
				<td class="left">需要拦截的url：</td>
				<td class="right"><input type="text" name="url" class="textInput"></td>
			</tr>
			<tr>
				<td class="left">访问次数/分钟：</td>
				<td class="right"><input type="text" name="number" class="textInput"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;padding: 8px;"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>