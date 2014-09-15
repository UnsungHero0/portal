<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<input type="hidden" id="symbolId" name="symbol"
	value="<s:property value="model.symbol"/>" />
	
<script type="text/javascript">

var currentFloorCode = "";
var exchangeCode = "${model.exchangeCode}";
if(exchangeCode == "HOSTC"){
	currentFloorCode = "10";
} else if(exchangeCode == "HNX"){
    currentFloorCode = "02";
} else if(exchangeCode == "UPCOM"){
    currentFloorCode = "03";
}  

$(document).ready(function(){
	$("#drawChartButtonGroup>input.hsButton").click(function(){
		var buttonElements = $("#drawChartButtonGroup").find("input.hsButton");
		$.each(buttonElements, function(index, item){$("#"+item.id).removeClass("button-active");});
		var buttonClickId = this.id;
		$("#"+buttonClickId).addClass("button-active");
		if(buttonClickId == "drawAll"){
			drawAllHSChartOnSISnapshot();
		} else if(buttonClickId == "draw3mHS"){
			draw3mHSChartOnSISnapshot();
		}
	});
});
</script>

<div class="content_ttpt">
	<!-- nav -->
	<jsp:include
		page="/WEB-INF/jsps/listedmarket/snippet/si-company-nav.jsp"></jsp:include>

	<!--Start All Tab menu  -->
	<div class="content_small">
		<div class="content_doanhnghiep">
			<div class="content_left">
				<!-- statistic and chart -->
				<!-- column statistic 1 -->
				<div class="width255">
					<ul class="list14">
						<li>
							<div class="rowa">
								<s:text name="web.label.weeklow" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strWeekLow" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.weekhigh" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strWeekHigh" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.TenDayAverageVolumn" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strAverageVolumn" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.market.capital" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strMarketCapital" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.ShareOutstanding" />
							</div>
							<div class="rowc">
								<s:property
									value="model.ifoComSnapshotViewExt.strShareOutstanding" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.ShareRelease" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strListedShares" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.DividendYield" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strDividendYield" />
								%
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.NoRightsDate" />
							</div>
							<div class="rowc">
								<s:property
									value="model.ifoComSnapshotViewExt.strExDividendDate" />
							</div>
						</li>
					</ul>
				</div>
				<!-- end column statistic 1 -->

				<!-- column statistic 2 -->
				<div class="width190">
					<ul class="list14">
						<li>
							<div class="rowb">
								<s:text name="web.label.EPS" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strEps" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.AnnualEPS" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strAnnualEPS" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.ROA" />
							</div>
							<div class="rowc">
								<s:property value="model.strRoa" />
								%
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.ROE" />
							</div>
							<div class="rowc">
								<s:property value="model.strRoe" />
								%
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.quotes.cellnames.leverage" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strLeverage" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.PE" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strPe" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.PriceToBook" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strPB" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text
									name="web.label.SnapshotAction.form.Quotes.CellNames.Beta" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strBeta" />
							</div>
						</li>
					</ul>
				</div>
				<!-- end column statistic 2 -->
				<!-- chart -->
				<div class="bieudokythuat">
					<div id="hsChartOnSnapshot">
						<img src="<web:url value='/images/ajax-loader.gif'/>" />
					</div>
					<div style="float: left; margin-left: 10px; display: inline-block;" id="drawChartButtonGroup">
						<a style="float: left;"
							href="<web:url value="/bieu-do-ky-thuat/${fn:toLowerCase(symbol)}.shtml"/>">
							<input class="iButton" type="button"
							style="width: 245px;"
							value='<s:text name="web.label.SnapshotAction.form.chart.title" />' />
						</a> <input
							style="position: absolute; right: 5px; top: 0px; z-index: 1; padding: 1px;"
							class="hsButton" type="button" value="all" id="drawAll"/> 
							<input id="draw3mHS"
							style="position: absolute; right: 30px; top: 0px; z-index: 1; padding: 1px;"
							class="hsButton button-active" type="button" value="3m"/>
					</div>
				</div>
				<!-- end chart -->

				<div class="clear"></div>
				<div class="bd" style="margin-top: 15px;">
					<div class="tabs_hotnews" id="container-2">
						<ul id="tab_menusup_doanhnghiep_tintuc"
							class="titleBar ui-tabs-nav sup_tab_class3 bgTitleBar">
							<li class=""><a href="#fragment-5"><s:text
										name="web.label.SnapshotAction.form.LatestHeadlines.tab.LatestHeadlines">Tin cập nhật</s:text>
							</a></li>
							<li>|</li>
							<li><a class="" href="#fragment-6"><s:text
										name="web.label.SnapshotAction.form.LatestHeadlines.tab.PublicInfo">Tin CBTT</s:text>
							</a></li>
							<li>|</li>
							<li><a href="#fragment-7"><s:text
										name="web.label.SnapshotAction.form.LatestHeadlines.tab.CompanysEvents">Tin liên quan</s:text>
							</a></li>
						</ul>

						<!--tab1-->
						<div class="ctTab_hotnews" id="fragment-5">
							<div class="ds_news" id="divLastestHeadlines">
								<img src="<web:url value='/images/ajax-loader.gif'/>" />
							</div>
							<div id="web_navLastestHeadlines"></div>
						</div>
						<!--tab2-->
						<div class="ctTab_hotnews" id="fragment-6">
							<div class="ds_news" id="divPublicInfo">
								<img src="<web:url value='/images/ajax-loader.gif'/>" />
							</div>
							<div id="web_navPublicinfo"></div>
						</div>
						<!--tab2-->
						<div class="ctTab_hotnews" id="fragment-7">
							<div class="ds_news" id="divCompanysEvents">
								<img src="<web:url value='/images/ajax-loader.gif'/>" />
							</div>
							<div id="web_navCompanysevents"></div>
						</div>
						<!--end tabs-->
					</div>
					<form name="fSnapshot_LatestHeadlines"
						id="fSnapshot_LatestHeadlines" method="post">
						<input type="hidden" id="LatestHeadlines_pagingInfo_indexPage"
							name="pagingInfo.indexPage" value="1" />
					</form>
				</div>
				<!-- End news content -->
			</div>
			<!-- End left content -->

			<!-- right content -->
			<div id="c-column" class="width240 display-inline">
			    <!-- cptd -->
			    <%-- <a href="<web:url value='/co-phieu-tam-diem.shtml' />" style="margin-bottom:10px;display:block;">
                    <img src="<web:url value='/images/thumbs/cptdbanner.png' />" />
			    </a> --%>
			     
			     <div class="clear"></div>
				<%-- realtime quote hidden--%>
				<div class="realtimeSymbolPrice" style="display: none;">
					<div class="realtime_line">
						<span id="symbol_pri"></span>
						<div class="realtime_amnt_and_change">
							<p style="min-height: 20px;">
								<span id="stock-state-flag" class="print left"></span> 
								<span id="symbol_per"></span>
							</p>
							<p>
								<span style="display: block; width: 50%; float: left;"
									class="blurtext">Tổng KLGD:</span><span id="symbol_vol"></span>
							</p>
						</div>
					</div>
					<div class="realtime_line">
						<div id="higP">
							<p class="blurtext">Cao nhất</p>
							<span id="symbol_higP"></span>
						</div>
						<div id="avgP">
							<p class="blurtext">Trung bình</p>
							<span id="symbol_avgP"></span>
						</div>
						<div id="lowP">
							<p class="blurtext">Thấp nhất</p>
							<span id="symbol_lowP"></span>
						</div>
					</div>
					<div class="realtime_line"
						style="border-bottom: 1px dotted #DCDCDC; padding-bottom: 10px;">
						<div class="left" style="width: 50%;">
							<span class="blurtext">Dư mua:</span> <span id="symbol_bV4"></span>
						</div>
						<div class="left" style="width: 50%;">
							<span class="blurtext">Dư bán:</span> <span id="symbol_oV4"></span>
						</div>
					</div>
					<div class="realtime_line">
						<span class="blurtext">Trạng thái:</span> <span id="marketState"></span>
					</div>
					<div class="realtime_line">
						<span class="blurtext">Thời điểm cập nhật:</span> <span
							id="marketCheckedTime"></span>
					</div>
					<div class="clear"></div>
				</div>
				<!-- Cung nghanh -->
				<form name="fSnapshot_RelatedCompany" id="fSnapshot_RelatedCompany"
					method="post">
					<input type="hidden" id="RelatedCompany_pagingInfo_indexPage"
						name="pagingInfo.indexPage" value="1" />
					<div class="display-inline pn_main" id="ctn-general">
						<ul class="hd">
							<li class="hd-center">
								<h5>
									<s:text
										name="web.label.SnapshotAction.form.RelatedCompany.title" />
								</h5>
							</li>
						</ul>

						<div class="bd">
							<div class="table_Market clearfix min-width240"
								id="Listed_RelatedCompanyForSnapshot_divResult">
								<table cellpadding="0" cellspacing="0" border="0" width="100%"
									class="nomal" id="Listed_RelatedCompanyForSnapshot_tableResult">
									<colgroup>
										<col width="20%" />
										<col width="15%" />
										<col width="35%" />
										<col width="30%" />
									</colgroup>
									<thead>
										<tr bgcolor="#eaedf0">
											<td class="company_header_left">&nbsp; <s:text
													name="web.label.symbol" />
											</td>
											<td class="company_header_center" align="center"><s:text
													name="web.label.SnapshotAction.form.RelatedCompany.Last" />
											</td>
											<td class="company_header_center" align="center"><s:text
													name="web.label.SnapshotAction.form.RelatedCompany.TodayChange" />
											</td>
											<td class="company_header_right" align="center"><s:text
													name="web.label.SnapshotAction.form.RelatedCompany.MarketCap" />
												&nbsp;</td>
										</tr>
									</thead>
									<tbody>
										<tr bgcolor="">
											<td><img
												src="<web:url value='/images/ajax-loader.gif'/>" /></td>
										</tr>
									</tbody>
								</table>
								<div id="web_navRelatedCompanies"></div>
							</div>
						</div>
					</div>
				</form>
				<!-- End cung nghanh -->
				<!-- Chi so pwr -->
				<div class="box_chiso_PR">
					<div class="title">
						<a style="color: #f39200;"
							href="<web:url value='y-tuong-dau-tu/power-ratings.shtml' />"><s:text
								name="analysis.stockInfo.company.snapshot.pwr">Chí số Power Rating</s:text>
						</a>
					</div>
					<ul id="index">
						<li><s:text name="analysis.stockInfo.company.snapshot.point">Điềm Pwr</s:text>
							: <span class="orange" id="todayPwr"></span></li>
						<li><s:text
								name="analysis.stockInfo.company.snapshot.pointLast4">Pwr 4 hôm trước</s:text>
							: <span class="orange" id="lastFourPwr"></span></li>
					</ul>
					<span id="message"></span>
				</div>
				<!-- End Chi so pwr -->
			</div>
			<!-- End right content -->

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

			<div id="noResultTitlePre" style="display: none;">
				<s:text name="web.powerating.message.pre.norecord" />
			</div>
			<div id="noResultTitlePost" style="display: none;">
				<s:text name="web.powerating.message.post.norecord" />
			</div>
		</div>
	</div>
</div>