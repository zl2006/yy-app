<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,用户详情</title>
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
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="loginID">登录账号:</label>${data.user.loginID }
                        </div>
                         <div class="pure-control-group">
                            <label for="name">用户姓名:</label>${data.user.name }
                        </div>
                        <div class="pure-control-group">
                            <label for="email">邮箱:</label>${data.user.email }
                        </div>
                         <div class="pure-control-group">
                            <label for="tel">电话:</label>${data.user.tel }
                        </div>
                        <div class="pure-control-group">
                            <label for="sex">性别： </label>
                            <c:if test="${data.user.sex == 'F'}">女</c:if>
							<c:if test="${data.user.sex == 'M'}">男</c:if>
                        </div>
                        <div class="pure-control-group">
                            <label for="status" >状态： </label>
                            <c:if test="${data.user.status ==1}">有效</c:if>
							<c:if test="${data.user.status ==0}">无效</c:if>
                        </div>
                    </div>
                    <div class="form-area">
                        <div class="title">
                            二、扩展信息
                        </div>
                        <fieldset>
                           <div class="pure-control-group">
	                            <label for="address">地址:</label>${data.user.address }
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="birthday">生日：</label><fmt:formatDate value="${data.user.birthday}" type="date"></fmt:formatDate>
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="latnNo">所在省市:</label>${data.user.latnNo }
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="organName">组织机构：</label>${data.user.organs[0].name }
	                        </div>
	                         <div class="pure-control-group">
	                            <label for="organName">用户组：</label>
	                            <c:forEach var="group" items="${data.groups}">${group.groupName}<span class="place-15 "></span></c:forEach>
	                        </div>
	                         <div class="pure-control-group">
	                            <label for="organName">用户角色：</label>
	                            <div   style="margin-top:-20px;margin-left:176px;margin-right:20px;padding: 0 10px 0 0">
	                            	<c:forEach var="role" items="${data.roles}">${role.name}<span class="place-15 "></span></c:forEach>
	                            </div>
	                        </div>
                        </fieldset>
                    </div>  
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/user/list.do">返回</a>
               	</div>
            </form>
            <!--form end-->
        </div>
        <!--content end-->
    </div>
</div>
<!--main end-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript">
    require(['jqvalidator',/* 'sticky',*/ 'jqsuperslide'], function ($, /*sticky,*/ slide) {
    	$(document).ready(function() { 
           // sticky("#menu", {top: 0, left: 0});
            slide("#nav").slide({ titCell: "h3",  targetCell: "ul",    defaultIndex: 1, effect: "slideDown", delayTime: 300,  trigger: "click",  defaultPlay: false, returnDefault: false  });
            slide("#site-menu").slide({  type: "menu",  titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false });
    	});
    })
</script>
</body>
</html>		