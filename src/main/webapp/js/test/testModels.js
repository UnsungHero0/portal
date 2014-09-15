define(function(require){
	var jqueryUrl = "js/jquery-1.9.1.min.js";
	var jqueryUtilsUrl = "js/web.jquery.utils.js";
	var stockModelUrl = "js/web/consultingcenter/stockrealtime/StockModel.js";
	var marketModelUrl = "js/web/snapshots/MarketModel.js";
	var numberUrl = "js/NumberFormat154.js";
	
	require([jqueryUrl, stockModelUrl, marketModelUrl, jqueryUtilsUrl, numberUrl], function(){
		module("Test Stock Model", {
			setup: function(){
				CONST_HSX_NAME = "10";
				CONST_HNX_NAME = "02";
				CONST_UPC_NAME = "03";
				CONST_VN30_NAME = "11";
				CONST_HX30_NAME = "12";
				WEB_CONTEXT = "";
				
				ATC_market = new MarketModel({});
				ATC_market.snapShot = {
					floorCode: CONST_HNX_NAME,
					status: "30"
				};	
				
				ATO_market = new MarketModel({});
				ATO_market.snapShot = {
					floorCode: CONST_VN30_NAME,
					status: "P"
				};
				
				other_market = new MarketModel({});
				other_market.snapShot = {};
			},
			
			teardown: function(){
				
			}
		});
		
		test("StockModel._getDisplayPrice()", function(){
			stockModel = new StockModel();
			stockModel.stock = {
				floorCode: CONST_HNX_NAME,
				currentPrice: 12,
				currentQtty: 13,
			};
			equal(stockModel._getDisplayPrice(ATC_market), stockModel.stock.currentPrice);
			
			stockModel.stock = {
				floorCode: CONST_HNX_NAME,
				currentPrice: 12,
				currentQtty: 0,
			};
			equal(stockModel._getDisplayPrice(ATO_market), 0);
			
			stockModel.stock = {
				floorCode: CONST_HNX_NAME,
				currentPrice: 0,
				currentQtty: 0,
				matchPrice: 35
			};
			equal(stockModel._getDisplayPrice(ATO_market), stockModel.stock.matchPrice);
			
			stockModel.stock = {
				floorCode: CONST_HSX_NAME,
				currentPrice: 0,
				currentQtty: 0,
				matchPrice: 35,
				projectOpen: 45
			};
			equal(stockModel._getDisplayPrice(ATO_market), stockModel.stock.projectOpen);
			
			stockModel.stock = {
				floorCode: CONST_HSX_NAME,
				currentPrice: 0,
				currentQtty: 0,
				matchPrice: 35,
				projectOpen: 45
			};
			equal(stockModel._getDisplayPrice(other_market), stockModel.stock.matchPrice);
		});
		
		test("StockModel.getDisplayGainloss()", function(){
			stockModel = new StockModel();
			stockModel.buyPrice = 100;
			stockModel.stock = {
				displayPrice: 110	
			};
			equal(stockModel.getDisplayGainloss(), "10.0");
			
			stockModel.buyPrice = 100;
			stockModel.stock = {
				displayPrice: 90	
			};
			equal(stockModel.getDisplayGainloss(), "-10.0");
			
			stockModel.buyPrice = 100;
			stockModel.stock = {
				displayPrice: 100	
			};
			equal(stockModel.getDisplayGainloss(), "0.0");
		});
		
		test("StockModel.update()", function(){
			stockModel = new StockModel();
			stockModel.buyPrice = 10;
			stock = {
				floorCode: CONST_HNX_NAME,
				currentPrice: 12,
				currentQtty: 13,
				basicPrice: 9
			};
			stockModel.update(stock, ATC_market);
			equal(stockModel.stock.displayPrice, stock.currentPrice);
			equal(stockModel.getDisplayGainloss(), "20.0");
		});
		
	});//End require
	
});