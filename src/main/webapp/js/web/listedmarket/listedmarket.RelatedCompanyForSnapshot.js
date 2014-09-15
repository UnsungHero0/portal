/**
* All javascript code of Login functionality store in this file.
*/

var _relatedCompanyForSnapshotClazzSearchListing = new RelatedCompanyForSnapshotClazzSearchListing();   

$(document).ready(function(){			
	var otherStreet = false;
	
	//+++ init RelatedCompanyForSnapshotClazzSearchListing
	_relatedCompanyForSnapshotClazzSearchListing.execute();
		
});

//Paging
function _goToRelatedCom(id, index) {
    try {
          $("#RelatedCompany_pagingInfo_indexPage").val(index);
          _relatedCompanyForSnapshotClazzSearchListing.execute();
    } catch (e) {
          alert("_goToRelatedCom(): " + e);
    }
}