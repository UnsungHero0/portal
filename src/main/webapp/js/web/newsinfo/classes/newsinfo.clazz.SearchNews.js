(function($) {
$.searchNewsClazzSearchListingOption = function() {};
$.extend($.searchNewsClazzSearchListingOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null //function (responseText, statusText)
	},
	funcName : {
        navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name 		: "#fSearchNews",
		fields : {
				keyWord 		: "#fSearchNews_keyWord",
				pagingIndex : "#fSearchNews_pagingInfo_indexPage"
		},
		searchResult : {
			searchNewsResult : "#fSearchNews_result"
		}
	},
	buttons : {
		btSearch : "#fSearchNews_Search"
			}
	});
})(jQuery);

/*************************************************************************************
* Search News Class
*************************************************************************************/
function SearchNewsClazzSearchListing() {
	this.validator = null;

	jQuery.extend(jQuery.searchNewsClazzSearchListingOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
SearchNewsClazzSearchListing.prototype.getOption = function () {
	return $.searchNewsClazzSearchListingOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
SearchNewsClazzSearchListing.prototype.init = function() {
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
SearchNewsClazzSearchListing.prototype.getSelectedItemIds = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
SearchNewsClazzSearchListing.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** execute function
**************************************************************************************/
SearchNewsClazzSearchListing.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();

	var fValid = $(opts.form.name).valid();
	if(fValid) {
		var options = {
			beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback
			success:       _this.execute_PostSubmit,  // post-submit callback
			//complete:  _this.execute_Complete,

			// other available options:
			url:       URL_SEARCH_NEWS, // override for form's 'action' attribute
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
SearchNewsClazzSearchListing.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.searchNewsClazzSearchListingOption;
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
SearchNewsClazzSearchListing.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.searchNewsClazzSearchListingOption;
	var _this = opts.clazzHandler;
	//+++ hidden loading image
	$(opts.loading).web_hiddenLoading();
	//---

	if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {						
		$.web_message.error(_this.validator, responseText.error);
		try {
			if($.isFunction(opts.func.callbackExecuteFail)) {
				opts.func.callbackExecuteFail();
			}
		} catch (e) {			
		}			
	} else {
		if($.isFunction(opts.func.callbackExecuted)) {
			opts.func.callbackExecuted();
		}
		
		var model = responseText.model;				
		if(model != null) {
			var content = '';
			
			content += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';	
			content += '<tr>';
			content += '<td width="100%" height="40px" class="company_left">';
			content += '<span class="txtText"><b class="td_title_Calendar">' +'['+ $.web_utils.toValue(model.pagingInfo.total) + ']' + '<b class="bluetext">' + RESULTBYKEYWORD_MESSAGE + '</b>'+ '[' + $.web_utils.toValue(model.keyWord)+ ']' + '</span></b>';			
			content += '<td width="0%" class="company_right">';
			content += '</td>';
			content += '</tr>';
			content += '</table>';
			
			for(var index in model.searchResult) {
				var obj = model.searchResult[index];			
                if (model != null && model.searchResult != null && model.searchResult.length > 0 ) {
    				content += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
    				content += '<tr>';
    				content += '<td width="100%" class="company_left">';
    				//content += '<span class="txtText"><b class="bluetext">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType) + '</span></b>';			
    				content += '<p><span class="txtText">' + $.web_utils.toValue(obj.newsAbstract) + '</span>';
    				content += '<p><span class="txtText"><b>' + '<a href="' + URL_NEWS_DETAILS + '?ifoNews.newsId=' + obj.newsId + '&newsType=' + (obj.newsType ? obj.newsType : "") + '">' + DETAILS_TEXT + '</a>' + '</span></b>';
    				content += '</td>';				
    				content += '<td width="0%" class="company_right">';
    				content += '</td>';
    				content += '</tr>';
	
                }else {
               	 strContent += '';
                }	
			}
			content += '</table>';
			
			$('#fSearchNews_result').html(content);
			
			var funcOptions = {
				goto_func: opts.funcName.navFuncName
			};
			var idOptions = {
				navContainer : "fSearchNews_paging"
			};
			$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);
			
		}	
		
	}
};