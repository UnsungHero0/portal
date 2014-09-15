<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
	swfobject.embedSWF("<s:url value="/flash/open-flash-chart.swf"/>"
		, "PowerRatingChart", "230", "200", "9.0.0", "expressInstall.swf"
		, {"data-file":"<web:url value="/y-tuong-dau-tu/power-ratings-breakdown.shtml"/>"
			, "loading":"<s:text name='chart.label.loading'>Loading data...</s:text>" }
	);
</script>

<div style="display: none;" id="activeAccordIndex">${model.activeAccordIndex}</div>

<div style="height: 10px;"></div>

<div class ="general pn_main" id="ctn-general" style="padding:5px 7px">
	
	<ul class="hd">
		<li class="hd-left"></li>
		<li class="hd-right"></li>
		<li class="hd-center">
			<div  class="heading_pr">
				<h5>
					<s:text name="web.label.PowerRating.GeneralMarketRating" />
				</h5>
				<span class="fr heading_pr_date">${model.mostRecentDate}</span>
			</div>
		</li>
	</ul>


	
		
	<div class="bd">
		<div class="padding0px">
			<div class="clearfix" >
				<table cellpadding="0" cellspacing="0" border="0" width="100%"
					style="padding: 9px 2px;">
					<tr>
						<td style="width: 60%" valign="top">
							
								
									<table class="table1_PRs"  id="generalMarketPowerRatingTable"
									bordercolor="#ffffff" 
									width="100%" cellspacing="0" cellpadding="0" border="0">
									<colgroup>
										<col width="15%">
										<col width="11%">
										<col width="14%">
										<col width="18%">
										<col width="16%">
										<col width="11%">
									</colgroup>	
									<tbody> 
									<tr>
											<th >
												<s:text name="web.table.header.Indices" />
											</th>
											<th  >
												<s:text name="web.table.header.TodayPowerRating" />
											</th>
											<th  >
												<s:text name="web.table.header.TodayPrice" /> 
											</th>
											<th  >
												<s:text name="web.table.header.FourDayAgoSessionPR" />
											</th>
											<th  >
												<s:text name="web.table.header.PriceVariantPercent" /> 
											</th>
											<th  >
												<s:text name="web.table.header.ViewGrap" />
											</th>
										</tr>
										</tbody>
									</table >
									<table class="table1_PRs_active"  
									bordercolor="#ffffff" 
									width="100%" cellspacing="0" cellpadding="0" border="0">
									<colgroup>
										<col width="15%">
										<col width="11%">
										<col width="14%">
										<col width="18%">
										<col width="16%">
										<col width="11%">
									</colgroup>	
									<tbody> 
										<c:choose>
											<c:when test="${not empty model.generalMarketPrs}">
												<c:forEach var="item" items="${model.generalMarketPrs}" varStatus="status">
													<tr>
														<td   nowrap="nowrap" >
															<span class="padtext bold2"><c:out value="${item.symbol}"/></span>
														</td>
														<td >
														<c:choose>
														  <c:when test="${item.pctMarkChange == null}">
														    <c:set var="className" value="padtext"></c:set>
														  </c:when>
														  <c:when test="${item.pctMarkChange == 0}">
														    <c:set var="className" value="padtext color3"></c:set>
														  </c:when>
														  <c:when test="${item.pctMarkChange < 0}">
														    <c:set var="className" value="padtext color1"></c:set>
														  </c:when>
														  <c:otherwise>
														    <c:set var="className" value="padtext color2"></c:set>
														  </c:otherwise>
														</c:choose>
															<span class="${className}"><div align="right" style="width: 20px"><c:out value="${item.mark}"/></div> </span>
															
														</td>
														<td >
															<c:choose>
															  <c:when test="${item.pctPriceChange == null}">
															    <c:set var="className" value="padtext"></c:set>
															  </c:when>
															  <c:when test="${item.pctPriceChange == 0}">
															    <c:set var="className" value="padtext color3"></c:set>
															  </c:when>
															  <c:when test="${item.pctPriceChange < 0}">
															    <c:set var="className" value="padtext color1"></c:set>
															  </c:when>
															  <c:otherwise>
															    <c:set var="className" value="padtext color2"></c:set>
															  </c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right" style="width: 40px"><c:out value="${item.closePrice}"/></div> </span>
														</td>
														<td ><span class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></span></td>
														<td  >
															<c:choose>
															  <c:when test="${item.closePrice == null || item.closePriceB4days == null}">
															    <c:set var="className" value="padtext"></c:set>
															  </c:when>
															  <c:when test="${item.closePrice == item.closePriceB4days}">
															    <c:set var="className" value="padtext color3"></c:set>
															  </c:when>
															  <c:when test="${item.closePrice < item.closePriceB4days}">
															    <c:set var="className" value="padtext color1"></c:set>
															  </c:when>
															  <c:otherwise>
															    <c:set var="className" value="padtext color2"></c:set>
															  </c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right" style="width: 30px"><c:out value="${item.priceChangPct}"/></div></span>
														</td>
														<td ><img src="<web:url value='/images/front/icon_link_chart.png'/>" /></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
													<tr>
														<td  colspan="6"   >
															<div style="font-weight:bold"><s:text name="web.label.messages.norecord"/></div>
														</td>
													</tr>
											</c:otherwise>
										</c:choose>
										<tr>
											<td colspan="5"  align="left" >
												<div align="left">&nbsp;<s:text name="web.table.header.NumberOfRatedCode" /></div>
											</td>
											<td >
												
													<div align="right" style="padding-right: 15px;"><b><c:out value="${model.todayRatedCodeNumber}"/></b></div>
												
											</td>
										</tr>
										<tr>
											<td  colspan="5"  align="left" >
												<div align="left">&nbsp;<s:text name="web.table.header.AverageRatingPoint" /></div>
											</td>
											<td   >
												
													<div align="right" style="padding-right: 15px;"><b><c:out value="${model.strAvgGeneralMarketPr}"/></b></div>
													
											</td>
										</tr>
									</tbody>
								</table>
								
						</td>
						<td style="width: 25%">
							<div id="PowerRatingChart"></div>
						</td>
						<td style="width: 15%">
							  <div class="mark">
							      <span class="fl changecolor1">&nbsp;</span>
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(0)" ><s:text name="web.graft.list.FirstLevel"></s:text></a></span>
							
							      <div class="ClearBoth"></div>
							  </div>
							  <div class="mark">
							      <span class="fl changecolor2">&nbsp;</span>
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(1)"><s:text name="web.graft.list.SecondLevel"></s:text></a></span>
							      <div class="ClearBoth"></div>
							  </div>
							  <div class="mark">
							
							      <span class="fl changecolor3">&nbsp;</span>
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(2)"><s:text name="web.graft.list.ThirdLevel"></s:text></a></span>
							      <div class="ClearBoth"></div>
							  </div>
							  <div class="mark">
							      <span class="fl changecolor4">&nbsp;</span>
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(3)" ><s:text name="web.graft.list.FourthLevel"></s:text></a></span>
							      <div class="ClearBoth"></div>
							
							  </div>
							  <div class="mark">
							      <span class="fl changecolor5">&nbsp;</span>
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(4)" ><s:text name="web.graft.list.FifthLevel"></s:text></a></span>
							      <div class="ClearBoth"></div>
							  </div>
							  <div class="mark">
							      <span class="fl changecolor6">&nbsp;</span>
							
							      <span class="fl changetxt"><a href="javascript:showLevelPopup(5)"><s:text name="web.graft.list.SixthLevel"></s:text></a></span>
							      <div class="ClearBoth"></div>
							 </div>
							</td>
					</tr>
					<tr>
						<td>
						</td>
						<td >
							<div align="center"><b><s:text name="web.graft.label.Description"></s:text></b></div>
						</td>
						<td>
						</td>
					</tr>
				</table>
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
	</div>
			
					<div class="tabs_hotnews" id="container-3">
						<div style="clear: both"></div>
						
						<s:hidden id="fListedMarket_mostActiveFloorCode" value="0"></s:hidden>
						
						<div  id="accordion" >
							<div id="topTenPrTitle">
								<ul class="hd">
									<li class="hd-left"></li>
									<li class="hd-right"></li>
									<li class="hd-center">
										<div class="heading_pr">
										
											<span class="fl heading_pr_tt">
												<a onclick="return false;">
												<s:text name="web.label.PowerRating.DetailTopTen" />
												</a>
											</span>
										</div>
									</li>
								</ul>
							</div>
								
								<div class="ds_active">
									<div id="topTenPrContent">
											<div class="padding0px">
												<div class="clearfix">
													<table border="0" id="topTenPowerRatingTable"
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs">
														<colgroup>
															<col width="5%">
															<col width="5%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
														</colgroup>
														<tbody>
															<tr >
																<th align="center">
																	<b><s:text name="web.table.header.Index" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.SecurityCode" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPowerRating" />
																	</b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPrice" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.FourDayAgoSessionPR" />
																	</b>
																</th>
																
																<th  align="center">
																	<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.ViewGrap" />
																	</b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.ViewRelatedNews" /> </b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.AddToWatchList" />
																	</b>
																	
																</th>
															</tr>
														</tbody>
														</table>
														<table border="0" 
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs_active">
														<colgroup>
															<col width="5%">
															<col width="5%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
														</colgroup>
														<tbody>
															<c:choose>
																<c:when test="${not empty model.topTenPrs}">
																	
																	<c:forEach var="item" items="${model.topTenPrs}" varStatus="status" >
																		<c:set var="rowClass" value="thirstrow trBackground"></c:set>
																		<c:if test="${status.index%2==0}">																			
																			<c:set var="rowClass" value="thirstrow "></c:set>
																		</c:if>
																		<tr >
																			<td align="center">&nbsp;<c:out value="${status.index + 1}"/></td>
																			<td  nowrap="nowrap" align="center">
																			<a href="javascript:doQuickSearchSymbol('<c:out value="${item.symbol}"/>');">
																				<span class="bold2"><c:out value="${item.symbol}"/></span>
																				</a>
																			</td>
																			<td align="center">
																			
																			  <c:choose>
																				  <c:when test="${item.pctMarkChange == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange == 0}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange < 0}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 20px"><c:out value="${item.mark}"/></div> </span>
																				
																			</td>
																			<td align="center">
																				<c:choose>
																					<c:when test="${item.pctPriceChange == null}">
																					<c:set var="className" value="padtext"></c:set>
																					</c:when>
																					<c:when test="${item.pctPriceChange == 0}">
																					<c:set var="className" value="padtext color3"></c:set>
																					</c:when>
																					<c:when test="${item.pctPriceChange < 0}">
																					<c:set var="className" value="padtext color1"></c:set>
																					</c:when>
																					<c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																					</c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 35px"><c:out value="${item.closePrice}"/></div> </span>
																			</td>
																			<td align="center"><span class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></span></td>
																			<td align="center">
																				<c:choose>
																				  <c:when test="${item.closePrice == null || item.closePriceB4days == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice == item.closePriceB4days}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice < item.closePriceB4days}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 50px"><c:out value="${item.priceChangPct}"/></div></span>
																			</td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_link_chart.png'/>" /></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_news_cp.gif'/>" onclick='navigateToCompanyNews("<c:out value="${item.symbol}"/>")'/></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_add_watchlist.gif'/>" onclick='addToWatchList("<c:out value="${item.symbol}"/>")' style=""/></a></td>
																		</tr>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																		<tr>
																			<td class="al" colspan="4">
																				<div style="font-weight:bold"><s:text name="web.label.messages.norecord"/></div>
																			</td>
																		</tr>
																</c:otherwise>
															</c:choose>
														</tbody>
													</table>
												</div>
												<div class="bottom_inner clearfix">
													<div class="left fl"></div>
													<div class="right fr"></div>
												</div>
											</div>
									
									</div>
								</div>
							
							
							<div id="upgradedTopTenTitle">
							<ul class="hd">
									<li class="hd-left"></li>
									<li class="hd-right"></li>
									<li class="hd-center">
										<div class="heading_pr">
										
											<span class="fl heading_pr_tt">
												<a onclick="return false;">
												<s:text name="web.label.PowerRating.DetailUpgradedTopTen" />
												</a>
											</span>
										</div>
									</li>
								</ul>
								</div>
							<div class="ds_active">
									<div id="upgradedTenPrContent">
											<div class="padding0px">
												<div class="clearfix">
													<table border="0" id="topTenPowerRatingTable"
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs">
															<colgroup>
																<col width="5%">
																<col width="5%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
															</colgroup>
															<tbody>
															<tr >
																<th  align="center">
																	<b><s:text name="web.table.header.Index" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.SecurityCode" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPowerRating" />
																	</b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPrice" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.FourDayAgoSessionPR" />
																	</b>
																</th>
																
																<th  align="center">
																	<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.ViewGrap" />
																	</b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.ViewRelatedNews" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.AddToWatchList" />
																	</b>
																</th>
															</tr>
														</tbody>
														</table>
														<table border="0" id="topTenPowerRatingTable"
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs_active">
														<colgroup>
															<col width="5%">
															<col width="5%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
														</colgroup>
														<tbody>
															<c:choose>
																<c:when test="${not empty model.upgradedTopTenPrs}">
																	
																	<c:forEach var="item" items="${model.upgradedTopTenPrs}" varStatus="status" >
																		<c:set var="rowClass" value="thirstrow trBackground"></c:set>
																		<c:if test="${status.index%2==0}">																			
																			<c:set var="rowClass" value="thirstrow "></c:set>
																		</c:if>
																		<tr>
																			<td align="center">&nbsp;<c:out value="${status.index + 1}"/></td>
																			<td  nowrap="nowrap" align="center">
																			<a href="javascript:doQuickSearchSymbol('<c:out value="${item.symbol}"/>');">
																				<span class="bold2"><c:out value="${item.symbol}"/></span>
																				</a>
																			</td>
																			<td align="center">
																			<c:choose>
																			  <c:when test="${item.pctMarkChange == null}">
																				<c:set var="className" value="padtext"></c:set>
																			  </c:when>
																			  <c:when test="${item.pctMarkChange == 0}">
																				<c:set var="className" value="padtext color3"></c:set>
																			  </c:when>
																			  <c:when test="${item.pctMarkChange < 0}">
																				<c:set var="className" value="padtext color1"></c:set>
																			  </c:when>
																			  <c:otherwise>
																				<c:set var="className" value="padtext color2"></c:set>
																			  </c:otherwise>
																			</c:choose>
																				<span class="${className}"><div align="right" style="width: 20px"><c:out value="${item.mark}"/></div> </span>
																				
																			</td>
																			<td align="center">
																				<c:choose>
																				<c:when test="${item.pctPriceChange == null}">
																				<c:set var="className" value="padtext"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange == 0}">
																				<c:set var="className" value="padtext color3"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange < 0}">
																				<c:set var="className" value="padtext color1"></c:set>
																				</c:when>
																				<c:otherwise>
																				<c:set var="className" value="padtext color2"></c:set>
																				</c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 40px"><c:out value="${item.closePrice}"/></div> </span>
																			</td>
																			<td align="center"><span class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></span></td>
																			<td align="center">
																				<c:choose>
																				  <c:when test="${item.closePrice == null || item.closePriceB4days == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice == item.closePriceB4days}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice < item.closePriceB4days}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 50px"><c:out value="${item.priceChangPct}"/></div></span>
																			</td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_link_chart.png'/>" /></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_news_cp.gif'/>" onclick='navigateToCompanyNews("<c:out value="${item.symbol}"/>")'/></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_add_watchlist.gif'/>" onclick='addToWatchList("<c:out value="${item.symbol}"/>")' style=""/></a></td>
																		</tr>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																		<tr>
																			<td colspan="4">
																				<div style="font-weight:bold"><s:text name="web.label.messages.norecord"/></div>
																			</td>
																		</tr>
																</c:otherwise>
															</c:choose>
														</tbody>
													</table>
												</div>
												<div class="bottom_inner clearfix">
													<div class="left fl"></div>
													<div class="right fr"></div>
												</div>
											</div>
									
									</div>
								</div>
							
							<div id="bottomTenTitle">
							<ul class="hd">
									<li class="hd-left"></li>
									<li class="hd-right"></li>
									<li class="hd-center">
										<div class="heading_pr">
										
											<span class="fl heading_pr_tt">
												<a onclick="return false;">
												<s:text name="web.label.PowerRating.DetailBottomTen" />
												</a>
											</span>
										</div>
									</li>
								</ul>
								</div>
							<div class="ds_active">
									<div id="bottomTenPrContent">
											<div class="padding0px">
												<div class="clearfix">
													<table border="0" id="topTenPowerRatingTable" class="table1_PRs"
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0">
															<colgroup>
																<col width="5%">
																<col width="5%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
															</colgroup>
															<tbody>
															<tr >
																<th  align="center">
																	<b><s:text name="web.table.header.Index" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.SecurityCode" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPowerRating" />
																	</b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.TodayPrice" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.FourDayAgoSessionPR" />
																	</b>
																</th>
																
																<th align="center">
																	<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.ViewGrap" />
																	</b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.ViewRelatedNews" /> </b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.AddToWatchList" />
																	</b>
																</th>
															</tr>
														</tbody>
														</table>
														<table border="0" 
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs_active">
														<colgroup>
															<col width="5%">
															<col width="5%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
														</colgroup>
														<tbody>
															<c:choose>
																<c:when test="${not empty model.bottomTenPrs}">
																	
																	<c:forEach var="item" items="${model.bottomTenPrs}" varStatus="status" >
																		<c:set var="rowClass" value="thirstrow trBackground"></c:set>
																		<c:if test="${status.index%2==0}">																			
																			<c:set var="rowClass" value="thirstrow "></c:set>
																		</c:if>
																		<tr >
																			<td align="center">&nbsp;<c:out value="${status.index + 1}"/></td>
																			<td  nowrap="nowrap" align="center">
																			<a href="javascript:doQuickSearchSymbol('<c:out value="${item.symbol}"/>');">
																				<span class="bold2"><c:out value="${item.symbol}"/></span>
																				</a>
																			</td>
																			<td align="center">
																			<c:choose>
																				  <c:when test="${item.pctMarkChange == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange == 0}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange < 0}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 20px"> <c:out value="${item.mark}"/></div> </span>
																				
																			</td>
																			<td align="center">
																				<c:choose>
																				<c:when test="${item.pctPriceChange == null}">
																				<c:set var="className" value="padtext"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange == 0}">
																				<c:set var="className" value="padtext color3"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange < 0}">
																				<c:set var="className" value="padtext color1"></c:set>
																				</c:when>
																				<c:otherwise>
																				<c:set var="className" value="padtext color2"></c:set>
																				</c:otherwise>
																			</c:choose>
																				<span class="${className}"><div align="right" style="width: 40px"> <c:out value="${item.closePrice}"/> </div></span>
																			</td>
																			<td align="center"><span class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></span></td>
																			<td align="center">
																				<c:choose>
																				  <c:when test="${item.closePrice == null || item.closePriceB4days == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice == item.closePriceB4days}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice < item.closePriceB4days}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>	
																				<span class="${className}"><div align="right" style="width: 50px"><c:out value="${item.priceChangPct}"/></div></span>
																			</td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_link_chart.png'/>" /></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_news_cp.gif'/>" onclick='navigateToCompanyNews("<c:out value="${item.symbol}"/>")'/></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_add_watchlist.gif'/>" onclick='addToWatchList("<c:out value="${item.symbol}"/>")' style=""/></a></td>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																		<tr>
																			<td colspan="4">
																				<div style="font-weight:bold"><s:text name="web.label.messages.norecord"/></div>
																			</td>
																		</tr>
																</c:otherwise>
															</c:choose>
														</tbody>
													</table>
												</div>
												<div class="bottom_inner clearfix">
													<div class="left fl"></div>
													<div class="right fr"></div>
												</div>
											</div>
									
									</div>
								</div>
							
							<div id="downgradedBottomTenTitle">
							
							<ul class="hd">
									<li class="hd-left"></li>
									<li class="hd-right"></li>
									<li class="hd-center">
										<div class="heading_pr">
										
											<span class="fl heading_pr_tt">
												<a onclick="return false;">
												<s:text name="web.label.PowerRating.DetailDowngradedBottomTen" />
												</a>
											</span>
										</div>
									</li>
								</ul>
							</div>
							<div class="ds_active">
									<div id="dowgradedTenPrContent">
											<div class="padding0px">
												<div class="clearfix">
													<table border="0" id="topTenPowerRatingTable" class="table1_PRs"
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0">
															<colgroup>
																<col width="5%">
																<col width="5%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
																<col width="8%">
															</colgroup>
															<tbody>
															<tr >
																<th  align="center">
																	<b><s:text name="web.table.header.Index" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.SecurityCode" /> </b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.TodayPowerRating" />
																	</b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.TodayPrice" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.FourDayAgoSessionPR" />
																	</b>
																</th>
																
																<th align="center">
																	<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
																</th>
																<th  align="center">
																	<b><s:text name="web.table.header.ViewGrap" />
																	</b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.ViewRelatedNews" /> </b>
																</th>
																<th align="center">
																	<b><s:text name="web.table.header.AddToWatchList" />
																	</b>
																</th>
															</tr>
														</tbody>
														</table>
														<table border="0" 
														bordercolor="#ffffff" 
														width="100%" cellspacing="0" cellpadding="0" class="table1_PRs_active">
														<colgroup>
															<col width="5%">
															<col width="5%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
															<col width="8%">
														</colgroup>
														<tbody>
															<c:choose>
																<c:when test="${not empty model.downgradedBottomTenPrs}">
																	
																	<c:forEach var="item" items="${model.downgradedBottomTenPrs}" varStatus="status" >
																		<c:set var="rowClass" value="thirstrow trBackground"></c:set>
																		<c:if test="${status.index%2==0}">																			
																			<c:set var="rowClass" value="thirstrow "></c:set>
																		</c:if>
																		<tr >
																			<td align="center">&nbsp;<c:out value="${status.index + 1}"/></td>
																			<td  nowrap="nowrap" align="center">
																			<a href="javascript:doQuickSearchSymbol('<c:out value="${item.symbol}"/>');">
																				<span class="bold2"><c:out value="${item.symbol}"/></span>
																				</a>
																			</td>
																			<td align="center">
																				<c:choose>
																				  <c:when test="${item.pctMarkChange == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange == 0}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.pctMarkChange < 0}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 20px"><c:out value="${item.mark}"/></div> </span>
																				
																			</td>
																			<td align="center">
																				<c:choose>
																				<c:when test="${item.pctPriceChange == null}">
																				<c:set var="className" value="padtext"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange == 0}">
																				<c:set var="className" value="padtext color3"></c:set>
																				</c:when>
																				<c:when test="${item.pctPriceChange < 0}">
																				<c:set var="className" value="padtext color1"></c:set>
																				</c:when>
																				<c:otherwise>
																				<c:set var="className" value="padtext color2"></c:set>
																				</c:otherwise>
																			</c:choose>
																				<span class="${className}"><div align="right" style="width: 40px"><c:out value="${item.closePrice}"/></div> </span>
																			</td>
																			<td align="center"><span class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></span></td>
																			<td align="center">
																				<c:choose>
																				  <c:when test="${item.closePrice == null || item.closePriceB4days == null}">
																					<c:set var="className" value="padtext"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice == item.closePriceB4days}">
																					<c:set var="className" value="padtext color3"></c:set>
																				  </c:when>
																				  <c:when test="${item.closePrice < item.closePriceB4days}">
																					<c:set var="className" value="padtext color1"></c:set>
																				  </c:when>
																				  <c:otherwise>
																					<c:set var="className" value="padtext color2"></c:set>
																				  </c:otherwise>
																				</c:choose>
																				<span class="${className}"><div align="right" style="width: 50px"><c:out value="${item.priceChangPct}"/></div></span>
																			</td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_link_chart.png'/>" /></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_news_cp.gif'/>" onclick='navigateToCompanyNews("<c:out value="${item.symbol}"/>")'/></a></td>
																			<td align="center"><a ><img src="<web:url value='/images/front/icon_add_watchlist.gif'/>" onclick='addToWatchList("<c:out value="${item.symbol}"/>")' style=""/></a></td>
																		</tr>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																		<tr>
																			<td class="al" colspan="4">
																				<div style="font-weight:bold"><s:text name="web.label.messages.norecord"/></div>
																			</td>
																		</tr>
																</c:otherwise>
															</c:choose>
														</tbody>
													</table>
												</div>
												<div class="bottom_inner clearfix">
													<div class="left fl"></div>
													<div class="right fr"></div>
												</div>
											</div>
									
									</div>
								</div>
							
							
							<div id="myWatchListTitle">
							<ul class="hd">
									<li class="hd-left"></li>
									<li class="hd-right"></li>
									<li class="hd-center">
										<div class="heading_pr">
										
											<span class="fl heading_pr_tt">
												<a onclick="loadMyWatchList();"><s:text name="web.label.PowerRating.DetailMyWatchList" /></a>
											</span>
										</div>
									</li>
								</ul>
								</div>
							
								<div class="ds_active">
									<div id="myWatchListContent"></div>
								</div>
							
						<!--end tabs-->
					</div>
					
				</div>
			</div>
	
