<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<script type="text/javascript">
<!--
	var firstVideoURL = '';
//-->
</script>
<s:if test="#titles.keys.(#this.contains(#parameters[\"newsType\"][0]))">
	<s:set var="text" value="#titles.get(#parameters[\"newsType\"][0])"></s:set>
</s:if>
<s:else>
	<s:set var="text" value="getText('web.label.analysisTool.Report.LatestReport')"></s:set>
</s:else>

<c:if test="${ifoNews.newsType == 'PM'}">
	<s:set var="text" value="getText(\"leftmenu.label.analysisTool.MarketReview\")"></s:set>
</c:if>


<div id="content_ttpt">         
		
     <ul class="ui-tabs-nav tab_ttpt">                               
        <li <web:menuOut code='subMenuAnalysis_News_MacVN' classValue='ui-tabs-selected'/>><a href="<web:url value='/analysis/news/macvn.shtml'/>">Tin trong nước</a></li>
        <li <web:menuOut code='subMenuAnalysis_News_MacWorld' classValue='ui-tabs-selected'/>><a href="<web:url value='/analysis/news/macworld.shtml'/>">Tin quốc tế</a></li>
        <li <web:menuOut code='subMenuAnalysis_News_Experts' classValue='ui-tabs-selected'/>><a href="<web:url value='/analysis/news/experts.shtml'/>">Ý kiến chuyên gia</a></li>
        <li <web:menuOut code='subMenuAnalysis_News_Disclousure' classValue='ui-tabs-selected'/>><a href="<web:url value='/analysis/news/disclousure.shtml'/>">Công bố thông tin</a></li>
        <li <web:menuOut code='subMenuAnalysis_News_Marketcalendar' classValue='ui-tabs-selected'/>><a href="<web:url value='/analysis/news/marketcalendar.shtml'/>">Lịch sự kiện</a></li>                                                           
    </ul>       

    <div class="clear"></div>       
    	
	<div class="content_small">
	    <div class="content_tab" id="tab-1">
	        <div class="content_ttpt">  	    				
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>
							<div style="margin: 20px 5px 0px 7px" align="left">
								<!-- ################## -->
								<input type="hidden" value="${ifoNews.newsId}" id="NewsDetail_NewsId"/>
								<input type="hidden" value="${ifoNews.newsType}" id="NewsDetail_NewsType"/>
								<input type="hidden" value="${ifoNews.locale}" id="NewsDetail_Local"/>
								<table>
									<tr><td>
										<s:if test="%{ifoNews.hasVideoAtt}">
											<div class="" style="padding:10px 5px">
										    	<table cellpadding="0" cellspacing="0" border="0" width="100%">
										       		<tr>
										       			<td valign="top">
										       				<p class="n-hdr-title">
																<s:property value="ifoNews.newsHeader" escape="false"/> 
																
															</p>
															<p class="n-hdr">
																<fmt:formatDate pattern="hh:mm a -| dd/MM/yyyy" value="${ifoNews.newsDate}"/>
																  - <b><s:property value="ifoNews.newsResource"/></b> 
															</p>
													       	<br/>
										           			<cite>
										           				<s:property value="ifoNews.newsAbstract" escape="false"/>
										           			</cite>
										           			<br/>
										               	</td>
										               	<td width="15">&nbsp;</td>
										               	<td valign="top" width="245" align="right">
										               		<s:if test="%{!@vn.com.vndirect.commons.utility.VNDirectWebUtilities@isOnTradingTime()}">
										               			<div id="player">
											               		</div>
											               		<span class="detail_video" style="padding-left:5px">
											               			<span id="videoDesc"><s:property value="ifoNews.attachmentVideoNews[0].description"/></span>
											               			<br/>
											               			<s:iterator var="item" value="ifoNews.attachmentVideoNews" status="idx">
											            				<a href="javascript:void(0)" class="section {id : '<s:property value="attachmentsId"/>',desc : '<s:property value="des4Js"/>', videoURL : '<s:property value="%{(new vn.com.vndirect.web.struts2.analysistools.AnalysisReportAction()).getURL(originalLink)}"/>'}"><s:text name="web.label.NewsInfo.Sector"/> <s:property value="#idx.count"/></a>
											               				<br/>
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
																		<img src='<s:url value="/flash/flashplayer_VN.jpg" />'/>
																	</c:when>
																	<c:otherwise>
																		<img src='<s:url value="/flash/flashplayer_EN.jpg" />'/>
																	</c:otherwise>
																</c:choose>
										               		</s:else>
										               	</td>
										            </tr>
										            <tr>
										    			<td colspan="3">
										    				<p class="n-text">
										    					<s:property value="ifoNews.newsContent" escape="false"/>
															</p>
															<div>
																<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
																	<b>File đính kèm :</b>
																</c:if>
																<s:iterator value="ifoNews.newsAttWithoutVideos">
																	<a href = "javascript:download('<s:property value="originalLink"/>', 'newsAttach', '<s:property value="fileName"/>', '<s:property value="attachmentsId"/>');">
																		<s:property value="fileName"/>
														            </a> &nbsp;
																</s:iterator>
														    </div>
										    			</td>
										            </tr>
										        </table>
										    </div>
										</s:if>
										<s:else>
											<div id="new_content_id" class="" style="padding:10px 5px">
												<p class="n-hdr-title">
													<s:property value="ifoNews.newsHeader" escape="false"/> 
												</p>
												<p class="n-hdr">													
													<fmt:formatDate pattern="hh:mm a | dd/MM/yyyy" value="${ifoNews.newsDate}"/>
													  | <b><s:property value="ifoNews.newsResource"/></b> 
												</p>
												<br/>
												<div align="left">
													<s:property value="ifoNews.newsContent" escape="false"/>
												</div>
												<c:if test="${ifoNews.newsType != 'Expert'}">
													<div>
														<c:if test="${fn:length(ifoNews.newsAttWithoutVideos)!=0}">
															<b>File đính kèm :</b>
														</c:if>
														<s:iterator value="ifoNews.newsAttWithoutVideos">
															<a href = "javascript:download('<s:property value="originalLink"/>', 'newsAttach', '<s:property value="fileName"/>', '<s:property value="attachmentsId"/>');">
																<s:property value="fileName"/>
												            </a> &nbsp;
														</s:iterator>
												    </div>
											    </c:if>
											    <br/>
										    </div>
										</s:else>
									</td></tr>
									
									<tr><td>
										<!-- ################## -->
										<div class="n_list_other" id="NewsDetail_ListOtherInDayNews_All_Content">
											<p><b><s:text name="web.label.NewsAction.form.newNews">New News</s:text></b></p>
											<hr>
											<div style="margin: 5px" id="NewsDetail_ListOtherInDayNews"></div>
										</div>
										<div class="n_list_other" id="NewsDetail_ListOtherNews_All_Content">
											<p><b><s:text name="web.label.NewsAction.form.otherNews">Other News</s:text></b></p>
											<hr>
											<div style="margin: 5px" id="NewsDetail_ListOtherNews"></div>
										</div>
										<div class="n_list_other" id="NewsDetail_ViewMoreNew">
											<p><b>
												<c:choose>
													<c:when test="${ifoNews.newsType == 'MacVN'}">
														<a href="<web:url value="/analysis/news/macvn.shtml"/>">														
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'MacWorld'}">
														<a href="<web:url value="/analysis/news/macword.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'Disclousure'}">
														<a href="<web:url value="/analysis/news/disclousure.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'IPO'}">
														<a href="<web:url value="/listed/MacroNews_IPO.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>														
													<c:when test="${ifoNews.newsType == 'VNDIRECT'}">
														<a href="<web:url value="/home/info/InfoNewsOfVndirect.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'IR'}">
														<a href="<web:url value="/home/info/InfoShareholderNews.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'Expert'}">
														<a href="<web:url value="/analysis/news/experts.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
													<c:when test="${ifoNews.newsType == 'PM'}">
														<a href="<web:url value="/analysis/NewsDetail_MarketReview.shtml"/>">
															<s:text name="web.label.NewsAction.form.viewMoreNews">View More News</s:text>
														</a>
													</c:when>
												</c:choose>												
											</b></p>												
										</div>
									</td></tr>
								</table>
							</div>
						</td>
					</tr>
				</table>				
	         </div>	
	         
	         <div id="c-column" class="width340">
                
                <form class="box_loc" action="">                    
                    <br/>
                      
                    <input type="checkbox"  id="loc"/> &nbsp;
                    <label for="loc">Lọc thông tin từ </label>
                    
                    <input class="input" name="strFromDate" type="text" id="fHistoricalPrice_FromDate" value="Ngày" onblur="if(this.value=='') this.value='Ngày';" onfocus="if(this.value=='Ngày') this.value='';"/> đến                                        
                    <input class="input" name="strToDate" type="text" id="fHistoricalPrice_ToDate" value="Ngày" onblur="if(this.value=='') this.value='Ngày';" onfocus="if(this.value=='Ngày') this.value='';"/> 
                    
                </form>
                     
             </div><!-- end #c-column -->
	         	
		   </div>
		</div>
	</div>
</div>