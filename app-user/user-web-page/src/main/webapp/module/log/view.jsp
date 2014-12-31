<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
			request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>日志详情</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "日志详情";
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

						<div class="bs-example" style="content:" >
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">操作者:</label>
									<label class="col-sm-3 control-label view-content">${data.operName }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">操作类型:</label>
									<label class="col-sm-3 control-label view-content">${data.operType }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">业务数据类型:</label>
									<label class="col-sm-3 control-label view-content">${data.busiDataType }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">业务数据ID:</label>
									<label class="col-sm-3 control-label view-content">${data.busiDataID }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">描述:</label>
									<label class="col-sm-3 control-label view-content">${data.remark }</label>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-10 col-sm-2">
										<button class="btn btn-primary" type="button" onclick="javascript:window.history.back()">返回</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!--  row end-->
	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>
