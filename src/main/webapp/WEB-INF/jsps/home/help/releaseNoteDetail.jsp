<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content_ttpt">
	<jsp:include page="/WEB-INF/jsps/home/help/snippet/helpNav.jsp"></jsp:include>
	<div class="content_tab">
		<div class="content_ttpt">
			<div class="detail_tt_nd">
				<input type="hidden" value="${ifoNews.newsId}"
					id="NewsDetail_NewsId" /> <input type="hidden"
					value="${ifoNews.newsType}" id="NewsDetail_NewsType" /> <input
					type="hidden" value="${ifoNews.locale}" id="NewsDetail_Local" />
				<h1 class="title">
					<SPAN class=""><s:property value="ifoNews.newsHeader"
							escape="false" /> </SPAN>
				</h1>
				<span class="time"> <fmt:formatDate	pattern="hh:mm a - dd/MM/yyyy" value="${ifoNews.newsDate}"></fmt:formatDate>
					-&nbsp;<b><s:property value="ifoNews.newsResource" /> </b>
				</span>

				<div class="Lead" style="width: 100%; float: left;">
					<s:property value="ifoNews.newsContent" escape="false" />
				</div>
			</div>

		</div>
		<!-- right -->
		<div id="c-column" class="width340">
			<div class="box_listnew">
				<web:productSubject var="objProdSub"
					productCode="RELEASE_NOTE_IMPORTANTS" />
				<c:out value="${objProdSub['product'].productOverview}"
					escapeXml="false" />
			</div>
		</div>
	</div>

</div>


