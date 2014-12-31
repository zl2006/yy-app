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
<title>系统列表</title>
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
								action="${basePath}/system/list.do?resID=${param.resID}"
								method="post">
								<input type="hidden" value="${data.pagination.index}"
									name="pagination.index" id="pagination_index">
								<div class="form-group last-group">
									<label for="systemCode" class="col-sm-1 control-label">系统编号:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="systemCode"
											placeholder="系统编号" name="systemCode"
											value="${reqParams.systemCode }">
									</div>
									<label for="name" class="col-sm-1 control-label">名称:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="name"
											placeholder="名称" name="name" value="${reqParams.name }">
									</div>
									<div class="col-sm-offset-3 col-sm-1">
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
								<th>应用名称</th>
								<th>应用编号</th>
								<th>应用URL</th>
								<th>描述</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" varStatus="status" items="${data.result}">
								<tr view="/system/view.do?resID=42&systemCode=${item.systemCode}">
									<td>${data.pagination.index*data.pagination.pageSize + status.index + 1}</td>
									<td>${item.name }</td>
									<td>${item.systemCode}</td>
									<td>${item.url }</td>
									<td>${item.description }</td>
									<td><c:if test="${item.status == 1}">有效</c:if> <c:if
											test="${item.status == 0}">无效</c:if></td>
									<td>
										<a resID="28" class="popedom" condition="1" params="systemCode=${item.systemCode}"></a>
										<a resID="29" class="popedom" condition='<c:if test="${item.status == 0}">1</c:if>' params="systemCode=${item.systemCode}"></a>
										<a resID="30" class="popedom" condition='<c:if test="${item.status == 1}">1</c:if>' params="systemCode=${item.systemCode}"></a>
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
