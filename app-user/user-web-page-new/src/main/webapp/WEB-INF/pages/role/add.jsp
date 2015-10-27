<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,增加角色</title>
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
            <form action="${basePath }/role/save.json" id="save_update_form" method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="roleCode">角色编号:</label>
                            <input id="roleCode" name="roleCode" type="text"  placeholder="请输入角色编号"  data-rule="required;length[~64]" class="pure-u-1-5">
                            <input type="hidden" value="1" name="status">
                        </div>
                         <div class="pure-control-group">
                            <label for="systemName">应用系统:</label>
                            <input id="systemName" name="systemName" type="text" placeholder="选择系统"  class="pure-u-1-5" readonly="readonly" data-rule="required">
                            <input type="hidden" name="systemCode" id="systemCode">
                            <a class="button-xsmall pure-button pure-button-primary" id="selectSystem">选择</a>
                        </div>
                         <div class="pure-control-group">
                            <label for="name">角色名称:</label>
                            <input id="name" name="name" type="text" placeholder="请输入角色名称"  data-rule="required;length[~64]" class="pure-u-1-5">
                        </div>
                        <div class="pure-control-group">
                            <label for="description">描述:</label>
                           <textarea rows="5" cols="30" placeholder="描述" name="description" id="description" data-rule="length[~255]"  ></textarea>
                        </div>
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<button type="button" id="submitBtn" class="pure-button pure-button-primary pure-u-1-8 button-large">提交</button>
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/role/list.do">返回</a>
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
    		
    		$("#selectSystem").on('click', function(event){
				require(['system'],function(SystemSelectModal){
					var systemModal = null;
					systemModal = new SystemSelectModal({"basePath" : '${basePath}' ,  "selectedSystem":function(systemCode,name) {
						$('#systemCode').val(systemCode);
						$('#systemName').val(name);
						$('#parentResID').val('');
						$('#parentName').val('');
						systemModal.close();
					}});
					systemModal.open();
				});//seajs.use end
			});//click end  s
    	});
    })
</script>
</body>
</html>		