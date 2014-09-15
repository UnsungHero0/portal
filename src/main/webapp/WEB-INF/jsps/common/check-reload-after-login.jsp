<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String errorCode  = request.getParameter("error");
	if ("true".equals(errorCode)) {
%>
	<script type="text/javascript">
		function setCookie(name, value, expire) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expire);
			var cookieValue = escape(value) + ";expires=" + exdate.toUTCString()+";path=/";
			document.cookie = name + "=" + cookieValue;
		}
		setCookie("hadLogined","",15000);
		//window.location = '<s:url value="/timeout.shtml"></s:url>';
	</script>
<%	
	}
 %>
<script type="text/javascript">
	var URL_HOME_PAGE = '<s:url value="/"></s:url>';
	var param = 'ref=';
	// Only redirect when this window is not in IFRAME
    if(location.href.indexOf("trading") > 0){
        window.location = "/trade/chung-khoan-truc-tuyen-tong-quan.shtml";
    } else if (location.href.indexOf(param) > 0) {
        var redirect = location.href.substr(location.href.indexOf("ref=") + param.length);
		if (redirect == 'undefined') {
			redirect = URL_HOME_PAGE;
		}
	    window.location = redirect;
	} else {
		window.location=URL_HOME_PAGE;
	}
</script>
