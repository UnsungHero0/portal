/*
* @author: TungNQ (tungnq.nguyen@yahoo.com)
*/
jQuery(function($) {
	$.ajaxSetup({
	  type: "POST"
	  , global: true
	  ,error: function(XMLHttpRequest, textStatus, errorThrown){
			var oHttp = XMLHttpRequest;
			var httpStatus = "";
			if($.web_utils.isNotNull(oHttp) && $.web_utils.isNotNull(oHttp.status)) {
				try {	
					httpStatus = oHttp.status;
					if(httpStatus != 200) {	
						var opts = null;					
						//+++ http error 4xx
						if(httpStatus >= 400 && httpStatus <= 499) {
							opts = {div_dialog : "sysDialogHttpError"
								, div_dialog_err_content : "sysDialogHttp4xErrorContent"
								, div_dialog_err_status : "sysDialogHttp4xErrorStatus"
								};
						//+++ http error 5xx
						} else if(httpStatus >= 500 && httpStatus <= 599) {
							opts = {div_dialog : "sysDialogHttp5xError"
									, div_dialog_err_content : "sysDialogHttp5xErrorContent"
									, div_dialog_err_status : "sysDialogHttp5xErrorStatus"
									};
						} else {
							opts = {div_dialog : "sysDialogHttpError"
									, div_dialog_err_content : "sysDialogHttpErrorContent"
									, div_dialog_err_status : "sysDialogHttpErrorStatus"
									};
						}
						
						$("#" + opts.div_dialog_err_status).html(httpStatus);
						$("#" + opts.div_dialog).dialog({
							autoOpen:	true,
							closeOnEsc:	true,
							height: 250,
							width: 400
						});
						$("#" + opts.div_dialog).dialog('open'); 
						return false;
					}
				} catch (e) {
//					if(DEBUG) {
//	    				$.log("Ajax Error: " + e);
//	    			}
				}				
			}
//			if(DEBUG) {
//				$.log('Something wrong...!!! - httpStatus:' + httpStatus + ", textStatus:" + textStatus + ", errorThrown: " + errorThrown);
//			}
			return true;
		}
	});
	
	//+++ setting dialog
	$.ui.dialog.prototype.options.bgiframe = true;
});

