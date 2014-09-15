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
		<web:menuOut code='si_report_balance' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/bang-can-doi-ke-toan${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.Financial.Button.BalanceSheet">Bảng cân đối kế toán</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_report_income' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value="/bao-cao-ket-qua-kinh-doanh${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.Financial.Button.IncomeStatement">Báo cáo KQKD</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_report_cashflow' classValue='ui-tabs-selected'/>>
		<a
			href="<web:url value="/bao-cao-luu-chuyen-tien-te${symbol_}.shtml"/>"><span><s:text
					name="web.label.ListedMarket.Tab.Financial.Button.CashFlow">Báo cáo lưu chuyển tiền tệ</s:text>
		</span> </a>
	</li>
</ul>

<div class="clear"></div>