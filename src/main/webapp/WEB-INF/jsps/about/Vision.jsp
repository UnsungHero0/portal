<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content_ttpt">
	<div class="content_tab">
		<!-- sub menus -->
		<jsp:include page="/WEB-INF/jsps/about/snippet/aboutNav.jsp"></jsp:include>

		<!-- left content -->
		<div class="content_ttpt ct-textnone">
		    <!-- use INFO_VISION_NEW for test. Change to INFO_VISION -->
			<web:productSubject var="objProdSub" productCode="INFO_VISION_NEW" />
			<c:out value="${objProdSub['product'].productOverview}"
				escapeXml="false" />
		</div>
		<!-- end left content -->

		<!-- Right content -->
		<div id="c-column" class="width340">
			<web:document var="imgDoc" productCode="BANNER_ABOUT_VNDIRECT"
				download="true" />
			<img src="${imgDoc['document'].documentUri}" />
		</div>
		<!-- End right content -->
	</div>
</div>