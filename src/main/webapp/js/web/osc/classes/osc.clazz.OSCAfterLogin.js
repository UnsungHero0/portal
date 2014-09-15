/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend oscAfterLoginOption
*
* jQuery.extend(jQuery.oscAfterLoginOption, {
* });
*
*************************************************************************************/

(function($) {
$.oscAfterLoginOption = function() {};
$.extend($.oscAfterLoginOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fOSCAfterLoginAfterLogin",
		fields : {
		
		},
		divs : {
		}
	},
	buttons : {
		
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: OSCClazzOSCAfterLogin Class
*
*************************************************************************************/
function OSCClazzOSCAfterLogin() {
	this.validator = null;
	
	jQuery.extend(jQuery.oscAfterLoginOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
OSCClazzOSCAfterLogin.prototype.getOption = function () {
	return $.oscAfterLoginOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
OSCClazzOSCAfterLogin.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();

	_this.resetInitData();
};
/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
OSCClazzOSCAfterLogin.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

/*************************************************************************************
*** check valid form function
**************************************************************************************/
OSCClazzOSCAfterLogin.prototype.validForm = function() {
	var _this = this;
	var opts = _this.getOption();
	return $(opts.form.name).valid();
};

/*************************************************************************************
*** execute function
**************************************************************************************/
OSCClazzOSCAfterLogin.prototype.execute = function() {
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
OSCClazzOSCAfterLogin.prototype.execute_PreSubmit = function(formData, jqForm, options) {
	var opts = $.oscAfterLoginOption;
	var _this = opts.clazzHandler;	
	
	$.web_message.clear();
	
	//+++ show loading image
	$(opts.loading).web_showLoading();
	//---
		
//	var queryString = $.param(formData); 
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
OSCClazzOSCAfterLogin.prototype.execute_PostSubmit = function(responseText, statusText) {
	var opts = $.oscAfterLoginOption;
	var _this = opts.clazzHandler;
	alert('responseText = ' + responseText);
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
		/*
		$(opts.form.name).resetForm();
					
		try {
			if(opts.func.callbackExecuted !== null && $.isFunction(opts.func.callbackExecuted)) {
				opts.func.callbackExecuted(responseText);
			}			
		} catch (e) {			
		}
		*/
		
	}
};

OSCClazzOSCAfterLogin.prototype.genSubjectList = function(responseText, statusText) {	
	var str = "";	
	var $tabs = $('#container-1').tabs();
	var selected = $tabs.tabs('option', 'selected');
	
	// append content of productDetails
	var wpProduct = responseText.model.wpProduct;	
	if (wpProduct != null) {
		if (wpProduct.productCode == 'FAQs') {
			var divResult = $('#listFAQ');
			str = str + '<ul id="ulnews">';
			$.each(wpProduct.wpSubjectList, function(intIndex, subject) {
				// gen subject content
				str = str + '<li><a href="' + URL_OSC_CONTETNT_DETAIL + '?subjectCode=' + subject.subjectCode + '&productCode=' + wpProduct.productCode + '&stab=' + selected + '">' + subject.subjectName + '</a></li>';			
//				str = str + '<li><a href="javascript:viewContentDetail(\'' + wpProduct.productCode + '\',\'' + subject.subjectId + '\');">' + subject.subjectName + '</a></li>';
				//str = str + '<h3><a href="#">' + subject.subjectName + '</a></h3>';
//				str = str + '<div id="' + wpProduct.productCode + '|' + subject.subjectId + '" style="display: none">' + subject.subjectContent + '</div>';
			});
			str = str + '</ul>';
			divResult.html(str);
		} else if (wpProduct.productCode == 'SERVICE_ACCOUNT') {
			var divResult = $('#listServiceYourAccount');
			$.each(wpProduct.wpSubjectList, function(intIndex, subject) {
				str = str + '<p class="label"><a href="' + URL_OSC_CONTETNT_DETAIL + '?subjectCode=' + subject.subjectCode + '&productCode=' + wpProduct.productCode + '&stab=' + selected + '">' + subject.subjectName + '</a></p>';
			});
			divResult.html(str);
		} else if (wpProduct.productCode == 'MANAGE_CASH') {
			var divResult = $('#listManageYourCash');
			$.each(wpProduct.wpSubjectList, function(intIndex, subject) {
				str = str + '<p class="label"><a href="' + URL_OSC_CONTETNT_DETAIL + '?subjectCode=' + subject.subjectCode + '&productCode=' + wpProduct.productCode + '&stab=' + selected + '">' + subject.subjectName + '</a></p>';
			});
			divResult.html(str);
		}
	}
}

OSCClazzOSCAfterLogin.prototype.genFormAndApplication = function(responseText, statusText) {
	var html ='';
	var documents = responseText.model.wpDocumentList;
	if(documents != null && documents.length != 0){
		var lastProductId = 0;
		// seperate GUI into 2 field(left,right). This variable 'size' define how many documents per field.
		var size = Math.round(documents.length / 2);
		var isFirstElementColumnRight = false;
		$.each(documents, function(i, document){
			var product = document.wpProduct;
			// start right field
			if(size == i && i > 1){
				html += '</div><div class="box_440 right">';
				isFirstElementColumnRight = true;
			}
			if(size < i){
				isFirstElementColumnRight = false;
			}
			// left field
			if(lastProductId == 0){
				html = "<div class='box_440 left'>";
				html += "<ul class='list16 line'>";
				html += '<li class="title">' + product.productName + '</li>';
			} else if(lastProductId != product.productId) {
				html += '</ul>';
				html += "<ul class='list16 line'>";
				html += '<li class="title">' + product.productName + '</li>';
			} else if((lastProductId == product.productId) && isFirstElementColumnRight){
				html += "<ul class='list16 line'>";
				isFirstElementColumnRight = false;
			}
			var urldownload = "javascript:downloadResource('" + document.documentUri + "','" + document.documentName + "');";
			html += '<li><a href="' + urldownload + '">' + '<img src="' + $.web_resource.getContext() + '/images/icons/icon-1.png"/> ' + document.documentTitle + '</a></li>';
			
			lastProductId = product.productId;
		});
		html += '</ul></div>';
		$('#listFormAndApplication').html(html);
	}
}

OSCClazzOSCAfterLogin.prototype.genContactUs = function(responseText, statusText) {
	var divResult = $('#listContactUs');
	var str = "";	
	
	// append content of productDetails
	var wpProduct = responseText.model.wpProduct;
	if (wpProduct != null) {		
		// gen product content
		str = wpProduct.productOverview;			
				
	}
	divResult.html(str);
}

