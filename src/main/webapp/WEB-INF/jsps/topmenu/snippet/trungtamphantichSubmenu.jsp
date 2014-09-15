<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="symbol_" value="" />
<c:if test="${not empty symbol || not empty currentSymbol}">
	<c:choose>
		<c:when test="${not empty symbol}">
			<c:set var="symbol_" value="/${fn:toLowerCase(symbol)}" />
		</c:when>
		<c:otherwise>
			<c:set var="symbol_" value="/${fn:toLowerCase(currentSymbol)}" />
		</c:otherwise>
	</c:choose>
</c:if>


<div class="box_load">
	<ul class="list_menu_sup">
		<li class="title">
			<a href="<web:url value='/tin-trong-nuoc.shtml'/>"> <s:text
					name="home.topMenu.analysis.news">Tin tức - Nhận định</s:text> </a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_MacVN' classValue='active'/>
				href="<web:url value='/tin-trong-nuoc.shtml'/>"><s:text name="analysis.news.localNews">Tin trong nước</s:text>
				</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='active'/>
				href="<web:url value='/tin-quoc-te.shtml'/>"><s:text name="analysis.news.internationalNews">Tin quốc tế</s:text>
				</a>
		</li>
		
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_Disclosure' classValue='active'/>
				href="<web:url value='/cong-bo-thong-tin.shtml'/>"><s:text name="analysis.news.disclosureInformation">Công bố thông tin</s:text>
				</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='active'/>
				href="<web:url value='/lich-su-kien.shtml'/>"><s:text name="analysis.news.marketCalendar">Lịch sự kiện</s:text>
				</a>
		</li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a
				href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml'/>">
				<s:text name="home.topMenu.analysis.marketStatistics">Thông kê thi trường</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_MarketStatistics_Overview' classValue='active'/>
				href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml'/>"><s:text 
				    name="home.topMenu.analysis.marketOverview">Tổng quan thị trường</s:text>
				</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_MarketStatistics_HistoricalPrice' classValue='active'/>
				href="<web:url value='/thong-ke-thi-truong-chung-khoan/lich-su-gia.shtml'/>"><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice">Lịch sử giá</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_MarketStatistics_TradingStatistics' classValue='active'/>
				href="<web:url value='/thong-ke-thi-truong-chung-khoan/ket-qua-giao-dich.shtml'/>"><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics">Kết quả giao dịch</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_MarketStatistics_ForeignTrading' classValue='active'/>
				href="<web:url value='/thong-ke-thi-truong-chung-khoan/giao-dich-nha-dau-tu-nuoc-ngoai.shtml'/>"><s:text
					name="web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading">Giao dịch NĐT NN</s:text>
			</a>
		</li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a href="<web:url value='/tong-quan${symbol_}.shtml'/>"><s:text
                    name="home.topMenu.analysis.stockInfo">Thông tin cổ phiếu</s:text></a>
		</li>
		<li>
			<a <web:menuOut code='si_company' classValue='active'/>
				href="<web:url value='/tong-quan${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.company">Doanh
            nghiệp</s:text>
			</a>
		</li>
		<li>
			<a <web:menuOut code='si_ownership' classValue='active'/>
				href="<web:url value='/co-dong-chinh${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.ownership">Quyền sở hữu</s:text>
			</a>
		</li>
		<li>
			<a <web:menuOut code='si_chart' classValue='active'/>
				href="<web:url value='/bieu-do-ky-thuat${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.charts">Biểu đồ kỹ thuật</s:text>
			</a>
		</li>
		<li>
			<a <web:menuOut code='si_news' classValue='active'/>
				href="<web:url value='/tin-doanh-nghiep${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.companyNews">Tin doanh nghiệp</s:text>
			</a>
		</li>
		<li>
			<a <web:menuOut code='si_report' classValue='active'/>
				href="<web:url value='/bang-can-doi-ke-toan${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.financialStatements">Báo cáo tài chính</s:text>
			</a>
		</li>
		<li>
			<a <web:menuOut code='si_statistic' classValue='active'/>
				href="<web:url value='/lich-su-gia${symbol_}.shtml'/>"><s:text
					name="analysis.stockInfo.marketStatistics">Thống kê thị trường</s:text>
			</a>
		</li>
	</ul>
	<%-- <ul class="list_menu_sup line">
		<li class="title">
			<a
				href="<web:url value='/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml'/>">
				<s:text name="home.topMenu.analysis.analysisTool">Công cụ phân tích</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_ResearchTool_StockScreenerView' classValue='active'/>
				href="<web:url value='/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Refine.StockScreener">Sàng lọc cổ phiếu</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_ResearchTool_StockWizardView' classValue='active'/>
				href="<web:url value='/cong-cu-phan-tich-chung-khoan/stock-wizard.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Refine.StockWizard">Stock Wizard</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_ResearchTool_PowerRatingView' classValue='active'/>
				href="<web:url value='/y-tuong-dau-tu/power-ratings.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Refine.PowerRating">Power Ratings</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_ResearchTool_FlashChart' classValue='active'/>
				href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Technical.Charts">Biểu đồ kỹ thuật</s:text>
			</a>
		</li>
	</ul> --%>
	<%-- <ul class="list_menu_sup line">
		<li class="title">
			<a
				href="<web:url value='/phan-tich-nganh/tong-quan-nganh.shtml'/>">
				<s:text name="home.topMenu.analysis.industyAnalysis">Phân tích ngành</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_IndustryAnalysis_SectorOverview' classValue='active'/>
				href="<web:url value='/phan-tich-nganh/tong-quan-nganh.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Sector.Overview">Tổng quan ngành</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_IndustryAnalysis_ListSectorView' classValue='active'/>
				href="<web:url value='/phan-tich-nganh/chi-so-nganh.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Sector.Index">Chỉ số ngành</s:text>
			</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_IndustryAnalysis_ListSectorIndex' classValue='active'/>
				href="<web:url value='/phan-tich-nganh/phan-loai-nganh.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Sector.Classification">Phân loại ngành</s:text>
			</a>
		</li>
	</ul> BANNER_MARKET_INFO_ON_MENU--%>
	<a class="small_banner_submenu" onclick="window.open('https://www.vndirect.com.vn/portal/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml');">
		<web:document var="imgDoc" productCode="BANNER_MARKET_INFO_ON_MENU"
                download="true" />
        <img src="${imgDoc['document'].documentUri}" />
	</a>
	<a class="icon_dow_supmenu"></a>
</div>