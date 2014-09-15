/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend changePasswordOption
*
* jQuery.extend(jQuery.changePasswordOption, {
* });
*
*************************************************************************************/

(function($) {
$.changePasswordOption = function() {};
$.extend($.changePasswordOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fChangePassword",
		fields : {
			errorMessage		: "#fChangePassword_errorMessage",
			currentPassword		: "#fChangePassword_currentPassword",
			newPassword			: "#fChangePassword_newPassword",
			confirmPassword		: "#fChangePassword_confirmPassword"
		}
	},
	buttons : {
		save 	: "#fChangePassword_save"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: OSCClazzChangePassword Class
*
*************************************************************************************/
function OSCClazzChangePassword() {
	this.validator = null;
	
	jQuery.extend(jQuery.changePasswordOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
OSCClazzChangePassword.prototype.getOption = function () {
	return $.changePasswordOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
OSCClazzChangePassword.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();

	_this.resetInitData();
	
	//+++ init validation
	_this.validator = $(opts.form.name).validate({debug: false});
	
	$(opts.form.fields.currentPassword).rules("add", { required: true, maxlength: 32, minlength: 6});
	$(opts.form.fields.newPassword).rules("add", { required: true, maxlength: 32, minlength: 6});
	$(opts.form.fields.confirmPassword).rules("add", { required: true, maxlength: 32, minlength: 6});
	
};
/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
OSCClazzChangePassword.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
OSCClazzChangePassword.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
OSCClazzChangePassword.prototype.execute = function() {
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
OSCClazzChangePassword.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.changePasswordOption;
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
OSCClazzChangePassword.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.changePasswordOption;
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
		$.web_message.actionMsg(responseText.error);

	}
};


