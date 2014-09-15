var _homeWorldIndex = new HomeWorldIndex();

var metadata;

$().ready(function() {
	var $tabs2 = $('#worldindex-container').tabs({
	   select: function(event, ui) {
		  metadata = $(ui.tab).metadata();
		  _homeWorldIndex.loadData(metadata);
	   }
	});

	var selectedIdx = $tabs2.tabs('option', 'selected'); // => 0
	if(selectedIdx == 0) {
		metadata = $('#worldindex-container').find("a:first").metadata();
		_homeWorldIndex.loadData(metadata);//This is a tricky :(
	} else {
		$tabs2.tabs('select', 0); // switch to first tab
		//$tabs2.tabs('select', selectedIdx); // switch selected tab
	}
});

/*************************************************************************************
* @author
*
* @public: OscClazzNewContent Class
*
*************************************************************************************/
function HomeWorldIndex() {

}

/*************************************************************************************
* Load Image
**************************************************************************************/
HomeWorldIndex.prototype.loadImage = function (div) {
	$(div).empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
};

/*************************************************************************************
* Load Data
**************************************************************************************/
HomeWorldIndex.prototype.loadData = function (metadata) {
	var _this = this;

	 var params = [
  	               	{name : 'market', value : metadata.market}
  	              ];
	 var action = {div : metadata.div, navigator : metadata.navigator};
	$.ajax({
		   type: "POST",
		   url: eval(metadata.url),
		   data: params,
		   dataType: "json",
		   success: function(data) {
				_this.populateData(data, action);
			},
		   beforeSend: function(){
				_this.loadImage(action.div);
		   }
	});
};

/*************************************************************************************
* Populate Data
**************************************************************************************/
HomeWorldIndex.prototype.populateData = function (responseText, options) {
	if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {
        $.web_message.error(null, responseText.error);
        //+++ update market overview news
	 } else {
		 try{
		 	var result = responseText.model.lstWorldQuote;
			var div = $(options.div).empty();
			var owner = this;
			var length = result.length;

			var strContent = '<table width="100%" class="small_table" cellpadding="0" cellspacing="0" border="0">';
			/*
	   		 strContent +='<tr><td>&nbsp;</td>';
	   		 strContent +='<td><b><span>' + WORLD_INDEX_COL1 + '</span></b></td>';
	   	     strContent +='<td><b><span>' + WORLD_INDEX_COL2 + '</span></b></td>';
	   		 strContent +='<td><b class="fr"><span>' + WORLD_INDEX_COL3 + '</span></b></td>';
	         strContent +='</tr>';
	         */
	         $.each(result, function(i, market){
	        	 strContent += '<tr><td class="col1" style="font-weight: bold;">'+ market.name + '</td>';
          		 if (market.chgIdx > 0) {
              		 strContent += '<td class="col1"><span class="color2" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.index, 2) + '</span></td>';
              		 strContent += '<td class="col1" nowrap="nowrap" ><img src="' + $.web_resource.getContext() + '/images/front/up.gif" hspace="5" /><span class="color2" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.chgIdx, 2) + '</span></td>';
                    strContent += '<td class="col1" nowrap="nowrap"><span class="fr color2" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.pctIdx, 2) + '%</span></td>';
              	 } else if (market.chgIdx < 0) {
              		 strContent += '<td class="col1"><span class="color1" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.index, 2) + '</span></td>';
              		 strContent += '<td class="col1" nowrap="nowrap"><img src="' + $.web_resource.getContext() + '/images/front/down.gif" hspace="5" /><span class="color1" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.chgIdx, 2) + '</span></td>';
                    strContent += '<td class="col1" nowrap="nowrap"><span class="fr color1" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.pctIdx, 2) + '%</span></td>';
              	 } else {
              		 strContent += '<td class="col1"><span class="color3" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.index, 2) + '</span></td>';
              		 strContent += '<td class="col1" nowrap="nowrap"><img src="' + $.web_resource.getContext() + '/images/front/bang.gif" hspace="5" /><span class="color3" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.chgIdx, 2) + '</span></td>';
                    strContent += '<td class="col1" nowrap="nowrap"><span class="fr color3" style="font-weight: bold;">' + $.web_utils.fomatNumberWithScale(market.pctIdx, 2) + '%</span></td>';
              	 }
          		 strContent += '</tr>';
	         });

	         strContent += '</table>';
	         div.append(strContent);
		 }catch(e) {
			alert(e);
		}
	 }
};