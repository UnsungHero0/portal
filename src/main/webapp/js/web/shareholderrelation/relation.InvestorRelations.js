var _companyEvents = new CompanyRelationNews();
//var opts = _companyEvents.getOption();

$().ready(function() {
	_companyEvents.init();
	
	var divContent = $("#holderRelationNewsContent");
	
	_companyEvents.searchEvents();
	
});


/*************************************************************************************
* @author 
*
* Define Home Object
* using the flowing query to extend CompanyEventsOption
*
* jQuery.extend(jQuery.companyEventsOption, {
* });
*
*************************************************************************************/
var objNewsHelper = new NewsHelper();

(function($) {
$.companyRelationNewsOption = function() {};
$.extend($.companyRelationNewsOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},
	funcName : {
		navFuncName : "_goTo" // function _goTo(id, index)
	},
	form : {
		name : "#fCompanyEvents",
		divs : {
			content				: "#holderRelationNewsContent"
		},
		navDivs : {
			navContainer		: "fSearchSymbol_paging"
		},
		fields : {
			pagingIndex 		: "#fCompanyEvents_pagingInfo_indexPage",
			fromDate	 		: "#fCompanyEvents_fromDateId",
			toDate		 		: "#fCompanyEvents_toDateId"
		}
	},
	buttons : {
		marketsNews				: "#fCompanyEvents_buttonMarketsNews",
		companyEvents			: "#fCompanyEvents_buttonCompanyEvents",
		search					: "#fCompanyEvents_searchButton"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: CompanyEvents Class
*
*************************************************************************************/
function CompanyRelationNews() {
	this.validator = null;
	
	jQuery.extend(jQuery.companyRelationNewsOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
CompanyRelationNews.prototype.getOption = function () {
	return $.companyRelationNewsOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
CompanyRelationNews.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
	
	_this.resetInitData();
};

/*************************************************************************************
* resetInitData functionality
**************************************************************************************/
CompanyRelationNews.prototype.resetInitData = function() {
	var _this = this;
	_this.validator = null;
};

CompanyRelationNews.prototype.searchEvents = function() {
	var opts = _companyEvents.getOption();

	var divContent = $("#holderRelationNewsContent");
	
	
	var formFields = {
		"type" 							: objNewsHelper.TYPE_NEWS + ',' + objNewsHelper.TYPE_EVENT + ',' + objNewsHelper.TYPE_PUBLIC_INFO,
		"searchIfoNews.strFromNewsDate" : '',
		"searchIfoNews.strToNewsDate" 	: '',
		"pagingInfo.offset" 			: 5,
		"pagingInfo.indexPage" 			: 1
	 };
	
	 var options = {
		 action : objNewsHelper.URL_SYMBOL_TYPE_LIST,
         callbackPostSubmit : function (responseText, statusText) {
             try {
            	 
            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {
                     $.web_message.error(null, responseText.error);
                     
                     //+++ update ipo news
            	 } else {
            	
            		//Companys Events processing
            		 var strContent = '';

            		 divContent.html("");
	                 var model = responseText.model;
	                 
	                 //clear content
	                 if (model.messageFromNewsDate == FOR_EVENT_FROMNEWSDATE_FIELD_INVALID && model.messageToNewsDate == null ) {
	                	 
	                	 strContent += '<span class="error">' + FOR_EVENT_FROMNEWSDATE_FIELD_INVALID + '</span>';	
	                 } else if (model.messageToNewsDate == FOR_EVENT_TONEWSDATE_FIELD_INVALID && model.messageFromNewsDate == null) {
	                	 
	                	 strContent += '<span class="error">' + FOR_EVENT_TONEWSDATE_FIELD_INVALID + '</span>' ;
	                 } else if (model.messageFromNewsDate == FOR_EVENT_FROMNEWSDATE_FIELD_INVALID && model.messageToNewsDate == FOR_EVENT_TONEWSDATE_FIELD_INVALID) {
	                	 
	                	 strContent += '<span class="error">' + FOR_EVENT_FROMNEWSDATE_FIELD_INVALID + '</span>' + '<BR>';
	                	 strContent += '<span class="error">' + FOR_EVENT_TONEWSDATE_FIELD_INVALID + '</span>';
	                 } else if (model != null && model.searchResult != null && model.searchResult.length > 0) {
	                	 
	                	 $.each(model.searchResult, function(i, newsinfo){
                			 strContent += '<p><span class="date">' + $.web_utils.dateFormat2Show(newsinfo.newsDate, 'dd/mm/yyyy') + '</span>';
	                		 strContent += getNewsDetailUrl(newsinfo.newsId, newsinfo.newsHeader + '(' + newsinfo.newsResource + ')', newsinfo.newsType);
	         			 });
	                 } else {
	                	 
	                	 strContent += '<span class="error">' + COMMON_NOT_FOUND + '</span>';
	                 }  
	                 
	                 divContent.html(strContent);
                     
                    /* var funcOptions = {
	                	goto_func : opts.funcName.navFuncName
	                 };		                 
	                 var idOptions = {
	                	navContainer : opts.form.navDivs.navContainer
	                 };
	                 $.web_paging.showItem(model.pagingInfo, funcOptions, idOptions);*/
            	 }
             } catch (e) {
                 alert(e);
             }
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};
