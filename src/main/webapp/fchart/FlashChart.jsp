<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.vndirect.com.vn/online-brokerage/utility" prefix="vndirectUtil" %>
<%@page import="java.util.StringTokenizer"%>

<script src="AC_OETags.js" language="javascript"></script>

<script type="text/javascript">
addLoadEvent(function () {
    try {
		var oTextbox = new AutoSuggestControl(document.getElementById("symbol4ChartId"), new SuggestionProvider());    
		oTextbox.enterTrigger = function() {
			try {
				addSymbol(); 
				doPortletCompanySnapshot();
			} catch (e) {}
		};
	} catch (e) {
		alert(e);
	}
  }
);
</script>

<script language="JavaScript" type="text/javascript">
<!--
addLoadEvent(function () {
	<%
		String isReload = request.getParameter("reLoad");
		if("true".equalsIgnoreCase(isReload)) {
	%>
		goToUri(FLASH_CHART_URL+"?reLoad=false");
	<%}%>
	doPortletCompanySnapshot();
});

function doPortletCompanySnapshot() {
	try {
		var objCompanySnapshotHelper = new CompanySnapshotHelper();
		var oDivProgressImage = null;
		var symbol = document.getElementById('symbol4ChartId').value;
		var objPortletCompanySnapshotControl = new VNDSPortletControl(oDivProgressImage, _funcShowCompanySnapshot, new VNDSPortletProvider());
		objPortletCompanySnapshotControl.url = objCompanySnapshotHelper.URL;
		objPortletCompanySnapshotControl.addParam("symbol", symbol);
		objPortletCompanySnapshotControl.refresh();
	} catch(e){
		alert(e);
	}
}

function _funcShowCompanySnapshot(oVNDSPortletBean) {
	var divShowSnapshot = document.getElementById("td.companySnapshot.id");
	var divShowCompanyInfo = document.getElementById("div.quotesCompanyInfo.id");
	var strContent = oVNDSPortletBean.get("snapshotContent");
	var strCompayInfo = oVNDSPortletBean.get("compayInfoContent");
	divShowCompanyInfo.innerHTML = strCompayInfo;
	
	//view index info
	var divShowIndexInfo = document.getElementById("Index.Info.id");
	var divShowQuickLinks = document.getElementById("td.quickLinks.id");
	var divShowChartDes = document.getElementById("td.ChartDes.id");
	var isShowHoHaInfo = oVNDSPortletBean.get("showHO_HAInfoContent");
	if(trim(isShowHoHaInfo) == 'true') {
		divShowIndexInfo.style.display='block';
		divShowSnapshot.innerHTML = "";
		divShowQuickLinks.style.display="none";
		divShowChartDes.style.display='block';
	} else {
		divShowIndexInfo.style.display='none';
		divShowSnapshot.innerHTML = strContent;
		divShowQuickLinks.style.display="block";
		divShowChartDes.style.display='none';
	}
}
// -----------------------------------------------------------------------------
// -----------------------------------------------------------------------------
// Globals
// Major version of Flash required
var requiredMajorVersion = 9;
// Minor version of Flash required
var requiredMinorVersion = 0;
// Minor version of Flash required
var requiredRevision = 28;
//var symbol ="acb";
var symbol="";
//var range = "15/01/2000,15/01/2008";
var range = "";
//var compare = "ree+htv+vtv+bbc";
var compare = "";
//var indicator = "SMA(50,20,10)+EMA(50,30,10)+BBANDS(12,2)+MFI(10)+VOLMA(5)+WR(10)";
var indicator = "";
//var charttype = "L";
var charttype ="";
//var crosshair = "TRB";
var crosshair = "";
//var logscale = "LI"; 
var logscale = "";
var link ="";
var flashHeight = 380;//380
var flashWidth = 770;//770
// -----------------------------------------------------------------------------
// -->
</script>
				
<!-- CONTENT 1 -->

<!-- Company Information -->
<div id="div.quotesCompanyInfo.id"></div>

