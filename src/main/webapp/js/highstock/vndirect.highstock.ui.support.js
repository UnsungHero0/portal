$(function(){
	// define dialog indicators
	defineDialogIndicators();
	
	$('#openIndicatorList').click(function(){
		closeProcessCompare();
		if(!$('.indicatorList').hasClass('opened')){
			$('.indicatorList').addClass('opened');
			$('.indicatorList').show();
			$('#openIndicatorList').addClass('hsButtonActive');
		} else {
			$('.indicatorList').removeClass('opened');
			$('.indicatorList').hide();
		}
	});
	$('#openChartType').click(function(){
		closeProcessCompare();
		if(!$('.chartTypeList').hasClass('opened')){
			$('.chartTypeList').addClass('opened');
			$('.chartTypeList').show();
			$('#openChartType').addClass('hsButtonActive');
		} else {
			$('.chartTypeList').removeClass('opened');
			$('.chartTypeList').hide();
		}
	});
	$('#openCompare').click(function(){
		if(!$('.processCompare').hasClass('opened')){
			$('#hs-ComparingSymbol').html(SYMBOL);
			$('.processCompare').addClass('opened');
			$('.processCompare').show();
			$('#openCompare').addClass('hsButtonActive');
		} else {
			$('.processCompare').removeClass('opened');
			$('.processCompare').hide();
		}
	});
	$('#openTools').click(function(){
		closeProcessCompare();
		if(!$('.toolList').hasClass('opened')){
			$('.toolList').addClass('opened');
			$('.toolList').show();
			$('#openTools').addClass('hsButtonActive');
		} else {
			$('.toolList').removeClass('opened');
			$('.toolList').hide();
		}
	});
	
	closePopupOnOutsideClick();
});
function closePopupOnOutsideClick(){
	var indicators_is_inside = false;
 	$('.indicatorList').hover(function(){
    	indicators_is_inside=true; }, function(){
    		indicators_is_inside=false;
	});
	$('body').mouseup(function() {
  		if (!indicators_is_inside) {
   			closeIndicatorList();
  		}
 	});
	    
	var chartType_is_inside = false;
	$('.chartTypeList').hover(function(){
	    chartType_is_inside=true; }, function(){
	        chartType_is_inside=false;
	    });
    $('body').mouseup(function() {
  		if (!chartType_is_inside) {
   			closeChartTypeList();
  		}
	});
	    
//	var compare_is_inside = false;
//	$('.processCompare ').hover(function(){
//	    compare_is_inside=true; }, function(){
//	        compare_is_inside=false;
//	});
//	$('body').mouseup(function() {
//	    if (!compare_is_inside) {
//	   		closeProcessCompare();
//	  	}
//	});
	
	var drawTrendline_is_inside = false;
	$('.toolList').hover(function(){
	    drawTrendline_is_inside=true; }, function(){
	        drawTrendline_is_inside=false;
	});
	$('body').mouseup(function() {
	    if (!drawTrendline_is_inside) {
	   		closeToolList();
	  	}
	});
}
/** define dialog all indicators */
function defineDialogIndicators(){
	$('.dialogInputParams').dialog({
		autoOpen: false,
		modal: true,
        closeOnEscape: true,
        resizable: false,
        show: 'drop',
        hide: 'drop',
        position: ['center', 130]
	});
}
function closeInputParamsDialog(){
	$('.dialogInputParams').dialog('close');
}
function closeIndicatorList(){
	$('.indicatorList').removeClass('opened');
	$('#openIndicatorList').removeClass('hsButtonActive');
	$('.indicatorList').hide();
}
function closeChartTypeList(){
	$('.chartTypeList').removeClass('opened');
	$('#openChartType').removeClass('hsButtonActive');
	$('.chartTypeList').hide();
}
function closeProcessCompare(){
	$('.processCompare').removeClass('opened');
	$('#openCompare').removeClass('hsButtonActive');
	$('.processCompare').hide();
}
function closeToolList(){
	$('.toolList').removeClass('opened');
	$('#openTools').removeClass('hsButtonActive');
	$('.toolList').hide();
}
function openSMAInputParams(){
	closeIndicatorList();
	$('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getSMAInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Simple Moving Average (SMA)');
	$('.dialogInputParams').dialog('open');
}
function openEMAInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getEMAInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Exponential Moving Average (EMA)');
	$('.dialogInputParams').dialog('open');
}
function openBBandsInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getBBandsInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Bollinger Bands (BBands)');
	$('.dialogInputParams').dialog('open');
}
function openMFIInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getMFIInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Money Flow Index (MFI)');
	$('.dialogInputParams').dialog('open');
}
function openMACDInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getMACDInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Moving Average Convergence Divergence (MACD)');
	$('.dialogInputParams').dialog('open');
}
function openPSARInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getPSARInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Parabolic SAR (PSAR)');
	$('.dialogInputParams').dialog('open');
}
function openROCInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getROCInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Rate of Change (ROC)');
	$('.dialogInputParams').dialog('open');
}
function openRSIInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getRSIInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Relative Strength Index (RSI)');
	$('.dialogInputParams').dialog('open');
}
function openSSInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getSSInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Slow Stochastic (SS)');
	$('.dialogInputParams').dialog('open');
}
function openFSInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getFSInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Fast Stochastic (FS)');
	$('.dialogInputParams').dialog('open');
}
function openVMAInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getVMAInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Volume + VMA');
	$('.dialogInputParams').dialog('open');
}
function openWRInputParams(){
	closeIndicatorList();
    $('.dialogInputParams').load($.web_resource.getContext() + "ajax/analysis/getWRInputParams.shtml");
    $('.dialogInputParams').dialog('option', 'title', 'Williams %R (WR)');
	$('.dialogInputParams').dialog('open');
}