<div id="powerLevelDialog" title='Power Ratings' style="display: none;">
	<div align="left" id="divSumarizeInfo"></div>
	<br/> 
	<div style="width:auto" align="center">		
		<div align="center" id = "loadWaitImg"></div>
		<div id="content" align="center" style="overflow: auto; max-height:300px"></div>
	</div>
</div>
<div id="secCodeHeaderTitle" style="display: none;">
	<s:text name="web.table.header.SecurityCode" />
</div>
<div id="todayPrHeaderTitle" style="display: none;">
	<s:text name="web.table.header.TodayPowerRating" />
</div>
<div id="priceHeaderTitle" style="display: none;">
	<s:text name="web.table.header.TodayPrice" />
</div>
<div id="fourDayHeaderTitle" style="display: none;">
	<s:text name="web.table.header.FourDayAgoSessionPR" />
</div>

<div id="indexHeaderTitle" style="display: none;">
	<s:text name="web.table.header.Index" />
</div>
<div id="prVariantPercentageHeaderTitle" style="display: none;">
	<s:text name="web.table.header.PriceVariantPercent" />
</div>
<div id="viewGraphHeaderTitle" style="display: none;">
	<s:text name="web.table.header.ViewGrap" />
</div>
<div id="viewCompanyNewsHeaderTitle" style="display: none;">
	<s:text name="web.table.header.ViewRelatedNews" />
