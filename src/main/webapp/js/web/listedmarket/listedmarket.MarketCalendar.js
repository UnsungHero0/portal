/**
* All javascript code of Login functionality store in this file.
*/

var _marketCalendarClazzSearchListing = new MarketCalendarClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	$.web_autocomplete.symbols('fMarketCalendar_symbol', URL_SYMBOL_AUTO_SUGGESTION );
	
	//+++ init MarketCalendarClazzSearchListing
	_marketCalendarClazzSearchListing.execute();
	
	var opts = _marketCalendarClazzSearchListing.getOption();	
	
	//+++ search
	$(opts.buttons.btSearch).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			_marketCalendarClazzSearchListing.execute();
		}
	});
	
	//+++Calendar
	var oMarketCalendar = null;
	function updateSpecialDays(oMarketCalendarBean) {
		// alert("oMarketCalendarBean: " + oMarketCalendarBean);
		if(oMarketCalendarBean != null) {
			var key = oMarketCalendarBean.year + "." + oMarketCalendarBean.month;
			// alert(key + " --- " +  oMarketCalendarBean.days);
			SPECIAL_DAYS[key] = oMarketCalendarBean.days;
		}
	};

	/**
	*
	*/
	function dateIsSpecial(year, month, day) {
		var key = year + "." + month;	
	    var m = SPECIAL_DAYS[key];
	    if (!m) return false;
	    for (var i in m)  {
	    	if (m[i] == day) return true;
	    }
	    return false;
	  };

	  function dateChanged(calendar) {
		doSearchByDate(calendar.date.print("%Y/%m/%d"));
	  };

	  function ourDateStatusFunc(date, y, m, d) {
	    if (dateIsSpecial(y, m, d))
	      return "special";
	    else
	      return false; // other dates are enabled
	      // return true if you want to disable other dates
	  };

	  Calendar.setup(
	    {
	      flat         : "calendar-container", // ID of the parent element
	      flatCallback : dateChanged,          // our callback function
	      date			: RIGHTSDATE,
	      dateStatusFunc : ourDateStatusFunc
	    }
	  );
});

function doSearchByDate(strDate){	
	$("#fMarketCalendar_srtRightsDate").val(strDate);
	$("#fMarketCalendar_typeOfDate").val("");
	_marketCalendarClazzSearchListing.execute();
}

//Paging
function _goTo(id, index) {
    try {
          $("#fMarketCalendar_pagingInfo_indexPage").val(index);
          _marketCalendarClazzSearchListing.execute();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}