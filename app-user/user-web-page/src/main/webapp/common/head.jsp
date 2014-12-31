<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="systems" value="${_SITE_MAIN_DATA_.systems}"></c:set>
<!--  网站头部信息(应用系统列表) -->
<div role="navigation" class="navbar navbar-inverse navbar-fixed-top ">
	<div class="container">
		<div class="navbar-header">
			<a href="#" class="navbar-brand">电商平台应用中心</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:forEach begin="0" end="4" var="item" items="${systems}"
					varStatus="status">
					<li
						<c:if test="${_SITE_MAIN_DATA_.systemCode == item.systemCode}">class="active"</c:if>><a
						href="${item.url}">${item.name}</a></li>
				</c:forEach>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">更多应用<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<c:forEach begin="5" var="item" items="${systems}"
							varStatus="status">
							<li
								<c:if test="${_SITE_MAIN_DATA_.systemCode == item.systemCode}">class="active"</c:if>><a
								href="${item.url}">${item.name}</a></li>
						</c:forEach>
						<!--  
							<li class="divider"></li>-->
					</ul></li>
			</ul>
			<div class="pull-right" style="color: white;">
				${_SITE_MAIN_DATA_.user.name},您好!
				当前系统：${_SITE_MAIN_DATA_.currentSystem.name}. &nbsp;<a href="#">【退出】</a>
			</div>
		</div>
	</div>
</div>