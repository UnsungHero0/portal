var _homeClazzHomePage = new HomeClazzHomePage();

$().ready(function() {
	_homeClazzHomePage.init();
	
	var opts = _homeClazzHomePage.getOption();
	
	$.web_autocomplete.symbols('symbolSuggestionId', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310 
				,callbackResult: function(e, item){
					doQuickSearchSymbol($('#symbolSuggestionId').val(), FROM_MODULE) ;
				}
			}
	);
	
	$('#symbolSuggestionId').bind("keypress",
		function(event) {
			if (event.keyCode == 13) {
				$('#symbolSuggestionId').val($('#symbolSuggestionId').val().toUpperCase());
				$(opts.buttons.searchSymbol).click();
			};
		}
	);
	
	$.web_autocomplete.symbols('fhome_symbolSearch', URL_SYMBOL_AUTO_SUGGESTION );
	
	$("#fHome_btnSymbolSearch").click(function(){
		if($("#symbolSuggestionId").val() != '' && $("#symbolSuggestionId").val() != $('#nhapMaChungKhoanInputText').val()){
			doQuickSearchSymbol($("#symbolSuggestionId").val(), FROM_MODULE) ;
		}
	});
	
	$('.content_slide:not(:first-child)').hide();
});

// Begin adding js for update new GUI
var last_Click_Num = 1;
function hover_slide(num) {
	if (last_Click_Num != num) {
		for(var i=1;i<=4;i++){
			$('#box' + i).hide();
			$('#is_' + i).removeClass('selected');
		}
		
		$('#is_' + num + '').removeClass('selected').addClass('selected');
		$('#box' + num + '').show();
		
		last_Click_Num = num;
	}
}
