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
<title>新增字典</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "字典信息";
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
							<form role="form" class="form-horizontal" action="${basePath }/dic/edit.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<input type="hidden" name="dicID" value="${data.dicID }"/>
								<input type="hidden" name="status" value="${data.status }"/>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="type">字典类型:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入字典类型" id="type" maxlength="16" name="type"
											class="form-control" check-type="required" value="${data.type }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">字典名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入字典名称" id="name" maxlength="64" name="name"
											class="form-control" check-type="required" value="${data.name }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="dicCode">字典编码:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="字典编码" id="dicCode" maxlength="32" name="dicCode"
											class="form-control" check-type="required" value="${data.dicCode }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="value">字典值:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入字典值" id="value" maxlength="255" name="value"
											class="form-control" check-type="required" value="${data.value }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="orderNO">序号:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入序号" id="orderNO" name="orderNO"
											class="form-control" check-type="required number" value="${data.orderNO }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="description">字典描述:</label>
									<div class="col-sm-6">
										<textarea rows="3" cols="10" placeholder="描述" name="description"
											id="description" class="form-control" maxlength="255" >${data.description}</textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-7 col-sm-10">
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
		seajs.use("${basePath}/resources/js/appuser/business/business",function(Business){
			var business = new Business({basePath : "${basePath}"});
			business.init_saveorupdate_page();
		});
	</script>
</body>
</html>
