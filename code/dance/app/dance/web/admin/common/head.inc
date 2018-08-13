<%@ page language="java"  pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePathHttp = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String basePathHttps = request.getScheme()+"s://"+request.getServerName()+request.getContextPath()+"/";
String basePath=basePathHttp;
%>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">

<link rel="stylesheet" href="resource/js/modules/element-ui/index.css" />





