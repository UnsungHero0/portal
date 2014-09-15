(function($) {
$.tradingStatisticsClazzSearchListingOption = function() {};
$.extend($.tradingStatisticsClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	funcName : {
        navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		ForSymbol 	: "#fTradingStatisticsForSymbol",
		name 		: "#fTradingStatistics",
		fields : {
		
				//Use for TradingStatisticsForSymbol
				symbol : "#searchMarketStatisticsView.symbol.id",
				fromDate : "#fTradingStatisticsForSymbol_FromDate",
				toDate : "#fTradingStatisticsForSymbol_ToDate",

				//Use for Trading Statistics
				market 		: "#fTradingStatistics_market",
				date 		: "#fTradingStatistics_TradingDate",
				pagingIndex : "#fTradingStatistics_pagingInfo_indexPage"
		}
	},
	buttons : {
		btSearch : "#fTradingStatistics_Search",
		btView : "#fTradingStatisticsForSymbol_View"
			}
	});
})(jQuery);

/*************************************************************************************
* Trading Statistics For Symbol Class
*************************************************************************************/
function TradingStatisticsClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.tradingStatisticsClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.getOption = function () {
	return $.tradingStatisticsClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
	//+++ resetInitData
	_this.resetInitData();
};

/*************************************************************************************
* getSelectedItemIds functionality
*
* @return Array of seleted Ids
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_TRADINGSTATISTICS, // override for form's 'action' attribute
			type:      'post',        // 'get' or 'post', override for form's 'method' attribute
			dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)
			//clearForm: true        // clear all form fields after successful submit
			// resetForm: true        // reset the form after successful submit
			//iframe: false

			// $.ajax options can be used here too, for example:
			//timeout:   3000
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
*** execute function for Trading Statistics For Symbol
**************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.executeForSymbol = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.ForSymbol).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit_ForSymbol,  // pre-submit callback
			success:       _this.execute_PostSubmit_ForSymbol,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_TRADING_FORSMBOL, // override for form's 'action' attribute
			type:      'post',        // 'get' or 'post', override for form's 'method' attribute
			dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)
			//clearForm: true        // clear all form fields after successful submit
			// resetForm: true        // reset the form after successful submit
			//iframe: false

			// $.ajax options can be used here too, for example:
			//timeout:   3000
		};
		// inside event callbacks 'this' is the DOM element so we first
		// wrap it in a jQuery object and then invoke ajaxSubmit
		$(opts.form.ForSymbol).ajaxSubmit(options);
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
* jqForm is a jQuery object encapsulating the form element.  To access the
* DOM element for the form do this:
* var formElement = jqForm[0];
*
* here we could return false to prevent the form from being submitted;
* returning anything other than false will allow the form submit to continue
*************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.tradingStatisticsClazzSearchListingOption;
	var _this = opts.clazzHandler;

	$.web_message.clear();

	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---

	// here we could return false to prevent the form from being submitted;
	// returning anything other than false will allow the form submit to continue
	return true;
};
/*************************************************************************************
* execute_PreSubmit function
*
* pre-submit callback
* formData is an array; here we use $.param to convert it to a string to display it
* but the form plugin does this for you automatically when it submits the data
* var queryString = $.param(formData);
*
* jqForm is a jQuery object encapsulating the form element.  To access the
* DOM element for the form do this:
* var formElement = jqForm[0];
*
* here we could return false to prevent the form from being submitted;
* returning anything other than false will allow the form submit to continue
*************************************************************************************/
TradingStatisticsClazzSearchListing.prototype.execute_PreSubmit_ForSymbol = function(formData, jqForm, options) {
	var opts = $.tradingStatisticsClazzSearchListingOption;
	var _this = opts.clazzHandler;

	$.web_message.clear();

	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---

	// here we could return false to prevent the form from being submitted;
	// returning anything other than false will allow the form submit to continue
	return true;
};
/************Dinh nghia ket qua tra ve cua List Search***************/
TradingStatisticsClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.tradingStatisticsClazzSearchListingOption;
	var _this = opts.clazzHandler;

	if(responseText.error != null && responseText.error != 'undefined' && responseText.error.hasErrors) {
		
		//+++ show dialog message (error * action messages)
		$.web_message.error(_this.validator, responseText.error);
		
		//Delete table content
		$("#Listed_TradingStatistics_tableResult").tableManager_deleteAllRow();
		
		//Delete paging if search result = 0
		var funcOptions = {
				goto_func: opts.funcName.navFuncName
		};
		var idOptions = {
				navContainer : "fSearchSymbol_paging"
		};
		var model = responseText.model;
		$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);

	} else {
		$.web_message.actionMsg(responseText.error);

		try {
			if(opts.func.callbackExecuted != null && $.isFunction(opts.func.callbackExecuted)) {
				opts.func.callbackExecuted(responseText);
			} else {
				var model = responseText.model;
				if(model.searchResult!= null) {

					$("#Listed_TradingStatistics_tableResult").tableManager_deleteAllRow();

					//==========Finance show===============
					var arrData = new Array(11);
					var options = {
							totalCell:11,
							cellClasses: {
						
								0: "historicalpriceforsymbol",
								1: "historicalpriceforsymbol",
								2: "historicalpriceforsymbol",
								3: "historicalpriceforsymbol",
								4: "historicalpriceforsymbol",
								5: "historicalpriceforsymbol",
								6: "historicalpriceforsymbol",
								7: "historicalpriceforsymbol",
								8: "historicalpriceforsymbol",
								9: "historicalpriceforsymbol",
								10: "historicalpriceforsymbol_end"
							}
						};
					
					for(var index in model.searchResult) {
						var obj = model.searchResult[index];
				
						arrData[0] = obj.id.secCode +"&nbsp;&nbsp;&nbsp;";
						arrData[1] = obj.id.closePrice + "&nbsp;";
						arrData[2] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidOrder, 0) +"&nbsp;";
						arrData[3] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn, 0) +"&nbsp;";
						arrData[4] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerOrder, 0) +"&nbsp;";
						arrData[5] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerVolumn, 0) +"&nbsp;";
						arrData[6] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn/obj.id.bidOrder, 0) +"&nbsp;";
						arrData[7] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerVolumn/obj.id.offerOrder, 0) + "&nbsp;"; 
						arrData[8] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn - obj.id.offerVolumn, 0) + "&nbsp;";
						arrData[9] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalVolumn, 0) +"&nbsp;";
						arrData[10] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalValue, 0) + "&nbsp;"; 
									
						//Show table and set css
						$("#Listed_TradingStatistics_tableResult").tableManager_addRow(arrData, options);
					}
					//Paging 
					var funcOptions = {
	                        goto_func: opts.funcName.navFuncName
	                  };
	                  var idOptions = {
	                        navContainer : "fSearchSymbol_paging"
	                  };
	                  $.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);
				 }
			}
			
		 } catch (e) {
	}
 }
};

