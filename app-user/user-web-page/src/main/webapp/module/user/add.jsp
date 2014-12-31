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
<title>增加用户</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "用户信息";
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
							<form role="form" class="form-horizontal" action="${basePath }/user/save.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="loginID">登录ID:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="登录ID" id="loginID"
											minlength="8" maxlength="64" name="loginID"
											class="form-control" check-type="required"
											value="${data.loginID }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">用户姓名:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="用户姓名" name="name" id="name"
											class="form-control" check-type="required" 
											maxlength="64" value="${data.name }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="password">密码:</label>
									<div class="col-sm-3">
										<input type="password" placeholder="密码" name="password"
											id="password" class="form-control" check-type="required"
											minlength="8" maxlength="32" value="${data.password }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">邮箱:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="邮箱" name="email" id="email"
											class="form-control" check-type="mail" maxlength="128"
											value="${data.email}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="tel">电话:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="电话" name="tel" id="tel"
											class="form-control" maxlength="32" value="${data.tel}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sex">性别:</label>
									<div class="col-sm-5 ">
										<label class="checkbox-inline"> <input type="radio"
											name="sex" value="M" checked="checked"> 男
										</label> <label class="checkbox-inline"> <input type="radio"
											name="sex" value="F"> 女
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="status">状态:</label>
									<div class="col-sm-5 ">
										<label class="checkbox-inline"> <input type="radio"
											name="status" value="1" checked="checked"> 有效
										</label> <label class="checkbox-inline"> <input type="radio"
											name="status" value="0"> 无效
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="address">地址:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="地址" name="address" id="address" class="form-control" maxlength="255"
											value="${data.address}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="birthday">生日:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="生日" name="birthday" id="birthday" class="form-control date"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="latnNo">所在省市:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="所在省市" name="latnNo" id="latnNo" class="form-control" value="${data.latnNo}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="parentOrganCode">组织机构:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="选择组织" id="organName" class="form-control" readonly="readonly">
										<input type="hidden" name="organs[0].organCode" id="organCode" >
									</div>
									<div class="col-sm-1">
										<button class="btn" type="button" id="selectOrgan">选择</button>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button class="btn btn-primary" type="submit">注册</button>
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
			var business = new Business({base_path : "${basePath}" });
			business.init_saveorupdate_page();
			seajs.use('my97date');
			
			$("#selectOrgan").on('click', function(event){
				//按需要才加载JS文件
				seajs.use('organ',function(OrganSelectModal){
					var organModal = new OrganSelectModal({"basePath" : "${basePath}" ,  "selectedOrgan":function(organCode,organName) {
						$('#organCode').val(organCode);
						$('#organName').val(organName);
						organModal.close();
					}});
					organModal.open();
				});
			});
			
		});
	</script>
</body>
</html>
