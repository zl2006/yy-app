<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑用户</title>
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
            <form action="${basePath }/user/edit.json" id="save_update_form" method="post">
            	<input type="hidden" name="userID" value="${data.userID }"/>
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>登录账号:</label>
                            <input name="loginID" type="text"  class="pure-u-1-5"  value="${data.loginID }" readonly>
                        </div>
                         <div class="pure-control-group">
                            <label>用户姓名:</label>
                            <input name="name" type="text"  data-rule="required;length[~64]" class="pure-u-1-5" value="${data.name }">
                        </div>
                        <div class="pure-control-group">
                            <label>邮箱:</label>
                            <input id="email" name="email" type="text"   data-rule="email;length[~128]" class="pure-u-2-5" value="${data.email}">
                        </div>
                         <div class="pure-control-group">
                            <label>电话:</label>
                            <input id="tel" name="tel" type="text"   data-rule="length[~32]" class="pure-u-1-5" value="${data.tel}">
                        </div>
                        <div class="pure-control-group">
                            <label>性别:</label>
                            <input name="sex" type="radio" value="M" <c:if test="${data.sex == 'M'}">checked="checked"</c:if> >男
                            <input name="sex" type="radio" value="F" class="place-40" <c:if test="${data.sex == 'F'}">checked="checked"</c:if> >女
                        </div>
                        <div class="pure-control-group">
                            <label for="status">状态： </label>
                            <input name="status" type="radio" value="1" <c:if test="${data.status == 1}">checked="checked"</c:if> >有效
                            <input name="status" type="radio" value="0" class="place-40"  <c:if test="${data.status == 0}">checked="checked"</c:if> >无效
                        </div>
                    </div>
                    <div class="form-area">
                        <div class="title">
                            二、扩展信息<a href="javascript:void(0)" class="showhide">隐藏</a>
                        </div>
                        <fieldset>
                           <div class="pure-control-group">
	                            <label>地址:</label>
	                            <input name="address" type="text"   data-rule="length[~128]" class="pure-u-2-5" value="${data.address}">
	                        </div>
	                        <div class="pure-control-group">
	                            <label>生日：</label>
	                            <input name="birthday" type="text"   class="Wdate pure-u-1-8 " onclick="WdatePicker()"  value="<fmt:formatDate value="${data.birthday}" type="date"></fmt:formatDate>">
	                        </div>
	                        <div class="pure-control-group">
	                            <label>所在省市:</label>
	                            <input name="latnNo" type="text"   data-rule="length[~16]" class="pure-u-2-5"  value="${data.latnNo}">
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="organName">组织机构：</label>
	                            <input id="organName" name="organName" type="text" placeholder="请选择组织机构"  class="pure-u-1-5"  value="${data.organs[0].name }"  readonly>
	                            <input type="hidden" name="organs[0].organCode" id="organCode"   value="${data.organs[0].organCode }"  >
	                            <a class="button-xsmall pure-button pure-button-primary" id="selectOrgan">选择</a>
	                        </div>
                        </fieldset>
                    </div>  
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<button type="button" id="submitBtn" class="pure-button pure-button-primary pure-u-1-8 button-large">提交</button>
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/user/list.do">返回</a>
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
<script language="javascript" type="text/javascript" src="${basePath }/resources/js/my97date/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		