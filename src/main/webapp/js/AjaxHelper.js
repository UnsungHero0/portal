function AjaxHelper (serverProvider) {
	this.serverProvider = serverProvider;
	this.init();
}
AjaxHelper.prototype = {
	init: function(){
		this.crrServer = this.serverProvider.getAjaxRandomServer();
	},
	request: function (serviceUrl, callback) {
		var url = this.crrServer + serviceUrl;
		$.ajax({
			url: url,
			dataType: 'jsonp',
			jsonp: "jsonp"
		})
		.done(function(data){
			if (typeof data == "string") {
				data = JSON.parse(data);
			}
			callback(data);
		}.bind())
		.fail(function(){
			this.resetServer();
			setTimeout(function(){
				this.request(serviceUrl, callback);
			}.bind(this, serviceUrl, callback), 2000);
		}.bind(this, serviceUrl, callback));
		
	},
	resetServer: function() {
		this.crrServer = this.serverProvider.getAjaxNextServer();
	}
};