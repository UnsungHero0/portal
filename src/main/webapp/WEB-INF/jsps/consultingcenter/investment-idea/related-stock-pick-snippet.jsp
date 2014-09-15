<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<c:if test="${not empty datesByyyyymmddFormat}">
<p class="tt">KHUYẾN NGHỊ GẦN ĐÂY</p>
<ul>
<c:forEach var="date1" items="${datesByyyyymmddFormat}" varStatus="i">
    <c:set var="date2" value="${datesByddMMyyyyFormat[i.count-1]}" />
	<li><a href="<web:url value='/y-tuong-dau-tu/co-phieu-khuyen-nghi/${date1}.shtml' />">Khuyến nghị cổ phiếu ngày ${date2}</a><span class="date">(${date2})</span>
	</li>
	</c:forEach>
</ul>
</c:if>










