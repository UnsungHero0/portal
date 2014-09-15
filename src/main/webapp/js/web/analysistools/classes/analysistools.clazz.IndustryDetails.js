/*************************************************************************************
* @author  HuyLV
*
* Define report Object
* using the flowing query to extend reportOption
*
* jQuery.extend(jQuery.reportOption, {
* });
*
*************************************************************************************/

(function($) {
$.options = function() {};
$.extend($.options, {
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: SectorDetails Class
*
*************************************************************************************/
function IndustryDetails() {
	
}

/*************************************************************************************
* Initialization getOption
**************************************************************************************/
IndustryDetails.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
IndustryDetails.prototype.loadImage = function () {
	$("#company table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
IndustryDetails.prototype.populateCompanyData = function (data) {
	if (data.model) {
		try{
			var tbody = $("#company table tbody");
			tbody.empty();
			var owner = this;
			$.each(data.model.companiesCombo, function(i, item){
				tbody.append(
						'<tr>' +
							'<td width="">' +								
								'<span class="txtText bluetext"><a href="' + URL_STOCK_INFO + item.secCode + '.shtml">' + item.companyName + ' (' + item.secCode + ')' + '</a>' + '</span>' +
							'</td>' +
							'<td width="72" align="right">' +
								'<span class="txtText">' + $.web_utils.fomatDouble(item.closePrice) + '</span>' +
							'</td>' +
							'<td width="115" align="right" style="padding-right: 7px">' +
								'<span class="' + (item.chgIndex < 0 ? "vl_3month cl_red" : "vl_3month") + '">' + 
									(item.chgIndex > 0 ? "+" + $.web_utils.fomatDouble(item.chgIndex) : (item.chgIndex == 0 ? "0" : $.web_utils.fomatDouble(item.chgIndex))) +
									"(" + (item.pctIndex > 0 ? "+" + $.web_utils.fomatDouble(item.pctIndex) : (item.pctIndex == 0 ? "0" : $.web_utils.fomatDouble(item.pctIndex)))    + "%)" +
								'</span>' +
							'</td>' +
							'<td width="75" align="right">' +
								'<span class="txtText">' + $.web_utils.fomatLong(item.marketCap) + '</span>' +
							'</td>' +
							'<td width="110" align="right">' +
								'<span class="txtText">' + $.web_utils.fomatDouble(item.revenueGrowthAnnual) + '</span>' +
							'</td>' +
							'<td width="90" class="col_end" align="right" style="padding-right: 5px">' +
								'<span class="txtText">' + $.web_utils.fomatLong(item.income) + '</span>' +
							'</td>' +
						'</tr>'
				);
			})
			
			//paging
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'companyNav'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
			
			//records information
			var from = data.model.pagingInfo.index + 1;
			var to = from + data.model.companiesCombo.length - 1;
			var total = data.model.pagingInfo.total;
			$('#compRecordInfo').html($.web_utils.format(COMP_RECORD_INFO, from, to, total));
		}catch(e) {
			alert(e);
		}
	}
};
