<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,字典列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
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
            <form class="pure-form search" id="query_form" action="${basePath}/dic/list.do" method="post"><!--搜索表单-->
            	<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
                <label>字典编码：</label>
                <input name="dicCode" type="text" class="pure-u-1-5" value="${params.dicCode }">
                <label>字典名称：</label>
                <input name="name" type="text"  class="pure-u-1-5"  value="${params.name }">
                <label>字典类型：</label>
                <input name="type" type="text"  class="pure-u-1-5"  value="${params.type}">
                <button id="submitBtn" class="pure-button pure-button-primary">搜索</button>
            </form><!--search end-->
            <table class="pure-table search-res" width="100%" id="data_table"><!--搜索结果 -->
                <thead>
               		<tr>
						<th>序号</th>
						<th>字典编码</th>
						<th>字典名称</th>
						<th>字典值</th>
						<th>字典类型</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
                </thead>
                <tbody>
                <c:forEach var="item" varStatus="status" items="${data.result}">
                <tr class="pointer <c:if test="${ (status.index+1) % 2 == 0}">odd</c:if>"   view="/dic/view.do?dicID=${item.dicID}"  >
                    <td>${data.pagination.index*data.pagination.pageSize + status.index + 1}</td>
					<td>${item.dicCode }</td>
					<td>${item.name }</td>
					<td>${item.value }</td>
					<td>${item.type}</td>
					<td><c:choose><c:when test="${item.status == 1}">有效</c:when><c:when test="${item.status == 0}">无效</c:when> </c:choose></td>
					<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td>
                    <td width="120">
                    	<c:forEach var="itemoper" items="${_SITE_MAIN_DATA_.listOperations }">
							<a href="${itemoper.url }?dicID=${item.dicID}"  class="list_oper"  ename="${itemoper.ename }">${itemoper.name }</a>
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