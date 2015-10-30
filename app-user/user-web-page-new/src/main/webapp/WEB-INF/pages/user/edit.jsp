<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
    <link type="text/css" rel="stylesheet" href="${basePath }/resources/js/jqvalidator-amd/0.7.3/jquery.validator.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath }/resources/js/jqlayer/2.0/skin/layer.css"/>
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
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="loginID">登录账号:</label>
                            <input id="loginID" name="loginID" type="text"    data-rule="required;length[8~64]" class="pure-u-1-5"  value="${data.loginID }" readonly>
                            <input type="hidden" name="userID" value="${data.userID }"/>
                        </div>
                         <div class="pure-control-group">
                            <label for="name">用户姓名:</label>
                            <input id="name" name="name" type="text"  data-rule="required;length[~64]" class="pure-u-1-5" value="${data.name }">
                        </div>
                        <div class="pure-control-group">
                            <label for="email">邮箱:</label>
                            <input id="email" name="email" type="text"   data-rule="email;length[~128]" class="pure-u-2-5" value="${data.email}">
                        </div>
                         <div class="pure-control-group">
                            <label for="tel">电话:</label>
                            <input id="tel" name="tel" type="text"   data-rule="length[~32]" class="pure-u-1-5" value="${data.tel}">
                        </div>
                        <div class="pure-control-group">
                            <label for="sex" class="pure-radio">性别： </label>
                            <input name="sex" type="radio" value="M" <c:if test="${data.sex == 'M'}">checked="checked"</c:if> >男
                            <input name="sex" type="radio" value="F" class="place-40" <c:if test="${data.sex == 'F'}">checked="checked"</c:if> >女
                        </div>
                        <div class="pure-control-group">
                            <label for="status" class="pure-radio">状态： </label>
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
	                            <label for="address">地址:</label>
	                            <input id="address" name="address" type="text" placeholder="地址"  data-rule="length[~64]" class="pure-u-2-5" value="${data.address}">
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="birthday">生日：</label>
	                            <input id="birthday"  name="birthday" type="text" placeholder="出生日期" class="Wdate pure-u-1-8 " onclick="WdatePicker()"  value="<fmt:formatDate value="${data.birthday}" type="date"></fmt:formatDate>">
	                        </div>
	                        <div class="pure-control-group">
	                            <label for="latnNo">所在省市:</label>
	                            <input id="latnNo" name="latnNo" type="text" placeholder="所在省市"  data-rule="length[~64]" class="pure-u-2-5"  value="${data.latnNo}">
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
<script type="text/javascript">
    require(['jqvalidator',/* 'sticky',*/ 'jqsuperslide','business'], function ($, /*sticky,*/ slide, Business) {
    	$(document).ready(function() { 
           // sticky("#menu", {top: 0, left: 0});
            slide("#nav").slide({ titCell: "h3",  targetCell: "ul",    defaultIndex: 1, effect: "slideDown", delayTime: 300,  trigger: "click",  defaultPlay: false, returnDefault: false  });
            slide("#site-menu").slide({  type: "menu",  titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false });
            var business = new Business({base_path : "${basePath}" });
    		business.init_saveorupdate_page();
    		
    		
    		$("#selectOrgan").on('click', function(event){
				//按需要才加载JS文件
				require(['organ'],function(OrganSelectModal){
					var organModal = new OrganSelectModal({"basePath" : "${basePath}" ,  "selectedOrgan":function(organCode,organName) {
						$('#organCode').val(organCode);
						$('#organName').val(organName);
						organModal.close();
					}});
					organModal.open();
				});
			});
    	});
    })
</script>
</body>
</html>		