$().ready(function() {
/*
	// create ape client
	client = new APE.Client();    
    client.load();
    client.addEvent('load', function() {
    	client.core.start();
  	});

	// connect to channel
	client.addEvent('ready', function() {	  
	    // console.log('Your client is now connected');
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
	var tdContent = '';    
    var td = id + '_td';
    var spanId = id + 'Span';
    $('#' + td).empty();    
    
	if (data.chgIndex > 0) {
		tdContent = '<span style="font-weight:bold; font-size:16px">' + id + '</span><img src="' + $.web_resource.getContext() + '/images/front/up_2.gif" /></span>';
		tdContent += '<span id="' + spanId + '" style="padding-left:15px;background-color: yellow;"><b class="color2">' + data.currentIndex + '&nbsp;' + data.chgIndex + ' (' + $.web_utils.fomatNumberWithScale(data.pctIndex, 2) + '%)</b></span>';
    } else {
    	tdContent = '<span style="font-weight:bold; font-size:16px">' + id + '</span><img src="' + $.web_resource.getContext() + '/images/front/down_2.gif" /></span>';
		tdContent += '<span id="' + spanId + '" style="padding-left:15px;background-color: yellow;"><b class="color1">' + data.currentIndex + '&nbsp;' + data.chgIndex + ' (' + $.web_utils.fomatNumberWithScale(data.pctIndex, 2) + '%)</b></span>';
    }
    //console.log('tdContent : ' + tdContent);
    $('#' + td).html(tdContent);		
}

function removeHightline(id) {
	var spanId = id + 'Span';
	$("#" + spanId).attr("style", "padding-left:5px;");	
}