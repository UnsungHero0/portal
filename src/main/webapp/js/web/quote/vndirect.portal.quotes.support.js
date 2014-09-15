$(document).ready(function(){
	//processFullSnapShot(createMockIdataForTestQuote());
	//loadMarketIndex(createMockIdataForTestIndex());
});

// Clone HighCharts.numberFormat function
defaultOptions = {
	lang : {
		loading : 'Loading...',
		months : [ 'January', 'February', 'March', 'April', 'May', 'June',
				'July', 'August', 'September', 'October', 'November',
				'December' ],
		shortMonths : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug',
				'Sep', 'Oct', 'Nov', 'Dec' ],
		weekdays : [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
				'Friday', 'Saturday' ],
		decimalPoint : '.',
		resetZoom : 'Reset zoom',
		resetZoomTitle : 'Reset zoom level 1:1',
		thousandsSep : ','
	}
};
function pInt(s, mag) {
	return parseInt(s, mag || 10);
}
function hsNumberFormat(number, decimals, decPoint, thousandsSep) {
	var lang = defaultOptions.lang,
	// http://kevin.vanzonneveld.net/techblog/article/javascript_equivalent_for_phps_number_format/
	n = number, c = decimals === -1 ? getDecimals(number)
			: (isNaN(decimals = Math.abs(decimals)) ? 2 : decimals), d = decPoint === undefined ? lang.decimalPoint
			: decPoint, t = thousandsSep === undefined ? lang.thousandsSep
			: thousandsSep, s = n < 0 ? "-" : "", i = String(pInt(n = Math.abs(
			+n || 0).toFixed(c))), j = i.length > 3 ? i.length % 3 : 0;

	return s + (j ? i.substr(0, j) + t : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}
// end clone

function FormatVolume10(number) {
	var vTemp = StringToInt(number) * 10;
	var vNumber = FormatCurrency(vTemp.toString(), ",", ".");
	return vNumber.substring(0, vNumber.length - 1);
}

function FormatVolume100(number) {
	var vTemp = StringToInt(number) * 10;
	var vNumber = FormatCurrency(vTemp.toString(), ",", ".");
	return vNumber;
}

// format price correctly
function processPrice(priceID, price, priceColor) {
	if (price == "ATO" || price == "ATC") {
		setCellAndColor(priceID, price, 'w');
	} else {
		setCellAndColor(priceID, price, priceColor);
	}
}
function processVolume(volumeID, volume, volumeColor) {
	setCellAndColor(volumeID, FormatVolume10(volume, 0), volumeColor);
}
function getClass(c, f, r, lastPrice) {
	var cl;
	if (lastPrice == '0.0') {
		cl = 'e';
	} else {
		if (lastPrice == c) {
			cl = 'c';
		} else if (lastPrice > r) {
			cl = 'i';
		} else if (lastPrice == r) {
			cl = 'e';
		} else if (lastPrice == f) {
			cl = 'f';
		} else {
			cl = 'd';
		}
	}
	return cl;
}
function setCellAndColor(cellID, nValue, cl, isHighLight) {
	var cell = $(cellID);
	var setHl = 0;
	if(cell){
		var pValue = cell.html();
		if (pValue != nValue) {	
			if ((nValue == '0') || (nValue == '0.0') || (nValue == '0.00')){
				if ((pValue == "") || (pValue == "&nbsp;")) {
					setHl = 0;
				}
				else{
					if (cell.html.length > 0){
						cell.html("");
						setHl = 1;
					}
				}
			}
			else{
				cell.html(nValue);
				setHl = 1;
				if(isHighLight){
					highLightCell(cell);
				}
			}
			if (setHl == 1){
				setColor(cellID, cl);
				if(isHighLight){
					highLightCell(cell);
				}
				return cell;
			}
		}
		else{
			var n = cellID.indexOf("lat");
			if (n>0){
				// neu la cot lat thi van update
				setColor(cellID, cl);
				return cell;	
			}
		}
	}
	
	setColor(cellID, cl);
	return null;
}
function setColor(cellID, cl) {
	var cell = $(cellID);
	removeAllClass(cell);
	cell.addClass(cl);
	
	return cell;
}
function getImageUrl(oIndex, cIndex) {
	var image;
	if (oIndex == 0) {
		image = 'iconyellow.gif';
	} else {
		if (oIndex == cIndex) {
			image = 'iconyellow.gif';
		} else if (oIndex > cIndex) {
			image = 'downred.gif';
		} else {
			image = 'upliam.gif';
		}
	}
	
	return $.web_resource.getContext() + '/images/icons/' + image;
}
/** return icon image css class, text-color css class */
function getCssClazzForHomepage(oIndex, cIndex) {
	var clazz;
	if (oIndex == 0) {
		clazz = 'icon-nochange,textnochange';
	} else {
		if (oIndex == cIndex) {
			clazz = 'icon-nochange,textnochange';
		} else if (oIndex > cIndex) {
			clazz = 'icon-dow,textdow';
		} else {
			clazz = 'icon-up,textup';
		}
	}
	
	return clazz;
}
function highLightCell(cell) {
	cell.addClass('hl2');
				
	setTimeout(function() {
		cell.removeClass('hl2');
	}, 1000);
}

function highlightCells(changedCells){
	for(var i = 0; i < changedCells.length; i++){
		if(changedCells[i] != null){
			changedCells[i].addClass('hl2');
		}
	}
	
	setTimeout(function() {
		for(var i = 0; i < changedCells.length; i++){
			if(changedCells[i] != null) {
				changedCells[i].removeClass('hl2');
			}
		}
	}, 1000);
}
function removeAllClass(cellS){
	try{
		var cell = cellS;
		if (cell != null){
			cell.removeClass('i');
			cell.removeClass('d');
			cell.removeClass('c ');
			cell.removeClass('f');
			cell.removeClass('e');	
		}
	}
	catch(e){
	}	
}
function StringToInt(pString)
{
    //Convert sang so he so 10
    var vInt = parseInt(pString,10);
    if (isNaN(vInt))
    {
        return 0;
    }
    else
    {
        return vInt;
    }
}
//Doi so xxxxxx.xxxx thanh dinh dang xxx,xxx.xxxx
// num: chuoi can dinh dang
// delimitor: dau dinh dang
// separate: dau phan cach phan nguyen va thap phan
function FormatCurrency(num, delimitor, separate){
    var sign; 
	num = num.toString().replace(/\$|\,/g,''); 
	if(isNaN(num)) 
		num = "0"; 
	sign = (num == (num = Math.abs(num))); 
	var str=num.toString();
	var arr_str = str.split(separate);
	if(arr_str.length > 1){
		var tail = new String(arr_str[1]);
		if(tail.length<2){
			tail =tail + '0';
		}
	}else{
		tail = '';
	}	
	num = arr_str[0];
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) 
		num = num.substring(0,num.length-(4*i+3))+ delimitor + num.substring(num.length-(4*i+3)); 
	
	if (tail=='')
		ret_value = (((sign)?'':'-') + num);
	else
		ret_value = (((sign)?'':'-') + num + separate + tail);
	return ret_value; 
}