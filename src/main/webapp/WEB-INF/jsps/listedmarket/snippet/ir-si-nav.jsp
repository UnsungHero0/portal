<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-main-nav.jsp"></jsp:include>

<ul class="ui-tabs-nav tab_button" style="margin-bottom: 10px;">
	<li <web:menuOut code='ir_si_basicInfo' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml" />"><span><s:text
					name="ir.si.thongtincoban">Thông tin cơ bản</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='ir_si_chart' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/bieu-do-ky-thuat.shtml" />"><span><s:text
					name="analysis.stockInfo.charts">Biểu đồ kỹ thuật</s:text> </span> </a>
	</li>
	<li <web:menuOut code='ir_si_historicalPrice' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/lich-su-gia.shtml" />"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice">Lịch sử giá</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='ir_si_foreignTrading' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/giao-dich-nha-dau-tu-nuoc-ngoai.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading">Giao dịch NDT NN</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='ir_si_tradingStatistic' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/ket-qua-giao-dich.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics">Kết quả giao dịch</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='ir_si_ownership' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/quan-he-co-dong-vndirect/quyen-so-huu.shtml"/>"><label class="icon_active"></label>
			<s:text name="analysis.stockInfo.ownership">Quyền sở hữu</s:text> </a>
	</li>
</ul>

<div class="clear"></div>