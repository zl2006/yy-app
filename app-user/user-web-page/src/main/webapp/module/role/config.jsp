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
<title>角色配置</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">.bs-example:after { content: "角色配置"; }</style>
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
						<form class="form-horizontal" action="${basePath }/role/config.json?resID=${param.resID}" id="save_update_form"
								method="post">
						<input type="hidden" name="roleCode" value="${data.role.roleCode}">
						<div class="bs-example" style="content:" >
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">角色编号:</label>
									<label class="col-sm-3 control-label view-content">${data.role.roleCode } </label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">系统:</label>
									<label class="col-sm-3 control-label view-content">${data.system.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">角色名称:</label>
									<label class="col-sm-3 control-label view-content">${data.role.name }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态:</label>
									<label class="col-sm-3 control-label view-content">
										<c:if test="${data.role.status==1}">有效</c:if><c:if test="${data.role.status==0}">无效</c:if>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">描述:</label>
									<label class="col-sm-3 control-label view-content">${data.role.description }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">拥有资源:</label>
									<div class="col-sm-8 control-label view-content">
										<c:forEach var="res" items="${data.allResources}">
											<c:if test="${res.parentResID == -1 or empty res.parentResID }">
												<p style="font-size:16px;font-weight: bold;"><label class="checkbox-inline"><input type="checkbox" value="${res.resID }"  name="resID" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == res.resID }">checked="checked"</c:if> </c:forEach> />${res.name }:</label></p>
												<p>
												<c:forEach var="childRes" items="${data.allResources}">
													<c:if test="${childRes.parentResID == res.resID}">
														<label class="checkbox-inline"><input type="checkbox" name="resID" value="${childRes.resID }" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == childRes.resID }">checked="checked"</c:if> </c:forEach> />${childRes.name }</label>
													</c:if>
												</c:forEach>
												</p>
											</c:if>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-10 col-sm-2">
										<button class="btn btn-primary" type="submit">确定</button>
									</div>
								</div>
							</div><!-- page-header -->
						</div><!-- bs-example end -->
						</form>
					</div><!-- form-horizontal end -->
				</div><!-- bs-docs-section end-->
			</div><!-- main end -->
		</div><!--  row end-->
	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath }/resources/js/seajs/seajs/2.2.0/sea.js"></script>
	<script type="text/javascript" src="${basePath }/resources/js/appuser/app.js"></script>
	<script type="text/javascript"> 
		seajs.use(["${basePath}/resources/js/appuser/business/business","$"],function(Business,$){
			var business = new Business({base_path : "${basePath}" });
			business.init_saveorupdate_page();
		});
	</script>
</body>
</html>
