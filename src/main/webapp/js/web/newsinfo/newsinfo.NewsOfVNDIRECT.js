var _newsClazzNewsOfVNDIRECT = new NewsClazzNewsOfVNDIRECT();

$().ready(function() {	
	//News Of VNDIRECT
	_newsClazzNewsOfVNDIRECT.loadNewsOfVNDIRECT();
	
});

//Paging
function _goToNewsOfVNDIRECT(id, index) {
    try {
          $("#fNewsOfVNDIRECT_pagingInfo_indexPage").val(index);
          _newsClazzNewsOfVNDIRECT.loadNewsOfVNDIRECT();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}