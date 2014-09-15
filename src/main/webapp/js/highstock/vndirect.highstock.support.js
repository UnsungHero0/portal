var firstClick = true;
var firstData = [];
function changeChartType(obj){
	closeChartTypeList();
	if(HS_MODE == 'compare'){
		resetCompareDisplayList();	
	}
	CHART_TYPE = obj.attr('ref');
	redrawYAxisChartAfterDelete();
}
function startLoadingHighStock(){
	$('#hsDefaultContainerLoading').addClass('hsDefaultContainerLoading');
}
function stopLoadingHighStock(){
	$('#hsDefaultContainerLoading').removeClass('hsDefaultContainerLoading');
}
function generateDefaultChart(){
	var defaultData = PRICES;
	if(CHART_TYPE == 'candlestick' || CHART_TYPE == 'ohlc'){
		defaultData = PRICES_FULL;
	}
	var chartOptions = {
		chart : {
            renderTo : 'hsDefaultContainer',
            reflow: true, // auto fit size
            alignTicks: false, // for mutiple series
            spacingLeft: -5, // css
            borderWidth: 1,
        },
        // remove highCharts.com at bottom
        credits: {
            enabled: false
        },
        // zoom button
        rangeSelector : {
            selected : 1
        },
        title : {
            text : SYMBOL + ' Stock Price'
        },
        
        yAxis: [{
            title: {
                text: 'Price'
            },
            height: 388,
            lineWidth: 2,
            minorTickInterval: 'auto',
			minorGridLineColor: '#E6E6E6',
			//tickInterval: yAxisStep,
        }, {
        	title: {
                text: 'Volume'
            },
            top: 488, //488
            height: 100,
            offset: 0,
            lineWidth: 2,
            minorTickInterval: 'auto',
			minorGridLineColor: '#E6E6E6',
        }],
        
        series: [{
            type: CHART_TYPE,
            name: SYMBOL,
            data: defaultData,
            tooltip: {
				valueDecimals: 2
			},
            id: SYMBOL + 'price',
            fillColor : {
				linearGradient : {
					x1: 0, 
					y1: 0, 
					x2: 0, 
					y2: 1
				},
				stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
			},
			dataGrouping: {
		        enabled: false
		    },
        }, {
            type: 'column',
            name: 'Volume',
            data: VOLUMES,
            id: SYMBOL + 'volume',
            yAxis: 1,
			dataGrouping: {
		        enabled: false
		    }
        }],
        
        navigation: {
	        buttonOptions: {
	            align: 'left'
	        }
        },
        exporting: {
	        buttons: {
	            exportButton: {
	                x: 25 // position
	            },
	            printButton: {
	                x: 55 // position
	            }
	        }
        }
	};
	
	if(CHART_TYPE == 'candlestick' || CHART_TYPE == 'ohlc'){
		chartOptions.series[0].color = 'red';
		chartOptions.series[0].upColor = 'green';
	}
	
	return chartOptions;
}
/** In case add more one Y-axis */
function generateMoreYAxis(yAxis, isUpdate){
	var yAxisVolumn = 2 + MORE_YAXIS_LIST.length;
	if(isUpdate){
		yAxisVolumn -= 1;
	}
	var priceData = PRICES;
	var priceColor = '';
	var priceUpcolor = '';
	if(CHART_TYPE == 'candlestick' || CHART_TYPE == 'ohlc'){
		priceData = PRICES_FULL;
		priceColor = 'red';
		priceUpcolor = 'green';
	}
	var newICHARToptions = {
		chart : {
            renderTo : 'hsDefaultContainer',
            reflow: true, // auto fit size
            alignTicks: false, // for mutiple series
            spacingLeft: -5, // css
            borderWidth: 1
           // zoomType: 'x' // set zoom type
        },
        
        // remove highCharts.com at bottom
        credits: {
            enabled: false
        },

        rangeSelector : {
            selected : 1
        },

        title : {
            text : SYMBOL + ' Stock Price'
        },
        
        yAxis: yAxis,
        
        series: [{
            type: CHART_TYPE,
            name: SYMBOL,
            data: priceData,
            tooltip: {
				valueDecimals: 2
			},
            id: SYMBOL,
            color: priceColor,
            upColor: priceUpcolor,
			dataGrouping: {
		        enabled: false
		    }
        }, {
            type: 'column',
            name: 'Volume',
            data: VOLUMES,
            id: SYMBOL + 'volume',
            yAxis: yAxisVolumn,
			dataGrouping: {
		        enabled: false
		    }
        }],
        
        navigation: {
	        buttonOptions: {
	            align: 'left'
	        }
        },
        exporting: {
	        buttons: {
	            exportButton: {
	                x: 25 // position
	            },
	            printButton: {
	                x: 55 // position
	            }
	        }
        }
    };
	
	return newICHARToptions;
}
function generateCompareOptions(serieses){
	var newICHARToptions = {
	    chart : {
            renderTo : 'hsDefaultContainer',
            reflow: true, // auto fit size
            alignTicks: false, // for mutiple series
            spacingLeft: -5, // css
            borderWidth: 1,
            //zoomType: 'y' // set zoom type
        },
        
        // remove highCharts.com at bottom
        credits: {
            enabled: false
        },

	    rangeSelector: {
            selected : 1
	    },
	    
	    title : {
            text : 'Compare ' + SYMBOL + ' Stock Price'
        },
        
        yAxis: {
            title: {
                text: 'Compare'
            },
            height: 388,
            lineWidth: 2,
            labels: {
	    		formatter: function() {
	    			return (this.value > 0 ? '+' : '') + this.value + '%';
	    		}
	    	},
	    	plotLines: [{
	    		value: 0,
	    		width: 2,
	    		color: 'red'
	    	}]
        },

	    plotOptions: {
	    	series: {
	    		compare: 'percent'
	    	}
	    },
	    
	    tooltip: {
	    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
	    	valueDecimals: 2
	    },
	    
	    series: serieses,
	    
	    navigation: {
	        buttonOptions: {
	            align: 'left'
	        }
        },
        exporting: {
	        buttons: {
	            exportButton: {
	                x: 25 // position
	            },
	            printButton: {
	                x: 55 // position
	            }
	        }
        }
	};
	
	return newICHARToptions;
}
function renderFunctionCompareList(id){
	var obj = $('.hsNavigator .hsDisplayList #compare' + id);
	var name = obj.html();
	obj.css('background-color', COMPARE_CHART_COLORS[LIST_COMPARE_SYMBOL.indexOf(id)]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html('Gỡ bỏ');
	}, function(){
		$(this).css('background-color', COMPARE_CHART_COLORS[LIST_COMPARE_SYMBOL.indexOf(id)]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	obj.click(function(){
		// remove display btn
		$('.hsNavigator .hsDisplayList #compare' + id).remove();
		// remove from compare list
		removeSymbolFromCompareList(id);
		// remove from exist compare series
		EXIST_COMPARE_SERIES[id] = null;
		// remove chart
		removeAChartByID('compare' + id);
	});
}
function renderFunctionSMAList(id){
	var obj = $('.hsNavigator .hsDisplayList #SMA' + id);
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', SMA_CHART_COLORS[LIST_SMA_PERIOD.indexOf(id)]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', SMA_CHART_COLORS[LIST_SMA_PERIOD.indexOf(id)]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	obj.click(function(){
		// remove display
		$('.hsNavigator .hsDisplayList #SMA' + id).remove();
		// remove from exist all seires list
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'SMA' + id);
		EXIST_ALL_SERIES_NAME = removeBlankValueFromList(EXIST_ALL_SERIES_NAME);
		// remove from SMA list
		LIST_SMA_PERIOD = removeFromMAList(LIST_SMA_PERIOD, id);
		// remove from chart
		removeAChartByID('SMA' + id);
	});
}
function renderFunctionEMAList(id){
	var obj = $('.hsNavigator .hsDisplayList #EMA' + id);
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', EMA_CHART_COLORS[LIST_EMA_PERIOD.indexOf(id)]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', EMA_CHART_COLORS[LIST_EMA_PERIOD.indexOf(id)]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	obj.click(function(){
		// remove display
		$('.hsNavigator .hsDisplayList #EMA' + id).remove();
		// remove from exist all seires list
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'EMA' + id);
		EXIST_ALL_SERIES_NAME = removeBlankValueFromList(EXIST_ALL_SERIES_NAME);
		// remove from EMA list
		LIST_EMA_PERIOD = removeFromMAList(LIST_EMA_PERIOD, id);
		// remove from chart
		removeAChartByID('EMA' + id);
	});
}
function renderFunctionBBands(){
	var obj = $('.hsNavigator .hsDisplayList #bbands');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', B_BANDS_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', B_BANDS_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	obj.click(function(){
		// remove display
		$('.hsNavigator .hsDisplayList #bbands').remove();
		// remove from exist all seires list
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'BBandsTop');
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'BBandsBottom');
		BBANDS_EXIST_NAME = '';
		// remove from chart
		removeAChartByID('bbandsTop');
		removeAChartByID('bbandsBottom');
	});
}
function renderFunctionMFI(){
	var obj = $('.hsNavigator .hsDisplayList #mfi');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', MFI_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', MFI_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #mfi').remove();
		MFI_EXIST_PERIOD = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'MFI');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionMACD(){
	var obj = $('.hsNavigator .hsDisplayList #macd');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', MACD_CHART_COLOR[3]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', MACD_CHART_COLOR[3]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #macd').remove();
		MACD_EXIST_NAME = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'MACD');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionPSAR(){
	var obj = $('.hsNavigator .hsDisplayList #psar');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', PSAR_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', PSAR_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove/clear/redraw data
	obj.click(function(){
		// remove display
		$('.hsNavigator .hsDisplayList #psar').remove();
		// remove from exist all seires list
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'PSAR');
		PSAR_EXIST_NAME = '';
		// remove from chart
		removeAChartByID('psar');
	});
}
function renderFunctionROC(){
	var obj = $('.hsNavigator .hsDisplayList #roc');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', ROC_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', ROC_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #roc').remove();
		ROC_EXIST_PERIOD = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'ROC');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionRSI(){
	var obj = $('.hsNavigator .hsDisplayList #rsi');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', RSI_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', RSI_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #rsi').remove();
		RSI_EXIST_PERIOD = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'RSI');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionSS(){
	var obj = $('.hsNavigator .hsDisplayList #ss');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', SS_CHART_COLOR[2]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', SS_CHART_COLOR[2]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #ss').remove();
		SS_EXIST_NAME = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'SS');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionFS(){
	var obj = $('.hsNavigator .hsDisplayList #fs');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', FS_CHART_COLOR[2]);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', FS_CHART_COLOR[2]);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #fs').remove();
		FS_EXIST_NAME = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'FS');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionVMA(){
	var obj = $('.hsNavigator .hsDisplayList #vma');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', VMA_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', VMA_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #vma').remove();
		VMA_EXIST_NAME = '';
		EXIST_VOLUME_MA = '';
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionWR(){
	var obj = $('.hsNavigator .hsDisplayList #wr');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', WR_CHART_COLOR);
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', WR_CHART_COLOR);
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #wr').remove();
		WR_EXIST_PERIOD = '';
		removeFromExistAllSeries(MORE_YAXIS_LIST, EXIST_YAXIS_ALL_SERIES, 'WR');
		MORE_YAXIS_LIST = removeBlankValueFromList(MORE_YAXIS_LIST);
		
		//redraw yaxis
		redrawYAxisChartAfterDelete();
	});
}
function renderFunctionDividends(){
	var obj = $('.hsNavigator .hsDisplayList #dividends');
	var name = obj.html();
	var w = obj.width();
	obj.css('background-color', '#009BE3');
	obj.hover(function(){
		$(this).css('background-color', '#DCDCDC');
		$(this).css('color', '#696969');
		$(this).html(' Gỡ bỏ ');
		$(this).css('width', w);
	}, function(){
		$(this).css('background-color', '#009BE3');
		$(this).css('color', '#fff');
		$(this).html(name);
	});
	
	// remove
	obj.click(function(){
		$('.hsNavigator .hsDisplayList #dividends').remove();
		removeFromExistAllSeries(EXIST_ALL_SERIES_NAME, EXIST_ALL_SERIES, 'DIVIDENDS');

		// remove dividends serie from chart
		var allSeries = HS_CHART.series;
		for(var i = 0; i < allSeries.length; i ++){
			if(allSeries[i].type == 'flags'){
				HS_CHART.series[i].remove();
				isDisplayedDividends = false;
			}
		}
	});
}
function removeAChartByID(id){
	HS_CHART.get(id).remove();
}
/** to remove item from compare chart, smachart.. vv. */
function removeSymbolFromCompareList(symbol){
	var newlist = [];
	for(var i = 0; i < LIST_COMPARE_SYMBOL.length; i++){
		if(LIST_COMPARE_SYMBOL[i] != symbol){
			newlist.push(LIST_COMPARE_SYMBOL[i]);
		} else {
			newlist.push('remove');
		}
	}
	
	// re-assign compare list
	LIST_COMPARE_SYMBOL = newlist;
}

