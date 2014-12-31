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
<title>新增用户组</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "用户组信息";
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
								action="${basePath }/group/save.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="groupName">用户组名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入用户组名称" id="groupName" maxlength="64" name="groupName"
											class="form-control" check-type="required" value="${data.groupName }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="description">描述:</label>
									<div class="col-sm-6">
										<textarea rows="5" cols="3" placeholder="描述" name="description"
											id="description" class="form-control" maxlength="255"
											value="${data.description}"></textarea>
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
