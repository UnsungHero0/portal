<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content_ttpt">
	<!-- nav -->
	<jsp:include
		page="/WEB-INF/jsps/listedmarket/snippet/ir-management-nav.jsp"></jsp:include>

	<div class="content_small">
		<div class="content_tab">
			<web:productSubject var="objProdSub" productCode="IR_HoiDongQuanTri" />
			<c:out value="${objProdSub['product'].productOverview}"
				escapeXml="false" />
		</div>
	</div>
</div>