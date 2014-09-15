<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
$(function() {
    StockPick.init();
});
</script>

<div class="recommendDaily">
	<jsp:include page="/WEB-INF/jsps/consultingcenter/investment-idea/investment-idea-nav.jsp" />

<c:choose>
<c:when test="${not empty searchIfoNews}">

    <input type="hidden" id="currentDate" value="${date}"/>
	
	<div class="recommenDate">
		<p class="txt01">KHUYẾN NGHỊ CỔ PHIẾU NGÀY ${date}</p>
	</div>

    <div id="newscontent-tabs">
		<div class="rcmIndex">
			<c:if test="${not empty searchIfoNews}">
                <c:choose>
                    <c:when test="${commonNews eq true}">
						<div class="rcmIndexL rcmNote02">
                            <!-- @use ul,li here to valid jquery tabs -->
							<ul>
								<li>
									<h3>${searchIfoNews[0].newsHeader}</h3>
									<p>${searchIfoNews[0].newsAbstract}</p>
								</li>
							</ul>
						</div>
					</c:when>
                    <c:otherwise>
	                    <div class="rcmIndexL">
		                    <ul class="nameIndex">
		                        <c:forEach var="ifonews" items="${searchIfoNews}" varStatus="i">
		                              <c:set var="clz" value=""></c:set>
		                              <c:if test="${i.count eq 1}">
		                                  <c:set var="clz" value="ui-tabs-active"></c:set>
		                              </c:if>
		                             <li ref="tab-${i.count}-${ifonews.strSymbol}">
		                                <a href="#newscontent${i.count}" class=""><span>${ifonews.strSymbol }</span>
		                                    <p><c:choose>
		                                    <c:when test="${fn:length(ifonews.newsAbstract) gt 50}">
		                                        ${fn:substring(ifonews.newsAbstract, 0, 50)} ..
		                                        </c:when>
		                                        <c:otherwise>
		                                        ${ifonews.newsAbstract }
		                                        </c:otherwise>
		                                        </c:choose>
		                                    </p>
		                                </a>
		                            </li>
		                        </c:forEach>
		                    </ul>
		                </div>
                    </c:otherwise>
                </c:choose>
	          </c:if>
				<div class="rcmIndexR">
					<%--  --%>
					<web:productSubject var="objProdSub" productCode="STOCK_PICK_LAST" isUsingCache="false"/>
		            <c:out value="${objProdSub['product'].productOverview}"
		                escapeXml="false" />
				</div>
		</div>
		<!--rcmIndex-->
		
		<c:if test="${not empty searchIfoNews}">
		    <c:forEach var="ifonews" items="${searchIfoNews}" varStatus="i">
				<div class="boxAnalysis" id="newscontent${i.count}" ref="tab-${i.count}-${ifonews.strSymbol}">
				  ${ifonews.newsContent }
				  
				  <jsp:include page="/WEB-INF/jsps/consultingcenter/investment-idea/khuyen-cao-snippet.jsp"></jsp:include>
				</div>
			</c:forEach>
		</c:if>
	</div>
	
    <div class="clear"></div>
	
</c:when>
<c:otherwise>
    <p style="color:red">Không tìm thấy dữ liệu</p></br></br>
</c:otherwise>
</c:choose>
	
	<!-- Khuyến nghị đang mở/đóng -->
    <jsp:include page="/WEB-INF/jsps/consultingcenter/investment-idea/OpenAndCloseStockPick-snippet.jsp"></jsp:include>

	<div class="rcmNewBox">
		<div class="rcmRecent">
		    <div id="newerReports">
		      <img src="<web:url value='/images/ajax-loader.gif' />" />
		    </div>
		    <div class="clear"></div>
		    <div id="relatedReports">
		      <img src="<web:url value='/images/ajax-loader.gif' />" />
		    </div>
        </div>
		<div class="rcmOther">
			<ul>
				<div id="marketNews">
					<img src="<web:url value='/images/ajax-loader.gif' />" />
				</div>
				<%-- <li>
				    <a href="#"><img src="images/khuyennghi/charticon.png" /></a>
					<p>
						<a href="#">Giải mã thị trường 24.02.2014: Có thể tiếp tục
							điều chỉnh dưới hình thái đi ngang.</a><span class="date">(24/02/2014)</span>
					</p>
				</li> --%>
			</ul>
		</div>
	</div>
	<!--/rcmNewBox-->

	<div class="clear"></div>

</div>
