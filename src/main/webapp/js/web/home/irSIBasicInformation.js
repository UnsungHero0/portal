var chartOnSnapShot = null;
var PRICES_LIMIT = [];
var PRICES_ALL = [];
//var VOLUMES = [];
var IR_SI_LIMIT_DATA_MIN = 88888888;
var IR_SI_LIMIT_DATA_MAX = 0;
var IR_SI_ALL_MIN = 88888888;
var IR_SI_ALL_MAX = 0;
var limitMonths = -3;

$(document).ready(function() {
	draw3mHSChartOnIRSISnapshot();
	loadCompanyQuote();
});

/**
 * IrSi : quan hệ cổ đông - thông tin cổ phiếu
 */
function draw3mHSChartOnIRSISnapshot(){
	if(PRICES_LIMIT.length == 0){
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getLimitHistoricalPriceAjax.shtml",
			data : "symbol=VND&limitMonths=" + limitMonths,
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					// reset data
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max
						if (IR_SI_LIMIT_DATA_MIN >= stockData[i].close) {
							IR_SI_LIMIT_DATA_MIN = stockData[i].close;
						}
						if (IR_SI_LIMIT_DATA_MAX <= stockData[i].close) {
							IR_SI_LIMIT_DATA_MAX = stockData[i].close;
						}
						// set data
						PRICES_LIMIT.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_draw3mHSChartOnIRSISnapshot();
				}
			}
		});
	} else {
		_draw3mHSChartOnIRSISnapshot();
	}
}
function drawAllHSChartOnIRSISnapshot(){
	if(PRICES_ALL.length == 0){
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getHistoricalPriceAjax.shtml",
			data : "symbol=VND",
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					// reset data
					PRICES = [];
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max
						if (IR_SI_ALL_MIN >= stockData[i].close) {
							IR_SI_ALL_MIN = stockData[i].close;
						}
						if (IR_SI_ALL_MAX <= stockData[i].close) {
							IR_SI_ALL_MAX = stockData[i].close;
						}
						// set data
						PRICES_ALL.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_drawAllHSChartOnIRSISnapshot();
				}
			}
		});
	} else {
		_drawAllHSChartOnIRSISnapshot();
	}
}
function loadCompanyQuote() {
	$.ajax( {
		type : "POST",
		dataType : "json",
		url : $.web_resource.getContext()
				+ "ajax/irSnapshot/getCompanyQuote.shtml",
		success : function(data) {
			var quote = data.model.securitiesInfoForQuote;
			var extQuote = data.model.ifoCompanySnapshotViewExt;
			if(quote != null && extQuote != null){
				$('.ir-si-vndIndex #currentPrice').html(quote.currentPrice);
				var changeClass="down";
				if(quote.todayChangePrice >= 0){
					changeClass="up";
				}
				$('.ir-si-vndIndex #strTodayChange').html(quote.strTodayChange + ' (' + quote.strTodayChangePercent + ' %)');
				$('.ir-si-vndIndex #strTodayChange').addClass(changeClass);
			}
		}
	});
}
function _draw3mHSChartOnIRSISnapshot() {
	var yAxisStep = getTickInterval(IR_SI_LIMIT_DATA_MAX, IR_SI_LIMIT_DATA_MIN);
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
            min: IR_SI_LIMIT_DATA_MIN,
            max: IR_SI_LIMIT_DATA_MAX,
        }],
        series: [{
            type: 'line',
            name: 'VND',
            data: PRICES_LIMIT,
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
    chartOnSnapShot.rangeSelector.destroy();
}
function _drawAllHSChartOnIRSISnapshot(){
	var yAxisStep = getTickInterval(IR_SI_ALL_MAX, IR_SI_ALL_MIN);
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
            min: IR_SI_ALL_MIN,
            max: IR_SI_ALL_MAX,
        }],
        series: [{
            type: 'line',
            name: 'VND',
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