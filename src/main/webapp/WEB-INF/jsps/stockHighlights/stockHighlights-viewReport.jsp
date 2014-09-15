<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
var DOCs;
DOCs.push('${model.searchIfoNews.filePath}');
</script>

<jsp:include page="/WEB-INF/jsps/analysistools/snippet/analysis-news-nav.jsp"></jsp:include>

<div class="stockCenter">
    <c:choose>
        <c:when test="${not empty model.searchIfoNews}">
            <input type="hidden" value="${model.searchIfoNews.newsHeader}" id="currentNewsHeader"/>
			<div class="ttStockCenter">
				<h2>${model.searchIfoNews.newsHeader}</h2>
				<a href="<web:url value='/co-phieu-tam-diem.shtml' />" class="backico">Back</a>
				<a onclick="loadMenuOnReportView();" class="menuico active">Menu</a>
				
				<div class="boxStockHot">
	                <div class="listReportsub" id="listSymbolsHaveReports">
	                   <span class="ajaxLoadingIcon"></span>
	                </div>
	                
	                <form style="position:relative;z-index:9999;" 
		                    onsubmit="return isAvailableSubmitVoteReport();"
		                    action="https://docs.google.com/forms/d/1f35ThFHXcj_hWZ4xQ-y15nj8MngKhwhUapB-rrBhpYs/formResponse" 
		                    method="POST">
		                <div class="textareaReply">
		                    <input type="hidden" id="currentNewsHeader" name="entry.1580783082"/>
		                    <textarea class="reviewTextarea" name="entry.573537844" id="entry573537844"
		                        onfocus="if (this.value=='Gửi bình luận/ góp ý về báo cáo') this.value='';"
		                        onblur="if (this.value=='') this.value='Gửi bình luận/ góp ý về báo cáo';">Gửi bình luận/ góp ý về báo cáo</textarea>
		                    <input class="iButton reviewBtn" type="submit" value='Gửi' />
		                </div>
		            </form>
	                
	                <a onclick="closeMenuOnReportView();" class="btn-close">Close</a>
	            </div>
			</div>

            <jsp:include page="/WEB-INF/jsps/common/flexpaper-viewer.jsp"></jsp:include>
            <div class="clear"></div>
        </c:when>
        <c:otherwise>
            TODO: thông báo k có data hoac qua 6 thang
        </c:otherwise>
    </c:choose>	
</div>

