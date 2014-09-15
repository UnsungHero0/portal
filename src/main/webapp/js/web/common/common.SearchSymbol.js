var _commonClazzSearchSymbol = new CommonClazzSearchSymbol();

$().ready(function() {
//	_commonClazzSearchSymbol.init();
	_commonClazzSearchSymbol.execute();
	
	var opts = _commonClazzSearchSymbol.getOption();
	
	$(opts.buttons.searchSymbol).click(function(){
		$("#fSearchSymbol_pagingInfo_indexPage").val(1);
		_commonClazzSearchSymbol.execute();		
	});
	
	$('#fSearchSymbol_companyName').keypress(function(event) {
		if (event.keyCode == '13') {
			$(opts.buttons.searchSymbol).click();			
			event.preventDefault();
		}
	});

	
	$.web_autocomplete.symbols('fSearchSymbol_symbol', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 300,
				callbackResult: function(e, item){
					$(opts.buttons.searchSymbol).click();
				}
			}
	);
	
});

function _goTo(webNavId, index) {
	try {
		$("#fSearchSymbol_pagingInfo_indexPage").val(index);		
		_commonClazzSearchSymbol.execute();
	} catch (e) {
		alert("_goTo(): " + e);
	}
}



