<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑机构</title>
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
            <form action="${basePath }/organ/edit.json" id="save_update_form" method="post">
            	<input type="hidden" name="organID" value="${data.organ.organID }"/>
				<input type="hidden" name="status" value="${data.organ.status }"/>
				<input type="hidden" name="hasChild" value="${data.organ.hasChild }"/>
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label for="organCode">组织机构编号:</label>
                             <input type="text"   id="organCode"  class="pure-u-1-5"  name="organCode" class="form-control" readonly="readonly" value="${data.organ.organCode }">
                        </div>
                         <div class="pure-control-group">
                            <label for="name">父组织机构:</label>
                            <input type="text"  id="parentOrganName" readonly="readonly" id="parentOrganName" value="${data.parentOrgan.name}">
							<input type="hidden" name="parentOrganCode" id="parentOrganCode"  value="${data.parentOrgan.organCode}">
							<a class="button-xsmall pure-button pure-button-primary" id="selectOrgan">选择</a>
                        </div>
                        <div class="pure-control-group">
                            <label for="name">机构名称:</label>
                            <input type="text"  name="name" id="name"  class="pure-u-1-5"   data-rule="required;length[~128]"  value="${data.organ.name }">
                        </div>
                         <div class="pure-control-group">
                            <label for="principal">主负责人:</label>
                            <input type="text"  name="principal" id="principal"  class="pure-u-1-5"   data-rule="length[~64]"   value="${data.organ.principal}">
                        </div>
                        <div class="pure-control-group">
                            <label for="secondPrincipal" >第二负责人： </label>
                            <input type="text"  name="secondPrincipal" id="secondPrincipal"
										class="pure-u-1-5"   data-rule="length[~64]"   value="${data.organ.secondPrincipal}">
                        </div>
                        <div class="pure-control-group">
                            <label for="tel" >电话： </label>
                            <input type="text"   name="tel" id="tel" class="pure-u-1-5"   data-rule="length[~32]" value="${data.organ.tel}">
                        </div>
                        <div class="pure-control-group">
                            <label for="fax" >传真： </label>
                           <input type="text"  name="fax" id="fax" class="pure-u-1-5"   data-rule="length[~32]"  value="${data.organ.fax}">
                        </div>
                        <div class="pure-control-group">
                            <label for="postCode" >邮编： </label>
                            <input type="text" name="postCode" id="postCode" class="pure-u-1-5"   data-rule="length[~32]" value="${data.organ.postCode}">
                        </div>
                        <div class="pure-control-group">
                            <label for="description" >机构描述： </label>
                            <textarea rows="5" cols="35" placeholder="机构描述" name="description" 	id="description" data-rule="length[~255]"  >${data.organ.description}</textarea>
                        </div>
                        
                    </div>
                </div><!-- pure-form -->
                <div class="form-oper">
                    	<button type="button" id="submitBtn" class="pure-button pure-button-primary pure-u-1-8 button-large">提交</button>
                    	<a class="pure-button button-large pure-u-1-8" href="${basePath }/organ/list.do">返回</a>
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
    		
    		
    		$("#selectOrgan").on('click', function(event){
				//按需要才加载JS文件
				require(['organ'],function(OrganSelectModal){
					var organModal = new OrganSelectModal({"basePath" : "${basePath}" ,  "selectedOrgan":function(organCode,organName) {
						$('#parentOrganCode').val(organCode);
						$('#parentOrganName').val(organName);
						organModal.close();
					}});
					organModal.open();
				});
			});
    	});
    })
</script>
</body>
</html>		