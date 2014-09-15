function StockModel() {
	this.stock = {};
	this.jqueryObject = $(this);
};

StockModel.prototype = {
	
	update: function(stock, marketModel){
		this.stock = stock;
		this.stock.displayPrice = this._getDisplayPrice(marketModel);
		if(this.stock.displayPrice == 0){
			this.stock.displayPrice = stock.basicPrice;
		}
		this.stock.gainLoss = this.getDisplayGainloss();
		this.emit("stockUpdate", stock.code);
	},
	
	_getDisplayPrice: function(marketModel) {
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
	
	
	getDisplayGainloss: function(){
		if (this.buyPrice == 0) {
			return "";
		}
		var gainLoss =$.web_utils.fomatNumberWithScale((this.stock.displayPrice - this.buyPrice)*100/this.buyPrice, 1);
		return gainLoss;
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