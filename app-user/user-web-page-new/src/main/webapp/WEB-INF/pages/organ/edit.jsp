<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,编辑机构</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/pages/common/head.jsp"%>
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
                            <label>组织机构编号:</label>
                             <input type="text"   class="pure-u-1-5"  name="organCode"  readonly="readonly" value="${data.organ.organCode }">
                        </div>
                         <div class="pure-control-group">
                            <label>父组织机构:</label>
                            <input type="text"  id="parentOrganName" readonly="readonly" id="parentOrganName" value="${data.parentOrgan.name}">
							<input type="hidden" name="parentOrganCode" id="parentOrganCode"  value="${data.parentOrgan.organCode}">
							<a class="button-xsmall pure-button pure-button-primary" id="selectOrgan">选择</a>
							 <a class="button-xsmall pure-button pure-button-primary" id="clearOrgan">清除</a>
                        </div>
                        <div class="pure-control-group">
                            <label>机构名称:</label>
                            <input type="text"  name="name" id="name"  class="pure-u-1-5"   data-rule="required;length[~128]"  value="${data.organ.name }">
                        </div>
                         <div class="pure-control-group">
                            <label >主负责人:</label>
                            <input type="text"  name="principal"  class="pure-u-1-5"   data-rule="length[~64]"   value="${data.organ.principal}">
                        </div>
                        <div class="pure-control-group">
                            <label>第二负责人: </label>
                            <input type="text"  name="secondPrincipal"   class="pure-u-1-5"   data-rule="length[~64]"   value="${data.organ.secondPrincipal}">
                        </div>
                        <div class="pure-control-group">
                            <label>电话:</label>
                            <input type="text"   name="tel"  class="pure-u-1-5"   data-rule="length[~32]" value="${data.organ.tel}">
                        </div>
                        <div class="pure-control-group">
                            <label>传真:</label>
                           <input type="text"  name="fax" id="fax" class="pure-u-1-5"   data-rule="length[~32]"  value="${data.organ.fax}">
                        </div>
                        <div class="pure-control-group">
                            <label>邮编:</label>
                            <input type="text" name="postCode"  class="pure-u-1-5"   data-rule="length[~32]" value="${data.organ.postCode}">
                        </div>
                        <div class="pure-control-group">
                            <label>机构描述:</label>
                            <textarea rows="5" cols="45"   name="description" 	id="description" data-rule="length[~255]"  >${data.organ.description}</textarea>
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
<script type="text/javascript" src='${applicationScope.pageConfig["resources.path"] }/app/user/js/page.js'  page='{"module":"organ_business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		