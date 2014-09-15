$(document).ready(function() {
	doZoomNewsDetail();
	loadMarketInsightMostViewNews();
});

function loadMarketInsightMostViewNews(){
	var url = $.web_resource.getContext() + "ajax/news/getMostViewMarketInsightNewsAjax.shtml";
	$('#analysis-marketInsight-news-box').load(url);
}