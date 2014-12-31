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
<title>资源列表</title>
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
								action="${basePath}/resource/list.do?resID=${param.resID}"
								method="post">
								<input type="hidden" value="${data.pagination.index}"
									name="pagination.index" id="pagination_index">
								<div class="form-group last-group">
									<label for="systemCode" class="col-sm-1 control-label">系统编号:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="systemCode" placeholder="系统编号" name="systemCode" value="${reqParams.systemCode}">
									</div>
									<label for="name" class="col-sm-1 control-label">资源名称:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="parentOrganCode" placeholder="资源名称" name="name" value="${reqParams.name}">
									</div>
									<div class="col-sm-offset-2 col-sm-1">
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
								<th>资源名称</th>
								<th>资源位置</th>
								<th>资源类型</th>
								<th>应用系统</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" varStatus="status" items="${data.result}">
								<tr view="/resource/view.do?myResID=${item.resID}&resID=50" id="${item.resID}" <c:if test="${item.hasChild == 1}"> haschild="true" </c:if> <c:if test="${item.parentResID ne '-1'}"> pid="${item.parentResID}"</c:if> >
									<td>${data.pagination.index * data.pagination.pageSize + status.index + 1}</td>
									<td>${item.name }</td>
									<td>${item.url}</td>
									<td><c:if test="${item.type == 0  }">菜单（模块）</c:if>
										<c:if test="${item.type == 1  }">模块通用操作</c:if>
										<c:if test="${item.type == 2  }">模块列表操作</c:if>
										<c:if test="${item.type == 3  }">其它操作</c:if></td>
									<td>${item.systemCode }</td>
									<td><c:if test="${item.status == 1}">有效</c:if> <c:if
											test="${item.status == 0}">无效</c:if></td>
									<td>
										<a resID="51" class="popedom" condition="1" params="myResID=${item.resID}"></a>
										<a resID="52" class="popedom" condition='<c:if test="${item.status == 0}">1</c:if>' params="myResID=${item.resID}"></a>
										<a resID="53" class="popedom" condition='<c:if test="${item.status == 1}">1</c:if>' params="myResID=${item.resID}"></a>
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
	seajs.use(["${basePath}/resources/js/appuser/business/res_business"],function(ResBusiness){
		var res_business = new ResBusiness({base_path : "${basePath}" , currentPage : ${data.pagination.index+1} , totalPages : ${data.pagination.totalPage} ,opers:${_OPERATOR_JSON_}});
		res_business.init_list_page();
	});
	</script>
</body>
</html>
