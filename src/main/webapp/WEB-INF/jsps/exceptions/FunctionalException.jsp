<%@page import="java.io.StringWriter"%>
<%@page import="java.io.PrintWriter"%>
<%
	Exception exception = (Exception) request.getAttribute("exception");
	StringWriter strWriter = new StringWriter();
	if(exception != null)
  		exception.printStackTrace(new PrintWriter(strWriter));
%>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="vndirectFmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- Begin Content -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
			<div ><img src="<web:url value='/images/img/banner_4.jpg'/>" width="763" height="190" /></div>
			<div class="tool_dautu pn_main learfix">
				<table cellpadding="0" cellspacing="0" border="0" width="762">
					<tr>
						<td valign="top" width="247">
							<div class="layout">
								<div class="top_inner clearfix">
									<div class="left fl"></div>
									<div class="right fr"></div>
								</div>
								<div class="center_inner ct_layout">
									<h5><b><a href="#">FunctionalException</a></b></h5>
									<p/>
									<div class="desc" id="heightcol1">
										<%=strWriter.toString() %>
									</div>
								</div>
								<div class="bottom_inner clearfix">
									<div class="left fl"></div>
									<div class="right fr"></div>
								</div>
							</div>
						</td>
						<td width="10"></td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>

