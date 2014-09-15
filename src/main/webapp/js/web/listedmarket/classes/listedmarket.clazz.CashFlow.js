(function($) {
$.cashFlowClazzSearchListingOption = function() {};
$.extend($.cashFlowClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	form : {
		name : "#fCashFlow",
		fields : {
				moneyUnit : "#fCashFlow_MoneyUnit",
				fiscalQuarter : "#fCashFlow_fiscalQuarter",
				fiscalYear : "#fCashFlow_fiscalYear",
				numberTerm : "#fCashFlow_numberTerm"
		}
	},
	buttons : {
		btView : "#fCashFlow_View"
			}
	});
})(jQuery);

/*************************************************************************************
* IncomeStatementClazzSearchListing Class
*************************************************************************************/
function CashFlowClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.cashFlowClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
CashFlowClazzSearchListing.prototype.getOption = function () {
	return $.cashFlowClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
CashFlowClazzSearchListing.prototype.init = function() {
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
CashFlowClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
CashFlowClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
CashFlowClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_CASHFLOW, // override for form's 'action' attribute
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
CashFlowClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.cashFlowClazzSearchListingOption;
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
CashFlowClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.cashFlowClazzSearchListingOption;
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
				if(model.financeInfoList != null) {

					$("#Listed_CashFlow_tableResult").tableManager_deleteAllRow();

					$("#divFiscalDate1").html(model.financeInfoFirst.strFiscalDate1);
					$("#divFiscalDate2").html(model.financeInfoFirst.strFiscalDate2);
					$("#divFiscalDate3").html(model.financeInfoFirst.strFiscalDate3);
					$("#divFiscalDate4").html(model.financeInfoFirst.strFiscalDate4);
					$("#divFiscalDate5").html(model.financeInfoFirst.strFiscalDate5);

					//==========Finance show===============
					var arrData = new Array(6);
					var options1 = {
						totalCell:6,
						cellClasses: {
							0: "incomestatement_1",
							1: "incomestatement_right_1",
							2: "incomestatement_right_1",
							3: "incomestatement_right_1",
							4: "incomestatement_right_1",
							5: "incomestatement_end_1"
						}
					};
					var options2 = {
						totalCell:6,
						cellClasses: {
							0: "incomestatement_2",
							1: "incomestatement_right_2",
							2: "incomestatement_right_2",
							3: "incomestatement_right_2",
							4: "incomestatement_right_2",
							5: "incomestatement_end_2"
						}
					};
					for(var index in model.financeInfoList) {
						var obj = model.financeInfoList[index];
						if (obj.displayLevel == 0) {
							arrData[0] = obj.itemName ;
							arrData[1] = "&nbsp;";
							arrData[2] = "&nbsp;";
							arrData[3] = "&nbsp;";
							arrData[4] = "&nbsp;";
							arrData[5] = "&nbsp;"; 
						} else {
							arrData[0] = obj.itemName ;
							arrData[1] = obj.strNumericValue1 + "&nbsp;";
							arrData[2] = obj.strNumericValue2 + "&nbsp;";
							arrData[3] = obj.strNumericValue3 + "&nbsp;";
							arrData[4] = obj.strNumericValue4 + "&nbsp;";
							arrData[5] = obj.strNumericValue5 + "&nbsp;";
						}
						//Show table and set css
						$("#Listed_CashFlow_tableResult").tableManager_addRow(arrData, (obj.displayLevel != 0 && obj.displayLevel != 1) ? options1 : options2);
					}
				 }
			 }
			
		 } catch (e) {
	}
 }
};