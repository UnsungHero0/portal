<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div class="boxnewhot_top" style="display: none;">
	<!-- Start Breadcrumbs -->
	<%-- <web:slideNews /> --%>
	<div class="newsContent"></div>
	
	<!-- Search news -->
	<div class="box_search_global">
		<input type="text" id="symbolSuggestionId"
			onfocus="if (this.value=='<s:text name="commons.symbolsearch.textbox"></s:text>') this.value='';"
			onblur="if (this.value=='') this.value='<s:text name="commons.symbolsearch.textbox"></s:text>';"
			value="<s:text name='commons.symbolsearch.textbox'></s:text>"
			class="input" name="">
		<input type="button" class="button" id="fHome_btnSymbolSearch"
			name="Enter" />
	</div>
</div>