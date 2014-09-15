/*
* @author: TungNQ (tungnq.nguyen@yahoo.com)
*/

jQuery(function($) {		   
   // your code using $ alias here
   $.fn.tableManager = function(options) {
   		// Extend our default options with those provided.
   		this.opts = $.extend($.fn.tableManager.defaultOptions, options);   								      
		// Our plugin implementation code goes here.
		return this.each(function() {
			return this;
		});
   };
   
   /**
    * expose options
    */
    $.fn.tableManager.defaultOptions = {
		totalCell: 0,
		insertRowLast: true,
		trClasses: "",
		cellClasses: {},
		colSpan: {}
	};
    
   /**
   * add new row to table
   */     
   $.fn.tableManager_addRow = function(data, options) {
   		var defaultOptions = {
			totalCell: 0,
			insertRowLast: true,
			trClasses: "",
			cellClasses: {},
			colSpan: {}
		};
   		var opts = $.extend(defaultOptions, options);   		
   		return this.each(function() {
   			// alert(this + "---" + this.id);
   			var row = null;
   			
   			if($.web_utils.isNotNull(this.tBodies[0])) {
	   			row = this.tBodies[0].insertRow(opts.insertRowLast ? -1 : 0);
	   		} else {
	   			row = this.insertRow(opts.insertRowLast ? -1 : 0);
	   		}
   			
   			if($.web_utils.isNotNull(opts.trClasses)) {
   				row.className = opts.trClasses;
   			}
   			
	   		for(var i=0; i<opts.totalCell; i++) {
	   			var cell=row.insertCell(i);
	   			cell.innerHTML = data[i];
	   			if($.web_utils.isNotNull(opts.cellClasses[i])) {
	   				cell.className = opts.cellClasses[i];
	   			}
	   			if($.web_utils.isNotNull(opts.colSpan[i]) && opts.colSpan[i] > 0) {
	   				cell.colSpan = opts.colSpan[i];
	   			}
	   		}
	   		return this;
   		});
   };
   
   $.fn.tableManager_addColspanRow = function(data, options) {
	   var defaultOptions = {
			totalCell: 0,
			insertRowLast: true,
			trClasses: "",
			cellClasses: {},
			colSpan: {}
		};
  		var opts = $.extend(defaultOptions, options);   		
  		return this.each(function() {
  			// alert(this + "---" + this.id);
  			var row = null;
  			
  			if($.web_utils.isNotNull(this.tBodies[0])) {
	   			row = this.tBodies[0].insertRow(opts.insertRowLast ? -1 : 0);
	   		} else {
	   			row = this.insertRow(opts.insertRowLast ? -1 : 0);
	   		}
  			
  			if($.web_utils.isNotNull(opts.trClasses)) {
  				row.className = opts.trClasses;
  			}
  			
	   		for(var i=0; i<opts.totalCell; i++) {
	   			var cell=row.insertCell(i);
	   			cell.innerHTML = data[i];
	   			if($.web_utils.isNotNull(opts.cellClasses[i])) {
	   				cell.className = opts.cellClasses[i];
	   			}
	   			if($.web_utils.isNotNull(opts.colSpan[i]) && opts.colSpan[i] > 0) {
	   				cell.colSpan = opts.colSpan[i];
	   			}
	   		}
	   		
	   		return this;
  		});
  };
   /**
   * delete a row from tables
   */
   $.fn.tableManager_deleteRow = function(index) {
		return this.each(function() {
			// alert(this + "---" + this.id);
			if($.web_utils.isNotNull(this.tBodies[0])) {
				this.tBodies[0].deleteRow(index);
			} else {
				this.deleteRow(index);
			}
			return this;
		});
   };
   
   /**
   * delete all row from table
   */
   $.fn.tableManager_deleteAllRow = function() {
		return this.each(function() {
			var rowParent = null;
			// alert(this + "---" + this.id);
			if($.web_utils.isNotNull(this.tBodies[0])) {
				rowParent = this.tBodies[0];
			} else {
				rowParent = this;
			}
			// alert("row length:" + rowParent.rows.length);
			while(rowParent.rows.length > 0) {
				rowParent.deleteRow(0);
			}
			return this;
		});
   };   
 });