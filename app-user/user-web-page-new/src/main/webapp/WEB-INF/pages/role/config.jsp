<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,用户组配置</title>
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
            <form action="${basePath }/role/config.json" id="save_update_form" method="post">
            	<input type="hidden" name="roleCode" value="${data.role.roleCode}">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>角色编号:</label>${data.role.roleCode }
                        </div>
                         <div class="pure-control-group">
                            <label>系统:</label>${data.system.name }
                        </div>
                        <div class="pure-control-group">
                            <label>角色名称:</label>${data.role.name }
                        </div>
                         <div class="pure-control-group">
                            <label>状态:</label><c:choose><c:when test="${data.role.status == 1}">有效</c:when><c:when test="${data.role.status == 0}">无效</c:when> </c:choose> 
                        </div>
                        <div class="pure-control-group">
                            <label>描述:</label>${data.role.description }
                        </div>
                         <div class="pure-control-group">
                            <label>资源:</label> 
                            <c:if test="${not empty data.allResources  }">
                            <div   style="border:1px solid #d6d6d6; margin-top:-15px;margin-left:176px;margin-right:20px;padding: 0 10px 0px 10px;">
								<c:forEach var="mres" items="${data.allResources}"><!-- 模块(第一级) -->
										<c:if test="${mres.parentResID == -1 or empty mres.parentResID }">
												<p><input type="checkbox" value="${mres.resID }"  name="resID" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == mres.resID }">checked="checked"</c:if> </c:forEach> />${mres.name }:</p>
												<c:if test="${mres.hasChild == 1 }"><!-- 子模块(第二级) -->
													<c:forEach var="submres" items="${data.allResources}">
														<c:if test="${submres.parentResID == mres.resID}">
															<input type="checkbox" name="resID" value="${submres.resID }" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == submres.resID }">checked="checked"</c:if> </c:forEach> />${submres.name }
																<c:if test="${submres.hasChild == 1 }"><!-- 列表(第三级) -->
																	<c:forEach var="funcres" items="${data.allResources}">
																		<c:if test="${funcres.parentResID == submres.resID}">
																			<input type="checkbox" name="resID" value="${funcres.resID }" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == funcres.resID }">checked="checked"</c:if> </c:forEach> />${funcres.name }
																			<c:if test="${funcres.hasChild == 1 }"><!-- 按钮功能(第四级) -->
																				<c:forEach var="btnres" items="${data.allResources}">
																					<c:if test="${btnres.parentResID == funcres.resID}">
																						<input type="checkbox" name="resID" value="${btnres.resID }" <c:forEach var="item" items="${data.resources}"> <c:if test="${item.resID == btnres.resID }">checked="checked"</c:if> </c:forEach> />${btnres.name }
																					</c:if>
																				</c:forEach>
																			</c:if>
																		</c:if>
																	</c:forEach>
																</c:if>
														</c:if>
													</c:forEach>
												</c:if>
										 </c:if>
								</c:forEach>
							</div>
							</c:if>
                        </div><!-- role -->
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
 
<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		