<!-- Begin Content -->
<table class="menu" cellpadding="0px" cellspacing="0px">
	<tr>
		<td style="width:2px;" class="bottom">&nbsp;</td>
		<td>
		<c:choose>
			<c:when test="${vndirectUtil:isHASTCIndex()}">
				<table class="nomal" cellpadding="0px" cellspacing="0px">
					<tr>
						<td style="width: 25px;"><img src="../../../portal/images/layouts/tab-content-01.1.jpg" style="border: 0px none ;"></td>
						<td background="../../../portal/images/layouts/tab-content-05.1.jpg" nowrap="nowrap"><span style="font-weight: bold;">Charts</span></td>					
						<td style="width: 38px;"><img src="../../../portal/images/layouts/tab-content-02.jpg" style="border: 0px none ;"></td>
						<td background="../../../portal/images/layouts/tab-content-05.2.jpg" nowrap="nowrap"><a href="<vndirect:uriRewrite uri='../research/HistoricalPriceForSymbolY.do'/>">Market Statistic</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-04.1.jpg" style="border:0px;"/></td>
						<td style="width:98%;border-bottom-style:solid;border-width:1px;border-color:#B5B6C8;">&nbsp;</td>
					</tr>
				</table>			
			</c:when>
			<c:when test="${vndirectUtil:isHOSTCIndex()}">
				<table class="nomal" cellpadding="0px" cellspacing="0px">
					<tr>
						<td style="width: 25px;"><img src="../../../portal/images/layouts/tab-content-01.1.jpg" style="border: 0px none ;"></td>
						<td background="../../../portal/images/layouts/tab-content-05.1.jpg" nowrap="nowrap"><span style="font-weight: bold;">Charts</span></td>					
						<td style="width: 38px;"><img src="../../../portal/images/layouts/tab-content-02.jpg" style="border: 0px none ;"></td>
						<td background="../../../portal/images/layouts/tab-content-05.2.jpg" nowrap="nowrap"><a href="<vndirect:uriRewrite uri='../research/HistoricalPriceForSymbolY.do'/>">Market Statistic</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-04.1.jpg" style="border:0px;"/></td>
						<td style="width:98%;border-bottom-style:solid;border-width:1px;border-color:#B5B6C8;">&nbsp;</td>
					</tr>
				</table>			
			</c:when>
			<c:otherwise>
				<table class="menu" cellpadding="0px" cellspacing="0px">
					<tr>
						<td style="width:25px;"><img src="../../../portal/images/layouts/tab-content-01.2.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.2.jpg"><a href="<vndirect:uriRewrite uri='../research/Snapshot.do'/>">Company</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-03.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.2.jpg"><a href="<vndirect:uriRewrite uri='../research/MaijorHolders.do'/>">Ownership</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-03.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.2.jpg"><a href="<vndirect:uriRewrite uri='../research/NewsForSymbol.do'/>">NewsInfo</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-02.1.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.1.jpg"><a href="<vndirect:uriRewrite uri='FlashChart.do'/>"><span style="font-weight:bold;">Charts</span></a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-02.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.2.jpg"><a href="<vndirect:uriRewrite uri='../research/BalanceSheet.do'/>">Financial</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-03.jpg" style="border:0px;"/></td>
						<td  nowrap="nowrap"   background="../../../portal/images/layouts/tab-content-05.2.jpg"><a href="<vndirect:uriRewrite uri='../research/HistoricalPriceForSymbol.do'/>">Market Statistic</a></td>
						<td style="width:38px;"><img src="../../../portal/images/layouts/tab-content-04.1.jpg" style="border:0px;"/></td>
						<td style="width:98%;border-bottom-style:solid;border-width:1px;border-color:#B5B6C8;">&nbsp;</td>
					</tr>
				</table>			
				</c:otherwise>
				</c:choose>			
			</td>
			<td class="bottom" style="WIDTH: 2px">&nbsp;</td>
		</tr>
		
		<tr>
		
		<td style="width: 2px;" class="left" background="../../../portal/images/layouts/tab-content-under.jpg">&nbsp;</td>
		<td style="height: 29px;" background="../../../portal/images/layouts/tab-content-under.jpg">
		
		<table class="nomal" cellpadding="0" cellspacing="0">
				<tbody><tr>
					<td style="width: 10px;">
					</td><td><img src="../../../portal/images/buttons/top_button_01.gif" border="0"></td>
					<td background="../../../portal/images/buttons/top_button_03.gif" nowrap="nowrap"><span style="font-weight: bold;"><a href=""><span style="font-weight: bold;">Interactive</span></a></span></td>

					<td><img src="../../../portal/images/buttons/top_button_02.gif" border="0"></td>
					
					<td style="width: 10px;">
					</td><td><img src="../../../portal/images/buttons/top_button_01.gif" border="0"></td>
					<td background="../../../portal/images/buttons/top_button_03.gif" nowrap="nowrap"><a href="<vndirect:uriRewrite uri='../research/BasicChartForSymbol.do'/>">FlashChartOldVersion</a></td>
					<td><img src="../../../portal/images/buttons/top_button_02.gif" border="0"></td>
					
				</tr>
			</tbody></table>	
		</td>

		<td style="width:2px;" background="../../../portal/images/layouts/tab-content-under.jpg" class="right">&nbsp;</td>
		
		</tr>
		<tr>
			<td class="left" style="WIDTH: 2px; BORDER-BOTTOM-STYLE: solid">&nbsp;</td>
			<td class="bottom" style="VERTICAL-ALIGN: top;">
			<!-- ############### BEGIN: MAIN CONTENT ##################### -->
			<div class="empty">&nbsp;</div>
			
			<table class="normal" style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; WIDTH: 100%;" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table height="45">
							<tr>
								<td align="right" style="color:#033597; font-weight: bold;">
									InputSymbol
								</td>
								<td>
									<input name="symbol4Chart" type="text" id="symbol4ChartId" />
								</td>
								<td align="left">
									<table class="btn3" border="0" cellspacing="0" cellpadding="0">
				                         <tr>
				                           <td><img src="../../../portal/images/buttons/btn3_l.gif" width="5" height="19" /></td>
				                           <td class="mid">
				                           		<a href="javascript:addSymbol(); doPortletCompanySnapshot();">
													ChangeSymbol
												</a>
				                           </td>
				                           <td><img src="../../../portal/images/buttons/btn3_r.gif" width="5" height="19" /></td>
				                         </tr>
			                       </table>
								</td>
							</tr>
						</table>
						<div id="Index.Info.id"><br/></div>
