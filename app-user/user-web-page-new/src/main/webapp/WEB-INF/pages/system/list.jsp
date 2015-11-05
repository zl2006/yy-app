<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,系统列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
    <link type="text/css" rel="stylesheet" href="${basePath }/resources/js/jqlayer/2.0/skin/layer.css"/>
</head>
<body>
 <%@ include file="/WEB-INF/pages/common/top.jsp"%>
 <%@ include file="/WEB-INF/pages/common/menu.jsp"%>

<!--页面主体区-->
<div id="main" class="clearfix">
    <%@ include file="/WEB-INF/pages/common/leftmenu.jsp"%>
    <div id="content_wrap"><!--内容区-->
        <div class="content">
             <%@ include file="/WEB-INF/pages/common/point.jsp"%>
            <form class="pure-form search" id="query_form" action="${basePath}/system/list.do" method="post"><!--搜索表单-->
            	<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
                <label>系统编号：</label>
                <input name="systemCode" type="text"  class="pure-u-1-5"  value="${params.systemCode }">

                <label>名称：</label>
                <input name="name" type="text"  class="pure-u-1-5"  value="${params.name }">

                <button id="submitBtn" class="pure-button pure-button-primary">搜索</button>
            </form><!--search end-->
            <table class="pure-table search-res" width="100%" id="data_table"><!--搜索结果 -->
                <thead>
               		 <tr>
						<th>序号</th>
						<th>应用名称</th>
						<th>应用编号</th>
						<th>应用URL</th>
						<th>描述</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
                </thead>
                <tbody>
                <c:forEach var="item" varStatus="status" items="${data.result}">
                <tr class="pointer <c:if test="${ (status.index+1) % 2 == 0}">odd</c:if>" view="/system/view.do?systemCode=${item.systemCode}"   >
                    <td>${status.index + 1 + ( data.pagination.index * data.pagination.pageSize )}</td>
                    <td>${item.name }</td>
					<td>${item.systemCode}</td>
					<td>${item.url }</td>
					<td>${item.description }</td>
					<td><c:choose><c:when test="${item.status == 1}">有效</c:when><c:when test="${item.status == 0}">无效</c:when> </c:choose></td>
              		 <td width="130">
                    	<c:forEach var="itemoper" items="${_SITE_MAIN_DATA_.listOperations }">
							<a href="${itemoper.url }?systemCode=${item.systemCode}" style="cursor: hand;"  class="list_oper" ename="${itemoper.ename }">${itemoper.name }</a>
						</c:forEach>
                    </td>
                </tr>
                </c:forEach>
                 <c:if test="${empty data.result}">
					<tr>
						<td colspan="8" class="warning">未查询到数据</td>
					</tr>
				</c:if>
                </tbody>
            </table>
            <ul class="pagination" id="pagination"></ul><!-- 分页组件 -->
        </div><!-- content end-->
    </div>
</div><!--main end-->

<!-- 尾部区-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>


<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"business","oper":"list"}' data='{"base_path" : "${basePath}" ,"row_click":true, "currentPage" : ${data.pagination.index+1} , "totalPages" : ${data.pagination.totalPage}}'></script>
</body>
</html>