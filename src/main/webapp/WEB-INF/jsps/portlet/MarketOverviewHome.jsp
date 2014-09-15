<%@ taglib uri="/struts-tags" prefix="s"%>


<online>
<status><c:out value="${portletNewsOnlineAJAXForm.status}" /></status>
<error-message><c:out value="${portletNewsOnlineAJAXForm.errorMsg}" /></error-message>
<items>
 <item name="newsContent"><![CDATA[
 <table cellpadding="0px" cellspacing="2px" width="100%">
 	<s:if test="%{model.display = 'short'}">
	
	</s:if>
	<s:elseif test="%{model.display = 'Home'}">
	</s:elseif>
	<s:elseif test="%{model.display = 'Snapshot'}">
	</s:elseif>
	<s:else>
	
	</s:else> 
 	
</table>
 ]]></item>
</items>
</online>