/*
* @author: TungNQ (tungnq.nguyen@yahoo.com)
*
* $.web_message includes error, warning, info functionality.
* Method: $.web_message.error(fValidator, error, options)
* @param: error = {
				"actionErrors":[], 
				"actionMessages":[], 
				"fieldErrors":{"error1" : ["msg1", "msg2"], "error2" : ["msg1", "msg2"]}, 
				"hasActionErrors":false, 
				"hasActionMessages":false, 
				"hasErrors":false, 
				"hasFieldErrors":false
			}
*
* Method: $.web_message.warning(warningMsgs, options)
* @param:  warningMsgs = ["message 1", "message 2", "message 3"];
*
* Method: $.web_message.info(warningMsgs, options)
* @param:  infoMsgs = ["message 1", "message 2", "message 3"];
*/
jQuery.web_message = {
	/**
	 * { "errorAuth" : { 
	 * 	      "errorCode" : "01",
	 * 	      "errorMessages" : "Please Login",
	 * 	      "authUrl" : "URL"
	 * 	    }
	 * 	}
	 * jQuery.web_message.errorAuth(responseText);
	 * @return true|false
	 * true is okie for next process
	 * false is block next process 
	 */
	errorAuthMsg: function(responseText, opts) {
		var objResponseText = null;
		//convert if 
		if($.web_utils.isNotNull(opts) && opts.dataType == 'json') {
			objResponseText = responseText;
		} else {
			try {
				objResponseText = eval("(" + responseText + ")");
			} catch (e) {
				objResponseText = null;				
			}
		}
	
		var errorAuth = null;
		try {
			errorAuth = ($.web_utils.isNull(objResponseText) ? null : objResponseText.errorAuth);
		} catch (e) {
			errorAuth = null;
		}		
		if($.web_utils.isNotNull(errorAuth) &&  $.web_utils.isNotNull(errorAuth.hasAuthError)) {
			try {
				$("#sysDialogAuthError").dialog({
					autoOpen:	true,
					closeOnEsc:	true,
					bgiframe: true,
					modal: true,
					buttons: {
						Ok: function() {
							$(this).dialog('close');
							try {
								$.web_formAways.nav({}, {action :  errorAuth.authUrl});
							} catch (e){
								alert(e);
							}
						}
					}
				});
				$("#sysDialogAuthError").dialog('open'); 
				return false;
			} catch (e) {
//				if(DEBUG) {
//    				$.log("security error: authUrl=" + errorAuth.authUrl);    				
//    			}
				alert("security error: authUrl=" + errorAuth.authUrl);
			}
		}
		
		//+++ check idg auth error		
		var idgErrorAuth = null;
		try {
			idgErrorAuth = ($.web_utils.isNull(objResponseText) ? null : objResponseText.idgErrorAuth);
		} catch (e) {
			idgErrorAuth = null;
		}		
		if($.web_utils.isNotNull(idgErrorAuth) &&  $.web_utils.isNotNull(idgErrorAuth.hasAuthError)) {
			$.web_formAways.ajaxNav({}, 
					{action :  idgErrorAuth.authUrl
					, dataType : "text/html" 
					, callbackPostSubmit : function (responseText, statusText) {
							try {
								$("#sysDialogIdgAuthError").html(responseText);
								$("#sysDialogIdgAuthError").dialog({
									autoOpen:	true,
									closeOnEsc:	true,
									bgiframe: true,
									modal: true,
									height: 250,
									width: 400									
								});
								$("#sysDialogIdgAuthError").dialog('open');
							} catch (e) {
//								if(DEBUG) {
//				    				$.log("security error: authUrl=" + idgErrorAuth.authUrl);
//				    			}
								alert("security error: authUrl=" + idgErrorAuth.authUrl);
							}
						}
					});
			return false;
		}
		
		//+++ everything okie
		return true;
	}, 
		
	/**
	 * jQuery.web_message.showDialogErrorMsg(responseText);
	 */
	showDialogErrorMsg : function (responseText, options)  {
		var opts = jQuery.extend({
			div_dialog : "sysDialogSysError",
			action_error : "web_action_errors",
			action_message : "web_action_messages"
			}, options);
		
		var error = null;
		try {
			error = ($.web_utils.isNull(responseText) ? null : responseText.error);
		} catch (e) {
			error = null;
		}
		var showDialog = false;
		
		//+++ show action errors
		try {			
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasActionErrors) && error.hasActionErrors) {
				msgTxt = "";				
				for(index in error.actionErrors) {
					msgTxt += (index > 0 ? "<br/>" : "") + error.actionErrors[index];					
				}
				jQuery("#" + opts.action_error).html(msgTxt);
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'inline'});	
				
				showDialog = true;
			} else {
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
			}			
		} catch (e) {
//			if(DEBUG) {
//				$.log("showActionErrors: " + e);
//			}
			alert("showActionErrors: " + e);
		}
			
		//+++ show action messages
		try {
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasActionMessages) && error.hasActionMessages) {
				msgTxt = "";							
				for(index in error.actionMessages) {
					msgTxt += (index > 0 ? "<br/>" : "") + error.actionMessages[index];					
				}
				jQuery("#" + opts.action_message).html(msgTxt);
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'inline'});
				
				showDialog = true;
			} else {
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});	
			}			
		} catch (e) {
//			if(DEBUG) {
//				$.log("showActionErrors: " + e);
//			}
			alert("showActionErrors: " + e);
		}
				
