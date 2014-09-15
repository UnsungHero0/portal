/*************************************************************************************
* @author HuyLV
*
* Define newContentOption Object
* using the flowing query to extend newContentOption
*
* jQuery.extend(jQuery.newContentOption, {
* });
*
*************************************************************************************/

(function($) {
$.listSectorIndexOption = function() {};
$.extend($.listSectorIndexOption, {
	loading 					: "#progress_loading_img",
 	table 			: {
 		content					: "#listOfSectors"
 	}
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: OscClazzNewContent Class
*
*************************************************************************************/
function OscClazzListSectorIndex() {
	
}

/*************************************************************************************
* init getOption
**************************************************************************************/
OscClazzListSectorIndex.prototype.getOption = function () {
	return $.listSectorIndexOption;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
OscClazzListSectorIndex.prototype.loadImage = function () {
	$($.listSectorIndexOption.table.content + " table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Display Type
**************************************************************************************/
OscClazzListSectorIndex.prototype.populateDataIndex = function (data) {
	var bySector = SECTOR;
	var byAlphabetical = ALPHABETICAL;
	try{
		if (data.model) {
			var model = data.model;
			//display sorting options
			var outer = this;
						
			
			if (model.industries1) {
				var tbody = $($.listSectorIndexOption.table.content + " table tbody");
				tbody.empty();
				//display by sector name
				if (model.ifoIndustryCalcView.sortField == bySector) {
					$('#industriesL').append(outer.displaySectorColumn(model.industries1));
					$('#industriesC').append(outer.displaySectorColumn(model.industries2));
					$('#industriesR').append(outer.displaySectorColumn(model.industries3));
					
//					tbody.append(
//						'<tr>' + '<td width="3%"></td>' + outer.displaySectorColumn(model.industries1) + '<td width="3%"></td>' + outer.displaySectorColumn(model.industries2) + '</tr>'
//					);					
				}
			}					
		}
	}
	catch(e) {
		alert(e);
	}
};

/*************************************************************************************
* Format number
**************************************************************************************/
OscClazzListSectorIndex.prototype.displaySectorColumn = function(data) {
	var sResult = '<td valign="top" width="42%">';
	var sectorName = '';
	var eoi = true; //end of item
	var subItem = '';
	$.each(data, function(i, item) {
		
		if (!eoi && sectorName != item.sectorName) {
			subItem += '</ol>';
			eoi = true;
		}
		
		if (eoi && sectorName != item.sectorName) {
			sResult += subItem;
			subItem = '<ol class="list"><li class="title"><a href="javascript:gotoSelector('+item.sectorCode+')">' + item.sectorName + '</a></li>';
			eoi = false;
		}
		
		subItem += '<li>' +
						'<a href="javascript:void(0)" class="viewIndustry {industryCode : \'' + item.industryCode + '\', sectorCode : \'' + item.sectorCode + '\'}">' + 
							item.industryName +
						'</a>' + 
					'</li>';
		sectorName = item.sectorName;
	});
	subItem = subItem.replace('FormGeneral', 'FormGeneral end');
	sResult += subItem;
	sResult += '</td>';
	return sResult;
};

/*************************************************************************************
* Format number
**************************************************************************************/
OscClazzListSectorIndex.prototype.displayAlphaColumn = function (data) {
	var sResult = "";
	sResult += '<table width="100%" border="0" cellspacing="0" cellpadding="0" class="sectorColumn">';
	
	$.each(data, function(i, item){
		sResult += 
			'<tr>' + 
				'<td>' + 
					'<span class="bluetext">' + 
						'<a href="javascript:void(0)" class="viewIndustry {industryCode : \'' + item.industryCode + '\', sectorCode : \'' + item.sectorCode + '\'}">' + 
							item.industryName +
						'</a>' +
					'</span>' +
				'</td>' +
			'</tr>'
	});
	sResult += '</table>';
	
	return sResult;
}