/**
*
* @param: pagingInfo is JSON data
* "pagingInfo":{
*	"index":0
*	,"indexPage":1
*	,"offset":3
*	,"total":1
*	,"totalPage":1
* }
*
* @param funcOptions with default value
* {
* 	first_func : null,
*	previous_func : null,
* 	next_func: null,
* 	last_func: null,
* 	goto_func: null
* }
*
* @param : idOptions with default value
*	{
*	indexTextbox : "web_navIndexText",
*	totalText : "web_navTotalText",
*	pageText : "web_navPageText",
*	first : "web_navFirst", 
*	previous : "web_navPrevious",
*	next : "web_navNext",
*	last : "web_navLast",
*	navGoTo : "web_navGoTo"
*	}
*/
jQuery.web_paging = { 
	/*
	* show nav paging as First | Previous | Next | Last - Page/Total - Go 
	*/
	show : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({
					   		first_func : null,
					 		previous_func : null,
					   		next_func: null,
					   		last_func: null,
					   		goto_func: null
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		indexTextbox : "web_navIndexText",
					   		totalText : "web_navTotalText",
					   		pageText : "web_navPageText",
					   		first : "web_navFirst", 
					 		previous : "web_navPrevious",
					   		next : "web_navNext",
					   		last : "web_navLast",
					   		navGoTo : "web_navGoTo"
					   }, idOptions);
					   
		var indexPage = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		
		
		
		var firstIndex = (indexPage <=1 || totalPage <= 1 ? 0 : 1);
		var previousIndex = (indexPage <= 1 ? 0 : indexPage - 1);
		var nextIndex = ((totalPage - indexPage) <= 0 ? 0 : (indexPage + 1));
		var lastIndex = (totalPage <= 1 || (totalPage - indexPage) <= 0 ? 0 : totalPage);
		
		//+++ bind/unbind onclick handler for FIRST
		jQuery("#"+idOpts.first).unbind("click");
		jQuery("#"+idOpts.first).bind("click", function(){			
			if(firstIndex > 0 && funcOpts.first_func != null) {
				funcOpts.first_func(firstIndex);
			} else {
		  		//alert( jQuery(this).text() );
		  		//do nothing
		  	}
		});
		//+++ update i18n
		jQuery("#"+idOpts.first).html(jQuery.web_i18n.buttons.first);
		//---
			
		//+++ bind/unbind onclick handler for PREVIOUS
		jQuery("#"+idOpts.previous).unbind("click");
		jQuery("#"+idOpts.previous).bind("click", function(){			
			if(previousIndex > 0 && funcOpts.previous_func != null) {
				funcOpts.previous_func(previousIndex);
			} else {
		  		//alert( jQuery(this).text() );
		  		//do nothing
		  	}
		});
		//+++ update i18n
		jQuery("#"+idOpts.previous).html(jQuery.web_i18n.buttons.previous);
		//---
		
		//+++ bind/unbind onclick handler for NEXT
		jQuery("#"+idOpts.next).unbind("click");
		jQuery("#"+idOpts.next).bind("click", function(){			
			if(nextIndex > 0 && funcOpts.next_func != null) {
				funcOpts.next_func(nextIndex);
			} else {
		  		//alert( jQuery(this).text() );
		  		//do nothing
		  	}
		});
		//+++ update i18n
		jQuery("#"+idOpts.next).html(jQuery.web_i18n.buttons.next);
		//---
		
		//+++ bind/unbind onclick handler for LAST
		jQuery("#"+idOpts.last).unbind("click");
		jQuery("#"+idOpts.last).bind("click", function(){			
			if(lastIndex > 0 && funcOpts.last_func != null) {
				funcOpts.last_func(lastIndex);
			} else {
		  		//alert( jQuery(this).text() );
		  		//do nothing
		  	}
		});
		//+++ update i18n
		jQuery("#"+idOpts.last).html(jQuery.web_i18n.buttons.last);
		//---
		
		//+++ bind/unbind onclick handler for NAV_GOTO
		jQuery("#"+idOpts.navGoTo).unbind("click");
		jQuery("#"+idOpts.navGoTo).bind("click", function(){			
			try {
				var gotoIndex = jQuery("#"+idOpts.indexTextbox).val();
				if(gotoIndex > 0 && gotoIndex <= totalPage && gotoIndex != indexPage) {
					funcOpts.last_func(gotoIndex);
				} else {
			  		//alert( jQuery(this).text() );
			  		//do nothing
			  	}
			} catch (e) {
				//alert( jQuery(this).text() );
		  		//do nothing
			}				
		});
		//+++ update i18n
		jQuery("#"+idOpts.navGoTo).html(jQuery.web_i18n.buttons.go);
		//---
		
		//+++ update i18n
		jQuery("#"+idOpts.pageText).html(jQuery.web_i18n.buttons.page);
		//---
			
		jQuery("#"+idOpts.indexTextbox).val(indexPage);
		jQuery("#"+idOpts.totalText).text(totalPage);
		
		return;
	} ,
	
	/*
	* show nav paging as First | Previous | 1 | 2 | 3 | 4 | Next | Last 
	*/
	showItem : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({					   		
					   		goto_func: "void"
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		navContainer : "fSearchSymbol_paging"
					   }, idOptions);
		var pageIndex = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		var func = "javascript:" + funcOpts.goto_func;
		
