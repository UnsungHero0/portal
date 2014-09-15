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


<div style="height: 10px;"></div>
<div class ="general pn_main" id="ctn-general"  style="padding:5px 7px">
	<div class="clearfix" >
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
						<td style="width: 60%">
							
								
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
	</div>
	<div style="height: 10px;"></div>
	
	
	<div class="bd">

		<div class="clearfix" >
			<table cellpadding="" cellspacing="0" border="0" width="100%">
				<tr>
					<td style="width: 48%">
						<ul class="hd">
						<li class="hd-left"></li>
						<li class="hd-right"></li>
						<li class="hd-center">
							<div  class="heading_pr">
								<span class="fl heading_pr_tt">
									<a href='<web:url value="/y-tuong-dau-tu/power-ratings-detail.shtml?activeAccordIndex=0"/>'><s:text name="web.label.PowerRating.TopTen" /></a>
								</span>
								<span class="fr heading_pr_date">${model.mostRecentDate}</span>
							</div>
						</li>
						</ul>	

						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="topTenPowerRatingTable" class="table1_PRs_active"
									bordercolor="#ffffff" 
									width="100%" cellspacing="0" cellpadding="0px">
									<thead>
										<tr bgcolor="#efefef">
											<th width="5%" align="center">
												<b><s:text name="web.table.header.SecurityCode" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPowerRating" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPrice" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.FourDayAgoSessionPR" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty model.topTenPrs}">
												<c:forEach var="item" items="${model.topTenPrs}" varStatus="status">
													<tr>
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
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
													<tr>
														<td  colspan="4">
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

					</td>
					<td style="width: 2%">
					</td>
					<td style="width: 48%">
						<div class="clearfix">
							<ul class="hd">
							<li class="hd-left"></li>
							<li class="hd-right"></li>
							<li class="hd-center">
								<div  class="heading_pr">
									<span class="fl heading_pr_tt">
										<a href='<web:url value="/y-tuong-dau-tu/power-ratings-detail.shtml?activeAccordIndex=1"/>'><s:text name="web.label.PowerRating.UpgradedTopTen" /></a>
									</span>
									<span class="fr heading_pr_date">${model.mostRecentDate}</span>
								</div>
							</li>
							</ul>
							

							<div class="padding0px">
								<div class="clearfix">
									<table border="0" id="upgradedTopTenPowerRatingTable"
									bordercolor="#fffff" class="table1_PRs_active"
									width="100%" cellspacing="0" cellpadding="0px">
									<thead>
										<tr bgcolor="#efefef">
											<th width="5%" align="center">
												<b><s:text name="web.table.header.SecurityCode" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPowerRating" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPrice" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.FourDayAgoSessionPR" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
											</th>
											
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty model.upgradedTopTenPrs}">
												<c:forEach var="item" items="${model.upgradedTopTenPrs}" varStatus="status">
													<tr>
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
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
													<tr>
														<td  colspan="4">
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
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div style="height: 10px;"></div>
	<div class="bd">
		<div class="clearfix">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td style="width: 48%">
						
						<ul class="hd">
							<li class="hd-left"></li>
							<li class="hd-right"></li>
							<li class="hd-center">
								<div  class="heading_pr">
									<span class="fl heading_pr_tt">
										<a href='<web:url value="/y-tuong-dau-tu/power-ratings-detail.shtml?activeAccordIndex=2"/>'><s:text name="web.label.PowerRating.BottomTen" /></a>
									</span>
									<span class="fr heading_pr_date">${model.mostRecentDate}</span>
								</div>
							</li>
							</ul>
						

						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="bottomTenPowerRatingTable" class="table1_PRs_active"
									bordercolor="#ffffff" 
									width="100%" cellspacing="0" cellpadding="0">
									<thead>
										<tr bgcolor="#efefef">
											<th width="5%" align="center">
												<b><s:text name="web.table.header.SecurityCode" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPowerRating" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPrice" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.FourDayAgoSessionPR" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty model.bottomTenPrs}">
												<c:forEach var="item" items="${model.bottomTenPrs}" varStatus="status">
													<tr>
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
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
													<tr>
														<td  colspan="4">
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

					</td>
					<td style="width: 2%">
					</td>
					<td style="width: 48%">
						<div class="clearfix">
							
							<ul class="hd">
							<li class="hd-left"></li>
							<li class="hd-right"></li>
							<li class="hd-center">
								<div  class="heading_pr">
									<span class="fl heading_pr_tt">
										
										<a href='<web:url value="/y-tuong-dau-tu/power-ratings-detail.shtml?activeAccordIndex=3"/>'><s:text name="web.label.PowerRating.DowngradedBottomTen" /></a>
									</span>
									<span class="fr heading_pr_date">${model.mostRecentDate}</span>
								</div>
							</li>
							</ul>

							<div class="padding0px">
								<div class="clearfix">
									<table border="0" id="downgradedTenPowerRatingTable"
									bordercolor="#ffffff" class="table1_PRs_active"
									width="100%" cellspacing="0" cellpadding="0">
									<thead>
										<tr bgcolor="#efefef">
											<th width="5%" align="center">
												<b><s:text name="web.table.header.SecurityCode" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPowerRating" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.TodayPrice" /> </b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.FourDayAgoSessionPR" />
												</b>
											</th>
											<th width="8%" align="center">
												<b><s:text name="web.table.header.PriceVariantPercent" /> </b>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty model.downgradedBottomTenPrs}">
												<c:forEach var="item" items="${model.downgradedBottomTenPrs}" varStatus="status">
													<tr>
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
														<td align="center"><SPAN class="padtext"><div align="right" style="width: 20px"><c:out value="${item.markB4days}"/></div></SPAN></td>
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
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
													<tr>
														<td  colspan="4">
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
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div style="height: 10px;"></div>
	<div class="bd">

		<div class="clearfix">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td style="width: 48%">
						<div class="clearfix">
							
							<ul class="hd">
							<li class="hd-left"></li>
							<li class="hd-right"></li>
							<li class="hd-center">
								<div  class="clearfix">
									<span class="fl heading_pr_tt">
										<a href='<web:url value="/y-tuong-dau-tu/power-ratings-detail.shtml?activeAccordIndex=4"/>'><s:text name="web.label.PowerRating.MyWatchList" /></a>
									</span>
									
								</div>
							</li>
							</ul>	
						</div>
					
					
					</td>
					<td style="width: 2%">
					</td>
					<td style="width: 48%">
						
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<div id="powerLevelDialog" title='Power Ratings' style="display: none;">
	<div align="left" id="divSumarizeInfo"></div>
	<br/>
	<div style="width:auto" align="center">		
		<div align="center" id = "loadWaitImg"></div>
		<div id="content" align="center" style="max-height:300px; overflow: auto"></div>
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


<div id="firstSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.first" />
</div>

<div id="secondSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.second" />
</div>

<div id="thirdSumarizeInfoPart" style="display: none;">
	<s:text name="web.popup.sumarize.label.third" />
</div>


