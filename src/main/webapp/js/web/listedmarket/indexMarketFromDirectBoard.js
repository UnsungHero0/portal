/**
 * This function get market index from banggia (directboard).</br>
 * index = 10 : HOSE;
 * index = 11 : VN30;
 * index = 02 : HNX;
 * index = 12 : HNX30;
 * index = 03 : UPCOM;
 */
Config = {
	socket : {
		serverList : URL_SOCKET_SERVER.split(","),
		reconnectDelayTime : 10000
	}
};
var serverProvider = new ServerProvider(Config.socket.serverList);

function getIndexMarketFromDirectBoard(index){
	$.getJSON(
		BANG_GIA_LINK,
		{
			msgType : "loadFull",
		},
		function(data){
			$.each(data.data, function(i, idata){
				if(idata.mc == index){
					var cssClass = "textup";
					var sign = "+";
					if(idata.oIndex > idata.cIndex){
						sign = "-";
						cssClass = "textdow";
					} else if(idata.oIndex == idata.cIndex){
						sign = "";
						cssClass = "textnochange";
					}
					
					var obj = "#IndexMarket_" + index;
					$(obj).addClass(cssClass);
					$(obj + ' #currentIndex').html(idata.cIndex.toFixed(2));
					var ots = idata.ot.split('|');
					$(obj + ' #changeIndex').html(" " + sign + ots[0]);
					$(obj + ' #changeIndexInPercent').html(sign + " " + ots[1]);
				}
			});
		},
		"jsonp"
	);
}

function getMarketInfoFromDatabase(){
	var url = $.web_resource.getContext() + "getMarketInfo.shtml";
	var deffer = new $.Deferred();
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		success: function(responseText, statusText){
			try {
				if (responseText.error !== null
						&& responseText.error !== 'undefined'
						&& responseText.error.hasErrors) {
					//$.web_message.error(null, responseText.error);
				} else {
					var model = responseText.model;
					// HOSE
					if (model != null && model.hostcMarket != null) {
						renderMarketInfoFromDatbase(model.hostcMarket);
					}
					// VN30
					if (model != null && model.vn30Market != null) {
						renderMarketInfoFromDatbase(model.vn30Market);
					}
					// HNX30
					if (model != null && model.hnx30Market != null) {
						renderMarketInfoFromDatbase(model.hnx30Market);
					}
					// HNX
					if (model != null && model.hastcMarket != null) {
						renderMarketInfoFromDatbase(model.hastcMarket);
					}
					// Upcom		                 
					if (model != null && model.upComMarket != null) {
						renderMarketInfoFromDatbase(model.upComMarket);
					}
				}
				deffer.resolve();
			} catch (e) {
			}
		}
	});
	return deffer.promise();
}
var ajaxHelper = new AjaxHelper(serverProvider);
var sk = new SocketManager(serverProvider);
function getMarketInfoFromDirectBoard(index){
	ajaxHelper.request('priceservice/market/snapshot/q=codes:' + index, function (dataResponse) {
		var data = dataResponse[index].data;
		renderMarketInfo(data);
		sk.registConsumer({type: DataEventType.MARKETINFO, codes: [index]});
	});
	sk.start();
	sk.on("returnData", function(event) {
		if(event.message.name == DataEventType.MARKETINFO && Object.keys(event.message.data).length > 0){
			renderMarketInfo(event.message.data);
		}
	});
	sk.on(DataEventType.MARKETINFO, function(event) {
		renderMarketInfo(event.message);
	});
}

function renderMarketInfoFromDatbase(data){
	var cssClass = "textup";
	var sign = "+";
	if(data.chgIndex < 0){
		sign = "-";
		cssClass = "textdow";
	} else if(data.chgIndex == 0){
		sign = "";
		cssClass = "textnochange";
	}
	var obj = "#IndexMarket_" + data.floorCode;
	$(obj).removeClass("textup textdow textnochange");
	$(obj).addClass(cssClass);
	$(obj + ' #currentIndex').html($.web_utils.fomatNumberWithScale(data.currentIndex, 2));
	$(obj + ' #changeIndex').html(" " + sign +  $.web_utils.fomatNumberWithScale(Math.abs(data.chgIndex), 2));
	$(obj + ' #changeIndexInPercent').html(sign + " " +  $.web_utils.fomatNumberWithScale(Math.abs(data.pctIndex), 2) + "%");
}

function renderMarketInfo(data){
	var cssClass = "textup";
	var sign = "+";
	if(data.priorMarketIndex > data.marketIndex){
		sign = "-";
		cssClass = "textdow";
	} else if(data.priorMarketIndex == data.marketIndex){
		sign = "";
		cssClass = "textnochange";
	}
	var changeData = getChange( data.marketIndex,  data.priorMarketIndex);
	var obj = "#IndexMarket_" + data.floorCode;
	$(obj).removeClass("textup textdow textnochange");
	$(obj).addClass(cssClass);
	$(obj + ' #currentIndex').html($.web_utils.fomatNumberWithScale(data.marketIndex, 2));
	$(obj + ' #changeIndex').html(" " + sign +  Math.abs(changeData.changingIndex));
	$(obj + ' #changeIndexInPercent').html(sign + " " +  Math.abs(changeData.changingPercent) + "%");
}

function getTextIconSign(index, priorIndex) {
	var classChange = [];
	if(index > priorIndex){
		classChange.push("textup");
		classChange.push("icon-up");
		classChange.push("+");
	} else if(index < priorIndex){
		classChange.push("textdow");
		classChange.push("icon-dow");
		classChange.push("");
	} else {
		classChange.push("textnochange");
		classChange.push("icon-nochange");
		classChange.push("");
	}
	return classChange;
}

function getIdMarket(floorCode){
	if(floorCode === CONST_HSX_NAME){
		return "#hose";
	} else if(floorCode === CONST_HNX_NAME) {
		return "#hnx";
	} else if(floorCode === CONST_UPC_NAME) {
		return "#upcom";
	} else if(floorCode === CONST_VN30_NAME) {
		return "#vn30";
	} else{
		return "#hnx30";
	} 
}

function getChange(index, priorIndex) {
	var diffIndex = $.web_utils.fomatNumberWithScale(index - priorIndex, 2);
	var changeData = {
		changingIndex: diffIndex,
		changingPercent: $.web_utils.fomatNumberWithScale(diffIndex/priorIndex * 100, 2)
	};
	
	return changeData;
}