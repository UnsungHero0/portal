/**
 * @class
 * @param {HTMLInputElement} otxtSymbol
 * @param {TAChartManagerProvider} oTAChartManagerProvider
 */
function TAChartManagerControl (/*HTMLInputElement*/ otxtSymbol
								, /*TAChartManagerProvider*/oTAChartManagerProvider) {
	/**
	 * @return {HTMLInputElement}
	 */
	this.txtSymbol = otxtSymbol;
	
	/**
	 * @return {Element}
	 */
	this.divProgressImage = null;
	
	/**
	 * @return {TAChartManagerProvider}
	 */
	this.objTAChartManagerProvider = oTAChartManagerProvider;
	
	
	/**
	 * @return {function}
	 */
	this.funcBuildTAChart = null;
	
	/**
	 * @return {function}
	 */
	this.funcShowSearchSymbolError = null;
	
	/**
	 * @param {SymbolInfoBean} oSymbolInfoBean
	 * @return {function}
	 */
	this.funcBuildSymbolInfo = null;
};

/**
 * @public
 * @return {void}
 */
TAChartManagerControl.prototype.searchSymbol = function () {
	try {
		this.funcShowSearchSymbolError("");
	} catch (e) { }
	try {
		/**
	     * @return {TAChartManagerControl}
	     */
		var oThis = this;
		oThis.objTAChartManagerProvider.searchSymbol(oThis);
	} catch (e) {
		alert("TAChartManagerControl::searchSymbol() " + e);
	}
};

/**
 * This method handles result from searchSymbol method
 * @private
 * @param {SearchSymbolResponseBean} oSearchSymbolResponseBean
 * @param
 */
TAChartManagerControl.prototype.searchSymbolCallback = function (/*SearchSymbolResponseBean*/oSearchSymbolResponseBean) {
	if("OK" == oSearchSymbolResponseBean.status) {
		var oThis = this;
		//time out is 50 milli-seconds		 
		oThis.getSymbolInfo();
		oThis.funcBuildTAChart();
		// var timeoutId1 = setTimeout(oThis.funcBuildTAChart(), 100);
		
	    // var timeoutId2 = setTimeout(oThis.getSymbolInfo(), 1000);
	} else {
		// call function method
		try {
			this.funcShowSearchSymbolError(oSearchSymbolResponseBean.errorMsg);
		} catch (e) {
			alert("TAChartManagerControl::searchSymbolCallback() --> funcShowSearchSymbolError() ::" + e);
		}
	}
};

/**
 * @private
 * 
 */
TAChartManagerControl.prototype.getSymbolInfo = function () {
	try {
		/**
	     * @return {TAChartManagerControl}
	     */
		var oThis = this;
		this.objTAChartManagerProvider.getSymbolInfo(oThis);
	} catch (e) {
		alert("TAChartManagerControl::searchSymbol() " + e);
	}
};

/**
 * This method handles result fromg getSymbolInfo method.
 * @private
 * @param {SymbolInfoBean} oSymbolInfoBean
 * @return {void}
 */
TAChartManagerControl.prototype.getSymbolInfoCallback = function (/*SymbolInfoBean*/ oSymbolInfoBean) {
	// call function method
	try {		
		this.funcBuildSymbolInfo(oSymbolInfoBean);
	} catch (e) {
		alert("TAChartManagerControl::getSymbolInfoCallback() --> funcBuildSymbolInfo() ::" + e);
	}
};

/**
* Show progress image
* @private
* @return {void}
*/
TAChartManagerControl.prototype.showProgressImage = function () {
	try {
		this.divProgressImage.style.display = "inline";
	} catch(e) {
		// alert(e);
	}
};

/**
* Hide progress image
* @private
* @return {void}
*/
TAChartManagerControl.prototype.hideProgressImage = function () {
	try {
		this.divProgressImage.style.display = "none";
	} catch(e) {
		// alert(e);
	}
};

/**
 * @class
 */
function TAChartManagerProvider() {
	/*
	 * @return {XmlHttpRequest}
	 */
	this.http = zXmlHttp.createRequest();
	this.http2 = zXmlHttp.createRequest();
};

/**
 * search a symbol and store company information into Session
 * @param {TAChartManagerControl} oTAChartManagerControl
 * @return {void}
 */
