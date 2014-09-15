<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.StringTokenizer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>
<jsp:directive.page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"/>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%
	boolean isHatcIndex = VNDirectWebUtilities.isHASTCIndex((HttpServletRequest)pageContext.getRequest());
	boolean isHotcIndex = VNDirectWebUtilities.isHOSTCIndex((HttpServletRequest)pageContext.getRequest());
%>

<script>
var mmTA = new TMainMenu('mmTA','horizontal');
var pmTA1000 = new TPopMenu('<b>Technical_Indicators</b>','0','','','Technical Indicators');
	var pmTA1100 = new TPopMenu("Simple Moving Average","../../../hmenu/icons/checkbox-0.gif","f","javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartSMAIndicator', 'imgChartSMAIndicatorId')","Simple Moving Average", "imgChartSMAIndicatorId");
	var pmTA1200 = new TPopMenu('Exponential Moving Average (EMA)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartEMAIndicator', 'imgChartEMAIndicatorId')",'Exponential Moving Average (EMA)', 'imgChartEMAIndicatorId');
	var pmTA1300 = new TPopMenu('Bollinger Bands (BBand)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartBBandsIndicator', 'imgChartBBandsIndicatorId')",'Bollinger Bands (BBand)', 'imgChartBBandsIndicatorId');
	var pmTA1400 = new TPopMenu('Money Flow Index (MFI)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartMFIIndicator', 'imgChartMFIIndicatorId')",'Money Flow Index (MFI)','imgChartMFIIndicatorId');
	var pmTA1500 = new TPopMenu('MACD','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartMACDIndicator', 'imgChartMACDIndicatorId')",'MACD','imgChartMACDIndicatorId');
	var pmTA1600 = new TPopMenu('Parabolic SAR (PSAR)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartPSARIndicator', 'imgChartPSARIndicatorId')",'Parabolic SAR (PSAR)','imgChartPSARIndicatorId');
	var pmTA1700 = new TPopMenu('Rate of exchange (ROC)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartROCIndicator', 'imgChartROCIndicatorId')",'Rate of exchange (ROC)','imgChartROCIndicatorId');
	var pmTA1800 = new TPopMenu('Relative Streng Index (RSI)','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartRSIIndicator', 'imgChartRSIIndicatorId')",'Relative Streng Index (RSI)','imgChartRSIIndicatorId');
	var pmTA1900 = new TPopMenu('Slow Stochastic','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartSlowStochastricIndicator', 'imgChartSlowStochastricIndicatorId')",'Slow Stochastic','imgChartSlowStochastricIndicatorId');
	var pmTA1A00 = new TPopMenu('Fast Stochastic','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartFastStochastricIndicator', 'imgChartFastStochastricIndicatorId')",'Fast Stochastic','imgChartFastStochastricIndicatorId');
	var pmTA1B00 = new TPopMenu('Volume','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartVolumeIndicator', 'imgChartVolumeIndicatorId')",'Volume','imgChartVolumeIndicatorId');
	var pmTA1C00 = new TPopMenu('Volume + MA','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartVolumeMAIndicator', 'imgChartVolumeMAIndicatorId')",'Volume + MA','imgChartVolumeMAIndicatorId');
	var pmTA1D00 = new TPopMenu('William %R','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=selectIndicator(this, INDICATOR_ACTION, 'ChartWilliamsRIndicator', 'imgChartWilliamsRIndicatorId')",'William %R','imgChartWilliamsRIndicatorId');

var pmTA2000 = new TPopMenu('<b>Chart Setting</b>','0','','','Chart Setting');
 var pmTA2100 = new TPopMenu('Line Type','','','','Line Type');
	var pmTA2110 = new TPopMenu('OHLC','../../../hmenu/icons/radio-1.gif','f',"javascript:retval=doChangeLineTypeChart(LINE_TYPE_ACTION, 'imgMainChartId', 'OHLC')",'OHLC','imgOHLCId');
	var pmTA2120 = new TPopMenu('Candlestick','../../../hmenu/icons/radio-0.gif','f',"javascript:retval=doChangeLineTypeChart(LINE_TYPE_ACTION, 'imgMainChartId', 'CAND')",'Candlestick','imgCANDId');
	var pmTA2130 = new TPopMenu('Line','../../../hmenu/icons/radio-0.gif','f',"javascript:retval=doChangeLineTypeChart(LINE_TYPE_ACTION, 'imgMainChartId', 'LINE')",'Line','imgLINEId');
 var pmTA2200 = new TPopMenu('Chart Scale','','','','Chart Scale');
	var pmTA2210 = new TPopMenu('Logarithmic','../../../hmenu/icons/radio-0.gif','f',"javascript:retval=doChangeScaleChart(CHART_SCALE_ACTION, 'imgMainChartId', 'LOG')",'Logarithmic','imgLOGId');
	var pmTA2220 = new TPopMenu('Linear','../../../hmenu/icons/radio-0.gif','f',"javascript:retval=doChangeScaleChart(CHART_SCALE_ACTION, 'imgMainChartId', 'LINEAR')",'Linear','imgLINEARId');

var pmTA3000 = new TPopMenu('<b>Compare</b>','0','','','Compare');
 var pmTA3100 = new TPopMenu('Index','','','','Index');
	<% if(!isHotcIndex) {%>
		var pmTA3110 = new TPopMenu('VNIndex','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=doCompareIndex('imgMainChartId', 'imgVNIndexId', 'VNIndex', 'HOSTC');",'VNIndex','imgVNIndexId');
	<% } %>
	<% if(!isHatcIndex) {%>
		var pmTA3120 = new TPopMenu('HNX Index','../../../hmenu/icons/checkbox-0.gif','f',"javascript:retval=doCompareIndex('imgMainChartId', 'imgHASTCId', 'HNX', 'HNX');",'HNX Index','imgHASTCId');
	<% } %>

 var pmTA3200 = new TPopMenu('Add Symbol(s)','','f',"javascript:retval=doAddRemoveSymbols('ADD_SYMBOL');",'Add Symbol(s)');
 var pmTA3300 = new TPopMenu('Remove Symbol(s)','','f',"javascript:retval=doAddRemoveSymbols('REMOVE_SYMBOL');",'Remove Symbol(s)');

//+++ Add items to menu
mmTA.Add(pmTA1000);
 pmTA1000.Add(pmTA1100);
 pmTA1000.Add(pmTA1200);
 pmTA1000.Add(pmTA1300);
 pmTA1000.Add(pmTA1400);
 pmTA1000.Add(pmTA1500);
 pmTA1000.Add(pmTA1600);
 pmTA1000.Add(pmTA1700);
 pmTA1000.Add(pmTA1800);
 pmTA1000.Add(pmTA1900);
 pmTA1000.Add(pmTA1A00);
 pmTA1000.Add(pmTA1B00);
 pmTA1000.Add(pmTA1C00);
 pmTA1000.Add(pmTA1D00);

mmTA.Add(pmTA2000);
 pmTA2000.Add(pmTA2100);
	pmTA2100.Add(pmTA2110);
	pmTA2100.Add(pmTA2120);
	pmTA2100.Add(pmTA2130);
 pmTA2000.Add(pmTA2200);
	pmTA2200.Add(pmTA2210);
	pmTA2200.Add(pmTA2220);

mmTA.Add(pmTA3000);
 pmTA3000.Add(pmTA3100);
	<% if(!isHotcIndex) {%>
		pmTA3100.Add(pmTA3110);
	<% } %>
	<% if(!isHatcIndex) {%>
		pmTA3100.Add(pmTA3120);
	<% } %>
 pmTA3000.Add(pmTA3200);
 pmTA3000.Add(pmTA3300);

//--- Add items to menu

//+++ Set Menu's configuration: xp style
mmTA.SetCorrection(0,0);
mmTA.SetCellSpacing(0);

mmTA.SetExpandIcon(true,'','6');
//mmTA.SetType('free');
mmTA.SetType('float');


//uncomment these lines to see the effect
//mmTA._header.SetBackground('red','','','');
//mmTA._header.SetFont('arial','10pt');
//mmTA._header.SetText('white','right','bold','','');
//mmTA.SetHeaderText('Click Here to get the idea');

//pmHist00.SetType('h');
//pmHome00.SetType('header');
//mmTA._pop._header.SetBackground('red','','','');
//mmTA._pop._header.SetFont('arial','10pt');
//mmTA._pop._header.SetText('white','right','bold','','');

//mmTA.SetBackground('whitesmoke','img/xp.gif','repeat-y','top left');
mmTA.SetBackground('','../images/layouts/tab-content-under.jpg','repeat-y','top left');
mmTA.SetItemText('','center','','','');
mmTA.SetItemTextHL('','center','','','');
mmTA.SetItemTextClick('','center','','','');

// mmTA.SetItemDimension(100,20);
mmTA.SetItemDimension(120,15);
mmTA.SetShadow(false,'#B0B0B0',6);
mmTA.SetBorder(0,'gray','solid');
mmTA.SetItemBorder(0,'gray','solid');

mmTA._pop.SetCorrection(0,0);
mmTA._pop.SetAlpha(90);
mmTA._pop.SetItemDimension(150,20);
mmTA._pop.SetPaddings(1);
mmTA._pop.SetBackground('whitesmoke','img/xp.gif','repeat-y','top left');
mmTA._pop.SetSeparator(125,'right','gray','');
mmTA._pop.SetExpandIcon(true,'',6);
mmTA._pop.SetFont('tahoma,verdana,arial','8pt');
mmTA._pop.SetBorder(1,'gray','solid');
mmTA._pop.SetShadow(true,'#B0B0B0',6);
mmTA._pop.SetDelay(500);
//--- Set Menu's configuration


var isDebug = false;

var listIndicatorId = new Array("imgChartSMAIndicatorId"
								, "imgChartEMAIndicatorId"
								, "imgChartBBandsIndicatorId"
								, "imgChartMFIIndicatorId"
								, "imgChartMACDIndicatorId"
								, "imgChartPSARIndicatorId"
								, "imgChartROCIndicatorId"
								, "imgChartRSIIndicatorId"
								, "imgChartSlowStochastricIndicatorId"
								, "imgChartFastStochastricIndicatorId"
								, "imgChartVolumeIndicatorId"
								, "imgChartVolumeMAIndicatorId"
								, "imgChartWilliamsRIndicatorId");

var listCompetitorId = new Array("imgVNIndexId"
								,"imgHASTCId");

var listLineType = new Array("imgOHLCId", "imgCANDId", "imgLINEId");
var listCharScale = new Array("imgLOGId", "imgLINEARId");

/* Preload image */
var checkbox0 = new Image();
checkbox0.src="../../../hmenu/icons/checkbox-0.gif";
var checkbox1 = new Image();
checkbox1.src="../../../hmenu/icons/checkbox-1.gif";

var radio0 = new Image();
radio0.src="../../../hmenu/icons/radio-0.gif";
var radio1 = new Image();
radio1.src="../../../hmenu/icons/radio-1.gif";

/* Define user action */
var INDICATOR_ACTION = "INDICATOR";
var CHART_SCALE_ACTION = "CHART_SCALE";
var LINE_TYPE_ACTION = "LINE_TYPE";
var CHANGE_DATE_ACTION = "CHANGE_DATE";
var COMPETITOR_ACTION = "COMPETITOR";
</script>
<div id="page_TTNY" class="pn_main">
	<div class="tabs_TTNY" >
		<div style="clear:both"></div>
		<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
			<li class="ui-tabs-selected"><a href="#fragment-1"><b><s:text name="leftmenu.label.analysisTool.Technical.Analysis"></s:text></b></a></li>
			<li><a href="<web:url value="/analysis/historical-price.shtml"/>"><b><s:text name="web.label.ListedMarket.Tab.MarketStatistics">Market Statistics</s:text></b></a></li>
		</ul>
		<!--tabs 1-->
		<div class="ctTab_TTNY" id="fragment-1" align="left">
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>
			<div class="center_inner clearfix">
				<div class="clearfix">
					<div style="height:10px;"></div>
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td> <span class="cl_blue" style="text-transform:uppercase; font-weight:bold;"><s:text name="web.label.FlashChartAction.enterSymbol"/>:</span></td>
							<td style="padding:0px 5px;">
								<input id="symbol4ChartId" name="symbol" value="<c:out value="${symbol}" />" />
							</td>
							<td>
								<span  class="btn_left_inbox">
									<span class="btn_right_inbox">
										<span class="btn_center_inbox">
											<input type="button"  onclick="doChangeSymbol()" value="<s:text name="web.label.FlashChartAction.drawChart"/>" style="text-transform:uppercase;" />
										</span>
									</span>
								</span>
							</td>
						</tr>
					</table>
					<table style="width: 100%; margin: 0 auto; border: 0px;" align="center" cellspacing="0" cellpadding="0">
					 <tr style="height: 1em" >
						<td style="height: 1em; width: 1px;" align="left">
							<span id="displayMenuId">&nbsp;</span>
						</td>
						<td style="height: 1em" align="left">
							<script language="JavaScript" type="text/JavaScript">
								var posObj = document.getElementById("displayMenuId");
								var dLeft = getPosLeft(posObj) + 5;
								var dTop = 207 + 7;
								// dTop = (dTop > 5 ? dTop - 5 : dTop);

								// mmTA.SetPosition('absolute',dLeft,dTop);
								mmTA.SetPosition('relative',dLeft,dTop);

								mmTA.Build();

								Initialize();

								// alert("dLeft:" + dLeft + ", dTop:" + dTop);
							</script>
						</td>
						<td align="right">
						</td>
					 </tr>
					</table>
					<div style="height:10px;"></div>
					<web:quotesCompanySnapshot chart="true"/>
					<div style="height:10px;"></div>
					<div id="divBasicChart">
						<img id="imgMainChartId" alt="Basic Chart" src="<s:url value='/images/chart/default.ta.chart.jpg'/>">
						<div id='progress_indicator' style="position:absolute;display:none;top:250px;left:450px;z-index:1000;"><img src="<s:url value='/images/icons/progress.gif'/>" /></div>
					<script type="text/javascript">
						var intPriorDay = 0;
						var intPriorMonth = 0;
						var intPriorYear = -1;


						doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', intPriorDay, intPriorMonth, intPriorYear);
					</script>
					</div>

					<div style="text-align: right; padding: 5px 5px 5px 5px;">
						<span> <a id="navChart-500Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION, 'imgMainChartId', -5, 0, 0);" ><s:text name="web.label.fivedays"></s:text></a></span>
						<span> | <a id="navChart0-10Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, -1, 0);" ><s:text name="web.label.onemonth"></s:text></a></span>
						<span> | <a id="navChart0-30Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, -3, 0);" ><s:text name="web.label.threemonths"></s:text></a></span>
						<span> | <a id="navChart00-1Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, 0, -1);" ><s:text name="web.label.oneyear"></s:text></a></span>
						<span> | <a id="navChart00-2Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, 0, -2);" ><s:text name="web.label.twoyears"></s:text></a></span>
						<span> | <a id="navChart00-5Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, 0, -5);" ><s:text name="web.label.fiveyears"></s:text></a></span>
						<span> | <a id="navChart00-50Id" href="javascript:doChangeDateChart(CHANGE_DATE_ACTION,'imgMainChartId', 0, 0, -50);" ><s:text name="web.label.max"></s:text> </a></span>
					</div>

					<p class="cl_red" style="margin-top:20px;"><s:text name="web.label.FlashChartAction.ChartDescription"/></p>
				</div>
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>

		<form id="drawChartForm" action="<web:url value='/analysis/BasicChart.shtml'/>" method="Post">
			<input type="hidden" name="symbol" id="submitSymbol"/>
			<script>
				//var URL_FLASH_CHART_AJAX = "<web:url value='/ajax/analysis/RefreshFlashChartData.shtml'/>";
				function doChangeSymbol() {
					$('#submitSymbol').val($('#symbol4ChartId').val());
					$('#drawChartForm').submit();
				}
				$().ready(function() {
					$("#dialog").dialog( {
						height : 450,
						width : 800,
						modal : true,
						autoOpen: false
						//,close: function(event, ui) { alert('please reload search customer form') }
					});
				});

			</script>
		</form>
	<div id="dialog" title="Add symbol">
	</div>


	</div>
</div>

