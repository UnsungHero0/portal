var _newsOnlineClazzFreeRegister = new NewsOnlineClazzFreeRegister();

$().ready(function() {
	//Populate IPONews
	_newsOnlineClazzFreeRegister.loadIPONews();
	//Populate VNDSNews
	_newsOnlineClazzFreeRegister.loadVNDSNews();
	
});
//Paging
function _goToStockInfo(id, index) {
    try {
          $("#fFreeRegisterHomePage_indexPage").val(index);
          _newsOnlineClazzFreeRegister.loadIPONews();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}

function _goToVNDSNews(id, index) {
    try {
          $("#fFreeRegisterHomePage_indexPage").val(index);
          _newsOnlineClazzFreeRegister.loadVNDSNews();
    } catch (e) {
          alert("_goTo(): " + e);
    }
}