<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
    buildLayout();
});

function buildLayout(){
	var cssClasses = new Object();
	var symbols = [];
    
	<c:if test="${not empty model.searchIfoNewsResult}">
		<c:forEach varStatus="i" var="news" items="${model.searchIfoNewsResult}">
		    symbols.push('${news.strSymbol}');
		    cssClasses['${news.strSymbol}'] = getCssClass('${i.count-1}');
		</c:forEach>
	</c:if>
	
	var html = '';
	if(symbols.length > 0){
	    symbols.sort();
	    
	    for(var i=0; i < symbols.length; i++){
	        html += '<a onclick="loadNewsestReport(' + "'" + symbols[i] + "'";
	        html += ');"' + ' class="' + cssClasses[symbols[i]] + '"';
	        html += '>' + symbols[i] + '</a>';
	    }
	}
    $('.topSharesMain').html(html);
}

/*function toRandom(arr){
    var newArr = [];
    
    var length = arr.length;
    while(length > 0) {
        var randomNumb = Math.floor(Math.random()*1000)%length;
        newArr.push(arr[randomNumb]);
        length--;
        arr.splice(randomNumb,1);
    }
    return newArr;
}*/

function getCssClass(index){
	
	if(typeof index == 'undefined'){
		return '';
	}
	
    if(index < 2){
        return 'first';
    } else if(index < 8){
    	return 'second';
    } else if(index < 15){
        return 'third';
    }
    
    return '';
}
</script>