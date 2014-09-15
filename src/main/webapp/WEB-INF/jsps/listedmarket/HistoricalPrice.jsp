<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="fHistoricalPrice" id="fHistoricalPrice" method="post" action="">
    <input type="hidden" name="model.downloadType" id="downloadType">         
    <input id="pagingInfo_indexPage" type="hidden" name="pagingInfo.indexPage" value="1" />
    <div id="content_ttpt">        

        <!-- nav -->
        <jsp:include page="/WEB-INF/jsps/listedmarket/snippet/market-overview-nav.jsp"></jsp:include>

        <div class="content_small">
            <div class="content_tab" id="tab-1">            
                <div class="paging">
                    <web:showPaging usingURLForNav="false" navAction="_goTo" pagingInfo="${pagingInfo}" usingPageOverTotal="true" usingLastPage="true"></web:showPaging>                                                        
                </div> 
                
                <div class="box_content_tktt">
                    <div class="box_tienich">
                        <span class="mck"><strong><s:text name="web.label.symbol" /></strong>
                            <input type="text" name="searchMarketStatisticsView.symbol" id="symbolID" class="inputmck" value="${model.searchMarketStatisticsView.symbol}">                            
                        </span>
                        
                        <span class="time"> <span><s:text name="web.label.CompanyEventsAction.Fromdate"/></span>        
                            <input name="strFromDate" value="${model.strFromDate}" type="text" id="fHistoricalPrice_FromDate"/>&nbsp;&nbsp;&nbsp; <span><s:text name="web.label.CompanyEventsAction.Todate"/></span>                             
                            <input name="strToDate" value="${model.strToDate}" type="text" id="fHistoricalPrice_ToDate" class=""/>                            
                        </span>
                        
                        <input type="submit" style="margin-left:20px;width:100px;" id="fHistoricalPrice_View" class="iButton autoHeight" value="<s:text name="button.search"/>">
                        
                        <div class="box_taidulieu">                        
                            <a href="javascript:doDownload();">
                              <span class="icon_download left"></span> <span class="text">
                              <s:text name="button.downloadFile"/>                              
                              </span>
                            </a>
                        </div>
                    </div>
                    <ul class="list_tktt lichsugia">
                        <li class="header box_lsg_header">
                            <div class="row-time  noline"><s:text name="web.label.date">Ngày</s:text></div>
                            <div class="row2"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Change"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Open"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.CellNames.High"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Low"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.CellNames.Close"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AveragePrice"/></div>
                            <div class="row1"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AdjustPrice"/></div>
                            <div class="row3"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Volume"/></div>
                            <div class="row3"><s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.PtVolume"/></div>
                        </li>
                        
	                    <c:forEach var="item" items="${model.searchResult}" varStatus="i">
							<li>
								<div class="row-time  noline">	
								    ${item.id.transDate}
								    <%--<fmt:formatDate pattern="hh:mm a | dd/MM/yyyy" value="${item.id.transDate}"/>--%>
								</div>
								<div class="row2">
									<c:choose>
									     <c:when test="${item.chgIndex > 0}">										          										    
									          <span class="textup">
									              <fmt:formatNumber pattern="+###,###.#"><c:out value="${item.chgIndex}" /></fmt:formatNumber>
									              <fmt:formatNumber pattern="(+###,###.##%)"><c:out value="${item.pctIndex/100}" /></fmt:formatNumber>
									              <label id="hoseIcon" class="print right icon-up"></label>										              
									          </span>     
									     </c:when>
									     
									     <c:when test="${item.chgIndex < 0}">
                                                 <span class="textdow">
                                                     <fmt:formatNumber pattern="###,###.#"><c:out value="${item.chgIndex}" /></fmt:formatNumber>
                                                     <fmt:formatNumber pattern="(-###,###.##%)"><c:out value="${(item.pctIndex*-1)/100}" /></fmt:formatNumber>
                                                     <label id="hoseIcon" class="print right icon-dow"></label>
                                                 </span>     
                                            </c:when>
									     
									     <c:when test="${item.chgIndex == 0}">
                                                 <span class="texttrunglap">
                                                     <fmt:formatNumber pattern="###,###.#"><c:out value="${item.chgIndex}" /></fmt:formatNumber>
                                                     <fmt:formatNumber pattern="(###,###.#%)"><c:out value="${item.pctIndex}" /></fmt:formatNumber>
                                                     <label id="hoseIcon" class="print right icon-nochange"></label>
                                                 </span>     
                                            </c:when>
									</c:choose>																			
								</div>
								
								<div class="row1">${item.id.openPrice}</div>
								<div class="row1">${item.id.highPrice}</div>
								<div class="row1">${item.id.lowPrice}</div>
                                   <div class="row1">${item.id.closePrice}</div>									
								<div class="row1">									
								   <fmt:formatNumber pattern="###,###.##"><c:out value="${item.id.averagePrice}" /></fmt:formatNumber>
								</div>
								<div class="row1">${item.id.adClosePrice}</div>
								<div class="row3">									   
								   <fmt:formatNumber pattern="###,###.##"><c:out value="${item.id.volume}" /></fmt:formatNumber>   
								</div>
								<div class="row3">
								   <c:choose>
								       <c:when test="${item.id.ptVolume == 0}">
								           - 
								       </c:when>
								       <c:when test="${item.id.ptVolume != 0}">
								           <fmt:formatNumber pattern="###,###.##"><c:out value="${item.id.ptVolume}" /></fmt:formatNumber>									           
								       </c:when>
								   </c:choose>									   									  									 
						        </div>
							</li>
						</c:forEach>                          
                    </ul>                                                                             
                </div>
                <!-- End HistoricalPrice -->                    
                <div class="note">
                    <s:text name="web.label.MarketStatisticsAction.form.HistoricalPrice.Description"></s:text>
                </div>
            </div>
        </div>
    </div>
    <!--End All Tab menu  -->
</form>