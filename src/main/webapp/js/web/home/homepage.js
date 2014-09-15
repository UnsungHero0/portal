var floorCodes = [CONST_HSX_NAME, CONST_HNX_NAME, CONST_UPC_NAME, CONST_VN30_NAME, CONST_HX30_NAME];
var ajaxHelper = new AjaxHelper(serverProvider);

$(document).ready(function(){
	getMarketInfo().done(loadRealTimeMarketInfo);
});

function loadRealTimeMarketInfo(){
	var sk = new SocketManager(serverProvider);
	var marketCodes = floorCodes.join(",");
	ajaxHelper.request('priceservice/market/snapshot/q=codes:' + marketCodes, function (data) {
		processInitAjaxData(data);
	});

	sk.on("returnData", function(event) {
	    processInitData(event.message.data);
	});
	sk.on("init", function(event) {
		sk.registConsumer({type: DataEventType.MARKETINFO, codes: floorCodes});
	});

	sk.on(DataEventType.MARKETINFO, function(event) {
		updateMarketInfo(event.message);
	});
	sk.start();
}
//Load du lieu nhanh hon truoc khi ket noi sockjs 
function processInitAjaxData(data){
	//TODO dung foreach
	for(var index = 0; index < floorCodes.length; index++){
		updateMarketInfo(data[floorCodes[index]].data);
	}
}

/*
 *  Update lai full du lieu
 *  Tranh truong hop thieu message luc sockjs chua ket noi
 */
function processInitData(data){
	for(var index = 0; index < floorCodes.length; index++){
		updateMarketInfo(data[floorCodes[index]].snapshot.data);
	}
}

function updateMarketInfo (marketInfo) {
	var marketId = getIdMarket(marketInfo.floorCode);
	var textIconSign = getTextIconSign(marketInfo.marketIndex, marketInfo.priorMarketIndex);
	var dataChange = getChange(marketInfo.marketIndex, marketInfo.priorMarketIndex);
	var classIcon = "print right " + textIconSign[1];
	$('.box_bangCK .line ' + marketId +' a').attr('class', textIconSign[0]);
	$('.box_bangCK .line ' + marketId + ' ' + marketId + 'Icon').attr('class', classIcon);
	$('.box_bangCK .line2 ' + marketId +' a').attr('class', textIconSign[0]);
	$('.box_bangCK .line2 ' + marketId +' #currentIndex').html($.web_utils.fomatNumberWithScale(marketInfo.marketIndex, 2));
	$('.box_bangCK .line2 ' + marketId +' #change').html(dataChange.changingIndex + " (" + dataChange.changingPercent + "%)");
	
	var obj = "#IndexMarket_" + marketInfo.floorCode;
	var textClass = textIconSign[0];
	$(obj).removeClass("textup textdow textnochange");
	$(obj).addClass(textClass);
	$(obj + ' #currentIndex').html($.web_utils.fomatNumberWithScale(marketInfo.marketIndex, 2));
	$(obj + ' #changeIndex').html(textIconSign[2]+dataChange.changingIndex);
	$(obj + ' #changeIndexInPercent').html(textIconSign[2]+dataChange.changingPercent + "%");
}

function renderInitMarketInfo(marketInfo){
	var marketId = getIdMarket(marketInfo.floorCode);
	var textClass = "";
	var iconClass = "";
	var sign = "";
	if(marketInfo.chgIndex > 0){
		textClass  = "textup";
		iconClass = "icon-up";
		sign = "+";
	} else if (marketInfo.chgIndex < 0) {
		textClass  = "textdow";
		iconClass = "icon-dow";
		sign = "-";
	} else {
		textClass  = "textnochange";
		iconClass = "icon-nochange";
	}
	var classIcon = "print right " + iconClass;
	var changingIndex =  $.web_utils.fomatNumberWithScale(marketInfo.chgIndex, 2);
	var changingPercent = $.web_utils.fomatNumberWithScale(marketInfo.pctIndex, 2);
	$('.box_bangCK .line ' + marketId +' a').attr('class', textClass);
	$('.box_bangCK .line ' + marketId + ' ' + marketId + 'Icon').attr('class', classIcon);
	$('.box_bangCK .line2 ' + marketId +' a').attr('class', textClass);
	$('.box_bangCK .line2 ' + marketId +' #currentIndex').html($.web_utils.fomatNumberWithScale(marketInfo.marketIndex, 2));
	$('.box_bangCK .line2 ' + marketId +' #change').html(changingIndex + " (" + changingPercent + "%)");
	
	var obj = "#IndexMarket_" + marketInfo.floorCode;
	$(obj).removeClass("textup textdow textnochange");
	$(obj).addClass(textClass);
	$(obj + ' #currentIndex').html($.web_utils.fomatNumberWithScale(marketInfo.marketIndex, 2));
	$(obj + ' #changeIndex').html(sign + Math.abs(changingIndex));
	$(obj + ' #changeIndexInPercent').html(sign + Math.abs(changingPercent) + "%");
}

//Lay thong tin tu database
function getMarketInfo(){
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
						renderInitMarketInfo(model.hostcMarket);
					}
					// VN30
					if (model != null && model.vn30Market != null) {
						renderInitMarketInfo(model.vn30Market);
					}
					// HNX30
					if (model != null && model.hnx30Market != null) {
						renderInitMarketInfo(model.hnx30Market);
					}
					// HNX
					if (model != null && model.hastcMarket != null) {
						renderInitMarketInfo(model.hastcMarket);
					}
					// Upcom		                 
					if (model != null && model.upComMarket != null) {
						renderInitMarketInfo(model.upComMarket);
					}
				}
				deffer.resolve();
			} catch (e) {
			}
		}
	});
	return deffer.promise();
}

function _syncMarketIndexWithMarketOnHomepage(cssclass, index, cIndex, changeIndex, changePercent){
	var obj = "#IndexMarket_" + index;
	$(obj).addClass(cssclass);
	$(obj + ' #currentIndex').html(cIndex);
	$(obj + ' #changeIndex').html(" " +  changeIndex);
	$(obj + ' #changeIndexInPercent').html(changePercent);
}
function imageRotator() {
	var curImg = $('#box1 #imageShow li.current');
	var nextImg = curImg.next();

	if (nextImg.length == 0) {
		nextImg = $('#box1 #imageShow li:first');
	}

	curImg.removeClass('current').addClass('previous');
	nextImg.css({
		opacity : 0
	}).addClass('current').animate({
		opacity : 1
	}, 1000, function() {
		curImg.removeClass('previous');
	});
};