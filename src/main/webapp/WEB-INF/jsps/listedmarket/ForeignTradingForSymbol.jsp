<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
    FROM_MODULE = URL_FOREIGN_TRADING;
});
</script>

<form name="fForeignTradingForSymbol" id="fForeignTradingForSymbol"
	method="post">
	<input id="fForeignTradingForSymbol_pagingInfo_indexPage" type="hidden"
		name="pagingInfo.indexPage" value="1" />

	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-statistic-nav.jsp"></jsp:include>

		<!--Start All Tab menu  -->
		<div class="content_small">
			<div class="content_tab" style="padding-top: 15px;">
				<div class="si_tab_new">
					<div>
						<h2>
							<span><s:text name="web.label.symbol" /> </span>
							<input type="text" name="searchMarketStatisticsView.symbol"
								id="fForeignTradingForSymbol_symbolID" disabled="disabled"
								value="<c:out value='${model.searchMarketStatisticsView.symbol}'/>"
								style="height: 20px; width: 88px; font-weight: bold;">
							<s:text name="web.label.MarketStatisticsAction.form.Fromdate" />
							<input name="strFromDate" type="text" class="textshortwidth"
								id="fForeignTradingForSymbol_FromDate"
								style="height: 20px; width: 88px;" />
							<s:text name="web.label.MarketStatisticsAction.form.Todate" />
							<input name="strToDate" type="text" class="textshortwidth"
								id="fForeignTradingForSymbol_ToDate"
								style="height: 20px; width: 88px;" />
							<input type="button" class="iButton autoHeight"
                                id="fForeignTradingForSymbol_View"
                                value='<s:text name="button.search"/>'>
						</h2>
					</div>
				</div>


				<!--Start Foreign Trading For Symbol-->
				<div class="table_Market clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<!--Content here  -->
							<td>
								<table class="nomal" cellpadding="0px" cellspacing="0px"
									width="100%" id="Listed_ForeignTradingForSymbol_tableResult">
									<colgroup>
										<col width="10%" />
										<col width="15%" />
										<col width="15%" />
										<col width="13%" />
										<col width="17%" />
										<col width="13%" />
										<col width="17%" />
									</colgroup>
									<thead>
										<tr bgcolor="#efefef">
											<td class="bordertd"
												style="font-weight: bold; text-align: center;" rowspan="2">
												<s:text name="web.label.date" />
											</td>
											<td class="bordertd"
												style="font-weight: bold; text-align: center;" rowspan="2">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.TotalRoom" />
											</td>
											<td class="bordertd"
												style="font-weight: bold; text-align: center;" rowspan="2">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.CurrentRoom" />
											</td>
											<td class="bordertd"
												style="font-weight: bold; text-align: center;" colspan="2">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.BuyingVolume" />
											</td>
											<td class="col_end"
												style="font-weight: bold; text-align: center;" colspan="2">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.SellingVolume" />
											</td>
										</tr>
										<tr bgcolor="#efefef">
											<td class="bordertd"
												style="font-weight: bold; text-align: center;">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Bids" />
											</td>
											<td class="bordertd"
												style="font-weight: bold; text-align: center;">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value" />
											</td>
											<td class="bordertd"
												style="font-weight: bold; text-align: center;">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Offers" />
											</td>
											<td class="col_end"
												style="font-weight: bold; text-align: center;">
												<s:text
													name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value" />
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div id="imageForeignTradingForSymbol"
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
					<div class="fpCalendar" style="padding-bottom: 20px;">
						<div class="fpCalendar-left"></div>
						<div class="fpCalendar-right"></div>
						<div align="right" class="fpCalendar-center"
							id="fSearchSymbol_paging"></div>
					</div>
				</div>
				<!-- End Foreign Trading For Symbol -->
			</div>
		</div>
	</div>
	<!--End All Tab menu  -->
</form>