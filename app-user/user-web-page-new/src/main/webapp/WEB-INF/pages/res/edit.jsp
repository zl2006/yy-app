<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑资源</title>
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
            <form action="${basePath }/res/update.json" id="save_update_form" method="post">
            	<input type="hidden" name="myResID" value="${data.res.resID }">
				<input type="hidden" name="status" value="${data.res.status}" >
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>资源名称:</label>
                            <input name="name" type="text"    data-rule="length[~64]" class="pure-u-1-5"  value="${data.res.name }" readonly>
                        </div>
                         <div class="pure-control-group">
                            <label>资源英文名称:</label>
                            <input name="ename" type="text"  data-rule="length[~64]" class="pure-u-1-5" value="${data.res.ename }">
                        </div>
                        <div class="pure-control-group">
                            <label>资源类型:</label>
							<input type="radio" name="type" value="0" <c:if test="${data.res.type == 0  }">checked="checked"</c:if> > 菜单（模块）
							<input type="radio" name="type" value="1" <c:if test="${data.res.type == 1  }">checked="checked"</c:if> > 子菜单（子模块）
							<input type="radio" name="type" value="2" <c:if test="${data.res.type == 2  }">checked="checked"</c:if> > 子菜单项（功能）
							<input type="radio" name="type" value="3" <c:if test="${data.res.type == 3  }">checked="checked"</c:if> > 列表操作
							<input type="radio" name="type" value="3" <c:if test="${data.res.type == 4  }">checked="checked"</c:if> > 按钮等操作
                        </div>
                         <div class="pure-control-group">
                            <label>资源所属系统:</label>
                            <input name="systemName" type="text"   data-rule="required" class="pure-u-1-5" value="${data.system.name }" readonly="readonly">
                            <input type="hidden" name="systemCode" id="systemCode" value="${data.system.systemCode }">
                            <a class="button-xsmall pure-button pure-button-primary" id="selectSystem">选择</a>
                            <a class="button-xsmall pure-button pure-button-primary" id="clearSystem">清除</a>
                        </div>
                        <div class="pure-control-group">
                            <label>父资源:</label>
                            <input type="text"  id="parentName" value="${data.parentRes.name }"  class="pure-u-1-5"  readonly="readonly">
							<input type="hidden" name="parentResID" id="parentResID"  value="${data.res.parentResID }">
							<a class="button-xsmall pure-button pure-button-primary" id="selectResource">选择</a>
							<a class="button-xsmall pure-button pure-button-primary" id="clearResource">清除</a>
                        </div>
                        <div class="pure-control-group">
                            <label>资源URL: </label>
                            <input type="text"  name="url" 	 data-rule="required;length[~128]"    value="${data.res.url }">
                        </div>
                         <div class="pure-control-group">
                            <label for="icon" >资源图标： </label>
                            <input type="text"  name="icon" data-rule="length[~128]"   value="${data.res.icon}"  class="pure-u-1-5" >
                        </div>
                         <div class="pure-control-group">
                            <label for="icon" >样式： </label>
                            <input type="text"  name="style" data-rule="length[~128]" class="pure-u-1-2" value="${data.res.style}">
                        </div>
                         <div class="pure-control-group">
                            <label for="description" >描述： </label>
                           <textarea rows="4" cols="45" name="description" data-rule="length[~128]" >${data.res.description}</textarea>
                        </div>
                         <div class="pure-control-group">
                             <label for="orderNO" >顺序： </label>
                            <input type="text"  name="orderNO" id="orderNO"   value="${data.res.orderNO }" data-rule="digits"  class="pure-u-1-5" >
                        </div>
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<button type="button" id="submitBtn" class="pure-button pure-button-primary pure-u-1-8 button-large">提交</button>
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/res/list.do">返回</a>
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
    require(['jqvalidator',  'jqsuperslide','res_business'], function ($,   slide, Business) {
    	$(document).ready(function() { 
            slide("#nav").slide({ titCell: "h3",  targetCell: "ul",    defaultIndex: 1, effect: "slideDown", delayTime: 300,  trigger: "click",  defaultPlay: false, returnDefault: false  });
            slide("#site-menu").slide({  type: "menu",  titCell: ".menu-item", targetCell: ".menu-item-sub", delayTime: 400, triggerTime: 0, returnDefault: false });
            var business = new Business({base_path : "${basePath}" });
    		business.init_saveorupdate_page();
    	});
    })
</script>
</body>
</html>		