function resetHighStock(){
	$('.hsNavigator .hsDisplayList').html('');
	HS_MODE = 'default';
	LIST_COMPARE_SYMBOL = [];
	LIST_SMA_PERIOD = [];
	LIST_EMA_PERIOD = [];
 	BBANDS_EXIST_NAME = '';
 	MFI_EXIST_PERIOD = '';
 	WR_EXIST_PERIOD = '';
 	ROC_EXIST_PERIOD = '';
 	RSI_EXIST_PERIOD = '';
 	MACD_EXIST_NAME = '';
 	SS_EXIST_NAME = '';
 	FS_EXIST_NAME = '';
 	VMA_EXIST_NAME = '';
 	PSAR_EXIST_NAME = '';
 	EXIST_COMPARE_SERIES = new Object;
 	EXIST_ALL_SERIES = new Object;
 	EXIST_ALL_SERIES_NAME = [];
 	EXIST_YAXIS_ALL_SERIES = new Object();
 	MORE_YAXIS_LIST = [];
 	EXIST_VOLUME_MA = '';
 	CHART_TYPE = 'candlestick';
	
	HS_CHART.destroy();
}
function isAvaialbeSymbolForCompare(symbolToCompare){
	if(symbolToCompare == SYMBOL){
		return false;
	}
	
	if(LIST_COMPARE_SYMBOL.length == 0){
		return true;
	}
	
	if(LIST_COMPARE_SYMBOL.indexOf('remove') != -1){
		return true;
	}
	
	if(LIST_COMPARE_SYMBOL.length == 5){
		return false;
	}
	
	// check if this symbol is not in the compare list
	if (LIST_COMPARE_SYMBOL.indexOf(symbolToCompare) == -1){
		return true;
	}
	
	return false;
}
function addSymbolToCompareList(symbolToCompare){
	if(isAvaialbeSymbolForCompare(symbolToCompare)){
		if(LIST_COMPARE_SYMBOL.indexOf('remove') != -1){
			var index = LIST_COMPARE_SYMBOL.indexOf('remove');
			LIST_COMPARE_SYMBOL[index] = symbolToCompare;
		} else {
			LIST_COMPARE_SYMBOL.push(symbolToCompare);
		}
	}
}
function removeFromMAList(list, period){
	var newlist = [];
	for(var i = 0; i < list.length; i++){
		if(list[i] != period){
			newlist.push(list[i]);
		}
	}
	
	return newlist;
}
function removeFromExistAllSeries(listName, listData, name){
	if(listName.indexOf(name) != -1){
		listData[name] = null;
		listName[listName.indexOf(name)] = '';
	}
}
function removeBlankValueFromList(list){
	if(list.length > 0){
		var newlist = [];
		for(var i = 0; i < list.length; i++){
			if(list[i] != ''){
				newlist.push(list[i]);
			}
		}

		return newlist;
	}
	
	return list;
}
function drawNewYAxisChart(newTitle, newSeries){
	HS_CHART.destroy();
	var newYAxisOpt = [];
	// add first
	newYAxisOpt.push({
        title: {
            text: 'Price'
        },
        height: 388,
        lineWidth: 2,
		dataGrouping: {
	        enabled: false
	    },
	    minorTickInterval: 'auto',
		minorGridLineColor: '#E6E6E6',
		//tickInterval: yAxisStep,
    });
	
	if(MORE_YAXIS_LIST.length == 0){
		newYAxisOpt.push({
	    	title: {
	            text: newTitle
	        },
	        top: 488,
	        height: 80,
	        offset: 0,
	        lineWidth: 2,
			dataGrouping: {
		        enabled: false
		    }
		});
	} else // add exist then add new
	{
		// add exist
		for(var i = 0; i < MORE_YAXIS_LIST.length; i++){
			newYAxisOpt.push({
		    	title: {
		            text: MORE_YAXIS_LIST[i]
		        },
		        top: 388 + 100 * (i + 1),
		        height: 80,
		        offset: 0,
		        lineWidth: 2,
				dataGrouping: {
			        enabled: false
			    }
			});
		}
		
		// add new
		newYAxisOpt.push({
	    	title: {
	            text: newTitle
	        },
	        top: 388 + 100 * (MORE_YAXIS_LIST.length + 1),
	        height: 80,
	        offset: 0,
	        lineWidth: 2,
			dataGrouping: {
		        enabled: false
		    }
		});
	}
	
	// add last
	newYAxisOpt.push({
    	title: {
            text: 'Volume'
        },
        top: 388 + 100 * (MORE_YAXIS_LIST.length + 2),
        height: 100,
        offset: 0,
        lineWidth: 2,
		dataGrouping: {
	        enabled: false
	    }
	});
	
	// end change
	var newMinHeight = (588 + 100 * (MORE_YAXIS_LIST.length + 2)) + 'px';
	$('#hsDefaultContainer').css('min-height', newMinHeight);
	HS_CHART = new Highcharts.StockChart(generateMoreYAxis(newYAxisOpt, false));
	
	// apply the date pickers
    registerHSChartCalendarPicker();
	
	HS_CHART.addSeries(newSeries);
	
	// add exist yaxis
	addAllExistYAxisSeriesToChart();
	
	// add exist series
	addAllExistSeriseToChart();
	
	updateVolumeMAIfExist(false);
}
function redrawYAxisChartAfterDelete(){
	//var yAxisStep = getYAxisStep(MAX_IN_THREE_MONTH, MIN_IN_THREE_MONTH);
	HS_CHART.destroy();
	var newYAxisOpt = [];
	// add first
	newYAxisOpt.push({
        title: {
            text: 'Price'
        },
        height: 388,
        lineWidth: 2,
		dataGrouping: {
	        enabled: false
	    },
	    minorTickInterval: 'auto',
		minorGridLineColor: '#E6E6E6',
		//tickInterval: yAxisStep,
    });
	// add exist
	if(MORE_YAXIS_LIST.length > 0){
		for(var i = 0; i < MORE_YAXIS_LIST.length; i++){
			newYAxisOpt.push({
		    	title: {
		            text: MORE_YAXIS_LIST[i]
		        },
		        top: 388 + 100 * (i+1),
		        height: 80,
		        offset: 0,
		        lineWidth: 2,
            	minorTickInterval: 'auto',
				minorGridLineColor: '#E6E6E6',
				dataGrouping: {
			        enabled: false
			    }
			});
		}
	} 
	// add last
	newYAxisOpt.push({
    	title: {
            text: 'Volume'
        },
        top: 388 + 100 * (MORE_YAXIS_LIST.length + 1),
        height: 100,
        offset: 0,
        lineWidth: 2,
        minorTickInterval: 'auto',
		minorGridLineColor: '#E6E6E6',
		dataGrouping: {
	        enabled: false
	    }
	});
	
	// end change
	var newMinHeight = (588 + 100 * (MORE_YAXIS_LIST.length + 1)) + 'px';
	$('#hsDefaultContainer').css('min-height', newMinHeight);
	HS_CHART = new Highcharts.StockChart(generateMoreYAxis(newYAxisOpt, true));
	
	// apply the date pickers
    registerHSChartCalendarPicker();
	
	// add exist series
	addAllExistSeriseToChart();
	
	// update YAxis position
	if(MORE_YAXIS_LIST.length > 0){
		updateYAxisPosition();
	}
	// add exist yaxis
	addAllExistYAxisSeriesToChart();
	
	updateVolumeMAIfExist(true);
}
function addAllExistSeriseToChart(){
	if(EXIST_ALL_SERIES_NAME.length > 0){
		for(var i = 0; i < EXIST_ALL_SERIES_NAME.length; i++){
			if(EXIST_ALL_SERIES_NAME[i] != ''){
				HS_CHART.addSeries(EXIST_ALL_SERIES[EXIST_ALL_SERIES_NAME[i]]);
			}
		}
	}
}
function addAllExistYAxisSeriesToChart(){
	if(MORE_YAXIS_LIST.length > 0){
		for(var i = 0; i < MORE_YAXIS_LIST.length; i++){
			var series = EXIST_YAXIS_ALL_SERIES[MORE_YAXIS_LIST[i]];
			if(series != null && series.length > 0){
				for(var j = 0; j < series.length; j ++){
					HS_CHART.addSeries(series[j]);		
				}
			}
		}
	}
}
function updateYAxisPosition(){
	if(MORE_YAXIS_LIST.length > 0){
		for(var i = 0; i < MORE_YAXIS_LIST.length; i++){
			var series = EXIST_YAXIS_ALL_SERIES[MORE_YAXIS_LIST[i]];
			var newSerieses = [];
			if(series != null && series.length > 0){
				for(var j = 0; j < series.length; j ++){
					var s = series[j];
					// update yAxis
					s.yAxis = i + 1;
					newSerieses.push(s);
				}
			}
			EXIST_YAXIS_ALL_SERIES[MORE_YAXIS_LIST[i]] = newSerieses;
		}
	}
}
function updateVolumeMAIfExist(isUpdate){
	if(EXIST_VOLUME_MA != ''){
		var yAxis = 2 + MORE_YAXIS_LIST.length;
		if(isUpdate){
			yAxis -=1;
		}
		var vmaSeries = EXIST_ALL_SERIES['VMA'];
		vmaSeries.yAxis = yAxis;
		HS_CHART.addSeries(vmaSeries);
	}
}
function isValidPeriod(period){
	if(period == 'undefined' || period.trim() == '' || isNaN(period.trim())){
		return false;
	}
	
	return true;
}
function resetCompareDisplayList(){
	if(HS_MODE == 'compare'){
		if(LIST_COMPARE_SYMBOL.length > 0){
			$('.hsNavigator .hsDisplayList #compare' + SYMBOL + 'volume').remove();
			for(var i=0; i<LIST_COMPARE_SYMBOL.length; i++){
				$('.hsNavigator .hsDisplayList #compare' + LIST_COMPARE_SYMBOL[i]).remove();
			}
		}
		LIST_COMPARE_SYMBOL = [];
		HS_MODE = 'default';
	} else if(HS_MODE == 'default'){
		resetHighStock();
	}
}
function registerHSChartCalendarPicker(){
	setTimeout(function(){
        $('input.highcharts-range-selector', $('#'+HS_CHART.options.chart.renderTo))
            .datepicker();
    },0);
}
function createDividendsSerie(){
	var eventSeries = [];
	if(EVENTS.length > 0){
		var eventData = [];
		for(var i = 0; i < EVENTS.length; i++){
			eventData.push({
				x : EVENTS[i][0],
				title : 'D',
				text : "Cổ tức bằng tiền"
			});
		}
		
		eventSeries = {
			type: 'flags',
	        data: eventData,
	        tooltip: {
				valueDecimals: 2
			},
			color : '#5F86B3',
			fillColor : '#5F86B3',
			onSeries : SYMBOL + 'price',
			shape : 'circlepin',
			width : 16,
			style : {// text style
				color : 'white'
			},
			states : {
				hover : {
					fillColor : '#395C84' // darker
				}
			}
		};
	}
	
	
	return eventSeries;
}
var uagent = navigator.userAgent.toLowerCase();
var ipadDevice = "ipad";
var iphoneDevice = "iphone";
var androidDevice = "android";
var windowPhone7Device = "windows phone os 7";
var blackberryDevice = "blackberry";
var tablet = "tablet";
function detectDevice(){
	if(uagent.search(ipadDevice) > -1 || uagent.search(iphoneDevice) > -1
			|| uagent.search(androidDevice) > -1 || uagent.search(windowPhone7Device) > -1
			|| uagent.search(blackberryDevice) > -1 || uagent.search(tablet) > -1){
		return true;
	} else return false;
}

