function StockView(params){
	this.wrp = params.wrp;
};

StockView.prototype = {
	
	render: function(stockModel){
		var stock = stockModel.stock;
		var currentPrice = stock.displayPrice;
		var cell = this.wrp.find("#" + stock.code).find("#ost-currentPrice");
		this._updateCellValueAndColor(cell, currentPrice, "");
		var gainLossPrice = stockModel.getDisplayGainloss();
		var gainLossCell = this.wrp.find("#" + stock.code).find("#ost-gainLoss");
		this._updateCellValueAndColor(gainLossCell, this._formatGainloss(gainLossPrice), this._getGainLossColor(gainLossPrice));
		
	},
	
	clearPrice: function(){
		$.each(this.wrp.find("table tr"), function(index,item){
			var symbol = $(item).attr("id");
			
			var currentPriceCell = this.wrp.find("#" + symbol).find("#ost-currentPrice");
			currentPriceCell.html("");
			
			var grainLossCell = this.wrp.find("#" + symbol).find("#ost-gainLoss");
			grainLossCell.html("");
		}.bind(this));
	},
	
	_formatGainloss: function(gainLoss){
		if (gainLoss > 0) {
			return "+" + Math.abs(gainLoss) + "%";
		} else if (gainLoss < 0) {
			return "-" + Math.abs(gainLoss) + "%";
		} else {
			return Math.abs(gainLoss) + "%";
		}
	},
	
	_getGainLossColor: function(gainLoss){
		if (gainLoss > 0) {
			return "txtbold txtgreen";
		} else if (gainLoss < 0) {
			return "txtbold txtred";
		} else if (gainLoss == 0){
			return "txtbold txtorange";
		} else {
			return "";
		}
	},
	
	_updateCellValueAndColor: function(cell, value, colorClass){
		var oldValue = cell.html();
		if(oldValue != value){
			cell.attr("class", colorClass);
			this._highlightCell(cell);
			cell.html(value);
		}
	},
	
	_highlightCell: function(cell){
		cell.addClass("hl2");
		setTimeout(function() {
			cell.removeClass('hl2');
		}, 1000);
	}
};