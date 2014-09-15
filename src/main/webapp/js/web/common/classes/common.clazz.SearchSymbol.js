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
$.searchSymbolOption = function() {};
$.extend($.searchSymbolOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},
	funcName : {
		navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name : "#fSearchSymbol",
		fields : {
			companyName    	: "#fSearchSymbol_companyName",
			sectorCode    	: "#fSearchSymbol_sectorCode",
			exchangeCode    	: "#fSearchSymbol_exchangeCode"
		},
		result : {
			symbolSearchResultId : "#fSearchSymbol_result"
		}
	},
	buttons : {
		searchSymbol     : "#fSearchSymbol_btnSymbolSearch"
		
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: CommonClazzSearchSymbol Class
*
*************************************************************************************/
function CommonClazzSearchSymbol() {
	this.validator = null;
	
	jQuery.extend(jQuery.searchSymbolOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
CommonClazzSearchSymbol.prototype.getOption = function () {
	return $.searchSymbolOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
CommonClazzSearchSymbol.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();

	_this.resetInitData();
};
/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
CommonClazzSearchSymbol.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
CommonClazzSearchSymbol.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
CommonClazzSearchSymbol.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();
	
	var fValid = $(opts.form.name).valid();

	if(fValid) {
		var options = { 	         
	        beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback 
	        success:       _this.execute_PostSubmit,  // post-submit callback        
	 
	        // other available options: 
	        //url : 
	        type:      'post',        // 'get' or 'post', override for form's 'method' attribute 
	        dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)        
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
CommonClazzSearchSymbol.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.searchSymbolOption;
	var _this = opts.clazzHandler;	
	
	$.web_message.clear();
	
	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---
		
	var queryString = $.param(formData); 
//    alert('About to submit: \n\n' + queryString); 
	    
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
CommonClazzSearchSymbol.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.searchSymbolOption;
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
		var content = '';
		var locale = model.lang;
		
		var tbody = $($.searchSymbolOption.form.result.symbolSearchResultId + " table tbody");
		tbody.empty();
		if (model.result != null && model.result.length > 0){
			var forwardUrl = $('#forwardUrl').val();
			if(forwardUrl == 'null' || forwardUrl == ''){
				forwardUrl = 'tong-quan/';
			}
			for(var index in model.result) {
				var obj = model.result[index];
				content += "<tr>";
				content += "<td class=\"bordertd_\"><span class=\"txtText\">";
				var url = $.web_resource.getContext() + forwardUrl
						+ $.web_utils.toValue(obj.id.symbol).toLowerCase() + ".shtml";
				content += "<a href='" + url + "'>";
				content +=  $.web_utils.toValue(obj.id.symbol);
				content += "</a>"; 
				content += "</td>";
				
				content += "<td class=\"bordertd_\"><span class=\"txtText\">";
				content += "<a href='" + url + "'>";
				content += "<b>";
				content +=  $.web_utils.toValue(obj.id.abbName);
				content += "</b>";
				content += "</a>";
				content += "</td>";
				
				content += "<td class=\"bordertd_\"><span class=\"txtText\">";
				if (locale = 'vn')
					content +=  $.web_utils.toValue(obj.id.companyName);
				else
					content +=  $.web_utils.toValue(obj.id.companyFullName);
				content += "</td>";
				
				content += "<td class=\"bordertd_\"><span class=\"txtText\">";
				content +=  $.web_utils.toValue(obj.sectorName);
				content += "</td>";
				
				content += "<td class=\"col_end_\"><span class=\"txtText\">";
				content +=  $.web_utils.toValue(obj.exchangeName);
				content += "</td>";
				content += "</tr>";
			}
		}else {
			//+++ show message not found
				content += '<tr><td class="company_left"><span class="error">&nbsp;&nbsp;' + SYMBOLPROCESSINGACTION_NOT_FOUND + '</span></td>';
				content += '<td class="company_black"></td><td class="company_gray"></td><td class="company_right"></td></tr>';

		}
//		content += "</table>";
		tbody.append(content);
		
//		$('#fSearchSymbol_result').html(content);
		
		var funcOptions = {
			goto_func: opts.funcName.navFuncName
		};
		var idOptions = {
			navContainer : "fSearchSymbol_paging"
		};
		$.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);	
	}
};