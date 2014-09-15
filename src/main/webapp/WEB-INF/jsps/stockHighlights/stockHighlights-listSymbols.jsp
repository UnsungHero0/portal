<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>
<authz:authorize ifAnyGranted="ROLE_ONLINE_TRADING">
    <c:set var="symbolClass" value="linkGreen" />
</authz:authorize>

<h2>DANH SÁCH BÁO CÁO</h2>
<c:if test="${not empty model.listSymbolsFreeReports}">
	<c:forEach var="freeSymbol" items="${model.listSymbolsFreeReports}">
		<a href="<web:url value='/co-phieu-tam-diem/xem-thu-bao-cao.shtml?symbol=${freeSymbol}' />" class="linkGreen">${freeSymbol}</a>
	</c:forEach>
</c:if>
<c:if test="${not empty model.listSymbolsFreeReports}">
	<c:forEach var="symbol" items="${model.listSymbolsHaveReports}">
		<a href="<web:url value='/co-phieu-tam-diem/xem-bao-cao.shtml?symbol=${symbol}' />" class="${symbolClass}">${symbol}</a>
	</c:forEach>
</c:if>
	