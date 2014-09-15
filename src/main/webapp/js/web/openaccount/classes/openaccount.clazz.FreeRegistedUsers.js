(function($) {
$.openaccountFreeRegistedUsers = function() {};
$.extend($.openaccountFreeRegistedUsers, {
	func : {
		callbackExecuted : null, //function (responseText, statusText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fFreeRegistedUsers",
		fields : {
			fullName		:"#fFreeRegistedUsers_fullName",
			email  			: "#fFreeRegistedUsers_email",  			
			passWord 		: "#fFreeRegistedUsers_passWord",						
			confirmPass 	: "#fFreeRegistedUsers_confirmPass",
			dateofBirth 	: "#fFreeRegistedUsers_dateofBirth",	
			sex 			: "#fFreeRegistedUsers_sex",
			address 		: "#fFreeRegistedUsers_address",
			province 		: "#fFreeRegistedUsers_province",
			country 		: "#fFreeRegistedUsers_country",
			telephone 		: "#fFreeRegistedUsers_telephone",
			agreement 		: "#fFreeRegistedUsers_agreement"
		},
		
		buttons : {
			save 			: '#fFreeRegistedUsers_btSave'
		}
	}
});
})(jQuery);

/*************************************************************************************
*
* @public: OpenaccountClazzFreeRegistedUsers Class
*
*************************************************************************************/
function OpenaccountClazzFreeRegistedUsers() {
	this.validator = null;
	
	jQuery.extend(jQuery.openaccountFreeRegistedUsers, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.getOption = function () { 
	return $.openaccountFreeRegistedUsers;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
	//+++ resetInitData
	_this.resetInitData();	
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;	
	
	//$('#fFreeRegistedUsers_btSave').attr("disabled", "disabled");
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.execute = function() {
	var _this = this;
	var opts = _this.getOption();
	
	var fValid = $(opts.form.name).valid();
	//alert(fValid);
	if(fValid) {
		var options = { 	         
	        beforeSubmit:  _this.execute_PreSubmit,  // pre-submit callback 
	        success:       _this.execute_PostSubmit,  // post-submit callback        
	 
	        // other available options: 
	        //url:       "/ajax/account/AccountManagerCheckAccount",         
	        // override for form's 'action' attribute 
	        //url : URL_CREATE_FREEACCOUNTUSERS,
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
OpenaccountClazzFreeRegistedUsers.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.openaccountFreeRegistedUsers;
	var _this = opts.clazzHandler;	
	
	$.web_message.clear();

	
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
*************************************************************************************/
OpenaccountClazzFreeRegistedUsers.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.openaccountFreeRegistedUsers;
	var _this = opts.clazzHandler;
		
	if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {						
		$.web_message.error(_this.validator, responseText.error);
		//$.web_message.error(null, responseText.error);
		try {
			if($.isFunction(opts.func.callbackExecuteFail)) {
				opts.func.callbackExecuteFail();
			}
		} catch (e) {			
		}			
	} else {
		$.web_message.actionMsg(responseText.error);
		
		$(opts.form.name).resetForm();
					
		try {
			if($.isFunction(opts.func.callbackExecuted)) {
				opts.func.callbackExecuted(responseText);
			}
			//window.location=URL_FREEREGISTEDUSERS_SUCCESS;
		} catch (e) {			
			alert(e);
		}
	}
};