//		var strNavBuf = '<div class="fpCalendar">';
//		strNavBuf += '<div class="fpCalendar-left"></div>';
//		strNavBuf += '<div class="fpCalendar-right"></div>';
//		strNavBuf += '<div class="fpCalendar-center" align="right">';
		var strNavBuf = '<div class="bt_change clearfix">';
		
		// show 'first'
		if(pageIndex > 3) {
			strNavBuf += '<span class="back">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (1) + ')">'+ '<<' + '</a>';
			strNavBuf += '</span>';
		}
		
		// show previous
		if(pageIndex > 1) {
			strNavBuf += '<span class="back">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')">'+ '<' + '</a>';
			strNavBuf += '</span>';
		}
		
		strNavBuf += '<span class=\"index\">';
		
		if (pageIndex > 1) {
			strNavBuf += ' ... ';
		}
		var index = 0;
		for(var i in pagingInfo.pagingItems) {
			var pagingItem = pagingInfo.pagingItems[i];
			var itemIndex = pagingItem.index;
			var itemText = pagingItem.text;
			
			if ((pageIndex == totalPage && (pageIndex == itemIndex + 4 || pageIndex == itemIndex + 3))
        			|| (pageIndex == totalPage - 1 && pageIndex == itemIndex + 3)) {
				strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
			}
			
			if (pageIndex - itemIndex < 3 && pageIndex - itemIndex > -3) {
        		if (pageIndex == itemIndex) {
        			strNavBuf += '<a class="inactive" href="javascript:void(0);" disabled="disabled">' + itemText + '</a>';
        		} else {
        			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
        		}
        	}
			
			if ((pageIndex == 1 && (itemIndex == 4 || itemIndex == 5))
        			|| (pageIndex == 2 && itemIndex == 5)) {
				strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
        	}
		}
		
		if (totalPage > pageIndex + 2) {
			strNavBuf += ' ... ';
		}
		
		strNavBuf += '</span>';
		
		if(totalPage > pageIndex) {
			strNavBuf += '<span class="next">';
			//strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ PAGING_NEXT + '</a>';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ '>' + '</a>';
			strNavBuf += '</span>';
		}
		
