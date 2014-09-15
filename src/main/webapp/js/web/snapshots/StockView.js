function StockView(){
	
} 


StockView.prototype = {
	showStockInfoBoxRealtime: function(){
		$('.realtimeSymbolPrice').show();
	},
	renderUpdateStock : function(stock, isHighLight){
		var price = stock.displayPrice;
		var cl = this.getTextClass(price, stock.floorPrice, stock.ceilingPrice, stock.basicPrice);
		setCellAndColor('#symbol_pri', this.formatPriceNumber(price), cl, isHighLight);
		
		// Tong khoi luong khop (tongKL GD)
		setCellAndColor('#symbol_vol', FormatVolume10(stock.accumulatedVol, 0), '', isHighLight);
		
		// Gia thay doi
		this.renderChangePrice(stock, cl, isHighLight);
		
		//Gia tran
		var higClass = this.getTextClass(stock.highestPrice, stock.floorPrice, stock.ceilingPrice, stock.basicPrice);
		setCellAndColor('#symbol_higP', this.formatPriceNumber(stock.highestPrice), higClass, isHighLight);
		//Gia San
		var lowClass = this.getTextClass(stock.lowestPrice, stock.floorPrice, stock.ceilingPrice, stock.basicPrice);
		setCellAndColor('#symbol_lowP', this.formatPriceNumber(stock.lowestPrice), lowClass, isHighLight);
		//Gia TB
		var avePrice = "";
		if(stock.accumulatedVol != 0){
			avePrice = $.web_utils.fomatNumberWithScale(stock.accumulatedVal * 1000000 / (stock.accumulatedVol*10), 1);
			var aveClass = this.getTextClass(avePrice, stock.floorPrice, stock.ceilingPrice, stock.basicPrice);
			setCellAndColor('#symbol_avgP', this.formatPriceNumber(avePrice), aveClass, isHighLight);
		} else {
			$('#symbol_avgP').html('');
		}
		
		// Du mua, du ban
		this.renderBidOffer(stock, isHighLight);
	},
	renderChangePrice: function(stock, cl, isHighLight){
		var change = stock.changingPrice;
		var percent = stock.changingPercentPrice;
		var isShowChanging = stock.isShowChangingPrice;
		var displayChangeData = "";
		if(isShowChanging) {
			if(change > 0) {
				displayChangeData = change + " (+" + percent +"%)";
				$("#symbol_per").html(displayChangeData);
				$("#stock-state-flag").attr("class", "print left icon-up");
			} else if(change < 0){
				displayChangeData = Math.abs(change) + " (-" + Math.abs(percent) +"%)";
				$("#symbol_per").html(displayChangeData);
				$("#stock-state-flag").attr("class", "print left icon-dow");
			} else {
				displayChangeData = change + " (" + percent +"%)";
				$("#symbol_per").html(displayChangeData);
				$("#stock-state-flag").attr("class", "print left icon-nochange");
			}
			setCellAndColor('#symbol_per', displayChangeData , cl, isHighLight);
		} else {
			$('#symbol_per').html("");
			$("#stock-state-flag").attr("class", "print left");
		}
	},
	renderBidOffer: function(stock, isHighLight){
		if(stock.bV4 != 0) {
			setCellAndColor('#symbol_bV4', FormatVolume10(stock.bV4,0), '', isHighLight);
		} else {
			$("#symbol_bV4").html("-");	
		}
		
		if(stock.oV4 != 0) {
			setCellAndColor('#symbol_oV4', FormatVolume10(stock.oV4,0), '', isHighLight);
		} else {
			$("#symbol_oV4").html("-");
		}
	},
	getTextClass: function(price, floorPrice, ceilingPrice, basicPrice){
		var cl;
		if (price == 0) {
			cl = 'e';
		} else {
			if (price == ceilingPrice) {
				cl = 'c';
			} else if (price > basicPrice) {
				cl = 'i';
			} else if (price == basicPrice) {
				cl = 'e';
			} else if (price == floorPrice) {
				cl = 'f';
			} else {
				cl = 'd';
			}
		}
		return cl;
	},
	renderMarketStatus: function(marketInfo){
		var now = new Date();
		$('#marketState').html(this.getStatusMarket(marketInfo));
		$('#marketCheckedTime').html(now.format("dd/mm/yyyy HH:MM"));
	},
	getStatusMarket: function(info){
		var status = info.status;
		var floorCode = info.floorCode;
		var vStatus = "Đóng cửa";
		if (status == "null") {
			vStatus = "Đóng cửa";
		} else if (status == "O" || status == "5") {
			vStatus = "Phiên Liên tục";
		} else if (status == "P" || status == "2") {
			if ((floorCode == CONST_HSX_NAME) || (floorCode == CONST_VN30_NAME)) {
				vStatus = "Phiên ATO";
			} else {
				vStatus = "Đóng cửa";
			}
		} else if (status == "A" || status == "9" || status == "30") {
			vStatus = "Phiên ATC";
		} else if (status == "C" || status == "0" || status == "35") {
			vStatus = "Phiên GDTT";
		} else if (status == "I" || status == "BSU" || status == "3"
				|| status == "10") {
			vStatus = "Nghỉ trưa";
		} else if (status == "11") {
			vStatus = "Đóng cửa";
		}
	
		return vStatus;
	},
	formatPriceNumber: function(price){
		if (!price || price == "0" || price == "0.0"){
			return "";
		} else {
			return formatDotNumber(price);
		}
	}
	
	
};