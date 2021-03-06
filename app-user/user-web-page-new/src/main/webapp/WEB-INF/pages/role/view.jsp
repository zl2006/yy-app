<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,角色详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
</head>
<body>
 <%@ include file="/WEB-INF/pages/common/top.jsp"%>
 <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
 
<div id="main" class="clearfix">
     <%@ include file="/WEB-INF/pages/common/leftmenu.jsp"%>
    <div class="content_wrap">
        <div class="content">
             <%@ include file="/WEB-INF/pages/common/point.jsp"%>
            <form action="#"  method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、角色信息
                        </div>
                        <div class="pure-control-group">
                            <label>角色编号:</label>${data.role.roleCode }
                        </div>
                         <div class="pure-control-group">
                            <label>系统:</label>${data.system.name }
                        </div>
                        <div class="pure-control-group">
                            <label>角色名称:</label>${data.role.name}
                        </div>
                         <div class="pure-control-group">
                            <label>状态:</label><c:choose><c:when test="${data.role.status == 1}">有效</c:when><c:when test="${data.role.status == 0}">无效</c:when> </c:choose> 
                        </div>
                        <div class="pure-control-group">
                            <label>描述:</label>${data.role.description }
                        </div>
                        <div class="pure-control-group">
							<label>拥有资源:</label>
							<div   style="margin-top:-20px;margin-left:176px;margin-right:20px;padding: 0 10px 10px 0">
								<c:forEach var="res" items="${data.resources}">
									<c:if test="${res.parentResID == -1 or empty res.parentResID }">
										${res.name }:
										<c:forEach var="childRes" items="${data.resources}">
											<c:if test="${childRes.parentResID == res.resID}">
												${childRes.name }
											</c:if>
										</c:forEach>
										<br>
									</c:if>
								</c:forEach>
							</div>
						</div>
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/role/list.do">返回</a>
               	</div>
            </form>
            <!--form end-->
        </div>
        <!--content end-->
    </div>
</div>
<!--main end-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>
 
<script type="text/javascript" src='${applicationScope.pageConfig["resources.path"] }/app/user/js/page.js' page='{"module":"business","oper":"view"}' ></script>
</body>
</html>		