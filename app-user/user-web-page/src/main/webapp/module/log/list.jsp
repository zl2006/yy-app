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
<title>日志列表</title>
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
							<form class="form-horizontal" role="form" id="query_form" action="${basePath}/log/list.do?resID=${param.resID}" method="post">
								<input type="hidden" value="${data.pagination.index}"
									name="pagination.index" id="pagination_index">
								<div class="form-group">
									<label for="operName" class="col-sm-1 control-label">操作者:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="operName" placeholder="操作者" name="operName" value="${reqParams.operName }">
									</div>
									<label for="startCreateTime" class="col-sm-1 control-label">创建时间:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="startCreateTime" placeholder="创建起始时间" name="startCreateTime" value="<fmt:formatDate value="${reqParams.startCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									</div>	
									<label for="endCreateTime" style="float:left;padding-top:7px;">至</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="endCreateTime" placeholder="创建结束时间" name="endCreateTime" value="<fmt:formatDate value="${reqParams.endCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									</div>	
								</div>
								<div class="form-group last-group">
									<label for="busiDataType" class="col-sm-1 control-label">业务类型:</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="busiDataType"
											placeholder="业务类型" name="busiDataType" value="${reqParams.busiDataType }">
									</div>
									<div class="col-sm-offset-7 col-sm-1">
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
								<th>操作者名称</th>
								<th>操作类型</th>
								<th>业务数据类型</th>
								<th>描述</th>
								<th>创建时间</th>
								<th>更新时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" varStatus="status" items="${data.result}">
								<tr view="/log/view.do?resID=35&logID=${item.logID}">
									<td>${data.pagination.index*data.pagination.pageSize + status.index + 1}</td>
									<td>${item.operName }</td>
									<td>${item.operType }</td>
									<td>${item.busiDataType }</td>
									<td>${item.remark}</td>
									<td><fmt:formatDate value="${item.createTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${item.updateTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
							<c:if test="${empty data.result}">
								<tr>
									<td colspan="8" class="warning">未查询到数据</td>
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
			var business = new Business({base_path : "${basePath}" , currentPage : ${data.pagination.index+1} , totalPages : ${data.pagination.totalPage} ,opers:${_OPERATOR_JSON_}});
			business.init_list_page();
			seajs.use('my97date');
		});
	</script>
</body>
</html>
