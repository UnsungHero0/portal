var page = 1;
$(document).ready(function() {
	var totalPage = $('#totalPage').val();
	var total_Page = $('#analysis-news-totalPage').val();
	if(page == totalPage || totalPage == 0 || page == total_Page || total_Page == 0){
		$('.barLoadMore').css("display", "none");
	}
	var d = new Date();
	var currentyear = d.getFullYear();
	$('#fHistoricalPrice_FromDate').datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fHistoricalPrice_ToDate').val() != ""){
				document.vndirectNewsForm_SearchNews.submit();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});

	$('#fHistoricalPrice_ToDate').datepicker( {
		changeMonth : true,
		changeYear : true,
		yearRange: "1930:" + currentyear,
		onSelect : function() {
			if($('#fHistoricalPrice_FromDate').val() != ""){
				document.vndirectNewsForm_SearchNews.submit();
			}
			return false;
		},
		onClose : function() {
			return false;
		}
	});
	
	$("#loadMore").click(function(){
        page++;
        if(page <= $('#totalPage').val()){
	        $.ajax({
	            type: "POST",
	            dataType: "json",
	            data: "currentIndex="+page+"&strFromDate="+$('#strFromDate').val()+"&strToDate="+$('#strToDate').val(),
	            url: $.web_resource.getContext() + "ajax/loadMoreNews/vndirectNews.shtml",
	            success: function(responseText){        	        	   
	                var model = responseText.model;
	                var html  = "";
	                $.each(model.searchResult, function(i, obj){
	                	html += '<li class="content_list">';
	                	html += '<h2 class="title"><a href="'+obj.urlDetail+'">';              	
	                	html +=  obj.newsHeader;
	                    html += '</a></h2>';
	                	html += '<p class="time">'+obj.newsDateTimeStr+' | '+obj.newsDateStr+' <b> '+obj.newsResource+'</b></p>';
	                	if(obj.newsAbstract != null){
	                	   html += '<p>'+obj.newsAbstract+'</p>';
	                	}
	                	html += '</li>';
	                });
	                $('ul.listnew').append(html);
	                $("#loadingMoreImage").empty();
	            },
	            beforeSend: function() {
	            	$("#loadingMoreImage").empty().html('<img src="' + AJAX_IMAGE_LOADING + '" />');
	            }
	        });
	        if(page == $('#totalPage').val()){
	        	$('.barLoadMore').css("display", "none");
	        }
        }
        
        return false;
    });
});

//function _goTo(index) {
//	$('#pagingInfo_indexPage').val(index);
//	var url = "";
//	if (index == 1) {
//		url = $.web_resource.getContext() + "vndirect/tin-vndirect.shtml";
//	} else if (index > 1) {
//		url = $.web_resource.getContext() + "vndirect/tin-vndirect-" + index
//				+ ".shtml";
//	}
//	document.vndirectForm.action = url;
//	vndirectForm.submit();
//}