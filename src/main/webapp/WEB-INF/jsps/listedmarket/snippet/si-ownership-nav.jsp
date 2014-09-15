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
		<web:menuOut code='si_ownership_major' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/co-dong-chinh${symbol_}.shtml"/>"><span><s:text
					name="web.button.MaijorHoldersAction.MaijorHolder">Cổ đông chính</s:text>
		</span> </a>
	</li>
	<li
		<web:menuOut code='si_ownership_insider' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/giao-dich-noi-bo${symbol_}.shtml"/>"><span><s:text
					name="web.button.MaijorHoldersAction.InsiderTrans">Giao dịch nội bộ</s:text>
		</span> </a>
	</li>
</ul>

<div class="clear"></div>