<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<s:set name="powerRatingDef" value="true" scope="action"></s:set>
<s:set name="powerRatingAdv" value="true" scope="action"></s:set>
<s:set name="powerRatingUser" value="true" scope="action"></s:set>
<s:set name="powerRatingMenu" value="false" scope="action"></s:set>

<tiles:insertDefinition name="Analysis.PowerRatingTmpl">
	<tiles:putAttribute name="MainContent">
					
		<tiles:insertDefinition name="Analysis.PowerRatingDetailTmpl"></tiles:insertDefinition>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

