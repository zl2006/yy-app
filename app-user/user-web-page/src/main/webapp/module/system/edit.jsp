<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新系统</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "系统信息";
}
</style>
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<!--  -->
	<div class="container bs-docs-container">
		<div class="row">
			<jsp:include page="/common/menu.jsp"></jsp:include>
			<!--  内容区域 -->
			<div role="main" class="col-md-10">
				<!--  导航及操作区域  -->
				<div class="bs-docs-section">
					<div class="page-header">
						<jsp:include page="/common/navigator.jsp"></jsp:include>

						<div class="bs-example" style="content:">
							<form role="form" class="form-horizontal"
								action="${basePath }/system/edit.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<input type="hidden" name="status" value="${data.status}">
								<input type="hidden" name="systemID" value="${data.systemID }">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="systemCode">系统编号:</label>
									<div class="col-sm-3">
										<input type="text" id="systemCode" name="systemCode" class="form-control" 
											value="${data.systemCode }" readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">应用名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="应用名称" name="name" id="name"
											class="form-control" check-type="required"
											maxlength="64" value="${data.name }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="icon">应用图标:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="应用图标" name="icon"
											id="icon" class="form-control"  maxlength="128" value="${data.icon }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">应用URL:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="应用URL" name="url" id="url"
											class="form-control" check-type="url" maxlength="128" value="${data.url}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="latnNo">描述:</label>
									<div class="col-sm-5">
										<textarea rows="4" cols="80" name="description"
											id="description" class="form-control">${data.description}</textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button class="btn btn-primary" type="submit">确定</button>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<span id="error_info" style="color: #FF0000;">${errors }</span>
									</div>
								</div>
								<div id="result_info" style="display:none">
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!--  row end-->
	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath }/resources/js/seajs/seajs/2.2.0/sea.js"></script>
	<script type="text/javascript" src="${basePath }/resources/js/appuser/app.js"></script>
	<script type="text/javascript">
		seajs.use(["${basePath}/resources/js/appuser/business/business","$"],function(Business,$){
			var business = new Business({basePath : "${basePath}" });
			business.init_saveorupdate_page();
		});
	</script>
</body>
</html>
