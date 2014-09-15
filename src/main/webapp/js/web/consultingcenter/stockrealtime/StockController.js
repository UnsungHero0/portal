function StockController(params){
	this.wrp = params.wrp;
	this.FLOOR_CODES = params.FLOOR_CODES;
	this.ajaxHelper = params.ajaxHelper;
	this.marketModels = this._createMarketModels();
	this.stockModels = {};
	this.stockView = new StockView(params);
	this.stockList = [];
	this.socketManager =  params.socketManager;
	this._extractSymbolAndBuyPrice();
	this._listenFromSocket();
	this._registConsumeMarketAndStock();
};

StockController.prototype = {
	_registConsumeStockPick: function(){
		this._listenModelEvent();
		this.socketManager.registConsumer({type:DataEventType.STOCK, codes: this.stockList});
	},
	
	_registConsumeMarket: function(){
		this.socketManager.registConsumer({type:DataEventType.MARKETINFO, codes: this.FLOOR_CODES});
	},
	
	_initMarketList: function(){
		var deferred = new $.Deferred();
		this.ajaxHelper.request('priceservice/market/snapshot/q=codes:' + this.FLOOR_CODES.join(","), function (data) {
			$.each(this.FLOOR_CODES, function(index, floorCode){
				this.marketModels[floorCode].setSnapshot(data[floorCode].data);
			}.bind(this));
			
			deferred.resolve();
		}.bind(this));
		return deferred.promise();
	},
	
	_listenFromSocket: function(){
		var self = this;
		this.socketManager.on("returnData", function(event){
			self.state = "uncleared";
			
			if(event.message.name == DataEventType.STOCK && Object.keys(event.message.data).length > 0){
				var listStock = event.message.data;
				$.each(self.stockList, function(index, symbol){
					self.stockModels[symbol].update(listStock[symbol], self.marketModels[listStock[symbol].floorCode]);
				});
			}
			
			if(event.message.name == DataEventType.MARKETINFO && Object.keys(event.message.data).length > 0){
				var marketInfos = event.message.data;
				$.each(self.FLOOR_CODES, function(index, floorCode){
					if (marketInfos[floorCode]) {
						self.marketModels[floorCode].setSnapshot(marketInfos[floorCode].snapshot.data);
					}
				});
			}
		});
		
		this.socketManager.on(DataEventType.STOCK, function(event){
			self.stockModels[event.message.code].update(event.message, self.marketModels[event.message.floorCode]);
		});
		
		this.socketManager.on(DataEventType.MARKETINFO, function(event){
			var market = event.message;
			self.marketModels[market.floorCode].setSnapshot(market);
		});
		
	},
	
	_listenModelEvent: function(){
		var self = this;
		
		$.each(self.stockList, function(index, symbol){
			self.stockModels[symbol].on("stockUpdate", function(data){
				var symbol = data.message;
				self.stockView.render(self.stockModels[symbol]);
			});
		});

		$.each(self.FLOOR_CODES, function(index, floorCode){
			self.marketModels[floorCode].on("onChangeStatus", function(){
				$.each(self.stockModels, function(index, stockModel){
					if(stockModel.stock.floorCode == floorCode){
						stockModel.update(stockModel.stock, self.marketModels[floorCode]);
					}
				});
			});
		});
	},
	
	_createMarketModels: function(){
		function createMarketModel(){
			return new MarketModel({
				ajaxHelper: this.ajaxHelper
			});
		};
		
		var marketModels = {};
		$.each(this.FLOOR_CODES, function(index, floorcode){
			marketModels[floorcode] = createMarketModel();
		});
		return marketModels;
	},
	
	_registConsumeMarketAndStock: function(){
		this._initMarketList()
		.done(this._registConsumeStockPick.bind(this))
		.done(this._registConsumeMarket.bind(this))
		.done(this._listenPauseEvent.bind(this));
	},
	
	_extractSymbolAndBuyPrice: function(){
		$.each(this.wrp.find("table tr"), function(index,item){
			var symbol = $(item).attr("id");
			if(typeof symbol != 'undefined') {
				this.stockList.push(symbol);
			}
			//Initialize stock models
			var buyPriceCell = this.wrp.find("#" + symbol).find("#ost-buyPrice");
			var buyPrice = buyPriceCell.html();
			this.stockModels[symbol] = new StockModel();
			this.stockModels[symbol].buyPrice = buyPrice;
		}.bind(this));
	},
	
	_listenPauseEvent: function(){
		var self = this;
		this.socketManager.on("pause", function(event){
			if(self.state != "cleared"){
				self.state = "cleared";
				self.stockView.clearPrice();
			}
		});
	}
};