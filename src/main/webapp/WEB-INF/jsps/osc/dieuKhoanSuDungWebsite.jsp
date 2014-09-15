<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="margin:20px 0;line-height: 28px;">
	<web:productSubject var="objProdSub" productCode="DISCLOSURES" />
	<c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
</div>