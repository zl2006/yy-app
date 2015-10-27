<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="navigators" value="${_SITE_MAIN_DATA_.navigators}"></c:set>
<div class="posi">
	当前位置：
	<c:if test="${fn:length(navigators) gt 0}">
                		${navigators[0].name} 
                		<!--  面包屑导航提示 -->
		<c:forEach begin="1" var="item" items="${navigators}">
			 -- <a href="${item.url}">&nbsp;&nbsp;${item.name}</a>
		</c:forEach>
	</c:if>
</div>