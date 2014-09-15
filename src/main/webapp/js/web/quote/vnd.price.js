/************************** VNDPRICE SERVICE **************************************
// depends on jQuery
// @author: Chau
**********************************************************************************/
var vndPrice = new Object();


vndPrice.timeInterval = function(interval){
	if(interval != null)
		vndPrice._interval = interval;
	return this._interval;
}



vndPrice._processReloadEvent = function(zdata){
	var data = zdata.data;
	$.each(data, function(i, idata){
		var id = idata.id;
		var eType = '';
		switch (id){
			case 1101: // FullSnapShot
				eType = 'IndexUpdate';
				break;
			case 2100:
				eType = 'FullSnapShot';
				break;
		}
		
		
		if(eType != ''){
			if(vndPrice._events[eType] != undefined){
				vndPrice._events[eType](idata);
			}
		}	
	
	});
}

vndPrice._processGetChangeEvent = function(zdata){
	var data = vndPrice._filterServerData(zdata);
	
	if (data.length > 0){
		$.each(data, function(i, idata){
			id = idata.id;							
			var eType = ''; // event type
			switch (id){
				case 1101: // IndexUpdate
					eType = 'IndexUpdate';					
					break;
				case 2100: // FullSnapShot
					eType = 'FullSnapShot';
					break;
				case 3210: // TP								
					eType = 'TP';
					break;
				case 3220: // LS								
					eType = 'LS';
					break;	
				case 3250: // TR
					eType = 'TR';
					break;
				default:
					}
			
			if(eType != ''){
				if(vndPrice._events[eType] != undefined){
					vndPrice._events[eType](idata);
				}
			}			
					
		});
	}	
}

// process update from server 
// channel out update for stocks or update for index
vndPrice._processServerUpdate = function (zdata){

	if(zdata.id == 9999){
		// fire a event to reload the data
		vndPrice.reLoad(function(zdata){
			vndPrice._processReloadEvent(zdata);
		}, function(){
			console.error("Error reload data");
		});
		return;
	}	
	
	vndPrice._processGetChangeEvent(zdata);
}

// filter the server so that it contains only latest update
// each object in zdata.data contain a field 'id'
// assume that the later object will the most updated
// for example: an array of {{id : 100, price : 10}, {id : 100, price : 12}}
// will be filtered out to only {{id : 100, price : 12}}
// NOTE that server will return the server time as well but here we don't care about that
vndPrice._filterServerData = function(zdata){
	var idTable = new Object();
	var output = zdata.data;
	for(var i = output.length - 1; i >=0; i--){
		if(output[i].id == undefined){
			throw "vndPrice._filterServerData: Invalid Input";
		}
		
		if(idTable[output[i].id] != undefined){
			output.splice(i,1);
		}
		else{
			idTable[output[i].id] = true;
		}
	}
	
	return output;
}

vndPrice.baseLink = function(baseLink){	
	if(baseLink != null)
		this._baseLink = baseLink;
	return this._baseLink;
}

vndPrice._isStockInList = function(symbol, requestedStocks){
	for(var i = 0; i < requestedStocks.length; i++){	
		if(requestedStocks[i] == symbol)
			return true;
	}
	return false;
}

vndPrice.reLoad = function(callback, errorcallback){
	if(vndPrice._vStockList == undefined){
		throw "vndPrice.reLoad(): _vStockList is undefined";
	}
	
	vndPrice._reLoadList(vndPrice._vStockList, callback, errorcallback);
}



vndPrice._reLoadList = function(list, callback, errorcallback){	 
	var success = false;

	$.getJSON(this._baseLink,{
			msgType : "loadFull",
			tradingCenter : "",
			listCode : list
		},function(zdata){
			success = true;
			vndPrice._serverSeq = zdata.seq;
			if(zdata != undefined && callback != undefined){							
				callback(zdata);
			}
			else{
				if(errorcallback != undefined){
					errorcallback("vndPrice.reLoad: invalid error return");
				}
			}
		});
		
		setTimeout(function() {
			if (!success)
			{
				// Handle error accordingly				
				if(errorcallback != null){
					errorcallback("vndPrice.reLoad: error getJSON");
				}
			}
		}, 2000);
		
		
}

