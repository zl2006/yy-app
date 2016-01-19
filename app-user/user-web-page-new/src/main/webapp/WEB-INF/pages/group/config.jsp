<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,用户组配置</title>
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
            <form action="${basePath }/group/config.json" id="save_update_form" method="post">
            	<input type="hidden" name="userGroupID" value="${data.userGroup.userGroupID }"/>
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>用户组名称:</label>${data.userGroup.groupName }
                        </div>
                         <div class="pure-control-group">
                            <label>用户组描述:</label>${data.userGroup.description }
                        </div>
                        <div class="pure-control-group">
                            <label>状态:</label><c:choose><c:when test="${data.userGroup.status == 1}">有效</c:when><c:when test="${data.userGroup.status == 0}">无效</c:when> </c:choose> 
                        </div>
                         <div class="pure-control-group">
                            <label>角色:</label> 
                            <div   style="border:1px solid #d6d6d6; margin-top:-15px;margin-left:176px;margin-right:20px;padding: 0 10px 0px 10px">
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
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/group/list.do">返回</a>
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