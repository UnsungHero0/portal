(function($) {
$.foreignTradingClazzSearchListingOption = function() {};
$.extend($.foreignTradingClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	funcName : {
        navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name : "#fForeignTrading",
		ForSymbol: "#fForeignTradingForSymbol",
		fields : {
				//Use  for Foreign Trading For Symbol
				symbol 					: "#fForeignTradingForSymbol_symbolID",
				fromDate 				: "#fForeignTradingForSymbol_FromDate",
				toDate 					: "#fForeignTradingForSymbol_ToDate",
				pagingIndexForSymbol 	:"#fForeignTradingForSymbol_pagingInfo_indexPage",
				//Use  for Foreign Trading
				market 					: "#fTradingStatistics_market",
				strTradingDate 			: "#fForeignTrading_TradingDate",
				pagingIndex 			: "#fForeignTrading_pagingInfo_indexPage"
		}
	},
	buttons : {
				btView 					: "#fForeignTradingForSymbol_View",
				btSearch				: "#fForeignTrading_Search"
			}
	});
})(jQuery);

/*************************************************************************************
* Trading Statistics Class
*************************************************************************************/
function ForeignTradingClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.foreignTradingClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
ForeignTradingClazzSearchListing.prototype.getOption = function () {
	return $.foreignTradingClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
ForeignTradingClazzSearchListing.prototype.init = function() {
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
ForeignTradingClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
ForeignTradingClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
ForeignTradingClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_TRADINGFOREIGNINVESTORS, // override for form's 'action' attribute
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
*** execute function
**************************************************************************************/
ForeignTradingClazzSearchListing.prototype.executeForSymbol = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.ForSymbol).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit_ForSymbol,  // pre-submit callback
			success:       _this.execute_PostSubmit_ForSymbol,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_FOREIGNTRADINGFORSYMBOL, // override for form's 'action' attribute
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
ForeignTradingClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.foreignTradingClazzSearchListingOption;
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
ForeignTradingClazzSearchListing.prototype.execute_PreSubmit_ForSymbol = function(formData, jqForm, options) {
	var opts = $.foreignTradingClazzSearchListingOption;
	var _this = opts.clazzHandler;

	$.web_message.clear();

	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---

	// here we could return false to prevent the form from being submitted;
	// returning anything other than false will allow the form submit to continue
	return true;
};
/************Dinh nghia ket qua tra ve cua List Search ForSymbol***************/
ForeignTradingClazzSearchListing.prototype.execute_PostSubmit_ForSymbol = function(responseText, statusText) {
	var opts = $.foreignTradingClazzSearchListingOption;
	var _this = opts.clazzHandler;

	if(responseText.error != null && responseText.error != 'undefined' && responseText.error.hasErrors) {
		
		//+++ show dialog message (error * action messages)
		$.web_message.error(_this.validator, responseText.error);
		
		//Delete table content
		$("#Listed_ForeignTradingForSymbol_tableResult").tableManager_deleteAllRow();
		
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

					$("#Listed_ForeignTradingForSymbol_tableResult").tableManager_deleteAllRow();

					//==========Finance show===============
					var arrData = new Array(7);
					var options = {
							totalCell:7,
							cellClasses: {
						
								0: "historicalpriceforsymbol",
								1: "historicalpriceforsymbol",
								2: "historicalpriceforsymbol",
								3: "historicalpriceforsymbol",
								4: "historicalpriceforsymbol",
								5: "historicalpriceforsymbol",
								6: "historicalpriceforsymbol_end"
							}
						};
					
					for(var index in model.searchResult) {
						var obj = model.searchResult[index];
						
						arrData[0] = $.web_utils.dateFormat2Show(obj.id.tradingDate, "dd/mm/yyyy")+"&nbsp;";
						arrData[1] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalRoom, 0) + "&nbsp;";
						arrData[2] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.currentRoom, 0) + "&nbsp;";
						arrData[3] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.buyingVolume, 0) + "&nbsp;";
						arrData[4] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.buyingValue, 0) + "&nbsp;";
						arrData[5] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.sellingVolume, 0) + "&nbsp;";
						arrData[6] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.sellingValue, 0) + "&nbsp;";
									
						//Show table and set css
						$("#Listed_ForeignTradingForSymbol_tableResult").tableManager_addRow(arrData, options);
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

/************Dinh nghia ket qua tra ve cua List Search ***************/
ForeignTradingClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.foreignTradingClazzSearchListingOption;
	var _this = opts.clazzHandler;

	if(responseText.error != null && responseText.error != 'undefined' && responseText.error.hasErrors) {
		
		//+++ show dialog message (error * action messages)
		$.web_message.error(_this.validator, responseText.error);
		
		//Delete table content
		$("#Listed_ForeignTrading_tableResult").tableManager_deleteAllRow();
		
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

					$("#Listed_ForeignTrading_tableResult").tableManager_deleteAllRow();

					//==========Finance show===============
					var arrData = new Array(7);
					var options = {
							totalCell:7,
							cellClasses: {
						
								0: "historicalprice",
								1: "historicalpriceforsymbol",
								2: "historicalpriceforsymbol",
								3: "historicalpriceforsymbol",
								4: "historicalpriceforsymbol",
								5: "historicalpriceforsymbol",
								6: "historicalpriceforsymbol_end"
							}
						};
					
					for(var index in model.searchResult) {
						var obj = model.searchResult[index];
						
						arrData[0] = "&nbsp;&nbsp;&nbsp;" + $.web_utils.toValue(obj.id.secCode);
						arrData[1] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.totalRoom, 0) + "&nbsp;";
						arrData[2] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.currentRoom, 0) + "&nbsp;";
						arrData[3] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.buyingVolume, 0) + "&nbsp;";
						arrData[4] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.buyingValue, 0) + "&nbsp;";
						arrData[5] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.sellingVolume, 0) + "&nbsp;";
						arrData[6] = $.web_utils.fomatNumberWithScaleSeprate(obj.id.sellingValue, 0) + "&nbsp;";
									
						//Show table and set css
						$("#Listed_ForeignTrading_tableResult").tableManager_addRow(arrData, options);
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