var chartOnSnapShot = null;
var PRICES_LIMIT = [];
var PRICES_ALL = [];
var IR_SNAPSHOT_LIMIT_MIN = 88888888;
var IR_SNAPSHOT_LIMIT_MAX = 0;
var IR_SNAPSHOT_ALL_MIN = 88888888;
var IR_SNAPSHOT_ALL_MAX = 0;

var limitMonths = -3;

$(document).ready(function() {
	draw3mHSChartOnIRSnapshot();
	loadCompanyQuote();
	loadMostNews();
	loadReports();
});

function draw3mHSChartOnIRSnapshot(){
	if(PRICES_LIMIT.length == 0) {
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getLimitHistoricalPriceAjax.shtml",
			data : "symbol=VND&limitMonths=" + limitMonths,
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max in 3 months
						if(IR_SNAPSHOT_LIMIT_MIN >= stockData[i].close){
							IR_SNAPSHOT_LIMIT_MIN = stockData[i].close;
						}
						if(IR_SNAPSHOT_LIMIT_MAX <= stockData[i].close){
							IR_SNAPSHOT_LIMIT_MAX = stockData[i].close;
						}

						PRICES_LIMIT.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_draw3mHSChartOnIRSnapshot();
				}
			}
		});
	} else {
		_draw3mHSChartOnIRSnapshot();
	}
}

function drawAllHSChartOnIRSnapshot(){
	if(PRICES_ALL.length == 0) {
		$.ajax( {
			type : "POST",
			dataType : "json",
			url : $.web_resource.getContext()
					+ "ajax/analysis/getHistoricalPriceAjax.shtml",
			data : "symbol=VND",
			success : function(data) {
				if(data.model.highStock != null && data.model.highStock.data.length > 0){
					var stockData = data.model.highStock.data;
					for ( var i = 0; i < stockData.length; i++) {
						// find min, max in all
						if(IR_SNAPSHOT_ALL_MIN >= stockData[i].close){
							IR_SNAPSHOT_ALL_MIN = stockData[i].close;
						}
						if(IR_SNAPSHOT_ALL_MAX <= stockData[i].close){
								IR_SNAPSHOT_ALL_MAX = stockData[i].close;
						}
						PRICES_ALL.push( [ stockData[i].transDate, stockData[i].close ]);
					}
					_drawAllHSChartOnIRSnapshot();
				}
			}
		});
	} else {
		_drawAllHSChartOnIRSnapshot();
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
				$('.ir-snapshot-left1 .companyQuote #currentPrice').html(quote.currentPrice);
				var changeClass="down";
				if(quote.todayChangePrice >= 0){
					changeClass="up";
				}
				$('.ir-snapshot-left1 .companyQuote #strTodayChange').html(quote.strTodayChange + ' (' + quote.strTodayChangePercent + ' %)');
				$('.ir-snapshot-left1 .companyQuote #strTodayChange').addClass(changeClass);
				$('.ir-snapshot-left1 .companyQuote #strTotalVolumn').html(quote.strTotalVolumn);
				$('.ir-snapshot-left1 .companyQuote #strMarketIndex').html(extQuote.strMarketCapital);
				$('.ir-snapshot-left1 .companyQuote #str52WeekHighestPrice').html(extQuote.strWeekHigh);
				$('.ir-snapshot-left1 .companyQuote #str52WeekLowestPrice').html(extQuote.strWeekLow);
				$('.ir-snapshot-left1 .companyQuote #strOpenPrice').html(quote.strOpenPrice);
				$('.ir-snapshot-left1 .companyQuote #strClosePrice').html(quote.strClosePrice == "0.0" ? "-" : quote.strClosePrice);
			}
		}
	});
}
function loadMostNews(){
	$.ajax( {
		type : "POST",
		dataType : "json",
		url : $.web_resource.getContext()
				+ "ajax/irSnapshot/getMostNews.shtml",
		success : function(data) {
			if(data.model.mostViewResult != null){
				var strContent = "";
				$.each(data.model.mostViewResult, function(i, newsinfo){
            		strContent += '<li class="n_other_news_list">';
            		strContent += '<a href="' + newsinfo.urlDetail + '">' 
            					+ newsinfo.newsHeader + ' (' + newsinfo.newsResource +')</a>' + '<span class="newsDate"> - ' 
            					+ $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
            		strContent += '</li>';
     			});
				$('.ir-snapshot-left2 .mostnews #contents').html(strContent);
			}
		}
	});
}
function loadReports(){
	$.ajax( {
		type : "POST",
		dataType : "json",
		url : $.web_resource.getContext()
				+ "ajax/irSnapshot/getReports.shtml",
		success : function(data) {
			var results = data.model.ifoDocumentResult;
			if(results != null){
				var strContent = "";
				$.each(results, function(i, result){
            		strContent += '<li class="n_other_news_list">';
            		strContent += '<span>' + $.web_utils.dateFormat2Show(result.releasedDate, 'dd/mm/yyyy') + '</span>';
            		var url = '<a href=' + "'" + 'javascript:download("' + result.filePath + '", "research", "' + result.fileName + '")' + "'>";
            		url += result.title + "</a>";
            		url += '<span class="newsDate"> - (Nhấn vào để tải về)</span>';
            		strContent += url;
            		strContent += '</li>';
     			});
				$('.ir-snapshot-left2 .reports #contents').html(strContent);
			}
		}
	});
}
function _draw3mHSChartOnIRSnapshot() {
	var yAxisStep = getTickInterval(IR_SNAPSHOT_LIMIT_MAX, IR_SNAPSHOT_LIMIT_MIN);
	var chartOptions = {
        chart : {
            renderTo : 'hsChartOnSnapshot',
            alignTicks: false, // for mutiple series
            spacingLeft: 0, // css
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
            min: IR_SNAPSHOT_LIMIT_MIN,
            max: IR_SNAPSHOT_LIMIT_MAX,
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
		    },
        }]
    };

    chartOnSnapShot = new Highcharts.StockChart(chartOptions);
    chartOnSnapShot.rangeSelector.destroy();
}
function _drawAllHSChartOnIRSnapshot(){
	var yAxisStep = getTickInterval(IR_SNAPSHOT_ALL_MAX, IR_SNAPSHOT_ALL_MIN);
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
            min: IR_SNAPSHOT_ALL_MIN,
            max: IR_SNAPSHOT_ALL_MAX,
        }],
        series: [{
            type: 'line',
            name: 'VND',
            data: PRICES_ALL,
            tooltip: {
                valueDecimals: 2
            },
            color: '#F39200',
            dataGrouping: {
		        enabled: false
		    },
        }]
    };

    chartOnSnapShot = new Highcharts.StockChart(chartOptions);
}