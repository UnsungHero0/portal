var originData = new Object();

function processFullSnapShot(zdata){
	$('.realtimeSymbolPrice').show();
	try {
		originData.r = zdata.r;
		// Lay mau
		var cl = getClass(zdata.c,zdata.f,zdata.r,zdata.lastPrice);
		//setCellAndColor('#symbol_sym', zdata.sym, cl);
		//originData.sym = zdata.sym;
		// Gia khop cuoi cung
		var rLastPrice = zdata.lastPrice;
		if(rLastPrice == 0){
			rLastPrice = zdata.r;
		}
		setCellAndColor('#symbol_pri', formatDotNumber(rLastPrice), cl);
		originData.lastPrice = rLastPrice;
		// Tong khoi luong khop (tongKL GD)
		setCellAndColor('#symbol_vol', FormatVolume10(zdata.lot,0), '');
		originData.lot = zdata.lot;
		// change
		setCellAndColor('#symbol_per', zdata.ot , cl);
		originData.ot = zdata.ot;
		if(zdata.ot == "0.0"){
			$("#symbol_per").html(zdata.ot + " (0.00%)");
			$("#stock-state-flag").addClass("icon-nochange");
		} else {
			var sign = zdata.lastPrice < zdata.r ? "-" : "+";
			$("#symbol_per").html(zdata.ot + " (" + sign + zdata.changePc +"%)");
			if(sign == "-"){
				$("#stock-state-flag").addClass("icon-dow");
			} else {
				$("#stock-state-flag").addClass("icon-up");
			}
		}
		// last volume
//		setCellAndColor('#symbol_lat', FormatVolume10(zdata.lastVolume,0), cl);
//		originData.lastVolume = zdata.lastVolume;
		
		// avg price
		var aveClass = getClass(zdata.c,zdata.f,zdata.r,zdata.avePrice);
		setCellAndColor('#symbol_avgP', formatDotNumber(zdata.avePrice), aveClass);
		originData.avePrice = zdata.avePrice;
		// high price
		var higClass = getClass(zdata.c,zdata.f,zdata.r,zdata.highPrice);
		setCellAndColor('#symbol_higP', formatDotNumber(zdata.highPrice), higClass);
		originData.highPrice = zdata.highPrice;
		// low price
		var lowClass = getClass(zdata.c,zdata.f,zdata.r,zdata.lowPrice);
		setCellAndColor('#symbol_lowP', formatDotNumber(zdata.lowPrice), lowClass);
		originData.lowPrice = zdata.lowPrice;
		
		// Du mua, du ban
		if (zdata.g7 != null){
			var g7 = zdata.g7.split("|");
			// set KL mua 4
			if(g7[0] != 0) {
				setCellAndColor('#symbol_bV4', FormatVolume10(g7[0],0), '');
			} else {
				$("#symbol_bV4").html("-");	
			}
				
			// set kl
			if(g7[1] != 0) {
				setCellAndColor('#symbol_oV4', FormatVolume10(g7[1],0), '');
			} else {
				$("#symbol_oV4").html("-");
			}
			
			originData.bV4 = g7[0];
			originData.oV4 = g7[1];
		}
	} catch (e) {
	}
}

function processLS(zdata){
	try {
		if(originData && zdata){
			// Gia khop cuoi cung
			setCellAndColor('#symbol_sym', zdata.sym, zdata.cl);
			if(zdata.lastPrice != originData.lastPrice){
				setCellAndColor('#symbol_pri', formatDotNumber(zdata.lastPrice), zdata.cl);
				highLightCell($('#symbol_pri'));
				originData.lastPrice = zdata.lastPrice;
			}
	
			// Tong khoi luong khop
			if(zdata.totalVol != originData.lot){
				setCellAndColor('#symbol_vol', FormatVolume10(zdata.totalVol,0), '');
				highLightCell($('#symbol_vol'));
				originData.lot = zdata.totalVol;
			}
			if(zdata.lastVol != originData.lastVolume){
				setCellAndColor('#symbol_lat', FormatVolume10(zdata.lastVol,0), zdata.cl);
				highLightCell($('#symbol_lat'));
				originData.lastVolume = zdata.lastVol;
			}
		
			// % thay doi
			if(zdata.change != originData.ot){
				$('#symbol_per').html("");
				setCellAndColor('#symbol_per', zdata.change , zdata.cl);
				if(zdata.change == "0.0"){
					$("#symbol_per").html(zdata.change + " (0.00%)");
					$("#stock-state-flag").removeClass("icon-dow").removeClass("icon-up").addClass("icon-nochange");
				} else {
					var sign = zdata.lastPrice < originData.r ? "-" : "+";
					$("#symbol_per").html(zdata.change + " (" + sign + zdata.changePc +"%)");
					if(sign == "-"){
						$("#stock-state-flag").removeClass("icon-nochange").removeClass("icon-up").addClass("icon-dow");
					} else {
						$("#stock-state-flag").removeClass("icon-nochange").removeClass("icon-dow").addClass("icon-up");
					}
				}
				highLightCell($('#symbol_per'));
				originData.ot = zdata.change;
			}
			
			// % avg
			if(zdata.ap != originData.avePrice){
				setCellAndColor('#symbol_avgP', formatDotNumber(zdata.ap), zdata.ca);
				highLightCell($('#symbol_avgP'));
				originData.avePrice = zdata.ap;
			}
			// % highest
			if(zdata.hp != originData.highPrice){
				setCellAndColor('#symbol_higP', formatDotNumber(zdata.hp), zdata.ch);
				highLightCell($('#symbol_higP'));
				originData.highPrice = zdata.hp;
			}
			// % lowest
			if(zdata.lp != originData.lowPrice){
				setCellAndColor('#symbol_lowP', formatDotNumber(zdata.lp), zdata.lc);
				highLightCell($('#symbol_lowP'));
				originData.lowPrice = zdata.lp;
			}
		}
	} catch (e) {
	}
}

