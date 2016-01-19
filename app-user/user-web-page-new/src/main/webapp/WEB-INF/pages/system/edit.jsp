<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑系统</title>
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
            <form action="${basePath }/system/edit.json" id="save_update_form" method="post">
           		 <input type="hidden" name="status" value="${data.status}">
				<input type="hidden" name="systemID" value="${data.systemID }">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>系统编号:</label>
                            <input name="systemCode" type="text"  class="pure-u-1-5" value="${data.systemCode }" readonly="readonly">
                        </div>
                         <div class="pure-control-group">
                             <label>应用名称:</label>
                            <input name="name" type="text"   data-rule="required;length[~64]" class="pure-u-1-5" value="${data.name }">
                        </div>
                        <div class="pure-control-group">
                             <label>应用图标:</label>
                            <input  name="icon" type="text"   data-rule="length[~128]" class="pure-u-2-5" value="${data.icon }">
                        </div>
                         <div class="pure-control-group">
                            <label>应用URL:</label>
                            <input name="url" type="text" data-rule="length[~128]" class="pure-u-1-5" value="${data.url}">
                        </div>
                        <div class="pure-control-group">
                            <label for="description">描述： </label>
                            <textarea rows="4" cols="45" name="description" >${data.description}</textarea>
                        </div>
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<button type="button" id="submitBtn" class="pure-button pure-button-primary pure-u-1-8 button-large">提交</button>
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/system/list.do">返回</a>
               	</div>
               	<div class="form-group"><!-- 错误信息 -->
						<div class="col-sm-offset-2 col-sm-10">
							<span id="error_info" style="color: #FF0000;">${errors }</span>
						</div>
				</div>
               	<div id="result_info" style="display:none"> </div><!-- ajax表单异步请求接收数据 -->
            </form>
            <!--form end-->
        </div>
        <!--content end-->
    </div>
</div>
<!--main end-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>
<script type="text/javascript" src='${applicationScope.pageConfig["resources.path"] }/app/user/js/page.js' page='{"module":"business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		