TAChartManagerProvider.prototype.searchSymbol = function (/*TAChartManagerControl*/ oTAChartManagerControl) {
	/**
	 * @return {XmlHttpRequest}
	 */
    var oHttp = this.http2;
    
    /**
     * @return {TAChartManagerProvider}
     */
	var oThis = this;
	
	var symbol = "";
    try {
    	symbol = oTAChartManagerControl.txtSymbol.value;
    } catch (e) {
    	symbol = "";
    }
	symbol = oThis.preProcessSymbol(symbol);
	if(symbol == "") {
		return;
	}
	
	//Show progress image
    oTAChartManagerControl.showProgressImage();
    
    //cancel any active requests                          
    if (oHttp.readyState != 0) {
        oHttp.abort();
    }                     
    
    oHttp.onreadystatechange = function () {
		if(oHttp.readyState ==READY_STATE_COMPLETE) {
			if( oHttp.status == PAGE_SUCCESS) {    
	        	try {
		            //evaluate the returned text JavaScript (an array)
		          	var strXML = oHttp.responseText;		          	
		          	// alert(strXML);		          
		          	/*
		          	 * <online>
					 *	<status>OK|ERROR</status>
					 *	<error-message></error-message>
					 * </online>
		          	 */
					var oXml = zXmlDom.createDocument();
					oXml.loadXML(strXML);
					
					var status = zXPath.selectSingleNode(oXml.documentElement, "/online/status");
					var errMsg = zXPath.selectSingleNode(oXml.documentElement, "/online/error-message");					
					
					//alert("--->>> process XML");
					//process xml content
		          	var oSearchSymbolResponseBean = new SearchSymbolResponseBean();
		          	oSearchSymbolResponseBean.status = removeEnter(status.text);
		          	oSearchSymbolResponseBean.errorMsg = removeEnter(errMsg.text);					
					
		          	// Call back method										
		           oTAChartManagerControl.searchSymbolCallback(oSearchSymbolResponseBean);
		           
		           oTAChartManagerControl.hideProgressImage();
	            } catch (e) {
	            	alert("searchSymbol():: " + e);
	            }      
        	}
        }    
    };
    
	var uri = "/online/brokerage/ajax/QuickSearchSymbol.do?symbol=" + symbol;
	var url = getAJAXProxyUrl();
	
	var paramXMLContent = "";
	paramXMLContent += "<online>";
	paramXMLContent += "<request-uri><![CDATA[" + uri + "]]></request-uri>";
	paramXMLContent += "<params>";
	paramXMLContent += "<param name=\"symbol\"><![CDATA[" + symbol + "]]></param>";
	paramXMLContent += "</params>";
	paramXMLContent += "</online>";

	// alert(paramXMLContent);
    //open connection to server	
	oHttp.open("POST",url,true);
	oHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //send the request
    oHttp.send(paramXMLContent);
};

/**
 * get price & company infoirmation
 * @param {TAChartManagerControl} oTAChartManagerControl
 * @return {void}
 */
TAChartManagerProvider.prototype.getSymbolInfo = function (/*TAChartManagerControl*/ oTAChartManagerControl) {
	/**
	 * @return {XmlHttpRequest}
	 */
    var oHttp = this.http;
    
    /**
     * @return {TAChartManagerProvider}
     */
	var oThis = this;
		
	//Show progress image
    oTAChartManagerControl.showProgressImage();
    
    //cancel any active requests                          
    if (oHttp.readyState != 0) {
        oHttp.abort();
    }                     
    
    oHttp.onreadystatechange = function () {
		if(oHttp.readyState ==READY_STATE_COMPLETE) {
			if( oHttp.status == PAGE_SUCCESS) {    
	        	try {
		            //evaluate the returned text JavaScript (an array)
		          	var strXML = oHttp.responseText;		          	
		          	// alert(strXML);		          
					var oXml = zXmlDom.createDocument();
					oXml.loadXML(strXML);
					
					var oSymbolInfoBean = oThis.processSymbolInfoXML(oXml);
										
		          	// Call back method										
		           oTAChartManagerControl.getSymbolInfoCallback(oSymbolInfoBean);
		           
		           oTAChartManagerControl.hideProgressImage();
	            } catch (e) {
	            	alert("getSymbolInfo():: " + e);
	            }      
        	}
        }    
    };
    
	var uri = "/online/brokerage/ajax/SymbolInfoDetail.do";
	var url = getAJAXProxyUrl();
	
	var paramXMLContent = "";
	paramXMLContent += "<online>";
	paramXMLContent += "<request-uri><![CDATA[" + uri + "]]></request-uri>";
	paramXMLContent += "<params>";	
	paramXMLContent += "</params>";
	paramXMLContent += "</online>";

	// alert(paramXMLContent);
    //open connection to server	
	oHttp.open("POST",url,true);
	oHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //send the request
    oHttp.send(paramXMLContent);
};

