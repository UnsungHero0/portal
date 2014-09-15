jQuery(function($) {
	$.fn.web_showLoading_defaultOption = {		
		loading_img_url : $.web_resource.getContext() + "/images/ajax-loader.gif"
	};
	
	/**
	* show loading image
	*/
	$.fn.web_showLoading = function(options) {
		var opts = $.extend($.fn.web_showLoading_defaultOption, options);
		return this.each(function() {
			$(this).html("<img src='" + opts.loading_img_url + "' border='0'/>");
			return this;
		});
	};
	
	/**
	* hidden loading image
	*/
	$.fn.web_hiddenLoading = function(options) {
		return this.each(function() {			
			$(this).html("");
			return this;
		});
	};	
	
	/**
	 * reset element of form
	 */
	$.fn.reset = function () {
	  $(this).each (function() { this.reset(); });
	};
	
});

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
	$.fn.buildHTMLForm = function(formId, type, action, formFields) {
		var htmlForm = '<form id="' + formId + '" name="' + formId + '" method="' + type + '" action="' + action + '">';						
		for(var fieldName in formFields) {
			var fieldValue = formFields[fieldName];
			var type = typeOf(fieldValue);
			if(type != 'function' || type != null || type != 'undefined') {
				if (type == 'array') {
					for(var index in fieldValue) {
						htmlForm += '<input type="hidden" name="' + fieldName + '" value="' +fieldValue[index]+ '"/>';
					}
				} else {
					htmlForm += '<input type="hidden" name="' + fieldName + '" value="' +fieldValue+ '"/>';
				}
			} 
		}		
		htmlForm += '</form>';
		$(this).html(htmlForm);
		return htmlForm;
	};

/*
 * check all check boxs is checked or not
 *
 * @name     isCheckedAllCheckboxes
 * @param    filter   only toggle checkboxes matching this expression
 * @example  $("#myform").isCheckedAllCheckboxes();
 * @example  $("#myform").isCheckedAllCheckboxes(".onlyme");
 * @example  $("#myform").isCheckedAllCheckboxes(":not(.notme)");
 * @example  $("#myform").isCheckedAllCheckboxes("*");
 *
 */
jQuery.fn.isCheckedAllCheckboxes = function(filter){
	filter = filter || "*";
	var returnChecked = true;
	var hasCheckBox = false;	
	this.each( function() {
		jQuery("input:checkbox").filter(filter).each( function(){				
				returnChecked = (returnChecked && this.checked);
				hasCheckBox = true;
		});
	});
	return (hasCheckBox ? returnChecked : false);
};	

/**
 * get all values of checked checkboxs.
 *
 * @name     getCheckedValueCheckboxes
 * @param    filter   only toggle checkboxes matching this expression
 * @return Array of checked value of checkbox
 *
 * @example  $("#myform").getCheckedValueCheckboxes();
 * @example  $("#myform").getCheckedValueCheckboxes(".onlyme");
 * @example  $("#myform").getCheckedValueCheckboxes(":not(.notme)");
 * @example  $("#myform").getCheckedValueCheckboxes("*");
 *
 */
jQuery.fn.getCheckedValueCheckboxes = function(filter){
	filter = filter || "*";
	var arrValues = new Array();
	this.each( function() {
		try {
			jQuery("input:checkbox", this).filter(filter).each( function(){
				if(this.checked) {
					arrValues.push(this.value);
				}
			});
		} catch (e) {
			if(DEBUG) {
				$.log("getCheckedValueCheckboxes:" + e);
			}
		}
	});
	return arrValues;
};
	
