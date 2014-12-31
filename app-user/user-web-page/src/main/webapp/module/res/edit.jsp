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
<title>编辑资源</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
<style type="text/css">
.bs-example:after {
	content: "资源信息";
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
							<form role="form" class="form-horizontal" action="${basePath }/resource/update.json?resID=${param.resID}"
								method="post" id="save_update_form">
								<input type="hidden" name="myResID" value="${data.res.resID }">
								<input type="hidden" name="status" value="${data.res.status}" >
								<div class="form-group">
									<label class="col-sm-2 control-label" for="name">资源名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="资源名称" id="name" maxlength="64" name="name"
											class="form-control" check-type="required"
											value="${data.res.name }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="ename">资源英文名称:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="资源英文名称" name="ename" id="ename"
											class="form-control" check-type="required" 
											maxlength="64" value="${data.res.ename }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="type">资源类型:</label>
									<div class="col-sm-8 ">
										<label class="checkbox-inline">
											<input type="radio" name="type" value="0" <c:if test="${data.res.type == 0  }">checked="checked"</c:if> > 菜单（模块）
										</label>
										<label class="checkbox-inline"> 
											<input type="radio" name="type" value="1" <c:if test="${data.res.type == 1  }">checked="checked"</c:if> > 模块通用操作
										</label>
										<label class="checkbox-inline"> 
											<input type="radio" name="type" value="2" <c:if test="${data.res.type == 2  }">checked="checked"</c:if> > 模块列表操作
										</label>
										<label class="checkbox-inline"> 
											<input type="radio" name="type" value="3" <c:if test="${data.res.type == 3  }">checked="checked"</c:if> > 其它操作
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="systemName">资源所属系统:</label>
									<div class="col-sm-3">
										<input type="text" value="${data.system.name }" placeholder="选择系统" id="systemName" name="systemName" check-type="required" class="form-control" readonly="readonly">
										<input type="hidden" name="systemCode" id="systemCode" value="${data.system.systemCode }">
									</div>
									<div class="col-sm-1">
										<button class="btn" type="button" id="selectSystem">选择</button>
									</div>
								</div> 
								<div class="form-group">
									<label class="col-sm-2 control-label" for="parentName">父资源:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="选择资源" id="parentName" class="form-control" readonly="readonly" value="${data.parentRes.name }">
										<input type="hidden" name="parentResID" id="parentResID" value="${data.parentRes.resID }">
									</div>
									<div class="col-sm-1">
										<button class="btn" type="button" id="selectResource">选择</button>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="url">资源URL:</label>
									<div class="col-sm-3">
										<input type="text" placeholder="资源URL" name="url"
											id="url" class="form-control" check-type="required" maxlength="128" value="${data.res.url }">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="icon">资源图标:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="资源图标" name="icon" id="icon"
											class="form-control" maxlength="128" value="${data.res.icon}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="style">样式:</label>
									<div class="col-sm-5">
										<input type="text" placeholder="样式" name="style" id="style"
											class="form-control" maxlength="128" value="${data.res.style}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="description">描述:</label>
									<div class="col-sm-5">
										<textarea rows="4" cols="80">${data.res.description}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="orderNO">顺序:</label>
									<div class="col-sm-1">
										<input type="text" placeholder="顺序" name="orderNO" id="orderNO" class="form-control" value="1">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button class="btn btn-primary" type="submit">确定</button>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<span id="error_info" style="color: #FF0000;">${errors}</span>
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
		seajs.use(["${basePath}/resources/js/appuser/business/res_business","$"],function(ResBusiness,$){
			var business = new ResBusiness({base_path : "${basePath}" });
			business.init_saveorupdate_page();
		});
	</script>
</body>
</html>
