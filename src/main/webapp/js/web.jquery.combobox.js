jQuery(function($) {
	$.fn.comboboxManager = function(options) {
		return this.each(function() {
			return this;
		});
	};
	
	/**
	* remove all data of combobox
	* Use: $("#combobox_name").comboboxManager_removeAll();
	*/
	$.fn.comboboxManager_removeAll = function() {
		// alert("comboboxManager_removeAll...");
		return this.each(function() {
			// alert("this.tagName.toLowerCase():" + this.tagName.toLowerCase());
			if(this.tagName.toLowerCase() == 'select') {
				// process remove all item of select
				var size = this.length;
				var i;
				for(i = size -1; i>-1; i--) {
					this.remove(i);
				}
			}
			return this;
		});
	};
	
	/**
	* remove selected Items of combobox
	* Use: $("#combobox_name").comboboxManager_removeSelectedItems();
	*/
	$.fn.comboboxManager_removeSelectedItems = function() {
		// alert("comboboxManager_removeItems...");
		var valueList = '';
		this.each(function() {
					
			if(this.tagName.toLowerCase() == 'select') {
				// process remove items of select
				var size = this.length;
				
				var i;
				for(i = size -1; i>-1; i--) {
					if (this.options[i].selected) {
						//alert(': value = ' + this.options[i].value);
						valueList += this.options[i].value + ',';
						this.remove(i);
					}
				}
			}
		});
		return valueList;
	};
	
	/**
	* remove selected Items of combobox
	* Use: $("#combobox_name").comboboxManager_removeSelectedItems();
	*/
	$.fn.comboboxManager_getSelectedIds = function() {
		// alert("comboboxManager_removeItems...");
		var valueList = '';
		this.each(function() {
					
			if(this.tagName.toLowerCase() == 'select') {
				// process remove items of select
				var size = this.length;
				
				var i;
				for(i = size -1; i>-1; i--) {
					if (this.options[i].selected) {
						//alert(': value = ' + this.options[i].value);
						valueList += this.options[i].value + ',';
					}
				}
			}
		});
		return valueList;
	};
	
	/**
	* add all data to items
	* format of data is array of items
	* data = [["data1", "value1"], ["data2","value2"], ["data3","value3"]]
	* Use: $("#combobox_name").comboboxManager_addItems(data);
	*/
	$.fn.comboboxManager_addItems = function(data) {
		// alert("comboboxManager_addItems...");
		return this.each(function() {
			// alert("this.tagName.toLowerCase():" + this.tagName);			
			if(this.tagName.toLowerCase() == 'select') {
				// add all data to items
				var size = data.length;
				// alert("data.length:" + size);
				var i;
				for(i = 0; i<size; i++) {					
					var opt = document.createElement('option');									
					var optArr = data[i];
					opt.value = optArr[0];
					opt.text = optArr[1];											
					try {						
						// opt.innerHTML = optArr[1];
						this.add(opt, null); //// standards compliant
						//alert("opt.innerHtml:" + opt.innerHtml);						
					} catch (e) {						
						this.add(opt); // IE only
						//alert("opt.text:" + opt.text);
					}					
				}
			}
			return this;
		});
	};
		
	/**
	* set seleted value
	* Use: $("#combobox_name").comboboxManager_focusValue(val);
	*/
	$.fn.comboboxManager_focusValue = function(val) {
		return this.each(function() {
			if(this.tagName.toLowerCase() == 'select') {
				// process remove all item of select
				try {
					var size = this.length;
					var i;
					for(i = 0; i < size; i++) {
						if(this.options[index].value == val) {
							this.selectedIndex = i;
							return this;
						}
					}					
				} catch (e) {
				}
			}
			return this;
		});
	};		

	$.fn.comboboxManager_getSelectedValue = function() {
		return $("#" + $(this).attr("id") + " :selected").val();
	};
	
	$.fn.comboboxManager_getSelectedText = function() {
		return $("#" + $(this).attr("id") + " :selected").text();
	};
	
	$.fn.comboboxManager_toValueString = function(delim) {
		var arr = new Array();
		$("#" + $(this).attr("id") + " option").each(function() {
			arr.push(this.value);
		});
		return arr.join(delim == null ? ',' : delim);
	};
	
	$.fn.comboboxManager_toTextString = function(delim) {
		var arr = new Array();
		$("#" + $(this).attr("id") + " option").each(function() {
			arr.push(this.text);
		});
		return arr.join(delim == null ? ',' : delim);
	};
	
	$.fn.comboboxManager_hasValue = function(value) {
		var matches = $("#" + $(this).attr("id") + " option[value='" + value + "']");
		if (matches.length > 0) return true;
		return false;
	};
	
	$.fn.comboboxManager_hasValueStartWith = function(value) {
		var matches = $("#" + $(this).attr("id") + " option[value^='" + value + "']");
		if (matches.length > 0) return true;
		return false;
	};
	
	$.fn.comboboxManager_hasValueEndWith = function(value) {
		var matches = $("#" + $(this).attr("id") + " option[value$='" + value + "']");
		if (matches.length > 0) return true;
		return false;
	};
	
	$.fn.comboboxManager_getText = function(value) {
		var matches = $("#" + $(this).attr("id") + " option[value='" + value + "']");
		if (matches.length > 0) return $(matches[0]).text();
		return '';
	};
});