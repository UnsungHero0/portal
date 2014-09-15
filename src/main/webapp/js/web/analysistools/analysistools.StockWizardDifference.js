$().ready(function() {
	
	$.web_autocomplete.symbols('symbol1', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310, 
				callbackResult: function(e, item){
					$('#StockWizardDifference').submit();
			}}
	);
	
	$('#StockWizardDifference').bind("submit", function() {
		$('#symbol1').val($('#symbol1').val().toUpperCase());
	});
	
	
	
	$.web_autocomplete.symbols('symbol2', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310, 
				callbackResult: function(e, item){
					$('#StockWizardDifference').submit();
			}}
	);
	
	$('#StockWizardDifference').bind("submit", function() {
		$('#symbol2').val($('#symbol2').val().toUpperCase());
	});
	
});

