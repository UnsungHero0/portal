<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
	FROM_MODULE = URL_FLASH_CHART;
	
	// draw chart using HighStock
	drawChartForFirstTime(${model.highStock.data});
});
</script>
<input type="hidden" id="whichSymbolIsDraw" value="${model.symbol}" />
<div class="content_ttpt">
	<!-- nav -->
	<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/si-main-nav.jsp"></jsp:include>
	<div class="content_small">
		<div class="content_tab">
			<jsp:include
				page="/WEB-INF/jsps/analysis/stockInformation/snippet/highstock/highStockChart.jsp"></jsp:include>
		</div>
		<!-- end .content_ttpt -->
	</div>
</div>
<!-- end .content_small -->