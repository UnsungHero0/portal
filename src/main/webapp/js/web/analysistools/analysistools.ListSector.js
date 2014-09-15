//URL_LIST_INDUSTRIES_AJAX, URL_LIST_COMPANIES_AJAX, URL_LIST_SECTOR_AJAX, URL_SEARCH_BY_SYMBOL_AJAX

$().ready(function() {
	//initiate data and event handler
	var sector = new Sector();
	var industry = new Industry();
	var company = new Company();
	
	var screen = sector;
	
	sector.eventHandlers.push(function(){
		$('.industry').unbind();
		$('.industry').click(function(){
			var _sectorCode = $(this).metadata().sectorCode;
			industry.sectorCode = _sectorCode;
			industry.load();
			//Switch to industry screen
			screen = industry;
		});
	});
	
	
	industry.eventHandlers.push(function(){
		$('.company').unbind();
		$('.company').click(function(){
			var _industryCode = $(this).metadata().industryCode;
			var _sectorCode = $(this).metadata().sectorCode;
			company.sectorCode = _sectorCode;
			company.industryCode = _industryCode;
			company.load();
			//Switch to company screen
			screen = company;
		});
	});
	
	company.eventHandlers.push(sector.eventHandlers[0]);
	
	//process
	var industryCode = $.web_utils.getUrlVar('industryCode');
	var sectorCode = $.web_utils.getUrlVar('sectorCode');
	var symbol = $.web_utils.getUrlVar('symbol');
	
	if($.web_utils.getUrlVars().length == 0){
		//load sector
		sector.load();
	} else if ($.web_utils.isNotEmpty(symbol)) {
		//search company by symbol
		searchBySymbol(symbol);
		
	} else if ($.web_utils.isNotEmpty(sectorCode) 
			&& !$.web_utils.isNotEmpty(industryCode)) {
		//load industry
		industry.sectorCode = sectorCode;
		industry.load();
		//Switch to industry screen
		screen = industry;
	}
	else if ($.web_utils.isNotEmpty(sectorCode)
			&& $.web_utils.isNotEmpty(industryCode)) {
		//load company
		company.sectorCode = sectorCode;
		company.industryCode = industryCode;
		company.load();
		//Switch to company screen
		screen = company;
	} 
	
	$('.sorting').click(function(){
		var _sortField = $(this).metadata().sortField;
		screen.sort = true;
		screen.sortField = _sortField;
		screen.header = this;
		screen.load();
	});
	
	//other stuff;
	//auto complete
	$.web_autocomplete.symbols('searchSymbolId', URL_SYMBOL_AUTO_SUGGESTION,
			{width : 310, 
				callbackResult: function(e, item){
			}}
	);
	
	//Click event of search button
	$("#viewIndustry").click(function(){
		var symbol = $("#searchSymbolId").val();
		var idx = symbol.indexOf("-");
		symbol = (idx > 0 ? symbol.substring(0, idx) : symbol);
		
		searchBySymbol(symbol);
	});
	
	$('#searchSymbolId').bind("keypress",
		function(event) {
			if (event.keyCode == 13) {
				$("#viewIndustry").click();
			};
		}
	);
	
	function searchBySymbol(symbol) {
		var _url = company.url;
		var _params = company.getParams;
		
		//initiate data with new value
		company.url = URL_SEARCH_BY_SYMBOL_AJAX;
		company.sort = false;
		company.getParams = function() {
			return [{name : 'ifoCompanyCalcView.secCode', value : symbol}];
		};
		company.isSeachSymbol = true;
		company.load();
		
		//restore original data
		company.url = _url;
		company.getParams = _params;
		//Switch to company screen
		screen = company;
	}
});

function Sector(){
	//initiate data for sector
	this.sectorCode = "";
	this.sortField = "";
	this.header = null;
	this.order = "ASC";
	this.objSector = new OscClazzListSector();
	this.eventHandlers = [];
	this.sort = false;
	this.receiveData = this.objSector.populateSector;
	this.url = URL_LIST_SECTOR_AJAX;
	this.isSeachSymbol = false;
	
	//parameters
	this.getParams = function() {
		var result = [];
		if (this.sort) {
			result.push({name : 'ifoSectorCalcView.sortField', value : this.sortField});
			result.push({name : 'ifoSectorCalcView.order', value : this.order});
		}
		return result;
	};
	
	//request and grab data from server
	this.load = function(){
		var _this = this;
		$("#recordNotFound").hide();
		$.ajax({
			   type: "POST",
			   url: this.url,
			   data: this.getParams(),
			   dataType: "json",
			   success: function(data) {
					if(data.error.actionErrors.length != 0) {
						//nothing returned
						$("#recordNotFound").show();
						$("#listOfSectors table tbody").empty();
					} else {
						
						
						//receiving data
						
						//clean up the table
						_this.removeSortIcon();
						
						//receiving data
						_this.receiveData.call(_this.objSector, data);
						if (_this.isSeachSymbol && data.model) {							
							_this.sectorCode = data.model.ifoSectorCalcView.sectorCode;
							_this.industryCode = data.model.ifoIndustryCalcView.industryCode;
							_this.isSeachSymbol = false;
						}
						
						//add event handler
						$.each(_this.eventHandlers, function(i, item){
							if (typeof item == 'function') {
								item.call(_this);
							}
						});
						
						//if sort is needed
						if (_this.sort) {
							_this.showSortIcon();
						}
					}
			   },
			   beforeSend: this.objSector.loadImage /*show loading image icon*/
		});
	};
	//hide up & down icon
	this.removeSortIcon = function() {
		$('.sorting').children('img').removeClass('table-header-up');
		$('.sorting').children('img').removeClass('table-header-down');
	};
	
	//show up & down icon
	this.showSortIcon = function() {
		if (this.header == null) throw new Error("the header object of the table is null");
		if (this.order == 'ASC') {
			this.order = 'DESC';
			$(this.header).children('img:first').removeClass('table-header-up');
			$(this.header).children('img:first').addClass('table-header-down');
		} else {
			this.order = 'ASC';
			$(this.header).children('img:first').removeClass('table-header-down');
			$(this.header).children('img:first').addClass('table-header-up');
		}
	};
}

function Industry() {
	//initiate data for industry
	this.industryCode = "";
	this.url = URL_LIST_INDUSTRIES_AJAX;
	this.getParams = function() {
		var result = [{name : 'ifoIndustryCalcView.sectorCode', value : this.sectorCode}];
		if (this.sort) {
			result.push({name : 'ifoIndustryCalcView.sortField', value : this.sortField});
			result.push({name : 'ifoIndustryCalcView.order', value : this.order});
		}
		return result;
	};
	this.receiveData = this.objSector.populateIndustries;
}

Industry.prototype = new Sector();
Industry.prototype.constructor = Industry;

function Company() {
	//initiate data for company
	this.url = URL_LIST_COMPANIES_AJAX;
	this.getParams = function() {
		var result = [
				        	{name : 'ifoCompanyCalcView.sectorCode', value : this.sectorCode},
				        	{name : 'ifoCompanyCalcView.industryCode', value : this.industryCode}
				     ];
		if (this.sort) {
			result.push({name : 'ifoCompanyCalcView.sortField', value : this.sortField});
			result.push({name : 'ifoCompanyCalcView.order', value : this.order});
		}
		return result;
	};
	this.receiveData = this.objSector.populateCompanies;
}

Company.prototype = new Industry();
Company.prototype.constructor = Company;

