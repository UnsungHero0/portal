/*************************************************************************************
 * @author 
 *
 * Define Home Object
 * using the flowing query to extend homeOption
 *
 * jQuery.extend(jQuery.HomeOption, {
 * });
 *
 *************************************************************************************/
var objNewsHelper = new NewsHelper();

(function($) {
	$.newsinfoOption = function() {
	};
	$.extend($.newsinfoOption, {
		loading : "#progress_loading_img",
		func : {
			callbackExecuted : null, //function (responseText)
			callbackExecuteFail : null
		//function()
		},
		funcName : {
			navFuncName : "_goTo" // function _goTo(index)
	},
	form : {
		name : "#fSnapshot_LatestHeadlines",
		divs : {
			lastestHeadlines : "divLastestHeadlines",
			publicinfo : "divPublicInfo",
			companysevents : "divCompanysEvents"
		},

		navDivs : {
			navLastestHeadlines : "web_navLastestHeadlines",
			navPublicinfo : "web_navPublicinfo",
			navCompanysevents : "web_navCompanysevents"
		},

		fields : {
			pagingIndex : "#LatestHeadlines_pagingInfo_indexPage"
		}
	}
	});
})(jQuery);

/*************************************************************************************
 * @author 
 *
 * @public: LatesHeadlinesForSnapshot Class
 *
 *************************************************************************************/
