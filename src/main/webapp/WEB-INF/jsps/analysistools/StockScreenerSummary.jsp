<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.i18n.I18NUtility"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
%>
<script type="text/javascript">

</script>
<style>
<!--
thead tr td {
	text-align: center;
}
-->
</style>

<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="true" scope="action"></s:set>
<s:set name="listedScreen" value="true" scope="action"></s:set>
<s:set name="stockWizard" value="true" scope="action"></s:set>

<web:url value="/analysis/StockScreenerList.shtml"
	var="StockScreenerListURL"></web:url>
<s:form id="cacheForm" theme="simple">
	<s:hidden id="cacheData" name="cacheData"></s:hidden>
</s:form>
<tiles:insertDefinition name="Analysis.InvestmentIdeasNavTmpl">
	<tiles:putAttribute name="MainContent">
		<div class="title_kq">
			Kết quả
		</div>
		<div class="left_text_slcp width">
			<p>
				<c:if test="${not empty searchStockScreenerBean.saveSearchName}">
					<b> <s:property value="searchStockScreenerBean.saveSearchName" />
					</b>
					<br />
				</c:if>
				<s:property
					value="%{getText('web.label.Result_View.Matching_Result', {model.pagingInfo.getTotal()})}"
					escape="false" />
			</p>

			<input type="button" class="modify iButton"
				value='<s:text name="web.label.Result_View.ModifySearch_Button"/>'>

			<input type="button" class="save iButton"
				id="fNewsForSymbol_searchButton"
				value='<s:text name="web.label.Result_View.Save_Screen_Button"/>'>

			<a href="<web:url value='/cong-cu-phan-tich-chung-khoan/danh-sach-cac-tieu-chi-da-luu.shtml'/>">
                   <input type="button" class="iButton"
                      value='<s:text name="Messages.Commons.Buttons.Saved">Tiêu chí đã lưu</s:text>'>
               </a>
		</div>

		<div class="box_slcp">
			<%--#########################################HEADER CONTENT######################################## --%>
			<ul class="ui-tabs-nav tab_supmenu_slcp" id="tab_menusup_slcp">
				<li class="ui-tabs-selected">
					<a href="javascript:void(0);" class="action {name : 'summary'}">
						<s:text name="web.label.Result_View.Tabs.Summary" />
					</a>
				</li>
				<li class="">
					|
				</li>
				<li class="">
					<a href="javascript:void(0);"
						class="action {name : 'pricePerformance'}"> <s:text
							name="web.label.Result_View.Tabs.PricePerformance" />
					</a>
				</li>
				<li class="">
					|
				</li>
				<li class="">
					<a href="javascript:void(0);"
						class="action {name : 'fundamentals'}"><s:text
							name="web.label.Result_View.Tabs.Fundamentals" />
					</a>
				</li>
				<li class="">
					|
				</li>
				<li class="">
					<a href="javascript:void(0);"
						class="action {name : 'earningsDividends'}"><s:text
							name="web.label.Result_View.Tabs.Earning_Dividends" />
					</a>
				</li>
				<li class="">
					|
				</li>
				<li class="">
					<a href="javascript:void(0);" class="action {name : 'technicals'}"><s:text
							name="web.label.Result_View.Tabs.Technicals" />
					</a>
				</li>
			</ul>

			<div class="clear"></div>

			<%--#########################################BODY CONTENT######################################## --%>
			<div id="tab_sup_5" class="clearfix ui-tabs-container" style="">
				<div id="summary" class="table_company clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl_bdkt">
						<colgroup>
							<col width="5%" />
							<col width="25%" />
							<col width="15%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="5%" />
						</colgroup>
						<thead>
							<tr
								style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
								<td>
									<span class="txtText"> <b><s:text
												name="web.label.Result_View.Symbol" />
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <%
 	if ("vn".equalsIgnoreCase(locale)) {
 %> <a href="javascript:void(0);"
											class="sortBy {sortField : 'COMPANY_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	} else {
 %> <a href="javascript:void(0);"
											class="sortBy {sortField : 'COMPANY_FULL_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	}
 %> </b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'ITEM_NAME'}"><s:text
													name="web.label.Result_View.Industry" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'CLOSE_PRICE'}"><s:text
													name="web.label.Result_View.PriorClose" /> </a> </b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'f1000002'}"><s:text
													name="web.label.Result_View._5daysChange" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'f1000003'}"><s:text
													name="web.label.Result_View._4weeksChange" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'f1000006'}"><s:text
													name="web.label.Result_View._52weeksChange" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b> <a
											href="javascript:void(0);"
											class="sortBy {sortField : 'f51003'}"><s:text
													name="web.label.Result_View.MarketCap" /> </a>
									</b> </span>
								</td>
								<td class="col_end">
                                    Canslim 
                                </td>
							</tr>
						</thead>
						<tbody>
							<!-- MAIN CONTENT GOES HERE -->
						</tbody>
					</table>
					<div class="fpCalendar">
						<%--<div align="right" class="fpCalendar-center" id="summaryNavigator"></div>--%>
					</div>
				</div>
			</div>

			<div id="tab_sup_6" class="clearfix ui-tabs-container" style="">
				<div id="pricePerformance" class="table_company clearfix"
					style="display: none;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl_bdkt">
						<colgroup>
							<col width="5%" />
							<col width="25%" />
							<col width="15%" />
							<col width="15%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
						</colgroup>
						<thead>
							<tr
								style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
								<td>
									<span class="txtText"> <b><s:text
												name="web.label.Result_View.Symbol" />
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b>
											<%
												if ("vn".equalsIgnoreCase(locale)) {
											%> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	} else {
 %> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_FULL_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	}
 %>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000002'}"><s:text
													name="web.label.Result_View.PriceChangeOver5Days" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000003'}"><s:text
													name="web.label.Result_View.PriceChangeOver4Weeks" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000007'}"><s:text
													name="web.label.Result_View.Vs.VnIndex4Weeks" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000008'}"><s:text
													name="web.label.Result_View.Vs.VnIndex13Weeks" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000010'}"><s:text
													name="web.label.Result_View.Vs.VnIndex52Weeks" /> </a>
									</b> </span>
								</td>
								<td class="col_end">
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51007'}"><s:text
													name="web.label.Result_View.Beta" /> </a>
									</b> </span>
								</td>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
					<div class="fpCalendar">
						<%--<div align="right" class="fpCalendar-center" id="pricePerformanceNavigator"></div>--%>
					</div>
				</div>
			</div>

			<div id="tab_sup_7" class="clearfix ui-tabs-container" style="">
				<div id="fundamentals" class="table_company clearfix"
					style="display: none;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl_bdkt">
						<colgroup>
							<col width="5%" />
							<col width="20%" />
							<col width="15%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
						</colgroup>
						<thead>
							<tr
								style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
								<td>
									<span class="txtText"> <b><s:text
												name="web.label.Result_View.Symbol" />
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b>
											<%
												if ("vn".equalsIgnoreCase(locale)) {
											%> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	} else {
 %> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_FULL_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	}
 %>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51009'}"><s:text
													name="web.label.Result_View.pe_ratio" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51011'}"><s:text
													name="web.label.Result_View.price_sale_ratio" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51012'}"><s:text
													name="web.label.Result_View.price_book_ratio" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51010'}"><s:text
													name="web.label.Result_View.PEG" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53004'}"><s:text
													name="web.label.Result_View.profit_margin" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f52002'}"><s:text
													name="web.label.Result_View.return_equity" /> </a>
									</b> </span>
								</td>
								<td class="col_end">
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f52001'}"><s:text
													name="web.label.Result_View.return_asset" /> </a>
									</b> </span>
								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="fpCalendar">
						<%--<div align="right" class="fpCalendar-center" id="fundamentalsNavigator"></div>--%>
					</div>
				</div>
			</div>

			<div id="tab_sup_8" class="clearfix ui-tabs-container" style="">
				<div id="earningsDividends" class="table_company clearfix"
					style="display: none;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl_bdkt">
						<colgroup>
							<col width="5%" />
							<col width="20%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
						</colgroup>
						<thead>
							<tr
								style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
								<td>
									<span class="txtText"> <b><s:text
												name="web.label.Result_View.Symbol" />
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b>
											<%
												if ("vn".equalsIgnoreCase(locale)) {
											%> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	} else {
 %> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_FULL_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	}
 %>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53009'}"><s:text
													name="web.label.Result_View.eps_growth_annual" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53030'}"><s:text
													name="web.label.Result_View.revenue_growth_annual" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53039'}"><s:text
													name="web.label.Result_View.eps_growth_quarterly" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53033'}"><s:text
													name="web.label.Result_View.revenue_growth_quarterly" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f51005'}"><s:text
													name="web.label.Result_View.dividend_yield" /> </a>
									</b> </span>
								</td>
								<td class="col_end">
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f53003'}"><s:text
													name="web.label.Result_View.dividend_growth_5_year" /> </a>
									</b> </span>
								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>

			<div id="tab_sup_9" class="clearfix ui-tabs-container" style="">
				<div id="technicals" class="table_company clearfix"
					style="display: none;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl_bdkt">
						<colgroup>
							<col width="5%" />
							<col width="20%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr
								style="background: url(../images/img/imgbgr_firstline.gif) repeat-x left bottom">
								<td>
									<span class="txtText"> <b><s:text
												name="web.label.Result_View.Symbol" />
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b>
											<%
												if ("vn".equalsIgnoreCase(locale)) {
											%> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	} else {
 %> <a href="javascript:void(0)"
											class="sortBy {sortField : 'COMPANY_FULL_NAME'}"><s:text
													name="web.label.Result_View.CompanyName" /> </a> <%
 	}
 %>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'f1000006'}"><s:text
													name="web.label.Result_View.ChgLast52Weeks" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'vs_sma_13_day'}"><s:text
													name="web.label.Result_View.Vs13DaysSMA" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'vs_sma_50_day'}"><s:text
													name="web.label.Result_View.Vs50DaysSMA" /> </a>
									</b> </span>
								</td>
								<td>
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'PCT_BELOW_52_WEEK_HIGH'}"><s:text
													name="web.label.Result_View.Below52WkHigh" /> </a>
									</b> </span>
								</td>
								<td class="col_end">
									<span class="txtText"> <b><a
											href="javascript:void(0)"
											class="sortBy {sortField : 'PCT_ABOVE_52_WEEK_LOW'}"><s:text
													name="web.label.Result_View.Above52WkLow" /> </a>
									</b> </span>
								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="fpCalendar">
						<%--<div align="right" class="fpCalendar-center" id="technicalsNavigator"></div>--%>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>