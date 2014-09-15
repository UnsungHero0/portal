<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#ExecuteError_btnClose").click(function(){
		try {
			self.parent.tb_remove();
		} catch (e) {
		}
	});
});
//-->
</script>

<br/><br/>
<div style="color: red; text-align: center">
	<s:text name="commons.error.executeError">Can not execute this functionality!</s:text>
</div>
<br/><br/>
<div style="text-align: center">
	<input id="ExecuteError_btnClose" type="button" value="<s:text name='button.close'/>" class="NormalButton"/>
</div>