<script language="JavaScript" type="text/javascript">
	
	// Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
var hasProductInstall = DetectFlashVer(6, 0, 65);

// Version check based upon the values defined in globals
var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);

if ( hasProductInstall && !hasRequestedVersion ) {
	// DO NOT MODIFY THE FOLLOWING FOUR LINES
	// Location visited after installation is complete if installation is required
	var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
	var MMredirectURL = window.location;
    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
    var MMdoctitle = document.title;

	AC_FL_RunContent(
		"src", "playerProductInstall",
		"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
		"width", flashWidth,
		"height", flashHeight,
		"align", "middle",
		"id", "flashchart",
		"quality", "high",
		"bgcolor", "#869ca7",
		"name", "flashchart",
		"allowScriptAccess","sameDomain",
		"type", "application/x-shockwave-flash",
		"wmode","transparent",
		"pluginspage", "https://www.adobe.com/go/getflashplayer"
	);
} else if (hasRequestedVersion) {
	// if we've detected an acceptable version
	// embed the Flash Content SWF when all tests are passed
	AC_FL_RunContent(
			"src", "flashchart",
			"width", flashWidth,
			"height", flashHeight,
			"align", "middle",
			"id", "flashchart",
			"quality", "high",
			"bgcolor", "#869ca7",
			"name", "flashchart",
			"allowScriptAccess","sameDomain",
			"type", "application/x-shockwave-flash",
			"wmode","transparent",
			"pluginspage", "https://www.adobe.com/go/getflashplayer"
	);
  } else {  // flash is too old or we can't detect the plugin
    /*
    var alternateContent = 'Alternate HTML content should be placed here. '
  	+ 'This content requires the Adobe Flash Player. '
   	+ '<a href=https://www.adobe.com/go/getflash/>Get Flash</a>';
   	*/
   	
   	document.write('<vndirect:message key="/Messages/Quotes/Lables/FlashChart/FlashPlayerNotice"/>' + "<a href='<vndirect:uriRewrite uri='../research/BasicChartForSymbol.do'/>' class='blueUnderline'>click here.</a>");  // insert non-flash content
  }
  		
  function CallFlex()
	{
		//alert("addSymbol");
		getFlexApp('flashchart').CallFlex(symbol,range,compare,indicator,charttype,crosshair,logscale);
	} 
  function getFlexApp(appName) {
	if (navigator.appName.indexOf ("Microsoft") !=-1) {
		return window[appName];
		} else {
		return document[appName];
		}
	}
  function addSymbol()
	{
		symbol  = document.getElementById('symbol4ChartId').value;
		
		//<!-- flashchart la ten cua ung dung flex -->
		getFlexApp('flashchart').addSymbol(symbol);
		//refresh quickSearchSymbolId
		try {
			document.getElementById("quickSearchSymbolId").value = symbol.toUpperCase();
		} catch (e) {}
	}
  
  /* this function is used to display flashchart with parameters on email*/
  //window.onload = SetValiable("acb","15/01/2000,18/01/2008","ree+VnIndex","SMA(50,20,10)+EMA(50,30,10)+BBANDS(12,2)+MFI(10)+VOLMA(5)+WR(10)","L","TRB","LO");
   
  // This function is used for setting parameters when creating a flashchart
  function SetValiable(_symbol,_range,_compare,_indicator,_charttype,_crosshair,_logscale)
  {
  		symbol = _symbol;
		range = _range;
		compare = _compare;
		indicator = _indicator;
		charttype = _charttype;
		crosshair = _crosshair;
		logscale = _logscale;
  }
  
  function SetSymbol(_symbol) {
  	symbol = _symbol;
  }
  
  function SetTxtNameValue(value) {
  	document.getElementById("symbol4ChartId").value = value;
  }	   
  /* 
   This function is used for getting parameters from curent flashchart
   This function is called when clicking on 'Email' link from menubar of current flashchart 
   */
  function implementEmailFeature(_symbol,_range,_compare,_indicator,_charttype,_crosshair,_logscale)
  {
  		/*
		symbol = _symbol;
		range = _range;
		compare = _compare;
		indicator = _indicator;
		charttype = _charttype;
		crosshair = _crosshair;
		logscale = _logscale;
		*/
		
		var uri = "from=email";
		uri = uri + "&symbol=" + _symbol;
		uri = uri +  "&range=" + _range;
		//uri = uri +  "&compare=" + _compare;
		uri = uri +  "&" + _compare;
		
		//uri = uri +  "&indicator=" + _indicator;
		uri = uri +  "&" + _indicator;
		
		//uri = uri +  "&charttype=" + _charttype;
		uri = uri +  "&" + _charttype;
		
		//uri = uri + "&crosshair=" + _crosshair;
		uri = uri + "&" + _crosshair;
		
		//uri = uri + "&logscale=" + _logscale;
		uri = uri + "&" + _logscale;
		
		//alert(uri);					
 }
 function changeHeight(_height)
  {
  		flashHeight = flashHeight + _height;
  		document.getElementById("flashchart").height= flashHeight;
  		//alert("changeH");
  }
  
  /* This method is called when flashchart is ready */
  function CallJavaScriptOnloadChart() {
    //+++ set timeout 1s=1000 milliseconds 
	var t=setTimeout("_timeoutCallJavaScriptOnloadChart()",1000);
	//---
  }
  
  function _timeoutCallJavaScriptOnloadChart()
  {	
  <%
  	String callFrom = request.getParameter("from");
  	callFrom = callFrom == null ? "symbol":callFrom;
  
  	if("symbol".equalsIgnoreCase(callFrom)) {
  	    if("symbol".equalsIgnoreCase(callFrom)) {
  %>
	  		var symbol_ =  "<c:out value='${flashChartForm.symbol}' />";
	  		SetTxtNameValue(symbol_);
	  		SetSymbol(symbol_);
	  		getFlexApp('flashchart').addSymbol(symbol);
  <%    }
  	} else if("email".equalsIgnoreCase(callFrom)) {
	  	String symbol = request.getParameter("symbol");
	  	symbol = (symbol == null ? "" : symbol);
	  	
	  	String range = request.getParameter("range");
	  	range = (range == null ? "" : range);
	  	
	  	String compare = request.getParameter("compare");
	  	compare = (compare == null ? "" : compare);
	  	
	  	String indicator = request.getParameter("indicator");
	  	indicator = (indicator == null ? "" : indicator);
	  	if(indicator.length() > 0) {
	  		StringBuffer strBuff = new StringBuffer();
		  	StringTokenizer strToken = new StringTokenizer(indicator, "+ ");
		  	while(strToken.hasMoreElements()) {
		  		strBuff.append(strBuff.length() == 0 ? "" : "+").append(strToken.nextElement());
		  	}
		  	indicator = strBuff.toString();
	  	}
	  	
	  	String charttype = request.getParameter("charttype");
	  	charttype = (charttype == null ? "" : charttype);
	  	
	  	String crosshair = request.getParameter("crosshair");
	  	crosshair = (crosshair == null ? "" : crosshair);
	  	
	  	String logscale = request.getParameter("logscale");
	  	logscale = (logscale == null ? "" : logscale);
  %>
  		//+++ set value
  		SetValiable("<%=symbol%>","<%=range%>","<%=compare%>","<%=indicator%>","<%=charttype%>","<%=crosshair%>","<%=logscale%>");
  		getFlexApp('flashchart').CallFlex(symbol,range,compare,indicator,charttype,crosshair,logscale);
  <%
  	}			  	
  %>		
  
  }
