var HS_CHART; // Chart variable
var HS_MODE = 'default';
var PRICES = []; // default price of symbol (close price only)
var PRICES_FULL = [];
var EVENTS = [];
var VOLUMES = []; // default volume
var CHART_TYPE = 'candlestick';
var SYMBOL;
// define the compare list.
var LIST_COMPARE_SYMBOL = [];
var LIST_SMA_PERIOD = [];
var LIST_EMA_PERIOD = [];
var BBANDS_EXIST_NAME = '';
var MFI_EXIST_PERIOD = '';
var WR_EXIST_PERIOD = '';
var ROC_EXIST_PERIOD = '';
var RSI_EXIST_PERIOD = '';
var MACD_EXIST_NAME = '';
var SS_EXIST_NAME = '';
var FS_EXIST_NAME = '';
var VMA_EXIST_NAME = '';
var PSAR_EXIST_NAME = '';
var COMPARE_CHART_COLORS = ['#89A54E','#80699B','#3D96AE','#DB843D','#92A8CD'];
var SMA_CHART_COLORS = ['#666600','#999900','#CCCC00'];
var EMA_CHART_COLORS = ['#663D14','#995C1F','#CC7A29'];
var B_BANDS_CHART_COLOR = '#FF3300';
var PSAR_CHART_COLOR = '#808000';
var MFI_CHART_COLOR = '#000080';
var WR_CHART_COLOR = '#000080';
var ROC_CHART_COLOR = '#000080';
var RSI_CHART_COLOR = '#000080';
var VMA_CHART_COLOR = 'blue';
var MACD_CHART_COLOR = ['green', 'red', 'blue', '#000080'];
var SS_CHART_COLOR = ['green', 'red', '#000080'];
var FS_CHART_COLOR = ['green', 'red', '#000080'];
var EXIST_COMPARE_SERIES = new Object;
var EXIST_ALL_SERIES = new Object;
var EXIST_ALL_SERIES_NAME = [];
var EXIST_YAXIS_ALL_SERIES = new Object();
var MORE_YAXIS_LIST = [];
var EXIST_VOLUME_MA = '';

// default value for technique chart dialog
var SMA_INPUTS = ['15', '', ''];
var EMA_INPUTS = ['30', '', ''];
var BBANDS_INPUTS = ['20', '2'];
var MFI_INPUTS = ['14'];
var MACD_INPUTS = ['26', '12', '9'];
var PSAR_INPUTS = ['0.02', '0.20'];
var ROC_INPUTS = ['12'];
var RSI_INPUTS = ['14'];
var SS_INPUTS = ['14', '3'];
var FS_INPUTS = ['5', '3'];
var VMA_INPUTS = ['13'];
var WR_INPUTS = ['14'];

// flags
var isDisplayedDividends = false;

$(function(){
	if(detectDevice()){
		$('#hsDefaultContainer').css("width", "95%");
		$('#hsDefaultContainer').css("margin-left", "2.5%");
	}
	
	// Set the datepicker's date format
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        onSelect: function(dateText) {
            this.onchange();
            this.onblur();
        }
    });
	$.web_autocomplete.symbols('symbolToCompare', URL_SYMBOL_AUTO_SUGGESTION,
		{width : 300}
	);
	$.web_autocomplete.symbols('redrawChartSymbol', URL_SYMBOL_AUTO_SUGGESTION,
		{width : 300}
	);
	
	// compare
	$('#symbolToCompare').bind("keypress",
		function(event) {
			if (event.keyCode == 13) {
				if($('#symbolToCompare').val() != "" && SYMBOL != $('#symbolToCompare').val().toUpperCase()){
					$('#symbolToCompare').val($('#symbolToCompare').val().toUpperCase());
					drawCompare();
				}
			};
		}
	);
	
	// clear
	$('#clearAllBtn').click(function(){
		resetHighStock();
		drawDefaultChart();
	});
	
	//////////////////////////////
	// for research tools chart
	$('#redrawChartSymbol').bind("keypress",
		function(event) {
			if (event.keyCode == 13) {
				$('#redrawChartSymbol').val($('#redrawChartSymbol').val().toUpperCase());
				drawOtherSymbol();
			};
		}
	);
});

function drawChartForFirstTime(highStockData){
	if(highStockData.length > 0){
		// initilize data for the first time.
		for(var i = 0; i < highStockData.length; i++){
			// set data
			PRICES_FULL.push([
				 highStockData[i].transDate,
				 highStockData[i].open,
				 highStockData[i].high,
				 highStockData[i].low,
				 highStockData[i].close,
				 
			]);
			PRICES.push([
				 highStockData[i].transDate,
				 highStockData[i].close
			]);
			VOLUMES.push([
	             highStockData[i].transDate,
	             highStockData[i].volume
	        ]);
			
			if(highStockData[i].event == "DIVIDEND"){
				EVENTS.push([
	             	highStockData[i].transDate,
	             	highStockData[i].event
	            ]);
			}
			
		}
		SYMBOL = $('#whichSymbolIsDraw').val();
		
	    // Create the chart
	    drawDefaultChart();
    } else {
    	alert('Error in creating chart.');
    }
}
/**
 * isDisplayEvent : flag to display event on chart
 */
