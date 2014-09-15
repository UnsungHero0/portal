<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<%-- 
<link rel="stylesheet" href="../../../css/web/default/layout.css" type="text/css" media="screen" />	
<link rel="stylesheet" href="../../../css/web/default/common.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/thickbox.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/jquery.ui/themeroller/ui.all.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/jquery.ui/vtabs/v.ui.tabs.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/jquery.ui/vtabs/v.ui.tabs-yui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../../../css/jquery.menu/jquery.jdMenu.css" type="text/css" media="screen" />
--%>

<br/><br/>
<table  class="tb">
	<colgroup>
		<col width="25%"/>
		<col width="50%"/>
		<col width="25%"/>
	</colgroup>
  <tr>
  	<td>&nbsp;</td>
    <td>
	    <div class="BoxContainer" align="left">
			<div class="TitleBoxLeft" style="width: 120px">
		        <div class="TitleBoxRight">
		            <div class="TitleBoxMiddle" style="text-align: left; padding-left: 5px;">
		            <s:text name="web.label.login.legend.title">Search School Year</s:text>
		            </div>
		        </div>
		    </div>
			<div class="BoxContent">
		        <fieldset style="margin: 0px 0px 0px 0px">
		        	<c:if test="${not empty param.error}">
					  <font color="red">
					  	<s:text name="web.label.login.fail">Your login attempt was not successful, try again!</s:text><br/>
					    <s:text name="web.label.login.reason">Reason</s:text> :<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
					  </font>					  
					</c:if>										
					
					<form id="fLogin" name="fLogin" action="Login.htm" method="post">
						<input type="hidden" name="_spring_security_remember_me" value="1"/>
						<input type="hidden" name="action" id="action" value="" />
												
						<table width="100%" cellpadding="0px" cellspacing="2px" border="0px">
							<colgroup>					
								<col width="25%" />
								<col width="75%" />							
							</colgroup>
							<tbody>
								<tr>
									<td align="right">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><s:text name="web.label.login.form.username">User Name</s:text> :</td>
									<td><input type="text" id="fLogin_username"  name="j_username" value="" class="Text150" /></td>
								</tr>
								<tr>
									<td><s:text name="web.label.login.form.password">New Password</s:text> <span class="Require">*</span>:</td>
									<td><input type="password" id="fLogin_password" name="j_password" value="" class="Text150" /></td>
								</tr>
								<tr>
									<td><s:text name="web.label.login.form.captcha">Security Image</s:text> <span class="Require">*</span>:</td>
									<td><input type="text" id="fLogin_captchaSec" name="strCaptchaSec" value="" class="Text100" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>
										<img alt="Captcha Sec" src="<web:url value='/online/download/home/CaptchaImage.shtml'/>"/>
										<%-- 										
										<br/><img alt="Captcha Sec" src="<web:url value='/online/download/home/Captcha4Login.shtml'/>"/>										
										<br/>For Project: <img alt="Captcha Sec" src="<web:url value='/online/download/home/Captcha4ProjSubmission.shtml'/>"/>
										<br/>For Comments: <img alt="Captcha Sec" src="<web:url value='/online/download/home/Captcha4Comments.shtml'/>"/>
										--%>
									</td>
								</tr>								
								<tr>
									<td align="right">&nbsp;</td>
									<td>
										<br/>
										<input id="fLogin_btnLogin" type="button" value="<s:text name='button.login'/>" class="NormalButton" />							
									</td>
								</tr>
								<tr>
									<td align="right">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
					</form>
				</fieldset>
		    </div>                	    
		</div>
    </td> 
    <td>&nbsp;</td>   
  </tr>
</table>
<br/><br/>