// load a list of stock
vndPrice._loadChange = function(seq, floorCode, callback, errorCallback){

	var success = false;
		
	$.getJSON(this._baseLink,{
		msgType : "loadChange",
		listShare : vndPrice._vStockList,
		tradingCenter: floorCode,
		sequence: seq
	},function(zdata){		
		vndPrice._serverSeq = zdata.seq;
		success = true;
		if(callback != undefined){
			callback(zdata);		
		}			
	},"jsonp");
	
	setTimeout(function() {
		if (!success)
		{
			// Handle error accordingly			
			if(errorCallback != undefined){
				errorCallback("vndPrice._loadChange: error getJSON");
			}
		}
	}, 2000);
}


// vndPrice.startLoadPrice = function(

// convert list of stock to correct format to send to server
vndPrice._register = function(stockList){
	var output = "";
	$.each(stockList, function(i, stock){
		output += stock.toUpperCase() + ",";
		///vndPrice._requestedStocks.push(stock);
	});	
	return output;
}

// register a list of stock
vndPrice.register = function(stockList){
	vndPrice._vStockList = vndPrice._register(stockList);
}
 

vndPrice._initReady = false; 
 
vndPrice.init = function(errorcallback){
	if(vndPrice._vStockList == undefined){
		throw "vndPrice.reLoad(): _vStockList is undefined";
	}
	
	vndPrice.reLoad(
		function(zdata){
			vndPrice._processReloadEvent(zdata);
			vndPrice._initReady = true;
		}, 	
		function(){
			console.error("Error initial reload");
			vndPrice._stopFlag = false; // will start anyway
			if(errorcallback != undefined){
				errorcallback();
			}
	});
}

vndPrice._initForTest = function(errorcallback){
	if(vndPrice._vStockList == undefined){
		throw "vndPrice.reLoad(): _vStockList is undefined";
	}
	
	vndPrice.reLoad(
		function(zdata){
			vndPrice._processReloadEvent(zdata);
			vndPrice._initReady = true;		
			vndPrice._serverSeq = vndPrice._serverSeq - 300;
		}, 	
		function(){
			console.error("Error initial reload");
			vndPrice._stopFlag = false; // will start anyway
			if(errorcallback != undefined){
				errorcallback();
			}
	});
}

// start listening to server
// assumming that list of stocks has already been registered by vndPrice.register()
vndPrice.start = function(){	
	vndPrice._stopFlag = false;
	vndPrice._startProcess();
}

vndPrice.stop = function(){
	vndPrice._stopFlag = true;
	vndPrice._initReady = false;
}

vndPrice._startProcess = function()
{
	if(vndPrice._stopFlag != true){
		setTimeout("vndPrice._startProcess()", vndPrice._interval);		
		vndPrice._doJob();
	}
}

// this function is used for testing purpose
// will set serverSeq = serverSeq - diff
vndPrice._rewind = function(diff){
	this._serverSeq = this._serverSeq - diff;
}

vndPrice._doJob = function(){
	// load change then process server update
	
	if(vndPrice._initReady){
		vndPrice._loadChange(vndPrice._serverSeq, '', vndPrice._processServerUpdate, 	
		function(){
			console.error("error unhandled");
		});
	}
}

// call this function to start price server
// and test callback
// this will init vndPrice, issue 3 calls to server to get all events happen within last 300 sequence
vndPrice.dryTest = function(){
	vndPrice._initForTest();
	vndPrice.start();	
}

// ============ EVENTS ============================
vndPrice._events = new Object();

// allow user to bind callback to vndPrice events
// possible event:
// - 'indexUpdate': an Update for an index
// - 'LS': update gia/volume
// - 'TP': update 3 gia (mua hoac ban)
// - 'IndexUpdate': update Index
// - 'TR': update gia tri mua ban nuoc ngoai
// other input will not do anything
vndPrice.bind = function(eType, callback){
	if(eType != 'IndexUpdate' && eType != 'FullSnapShot' && eType != 'LS' && eType != 'TP' && eType != 'TR'){
		return;
	}
	
	if(callback!=null){
		this._events[eType] = callback;
	}
}

// *******************************************************************************8
// default setup
vndPrice.timeInterval(500); // request set to 100 ms
vndPrice._stopFlag = true;
vndPrice._baseLink = "http://";
vndPrice._serverSeq = 0;
vndPrice._vStockList = "";

