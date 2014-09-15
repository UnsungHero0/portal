/**
* All javascript code of Login functionality store in this file.
*/

var _searchNewsClazzSearchListing = new SearchNewsClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++ init SearchNewsClazzSearchListing
	_searchNewsClazzSearchListing.execute();
		
	var opts = _searchNewsClazzSearchListing.getOption();
		
	//+++ search
	$(opts.buttons.btSearch).click(function(){ 
		var fValid = $(opts.form.name).valid();
		if(fValid) {
			$('#fSearchNews_pagingInfo_indexPage').val(1);
			_searchNewsClazzSearchListing.execute();
		}
	});
});
//Paging
function _goTo(id, index) {
    try {
          $("#fSearchNews_pagingInfo_indexPage").val(index);
          _searchNewsClazzSearchListing.execute();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}