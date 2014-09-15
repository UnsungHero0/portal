/*method IndexOf of Array*/
if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(searchElement, fromIndex) {
		var i, pivot = (fromIndex) ? fromIndex : 0, length;

		if (!this) {
			throw new TypeError();
		}

		length = this.length;

		if (length === 0 || pivot >= length) {
			return -1;
		}

		if (pivot < 0) {
			pivot = length - Math.abs(pivot);
		}

		for (i = pivot; i < length; i++) {
			if (this[i] === searchElement) {
				return i;
			}
		}
		return -1;
	};
}

if (!console || !console.log){
	console = {
		log: function(){
			return;
		}
	};
}
/*
console.error = function(){};
console.exception = function(){};*/

if (!Function.prototype.bind) {
	Function.prototype.bind = function(oThis) {
		if (typeof this !== "function") {
			// closest thing possible to the ECMAScript 5 internal IsCallable
			// function
			throw new TypeError(
					"Function.prototype.bind - what is trying to be bound is not callable");
		}

		var aArgs = Array.prototype.slice.call(arguments, 1), fToBind = this, fNOP = function() {
		}, fBound = function() {
			return fToBind.apply(this instanceof fNOP && oThis ? this : oThis,
					aArgs.concat(Array.prototype.slice.call(arguments)));
		};

		fNOP.prototype = this.prototype;
		fBound.prototype = new fNOP();

		return fBound;
	};
}

//From
//https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/keys
if (!Object.keys) {
	Object.keys = (function() {
		'use strict';
		var hasOwnProperty = Object.prototype.hasOwnProperty, hasDontEnumBug = !({
			toString : null
		}).propertyIsEnumerable('toString'), dontEnums = [ 'toString',
				'toLocaleString', 'valueOf', 'hasOwnProperty', 'isPrototypeOf',
				'propertyIsEnumerable', 'constructor' ], dontEnumsLength = dontEnums.length;

		return function(obj) {
			if (typeof obj !== 'object'
					&& (typeof obj !== 'function' || obj === null)) {
				throw new TypeError('Object.keys called on non-object');
			}

			var result = [], prop, i;

			for (prop in obj) {
				if (hasOwnProperty.call(obj, prop)) {
					result.push(prop);
				}
			}

			if (hasDontEnumBug) {
				for (i = 0; i < dontEnumsLength; i++) {
					if (hasOwnProperty.call(obj, dontEnums[i])) {
						result.push(dontEnums[i]);
					}
				}
			}
			return result;
		};
	}());
}