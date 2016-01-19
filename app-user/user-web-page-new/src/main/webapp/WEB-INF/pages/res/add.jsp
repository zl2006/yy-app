<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开店助手-互联网营销,增加资源</title>
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
            <form action="${basePath }/res/save.json" id="save_update_form" method="post">
                <div class="pure-form pure-form-aligned form">
                    <div class="form-area">
                        <div class="title">
                            一、基本信息
                        </div>
                        <div class="pure-control-group">
                            <label>资源名称:</label>
                            <input  name="name" type="text" placeholder="资源名称"  data-rule="required;length[~64]" class="pure-u-1-5">
                        </div>
                         <div class="pure-control-group">
                            <label>资源英文名称:</label>
                            <input   name="ename" type="text" placeholder="资源英文名称"  data-rule="required;length[~64]" class="pure-u-1-5">
                        </div>
                         <div class="pure-control-group">
                            <label>资源类型:</label>
                            <input name="type" type="radio" value="0" checked="checked">菜单（模块）
                            <input name="type" type="radio" value="1" class="place-40">子菜单（子模块）
                            <input name="type" type="radio" value="2" class="place-40">子菜单项（功能）
                            <input name="type" type="radio" value="3" class="place-40">列表操作
                            <input name="type" type="radio" value="4" class="place-40">按钮等操作
                        </div>
                        <div class="pure-control-group">
                            <label>资源所属系统:</label>
                            <input type="text" placeholder="选择系统" id="systemName" name="systemName"  data-rule="required"   class="pure-u-1-5"  readonly="readonly">
							<input type="hidden" name="systemCode" id="systemCode">
							 <a class="button-xsmall pure-button pure-button-primary" id="selectSystem">选择</a>
							 <a class="button-xsmall pure-button pure-button-primary" id="clearSystem">清除</a>
                        </div>
                         <div class="pure-control-group">
                            <label>父资源:</label>
                            <input type="text" placeholder="选择资源" id="parentName"   class="pure-u-1-5"  readonly="readonly">
							<input type="hidden" name="parentResID" id="parentResID" >
							<a class="button-xsmall pure-button pure-button-primary" id="selectResource">选择</a>
							<a class="button-xsmall pure-button pure-button-primary" id="clearResource">清除</a>
						</div>
                        <div class="pure-control-group">
                            <label>资源URL: </label>
                            <input type="text" placeholder="资源URL" name="url"    class="pure-u-1-5"  data-rule="required;length[~128]"  >
                        </div>
                        <div class="pure-control-group">
                            <label >资源图标:</label>
                            <input type="text" placeholder="资源图标" name="icon"  class="pure-u-1-5" data-rule="length[~128]" >
                        </div>
                        <div class="pure-control-group">
                            <label>样式:</label>
                            <input type="text" placeholder="样式" name="style"    data-rule="length[~128]" class="pure-u-1-2">
                        </div>
                        <div class="pure-control-group">
                            <label >状态:</label>
                           <input type="radio" 	name="status" value="1" checked="checked"> 有效
							<input type="radio" name="status" value="0"> 无效
                        </div>
                        <div class="pure-control-group">
                            <label>描述:</label>
                           <textarea rows="4" cols="45" name="description" data-rule="length[~128]" ></textarea>
                        </div>
                        <div class="pure-control-group">
                            <label for="orderNO" >顺序： </label>
                            <input type="text" placeholder="顺序" name="orderNO"    value="1" data-rule="digits">
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
 
<script type="text/javascript" src='${applicationScope.pageConfig["resources.path"] }/app/user/js/page.js' page='{"module":"res_business","oper":"addoredit"}' data='{"base_path" : "${basePath}" }'></script>
</body>
</html>		