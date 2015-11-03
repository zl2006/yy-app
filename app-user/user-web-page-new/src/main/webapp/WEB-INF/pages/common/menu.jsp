<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="systems" value="${_SITE_MAIN_DATA_.systems}"></c:set>
<c:set var="resources" value="${_SITE_MAIN_DATA_.resources}"></c:set>
<c:set var="modules" value="${_SITE_MAIN_DATA_.modules}"></c:set>
<!--系统及模块区域-->
<div id="menu">
    <ul id="site-menu">
        <li class=" menu-item"><a href="${_SITE_MAIN_DATA_.indexUrl }">首页</a></li>
        <!-- 单系统菜单  -->
        <c:if test="${not _SITE_MAIN_DATA_.portalEnable }">
	        <c:forEach var="mditem" items="${modules}"
						varStatus="status">
				<li class="menu-item">
					<span><a href="${mditem.url }">${mditem.name }</a></span>
	            </li>
			</c:forEach>
		</c:if>
		<!--  门户菜单 -->
        <c:if test="${_SITE_MAIN_DATA_.portalEnable }">
	        <c:forEach begin="0" end="9" var="sysitem" items="${systems}"
						varStatus="status">
				<li class="menu-item <c:if test="${_SITE_MAIN_DATA_.systemCode == sysitem.systemCode}">select</c:if>" >
					<span><a href="${sysitem.url }" >${sysitem.name }</a></span>
					<ul class="menu-item-sub"><c:forEach var="mditem" items="${modules}" varStatus="status"><c:if test="${mditem.systemCode == sysitem.systemCode }"><li><a href="${mditem.url }">${mditem.name }</a></li></c:if></c:forEach></ul>
	            </li>
			</c:forEach>
		</c:if>
    </ul>
</div><!--men end-->