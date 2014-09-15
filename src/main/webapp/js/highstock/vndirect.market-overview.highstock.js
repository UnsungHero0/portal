var PRICES = [];
//var VOLUMES = [];
//var MARKET_INDEX = 1;
var MARKET_MIN_IN_THREE_MONTHS = 88888888;
var MARKET_MAX_IN_THREE_MONTHS = 0;
var MARKET_MIN_IN_ALL = 88888888;
var MARKET_MAX_IN_ALL = 0;
/** MO = Market Overview */
function loadMOHOSEChart(){
	//MARKET_INDEX = changeIndex;
	var symbol = 'VNINDEX';
	PRICES = [];
	VOLUMES = [];
    $.ajax( {
        type : "POST",
        dataType : "json",
        url : $.web_resource.getContext()
                + "ajax/analysis/getHistoricalPriceAjax.shtml",
        data : "symbol=" + symbol,
        success : function(data) {
	    	if(data.model.highStock != null && data.model.highStock.data.length > 0){
	    		MARKET_MIN_IN_THREE_MONTHS = 88888888;
				MARKET_MAX_IN_THREE_MONTHS = 0;
				MARKET_MIN_IN_ALL = 88888888;
				MARKET_MAX_IN_ALL = 0;
	            var stockData = data.model.highStock.data;
	            for ( var i = 0; i < stockData.length; i++) {
	            	// find min, max
					if (stockData.length - i <= 90) {
						if (MARKET_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							MARKET_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (MARKET_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							MARKET_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
					}
					if (MARKET_MIN_IN_ALL >= stockData[i].close) {
						MARKET_MIN_IN_ALL = stockData[i].close;
					}
					if (MARKET_MAX_IN_ALL <= stockData[i].close) {
						MARKET_MAX_IN_ALL = stockData[i].close;
					}
			
					// set data
	                PRICES.push( [ stockData[i].transDate, stockData[i].close ]);
	               // VOLUMES.push( [ stockData[i].transDate, stockData[i].volume ]);
	            }
	            draw3mHSChartOnSnapshot('hoseHSMarketOverview', 'HOSE');
	        }
        }
    });
}
function loadMOVN30Chart(){
	//MARKET_INDEX = changeIndex;
	var symbol = 'VN30';

	PRICES = [];
	VOLUMES = [];
    $.ajax( {
        type : "POST",
        dataType : "json",
        url : $.web_resource.getContext()
                + "ajax/analysis/getHistoricalPriceAjax.shtml",
        data : "symbol=" + symbol,
        success : function(data) {
	    	if(data.model.highStock != null && data.model.highStock.data.length > 0){
	    		MARKET_MIN_IN_THREE_MONTHS = 88888888;
				MARKET_MAX_IN_THREE_MONTHS = 0;
				MARKET_MIN_IN_ALL = 88888888;
				MARKET_MAX_IN_ALL = 0;
	            var stockData = data.model.highStock.data;
	            for ( var i = 0; i < stockData.length; i++) {
	            	// find min, max
					if (stockData.length - i <= 90) {
						if (MARKET_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							MARKET_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (MARKET_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							MARKET_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
					}
					if (MARKET_MIN_IN_ALL >= stockData[i].close) {
						MARKET_MIN_IN_ALL = stockData[i].close;
					}
					if (MARKET_MAX_IN_ALL <= stockData[i].close) {
						MARKET_MAX_IN_ALL = stockData[i].close;
					}
			
					// set data
	                PRICES.push( [ stockData[i].transDate, stockData[i].close ]);
	                //VOLUMES.push( [ stockData[i].transDate, stockData[i].volume ]);
	            }
	            draw3mHSChartOnSnapshot('vn30HSMarketOverview', 'VN30');
            }
        }
    });
}
function loadMOHNXChart(){
	//MARKET_INDEX = changeIndex;
	var symbol = 'HNX';

	PRICES = [];
	VOLUMES = [];
    $.ajax( {
        type : "POST",
        dataType : "json",
        url : $.web_resource.getContext()
                + "ajax/analysis/getHistoricalPriceAjax.shtml",
        data : "symbol=" + symbol,
        success : function(data) {
	    	if(data.model.highStock != null && data.model.highStock.data.length > 0){
	    		MARKET_MIN_IN_THREE_MONTHS = 88888888;
				MARKET_MAX_IN_THREE_MONTHS = 0;
				MARKET_MIN_IN_ALL = 88888888;
				MARKET_MAX_IN_ALL = 0;
	            var stockData = data.model.highStock.data;
	            for ( var i = 0; i < stockData.length; i++) {
	            	// find min, max
					if (stockData.length - i <= 90) {
						if (MARKET_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							MARKET_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (MARKET_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							MARKET_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
					}
					if (MARKET_MIN_IN_ALL >= stockData[i].close) {
						MARKET_MIN_IN_ALL = stockData[i].close;
					}
					if (MARKET_MAX_IN_ALL <= stockData[i].close) {
						MARKET_MAX_IN_ALL = stockData[i].close;
					}
			
					// set data
	                PRICES.push( [ stockData[i].transDate, stockData[i].close ]);
	               // VOLUMES.push( [ stockData[i].transDate, stockData[i].volume ]);
	            }
	            draw3mHSChartOnSnapshot('hnxHSMarketOverview', 'HNX');
	        }
        }
    });
}
function loadMOHNX30Chart(){
	//MARKET_INDEX = changeIndex;
	var symbol = 'HNX30';

	PRICES = [];
	VOLUMES = [];
    $.ajax( {
        type : "POST",
        dataType : "json",
        url : $.web_resource.getContext()
                + "ajax/analysis/getHistoricalPriceAjax.shtml",
        data : "symbol=" + symbol,
        success : function(data) {
	    	if(data.model.highStock != null && data.model.highStock.data.length > 0){
	    		MARKET_MIN_IN_THREE_MONTHS = 88888888;
				MARKET_MAX_IN_THREE_MONTHS = 0;
				MARKET_MIN_IN_ALL = 88888888;
				MARKET_MAX_IN_ALL = 0;
	            var stockData = data.model.highStock.data;
	            for ( var i = 0; i < stockData.length; i++) {
	            	// find min, max
					if (stockData.length - i <= 90) {
						if (MARKET_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							MARKET_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (MARKET_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							MARKET_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
					}
					if (MARKET_MIN_IN_ALL >= stockData[i].close) {
						MARKET_MIN_IN_ALL = stockData[i].close;
					}
					if (MARKET_MAX_IN_ALL <= stockData[i].close) {
						MARKET_MAX_IN_ALL = stockData[i].close;
					}
			
					// set data
	                PRICES.push( [ stockData[i].transDate, stockData[i].close ]);
	               // VOLUMES.push( [ stockData[i].transDate, stockData[i].volume ]);
	            }
	            draw3mHSChartOnSnapshot('hnx30HSMarketOverview', 'HNX30');
            }
        }
    });
}
function loadMOUPCOMChart(){
	//MARKET_INDEX = changeIndex;
	var symbol = 'UPCOM';

	PRICES = [];
	VOLUMES = [];
    $.ajax( {
        type : "POST",
        dataType : "json",
        url : $.web_resource.getContext()
                + "ajax/analysis/getHistoricalPriceAjax.shtml",
        data : "symbol=" + symbol,
        success : function(data) {
	    	if(data.model.highStock != null && data.model.highStock.data.length > 0){
	    		MARKET_MIN_IN_THREE_MONTHS = 88888888;
				MARKET_MAX_IN_THREE_MONTHS = 0;
				MARKET_MIN_IN_ALL = 88888888;
				MARKET_MAX_IN_ALL = 0;
	            var stockData = data.model.highStock.data;
	            for ( var i = 0; i < stockData.length; i++) {
	            	// find min, max
					if (stockData.length - i <= 90) {
						if (MARKET_MIN_IN_THREE_MONTHS >= stockData[i].close) {
							MARKET_MIN_IN_THREE_MONTHS = stockData[i].close;
						}
						if (MARKET_MAX_IN_THREE_MONTHS <= stockData[i].close) {
							MARKET_MAX_IN_THREE_MONTHS = stockData[i].close;
						}
					}
					if (MARKET_MIN_IN_ALL >= stockData[i].close) {
						MARKET_MIN_IN_ALL = stockData[i].close;
					}
					if (MARKET_MAX_IN_ALL <= stockData[i].close) {
						MARKET_MAX_IN_ALL = stockData[i].close;
					}
			
					// set data
	                PRICES.push( [ stockData[i].transDate, stockData[i].close ]);
	              //  VOLUMES.push( [ stockData[i].transDate, stockData[i].volume ]);
	            }
	            draw3mHSChartOnSnapshot('upcomHSMarketOverview', 'UPCOM');
            }
        }
    });
}

function draw3mHSChartOnSnapshot(id, marketName) {
	var yAxisStep = getTickInterval(MARKET_MAX_IN_THREE_MONTHS, MARKET_MIN_IN_THREE_MONTHS);
	var color = '#F39200';
    var chartOptions = {
        chart : {
            renderTo : id,
            alignTicks: false, // for mutiple series
            spacingLeft: 0, // css
            spacingTop: -18,
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
             selected: 1
        },
         scrollbar: {
            enabled: false
        },
        yAxis: [{
            height: 185,
            lineWidth: 1,
            title: {text:marketName},
            minorTickInterval: 'auto',
            minorGridLineColor: '#E6E6E6',
            tickInterval: yAxisStep,
            min: MARKET_MIN_IN_THREE_MONTHS,
            max: MARKET_MAX_IN_THREE_MONTHS,
        }],
        series: [{
            type: 'line',
            name: marketName,
            data: PRICES,
            tooltip: {
                valueDecimals: 2
            },
            color: color,
            dataGrouping: {
		        enabled: false
		    }
        }]
    };

    marketOverviewChart = new Highcharts.StockChart(chartOptions);
    marketOverviewChart.rangeSelector.destroy();
}