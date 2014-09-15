//var _listedMarketClazzMostActive = new ListedMarketClazzMostActive();
//var opts = _listedMarketClazzMostActive.getOption();

$().ready(function() {
	// load MostActive data
	
	var $tabs3 = $('#container-3').tabs({
		beforeActivate: function(event, ui) {
			var index = ui.newTab.index()/2;
			loadMostActiveCompany(index);
	   }
	});
	
	var selectedIdx = $tabs3.tabs('option', 'selected'); // => 0
	if(selectedIdx == 0) {
		loadMostActiveCompany(0);
	} else {
		$tabs3.tabs('select', 0); // switch to first tab
	}
	
});

function loadMostActiveCompany(index) {
	// set value to marketOption
	var marketOption = "ACTIVE";
	var marketOptionTitle = "Actives";
	if (index == 0) {
		marketOption = "ACTIVE";
		marketOptionTitle = "Actives";
	} else if (index == 1) {
		marketOption = "PER_GAINERS";
		marketOptionTitle = "% Gainers";
	} else if (index == 2) {
		marketOption = "PER_LOSERS";
		marketOptionTitle = "% Losers";
	} else if (index == 3) {
		marketOption = "PRI_GAINERS";
		marketOptionTitle = "$ Gainer";
	} else if (index == 4) {
		marketOption = "PRI_LOSERS";
		marketOptionTitle = "$ Losers";
	}
	
	// change title
	var mostActiveTitle = "#fListedMarket_mostTitle";
	$(mostActiveTitle).html('<h5>MOST ' + marketOptionTitle + '</h5><span class="title_othernews"><a href="' + URL_TRADINGS + '">' + resultTransactions + '&nbsp;&raquo;</a></span>');
	
	// set area for ajax active 
	var hoseContent = "#fListedMarket_hoseContent" + index;
	var hnxContent = "#fListedMarket_hnxContent" + index;
	var upComContent = "#fListedMarket_upComContent" + index;
	
//	var accordion = "#accordion" + index;
//	$(accordion).accordion( "destroy" );
	var formFields = {
		"marketOption.option" 		: marketOption
	};
    var options = {
		action : URL_MOST_ACTIVES,
		callbackPostSubmit : function (responseText, statusText) {
    		var model = responseText.model;
    		// build content for hose content
    		$(hoseContent).html(buildMostActiveContent(model.listHostcSummary));    		
    		// build content for hnx content
    		$(hnxContent).html(buildMostActiveContent(model.listHastcSummary));    		
    		// build content for upcom content
    		$(upComContent).html(buildMostActiveContent(model.listUpComSummary));
    		
    		//var floorCode = $("#fListedMarket_mostActiveFloorCode").val();
//			$(accordion).accordion({
//				active : parseInt(floorCode),
//				collapsible: true,
//				autoHeight: false,
//				change: function(event, ui) {
//					var strNewHeader = ui.newHeader.text();
//					if (strNewHeader.indexOf("HNX") >= 0) {
//						$("#fListedMarket_mostActiveFloorCode").val(1);
//					} else if (strNewHeader.indexOf("UPCOM") >= 0) {
//						$("#fListedMarket_mostActiveFloorCode").val(2);
//					} else {
//						$("#fListedMarket_mostActiveFloorCode").val(0);
//					}
//				}				
//			});
			
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
};

/* keep floor code when change tab
 * change "most" title when change tab*/
function changeTabMostActive(index) {
	// set value to marketOption
	var marketOptionTitle = "Actives";
	if (index == 0) {
		marketOptionTitle = "Actives";
	} else if (index == 1) {
		marketOptionTitle = "% Gainers";
	} else if (index == 2) {
		marketOptionTitle = "% Losers";
	} else if (index == 3) {
		marketOptionTitle = "$ Gainer";
	} else if (index == 4) {
		marketOptionTitle = "$ Losers";
	}
	
	// change title
	var mostActiveTitle = "#fListedMarket_mostTitle";
	$(mostActiveTitle).html('<h5>MOST ' + marketOptionTitle + '</h5><span class="title_othernews"><a href="' + URL_TRADINGS + '">' + resultTransactions + '&nbsp;&raquo;</a></span>');
	
	//var floorCode = $("#fListedMarket_mostActiveFloorCode").val(); // 0 : HOSE, 1 : HNX, 2 : UPCOM
	var accordion = "#accordion" + index;
	$(accordion).accordion( "destroy" );
//	$(accordion).accordion({
//		active : parseInt(floorCode),
//		collapsible: true,
//		autoHeight: false,
//		change: function(event, ui) {
//			var strNewHeader = ui.newHeader.text();
//			if (strNewHeader.indexOf("HNX") >= 0) {
//				$("#fListedMarket_mostActiveFloorCode").val(1);
//			} else if (strNewHeader.indexOf("UPCOM") >= 0) {
//				$("#fListedMarket_mostActiveFloorCode").val(2);
//			} else {
//				$("#fListedMarket_mostActiveFloorCode").val(0);
//			}
//		}				
//	});
	
}

function buildMostActiveContent (listCompanySumary) {
	var strContent = '';
	
	strContent += '<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table_active">';
	strContent += '<colgroup><col width="15%"><col width="15%"><col width="15%"><col width="15%"><col width="20%"><col width="20%"></colgroup>';
	if(listCompanySumary != null && listCompanySumary.length > 0) {
		var tdClass = "";
   	 	$.each(listCompanySumary, function(i, comSummary){
   	 		if (i == 0) {
   	 			tdClass = "col2";
   	 		} else {
   	 			tdClass = "col3";
   	 		}
	   	 	var displayStyle = "color1";
	   		var displayMark = "";
	   		if (comSummary.priceChange > 0) {
	   			displayStyle = "color2";
	   			displayMark = "+";
	   		} else if (comSummary.priceChange == 0) {
	   			displayStyle = "color3";
	   		}
	   		
	   		var highestStyle = "color1";
	   		if (comSummary.highestPrice > comSummary.basicPrice) {
	   			highestStyle = "color2";
	   		} 
	   		
	   		var lowestStyle = "color1";
	   		if (comSummary.lowestPrice > comSummary.basicPrice) {
	   			lowestStyle = "color2";
	   		}
	   		
	   		var lastStyle = "color1";
	   		if (comSummary.lastPrice > comSummary.basicPrice) {
	   			lastStyle = "color2";
	   		}
	   		
   	 		strContent += '<tr>';
   	 		//strContent += '<td class="' + tdClass + '" align="left"><a href="javascript:doQuickSearchSymbol(\'' + comSummary.displayCompanyName + '\');">' + comSummary.displayCompanyName + '</a></td>';
   	 		var url = $.web_resource.getContext() + "tong-quan/" + comSummary.displayCompanyName.toLowerCase() + ".shtml";
   	 		strContent += '<td class="' + tdClass + '" align="left"><a href="' + url + '">' + comSummary.displayCompanyName + '</a></td>';
   	 		strContent += '<td class="' + tdClass + '" align="right"><span class="' + highestStyle + '">' +  $.web_utils.fomatNumberWithScale(comSummary.highestPrice, 1) + '</span></td>';
   	 		strContent += '<td class="' + tdClass + '" align="right"><span class="' + lowestStyle + '">' + $.web_utils.fomatNumberWithScale(comSummary.lowestPrice, 1) + '</span></td>';
   	 		strContent += '<td class="' + tdClass + '" align="right"><span class="' + lastStyle + '">' + $.web_utils.fomatNumberWithScale(comSummary.lastPrice, 1) + '</span></td>';
   	 		strContent += '<td class="' + tdClass + '" align="right"><span class="' + displayStyle + '">' + $.web_utils.fomatNumberWithScale(comSummary.priceChange, 1) + '(' + displayMark + $.web_utils.fomatNumberWithScale(comSummary.percentChange, 2) + '%)' + '</span></td>';
   	 		strContent += '<td class="' + tdClass + '" align="right"><span>' + $.web_utils.fomatNumberWithScale(comSummary.volume, 0) + '</span></td>';
   	 		strContent += '</tr>';
		});
    }
	
	strContent += '</table>'; 
	
	return strContent;
}