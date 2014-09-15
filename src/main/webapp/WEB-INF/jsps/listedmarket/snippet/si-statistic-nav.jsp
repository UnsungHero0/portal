<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="symbol_" value="" />
<c:if test="${not empty symbol}">
	<c:set var="symbol_" value="/${fn:toLowerCase(symbol)}" />
</c:if>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/si-main-nav.jsp"></jsp:include>

<ul id="tab_menusup_ttcp_dn" class="ui-tabs-nav tab_button">
	<li
		<web:menuOut code='si_statistic_historical' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/lich-su-gia${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice">Lịch sử giá</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_statistic_foreign' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value="/giao-dich-nha-dau-tu-nuoc-ngoai${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading">Giao dịch NDT NN</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_statistic_trading' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/ket-qua-giao-dich${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics">Kết quả giao dịch</s:text>
		</span> </a>
	</li>
</ul>

<div class="clear"></div>