<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,权限配置</title>
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
            <form action="${basePath }/user/config.json" id="save_update_form" method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="loginID">登录账号:</label>
                            ${data.user.loginID }
                            <input type="hidden" name="loginID" value="${data.user.loginID }" />
							<input type="hidden" name="userID" value="${data.user.userID }" />
                        </div>
                         <div class="pure-control-group">
                            <label for="name">用户姓名:</label>
                            ${data.user.name }
                        </div>
                        <div class="pure-control-group">
                            <label for="email">邮箱:</label>
                            ${data.user.email }
                        </div>
                         <div class="pure-control-group">
                            <label for="tel">电话:</label>
                           ${data.user.tel }
                        </div>
                        <div class="pure-control-group">
                            <label for="sex" class="pure-radio">性别： </label>
                            <c:if test="${data.user.sex =='F'}">男</c:if>
							<c:if test="${data.user.sex =='M'}">女</c:if>
                        </div>
                        <div class="pure-control-group">
                            <label for="status" class="pure-radio">状态： </label>
                            <c:if test="${data.user.status ==1}">有效</c:if>
							<c:if test="${data.user.status ==0}">无效</c:if>
                        </div>
                         <div class="pure-control-group">
                            <label for="tel">组织机构:</label> ${data.user.organs[0].name }
                        </div>
                        <div class="pure-control-group">
                            <label for="groups" class="pure-checkbox">用户组： </label>
                            <c:forEach var="group" items="${data.allGroups.result }">
								<input type="checkbox"  name="groups" <c:forEach var="originGroup" items="${data.groups}">
									<c:if test="${originGroup.userGroupID == group.userGroupID }">
										checked="checked"
									</c:if>
								</c:forEach>
								value="${group.userGroupID }" />${group.groupName }<span class="place-15"></span>
							</c:forEach>
                        </div>
                         <div class="pure-control-group">
                            <label for="roles">角色:</label> 
                            <div   style="border:1px solid #d6d6d6; margin-top:-15px;margin-left:176px;margin-right:20px;padding: 0 10px 10px 10px">
								<c:forEach var="system" items="${data.allSystems.result }" varStatus="status">
									<div style="margin:0px;line-height: 25px;"><b>${status.index + 1},${system.name }(${system.systemCode})</b></div>
									<c:forEach var="role" items="${data.allRoles.result }">
										<c:if test="${system.systemCode == role.systemCode }">
											<input style="line-height: 27px" type="checkbox" name="roles" value="${role.roleCode }" <c:forEach var="originRole" items="${data.roles}">
												<c:if test="${originRole.roleCode == role.roleCode }">
													checked="checked"
												</c:if>
											</c:forEach>/>${role.name }<span class="place-15"></span>
										</c:if>
									</c:forEach><br><br>
								</c:forEach>
							</div>
                        </div>
                    </div><!-- form area -->
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
    	});
    })
</script>
</body>
</html>		