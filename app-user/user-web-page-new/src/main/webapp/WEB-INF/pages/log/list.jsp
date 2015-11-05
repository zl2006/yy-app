<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,日志列表</title>
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
            <form class="pure-form search" id="query_form" action="${basePath}/log/list.do" method="post"><!--搜索表单-->
            	<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
                <label>操作者：</label>
                <input name="operName" type="text"   class="pure-u-1-6" value="${params.operName }">

				<label>日志时间：</label>
                <input type="text" class="pure-u-1-6" id="startCreateTime" placeholder="起始时间" name="startCreateTime" value="<fmt:formatDate value="${params.startCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">到
				<input type="text" class="pure-u-1-6" id="endCreateTime" placeholder="结束时间" name="endCreateTime" value="<fmt:formatDate value="${params.endCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                
                <label>业务类型：</label>
                <input name="busiDataType" type="text" class="pure-u-1-8" value="${params.busiDataType }">

                <button id="submitBtn" class="pure-button pure-button-primary">搜索</button>
            </form><!--search end-->
            <table class="pure-table search-res" width="100%" id="data_table"><!--搜索结果 -->
                <thead>
               		 <tr>
						<th>序号</th>
						<th>操作者名称</th>
						<th>操作类型</th>
						<th>业务数据类型</th>
						<th>描述</th>
						<th>创建时间</th>
						<th>更新时间</th>
					</tr>
                </thead>
                <tbody>
                <c:forEach var="item" varStatus="status" items="${data.result}">
					<tr view="/log/view.do?logID=${item.logID}" class="pointer <c:if test="${ (status.index+1) % 2 == 0}">odd</c:if>">
						<td>${data.pagination.index*data.pagination.pageSize + status.index + 1}</td>
						<td>${item.operName }</td>
						<td>${item.operType }</td>
						<td>${item.busiDataType }</td>
						<td>${item.remark}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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

<script language="javascript" type="text/javascript" src="${basePath }/resources/js/my97date/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"business","oper":"list"}' data='{"base_path" : "${basePath}" ,"row_click":true, "currentPage" : ${data.pagination.index+1} , "totalPages" : ${data.pagination.totalPage}}'></script>
</body>
</html>