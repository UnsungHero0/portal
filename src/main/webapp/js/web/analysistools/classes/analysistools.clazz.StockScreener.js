/*************************************************************************************
* @author TungNQ
*
* Define stockScreenerCntOption Object
* using the flowing query to extend stockScreenerCntOption
*
* jQuery.extend(jQuery.stockScreenerCntOption, {
* });
*
*************************************************************************************/
(function($) {
$.stockScreenerCntOption = function() {};
$.extend($.stockScreenerCntOption, {
	form : {
		name : "#vndirectCntFrm",
		fields : {
			actionId : "action.id",
			asset_class_1 : "#asset_class_1.id",
			asset_class_2 : "#asset_class_2.id",
			asset_class_3 : "#asset_class_3.id",
			asset_class_3 : "#asset_class_4.id",
			component_1 : "#component_1.id",
			component_2 : "#component_2.id",
			component_3 : "#component_3.id",
			sector : "#sector.id",
			industry : "#industry.id",
			share_price_from : "#share_price_from.id",
			share_price_to : "#share_price_to.id",
			price_change_from : "#price_change_from.id",
			price_change_1 : "#price_change_1.id",
			price_change_2 : "#price_change_2.id",
			price_change_3 : "#price_change_3.id",
			price_change_4 : "#price_change_4.id",
			price_change_5 : "#price_change_5.id",
			price_performance_1 : "#price_performance_1.id",
			price_performance_2 : "#price_performance_2.id",
			price_performance_3 : "#price_performance_3.id",
			price_performance_4 : "#price_performance_4.id",
			price_performance_5 : "#price_performance_5.id",
			price_performance_6 : "#price_performance_6.id",
			over_1 : "#over_1.id",
			over_2 : "#over_2.id",
			over_3 : "#over_3.id",
			average_volume_from : "#average_volume_from.id",
			average_volume_to : "#average_volume_to.id",
			pe_ratio_1 : "#pe_ratio_1.id",
			pe_ratio_2 : "#pe_ratio_2.id",
			pe_ratio_3 : "#pe_ratio_3.id",
			pe_ratio_4 : "#pe_ratio_4.id",
			pe_ratio_5 : "#pe_ratio_5.id",
			pe_ratio_6 : "#pe_ratio_6.id",
			peg_1 : "#peg_1.id",
			peg_2 : "#peg_2.id",
			profit_margin_1 : "#profit_margin_1.id",
			profit_margin_2 : "#profit_margin_2.id",
			profit_margin_3 : "#profit_margin_3.id",
			profit_margin_4 : "#profit_margin_4.id",
			profit_margin_5 : "#profit_margin_5.id",
			profit_margin_6 : "#profit_margin_6.id",
			profit_margin_and_1 : "#profit_margin_and_1.id",
			profit_margin_and_2 : "#profit_margin_and_2.id",
			price_sale_ratio_1 : "#price_sale_ratio_1.id",
			price_sale_ratio_2 : "#price_sale_ratio_2.id",
			price_sale_ratio_3 : "#price_sale_ratio_3.id",
			price_sale_ratio_4 : "#price_sale_ratio_4.id",
			price_sale_ratio_5 : "#price_sale_ratio_5.id",
			price_sale_ratio_6 : "#price_sale_ratio_6.id",
			price_sale_ratio_7 : "#price_sale_ratio_7.id",
			price_book_ratio_1 : "#price_book_ratio_1.id",
			price_book_ratio_2 : "#price_book_ratio_2.id",
			price_book_ratio_3 : "#price_book_ratio_3.id",
			price_book_ratio_4 : "#price_book_ratio_4.id",
			return_equity_1 : "#return_equity_1.id",
			return_equity_2 : "#return_equity_2.id",
			return_equity_3 : "#return_equity_3.id",
			return_equity_4 : "#return_equity_4.id",
			return_equity_and_1 : "#return_equity_and_1.id",
			return_equity_and_2 : "#return_equity_and_2.id",
			return_asset_1 : "#return_asset_1.id",
			return_asset_2 : "#return_asset_2.id",
			return_asset_3 : "#return_asset_3.id",
			return_asset_4 : "#return_asset_4.id",
			eps_growth_annual_1 : "#eps_growth_annual_1.id",
			eps_growth_annual_2 : "#eps_growth_annual_2.id",
			eps_growth_annual_3 : "#eps_growth_annual_3.id",
			eps_growth_annual_4 : "#eps_growth_annual_4.id",
			eps_growth_annual_5 : "#eps_growth_annual_5.id",
			eps_growth_annual_6 : "#eps_growth_annual_6.id",
			eps_growth_annual_7 : "#eps_growth_annual_7.id",
			eps_growth_annual_8 : "#eps_growth_annual_8.id",
			revenue_growth_annual_1 : "#revenue_growth_annual_1.id",
			revenue_growth_annual_2 : "#revenue_growth_annual_2.id",
			revenue_growth_annual_3 : "#revenue_growth_annual_3.id",
			revenue_growth_annual_4 : "#revenue_growth_annual_4.id",
			revenue_growth_annual_5 : "#revenue_growth_annual_5.id",
			revenue_growth_annual_6 : "#revenue_growth_annual_6.id",
			revenue_growth_annual_7 : "#revenue_growth_annual_7.id",
			revenue_growth_annual_8 : "#revenue_growth_annual_8.id",
			eps_growth_quarterly_1 : "#eps_growth_quarterly_1.id",
			eps_growth_quarterly_2 : "#eps_growth_quarterly_2.id",
			eps_growth_quarterly_3 : "#eps_growth_quarterly_3.id",
			eps_growth_quarterly_4 : "#eps_growth_quarterly_4.id",
			eps_growth_quarterly_5 : "#eps_growth_quarterly_5.id",
			eps_growth_quarterly_6 : "#eps_growth_quarterly_6.id",
			eps_growth_quarterly_7 : "#eps_growth_quarterly_7.id",
			eps_growth_quarterly_8 : "#eps_growth_quarterly_8.id",
			revenue_growth_quarterly_1 : "#revenue_growth_quarterly_1.id",
			revenue_growth_quarterly_2 : "#revenue_growth_quarterly_2.id",
			revenue_growth_quarterly_3 : "#revenue_growth_quarterly_3.id",
			revenue_growth_quarterly_4 : "#revenue_growth_quarterly_4.id",
			revenue_growth_quarterly_5 : "#revenue_growth_quarterly_5.id",
			revenue_growth_quarterly_6 : "#revenue_growth_quarterly_6.id",
			revenue_growth_quarterly_7 : "#revenue_growth_quarterly_7.id",
			revenue_growth_quarterly_8 : "#revenue_growth_quarterly_8.id",
			dividend_growth_5_year_1 : "#dividend_growth_5_year_1.id",
			dividend_growth_5_year_2 : "#dividend_growth_5_year_2.id",
			dividend_growth_5_year_3 : "#dividend_growth_5_year_3.id",
			dividend_growth_5_year_4 : "#dividend_growth_5_year_4.id",
			dividend_growth_5_year_5 : "#dividend_growth_5_year_5.id",
			dividend_yield_1 : "#dividend_yield_1.id",
			dividend_yield_2 : "#dividend_yield_2.id",
			dividend_yield_3 : "#dividend_yield_3.id",
			dividend_yield_4 : "#dividend_yield_4.id",
			dividend_yield_5 : "#dividend_yield_5.id",
			dividend_yield_6 : "#dividend_yield_6.id",
			dividend_yield_and_1 : "#dividend_yield_and_1.id",
			dividend_yield_and_2 : "#dividend_yield_and_2.id",
			_52_week_high : "#_52_week_high.id",
			_52_week_high_1 : "#_52_week_high_1.id",
			_52_week_low : "#_52_week_low.id",
			_52_week_low_1 : "#_52_week_low_1.id",
			price_closes_sam_1 : "#price_closes_sam_1.id",
			price_closes_sam_2 : "#price_closes_sam_2.id",
			price_closes_sam_3 : "#price_closes_sam_3.id",
			price_closes_sam_4 : "#price_closes_sam_4.id",
			price_closes_sam_5 : "#price_closes_sam_5.id",
			price_closes_sam_6 : "#price_closes_sam_6.id",
			price_closes_sam_7 : "#price_closes_sam_7.id",
			price_closes_sam_8 : "#price_closes_sam_8.id",
			above_below_1 : "#above_below_1.id",
			above_below_2 : "#above_below_2.id",
			_13_day_sma_1 : "#_13_day_sma_1.id",
			_13_day_sma_2 : "#_13_day_sma_2.id"
		}
	}
});
})(jQuery);
/*************************************************************************************
* @author TungNQ
*
* @public: StockScreenerClass Class
*
*************************************************************************************/
function StockScreenerClass() {
	jQuery.extend(jQuery.stockScreenerCntOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
StockScreenerClass.prototype.getOption = function () {
	return $.stockScreenerCntOption;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
StockScreenerClass.prototype.validForm = function() {
	//TODO: validate form

	return true;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
StockScreenerClass.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = _this.validForm();
	if(fValid) {
		var options = {
			beforeSubmit:	_this.execute_PreSubmit,	// pre-submit callback
			success:		_this.execute_PostSubmit,	// post-submit callback

			// other available options:
			url:		URL_STOCK_SCREENER_CALCULATE_AJAX,		// override for form's 'action' attribute
			type:		'post',		// 'get' or 'post', override for form's 'method' attribute
			dataType:	'json'		// 'xml', 'script', or 'json' (expected server response type)
			//clearForm: true		// clear all form fields after successful submit
			// resetForm: true		// reset the form after successful submit
	 		//iframe: false

			// $.ajax options can be used here too, for example:
			//timeout:	3000
		};
		// inside event callbacks 'this' is the DOM element so we first
		// wrap it in a jQuery object and then invoke ajaxSubmit
		 $(opts.form.name).ajaxSubmit(options);
	}
	// !!! Important !!!
	// always return false to prevent standard browser submit and page navigation
	return false;
};

/*************************************************************************************
* execute_PreSubmit function
*
* pre-submit callback
* formData is an array; here we use $.param to convert it to a string to display it
* but the form plugin does this for you automatically when it submits the data
* var queryString = $.param(formData);
*
* jqForm is a jQuery object encapsulating the form element.	To access the
* DOM element for the form do this:
* var formElement = jqForm[0];
*
* here we could return false to prevent the form from being submitted;
* returning anything other than false will allow the form submit to continue
*************************************************************************************/
StockScreenerClass.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.stockScreenerCntOption;
	var _this = opts.clazzHandler;
	//+++ show loading image
	//$(opts.loading).web_showLoading();
	//---

	// here we could return false to prevent the form from being submitted;
	// returning anything other than false will allow the form submit to continue
	return true;
};

/*************************************************************************************
* execute_PostSubmit function
*
* post-submit callback
* for normal html responses, the first argument to the success callback
* is the XMLHttpRequest object's responseText property
*
* if the ajaxForm method was passed an Options Object with the dataType
* property set to 'xml' then the first argument to the success callback
* is the XMLHttpRequest object's responseXML property
*
* if the ajaxForm method was passed an Options Object with the dataType
* property set to 'json' then the first argument to the success callback
* is the json data object returned by the server
*
* @param : responseText is JSON/XML/HTML data format
*
*************************************************************************************/
StockScreenerClass.prototype.execute_PostSubmit = function(data, statusText) {
	var opts = $.stockScreenerCntOption;
	var _this = opts.clazzHandler;

	//+++ hidden loading image
	//$(opts.loading).web_hiddenLoading();
	//---
	try {
		// show result
		if (data.model.searchStockScreenerBean) {
			$("#cnt_asset_class").html(data.model.searchStockScreenerBean.cnt_asset_class);
			$("#cnt_component").html(data.model.searchStockScreenerBean.cnt_component);
			$("#cnt_sector_industry").html(data.model.searchStockScreenerBean.cnt_sector_industry);
			$("#cnt_g1_c_result").html(data.model.searchStockScreenerBean.cnt_g1_c_result);
			$("#cnt_share_price").html(data.model.searchStockScreenerBean.cnt_share_price);
			$("#cnt_price_change").html(data.model.searchStockScreenerBean.cnt_price_change);
			$("#cnt_price_performance").html(data.model.searchStockScreenerBean.cnt_price_performance);
			$("#cnt_average_volume").html(data.model.searchStockScreenerBean.cnt_average_volume);
			$("#cnt_g2_c_result").html(data.model.searchStockScreenerBean.cnt_g2_c_result);
			$("#cnt_pe_ratio").html(data.model.searchStockScreenerBean.cnt_pe_ratio);
			$("#cnt_peg").html(data.model.searchStockScreenerBean.cnt_peg);
			$("#cnt_profit_margin").html(data.model.searchStockScreenerBean.cnt_profit_margin);
			$("#cnt_price_sale_ratio").html(data.model.searchStockScreenerBean.cnt_price_sale_ratio);
			$("#cnt_price_book_ratio").html(data.model.searchStockScreenerBean.cnt_price_book_ratio);
			$("#cnt_return_equity").html(data.model.searchStockScreenerBean.cnt_return_equity);
			$("#cnt_return_asset").html(data.model.searchStockScreenerBean.cnt_return_asset);
			$("#cnt_g3_c_result").html(data.model.searchStockScreenerBean.cnt_g3_c_result);
			$("#cnt_eps_growth_annual").html(data.model.searchStockScreenerBean.cnt_eps_growth_annual);
			$("#cnt_revenue_growth_annual").html(data.model.searchStockScreenerBean.cnt_revenue_growth_annual);
			$("#cnt_eps_growth_quarterly").html(data.model.searchStockScreenerBean.cnt_eps_growth_quarterly);
			$("#cnt_revenue_growth_quarterly").html(data.model.searchStockScreenerBean.cnt_revenue_growth_quarterly);
			$("#cnt_dividend_growth_5_year").html(data.model.searchStockScreenerBean.cnt_dividend_growth_5_year);
			$("#cnt_dividend_yield").html(data.model.searchStockScreenerBean.cnt_dividend_yield);
			$("#cnt_g4_c_result").html(data.model.searchStockScreenerBean.cnt_g4_c_result);
			$("#cnt_52_week_high").html(data.model.searchStockScreenerBean.cnt_52_week_high);
			$("#cnt_52_week_low").html(data.model.searchStockScreenerBean.cnt_52_week_low);
			$("#cnt_price_closes_sam").html(data.model.searchStockScreenerBean.cnt_price_closes_sam);
			$("#cnt_13_day_sma").html(data.model.searchStockScreenerBean.cnt_13_day_sma);
			$("#cnt_g5_c_result").html(data.model.searchStockScreenerBean.cnt_g5_c_result);
			$("#cnt_all_c").html(data.model.searchStockScreenerBean.cnt_all_c);
			$("#cnt_all_c_result_1").html(data.model.searchStockScreenerBean.cnt_all_c_result);
			$("#cnt_all_c_result_2").html(data.model.searchStockScreenerBean.cnt_all_c_result);
			$("#cnt_all_c_result_3").html(data.model.searchStockScreenerBean.cnt_all_c_result);
		} 
	} catch (e) {
		alert(e);
	}
};
