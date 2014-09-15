var objNewsHelper = new NewsHelper();

(function($) {
$.shareholderNewsOption = function() {};
$.extend($.shareholderNewsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	funcName : {
		navFuncName : "_goToShareholderNews" // function _goTo(index)
	},
	form : {
		name : "#fShareholderNews",
		divs : {			
			shareholdernews			: "divShareholderNews.id"
		},
	
		navDivs : {
			navShareholderNews		: "web_navShareholderNews"
		},
		
		fields : {
			pagingIndex 			: "#fShareholderNews_pagingInfo_indexPage"
		}
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: NewsClazzShareholderNews Class
*
*************************************************************************************/
function NewsClazzShareholderNews() {
	this.validator = null;
	
	jQuery.extend(jQuery.shareholderNewsOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
NewsClazzShareholderNews.prototype.getOption = function () {
	return $.shareholderNewsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
NewsClazzShareholderNews.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
* load VNDS News functionality
**************************************************************************************/
NewsClazzShareholderNews.prototype.loadShareholderNews = function() {
	var opts = _newsClazzShareholderNews.getOption();
	var shareholdernewsDiv = $(opts.form.divs.shareholdernews);
	var divShowShareholderNews = document.getElementById(shareholdernewsDiv.selector);
	var formFields = {
             "type" : objNewsHelper.TYPE_SHAREHOLDER_NEWS,
             "pagingInfo.offset" : objNewsHelper.NUMBER_ITEM,
             "pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val()
	 };
	 var options = {
             action : objNewsHelper.URL_VND_SHAREHOLDER_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
	            	 } else {
	            		 var content = '';
	            		 content += '<ul id="uldisplaynews">';
	            		 divShowShareholderNews.innerHTML = '';
	            		 var att;
		                 var model = responseText.model;
		                 if(model != null && model.searchResult != null && model.searchResult != null) {
		                	 $.each(model.searchResult, function(i, obj){
		        				content += '<li><p>';
		        				content += '<span class="sn_title">' + getNewsDetailUrl(obj.newsId, obj.newsHeader, obj.newsType, att , URL_NEWS_DETAILS_2) +  '</span>';
		        				content += '<span>&nbsp;&nbsp;&nbsp;(' + obj.newsDateStr +  ')</span>';
		        				content += '</p>';
		        				content += '<cite>' + $.web_utils.toValue(obj.newsAbstract) + '</cite>';
		        				content += '</li>';
		         			})
                         } 
		                 divShowShareholderNews.innerHTML = content;
		                 
		                 var funcOptions = {
 		                	goto_func : opts.funcName.navFuncName	 
 		                 };		                 
 		                 var idOptions = {
 		                	navContainer : opts.form.navDivs.navShareholderNews
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