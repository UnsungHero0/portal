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
var objMarketOverviewHelper = new MarketOverviewHelper();
var _marketOverviewClazzHome = new MarketOverviewClazzHome();
	
(function($) {
$.marketOverviewOption = function() {};
$.extend($.marketOverviewOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fListedMarket",
		divs : {
			hose        			: "divHose.id",
			vn30        			: "divVN30.id",
			hnx30        			: "divHNX30.id",
			hnx						: "divHnx.id",
			upcom					: "divUpcom.id",
			worldindex              : "divWorldIndex.id"
		}
	}
});
})(jQuery);
	
/*************************************************************************************
* @author 
*
* @public: MarketOverviewClazzHome Class
*
*************************************************************************************/
function MarketOverviewClazzHome() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketOverviewOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
MarketOverviewClazzHome.prototype.getOption = function () {
	return $.marketOverviewOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
MarketOverviewClazzHome.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};
/*************************************************************************************
* load Market Overview News functionality
**************************************************************************************/
MarketOverviewClazzHome.prototype.loadMarketOverviewNews = function() {
	var formFields = {
		"showin" : objMarketOverviewHelper.SHOWIN_HOME
	};
	var options = {
		action : objMarketOverviewHelper.URL,
		callbackPostSubmit : function(responseText, statusText) {
			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					$.web_message.error(null, responseText.error);
				} else {
					var model = responseText.model;
					// HOSE
					if (model != null && model.hostcMarket != null) {
						if (model.hostcChagIndex > 0) {
							$('.box_bangCK .line #hose a').addClass('textup');
							$('.box_bangCK .line #hose #hoseIcon').addClass('icon-up');
							$('.box_bangCK .line2 #hose a').addClass('textup');
							$('.box_bangCK .line2 #hose #currentIndex').html(model.hostcCurrentIndex);
							$('.box_bangCK .line2 #hose #change').html("+" + $.web_utils.fomatNumberWithScale(model.hostcChagIndex, 2) + '(+' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)');
						} else if(model.hostcChagIndex == 0){
							$('.box_bangCK .line #hose a').addClass('textnochange');
							$('.box_bangCK .line #hose #hoseIcon').addClass('icon-nochange');
							$('.box_bangCK .line2 #hose a').addClass('textnochange');
							$('.box_bangCK .line2 #hose #currentIndex').html(model.hostcCurrentIndex);
							$('.box_bangCK .line2 #hose #change').html($.web_utils.fomatNumberWithScale(model.hostcChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)');
						} else {
							$('.box_bangCK .line #hose a').addClass('textdow');
							$('.box_bangCK .line #hose #hoseIcon').addClass('icon-dow');
							$('.box_bangCK .line2 #hose a').addClass('textdow');
							$('.box_bangCK .line2 #hose #currentIndex').html(model.hostcCurrentIndex);
							$('.box_bangCK .line2 #hose #change').html($.web_utils.fomatNumberWithScale(model.hostcChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)');
						}
					}
					// VN30
					if (model != null && model.vn30Market != null) {
						if (model.vn30ChagIndex > 0) {
							$('.box_bangCK .line #vn30 a').addClass('textup');
							$('.box_bangCK .line #vn30 #vn30Icon').addClass('icon-up');
							$('.box_bangCK .line2 #vn30 a').addClass('textup');
							$('.box_bangCK .line2 #vn30 #currentIndex').html(model.vn30CurrentIndex);
							$('.box_bangCK .line2 #vn30 #change').html("+" + $.web_utils.fomatNumberWithScale(model.vn30ChagIndex, 2) + '(+' + $.web_utils.fomatNumberWithScale(model.vn30Market.pctIndex, 2) + '%)');
						} else if(model.vn30ChagIndex == 0){
							$('.box_bangCK .line #vn30 a').addClass('textnochange');
							$('.box_bangCK .line #vn30 #vn30Icon').addClass('icon-nochange');
							$('.box_bangCK .line2 #vn30 a').addClass('textnochange');
							$('.box_bangCK .line2 #vn30 #currentIndex').html(model.vn30CurrentIndex);
							$('.box_bangCK .line2 #vn30 #change').html($.web_utils.fomatNumberWithScale(model.vn30ChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.vn30Market.pctIndex, 2) + '%)');
						} else {
							$('.box_bangCK .line #vn30 a').addClass('textdow');
							$('.box_bangCK .line #vn30 #vn30Icon').addClass('icon-dow');
							$('.box_bangCK .line2 #vn30 a').addClass('textdow');
							$('.box_bangCK .line2 #vn30 #currentIndex').html(model.vn30CurrentIndex);
							$('.box_bangCK .line2 #vn30 #change').html($.web_utils.fomatNumberWithScale(model.vn30ChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.vn30Market.pctIndex, 2) + '%)');
						}
					}
					// HNX30
					if (model != null && model.hnx30Market != null) {
						if (model.hnx30ChagIndex > 0) {
							$('.box_bangCK .line #hnx30 a').addClass('textup');
							$('.box_bangCK .line #hnx30 #hnx30Icon').addClass('icon-up');
							$('.box_bangCK .line2 #hnx30 a').addClass('textup');
							$('.box_bangCK .line2 #hnx30 #currentIndex').html(model.hnx30CurrentIndex);
							$('.box_bangCK .line2 #hnx30 #change').html("+" + 
								$.web_utils.fomatNumberWithScale(model.hnx30ChagIndex, 2) + '(+' + $.web_utils.fomatNumberWithScale(model.hnx30Market.pctIndex, 2) + '%)');
						} else if(model.hnx30ChagIndex == 0){
							$('.box_bangCK .line #hnx30 a').addClass('textnochange');
							$('.box_bangCK .line #hnx30 #hnx30Icon').addClass('icon-nochange');
							$('.box_bangCK .line2 #hnx30 a').addClass('textnochange');
							$('.box_bangCK .line2 #hnx30 #currentIndex').html(model.hnx30CurrentIndex);
							$('.box_bangCK .line2 #hnx30 #change').html(
								$.web_utils.fomatNumberWithScale(model.hnx30ChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hnx30Market.pctIndex, 2) + '%)');
						} else {
							$('.box_bangCK .line #hnx30 a').addClass('textdow');
							$('.box_bangCK .line #hnx30 #hnx30Icon').addClass('icon-dow');
							$('.box_bangCK .line2 #hnx30 a').addClass('textdow');
							$('.box_bangCK .line2 #hnx30 #currentIndex').html(model.hnx30CurrentIndex);
							$('.box_bangCK .line2 #hnx30 #change').html(
								$.web_utils.fomatNumberWithScale(model.hnx30ChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hnx30Market.pctIndex, 2) + '%)');
						}
					}
					// HNX
					if (model != null && model.hastcMarket != null) {
						if (model.hastcChagIndex > 0) {
							$('.box_bangCK .line #hnx a').addClass('textup');
							$('.box_bangCK .line #hnx #hnxIcon').addClass('icon-up');
							$('.box_bangCK .line2 #hnx a').addClass('textup');
							$('.box_bangCK .line2 #hnx #currentIndex').html(model.hastcCurrentIndex);
							$('.box_bangCK .line2 #hnx #change').html("+" + $.web_utils.fomatNumberWithScale(model.hastcChagIndex, 2) + '(+' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)');
						} else if(model.hastcChagIndex == 0){
							$('.box_bangCK .line #hnx a').addClass('textnochange');
							$('.box_bangCK .line #hnx #hnxIcon').addClass('icon-nochange');
							$('.box_bangCK .line2 #hnx a').addClass('textnochange');
							$('.box_bangCK .line2 #hnx #currentIndex').html(model.hastcCurrentIndex);
							$('.box_bangCK .line2 #hnx #change').html($.web_utils.fomatNumberWithScale(model.hastcChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)');
						} else {
							$('.box_bangCK .line #hnx a').addClass('textdow');
							$('.box_bangCK .line #hnx #hnxIcon').addClass('icon-dow');
							$('.box_bangCK .line2 #hnx a').addClass('textdow');
							$('.box_bangCK .line2 #hnx #currentIndex').html(model.hastcCurrentIndex);
							$('.box_bangCK .line2 #hnx #change').html($.web_utils.fomatNumberWithScale(model.hastcChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)');
						}
					}
					// Upcom		                 
					if (model != null && model.upComMarket != null) {
						if (model.upComChagIndex > 0) {
							$('.box_bangCK .line #upcom a').addClass('textup');
							$('.box_bangCK .line #upcom #upcomIcon').addClass('icon-up');
							$('.box_bangCK .line2 #upcom a').addClass('textup');
							$('.box_bangCK .line2 #upcom #currentIndex').html(model.upComCurrentIndex);
							$('.box_bangCK .line2 #upcom #change').html("+" + $.web_utils.fomatNumberWithScale(model.upComChagIndex, 2) + '(+' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)');
						} else if (model.upComChagIndex == 0) {
							$('.box_bangCK .line #upcom a').addClass('textnochange');
							$('.box_bangCK .line #upcom #upcomIcon').addClass('icon-nochange');
							$('.box_bangCK .line2 #upcom a').addClass('textnochange');
							$('.box_bangCK .line2 #upcom #currentIndex').html(model.upComCurrentIndex);
							$('.box_bangCK .line2 #upcom #change').html($.web_utils.fomatNumberWithScale(model.upComChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)');
						} else {
							$('.box_bangCK .line #upcom a').addClass('textdow');
							$('.box_bangCK .line #upcom #upcomIcon').addClass('icon-dow');
							$('.box_bangCK .line2 #upcom a').addClass('textdow');
							$('.box_bangCK .line2 #upcom #currentIndex').html(model.upComCurrentIndex);
							$('.box_bangCK .line2 #upcom #change').html($.web_utils.fomatNumberWithScale(model.upComChagIndex, 2) + '(' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)');
						}
					}
				}
			} catch (e) {
			}
		}
	};
	$.web_formAways.ajaxNav(formFields, options);
};


/*************************************************************************************
* load World Market Overview News functionality
**************************************************************************************/
MarketOverviewClazzHome.prototype.loadWorldMarketOverviewNews = function() {
	var opts = _marketOverviewClazzHome.getOption();
	var worldIndexDiv = $(opts.form.divs.worldindex);
	var divWorldIndex = document.getElementById(worldIndexDiv.selector);
	var formFields = {"showin" : objMarketOverviewHelper.SHOWIN_HOME};
	 var options = {
             action : objMarketOverviewHelper.URL_WORLD_MARKET,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market overview news
	            	 } else {
	            		 //Hose processing
	            		 var strContent = '<table cellpadding="0" cellspacing="0" border="0" width="100%" class="small_table">';
	            		 strContent +='<tr><td>&nbsp;</td>';
	            		 strContent +='<td><b><span>' + WORLD_INDEX_COL1 + '</span></b></td>';
	            	     strContent +='<td><b><span>' + WORLD_INDEX_COL2 + '</span></b></td>';
	            		 strContent +='<td><b class="fr"><span>' + WORLD_INDEX_COL3 + '</span></b></td>';
                     	 strContent +='</tr>';
                        
                     	divWorldIndex.innerHTML = '';
		                 var model = responseText.model;
		                 var market;
		                 //clear Dow Jones content		                 
		                 if(model != null && model.listMarketInfo != null) {	 
		                	 $.each(model.listMarketInfo, function(i, market) {
	                			 strContent += '<tr><td class="col1">'+ market.floorCode + '</td>';		                		 
		                		 if (market.chgIndex > 0) {
			                		 strContent += '<td class="col1"><span class="color2">' + $.web_utils.fomatNumberWithScale(market.currentIndex, 2) + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/up.gif" hspace="5" /><span class="color2">' + $.web_utils.fomatNumberWithScale(market.chgIndex, 2) + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color2">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 } else if (market.chgIndex < 0) {
			                		 strContent += '<td class="col1"><span class="color1">' + $.web_utils.fomatNumberWithScale(market.currentIndex, 2) + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/down.gif" hspace="5" /><span class="color1">' + $.web_utils.fomatNumberWithScale(market.chgIndex, 2) + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color1">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 } else {
			                		 strContent += '<td class="col1"><span class="color3">' + $.web_utils.fomatNumberWithScale(market.currentIndex, 2) + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/bang.gif" hspace="5" /><span class="color3">' + $.web_utils.fomatNumberWithScale(market.chgIndex, 2) + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color3">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 }
		                		 strContent += '</tr>';
		                	 });
		                	 
		                 }
		                 divWorldIndex.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 //alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};

// Moved from newsinfo.clazz.NewsHelper.js
function MarketOverviewHelper() {
    this.PARAM_SHOWIN = "showin";
    this.SHOWIN_ACCOUNT = "Account";
    this.SHOWIN_HOME = "Home";
    this.SHOWIN_PLACEORDER = "PlaceOrder";
    this.URL = $.web_resource.getContext() + "/ajax/news/MarketOverviewAJAX_MarketOverviewHome.shtml";
    this.URL_WORLD_MARKET = $.web_resource.getContext()
			+ "/ajax/news/MarketOverviewAJAX_GetWorldMarketOverviewHome.shtml";
}