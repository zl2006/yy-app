<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="${basePath}/resources/css/login.css">
</head>
<body id="body-mint" class="mint login d2c ">
	<div id="background-gradient">
		<div id="wrapper">
			<div id="body-container">
				<div id="body-header">
					<ul id="header-links" class="links hidden">
						<li id="hdr-username" class="ellipsis"></li>
					</ul>
					<div class="logo-and-tabs">
						<div id="logos-wrapper">
							<a href="javascript:void(0)" style="font-size: 50px">统一用户管理系统</a>
						</div>
					</div>
				</div>
				<div id="main">
					<div id="formbox-login" class="formbox firefox">
						<div class="title">
							<h1 id="login-label" class="login-label">登录到电商管理平台</h1>
						</div>
						<div class="signup-body">
							<div id="formbox-login-tips" class="tips">
								<h4>您还没有注册用户?</h4>
								<a href="login.event?task=S">免费注册</a>
								<h4 style="margin-top: 20px;">忘记密码?</h4>
								<a id="formbox-login-recover"> 重置密码</a>
							</div>
							<form method="post" id="form-login"
								action="${basePath}/dologin.do">
								<input type="hidden" value="${param.return_url}" name="return_url">
								<fieldset>
									<ol>
										<li id="li_username"><label for="form-login-username">登录账号</label><input
											type="text" name="username" class="text" id="username"
											value="${params.username}">
											<div class="vError" id="err_username">${errors.loginID }</div></li>
										<li id="li_password"><label for="form-login-password">密码</label><input
											type="password" name="password" class="text" id="password"
											value="${params.password}">
											<div class="vError" id="err_pwd">${errors.password }</div></li>
										<li id="remember_me"><input type="checkbox"
											name="remember" class="checkbox"><label
											for="form-login-remember">&nbsp;在本计算机上记住账号 </label></li>
										<li id="log_in"><input type="button" id="loginBtn"
											value="登录" class="btn btn-primary signup-submit">
									</ol>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<div id="body-footer">
					<div class="secondary-links">
						<a target="_blank" id="body-footer-help"
							href="http://www.mint.com/help">帮助</a><a target="_blank"
							id="about-us" href="http://www.mint.com/about/">关于我们</a>
					</div>
					<p class="copyright">
						&copy;2007&ndash;2013
						<txtrpmt key="PFMName">YY </txtrpmt>
						Software, Inc.
					</p>
				</div>
			</div>
		</div>
	</div>
	<script src="${basePath }/resources/js/jquery-1.10.2.min.js"
		type="text/javascript"></script>
	<script src="${basePath}/resources/js/login.js"></script>
</body>
</html>