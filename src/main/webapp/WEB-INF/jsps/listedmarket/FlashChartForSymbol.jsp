<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.StringTokenizer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
    FROM_MODULE = URL_FLASH_CHART;
});
</script>

<form name="fMaijorHolders" action="" method="post">
	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-main-nav.jsp"></jsp:include>

		<!--Start All Tab menu  -->
		<div class="content_small" style="padding-bottom: 20px;">
			<input type="hidden" id="symbol4ChartId" name="symbol"
				value="<c:out value="${symbol}" />" />
			<div id="divFlashchart"></div>

<script language="JavaScript" type="text/javascript">
// -----------------------------------------------------------------------------
// Globals
// Major version of Flash required
var requiredMajorVersion = 9;
// Minor version of Flash required
var requiredMinorVersion = 0;
// Minor version of Flash required
var requiredRevision = 28;
//var symbol ="acb";
var symbol = "";
//var range = "15/01/2000,15/01/2008";
var range = "";
//var compare = "ree+htv+vtv+bbc";
var compare = "";
//var indicator = "SMA(50,20,10)+EMA(50,30,10)+BBANDS(12,2)+MFI(10)+VOLMA(5)+WR(10)";
var indicator = "";
//var charttype = "L";
var charttype = "";
//var crosshair = "TRB";
var crosshair = "";
//var logscale = "LI";
var logscale = "";
var link = "";
var flashHeight = 380;//380
var flashWidth = 770;//770
// -----------------------------------------------------------------------------
// -->
</script>
			<script language="JavaScript" type="text/javascript">
function CallFlex() {
	//alert("addSymbol");
	getFlexApp('flashchart').CallFlex(symbol, range, compare, indicator,
			charttype, crosshair, logscale);
}
function getFlexApp(appName) {
	//	if (navigator.appName.indexOf ('Microsoft') !=-1) {
	//		return window[appName];
	//	} else {
		//alert(document[appName]);
	return document[appName];
	//	}
}
function addSymbol() {
	//var symbol = $("#symbol4ChartId").val();
	symbol = $("#symbol4ChartId").val();

	//<!-- flashchart la ten cua ung dung flex -->
	getFlexApp('flashchart').addSymbol(symbol);
}

/* this function is used to display flashchart with parameters on email*/
//window.onload = SetValiable("acb","15/01/2000,18/01/2008","ree+VnIndex","SMA(50,20,10)+EMA(50,30,10)+BBANDS(12,2)+MFI(10)+VOLMA(5)+WR(10)","L","TRB","LO");
// This function is used for setting parameters when creating a flashchart
function SetValiable(_symbol, _range, _compare, _indicator, _charttype,
		_crosshair, _logscale) {
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
	//document.getElementById("symbol4ChartId").value = value;
	$("#symbol4ChartId").val(value);
}
/*
 This function is used for getting parameters from curent flashchart
 This function is called when clicking on 'Email' link from menubar of current flashchart
 */
function implementEmailFeature(_symbol, _range, _compare, _indicator,
		_charttype, _crosshair, _logscale) {
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
	uri = uri + "&range=" + _range;
	//uri = uri +  "&compare=" + _compare;
	uri = uri + "&" + _compare;

	//uri = uri +  "&indicator=" + _indicator;
	uri = uri + "&" + _indicator;

	//uri = uri +  "&charttype=" + _charttype;
	uri = uri + "&" + _charttype;

	//uri = uri + "&crosshair=" + _crosshair;
	uri = uri + "&" + _crosshair;

	//uri = uri + "&logscale=" + _logscale;
	uri = uri + "&" + _logscale;

	//alert(uri);
}
function changeHeight(_height) {
	flashHeight = flashHeight + _height;
	document.getElementById("flashchart").height = flashHeight;
	//alert("changeH");
}

/* This method is called when flashchart is ready */
function CallJavaScriptOnloadChart() {
	//+++ set timeout 1s=1000 milliseconds
	var t = setTimeout("_timeoutCallJavaScriptOnloadChart()", 1000);
	//---
}

function _timeoutCallJavaScriptOnloadChart() {
<%String callFrom = request.getParameter("from");
			callFrom = callFrom == null ? "symbol" : callFrom;

			if ("symbol".equalsIgnoreCase(callFrom)) {
				if ("symbol".equalsIgnoreCase(callFrom)) {%>
			var symbol_ =  "<c:out value='${symbol}' />";
			SetTxtNameValue(symbol_);
			SetSymbol(symbol_);
			getFlexApp('flashchart').addSymbol(symbol);
  <%}
			} else if ("email".equalsIgnoreCase(callFrom)) {
				String symbol = request.getParameter("symbol");
				symbol = (symbol == null ? "" : symbol);

				String range = request.getParameter("range");
				range = (range == null ? "" : range);

				String compare = request.getParameter("compare");
				compare = (compare == null ? "" : compare);

				String indicator = request.getParameter("indicator");
				indicator = (indicator == null ? "" : indicator);
				if (indicator.length() > 0) {
					StringBuffer strBuff = new StringBuffer();
					StringTokenizer strToken = new StringTokenizer(indicator, "+ ");
					while (strToken.hasMoreElements()) {
						strBuff.append(strBuff.length() == 0 ? "" : "+").append(strToken.nextElement());
					}
					indicator = strBuff.toString();
				}

				String charttype = request.getParameter("charttype");
				charttype = (charttype == null ? "" : charttype);

				String crosshair = request.getParameter("crosshair");
				crosshair = (crosshair == null ? "" : crosshair);

				String logscale = request.getParameter("logscale");
				logscale = (logscale == null ? "" : logscale);%>
		//+++ set value
		SetValiable("<%=symbol%>","<%=range%>","<%=compare%>","<%=indicator%>","<%=charttype%>","<%=crosshair%>","<%=logscale%>");
		getFlexApp('flashchart').CallFlex(symbol,range,compare,indicator,charttype,crosshair,logscale);
  <%}%>

  }
// -->
</script>

			<!-- expressInstall.swf -->
			<script type="text/javascript">
var swfUrl = "<web:url value='/fchart/flashchart.swf'/>";
var id = "divFlashchart";
var width = flashWidth;
var height = flashHeight;

var version = "9.0.115";
var expressInstallSwfurl = "<web:url value='/flash/expressInstall.swf'/>";
var flashvars = {
	swfid : "flashchart"
};
var params = {
	wmode : "transparent",
	quality : "high",
	bgcolor : "#869ca7",
	allowScriptAccess : "sameDomain"
};
var attributes = {
	id : "flashchart",
	name : "VNDirect Flashchart"
};

swfobject.embedSWF(swfUrl, id, width, height, version, expressInstallSwfurl,
		flashvars, params, attributes);
//swfobject.embedSWF(swfUrl, id, width, height, version, expressInstallSwfurl, flashvars, params, attributes, callbackFn)
</script>
		</div>
	</div>
</form>