/**
 * @private
 * @param {String} symbol
 * @return {String}
 */
TAChartManagerProvider.prototype.preProcessSymbol = function (symbol) {
	try {
		var index1 = symbol.indexOf("-");
		if(index1 > 0) {
			return symbol.substring(0, index1);
		} else {
			return symbol;
		}		
	} catch (e) {
		alert(e);
		return symbol;
	}
};

/**
 * @retrun {SymbolInfoBean}
 */
TAChartManagerProvider.prototype.processSymbolInfoXML = function(/*zXmlDom*/ oXml) {
	var oSymbolInfoBean = new SymbolInfoBean();
	var str;
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/companyInfo");
	oSymbolInfoBean.companyInfo = removeEnter(str.text);
	
	// process snapshot information
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/weekHigh");
	oSymbolInfoBean.weekHigh = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/weekLow");
	oSymbolInfoBean.weekLow = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/marketCapital");
	oSymbolInfoBean.marketCapital = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/shareOutstanding");
	oSymbolInfoBean.shareOutstanding = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/declaredDividend");
	oSymbolInfoBean.declaredDividend = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/dividendYield");
	oSymbolInfoBean.dividendYield = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/exDividendDate");
	oSymbolInfoBean.exDividendDate = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/dividendPayableDate");
	oSymbolInfoBean.dividendPayableDate = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/eps");
	oSymbolInfoBean.eps = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/roa");
	oSymbolInfoBean.roa = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/roe");
	oSymbolInfoBean.roe = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/leverage");
	oSymbolInfoBean.leverage = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/earningsValue");
	oSymbolInfoBean.earningsValue = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/bookValue");
	oSymbolInfoBean.bookValue = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/beta");
	oSymbolInfoBean.beta = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/averageVolumn");
	oSymbolInfoBean.averageVolumn = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/pe");
	oSymbolInfoBean.pe = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/foreignOwnership");
	oSymbolInfoBean.foreignOwnership = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/foreignSoldVol");
	oSymbolInfoBean.foreignSoldVol = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/foreignBoughtVol");
	oSymbolInfoBean.foreignBoughtVol = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/snapshot/companyOverview");
	oSymbolInfoBean.companyOverview = removeEnter(str.text);

	// process price information
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/price");
	oSymbolInfoBean.price = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/prevPrice");
	oSymbolInfoBean.prevPrice = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/tradeDate");
	oSymbolInfoBean.tradeDate = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/chgIndex");
	oSymbolInfoBean.chgIndex = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/pctIndex");
	oSymbolInfoBean.pctIndex = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/ceilingPrice");
	oSymbolInfoBean.ceilingPrice = removeEnter(str.text);
	str = zXPath.selectSingleNode(oXml.documentElement, "/online/priceInfo/floorPrice");
	oSymbolInfoBean.floorPrice = removeEnter(str.text);
	
	return oSymbolInfoBean;
};

/**
 * @private
 * @class
 */
function SearchSymbolResponseBean () {
	/**
	 * @return {String}
	 */
	this.status = "";
	
	/**
	 * @return {String}
	 */
	this.errorMsg = "";	
};

/**
 * @class
 */
function SymbolInfoBean () {
	this.companyInfo = "";
	
	/* Price information */
	this.price = "";
	this.prevPrice = "";
	this.tradeDate = "";
	this.chgIndex = "";
	this.pctIndex = "";
	this.ceilingPrice = "";
	this.floorPrice = "";
	
	
	/* Snapshot information */
	this.weekHigh ="";
	this.weekLow ="";
	this.marketCapital ="";
	this.shareOutstanding ="";
	this.declaredDividend ="";
	this.dividendYield ="";
	this.exDividendDate ="";
	this.dividendPayableDate ="";
	this.eps ="";
	this.roa ="";
	this.roe ="";
	this.leverage ="";
	this.earningsValue ="";
	this.bookValue ="";
	this.beta ="";
	this.averageVolumn ="";
	this.pe ="";
	this.foreignOwnership ="";
	this.foreignSoldVol ="";
	this.foreignBoughtVol ="";
	this.companyOverview ="";
};