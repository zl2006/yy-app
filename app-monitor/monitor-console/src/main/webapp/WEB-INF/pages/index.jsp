<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=contextPath%>/resources/css/pure-min.css">
<link rel="stylesheet" href="<%=contextPath%>/resources/css/site.css">
<link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
<link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
<script type="text/javascript" src="<%=contextPath%>/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/js/juicer-min.js"></script>
<title>监控系统</title>
</head>
<body>
	<div id="menu">
		<a class="title">监控类型</a>
		<ul class="monitor-type">
			<c:forEach var="plugin" items="${data.plugins}">
				<a href="${contextPath }?plugin=${plugin.code}"><li <c:if test="${plugin.code eq data.currentPlugin.code }">class="select"</c:if> >${plugin.name }</li></a>
			</c:forEach>
		</ul><!--
		<div id="system-set">
			<a href="javascript:void(0)">系统设置</a>
		</div>  -->
	</div>
	${data.content }
	<div id="add_top" style="display: inline;">
		<img src="<%=contextPath%>/resources/images/delete.png" width="30" height="30" id="del_plugin" title="删除">
		<img src="<%=contextPath%>/resources/images/edit.png" width="30" height="30"  style="margin-left:10px" id="edit_plugin" title="编辑">
		<img src="<%=contextPath%>/resources/images/add.png" width="30" height="30" style="margin-left:10px" id="add_plugin" title="新增">
	</div>
</body>
</html>