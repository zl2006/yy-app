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
<title>组织机构</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after { content: "组织机构详情"; }
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
									<label class="col-sm-2 control-label">组织机构编号:</label>
									<label class="col-sm-3 control-label view-content">${data.organ.organCode }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">父组织机构:</label>
									<label class="col-sm-3 control-label view-content">${data.parentOrgan.name}</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">机构名称:</label>
									<label class="col-sm-3 control-label view-content">${data.organ.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">主负责人:</label>
									<label class="col-sm-3 control-label view-content">${data.organ.principal}</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">第二负责人:</label>
									<label class="col-sm-3 control-label view-content">
										${data.organ.secondPrincipal}
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">电话:</label>
									<label class="col-sm-3 control-label view-content"> ${data.organ.tel} </label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">传真:</label>
									<label class="col-sm-3 control-label view-content">${data.organ.fax} </label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">邮编:</label>
									<label class="col-sm-3 control-label view-content"> ${data.organ.postCode} </label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">机构描述:</label>
									<label class="col-sm-3 control-label view-content">
										${data.organ.description}
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态:</label>
									<label class="col-sm-3 control-label view-content">
										<c:if test="${data.organ.status ==1}">有效</c:if>
										<c:if test="${data.organ.status ==0}">无效</c:if>
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
