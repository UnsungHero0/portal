function onChangeContent(selectedIndex, contentContainers, firstSpans, secondSpans, thirdSpans){
	
	for(var i=0; i<4;i++){
		contentContainers[i].hide();
		firstSpans[i].attr("class","btn_left_inbox");
		secondSpans[i].attr("class","btn_right_inbox");
		thirdSpans[i].attr("class","btn_center_inbox");
		
	}
	contentContainers[selectedIndex].show();
	firstSpans[selectedIndex].attr("class","btn_left_SQ");
	secondSpans[selectedIndex].attr("class","btn_right_SQ");
	thirdSpans[selectedIndex].attr("class","btn_center_SQ");

	
}

function changeDescriptionContent(index){
	
	var descContainer =$("#prDefinitionContent");
	var advContainer = $("#prAdvantageContent");
	var userContainer =$("#prUserContent");
	var menuContainer = $("#prMenuContent");
	
	var containerList = new Array();
	containerList[0] = descContainer;
	containerList[1] = advContainer;
	containerList[2] = userContainer;
	containerList[3] = menuContainer;
	
	var firstSpanList = new Array();
	firstSpanList[0] = $("#descFirstSpan");
	firstSpanList[1] = $("#advFirstSpan");
	firstSpanList[2] = $("#userFirstSpan");
	firstSpanList[3] = $("#menuFirstSpan");
	
	var secondSpanList = new Array();
	secondSpanList[0] = $("#descSecondSpan");
	secondSpanList[1] = $("#advSecondSpan");
	secondSpanList[2] = $("#userSecondSpan");
	secondSpanList[3] = $("#menuSecondSpan");

	var thirdSpanList = new Array();
	thirdSpanList[0] = $("#descThirdSpan");
	thirdSpanList[1] = $("#advThirdSpan");
	thirdSpanList[2] = $("#userThirdSpan");
	thirdSpanList[3] = $("#menuThirdSpan");
	
	onChangeContent(index, containerList, firstSpanList, secondSpanList, thirdSpanList);
	
}



$().ready(function() {
	$(function(){
		$('#powerLevelDialog').dialog({
			width: 500,
			autoOpen: false,
			resizable: false,
			buttons: {
				Close: function(){					
					$(this).dialog('close');
				}
			}
		});
	});
	
	
});
		 
function showLevelPopup(level){
	loadWaitImage();
	$('#powerLevelDialog').dialog('open');
	var strContent = "";
	var content = $('#content');
	content.html(strContent);			
	loadData(level);
}


function loadWaitImage(){
	$('#loadWaitImg').html('<img src="' + AJAX_IMAGE_LOADING + '" />');
	$('#loadWaitImg').show();
}

function hideWaitImage(){
	$("#loadWaitImg").hide();
}


function loadData(level){

	
	$.ajax({
		type: "POST",
		dataType: "json",
		data: "level="+level,
		url: URL_GET_POWER_RATING_BY_LEVEL,
		success: function(data){
			hideWaitImage();
			populateContent(data);
		},
		beforeSend: null
	});
}

populateContent = function(data) {
	if (data.model) {
		try {
	
			var content = $('#content');
			var divSumarizeInfo = $('#divSumarizeInfo');
			 
			
			var secCodeHeader = $('#secCodeHeaderTitle').html(); 
			var todayPrHeader = $('#todayPrHeaderTitle').html();
			var priceHeader = $('#priceHeaderTitle').html();
			var fourDayHeader = $('#fourDayHeaderTitle').html();
			
			var firstSumarizePart = $('#firstSumarizeInfoPart').html(); 
			var secondSumarizePart = $('#secondSumarizeInfoPart').html();
			var thirdSumarizePart = $('#thirdSumarizeInfoPart').html();
			
			var strContent = '';
			var sumarizeContent='';
			strContent = strContent + '<div class="padding0px">'+
			'<div class="clearfix">'+
				'<table border="1" id=""'+
				'bordercolor="#D5D8E1" style="border-collapse: collapse;"'+
				'width="100%" cellspacing="0" cellpadding="3px">'+
				'<thead>'+
					'<tr bgcolor="#efefef">'+
						'<th width="5%" align="center">'+
							'<b>'+secCodeHeader+' </b>'+
						'</th>'+
						'<th width="8%" align="center">'+
							'<b>'+todayPrHeader+
							'</b>'+
						'</th>'+
						'<th width="8%" align="center">'+
							'<b>'+ priceHeader +'</b>'+
						'</th>'+
						'<th width="8%" align="center">'+
							'<b>'+ fourDayHeader +
							'</b>'+
						'</th>'+
					'</tr>'+
				'</thead>'+
				'<tbody>';
			
			if (data.model.levelPrs != null && data.model.levelPrs.length > 0) {
				var nextClassName;
				var className;
				$.each(data.model.levelPrs, function(i, obj){
					
					if(obj.pctMarkChange == null )
						className='padtext';
					else if(obj.pctMarkChange == 0)
						className='padtext color3';
					else if (obj.pctMarkChange < 0)
						className='padtext color1';
					else
						className='padtext color2';
					
					if(obj.pctPriceChange == null)
						nextClassName='padtext';
					else if(obj.pctPriceChange  == 0)
						nextClassName='padtext color3';
					else if (obj.pctPriceChange < 0)
						nextClassName='padtext color1';
					else
						nextClassName='padtext color2';
					
					strContent +='<tr>'+
					'<td align="center" nowrap="nowrap">'+
						'<span class="padtext bold2">'+obj.symbol+'</span></div>'+
					'</td>'+
					'<td align="center"><span class="'+className+'"><div align="right" style="width: 20px">'+obj.mark+'</div></span></td>'+
					'<td align="center"><span class="'+nextClassName+'"><div align="right" style="width: 40px">'+obj.closePrice+'</div></span></td>'+
					'<td align="center"><span class="padtext"><div align="right" style="width: 20px">'+obj.markB4days+'</div></span></td>'+
					'</tr>';
				});
			}
				strContent += '</tbody>'+
					'</table>'+
						'</div>'+
						'<div class="bottom_inner clearfix">'+
						'<div class="left fl"></div>'+
						'<div class="right fr"></div>'+
					'</div>'+
				'</div>';				
			
			sumarizeContent = '<b>' + firstSumarizePart + ' ' + data.model.mostRecentDate + ' ' + secondSumarizePart
								+ ' ' + data.model.levelPrs.length
								+'/'+data.model.todayRatedCodeNumber + ' ' + thirdSumarizePart
								+ ' ' + getLevelLabel(data.model.level) + '</b>';
			
			content.html(strContent);
			divSumarizeInfo.html(sumarizeContent);
			} catch(e) {
			alert(e);
		}
	}	
};

function getLevelLabel(level){
	if(level == 0)
		return '8-10';
	else if(level == 1)
		return '5-7';
	else if(level == 2)
		return '1-4';
	else if(level == 3)
		return '(0,-3)';
	else if(level == 4)
		return '(-4,-6)';
	else 
		return '(-7,-10)';
	
}



