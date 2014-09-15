/**
* All javascript code of Login functionality store in this file.
*/

//var _foreignTradingClazzSearchListing = new ForeignTradingClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++Date
    $('#fForeignTrading_TradingDate').datepicker({
        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}
    });
    
    
});

// Paging
function _goTo(index){    
    $("#pagingInfo_indexPage").attr("value", index);	   
    $("#fForeignTrading").submit();
    return false;
}

//download report
function doDownload() {
	// set downloadType
	$("#downloadType").val("$HP_DL_TYPE$");
	
	// change action to download
	$("#fHistoricalPrice").attr("action", URL_DOWNLOAD_REPORT_FOR_SYMBOL);
	$("#fHistoricalPrice").submit();
	
	// reset url action
	$("#fHistoricalPrice").attr("action", "");
}