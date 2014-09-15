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
									<h5><b><a href="#">Redirect URL</a></b></h5>
									<p/>
									<div class="desc" id="heightcol1">
										<c:choose>
											<c:when test="${not empty param.fwd}">
												<c:choose>
													<c:when test="${not empty param.ticket}">
														<web:url var="urlMapping" value="/home/ForwardSecUrl.shtml" includeParam="true" removeParams="ticket;"/>
													</c:when>
													<c:otherwise>
														<web:url var="urlMapping" value="${param.fwd}" includeParam="true" removeParams="ticket;fwd;"/>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<web:url var="urlMapping" value="/home.shtml"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<a href="${urlMapping}"> <c:out value="${urlMapping}" default="Redirect URL..."/></a>
								<script type="text/javascript">
									function goPage() {
										var url = '${urlMapping}';
										if(url.length > 0) {
											window.location = url;
										}
									}
									setTimeout("goPage();",100);
								</script>
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

