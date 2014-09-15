/* OHLC = "OHLC"; CANDLESTICK = "CAND"; LINE = "LINE"; */
var lineType = "LINE";
/* LOGARITHMIC = "LOG"; LINEAR = "LINEAR"; */
var chartScale = "LINEAR";

var priorDay = 0;
var priorMonth = 0;
var priorYear = -1;

////////////////



function doChangeChart(imgChartId, listParamNames, listParamValues, userAction) {	
	var params = [];
	params.push({name : 'userAction', value: userAction});	
	params.push({name : 'priorDay', value: priorDay});	
	params.push({name : 'priorMonth', value: priorMonth});	
	params.push({name : 'priorYear', value: priorYear});	
	params.push({name : 'lineType', value: lineType});
	params.push({name : 'chartScale', value: chartScale});
	if(listParamNames != null && listParamValues != null) {
		var i, size = listParamNames.length;
		for(i=0; i<size; i++) {
			params.push({name : listParamNames[i], value: listParamValues[i]});	
		}
	}	
	$.ajax({
		   type: "POST",
		   url: URL_FLASH_CHART_AJAX,
		   data: params,
		   dataType: "json",
		   success: function(data) {
				var model = data.model;
				var chartId = model.taChart.cacheKey;
				var strListIndicator = model.strListIndicator;
				var strListCompetitor = model.strListCompetitor;
				var strLineType = model.chartInfo.lineType;
				var strChartScale = model.chartInfo.chartScale;
				$('#'+imgChartId).removeAttr("src");
				$('#'+imgChartId).attr("src", model.chartURL + "?cacheKey=" + chartId);
				
				try {
					refreshSelectedIndicator(strListIndicator);
				} catch (e) {
					alert("refreshSelectedIndicator(" + strListIndicator + ") - e:" + e);
				}
				
				try {
					refreshSelectedCompetitor(strListCompetitor);
				} catch (e) {
					alert("refreshSelectedCompetitor(" + strListCompetitor + ") - e:" + e);
				}
				//update LineType
				try {
					refreshSelectedLineType(strLineType);
				} catch (e) {
					alert("refreshLineType(" + strLineType + ") - e:" + e);
				}
				//update ChartScale
				try {
					refreshSelectedChartScale(strChartScale);												
				} catch (e) {
					alert("refreshChartScale(" + strChartScale + ") - e:" + e);
				}
				
				//refreshNavChart
				refreshNavTAChart();
				hideProgressIndicator();		
		   },
		   beforeSend: function(){
			   showProgressIndicator();		
		   }
	});

}

function doChangeDateChart(userAction, imgChartId, _priorDay, _priorMonth, _priorYear) {
	priorDay = _priorDay;
	priorMonth = _priorMonth;
	priorYear = _priorYear;
	doChangeChart(imgChartId, null, null, userAction);
}


/**
*
*/
function doChangeLineTypeChart(userAction, imgChartId, _lineType) {
	lineType = _lineType;
	doChangeChart(imgChartId, null, null, userAction);
}

function doChangeScaleChart(userAction, imgChartId, _chartScale) {
	chartScale = _chartScale;
	doChangeChart(imgChartId, null, null, userAction);
}



/* Using to refresh chart link */
var navTACharIds = new Array(
			"navChart-500Id"
			, "navChart0-10Id"
			, "navChart0-30Id"
			, "navChart00-1Id"
			, "navChart00-2Id"
			, "navChart00-5Id"
			, "navChart00-50Id"
			);
function refreshNavTAChart() {
	var size = navTACharIds.length;
	var i;
	for(i=0; i<size; i++) {
		try {
			document.getElementById(navTACharIds[i]).className = "chartUnSelected";
		} catch (e) {}
	}
	
	//build chartId
	var chartId = "navChart" + priorDay + "" + priorMonth + "" + priorYear + "Id";
			try {
			document.getElementById(chartId).className = "chartSelected";
		} catch (e) {}
}
///////////////


