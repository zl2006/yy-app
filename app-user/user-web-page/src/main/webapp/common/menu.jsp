<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="menus" value="${_SITE_MAIN_DATA_.modules}"></c:set>
<!--  菜单区域 -->
<div class="col-md-2">
	<div role="complementary" class="bs-sidebar hidden-print affix">
		<ul class="nav bs-sidenav">
			<!-- 主菜单 -->
			<c:forEach var="item" items="${menus}">
				<c:if test="${item.parentResID == -1 }">
					<!-- li class="active"-->
					<li><a href="${item.url}">${item.name }</a>
						<!-- 子菜单 -->
						<ul class="nav">
							<c:forEach var="childItem" items="${menus}">
								<c:if test="${childItem.parentResID == item.resID }">
									<li><a href="${childItem.url}">${childItem.name }</a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>