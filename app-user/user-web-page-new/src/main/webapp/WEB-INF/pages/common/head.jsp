<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	request.setAttribute("basePath", basePath);
%>
<link rel="stylesheet" href='${applicationScope.pageConfig["resources.path"] }/css/pure/0.6.0/pure-min.css'>
<link rel="stylesheet" href='${applicationScope.pageConfig["resources.path"] }/css/site/site.css'>
<link rel="stylesheet" href='${applicationScope.pageConfig["resources.path"] }/js/amd-alter/ui-jqvalidator/0.7.3/jquery.validator.css'>