/**
* Static method
*/
jQuery.web_utils = {
	/**
	* Get description of refData
	* @param listRefData is array of RefData object. its JSON format is:
	* "listRefData":[
	*	{
	*		"id":210
	*		,"groupCode":"PROPERTY_ACTION_TYPE"
	*		,"itemCode":"SELL"
	*		,"lang":"vn"
	*		,"description":"Bán"
	*	},
	*	{
	*		.............
	*		.............
	*	}
	*]
	*
	* @param itemCode
	* 
	* Use: $.web_utils.getRefData(listRefData, itemCode);
	*/
	getRefData : function(listRefData, itemCode) {
		try {
			if(listRefData != null && itemCode != null) {
				for(var index in listRefData) {
					var obj = listRefData[index];
					var value = obj["itemCode"];
					// alert("obj: " + obj + ", value: " + value + ", itemCode:" + itemCode );
					if(value == itemCode) {
						return obj["description"];
					}
				}
			}
		} catch (e) {
			if(DEBUG) {
				$.log("getRefData:" + e);
			}
		}
		return itemCode;
	},
	
	/**
	 * Use: $.web_utils.isNull(obj);
	 */
	isNull : function(obj) {
		try {
			return (obj == 'undefined' || obj == null);
		} catch (e) {			
		}
		return false;
	},
	
	/**
	 * Use: $.web_utils.isNotNull(obj);
	 */
	isNotNull : function(obj) {
		try {
			return (obj != 'undefined' && obj != null);
		} catch (e) {			
		}
		return false;
	},
	
	/**
	 * Use: $.web_utils.isNotEmpty(obj);
	 */
	isNotEmpty : function(obj) {
		try {
			return(obj != 'undefined' && obj != null && jQuery.trim(obj).length > 0);
		} catch (e) {			
		}
		return false;
	},
	
	/**
	*
	* @param: formId is form id or item which contains checkbox items.
	* @options: contains selectAll & selected classes with format
	*
	* @using: var opts = {
	*	selectAllClazz : "chkSelectAll",
	*	selectedClazz : "chkSelectedId"
	* };
	* var formId = "fSearchProject_tbResult";
	* $.web_utils.synchSelectAllCheckboxes(formId, opts);
	*/
	synchSelectAllCheckboxes: function(formId, options){
		try {
			var opts = jQuery.extend({
					selectAllClazz : "chkSelectAll",
					selectedClazz : "chkSelectedId"
				}, options);
				
			var strFormId = (formId.indexOf("#") === 0 ? "" : "#") + formId;						
			var checked = $(strFormId).isCheckedAllCheckboxes('.' + opts.selectedClazz);
			
			//alert("formId:" + strFormId + ", selectAllClazz:" + opts.selectAllClazz + ", selectedClazz: " + opts.selectedClazz + ", checked:" + checked);
					
			if(checked) {
				$(strFormId).checkCheckboxes('.' + opts.selectAllClazz);
			} else {
				$(strFormId).unCheckCheckboxes('.' + opts.selectAllClazz);
			}
		} catch (e) {
			if(DEBUG) {
				$.log("synchSelectAllCheckboxes:" + e);
			}
		}
	},
	
	/**
	*
	* @param: formId is form id or item which contains checkbox items.
	* @options: contains selectAll & selected classes with format
	*
	* @using: var opts = {
	*	selectAllClazz : "chkSelectAll",
	*	selectedClazz : "chkSelectedId"
	* };
	* var formId = "fSearchProject_tbResult";
	* $.web_utils.synchSelectedCheckboxes(formId, opts);
	*/
	synchSelectedCheckboxes: function(formId, options){
		try {
			var opts = jQuery.extend({
					selectAllClazz : "chkSelectAll",
					selectedClazz : "chkSelectedId"
				}, options);
			
			var strFormId = (formId.indexOf("#") === 0 ? "" : "#") + formId;
			var checked = $(strFormId).isCheckedAllCheckboxes('.' + opts.selectAllClazz);
			
			//alert("formId:" + strFormId + ", selectAllClazz:" + opts.selectAllClazz + ", selectedClazz: " + opts.selectedClazz + ", checked:" + checked);
					
			if(checked) {
				$(strFormId).checkCheckboxes('.' + opts.selectedClazz);
			} else {
				$(strFormId).unCheckCheckboxes('.' + opts.selectedClazz);
			}
		} catch (e) {
			if(DEBUG) {
				$.log("synchSelectedCheckboxes:" + e);
			}
		}
	},
	
	/**
	*
	* @param: chkValue is value of check box.
	* @options: contains selectAll & selected classes with format
	*
	* @using: var opts = {
	*	funcSynchChkSelectItemName : "_synchChkSelectItem",
	*	selectedClazz : "chkSelectedId",
	*	selectedName : "chkSelectedName_selectedId"
	* };
	* var chkValue = obj.id;
	* $.web_utils.buildSynchCheckboxeItem(chkValue, opts);
	*/
	buildSynchCheckboxeItem: function(chkValue, options){
		var opts = jQuery.extend({				
				funcSynchChkSelectItemName : "_synchChkSelectItem",
								
				selectedClazz : "chkSelectedId",
				selectedName : "chkSelectedName_selectedId"
			}, options);
		var chkFunc = 'javascript:' + opts.funcSynchChkSelectItemName + '()'; 
		return '<input name="' + opts.selectedName + '" class="' + opts.selectedClazz + '" type="checkbox" value="' + chkValue + '" onclick="' + chkFunc + '"/>';		
	},
	
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
	*
	* Use: $.web_utils.typeOf(value);
	*/
	typeOf: function(value) {
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
	} ,
	
	/**
	* show text box model
	* @param: caption
	* @param: url
	* @param: imageGroup
	* Use: $.web_utils.showTextBox(caption, url, imageGroup);
	*/
	showTextBox : function (caption, url, imageGroup) {
		try {
			tb_show(caption, url, imageGroup);
		} catch (e) {
			alert("showTextBox:" + e);
		}
	} ,
	
	/**
	* get value of a object.
	* return "" when object is null or 'undefined'
	* 
	* Use: $.web_utils.toValue(obj);
	*/
	toValue :  function (obj) {
		try {
			if(obj != null && obj != 'undefined') {
				return obj;
			} else {
				return "";
			}
		} catch (e) {
		}
		return obj;
	},
	
	/**
	 * convert file size with format Byte, KB, M, G,...
	 * return "" when object is null or 'undefined'
	 * 
	 * Use: $.web_utils.toFileSize(obj);
	 */
	toFileSize: function (obj) {
		try {
			if(obj != null && obj != 'undefined') {
				var _Byte = 1;
				var _KB = 1000;
				var _M = 1000 * _KB;
				var _G = 1000 * _M;
				
				var tmp;
				var suffix;
				if(_Byte <= obj && obj < _KB) {
					tmp = Number(obj);					
					suffix = "Byte"; 
				} else if(_KB <= obj && obj < _M) {
					tmp = Number(obj) / _KB;					
					suffix = "KB";
				} else if(_M <= obj && obj < _G) {
					tmp = Number(obj) / _M;					
					suffix = "M";
				} else {
					tmp = Number(obj) / _G;
					suffix = "G";
				}
				
				tmp = Math.round(tmp * 10) / 10;
				
				return (tmp + suffix);
			}
		} catch (e) {
			if(DEBUG) {
				$.log("toFileSize:" + e);
			}
		}
		return "";
	},
	
	/**
	 * Format Number in x,xxx,xxx.xx format
	 * 
	 * Use: $.web_utils.fomatNumberWithScaleSeprate(numberStr, scaleNum, separate);
	 */
	fomatNumberWithScaleSeprate : function(numberStr, scaleNum, separate) {
		try {
			var nf = new NumberFormat(numberStr);
			nf.setPlaces(scaleNum);
//			nf.setSeparators(true, separate);
			nf.setSeparators(true);
			return nf.toFormatted();
		} catch (e) {
			if(DEBUG) {
				$.log('fomatNumberWithScaleSeprate(numberStr, scaleNum, separate) - '  +e);
			}
		}
		return numberStr;
	},
	
	/**
	 * Format Number in x,xxx,xxx.xx format
	 * 
	 * Use: $.web_utils.fomatNumberWithScale(numberStr, scaleNum); 
	 */
	fomatNumberWithScale: function (numberStr, scaleNum) {
		return $.web_utils.fomatNumberWithScaleSeprate(numberStr, scaleNum, ',');
	},
	
	/**
	 * Format Number in x,xxx,xxx format
	 * 
	 * Use: $.web_utils.fomatLong(numberStr); 
	 */
	fomatLong: function (numberStr) {
		return $.web_utils.fomatNumberWithScaleSeprate(numberStr, 0, ',');
	},

	/**
	 * Format Number in x,xxx,xxx format
	 * 
	 * Use: $.web_utils.fomatLongNotZero(numberStr); 
	 */
	fomatLongNotZero: function (numberStr) {
		if (numberStr == '0') {
			return '-';
		} else {
			return $.web_utils.fomatNumberWithScaleSeprate(numberStr, 0, ',');
		}
	},

	/**
	 * Format Number in x,xxx,xxx.xx format
	 * 
	 * Use: $.web_utils.fomatDouble(numberStr); 
	 */
	fomatDouble: function (numberStr) {
		return $.web_utils.fomatNumberWithScaleSeprate(numberStr, 2, ',');
	}, 
	
	dateFormat2Show : function(strDate, format) {
		if (strDate != null && strDate != '') {
			var pYear = strDate.substr(0,4);
			var pMonth = strDate.substr(5,2);
			var pDay = strDate.substr(8,2);
			strDate = pMonth + '/' + pDay + '/' + pYear;
			strDate = dateFormat(strDate, format);
		}
		return strDate;
	},
	
	/**
	 * Use: $.web_utils.urlRewrite(url); 
	 */
	urlRewrite: function (url) {
		try {
			if($.web_utils.isNotNull(url) && url.indexOf("-6868-") > 0) {
				var extIdx = url.indexOf(".shtml");
				if(extIdx > 0) {
					return (url.substr(0, extIdx) + "-" + (new Date()).getTime() + url.substr(extIdx));
				}
			}
		} catch (e) {
			if(DEBUG) {
				$.log('urlRewrite(url) - '  +e);
			}
		}
		return url;
	},
	/**
	 * The function returns an array/object with your URL parameters and their values
	 * For example, consider we have the following URL:
	 * http://www.example.com/?me=myValue&name2=SomeOtherValue
	 * Calling $.web_utils.getUrlVars() function would return you the following array:
	 * {
		    "me"    : "myValue",
		    "name2" : "SomeOtherValue"
		}
	 */
	getUrlVars: function(){
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	      hash = hashes[i].split('=');
	      vars.push(hash[0]);
	      vars[hash[0]] = hash[1];
	    }
	    if (vars[0] == window.location.href) return [];
	    return vars;
	},
	
	/**
	 * Getting URL var by its name
	 * $.web_utils.getUrlVar(name2) = 'SomeOtherValue'
	 */
	getUrlVar: function(name){
	    return $.web_utils.getUrlVars()[name];
	},
	
	/**
	 * A simple string formatting utility for building strings similar to Java
	 * $.web_utils.format( "two tokens, two args ({0},{1})<br />", "arg1", "arg2")
	 */
	format: function(text){
		//check if there are two arguments in the arguments list
		if ( arguments.length <= 1 )
		{
		//if there are not 2 or more arguments there's nothing to replace
		//just return the original text
		return text;
		}
		//decrement to move to the second argument in the array
		var tokenCount = arguments.length - 2;
		for( var token = 0; token <= tokenCount; token++ )
		{
		//iterate through the tokens and replace their place holders from the original text in order
		text = text.replace( new RegExp( "\\{" + token + "\\}", "gi" ),
		                                        arguments[ token + 1 ] );
		}
		return text;
	}
};

