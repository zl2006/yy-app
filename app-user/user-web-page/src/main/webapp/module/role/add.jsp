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
<title>新增角色</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "角色信息";
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
								action="${basePath }/role/save.json?resID=${param.resID}" method="post" id="save_update_form">
								<input type="hidden" value="1" name="status">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="roleCode">角色编号:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入角色编号" id="roleCode" maxlength="64" name="roleCode"
											class="form-control" check-type="required" value="${data.roleCode }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="groupName">系统编码:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="选择系统" id="systemName" name="systemName" check-type="required" class="form-control" readonly="readonly">
										<input type="hidden" name="systemCode" id="systemCode">
									</div>
									<div class="col-sm-1">
										<button class="btn" type="button" id="selectSystem">选择</button>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">角色名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入角色名称" id="name" maxlength="64" name="name"
											class="form-control" check-type="required" value="${data.name }">
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
		seajs.use(["${basePath}/resources/js/appuser/business/business",'$'],function(Business,$){
			var business = new Business({base_path : "${basePath}"});
			business.init_saveorupdate_page();
			$("#selectSystem").on('click', function(event){
				seajs.use('system',function(SystemSelectModal){
					var systemModal = null;
					systemModal = new SystemSelectModal({"basePath" : '${basePath}' ,  "selectedSystem":function(systemCode,name) {
						$('#systemCode').val(systemCode);
						$('#systemName').val(name);
						$('#parentResID').val('');
						$('#parentName').val('');
						systemModal.close();
					}});
					systemModal.open();
				});//seajs.use end
			});//click end  s
		});
	</script>
</body>
</html>
