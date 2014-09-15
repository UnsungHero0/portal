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

<!-- Start Breadcrumbs -->
<web:quotesCompanyInfo />

<ul id="tab_menusup_ttcp" class="ui-tabs-nav tab_ttpt"
	style="margin-top: 15px;">
	<li <web:menuOut code='si_company' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/tong-quan${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.company">Doanh nghiệp</s:text> </a>
	</li>
	<li <web:menuOut code='si_ownership' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/co-dong-chinh${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.ownership">Quyền sở hữu</s:text> </a>
	</li>
	<li <web:menuOut code='si_chart' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/bieu-do-ky-thuat${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.charts">Biểu đồ kỹ thuật</s:text> </a>
	</li>
	<li <web:menuOut code='si_news' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/tin-doanh-nghiep${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.companyNews">Tin doanh nghiệp</s:text> </a>
	</li>
	<li <web:menuOut code='si_report' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/bang-can-doi-ke-toan${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.financialStatements">Báo cáo tài chính</s:text></a>
	</li>
	<li <web:menuOut code='si_statistic' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/lich-su-gia${symbol_}.shtml"/>"><label class="icon_active"></label> <s:text name="analysis.stockInfo.marketStatistics">Thống kê thị trường</s:text></a>
	</li>
</ul>

<div class="clear"></div>