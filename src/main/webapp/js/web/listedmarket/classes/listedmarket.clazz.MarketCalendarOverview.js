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
$.marketCalendarOption = function() {};
$.extend($.marketCalendarOption, {
	loading : "#progress_loading_img",
	funcName : {
		navFuncName : "_goToMarketCalendar" // function _goTo(id, index)
	},
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fListedMarket",
		fields : {
			symbol					: "#fListedMarket_marketCalendar_symbol",
			searchTypeOfDate		: "#fListedMarket_marketCalendar_searchTypeOfDate",
			eventType				: "#fListedMarket_marketCalendar_eventType",
			pagingIndex 			: "#fListedMarket_marketCalendar_indexPage",
			marketCalendarContent	: "#fListedMarket_marketCalendarContent"
		}
	},
	buttons : {
		searchButton			: "#fListedMarket_marketCalendar_searchButton"
	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: ListedMarketClazzMarketCalendar Class
*
*************************************************************************************/
function ListedMarketClazzMarketCalendar() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketCalendarOption, {
		clazzHandler : this
	});
}

/*************************************************************************************
* init getOption
**************************************************************************************/
ListedMarketClazzMarketCalendar.prototype.getOption = function () {
	return $.marketCalendarOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
ListedMarketClazzMarketCalendar.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};

ListedMarketClazzMarketCalendar.prototype.loadMarketCalendar = function() {
	var _this = this;
	var opts = _this.getOption();
	var formFields = {
		"symbol" 				: $(opts.form.fields.symbol).val(),
		"searchTypeOfDate"		: $(opts.form.fields.searchTypeOfDate).val(),
		"eventType"				: $(opts.form.fields.eventType).val(),
		"pagingInfo.offset" 	: objNewsHelper.NUMBER_ITEM,
        "pagingInfo.indexPage" 	: $(opts.form.fields.pagingIndex).val()
	};
    var options = {
		action : URL_MARKET_CALENDAR,
		callbackPostSubmit : function (responseText, statusText) {
    		var model = responseText.model;
    		var marketCalendarContent = $(opts.form.fields.marketCalendarContent);
    		marketCalendarContent.html("");
    		
    		var strContent = '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
    		if (model.searchResult != null && model.searchResult.length > 0) {
    			strContent += '<colgroup><col width="20%"><col width="20%"><col width="60%"></colgroup>';
    			var tdClass = "";
    			$.each(model.searchResult, function(i, item){
//    				if (i == 0) {
//    					tdClass = "col2";
//    				} else {
//    					tdClass = "col3";
//    				}
//    				tdClass = "col2";
    				strContent += '<tr>';
    				strContent += '<td class="date"><span class="date">' + $.web_utils.dateFormat2Show(item.rightsDate, 'dd/mm/yyyy') + '</span></td>';
    				strContent += '<td class="' + tdClass + '" align="center" >' + item.symbol + '</td>';
    				strContent += '<td class="' + tdClass + '" align="left">' + item.eventNote + '</td>';
    				strContent += '</tr>';
    			})
    		} else {
    			// show message not found
    			strContent += '<tr><td colpan="3" class="col2"><span class="error">&nbsp;&nbsp;' + COMMON_NOT_FOUND + '</span></td></tr>';
    		}
    		
    		strContent += '</table>';
    		marketCalendarContent.html(strContent);
    		
    		var funcOptions = {
            	goto_func : opts.funcName.navFuncName	 
    		};
	        var idOptions = {
            	navContainer : "web_navMarketCalendar"
            };
            $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
    		
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
	 
};