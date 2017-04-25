<%@ page pageEncoding="UTF-8" session="false"%>

<%
	String contextPath = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
	System.out.println("---------"+basePath);
%>
<html>
<title>RESTful Spring Security</title>
<meta charset="UTF-8">
<body>
	<form action="${basePath}/login" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value=" 登录 " /> <input type="reset" value=" 重置 " /></td>
			</tr>
		</table>
	</form>
</body>
</html>