/**
* All javascript code of Login functionality store in this file.
*/
$(document).ready(function(){				
	//+++Date
    $('#fTradingStatistics_TradingDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}

    });    	
});



//Paging
function _goTo(index){    
    $("#pagingInfo_indexPage").attr("value", index);	   
    $("#fTradingStatistics").submit();
    return false;
}


//download report
function doDownload() {
	// set downloadType
	$("#downloadType").val("$TDS_DL_TYPE$");
	// change action to download
	$("#fTradingStatistics").attr("action", URL_DOWNLOAD_REPORT);
	$("#fTradingStatistics").submit();
	
	// reset url action
	$("#fTradingStatistics").attr("action", "");
}