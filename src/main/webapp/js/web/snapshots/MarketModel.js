function MarketModel(params) {
	this.ajaxHelper = params.ajaxHelper;
	this.jqueryObject = $(this);
	this.preSnapshotStatus = 'null';
}

MarketModel.prototype = {
	initMarketInfo: function(floorCode){
		var deffer = new $.Deferred();
		ajaxHelper.request('priceservice/market/snapshot/q=codes:' + floorCode, function (data) {
			marketInfo = data[floorCode].data;
			this.initSnapshot(marketInfo);
			deffer.resolve();
		}.bind(this));
		return deffer.promise();
	},
	
	initSnapshot: function(marketInfo){
		this.snapShot = marketInfo;
	},
	
	setSnapshot: function(item){
		if (typeof item == "undefined" ) return;
		this.snapShot = item;
		if(this.preSnapshotStatus != item.status){
			this.preSnapshotStatus = item.status;
			this.emit("onChangeStatus", item.floorCode);
		}
		
	},
	
	checkATO : function(snapShot) {
		if (snapShot.floorCode == CONST_HSX_NAME
				|| snapShot.floorCode == CONST_VN30_NAME) {
			if (snapShot.status == "P" || snapShot.status == "2") {
				return true;
			}
		}
		return false;
	},
	
	checkATC : function(snapShot) {
		if (snapShot.status == "A"
				|| snapShot.status == "9"
				|| (snapShot.floorCode == CONST_HNX_NAME && snapShot.status == "30")
				|| (snapShot.floorCode == CONST_HX30_NAME && snapShot.status == "30")) {
			return true;
		}
		return false;
	},
	
	isATO : function() {
		return this.checkATO(this.snapShot);
	},
	
	isATC : function() {
		return this.checkATC(this.snapShot);
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