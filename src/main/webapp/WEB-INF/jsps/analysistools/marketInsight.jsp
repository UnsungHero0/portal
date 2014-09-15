<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content_ttpt">
	<!-- nav -->
	<jsp:include
		page="/WEB-INF/jsps/analysistools/snippet/analysis-news-nav.jsp"></jsp:include>

	<div class="content_small">
		<div class="content_tab">
			<!-- left -->
			<div class="content_video">
				<div class="detail_tt_nd">
					<h1 class="title">
						<span><s:property value="ifoNews.newsHeader" escape="false" />
						</span>
						<ul class="zoomButtons">
							<li id="print"></li>
							<li id="zoomOut"></li>
							<li id="noZoom"></li>
							<li id="zoomIn"></li>
						</ul>
					</h1>
					<p style="font-size: 13px;">
						Views:
						<s:property value="ifoNews.hit" escape="false" />
					</p>
					<span class="time"> <fmt:formatDate
							pattern="hh:mm a - dd/MM/yyyy" value="${ifoNews.newsDate}" /> -
						<s:property value="ifoNews.newsResource" />
					</span>
					<h2 class="Lead">
						<s:property value="ifoNews.newsContent" escape="false" />
					</h2>
				</div>

				<!-- Download attach files -->
				<div class="padding-top10">
					<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
						<b>File đính kèm :</b>
					</c:if>
					<s:iterator value="ifoNews.newsAttWithoutVideos">
						<a
							href="javascript:download('<s:property value="originalLink"/>', 'newsAttach', '<s:property value="fileName"/>', '<s:property value="attachmentsId"/>');">
							<s:property value="fileName" />
						</a> &nbsp;
                    </s:iterator>
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

				<!-- news list -->
				<c:if test="${model.searchResult != null}">
					<ul class="n_list_other list_detail_tt_nd" id="">
						<li class="title bgTitleBar"><s:text
								name="analysis.news.marketCommentary">Nhận định thị trường</s:text></li>
						<div class="n_list_other_detail"
							id="NewsDetail_ListOtherInDayNews">
							<c:forEach var="item" items="${model.searchResult}">
								<li class="n_other_news_list"><a href="${item.urlDetail}">${item.newsHeader}</a>
									<span class="textcreatnews"> (${item.newsDateStr})</span></li>
							</c:forEach>
						</div>
					</ul>
				</c:if>
			</div>

			<!-- right -->
			<div class="width340" id="c-column">
				<div id="analysis-marketInsight-news-box">
					<span class="ajaxLoadingIcon"></span>
				</div>
			</div>
			<!--end tabs-->
		</div>
	</div>
</div>