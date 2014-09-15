var _marketOverviewClazzHome = new MarketOverviewClazzHome();

$().ready(function() {
    //Populate Market Overview
	_marketOverviewClazzHome.loadMarketOverviewNews();
	//populate world index: Dow Jones, Nasdaq, and S&P 500
	//_marketOverviewClazzHome.loadWorldMarketOverviewNews();
/*		
	// create ape client
	client = new APE.Client();    
    client.load();
    client.addEvent('load', function() {
    	client.core.start();
  	});

	// connect to channel
	client.addEvent('ready', function() {	  
	    //console.log('Your client is now connected');
	    client.core.join('VNIndex');
	    client.core.join('HNX');	    
	    client.core.join('UPCOM');
	});
	
	//Display data which server returns.
	client.onRaw('data', function(raw, pipe) {	    
	    displayData(raw.data);
	    setTimeout(function(){removeHightline(raw.data.symbol); raw=null;}, 1000);
	});
*/
});

function displayData(data) {
	var id = data.symbol;
	var symbolImage = '';
    var symbolValue = '';
    var spanId = id + 'Span';
    
	if (data.chgIndex > 0) {
    	symbolImage = '<span class="Title-ck">' + id + '<br /><img src="' + $.web_resource.getContext() + '/images/front/ico_green.gif" class="set-img" /></span>';
    	symbolValue = '<span id="' + spanId + '" style="padding-left:15px;background-color: yellow;"><b class="color2">' + data.currentIndex + '<br />' + data.chgIndex + ' (' + $.web_utils.fomatNumberWithScale(data.pctIndex, 2) + '%)</b></span>';
    } else {
    	symbolImage = '<span class="Title-ck">' + id + '<br /><img src="'+ $.web_resource.getContext() +'/images/front/ico_red.gif" class="set-img" /></span>';
    	symbolValue = '<span id="' + spanId + '" style="padding-left:15px;background-color: yellow;"><b class="color1">' + data.currentIndex + '<br />' + data.chgIndex + ' (' + $.web_utils.fomatNumberWithScale(data.pctIndex, 2) + '%)</b></span>';
    }
    //console.log('symbolImage : ' + symbolImage);
    //console.log('symbolValue : ' + symbolValue);
      
    $("#"+id+"Image").html(symbolImage);
    $("#"+id+"Value").html(symbolValue);		
}

function removeHightline(id) {
	var spanId = id + 'Span';
	$("#" + spanId).attr("style", "padding-left:5px;");	
}