//		if(DEBUG) {
//			$.log("showDialog:" + showDialog);
//		}
		
		if($.web_utils.isNotNull(error) &&  showDialog) {
			try {				
				$("#" + opts.div_dialog).dialog({
					autoOpen:	true,
					closeOnEsc:	true,
					bgiframe: true,
					modal: true,
					height: 250,
					width: 400
				});
				$("#" + opts.div_dialog).dialog('open'); 
				return false;
			} catch (e) {
//				if(DEBUG) {
//    				$.log("showDialogErrorMsg: " + e);
//    			}
				alert("showDialogErrorMsg: " + e);
			}
		}
		return true;
	},
	/**
	* show error message from Struts2-validation
	* var error = {
	*	"actionErrors":[], 
	*	"actionMessages":[], 
	*	"fieldErrors":{"field_name1" : ["msg1", "msg2"], "field_name2" : ["msg1", "msg2"]}, 
	*	"hasActionErrors":false, 
	*	"hasActionMessages":false, 
	*	"hasErrors":true, 
	*	"hasFieldErrors":true};
	*	
	*	var validator = $('#form_name').validate({ debug: false });
	*	$.web_message.error(validator, error);
	*/
	error: function(fValidator, error, options) {
		var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);
		var index;
		var msgTxt;
		//+++ show field errors
		if($.web_utils.isNotNull(fValidator)) {
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasFieldErrors) && error.hasFieldErrors) {				
				var fieldErrors = error.fieldErrors;					
				for(index in fieldErrors) {
					try {
						var errorMap = {};
						var fieldError = fieldErrors[index];
						var indexTmp = index;
						if(index.indexOf("model.") > -1) {
							indexTmp = index.replace("model.", "");											
						}
						var msgFieldError = "";
						for(var msgIndex in fieldError) {
							msgFieldError += (msgIndex > 0 ? "," : "") + fieldError[msgIndex];
						}
						errorMap[indexTmp] = msgFieldError;
						fValidator.showErrors(errorMap);
						// alert("showFieldErrors: " + msgFieldError);
					} catch (e) {
//						if(DEBUG) {
//	        				$.log("showFieldErrors: " + e);
//	        			}
						alert("showFieldErrors: " + e);
					}			
				}
			}
		}
		
		//+++ show action errors
		try {			
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasActionErrors) && error.hasActionErrors) {
				msgTxt = "";				
				for(index in error.actionErrors) {
					msgTxt += (index > 0 ? "<br/>" : "") + error.actionErrors[index];					
				}
				jQuery("#" + opts.action_error).html(msgTxt);
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'inline'});					
			} else {
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
			}			
		} catch (e) {
//			if(DEBUG) {
//				$.log("showActionErrors: " + e);
//			}
			alert("showActionErrors: " + e);
		}
			
		//+++ show action messages
		try {
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasActionMessages) && error.hasActionMessages) {
				msgTxt = "";							
				for(index in error.actionMessages) {
					msgTxt += (index > 0 ? "<br/>" : "") + error.actionMessages[index];					
				}
				jQuery("#" + opts.action_message).html(msgTxt);
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'inline'});
			} else {
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});	
			}			
		} catch (e) {
//			if(DEBUG) {
//				$.log("showActionErrors: " + e);
//			}
			alert("showActionErrors: " + e);
		}
		return;
	},
	
	/**
	* show error message from Struts2-validation
	* var error = {
	*	"actionErrors":[], 
	*	"actionMessages":[], 
	*	"fieldErrors":{"field_name1" : ["msg1", "msg2"], "field_name2" : ["msg1", "msg2"]}, 
	*	"hasActionErrors":false, 
	*	"hasActionMessages":false, 
	*	"hasErrors":true, 
	*	"hasFieldErrors":true};
	*	
	*	var validator = $('#form_name').validate({ debug: false });
	*	$.web_message.error(validator, error);
	*/
	actionMsg: function(error, options) {
		var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);
		var index;		
		//+++ show action messages
		try {
			if($.web_utils.isNotNull(error) && $.web_utils.isNotNull(error.hasActionMessages) && error.hasActionMessages) {
				var msgTxt = "";
				for(index in error.actionMessages) {
					msgTxt += (index > 0 ? "<br/>" : "") + error.actionMessages[index];					
				}			
//				 alert("msgTxt:" +  opts.action_message + " - " + msgTxt);
				jQuery("#" + opts.action_message).html(msgTxt);
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'inline'});
			} else {
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});
			}			
		} catch (e) {
//			if(DEBUG) {
//				$.log("showActionMessage: " + e);
//			}
			alert("showActionMessage: " + e);
		}
		return;
	},
	
	/*
	* Using to show warning message
	* var warningMsgs = ["message 1", "message 2", "message 3"];
	* $.web_message.warning(warningMsgs);
	*/
	warning: function(warningMsgs, options) {
		var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);	
		try {
			if(warningMsgs != null && warningMsgs.length > 0) {
				var msgTxt = "";
				for(var index in warningMsgs) {				
					msgTxt += (index > 0 ? "<br/>" : "") + warningMsgs[index];				
				}
				jQuery("#" + opts.action_error).html(msgTxt);
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'inline'});
			} else {
				jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
			}
		} catch (e) {
			jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
		}
		return;
	},
	
	/*
	* Using to show info message
	* var infoMsgs = ["message 1", "message 2", "message 3"];
	* $.web_message.info(infoMsgs);
	*/
	info: function(infoMsgs, options) {
		var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);			
		try {
			if(warningMsgs != null && warningMsgs.length > 0) {
				var msgTxt = "";
				for(var index in infoMsgs) {				
					msgTxt += (index > 0 ? "<br/>" : "") + infoMsgs[index];				
				}
				jQuery("#" + opts.action_message).html(msgTxt);
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'inline'});
			} else {
				jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});
			}
		} catch (e) {
			jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});
		}		
		return;
	},
	clearMsg: function(options) {
			var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);	
			jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});
	},
	clearError: function(options) {
			var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);	
			jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
	},
	clear:  function(options) {
			var opts = jQuery.extend({
							action_error : "web_action_errors",
							action_message : "web_action_messages"
							}, options);	
			jQuery("#" + opts.action_error).css({'color': 'red', 'display':'none'});
			jQuery("#" + opts.action_message).css({'color': 'blue', 'display':'none'});
	}
};