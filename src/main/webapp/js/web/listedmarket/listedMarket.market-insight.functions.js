$(document).ready(function(){
});

/**
 * Box Chiến lược đầu tư tuần
 */
function getWeeklyInvestmentStrategy(page){
	var formFields = {
		"type" : "Research",
		"pagingInfo.indexPage": page,
		"pagingInfo.offset" : 3
	};
    var options = {
         action : $.web_resource.getContext() + "ajax/news/News_GetCenterAnalysisOfVNDirect.shtml",
         callbackPostSubmit : function (responseText, statusText) {
             try {
            	 if(responseText.error !== null && responseText.error !== 'undefined' && responseText.error.hasErrors) {
                     $.web_message.error(null, responseText.error);
            	 } else {
            		 $('#weeklyInvestmentStrategy').html('<span class="ajaxLoadingIcon"></span>');
            		 var model = responseText.model;
            		 var strContent = "";
	                 if(model != null && model.searchResult != null && model.searchResult != null) {
	                	 strContent = "<ul>";
	                	 $.each(model.searchResult, function(i, newsinfo){
		                	 for (i = 0; i < newsinfo.pdfFileList.length; i++) {
		                		 var attId = newsinfo.attachmentsId[i];
			                	 var fileName = newsinfo.fileNames[i];
			                	 var pdfFile = newsinfo.pdfFileList[i];
			                	 
			                	 strContent += '<li><span class="newsHeader"><a href="javascript:download(\'' + pdfFile;
		                		 strContent += '\',\'newsAttach\',\'' + fileName  +'\',\''+ attId + '\')">' + newsinfo.newsHeader + '</a></span>';

		                		 strContent += '<span class="newsDownload"><a href="javascript:download(\'' + pdfFile;
		                		 strContent += '\',\'newsAttach\',\'' + fileName  +'\',\''+ attId + '\')"><img class="left" src="' 
		                		 				+ $.web_resource.getContext() + 'images/icons/icon_pdf.png" />' + 'Download' + '</a></span>';
			                	 				                	 
			                	 strContent += '<div class="clear"></div></li>';
		                	 }
	         			});
	                	strContent += '<li><div id="web_navWeeklyInvestmentStrategy" class="right"></div><div class="clear"></div></li>';
	                	strContent += '</ul>';
                     }
	                 $('#weeklyInvestmentStrategy').html(strContent);
	                 $("#weeklyInvestmentStrategy li:last-child").css("border","none");
	                 
	                 // generate paging button
	                 var funcOptions = {
	                     goto_func: 'changeWeeklyInvestmentStrategy'
	         		 };
         			 var idOptions = {
                         navContainer : "web_navWeeklyInvestmentStrategy"
         			 };
	         		 $.web_paging.showNewsItem(model.pagingInfo, funcOptions, idOptions);
            	 }
             } catch (e) {
                 alert(e);
             }
         }
	 };
	 $.web_formAways.ajaxNav(formFields, options);
}
/**
 * callback function for paging
 * @param navigator
 * @param page
 */
function changeWeeklyInvestmentStrategy(navigator, page){
	getWeeklyInvestmentStrategy(page);
}