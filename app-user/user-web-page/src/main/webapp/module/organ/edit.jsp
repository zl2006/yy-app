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
<title>编辑组织机构</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "组织机构信息";
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
								action="${basePath }/organ/edit.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<input type="hidden" name="organID" value="${data.organ.organID }"/>
								<input type="hidden" name="status" value="${data.organ.status }"/>
								<input type="hidden" name="hasChild" value="${data.organ.hasChild }"/>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="organCode">组织机构编号:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="请输入组织机构编号" id="organCode" maxlength="32" name="organCode"
											class="form-control" check-type="required" readonly="readonly"
											value="${data.organ.organCode }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="parentOrganCode">父组织机构:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="选择父组织机构" id="parentOrganName" class="form-control" readonly="readonly" id="parentOrganName" value="${data.parentOrgan.name}">
										<input type="hidden" name="parentOrganCode" id="parentOrganCode"  value="${data.parentOrgan.organCode}">
									</div>
									<div class="col-sm-1">
										<button class="btn" type="button" id="selectOrgan">选择</button>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">机构名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="机构名称" name="name"
											id="name" class="form-control" check-type="required"
											maxlength="128" value="${data.organ.name }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="principal">主负责人:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="主负责人" name="principal" id="principal"
											class="form-control"  maxlength="64"
											value="${data.organ.principal}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="secondPrincipal">第二负责人:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="第二负责人" name="secondPrincipal" id="secondPrincipal"
											class="form-control" maxlength="64" value="${data.organ.secondPrincipal}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="tel">电话:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="电话" name="tel" id="tel"
											class="form-control" maxlength="32" value="${data.organ.tel}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="fax">传真:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="传真" name="fax" id="fax"
											class="form-control" maxlength="32" value="${data.organ.fax}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="postCode">邮编:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="邮编" name="postCode"
											id="postCode" class="form-control" maxlength="32"
											value="${data.organ.postCode}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="description">机构描述:</label>
									<div class="col-sm-6">
										<textarea rows="5" cols="3" placeholder="机构描述" name="description"
											id="description" class="form-control" maxlength="255" >${data.organ.description}</textarea>
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
		seajs.use(["${basePath}/resources/js/appuser/business/organ_business","$"],function(OrganBusiness,$){
			var organ_business = new OrganBusiness({base_path : "${basePath}"});
			organ_business.init_saveorupdate_page();
		});
	</script>
</body>
</html>
