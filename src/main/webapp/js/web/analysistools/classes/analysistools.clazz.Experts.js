/*************************************************************************************
* @author 
*
* Define Common Object
* using the flowing query to extend searchSymbolOption
*
* jQuery.extend(jQuery.CommonOption, {
* });
*
*************************************************************************************/

(function($) {
$.expertsOption = function() {};
$.extend($.expertsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},
	funcName : {
		navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name : "#fExperts",
		fields : {
			content		: "#fExperts_content"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: AnalysisTooolsClazzExperts Class
*
*************************************************************************************/
function AnalysisTooolsClazzExperts() {
	this.validator = null;
	
	jQuery.extend(jQuery.expertsOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
AnalysisTooolsClazzExperts.prototype.getOption = function () {
	return $.expertsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
AnalysisTooolsClazzExperts.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();

	_this.resetInitData();
};
/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
AnalysisTooolsClazzExperts.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
AnalysisTooolsClazzExperts.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
AnalysisTooolsClazzExperts.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();
	
	var fValid = $(opts.form.name).valid();

	if(fValid) {
		var options = {
	        beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback 
	        success:       _this.execute_PostSubmit,  // post-submit callback        
	 
	        // other available options: 
	        url 		: URL_VIEW_CONTENT_OVERVIEW,
	        type		: 'post',        // 'get' or 'post', override for form's 'method' attribute 
	        dataType	: 'json'        // 'xml', 'script', or 'json' (expected server response type)        
	        //clearForm: true        // clear all form fields after successful submit 
	        //resetForm: true        // reset the form after successful submit 
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
AnalysisTooolsClazzExperts.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.expertsOption;
	var _this = opts.clazzHandler;	
	
	$.web_message.clear();
	
	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---
		
	var queryString = $.param(formData); 
	    
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
* @param : responseText is JSON data format 
{ "error" : { 
      "actionErrors" : [ "Có lỗi thực thi" ],
      "actionMessages" : [  ],
      "fieldErrors" : {  },
      "hasActionErrors" : true,
      "hasActionMessages" : false,
      "hasErrors" : true,
      "hasFieldErrors" : false
    },
  "model" : { 
      "post" : { 
         
        },
      "content" : { 

        }
    }
}*
*************************************************************************************/
AnalysisTooolsClazzExperts.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.expertsOption;
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
		
		var att;
		var cat = 'img';
		
		var expertContent = $(opts.form.fields.content);
		expertContent.html("");
		
		var model = responseText.model;
		var strContent = '<table cellpadding="0" cellspacing="4" border="0" width="742">';
		if (model.searchResult != null && model.searchResult.length > 0) {
			$.each(model.searchResult, function(i, obj){
				if (i%2 == 0) {
					strContent += '<tr>';
				}
				strContent += '<td width="365" valign="top"><div class="clearfix">';
				$.each(obj.imagesList, function(j, obj2){
					var idx = obj2.indexOf("||");
					if (idx > -1 && obj2.substring(0, idx) == 'Image') {						
						strContent += '<span class="img_CG">' + '<img src="' + viewDownloadImage(obj2.substring(idx+2), cat, 100, 115) + '" />' + '</span>';
					}
				})				
				strContent += '<p><b class="bluetext">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att, URL_NEWS_DETAILS_Expert) + '</b></p>';
				strContent += '<p class="textSapo">' + obj.newsDateTimeStr + ' | ' + obj.newsDateStr + '</p>';
				strContent += '<p class="textSapo">' + $.web_utils.toValue(obj.resource) + '</p>';				
				strContent += '<cite class="textDesc">' + $.web_utils.toValue(obj.newsAbstract) + '</cite>';
				strContent += '</div></td>';
				
				if (i%2 == 0) {
					strContent += '<td>&nbsp;</td>';
				}
				
				if (i%2 == 1) {
					strContent += '</tr>';
					strContent += '<tr><td colspan="3"><div><img src="' + $.web_resource.getContext() + '/images/front/spacer.gif" width="1" height="20" /></div></td></tr>';
				}
			})
		} else {
			// show message not found
			strContent += '<tr><td><p><span class="error">' + COMMON_NOT_FOUND + '</span></p></td></tr>';
		}
		
		if (model.searchResult.length%2 == 1) {
			strContent += '</tr>';
		}
		strContent += '</table>';
		expertContent.html(strContent);
		
		var funcOptions = {
        	goto_func : opts.funcName.navFuncName
		};
        var idOptions = {
        	navContainer : "web_navExperts"
        };
        $.web_paging.showRightNewsItem(model.pagingInfo, funcOptions, idOptions);
		
	}
};
