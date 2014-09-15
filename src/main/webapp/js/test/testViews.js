define(function(require){
	var modules = [
	    "js/jquery-1.9.1.min.js",
	    "js/web/consultingcenter/stockrealtime/StockView.js",
	    "js/web/consultingcenter/stockrealtime/StockModel.js"
	];
	
	require(modules, function(){
		module("Test Stock View", {
			setup: function(){
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
				stockView = new StockView({wrp: $("#openStockPick")});
			},
			
			teardown: function(){
				$("#stockview").html("");
			}
		});
		
		test("StockView._formatGainloss()", function(){
			equal(stockView._formatGainloss("10.1"), "+10.1%");
			equal(stockView._formatGainloss("-10.1"), "-10.1%");
			equal(stockView._formatGainloss("0"), "0%");
		});
		
		test("StockView._highlightCell()", function(){
			var cell = stockView.wrp.find("#TNC").find("#ost-currentPrice");
			stockView._highlightCell(cell);
			equal(cell.attr("class"), "hl2");
		});
		
		test("StockView._updateCellValueAndColor()", function(){
			var cell = stockView.wrp.find("#TNC").find("#ost-currentPrice");
			stockView._updateCellValueAndColor(cell, 55, "testClass");
			equal(cell.attr("class"), "testClass hl2");
			equal(cell.html(), "55");
		});
		
		test("StockView.clearPrice()", function(){
			stockView.clearPrice();
			
			var cell1 = stockView.wrp.find("#TNC").find("#ost-currentPrice");
			equal(cell1.html(), "");
			
			var cell2 = stockView.wrp.find("#VSH").find("#ost-currentPrice");
			equal(cell2.html(), "");
			
			var cell3 = stockView.wrp.find("#TNC").find("#ost-gainLoss");
			equal(cell3.html(), "");
			
			var cell4 = stockView.wrp.find("#VSH").find("#ost-gainLoss");
			equal(cell4.html(), "");
		});
		
		test("StockView.render()", function(){
			stockModel = new StockModel();
			stockModel.buyPrice = 100;
			stockModel.stock = {
				displayPrice: 110,
				code: "TNC"
			};
			stockView.render(stockModel);
			
			var cellPrice = stockView.wrp.find("#TNC").find("#ost-currentPrice");
			equal(cellPrice.html(), "110");
			equal(cellPrice.attr("class"), "hl2");
			
			var cellGainLoss = stockView.wrp.find("#TNC").find("#ost-gainLoss");
			equal(cellGainLoss.html(), "+10%");
			equal(cellGainLoss.attr("class"), "txtbold txtgreen hl2");
		});
		
	});//End require
	
});