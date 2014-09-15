<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="content_ttpt">
	<jsp:include page="/WEB-INF/jsps/consultingcenter/investment-idea/investment-idea-nav.jsp" />

	<div class="content_small">

		<div id="tab-1" class="content_tab ui-tabs-container ui-tabs-hide"
			style="display: block;">
			<div class="hd_power_ratings">
				<a
					href="<web:url value='/tro-giup-san-pham-dich-vu/huong-dan-su-dung-power-ratings.shtml'/>"><span
					class="icon_row1"></span><span class="text right"><s:text name="analysis.powerRating.guide">Hướng dẫn
						sử dụng Power Rating</s:text></span>
				</a>
			</div>

			<div class="clear"></div>

			<%--<div style="display: none;" id="activeAccordIndex">${model.activeAccordIndex}</div>--%>

			<div class="clearfix padding-left10" style="height: 306px;">
				<div class="hd">
					<div class="hd-center">
						<div class="heading_pr">
							<h5>
								<s:text name="web.label.PowerRating.GeneralMarketRating" />
							</h5>
							<span class="right heading_pr_date">${model.mostRecentDate}</span>
							<div style="width: 100%; float: left;" id="hidetexta1"></div>
						</div>
					</div>
					<div style="" class="sub-menu-sel bd" id="box_1">
						<div class="content_dv">
							<div class="clearfix">
								<div class="box_left">
									<table class="table1_PRs" id="generalMarketPowerRatingTable"
										bordercolor="#ffffff" width="100%" cellspacing="0"
										cellpadding="0" border="0">
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
												<th>
													<s:text name="web.table.header.Indices" />
												</th>
												<th>
													<s:text name="web.table.header.TodayPowerRating" />
												</th>
												<th>
													<s:text name="web.table.header.TodayPrice" />
												</th>
												<th>
													<s:text name="web.table.header.FourDayAgoSessionPR" />
												</th>
												<th>
													<s:text name="web.table.header.PriceVariantPercent" />
												</th>
												<th>
													<s:text name="web.table.header.ViewGrap" />
												</th>
											</tr>
										</tbody>
									</table>
									<table class="table1_PRs_active" bordercolor="#ffffff"
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
													<c:forEach var="item" items="${model.generalMarketPrs}"
														varStatus="status">
														<tr>
															<td nowrap="nowrap">
																<span class="padtext bold2"><c:out
																		value="${item.symbol}" />
																</span>
															</td>
															<td>
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
																<span class="${className}"><div align="right"
																		style="width: 20px">
																		<c:out value="${item.mark}" />
																	</div> </span>

															</td>
															<td>
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
																<span class="${className}"><div align="right"
																		style="width: 40px">
																		<c:out value="${item.closePrice}" />
																	</div> </span>
															</td>
															<td>
																<span class="padtext"><div align="right"
																		style="width: 20px">
																		<c:out value="${item.markB4days}" />
																	</div>
																</span>
															</td>
															<td>
																<c:choose>
																	<c:when
																		test="${item.closePrice == null || item.closePriceB4days == null}">
																		<c:set var="className" value="padtext"></c:set>
																	</c:when>
																	<c:when
																		test="${item.closePrice == item.closePriceB4days}">
																		<c:set var="className" value="padtext color3"></c:set>
																	</c:when>
																	<c:when
																		test="${item.closePrice < item.closePriceB4days}">
																		<c:set var="className" value="padtext color1"></c:set>
																	</c:when>
																	<c:otherwise>
																		<c:set var="className" value="padtext color2"></c:set>
																	</c:otherwise>
																</c:choose>
																<span class="${className}"><div align="right"
																		style="width: 30px">
																		<c:out value="${item.priceChangPct}" />
																	</div>
																</span>
															</td>
															<td>
																<a data_symbol="${item.symbol}" class="flash_chart_link"
																	href="<web:url value="/cong-cu-phan-tich-chung-khoan/bieu-do-ky-thuat.shtml"/>"><img
																		src="<web:url value='/images/front/icon_link_chart.png'/>" />
																</a>
															</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="6">
															<div style="font-weight: bold">
																<s:text name="web.label.messages.norecord" />
															</div>
														</td>
													</tr>
												</c:otherwise>
											</c:choose>
											<tr>
												<td colspan="5" align="left">
													<div align="left">
														&nbsp;
														<s:text name="web.table.header.NumberOfRatedCode" />
													</div>
												</td>
												<td>

													<div align="right" style="padding-right: 15px;">
														<c:out value="${model.todayRatedCodeNumber}" />
														
													</div>

												</td>
											</tr>
											<tr>
												<td colspan="5" align="left">
													<div align="left">
														&nbsp;
														<s:text name="web.table.header.AverageRatingPoint" />
													</div>
												</td>
												<td>

													<div align="right" style="padding-right: 15px;">
														<c:out value="${model.strAvgGeneralMarketPr}" />
														
													</div>

												</td>
											</tr>
										</tbody>
									</table>
								</div>

								<input type='hidden' id="pr-firstLevel-text"
									value='<s:text name="web.graft.tip.FirstLevel"></s:text>' />
								<input type='hidden' id="pr-secondLevel-text"
									value='<s:text name="web.graft.tip.SecondLevel"></s:text>' />
								<input type='hidden' id="pr-thirdLevel-text"
									value='<s:text name="web.graft.tip.ThirdLevel"></s:text>' />
								<input type='hidden' id="pr-fourthLevel-text"
									value='<s:text name="web.graft.tip.FourthLevel"></s:text>' />
								<input type='hidden' id="pr-fifthLevel-text"
									value='<s:text name="web.graft.tip.FifthLevel"></s:text>' />
								<input type='hidden' id="pr-sixthLevel-text"
									value='<s:text name="web.graft.tip.SixthLevel"></s:text>' />

								<div class="box_right" style="width: 430px; margin-right: 10px">
									<table style="width: 100%;">
										<tr>
											<td>
												<div id="PowerRatingChart"></div>
											</td>
											<td>
												<div class="mark">
													<span class="fl changecolor1">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(0)"><s:text
																name="web.graft.list.FirstLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
												<div class="mark">
													<span class="fl changecolor2">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(1)"><s:text
																name="web.graft.list.SecondLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
												<div class="mark">
													<span class="fl changecolor3">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(2)"><s:text
																name="web.graft.list.ThirdLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
												<div class="mark">
													<span class="fl changecolor4">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(3)"><s:text
																name="web.graft.list.FourthLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
												<div class="mark">
													<span class="fl changecolor5">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(4)"><s:text
																name="web.graft.list.FifthLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
												<div class="mark">
													<span class="fl changecolor6">&nbsp;</span>
													<span class="fl changetxt"><a
														href="javascript:showLevelPopup(5)"><s:text
																name="web.graft.list.SixthLevel"></s:text>
													</a>
													</span>
													<div class="ClearBoth"></div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div align="center">
													<s:text name="web.graft.label.Description"></s:text>
													
												</div>
											</td>
											<td></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>

			<div id="accordion" class="padding-bottom10">

				<div id="myWatchListTitle"
					class="clearfix padding-left10 padding-top10" style="display: none;">
					<ul class="hd">
						<li class="hd-center">
							<div class="heading_pr">
								<h5>
									<a onclick="loadMyWatchList();"><s:text
											name="web.label.PowerRating.DetailMyWatchList" />
									</a>
								</h5>
							</div>
						</li>
					</ul>
				</div>

				<div class="ds_active padding-left10">
					<div id="myWatchListContent" style="display: none;"></div>
				</div>

				<div id="topTenPrTitle"
					class="clearfix padding-left10 padding-top10">
					<ul class="hd">
						<li class="hd-center">
							<h5>
								<a onclick="return false;"> <s:text
										name="web.label.PowerRating.DetailTopTen" /> </a>
							</h5>
						</li>
					</ul>
				</div>

				<div class="ds_active padding-left10">
					<div id="topTenPrContent">
						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="topTenPowerRatingTable"
									bordercolor="#ffffff" width="100%" cellspacing="0"
									cellpadding="0" class="table1_PRs">
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
										<tr>
											<th align="center">
											    <s:text name="web.table.header.Index" />
											</th>
											<th align="center">
												<s:text name="web.table.header.SecurityCode" />
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPowerRating" />
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPrice" />
											</th>
											<th align="center">
												<s:text name="web.table.header.FourDayAgoSessionPR" />
											</th>

											<th align="center">
												<s:text name="web.table.header.PriceVariantPercent" />
											</th>
											<th align="center">
											    <s:text name="web.table.header.ViewGrap" />
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewRelatedNews" />
											</th>
											<th align="center">
												<s:text name="web.table.header.AddToWatchList" />
											</th>
										</tr>
									</tbody>
								</table>
								<table border="0" bordercolor="#ffffff" width="100%"
									cellspacing="0" cellpadding="0" class="table1_PRs_active">
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

												<c:forEach var="item" items="${model.topTenPrs}"
													varStatus="status">
													<c:set var="rowClass" value="thirstrow trBackground"></c:set>
													<c:set var="symbolLowerCase"
														value="${fn:toLowerCase(item.symbol)}" />
													<c:if test="${status.index%2==0}">
														<c:set var="rowClass" value="thirstrow "></c:set>
													</c:if>
													<tr>
														<td align="center">
															&nbsp;
															<c:out value="${status.index + 1}" />
														</td>
														<td nowrap="nowrap" align="center">
															<a
																href="<web:url value='/tong-quan/${symbolLowerCase}.shtml'/>">
																<span class="bold2"><c:out value="${item.symbol}" />
															</span> </a>
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
															<span class="${className}"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.mark}" />
																</div> </span>

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
															<span class="${className}"><div align="right"
																	style="width: 35px">
																	<c:out value="${item.closePrice}" />
																</div> </span>
														</td>
														<td align="center">
															<span class="padtext"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.markB4days}" />
																</div>
															</span>
														</td>
														<td align="center">
															<c:choose>
																<c:when
																	test="${item.closePrice == null || item.closePriceB4days == null}">
																	<c:set var="className" value="padtext"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice == item.closePriceB4days}">
																	<c:set var="className" value="padtext color3"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice < item.closePriceB4days}">
																	<c:set var="className" value="padtext color1"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="className" value="padtext color2"></c:set>
																</c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right"
																	style="width: 50px">
																	<c:out value="${item.priceChangPct}" />
																</div>
															</span>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/bieu-do-ky-thuat.shtml'/>"><img
																	src="<web:url value='/images/front/icon_link_chart.png'/>" />
															</a>
														</td>

														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/tin-doanh-nghiep/${symbolLowerCase}.shtml'/>"><img
																	src="<web:url value='/images/front/icon_news_cp.gif'/>" />
															</a>
														</td>
														<td align="center">
															<a><img
																	src="<web:url value='/images/front/icon_add_watchlist.gif'/>"
																	onclick='addToWatchList("<c:out value="${item.symbol}"/>")'
																	style="" />
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td class="al" colspan="4">
														<div style="font-weight: bold">
															<s:text name="web.label.messages.norecord" />
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>

				<div id="upgradedTopTenTitle"
					class="clearfix padding-left10 padding-top10">
					<ul class="hd">
						<li class="hd-center">
							<div class="heading_pr">
								<h5>
									<a onclick="return false;"> <s:text
											name="web.label.PowerRating.DetailUpgradedTopTen" /> </a>
								</h5>
							</div>
						</li>
					</ul>
				</div>

				<div class="ds_active padding-left10">
					<div id="upgradedTenPrContent">
						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="topTenPowerRatingTable"
									bordercolor="#ffffff" width="100%" cellspacing="0"
									cellpadding="0" class="table1_PRs">
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
										<tr>
											<th align="center">
												<s:text name="web.table.header.Index" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.SecurityCode" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPowerRating" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPrice" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.FourDayAgoSessionPR" />
												
											</th>

											<th align="center">
												<s:text name="web.table.header.PriceVariantPercent" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewGrap" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewRelatedNews" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.AddToWatchList" /> 
											</th>
										</tr>
									</tbody>
								</table>
								<table border="0" id="topTenPowerRatingTable"
									bordercolor="#ffffff" width="100%" cellspacing="0"
									cellpadding="0" class="table1_PRs_active">
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

												<c:forEach var="item" items="${model.upgradedTopTenPrs}"
													varStatus="status">
													<c:set var="rowClass" value="thirstrow trBackground"></c:set>
													<c:set var="symbolLowerCase"
														value="${fn:toLowerCase(item.symbol)}" />
													<c:if test="${status.index%2==0}">
														<c:set var="rowClass" value="thirstrow "></c:set>
													</c:if>
													<tr>
														<td align="center">
															&nbsp;
															<c:out value="${status.index + 1}" />
														</td>
														<td nowrap="nowrap" align="center">
															<a
																href="<web:url value='/tong-quan/${symbolLowerCase}.shtml'/>">
																<span class="bold2"><c:out value="${item.symbol}" />
															</span> </a>
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
															<span class="${className}"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.mark}" />
																</div> </span>

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
															<span class="${className}"><div align="right"
																	style="width: 40px">
																	<c:out value="${item.closePrice}" />
																</div> </span>
														</td>
														<td align="center">
															<span class="padtext"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.markB4days}" />
																</div>
															</span>
														</td>
														<td align="center">
															<c:choose>
																<c:when
																	test="${item.closePrice == null || item.closePriceB4days == null}">
																	<c:set var="className" value="padtext"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice == item.closePriceB4days}">
																	<c:set var="className" value="padtext color3"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice < item.closePriceB4days}">
																	<c:set var="className" value="padtext color1"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="className" value="padtext color2"></c:set>
																</c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right"
																	style="width: 50px">
																	<c:out value="${item.priceChangPct}" />
																</div>
															</span>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/bieu-do-ky-thuat.shtml'/>"><img
																	src="<web:url value='/images/front/icon_link_chart.png'/>" />
															</a>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/tin-doanh-nghiep/${symbolLowerCase}.shtml'/>"><img
																	src="<web:url value='/images/front/icon_news_cp.gif'/>" />
															</a>
														</td>
														<td align="center">
															<a><img
																	src="<web:url value='/images/front/icon_add_watchlist.gif'/>"
																	onclick='addToWatchList("<c:out value="${item.symbol}"/>")'
																	style="" />
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="4">
														<div style="font-weight: bold">
															<s:text name="web.label.messages.norecord" />
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>

				<div id="bottomTenTitle"
					class="clearfix padding-left10 padding-top10">
					<ul class="hd">
						<li class="hd-center">
							<div class="heading_pr">
								<h5>
									<a onclick="return false;"> <s:text
											name="web.label.PowerRating.DetailBottomTen" /> </a>
								</h5>
							</div>
						</li>
					</ul>
				</div>

				<div class="ds_active padding-left10">
					<div id="bottomTenPrContent">
						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="topTenPowerRatingTable" class="table1_PRs"
									bordercolor="#ffffff" width="100%" cellspacing="0"
									cellpadding="0">
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
										<tr>
											<th align="center">
												<s:text name="web.table.header.Index" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.SecurityCode" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPowerRating" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPrice" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.FourDayAgoSessionPR" />
												
											</th>

											<th align="center">
												<s:text name="web.table.header.PriceVariantPercent" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewGrap" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewRelatedNews" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.AddToWatchList" /> 
											</th>
										</tr>
									</tbody>
								</table>
								<table border="0" bordercolor="#ffffff" width="100%"
									cellspacing="0" cellpadding="0" class="table1_PRs_active">
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

												<c:forEach var="item" items="${model.bottomTenPrs}"
													varStatus="status">
													<c:set var="rowClass" value="thirstrow trBackground"></c:set>
													<c:set var="symbolLowerCase"
														value="${fn:toLowerCase(item.symbol)}" />

													<c:if test="${status.index%2==0}">
														<c:set var="rowClass" value="thirstrow "></c:set>
													</c:if>
													<tr>
														<td align="center">
															&nbsp;
															<c:out value="${status.index + 1}" />
														</td>
														<td nowrap="nowrap" align="center">
															<a
																href="<web:url value='/tong-quan/${symbolLowerCase}.shtml'/>">
																<span class="bold2"><c:out value="${item.symbol}" />
															</span> </a>
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
															<span class="${className}"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.mark}" />
																</div> </span>

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
															<span class="${className}"><div align="right"
																	style="width: 40px">
																	<c:out value="${item.closePrice}" />
																</div>
															</span>
														</td>
														<td align="center">
															<span class="padtext"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.markB4days}" />
																</div>
															</span>
														</td>
														<td align="center">
															<c:choose>
																<c:when
																	test="${item.closePrice == null || item.closePriceB4days == null}">
																	<c:set var="className" value="padtext"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice == item.closePriceB4days}">
																	<c:set var="className" value="padtext color3"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice < item.closePriceB4days}">
																	<c:set var="className" value="padtext color1"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="className" value="padtext color2"></c:set>
																</c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right"
																	style="width: 50px">
																	<c:out value="${item.priceChangPct}" />
																</div>
															</span>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/bieu-do-ky-thuat.shtml'/>"><img
																	src="<web:url value='/images/front/icon_link_chart.png'/>" />
															</a>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/tin-doanh-nghiep/${symbolLowerCase}.shtml'/>"><img
																	src="<web:url value='/images/front/icon_news_cp.gif'/>" />
															</a>
														</td>
														<td align="center">
															<a><img
																	src="<web:url value='/images/front/icon_add_watchlist.gif'/>"
																	onclick='addToWatchList("<c:out value="${item.symbol}"/>")'
																	style="" />
															</a>
														</td>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="4">
														<div style="font-weight: bold">
															<s:text name="web.label.messages.norecord" />
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>

				<div id="downgradedBottomTenTitle"
					class="clearfix padding-left10 padding-top10">
					<ul class="hd">
						<li class="hd-center">
							<div class="heading_pr">
								<h5>
									<a onclick="return false;"> <s:text
											name="web.label.PowerRating.DetailDowngradedBottomTen" /> </a>
								</h5>
							</div>
						</li>
					</ul>
				</div>

				<div class="ds_active padding-left10">
					<div id="dowgradedTenPrContent">
						<div class="padding0px">
							<div class="clearfix">
								<table border="0" id="topTenPowerRatingTable" class="table1_PRs"
									bordercolor="#ffffff" width="100%" cellspacing="0"
									cellpadding="0">
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
										<tr>
											<th align="center">
												<s:text name="web.table.header.Index" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.SecurityCode" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPowerRating" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.TodayPrice" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.FourDayAgoSessionPR" />
												
											</th>

											<th align="center">
												<s:text name="web.table.header.PriceVariantPercent" />
												
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewGrap" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.ViewRelatedNews" /> 
											</th>
											<th align="center">
												<s:text name="web.table.header.AddToWatchList" /> 
											</th>
										</tr>
									</tbody>
								</table>
								<table border="0" bordercolor="#ffffff" width="100%"
									cellspacing="0" cellpadding="0" class="table1_PRs_active">
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

												<c:forEach var="item"
													items="${model.downgradedBottomTenPrs}" varStatus="status">
													<c:set var="rowClass" value="thirstrow trBackground"></c:set>
													<c:set var="symbolLowerCase"
														value="${fn:toLowerCase(item.symbol)}" />
													<c:if test="${status.index%2==0}">
														<c:set var="rowClass" value="thirstrow "></c:set>
													</c:if>
													<tr>
														<td align="center">
															&nbsp;
															<c:out value="${status.index + 1}" />
														</td>
														<td nowrap="nowrap" align="center">
															<a
																href="<web:url value='/tong-quan/${symbolLowerCase}.shtml'/>">
																<span class="bold2"><c:out value="${item.symbol}" />
															</span> </a>
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
															<span class="${className}"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.mark}" />
																</div> </span>

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
															<span class="${className}"><div align="right"
																	style="width: 40px">
																	<c:out value="${item.closePrice}" />
																</div> </span>
														</td>
														<td align="center">
															<span class="padtext"><div align="right"
																	style="width: 20px">
																	<c:out value="${item.markB4days}" />
																</div>
															</span>
														</td>
														<td align="center">
															<c:choose>
																<c:when
																	test="${item.closePrice == null || item.closePriceB4days == null}">
																	<c:set var="className" value="padtext"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice == item.closePriceB4days}">
																	<c:set var="className" value="padtext color3"></c:set>
																</c:when>
																<c:when
																	test="${item.closePrice < item.closePriceB4days}">
																	<c:set var="className" value="padtext color1"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="className" value="padtext color2"></c:set>
																</c:otherwise>
															</c:choose>
															<span class="${className}"><div align="right"
																	style="width: 50px">
																	<c:out value="${item.priceChangPct}" />
																</div>
															</span>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/bieu-do-ky-thuat.shtml'/>"><img
																	src="<web:url value='/images/front/icon_link_chart.png'/>" />
															</a>
														</td>
														<td align="center">
															<a data_symbol="${item.symbol}" class="flash_chart_link"
																href="<web:url value='/tin-doanh-nghiep/${symbolLowerCase}.shtml'/>"><img
																	src="<web:url value='/images/front/icon_news_cp.gif'/>" />
															</a>
														</td>
														<td align="center">
															<a><img
																	src="<web:url value='/images/front/icon_add_watchlist.gif'/>"
																	onclick='addToWatchList("<c:out value="${item.symbol}"/>")'
																	style="" />
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td class="al" colspan="4">
														<div style="font-weight: bold">
															<s:text name="web.label.messages.norecord" />
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!-- end -->
				<!--end tabs-->
			</div>




			<div id="powerLevelDialog" title='Power Ratings'
				style="display: none;">
				<div align="left" id="divSumarizeInfo"></div>
				<br />
				<div style="width: auto" align="center">
					<div align="center" id="loadWaitImg"></div>
					<div id="content" align="center"
						style="overflow: auto; max-height: 300px"></div>
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

			<div id="watchListDialog" title='Power Ratings Watch List'
				style="display: none;">
				<div style="width: auto" align="center">
					<div class="Addwatchlist_hd" align="left">
						<span class="fl stocks_title"><s:text
									name="web.popup.watchlist.MessagePrefix" /> <span
								id="currentSecCode"></span>
							<s:text name="web.popup.watchlist.MessagePostfix" />
						
						</span>
						<div class="ClearBoth"></div>
					</div>
					<div class="Addwatchlist_bd" align="left">
						<p>
							<s:text name="web.popup.watchlist.GuideInfo" />
						</p>
						<div>
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td>
										<input size="23" class="itext" name="symbolSuggestion"
											id="symbolWatchListSuggestionId"
											onfocus="if (this.value=='<s:text name="commons.symbolsearch.textbox"></s:text>') this.value='';"
											onblur="if (this.value=='') this.value='<s:text name="commons.symbolsearch.textbox"></s:text>';"
											value="<s:text name='commons.symbolsearch.textbox'></s:text>" />
									</td>
									<td>
										<table cellspacing="0" cellpadding="0" border="0">
											<tr>
												<td>
													<img width="6" height="26"
														src="<web:url value='/images/button/bt_left.gif'/>">
												</td>
												<td>
													<input type="button" value="Add watchlist"
														class="bt_button" id="btnAddWatchList" />
												</td>
												<td>
													<img width="6" height="26"
														src="<web:url value='/images/button/bt_right.gif'/>">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>



						<div id="popupContent" align="center"
							style="max-height: 300px; overflow: auto; width: auto;">

						</div>

					</div>
				</div>
				<div align="center" id="loadWatchListWaitImg"></div>
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
		</div>
	</div>
	<div class="clear"></div>
</div>




