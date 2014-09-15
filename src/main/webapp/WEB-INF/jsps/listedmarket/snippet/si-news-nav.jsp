<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="symbol_" value="" />
<c:if test="${not empty currentSymbol}">
	<c:set var="symbol_" value="/${fn:toLowerCase(currentSymbol)}" />
</c:if>

<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/si-main-nav.jsp"></jsp:include>

<ul id="tab_menusup_ttcp_dn" class="ui-tabs-nav tab_button"
	style="margin-bottom: 15px;">
	<li <web:menuOut code='si_news_news' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/tin-doanh-nghiep${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.news.disclosure">Công bố thông tin</s:text>
		</span> </a>
	</li>
	<li <web:menuOut code='si_news_events' classValue='ui-tabs-selected'/>>
		<a href="<web:url value="/tin-lien-quan${symbol_}.shtml"/>"><span><s:text
					name="analysis.stockInfo.news.relate">Tin liên quan</s:text> </span> </a>
	</li>
</ul>

<div class="clear"></div>