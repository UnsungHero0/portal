<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.vndirect.com.vn/online-brokerage/utility"
	prefix="vndirectUtil"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<input type="hidden" id="allIndustriesText" value="<s:text name='web.label.StockScreen.All_industries'/>"/>
<form id="vndirectCntFrm" name="vndirectCntFrm">
	<s:hidden id="asset_class_1.id"
		name="searchStockScreenerBean.asset_class_1" />
	<s:hidden id="asset_class_2.id"
		name="searchStockScreenerBean.asset_class_2" />
	<s:hidden id="asset_class_3.id"
		name="searchStockScreenerBean.asset_class_3" />
	<s:hidden id="asset_class_4.id"
		name="searchStockScreenerBean.asset_class_4" />
	<s:hidden id="component_1.id"
		name="searchStockScreenerBean.component_1" />
	<s:hidden id="component_2.id"
		name="searchStockScreenerBean.component_2" />
	<s:hidden id="component_3.id"
		name="searchStockScreenerBean.component_3" />
	<s:hidden id="sector.id" name="searchStockScreenerBean.sector" />
	<s:hidden id="industry.id" name="searchStockScreenerBean.industry" />
	<s:hidden id="share_price_from.id"
		name="searchStockScreenerBean.share_price_from" />
	<s:hidden id="share_price_to.id"
		name="searchStockScreenerBean.share_price_to" />
	<s:hidden id="price_change_from.id"
		name="searchStockScreenerBean.price_change_from" />
	<s:hidden id="price_change_1.id"
		name="searchStockScreenerBean.price_change_1" />
	<s:hidden id="price_change_2.id"
		name="searchStockScreenerBean.price_change_2" />
	<s:hidden id="price_change_3.id"
		name="searchStockScreenerBean.price_change_3" />
	<s:hidden id="price_change_4.id"
		name="searchStockScreenerBean.price_change_4" />
	<s:hidden id="price_change_5.id"
		name="searchStockScreenerBean.price_change_5" />
	<s:hidden id="price_performance_1.id"
		name="searchStockScreenerBean.price_performance_1" />
	<s:hidden id="price_performance_2.id"
		name="searchStockScreenerBean.price_performance_2" />
	<s:hidden id="price_performance_3.id"
		name="searchStockScreenerBean.price_performance_3" />
	<s:hidden id="price_performance_4.id"
		name="searchStockScreenerBean.price_performance_4" />
	<s:hidden id="price_performance_5.id"
		name="searchStockScreenerBean.price_performance_5" />
	<s:hidden id="price_performance_6.id"
		name="searchStockScreenerBean.price_performance_6" />
	<s:hidden id="over_1.id" name="searchStockScreenerBean.over_1" />
	<s:hidden id="over_2.id" name="searchStockScreenerBean.over_2" />
	<s:hidden id="over_3.id" name="searchStockScreenerBean.over_3" />
	<s:hidden id="average_volume_from.id"
		name="searchStockScreenerBean.average_volume_from" />
	<s:hidden id="average_volume_to.id"
		name="searchStockScreenerBean.average_volume_to" />
	<s:hidden id="pe_ratio_1.id" name="searchStockScreenerBean.pe_ratio_1" />
	<s:hidden id="pe_ratio_2.id" name="searchStockScreenerBean.pe_ratio_2" />
	<s:hidden id="pe_ratio_3.id" name="searchStockScreenerBean.pe_ratio_3" />
	<s:hidden id="pe_ratio_4.id" name="searchStockScreenerBean.pe_ratio_4" />
	<s:hidden id="pe_ratio_5.id" name="searchStockScreenerBean.pe_ratio_5" />
	<s:hidden id="pe_ratio_6.id" name="searchStockScreenerBean.pe_ratio_6" />
	<s:hidden id="pe_ratio_7.id" name="searchStockScreenerBean.pe_ratio_7" />
	<s:hidden id="peg_1.id" name="searchStockScreenerBean.peg_1" />
	<s:hidden id="peg_2.id" name="searchStockScreenerBean.peg_2" />
	<s:hidden id="profit_margin_1.id"
		name="searchStockScreenerBean.profit_margin_1" />
	<s:hidden id="profit_margin_2.id"
		name="searchStockScreenerBean.profit_margin_2" />
	<s:hidden id="profit_margin_3.id"
		name="searchStockScreenerBean.profit_margin_3" />
	<s:hidden id="profit_margin_4.id"
		name="searchStockScreenerBean.profit_margin_4" />
	<s:hidden id="profit_margin_5.id"
		name="searchStockScreenerBean.profit_margin_5" />
	<s:hidden id="profit_margin_and_1.id"
		name="searchStockScreenerBean.profit_margin_and_1" />
	<s:hidden id="profit_margin_and_2.id"
		name="searchStockScreenerBean.profit_margin_and_2" />
	<s:hidden id="price_sale_ratio_1.id"
		name="searchStockScreenerBean.price_sale_ratio_1" />
	<s:hidden id="price_sale_ratio_2.id"
		name="searchStockScreenerBean.price_sale_ratio_2" />
	<s:hidden id="price_sale_ratio_3.id"
		name="searchStockScreenerBean.price_sale_ratio_3" />
	<s:hidden id="price_sale_ratio_4.id"
		name="searchStockScreenerBean.price_sale_ratio_4" />
	<s:hidden id="price_sale_ratio_5.id"
		name="searchStockScreenerBean.price_sale_ratio_5" />
	<s:hidden id="price_sale_ratio_6.id"
		name="searchStockScreenerBean.price_sale_ratio_6" />
	<s:hidden id="price_sale_ratio_7.id"
		name="searchStockScreenerBean.price_sale_ratio_7" />
	<s:hidden id="price_book_ratio_1.id"
		name="searchStockScreenerBean.price_book_ratio_1" />
	<s:hidden id="price_book_ratio_2.id"
		name="searchStockScreenerBean.price_book_ratio_2" />
	<s:hidden id="price_book_ratio_3.id"
		name="searchStockScreenerBean.price_book_ratio_3" />
	<s:hidden id="price_book_ratio_4.id"
		name="searchStockScreenerBean.price_book_ratio_4" />
	<s:hidden id="price_book_ratio_5.id"
		name="searchStockScreenerBean.price_book_ratio_5" />
	<s:hidden id="return_equity_5.id"
		name="searchStockScreenerBean.return_equity_5" />
	<s:hidden id="return_equity_1.id"
		name="searchStockScreenerBean.return_equity_1" />
	<s:hidden id="return_equity_2.id"
		name="searchStockScreenerBean.return_equity_2" />
	<s:hidden id="return_equity_3.id"
		name="searchStockScreenerBean.return_equity_3" />
	<s:hidden id="return_equity_4.id"
		name="searchStockScreenerBean.return_equity_4" />
	<s:hidden id="return_equity_and_1.id"
		name="searchStockScreenerBean.return_equity_and_1" />
	<s:hidden id="return_equity_and_2.id"
		name="searchStockScreenerBean.return_equity_and_2" />
	<s:hidden id="return_asset_5.id"
		name="searchStockScreenerBean.return_asset_5" />
	<s:hidden id="return_asset_1.id"
		name="searchStockScreenerBean.return_asset_1" />
	<s:hidden id="return_asset_2.id"
		name="searchStockScreenerBean.return_asset_2" />
	<s:hidden id="return_asset_3.id"
		name="searchStockScreenerBean.return_asset_3" />
	<s:hidden id="return_asset_4.id"
		name="searchStockScreenerBean.return_asset_4" />
	<s:hidden id="return_asset_and_1.id"
		name="searchStockScreenerBean.return_asset_and_1" />
	<s:hidden id="return_asset_and_2.id"
		name="searchStockScreenerBean.return_asset_and_2" />
	<s:hidden id="eps_growth_annual_1.id"
		name="searchStockScreenerBean.eps_growth_annual_1" />
	<s:hidden id="eps_growth_annual_2.id"
		name="searchStockScreenerBean.eps_growth_annual_2" />
	<s:hidden id="eps_growth_annual_3.id"
		name="searchStockScreenerBean.eps_growth_annual_3" />
	<s:hidden id="eps_growth_annual_4.id"
		name="searchStockScreenerBean.eps_growth_annual_4" />
	<s:hidden id="eps_growth_annual_5.id"
		name="searchStockScreenerBean.eps_growth_annual_5" />
	<s:hidden id="eps_growth_annual_6.id"
		name="searchStockScreenerBean.eps_growth_annual_6" />
	<s:hidden id="eps_growth_annual_7.id"
		name="searchStockScreenerBean.eps_growth_annual_7" />
	<s:hidden id="eps_growth_annual_8.id"
		name="searchStockScreenerBean.eps_growth_annual_8" />
	<s:hidden id="revenue_growth_annual_1.id"
		name="searchStockScreenerBean.revenue_growth_annual_1" />
	<s:hidden id="revenue_growth_annual_2.id"
		name="searchStockScreenerBean.revenue_growth_annual_2" />
	<s:hidden id="revenue_growth_annual_3.id"
		name="searchStockScreenerBean.revenue_growth_annual_3" />
	<s:hidden id="revenue_growth_annual_4.id"
		name="searchStockScreenerBean.revenue_growth_annual_4" />
	<s:hidden id="revenue_growth_annual_5.id"
		name="searchStockScreenerBean.revenue_growth_annual_5" />
	<s:hidden id="revenue_growth_annual_6.id"
		name="searchStockScreenerBean.revenue_growth_annual_6" />
	<s:hidden id="revenue_growth_annual_7.id"
		name="searchStockScreenerBean.revenue_growth_annual_7" />
	<s:hidden id="revenue_growth_annual_8.id"
		name="searchStockScreenerBean.revenue_growth_annual_8" />
	<s:hidden id="eps_growth_quarterly_1.id"
		name="searchStockScreenerBean.eps_growth_quarterly_1" />
	<s:hidden id="eps_growth_quarterly_2.id"
		name="searchStockScreenerBean.eps_growth_quarterly_2" />
	<s:hidden id="eps_growth_quarterly_3.id"
		name="searchStockScreenerBean.eps_growth_quarterly_3" />
	<s:hidden id="eps_growth_quarterly_4.id"
		name="searchStockScreenerBean.eps_growth_quarterly_4" />
	<s:hidden id="eps_growth_quarterly_5.id"
		name="searchStockScreenerBean.eps_growth_quarterly_5" />
	<s:hidden id="eps_growth_quarterly_6.id"
		name="searchStockScreenerBean.eps_growth_quarterly_6" />
	<s:hidden id="eps_growth_quarterly_7.id"
		name="searchStockScreenerBean.eps_growth_quarterly_7" />
	<s:hidden id="eps_growth_quarterly_8.id"
		name="searchStockScreenerBean.eps_growth_quarterly_8" />
	<s:hidden id="revenue_growth_quarterly_1.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_1" />
	<s:hidden id="revenue_growth_quarterly_2.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_2" />
	<s:hidden id="revenue_growth_quarterly_3.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_3" />
	<s:hidden id="revenue_growth_quarterly_4.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_4" />
	<s:hidden id="revenue_growth_quarterly_5.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_5" />
	<s:hidden id="revenue_growth_quarterly_6.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_6" />
	<s:hidden id="revenue_growth_quarterly_7.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_7" />
	<s:hidden id="revenue_growth_quarterly_8.id"
		name="searchStockScreenerBean.revenue_growth_quarterly_8" />
	<s:hidden id="dividend_growth_5_year_1.id"
		name="searchStockScreenerBean.dividend_growth_5_year_1" />
	<s:hidden id="dividend_growth_5_year_2.id"
		name="searchStockScreenerBean.dividend_growth_5_year_2" />
	<s:hidden id="dividend_growth_5_year_3.id"
		name="searchStockScreenerBean.dividend_growth_5_year_3" />
	<s:hidden id="dividend_growth_5_year_4.id"
		name="searchStockScreenerBean.dividend_growth_5_year_4" />
	<s:hidden id="dividend_growth_5_year_5.id"
		name="searchStockScreenerBean.dividend_growth_5_year_5" />
	<s:hidden id="dividend_yield_1.id"
		name="searchStockScreenerBean.dividend_yield_1" />
	<s:hidden id="dividend_yield_2.id"
		name="searchStockScreenerBean.dividend_yield_2" />
	<s:hidden id="dividend_yield_3.id"
		name="searchStockScreenerBean.dividend_yield_3" />
	<s:hidden id="dividend_yield_4.id"
		name="searchStockScreenerBean.dividend_yield_4" />
	<s:hidden id="dividend_yield_5.id"
		name="searchStockScreenerBean.dividend_yield_5" />
	<s:hidden id="dividend_yield_6.id"
		name="searchStockScreenerBean.dividend_yield_6" />
	<s:hidden id="dividend_yield_and_1.id"
		name="searchStockScreenerBean.dividend_yield_and_1" />
	<s:hidden id="dividend_yield_and_2.id"
		name="searchStockScreenerBean.dividend_yield_and_2" />
	<s:hidden id="_52_week_high.id"
		name="searchStockScreenerBean._52_week_high" />
	<s:hidden id="_52_week_high_1.id"
		name="searchStockScreenerBean._52_week_high_1" />
	<s:hidden id="_52_week_low.id"
		name="searchStockScreenerBean._52_week_low" />
	<s:hidden id="_52_week_low_1.id"
		name="searchStockScreenerBean._52_week_low_1" />
	<s:hidden id="price_closes_sam_1.id"
		name="searchStockScreenerBean.price_closes_sam_1" />
	<s:hidden id="price_closes_sam_2.id"
		name="searchStockScreenerBean.price_closes_sam_2" />
	<s:hidden id="price_closes_sam_3.id"
		name="searchStockScreenerBean.price_closes_sam_3" />
	<s:hidden id="price_closes_sam_4.id"
		name="searchStockScreenerBean.price_closes_sam_4" />
	<s:hidden id="price_closes_sam_5.id"
		name="searchStockScreenerBean.price_closes_sam_5" />
	<s:hidden id="price_closes_sam_6.id"
		name="searchStockScreenerBean.price_closes_sam_6" />
	<s:hidden id="price_closes_sam_7.id"
		name="searchStockScreenerBean.price_closes_sam_7" />
	<s:hidden id="price_closes_sam_8.id"
		name="searchStockScreenerBean.price_closes_sam_8" />
	<s:hidden id="above_below_1.id"
		name="searchStockScreenerBean.above_below_1" />
	<s:hidden id="above_below_2.id"
		name="searchStockScreenerBean.above_below_2" />
	<s:hidden id="_13_day_sma_1.id"
		name="searchStockScreenerBean._13_day_sma_1" />
	<s:hidden id="_13_day_sma_2.id"
		name="searchStockScreenerBean._13_day_sma_2" />
	<s:hidden name="searchStockScreenerBean.saveSearchName" />
	<s:hidden name="searchStockScreenerBean.saveSearchId" />