//		strNavBuf += '</div>';
//		strNavBuf += '</div>';
		strNavBuf += '</div>';
		
		// alert("strNavBuf:" + strNavBuf);
		jQuery("#"+idOpts.navContainer).html(strNavBuf);
	} ,
	
	/*
	* show nav paging for vndirect-info as First | Previous | 1 | 2 | 3 | 4 | Next | Last 
	*/
	showVInfoItem : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({					   		
					   		goto_func: "void"
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		navContainer : "fSearchSymbol_paging"
					   }, idOptions);
		var pageIndex = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		var func = "javascript:" + funcOpts.goto_func;
		
		var strNavBuf = '<div class="bt_change clearfix">';
		
		// show previous
		if(pageIndex > 1) {
			strNavBuf += '<span class="back">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')">'+ PAGING_BACK + '</a>';
			strNavBuf += '</span>';
		}
		
		strNavBuf += '<span class=\"number\">';
		
		if (pageIndex > 1) {
			strNavBuf += ' ... ';
		}
		var index = 0;
		for(var i in pagingInfo.pagingItems) {
			var pagingItem = pagingInfo.pagingItems[i];
			var itemIndex = pagingItem.index;
			var itemText = pagingItem.text;
			
			if ((pageIndex == totalPage && (pageIndex == itemIndex + 4 || pageIndex == itemIndex + 3))
        			|| (pageIndex == totalPage - 1 && pageIndex == itemIndex + 3)) {
				strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
			}
			
			if (pageIndex - itemIndex < 3 && pageIndex - itemIndex > -3) {
        		if (pageIndex == itemIndex) {
        			strNavBuf += '<a href="javascript:void(0);"><b>' + itemText + '</b></a>';
        		} else {
        			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
        		}
        	}
			
			if ((pageIndex == 1 && (itemIndex == 4 || itemIndex == 5))
        			|| (pageIndex == 2 && itemIndex == 5)) {
				strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + itemIndex + ')">' + itemText + '</a>';
        	}
		}
		
		if (totalPage > pageIndex + 2) {
			strNavBuf += ' ... ';
		}
		
		strNavBuf += '</span>';
		
		if(totalPage > pageIndex) {
			strNavBuf += '<span class="next">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ PAGING_NEXT + '</a>';
			strNavBuf += '</span>';
		}
		
		strNavBuf += '</div>';
		
		// alert("strNavBuf:" + strNavBuf);
		jQuery("#"+idOpts.navContainer).html(strNavBuf);
	} ,
	
	/*
	* show nav paging as First | Previous | Next | Last
	*/
	showNewsItem : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({					   		
					   		goto_func: "void"
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		navContainer : "fSearchSymbol_paging"
					   }, idOptions);
		var pageIndex = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		var func = "javascript:" + funcOpts.goto_func;
		
		var strNavBuf = '<div class="bt_change_fix clearfix">';
		
		// show previous
		if(pageIndex > 1) {
			strNavBuf += '<span class="fl back" style="padding-right: 15px;">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')"><< '+ PAGING_BACK + '</a>';
			//strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')">'+ "<<" + '</a>';
			strNavBuf += '</span>';
		}
		
		if(totalPage > pageIndex) {
			strNavBuf += '<span class="fr next">';			
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ PAGING_NEXT + ' >></a>';
			//strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ ">>" + '</a>';
			strNavBuf += '</span>';
		}
		strNavBuf += '</div>';
		
		// alert("strNavBuf:" + strNavBuf);
		jQuery("#"+idOpts.navContainer).html(strNavBuf);
	},
	
	/*
	* show nav paging as First | Previous | Next | Last (align right)
	*/
	showRightNewsItem : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({					   		
					   		goto_func: "void"
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		navContainer : "fSearchSymbol_paging"
					   }, idOptions);
		var pageIndex = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		var func = "javascript:" + funcOpts.goto_func;
		
		var strNavBuf = '<div class="bt_change" align="right">';
		
		// show previous
		if(pageIndex > 1) {
			strNavBuf += '<span class="back">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')">'+ PAGING_BACK + '</a>';
			strNavBuf += '</span>';
			strNavBuf += '<img src="' + $.web_resource.getContext() + '/images/front/spacer.gif" width="15" height="1" />';
		}
		
		if(totalPage > pageIndex) {
			strNavBuf += '<span class="next">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ PAGING_NEXT + '</a>';
			strNavBuf += '</span>';
		}
		strNavBuf += '</div>';
		
		// alert("strNavBuf:" + strNavBuf);
		jQuery("#"+idOpts.navContainer).html(strNavBuf);
	},
	
	/*
	* show nav paging as Previous | (Other companies) Next
	*/
	showRelatedCompanies : function(pagingInfo, funcOptions, idOptions) {
		var funcOpts = jQuery.extend({					   		
					   		goto_func: "void"
					   	}, funcOptions);
					   				
		var idOpts = jQuery.extend({
					   		navContainer : "fSearchSymbol_paging"
					   }, idOptions);
		var pageIndex = pagingInfo.indexPage;
		var totalPage = pagingInfo.totalPage;
		
		var func = "javascript:" + funcOpts.goto_func;
		
		var strNavBuf = '<table cellpadding="0" cellspacing="0" border="0" width="100%" class="nomal"><tr>';
		// show previous
		if(pageIndex > 1 && (totalPage == pageIndex)) {
			strNavBuf += '<td colspan="2" align="left" class="company_footer_left">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')"><<</a>';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
			strNavBuf += '<td colspan="2" align="right" class="company_footer_right">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
		} else if (pageIndex > 1) {
			strNavBuf += '<td colspan="2" align="left" class="company_footer_left">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex - 1) + ')"><<</a>';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
		}
		
		if(totalPage > pageIndex && pageIndex == 1) {
			strNavBuf += '<td colspan="2" align="left" class="company_footer_left">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
			strNavBuf += '<td colspan="2" align="right" class="company_footer_right">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">'+ PAGING_OTHER_COMPANY + ' >></a>';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
		} else if (totalPage > pageIndex) {
			strNavBuf += '<td colspan="2" align="right" class="company_footer_right">';
			strNavBuf += '<div class="OtherCompany">';
			strNavBuf += '<a href="' + func + '(\'' + idOpts.navContainer + '\',' + (pageIndex + 1) + ')">>></a>';
			strNavBuf += '</div>';
			strNavBuf += '</td>';
		}
		strNavBuf += '</tr></table>';
		
		// alert("strNavBuf:" + strNavBuf);
		jQuery("#"+idOpts.navContainer).html(strNavBuf);
	}
};



