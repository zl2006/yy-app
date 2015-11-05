<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,资源详情</title>
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
                            <label>资源名称:</label>${data.res.name }
                        </div>
                         <div class="pure-control-group">
                            <label>资源英文名称:</label>${data.res.ename }
                        </div>
                        <div class="pure-control-group">
                            <label>资源类型:</label>
                            <c:choose>
								<c:when test="${data.res.type == 0  }">菜单（模块）</c:when>
								<c:when test="${data.res.type == 1  }">子菜单（子模块）</c:when>
								<c:when test="${data.res.type == 2  }">子菜单项（功能）</c:when>
								<c:when test="${data.res.type == 3  }">列表操作</c:when>
								<c:when test="${data.res.type == 4 }">按钮等操作</c:when>
								<c:otherwise>无</c:otherwise>
							</c:choose>
                        </div>
                         <div class="pure-control-group">
                            <label>资源所属系统:</label>${data.system.name }
                        </div>
                        <div class="pure-control-group">
                            <label>父资源:</label>${data.parentRes.name }
                        </div>
                        <div class="pure-control-group">
                            <label>资源图标:</label>${data.res.icon }
                        </div>
                        <div class="pure-control-group">
                            <label>样式:</label>${data.res.style }
                        </div>
                         <div class="pure-control-group">
                            <label>描述:</label>${data.res.description }
                        </div>
                         <div class="pure-control-group">
                            <label>顺序:</label>${data.res.orderNO }
                        </div>
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/res/list.do">返回</a>
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