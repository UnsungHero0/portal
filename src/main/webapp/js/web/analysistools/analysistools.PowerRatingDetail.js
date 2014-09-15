

function loadWatchWaitImage(){
	
	$('#loadWatchListWaitImg').html('<img src="' + AJAX_IMAGE_LOADING + '" />');
	
	$('#loadWatchListWaitImg').show();
	
	
}

function hideWatchWaitImage(){
	$("#loadWatchListWaitImg").hide();
}

$().ready(function() {
	//var activeAccordIndex = $("#activeAccordIndex").html();
	// default action index first div
	loadMostActiveCompany(0);
	$(function(){
		$('#watchListDialog').dialog({
			width: 370,
			autoOpen: false,
			resizable: false,
			buttons: {
				Close: function(){					
					$(this).dialog('close');
				}
			}
		});
	});
	
	$.web_autocomplete.symbols('symbolWatchListSuggestionId', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310 
			}
	);
	
	$('#symbolWatchListSuggestionId').bind("keypress",
			function(event) {
				if (event.keyCode == 13) {
					$('#symbolWatchListSuggestionId').val($('#symbolWatchListSuggestionId').val().toUpperCase());
					$("#btnAddWatchList").click();
				};
			}
		);
	
	$("#btnAddWatchList").click(function(){
		doAddSecCodeToWatchList($("#symbolWatchListSuggestionId").val());
	});
	
});

function doAddSecCodeToWatchList(secCode){
	addToWatchList(secCode.toUpperCase());
}

function loadMostActiveCompany(index) {
	
//	var fListedMarket_hoseTitle = "#fListedMarket_hoseTitle"; 
		
	var accordion = "#accordion";
//	$(accordion).accordion( "destroy" );
	
	$(accordion).accordion({
		collapsible: true,
		autoHeight: false,	
		change: function(event, ui) {
			var strNewHeader = ui.newHeader.text();
			return false;
		}		
	});	
	
	if(index == 0){
		//return false;
		//loadMyWatchList();
		$('#accordion').accordion('activate','#myWatchListTitle');
	} 
	
	if(index == 1){
		$('#accordion').accordion('activate','#topTenPrTitle');
	}		
	if(index == 2){
		$('#accordion').accordion('activate','#upgradedTopTenTitle');
	}
	if(index == 3){
		$('#accordion').accordion('activate','#bottomTenTitle');
	}
	if(index == 4){
		$('#accordion').accordion('activate','#downgradedBottomTenTitle');
	}
};



function navigateToCompanyNews(secCode){
	doQuickSearchSymbol(secCode, 8);
}

var SEPARATOR = '|';

function getSecCodeFromCookie(sampleCookie){
	var index = sampleCookie.indexOf(SEPARATOR);
	if(index > 0)
		return sampleCookie.substring(0, index);
	else
		return '';
}

function getRankFromCookie(sampleCookie){
	var index = sampleCookie.indexOf(SEPARATOR);
	if(index > 0)
		return sampleCookie.substring(index + 1);
	else return '';
}

function setSecCodeToCookie(secCode){
	var prefix = "VNDSECLIST.";
	var allCookies, tmp, tmpSecCode, tmpOrder;
	var selfOrder;
	allCookies = $.cookies.filter(/^VNDSECLIST/g);
	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
			for( name in allCookies )
	        {
				tmp = $.cookies.get( name );
				
	                if(getRankFromCookie(tmp) >= 10){
	                	$.cookies.del( name );
	                }
	                if(getSecCodeFromCookie(tmp) == secCode){
	                	selfOrder = getRankFromCookie(tmp); 	
	                }	
	        }
	}
	
	
	
	allCookies = $.cookies.filter(/^VNDSECLIST/g);
	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
			for( name in allCookies )
	        {
				tmp = $.cookies.get( name );
					if(selfOrder == null){
						tmpSecCode = getSecCodeFromCookie(tmp);
		                tmpOrder = parseInt(getRankFromCookie(tmp)) + 1;
		                $.cookies.set(name, tmpSecCode + SEPARATOR+ tmpOrder);
					} 
	                if(selfOrder != null && getRankFromCookie(tmp)< selfOrder){
		                tmpSecCode = getSecCodeFromCookie(tmp);
		                tmpOrder = parseInt(getRankFromCookie(tmp)) + 1;
		                $.cookies.set(name, tmpSecCode + SEPARATOR+ tmpOrder);
	                }	
	        }
	}
	$.cookies.set(prefix+secCode, secCode+SEPARATOR+"1");	
}

