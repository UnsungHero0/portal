var page = 1;

$(document).ready(function() {
	var totalPage = $('#analysis-news-totalPage').val();
	if(page == totalPage || totalPage == 0){
		$('.barLoadMore').css("display", "none");
	}
	var d = new Date();
	var currentyear = d.getFullYear();
	//+++Date
    $('#fHistoricalPrice_FromDate').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: "1930:" + currentyear,
        onSelect: function (){
        	if($('#fHistoricalPrice_ToDate').val() != ""){
        		document.ttptNews_SearchNews.submit();
        	}
        	return false;
        },
        onClose: function (){return false;}
    });
	
    $('#fHistoricalPrice_ToDate').datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: "1930:" + currentyear,
        onSelect: function (){
        	if($('#fHistoricalPrice_FromDate').val() != ""){
        		document.ttptNews_SearchNews.submit();
        	}
        	return false;
        },
        onClose: function (){return false;}
    });
    
    // Load most viewed news - right content
    loadMostViewedNews_TTPT($('#analysis-news-newsType').val());
    
    // load more button action
    $("#loadMore").click(function(){
        page++;
        var totalPage = $('#analysis-news-totalPage').val();
        var newsType = $('#analysis-news-newsType').val();
        var strFromDate = $('#fHistoricalPrice_FromDate').val();
        var strToDate = $('#fHistoricalPrice_ToDate').val();
        if(page <= totalPage){
	        $.ajax({
	            type: "POST",
	            dataType: "json",
	            data: "currentIndex="+page+"&newsType="+newsType+"&strFromDate="+strFromDate+"&strToDate="+strToDate,
	            url: URL_LOAD_MORE_NEWS,
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
	        if(page == totalPage){
	        	$('.barLoadMore').css("display", "none");
	        }
        } 
        
        return false;
    });
});

function loadMostViewedNews_TTPT(newsType){
	var url = $.web_resource.getContext() + "ajax/news/getMostViewedNewsAjax.shtml?newsType=" + newsType;
	$('#ttpt-mostViewedNews-box').load(url);
}