/*
* this function is used to refresh selected indicators
*/
function refreshSelectedIndicator(strListSelectedIndicator) {
	if(strListSelectedIndicator == "undifined" || strListSelectedIndicator == null) {
		return;
	}
	try {
		var size = listIndicatorId.length;
		var i;
		var currImg;
		for(i = 0; i < size; i++) {
			currImg = document.getElementById("icon_" + listIndicatorId[i]);
			if(currImg != null && currImg != "undefined") {
				currImg.src = checkbox0.src;
			}
		}
		var debug = "";
		var listSelectedIndicator = strListSelectedIndicator.split("|");
		size = listSelectedIndicator.length;
		for(i=0; i<size; i++) {
			debug += listSelectedIndicator[i];
			currImg = document.getElementById("icon_" + "img" + listSelectedIndicator[i] + "Id");
			if(currImg != null && currImg != "undefined") {
				currImg.src = checkbox1.src;
			}
		}
		if(isDebug) {
			alert(debug);
		}
	} catch (e) {
		alert(e);
	}
}

/**
* this function is used to refresh selected competitors
*/
function refreshSelectedCompetitor(strListSelectedCompetitor) {
	if(strListSelectedCompetitor == "undifined" || strListSelectedCompetitor == null) {
		return;
	}
	try {
		var size = listCompetitorId.length;
		var i;
		var currImg;
		for(i = 0; i < size; i++) {
			currImg = document.getElementById("icon_" + listCompetitorId[i]);
			if(currImg != null && currImg != "undefined") {
				currImg.src = checkbox0.src;
			}
		}
		var debug = "";
		var listSelectedCompetitor = strListSelectedCompetitor.split("|");
		size = listSelectedCompetitor.length;
		for(i=0; i<size; i++) {
			debug += listSelectedCompetitor[i];
			currImg = document.getElementById("icon_" + "img" + listSelectedCompetitor[i] + "Id");
			if(currImg != null && currImg != "undefined") {
				currImg.src = checkbox1.src;
			}
		}
		if(isDebug) {
			alert(debug);
		}
	} catch (e) {
		alert(e);
	}	
}

/**
* this function is used to refresh selected line type
*/
function refreshSelectedLineType(strLineType) {
	if(strLineType == "undifined" || strLineType == null) {
		return;
	}
	try {
		var size = listLineType.length;
		var i;
		var currImg;
		for(i = 0; i < size; i++) {
			currImg = document.getElementById("icon_" + listLineType[i]);
			if(currImg != null && currImg != "undefined") {
				currImg.src = radio0.src;
			}
		}
		
		currImg = document.getElementById("icon_" + "img" + strLineType + "Id");
		if(currImg != null && currImg != "undefined") {
			currImg.src = radio1.src;
		}
		if(isDebug) {
			alert(strLineType);
		}
	} catch (e) {
		alert(e);
	}		
}

/**
* this function is used to refresh selected chart scale
*/
function refreshSelectedChartScale(strChartScale) {
	if(strChartScale == "undifined" || strChartScale == null) {
		return;
	}
	try {
		var size = listCharScale.length;
		var i;
		var currImg;
		for(i = 0; i < size; i++) {
			currImg = document.getElementById("icon_" + listCharScale[i]);
			if(currImg != null && currImg != "undefined") {
				currImg.src = radio0.src;
			}
		}
		
		currImg = document.getElementById("icon_" + "img" + strChartScale + "Id");
		if(currImg != null && currImg != "undefined") {
			currImg.src = radio1.src;
		}
		if(isDebug) {
			alert(strChartScale);
		}
	} catch (e) {
		alert(e);
	}		
}				 

/**
* popup a new window for use selected a Indicator
*/
function selectIndicator(action, userAction, selectIndicator , imgIndicator) {
	var url = "<vndirect:uriRewrite uri='../../../online/brokerage/research/BasicChartForSymbol.do'/>";
	url = addParam2URL(url, "USERACTION", selectIndicator);
	url = addParam2URL(url, "imgIndicator", imgIndicator);
	url = addParam2URL(url, "selectedIndicator", selectIndicator);

openNormalWindow(url);        
document.body.style.display = "none";
document.body.style.display = "";
return;
}   	