/*
 * Using to show/hiden dialog
 */
jQuery.web_dialog = {
	/**
	 * Use: $.web_dialog.showSysDialogAuthError(formFields, options); 
	 */
	showSysDialogAuthError: function(formFields, options) {
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
							$.web_formAways.nav(formFields, options);
						} catch (e){
							if(DEBUG) {
								$.log('showSysDialogAuthError::OK- '  +e);
							}
						}
					}
				}
			});
			$("#sysDialogAuthError").dialog('open'); 
		} catch (e) {		
			if(DEBUG) {
				$.log('showSysDialogAuthError() - '  +e);
			}
		}
	},
	
	/**
	 * Use: $.web_dialog.showSysDialogIdgAuth(idgErrorAuth); 
	 */
	showSysDialogIdgAuth: function(idgErrorAuth) {
		try {
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
							if(DEBUG) {
			    				$.log("showSysDialogIdgAuth: authUrl=" + idgErrorAuth.authUrl);
			    			}
						}
					}
				});
		} catch (e) {		
			if(DEBUG) {
				$.log('showSysDialogIdgAuth() - '  +e);
			}
		}
	},
	
	/**
	 * Use: $.web_dialog.showSysDialogWindow(formFields, options); 
	 * 
	 * @param: formFields is {"field_1" : "field_value_1", "field_2" : "field_value_2"}
	 * @param: options = {action: url, title: ""}
	 */
	showSysDialogWindow: function(formFields, options) {
		try {
			if($.web_utils.isNotNull(options) && $.web_utils.isNotNull(options.action)) {
				var strTitle = ($.web_utils.isNotNull(options.title) ? options.title : "" );
				$.web_formAways.ajaxNav(formFields, 
					{action :  options.action
					, dataType : "text/html" 
					, callbackPostSubmit : function (responseText, statusText) {
							try {
								$("#sysDialogWindow").html(responseText);
								$("#sysDialogWindow").dialog({
									title: strTitle,
									autoOpen:	true,
									closeOnEsc:	true,
									bgiframe: true,
									modal: true,
									height: 450,
									width: 650									
								});
								$("#sysDialogWindow").dialog('open');
							} catch (e) {
								if(DEBUG) {
				    				$.log("sysDialogWindow: authUrl=" + idgErrorAuth.authUrl);
				    			}
							}
						}
					});
			}
		} catch (e) {		
			if(DEBUG) {
				$.log('showSysDialogWindow() - '  +e);
			}
		}
	},
	
	/**
	 * Use: $.web_dialog.hidenSysDialogWindow();
	 */
	hidenSysDialogWindow: function() {
		try {
			$("#sysDialogWindow").dialog('close');
		} catch (e) {		
			if(DEBUG) {
				$.log('hidenSysDialogWindow() - '  +e);
			}
		}
	}

};

