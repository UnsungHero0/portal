<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
	var firstVideoURL = '<s:property value="%{getURL(news.attachmentVideoNews[0].originalLink)}"/>';
	var attachmentId = '<s:property value="%{news.attachmentVideoNews[0].attachmentsId}"/>';
	var isOnTradingTime = <%=VNDirectWebUtilities.isOnTradingTime()%>;
//-->
</script>
<div id="phan_tich_co_ban">
    <div class="tab clearfix">
		<div class="tab_infor">
			<a href="#" class="select_tab"><b><s:text name="web.label.report.Title"/></b></a>
		</div>
		<div style="border:1px solid #616D7E; border-top:none;">
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>
			<div class="center_inner clearfix">
               	<!---->
                <div class="Tbl-Search-Sector clearfix">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                        	<td>
                        		<strong><s:text name="web.label.report.Group"/></strong> 
                        		<web:refData var="listRefPropertyTypes" group="ATTACHMENTS"/>
								<s:select id="newsType" name="newsType" headerKey="1" list="listRefPropertyTypes" listValue="description" listKey="itemCode" theme="simple"/>
                        	</td>
                            <td width="20"></td>
                            <td>
                            	<strong><s:text name="web.label.date"/></strong>
                            	<input id="date-pick" type="text" size="20" style="padding:1px 2px; margin:0 6px" />
                            </td>
                            <td>
                            	<span class="btn_left_inbox"> 
									<span class="btn_right_inbox"> 
										<span class="btn_center_inbox">
											<input id="search" type="button" value="<s:text name="button.search"/>">
										</span>
									</span>
								</span>
                            </td>
                        </tr>
                    </table>
                </div>
                <!---->
                <div class="clearfix" style="padding:5px">
               		<div class="" style="padding:10px 5px">
                   		<table cellpadding="0" cellspacing="0" border="0" width="100%">
                       		<tr>
                       			<td valign="top"><p><b class="bluetext" style="font-size:14px">${news.newsHeader}</b></p>
                           			<div style="text-align: justify">${news.newsAbstract}</div>
                           			<br/>
                               	</td>
                               	<td width="15">&nbsp;</td>
                               	<td valign="bottom" width="245" align="center">
                               		<s:if test="news.hasVideoAtt">
                               			<s:if test="%{!@vn.com.vndirect.commons.utility.VNDirectWebUtilities@isOnTradingTime()}">
	                                 		<div id="player">
	                                 		</div>
	                                 		<span class="detail_video" style="padding-left:5px">
	                                 			<span id="videoDesc"><s:property value="news.attachmentVideoNews[0].description"/></span>
	                                 			<br/>
	                                 			<s:iterator var="item" value="news.attachmentVideoNews" status="idx">
	                              				<a href="javascript:void(0)" title="<s:property value="description"/>" class="section {id : <s:property value="attachmentsId"/>,desc : '<s:property value="des4Js"/>', videoURL : '<s:property value="%{getURL(originalLink)}"/>'}"><s:text name="web.label.analysisTool.Report.Sector"/> <s:property value="#idx.count"/></a>
	                                 				<br/>
	                              			</s:iterator>
	                                 		</span>
                                		</s:if>
                                		<s:else>
                                			<div>
					               				<c:choose>
													<c:when test="${locale == 'vn'}">
														<img src='<s:url value="/flash/video_bantin_VN.jpg" />' width="300"/>
													</c:when>
													<c:otherwise>
														<img src='<s:url value="/flash/flashplayer_EN.jpg" />' width="300"/>
													</c:otherwise>
												</c:choose>
					               			</div>
					               		</s:else>
                               		</s:if>
                               		<s:if test="news">
                               			<span id="detail">
                            				<script type="text/javascript">
                            					document.write(getNewsDetailUrl('${news.newsId}', '<s:text name="web.label.detail"/> &raquo;', '${news.newsType}'));
                            				</script>
                            			</span>
                               		</s:if>
                               	</td>
                           </tr>
                       </table>
                   	</div>	
					<!---->
                   	<div id="dailyNews" class="table_company clearfix" style="padding-top:20px">
                        <table cellpadding="0" cellspacing="0" border="0" width="748" style="border-top:1px solid #B0B0B0">
                        	  <thead>
                             <tr style="background:url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
                                 <td width="80"><span class="txtText"><b><s:text name="web.label.date"/></b></span></td>
                                 <td align="left"><span class="txtText"><b class="bluetext" id="title"><s:property value="#titles.get(newsType)"/></b></span></td>
                                 <%--td width="80" align="center" ><span class="txtText"><b>Video</b></span></td> --%>
                                 <td width="75" align="center" class="col_end"><span class="txtText"><b>File</b></span></td>
                             </tr> 
                            </thead>
                            <tbody>
                            	
                            </tbody>
                        </table>
                        <div class="fpCalendar">
							<div class="fpCalendar-left"></div>
							<div class="fpCalendar-right"></div>
							<div align="right" class="fpCalendar-center" id="dailyNewsNavigator"></div>
						</div>	
                    </div>
                    <!---->
                    <div id="latestReport" class="table_company clearfix" style="padding-top:20px">
                        <table cellpadding="0" cellspacing="0" border="0" width="748" style="border-top:1px solid #B0B0B0">
                        	<thead>
								<tr style="background:url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
									<td width="80"><span class="txtText"><b><s:text name="web.label.date"/></b></span></td>
                                   	<td align="left"><span class="txtText"><b class="bluetext"><s:text name="web.label.analysisTool.Report.LatestReport"/></b></span></td>
                                   	<td width="190"><span class="txtText"><b style="color:#006699"><s:text name="web.label.report.Group"/></b></span></td>
                                   	<%--td width="80" align="center" ><span class="txtText"><b>Video</b></span></td> --%>
                                   	<td width="75" align="center" class="col_end"><span class="txtText"><b>File</b></span></td>
                               	</tr> 
                            </thead>
                            <tbody>                              	 
                            </tbody>
                      	</table>
                   		<div class="fpCalendar">
							<div class="fpCalendar-left"></div>
							<div class="fpCalendar-right"></div>
							<div align="right" class="fpCalendar-center" id="latestReportNavigator"></div>
						</div>                        
                	</div>
            	</div>            
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
	</div>
</div>
