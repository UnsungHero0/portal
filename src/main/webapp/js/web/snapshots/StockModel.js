function StockModel(params) {
	this.ajaxHelper = params.ajaxHelper;
	this.symbol = params.symbol;
	this.jqueryObject = $(this);
	this.isHighLight = false;
}

StockModel.prototype = {
	initStock: function(stock){
		this.stock = stock;
		this.emit("finishInitStock", stock);
	},
	updateStock: function(stock, marketModel){
		this.stock = stock;
		this.setDisplayPrice(marketModel);
		this.setDisplayMatchQtty(marketModel);
		this.setChangingPrice(this.stock.displayPrice, this.stock.displayMatchQtty);
		this.setBidOffer();
		this.emit("stockUpdate", stock);
	},
	setDisplayPrice: function(marketModel){
		var displayPrice = this.getDisplayPrice(marketModel);
		if(displayPrice == 0) {
			this.stock.displayPrice = this.stock.basicPrice;
		} else {
			this.stock.displayPrice = displayPrice;
		}
	},
	getDisplayPrice: function(marketModel) {
		/* * Với sàn HNX, UPCOM
		 *   - current price là giá khớp dự kiến của các phiên từ phiên ATC trở về sau
		 *   - matchPrice là giá khớp của các phiên trước phiên ATC
		 * */
		if (this.stock.floorCode  == CONST_HNX_NAME || this.stock.floorCode  == CONST_UPC_NAME) {
			if (this.stock.currentPrice > 0) {
				if (marketModel.isATC() || this.stock.currentQtty > 0) {
					return this.stock.currentPrice;
				}
				return 0;
			}
			return this.stock.matchPrice;
		} 
		
		/* * Với sàn HOSE 
		 * - projectOpen là giá khớp dự kiến trong phiên ATO, ATC
		 * - matchPrice là giá khớp của phiên liên tục
		 * */
		if (marketModel.isATO() || marketModel.isATC()) {
			return this.stock.projectOpen;
		}
		
		return this.stock.matchPrice;
	},
	setDisplayMatchQtty: function(marketModel){
		this.stock.displayMatchQtty = this.getDisplayMatchQtty(marketModel);
	},
	getDisplayMatchQtty: function(marketModel) {
		/* * Với sàn HNX, UPCOM
		 *   - currentQtty là khối lượng khớp dự kiến của các phiên từ phiên ATC trở về sau
		 *   - matchQtty là khối lượng khớp của các phiên trước phiên ATC
		 * */
		if (this.stock.floorCode  == CONST_HNX_NAME || this.stock.floorCode  == CONST_UPC_NAME) {
			if (this.stock.currentPrice > 0) {
				return this.stock.currentQtty;
			}
			return this.stock.matchQtty;
		} 
		
		/* * Với sàn HOSE 
		 * - khối lượng khớp bằng 0 trong phiên ATO, ATC
		 * - matchQtty là khối lượng khớp của phiên liên tục
		 * */
		if (marketModel.isATO() || marketModel.isATC()) {
			return 0;
		}
		return this.stock.matchQtty;
	},
	setChangingPrice: function(displayPrice, matchQuantity){
		var change = 0;
		if (displayPrice > 0 && matchQuantity > 0) {
			change = displayPrice - this.stock.basicPrice;
			change = Math.round(change*10)/10;
			var changePercent = $.web_utils.fomatNumberWithScale(change / this.stock.basicPrice * 100, 2);
			
			this.stock.changingPrice = change;
			this.stock.changingPercentPrice = changePercent;
			this.stock.isShowChangingPrice = true;
		} else {
			this.stock.changingPrice = 0;
			this.stock.changingPercentPrice = 0;
			this.stock.isShowChangingPrice = false;
		}
		
	},
	setBidOffer: function(){
		var bV4 = 0;
		var oV4 = 0;
		if ((this.stock.floorCode == CONST_HNX_NAME || this.stock.floorCode == CONST_UPC_NAME) &&  typeof this.stock.accumulatedVol != "undefined"){
			if ( this.stock.totalBidQtty ){
				bV4 = this.stock.totalBidQtty - this.stock.accumulatedVol;
				if (bV4 < 0) {
					bV4 = 0;
				}
			}  
			//du ban
			if ( this.stock.totalOfferQtty ){
				oV4 = this.stock.totalOfferQtty - this.stock.accumulatedVol;
				if (oV4 < 0) {
					oV4 = 0;
				}
			}
		}
		this.stock.oV4 = oV4;
		this.stock.bV4 = bV4;
	},
	initStockData: function(symbol){
		//Call ajax to get Stock
		this.ajaxHelper.request("priceservice/secinfo/snapshot/q=codes:"+symbol, function (data){
			if(data[0] != null){
				this.initStock(data[0]);
			} 
		}.bind(this));
	},
	
	emit : function(msgName, msgData) {
		this.jqueryObject.trigger({
			type : msgName,
			message : msgData,
			time : new Date()
		});
	},
	on : function(msgName, callback) {
		$(this).bind(msgName, callback);
	}

};