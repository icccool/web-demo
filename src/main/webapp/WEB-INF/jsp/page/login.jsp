<%@ page language="java" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>         
<!DOCTYPE html>
<html>       
	<head>
		<meta charset="utf-8">
		<title>商户管理系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	</head>
    <body>
	 <div id="loginWrap">
	 	<div id="loginLogo"></div>
	 	<div id="loginFormWrap">
	 		<div id="loginAd"></div>
	 		<form id="loginForm" method="POST">
	 			<div id="errorMessage"></div>
	 			<div class="loginLine pr clearfix">
	 				<label>用户名：</label><input id="j_username" name="j_username" type="text" class="loginInput loginName"/><span class="icon_username"></span>
	 			</div>
	 			<div class="loginLine pr clearfix">
	 				<label>密码：</label><input id="j_password" name="j_password" type="password" class="loginInput loginPwd"/><span class="icon_pwd"></span>
	 			</div>
	 			<div class="loginLine clearfix">
	 				<label>验证码：</label><input type="text" id="veryCode" name="validateCode" class="loginInput loginCode"><img id="verifyCodeImgId" class="hand" title="点击刷新验证码" onclick="changeVerifyCode()" src="<%=basePath %>/servlet/VerifyCodeServlet" width="84" height="24"/>
	 			</div>
	 			<div class="loginLine clearfix">
	 				<button type="submit" class="loginBtn">登&nbsp;&nbsp;录</button>
	 			</div>
	 			<div id="info" style="text-indent:145px;line-height:40px;"></div> 
	 		</form>
	 	</div>
	 </div>
	</body>	      
	<script type="text/javascript">
		
	</script>
	<script src="<%=basePath %>/js/jquery.min.js"></script>
	<script src="<%=basePath %>/js/login.js"></script>	
</html>