// -->
</script>
		
<noscript>
  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="flashchart" width="100%" height="100%"
			codebase="https://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="flashchart.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="sameDomain" />
			<param name="wmode" value="transparent" />
			<embed src="flashchart.swf" 
				width="100%" height="100%" align="middle"
				type="application/x-shockwave-flash">
			</embed>
	</object>
</noscript>
					</td>
				</tr>
				<tr>
					<td>
						<br/>
						<table class="nomal">
							<tr>
								<td colspan="3">
									<div id="td.ChartDes.id">
										Chart-Description
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top" width="60%">
									<div id="td.companySnapshot.id">
										<img src="../../../portal/images/icons/progress-small.gif"/>
									</div>
								</td>
								<td width="2%">&nbsp;</td>
								<td valign="top">
									<div id="td.quickLinks.id">
									<table class="normal">
										
										<tr>
											<td>
												
												<table width="100%" class="tb-2" border="0" cellspacing="0" cellpadding="0">
									              <tr>
									                <th colspan="2" style="background:url(../../../portal/images/layouts/tb2_tl.gif) left no-repeat; padding-left:11px; padding-top:5px;">
									                	QuickLinks
									                </th>
									                <td width="11" align="right"><img src="../../../portal/images/layouts/tb2_tr.gif" width="11" height="37" /></td>
									              </tr>
									              <tr>
									                <td style="background:url(../../../portal/images/layouts/tb_dot.gif) left repeat-y;">&nbsp;</td>
									                <td width="268">
													  	<div class="quick-l"><a href="<vndirect:uriRewrite uri='../research/HistoricalPriceForSymbol.do' />" class="h1">
													  		HistoricalQuote
													  	</a>
														<a href="<vndirect:uriRewrite uri='../../tradingideas/industry_browser/ListCompanies.do'/>?USERACTION=SEARCH_BY_SYMBOL&FROM=FlashChart" class="h2">
															ViewIndustryPeers
														</a>
														<a href="<vndirect:uriRewrite uri='../research/BalanceSheet.do' />" class="h3">
															FinancialStatement
														</a>
														<a href="<vndirect:uriRewrite uri='../research/Profile.do' />" class="e-nd h5">
															CompanyProfiles
														</a></div>
													</td>
									                <td style="background:url(../../../portal/images/layouts/tb_dot.gif) right repeat-y;">&nbsp;</td>
									              </tr>
									              <tr>
									                <td width="11"><img src="../../../portal/images/layouts/tb_bl.gif" width="11" height="7" /></td>
									                <td style="background:url(../../../portal/images/layouts/tb_dot.gif) bottom repeat-x;"><img src="../../../portal/images/commons/0.gif" /></td>
									                <td align="right"><img src="../../../portal/images/layouts/tb_br.gif" width="11" height="7" /></td>
									              </tr>
									            </table>
												
											</td>
										</tr> 
										
									</table>
									Chart-Description
									</div>
								</td>
							</tr>
						</table>
				</tr>
			</table>
			<br/>
			<!-- ############### END: MAIN CONTENT ##################### -->
			</td>
			<td class="right" style="WIDTH: 2px; BORDER-BOTTOM-STYLE: solid">&nbsp;</td>
		</tr>
	</table>
	<!-- End Content -->
<!-- /CONTENT 1 -->
