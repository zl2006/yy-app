<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,机构列表</title>
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
            <form class="pure-form search" id="query_form" action="${basePath}/organ/list.do" method="post"><!--搜索表单-->
            	<input type="hidden" value="${data.pagination.index}" name="pagination.index" id="pagination_index">
                <label>机构编号:</label>
                <input name="organCode" type="text"  class="pure-u-1-5"  value="${params.organCode }">
                <label>机构名称:</label>
                <input name="name" type="text"  class="pure-u-1-5"  value="${params.name }">
                <button id="submitBtn" class="pure-button pure-button-primary">搜索</button>
            </form><!--search end-->
            <table class="pure-table search-res" width="100%" id="data_table"><!--搜索结果 -->
                <thead>
               		 <tr>
						<th>序号</th>
						<th>机构名称</th>
						<th>电话</th>
						<th>邮编</th>
						<th>描述</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
                </thead>
                <tbody>
                <c:forEach var="item" varStatus="status" items="${data.result}">
                <tr class="pointer <c:if test="${ (status.index+1) % 2 == 0}">odd</c:if>" view="/organ/view.do?organCode=${item.organCode}"    id="${item.organCode}" <c:if test="${item.hasChild == 1}"> haschild="true" </c:if> <c:if test="${item.parentOrganCode ne '-1'}"> pid="${item.parentOrganCode}"</c:if>  >
                    <td>${status.index + 1 + ( data.pagination.index * data.pagination.pageSize )}(${item.organCode})</td>
                    <td>${item.name }</td>
					<td>${item.tel}</td>
					<td>${item.postCode }</td>
					<td>${item.description }</td>
                    <td><c:choose><c:when test="${item.status == 1}">有效</c:when><c:when test="${item.status == 0}">无效</c:when> </c:choose></td>
                    <td width="120">
                    	<c:forEach var="itemoper" items="${_SITE_MAIN_DATA_.listOperations }">
							<a href="${itemoper.url }?organCode=${item.organCode}"    class="list_oper" ename="${itemoper.ename }">${itemoper.name }</a>
						</c:forEach>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul class="pagination" id="pagination"></ul><!-- 分页组件 -->
            <textarea id="listoper" style="display: none">
          	    <c:forEach var="itemoper" items="${_SITE_MAIN_DATA_.listOperations }">
					<a href="${itemoper.url }?organCode=&{item.organCode}"  class="list_oper" ename="${itemoper.ename }">${itemoper.name }</a>
			</c:forEach></textarea>
        </div><!-- content end-->
    </div>
</div><!--main end-->

<!-- 尾部区-->
 <%@ include file="/WEB-INF/pages/common/footer.jsp"%>


<script type="text/javascript" src="${basePath }/resources/js/require/2.1.11/require.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/js/require.config.js"></script>
<script type="text/javascript">
    requirejs( [ 'jqsuperslide',  'organ_business' ], function (slide ,Business) {
        slide("#nav").slide({titCell:"h3", targetCell:"ul",defaultIndex:1,effect:"slideDown",delayTime:300,trigger:"click",defaultPlay:false,returnDefault:false});
        slide("#site-menu").slide({ type: "menu", titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false  });
		
		var organ_business = new Business({base_path : "${basePath}" , currentPage : ${data.pagination.index+1} , totalPages : ${data.pagination.totalPage} ,opers:[]});
		organ_business.init_list_page();
    })
</script>
</body>
</html>