function addToWatchList(secCode){
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "symbol=" + secCode,
		url: URL_POWER_RATING_SYMBOL_QUICK_SEARCH,
		success: function(data){
			var model = data.model;
			if(model.existSymbol == 'N'){
				return;
			}
			setSecCodeToCookie(secCode.toUpperCase());
//			open Add Watch List Dialog	
			var strContent = "";
			var content = $('#popupContent');
			content.html(strContent);
			$('#symbolWatchListSuggestionId').val('');
			$('#watchListDialog').dialog('open');
			loadWatchWaitImage();
			loadWatchListData(secCode.toUpperCase());
		},
		beforeSend: null
	});	
	
}
//============================================================================================

function loadWatchListData(secCode){
	currentSecCode = secCode;
	$("#currentSecCode").html('<b>'+currentSecCode+'</b>');
	var allSecCode = getWatchSecCodeList();
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "watchSecCodeList="+allSecCode,
		url: URL_GET_POWER_RATING_BY_WATCHLIST,
		success: function(data){
		hideWatchWaitImage();
		populatePopupWatchListContent(data);
		},
		beforeSend: null
	});
}
var currentSecCode ;

function deleteWatchListItem(item){
	$('#'+item).remove();
	var valueToDelete, allCookies, name;
	var deletedOrder;

	valueToDelete = item;
//	allCookies = $.cookies.get();
	
	allCookies = $.cookies.filter(/^VNDSECLIST/g);

	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
	        for( name in allCookies )
	        {
	            
	        	if( getSecCodeFromCookie(allCookies[name]) === valueToDelete )
	                {
	        			deletedOrder = getRankFromCookie(allCookies[name]); 
	        			$.cookies.del( name );
	                }
	        }
	}
	reOrderCookie(deletedOrder);
}

function reOrderCookie(orderMark){
	var  allCookies, name , tmp, tmpOrder, tmpSecCode;
	allCookies = $.cookies.filter(/^VNDSECLIST/g);

	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
	        for( name in allCookies )
	        {
	            
	        	tmp = $.cookies.get( name );
	        	if( getRankFromCookie(tmp) > orderMark )
	                {
		        		
	        			tmpSecCode = getSecCodeFromCookie(tmp);
		                tmpOrder = parseInt(getRankFromCookie(tmp)) - 1;
		                $.cookies.set(name, tmpSecCode + SEPARATOR +tmpOrder);
	                }
	        }
	}
	
}

function removeAllWatchList(){
	
	$("#tblWatchList tr:gt(1)").remove();
	allCookies = $.cookies.filter(/^VNDSECLIST/g);

	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
	        for( name in allCookies )
	        {
               	$.cookies.del( name );
	        }
	}
	
}

