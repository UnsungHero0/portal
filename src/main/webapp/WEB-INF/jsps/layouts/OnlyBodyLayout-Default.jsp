<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:text name="system.label.layout.title"/></title>
		<meta http-equiv="Page-Exit" content="progid:DXImageTransform.Microsoft.Fade(duration=.3)" />

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>

		<link rel="icon" href="<web:url value='/favicon.png'/>"
            type="image/x-icon" />
        <link rel="shortcut icon" href="<web:url value='/favicon.png'/>"
            type="image/x-icon" />

		<tiles:useAttribute id="list" name="default-css" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<link rel="stylesheet" href="<c:url value='${item}'/>?time=@time_param@" type="text/css" media="screen" />
		</c:forEach>
		<%-- Module CSS --%>
		<tiles:useAttribute id="list" name="module-css" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<link rel="stylesheet" href="<c:url value='${item}'/>?time=@time_param@" type="text/css" media="screen" />
		</c:forEach>
		<%-- Addition CSS --%>
		<tiles:useAttribute id="list" name="css" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<link rel="stylesheet" href="<c:url value='${item}'/>?time=@time_param@" type="text/css" media="screen" />
		</c:forEach>
		
		<!--[if gte IE 6]>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/iespecific.css'/>?time=@time_param@" />
		<![endif]-->

		<%-- Global Script --%>
		<tiles:insertAttribute name="global-script" />

		<%-- Default JS --%>
		<tiles:useAttribute id="list" name="default-js" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<script src="<c:url value='${item}'/>?time=@time_param@" type="text/javascript"></script>
		</c:forEach>
		<%-- Menu JS --%>
		<tiles:useAttribute id="list" name="menu-js" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<script src="<c:url value='${item}'/>?time=@time_param@" type="text/javascript"></script>
		</c:forEach>
		<%-- Module JS --%>
		<tiles:useAttribute id="list" name="module-js" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<script src="<c:url value='${item}'/>?time=@time_param@" type="text/javascript"></script>
		</c:forEach>
		<%-- Addition JS --%>
		<tiles:useAttribute id="list" name="js" classname="java.util.List" />
		<c:forEach var="item" items="${list}">
			<script src="<c:url value='${item}'/>?time=@time_param@" type="text/javascript"></script>
		</c:forEach>

	</head>
	<body>
		<div>
			<div id="web_action_errors" style="color: red; display: none;"></div>
			<div id="web_action_messages" style="color: blue; display: none;"></div>
			<tiles:insertAttribute name="body" />
		</div>
	</body>
</html>
