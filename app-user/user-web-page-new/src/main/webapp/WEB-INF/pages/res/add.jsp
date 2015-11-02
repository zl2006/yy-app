<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,增加资源</title>
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
            <form action="${basePath }/res/save.json" id="save_update_form" method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="name">资源名称:</label>
                            <input id="name" name="name" type="text" placeholder="资源名称"  data-rule="required;length[~64]" class="pure-u-1-5">
                        </div>
                         <div class="pure-control-group">
                            <label for="ename">资源英文名称:</label>
                            <input id="ename" name="ename" type="text" placeholder="资源英文名称"  data-rule="required;length[~64]" class="pure-u-1-5">
                        </div>
                         <div class="pure-control-group">
                            <label for="type"  class="pure-radio">资源类型:</label>
                            <input name="type" type="radio" value="0" checked="checked">菜单（模块）
                            <input name="type" type="radio" value="1" class="place-40">子菜单（子模块）
                            <input name="type" type="radio" value="2" class="place-40">子菜单项（功能）
                            <input name="type" type="radio" value="3" class="place-40">列表操作
                            <input name="type" type="radio" value="4" class="place-40">按钮等操作
                        </div>
                        <div class="pure-control-group">
                            <label for="systemName">资源所属系统:</label>
                            <input type="text" placeholder="选择系统" id="systemName" name="systemName"  data-rule="required" class="form-control" readonly="readonly">
							<input type="hidden" name="systemCode" id="systemCode">
							 <a class="button-xsmall pure-button pure-button-primary" id="selectSystem">选择</a>
                        </div>
                         <div class="pure-control-group">
                            <label for="tel">父资源:</label>
                            <input type="text" placeholder="选择资源" id="parentName" class="form-control" readonly="readonly">
							<input type="hidden" name="parentResID" id="parentResID" >
							<a class="button-xsmall pure-button pure-button-primary" id="selectResource">选择</a>
						</div>
                        <div class="pure-control-group">
                            <label for="url">资源URL: </label>
                            <input type="text" placeholder="资源URL" name="url" 	id="url" class="form-control" data-rule="required;length[~128]"  >
                        </div>
                        <div class="pure-control-group">
                            <label for="icon" >资源图标： </label>
                            <input type="text" placeholder="资源图标" name="icon" id="icon" class="form-control" data-rule="length[~128]" >
                        </div>
                        <div class="pure-control-group">
                            <label for="icon" >样式： </label>
                            <input type="text" placeholder="样式" name="style" id="style"  data-rule="length[~128]" class="pure-u-1-2">
                        </div>
                        <div class="pure-control-group">
                            <label for="status" >状态： </label>
                           <input type="radio" 	name="status" value="1" checked="checked"> 有效
							<input type="radio" name="status" value="0"> 无效
                        </div>
                        <div class="pure-control-group">
                            <label for="description" >描述： </label>
                           <textarea rows="4" cols="60" name="description"></textarea>
                        </div>
                        <div class="pure-control-group">
                            <label for="orderNO" >顺序： </label>
                            <input type="text" placeholder="顺序" name="orderNO" id="orderNO"   value="1">
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
    require(['jqvalidator',/* 'sticky',*/ 'jqsuperslide','res_business'], function ($, /*sticky,*/ slide, Business) {
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