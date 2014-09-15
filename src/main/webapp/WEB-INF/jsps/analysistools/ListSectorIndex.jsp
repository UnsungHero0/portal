<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
<!--
	var SECTOR = '<s:property value="@vn.com.vndirect.domain.struts2.analysistools.ListSectorModel@BY_SECTOR"/>';
	var ALPHABETICAL = '<s:property value="@vn.com.vndirect.domain.struts2.analysistools.ListSectorModel@ALPHABETICAL"/>';
//-->

    function gotoSelector(codeSelector) { 
           $('#listSector #sectorCodeSelected').attr('value', codeSelector);
           $('#listSector').submit();
           
    }

</script>

<s:set var="btnClassification" value="false" scope="action"></s:set>
<s:set var="btnIndex" value="true" scope="action"></s:set>
<s:set var="btnOverview" value="true" scope="action"></s:set>


<form method="post" id="listSector" action="<web:url value="/phan-tich-nganh/chi-so-nganh.shtml"/>">    
    <input type="hidden" name="sectorCodeSelected" id="sectorCodeSelected" value="4" />
</form>

<tiles:insertDefinition name="Analysis.FundamentalityTmpl">
	<tiles:putAttribute name="MainContent">

		<div class="box_250" id="industriesL">
		</div>
		<div class="box_215" id="industriesC">
        </div>
        <div class="box_215" id="industriesR">
        </div>        
	    
	    
		<div id="listOfSectors" class="content_phantichnganh">
			<table cellpadding="3" cellspacing="1" border="0" width="100%" align="left">
				<tbody>
				
				</tbody>
			</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
