<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<web:productSubject var="objProdSub" productCode="SPDV_SPGD" />
<c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />