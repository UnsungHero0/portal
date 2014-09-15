/*************************************************************************************
* @author  HuyLV
*
* Define newContentOption Object
* using the flowing query to extend newContentOption
*
* jQuery.extend(jQuery.newContentOption, {
* });
*
*************************************************************************************/

(function($) {
$.listSectorOption = function() {};
$.extend($.listSectorOption, {
	loading 					: "#progress_loading_img",
	form : {
		fields 	: {
			searchSymbol		: "#searchSymbolId"
			}
	},
	buttons 		: {
		viewIndustry			: "#viewIndustry"
	},
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
function OscClazzListSector() {

}

/*************************************************************************************
* init getOption
**************************************************************************************/
OscClazzListSector.prototype.getOption = function () {
	return $.listSectorOption;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
OscClazzListSector.prototype.loadImage = function () {
	$($.listSectorOption.table.content + " table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
OscClazzListSector.prototype.populateSector = function(data) {
	if (data.model) {
		try{
			$("#navigator").hide();
			var tbody = $($.listSectorOption.table.content + " table tbody");
			tbody.empty();
			var owner = this;
			var className = '';
			$.each(data.model.searchResult, function(i, item){
				if (i % 2 == 1) {
					className = 'thirstrow trBackground';
				} else {
					className = 'thirstrow';
				}
				tbody.append(
					'<tr class="' + className + '">' +
						'<td width="107" style="border-right:1px solid #b0b0b0">' +
						'<span class="txtText"><b><a href="javascript:void(0)" class="industry {sectorCode : \'' + item.sectorCode + '\'}">' + item.sectorName + '</a></b></span>' +
						'</td>' +
						owner.getStatisticRow(item) +
					'</tr>');
			})
		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* populateIndustries functionality
**************************************************************************************/
OscClazzListSector.prototype.populateIndustries = function(data) {
	if (data.model) {
		try{
			$("#navigator").hide();
			var tbody = $($.listSectorOption.table.content + " table tbody");
			tbody.empty();
			var owner = this;
			//data for a sector
			tbody.append(
					'<tr class="thirstrow">' +
						'<td  width="107" style="border-right:1px solid #b0b0b0">' +
							'<b>' + SECTOR + ':</b><br>' + data.model.ifoSectorCalcView.sectorName +
						'</td>' +
						owner.getStatisticRow(data.model.ifoSectorCalcView) +
					"</tr>" +
					'<tr class="thirstrow">' +
						'<td colspan="14" align="center"><b>' + INDUSTRIES + '</b></td>' +
					'</tr>'
			);
			var className = '';
			//detail data for a sector
			$.each(data.model.searchResult, function(i, item){
				if (i % 2 == 1) {
					className = 'thirstrow trBackground';
				} else {
					className = 'thirstrow';
				}
				tbody.append(
					'<tr class="' + className + '">' +
						'<td width="107" style="border-right:1px solid #b0b0b0">' +
							'<b><a href="javascript:void(0)" class="company {industryCode : \'' + item.industryCode + '\', sectorCode : \'' + data.model.ifoSectorCalcView.sectorCode + '\'}">' + item.industryName + '</a></b>' +
						'</td>' +
						owner.getStatisticRow(item) +
					'</tr>');
			})

		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* populateCompanies functionality
**************************************************************************************/
OscClazzListSector.prototype.populateCompanies = function(data) {
	if (data.model) {
		try{
			var tbody = $($.listSectorOption.table.content + " table tbody");
			tbody.empty();

			var owner = this;
			//data for a sector
			tbody.append(
					'<tr class="thirstrow">' +
						'<td  width="107" style="border-right:1px solid #b0b0b0">' +
							'<b>' + SECTOR + ':</b><br>' +
							'<a href="javascript:void(0);" class="industry {sectorCode : \'' + data.model.ifoSectorCalcView.sectorCode + '\'}">' +
									data.model.ifoSectorCalcView.sectorName +
							'</a>' +
						'</td>' +
						owner.getStatisticRow(data.model.ifoSectorCalcView) +
					"</tr>" +
					'<tr class="thirstrow">' +
						'<td  width="107" style="border-right:1px solid #b0b0b0">' +
							'<b>' + INDUSTRIES + ':</b> <br>' +
							data.model.ifoIndustryCalcView.industryName +
						'</td>' +
						owner.getStatisticRow(data.model.ifoIndustryCalcView) +
					'</tr>' +
					'<tr class="thirstrow">' +
						'<td colspan="14" align="center"><b>' + COMPANIES + '</b></td>' +
					'</tr>'
			);

			//add class for the link above the table
			$("#navigator_sectorName").attr("class", 'industry {sectorCode : \'' + data.model.ifoSectorCalcView.sectorCode + '\'}');
			$("#navigator_sectorName").text(data.model.ifoSectorCalcView.sectorName);
			$("#navigator_industryName").text(data.model.ifoIndustryCalcView.industryName);
			$("#navigator").show();

			//detail data for a sector
			var className = '';
			$.each(data.model.searchResult, function(i, item){
				if (i % 2 == 1) {
					className = 'thirstrow trBackground';
				} else {
					className = 'thirstrow';
				}
				
				tbody.append(
					'<tr class="' + className + '">' +
						'<td width="107" style="border-right:1px solid #b0b0b0">' +
							'<b><a href="'+URL_STOCK_INFO + item.secCode.toLowerCase() + '.shtml">'+item.secCode+'</a></b>' +
						'</td>' +
						owner.getStatisticRow(item) +
					'</tr>');
			})

		}catch(e) {
			alert(e);
		}
	}
};

/*************************************************************************************
* Format number
**************************************************************************************/
OscClazzListSector.prototype.formatNumber = function (number) {
	return (number != 0 ? $.web_utils.fomatDouble(number, 2) : "N/A");
};


/*************************************************************************************
* Format number
**************************************************************************************/
OscClazzListSector.prototype.getStatisticRow = function (item) {
	var sResult =
		'<td width="40" align="right">' + this.formatNumber(item.pe) + '</td>' +
		'<td width="40" align="right" style="border-left:1px solid #b0b0b0;border-right:1px solid #b0b0b0;">' + this.formatNumber(item.pb) + '</td>' +
		'<td width="60" align="right">' + this.formatNumber(item.scopeMaketCap) + '</td>' +
		'<td width="75" align="right" style="border-left:1px solid #b0b0b0">' + this.formatNumber(item.scopeAsset) + '</td>' +
		'<td width="67" align="right" style="border-left:1px solid #b0b0b0; border-right:1px solid #b0b0b0">' + this.formatNumber(item.scopeEquity) + '</td>' +
		'<td width="50" align="right">' + this.formatNumber(100*item.growthAsset) + '</td>' +
		'<td width="40" align="right" style="border-right:1px solid #b0b0b0; border-left:1px solid #b0b0b0">' + this.formatNumber(100*item.growthRevenue) + '</td>' +
		'<td width="43" align="right">' + this.formatNumber(100*item.roa) + '</td>' +
		'<td width="42" align="right" style="border-left:1px solid #b0b0b0">' + this.formatNumber(100*item.roe) + '</td>' +
		'<td width="38" align="right" style="border-right:1px solid #b0b0b0; border-left:1px solid #b0b0b0">' + this.formatNumber(100*item.profitMargin) + '</td>' +
		'<td width="35" align="right" >' + this.formatNumber(item.debtEquity) + '</td>' +
		'<td width="42" align="right" style="border-left:1px solid #b0b0b0">' + this.formatNumber(item.currentRatio) + '</td>' +
		'<td width="70" align="right" style="border-left:1px solid #b0b0b0">' + this.formatNumber(item.ebitda) + '</td>';
	return sResult;
};

/*************************************************************************************
* Format number
**************************************************************************************/
OscClazzListSector.prototype.toString = function () {
	return "OscClazzListSector";
};