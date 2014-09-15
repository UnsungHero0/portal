define(function(require){
	var modules = [
	    "js/web/consultingcenter/stockrealtime/StockModel.js",
	    "js/web/snapshots/MarketModel.js",
	    "js/web/consultingcenter/stockrealtime/StockView.js",
		"js/web/consultingcenter/stockrealtime/StockController.js"
	];
	
	require(modules, function(){
		CONST_HSX_NAME = "10";
		CONST_HNX_NAME = "02";
		CONST_UPC_NAME = "03";
		CONST_VN30_NAME = "11";
		CONST_HX30_NAME = "12";
		var serverProvider = new ServerProvider("http://123.30.240.104:80/,http://123.30.240.113:80/,http://202.160.125.23:80/,http://202.160.125.47:80/,http://202.160.125.24:80/".split(","));
		sdiv = 
			'<div id="openStockPick" aria-labelledby="openStockPickTab" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="true" aria-hidden="false">'
			+ '<table border="0" cellspacing="0" cellpadding="0" class="recommendTable">'
			+ '<colgroup> <col width="4%"> <col width="9%"> <col width="12%"> <col> <col> <col> <col> <col> <col width="30%"> </colgroup>'
			+ '<tbody>'
			+ '<tr>'
			+ '<th>TT</th> <th>Cổ phiếu</th> <th>Ngày mua</th> <th>Giá mua</th> <th>Giá<br>(26/3/2014)</th> <th>Giá<br>mục tiêu </th> <th>Giá<br>cắt lỗ </th> <th>Lãi/lỗ<br>tạm tính </th> <th>Ghi chú</th>'
			+ '</tr>'
			+ '<tr id="TNC">'
			+ '<td>1</td>'
			+ '<td class="txtbold"><a href="https://www.vndirect.com.vn/portal/y-tuong-dau-tu/co-phieu-khuyen-nghi/20140321.shtml">TNC</a></td>'
			+ '<td>21/03/2014</td>'
			+ '<td id="ost-buyPrice">15.4</td>'
			+ '<td id="ost-currentPrice" class="">15.2</td>'
			+ '<td>17</td>'
			+ '<td>14.7</td>'
			+ '<td class="txtbold txtred" id="ost-gainLoss">-1.3%</td>'
			+ '<td>Vẫn có thể mua mới</td>'
			+ '</tr>'
			+ '<tr id="VSH" class="backgroudGray">'
			+ '<td>32</td>'
			+ '<td class="txtbold"><a href="https://www.vndirect.com.vn/portal/y-tuong-dau-tu/co-phieu-khuyen-nghi/20131111.shtml">VSH</a></td>'
			+ '<td>11/11/2013</td>'
			+ '<td id="ost-buyPrice">13.8</td>'
			+ '<td id="ost-currentPrice" class="">17.5</td>'
			+ '<td>20</td>'
			+ '<td>13.5</td>'
			+ '<td class="txtbold txtgreen" id="ost-gainLoss">+26.8%</td>'
			+ '<td>&nbsp;</td>'
			+ '</tr></tbody>'
			+ '</table>'
			+ '</div>';
		
		$("#stockview").html(sdiv);
		
		var controller = new StockController({
			wrp: $("#openStockPick"),
			FLOOR_CODES: [CONST_HSX_NAME, CONST_HNX_NAME, CONST_UPC_NAME, CONST_VN30_NAME, CONST_HX30_NAME],
			ajaxHelper: new AjaxHelper(serverProvider),
			//socketManager: new SocketManager(serverProvider)
			socketManager: {
				emit: function(msgName, msgData){
					$(this).trigger({
						type : msgName,
						message : msgData,
						time : new Date()
					});
				},
				on: function(msgName, callback){
					$(this).bind(msgName, callback);
				},
				registConsumer: function(){},
				stopConsume: function(){}
			}
		});
		controller.stockModels["TNC"].stock = {"floorCode":"02","dateNo":"1303","tradingDate":"1396310400000","time":"10:50:24","stockId":"5904","code":"TNC","companyName":"null","stockType":"ST","totalListingQtty":"27719850","tradingUnit":"100","listtingStatus":"0","adjustQtty":"0","referenceStatus":"0","adjustRate":"0","dividentRate":"0","status":"0","totalRoom":"0","currentRoom":"1271630.2","basicPrice":"14.8","openPrice":"14.8","closePrice":"0","currentPrice":"0","currentQtty":"0","highestPrice":"14.8","lowestPrice":"14.5","ceilingPrice":"16.2","floorPrice":"13.4","totalOfferQtty":"37440","totalBidQtty":"46430","priorPrice":"0","priorClosePrice":"14.8","matchPrice":"14.5","matchQtty":"570","matchValue":"0.08265","averagePrice":"0","indexPrice":"0","bidPrice01":"14.5","bidQtty01":"7270","bidPrice02":"14.4","bidQtty02":"9660","bidPrice03":"14.3","bidQtty03":"3510","offerPrice01":"14.6","offerQtty01":"970","offerPrice02":"14.7","offerQtty02":"920","offerPrice03":"14.8","offerQtty03":"2000","prevPriorPrice":"0","yieldmat":"0","parvalue":"10000","totalTradingValue":"2.76279","totalTradingQtty":"18900","accumulatedVal":"2.76279","accumulatedVol":"18900","buyForeignValue":"0","sellForeignValue":"0","buyForeignQtty":"0","sellForeignQtty":"0","projectOpen":"0","sequence":"107279"};
		controller.stockModels["VSH"].stock = {"floorCode":"10","dateNo":"1303","tradingDate":"1396310400000","time":"10:50:24","stockId":"5904","code":"VSH","companyName":"null","stockType":"ST","totalListingQtty":"27719850","tradingUnit":"100","listtingStatus":"0","adjustQtty":"0","referenceStatus":"0","adjustRate":"0","dividentRate":"0","status":"0","totalRoom":"0","currentRoom":"1271630.2","basicPrice":"14.8","openPrice":"14.8","closePrice":"0","currentPrice":"0","currentQtty":"0","highestPrice":"14.8","lowestPrice":"14.5","ceilingPrice":"16.2","floorPrice":"13.4","totalOfferQtty":"37440","totalBidQtty":"46430","priorPrice":"0","priorClosePrice":"14.8","matchPrice":"14.5","matchQtty":"570","matchValue":"0.08265","averagePrice":"0","indexPrice":"0","bidPrice01":"14.5","bidQtty01":"7270","bidPrice02":"14.4","bidQtty02":"9660","bidPrice03":"14.3","bidQtty03":"3510","offerPrice01":"14.6","offerQtty01":"970","offerPrice02":"14.7","offerQtty02":"920","offerPrice03":"14.8","offerQtty03":"2000","prevPriorPrice":"0","yieldmat":"0","parvalue":"10000","totalTradingValue":"2.76279","totalTradingQtty":"18900","accumulatedVal":"2.76279","accumulatedVol":"18900","buyForeignValue":"0","sellForeignValue":"0","buyForeignQtty":"0","sellForeignQtty":"0","projectOpen":"0","sequence":"107279"};
		
		module("Test Stock Controller", {
			setup: function(){
				
			},
			
			teardown: function(){
				$("#stockview").html("");
			}
		});
		
		test("StockController._createMarketModels()", function(){
			var marketModels = controller._createMarketModels();
			notEqual(typeof marketModels, "undefined");
			notEqual(typeof marketModels[CONST_HSX_NAME], "undefined");
		});
		
		asyncTest("StockController._initMarketList()", function() {
			expect( 1 );
			
			controller._initMarketList().done(function(){
				notEqual(typeof controller.marketModels[CONST_HSX_NAME].snapShot, "undefined");
				start();
			});
		});
		
		/*asyncTest("StockController.onChangeStatus()", function() {
			controller.socketManager.on(DataEventType.MARKETINFO, function(event){
				ok(true, "success");
				start();
			});
			
			market = {"advance":117,"bidVol":0,"changedIndex":0,"controlCode":null,"dateNo":null,"decline":114,"floorCode":"10","highestIndex":582.81,"lowestIndex":582.81,"marketIndex":590.14,"noChange":60,"offerVol":0,"percentIndex":0,"priorMarketIndex":588.06,"totalShareTraded":15133593,"totalStock":0,"totalTrade":6264.3,"totalValueTraded":2527.498,"tradingDate":1395907243501,"tradingTime":"15:12:10","shareTraded":null,"status":"12","marketID":null,"totalNormalTradedQttyRd":0,"totalNormalTradedValueRd":0,"totalNormalTradedQttyOd":0,"totalNormalTradedValueOd":0,"totalPTTradedQtty":0,"totalPTTradedValue":0,"upVolume":8356681,"downVolume":3634157,"noChangeVolume":2655688,"sequence":286};
			controller.socketManager.emit(DataEventType.MARKETINFO, market);
		});*/
		
		/*asyncTest("StockController.updateStock() Controller level", function() {
			controller.socketManager.on(DataEventType.STOCK, function(event){
				ok(true, "success");
				start();
			});
			
			stock = {"floorCode":"02","dateNo":"1303","tradingDate":"1396310400000","time":"10:50:24","stockId":"5904","code":"TNC","companyName":"null","stockType":"ST","totalListingQtty":"27719850","tradingUnit":"100","listtingStatus":"0","adjustQtty":"0","referenceStatus":"0","adjustRate":"0","dividentRate":"0","status":"0","totalRoom":"0","currentRoom":"1271630.2","basicPrice":"14.8","openPrice":"14.8","closePrice":"0","currentPrice":"0","currentQtty":"0","highestPrice":"14.8","lowestPrice":"14.5","ceilingPrice":"16.2","floorPrice":"13.4","totalOfferQtty":"37440","totalBidQtty":"46430","priorPrice":"0","priorClosePrice":"14.8","matchPrice":"14.5","matchQtty":"570","matchValue":"0.08265","averagePrice":"0","indexPrice":"0","bidPrice01":"14.5","bidQtty01":"7270","bidPrice02":"14.4","bidQtty02":"9660","bidPrice03":"14.3","bidQtty03":"3510","offerPrice01":"14.6","offerQtty01":"970","offerPrice02":"14.7","offerQtty02":"920","offerPrice03":"14.8","offerQtty03":"2000","prevPriorPrice":"0","yieldmat":"0","parvalue":"10000","totalTradingValue":"2.76279","totalTradingQtty":"18900","accumulatedVal":"2.76279","accumulatedVol":"18900","buyForeignValue":"0","sellForeignValue":"0","buyForeignQtty":"0","sellForeignQtty":"0","projectOpen":"0","sequence":"107279"};
			controller.socketManager.emit(DataEventType.STOCK, stock);
		});*/
		
		asyncTest("StockController.updateStock() Model level", function() {
			controller.stockModels["TNC"].on("stockUpdate", function(data){
				ok(true, "success");
				start();
			});
			
			stock = {"floorCode":"02","dateNo":"1303","tradingDate":"1396310400000","time":"10:50:24","stockId":"5904","code":"TNC","companyName":"null","stockType":"ST","totalListingQtty":"27719850","tradingUnit":"100","listtingStatus":"0","adjustQtty":"0","referenceStatus":"0","adjustRate":"0","dividentRate":"0","status":"0","totalRoom":"0","currentRoom":"1271630.2","basicPrice":"14.8","openPrice":"14.8","closePrice":"0","currentPrice":"0","currentQtty":"0","highestPrice":"14.8","lowestPrice":"14.5","ceilingPrice":"16.2","floorPrice":"13.4","totalOfferQtty":"37440","totalBidQtty":"46430","priorPrice":"0","priorClosePrice":"14.8","matchPrice":"14.5","matchQtty":"570","matchValue":"0.08265","averagePrice":"0","indexPrice":"0","bidPrice01":"14.5","bidQtty01":"7270","bidPrice02":"14.4","bidQtty02":"9660","bidPrice03":"14.3","bidQtty03":"3510","offerPrice01":"14.6","offerQtty01":"970","offerPrice02":"14.7","offerQtty02":"920","offerPrice03":"14.8","offerQtty03":"2000","prevPriorPrice":"0","yieldmat":"0","parvalue":"10000","totalTradingValue":"2.76279","totalTradingQtty":"18900","accumulatedVal":"2.76279","accumulatedVol":"18900","buyForeignValue":"0","sellForeignValue":"0","buyForeignQtty":"0","sellForeignQtty":"0","projectOpen":"0","sequence":"107279"};
			controller.socketManager.emit(DataEventType.STOCK, stock);
		});
		
		asyncTest("StockController.onChangeStatus()", function() {
			controller.stockModels["VSH"].on("stockUpdate", function(data){
				ok(true, "success");
				start();
			});
			
			market = {"advance":117,"bidVol":0,"changedIndex":0,"controlCode":null,"dateNo":null,"decline":114,"floorCode":"10","highestIndex":582.81,"lowestIndex":582.81,"marketIndex":590.14,"noChange":60,"offerVol":0,"percentIndex":0,"priorMarketIndex":588.06,"totalShareTraded":15133593,"totalStock":0,"totalTrade":6264.3,"totalValueTraded":2527.498,"tradingDate":1395907243501,"tradingTime":"15:12:10","shareTraded":null,"status":"12","marketID":null,"totalNormalTradedQttyRd":0,"totalNormalTradedValueRd":0,"totalNormalTradedQttyOd":0,"totalNormalTradedValueOd":0,"totalPTTradedQtty":0,"totalPTTradedValue":0,"upVolume":8356681,"downVolume":3634157,"noChangeVolume":2655688,"sequence":286};
			controller.socketManager.emit(DataEventType.MARKETINFO, market);
		});
		
	});//End require
	
});