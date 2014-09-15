<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
    // draw chart using HighStock
    drawChartForFirstTime(${model.highStock.data});
});
</script>
<input type="hidden" id="whichSymbolIsDraw" value="${model.symbol}" />

<jsp:include
	page="/WEB-INF/jsps/analysis/stockInformation/snippet/highstock/hsNavigatorForChart.jsp"></jsp:include>

<div id="hsDefaultContainerLoading"></div>
<div id="hsDefaultContainer">
	<img src="<web:url value='/images/ajax-loader.gif'/>" />
</div>
<div class="clear"></div>
