/**
* static function
* $.web_autocomplete.symbols(...)
* $.web_autocomplete.accounts(...)
*/
jQuery.web_autocomplete = {
	symbols : function(txtInputId, url, options) {
		try {
			var opts = jQuery.extend({
				width : 310,
				callbackResult : null //function(e, item){}
			}, options);
			$("#" + txtInputId).autocomplete(url, {
				multiple: false,
				dataType: "json",
				//minChars: 0,
				width: opts.width,
				matchContains: true,
				autoFill: true,
				scroll: false,
				parse: function(data) {
					try {
					return $.map(data.model.stockExchanges, function(row) {
						return {
							data: row,
							value: row.symbol,
							result: row.symbol
						};
					});
					} catch (e) {
						alert('parse error: ' + e);
					}
				},
				formatItem: function(item) {
					var stock = item;
					var str = "<table width='100%' border='0px'><tr><td align='left' width='15%' class='ac_formatItem'>";
						str = str + stock.symbol + "</td><td align='left' width='85%' class='ac_formatItem'><i>";
						str = str + stock.companyName + "(" + stock.exchangeCode + ")</i></td></tr></table>";
					return str;
				},
				formatResult: function(row) {
					return row.symbol;
				}
			}).result(function(e, item) {
				try {
					if($.isFunction(opts.callbackResult)) {
						opts.callbackResult(e, item);
					}
				} catch(e){
					alert('result error = ' + e);
				}
			});
		} catch (e){
			alert('error = ' + e);
		}
	},
	/*
	 *
	 */
	accounts : function(txtInputId, url, options) {
		try {
			var opts = jQuery.extend({
				width : 310,
				callbackResult : null //function(e, item){}
		   }, options);

			$("#" + txtInputId).autocomplete(url, {
				multiple: false,
				dataType: "json",
				//minChars: 0,
				width: opts.width,
				matchContains: false,
				autoFill: true,
				scroll: false,
				parse: function(data) {
					try {
						return $.map(data.model.customerAccounts, function(row) {
							return {
								data: row,
								value: row.accountNumber,
								result: row.accountNumber
							};
						});
					} catch (e) {
						alert(e);
					}
				},
				formatItem: function(item) {
					var acc = item;
					var str = "<table width='100%' border='0px'><tr><td align='left' width='15%' class='ac_formatItem'>";
						str = str + acc.accountNumber + "</td><td align='left' width='85%' class='ac_formatItem'><i>";
						str = str + "[" + acc.customerId + " - " + acc.accountName + "]</i></td></tr></table>";
					return str;
				},
				formatResult: function(row) {
					return row.accountNumber;
				}
			}).result(function(e, item) {
				try {
					if($.isFunction(opts.callbackResult)) {
						opts.callbackResult(e, item);
					}
				} catch(e){}
			});
		} catch (e) {}
	}
};