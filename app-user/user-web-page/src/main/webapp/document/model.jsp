<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${basePath}/resources/js/document/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${basePath}/resources/js/document/docs.css">
<link href="${basePath }/resources/js/document/json/s.css" type="text/css" rel="stylesheet"></link>
<title>${data.name}</title>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">系统服务文档</a>
			</div>
		</div>
	</header>

	<div class="container bs-docs-container">
		<div class="row">
			<!-- 实体名细 -->
			<div class="col-md-12" role="main">
				<div class="bs-docs-section">

					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="baseinfo">基础信息</a>
						</h4>
					</div>
					<div>
						<b>◆实体名称:</b>${data.name}
					</div>
					<div>
						<b>◆实体描述:</b>${data.description}
					</div>
					<div>
						<b>◆服实体类名: </b> ${data.className}
					</div>


					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="inputparam">字段信息</a>
						</h4>
					</div>
					<c:if test="${empty data.fields}">
						无字段描述信息
					</c:if>
					<c:if test="${ !empty data.fields}">
						<table class="table table-bordered" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>中文名称</th>
									<th>字段名称</th>
									<th>是否数组或LIst</th>
									<th>描述</th>
									<th>字段类</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${data.fields}" varStatus="idx1">
									<tr>
										<td>${idx1.index+1}</td>
										<td>${item.name}</td>
										<td>${item.fieldName}</td>
										<td><c:if test="${item.islist}">是</c:if><c:if test="${not item.islist}">否</c:if></td>
										<td>${item.description}</td>
										<td><a
											href="${basePath}/document.do?method=model&className=${item.classname}">${item.classname}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>


				</div>
			</div>
		</div>
	</div>


	<script src="${basePath }/resources/js/document/json/c.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/jquery.form.min.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/json/c.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/date/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.ImgCollapsed = "${basePath }/resources/js/document/json/Collapsed.gif";
		window.ImgExpanded = "${basePath }/resources/js/document/json/Expanded.gif";
	</script>
</body>
</html>