function processTP(zdata){
	try {
		if (zdata && originData){
			if (zdata.side == 'B'){
				// ben mua
				if (zdata.vol4 != null){
					// set kl
					if (zdata.vol4 >0){
						setCellAndColor('#symbol_bV4', FormatVolume10(zdata.vol4,0), '');
						highLightCell($('#symbol_bV4'));
					}
				}
			} else {
				// ben ban
				if (zdata.vol4 != null){
					// set kl
					if (zdata.vol4 >0){
						setCellAndColor('#symbol_oV4', FormatVolume10(zdata.vol4,0), '');
						highLightCell($('#symbol_oV4'));
					}
				}
			}
		}
	} catch (e) {
	}
}

function processIndexUpdate(zdata){
	var clazzs = getCssClazzForHomepage(zdata.oIndex ,zdata.cIndex).split(',');
	var sign = "";
	if(zdata.oIndex > zdata.cIndex){
		sign = "-";
	}
	
	var ots = zdata.ot.split('|');

	if(zdata.mc == '10'){
		$('#hoseIcon').removeClass('icon-dow').removeClass('icon-up').removeClass('icon-nochange').addClass(clazzs[0]);
		$('#hose a').removeClass('textdow').removeClass('textup').removeClass('textnochange').addClass(clazzs[1]);
		$('#hose #currentIndex').html(zdata.cIndex.toFixed(2));
		$('#hose #change').html(sign + ots[0] + ' (' + sign + ots[1] + ')');
	} else if(zdata.mc == '02'){
		$('#hnxIcon').removeClass('icon-dow').removeClass('icon-up').removeClass('icon-nochange').addClass(clazzs[0]);
		$('#hnx a').removeClass('textdow').removeClass('textup').removeClass('textnochange').addClass(clazzs[1]);
		$('#hnx #currentIndex').html(zdata.cIndex.toFixed(2));
		$('#hnx #change').html(sign + ots[0] + ' (' + sign + ots[1] + ')');
	} else if(zdata.mc == '11'){
		$('#vn30Icon').removeClass('icon-dow').removeClass('icon-up').removeClass('icon-nochange').addClass(clazzs[0]);
		$('#vn30 a').removeClass('textdow').removeClass('textup').removeClass('textnochange').addClass(clazzs[1]);
		$('#vn30 #currentIndex').html(zdata.cIndex.toFixed(2));
		$('#vn30 #change').html(sign + ots[0] + ' (' + sign + ots[1] + ')');
	} else if(zdata.mc == '12'){
		$('#hnx30Icon').removeClass('icon-dow').removeClass('icon-up').removeClass('icon-nochange').addClass(clazzs[0]);
		$('#hnx30 a').removeClass('textdow').removeClass('textup').removeClass('textnochange').addClass(clazzs[1]);
		$('#hnx30 #currentIndex').html(zdata.cIndex.toFixed(2));
		$('#hnx30 #change').html(sign + ots[0] + ' (' + sign + ots[1] + ')');
	} else if(zdata.mc == '03'){
		$('#upcomIcon').removeClass('icon-dow').removeClass('icon-up').removeClass('icon-nochange').addClass(clazzs[0]);
		$('#upcom a').removeClass('textdow').removeClass('textup').removeClass('textnochange').addClass(clazzs[1]);
		$('#upcom #currentIndex').html(zdata.cIndex.toFixed(2));
		$('#upcom #change').html(sign + ots[0] + ' (' + sign + ots[1] + ')');
	}
	
	// change at ListedMarketOnHomepage.jsp too
	_syncMarketIndexWithListedMarketOnHomepage(clazzs[1], zdata.mc, sign, zdata.cIndex.toFixed(2), ots);
}

