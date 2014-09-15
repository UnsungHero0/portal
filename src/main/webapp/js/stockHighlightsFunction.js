//store docUri
var DOCs = [];

// to store current news_id to get related reports
var NEWS_ID = "";

$(document).ready(function(){
});

function loadReports(tabname){
	if(tabname == 'newest'){
		$('#listReportsContent').load($.web_resource.getContext() + "ajax/stock-highlights/getNewestReports.shtml", function(){
			if($('#indexPage').val() < $('#total').val()){
				$('#loadMoreReportsBtn').show();
			}
		});
	} 
	else if(tabname == 'mostview'){
		$('#reportsTable').load($.web_resource.getContext() + "ajax/stock-highlights/getMostViewReports.shtml", function(){
			if($('#indexPage').val() < $('#total').val()){
				$('#loadMoreReportsBtn').show();
			}
		});
	}
	
	
}
function loadMoreReports(){
	var index = $('#indexPage').val();
    var total = $('#total').val();
    
    var loader = "<li class='ajaxLoadingIcon' style='padding:0'></li>";
    $('#listReportsContent').append(loader);

    if(index < total){
    	index ++;
        var url = $.web_resource.getContext() + 'ajax/stock-highlights/getNewestReports.shtml';
        $.ajax({
            url: url,
            data: "pagingInfo.indexPage=" + index,
            dataType: "html",
            success: function(data){
            	$('#listReportsContent .ajaxLoadingIcon').remove();
                $('#listReportsContent').append(data);
                $('#indexPage').val(index);
            }
        });
        
        if(index == total){
            $('#loadMoreReportsBtn').hide();
        }
    }
}

function loadMoreRelatedReports(){
	var index = $('#relate_indexPage').val();
    var total = $('#relate_total').val();

    if(index < total){
    	index ++;
        var url = $.web_resource.getContext() + 'ajax/stock-highlights/getRelatedReports.shtml';
        $.ajax({
            url: url,
            data: "pagingInfo.indexPage=" + index + "&newsId=" + NEWS_ID,
            dataType: "html",
            success: function(data){
                $('#relatedReportsData').append(data);
                $('#relate_indexPage').val(index);
            }
        });
        
        if(index == total){
            $('#loadMoreRelatedReportsBtn').hide();
        }
    }
}
function loadOutstandingReports(){
	// load outstanding reports
	$('.topSharesMain').load($.web_resource.getContext() + 'ajax/stock-highlights/getOutstandingReportsAjax.shtml');
}
function isAvailableSubmitVoteReport(){
	if($("#currentNewsHeader").val() != '' 
		     && $("#entry573537844").val() != ''
			 && $("#entry573537844").val() != 'Gửi bình luận/ góp ý về báo cáo'){

		return true;
	}
	
	return false;
}
function isAvailableSubmitVoteProduct(){
    if($("#entry2125613608").val() != ''
             && $("#entry2125613608").val() != 'Gửi bình luận/ góp ý về sản phẩm'){

        return true;
    }
    
    return false;
}
function loadListSymbolsHaveReport(){
	$('#listSymbolsHaveReports').load($.web_resource.getContext() + 'ajax/stock-highlights/getListSymbolsHaveReportsAjax.shtml');
}
function loadMenuOnReportView(){
	if($('.boxStockHot').css('display') == 'block'){
		closeMenuOnReportView();
	} else {
		$('.boxStockHot').show();
		loadListSymbolsHaveReport();
	}
}
function closeMenuOnReportView(){
	$('.boxStockHot').hide();
}