function LatesHeadlinesForSnapshot() {
	this.validator = null;

	jQuery.extend(jQuery.newsinfoOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
 * init getOption
 **************************************************************************************/
LatesHeadlinesForSnapshot.prototype.getOption = function() {
	return $.newsinfoOption;
};

/*************************************************************************************
 * init functionality
 **************************************************************************************/
LatesHeadlinesForSnapshot.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

/*************************************************************************************
 * load Lastest Headlines functionality
 **************************************************************************************/
LatesHeadlinesForSnapshot.prototype.loadLastestHeadlines = function() {
	var opts = _latesHeadlinesClazzForSnapshot.getOption();
	var lastestHeadlinesDiv = $(opts.form.divs.lastestHeadlines);
	var divShowLastestHeadlines = document
			.getElementById(lastestHeadlinesDiv.selector);
	var formFields = {
		"pageUrl" : "thong-tin-co-phieu",
		"type" : objNewsHelper.TYPE_NEWS + ',' + objNewsHelper.TYPE_MARKET_NEWS
				+ ',' + objNewsHelper.TYPE_EXPERT_OPINION + ','
				+ objNewsHelper.TYPE_PUBLIC_INFO + ','
				+ objNewsHelper.TYPE_EVENT,
		"showin" : objNewsHelper.SHOWIN_SNAPSHOT,
		"pagingInfo.offset" : objNewsHelper.SNAPSHOT_NUMBER_ITEM,
		"pagingInfo.indexPage" : $('#LatestHeadlines_pagingInfo_indexPage')
				.val(),
		"currentSymbol" : $("#symbolId").val().toLowerCase()
	};
	var options = {
		action : objNewsHelper.URL_SYMBOL_TYPE_LIST,
		callbackPostSubmit : function(responseText, statusText) {

			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
					//+++ update ipo news
				} else {
					//News processing
					var strContent = '';

					divShowLastestHeadlines.innerHTML = '';
					var model = responseText.model;
					//clear News content
					if (model != null && model.searchResult != null
							&& model.searchResult != null) {
						strContent = '<ul class="n_list_other list_detail_tt_nd">';
						strContent += '<div class="n_list_other_detail">';
						$.each(model.searchResult, function(i, newsinfo) {
							strContent += '<li class="n_other_news_list">';
							strContent += '<a href="'
									+ newsinfo.urlDetail
									+ '">'
									+ newsinfo.newsHeader
									+ ' ('
									+ newsinfo.newsResource
									+ ')</a>'
									+ '<span class="newsDate"> - '
									+ $.web_utils.dateFormat2Show(
											newsinfo.newsDate, 'dd/mm/yyyy')
									+ '</span>';
							strContent += '</li>';
						});
						strContent += '</div></ul>';
					}
					divShowLastestHeadlines.innerHTML = strContent;

					var funcOptions = {
						goto_func : opts.funcName.navFuncName
					};
					var idOptions = {
						navContainer : opts.form.navDivs.navLastestHeadlines
					};
					// $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
					$.web_paging.showItem(model.pagingInfo, funcOptions,
							idOptions);
				}
			} catch (e) {
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
};

/*************************************************************************************
 * load Public Info functionality
 **************************************************************************************/
LatesHeadlinesForSnapshot.prototype.loadPublicInfo = function() {
	var opts = _latesHeadlinesClazzForSnapshot.getOption();
	var publicinfoDiv = $(opts.form.divs.publicinfo);
	var divShowPublicInfo = document.getElementById(publicinfoDiv.selector);
	var formFields = {
		"pageUrl" : "thong-tin-co-phieu",
		"type" : objNewsHelper.TYPE_NEWS + ',' + objNewsHelper.TYPE_MARKET_NEWS
				+ ',' + objNewsHelper.TYPE_EXPERT_OPINION,
		"showin" : objNewsHelper.SHOWIN_SNAPSHOT,
		"pagingInfo.offset" : objNewsHelper.SNAPSHOT_NUMBER_ITEM,
		"pagingInfo.indexPage" : $('#LatestHeadlines_pagingInfo_indexPage')
				.val(),
		"currentSymbol" : $("#symbolId").val().toLowerCase()
	};
	var options = {
		action : objNewsHelper.URL_SYMBOL_TYPE_LIST,
		callbackPostSubmit : function(responseText, statusText) {

			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
					//+++ update ipo news
				} else {
					//News processing
					var strContent = '';

					divShowPublicInfo.innerHTML = '';
					var model = responseText.model;
					//clear News content
					if (model != null && model.searchResult != null
							&& model.searchResult != null) {
						strContent = '<ul class="n_list_other list_detail_tt_nd">';
						strContent += '<div class="n_list_other_detail">';
						$.each(model.searchResult, function(i, newsinfo) {
							strContent += '<li class="n_other_news_list">';
							strContent += '<a href="'
									+ newsinfo.urlDetail
									+ '">'
									+ newsinfo.newsHeader
									+ ' ('
									+ newsinfo.newsResource
									+ ')</a>'
									+ '<span class="newsDate"> - '
									+ $.web_utils.dateFormat2Show(
											newsinfo.newsDate, 'dd/mm/yyyy')
									+ '</span>';
							strContent += '</li>';
						});
						strContent += '</div></ul>';
					}
					divShowPublicInfo.innerHTML = strContent;

					var funcOptions = {
						goto_func : opts.funcName.navFuncName
					};
					var idOptions = {
						navContainer : opts.form.navDivs.navPublicinfo
					};
					$.web_paging.showItem(model.pagingInfo, funcOptions,
							idOptions);
				}
			} catch (e) {
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
};

/*************************************************************************************
 * load IPO News functionality
 **************************************************************************************/
LatesHeadlinesForSnapshot.prototype.loadCompanysEvents = function() {
	var opts = _latesHeadlinesClazzForSnapshot.getOption();
	var companyseventsDiv = $(opts.form.divs.companysevents);
	var divShowCompanysEvents = document
			.getElementById(companyseventsDiv.selector);
	var formFields = {
		"pageUrl": "thong-tin-co-phieu",
		"type" : objNewsHelper.TYPE_PUBLIC_INFO + ','
				+ objNewsHelper.TYPE_EVENT,
		"showin" : objNewsHelper.SHOWIN_SNAPSHOT,
		"pagingInfo.offset" : objNewsHelper.SNAPSHOT_NUMBER_ITEM,
		"pagingInfo.indexPage" : $(opts.form.fields.pagingIndex).val(),
		"currentSymbol" : $("#symbolId").val().toLowerCase()
	};
	var options = {
		action : $.web_resource.getContext()
				+ "/ajax/news/News_GetCompanyEventNews.shtml",
		callbackPostSubmit : function(responseText, statusText) {
			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
					//+++ update ipo news
				} else {
					//Companys Events processing
					var strContent = '';

					divShowCompanysEvents.innerHTML = '';
					var model = responseText.model;
					//clear content
					if (model != null && model.searchResult != null
							&& model.searchResult != null) {
						strContent = '<ul class="n_list_other list_detail_tt_nd">';
						strContent += '<div class="n_list_other_detail">';
						$.each(model.searchResult, function(i, newsinfo) {
							strContent += '<li class="n_other_news_list">';
							strContent += '<a href="'
									+ newsinfo.urlDetail
									+ '">'
									+ newsinfo.newsHeader
									+ ' ('
									+ newsinfo.newsResource
									+ ')</a>'
									+ '<span class="newsDate"> - '
									+ $.web_utils.dateFormat2Show(
											newsinfo.newsDate, 'dd/mm/yyyy')
									+ '</span>';
							strContent += '</li>';
						});
						strContent += '</div></ul>';
					}
					divShowCompanysEvents.innerHTML = strContent;

					var funcOptions = {
						goto_func : opts.funcName.navFuncName
					};
					var idOptions = {
						navContainer : opts.form.navDivs.navCompanysevents
					};

					$.web_paging.showItem(model.pagingInfo, funcOptions,
							idOptions);
				}
			} catch (e) {
				alert(e);
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
}