populatePopupWatchListContent = function(data) {
	
	if (data.model) {
		try {
	
			var content = $('#popupContent');
			
			var messagePrefix = $('#messagePrefix').html(); 
			var messagePostfix = $('#messagePostfix').html();
			var guideInfo = $('#guideInfo').html();
			var yourList = $('#yourList').html();
			var secCode = $('#popupSecCode').html();
			var dateLabel = $('#popupDate').html();
			var PrLabel = $('#popupPr').html();
			var deleteImg = $('#popupDeleteImg').html();
			var deleteAllImg = $('#popupDeleteAllImg').html();
			
			var strContent = '';
			strContent = strContent + ''+	
			
		            '<div class="list_watchlist">'+
		            	'<table id="tblWatchList" cellpadding="0" cellspacing="1" border="0" width="100%" bgcolor="#d5d5f9">'+
		                	'<tr><th colspan="4" align="left"><strong>'+yourList+'</strong></th></tr>'+
		                    '<tr><td align="center"><b>'+secCode+'</b></td>'+
		                    	'<td align="center"><b>'+dateLabel+'</b></td>'+
		                        '<td align="center"><b>'+PrLabel+'</b></td>'+
		                        '<td><b>&nbsp;</b></td>'+
		                    '</tr>';
			var className;
			if (data.model.watchListPrs != null && data.model.watchListPrs.length > 0) {
				var flag=0;
				$.each(data.model.watchListPrs, function(i, obj){
					
					if(obj.pctMarkChange == null )
						className='padtext';
					else if(obj.pctMarkChange == 0)
						className='padtext color3';
					else if (obj.pctMarkChange < 0)
						className='padtext color1';
					else
						className='padtext color2';
					
					if(currentSecCode == obj.symbol){
						strContent +='<tr id="'+obj.symbol+'"><td align="center"><b>'+obj.symbol+'</b></td>'+
						'<td align="center">'+obj.strTransDate+'</td>'+
							'<td align="center"><span class="'+className+'"><b style="font-size:14px">'+obj.mark+'</b></span></td>'+
						'</td>'+
						'<td align="center"><a href="javascript:deleteWatchListItem(\''+obj.symbol+'\')" >'+deleteImg+'<img  style="margin-left:5px" src="' + WEB_CONTEXT + '/images/front/icon_delete.gif" /></a></td>'+
						'</tr>';
						flag=1;
					}
				});
				
				if(flag == 0){
					
					strContent +='<tr id="'+currentSecCode+'"><td align="center"><b>'+currentSecCode+'</b></td>'+
					'<td align="center">'+'-'+'</td>'+
						'<td align="center"><span ><b style="font-size:14px">'+'-'+'</b></span></td>'+
					'</td>'+
					'<td align="center"><a href="javascript:deleteWatchListItem(\''+currentSecCode+'\')" >'+deleteImg+'<img  style="margin-left:5px" src="' + WEB_CONTEXT + '/images/front/icon_delete.gif" /></a></td>'+
					'</tr>';
				}
				
				var tmp = 0;
				$.each(data.model.watchListPrs, function(i, obj){
					if(tmp>9) return;
					if(obj.pctPriceChange == null )
						className='padtext';
					else if(obj.pctPriceChange == 0)
						className='padtext color3';
					else if (obj.pctPriceChange < 0)
						className='padtext color1';
					else
						className='padtext color2';
					if(currentSecCode != obj.symbol){
						tmp++;
						strContent +='<tr id="'+obj.symbol+'"><td align="center"><b>'+obj.symbol+'</b></td>'+
						'<td align="center">'+obj.strTransDate+'</td>'+
							'<td align="center"><span class="'+className+'"><b style="font-size:14px">'+obj.mark+'</b></span></td>'+
						'</td>'+
						'<td align="center"><a href="javascript:deleteWatchListItem(\''+obj.symbol+'\')" >'+deleteImg+'<img  style="margin-left:5px" src="' + WEB_CONTEXT + '/images/front/icon_delete.gif" /></a></td>'+
						'</tr>';
					}
				});
			}
			strContent = strContent +  '<tr><td colspan="4" align="right"><a href="javascript:removeAllWatchList();"  class="linkred">'+deleteAllImg+'</a></td></tr>'+
		                '</table>'+
		            '</div>';
		       
			
			content.html(strContent);			
			} catch(e) {
			alert(e);
		}
	}	
};

//=========================================================================================


function getWatchSecCodeList(){
	var allCookies, name;
	var result = '';
	var tmp;
	
	allCookies = $.cookies.filter(/^VNDSECLIST/g);
	
	if( typeof allCookies === 'object' && allCookies instanceof Array )
	{
			
			for( name in allCookies )
	        {
	                tmp = $.cookies.get( name );
	                result = result + tmp+",";
	        }
	        result = result.substring(0,result.length - 1);
	}
	
	return result;
}

function loadMyWatchList(){
	var allSecCode = getWatchSecCodeList();
	
	
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "watchSecCodeList="+allSecCode,
		url: URL_GET_POWER_RATING_BY_WATCHLIST,
		success: function(data){
//			hideWaitImage();
		populateWatchContent(data);
		},
		beforeSend: null
	});
}

