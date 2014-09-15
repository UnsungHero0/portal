var _newsClazzShareholderNews = new NewsClazzShareholderNews();

$().ready(function() {	
	//Share holder News
	_newsClazzShareholderNews.loadShareholderNews();
	
});

//Paging
function _goToShareholderNews(id, index) {
    try {
          $("#fShareholderNews_pagingInfo_indexPage").val(index);
          _newsClazzShareholderNews.loadShareholderNews();
    } catch (e) {
          alert("_goToShareholderNews(): " + e);
    }
}