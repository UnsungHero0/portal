<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
<!--
	var OTHER_NEWS_MESSAGE = '<s:text name="web.label.NewsAction.form.otherNews" />';
//-->
</script>

<div class="pn_main" id="page_TTNY">
	<div class="tab clearfix">		
		<div class="tabs_TTNY">
			<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
      			<li class=""><a href="<web:url value='/analysis/AnalysisHome.shtml'/>"><b>Tin trong nước</b></a></li>
      			<li class=""><a href="<web:url value='/analysis/MacroNews_MacWorld.shtml'/>"><b>Tin quốc tế</b></a></li>
      			<li class=""><a href="<web:url value='/analysis/Commentaries.shtml'/>"><b>Ý kiến chuyên gia</b></a></li>
      			<li class=""><a href="<web:url value='/analysis/MacroNews_Disclousure.shtml'/>"><b>Công bố thông tin</b></a></li>
      			<li class=""><a href="<web:url value='/analysis/MarketCalendar.shtml'/>"><b>Lịch sự kiện</b></a></li>        				
			</ul>	
		</div>
		
		<div style="border: 1px solid #616D7E; border-top: none;">
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>
			<div class="center_inner clearfix">
				<!---->
				<div class="firstline_sector clearfix">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>
								<!-- button 1 -->
								<s:if test="%{commentaryNews}">
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
													<input type="button" onclick="window.location='<web:url value="/analysis/Commentaries.shtml"/>'" value="<s:text name="leftmenu.label.analysisTool.Outlook.Commentaries"/>">
											</span>
										</span>
									</span>
								</s:if>
								<s:else>
									<span class="btn_left_SQ">
										<span class="btn_right_SQ">
											<span class="btn_center_SQ">
												<input type="button" value="<s:text name="leftmenu.label.analysisTool.Outlook.Commentaries"/>">
											</span>
										</span>
									</span>
								</s:else>
								
								<!-- button 2 -->
								<s:if test="%{brokerNews}">
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
													<input type="button" onclick="window.location='<web:url value="/analysis/Brokers.shtml"/>'" value="<s:text name="leftmenu.label.analysisTool.Outlook.Brokers"/>">
											</span>
										</span>
									</span>
								</s:if>
								<s:else>
									<span class="btn_left_SQ">
										<span class="btn_right_SQ">
											<span class="btn_center_SQ">
												<input type="button" value="<s:text name="leftmenu.label.analysisTool.Outlook.Brokers"/>">
											</span>
										</span>
									</span>
								</s:else>
								
								<!-- button 3 -->
								<s:if test="%{expertNews}">
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
													<input type="button" onclick="window.location='<web:url value="/y-kien-chuyen-gia.shtml"/>'" value="<s:text name="leftmenu.label.analysisTool.Outlook.Experts"/>">
											</span>
										</span>
									</span>
								</s:if>
								<s:else>
									<span class="btn_left_SQ">
										<span class="btn_right_SQ">
											<span class="btn_center_SQ">
												<input type="button" value="<s:text name="leftmenu.label.analysisTool.Outlook.Experts"/>">
											</span>
										</span>
									</span>
								</s:else>
								
								<!-- button 4 -->								
								<s:if test="%{macroReport}">
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
												<input type="button" onclick="window.location='<web:url value="/analysis/MacroReport.shtml"/>'" value="<s:text name="leftmenu.label.analysisTool.Report.Analysis"></s:text>">
											</span>
										</span>
									</span>
								</s:if>
								<s:else>
									<span class="btn_left_SQ">
										<span class="btn_right_SQ">
											<span class="btn_center_SQ">
												<input type="button" value="<s:text name="leftmenu.label.analysisTool.Report.Analysis"></s:text>">
											</span>
										</span>
									</span>
								</s:else>
							</td>
						</tr>
					</table>
				</div>
				<tiles:insertAttribute name="MainContent"></tiles:insertAttribute>
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
	</div>
</div>
