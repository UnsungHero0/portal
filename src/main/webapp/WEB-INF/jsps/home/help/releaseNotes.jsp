<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content">
	<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>

	<web:productSubject var="objProdSub" productCode="RELEASE_NOTE_LIST" />
	<c:out value="${objProdSub['product'].productOverview}"
		escapeXml="false" />
</div>