</div>

<div id="firstSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.first" />
</div>

<div id="secondSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.second" />
</div>

<div id="thirdSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.third" />
</div>

<div id="watchListDialog" title='Power Ratings Watch List' style="display: none;">
	<div style="width:auto" align="center">		
		<div class="Addwatchlist_hd" align="left">
		        	<span class="fl stocks_title"><b><s:text name="web.popup.watchlist.MessagePrefix" />  <span id="currentSecCode" ></span><s:text name="web.popup.watchlist.MessagePostfix" /></b></span>
		            <div class="ClearBoth"></div>
		        </div>
		        <div class="Addwatchlist_bd" align="left">
		        	<p><s:text name="web.popup.watchlist.GuideInfo" /></p>
		            <div><table cellpadding="0" cellspacing="0" border="0">
		            	<tr><td><input size="23" class="itext" name="symbolSuggestion" id="symbolWatchListSuggestionId" onfocus="if (this.value=='<s:text name="commons.symbolsearch.textbox"></s:text>') this.value='';" onblur="if (this.value=='') this.value='<s:text name="commons.symbolsearch.textbox"></s:text>';" value="<s:text name='commons.symbolsearch.textbox'></s:text>"/></td>
		                	<td><table cellspacing="0" cellpadding="0" border="0">
			                    	<tr><td><img width="6" height="26" src="<web:url value='/images/button/bt_left.gif'/>"></td>
			                        	<td><input type="button" value="Add watchlist" class="bt_button" id="btnAddWatchList" /></td>
			                            <td><img width="6" height="26" src="<web:url value='/images/button/bt_right.gif'/>"></td>
			                        </tr>
			                    </table>
		                    </td>
		            	</tr>
		            </table></div>
		    	
		
		
		<div id="popupContent" align="center" style="max-height:300px; overflow: auto; width:auto; ">
			
		</div>
		
		</div>
		 </div>
		 	<div align="center" id = "loadWatchListWaitImg" ></div>
		    <div class="ClearBoth"></div>
		    
		</div>


<div id="messagePrefix" style="display: none;">
	<s:text name="web.popup.watchlist.MessagePrefix" />
</div>
<div id="messagePostfix" style="display: none;">
	<s:text name="web.popup.watchlist.MessagePostfix" />
</div>
<div id="guideInfo" style="display: none;">
	<s:text name="web.popup.watchlist.GuideInfo" />
</div>
<div id="yourList" style="display: none;">
	<s:text name="web.popup.watchlist.YourWatchList" />
</div>
<div id="popupSecCode" style="display: none;">
	<s:text name="web.popup.watchlist.SecCode" />
</div>
<div id="popupDate" style="display: none;">
	<s:text name="web.popup.watchlist.Date" />
</div>
<div id="popupPr" style="display: none;">
	<s:text name="web.popup.watchlist.Pr" />
</div>
<div id="popupDeleteImg" style="display: none;">
	<s:text name="web.popup.watchlist.Delete" />
</div>
<div id="popupDeleteAllImg" style="display: none;">
	<s:text name="web.popup.watchlist.DeleteAll" />
</div>










