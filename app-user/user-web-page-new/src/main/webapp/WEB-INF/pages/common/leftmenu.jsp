<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <c:set var="subModules" value="${_SITE_MAIN_DATA_.subModules}"></c:set>
 <c:set var="moduleFunctions" value="${_SITE_MAIN_DATA_.moduleFunctions}"></c:set>
 <c:set var="currentModule" value="${_SITE_MAIN_DATA_.currentModule}"></c:set>
 <c:set var="subModuleFunctions" value="${_SITE_MAIN_DATA_.subModuleFunctions}"></c:set>
 <c:set var="currentModuleFunc" value="${_SITE_MAIN_DATA_.currentModuleFunc}"></c:set>
 
 <div id="nav"><!--导航区-->
        <span class="title">常用功能</span>
        <c:choose>
        	<c:when test="${subModules == null || fn:length(subModules) eq 0 }"><!-- 无子模块下直接显示模块下的功能列表 -->
        		<h3 class="on n_parent"><i class="fa fa-male"></i>${currentModule.name }</h3>
		        <ul style="display: block;"><!--  <ul style="display: none;"> -->
		        	<c:forEach var="funcitem" items="${moduleFunctions }">
		        		<li class="n_child <c:if test="${ currentModuleFunc.resID eq funcitem.resID}">select</c:if>">-<a href="${funcitem.url }">${funcitem.name }</a></li>
		        	</c:forEach>
		        </ul>
        	</c:when>
        	<c:otherwise>
        		<c:forEach var="submditem" items="${subModules }"><!-- 有子模块下直接显示子模块下的功能列表 -->
	        		 <h3 class="on n_parent"><i class="fa fa-male"></i>${submditem.name }</h3>
			        <ul style="display: block;">
			        	<c:forEach var="funcitem" items="${subModuleFunctions }">
			        		<c:if test="${funcitem.parentResID eq submditem.resID}">
			           			 <li class="n_child <c:if test="${ currentModuleFunc.resID eq funcitem.resID}">select</c:if>">-${funcitem.name}</li>
			           		 </c:if>
			            </c:forEach>
			        </ul>
		        </c:forEach>
        	</c:otherwise>
        </c:choose>
    </div>
    <!--nav end-->