/************Dinh nghia ket qua tra ve cua List Search For Symbol***************/
TradingStatisticsClazzSearchListing.prototype.execute_PostSubmit_ForSymbol = function(responseText, statusText) {
	var opts = $.tradingStatisticsClazzSearchListingOption;
	var _this = opts.clazzHandler;

	if(responseText.error != null && responseText.error != 'undefined' && responseText.error.hasErrors) {
		
		//+++ show dialog message (error * action messages)
		$.web_message.error(_this.validator, responseText.error);
		
		//Delete table content
		$("#Listed_TradingStatisticsForSymbol_tableResult").tableManager_deleteAllRow();
		
		//Delete paging if search result = 0
		var funcOptions = {
				goto_func: opts.funcName.navFuncName
		};
		var idOptions = {
				navContainer : "fSearchSymbol_paging"
		};
		var model = responseText.model;
		$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);

		
	} else {
		$.web_message.actionMsg(responseText.error);

		try {
			if(opts.func.callbackExecuted != null && $.isFunction(opts.func.callbackExecuted)) {
				opts.func.callbackExecuted(responseText);
			} else {
				var model = responseText.model;
				if(model.searchResult != null) {

					$("#Listed_TradingStatisticsForSymbol_tableResult").tableManager_deleteAllRow();

					//==========Finance show===============
					var arrData = new Array(11);
					var options = {
							totalCell:11,
							cellClasses: {
						
								0: "historicalpriceforsymbol",
								1: "historicalpriceforsymbol",
								2: "historicalpriceforsymbol",
								3: "historicalpriceforsymbol",
								4: "historicalpriceforsymbol",
								5: "historicalpriceforsymbol",
								6: "historicalpriceforsymbol",
								7: "historicalpriceforsymbol",
								8: "historicalpriceforsymbol",
								9: "historicalpriceforsymbol",
								10: "historicalpriceforsymbol_end"
							}
						};
					
					for(var index in model.searchResult) {
						var obj = model.searchResult[index];
						
						arrData[0] = $.web_utils.dateFormat2Show(obj.id.transDate, "dd/mm/yyyy")+"&nbsp;";
						arrData[1] = obj.id.closePrice + "&nbsp;";
						arrData[2] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidOrder, 0) +"&nbsp;";
						arrData[3] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn, 0) +"&nbsp;";
						arrData[4] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerOrder, 0) +"&nbsp;";
						arrData[5] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerVolumn, 0) +"&nbsp;";
						arrData[6] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn/obj.id.bidOrder, 0) +"&nbsp;";
						arrData[7] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.offerVolumn/obj.id.offerOrder, 0) + "&nbsp;"; 
						arrData[8] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.bidVolumn - obj.id.offerVolumn, 0) + "&nbsp;";
						arrData[9] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalVolumn, 0) +"&nbsp;";
						arrData[10] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalValue, 0) + "&nbsp;"; 
									
						//Show table and set css
						$("#Listed_TradingStatisticsForSymbol_tableResult").tableManager_addRow(arrData, options);
					}
					//Paging 
					var funcOptions = {
	                        goto_func: opts.funcName.navFuncName
	                  };
	                  var idOptions = {
	                        navContainer : "fSearchSymbol_paging"
	                  };
	                  $.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);
				 }
			}
			
		 } catch (e) {
	}
 }
};