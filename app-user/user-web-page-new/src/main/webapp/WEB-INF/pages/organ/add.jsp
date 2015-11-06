<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,新增机构</title>
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
            <form action="${basePath }/organ/save.json" id="save_update_form" method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>组织机构编号:</label>
                            <input  name="organCode" type="text" placeholder="组织机构编号"  data-rule="required;length[~32]" class="pure-u-1-5">
                        </div>
                         <div class="pure-control-group">
                            <label>父组织机构:</label>
                            <input type="text" placeholder="选择父组织机构" id="parentOrganName"   readonly="readonly" id="parentOrganName"  data-rule="length[~64]" class="pure-u-1-5">
                            <input type="hidden" name="parentOrganCode" id="parentOrganCode" >
                            <a class="button-xsmall pure-button pure-button-primary" id="selectOrgan">选择</a>
                            <a class="button-xsmall pure-button pure-button-primary" id="clearOrgan">清除</a>
                        </div>
                         <div class="pure-control-group">
                            <label>机构名称:</label>
                            <input type="text"  name="name"  data-rule="required;length[~128]" class="pure-u-1-5">
                        </div>
                        <div class="pure-control-group">
                            <label >主负责人:</label>
                            <input type="text"   name="principal"  data-rule="length[~64]" class="pure-u-1-5" >
                        </div>
                         <div class="pure-control-group">
                            <label>第二负责人:</label>
                            <input type="text"   name="secondPrincipal"   data-rule="length[~64]" class="pure-u-1-5" >
                        </div>
                        <div class="pure-control-group">
                            <label>电话:</label>
                           <input type="text"   name="tel"   data-rule="length[~32]" class="pure-u-1-5" >
                        </div>
                        <div class="pure-control-group">
                            <label >传真:</label>
                            <input type="text"  name="fax" data-rule="length[~32]" class="pure-u-1-5" >
                        </div>
                        <div class="pure-control-group">
                            <label>邮编:</label>
                            <input type="text"   name="postCode" data-rule="length[~32]" class="pure-u-2-5">
                        </div>
                        <div class="pure-control-group">
                            <label >机构描述:</label>
                           <textarea rows="5" cols="45" placeholder="机构描述" name="description"  data-rule="length[~255]"></textarea>
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
<script type="text/javascript" src="${bastPath }/resources/js/app-user/page.js" page='{"module":"organ_business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		