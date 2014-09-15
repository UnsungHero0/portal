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
var _marketOverviewClazzFreeRegister = new MarketOverviewClazzFreeRegister();
	
(function($) {
$.marketOverviewOption = function() {};
$.extend($.marketOverviewOption, {
	loading : "#progress_loading_img",
	func : {
		callbackExecuted : null, //function (responseText)
		callbackExecuteFail : null //function()
	},	
	form : {
		name : "#fFreeRegisterHomePage",
		divs : {
			marketNews        			: "divMarketNews.id"
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
function MarketOverviewClazzFreeRegister() {
	this.validator = null;
	
	jQuery.extend(jQuery.marketOverviewOption, {
		clazzHandler : this
	});
}
/*************************************************************************************
* init getOption
**************************************************************************************/
MarketOverviewClazzFreeRegister.prototype.getOption = function () {
	return $.marketOverviewOption;
};

/*************************************************************************************
* init functionality
**************************************************************************************/
MarketOverviewClazzFreeRegister.prototype.init = function() {
	var _this = this;
	var opts = _this.getOption();
};
/*************************************************************************************
* load Market Overview News functionality
**************************************************************************************/
MarketOverviewClazzFreeRegister.prototype.loadMarketOverviewNews = function() {
	
	var opts = _marketOverviewClazzFreeRegister.getOption();
	var marketNewsDiv = $(opts.form.divs.marketNews);
	var divMarketNews = document.getElementById(marketNewsDiv.selector);
	var formFields = {"showin" : objNewsHelper.SHOWIN_FREEREGISTER_HOME};
	 var options = {
             action : objNewsHelper.URL_MARKET_NEWS,
             callbackPostSubmit : function (responseText, statusText) {
		 		
	             try {
	            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {                                                                                                
                         $.web_message.error(null, responseText.error);
                         //+++ update market overview news
	            	 } else { 	         
	            		 var strContent = '<table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableindex">';
	            		 divMarketNews.innerHTML = '';
		                 var model = responseText.model;
		                 
		                 if(model != null && model.hostcMarket != null){
		                	 if(model.hostcChagIndex > 0){
		                		 strContent +='<tbody><tr><td><b>HOSE</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/up_2.gif"></td>';
		                		 strContent +='<td><b class="color2">' + model.hostcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color2">' + model.hostcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.hostcChagIndex < 0){
		                		 strContent +='<tbody><tr><td><b>HOSE</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/down_2.gif"></td>';
		                		 strContent +='<td><b class="color1">' + model.hostcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color1">' + model.hostcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.hostcChagIndex == 0){
		                		 strContent +='<tbody><tr><td><b>HOSE</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/bang.gif"></td>';
		                		 strContent +='<td><b class="color3">' + model.hostcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color3">' + model.hostcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hostcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }
		                 }if(model != null && model.hastcMarket != null){
		                	 if(model.hastcCurrentIndex > 0){
		                		 strContent +='<tbody><tr><td><b>HNX</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/up_2.gif"></td>';
		                		 strContent +='<td><b class="color2">' + model.hastcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color2">' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.hastcCurrentIndex < 0){
		                		 strContent +='<tbody><tr><td><b>HNX</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/down_2.gif"></td>';
		                		 strContent +='<td><b class="color1">' + model.hastcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color1">' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.hastcCurrentIndex == 0){
		                		 strContent +='<tbody><tr><td><b>HNX</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/bang.gif"></td>';
		                		 strContent +='<td><b class="color3">' + model.hastcCurrentIndex + '</b></td>';
		                		 strContent +='<td><b class="color3">' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }
		                 }if(model != null && model.upComMarket != null){
		                	 if(model.upComChagIndex > 0){
		                		 strContent +='<tbody><tr><td class="end"><b>UPCOM</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/up_2.gif"></td>';
		                		 strContent +='<td class="end"><b class="color2">' + model.hastcCurrentIndex + '</b></td>';
		                		 strContent +='<td class="end"><b class="color2">' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.upComChagIndex < 0){
		                		 strContent +='<tbody><tr><td class="end"><b>UPCOM</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/down_2.gif"></td>';
		                		 strContent +='<td class="end"><b class="color1">' + model.hastcCurrentIndex + '</b></td>';
		                		 strContent +='<td class="end"><b class="color1">' + model.hastcChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.hastcMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }else if(model.upComChagIndex == 0){
		                		 strContent +='<tbody><tr><td class="end"><b>UPCOM</b>' + '<img hspace="5" src="' + $.web_resource.getContext() + '/images/front/bang.gif"></td>';
		                		 strContent +='<td class="end"><b class="color3">' + model.upComCurrentIndex + '</b></td>';
		                		 strContent +='<td class="end"><b class="color3">' + model.upComChagIndex + '(' + $.web_utils.fomatNumberWithScale(model.upComMarket.pctIndex, 2) + '%)' + '</b></td>';
		                	 }
		                 } 
		                 strContent +='</tbody></table>';
		                 strContent +='<div align="center" style="padding: 10px 0pt 6px;"><a href="' + URL_HOSESTOCKBOARD + '" target="_blank" name="HoseStockBoard"><img src="' + $.web_resource.getContext() + '/images/img/price-o.jpg"></a></div>';
		                 divMarketNews.innerHTML = strContent;
	            	 }
	             } catch (e) {
	                 alert(e);
	             }
             }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};