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
		<web:menuOut code='si_company_snapshot' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/tong-quan${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.company.snapshot">Tổng quan</s:text> </span> </a>
	</li>
	<li
		<web:menuOut code='si_company_profile' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/ho-so-doanh-nghiep${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.company.profile">Hồ sơ doanh nghiệp</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='si_company_key' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/thong-ke-co-ban${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.company.keyStatistics">Thống kê cơ bản</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_company_report' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/bao-cao-tai-chinh${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.company.publishedDocument">Cáo bạch & BCTC</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_company_forecast' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/chi-tieu-ke-hoach${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.company.forecast">Chỉ tiêu kế hoạch</s:text>
		</span> </a>
	</li>
</ul>


<div class="clear"></div>