//==================================================================
jQuery(function($) {
	/**
	* var formFields = {
	*		field_1 : field_value_1,
	*		field_2 : field_value_2,
	* };
	*/
	$.fn.web_formAways = function(formFields, options) {
		var opts = jQuery.extend({
							loading : "#progress_loading_img",					   		
					   		name : "__f___web_formAways",
					   		action : null,
					   		type : "post",
					   		divId : "web_formAways"					 		
					   }, options);
		//var index  = 0;
		return this.each(function() {
			//index ++;
			if(opts.action != null) {
				var formId = "me_" + (new Date()).getTime() + "_" + opts.name;
				var htmlForm = buildHTMLForm(formId, opts.type, opts.action, formFields);
				
				// alert(htmlForm);
				
				$(this).html(htmlForm);
				$("#" + formId).submit();
			}			
			return this;
		});
	};
	
	/**
	* 
	*/
	$.fn.web_formAjaxAways = function(formFields, options) {
		var opts = jQuery.extend({
							loading : "#progress_loading_img",					   		
					   		name : "__f___web_formAjaxAways",
					   		action : null,
					   		type : "post",
					   		divId : "web_formAways",
					   		callbackPostSubmit : null, //function (responseText, statusText)
					   		callbackExecuteFail : null, //function(error)
					   		dataType : "json"		 		
					   }, options);
		//var index  = 0;
		return this.each(function() {
			//index ++;
			if(opts.action != null) {
				var isAjax = true;
				var formId = "me_ajax_" + (new Date()).getTime() + "_" + opts.name;
				var htmlForm = buildHTMLForm(formId, opts.type, opts.action, formFields, isAjax);
				// alert(htmlForm);
				$(this).html(htmlForm);
				
				var ajaxOption = {
					beforeSubmit: function(formData, jqForm, options) { // pre-submit callback
						$.web_message.clear();
						
						//+++ show loading image
						if($.web_utils.isNotNull(opts.loading)) {
							$(opts.loading).web_showLoading();
						}
						//---
						
					    // here we could return false to prevent the form from being submitted; 
					    // returning anything other than false will allow the form submit to continue 
					    return true;
					},  
			        success: function (responseText, statusText) { // post-submit callback    
		        		try {
		        			//+++ hidden loading image
		        			if($.web_utils.isNotNull(opts.loading)) {
								$(opts.loading).web_hiddenLoading();
							}

		        			if($.web_message.errorAuthMsg(responseText, opts)) {
		        				if(opts.dataType == 'json') {
		        					//+++ show dialog message (error * action messages)
		        					$.web_message.showDialogErrorMsg(responseText);
		        					//---
		        					
				        			if($.web_utils.isNotNull(responseText.error) && $.web_utils.isNotNull(responseText.error.hasErrors) && responseText.error.hasErrors) {
				        				try {
				        					$.web_message.error(null, responseText.error);
				        					if($.isFunction(opts.callbackExecuteFail)) {
				        						opts.callbackExecuteFail(responseText.error);
				        					}
				        				} catch (e) {	
//				        					if(DEBUG) {
//				        						$.log("--- web_formAjaxAways - success:callbackExecuteFail-" + e);
//				        					}
				        				}			
				        			} else {
				        				try {
				        					$.web_message.actionMsg(responseText.error);
				        					
				        					if($.isFunction(opts.callbackPostSubmit)) {
				        						opts.callbackPostSubmit(responseText, statusText);
				        					}			
				        				} catch (e) {
//				        					if(DEBUG) {
//				        						$.log("--- web_formAjaxAways - success:callbackPostSubmit-" + e);
//				        					}
				        				}
				        			}
		        				
			        			} else {
			        				try {
				        				if($.isFunction(opts.callbackPostSubmit)) {
				        					opts.callbackPostSubmit(responseText, statusText);
				        				}
			        				} catch (e) {
//			        					if(DEBUG) {
//			        						$.log("success:callbackPostSubmit-" + e);
//			        					}
			        				}
			        			}
		        			} else {
		        				//TODO: nothing to process... auth error...
		        			}
		        	 	} catch (e) {
//		        	 		if(DEBUG) {
//		        	 			$.log("web_formAjaxAways --- success:" + e);
//		        	 		}
		        	 	}
		        	},
			        dataType:  opts.dataType        // 'xml', 'script', or 'json' (expected server response type)        
			 		
			        // $.ajax options can be used here too, for example: 
			        //timeout:   3000 
				};
				
				$("#" + formId).ajaxSubmit(ajaxOption);
			}			
			return this;
		});
	};
	
	/**
	* @param formId: string
	* @param type: post | get
	* @param action (htm file)
	* @param formFields = {
	*		field_1 : field_value_1,
	*		field_2 : field_value_2,
	* };
	* @return htmlForm 
	*/
	function buildHTMLForm(formId, type, action, formFields, isAjax) {
		var htmlForm = '<form id="' + formId + '" name="' + formId + '" method="' + type + '" action="' + action + '">';						
		for(var fieldName in formFields) {
			var fieldValue = formFields[fieldName];
			var type = typeOf(fieldValue);
			// alert("type:" + type);
			if(type != 'function' || type != null || type != 'undefined') {
				if (type == 'array') {
					// alert("array - fieldName:" + fieldName);
					for(var index in fieldValue) {
						htmlForm += '<input type="hidden" name="' + fieldName + '" value="' +fieldValue[index]+ '"/>';
					}
				} else {
					htmlForm += '<input type="hidden" name="' + fieldName + '" value="' +fieldValue+ '"/>';
				}
			} 
			
			if(isAjax != 'undefined' && isAjax != null && isAjax == true) {
				htmlForm += '<input type="hidden" name="_app_call_" value="ajax"/>';
			}
		}		
		htmlForm += '</form>';
		return htmlForm;
	};
	
	/**
	* typeOf return type of value
	*
	* Object  	 'object'
	* Array 	'array'
	* Function 	'function'
	* String 	'string'
	* Number 	'number'
	* Boolean 	'boolean'
	* null 		'null'
	* undefined 	'undefined'
	*/
	function typeOf(value) {
	    var s = typeof value;
	    if (s === 'object') {
	        if (value) {
	            if (value instanceof Array || value.length) {
	                s = 'array';
	            }
	        } else {
	            s = 'null';
	        }
	    }
	    return s;
	}
});

