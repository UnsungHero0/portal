<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<s:set var="commentaryNews" value="false" scope="action"></s:set>
<s:set var="brokerNews" value="true" scope="action"></s:set>
<s:set var="expertNews" value="true" scope="action"></s:set>
<s:set var="macroReport" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.ExpertIdeaTmpl">
	<tiles:putAttribute name="MainContent">
    	<div class="DivArticle" id="fCommentary_content"></div>		            
	</tiles:putAttribute>
</tiles:insertDefinition>