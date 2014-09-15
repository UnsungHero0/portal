var _listedMarketClazzMostPopularSymbol = new ListedMarketClazzMostPopularSymbol();
var opts = _listedMarketClazzMostPopularSymbol.getOption();

$().ready(function() {
	// load MostPopularSymbol data
	_listedMarketClazzMostPopularSymbol.loadMostPopularSymbol();
	
});