/**
* static function
* jQuery.web_formAways.nav({"field_1" : "field_value_1", "field_2" : "field_value_2"}, {action : "url..."})
* jQuery.web_formAways.ajaxNav({"field_1" : "field_value_1", "field_2" : "field_value_2"}, {action : "url..."})
*/
jQuery.web_formAways = {
	nav : function(formFields, options) {
		try {
			var opts = jQuery.extend({	
					loading : "#progress_loading_img",				   		
			   		name : "__f___web_formAways",
			   		action : null,
			   		type : "post",
			   		divId : "web_formAways"					 		
			   }, options);			   
			 jQuery("#" + opts.divId).web_formAways(formFields, opts);
		 } catch (e) {
		 	//alert("$.web_formAways:: " + e);
		 }
		},
		
	ajaxNav : function(formFields, options) {
		try {
			var opts = jQuery.extend({
					loading : "#progress_loading_img",					   		
			   		name : "__f___web_formAjaxAways",
			   		action : null,
			   		type : "post",
			   		divId : "web_formAways",
			   		callbackPostSubmit : null, //function (responseText, statusText)
			   		callbackExecuteFail : null, //function(error)
			   		dataType : "json"					 		
			   }, options);			   
			 jQuery("#" + opts.divId).web_formAjaxAways(formFields, opts);
		 } catch (e) {
		 	//alert("$.web_formAways:: " + e);
		 }
		}
};