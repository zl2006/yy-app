<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="functions" value="${_SITE_MAIN_DATA_.moduleFunctions}"></c:set>
<c:set var="navigators" value="${_SITE_MAIN_DATA_.navigators}"></c:set>
<!--  面包屑导航提示 -->
<ol style="margin-bottom: 5px;" class="breadcrumb">
	<span class="glyphicon glyphicon-hand-right"></span>
	<c:forEach begin="0" var="item" items="${navigators}">
		<li><a href="${item.url}">&nbsp;&nbsp;${item.name}</a></li>
	</c:forEach>
</ol>
<c:if test="${not empty  functions}">
	<!--  模块操作功能区域  -->
	<div class="well">
		<c:forEach var="item" items="${functions}">
			<span class="${item.style}"><a style="vertical-align: 3px;margin-left: 3px"
				href="${item.url}">${item.name}</a></span>
		</c:forEach>
	</div>
</c:if>
