<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户组配置</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after { content: "用户组配置"; }
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
							<form class="form-horizontal" action="${basePath }/group/config.json?resID=${param.resID}" id="save_update_form"
								method="post">
								<input type="hidden" name="userGroupID" value="${data.userGroup.userGroupID }"/>
								<div class="form-group">
									<label class="col-sm-2 control-label">用户组名称:</label>
									<label class="col-sm-3 control-label view-content">${data.userGroup.groupName }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">用户组描述:</label>
									<label class="col-sm-3 control-label view-content">${data.userGroup.description }</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态:</label>
									<label class="col-sm-3 control-label view-content"> <c:if test="${data.userGroup.status ==1}">有效</c:if> <c:if test="${data.userGroup.status ==0}">无效</c:if>
									</label>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">角色:</label>
									<div class="col-sm-8" style="border:1px solid; margin-top: 10px;margin-left:10px">
										<c:forEach var="system" items="${data.allSystems.result }" varStatus="status">
											<p style="margin:0px"><b>${status.index + 1},${system.name }(${system.systemCode})</b><p>
											<c:forEach var="role" items="${data.allRoles.result }">
												<c:if test="${system.systemCode == role.systemCode }">
													<label class="checkbox-inline"><input type="checkbox" name="roles" value="${role.roleCode }" <c:forEach var="originRole" items="${data.roles}">
														<c:if test="${originRole.roleCode == role.roleCode }">
															checked="checked"
														</c:if>
													</c:forEach>/>${role.name }</label>
												</c:if>
											</c:forEach>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-9 col-sm-2">
										<button class="btn btn-primary" type="submit" >确定</button>
									</div>
								</div>
							</form><!-- form -->
						</div><!-- bs-example end -->
					</div><!-- page header end -->
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