</form>

<s:set name="stockScreener" value="false" scope="action"></s:set>
<s:set name="listedScreenEnable" value="true" scope="action"></s:set>
<s:set name="listedScreen" value="true" scope="action"></s:set>
<s:set name="stockWizard" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.InvestmentIdeasTmpl">
	<tiles:putAttribute name="MainContent">
		<!--  -->
		<div>
			<s:text name="web.label.StockScreen.Title_1" />
		</div>

		<div class="left_text_slcp width">
			<p class="left">
			   <s:text name="web.label.StockScreen.Title_2" />
            </p>
			<!--<span class="button_long right">
			     <input type="button" value="<s:text name="web.label.StockScreen.ViewResult" />" onclick="javascript:doSummary();"/>
			</span>
			-->
			<div class="right">
				<input type="button" class="iButton"
					onclick="javascript:doSummary();"
					value='<s:text name="web.label.StockScreen.ViewResult" />'>
	
				<input type="button" class="iButton" onclick="javascript:doClear();"
					value='<s:text name="Messages.Commons.Buttons.Reset"/>'>

	            <a href="<web:url value='/cong-cu-phan-tich-chung-khoan/danh-sach-cac-tieu-chi-da-luu.shtml'/>">
		            <input type="button" class="iButton"
	                   value='<s:text name="Messages.Commons.Buttons.Saved">Tiêu chí đã lưu</s:text>'>
	            </a>
            </div>	
            <div class="clearfix"></div>			
		</div>

		<div class="box_slcp">
			<div class="left_text_slcp">
				<s:text name="web.label.StockScreen.There_are" />
				<span id="cnt_all_c_result_1" class="color">--</span>
				<s:text name="web.label.StockScreen.of_total" />
				<span id="cnt_all_c" class="color">--</span>
				<s:text name="web.label.StockScreen.companies_fits" />

				<span class="color padding-left20"> <s:text
						name="web.label.StockScreen.Total" />: <span
					id="cnt_all_c_result_2">--</span> </span>
			</div>

			<div class="clear"></div>

			<div id="tab_sup_1" class="clearfix ui-tabs-container ui-tabs-hide"
				style="display: block;">
				<div class="clearfixpadding-top10 padding-bottom10">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<h2>
									<s:text name="web.label.StockScreen.Market_segment" />
								</h2>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="content_dv">
							<div class="clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_sl">
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.AssetClass" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input type="button"
															title="<s:text name="web.label.StockScreen.TinyCapDes"/>"
															value="<s:text name="web.label.StockScreen.TinyCap"/>"
															id="asset_class_1" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="asset_class_2" type="button"
															title='<s:text name="web.label.StockScreen.SmallCapDes"/>'
															value="<s:text name="web.label.StockScreen.SmallCap"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="asset_class_3"
															title='<s:text name="web.label.StockScreen.MidCapDes"/>'
															type="button"
															value="<s:text name="web.label.StockScreen.MidCap"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="asset_class_4"
															title='<s:text name="web.label.StockScreen.LargeCapDes"/>'
															type="button"
															value="<s:text name="web.label.StockScreen.LargeCap"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_asset_class"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.Component_of" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="component_1" type="button" value="HOSE"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="component_2" type="button" value="HNX"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="component_3" type="button" value="OTC"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_component"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.Sector_Industry" />
										</td>
										<td class="td_btn">
											<div style="float: left;">
												<s:select headerKey=""
													headerValue="%{getText('web.label.StockScreen.All_sectors')}"
													list="listSectorsName" listKey="sectorCode" theme="simple"
													listValue="sectorName"
													onchange="javascript:inputText('sector');javascript:getListIndustry();"
													id="sector">
												</s:select>
												&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
											<div id="divShowIndustryListName_id" style="display: none;">
												<select onchange='javascript:inputText("industry");'
													id="industry"></select>
											</div>
										</td>
										<td align="right" id="cnt_sector_industry"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td align="right" colspan="3">
											<span class="bold_3"><s:text
													name="web.label.StockScreen.Cumulative" />:</span>&nbsp;
											<span class="c-result" id="cnt_g1_c_result"> -- </span>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tab_sup_1" class="clearfix ui-tabs-container ui-tabs-hide"
				style="display: block;">
				<div class="clearfixpadding-top10 padding-bottom10">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<h2>
									<s:text name="web.label.StockScreen.Price_Volume" />
								</h2>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="content_dv">
							<div class="clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_sl">
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.SharePrice" />
										</td>
										<td class="td_btn">
											<s:text name="web.label.StockScreen.Greater_than" />
											<input class="ip_txt" type="text" id="share_price_from"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean.share_price_from}" />"
												style="text-align: right"
												onblur="javascript:inputText('share_price_from');" />
											<s:text name="web.label.StockScreen._1000_VND_and_less_than" />
											<input class="ip_txt" type="text" id="share_price_to"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean.share_price_to}" />"
												style="text-align: right"
												onblur="javascript:inputText('share_price_to');" />
											<s:text name="web.label.StockScreen._1000_VND" />
										</td>
										<td id="cnt_share_price" align="right"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.Price_Change" />
										</td>
										<td class="td_btn">
											<s:text name="web.label.StockScreen.Greater_than" />
											<input class="ip_txt" type="text" id="price_change_from"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean.price_change_from}" />"
												size="6" style="text-align: right"
												onblur="javascript:inputText('price_change_from');" />
											<s:text name="web.label.StockScreen._over_the_last" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_change_1" type="button"
															value="<s:text name="web.label.StockScreen._1_Day"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_change_2" type="button"
															value="<s:text name="web.label.StockScreen._5_Days"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_change_3" type="button"
															value="<s:text name="web.label.StockScreen._4_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_change_4" type="button"
															value="<s:text name="web.label.StockScreen._13_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray" style="margin-right: 0px;"> <span
													class="btn_center_gray"> <input id="price_change_5"
															type="button"
															value="<s:text name="web.label.StockScreen._52_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_price_change"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.price_performance" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_1" type="button"
															value="<-40%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_2" type="button"
															value="-40% to 20%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_3" type="button"
															value="-20 to 0%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_4" type="button"
															value="0% to 20%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_5" type="button"
															value="20% to 40%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_performance_6" type="button"
															value="> 40%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.Over" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="over_1" type="button"
															value="<s:text name="web.label.StockScreen.Last_4_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="over_2" type="button"
															value="<s:text name="web.label.StockScreen.Last_12_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="over_3" type="button"
															value="<s:text name="web.label.StockScreen.Last_52_Weeks"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_price_performance"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.Average_Volume" />
										</td>
										<td class="td_btn">
											<s:text name="web.label.StockScreen.Greater_than" />
											<input class="ip_txt" type="text" id="average_volume_from"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean.average_volume_from}" />"
												style="text-align: right"
												onblur="javascript:inputText('average_volume_from');" />
											<s:text name="web.label.StockScreen._and_less_than" />
											<input class="ip_txt" type="text" id="average_volume_to"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean.average_volume_to}" />"
												style="text-align: right"
												onblur="javascript:inputText('average_volume_to');" />
											<s:text name="web.label.StockScreen._over_the_last_10_days" />
										</td>
										<td align="right" id="cnt_average_volume"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td align="right" colspan="3">
											<span class="bold_3"><s:text
													name="web.label.StockScreen.Cumulative" />:</span>&nbsp;
											<span class="c-result" id="cnt_g2_c_result"> -- </span>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tab_sup_1" class="clearfix ui-tabs-container ui-tabs-hide"
				style="display: block;">
				<div class="clearfixpadding-top10 padding-bottom10">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<h2>
									<s:text name="web.label.StockScreen.Fundamental" />
								</h2>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="content_dv">
							<div class="clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_sl">
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.pe_ratio" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_1" type="button" value="0 - 5x"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_2" type="button" value="5 - 10x"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_3" type="button" value="10 - 15x"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_4" type="button" value="10 - 20x"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_5" type="button" value="> 25x"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.And" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_6" type="button"
															value="<s:text name="web.label.StockScreen.Below_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="pe_ratio_7" type="button"
															value="<s:text name="web.label.StockScreen.Above_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_pe_ratio"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.PEG" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="peg_1" type="button"
															value="<s:text name="web.label.StockScreen._1_or_Less"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="peg_2" type="button"
															value="<s:text name="web.label.StockScreen.More_than_1"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_peg"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.profit_margin" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_1" type="button" value="0 - 5%"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_2" type="button"
															value="5% to 10%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_3" type="button"
															value="10% to 20%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_4" type="button"
															value="20% to 50%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_5" type="button" value="> 50%"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.And" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_and_1" type="button"
															value="<s:text name="web.label.StockScreen.Below_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="profit_margin_and_2" type="button"
															value="<s:text name="web.label.StockScreen.Above_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_profit_margin"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.price_sale_ratio" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_1" type="button"
															value="0 - 0.1x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_2" type="button"
															value="0.1 - 0.2x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_3" type="button"
															value="0.2 - 0.5x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_4" type="button"
															value="0.5 - 1x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_5" type="button"
															value="1 - 5x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_6" type="button"
															value="5 - 10x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_sale_ratio_7" type="button" value=">10x"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_price_sale_ratio"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>

									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.price_book_ratio" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_book_ratio_1" type="button"
															value="0 - 0.5x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_book_ratio_2" type="button"
															value="0.5 - 1x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_book_ratio_3" type="button"
															value="1 - 2x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_book_ratio_4" type="button"
															value="2 - 3x" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_book_ratio_5" type="button" value="> 3x"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_price_book_ratio"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.return_equity" />
										</td>
										<td class="td_btn">

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_5" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_1" type="button" value="0 - 5%"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_2" type="button"
															value="5% to 15%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_3" type="button"
															value="15% to 25%" onclick="toggleStatus(this)" /> </span> </span> </span>


											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_4" type="button" value="> 25%"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.And" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_and_1" type="button"
															value="<s:text name="web.label.StockScreen.Below_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_equity_and_2" type="button"
															value="<s:text name="web.label.StockScreen.Above_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_return_equity"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.return_asset" />
										</td>
										<td class="td_btn">

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_5" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_1" type="button" value="0 - 5%"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_2" type="button" value="5% to 15%"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_3" type="button"
															value="15% to 25%" onclick="toggleStatus(this)" /> </span> </span> </span>


											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_4" type="button" value="> 25%"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.And" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_and_1" type="button"
															value="<s:text name="web.label.StockScreen.Below_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="return_asset_and_2" type="button"
															value="<s:text name="web.label.StockScreen.Above_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_return_asset"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td align="right" colspan="3">
											<span class="bold_3"><s:text
													name="web.label.StockScreen.Cumulative" />:</span>&nbsp;
											<span class="c-result" id="cnt_g3_c_result"> -- </span>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tab_sup_1" class="clearfix ui-tabs-container ui-tabs-hide"
				style="display: block;">
				<div class="clearfixpadding-top10 padding-bottom10">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<h2>
									<s:text name="web.label.StockScreen.Earnings_and_Dividends" />
								</h2>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="content_dv">
							<div class="clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_sl">
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.eps_growth_annual" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_1" type="button"
															value="0 - 5" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_2" type="button"
															value="5 - 10" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_3" type="button"
															value="10 - 15" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_4" type="button"
															value="15 - 25" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_5" type="button"
															value="25 - 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_6" type="button" value="> 50"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_7" type="button"
															value="<s:text name="web.label.StockScreen.Positive_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_annual_8" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen._4_Current_Fiscal_Year" />
										</td>
										<td align="right" id="cnt_eps_growth_annual"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.revenue_growth_annual" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_1" type="button"
															value="0 - 5" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_2" type="button"
															value="5 - 10" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_3" type="button"
															value="10 - 15" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_4" type="button"
															value="15 - 25" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_5" type="button"
															value="25 - 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_6" type="button"
															value="> 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_7" type="button"
															value="<s:text name="web.label.StockScreen.Positive_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_annual_8" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen._4_Current_Fiscal_Year" />
										</td>
										<td align="right" id="cnt_revenue_growth_annual"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.eps_growth_quarterly" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_1" type="button"
															value="0 - 5" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_2" type="button"
															value="5 - 10" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_3" type="button"
															value="10 - 15" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_4" type="button"
															value="15 - 25" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_5" type="button"
															value="25 - 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_6" type="button"
															value="> 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_7" type="button"
															value="<s:text name="web.label.StockScreen.Positive_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="eps_growth_quarterly_8" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

										</td>
										<td align="right" id="cnt_eps_growth_quarterly"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.revenue_growth_quarterly" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_1" type="button"
															value="0 - 5" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_2" type="button"
															value="5 - 10" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_3" type="button"
															value="10 - 15" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_4" type="button"
															value="15 - 25" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_5" type="button"
															value="25 - 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_6" type="button"
															value="> 50" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_7" type="button"
															value="<s:text name="web.label.StockScreen.Positive_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="revenue_growth_quarterly_8" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_revenue_growth_quarterly"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>

									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.dividend_growth_5_year" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_growth_5_year_1" type="button"
															value="<s:text name="web.label.StockScreen.Negative_Change"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_growth_5_year_2" type="button"
															value="0 - 5" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_growth_5_year_3" type="button"
															value="5 - 15" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_growth_5_year_4" type="button"
															value="15 - 25" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_growth_5_year_5" type="button"
															value="> 25" onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_dividend_growth_5_year"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.dividend_yield" />
										</td>
										<td class="td_btn">

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_1" type="button"
															value="<s:text name="web.label.StockScreen.Positive_Yield"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_2" type="button" value="0 - 2"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_3" type="button" value="2 - 4"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_4" type="button" value="4 - 6"
															onclick="toggleStatus(this)" /> </span> </span> </span>


											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_5" type="button" value="6 - 8"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_6" type="button" value="> 8"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<br />
											<br />
											<s:text name="web.label.StockScreen.And" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_and_1" type="button"
															value="<s:text name="web.label.StockScreen.Below_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="dividend_yield_and_2" type="button"
															value="<s:text name="web.label.StockScreen.Above_Industry_Average"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_dividend_yield"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td align="right" colspan="3">
											<span class="bold_3"><s:text
													name="web.label.StockScreen.Cumulative" />:</span>&nbsp;
											<span class="c-result" id="cnt_g4_c_result"> -- </span>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="tab_sup_1" class="clearfix ui-tabs-container ui-tabs-hide"
				style="display: block;">
				<div class="clearfixpadding-top10 padding-bottom10">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<h2>
									<s:text name="web.label.StockScreen.Technicals" />
								</h2>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="content_dv">
							<div class="clearfix">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="tbl_sl">
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen._52_week_high" />
										</td>
										<td class="td_btn">
											<s:text name="web.label.StockScreen.Stock_is_within" />
											<input class="ip_txt" type="text" id="_52_week_high"
												size="10"
												value="<c:out value="${model.searchStockScreenerBean._52_week_high}" />"
												style="text-align: right"
												onblur="javascript:inputText('_52_week_high');" />
											<s:text name="web.label.StockScreen._of_its" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="_52_week_high_1" type="button"
															value="<s:text name="web.label.StockScreen._52_week_high"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_52_week_high"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen._52_week_low" />
										</td>
										<td class="td_btn">
											<s:text name="web.label.StockScreen.Stock_is_within" />
											<input class="ip_txt" type="text" id="_52_week_low" size="10"
												value="<c:out value="${model.searchStockScreenerBean._52_week_low}" />"
												style="text-align: right"
												onblur="javascript:inputText('_52_week_low');" />
											<s:text name="web.label.StockScreen._of_its" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="_52_week_low_1" type="button"
															value="<s:text name="web.label.StockScreen._52_week_low"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_52_week_low"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen.price_closes_sam" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_1" type="button"
															value="< -15%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_2" type="button"
															value="-15% to -10%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_3" type="button"
															value="-10% to -5%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_4" type="button"
															value="-5% to 0%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_5" type="button"
															value="0 to 5%" onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_6" type="button"
															value="5% to 10%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_7" type="button"
															value="10% to 15%" onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="price_closes_sam_8" type="button" value="> 15%"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<br />
											<br />
											<s:text name="web.label.StockScreen.Above_Below" />
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="above_below_1" type="button"
															value="<s:text name="web.label.StockScreen._13_Day_SMA"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="above_below_2" type="button"
															value="<s:text name="web.label.StockScreen._50_Day_SMA"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_price_closes_sam"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td class="td_title_box">
											<s:text name="web.label.StockScreen._13_50_Day_SMA" />
										</td>
										<td class="td_btn">
											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="_13_day_sma_1" type="button"
															value="<s:text name="web.label.StockScreen.Below"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>

											<span class="btn_left_gray"> <span
												class="btn_right_gray"> <span class="btn_center_gray">
														<input id="_13_day_sma_2" type="button"
															value="<s:text name="web.label.StockScreen.Above"/>"
															onclick="toggleStatus(this)" /> </span> </span> </span>
										</td>
										<td align="right" id="cnt_13_day_sma"
											style="border-bottom: 1px solid #a0a7b2;">
											---
										</td>
									</tr>
									<tr>
										<td align="right" colspan="3">
											<span class="bold_3"><s:text
													name="web.label.StockScreen.Cumulative" />:</span>&nbsp;
											<span class="c-result" id="cnt_g5_c_result"> -- </span>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="left_text_slcp width">			
            <div class="right">
                <input type="button" class="iButton"
                    onclick="javascript:doSummary();"
                    value='<s:text name="web.label.StockScreen.ViewResult" />'>
    
                <input type="button" class="iButton" onclick="javascript:doClear();"
                    value='<s:text name="Messages.Commons.Buttons.Reset"/>'>
                
                <a href="<web:url value='/cong-cu-phan-tich-chung-khoan/danh-sach-cac-tieu-chi-da-luu.shtml'/>">
                    <input type="button" class="iButton"
                       value='<s:text name="Messages.Commons.Buttons.Saved">Tiêu chí đã lưu</s:text>'>
                </a>
            </div>  
            <div class="clearfix"></div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>