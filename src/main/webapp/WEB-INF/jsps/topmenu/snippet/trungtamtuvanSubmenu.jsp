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
			<a href="<web:url value='/nhan-dinh-thi-truong.shtml'/>"> <s:text
					name="home.topMenu.analysis.market">Nhận định thị trường</s:text> </a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_MarketCommentary' classValue='active'/>
				href="<web:url value='/nhan-dinh-thi-truong.shtml'/>"><s:text name="analysis.news.marketCommentary">Bản tin thị trường</s:text>
				</a>
		</li>
		<li>
			<a
				<web:menuOut code='subMenuAnalysis_News_Experts' classValue='active'/>
				href="<web:url value='/y-kien-chuyen-gia.shtml'/>"><s:text name="analysis.news.expertOpinions">Ý kiến chuyên gia</s:text>
				</a>
		</li>
		<%-- <li>
            <a
                <web:menuOut code='subMenuAnalysis_StockHighlights' classValue='active'/>
                href="<web:url value='/co-phieu-tam-diem.shtml'/>"><s:text name="analysis.news.stockHighlights">CPTD</s:text>
            </a>
        </li> --%>
	</ul>
	<ul class="list_menu_sup line">
        <li class="title">
            <a
                href="<web:url value='/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml'/>">
                <s:text name="home.topMenu.analysis.investmentIdea">Ý tưởng đầu tư</s:text>
            </a>
        </li>
        <li>
            <a
                <web:menuOut code='subMenuAnalysis_ResearchTool_StockPick' classValue='active'/>
                href="<web:url value='/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml'/>"><s:text
                    name="label.stockpick">Cổ phiếu khuyến nghị</s:text>
            </a>
        </li>
        <li>
            <a
                <web:menuOut code='subMenuAnalysis_ResearchTool_PowerRatingView' classValue='active'/>
                href="<web:url value='/y-tuong-dau-tu/power-ratings.shtml'/>"><s:text
                    name="leftmenu.label.analysisTool.Refine.PowerRating">Power Ratings</s:text>
            </a>
        </li>
    </ul>
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
	</ul> --%>
	<ul class="list_menu_sup line">
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
				<web:menuOut code='subMenuAnalysis_ResearchTool_FlashChart' classValue='active'/>
				href="<web:url value='/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml'/>"><s:text
					name="leftmenu.label.analysisTool.Technical.Charts">Biểu đồ kỹ thuật</s:text>
			</a>
		</li>
		
		<li class="title" style="margin-top:10px;">
            <a
                href="<web:url value='/phan-tich-nganh/tong-quan-nganh.shtml'/>">
                <s:text name="home.topMenu.analysis.industyAnalysis">Phân tích ngành</s:text>
            </a>
        </li>
	</ul>
	<!-- BANNER_ADVISORY_CENTER_ON_MENU -->
	<a class="small_banner_submenu" onclick="window.open('https://www.vndirect.com.vn/portal/y-tuong-dau-tu/co-phieu-khuyen-nghi.shtml');">
		<web:document var="imgDoc" productCode="BANNER_ADVISORY_CENTER_ON_MENU"
                download="true" />
        <img src="${imgDoc['document'].documentUri}" />
	</a>
	<a class="icon_dow_supmenu"></a>
</div>