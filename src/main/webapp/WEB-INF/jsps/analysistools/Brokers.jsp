<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<s:set var="commentaryNews" value="true" scope="action"></s:set>
<s:set var="brokerNews" value="false" scope="action"></s:set>
<s:set var="expertNews" value="true" scope="action"></s:set>
<s:set var="macroReport" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.ExpertIdeaTmpl">
	<tiles:putAttribute name="MainContent">
    	<div class="DivArticle" id="fBroker_content"></div>
	</tiles:putAttribute>
</tiles:insertDefinition>