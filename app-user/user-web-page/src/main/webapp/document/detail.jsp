<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${basePath}/resources/js/document/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${basePath}/resources/js/document/docs.css">
<link href="${basePath }/resources/js/document/json/s.css" type="text/css" rel="stylesheet"></link>
<title>服务接口方法列表</title>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">系统服务文档</a>
			</div>
		</div>
	</header>

	<div class="container bs-docs-container">
		<div class="row">
			<!-- 方法列表 -->
			<div class="col-md-3">
				<div role="complementary" class="bs-sidebar hidden-print affix">
					<ul class="nav bs-sidenav"> 
						<c:forEach var="item" items="${data.service.methods}" step="1"
							varStatus="idx">
							<c:if test="${idx.index == 0 }">
								<li><a style="color: black;"><h4>${data.service.name}方法列表</h4></a></li>
							</c:if>
							<li><a
								href="${basePath}/document.do?method=detail&className=${item.className}&url=${item.url}">${item.name}</a>
						</c:forEach>
					</ul>
				</div>
			</div>


			<!-- 方法名细 -->
			<div class="col-md-9" role="main">
				<div class="bs-docs-section">
					<!-- 快速定位导航  -->
					<nav role="navigation" class="navbar navbar-default"
						style="margin-top: 30px">
						<div class="collapse navbar-collapse" style="padding-left: 0px">
							<ul class="nav navbar-nav">
								<li><a href="#baseinfo">基础信息</a></li>
								<li><a href="#inputparam">输入参数</a></li>
								<li><a href="#return">返回结果</a></li>
								<li><a href="#errorcode">错误码</a></li>
								<li><a href="#test">案例测试</a></li>
							</ul>
						</div>
					</nav>


					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="baseinfo">基础信息</a>
						</h4>
					</div>
					<div>
						<b class="">◆方法:</b>${data.method.name}
					</div>
					<div>
						<b>◆接口方法调用路径:</b>${basePath}${data.method.url}.json
					</div>
					<div>
						<b>◆服务接口类名称: </b> ${data.method.className}
					</div>
					<div>
						<b>◆请求方式: ${data.method.requestType}</b>
					</div>
					<div>
						<b>◆方法描述: </b>
					</div>
					<div>${data.method.description}</div>





					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="inputparam">输入参数</a>
						</h4>
					</div>
					<c:if test="${empty data.method.requestParams}">
						不需要输入参数
					</c:if>
					<c:if test="${ !empty data.method.requestParams}">
						<table class="table table-bordered" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>参数名称</th>
									<th>参数类型</th>
									<th>必填</th>
									<th>长度限制</th>
									<th>是否为数组</th>
									<th>参数描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="param1" items="${data.method.requestParams}"
									varStatus="idx1">
									<tr>
										<td>${idx1.index+1}</td>
										<td>${param1.name}</td>
										<td><!--${param1.type}--><a
											href="${basePath}/document.do?method=model&className=${param1.classname}">${param1.classname}</a></td>
										<td><c:if test="${param1.nullable}">否</c:if> <c:if
												test="${not param1.nullable}">是</c:if></td>
										<td>${param1.length}</td>
										<td><c:if test="${param1.islist}">是</c:if> <c:if
												test="${not param1.islist}">否</c:if></td>
										<td>${param1.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>





					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="return">返回结果</a>
						</h4>
					</div>
					<c:if test="${empty data.method.returns}">
						没有返回结果描述
					</c:if>
					<c:if test="${ !empty data.method.returns}">
						<table class="table table-bordered" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>类型</th>
									<th>描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${data.method.returns}"
									varStatus="idx1">
									<tr>
										<td>${idx1.index+1}</td>
										<td>${item.name}</td>
										<td><a target="_blank"
												href="${basePath}/document.do?method=model&className=${item.classname}">${item.classname}</a></td>
										<td>${item.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>


					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="errorcode">错误码</a>
						</h4>
					</div>
					<c:if test="${empty data.method.exceptions}">
						没有提供错误码信息
					</c:if>
					<c:if test="${ !empty data.method.exceptions}">
						<table class="table table-bordered" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>错误码</th>
									<th>错误描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${data.method.exceptions}"
									varStatus="idx1">
									<tr>
										<td>${idx1.index+1}</td>
										<td>${item.code}</td>
										<td>${item.message}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>


					<div class="bs-callout bs-callout-info" style="padding-left: 15px">
						<h4>
							<a name="test">案例测试</a>
						</h4>
					</div>
					<input type="hidden" id="req_url"
						value="${basePath}${data.method.url}.json"> <input
						type="hidden" id="req_method" value="${data.method.requestType}">
					<form class="form-horizontal" role="form" onsubmit="return false"
						accept-charset="UTF-8" id="form1"
						<c:if test="${data.method.hasfile}">enctype="multipart/form-data"</c:if>>
						<c:forEach var="item" items="${data.method.requestParams}">
							<c:set var="end" value="0"></c:set>
							<c:if test="${item.islist }">
								<c:set var="end" value="0"></c:set>
							</c:if>
							<c:forEach begin="0" end="${end}">
								<c:choose>
									<c:when test="${item.type=='file' }">
										<div class="form-group">
											<label class="col-sm-3 control-label">${item.name}<!--(${item.name})  --><c:if
													test="${!item.nullable }">
													<font color="red">*</font>
												</c:if>:
											</label>
											<div class="col-sm-6">
												<input type="file" class="form-control" name="${item.fieldName }">&nbsp;${item.type }(${item.fieldName})
											</div>
										</div>
									</c:when>
									<c:when test="${item.type=='date' }">
										<div class="form-group">
											<label class="col-sm-3 control-label">${item.name}<c:if
													test="${!item.nullable }">
													<font color="red">*</font>
												</c:if>:
											</label>
											<div class="col-sm-6">
												<input type="text" name="${item.fieldName }" style="height: 30px"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'${item.format}'})"
													class="Wdate">&nbsp;${item.type }(${item.fieldName})
											</div>
										</div>
									</c:when>
									<c:when test="${item.type=='object' }">
										
									</c:when>
									<c:otherwise>
										<div class="form-group">
											<label class="col-sm-3 control-label">${item.name}<c:if
													test="${!item.nullable }">
													<font color="red">*</font>
												</c:if>:
											</label>
											<div class="col-sm-6">
												<input type="text" name="${item.fieldName }"
													<c:if test="${item.type=='integer'}">onkeyUp = "DigitInput(this,event);"</c:if>
													<c:if test="${item.type=='decimal'}">onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)"</c:if>>&nbsp;${item.type }(${item.fieldName})
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:forEach>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
								<input type="submit" value="提交" id="submitBtn"
									style="width: 100px" class="btn btn-primary">
							</div>
						</div>
					</form>
					<div class="pure-u-1">
						<b>请求参数:</b>
						<div id="request_param"
							style="overflow: scroll; border: 1px solid #CECECE; background: none repeat scroll 0 0 #ECECEC; border-radius: 3px; color: black; height: 80px">

						</div>
						<br> <b>返回结果:</b>
					</div>
					<div id="ControlsRow" class="pure-u-1">
						<input type="Button" value="格式化" onclick="Process()" /> <span
							id="TabSizeHolder"> 缩进量 <select id="TabSize"
							onchange="TabSizeChanged()">
								<option value="1">1</option>
								<option value="2" selected="true">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
						</select>
						</span> <label for="QuoteKeys"> <input type="checkbox"
							id="QuoteKeys" onclick="QuoteKeysClicked()" checked="true" /> 引号
						</label>&nbsp; <a href="javascript:void(0);" onclick="SelectAllClicked()">全选</a>
						&nbsp; <span id="CollapsibleViewHolder"> <label
							for="CollapsibleView"> <input type="checkbox"
								id="CollapsibleView" onclick="CollapsibleViewClicked()"
								checked="true" /> 显示控制
						</label>
						</span> <span id="CollapsibleViewDetail"> <a
							href="javascript:void(0);" onclick="ExpandAllClicked()">展开</a> <a
							href="javascript:void(0);" onclick="CollapseAllClicked()">叠起</a>
							<a href="javascript:void(0);" onclick="CollapseLevel(3)">2级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(4)">3级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(5)">4级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(6)">5级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(7)">6级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(8)">7级</a> <a
							href="javascript:void(0);" onclick="CollapseLevel(9)">8级</a>
						</span>
					</div>
					<div
						style="height: 275px; overflow: auto; margin-bottom: 0px; opacity: 1;"
						class="Canvas well resizable processed" id="Canvas"></div>
					<div class="grippie" style="margin-right: 0px;"></div>
					<textarea id="RawJson" rows="0" cols="0"
						style="width: 0px; height: 0px"></textarea>
					<div id="output1">AJAX response will replace this content.</div>
				</div>
			</div>
		</div>
	</div>


	<script src="${basePath }/resources/js/document/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/jquery.form.min.js" type="text/javascript"></script>
	<script src="${basePath }/resources/js/document/json/c.js" type="text/javascript"></script>	
	<script src="${basePath }/resources/js/document/date/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.ImgCollapsed = "${basePath }/resources/js/document/json/Collapsed.gif";
		window.ImgExpanded = "${basePath }/resources/js/document/json/Expanded.gif";
	</script>
	<script src="${basePath }/resources/js/document/document.js" type="text/javascript"> </script>
</body>
</html>