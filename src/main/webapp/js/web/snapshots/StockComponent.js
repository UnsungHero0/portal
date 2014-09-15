function StockComponent(params){
	this.socketManager = params.socketManager;
	this.stockModel = new StockModel(params);
	this.marketModel = new MarketModel(params);
	this.stockView = new StockView();
	this.listenModelEvent();
	this.listenServerEvent();
}

StockComponent.prototype = {
	initData: function(symbol){
		this.stockModel.initStockData(symbol);
	},
	listenModelEvent: function(){
		var self = this;
		this.stockModel.on("finishInitStock", function(){
			this.stockView.showStockInfoBoxRealtime();
			this.marketModel.initMarketInfo(this.stockModel.stock.floorCode)
			.done(this.updateStockInfo.bind(this))
			.done(this.registerConsumeStock.bind(this))
			.done(this.resiterConsumeMarketInfo.bind(this));
		}.bind(this));
		
		this.stockModel.on("stockUpdate", function(){
			this.renderStockAndMarketData();
		}.bind(this));
		
		this.marketModel.on("onChangeStatus", function(){
			this.stockModel.isHighLight = true;
			this.stockModel.updateStock(this.stockModel.stock, this.marketModel);
		}.bind(this));
		
	},
	updateStockInfo: function(){
		this.stockModel.updateStock(this.stockModel.stock, this.marketModel);
	},
	
	renderStockAndMarketData: function(){
		this.stockView.renderUpdateStock(this.stockModel.stock, this.stockModel.isHighLight);
		this.stockView.renderMarketStatus(this.marketModel.snapShot);
		this.marketModel.isHighLight = true;
	},
	
	resiterConsumeMarketInfo: function(){
		this.socketManager.registConsumer({type: DataEventType.MARKETINFO, codes: [this.marketModel.snapShot.floorCode]});
	},
	
	registerConsumeStock: function(){
		this.socketManager.registConsumer({type: DataEventType.STOCK, codes: [this.stockModel.symbol]});
	},
	
	listenServerEvent : function() {
		
		this.socketManager.on("returnData", function(event) {
			if(event.message.name == DataEventType.STOCK && Object.keys(event.message.data).length > 0){
				this.stockModel.updateStock(event.message.data[this.stockModel.symbol], this.marketModel);
			}
			
			if(event.message.name == DataEventType.MARKETINFO && Object.keys(event.message.data).length > 0){
				var marketInfos = event.message.data;
				if (marketInfos[this.floorCode]) {
					this.marketModel.setSnapshot(marketInfos[this.floorCode].snapshot.data);
				}
			}
		}.bind(this));
		
		//Listen stock event
		this.socketManager.on(DataEventType.STOCK, function(event) {
			this.stockModel.updateStock(event.message, this.marketModel);
		}.bind(this));
		
		//Listen marketEvent
		this.socketManager.on(DataEventType.MARKETINFO, function (event) {
			var marketInfo = event.message;
			this.marketModel.setSnapshot(marketInfo);
		}.bind(this));
	}
		
};