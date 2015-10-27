<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="item" items="${_SITE_MAIN_DATA_.listOperations }">
	<a href="${item.url }" style="cursor: hand;">${item.name }</a>
</c:forEach>