<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<script type="text/javascript">
	function doFinish(){
		document.fRecoveUserInfoFinish.submit();
		return;
	}
</script>

<form name="fRecoveUserInfoFinish" id="fRecoveUserInfoFinish" method="post" action="<web:url value='/home.shtml'/>">
<div class="pn_main" id="page_TTNY">

 <!--Start Tab menu  -->
 <div class="tabs_TTNY" id="container-1" >
	<div style="clear:both"></div>
		<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
		<li class="ui-tabs-selected"><a href="#"><b><s:text name="web.label.userProfile.RecoverUserInfoFinish4FreeUser.tab.RecoverUserCerdentials">Recover User Cerdentials</s:text></b></a></li>
	   </ul>
	  <!--tabs 1-->
	  <div class="ctTab_TTNY" id="fragment-1" align="left">
		<div class="top_inner clearfix">
	  <div class="right fr"></div>
	  </div>
		<div class="center_inner_2 clearfix">
		<div class="center_inner">
		  <!---->
		  <div class="thirst_SS clearfix">
			<!--Recover User Info Finish-->
			<table class="nomal" cellpadding="0px" cellspacing="0px" style="width:100%; padding-left:5px; padding-right:5px;">
				<tr>
					<td class="all_border" style="padding:5px">
						<table class="nomal" cellpadding="1px" cellspacing="1px" style="width:100%">
							<tr>
								<td><B><c:out value='${model.messageFullName}'/></B><s:text name="web.label.userProfile.RecoverUserInfo.Message2VNDSUser.SendForEmail"/>
										<c:out value='${model.messageEmail}'/><s:text name="web.label.userProfile.RecoverUserInfo.Message2VNDSUser.Finish"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<table border="0" cellpadding="2" cellspacing="0" width="95%">
				<tr>
					<td align="center">
						<table class="nomal" cellpadding="0px" cellspacing="0px" align="center">
							<tr>
								<td style="width:10px;"/>
								<td>
								<span class="btn_left_calendar"><span class="btn_right_calendar"><span class="btn_center_calendar">
									<input type="button" value="<s:text name="web.label.userProfile.RecoverUserFinish.Button.Finish">Finish</s:text>" onclick="javascript:doFinish();">
								</span></span></span>
								</td>
								<td style="width:10px;"/>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!--End Recover User Info Finish-->
		 </div>
		 </div>
		 </div>
		 <div class="bottom_inner clearfix">
			<div class="left fl"></div>
			<div class="right fr"></div>
		</div>
	</div>
  </div>
</div>
<!--End Tab menu  -->
</form>