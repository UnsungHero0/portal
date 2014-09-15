var objNewsHelper = new NewsHelper();

(function($) {
$.newsOfVNDIRECTOption = function() {};
$.extend($.newsOfVNDIRECTOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	funcName : {
		navFuncName : "_goToNewsOfVNDIRECT" // function _goTo(index)
	},
	form : {
		name : "#fNewsOfVNDIRECT",
		divs : {			
			vndsnews			: "divVNDSNews.id"
		},
	
		navDivs : {
			navVndsnews			: "web_navVNDSNews"
		},
		
		fields : {
			pagingIndex 	: "#fNewsOfVNDIRECT_pagingInfo_indexPage"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: NewsClazzNewsOfVNDIRECT Class
*
*************************************************************************************/
function NewsClazzNewsOfVNDIRECT() {
	this.validator = null;
	
	jQuery.extend(jQuery.newsOfVNDIRECTOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
NewsClazzNewsOfVNDIRECT.prototype.getOption = function () {
	return $.newsOfVNDIRECTOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
NewsClazzNewsOfVNDIRECT.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* load VNDS News functionality
**************************************************************************************/
NewsClazzNewsOfVNDIRECT.prototype.loadNewsOfVNDIRECT = function() {
	var opts = _newsClazzNewsOfVNDIRECT.getOption();
	var vndsnewsDiv = $(opts.form.divs.vndsnews);
	var divShowVNDSNews = document.getElementById(vndsnewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_VNDS_NEWS,
             "pagingInfo.offset" : objNewsHelper.NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_VNDS_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
	            	 } else {
	            		 var content = '';
	            		 content += '<ul id="uldisplaynews">';
	            		 divShowVNDSNews.innerHTML = '';
		                 var model = responseText.model;
		                 var att;
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, obj){
		         				content += '<li><p>';
		        				content += '<span class="sn_title">' + obj.urlDetail +  '</span>';
		        				content += '<span>&nbsp;&nbsp;&nbsp;(' + obj.newsDateStr +  ')</span>';
		        				content += '</p>';
		        				content += '<cite>' + $.web_utils.toValue(obj.newsAbstract) + '</cite>';
		        				content += '</li>';
		         			})
                         } 
		                 divShowVNDSNews.innerHTML = content;
		                 
		                 var funcOptions = {
 		                	goto_func : opts.funcName.navFuncName	 
 		                 };		                 
 		                 var idOptions = {
 		                	navContainer : opts.form.navDivs.navVndsnews
 		                 };
 		                 $.web_paging.showVInfoItem(model.pagingInfo, funcOptions, idOptions);
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};