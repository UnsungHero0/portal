var chartOnSnapShot = null;
var PRICES_LIMIT = [];
var PRICES_ALL = [];
var SI_SNAPSHOT_MIN_IN_THREE_MONTHS = 88888888;
var SI_SNAPSHOT_MAX_IN_THREE_MONTHS = 0;
var SI_SNAPSHOT_MIN_IN_ALL = 88888888;
var SI_SNAPSHOT_MAX_IN_ALL = 0;

var limitMonths = -3;

$(function() {
	$('#container-2').tabs();
	
	// draw chart
	draw3mHSChartOnSISnapshot();
	
	// realtime view
	$('.realtimeSymbolPrice').hide();
	quickQuote($("#symbolId").val().toUpperCase());
});

function draw3mHSChartOnSISnapshot(){
	if(PRICES_LIMIT.length == 0){
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getLimitHistoricalPriceAjax.shtml",
			data : "symbol=" + $("#symbolId").val() + "&limitMonths=" + limitMonths,
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max
						if (SI_SNAPSHOT_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							SI_SNAPSHOT_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (SI_SNAPSHOT_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							SI_SNAPSHOT_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
						// set data
						PRICES_LIMIT.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_draw3mHSChartOnSISnapshot();
				}
			}
		});
	} else {
		_draw3mHSChartOnSISnapshot();
	}
}
function _draw3mHSChartOnSISnapshot() {
	var yAxisStep = getTickInterval(SI_SNAPSHOT_MAX_IN_THREE_MONTHS, SI_SNAPSHOT_MIN_IN_THREE_MONTHS);
	var chartOptions = {
        chart : {
            renderTo : 'hsChartOnSnapshot',
            alignTicks: false, // for mutiple series
            spacingLeft: 10, // css
            spacingTop: -2,
            backgroundColor: '#fafafa',
        },
        // remove highCharts.com at bottom
        credits: {
            enabled: false
        },
        navigator: {
            enabled: false
        },
        rangeSelector : {
             //enabled: false
             selected: 1
        },
         scrollbar: {
            enabled: false
        },
        yAxis: [{
            height: 185,
            lineWidth: 1,
            gridLineWidth: 1,
            minorTickInterval: 'auto',
            minorGridLineColor: '#E6E6E6',
            tickInterval: yAxisStep,
            min: SI_SNAPSHOT_MIN_IN_THREE_MONTHS,
            max: SI_SNAPSHOT_MAX_IN_THREE_MONTHS,
        }],
        series: [{
            type: 'line',
            name: $("#symbolId").val(),
            data: PRICES_LIMIT,
            tooltip: {
                valueDecimals: 2
            },
            color: '#f39200',
        }],
    };

    chartOnSnapShot = new Highcharts.StockChart(chartOptions);
    chartOnSnapShot.rangeSelector.destroy();
}

function drawAllHSChartOnSISnapshot(){
	if(PRICES_ALL.length == 0){
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getHistoricalPriceAjax.shtml",
			data : "symbol=" + $("#symbolId").val(),
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max
						if (SI_SNAPSHOT_MIN_IN_ALL >= stockData[i].close) {
							SI_SNAPSHOT_MIN_IN_ALL = stockData[i].close;
						}
						if (SI_SNAPSHOT_MAX_IN_ALL <= stockData[i].close) {
							SI_SNAPSHOT_MAX_IN_ALL = stockData[i].close;
						}
				
						// set data
						PRICES_ALL.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_drawAllHSChartOnSISnapshot();
				}
			}
		});
	} else {
		_drawAllHSChartOnSISnapshot();
	}
}
function _drawAllHSChartOnSISnapshot(){
	var yAxisStep = getTickInterval(SI_SNAPSHOT_MAX_IN_ALL, SI_SNAPSHOT_MIN_IN_ALL);
    var chartOptions = {
        chart : {
            renderTo : 'hsChartOnSnapshot',
            alignTicks: false, // for mutiple series
            spacingLeft: 10, // css
            spacingTop: 23,
            backgroundColor: '#fafafa',
        },
        // remove highCharts.com at bottom
        credits: {
            enabled: false
        },
        navigator: {
            enabled: false
        },
        rangeSelector : {
             enabled: false
           //  selected: 1
        },
         scrollbar: {
            enabled: false
        },
        yAxis: [{
            height: 185,
            lineWidth: 1,
            gridLineWidth: 1,
            minorTickInterval: 'auto',
            minorGridLineColor: '#E6E6E6',
            tickInterval: yAxisStep,
            min: SI_SNAPSHOT_MIN_IN_ALL,
            max: SI_SNAPSHOT_MAX_IN_ALL,
        }],
        series: [{
            type: 'line',
            name: $("#symbolId").val(),
            data: PRICES_ALL,
            tooltip: {
                valueDecimals: 2
            },
            color: '#f39200',
            dataGrouping: {
                enabled: false
            }
        }]
    };

    chartOnSnapShot = new Highcharts.StockChart(chartOptions);
}

