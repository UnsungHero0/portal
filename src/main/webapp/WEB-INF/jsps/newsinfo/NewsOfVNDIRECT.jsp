<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="vndirectForm" id="vndirectForm" method="post" action="">
	<input type="hidden" id="pagingInfo_offset" value="6"
		name="pagingInfo.offset" />
	<input type="hidden" id="pagingInfo_indexPage" value="1"
		name="pagingInfo.indexPage" />
	<input type="hidden" value="${strFromDate}" name="strFromDate"
		id="strFromDate">
	<input type="hidden" value="${strToDate}" name="strToDate"
		id="strToDate">
	<input type="hidden" value="${pagingInfo.totalPage}" id="totalPage">
</form>

<div id="content_ttpt" style="margin: 10px 0;">
	<div class="content_small">
		<div class="content_tab">
			<a id="top"></a>
			<!-- sub menus -->
			<jsp:include page="/WEB-INF/jsps/about/snippet/aboutNav.jsp"></jsp:include>

			<!-- Left content -->
			<div class="content_ttpt">
				<form class="box_loc" name="vndirectNewsForm_SearchNews"
					action="<web:url value="vndirect/tin-vndirect.shtml"/>"
					method="post" style="margin-top: 10px;">
					<s:text name="common.searchNews.filterFrom">Lọc tin từ</s:text>
					<input style="height: 20px; width: 88px;" name="strFromDate" type="text"
						id="fHistoricalPrice_FromDate" value="${strFromDate}" />
					&nbsp;&nbsp;&nbsp;<s:text name="common.searchNews.filterTo">đến</s:text>
					<input style="height: 20px; width: 88px;" name="strToDate" type="text"
						id="fHistoricalPrice_ToDate" value="${strToDate}" />
					<input type="submit" class="iButton" style="margin-right:20px;width:90px;" id="fHistoricalPrice_View"
						value='<s:text name="button.search"/>'>
				</form>

				<c:choose>
				    <c:when test="${empty model.searchResult}">
				        <p style="float: left; width: 100%;"><s:text name="newsMessage.empty"/></p>
				    </c:when>
				    <c:otherwise>
						<ul class="listnew">
							<c:forEach var="item" items="${model.searchResult}">
								<li>
									<h2 class="title">
										<a href="${item.urlDetail}" />${item.newsHeader}</a>
									</h2>
									<p class="time">
										${item.newsDateTimeStr} - ${item.newsDateStr}
										<b> ${item.newsResource}</b>
									</p>
									${item.newsAbstract}
								</li>
							</c:forEach>
						</ul>
				    </c:otherwise>
				</c:choose>

				<div class="clear"></div>
				<div class="content_bar">
					<div id="loadingMoreImage" style="text-align: center;"></div>
					<div class="barGoTop">
						<a href="#top"><s:text name="newsMoreNav.backToTop">Về đầu trang</s:text></a>
					</div>
					<div class="barLoadMore">
						<a id="loadMore"><s:text name="newsMoreNav.more">Xem thêm</s:text></a>
					</div>
				</div>
			</div>
			<!-- End left content -->
			<!-- Right content -->
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
			</div>
			<!-- End right content -->
		</div>
	</div>
</div>