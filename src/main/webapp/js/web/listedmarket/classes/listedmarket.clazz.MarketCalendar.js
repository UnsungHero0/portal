(function($) {
$.marketCalendarClazzSearchListingOption = function() {};
$.extend($.marketCalendarClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	funcName : {
        navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name : "#fMarketCalendar",
		fields : {
				symbol 		: "#fMarketCalendar_symbol",
				typeOfDate 	: "#fMarketCalendar_typeOfDate",
				eventType 	: "#fMarketCalendar_eventType",
				pagingIndex : "#fMarketCalendar_pagingInfo_indexPage",
				RightsDate	: "#fMarketCalendar_srtRightsDate"	
		}
	},
	buttons : {
		btSearch : "#fMarketCalendar_btSearch"
			}
	});
})(jQuery);

/*************************************************************************************
* Market Calendar Class
*************************************************************************************/
function MarketCalendarClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.tradingStatisticsForSymbolClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
MarketCalendarClazzSearchListing.prototype.getOption = function () {
	return $.marketCalendarClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
MarketCalendarClazzSearchListing.prototype.init = function() {
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
MarketCalendarClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
MarketCalendarClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
MarketCalendarClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       SEARCH_MARKETCALENDAR_URL, // override for form's 'action' attribute
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
MarketCalendarClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.marketCalendarClazzSearchListingOption;
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
MarketCalendarClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.marketCalendarClazzSearchListingOption;
	var _this = opts.clazzHandler;

	if(responseText.error != null && responseText.error != 'undefined' && responseText.error.hasErrors) {
		$.web_message.error(_this.validator, responseText.error);
	} else {
		$.web_message.actionMsg(responseText.error);

		try {
			if(opts.func.callbackExecuted != null && $.isFunction(opts.func.callbackExecuted)) {
				opts.func.callbackExecuted(responseText);
			} else {
				var model = responseText.model;
				if(model.searchResult != null) {

					$("#Listed_MarketCalendar_tableResult").tableManager_deleteAllRow();
					$("#Listed_MarketCalendar_divResult").show();

					//==========Finance show===============
					var arrData = new Array(6);
					var options = {
							totalCell:6,
							cellClasses: {
						
								0: "",
								1: "",
								2: "",
								3: "",
								4: "",
								5: "col_end"
							}
						};
					
					for(var index in model.searchResult) {
						var obj = model.searchResult[index];
						
						arrData[0] = "&nbsp;" + $.web_utils.dateFormat2Show(obj.rightsDate, "dd/mm/yyyy");
						arrData[1] = "&nbsp;" + $.web_utils.dateFormat2Show(obj.registerDate, "dd/mm/yyyy");
						arrData[2] = "&nbsp;" + $.web_utils.dateFormat2Show(obj.eventDate, "dd/mm/yyyy");
						arrData[3] = "&nbsp;" + obj.symbol;
						arrData[4] = "&nbsp;" + obj.eventDesc + "&nbsp;";
						arrData[5] = "&nbsp;" + obj.eventNote + "&nbsp;";
									
						//Show table and set css
						$("#Listed_MarketCalendar_tableResult").tableManager_addRow(arrData, options);
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