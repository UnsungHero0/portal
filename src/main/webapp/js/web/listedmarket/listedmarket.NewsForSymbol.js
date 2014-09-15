var _newsForSymbol = new NewsForSymbol();
var opts = _newsForSymbol.getOption();

$().ready(function() {
	_newsForSymbol.init();
	$(opts.buttons.search).click(function(){
		$('#fNewsForSymbol_pagingInfo_indexPage').val(1);
		_newsForSymbol.searchNews();
	});
	
	// pick date for field fromDate
	var d = new Date();
	var currentyear = d.getFullYear();
	$(opts.form.fields.fromDate).datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fNewsForSymbol_ToDateId').val() != ""){
				$('#fNewsForSymbol_pagingInfo_indexPage').val(1);
				_newsForSymbol.searchNews();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});
	
	// pick date for field toDate
	$(opts.form.fields.toDate).datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fNewsForSymbol_fromDateId').val() != ""){
				$('#fNewsForSymbol_pagingInfo_indexPage').val(1);
				_newsForSymbol.searchNews();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});
	
	_newsForSymbol.searchNews();
	
	$('.barLoadMore #loadMore').click(function(){
		_newsForSymbol.loadMoreNews();
	});
});

function _goTo(id, index) {
	try {
		$(opts.form.fields.pagingIndex).val(index);
		_newsForSymbol.searchNews();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}