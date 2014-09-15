<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<s:set var="commentaryNews" value="true" scope="action"></s:set>
<s:set var="brokerNews" value="true" scope="action"></s:set>
<s:set var="expertNews" value="false" scope="action"></s:set>
<s:set var="macroReport" value="true" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.ExpertIdeaTmpl">
	<tiles:putAttribute name="MainContent">
		<form name="fExperts" id="fExperts" method="post">
			<input type="hidden" id="fExperts_indexPage" name="fExperts_indexPage" value="1"/>

			<div style="border:0px solid #616D7E; border-top:none;">
				<div class="center_inner clearfix">
		            <div class="clearfix" style="padding:5px" id="fExperts_content"></div>
		            <div id="web_navExperts"></div> 
		            <%-- Paging --%>
		        </div>
			</div>
		</form>

	</tiles:putAttribute>
</tiles:insertDefinition>