/**
* All javascript code of Login functionality store in this file.
*/

var _historicalPriceForSymbolClazzSearchListing = new HistoricalPriceClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++Date
    $('#fHistoricalPrice_FromDate').datepicker({
        changeMonth: true,
        changeYear: true,
        onSelect: function (){return false;},
        onClose: function (){return false;}
    });
	
    $('#fHistoricalPrice_ToDate').datepicker({
        changeMonth: true,
        changeYear: true,
        onSelect: function (){return false;},
        onClose: function (){
        	$('#fHistoricalPrice_pagingInfo_indexPage').val(1);
        		_historicalPriceForSymbolClazzSearchListing.executeForSymbol();
        	 return false;
        	}
    });
    
	//+++ init HistoricalPriceForSymbolClazzSearchListing
	_historicalPriceForSymbolClazzSearchListing.executeForSymbol();
	
	var opts = _historicalPriceForSymbolClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btView).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			$('#fHistoricalPrice_pagingInfo_indexPage').val(1);
			_historicalPriceForSymbolClazzSearchListing.executeForSymbol();
		}
	});
});

//Paging
function _goTo(id, index) {
    try {
          $("#fHistoricalPrice_pagingInfo_indexPage").val(index);
          _historicalPriceForSymbolClazzSearchListing.executeForSymbol();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}

// download report
function doDownload() {
	var opts = _historicalPriceForSymbolClazzSearchListing.getOption();
	// set downloadType
	$("#downloadType").val("$HP_DL_TYPE$");
	// change action to download
	$(opts.form.name).attr("action", URL_DOWNLOAD_REPORT_FOR_SYMBOL);
	$(opts.form.name).submit();
}
