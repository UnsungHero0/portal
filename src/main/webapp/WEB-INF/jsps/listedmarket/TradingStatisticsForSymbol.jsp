<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
    FROM_MODULE = URL_TRADING_STATISTC;
});
</script>

<form name="fTradingStatisticsForSymbol"
	id="fTradingStatisticsForSymbol" method="post">
	<input type="hidden" name="model.downloadType" id="downloadType">
	<input id="fTradingStatisticsForSymbol_pagingInfo_indexPage"
		type="hidden" name="pagingInfo.indexPage" value="1" />

	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-statistic-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
				<div class="si_tab_new">
					<div>
						<h2>
							<span><s:text name="web.label.symbol" /> </span>
							<input type="text" name="searchMarketStatisticsView.symbol"
								id="searchMarketStatisticsView.symbol.id" disabled="disabled"
								value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>"
								style="height: 20px; width: 88px; font-weight: bold;">
							<s:hidden name="model.searchMarketStatisticsView.symbol"
								id="symbolID"></s:hidden>
							<s:text name="web.label.MarketStatisticsAction.form.Fromdate" />
							<input name="strFromDate" type="text" class="textshortwidth"
								id="fTradingStatisticsForSymbol_FromDate"
								style="height: 20px; width: 88px;" />
							<s:text name="web.label.MarketStatisticsAction.form.Todate" />
							<input name="strToDate" type="text" class="textshortwidth"
								id="fTradingStatisticsForSymbol_ToDate"
								style="height: 20px; width: 88px;" />
							<%--<span class="button_long"> <input type="button" onclick=""
									id="fTradingStatisticsForSymbol_View"
									value="<s:text name="button.search"></s:text>"> </span>
									--%>
							<input type="button" class="iButton autoHeight"
								id="fTradingStatisticsForSymbol_View"
								value='<s:text name="button.search"/>'>
							<a style="margin-left: 250px;" href="javascript:doDownload();"
								style="text-decoration: none"><img
									src="<web:url value='/images/img/ico_11.gif'/>" hspace="5" /> <s:text
									name="button.downloadFile" /> </a>
						</h2>
					</div>
				</div>


				<!--Start Trading Statistics For Symbol-->
				<div class="table_Market clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<!--Content here  -->
							<td>
								<table class="nomal" cellpadding="0px" cellspacing="0px"
									width="100%" id="Listed_TradingStatisticsForSymbol_tableResult">
									<colgroup>
										<col width="9%" />
										<col width="8%" />
										<col width="8%" />
										<col width="10%" />
										<col width="8%" />
										<col width="10%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="9%" />
										<col width="12%" />
									</colgroup>
									<thead>
										<tr bgcolor="#efefef">
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text name="web.label.date" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.CellNames.Close" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" colspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Bid" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" colspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Offer" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidAverage" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferAverage" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Change" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.TotalOFVolume" />
													&nbsp;
												</div>
											</td>
											<td class="col_end" rowspan="2">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.TotalOFValue" />
													&nbsp;
												</div>
											</td>
										</tr>
										<tr bgcolor="#efefef">
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidOrder" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidVolume" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferOrder" />
													&nbsp;
												</div>
											</td>
											<td class="bordertd">
												<div style="font-weight: bold; text-align: center">
													&nbsp;
													<s:text
														name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferVolume" />
													&nbsp;
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div id="imageTradingStatisticsForSymbol"
													style="height: 300px;">
													<img src="<web:url value='/images/ajax-loader.gif'/>" />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<!--End Content here  -->
						</tr>
					</table>
					<div class="fpCalendar">
						<div class="fpCalendar-left"></div>
						<div class="fpCalendar-right"></div>
						<div align="right" class="fpCalendar-center"
							id="fSearchSymbol_paging"></div>
					</div>
				</div>
				<!-- End Trading Statistics For Symbol -->
			</div>
		</div>
		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
			</div>
		</div>
	</div>
	<!--End All Tab menu  -->
</form>