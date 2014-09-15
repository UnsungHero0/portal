

SnapshotMain = {
	
	socketManager:  new SocketManager(serverProvider),
	
	ajaxHelper:  new AjaxHelper(serverProvider),
	
	init: function(symbol){
		this.symbol = symbol;
		this.createStockComponent();
		this.socketManager.start();
		this.initData();
	},
	
	createStockComponent: function(){
		this.stockComponent = new StockComponent({
			socketManager: this.socketManager,
			ajaxHelper: this.ajaxHelper,
			symbol: this.symbol
		});
	}, 

	initData: function(){
		this.stockComponent.initData(this.symbol);
	}
	
};