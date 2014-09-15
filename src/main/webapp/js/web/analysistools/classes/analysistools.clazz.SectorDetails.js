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
function SectorDetails() {
	
}

/*************************************************************************************
* Initialization getOption
**************************************************************************************/
SectorDetails.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
SectorDetails.prototype.loadImage = function () {
	$("#SectorDetails table tbody").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* Populate Daily News
**************************************************************************************/
SectorDetails.prototype.populateCompanyData = function (data) {
	if (data.model) {
		try{
			var tbody = $("#company table tbody");
			tbody.empty();
			var owner = this;
			$.each(data.model.searchResult, function(i, item){
				tbody.append(
						'<tr>' +
							'<td width="">' +
								'<span class="txtText">' + item.companyName + '</span>' +
							'</td>' +
							'<td width="72" align="right">' +
								'<span class="txtText">??</span>' +
							'</td>' +
							'<td width="115" align="right" style="padding-right: 7px">' +
								'<span class="txtText green">??</span>' +
							'</td>' +
							'<td width="75" align="right">' +
								'<span class="txtText">??</span>' +
							'</td>' +
							'<td width="110" align="right">' +
								'<span class="txtText">??</span>' +
							'</td>' +
							'<td width="90" class="col_end" align="right" style="padding-right: 5px">' +
								'<span class="txtText">??</span>' +
							'</td>' +
						'</tr>'
				);
			})
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'companyNav'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
