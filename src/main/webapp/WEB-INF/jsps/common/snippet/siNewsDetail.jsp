<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
    var firstVideoURL = '';
//-->
</script>
<s:if test="#titles.keys.(#this.contains(#parameters[\"newsType\"][0]))">
	<s:set var="text" value="#titles.get(#parameters[\"newsType\"][0])"></s:set>
</s:if>
<s:else>
	<s:set var="text"
		value="getText('web.label.analysisTool.Report.LatestReport')"></s:set>
</s:else>

<c:if test="${ifoNews.newsType == 'PM'}">
	<s:set var="text"
		value="getText(\"leftmenu.label.analysisTool.MarketReview\")"></s:set>
</c:if>

<div class="content_ttpt">
	<div class="detail_tt_nd">
		<input type="hidden" value="${ifoNews.newsId}" id="NewsDetail_NewsId" />
		<input type="hidden" value="${ifoNews.newsType}"
			id="NewsDetail_NewsType" />
		<input type="hidden" value="${ifoNews.locale}" id="NewsDetail_Local" />

		<s:if test="%{ifoNews.hasVideoAtt}">
			<h1 class="title">
				<SPAN><s:property value="ifoNews.newsHeader" escape="false" />
				</SPAN>
				<ul class="zoomButtons">
					<li id="print">
					</li>
					<li id="zoomOut">
					</li>
					<li id="noZoom">
					</li>
					<li id="zoomIn">
					</li>
				</ul>
			</h1>
			<span class="time""> <fmt:formatDate
					pattern="hh:mm a - dd/MM/yyyy" value="${ifoNews.newsDate}" /> -&nbsp;<s:property
					value="ifoNews.newsResource" /> </span>

			<h2 class="Lead">
				<s:property value="ifoNews.newsAbstract" escape="false" />
			</h2>

			<s:if
				test="%{!@vn.com.vndirect.commons.utility.VNDirectWebUtilities@isOnTradingTime()}">
				<div id="player"></div>
				<span class="detail_video" style="padding-left: 5px"> <span
					id="videoDesc"><s:property
							value="ifoNews.attachmentVideoNews[0].description" /> </span> <br /> <s:iterator
						var="item" value="ifoNews.attachmentVideoNews" status="idx">
						<a href="javascript:void(0)"
							class="section {id : '<s:property value="attachmentsId"/>',desc : '<s:property value="des4Js"/>', videoURL : '<s:property value="%{(new vn.com.vndirect.web.struts2.analysistools.AnalysisReportAction()).getURL(originalLink)}"/>'}"><s:text
								name="web.label.NewsInfo.Sector" /> <s:property
								value="#idx.count" /> </a>
					</s:iterator> </span>
				<script>
firstVideoURL = '<s:property value="%{(new vn.com.vndirect.web.struts2.analysistools.AnalysisReportAction()).getURL(ifoNews.attachmentVideoNews[0].originalLink)}"/>';
attachmentId = '<s:property value="%{ifoNews.attachmentVideoNews[0].attachmentsId}"/>';
</script>
			</s:if>
			<s:else>
				<c:choose>
					<c:when test="${locale == 'vn'}">
						<img src='<s:url value="/flash/flashplayer_VN.jpg" />' />
					</c:when>
					<c:otherwise>
						<img src='<s:url value="/flash/flashplayer_EN.jpg" />' />
					</c:otherwise>
				</c:choose>
			</s:else>
			<p class="n-text">
				<s:property value="ifoNews.newsContent" escape="false" />
			</p>
			<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
				<b>File đính kèm :</b>
			</c:if>
			<s:iterator value="ifoNews.newsAttWithoutVideos">
				<a
					href="javascript:download('<s:property value="originalLink"/>', 'newsAttach', '<s:property value="fileName"/>', '<s:property value="attachmentsId"/>');">
					<s:property value="fileName" /> </a> &nbsp;
                        </s:iterator>
		</s:if>
		<s:else>
			<h1 class="title">
				<SPAN class=""><s:property value="ifoNews.newsHeader"
						escape="false" /> </SPAN>
				<ul class="zoomButtons">
					<li id="print"></li>
					<li id="zoomOut"></li>
					<li id="noZoom"></li>
					<li id="zoomIn"></li>
				</ul>
			</h1>
			<span class="time"> <fmt:formatDate
					pattern="hh:mm a - dd/MM/yyyy" value="${ifoNews.newsDate}" /> -&nbsp;<b><s:property
						value="ifoNews.newsResource" /> </b> </span>

			<!-- error display here -->

			<div class="Lead content-news-style" style="width: 100%; float: left;">
				<s:property value="ifoNews.newsContent" escape="false" />
			</div>
			<c:if test="${ifoNews.newsType != 'Expert'}">
				<div>
					<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
						<b>File đính kèm :</b>
					</c:if>
					<s:iterator value="ifoNews.newsAttWithoutVideos">
						<a
							href="javascript:download('<s:property value="originalLink"/>', 'newsAttach', '<s:property value="fileName"/>', '<s:property value="attachmentsId"/>');">
							<s:property value="fileName" /> </a> &nbsp;
                                </s:iterator>
				</div>
			</c:if>
		</s:else>
	</div>
	<div class="plugin_social">
		<div class="facebook">
			<!-- like facebook -->
			<div class="fb-like" data-send="false" data-layout="button_count"
				data-width="450" data-show-faces="false"></div>
			<!-- end like facebook -->
		</div>

		<div class="googlePlus">
			<!--  +1 button google -->
			<div class="g-plusone" data-size="medium"></div>
			<script type="text/javascript">
window.___gcfg = {
	lang : 'vi'
};

(function() {
	var po = document.createElement('script');
	po.type = 'text/javascript';
	po.async = true;
	po.src = 'https://apis.google.com/js/plusone.js';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(po, s);
})();
</script>
			<!-- end +1 button google -->
		</div>
	</div>
	<div class="clear"></div>

	<input type="hidden" id="loadMoreNewsChoice" value="SI-Disclosure" />

	<div id="irDisclosureNewsDetailTitleBar"
		class="titleBar bgTitleBar text-upper" style="display: none;">
		<span id="margin-title">TIN CẬP NHẬT</span>
	</div>
	<div class="ds_news" id="irDisclosureNewsDetail">
		<img src="<web:url value='/images/ajax-loader.gif'/>" />
	</div>
</div>

<div id="c-column" class="width340">
	<div class="box_listnew">
		<div class="titleBar titleBar-margin-icon">
			<span id="title"><s:text name="analysis.news.mostWatched">TIN XEM NHIỀU</s:text>
			</span>
		</div>
		<hr class="hrline" />
		<div class="content_small">
			<ul class="list">
				<c:forEach var="item" items="${model.searchMostView}">
					<li>
						<a href="${item.urlDetail}" />${item.newsHeader}</a>
						<span class="textcreatnews"> (${item.newsDateStr})</span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<jsp:include page="../../facebook/FacebookLikeBox.jsp" />
</div>
<!-- end #c-column -->