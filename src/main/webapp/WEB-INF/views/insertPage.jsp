<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<style type="text/css">
	.left{
		width: 48%;
    	text-align: right;
   	    padding: 8px;
	}
	.textInput{
		border-left:none;
		border-right:none;
		border-top:none;
		border-bottom:1px solid #0F2543;
  }
</style>
</head>
<body>
	<form action="insert" method="post">
		<table width="100%">
			<tr>
				<td class="left">用户名：</td>
				<td class="right"><input type="text" name="userName" class="textInput"></td>
			</tr>
			<tr>
				<td class="left">密码：</td>
				<td class="right"><input type="password" name="password" class="textInput"></td>
			</tr>
			<tr>
				<td class="left">电话：</td>
				<td class="right"><input type="text" name="phone" class="textInput"></td>
			</tr>
			<tr>
				<td class="left">性别：</td>
				<td class="right"><input type="text" name="sex" class="textInput"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;padding: 8px;"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>