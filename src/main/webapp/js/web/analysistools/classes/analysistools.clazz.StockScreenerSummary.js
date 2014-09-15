/*************************************************************************************
* @author  HuyLV
*
* Define newContentOption Object
* using the flowing query to extend newContentOption
*
* jQuery.extend(jQuery.newContentOption, {
* });
*
*************************************************************************************/

(function($) {
$.options = function() {};
$.extend($.options, {
	content : ".content table tbody",
	div 	: [
	    	   	{name : "#summary"},
	    	   	{name : "#pricePerformance"},
	    	   	{name : "#fundamentals"},
	    	   	{name : "#earningsDividends"},
	    	   	{name : "#technicals"}
	    	   ],
	divToLoad : null
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: StockScreenerSummary Class
*
*************************************************************************************/
function StockScreenerSummary() {
	$.options.divToLoad = $.options.div[0].name + " table tbody";
}

/*************************************************************************************
* init getOption
**************************************************************************************/
StockScreenerSummary.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
StockScreenerSummary.prototype.loadImage = function () {
	$($.options.divToLoad).empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerSummary.prototype.buildSummaryTable = function(data) {
	if (data.model) {
		try{
			$.options.divToLoad = $.options.div[0].name + " table tbody";
			$.each($.options.div, function(i, item){$(item.name).hide()});
			$($.options.div[0].name).show();
			var tbody = $($.options.div[0].name + " table tbody").empty();
			var owner = this;
			$.each(data.model.listStockScreener, function(i, item){
				tbody.append(
						'<tr>' +
							'<td align="left" class="mck">' + 
								'<span class="txtText">' +
								'<a href="' + $.web_resource.getContext() + "tong-quan/" + item.analysisIndexingBean.symbol.toLowerCase() + '.shtml">' +
									item.analysisIndexingBean.symbol + 
								'</a>' +
								'</span>' +
							'</td>' + 
							'<td align="left" class="namecompany">' + 
								'<span class="txtText">' +
								("vn" == LOCALE ? item.analysisIndexingBean.companyName : item.analysisIndexingBean.companyFullName) + 
								'</span>' +
							'</td>' + 
							'<td align="center" class="rownganh">' + 
								'<span class="txtText">' +
								'<a href="javascript:viewIndustry(\'' + item.analysisIndexingBean.symbol + '\')">' +
									item.analysisIndexingBean.industryName + 
								'</a>' +
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.analysisIndexingBean.closePrice +
								'</span>' +
							'</td>' + 
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.fiveDaysChg + //5_days_chg
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.priceChgFourWeeks + //price_chg_4_weeks
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.fiftyTwoWeekChg + //52_week_chg
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.marketCap + //market_cap
								'</span>' +
							'</td>' +
							'<td align="right" class="rowd">' +
								'<a target="_blank" href="' + $.web_resource.getContext() + 'asd/' + item.analysisIndexingBean.symbol + '.shtml">' +
								item.analysisIndexingBean.symbol +
								'</a>' +
							'</td>' +
							
						'</tr>'
				);
			})
			tbody.append(
				'<td align="right" colspan="8">'+
					'<span class="right StockSummary_Paging">'+
						'<div id="summaryNavigator" class="bt_change clearfix" style="margin-bottom:5px;margin-top:0px;"></div>'+
					'</span>'+
				'</td>'
					
			);

			
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'summaryNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerSummary.prototype.buildPricePerformanceTable = function(data) {
	if (data.model) {
		try{
			$.options.divToLoad = $.options.div[1].name + " table tbody";
			$.each($.options.div, function(i, item){$(item.name).hide()});
			$($.options.div[1].name).show();
			var tbody = $($.options.div[1].name + " table tbody").empty();
			var owner = this;
			$.each(data.model.listStockScreener, function(i, item){
				tbody.append(
						'<tr>' +
							'<td align="left" class="mck">' + 
								'<span class="txtText">' +
								'<a href="' + $.web_resource.getContext() + "tong-quan/" + item.analysisIndexingBean.symbol.toLowerCase() + '.shtml">' +
									item.analysisIndexingBean.symbol + 
								'</a>' +
								'</span>' +
							'</td>' +  
							'<td align="left" class="namecompany">' + 
								'<span class="txtText">' +
								("vn" == LOCALE ? item.analysisIndexingBean.companyName : item.analysisIndexingBean.companyFullName) + 
								'</span>' +
							'</td>' + 
							
							'<td align="right" class="rowd">' +
								'<span class="txtText">' +
								item.fiveDaysChg + //5_days_chg
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' + 
								'<span class="txtText">' +
								item.priceChgFourWeeks + //price_chg_4_weeks
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' + 
								'<span class="txtText">' +
								item.stockPriceVsIndex4Weeks + //stock_price_vs_index_4_weeks
								'</span>' +
							'</td>' +  
							'<td align="right" class="rowd">' + 
								'<span class="txtText">' +
								item.stockPriceVsIndex13Weeks + //stock_price_vs_index_13_weeks
								'</span>' +
							'</td>' + 
							'<td align="right" class="rowd">' + 
								'<span class="txtText">' +
								item.stockPriceVsIndex52Weeks + //stock_price_vs_index_52_weeks
								'</span>' +
							'</td>' + 
							'<td align="right" class="rowd">' + 
								'<span class="txtText">' +
								item.beta + //beta
								'</span>' +
							'</td>' + 
						'</tr>'
				);
			})
			
			tbody.append(
				'<td align="right" colspan="8">'+
					'<span class="right StockSummary_Paging">'+
						'<div id="pricePerformanceNavigator"></div>'+
					'</span>'+
				'</td>'					
			);
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'pricePerformanceNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};


/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerSummary.prototype.buildFundamentalsTable = function(data) {
	if (data.model) {
		try{
			$.options.divToLoad = $.options.div[2].name + " table tbody";
			$.each($.options.div, function(i, item){$(item.name).hide()});
			$($.options.div[2].name).show();
			var tbody = $($.options.div[2].name + " table tbody").empty();
			var owner = this;
			$.each(data.model.listStockScreener, function(i, item){
				tbody.append(
						'<tr>' +
							'<td align="left">' + 
								'<span class="txtText">' +
								'<a href="' + $.web_resource.getContext() + "tong-quan/" + item.analysisIndexingBean.symbol.toLowerCase() + '.shtml">' +
									item.analysisIndexingBean.symbol + 
								'</a>' +
								'</span>' +
							'</td>' +  
							'<td align="left">' + 
								'<span class="txtText">' +
								("vn" == LOCALE ? item.analysisIndexingBean.companyName : item.analysisIndexingBean.companyFullName) + 
								'</span>' +
							'</td>' + 
							
							'<td align="right">' +
								'<span class="txtText">' +
								item.peRatio + //pe_ratio
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.priceSaleRatio + //price_sale_ratio
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.priceBookRatio + //price_book_ratio
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.pegRatio + //peg_ratio
								'</span>' +
							'</td>' + 
							'<td align="right">' + 
								'<span class="txtText">' +
								item.profitMargin + //profit_margin
								'</span>' +
							'</td>' + 
							'<td align="right">' + 
								'<span class="txtText">' +
								item.roe + //roe
								'</span>' +
							'</td>' + 
							'<td align="right" class="col_end">' + 
								'<span class="txtText">' +
								item.roa + //roa
								'</span>' +
							'</td>' + 
						'</tr>'
				);
			})
			
			tbody.append(
				'<td align="right" colspan="9">'+
					'<span class="right StockSummary_Paging">'+
						'<div id="fundamentalsNavigator"></div>'+
					'</span>'+
				'</td>'					
			);
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'fundamentalsNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerSummary.prototype.buildEarningsDividendsTable = function(data) {
	if (data.model) {
		try{
			$.options.divToLoad = $.options.div[3].name + " table tbody";
			$.each($.options.div, function(i, item){$(item.name).hide()});
			$($.options.div[3].name).show();
			var tbody = $($.options.div[3].name + " table tbody").empty();
			var owner = this;
			$.each(data.model.listStockScreener, function(i, item){
				tbody.append(
						'<tr>' +
							'<td align="left">' + 
								'<span class="txtText">' +
								'<a href="' + $.web_resource.getContext() + "tong-quan/" + item.analysisIndexingBean.symbol.toLowerCase() + '.shtml">' +
									item.analysisIndexingBean.symbol + 
								'</a>' +
								'</span>' +
							'</td>' +  
							'<td align="left">' + 
								'<span class="txtText">' +
								("vn" == LOCALE ? item.analysisIndexingBean.companyName : item.analysisIndexingBean.companyFullName) + 
								'</span>' +
							'</td>' + 
							
							'<td align="right">' +
								'<span class="txtText">' +
								item.epsGrowthManual + //eps_growth_manual
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.revernueGrowthManual + //revernue_growth_manual
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.epsGrowthQuarterly + //eps_growth_quarterly
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.revernueGrowthQuarterly + //revernue_growth_quarterly
								'</span>' +
							'</td>' + 
							'<td align="right">' + 
								'<span class="txtText">' +
								item.dividentYeald + //divident_yeald
								'</span>' +
							'</td>' + 
							'<td align="right" class="col_end">' + 
								'<span class="txtText" class="paging">' +
								item.dividentGrowth5Years + //divident_growth_5_years
								'</span>' +
							'</td>' + 
						'</tr>'
				);
			})
			
			tbody.append(
				'<td align="right" colspan="8">'+
					'<span class="right StockSummary_Paging">'+
						'<div id="earningsDividendsNavigator"></div>'+
					'</span>'+
				'</td>'					
			);
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'earningsDividendsNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerSummary.prototype.buildTechnicalsTable = function(data) {
	if (data.model) {
		try{
			$.options.divToLoad = $.options.div[4].name + " table tbody";
			$.each($.options.div, function(i, item){$(item.name).hide()});
			$($.options.div[4].name).show();
			var tbody = $($.options.div[4].name + " table tbody").empty();
			var owner = this;
			$.each(data.model.listStockScreener, function(i, item){
				tbody.append(
						'<tr>' +
							'<td align="left">' + 
								'<span class="txtText">' +
								'<a href="' + $.web_resource.getContext() + "tong-quan/" + item.analysisIndexingBean.symbol.toLowerCase() + '.shtml">' +
									item.analysisIndexingBean.symbol + 
								'</a>' +
								'</span>' +
							'</td>' +  
							'<td align="left">' + 
								'<span class="txtText">' +
								("vn" == LOCALE ? item.analysisIndexingBean.companyName : item.analysisIndexingBean.companyFullName) + 
								'</span>' +
							'</td>' + 
							
							'<td align="right">' +
								'<span class="txtText">' +
								item.fiftyTwoWeekChg + //52_week_chg
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.vsSma13Day + //vs_sma_13_day
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.vsSma50Day + //vs_sma_50_day
								'</span>' +
							'</td>' +  
							'<td align="right">' + 
								'<span class="txtText">' +
								item.pctBelow52WkHigh + //getPctBelow52WkHigh
								'</span>' +
							'</td>' + 
							'<td align="right" class="col_end">' + 
								'<span class="txtText">' +
								item.pctBelow52WkLow + //getPctBelow52WkLow
								'</span>' +
							'</td>' + 
						'</tr>'
				);
			})
			
			tbody.append(
				'<td align="right" colspan="7">'+
					'<span class="right StockSummary_Paging">'+
						'<div id="technicalsNavigator"></div>'+
					'</span>'+
				'</td>'					
			);
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'technicalsNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};