function quickQuote(stockCode) {
	if(stockCode != ""){
		try {
			var formFields = {
			};
			var options = {
				action : $.web_resource.getContext() + "ajax/analysis/checkWhereToGetPriceData.shtml",
				callbackExecuteFail : function (error) {
				},
				callbackPostSubmit : function (responseText, statusText) {
				$('.realtimeSymbolPrice').hide();
				if(responseText.model.isDataFromDatabase){
					try {
						var formFields = {
							"symbol": stockCode
							};
						var options = {
							action : $.web_resource.getContext() + "ajax/analysis/getIntradayPrice.shtml",
							callbackExecuteFail : function (error) {
							},
							callbackPostSubmit : function (responseText, statusText) {
								var model = responseText.model;
								if($.web_utils.isNotNull(model)) {
									if ($.web_utils.isNotNull(model.symbol) && $.web_utils.isNotNull(model.secInfo)) {
										$('.realtimeSymbolPrice').show();
										var secInfo = model.secInfo;
			
										floorP = secInfo.floorPrice;
										basicP = secInfo.basicPrice;
										ceilP = secInfo.ceilingPrice;
										
										symbolCssClass = getClass(ceilP, floorP, basicP, secInfo.matchPrice);
										
										$("#symbol_avgP").html($.web_utils.fomatNumberWithScale(secInfo.averagePrice, 1));
										removeAllClass($("#symbol_avgP"));
										$("#symbol_avgP").addClass(getClass(ceilP, floorP, basicP, secInfo.averagePrice));
										$("#symbol_higP").html($.web_utils.fomatNumberWithScale(secInfo.highestPrice, 1));
										removeAllClass($("#symbol_higP"));
										$("#symbol_higP").addClass(getClass(ceilP, floorP, basicP, secInfo.highestPrice));
										$("#symbol_lowP").html($.web_utils.fomatNumberWithScale(secInfo.lowestPrice, 1));
										removeAllClass($("#symbol_lowP"));
										$("#symbol_lowP").addClass(getClass(ceilP, floorP, basicP, secInfo.lowestPrice));
										
										$("#symbol_pri").html($.web_utils.fomatNumberWithScale(secInfo.matchPrice, 1));
										removeAllClass($("#symbol_pri"));
										$("#symbol_pri").addClass(symbolCssClass);
										
										$("#symbol_lat").html(FormatVolume10(secInfo.matchQtty, 0));
										removeAllClass($("#symbol_lat"));
										$("#symbol_lat").addClass(symbolCssClass);
										$("#symbol_per").html($.web_utils.fomatDouble(secInfo.chgIndex)+'('+ $.web_utils.fomatNumberWithScale(secInfo.pctIndex, 2) + '%)');
										removeAllClass($("#symbol_per"));
										$("#symbol_per").addClass(symbolCssClass);
										$("#symbol_vol").html(FormatVolume10(secInfo.totalTradingQtty, 0));
										
										$("#symbol_bV4").html(FormatVolume10(secInfo.totalBidQtty, 0));
										$("#symbol_oV4").html(FormatVolume10(secInfo.totalListingQtty, 0));
									}
								}
							}
						};
						$.web_formAways.ajaxNav(formFields, options);
					} catch (e) {
						console.log(e);
					}
				}
				else {
					SnapshotMain.init(stockCode);
				}
			}
		};
		$.web_formAways.ajaxNav(formFields, options);
		} catch (e) {
			console.log(e);
		}
	}
}

