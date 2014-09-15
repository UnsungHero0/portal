<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.StringTokenizer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<script type="text/javascript">

$(document).ready(function(){
    
    // default page = 1  
    var page = 1;
    var newsType = 'DailyReport';
    var totalPage = '<c:out value="${pagingInfo.total}" />';
    
    $("#loadMore").click(function(){ 
        page++;
        if(page <= totalPage){
            $.ajax({
                type: "POST",
                dataType: "json",
                data: "currentIndex="+page+"&newsType="+newsType,
                url: URL_LOAD_MORE_VIDEOS,
                success: function(responseText){                           
                    var model = responseText.model;
                    var html  = "";
                    $.each(model.searchResult, function(i, obj){
                    	html += '<li class="content_list">';
                    	html += '<img src="<web:url value="/images/thumbs/img_video.png"/>" class="photo">';
                        html += '<div class="boxcontent_small">';
                        html += '<h2 class="title"><a href="'+obj.urlDetail+'"/>'+obj.newsHeader+'</a></h2>';
                        if(obj.newsResource != null){
                          html += '<a class="textplay" href="'+obj.urlDetail+'"><span class="icon_video left"></span>Play Video</a>';
                        }
                        html += '</div>';
                    	html += '</li>';
                    });
                    $('ul.content_videos').append(html);
                    $("#loadingMoreImage").empty();
                },
                beforeSend: function() {
                    $("#loadingMoreImage").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
                }
            });
            if(page == totalPage){
            	$('.barLoadMore').css("display", "none");
            }
        }
        
        return false;
    });

});

function _goTo(index){          
    
    var url_action = $("#url_action").val();
    var url;
    if(index == 1){
        url = url_action + ".shtml";
    } else if(index > 1) {
        url = url_action + "-" + index + ".shtml";
    }
    $('#search_form').attr("action", url);
    $('#search_form').submit();
    return;
}
</script>
  
 <c:choose>
     <c:when test="${model.newsType == 'MacVN'}">
         <s:set var="urlAction"><web:url value="tin-trong-nuoc" /></s:set>
     </c:when>       
     <c:when test="${model.newsType == 'MacWorld'}">
         <s:set var="urlAction"><web:url value="tin-quoc-te" /></s:set>
     </c:when>               
     <c:when test="${model.newsType == 'Disclousure'}">
         <s:set var="urlAction"><web:url value="cong-bo-thong-tin" /></s:set>
     </c:when>
     <c:when test="${model.newsType == 'Experts'}">
         <s:set var="urlAction"><web:url value="y-kien-chuyen-gia" /></s:set>
     </c:when>
     <c:when test="${model.newsType == 'DailyReport'}">
         <s:set var="urlAction"><web:url value="nhan-dinh-thi-truong"/></s:set>
     </c:when>
 </c:choose>  
  
<div id="content_ttpt">
    
    <input type="hidden" id="url_action" name="url_action" value="<s:property value="#urlAction"/>">
    <a name="top"></a>        
    <ul class="ui-tabs-nav tab_ttpt">
        <li
            <web:menuOut code='subMenuAnalysis_News_MacVN' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/tin-trong-nuoc.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.localNews">Tin trong nước</s:text>
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/tin-quoc-te.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.internationalNews">Tin quốc tế</s:text>                      
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_MarketCommentary' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/nhan-dinh-thi-truong'/>">
                <label class="icon_active"></label><s:text name="analysis.news.marketCommentary">Nhận định TT</s:text>
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_Experts' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/y-kien-chuyen-gia.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.expertOpinions">Ý kiến chuyên gia</s:text>                       
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_Disclosure' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/cong-bo-thong-tin.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.disclosureInformation">Công bố thông tin</s:text>            
            </a>
        </li>
        <li
            <web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='ui-tabs-selected'/>>
            <a href="<web:url value='/lich-su-kien.shtml'/>">
                <label class="icon_active"></label><s:text name="analysis.news.marketCalendar">Lịch sự kiện</s:text>            
            </a>
        </li>
    </ul>  
        
    <div class="clear"></div>       
    <div class="content_small">
        <div class="content_tab" id="tab-1">
            <div class="content_ttpt">
                 
                 <form id="search_form" class="box_loc" action="<s:property value="#urlAction"/>" method="post">
                 <%--                                        
                    <label for="loc">Lọc thông tin từ </label>                                     
                    <input class="input" name="strFromDate" type="text" id="videoReport_FromDate" value="${strFromDate}"/> đến                                        
                    <input class="input" name="strToDate" type="text" id="videoReport_ToDate" value="${strToDate}"/>
                    <input type="submit" id="fHistoricalPrice_View" value="<s:text name="button.search"/>">                                              
                 --%>
                 </form>
                 
                 <%--                                                
                <div class="paging">
                    <web:showPaging usingURLForNav="false" navAction="_goTo" pagingInfo="${pagingInfo}"></web:showPaging>                                                        
                </div>                                                        
                --%>
                
                <ul class="list_video content_videos">                    
                    <c:forEach var="item" items="${model.searchResult}">
                         <li class="content_list">
                         
                            <img src="<web:url value='/images/thumbs/img_video.png'/>" class="photo">

							<div class="boxcontent_small">
								<h2 class="title"><a href="${item.urlDetail}"/>${item.newsHeader}</a></h2>
								<c:if test="${item.newsResource != ''}">
								    <a class="textplay" href="${item.urlDetail}"><span class="icon_video left"></span>Play Video</a>
								</c:if>								
							</div>
                        </li>
                     </c:forEach>
                 </ul>
                 
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
                <div class="box_listnew">
                    <div class="titleBar titleBar-margin-icon">
                        <span id="title"><s:text name="analysis.news.mostWatched">TIN XEM NHIỀU</s:text></span>
                    </div>
                    <hr class="hrline" />
                    <div class="content_small">
                        <ul class="list_video padding-top10">                        
	                    <c:forEach var="item" items="${model.searchMostView}">
	                        <li>
                                <img src="<web:url value='/images/thumbs/img_video.png'/>" class="photo_small">
                                <div class="content_small_2">
                                    <h2 class="title_small"><a href="${item.urlDetail}"/>${item.newsHeader}</a></h2>                                                                        
                                    <%-- <p><fmt:formatNumber pattern="###,###.## Views"><c:out value="${item.hit}" /></fmt:formatNumber></p>--%>
                                    <a class="textplay" href="${item.urlDetail}"><span class="icon_video left"></span>Play Video</a>
                                </div>
                            </li>       
	                    </c:forEach>               
                        </ul>          
                    </div>        
                </div>            
             </div><!-- end #c-column -->
         </div> 
              
    </div><!-- end .content_small -->
           
</div><!-- end .content_ttpt -->     
