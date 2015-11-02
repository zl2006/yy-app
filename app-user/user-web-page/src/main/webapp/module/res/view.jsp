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
<title>资源详情</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">.bs-example:after { content: "资源详情"; }</style>
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
									<label class="col-sm-2 control-label">资源名称:</label>
									<label class="col-sm-3 control-label view-content">${data.res.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">资源英文名称:</label>
									<label class="col-sm-3 control-label view-content">${data.res.ename }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">资源类型:</label>
									<label class="col-sm-3 control-label view-content">
										<c:choose>
											<c:when test="${data.res.type == 0  }">菜单（模块）</c:when>
											<c:when test="${data.res.type == 1  }">子菜单（子模块）</c:when>
											<c:when test="${data.res.type == 2  }">子菜单项（功能）</c:when>
											<c:when test="${data.res.type == 3  }">列表操作</c:when>
											<c:when test="${data.res.type == 4 }">按钮等操作</c:when>
											<c:otherwise>无</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">资源所属系统:</label>
									<label class="col-sm-3 control-label view-content">${data.system.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">父资源:</label>
									<label class="col-sm-3 control-label view-content">${data.parentRes.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">资源图标:</label>
									<label class="col-sm-3 control-label view-content">${data.res.icon }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">样式:</label>
									<label class="col-sm-3 control-label view-content">${data.res.style }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">描述:</label>
									<label class="col-sm-3 control-label view-content">${data.res.description }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">顺序:</label>
									<label class="col-sm-3 control-label view-content">${data.res.orderNO }</label>
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
