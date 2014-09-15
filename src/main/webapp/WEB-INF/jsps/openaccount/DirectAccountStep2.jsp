<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<tiles:insertDefinition name="DirectAccount">
	<tiles:putAttribute name="title">
		<s:text name="web.label.online.account.step2"></s:text>
	</tiles:putAttribute>
	<s:set var="step1" value="false"></s:set>
	<s:set var="tip" value="true"></s:set>
	<s:set var="tip1" value="false"></s:set>
	<tiles:putAttribute name="content">
		<%-- Back Form --%>
		<form method="post" action="<web:url value="/mo-tai-khoan-nha-dau-tu/buoc-1.shtml"/>" id="back">		
			<s:hidden id="cacheData" name="cacheData"></s:hidden>
		</form>
		<%-- --%>
		<form method="post" action="<web:url value="/mo-tai-khoan-nha-dau-tu/buoc-3.shtml"/>" id="next">
			<s:hidden id="cacheData" name="cacheData"></s:hidden>
			<jsp:include page="/WEB-INF/jsps/openaccount/Step2.jsp"></jsp:include>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>