function drawDefaultChart(){
	$('#hsDefaultContainer').css('min-height', '688px');
	// Create the default chart
    HS_CHART = new Highcharts.StockChart(generateDefaultChart());
    
    // apply the date pickers
    registerHSChartCalendarPicker();
}
function prepareDrawCompare(){
	if($('#symbolToCompare').val() != "" && SYMBOL != $('#symbolToCompare').val().toUpperCase()){
		$('#symbolToCompare').val($('#symbolToCompare').val().toUpperCase());
		drawCompare();
	}
}
function drawCompare(){
	closeProcessCompare();
	
	if(HS_MODE == 'default'){
		resetCompareDisplayList();
	}

	var symbolToCompare = $('#symbolToCompare').val().toUpperCase();
	if(isAvaialbeSymbolForCompare(symbolToCompare)){
		$.ajax({
			type: "POST",
			dataType: "json",
			url: $.web_resource.getContext() + "ajax/analysis/getHistoricalPriceAjax.shtml",
			data: "symbol=" + symbolToCompare,
			success: function(data){
				var otherHighStockData = data.model.highStock.data;
				if(otherHighStockData != 'undefined'){
					// set data
					var otherPrices = [];
					for(var i = 0; i < otherHighStockData.length; i++){
						otherPrices.push([
				             otherHighStockData[i].transDate,
				             otherHighStockData[i].close
				        ]);
					}
					
					// begin add series to redraw the chart
					var serieses = [];
					var priceSeries = {
						name: SYMBOL, // the original price
						data: PRICES,
						id: 'compare' + SYMBOL + 'price'
					};
					serieses.push(priceSeries);
					// set exist compare symbol
					if(LIST_COMPARE_SYMBOL.length > 0){
						for(var i = 0; i < LIST_COMPARE_SYMBOL.length; i++){
							if(LIST_COMPARE_SYMBOL[i] != 'remove'){
								// set old color
								var thisSeries = EXIST_COMPARE_SERIES[LIST_COMPARE_SYMBOL[i]];
								thisSeries.color = COMPARE_CHART_COLORS[i];
								serieses.push(thisSeries);
							}
						}
					}
					
					// add symbol to compare list
					addSymbolToCompareList(symbolToCompare);
					var newCompareSeries = {
						name: symbolToCompare,
						data: otherPrices,
						id: 'compare' + symbolToCompare,
						color: COMPARE_CHART_COLORS[LIST_COMPARE_SYMBOL.indexOf(symbolToCompare)]
					};
					serieses.push(newCompareSeries);
					
					// add to exist compare series list
					EXIST_COMPARE_SERIES[symbolToCompare] = newCompareSeries;
					
					$('#hsDefaultContainer').css('min-height', '588px');
					HS_CHART = new Highcharts.StockChart(generateCompareOptions(serieses));
					
					// apply the date pickers
					registerHSChartCalendarPicker();
					
					HS_MODE = 'compare';
					var html = '<li id="compare' + symbolToCompare + '">' + symbolToCompare + '</li>';
					$('.hsNavigator .hsDisplayList').append(html);
					renderFunctionCompareList(symbolToCompare);
				}
			},
		});
	} else {
		alert('This symbol has been added to the compare list.');
	}
}
function drawSMA(){
	startLoadingHighStock();
	
	// delete exist
	if(LIST_SMA_PERIOD.length > 0){
		for(var i = 0; i < LIST_SMA_PERIOD.length; i++){
			$('.hsNavigator .hsDisplayList #SMA' + LIST_SMA_PERIOD[i]).remove();
			removeAChartByID('SMA' + LIST_SMA_PERIOD[i]);
		}
	}
	
	// reset setting before click
	// clear SMA from exist all series
	if(LIST_SMA_PERIOD.length > 0){
		for(var i = 0; i < LIST_SMA_PERIOD.length; i++){
			var name = 'SMA' + LIST_SMA_PERIOD[i];
			removeFromExistAllSeries(EXIST_ALL_SERIES_NAME,EXIST_ALL_SERIES, name);
		}
		EXIST_ALL_SERIES_NAME = removeBlankValueFromList(EXIST_ALL_SERIES_NAME);
	}
	// reset sma list
	LIST_SMA_PERIOD = [];
	
	var period1 = $('.smaInputParams #period1').val();
	var period2 = $('.smaInputParams #period2').val();
	var period3 = $('.smaInputParams #period3').val();
	
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period1)){
		LIST_SMA_PERIOD.push(period1);
		params += "&smaPeriod1=" + period1;
		SMA_INPUTS[0] = period1;
	}
	if(isValidPeriod(period2)){
		LIST_SMA_PERIOD.push(period2);
		params += "&smaPeriod2=" + period2;
		SMA_INPUTS[1] = period2;
	}
	if(isValidPeriod(period3)){
		LIST_SMA_PERIOD.push(period3);
		params += "&smaPeriod3=" + period3;
		SMA_INPUTS[2] = period3;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getSMAAjax.shtml",
		data: params,
		success: function(data){
			var smaListData = data.model.highStock.listData;
			
			var newSeries = [];
			if(smaListData != 'undefined' || smaListData != null){
				for(var i = 0; i < smaListData.length; i++){
					var smaData = smaListData[i];
					var smaPrices = [];
					for(var j = 0; j < smaData.length; j++){
						smaPrices.push([
				             smaData[j].transDate,
				             smaData[j].close
				        ]);
					}
					var series = {
						type: 'line',
			            name: 'SMA(' + LIST_SMA_PERIOD[i] + ')',
			            data: smaPrices,
			            tooltip: {
							valueDecimals: 2
						},
						color: SMA_CHART_COLORS[i],
						id: 'SMA' + LIST_SMA_PERIOD[i],
						dataGrouping: {
							enabled: false
						}
					};
					newSeries.push(series);
					
					// add to EXIST_ALL_SERIES
					EXIST_ALL_SERIES_NAME.push('SMA' + LIST_SMA_PERIOD[i]);
					EXIST_ALL_SERIES['SMA' + LIST_SMA_PERIOD[i]] = series;
					
					// add sma to the navigator
					var html = '<li id="' + 'SMA' + LIST_SMA_PERIOD[i] + '">' + 'SMA(' + LIST_SMA_PERIOD[i] + ')</li>';
					$('.hsNavigator .hsDisplayList').append(html);
					renderFunctionSMAList(LIST_SMA_PERIOD[i]);
				}
			}
			
			// add to current chart
			for(var i = 0; i < newSeries.length; i++){
				HS_CHART.addSeries(newSeries[i]);
			}
		}
	});
	
	stopLoadingHighStock();
}
function drawEMA(){
	startLoadingHighStock();
	
	// delete exist
	if(LIST_EMA_PERIOD.length > 0){
		for(var i = 0; i < LIST_EMA_PERIOD.length; i++){
			$('.hsNavigator .hsDisplayList #EMA' + LIST_EMA_PERIOD[i]).remove();
			removeAChartByID('EMA' + LIST_EMA_PERIOD[i]);
		}
	}
	
	// reset setting before click
	// clear EMA from exist all series
	if(LIST_EMA_PERIOD.length > 0){
		for(var i = 0; i < LIST_EMA_PERIOD.length; i++){
			var name = 'EMA' + LIST_EMA_PERIOD[i];
			removeFromExistAllSeries(EXIST_ALL_SERIES_NAME,EXIST_ALL_SERIES,name);
		}
		EXIST_ALL_SERIES_NAME = removeBlankValueFromList(EXIST_ALL_SERIES_NAME);
	}
	// reset sma list
	LIST_EMA_PERIOD = [];
	
	var period1 = $('.emaInputParams #period1').val();
	var period2 = $('.emaInputParams #period2').val();
	var period3 = $('.emaInputParams #period3').val();
	
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period1)){
		LIST_EMA_PERIOD.push(period1);
		params += "&emaPeriod1=" + period1;
		EMA_INPUTS[0] = period1;
	}
	if(isValidPeriod(period2)){
		LIST_EMA_PERIOD.push(period2);
		params += "&emaPeriod2=" + period2;
		EMA_INPUTS[1] = period2;
	}
	if(isValidPeriod(period3)){
		LIST_EMA_PERIOD.push(period3);
		params += "&emaPeriod3=" + period3;
		EMA_INPUTS[2] = period3;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getEMAAjax.shtml",
		data: params,
		success: function(data){
			var emaListData = data.model.highStock.listData;
			
			var newSeries = [];
			if(emaListData != 'undefined' || emaListData != null){
				for(var i = 0; i < emaListData.length; i++){
					var emaData = emaListData[i];
					var emaPrices = [];
					for(var j = 0; j < emaData.length; j++){
						emaPrices.push([
				             emaData[j].transDate,
				             emaData[j].close
				        ]);
					}
					var series = {
						type: 'line',
			            name: 'EMA(' + LIST_EMA_PERIOD[i] + ')',
			            data: emaPrices,
			            tooltip: {
							valueDecimals: 2
						},
						color: EMA_CHART_COLORS[i],
						id: 'EMA' + LIST_EMA_PERIOD[i],
						dataGrouping: {
							enabled: false
						}
					};
					newSeries.push(series);

					// add to EXIST_ALL_SERIES
					EXIST_ALL_SERIES_NAME.push('EMA' + LIST_EMA_PERIOD[i]);
					EXIST_ALL_SERIES['EMA' + LIST_EMA_PERIOD[i]] = series;
					
					// add sma to the navigator
					var html = '<li id="' + 'EMA' + LIST_EMA_PERIOD[i] + '">' + 'EMA(' + LIST_EMA_PERIOD[i] + ')</li>';
					$('.hsNavigator .hsDisplayList').append(html);
					renderFunctionEMAList(LIST_EMA_PERIOD[i]);
				}
			}
			
			// add to current chart
			for(var i = 0; i < newSeries.length; i++){
				HS_CHART.addSeries(newSeries[i]);
			}
		},
	});
	
	stopLoadingHighStock();
}
function drawBBands(){
	startLoadingHighStock();
	
	// clear exist
	if(BBANDS_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #bbands').remove();
		HS_CHART.get('bbandsTop').remove();
		HS_CHART.get('bbandsBottom').remove();
	}
	
	var period = $('.bBandsInputParams #period').val();
	var deviation = $('.bBandsInputParams #deviation').val();
	
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period)){
		params += "&bBandsPeriod=" + period;
		BBANDS_INPUTS[0] = period;
	}
	if(isValidPeriod(deviation)){
		params += "&bBandsDeviation=" + deviation;
		BBANDS_INPUTS[1] = deviation;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getBBandsAjax.shtml",
		data: params,
		success: function(data){
			var bBandsData = data.model.highStock.data;
			if(bBandsData != 'undefined' || bBandsData != null){
				var bBandsPricesTop = [];
				var bBandsPricesBottom = [];
				for(var j = 0; j < bBandsData.length; j++){
					bBandsPricesTop.push([
			             bBandsData[j].transDate,
			             bBandsData[j].high
			        ]);
					bBandsPricesBottom.push([
			             bBandsData[j].transDate,
			             bBandsData[j].low
			        ]);
				}
				var bBandsSeriesTop = {
					type: 'line',
		            name: 'BBands Top',
		            data: bBandsPricesTop,
		            tooltip: {
						valueDecimals: 2
					},
					color: B_BANDS_CHART_COLOR,
					id: 'bbandsTop',
					lineWidth: 1,
					dataGrouping: {
						enabled: false
					}
				};
				var bBandsSeriesBottom = {
					type: 'line',
		            name: 'BBands Bottom',
		            data: bBandsPricesBottom,
		            tooltip: {
						valueDecimals: 2
					},
					color: B_BANDS_CHART_COLOR,
					id: 'bbandsBottom',
					lineWidth: 1,
					dataGrouping: {
						enabled: false
					}
				};
				
				// add to exist all series
				if(EXIST_ALL_SERIES_NAME.indexOf('BBandsTop') == -1){
					EXIST_ALL_SERIES_NAME.push('BBandsTop');
				}
				if(EXIST_ALL_SERIES_NAME.indexOf('BBandsBottom') == -1){
					EXIST_ALL_SERIES_NAME.push('BBandsBottom');
				}
				EXIST_ALL_SERIES['BBandsTop'] = bBandsSeriesTop;
				EXIST_ALL_SERIES['BBandsBottom'] = bBandsSeriesBottom;
				
				// add to the navigator
				var html = '<li id="' + 'bbands' + '">' + 'BBands(' + period + ',' + deviation + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionBBands();

				// add to current chart
				HS_CHART.addSeries(bBandsSeriesTop);
				HS_CHART.addSeries(bBandsSeriesBottom);
			}
			BBANDS_EXIST_NAME = 'BBANDS' + period + deviation;
		},
	});
	
	stopLoadingHighStock();
}
function drawMFI(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist mfi
	if(MFI_EXIST_PERIOD != ''){
		$('.hsNavigator .hsDisplayList #mfi').remove();
		HS_CHART.get('mfi').remove();
	}
	
	var period = $('.mfiInputParams #period').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period)){
		params += "&mfiPeriod=" + period;
		MFI_INPUTS[0] = period;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getMFIAjax.shtml",
		data: params,
		success: function(data){
			var mfiData = data.model.highStock.data;
			if(mfiData != 'undefined' || mfiData != null){
				var mfiPrices = [];
				for(var j = 0; j < mfiData.length; j++){
					mfiPrices.push([
			             mfiData[j].transDate,
			             mfiData[j].close
			        ]);
				}
				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('MFI') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('MFI');
				}
				var newSeries = {
					type: 'line',
		            name: 'MFI(' + period + ')',
		            data: mfiPrices,
		            tooltip: {
						valueDecimals: 2
					},
					color: MFI_CHART_COLOR,
					id: 'mfi',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				EXIST_YAXIS_ALL_SERIES['MFI'] = [newSeries];
				
				// add mfi to the navigator
				var html = '<li id="' + 'mfi' + '">' + 'MFI(' + period + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionMFI();
				
				if(MFI_EXIST_PERIOD != ''){
					HS_CHART.addSeries(newSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('MFI', newSeries);
				}
			}
			MFI_EXIST_PERIOD = period;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('MFI') == -1){
				MORE_YAXIS_LIST.push('MFI');
			}
		}
	});
	
	stopLoadingHighStock();
}
function drawMACD(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist macd
	if(MACD_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #macd').remove();
		HS_CHART.get('macd_macd').remove();
		HS_CHART.get('macd_ema').remove();
		HS_CHART.get('macd_divergency').remove();
	}
	
	// set params
	var slowPeriod = $('.macdInputParams #slowPeriod').val();
	var fastPeriod = $('.macdInputParams #fastPeriod').val();
	var signalPeriod = $('.macdInputParams #signalPeriod').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(slowPeriod)){
		params += "&slowPeriod=" + slowPeriod;
		MACD_INPUTS[0] = slowPeriod;
	}
	if(isValidPeriod(fastPeriod)){
		params += "&fastPeriod=" + fastPeriod;
		MACD_INPUTS[1] = fastPeriod;
	}
	if(isValidPeriod(signalPeriod)){
		params += "&signalPeriod=" + signalPeriod;
		MACD_INPUTS[2] = signalPeriod;
	}

	// ajax
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getMACDAjax.shtml",
		data: params,
		success: function(data){
			var macdData = data.model.highStock.data;
			if(macdData != 'undefined' || macdData != null){
				var macd = [];
				var ema = [];
				var divergency = [];
				for(var j = 0; j < macdData.length; j++){
					macd.push([
			             macdData[j].transDate,
			             macdData[j].close
			        ]);
					ema.push([
			             macdData[j].transDate,
			             macdData[j].high
			        ]);
					divergency.push([
						macdData[j].transDate,
						parseFloat(macdData[j].close) - parseFloat(macdData[j].high)
					]);
				}
				
				// add sma to the navigator
				var html = '<li id="macd">MACD(' + slowPeriod + ',' + fastPeriod + ',' + signalPeriod + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionMACD();

				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('MACD') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('MACD');
				}
				var macdSeries = {
					type: 'line',
		            name: 'MACD(' + slowPeriod + ',' + fastPeriod + ')',
		            data: macd,
		            tooltip: {
						valueDecimals: 3
					},
					color: MACD_CHART_COLOR[0],
					id: 'macd_macd',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				var emaSeries = {
					type: 'line',
		            name: 'EMA(' + signalPeriod + ')',
		            data: ema,
		            tooltip: {
						valueDecimals: 3
					},
					color: MACD_CHART_COLOR[1],
					id: 'macd_ema',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				var divergencySeries = {
					type: 'bar',
		            name: 'Divergency',
		            data: divergency,
		            tooltip: {
						valueDecimals: 3
					},
					color: MACD_CHART_COLOR[2],
					id: 'macd_divergency',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				
				EXIST_YAXIS_ALL_SERIES['MACD'] = [macdSeries, emaSeries, divergencySeries];
				
				if(MACD_EXIST_NAME != ''){
					HS_CHART.addSeries(macdSeries);
					HS_CHART.addSeries(emaSeries);
					HS_CHART.addSeries(divergencySeries);
				} else {
					// add to current chart
					drawNewYAxisChart('MACD', macdSeries);
					HS_CHART.addSeries(emaSeries);
					HS_CHART.addSeries(divergencySeries);
				}
			}
			MACD_EXIST_NAME = 'MACD' + slowPeriod + fastPeriod +  signalPeriod;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('MACD') == -1){
				MORE_YAXIS_LIST.push('MACD');
			}
		},
	});
	
	stopLoadingHighStock();
}
function drawPSAR(){
	startLoadingHighStock();
	
	// clear exist
	if(PSAR_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #psar').remove();
		HS_CHART.get('psar').remove();
	}
	
	var stepPeriod = $('.psarInputParams #stepPeriod').val();
	var maxStepPeriod = $('.psarInputParams #maxStepPeriod').val();
	var params = "symbol=" + SYMBOL;
	if(stepPeriod != '' || stepPeriod != 'undefined'){
		params += "&stepPeriod=" + stepPeriod;
		PSAR_INPUTS[0] = stepPeriod;
	}
	if(maxStepPeriod != '' || maxStepPeriod != 'undefined'){
		params += "&maxStepPeriod=" + maxStepPeriod;
		PSAR_INPUTS[1] = maxStepPeriod;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getPSARAjax.shtml",
		data: params,
		success: function(data){
			var psarData = data.model.highStock.data;
			if(psarData != 'undefined' || psarData != null){
				var psarPrice = [];
				for(var j = 0; j < psarData.length; j++){
					psarPrice.push([
			             psarData[j].transDate,
			             psarData[j].close
			        ]);
				}
				var psarSeries = {
					marker : {
						enabled : true,
						radius : 2
					},
		            name: 'PSAR(' + stepPeriod + '->' + maxStepPeriod + ')',
		            data: psarPrice,
		            tooltip: {
						valueDecimals: 2
					},
					color: PSAR_CHART_COLOR,
					id: 'psar',
					lineWidth: 0,
					dataGrouping: {
						enabled: false
					}
				};
				
				// add to exist all series
				if(EXIST_ALL_SERIES_NAME.indexOf('PSAR') == -1){
					EXIST_ALL_SERIES_NAME.push('PSAR');
				}
				EXIST_ALL_SERIES['PSAR'] = psarSeries;
				
				// add to the navigator
				var html = '<li id="psar">PSAR(' + stepPeriod + ',' + maxStepPeriod + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionPSAR();

				// add to current chart
				HS_CHART.addSeries(psarSeries);
			}
			PSAR_EXIST_NAME = 'PSAR' + stepPeriod + maxStepPeriod;
		},
	});
	
	stopLoadingHighStock();
}
function drawROC(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist
	if(ROC_EXIST_PERIOD != ''){
		$('.hsNavigator .hsDisplayList #roc').remove();
		HS_CHART.get('roc').remove();
	}
	
	var period = $('.rocInputParams #period').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period)){
		params += "&rocPeriod=" + period;
		ROC_INPUTS[0] = period;
	}
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getROCAjax.shtml",
		data: params,
		success: function(data){
			var rocData = data.model.highStock.data;
			if(rocData != 'undefined' || rocData != null){
				var rocPrices = [];
				for(var j = 0; j < rocData.length; j++){
					rocPrices.push([
			             rocData[j].transDate,
			             rocData[j].close
			        ]);
				}
				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('ROC') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('ROC');
				}
				var newSeries = {
					type: 'line',
		            name: 'ROC(' + period + ')',
		            data: rocPrices,
		            tooltip: {
						valueDecimals: 2
					},
					color: ROC_CHART_COLOR,
					id: 'roc',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				EXIST_YAXIS_ALL_SERIES['ROC'] = [newSeries];
				
				// add mfi to the navigator
				var html = '<li id="' + 'roc' + '">' + 'ROC(' + period + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionROC();
				
				if(ROC_EXIST_PERIOD != ''){
					HS_CHART.addSeries(newSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('ROC', newSeries);
				}
			}
			ROC_EXIST_PERIOD = period;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('ROC') == -1){
				MORE_YAXIS_LIST.push('ROC');
			}
		}
	});
	
	stopLoadingHighStock();
}
function drawRSI(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist
	if(RSI_EXIST_PERIOD != ''){
		$('.hsNavigator .hsDisplayList #rsi').remove();
		HS_CHART.get('rsi').remove();
	}
	
	var period = $('.rsiInputParams #period').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period)){
		params += "&rsiPeriod=" + period;
		RSI_INPUTS[0] = period;
	}
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getRSIAjax.shtml",
		data: params,
		success: function(data){
			var rsiData = data.model.highStock.data;
			if(rsiData != 'undefined' || rsiData != null){
				var rsiPrices = [];
				for(var j = 0; j < rsiData.length; j++){
					rsiPrices.push([
			             rsiData[j].transDate,
			             rsiData[j].close
			        ]);
				}
				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('RSI') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('RSI');
				}
				var newSeries = {
					type: 'line',
		            name: 'RSI(' + period + ')',
		            data: rsiPrices,
		            tooltip: {
						valueDecimals: 2
					},
					color: RSI_CHART_COLOR,
					id: 'rsi',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				EXIST_YAXIS_ALL_SERIES['RSI'] = [newSeries];
				
				// add to the navigator
				var html = '<li id="' + 'rsi' + '">' + 'RSI(' + period + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionRSI();
				
				if(RSI_EXIST_PERIOD != ''){
					HS_CHART.addSeries(newSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('RSI', newSeries);
				}
			}
			RSI_EXIST_PERIOD = period;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('RSI') == -1){
				MORE_YAXIS_LIST.push('RSI');
			}
		}
	});
	
	stopLoadingHighStock();
}
function drawSS(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist
	if(SS_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #ss').remove();
		HS_CHART.get('ss_k').remove();
		HS_CHART.get('ss_d').remove();
	}
	
	// set params
	var kPeriod = $('.ssInputParams #kPeriod').val();
	var dPeriod = $('.ssInputParams #dPeriod').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(kPeriod)){
		params += "&kPeriod=" + kPeriod;
		SS_INPUTS[0] = kPeriod;
	}
	if(isValidPeriod(dPeriod)){
		params += "&dPeriod=" + dPeriod;
		SS_INPUTS[1] = dPeriod;
	}

	// ajax
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getSSAjax.shtml",
		data: params,
		success: function(data){
			var ssData = data.model.highStock.data;
			if(ssData != 'undefined' || ssData != null){
				var ssk = [];
				var ssd = [];
				for(var j = 0; j < ssData.length; j++){
					ssk.push([
			             ssData[j].transDate,
			             ssData[j].close
			        ]);
					ssd.push([
			             ssData[j].transDate,
			             ssData[j].high
			        ]);
				}
				
				// add to the navigator
				var html = '<li id="ss">SS(' + kPeriod + ',' + dPeriod + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionSS();

				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('SS') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('SS');
				}
				var sskSeries = {
					type: 'line',
		            name: '%K(' + kPeriod + ')',
		            data: ssk,
		            tooltip: {
						valueDecimals: 2
					},
					color: SS_CHART_COLOR[0],
					id: 'ss_k',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				var ssdSeries = {
					type: 'line',
		            name: '%D(' + dPeriod + ')',
		            data: ssd,
		            tooltip: {
						valueDecimals: 2
					},
					color: SS_CHART_COLOR[1],
					id: 'ss_d',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				
				EXIST_YAXIS_ALL_SERIES['SS'] = [sskSeries, ssdSeries];
				
				if(SS_EXIST_NAME != ''){
					HS_CHART.addSeries(sskSeries);
					HS_CHART.addSeries(ssdSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('SS', sskSeries);
					HS_CHART.addSeries(ssdSeries);
				}
			}
			SS_EXIST_NAME = 'SS' + kPeriod + dPeriod;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('SS') == -1){
				MORE_YAXIS_LIST.push('SS');
			}
		},
	});
	
	stopLoadingHighStock();
}
function drawFS(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist
	if(FS_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #fs').remove();
		HS_CHART.get('fs_k').remove();
		HS_CHART.get('fs_d').remove();
	}
	
	// set params
	var kPeriod = $('.fsInputParams #kPeriod').val();
	var dPeriod = $('.fsInputParams #dPeriod').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(kPeriod)){
		params += "&fskPeriod=" + kPeriod;
		FS_INPUTS[0] = kPeriod;
	}
	if(isValidPeriod(dPeriod)){
		params += "&fsdPeriod=" + dPeriod;
		FS_INPUTS[1] = dPeriod;
	}

	// ajax
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getFSAjax.shtml",
		data: params,
		success: function(data){
			var fsData = data.model.highStock.data;
			if(fsData != 'undefined' || fsData != null){
				var fsk = [];
				var fsd = [];
				for(var j = 0; j < fsData.length; j++){
					fsk.push([
			             fsData[j].transDate,
			             fsData[j].close
			        ]);
					fsd.push([
			             fsData[j].transDate,
			             fsData[j].high
			        ]);
				}
				
				// add to the navigator
				var html = '<li id="fs">FS(' + kPeriod + ',' + dPeriod + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionFS();

				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('FS') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('FS');
				}
				var fskSeries = {
					type: 'line',
		            name: '%K(' + kPeriod + ')',
		            data: fsk,
		            tooltip: {
						valueDecimals: 2
					},
					color: FS_CHART_COLOR[0],
					id: 'fs_k',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				var fsdSeries = {
					type: 'line',
		            name: '%D(' + dPeriod + ')',
		            data: fsd,
		            tooltip: {
						valueDecimals: 2
					},
					color: FS_CHART_COLOR[1],
					id: 'fs_d',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				
				EXIST_YAXIS_ALL_SERIES['FS'] = [fskSeries, fsdSeries];
				
				if(FS_EXIST_NAME != ''){
					HS_CHART.addSeries(fskSeries);
					HS_CHART.addSeries(fsdSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('FS', fskSeries);
					HS_CHART.addSeries(fsdSeries);
				}
			}
			FS_EXIST_NAME = 'FS' + kPeriod + dPeriod;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('FS') == -1){
				MORE_YAXIS_LIST.push('FS');
			}
		},
	});
	
	stopLoadingHighStock();
}
function drawVMA(){
	startLoadingHighStock();
	
	// clear exist
	if(VMA_EXIST_NAME != ''){
		$('.hsNavigator .hsDisplayList #vma').remove();
		HS_CHART.get('vma').remove();
	}
	
	var period = $('.vmaInputParams #period').val();
	var params = "symbol=" + SYMBOL;
	if(period != '' || period != 'undefined'){
		params += "&vmaPeriod=" + period;
		VMA_INPUTS[0] = period;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getVolumeMAAjax.shtml",
		data: params,
		success: function(data){
			var vmaData = data.model.highStock.data;
			if(vmaData != 'undefined' || vmaData != null){
				var vmaPrice = [];
				for(var j = 0; j < vmaData.length; j++){
					vmaPrice.push([
			             vmaData[j].transDate,
			             vmaData[j].close
			        ]);
				}
				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				var vmaSeries = {
					type: 'line',
		            name: 'MA(' + period + ')',
		            data: vmaPrice,
		            tooltip: {
						valueDecimals: 2
					},
					color: VMA_CHART_COLOR,
					id: 'vma',
					lineWidth: 1,
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				
				// add to exist all series
				EXIST_VOLUME_MA = 'VMA';
				EXIST_ALL_SERIES['VMA'] = vmaSeries;
				
				// add to the navigator
				var html = '<li id="vma">Volume MA(' + period + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionVMA();

				// add to current chart
				HS_CHART.addSeries(vmaSeries);
			}
			VMA_EXIST_NAME = 'VMA' + period;
		},
	});
	
	stopLoadingHighStock();
}
function drawWR(){
	startLoadingHighStock();
	
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	
	// clear if exist
	if(WR_EXIST_PERIOD != ''){
		$('.hsNavigator .hsDisplayList #wr').remove();
		HS_CHART.get('wr').remove();
	}
	
	var period = $('.wrInputParams #period').val();
	var params = "symbol=" + SYMBOL;
	if(isValidPeriod(period)){
		params += "&wrPeriod=" + period;
		WR_INPUTS[0] = period;
	}
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: $.web_resource.getContext() + "ajax/analysis/getWRAjax.shtml",
		data: params,
		success: function(data){
			var wrData = data.model.highStock.data;
			if(wrData != 'undefined' || wrData != null){
				var wrPrices = [];
				for(var j = 0; j < wrData.length; j++){
					wrPrices.push([
			             wrData[j].transDate,
			             wrData[j].close
			        ]);
				}
				var yAxisValue = 1 + MORE_YAXIS_LIST.length;
				if(MORE_YAXIS_LIST.indexOf('WR') != -1){
					yAxisValue = 1 + MORE_YAXIS_LIST.indexOf('WR');
				}
				var newSeries = {
					type: 'line',
		            name: 'WR(' + period + ')',
		            data: wrPrices,
		            tooltip: {
						valueDecimals: 2
					},
					color: WR_CHART_COLOR,
					id: 'wr',
					yAxis: yAxisValue,
					dataGrouping: {
						enabled: false
					}
				};
				EXIST_YAXIS_ALL_SERIES['WR'] = [newSeries];
				
				// add to the navigator
				var html = '<li id="' + 'wr' + '">' + 'WR(' + period + ')</li>';
				$('.hsNavigator .hsDisplayList').append(html);
				renderFunctionWR();
				
				if(WR_EXIST_PERIOD != ''){
					HS_CHART.addSeries(newSeries);
				} else {
					// add to current chart
					drawNewYAxisChart('WR', newSeries);
				}
			}
			WR_EXIST_PERIOD = period;
			// add to exist all series
			if(MORE_YAXIS_LIST.indexOf('WR') == -1){
				MORE_YAXIS_LIST.push('WR');
			}
		}
	});
	
	stopLoadingHighStock();
}

