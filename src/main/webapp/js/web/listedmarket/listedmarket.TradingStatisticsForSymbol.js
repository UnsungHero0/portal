/**
* All javascript code of Login functionality store in this file.
*/

var _tradingStatisticsClazzSearchListing = new TradingStatisticsClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++Date
    $('#fTradingStatisticsForSymbol_FromDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}

    });
    
    $('#fTradingStatisticsForSymbol_ToDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){
        	$('#fTradingStatisticsForSymbol_pagingInfo_indexPage').val(1);
        	_tradingStatisticsClazzSearchListing.executeForSymbol();
        	return false;
        	}

    });
	
	//+++ init TradingStatisticsForSymbolClazzSearchListing
    _tradingStatisticsClazzSearchListing.executeForSymbol();
	
	var opts = _tradingStatisticsClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btView).click(function(){ 
		var fValid = $(opts.form.ForSymbol).valid();
		if(fValid) {
			$('#fTradingStatisticsForSymbol_pagingInfo_indexPage').val(1);
			_tradingStatisticsClazzSearchListing.executeForSymbol();
		}
	});
});
//Paging
function _goTo(id, index) {
    try {
          $("#fTradingStatisticsForSymbol_pagingInfo_indexPage").val(index);
          _tradingStatisticsClazzSearchListing.executeForSymbol();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}

//download report
function doDownload() {
	var opts = _tradingStatisticsClazzSearchListing.getOption();
	// set downloadType
	$("#downloadType").val("$TDS_4SB_DL_TYPE$");
	// change action to download
	$(opts.form.ForSymbol).attr("action", URL_DOWNLOAD_REPORT_FOR_SYMBOL);
	$(opts.form.ForSymbol).submit();
}
