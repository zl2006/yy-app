<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户详情</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after { content: "用户详情"; }
</style>
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<div class="container bs-docs-container">
		<div class="row">
			<jsp:include page="/common/menu.jsp"></jsp:include>
			<!--  内容区域 -->
			<div role="main" class="col-md-10">
				<!--  导航及操作区域  -->
				<div class="bs-docs-section">
					<div class="page-header">
						<jsp:include page="/common/navigator.jsp"></jsp:include>
						<div class="bs-example" style="content:" >
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">登录ID:</label>
									<label class="col-sm-3 control-label view-content">${data.user.loginID }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">用户姓名:</label>
									<label class="col-sm-3 control-label view-content">${data.user.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">邮箱:</label>
									<label class="col-sm-3 control-label view-content">${data.user.email }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">电话:</label>
									<label class="col-sm-3 control-label view-content">${data.user.tel }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">性别:</label>
									<label class="col-sm-3 control-label view-content">
										<c:if test="${data.user.sex == 'F'}">女</c:if>
										<c:if test="${data.user.sex == 'M'}">男</c:if>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态:</label>
									<label class="col-sm-3 control-label view-content">
										<c:if test="${data.user.status ==1}">有效</c:if>
										<c:if test="${data.user.status ==0}">无效</c:if>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">地址:</label>
									<label class="col-sm-3 control-label view-content">${data.user.address }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">生日:</label>
									<label class="col-sm-3 control-label view-content"><fmt:formatDate value="${data.user.birthday}" type="date"></fmt:formatDate></label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">所在省市:</label>
									<label class="col-sm-3 control-label view-content">${data.user.latnNo }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">组织机构:</label>
									<label class="col-sm-3 control-label view-content">${data.user.organs[0].name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">用户组:</label>
									<label class="col-sm-3 control-label view-content">
										<c:forEach var="group" items="${data.groups}">
											${group.groupName}、
										</c:forEach>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">用户角色:</label>
									<label class="col-sm-8 control-label view-content">
										<c:forEach var="role" items="${data.roles}">
											${role.systemCode}:${role.name}、
										</c:forEach>
									</label>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-10 col-sm-2">
										<button class="btn btn-primary" type="button" onclick="javascript:window.history.back()">返回</button>
									</div>
								</div>
							</div><!-- page-header -->
						</div><!-- bs-example end -->
					</div><!-- form-horizontal end -->
				</div><!-- bs-docs-section end-->
			</div><!-- main end -->
		</div><!--  row end-->
	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>