/** ************************************ */
// minh.nguyen: addition function for research tools page (rtChart.jsp)
function drawOtherSymbol(){
	var newSymbol = $('#redrawChartSymbol').val().toUpperCase();
	if(newSymbol != '' && newSymbol != SYMBOL){
		startLoadingHighStock();
		
		// get market highlight
		getMarketViewOnChart(newSymbol);
		
		// redraw chart
		$.ajax({
			type: "POST",
			dataType: "json",
			url: $.web_resource.getContext() + "ajax/analysis/getHistoricalPriceAjax.shtml",
			data: "symbol=" + newSymbol,
			success: function(data){
				if(data.model.highStock != null){
					var hsData = data.model.highStock.data;
					if(hsData.length > 0){
						// re-initilize data.
						PRICES_FULL = [];
						PRICES = [];
						VOLUMES = [];
						EVENTS = [];
						for(var i = 0; i < hsData.length; i++){
							PRICES_FULL.push([
								 hsData[i].transDate,
								 hsData[i].open,
								 hsData[i].high,
								 hsData[i].low,
								 hsData[i].close
							]);
							PRICES.push([
								 hsData[i].transDate,
								 hsData[i].close
							]);
							VOLUMES.push([
					             hsData[i].transDate,
					             hsData[i].volume
					        ]);
							
							if(hsData[i].event == "DIVIDEND"){
								EVENTS.push([
					             	hsData[i].transDate,
					             	hsData[i].event
					            ]);
							}
						}
					}
					SYMBOL = newSymbol;
					
					// Re-Create the chart
					resetHighStock();
	   				drawDefaultChart();
	   				stopLoadingHighStock();
	   			} else {
	   				alert('Error: Please choose another symbol.');
	   				stopLoadingHighStock();
	   			}
			},
			error: function(e){
				alert('Error: Please choose another symbol.');
				stopLoadingHighStock();
			}
		});
	}
	
	isDisplayedDividends = false;
}
function getMarketViewOnChart(newSymbol){
	$.ajax({
		type: "POST",
		url: $.web_resource.getContext() + "ajax/analysis/getRTMarketViewAjax.shtml",
		data: "symbol=" + newSymbol,
		success: function(data){
			$('.rtMarketSnapshot').html(data);
		},
		error: function(e){
			alert('Invalid. Please choose another symbol.');
		}
	});
}
function toogleDividends(){
	closeToolList();
	
	if(!isDisplayedDividends){
		var dividendsSerie = createDividendsSerie();
		if(dividendsSerie.length != 0){
			HS_CHART.addSeries(dividendsSerie);
			var html = '<li id="' + 'dividends' + '">' + 'Dividends</li>';
			$('.hsNavigator .hsDisplayList').append(html);
			renderFunctionDividends();
			
			isDisplayedDividends = true;
			
			// add to global exist series
			EXIST_ALL_SERIES_NAME.push('DIVIDENDS');
			EXIST_ALL_SERIES['DIVIDENDS'] = dividendsSerie;
		} else {
			alert("Cổ phiếu " + SYMBOL + " ko có event trả cổ tức.");
		}
	}
}