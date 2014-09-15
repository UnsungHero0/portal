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
	
	// store docUri
	var DOCs = [];
	// store something else as news-attach, reports to verify the document uri
	var TYPEs = [];
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

<div id="content_ttpt">
    <input type="hidden" id="news-newsType" value="${ifoNews.newsType}">
    
	<c:if test="${ifoNews.newsType != 'VNDIRECT'}">
	<ul class="ui-tabs-nav tab_ttpt">
		<c:choose>
			<c:when test="${ifoNews.newsType == 'Expert'}">
				<li
					<web:menuOut code='subMenuAnalysis_News_MarketCommentary' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/nhan-dinh-thi-truong.shtml'/>">
						<label class="icon_active"></label>
						<s:text name="analysis.news.marketCommentary">Nhận định TT</s:text>
					</a>
				</li>
				<li
					<web:menuOut code='subMenuAnalysis_News_Experts' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/y-kien-chuyen-gia.shtml'/>"> <label
							class="icon_active"></label>
						<s:text name="analysis.news.expertOpinions">Ý kiến chuyên gia</s:text>
					</a>
				</li>
				<%-- <li
	                <web:menuOut code='subMenuAnalysis_StockHighLights' classValue='ui-tabs-selected'/>>
	                <a href="<web:url value='/co-phieu-tam-diem.shtml'/>"> <label
	                    class="icon_active"></label>
	                <s:text name="analysis.news.stockHighlights">CPTD</s:text>
	               </a>
	            </li> --%>
			</c:when>
			<c:otherwise>
				<li
					<web:menuOut code='subMenuAnalysis_News_MacVN' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/tin-trong-nuoc.shtml'/>"> <label
							class="icon_active"></label>
						<s:text name="analysis.news.localNews">Tin trong nước</s:text> </a>
				</li>
				<li
					<web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/tin-quoc-te.shtml'/>"> <label
							class="icon_active"></label>
						<s:text name="analysis.news.internationalNews">Tin quốc tế</s:text>
					</a>
				</li>
				<li
					<web:menuOut code='subMenuAnalysis_News_Disclosure' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/cong-bo-thong-tin.shtml'/>"> <label
							class="icon_active"></label>
						<s:text name="analysis.news.disclosureInformation">Công bố thông tin</s:text>
					</a>
				</li>
				<li
					<web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='ui-tabs-selected'/>>
					<a href="<web:url value='/lich-su-kien.shtml'/>">
						<label class="icon_active"></label>
						<s:text name="analysis.news.marketCalendar">Lịch sự kiện</s:text> </a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
	</c:if>
	<c:if test="${ifoNews.newsType == 'VNDIRECT'}">
		<jsp:include page="/WEB-INF/jsps/about/snippet/aboutNav.jsp"></jsp:include>
	</c:if>

	<div class="content_small">
		<div class="content_tab">
			<div class="content_ttpt">
				<div class="detail_tt_nd">
					<input type="hidden" value="${ifoNews.newsId}"
						id="NewsDetail_NewsId" /> <input type="hidden"
						value="${ifoNews.newsType}" id="NewsDetail_NewsType" /> <input
						type="hidden" value="${ifoNews.locale}" id="NewsDetail_Local" />

					<s:if test="%{ifoNews.hasVideoAtt}">
						<h1 class="title">
							<SPAN><s:property value="ifoNews.newsHeader"
									escape="false" /> </SPAN>
							<ul class="zoomButtons">
								<li id="print"></li>
								<li id="zoomOut"></li>
								<li id="noZoom"></li>
								<li id="zoomIn"></li>
							</ul>
						</h1>
						<span class="time"> <fmt:formatDate
								pattern="hh:mm a - dd/MM/yyyy" value="${ifoNews.newsDate}" /> -&nbsp;<s:property
								value="ifoNews.newsResource" />
						</span>

						<h2 class="Lead">
							<s:property value="ifoNews.newsAbstract" escape="false" />
						</h2>

						<s:if
							test="%{!@vn.com.vndirect.commons.utility.VNDirectWebUtilities@isOnTradingTime()}">
							<div id="player"></div>
							<span class="detail_video" style="padding-left: 5px"> <span
								id="videoDesc"><s:property
										value="ifoNews.attachmentVideoNews[0].description" /> </span> <br />
								<s:iterator var="item" value="ifoNews.attachmentVideoNews"
									status="idx">
									<a href="javascript:void(0)"
										class="section {id : '<s:property value="attachmentsId"/>',desc : '<s:property value="des4Js"/>', videoURL : '<s:property value="%{(new vn.com.vndirect.web.struts2.analysistools.AnalysisReportAction()).getURL(originalLink)}"/>'}"><s:text
											name="web.label.NewsInfo.Sector" /> <s:property
											value="#idx.count" /> </a>
								</s:iterator>
							</span>
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
								<s:property value="fileName" />
							</a> &nbsp;
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
									value="ifoNews.newsResource" /> </b>
						</span>

						<!-- error display here -->

						<div class="Lead news-content" style="width: 100%; float: left;">
							<s:property value="ifoNews.newsContent" escape="false" />
						</div>
						<c:if test="${ifoNews.newsType != 'Expert'}">
							<div>
								<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
									<b>File đính kèm :</b>
								</c:if>
								<c:forEach var="item" items="${ifoNews.newsAttWithoutVideos }">
								    <a
                                        href="javascript:download('${item.originalLink}', 'newsAttach', '${item.fileName}', '${item.attachmentsId}');">
                                        ${item.fileName}
                                    </a> &nbsp;
                                    
                                    <script>
                                    DOCs.push('${item.originalLink}');
                                    TYPEs.push('newsAttach');
                                    </script>
                                    
								</c:forEach>
							</div>
						</c:if>
						
						<!-- flexpaper-viewer -->
						<%-- <div>
						  <jsp:include page="/WEB-INF/jsps/common/flexpaper-viewer.jsp"></jsp:include>
						</div> --%>
						
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

				<ul class="n_list_other list_detail_tt_nd"
					id="NewsDetail_ListOtherInDayNews_All_Content">
					<li class="title bgTitleBar"><s:text
							name="web.label.NewsAction.form.newNews">New News</s:text></li>
					<div class="n_list_other_detail" id="NewsDetail_ListOtherInDayNews">
						<img src="<web:url value='/images/ajax-loader.gif'/>" />
					</div>
				</ul>

				<ul class="n_list_other list_detail_tt_nd"
					id="NewsDetail_ListOtherNews_All_Content">
					<li class="title bgTitleBar"><s:text
							name="web.label.NewsAction.form.otherNews">Other News</s:text></li>
					<div class="n_list_other_detail" id="NewsDetail_ListOtherNews">
						<img src="<web:url value='/images/ajax-loader.gif'/>" />
					</div>
				</ul>
			</div>

			<div id="c-column" class="width340">
				<div class="box_listnew" id="ttpt-mostViewedNews-box">
					<span class="ajaxLoadingIcon"></span>
				</div>
				<jsp:include page="../facebook/FacebookLikeBox.jsp" />
			</div>
			
		</div>
		<!-- end #c-column -->
	</div>
</div>
</div>