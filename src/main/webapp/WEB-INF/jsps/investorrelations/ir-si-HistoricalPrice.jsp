<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="fHistoricalPrice" id="fHistoricalPrice" method="post">
	<input type="hidden" name="model.downloadType" id="downloadType">
	<input id="fHistoricalPrice_pagingInfo_indexPage" type="hidden"
		name="pagingInfo.indexPage" value="1" />

	<div id="content_ttpt">
		<!-- nav -->
		<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-si-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
				<div class="si_tab_new">
					<div>
						<h2>
							<span> <s:text name="web.label.symbol" /> </span>
							<input type="text" name="searchMarketStatisticsView.symbol"
								id="symbolID" disabled="disabled"
								value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>"
								style="height: 20px; width: 88px; font-weight: bold;">
							<input type="hidden" name="searchMarketStatisticsView.symbol"
								id="symbolID"
								value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>">
							<span style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="web.label.MarketStatisticsAction.form.Fromdate" />
							<input name="strFromDate" type="text" class="textshortwidth"
								id="fHistoricalPrice_FromDate"
								style="height: 20px; width: 88px;margin-right:10px;" />
							<s:text name="web.label.MarketStatisticsAction.form.Todate" />
							<input name="strToDate" type="text" class="textshortwidth"
								id="fHistoricalPrice_ToDate" style="height: 20px; width: 88px;margin-right:10px;" />
							<input type="button" class="iButton" id="fHistoricalPrice_View"
								value='<s:text name="button.search"/>'>
							<a href="javascript:doDownload();"
								style="text-decoration: none; margin-left: 200px;float:right;"><img
									src="<web:url value='/images/img/ico_11.gif'/>" hspace="5"
									style="margin-bottom: -4px;" /> <s:text
									name="button.downloadFile" /> </a>
						</h2>
					</div>
				</div>

				<!-- -->
				<div class="table_Market clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<!--Content here  -->
							<td>
								<table class="nomal" cellpadding="0px" cellspacing="0px"
									width="100%" id="Listed_HistoricalPrice_tableResult">
									<colgroup>
										<col width="9%" />
										<col width="13%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="12%" />
										<col width="12%" />
									</colgroup>
									<thead>
										<tr bgcolor="#efefef">
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text name="web.label.date" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Change" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.CellNames.Open" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.CellNames.High" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.CellNames.Low" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.CellNames.Close" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AveragePrice" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.AdjustPrice" />
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.Volume" />
												</div>
											</td>
											<td class="col_end">
												<div style="font-weight: bold; text-align: center">
													<s:text
														name="web.label.MarketStatisticsAction.form.HistoricalPrice.CellNames.PtVolume" />
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div id="imageHistoricalPriceForSymbol"
													style="height: 300px;">
													<img src="<web:url value='/images/ajax-loader.gif'/>" />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<!--End Content here  fSearchSymbol_paging-->
						</tr>
					</table>
					<div class="fpCalendar">
						<div class="fpCalendar-left"></div>
						<div class="fpCalendar-right"></div>
						<div align="right" class="fpCalendar-center"
							id="fSearchSymbol_paging"></div>
					</div>
				</div>
				<!-- End HistoricalPriceForSymbol -->
				<P style="padding: 20px 0;">
					<span style="color: red; font-size: 14px; font-style: italic;"><s:text
							name="web.label.MarketStatisticsAction.form.HistoricalPrice.Description"></s:text>
					</span>
				</P>
			</div>
		</div>
	</div>
	<!--End All Tab menu  -->
</form>