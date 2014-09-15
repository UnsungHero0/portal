
/**
* static function
* $.web_ui.updateTradingAccountOverview(uuid, accountObj, options)
*/
jQuery.web_ui = {
	/**
	 * 
	 * @param: uuid
	 * 
	 * $.web_ui.registerTradingOverview(uuid)
	 */
	registerTradingOverview: function (uuid) {
		if($.web_utils.isNotNull(LOAD_MAP_UUID_TAB) && $.web_utils.isNotNull(uuid)) {
			LOAD_MAP_UUID_TAB.put(uuid, "true");
		}		
	},
	
	/**
	 * 
	 * @param currUUID
	 * @param currActiveAccountObj
	 * @param options
	 * 
	 * $.web_ui.notifyUpdateTradingAccountOverview(currUUID, currActiveAccountObj, options)
	 * 
	 */
	notifyUpdateTradingAccountOverview: function(currUUID, currActiveAccountObj, options) {
		if($.web_utils.isNotNull(LOAD_MAP_UUID_TAB) && $.web_utils.isNotNull(currUUID) && $.web_utils.isNotNull(currActiveAccountObj)) {
			var opts = jQuery.extend({	
				accountName : "tradingAOAccountName"
				, selectedAccountNumberId : "sltTradingAOSelectedAccountNumberId"
				, custodyCode : "tradingAOCustodyCode"
				, customerId : "tradingAOCustomerId"
				, netAccountValue : "tradingAONetAccountValue"
				, cashAvailable : "tradingAOCashAvailable"	
				, txtCustomerId : "txtTradingAOCustomerId"
				, warningMsg : "tradingAOWarningChangeAccountMsg"
			}, options);
			
			var idx, uuid, custId;
			var listUUID = LOAD_MAP_UUID_TAB.keys();
			for(idx in listUUID) {
				uuid = listUUID[idx];
				if(uuid != currUUID) {
					custId = null;
					try {
						custId = $("#" + opts.txtCustomerId + "_" + uuid).val();						
					} catch (e) {
						if(DEBUG) {
							$.log('notifyUpdateTradingAccountOverview:: check custId ' + e);
						}
						custId = null;
					}
					if($.web_utils.isNull(custId)) {
						if(DEBUG) {
							$.log('notifyUpdateTradingAccountOverview:: remove ' + uuid);
						}
						LOAD_MAP_UUID_TAB.remove(uuid);
					} else {
						$.web_ui.updateTradingAccountOverview(uuid, currActiveAccountObj, opts);
						
						try {
							$("#" + opts.warningMsg + "_" + uuid).show();
						} catch (e) {
							if(DEBUG) {
								$.log('show warning msg....');
							}
						}
					} // end if($.web_utils.isNull(custId))
				}
			} // end for(idx in listUUID)
		}
	},
	
	/**
	 * @param uuid
	 * @param accountObj
	 * @param options
	 * 
	 * $.web_ui.updateTradingAccountOverview(uuid, accountObj, options)
	 */
	updateTradingAccountOverview : function(uuid, activeAccountObj, options) {
		try {				
			var opts = jQuery.extend({	
				accountName : "tradingAOAccountName"
				, selectedAccountNumberId : "sltTradingAOSelectedAccountNumberId"
				, custodyCode : "tradingAOCustodyCode"
				, customerId : "tradingAOCustomerId"
				, netAccountValue : "tradingAONetAccountValue"
				, cashAvailable : "tradingAOCashAvailable"
				, txtCustomerId : "txtTradingAOCustomerId"
				, warningMsg : "tradingAOWarningChangeAccountMsg"
			}, options);
			
			var _accountNumber = "";
			var _accountName = "";			
			var _custodyCode = "";
			var _customerId = "";
			var _netAccountValue = "";
			var _cashAvailable = "";
			var _arrAddedAcc = new Array();
			
			if($.web_utils.isNotNull(activeAccountObj) && $.web_utils.isNotNull(activeAccountObj.activeAccount)) {
				var accountObj = activeAccountObj.activeAccount;
				
				_accountNumber = accountObj.accountNumber;
				_accountName = accountObj.accountName;
				_custodyCode = accountObj.custodyCode;
				_customerId = accountObj.customerId;
				_netAccountValue = accountObj.strNetAccountValue;
				_cashAvailable = accountObj.strCashAvailable;
				
				if($.web_utils.isNotNull(activeAccountObj.listAccount)) {
					var _listAcc = activeAccountObj.listAccount;
					var idx;
					for(idx in _listAcc) {
						_arrAddedAcc.push([_listAcc[idx].accountNumber, _listAcc[idx].accountNumber]);
					}										
				} else {
					if(DEBUG) {
						$.log('activeAccountObj.listAccount Empty....');
					}
				}
			} 
			//+++ update lable
			$("#" + opts.accountName + "_" + uuid).html(_accountName);
			$("#" + opts.custodyCode + "_" + uuid).html(_custodyCode);
			$("#" + opts.customerId + "_" + uuid).html(_customerId);
			$("#" + opts.txtCustomerId + "_" + uuid).val(_customerId);
			
			$("#" + opts.netAccountValue + "_" + uuid).html(_netAccountValue);
			$("#" + opts.cashAvailable + "_" + uuid).html(_cashAvailable);
			
			//+++ update combobox
			//$("#combobox_name").comboboxManager_removeAll();			
			$("#" + opts.selectedAccountNumberId + "_" + uuid).comboboxManager_removeAll();
			//++++ add account number to combobox
			if(_arrAddedAcc.length == 0) {
				_arrAddedAcc.push(["", "---"]);
			}
			$("#" + opts.selectedAccountNumberId + "_" + uuid).comboboxManager_addItems(_arrAddedAcc);
			//++++ focus to selected account
			$("#" + opts.selectedAccountNumberId + "_" + uuid).comboboxManager_focusValue(_accountNumber);
									
		} catch (e){
			if(DEBUG) {
				$.log('updateTradingAccountOverview:: ' + e);
			}
		}
	}	
};