function _syncMarketIndexWithListedMarketOnHomepage(cssclass, index, sign, cIndex, ots){
	if(cssclass == "textup"){
		sign = "+";
	}
	var obj = "#IndexMarket_" + index;
	$(obj).addClass(cssclass);
	$(obj + ' #currentIndex').html(cIndex);
	$(obj + ' #changeIndex').html(" " + sign + ots[0]);
	$(obj + ' #changeIndexInPercent').html(sign + " " + ots[1]);
}

function processIndexUpdateOnlyGetStatusAndMarketCode(zdata){
	if (currentFloorCode != zdata.mc) {
		return;
	}
	
	var now = new Date();
	$('#marketState').html(getStatus(zdata.status,currentFloorCode));
	$('#marketCheckedTime').html(now.format("dd/mm/yyyy HH:MM"));
}

function getStatus(status,floorCode){
	var vStatus = "";
	if (status == "null"){
		vStatus = "Đóng cửa";
	}
	else if (status == "O"){
		if ((floorCode == CONST_HSX_NAME) || (floorCode == CONST_VN30_NAME)) {
			vStatus = "Phiên Liên tục"; 
		}		
		else{
			vStatus = "Mở cửa";
		}
	}
	else if (status == "P"){
		if ((floorCode == CONST_HSX_NAME) || (floorCode == CONST_VN30_NAME)){
			vStatus = "Phiên ATO"; 
		}
		else{
			vStatus = "Đóng cửa";
		}
	}
	else if (status == "A"){
		vStatus = "Phiên ATC"; 
	}
	else if (status == "C"){
		vStatus = "Phiên GDTT"; 
	}
	else if (status == "I"){
		vStatus = "Nghỉ trưa"; 
	}
	else{
		vStatus = "Đóng cửa";
	}
	return vStatus;
}

//function setMarketState(){
//	var now = new Date();
//	$('#marketState').html(getMarketState(now));
//	$('#marketCheckedTime').html(now.format("dd/mm/yyyy HH:MM"));
//}

function getMarketState(now){
	if(isClosedStateMarket(now)){
		return "Đóng cửa";
	}
	if(isOpenStateMarket(now)){
		return "Phiên mở cửa";
	}
	if(isKLDKStateMarket(now)){
		return "Khớp lệnh định kỳ";
	}
	if(isRestStateMarket(now)){
		return "Tạm nghỉ";
	}
	if(isCloseStateMarket(now)){
		return "Phiên đóng cửa";
	}
	
	return "";
}
/*Close: 14h30 - 9h00*/
function isClosedStateMarket(now){
	var d = now.getDay();
	// check t7, cn
	if(d == 7 || d == 0){
		return true;
	}
	
	var h = now.getHours();
	var m = now.getMinutes();
	
	if((h == 14 && m > 29) || h > 14 || h < 9){
		return true;
	}
	
	return false;
}
/*Open : 9h00 - 9h15*/
function isOpenStateMarket(now){
	var h = now.getHours();
	var m = now.getMinutes();
	if((h == 9 && m < 15)){
		return true;
	}
	
	return false;
}
/*Từ 9h15 - 11h30: Khớp lệnh định kỳ
 từ 13h00 - 13h45: Khớp lệnh định kỳ */
function isKLDKStateMarket(now){
	var h = now.getHours();
	var m = now.getMinutes();
	
	if((h == 9 && m > 14) || (h == 10) || (h == 11 && m < 30)){
		return true;
	}
	if (h == 13 && m < 45){
		return true;
	}
	
	return false;
}
/*Từ 11h30 - 13h00: Tạm nghỉ*/
function isRestStateMarket(now){
	var h = now.getHours();
	var m = now.getMinutes();
	if((h == 11 && m > 29) || (h ==12)){
		return true;
	}
	
	return false;
}
/*Từ 13h45 - 14h00: Phiên đóng cửa*/
function isCloseStateMarket(now){
	var h = now.getHours();
	var m = now.getMinutes();
	if((h == 13 && m > 44)){
		return true;
	}
	
	return false;
}
function formatDotNumber(number){
	if(number.toString().indexOf(".") == -1){
		number += ".0";
	}
	return number;
}
