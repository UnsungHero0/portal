(function($) {
$.relatedCompanyForSnapshotSearchListingOption = function() {};
$.extend($.relatedCompanyForSnapshotSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	fields : {
		pagingIndex : "#RelatedCompany_pagingInfo_indexPage"
	},
	funcName : {
        navFuncName : "_goToRelatedCom" // function _goTo(index)
	},
	form : {
		name : "#fSnapshot_RelatedCompany" }
	});
})(jQuery);

/*************************************************************************************
* RelatedCompanyForSnapshotClazzSearchListing Class
*************************************************************************************/
function RelatedCompanyForSnapshotClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.RelatedCompanyForSnapshotSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
RelatedCompanyForSnapshotClazzSearchListing.prototype.getOption = function () {
	return $.relatedCompanyForSnapshotSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
RelatedCompanyForSnapshotClazzSearchListing.prototype.init = function() {
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
RelatedCompanyForSnapshotClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
RelatedCompanyForSnapshotClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
RelatedCompanyForSnapshotClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,


			// other available options:
			url:       URL_SEARCH_RELATEDCOMPANYFORSNAPSHOT, // override for form's 'action' attribute
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
RelatedCompanyForSnapshotClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.relatedCompanyForSnapshotSearchListingOption;
	var _this = opts.clazzHandler;

	$.web_message.clear();

	//+++ show loading image
	$(opts.loading).web_showLoading();
	
	// here we could return false to prevent the form from being submitted;
	// returning anything other than false will allow the form submit to continue
	return true;
};

/************Dinh nghia ket qua tra ve cua List Search***************/
RelatedCompanyForSnapshotClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.relatedCompanyForSnapshotSearchListingOption;
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
				if(model.listQuote != null) {

					$("#Listed_RelatedCompanyForSnapshot_tableResult").tableManager_deleteAllRow();
					$("#Listed_RelatedCompanyForSnapshot_divResult").show();

					//==========Finance show===============
					var arrData = new Array(4);
					var options;
					for(var index in model.listQuote) {
						var obj = model.listQuote[index];
						var color1= obj.infoCompany.exchangCode == 'OTC'? "company_black" : (obj.secInfo.chgIndex == 0) ? "company_yellow" : obj.secInfo.chgIndex > 0? "company_green" : "company_red";
						var color2 = obj.infoCompany.exchangCode == 'OTC'? "company_gray" : (obj.secInfo.chgIndex == 0) ? "company_yellow" : obj.secInfo.chgIndex > 0? "company_green" : "company_red";
						options = {
								totalCell:4,
								trClasses : index%2==0 ? "" : "",
								cellClasses: {
									0: "company_left",
									1: color1,
									2: color2,
									3: "company_right"
								}
							};
						
						var url = $.web_resource.getContext() + "tong-quan/" + obj.infoCompany.secCode.toLowerCase() + ".shtml";
						
						arrData[0] = "&nbsp;" + '<SPAN style="font-weight: bold; color: black;">' +"<a href='" + url + "'>" + $.web_utils.toValue(obj.infoCompany.secCode)+'</a></SPAN>'; 
						arrData[1] = $.web_utils.fomatNumberWithScaleSeprate(obj.currentIndex, 1, ',') + "&nbsp;";
						arrData[2] = obj.infoCompany.exchangCode == 'OTC' ? "n/a" : $.web_utils.fomatNumberWithScaleSeprate(obj.secInfo.chgIndex, 1)+ "("+$.web_utils.fomatNumberWithScaleSeprate(obj.secInfo.pctIndex, 2) + "%" + ")"+ "&nbsp;";
						arrData[3] = obj.infoCompany.strMarketCapital + "&nbsp;";
						//Show table and set css
						$("#Listed_RelatedCompanyForSnapshot_tableResult").tableManager_addRow(arrData, options);						
					}
					//Paging 
					var funcOptions = {
	                        goto_func: opts.funcName.navFuncName
	                  };
	                  var idOptions = {
	                        navContainer : "web_navRelatedCompanies"
	                  };
	                  $.web_paging.showRelatedCompanies(model.pagingInfo, funcOptions, idOptions);
				 }
			 }
			
		 } catch (e) {
	}
 }
};