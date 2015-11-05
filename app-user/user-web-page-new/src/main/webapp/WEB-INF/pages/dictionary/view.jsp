<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,字典详情</title>
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
                            <label>字典类型:</label>${data.type }
                        </div>
                         <div class="pure-control-group">
                            <label>字典名称:</label>${data.name }
                        </div>
                        <div class="pure-control-group">
                            <label>字典编码:</label>${data.dicCode }
                        </div>
                         <div class="pure-control-group">
                            <label>字典值:</label>${data.value }
                        </div>
                        <div class="pure-control-group">
                            <label>序号:</label>${data.orderNO }
                        </div>
                        <div class="pure-control-group">
                            <label>状态:</label><c:choose><c:when test="${data.status == 1}">有效</c:when><c:when test="${data.status == 0}">无效</c:when> </c:choose>
                        </div>
                        <div class="pure-control-group">
                            <label> 描述:</label>${data.description }
                        </div>
                    </div> 
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/dic/list.do">返回</a>
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
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"business","oper":"view"}' ></script>
</body>
</html>		