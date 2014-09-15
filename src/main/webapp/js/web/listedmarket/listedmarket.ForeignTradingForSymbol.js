/**
* All javascript code of Login functionality store in this file.
*/

var _foreignTradingForSymbolClazzSearchListing = new ForeignTradingClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++Date
    $('#fForeignTradingForSymbol_FromDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){return false;}

    });
    
    $('#fForeignTradingForSymbol_ToDate').datepicker({

        changeMonth: true,

        changeYear: true,

        onSelect: function (){return false;},

        onClose: function (){
        	$('#fForeignTradingForSymbol_pagingInfo_indexPage').val(1);
        	_foreignTradingForSymbolClazzSearchListing.executeForSymbol();
        	return false;
        	}

    });
	
	//+++ init ForeignTradingClazzSearchListing
    _foreignTradingForSymbolClazzSearchListing.executeForSymbol();
	
	var opts = _foreignTradingForSymbolClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btView).click(function(){ 
		var fValid = $(opts.form.ForSymbol).valid();
		if(fValid) {
			$('#fForeignTradingForSymbol_pagingInfo_indexPage').val(1);
			_foreignTradingForSymbolClazzSearchListing.executeForSymbol();
		}
	});
});
//Paging
function _goTo(id, index) {
    try {
          $("#fForeignTradingForSymbol_pagingInfo_indexPage").val(index);
          _foreignTradingForSymbolClazzSearchListing.executeForSymbol();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}