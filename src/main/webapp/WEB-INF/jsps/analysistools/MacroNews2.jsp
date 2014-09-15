<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

 <c:choose>
	<c:when test="${model.newsType == 'MacVN'}">
		<s:set var="urlAction">
			<web:url value="tin-trong-nuoc" />
		</s:set>
	</c:when>
	<c:when test="${model.newsType == 'MacWorld'}">
		<s:set var="urlAction">
			<web:url value="tin-quoc-te" />
		</s:set>
	</c:when>
	<c:when test="${model.newsType == 'Disclousure'}">
		<s:set var="urlAction">
			<web:url value="cong-bo-thong-tin" />
		</s:set>
	</c:when>
	<c:when test="${model.newsType == 'Experts'}">
		<s:set var="urlAction">
			<web:url value="y-kien-chuyen-gia" />
		</s:set>
	</c:when>
</c:choose>

<div id="content_ttpt">
    <input type="hidden" id="url_action" name="url_action" value="<s:property value="#urlAction"/>">
    <input type="hidden" id="analysis-news-newsType" value="${model.newsType}">
    <input type="hidden" id="analysis-news-totalPage" value="${pagingInfo.totalPage}">
	<a name="top"></a>
	<ul class="ui-tabs-nav tab_ttpt">
	<c:choose>
		<c:when test="${model.newsType == 'Experts'}">
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

	<div class="clear"></div>       
    <div class="content_small">
        <div class="content_tab" id="tab-1">
            <div class="content_ttpt">
                 <form name="ttptNews_SearchNews" id="search_form" class="box_loc" action="<s:property value="#urlAction"/>" method="post">                                                            
                    <label for="loc"><s:text name="analysis.news.fromDate"/></label>                                     
                    <input style="height: 20px; width: 88px;" name="strFromDate" type="text" id="fHistoricalPrice_FromDate" value="${strFromDate}"/> 
                    &nbsp;&nbsp;&nbsp;<s:text name="analysis.news.toDate"/>                                        
                    <input style="height: 20px; width: 88px;" name="strToDate" type="text" id="fHistoricalPrice_ToDate" value="${strToDate}"/>
                    <input type="submit" id="fHistoricalPrice_View" value="<s:text name="button.search"/>" class="iButton">                                              
                 </form>
                
                 <c:choose>
				    <c:when test="${empty model.searchResult}">
				        <p style="float: left; width: 100%;"><s:text name="newsMessage.empty"/></p>
				    </c:when>
				    <c:otherwise>
                        <ul class="listnew">                    
		                    <c:forEach var="item" items="${model.searchResult}">
		                         <li class="content_list">                                                                           
		                            <h2 class="title"><a href="${item.urlDetail}"/>${item.newsHeader}</a></h2>
		                            <p class="time">${item.newsDateTimeStr} | ${item.newsDateStr} <b> ${item.newsResource}</b></p>
		                            <p>${item.newsAbstract}</p>
		                        </li>
		                     </c:forEach>
		                 </ul>
				    </c:otherwise>
				</c:choose>
                 
                 <div class="clearfix"></div>
                 <div class="content_bar">
                    <div id="loadingMoreImage" style="text-align: center;"></div>
                    <div class="barGoTop">
                        <a href="#top" id="goTop"><s:text name="newsMoreNav.backToTop"/></a>
                    </div>                                        
                    <div class="barLoadMore">
                        <a href="" id="loadMore"><s:text name="newsMoreNav.more"/></a>
                    </div>
                 </div>
             </div><!-- end .content_ttpt -->
             
             <div id="c-column" class="width340">
                <div class="box_listnew" id="ttpt-mostViewedNews-box">
                    <span class="ajaxLoadingIcon"></span>
                </div>
             </div><!-- end #c-column -->
         </div> 
              
    </div><!-- end .content_small -->
           
</div><!-- end .content_ttpt -->       