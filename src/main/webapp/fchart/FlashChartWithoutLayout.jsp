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

<!-- Begin Content -->
<table class="normal" cellpadding="0px" cellspacing="0px">
		<tr valign="top">
			<td class="bottom" style="VERTICAL-ALIGN: top;">
			<!-- ############### BEGIN: MAIN CONTENT ##################### -->
			<table class="normal" style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; WIDTH: 100%;" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table>
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
				                           		<a href="javascript:addSymbol();">
													ChangeSymbol
												</a>
				                           </td>
				                           <td><img src="../../../portal/images/buttons/btn3_r.gif" width="5" height="19" /></td>
				                         </tr>
			                       </table>
								</td>
							</tr>
						</table>
						
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
   	
   	document.write('<vndirect:message key="/Messages/Quotes/Lables/FlashChart/FlashPlayerNotice"/>' + "<a href='<vndirect:uriRewrite uri='../research/BasicChartForSymbol.do'/>' class='blueUnderline' target='blank'>click here.</a>");  // insert non-flash content
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
		try {
			symbol  = document.getElementById('symbol4ChartId').value;
			
			<!-- flashchart la ten cua ung dung flex -->
			getFlexApp('flashchart').addSymbol(symbol);
		} catch (e){}
		//refresh quickSearchSymbolId
		// document.getElementById("quickSearchSymbolId").value = symbol.toUpperCase();
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
  function CallJavaScriptOnloadChart()
  {
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
				width="100%" height="100%"  align="middle"
				loop="false"
				type="application/x-shockwave-flash">
			</embed>
	</object>
</noscript>
					</td>
				</tr>
				<tr>
					<td>
						<table class="nomal">
							<tr>
								<td width="90%">
									<div id="td.ChartDes.id">
										Chart-Description
									</div>
								</td>
								<td nowrap="nowrap" class="bg_2" valign="top" align="right">
									<div style="text-align: right; font-weight: bold;"><a href="https://www.vndirect.com.vn" target="blank"><span>Powered by VNDIRECT Securities Company</span></a></div>
								</td>
							</tr>
							
						</table>
				</tr>
			</table>
			<!-- ############### END: MAIN CONTENT ##################### -->
			</td>
		</tr>
	</table>
	<!-- End Content -->
<!-- /CONTENT 1 -->
