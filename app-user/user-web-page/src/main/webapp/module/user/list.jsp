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
<title>用户列表</title>
<link rel="stylesheet" href="${basePath}/resources/js/gallery/bootstrap/3.0.0/bootstrap.css">
<link rel="stylesheet" href="${basePath}/resources/css/docs.css">
<link rel="stylesheet" href="${basePath}/resources/css/app.css">
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<div class="container bs-docs-container">
		<div class="row">
			<jsp:include page="/common/menu.jsp"></jsp:include>
			<!--  内容区域 -->
			<div role="main" class="col-md-10">
				<div class="bs-docs-section">
					<div class="page-header">
						<!--  导航及操作区域  -->
						<jsp:include page="/common/navigator.jsp"></jsp:include>
						<!--  查询条件  -->
						<div class="well">
							<form class="form-horizontal" role="form" id="query_form"
								action="${basePath}/user/list.do?resID=${param.resID}" method="post">
								<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
								<div class="form-group">
									<label for="loginID" class="col-sm-1 control-label">登录ID:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="loginID" placeholder="登录ID" name="loginID" value="${reqParams.loginID }">
									</div>
									<label for="name" class="col-sm-1 control-label">用户姓名:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="name" placeholder="用户姓名" name="name" value="${reqParams.name }">
									</div>
									<label for="tel" class="col-sm-1 control-label">电话:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="tel" placeholder="电话" name="tel" value="${reqParams.tel }">
									</div>
									<div class="col-sm-1">
										<button type="submit" class="btn btn-primary">查询</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!--  数据区域  -->
				<div class="bs-docs-section">
					<table width="100%" class="table table-bordered table-hover" id="data_table">
						<thead>
							<tr>
								<th>序号</th>
								<th>登录ID</th>
								<th>用户姓名</th>
								<th>邮箱</th>
								<th>电话</th>
								<th>性别</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" varStatus="status" items="${data.result}">
								<tr view="/user/view.do?resID=43&loginID=${item.loginID }">
									<td>${status.index + 1}</td>
									<td>${item.loginID }</td>
									<td>${item.name }</td>
									<td>${item.email}</td>
									<td>${item.tel }</td>
									<td><c:if test="${item.sex == 'F'}">女</c:if> <c:if test="${item.sex == 'M'}">男</c:if></td>
									<td><c:if test="${item.status == 1}">有效</c:if> <c:if test="${item.status == 0}">无效</c:if></td>
									<td>
										<a resID="17" class="popedom" condition="1" params="loginID=${item.loginID}"></a>
										<a resID="46" class="popedom" condition="1" params="loginID=${item.loginID}"></a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty data.result}">
								<tr>
									<td colspan="6" class="warning">未查询到数据</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<ul id="pagination">

					</ul>
				</div>
			</div>
		</div>
		<!--  row end-->
	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath }/resources/js/seajs/seajs/2.2.0/sea.js"></script>
	<script type="text/javascript" src="${basePath }/resources/js/appuser/app.js"></script>
	<script type="text/javascript">
		seajs.use(["${basePath}/resources/js/appuser/business/business"],function(Business){
			var business = new Business({base_path : "${basePath}" , currentPage : ${data.pagination.index+1} , totalPages : ${data.pagination.totalPage},opers:${_OPERATOR_JSON_}});
			business.init_list_page();
		});
	</script>
</body>
</html>
