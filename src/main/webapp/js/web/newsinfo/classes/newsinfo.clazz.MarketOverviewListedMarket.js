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
		name : "#fHome",
		divs : {
			hose        			: "divHose.id",
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
	
	
	var opts = _marketOverviewClazzHome.getOption();
	var hoseDiv = $(opts.form.divs.hose);
	var hnxDiv = $(opts.form.divs.hnx);
	var upcomDiv = $(opts.form.divs.upcom);
	var divHose = document.getElementById(hoseDiv.selector);
	var divHnx = document.getElementById(hnxDiv.selector);
	var divUpcom = document.getElementById(upcomDiv.selector);
	var formFields = {"showin" : objMarketOverviewHelper.SHOWIN_HOME};
	 var options = {
             action : objMarketOverviewHelper.URL,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market overview news
	            	 } else {
	            		 //Hose processing
	            		 var strContent = '';
	            		 divHose.innerHTML = '';
		                 var model = responseText.model;
		                 //clear Hose content
		                 if(model != null && model.hostcMarket != null) {
		                	 //step 1: Show HOSE and UP or DOWN icon
		                	 if (model.hostcChagIndex > 0) {
		                		 strContent +='<span class="fl Title-ck">Hose<br /><img src="' + $.web_resource.getContext() + '/images/front/ico_green.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color2"><b>' + model.hostcCurrentIndex + '<br />' + model.hostcChagIndex + ' (' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)</b></span>';
		                	 } else {
		                		 strContent +='<span class="fl Title-ck">Hose<br /><img src="'+ $.web_resource.getContext() +'/images/front/ico_red.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color1"><b>' + model.hostcCurrentIndex + '<br />' + model.hostcChagIndex + ' (' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)</b></span>';
		                	 }
		                	 
		                	 
		                 } 
		                 divHose.innerHTML = strContent;
		                 //=========================
		                 //HNX processing
	            		 strContent = '';
	            		 divHnx.innerHTML = '';
		                 //clear HNX content
		                 if(model != null && model.hastcMarket != null) {
		                	 //step 1: Show HNX and UP or DOWN icon
		                	 if (model.hastcCurrentIndex > 0) {
		                		 strContent +='<span class="fl Title-ck">HNX<br /><img src="' + $.web_resource.getContext() + 'images/front/ico_green.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color2"><b>' + model.hastcCurrentIndex + '<br />' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)</b></span>';
		                	 } else {
		                		 strContent +='<span class="fl Title-ck">HNX<br /><img src="' + $.web_resource.getContext() + 'images/front/ico_red.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color1"><b>' + model.hastcCurrentIndex + '<br />' + model.hastcChagIndex + ' (' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)</b></span>';
		                	 }
		                 } 
		                 divHnx.innerHTML = strContent;
		                 //=========================
		                 //UPCOM processing
	            		 strContent = '';
	            		 divUpcom.innerHTML = '';
		                 //clear Upcom content
		                 if(model != null && model.upComMarket != null) {
		                	 //step 1: Show Upcom and UP or DOWN icon
		                	 if (model.upComChagIndex > 0) {
		                		 strContent +='<span class="fl Title-ck">UPCOM<br /><img src="' + $.web_resource.getContext() + 'images/front/ico_green.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color2"><b>' + model.upComCurrentIndex + '<br />' + model.upComChagIndex + ' (' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)</b></span>';
		                	 } else {
		                		 strContent +='<span class="fl Title-ck">UPCOM<br /><img src="' + $.web_resource.getContext() +'images/front/ico_red.gif" class="set-img" /></span>';
		                		 strContent +='<span class="fr color1"><b>' + model.upComCurrentIndex + '<br />' + model.upComChagIndex + ' (' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)</b></span>';
		                	 }
		                 } 
		                 divUpcom.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 alert(e);
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
		                		 if(i==1) //Dow Jones
		                			 strContent += '<tr><td class="col1">Dow Jones</td>';
		                		 else if(i==2) //Nasdaq
		                			 strContent += '<tr><td class="col1">Nasdaq</td>';
		                		 else //S&P 500
		                			 strContent += '<tr><td class="col1">S&P 500</td>';
		                		 
		                		 if (market.chgIndex > 0) {
			                		 strContent += '<td class="col1"><span class="color2">' + market.currentIndex + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/up.gif" hspace="5" /><span class="color2">' + market.chgIndex + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color2">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 } else if (market.chgIndex < 0) {
			                		 strContent += '<td class="col1"><span class="color1">' + market.currentIndex + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/down.gif" hspace="5" /><span class="color1">' + market.chgIndex + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color1">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 } else {
			                		 strContent += '<td class="col1"><span class="color3">' + market.currentIndex + '</span></td>';
			                		 strContent += '<td class="col1"><img src="' + $.web_resource.getContext() + '/images/front/bang.gif" hspace="5" /><span class="color3">' + market.chgIndex + '</span></td>';
	                                 strContent += '<td class="col1"><span class="fr color3">' + $.web_utils.fomatNumberWithScale(market.pctIndex, 2) + '</span></td>';
			                	 }
		                		 strContent += '</tr>';
		                	 });
		                	 
		                 }
		                 divWorldIndex.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};