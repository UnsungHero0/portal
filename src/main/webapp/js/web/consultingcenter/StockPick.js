StockPick = {
    FLOOR_CODES : [CONST_HSX_NAME, CONST_HNX_NAME, CONST_UPC_NAME, CONST_VN30_NAME, CONST_HX30_NAME],
    ajaxHelper:  new AjaxHelper(serverProvider),
	init: function(){
		$( "#recommend-tabs" ).tabs();
	    $( "#newscontent-tabs" ).tabs();
	    
	    this.newsCount = $('.rcmIndexL li').length;
	    
	    this.loadRelatedReport();
	    this.loadNewerReport();
	    this.loadNewestMarketNews();
	    
	    this.trackClickBoxContentEvent();
	    this.trackClickEffectBoxEvent();
	    
	    this.registerPlaceOrderBtnFunction();
	    this.registerBasicInfoContentFunction();
	    this.registerSeeMoreBasicInfoFunction();
	    
	    this.socketManager = new SocketManager(serverProvider);
	    this.stockController = this._createStockController(this.socketManager);
	    this.socketManager.start();
	},
	
	initForOnlyBodyEffectBox: function(){
		$( "#recommend-tabs" ).tabs();
	},
	
	initForOnlyBodyDemoNewsBox: function(){
		this.newsCount = 1;
		this.registerPlaceOrderBtnFunction();
		this.registerBasicInfoContentFunction();
	    this.registerSeeMoreBasicInfoFunction();
	},
	
	initForNotDA: function(){
		$( "#recommend-tabs" ).tabs();
		this.trackClickEffectBoxEvent();
		this._registerStockPick();
	},
	
	loadRelatedReport: function(){
		var url = WEB_CONTEXT + "y-tuong-dau-tu/ajax/getRelatedReportDates.shtml?date=" + $('#currentDate').val();
		$('#relatedReports').load(url);
	},
	
	loadNewerReport: function(){
		var url = WEB_CONTEXT + "y-tuong-dau-tu/ajax/getNewerReportDates.shtml?date=" + $('#currentDate').val();
		$('#newerReports').load(url);
	},

	loadNewestMarketNews: function(){
		var url = WEB_CONTEXT + "y-tuong-dau-tu/ajax/getNewsestMarketNews.shtml";
		$('#marketNews').load(url);
	},
	
	registerBasicInfoContentFunction: function(){
		var clickObj;
		var openObj;
		for(var i=1; i<=this.newsCount; i++){
			clickObj = $('#newscontent' + i + ' #basicInfoId');
			openObj = $(clickObj).closest('.boxAnalysis').find('#basicInfoContent');
			this.registerBasicInfoContentClick(clickObj, openObj);
		}
	},
	
	registerBasicInfoContentClick:function(clickObj, openObj){
		var self = this;
		$(clickObj).click(function(){
			if($(this).hasClass('st-basicInfoShow')){
				$(this).removeClass('st-basicInfoShow').addClass('st-basicInfoHide');
				$(openObj).show();
				
				self.trackShowBasicInfoEvent();
			} else {
				$(this).removeClass('st-basicInfoHide').addClass('st-basicInfoShow');
				$(openObj).hide();
			}
		});
	},
	
	registerPlaceOrderBtnFunction: function(){
		var clickObj;
		for(var i=1; i<=this.newsCount; i++){
			clickObj = $('#newscontent' + i + ' #placeOrderBtn');
			this.registerPlaceOrderBtnClick(clickObj);
		}
	},
	
	registerPlaceOrderBtnClick: function(obj){
		var self = this;
		$(obj).click(function(){
			self.trackClickTradeButtonEvent($(obj));
			
			var url = TRADE_CONTEXT + '/giao-dich-co-phieu/dat-lenh-mua-ban.shtml';
			var symbol = self.getSymbolToTrade($(obj));
			if(typeof symbol != "undefined" && symbol != ""){
				url += '?symbol=' + symbol + '&orderType=NB';
			}
			window.open(url, '_blank');
		});
	},
	
	getSymbolToTrade: function(tradeBtnObj){
		return $(tradeBtnObj).closest('.boxAnalysis').attr('ref').split('-')[2];
	},
	
	registerSeeMoreBasicInfoFunction: function(){
		var seeMoreObj;
		for(var i=1; i<=this.newsCount; i++){
			seeMoreObj = $('#newscontent' + i + ' #seeMoreBasicInfo');
			this.registerSeeMoreBasicInfoClick(seeMoreObj);
		}
	},
	
	registerSeeMoreBasicInfoClick: function(seeMoreObj){
		var self = this;
		$(seeMoreObj).click(function(){
			self.trackSeeMoreBasicInfoEvent();
			
			var url = $(this).attr('ref');
			window.open(url, '_blank');
		});
	},
	
	trackSeeMoreBasicInfoEvent: function(){
		var params = ['_trackEvent', 'Stock Pick', 'Basic Info', 'See More'];
		
		console.log(params);

		// track event
		_gaq.push(params);
	},
	
	trackShowBasicInfoEvent: function(){
		var trackParams = ['_trackEvent', 'Stock Pick', 'Basic Info', 'All Show Clicks'];

		console.log(trackParams);

		// track event
		_gaq.push(trackParams);
	},

	trackClickBoxContentEvent: function(){
		var newsContentTabs = $('#newscontent-tabs .nameIndex').find('li');
		$.each(newsContentTabs, function(index, tab){
			$(tab).click(function(){
				var trackParams = ['_trackEvent', 'Stock Pick', 'View News'];
				var trackLabel = $(tab).attr('ref');
				trackParams.push(trackLabel);

				console.log(trackParams);

				// track event
				_gaq.push(trackParams);
			});
		});
	},
	
	trackClickTradeButtonEvent: function(obj){
		// track all clicks
		var trackAllViewParams = ['_trackEvent', 'Stock Pick', 'Place Order', 'All Clicks']; 
		
		console.log(trackAllViewParams);
		
		_gaq.push(trackAllViewParams);

		// track each
		var trackParams = ['_trackEvent', 'Stock Pick', 'Place Order'];
		var trackLabel = $(obj).closest('.boxAnalysis').attr('ref');
		trackParams.push(trackLabel);
		
		console.log(trackParams);
		
		_gaq.push(trackParams);
	},
	
	trackClickEffectBoxEvent: function(){
		$('#openStockPickTab').click(function(){
			var params = ['_trackEvent', 'Stock Pick', 'Effect Box', 'Opening'];
			
			console.log(params);
			
			_gaq.push(params);
		});
		
		$('#closeStockPickTab').click(function(){
			var params = ['_trackEvent', 'Stock Pick', 'Effect Box', 'Closed'];
			
			console.log(params);
			
			_gaq.push(params);
		});
	},
	
	_registerStockPick: function(){
		$('#registerStockPick_btn').click(function(){
			$.ajax({
				   type: "POST",
				   url: $.web_resource.getContext() + 'y-tuong-dau-tu/ajax/register-customer.shtml',
				   success: function(dataResponse){
				   }
			});
		});
	},
	
	_createStockController: function(socketManager){
		return new StockController({
			wrp: $("#openStockPick"),
			FLOOR_CODES: this.FLOOR_CODES,
			ajaxHelper: this.ajaxHelper,
			socketManager: socketManager
		});
	},
	
};