/**
* Define Web Resource
*/
jQuery.web_resource = {
	icons : {
		bitmap : "images/icons/typebitmap.gif",
		doc : "images/icons/typedoc.gif",
		excel : "images/icons/typeexcel.gif",
		flash : "images/icons/typeflash.gif",
		html : "images/icons/typehtml.gif",
		image : "images/icons/typeimage.gif",
		message : "images/icons/typemessage.gif",
		movie : "images/icons/typemovie.gif",
		pdf : "images/icons/typepdf.gif",
		voice : "images/icons/typevoice.gif",
		web : "images/icons/typeweb.gif"
	},
	uris : {
		// for up/download
		upload : "online/upload/resource/UploadResource.shtml",
		multiUpload : "online/upload/resource/UploadMultiResource.shtml",
		download : "online/download/resource/Download.shtml?uri=",
		downloadThumbnail : "online/download/resource/ViewThumbnail.shtml?thumbnail=true&uri=",
		
		// for chart
		taChart : "viewchart.tachart?s=" 
		
	},
	
	/**
	* get context of web
	* Use: $.web_resource.getContext()
	*/
	getContext : function () {
		try {
			if(WEB_CONTEXT !== null && WEB_CONTEXT != 'undefined') {
				return WEB_CONTEXT;
			}
		} catch (e) {
			if(DEBUG) {
				$.log("getContext:" + e);
			}
		}
		return "/";
	},
	
	/**
	* get context of web
	* Use: $.web_resource.getContextResource()
	*/
	getContextResource : function () {
		try {
			if(WEB_CONTEXT_RESOURCE !== null && WEB_CONTEXT_RESOURCE != 'undefined') {
				return WEB_CONTEXT_RESOURCE;
			}
		} catch (e) {
			if(DEBUG) {
				$.log("getContextResource:" + e);
			}
		}
		return "/";
	},
	
	/**
	* get context of web
	* Use: $.web_resource.getContextChart()
	*/
	getContextChart : function () {
		try {
			if(WEB_CONTEXT_CHART !== null && WEB_CONTEXT_CHART != 'undefined') {
				return WEB_CONTEXT_CHART;
			}
		} catch (e) {
			if(DEBUG) {
				$.log("getContextResource:" + e);
			}
		}
		return "/";
	},
	
	/**
	* get URL of web's resource [context] + uri
	* Use: $.web_resource.getURL(uri)
	*/
	getURL : function (uri) {
		try {
			return jQuery.web_resource.getContext() + uri;
		} catch (e) {
			if(DEBUG) {
				$.log("getURL:" + e);
			}
		}
		return uri;
	},
	
	/**
	* get URL of web's resource [context] + uri
	* Use: $.web_resource.getURLResource(uri)
	*/
	getURLResource : function (uri) {
		try {
			return jQuery.web_resource.getContextResource() + uri;
		} catch (e) {
			if(DEBUG) {
				$.log("getURLResource:" + e);
			}
		}
		return uri;
	},
	
	/**
	* get URL of web's resource [context] + uri
	* Use: $.web_resource.getURLChart(uri)
	*/
	getURLChart : function (uri) {
		try {
			return jQuery.web_resource.getContextChart() + uri;
		} catch (e) {
			if(DEBUG) {
				$.log("getURLResource:" + e);
			}
		}
		return uri;
	},
	
	/**
	* get URL of download resource
	* Use: $.web_resource.urlUpload()
	*/
	urlUpload : function () {
		try {
			return jQuery.web_resource.getURLResource(jQuery.web_resource.uris.upload);
		} catch (e) {
			if(DEBUG) {
				$.log("urlUpload:" + e);
			}
		}
		return "";
	},	
	
	/**
	* get URL of download resource
	* Use: $.web_resource.urlMultiUpload()
	*/
	urlMultiUpload : function () {
		try {
			return jQuery.web_resource.getURLResource(jQuery.web_resource.uris.multiUpload);
		} catch (e) {
			if(DEBUG) {
				$.log("urlMultiUpload:" + e);
			}
		}
		return "";
	},
	
	/**
	* get URL of download resource
	* Use: $.web_resource.urlDownload()
	*/
	urlDownload : function () {
		try {
			return jQuery.web_resource.getURLResource(jQuery.web_resource.uris.download);
		} catch (e) {
			if(DEBUG) {
				$.log("urlDownload:" + e);
			}
		}
		return "";
	},	
	
	/**
	* get URL of download resource
	* Use: $.web_resource.urlDownloadThumbnail()
	*/
	urlDownloadThumbnail : function () {
		try {
			return jQuery.web_resource.getURLResource(jQuery.web_resource.uris.downloadThumbnail);
		} catch (e) {
			if(DEBUG) {
				$.log("urlDownloadThumbnail:" + e);
			}
		}
		return "";
	},
	
	/**
	* get URL of download resource
	* Use: $.web_resource.urlViewChart()
	*/
	urlViewChart : function () {
		try {
			return jQuery.web_resource.getURLChart(jQuery.web_resource.uris.taChart);
		} catch (e) {
			if(DEBUG) {
				$.log("urlDownloadThumbnail:" + e);
			}
		}
		return "";
	},
	
	/**
	* format ISO date (2002-11-22T00:00:00) to 2002-11-22 00:00:00
	*/
	formatISODate : function(strDate) {
		return strDate;
	}	
};
