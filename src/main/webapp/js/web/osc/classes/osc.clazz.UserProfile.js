/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend userProfileOption
*
* jQuery.extend(jQuery.userProfileOption, {
* });
*
*************************************************************************************/

(function($) {
$.userProfileOption = function() {};
$.extend($.userProfileOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fUserProfile",
		fields : {
			accountNumber			: "#fUserProfile_accountNumber",
			caller					: "#fUserProfile_caller",
			fullName				: "#fUserProfile_fullName",
			selectedAccountNumber	: "#fUserProfile_selectedAccountNumber",
			custodyCode				: "#fUserProfile_custodyCode",
			address					: "#fUserProfile_address",
			homeFone				: "#fUserProfile_homeFone",
			mobileFone				: "#fUserProfile_mobileFone",
			emailConfirm			: "#fUserProfile_emailConfirm",
			floorTrading			: "#fUserProfile_floorTrading",
			teleTrading				: "#fUserProfile_teleTrading",
			teleTrading_detail		: "#fUserProfile_teleTrading_detail",
			smsFone					: "#fUserProfile_smsFone",
			tradeFone1				: "#fUserProperties_tradeFone1",
			tradeFone2				: "#fUserProfile_tradeFone2",
			onlineTrading			: "#fUserProfile_onlineTrading",
			onlineTrading_detail	: "#fUserProfile_onlineTrading_detail",
			cacheData				: "#fUserProfile_cacheData",
			userName				: "#fUserProfile_userName",
			email					: "#fUserProfile_email"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: OSCClazzUserProfile Class
*
*************************************************************************************/
function OSCClazzUserProfile() {
	this.validator = null;
	
	jQuery.extend(jQuery.userProfileOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
OSCClazzUserProfile.prototype.getOption = function () {
	return $.userProfileOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
OSCClazzUserProfile.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();

	_this.resetInitData();
	
};
/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
OSCClazzUserProfile.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
OSCClazzUserProfile.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
OSCClazzUserProfile.prototype.execute = function() {
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
OSCClazzUserProfile.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.userProfileOption;
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
OSCClazzUserProfile.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.userProfileOption;
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
		
		var model = responseText.model;
		$(opts.form.fields.fullName).html(model.userProfile.fullName);
		$(opts.form.fields.selectedAccountNumber).html(model.userProfile.accountNumber);
		$(opts.form.fields.custodyCode).html(model.userProfile.custodyCode);
		$(opts.form.fields.address).html(model.userProfile.address);
		$(opts.form.fields.homeFone).html(model.userProfile.homeFone);
		$(opts.form.fields.mobileFone).html(model.userProfile.mobileFone);
		$(opts.form.fields.emailConfirm).html(model.userProfile.emailConfirm);
		$(opts.form.fields.userName).html(model.onlineUser.userName);
		$(opts.form.fields.email).html(model.onlineUser.email);
		$(opts.form.fields.tradeFone1).html(model.userProfile.tradeFone1);
		$(opts.form.fields.tradeFone2).html(model.userProfile.tradeFone2);
		
		if (model.userProfile.floorTrading == "Y") {
			$(opts.form.fields.floorTrading).html(Messages_Commons_Buttons_Yes);
		} else {
			$(opts.form.fields.floorTrading).html(Messages_Commons_Buttons_No);
		}
		
		if (model.userProfile.teleTrading == "Y") {
			$(opts.form.fields.teleTrading).html(Messages_Commons_Buttons_Yes);
			$(opts.form.fields.teleTrading_detail).show();
		} else {
			$(opts.form.fields.teleTrading).html(Messages_Commons_Buttons_No);
			$(opts.form.fields.teleTrading_detail).hide();
		}
		
		if (model.userProfile.onlineTrading == "Y") {
			$(opts.form.fields.onlineTrading).html(Messages_Commons_Buttons_Yes);
			$(opts.form.fields.onlineTrading_detail).show();
		} else {
			$(opts.form.fields.onlineTrading).html(Messages_Commons_Buttons_No);
			$(opts.form.fields.onlineTrading_detail).hide();
		}
		
		// update cache data
		$(opts.form.fields.cacheData).val(model.cacheData);
	}
};


