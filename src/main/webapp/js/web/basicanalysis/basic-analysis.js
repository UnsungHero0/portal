BasicAnalysis = {
	init: function(){
		this.loadROE();
		
		this.loadYearlyIncome();
		
		this.loadHNXSymbols();
		this.loadHOSESymbols();
	},
	
	initLocEPS: function(){
		this.HOSE_SYMBOL_LIST = [];
		this.HNX_SYMBOL_LIST = [];
		this.LOOPTIME = 0;
		
		this.loadFilterHNXSymbols();
		this.loadFilterHOSESymbols();
		
		this.regisCheckEpsBtn();
		this.regisBodyKeyPressFunction();
		
		this.regisFilterEPSHOSEBtn();
		this.regisFilterEPSHNXBtn();
	},
	
	regisBodyKeyPressFunction: function(){
		var self = this;
		$( "body" ).keypress(function(e){
			if ( e.which == 13 ) {
				if($('#symbolToCheck').val() != ""){
					self.checkEPS();
				}
			}
		});
	},
	
	regisCheckEpsBtn: function(){
		var self = this;
		$('#checkEPSBtn').click(function(){
			self.checkEPS();
		});
	},
	
	regisFilterEPSHOSEBtn: function(){
		var self = this;
		$('#filterEPSHOSEBtn').click(function(){
			for(var i = self.HOSE_SYMBOL_LIST.length-1; i >=0; i--) {
				if(self.LOOPTIME > 0){
					if(i >= self.HOSE_SYMBOL_LIST.length - self.LOOPTIME){
						toCheckThis(self.HOSE_SYMBOL_LIST[i]);
						setTimeout(5000, self.checkEPS());
					}
				} else {
					toCheckThis(self.HOSE_SYMBOL_LIST[i]);
					setTimeout(5000, self.checkEPS());
				}
			}
			for(var i = 0; i < self.LOOPTIME; i++){
				self.HOSE_SYMBOL_LIST.pop();
			}
		});
	},
	
	regisFilterEPSHNXBtn: function(){
		var self = this;
		$('#filterEPSHNXBtn').click(function(){
			for(var i = self.HNX_SYMBOL_LIST.length-1; i >=0; i--) {
				if(self.LOOPTIME > 0){
					if(i >= self.HNX_SYMBOL_LIST.length - self.LOOPTIME){
						toCheckThis(self.HNX_SYMBOL_LIST[i]);
						setTimeout(5000, self.checkEPS());
					}
				} else {
					toCheckThis(self.HNX_SYMBOL_LIST[i]);
					setTimeout(5000, self.checkEPS());
				}
			}
			for(var i = 0; i < self.LOOPTIME; i++){
				self.HNX_SYMBOL_LIST.pop();
			}
		});
	},
	
	checkEPS: function(){
		console.log("Check EPS " + $('#symbolToCheck').val());
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/loceps.shtml",
			data: {
				symbol: $('#symbolToCheck').val(),
				acceptableLastQuarter: $('#acceptableLastQuarter').val(), 
				acceptableSameQuarterLastYear: $('#acceptableSameQuarterLastYear').val()
			},
			async: false,
			success: function(data){
				if(data.model){
					if(data.model.isGoodEPSByCanslim){
						$('#checkEPSResult').css('color', 'green');
						$('#checkEPSResult').html("Ngon " + "<a target='_blank' href='" + $.web_resource.getContext() 
								+ "asd/" + data.model.symbol + ".shtml'> >>>> Check tiếp <<<< </a>");
						$('#goodList').html($('#goodList').html() + "," + "<a target='_blank' href='" + $.web_resource.getContext() 
								+ "asd/" + data.model.symbol + ".shtml'>" + data.model.symbol + "</a>");
						
					} else {
						$('#checkEPSResult').css('color', 'red');
						$('#checkEPSResult').html("Loại bỏ");
					}
				}
				
				var hnx = $('#_hxnSymbols').html();
				hnx = hnx.replace($('#symbolToCheck').val() + " --", '');
				$('#_hxnSymbols').html(hnx);
				
				var hose = $('#_hoseSymbols').html();
				hose = hose.replace($('#symbolToCheck').val() + " --", '');
				$('#_hoseSymbols').html(hose);
			},
			error: function(e){
				alert('error');
			}
		});
	},
	
	loadHOSESymbols: function(){
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewHOSESymbols.shtml",
			success: function(data){
				var html = "";
				$.each(data.model.hoseSymbols, function(i, sym){
					html += '<a href="' + $.web_resource.getContext() + 'asd/' + sym + '.shtml">' + sym + " -- </a>"; 
				});
				$('#_hoseSymbols').html(html);
			},
			error: function(e){
				alert('error');
			}
		});
	},
	
	loadHNXSymbols: function(){
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewHNXSymbols.shtml",
			success: function(data){
				var html = "";
				$.each(data.model.hnxSymbols, function(i, sym){
					html += '<a href="' + $.web_resource.getContext() + 'asd/' + sym + '.shtml">' + sym + " -- </a>"; 
				});
				$('#_hxnSymbols').html(html);
			},
			error: function(e){
				alert('error');
			}
		});
	},
	
	loadFilterHNXSymbols: function(){
		var self = this;
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewHNXSymbols.shtml",
			success: function(data){
				this.HNX_SYMBOL_LIST = data.model.hnxSymbols;
				var html = "";
				$.each(data.model.hnxSymbols, function(i, sym){
					self.HNX_SYMBOL_LIST.push(sym);
					html += '<a onclick="toCheckThis(' + "'" + sym + "'" + ')">' + sym + " -- </a>"; 
				});
				$('#_hxnSymbols').html(html);
			},
			error: function(e){
				alert('error');
			}
		});
	},
	
	loadFilterHOSESymbols: function(){
		var self = this;
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewHOSESymbols.shtml",
			success: function(data){
				var html = "";
				$.each(data.model.hoseSymbols, function(i, sym){
					self.HOSE_SYMBOL_LIST.push(sym);
					html += '<a onclick="toCheckThis(' + "'" + sym + "'" + ')">' + sym + " -- </a>"; 
				});
				$('#_hoseSymbols').html(html);
			},
			error: function(e){
				alert('error');
			}
		});
	},
	
	loadROE: function(){
		var self = this;
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewROE.shtml",
			data: "symbol=" + $('#_symbol').val(),
			success: function(data){
				var roe1 = data.model.pasicAnalysis.roeList[0];
				var roe2 = data.model.pasicAnalysis.roeList[1];
				var roe3 = data.model.pasicAnalysis.roeList[2];
				
				if(roe1 && roe1.contains('-')) {
					$('#_roe2013').css('color', 'red');
				}
				if(roe2 && roe2.contains('-')) {
					$('#_roe2012').css('color', 'red');
				}
				if(roe3 && roe3.contains('-')) {
					$('#_roe2011').css('color', 'red');
				}
				$('#_roe2013').html(self.write(roe1, " %"));
				$('#_roe2012').html(self.write(roe2, " %"));
				$('#_roe2011').html(self.write(roe3, " %"));
			},
			error: function(e){
				console.error(e);
			}
		});
	},
	
	write: function(val, suffix){
		if(val) {
			return val + suffix;
		}
		
		return "-"; 
	},
	
	loadYearlyIncome: function() {
		var self = this;
		$.ajax({
			url: $.web_resource.getContext() + "basic-analysis-ajax/viewYearlyIncome.shtml",
			data: "symbol=" + $('#_symbol').val(),
			success: function(data){
				var income1 = data.model.pasicAnalysis.incomeList[0];
				var income2 = data.model.pasicAnalysis.incomeList[1];
				var income3 = data.model.pasicAnalysis.incomeList[2];
				
				if(income1 && income1.contains('-')){
					$('#_income2013').css('color', 'red');
				}
				if(income2 && income2.contains('-')){
					$('#_income2012').css('color', 'red');
				}
				if(income3 && income3.contains('-')){
					$('#_income2011').css('color', 'red');
				}
				$('#_income2013').html(self.write(income1, " tỷ"));
				$('#_income2012').html(self.write(income2, " tỷ"));
				$('#_income2011').html(self.write(income3, " tỷ"));
			},
			error: function(e){
				console.error(e);
			}
		});
	}
};

function toCheckThis(sym){
	$('#symbolToCheck').val(sym);
}

