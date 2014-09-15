var _analysisTooolsClazzFlashChart = new AnalysisTooolsClazzFlashChart();

$().ready(function() {
	//var opts = _analysisTooolsClazzFlashChart.getOption();

	$.web_autocomplete.symbols('symbol4ChartId', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310
			,callbackResult: function(e, item){
				try {
					//+++ this function define in FlashChart.jsp
					addSymbol();
					doLoadSnapshot();
				} catch (e) {
				}
			}
			}
	);

	//++++ init page
	try {
		//+++ this function define in FlashChart.jsp
		//var t=setTimeout("addSymbol()",3000);
		addSymbol();
	} catch (e) {
	}
});

function doLoadSnapshot() {
	var params = [
	               	{name : 'symbol', value : $('#symbol4ChartId').val()}
	              ];
	$.ajax({
		   type: "POST",
		   url: URL_FLASH_CHART_AJAX,
		   data: params,
		   dataType: "html",
		   success: function(data) {
				if(data.indexOf("13276_0123456789") >= 0) {
					$('#indexDetail').html(data);
					$('#snapShotQuickLink').html("");
				} else {
					$('#companySnapshot').html(data);
					$('#indexDetail').html("");
				}
		   },
		   beforeSend: function(){
		   }
	});
}





