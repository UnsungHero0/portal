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
$.options = function() {};
$.extend($.options, {
});
})(jQuery);

/*************************************************************************************
* @author 
*
* @public: StockScreenerSummary Class
*
*************************************************************************************/
function StockScreenerList() {
}

/*************************************************************************************
* init getOption
**************************************************************************************/
StockScreenerList.prototype.getOption = function () {
	return $.options;
};

/*************************************************************************************
* Load Image
**************************************************************************************/
StockScreenerList.prototype.loadImage = function () {
	$('#listOfStockScreener table tbody').empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />')
};

/*************************************************************************************
* PopulateSector functionality
**************************************************************************************/
StockScreenerList.prototype.buildListTable = function(data) {
	if (data.model) {
		try{
			var tbody = $('#listOfStockScreener table tbody').empty();
			var owner = this;
			$.each(data.model.criteria, function(i, item){
				tbody.append(
						'<tr>' +
							'<td class="rowtt">' + 
								'<a href="javascript:void(0)" class="modify {id : \'' + item.saveSearchId + '\'}">' + 
									item.saveSearchName +
								'</a>' + 
							'</td>' + 
							'<td class="rowcompany_2" style="">' + 
								'<a style="color: #F39200" href="javascript:void(0)" class="history {id : \'' + item.saveSearchId +'\'}">' +
									item.numberOfMatchedItem + 
								'</a>' + 
							'</td>' + 
							'<td class="rowtime">' + 
									$.web_utils.dateFormat2Show(item.registerDated, 'dd/mm/yyyy') +
							'</td>' +  
							'<td class="row_xoa_edit">' +
									'<a href="javascript:void(0)" class="modify {id : \'' + item.saveSearchId + '\'}">' +
										EDIT +
									'</a>' + 
									'<a href="javascript:void(0)" class="right delete {id : \'' + item.saveSearchId +'\'}">' +
										'<img src="'+WEB_CONTEXT+'/images/icons/icon_delete.png"/>'+
										DELETE +
									'</a>' + 
							'</td>' + 
						'</tr>'
				);
			})
			
			var funcOptions = {
                goto_func: 'changePage'
			};
			var idOptions = {
                navContainer : 'listOfStockScreenerNavigator'
			};
			$.web_paging.showItem(data.model.pagingInfo, funcOptions, idOptions);
		}catch(e) {
			alert(e);
		}
	}
};
