(function($) {
$.historicalPriceClazzSearchListingOption = function() {};
$.extend($.historicalPriceClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	funcName : {

        navFuncName : "_goTo" // function _goTo(index)

	},
	form : {
		name : "#fHistoricalPrice",
		fields : {
				symbol : "#symbolID",
				fromDate : "#fHistoricalPrice_FromDate",
				toDate : "#fHistoricalPrice_ToDate"
		}
	},
	buttons : {
		btView : "#fHistoricalPrice_View"
			}
	});
})(jQuery);

/*************************************************************************************
* HistoricalPriceClazzSearchListing Class
*************************************************************************************/
function HistoricalPriceClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.historicalPriceClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
HistoricalPriceClazzSearchListing.prototype.getOption = function () {
	return $.historicalPriceClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
HistoricalPriceClazzSearchListing.prototype.init = function() {
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
HistoricalPriceClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
HistoricalPriceClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
HistoricalPriceClazzSearchListing.prototype.executeForSymbol = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_HISTORICALPRICEFORSYMBOL, // override for form's 'action' attribute
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
HistoricalPriceClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_HISTORICALPRICE, // override for form's 'action' attribute
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
HistoricalPriceClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.historicalPriceClazzSearchListingOption;
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
HistoricalPriceClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.historicalPriceClazzSearchListingOption;
	var _this = opts.clazzHandler;
	
	//+++ hidden loading image
	if($.web_utils.isNotNull(opts.loading)) {
		$(opts.loading).web_hiddenLoading();
	}
	
	if($.web_message.errorAuthMsg(responseText)) {
		
		//+++ show dialog message (error * action messages)
		$.web_message.showDialogErrorMsg(responseText);
		
		//Delete table content
		$("#Listed_HistoricalPrice_tableResult").tableManager_deleteAllRow();
		
		//Delete paging if search result = 0
		var funcOptions = {
				goto_func: opts.funcName.navFuncName
		};
		var idOptions = {
				navContainer : "fSearchSymbol_paging"
		};
		var model = responseText.model;
		$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);

		if($.web_utils.isNotNull(responseText.error) && $.web_utils.isNotNull(responseText.error.hasErrors) && responseText.error.hasErrors) {
			try {
				if($.isFunction(opts.callbackExecuteFail)) {
					opts.callbackExecuteFail(responseText.error);
				}			
			} catch (e) {	
				if(DEBUG) {
					$.log("--- success:callbackExecuteFail-" + e);
				}
			}			
		} else {
			try {
				//draw table result
				var model = responseText.model;
				
				if($.web_utils.isNotNull(model)) {
					
					var listIfoSecPriceView = model.searchResult;
					
					if($.web_utils.isNotNull(listIfoSecPriceView)) {
						var arrData = new Array(10);
						var options1 = {
								totalCell:10,
								cellClasses: {
									0: "historicalpriceforsymbol",
									1: "historicalpriceforsymbol_green",
									2: "historicalpriceforsymbol",
									3: "historicalpriceforsymbol",
									4: "historicalpriceforsymbol",
									5: "historicalpriceforsymbol",
									6: "historicalpriceforsymbol",
									7: "historicalpriceforsymbol",
									8: "historicalpriceforsymbol",
									9: "historicalpriceforsymbol_end"
								}
							};
							
							var options2 = {
									totalCell:10,
									cellClasses: {
										0: "historicalpriceforsymbol",
										1: "historicalpriceforsymbol_red",
										2: "historicalpriceforsymbol",
										3: "historicalpriceforsymbol",
										4: "historicalpriceforsymbol",
										5: "historicalpriceforsymbol",
										6: "historicalpriceforsymbol",
										7: "historicalpriceforsymbol",
										8: "historicalpriceforsymbol",
										9: "historicalpriceforsymbol_end"
									}
								};
							var options3 = {
									totalCell:10,
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
										9: "historicalpriceforsymbol_end"
									}
								};
						var obj;
						for(var index in listIfoSecPriceView) {
							obj = listIfoSecPriceView[index];
							arrData[0] = $.web_utils.dateFormat2Show(obj.id.transDate, "dd/mm/yyyy")+"&nbsp;";
							if (obj.chgIndex > 0){
								arrData[1] = "+" + $.web_utils.fomatNumberWithScaleSeprate(obj.chgIndex, 1) + "&nbsp;(+" + $.web_utils.fomatNumberWithScaleSeprate(obj.pctIndex, 2) + "%)" +"&nbsp;" + '<img src="' + $.web_resource.getContext() + '/images/front/up_2.gif"/>' +"&nbsp;";
							} else if (obj.chgIndex < 0){
								arrData[1] = $.web_utils.fomatNumberWithScaleSeprate(obj.chgIndex, 1) + "&nbsp;(" + $.web_utils.fomatNumberWithScaleSeprate(obj.pctIndex, 2) + "%)" +"&nbsp;" + '<img src="' + $.web_resource.getContext() + '/images/front/ico_08.gif"/>' +"&nbsp;";
							} else{
								arrData[1] = $.web_utils.fomatNumberWithScaleSeprate(obj.chgIndex, 0)+ "&nbsp;(" + $.web_utils.fomatNumberWithScaleSeprate(obj.pctIndex, 0) + "%)" +"&nbsp;" + '<img src="' + $.web_resource.getContext() + '/images/front/bang.gif"/>' +"&nbsp;";
							}		
							arrData[2] = obj.id.openPrice + "&nbsp;";
							arrData[3] = obj.id.highPrice + "&nbsp;";
							arrData[4] = obj.id.lowPrice + "&nbsp;";
							arrData[5] = obj.id.closePrice+ "&nbsp;";
							arrData[6] = $.web_utils.fomatDouble(obj.id.averagePrice, 2)+ "&nbsp;";
							arrData[7] = obj.id.adClosePrice + "&nbsp;";
							arrData[8] = $.web_utils.fomatLongNotZero(obj.id.volume)+"&nbsp;";
							arrData[9] = $.web_utils.fomatLongNotZero(obj.id.ptVolume)+"&nbsp;";

							//Show table and set css
							$("#Listed_HistoricalPrice_tableResult").tableManager_addRow(arrData,  obj.chgIndex > 0 ? options1 : obj.chgIndex < 0 ? options2 : options3);
							
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
				
				if($.isFunction(opts.func.callbackExecuted)) {
					opts.func.callbackExecuted(responseText);
				}			
			} catch (e) {
				if(DEBUG) {
					$.log("--- callbackExecuted-" + e);
				}
			}
		}
	} else {
		//TODO: nothing to process... auth error...
	}	
};