populateWatchContent = function(data) {
	if (data.model) {
		try {
	
			var content = $('#myWatchListContent');
			var secCodeHeader = $('#secCodeHeaderTitle').html(); 
			var todayPrHeader = $('#todayPrHeaderTitle').html();
			var priceHeader = $('#priceHeaderTitle').html();
			var fourDayHeader = $('#fourDayHeaderTitle').html();
			
			var indexHeader = $('#indexHeaderTitle').html(); 
			var prVariantPercentageHeader = $('#prVariantPercentageHeaderTitle').html();
			var viewGraphHeader = $('#viewGraphHeaderTitle').html();
			var viewNewsHeader = $('#viewCompanyNewsHeaderTitle').html();
			
			var strContent = '';
			
			strContent = strContent + '<div id="topTenPrContent">'+
			'<div class="padding0px">'+
			'<div class="clearfix">'+
				'<table border="0" id="topTenPowerRatingTable" class="table1_PRs_active"'+
					'bordercolor="#ffffff" '+
					'width="100%" cellspacing="0" cellpadding="0">'+
					'<thead>'+
						'<tr>'+
							'<th width="5%" align="center">'+indexHeader+
							'</th>'+
							'<th width="5%" align="center">'+secCodeHeader+
							'</th>'+
							'<th width="8%" align="center">'+todayPrHeader+
							'</th>'+
							'<th width="8%" align="center">'+priceHeader+
							'</th>'+
							'<th width="8%" align="center">'+fourDayHeader+
							'</th>'+							
							'<th width="8%" align="center">'+prVariantPercentageHeader+
							'</th>'+
							'<th width="8%" align="center">'+viewGraphHeader+
							'</th>'+
							'<th width="8%" align="center">'+viewNewsHeader+
							'</th>'+
						'</tr>'+
					'</thead>'+
					'<tbody>';
					var i=0;
					var className;
					if (data.model.watchListPrs != null && data.model.watchListPrs.length > 0) {
						$.each(data.model.watchListPrs, function(i, obj){
							if(i==10) return;
							i++;
							if(obj.pctMarkChange == null )
								className='padtext';
							else if(obj.pctMarkChange == 0)
								className='padtext color3';
							else if (obj.pctMarkChange < 0)
								className='padtext color1';
							else
								className='padtext color2';
							
							strContent +='<tr>'+
							'<td align="center">'+i+'</td>'+
							'<td align="center" nowrap="nowrap">'+
							'<a href="'+$.web_resource.getContext()+'tong-quan/'+obj.symbol.toLowerCase()+'.shtml">'+
								'<span class="padtext bold2">'+obj.symbol+'</span>'+
							'</a>'+
							'</td>'+
							'<td align="center">'+
							'<span class="'+className+'"><div align="right" style="width: 20px">'+obj.mark+'</div></span>'
							+'</td>';
							
							if(obj.pctPriceChange == null )
								className='padtext';
							else if(obj.pctPriceChange == 0)
								className='padtext color3';
							else if (obj.pctPriceChange < 0)
								className='padtext color1';
							else
								className='padtext color2';
							
							strContent +='<td align="center">'+
							'<span class="'+className+'"><div align="right" style="width: 40px">'+obj.closePrice+'</div></span>'+
							'</td>'+
							'<td align="center"><span class="padtext"><div align="right" style="width: 20px">'+obj.markB4days+'</div> </span></td>';
							
							if(obj.closePrice == null || obj.closePriceB4days == null)
								className='padtext';
							else if(obj.closePrice == obj.closePriceB4days)
								className='padtext color3';
							else if (obj.closePrice < obj.closePriceB4days)
								className='padtext color1';
							else
								className='padtext color2';
							
							strContent +=
							'<td align="center">'+
							'<span class="'+className+'"><div align="right" style="width: 50px">'+obj.priceChangPct+'</div></span>'+
							'</td>'+
							'<td align="center"><a data_symbol="'+obj.symbol+'" class="flash_chart_link" href="'+$.web_resource.getContext()+'analysis/stock-information/flash-chart.shtml"><img src="' + $.web_resource.getContext() + '/images/front/icon_link_chart.png" /></a></td>'+
							'<td align="center"><a data_symbol="'+obj.symbol+'" class="flash_chart_link" href="'+$.web_resource.getContext()+'tin-doanh-nghiep/'+obj.symbol.toLowerCase()+'.shtml"><img src="' + $.web_resource.getContext() + '/images/front/icon_news_cp.gif"/></a></td>'+
							'</tr>';
						});
					}
				strContent += '</tbody>'+
				'</table>'+
				'</div>'+
				'</div>'+
				'</div>';
					
			content.html(strContent);			
			} catch(e) {
			alert(e);
		}
	}	
};