/**
* Update chart & menu's status by selected indicator
*/
function doRefreshChartSelectIndicator(imgUri, strListIndicator, strListCompetitor, strLineType, strChartScale) { 
	try {				
		var imgTAChart = document.getElementById("imgMainChartId");	
		imgTAChart.src = imgUri;
	} catch (e) {
		alert("imgTAChart.src:" + e + ", chartUri:" + imgUri);
	}
	//update selected Indicator						
	try {
		refreshSelectedIndicator(strListIndicator);
	} catch (e) {
		alert("refreshSelectedIndicator(" + strListIndicator + ") - e:" + e);
	}
	//update selected Competitor
	try {			
		refreshSelectedCompetitor(strListCompetitor);
	} catch (e) {
		alert("refreshSelectedCompetitor(" + strListCompetitor + ") - e:" + e);
	}
	//update LineType
	try {
		refreshSelectedLineType(strLineType);
	} catch (e) {
		alert("refreshLineType(" + strLineType + ") - e:" + e);
	}
	//update ChartScale
	try {
		refreshSelectedChartScale(strChartScale);												
	} catch (e) {
		alert("refreshChartScale(" + strChartScale + ") - e:" + e);
	}			
}

/**
* popup a new window for use selected a Indicator
*/
function doAddRemoveSymbols(actionFor) {
//	var url = "<vndirect:uriRewrite uri='../../../online/brokerage/research/ChartAddRemoveSymbol.do'/>";
//	url = addParam2URL(url, "actionFor", actionFor);
//	url = addParam2URL(url, "imgIndicator", "imgMainChartId");
//	
//openNormalWindow(url);        
//document.body.style.display = "none";
//document.body.style.display = "";
//return;
	
	var url = URL_CLASSIC_CHART_ADD_SYMBOL;
	$("#dialog").load(url);
	$("#dialog").dialog('open');
} 
  

function doDrawChart(listParamNames, listParamValues, userAction) {	
	var params = [];
	params.push({name : 'userAction', value: userAction});	
	params.push({name : 'priorDay', value: priorDay});	
	params.push({name : 'priorMonth', value: priorMonth});	
	params.push({name : 'priorYear', value: priorYear});	
	params.push({name : 'lineType', value: lineType});
	params.push({name : 'chartScale', value: chartScale});
	if(listParamNames != null && listParamValues != null) {
		var i, size = listParamNames.length;
		for(i=0; i<size; i++) {
			params.push({name : listParamNames[i], value: listParamValues[i]});	
		}
	}	
	
	$.ajax({
		   type: "POST",
		   url: URL_FLASH_CHART_AJAX,
		   data: params,
		   dataType: "json",
		   success: function(data) {
				var model = data.model;
				alert(model);
				var chartId = model.taChart.cacheKey;
				alert(chartId);
				var strListIndicator = model.strListIndicator;
				var strListCompetitor = model.strListCompetitor;
				var strLineType = model.chartInfo.lineType;
				var strChartScale = model.chartInfo.chartScale;
				var chartUri = model.chartURL + "?cacheKey=" + chartId;
				doRefreshChartSelectIndicator(chartUri, strListIndicator, strListCompetitor, strLineType, strChartScale);				
				hideProgressIndicator();
		   },
		   beforeSend: function(){
			   showProgressIndicator();		
		   }
	});
	//setTimeout("window.close()", 100);
}
/////////////////////////////////############################################////////////////////////////   
function doCompareIndex(imgMainChartId, imgIndexId, symbolIndex, exchangeCode) {
	try {
		var imgIndex = document.getElementById("icon_" + imgIndexId);			
		var imgIndexSrc = imgIndex.src;
		var chartAction = "";
		if(imgIndexSrc.indexOf("checkbox-0") > -1) {
			chartAction = "DRAW";
		} else if(imgIndexSrc.indexOf("checkbox-1") > -1) {
			chartAction = "REMOVE";
		}
		
		var paramNames = new Array("selectedCompetitor", "selectedExchangeCode","chartAction");
		var paramValues = new Array(symbolIndex, exchangeCode, chartAction);			

		doChangeChart(imgMainChartId, paramNames, paramValues, COMPETITOR_ACTION);
	} catch (e) {
		alert(e);
	}
}   

function hideProgressIndicator() {
	var obj = document.getElementById("progress_indicator");
	if (obj!=null) {
		obj.style.display = "none";
	}
	return true;
}

function showProgressIndicator() {
	var obj = document.getElementById("progress_indicator");
	if (obj!=null) {	
		var nLeft = (window.screen.width / 2);
	    var nTop = (window.screen.height / 2) - 100;
	    
		obj.style.top = nTop + "px";
		obj.style.left = nLeft + "px";	
		obj.style.position = "absolute";
		obj.style.display = "inline";
	}
	return true;
}

