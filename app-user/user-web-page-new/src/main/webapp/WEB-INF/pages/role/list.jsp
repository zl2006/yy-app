<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,角色列表</title>
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
            <form class="pure-form search" id="query_form" action="${basePath}/role/list.do" method="post"><!--搜索表单-->
            	<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
                <label for="roleCode">编码：</label>
                <input name="roleCode" type="text"  class="pure-u-1-5"  value="${params.roleCode }">

                <label for="name">名称：</label>
                <input name="name" type="text"  class="pure-u-1-5"  value="${params.name }">

                <label for="systemCode">系统：</label>
                <input name="systemCode" type="text"  class="pure-u-1-5"  value="${params.systemCode }">

                <button id="submitBtn" class="pure-button pure-button-primary"><i class="fa fa-search"></i>搜索</button>
            </form><!--search end-->
            <table class="pure-table search-res" width="100%" id="data_table"><!--搜索结果 -->
                <thead>
               		<tr>
						<th>序号</th>
						<th>编码</th>
						<th>名称</th>
						<th>所属系统</th>
						<th>状态</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
                </thead>
                <tbody>
                <c:forEach var="item" varStatus="status" items="${data.result}">
                <tr style="cursor: hand"  view="/role/view.do?roleCode=${item.roleCode}" <c:if test="${ (status.index+1) % 2 == 0}">class="odd"</c:if>  >
                    <td>${status.index + 1 + ( data.pagination.index * data.pagination.pageSize )}</td>
                	<td>${item.roleCode }</td>
					<td>${item.name }</td>
					<td>${item.systemCode }</td>
					<td><c:if test="${item.status == 1}">有效</c:if> <c:if test="${item.status == 0}">无效</c:if></td>
					<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td>
                    <td width="150">
                    	<c:forEach var="itemoper" items="${_SITE_MAIN_DATA_.listOperations }">
							<a href="${itemoper.url }?roleCode=${item.roleCode}" style="cursor: hand;"  class="list_oper" ename="${itemoper.ename }">${itemoper.name }</a>
						</c:forEach>
                    </td>
                </tr>
                </c:forEach>
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
<script type="text/javascript">
    requirejs( [ 'jqsuperslide', /*'sticky',*/'business' ], function (slide/*,sticky*/,Business) {
        //sticky("#menu", {top:0, left:0});
        slide("#nav").slide({titCell:"h3", targetCell:"ul",defaultIndex:1,effect:"slideDown",delayTime:300,trigger:"click",defaultPlay:false,returnDefault:false});
        slide("#site-menu").slide({ type: "menu", titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false  });
       	var business = new Business({base_path : "${basePath}" ,row_click:true, currentPage : ${data.pagination.index+1} , totalPages : ${data.pagination.totalPage}});
		business.init_list_page();
    })
</script>
</body>
</html>