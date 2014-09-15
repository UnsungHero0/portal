<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<!-- Resarch tools -->
<script type="text/javascript">
$(function() {
	// draw chart using HighStock
	drawChartForFirstTime(${model.highStock.data});
});
</script>
<input type="hidden" id="whichSymbolIsDraw" value="${model.symbol}" />
<div class="content_ttpt">
	<!-- nav -->
	<jsp:include page="/WEB-INF/jsps/analysistools/snippet/rt-main-nav.jsp"></jsp:include>
	<div class="content_small">
		<div class="content_tab">
			<div class="rtRedrawChart">
			 <span class="rtRedrawChartLabel"><s:text name="analysis.stockInfo.charts.text1">Biểu đồ mã CK:</s:text></span>
				<input type="text" id="redrawChartSymbol"
					onfocus="if (this.value=='<s:text name="commons.symbolsearch.textbox"></s:text>') this.value='';"
					onblur="if (this.value=='') this.value='<s:text name="commons.symbolsearch.textbox"></s:text>';"
					value="${model.symbol}" />
				<input style="margin-left:10px; width:100px;" type="button" class="iButton" value="<s:text name='analysis.stockInfo.charts.draw'>Vẽ biểu đồ</s:text>" onclick="drawOtherSymbol();"/>
			</div>

			<div class="rtMarketSnapshot">
				<web:quotesCompanySnapshot chart="true" />
			</div>

			<jsp:include
				page="/WEB-INF/jsps/analysis/stockInformation/snippet/highstock/highStockChart.jsp"></jsp:include>
		</div>
		<!-- end .content_ttpt -->
	</div>
</div>
<!-- end .content_small -->
