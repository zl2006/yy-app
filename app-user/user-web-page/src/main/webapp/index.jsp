<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<!--  -->
	<div class="container bs-docs-container">
		<div class="row">
			<jsp:include page="/common/menu.jsp"></jsp:include>
			<!--  内容区域 -->
			<div role="main" class="col-md-10">
				<div class="bs-docs-section" id="shortcut">
					<div class="title">
						快捷操作入口
					</div>
					<div class="content clr">
						<a href="${basePath}/module/user/add.jsp?resID=16">新增用户</a>
						<a href="${basePath}/module/role/add.jsp?resID=54">新增角色</a>
						<a href="${basePath}/module/group/add.jsp?resID=31">新增用户组</a>
						<a href="${basePath}/module/system/add.jsp?resID=27">新增系统</a>
						<a href="${basePath}/module/organ/add.jsp?resID=18">新增组织</a>
						<a href="${basePath}/module/res/add.jsp?resID=49">新增资源</a>
					</div>
				</div>
				<div class="bs-docs-section" id="desc_area">
					<div class="title">
						系统示意图
					</div>
					<div class="content clr">
						<div class="wrap">
							<img  src="${basePath}/resources/images/user.png"/>
							<a href="${basePath}/user/list.do?resID=13" class="user"></a>
							<a href="${basePath}/group/list.do?resID=23" class="usergroup"></a>
							<a href="${basePath}/role/list.do?resID=15" class="role"></a>
							<a href="${basePath}/organ/list.do?resID=14" class="organ"></a>
							<a href="${basePath}/system/list.do?resID=22" class="system"></a>
							<a href="${basePath}/resource/list.do?resID=26" class="res"></a>
						</div>
					</div>
				</div>
			</div><!--  main end-->
		</div><!--  row end-->
	</div><!--  container end-->
	<jsp:include page="/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath }/resources/js/seajs/seajs/2.2.0/sea.js"></script>
	<script type="text/javascript" src="${basePath }/resources/js/appuser/app.js"></script>
	<script type="text/javascript">
		seajs.use("${basePath}/resources/js/appuser/index",function(index){
			index.init();
		});
	</script>
</body>
</html>
