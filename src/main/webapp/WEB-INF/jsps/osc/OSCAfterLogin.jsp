<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%
	String stab = request.getParameter("stab");
	stab = (stab == null ? "0" : stab.trim());
	if("0;1;2;3;".indexOf(stab + ";") < 0) {
		stab = "0";
	}
 %>
<script type="text/javascript">
	$(function() {
		$('#container-1').tabs({
			selected: <%=stab%>,
			cache: true,
			select: function(event, ui) {
				if (ui.index == 0) {
					if (!IS_AUTH) {
						$('#osc_validation').dialog('open');
						return false;
					} else {
						loadTabOnlineServiceCenter();
					}
				} else if (ui.index == 1) {
					loadTabFAQs();
				} else if (ui.index == 2) {
					loadTabFormAndApplication();
				} else if (ui.index == 3) {
					loadTabContactUs();
				}
			}
		});
		var $tabs = $('#container-1').tabs();
		var selected = $tabs.tabs('option', 'selected'); // => 0
		if (selected == 0 && IS_AUTH) {
			loadTabOnlineServiceCenter();
		 } else if (selected == 1) {
			loadTabFAQs();
		 } else if (selected == 2) {
			loadTabFormAndApplication();
		 } else if (selected == 3) {
			loadTabContactUs();
		 }
	});

</script>

<s:form action="" id="fOSCAfterLogin" method="post">
<div id="main" class="clearfix">
	<table cellpadding="0" cellspacing="0" border="0" width="750">
		<tr>
			<td width="9">&nbsp;</td>
			<td valign="top">
				<!---->
				<div class="clearfix">					             		
	            	<div class="flash">
	                 	<object width="735" height="227">
							<param name="movie" value="<web:url value="flash/homedirect_2.swf"/>">
							<embed src="<web:url value="flash/homedirect_2.swf"/>" width="735" height="195"  type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
						</object>							                    		
	                </div>
				</div>				
				<div class="clearfix">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" height="157" style="background:url(<s:url value="/images/img/app_img_tbl_02.gif"/>) repeat-x">
						<tr><td width="3"><img src='<s:url value="/images/img/app_img_tbl_01.gif" />' width="3" height="157" /></td>
							<td><div style="margin-left:15px">
								<p style="font-size:18px; color:#fb6800"><s:text name="web.label.OSCAction.form.osc.need.help"/></p>
								<p><s:text name="web.label.OSCAction.form.osc.advertisment"/></p>
								<b style="color:#273795"><s:text name="web.label.OSCAction.form.osc.change.account"/></b><br />
								<b style="color:#273795"><s:text name="web.label.OSCAction.form.osc.change.services"/></b><br />
								<b style="color:#273795"><s:text name="web.label.OSCAction.form.osc.guide.manager.portfolio"/></b>
							 </div></td>
							<td width="350"><img src='<s:url value="/images/img/thum_10.jpg"/>' width="340" height="157" /></td>
							<td width="3"><img src='<s:url value="/images/img/app_img_tbl_03.gif"/>' width="3" height="157" /></td>
						</tr>
					</table>
				</div>
				<!---->
				<div class="pn_main" id="page_TTNY">
					<div class="tabs_TTNY" id="container-1" >
						<div style="clear:both"></div>
						<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
							<li class="ui-tabs-selected"><a href="#fragment-1"><b><s:text name="web.label.OSCAction.form.osc.guide.online.service"/></b></a></li>
							<li><a href="#fragment-2"><b><s:text name="web.label.OSCAction.form.osc.guide.question.answer"/></b></a></li>
							<li><a href="#fragment-3"><b><s:text name="web.label.OSCAction.form.osc.guide.download.document"/></b></a></li>
							<li><a href="#fragment-4"><b><s:text name="web.label.OSCAction.form.osc.guide.contact"/></b></a></li>
						</ul>
						<!-- tab1 -->
						<div class="ctTab_TTNY" id="fragment-1" align="left">
							<div id="fragment-1-main">
								<div class="top_inner clearfix">
									<div class="right fr"></div>
								</div>
								<div class="center_inner clearfix">
									<div class="smTable clearfix">
										<table cellpadding="0" cellspacing="0" border="0" width="100%" height="55" style="background:url(<s:url value='/images/img/app_small_tbl_02.gif' />) repeat-x">
											<tr><td width="3"><img src='<s:url value="/images/img/app_small_tbl_01.gif" />' width="3" height="55" /></td>
												<td width="64"><img src='<s:url value="/images/img/img_43.jpg" />' width="42" height="45" /></td>
												<td><div class="font19px"><s:text name="web.label.OSCAction.form.osc.guide.online.service"/></div>
													<b style="color:#ff6600"><s:text name="web.label.OSCAction.form.osc.guide.quick.answer"/></b>
												</td>
												<td width="3"><img src='<s:url value="/images/img/app_small_tbl_03.gif" />' width="3" height="55" /></td>
											</tr>
										</table>
									</div>
									<div class="tbl_After_Login clearfix">
										<table cellpadding="0" cellspacing="0" border="0" width="740" class="table_AL">
											<tr><td valign="top" width="240">
												<div class="layout_After_Login">
													 <div class="hd clearfix">
														<span class="hd-left"></span>
														<span class="hd-right"></span>
														<span class="title_AL clearfix"><b><s:text name="web.label.OSCAction.form.osc.updateYourInformation"></s:text></b></span>
													</div>

													<div class="padding0px">
														<div class="content_AL">
															<p class="label"><a href='<web:url value="/thong-tin-co-ban.shtml"></web:url>'><s:text name="web.label.OSCAction.form.osc.update.userprofile"/></a></p>
														</div>
														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
												</div></td>
												<td width="10"></td>
												<td valign="top" width="240"><div class="layout_After_Login">
													 <div class="hd clearfix">
														<span class="hd-left"></span>
														<span class="hd-right"></span>
														<span class="title_AL clearfix"><b><s:text name="web.label.OSCAction.form.osc.serviceYourAccount"></s:text></b></span>
													</div>

													<div class="padding0px">
														<div class="content_AL" id="listServiceYourAccount">
														</div>
														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
												</div></td>
												<td width="10"></td>
												<td valign="top" width="240"><div class="layout_After_Login">
													 <div class="hd clearfix">
														<span class="hd-left"></span>
														<span class="hd-right"></span>
														<span class="title_AL clearfix"><b><s:text name="web.label.OSCAction.form.osc.managerYourCash"></s:text></b></span>
													</div>

													<div class="padding0px">
														<div class="content_AL" id="listManageYourCash">
														</div>
														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
												</div></td>
											</tr>
											<tr><td colspan="3" valign="top">
													<div class="layout_After_Login">
													 <div class="hd clearfix">
														<span class="hd-left"></span>
														<span class="hd-right"></span>
														<span class="title_AL clearfix"><b><s:text name="web.label.OSCAction.form.osc.your.request"/></b></span>
													</div>

													<div class="padding0px">
														<div class="Your_Requests">
															<table cellpadding="0" cellspacing="0" border="0" width="100%" id="yourRequest">
																<thead>
																	<tr>
																		<td colspan="6">
																			<div class="label" style="margin:5px 0 5px 10px"><s:text name="web.label.OSCAction.form.osc.change.send.services.request"/></div>
																		</td>
																	</tr>
																	<tr bgcolor="#eaedf0"><td width="5%"></td>
																		<td width="35%"><s:text name="web.label.OSCAction.form.osc.your.recent.requests"/></td>
																		<td width="25%"><s:text name="web.label.OSCAction.form.osc.date"/></td>
																		<td width="15%"><s:text name="web.label.OSCAction.form.osc.account"/></td>
																		<td width="15%"><s:text name="web.label.OSCAction.form.osc.status"/></td>
																		<td width="5%"></td>
																	</tr>	
																</thead>
																<tbody>
																</tbody>															
															</table>
														</div>
														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
												</div>
												<!---->
												<%--
												<div class="Div_Your_Question pn_main clearfix">
													<table cellpadding="0" cellspacing="0" border="0" width="100%" height="63" style="background:url(<s:url value='/images/img/Div_Your_Question_img_center.jpg' />) repeat-x">
														<tr><td width="3"><img src='<s:url value="/images/img/Div_Your_Question_img_left.jpg" />' width="3" height="63"/></td>
															<td><div style="padding-left:15px">
																	<p class="label"><s:text name="web.label.OSCAction.form.osc.search.more"/></p>
																	<input type="text" size="30" value="Enter Your Question Here" class="text" />
																	<span class="btn_left_inbox">
																		<span class="btn_right_inbox">
																			<span class="btn_center_inbox">
																				<input type="button" value="Tim">
																			</span>
																		</span>
																	</span>
																</div>
															</td>
															<td width="3"><img src='<s:url value="/images/img/Div_Your_Question_img_right.jpg" />' width="3" height="63" /></td>
														</tr>
													</table>
												</div>
												 --%>
												<!---->
												</td>
												<td width="10"></td>
												<td valign="top" width="240">
													<div class="layout">
														<div class="top_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>

														<div class="quick_answers clearfix">
															<table cellpadding="0" cellspacing="0" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_40.jpg" />' /></a></td>
																	<td><b class="bluetext"><a href="#"><s:text name="web.label.OSCAction.form.osc.quick.answer"/></a></b><br /><span class="bluetext"><s:text name="web.label.OSCAction.form.osc.make.question"/></span></td>
																</tr>
															</table>
														</div>

														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
													<!---->
													<div class="layout pn_main">
														<div class="top_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>

														<div class="quick_answers clearfix">
															<table cellpadding="3" cellspacing="3" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_41.jpg" />' /></a></td>
																	<td><b class="bluetext"><a href="#"><s:text name="web.label.OSCAction.form.osc.education.demos"/></a></b><br /><span class="bluetext"><s:text name="web.label.OSCAction.form.osc.education.description"/></span></td>
																</tr>
															</table>
														</div>

														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
													<!---->
													<div class="layout pn_main">
														<div class="top_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>

														<div class="quick_answers clearfix">
															<table cellpadding="3" cellspacing="3" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_42.jpg" />' /></a></td>
																	<td><b class="bluetext"><a href="#"><s:text name="footer.label.contactUs"/></a></b><br /><span class="bluetext"><s:text name="footer.label.contactUs"/> VNDIRECT</span></td>
																</tr>
															</table>
														</div>

														<div class="bottom_inner clearfix">
															<div class="left fl"></div>
															<div class="right fr"></div>
														</div>
													</div>
												</td>
											</tr>
											<tr><td colspan="5">
												<div class="TryItNow clearfix">
													<table cellpadding="0" cellspacing="0" border="0" width="100%" height="55" style="background:url(<s:url value='/images/img/app_small_tbl_02.gif'/>) repeat-x scroll 0">
														<tr>
															<td width="3"><img src='<s:url value="/images/img/app_small_tbl_01.gif" />' width="3" height="55" /></td>
															<td align="center"><div class="font18px" style="padding-right:30px;padding-left:30px"><s:text name="web.label.OSCAction.form.osc.welcome"/></div></td>
															<td width="3"><img src='<s:url value="/images/img/app_small_tbl_03.gif" />' width="3" height="55" /></td>
														</tr>
													</table>
												</div>
											</td></tr>
										</table>
									</div>
								</div>
								<div class="bottom_inner clearfix">
									<div class="left fl"></div>
									<div class="right fr"></div>
								</div>
							</div> <!-- end of fragment-1-main -->						
						</div>
						<!--tab2-->
						 <div class="ctTab_TTNY" id="fragment-2" align="left">
							<div id="fragment-2-main">

								<div class="top_inner clearfix">
									<div class="right fr"></div>
								   </div>

								   <div class="center_inner clearfix">
									<div class="smTable clearfix">
										<table cellpadding="0" cellspacing="0" border="0" width="100%" height="55" style="background:url(<s:url value="/images/img/app_small_tbl_02.gif"/>) repeat-x">
											<tr><td width="3"><img src='<s:url value="/images/img/app_small_tbl_01.gif"/>' width="3" height="55" /></td>
												<td width="64"><img src='<s:url value="/images/img/img_43.jpg"/>' width="42" height="45" /></td>
												   <td><div class="font19px"><s:text name="web.label.OSCAction.form.osc.frequently.asked.question"/></div>
													<b style="color:#ff6600"><s:text name="web.label.OSCAction.form.osc.frequently.asked.help"/></b>
												   </td>
												<td width="3"><img src='<s:url value="/images/img/app_small_tbl_03.gif"/>' width="3" height="55" /></td>
											   </tr>
										   </table>
									   </div>
									   <div class="tbl_After_Login clearfix">
										   <table cellpadding="0" cellspacing="0" border="0" width="740" class="table_AL">
											   <tr><td colspan="3" valign="top">
											   			<div>
											   				<ul id="ulnews">
											   					<li><a href="<web:url value='/home/RedirectUrl.shtml?code=HomeDirectInfo&ticket=true'/>"><s:text name="web.label.OSCAction.form.osc.frequently.asked.HD_info"/><img src="<web:url value="/images/front/newnew.gif"/>"/></a></li>
											   				</ul>											   			
											   			</div>
														<div id="listFAQ"></div>
													</td>
													<td width="10"></td>
													<td valign="top" width="240">
													<div class="layout">
														<div class="top_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>

														   <div class="quick_answers clearfix">
															<table cellpadding="0" cellspacing="0" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_40.jpg"/>' /></a></td>
																		<td><b class="bluetext"><a href="#"><s:text name="web.label.OSCAction.form.osc.quick.answer"/></a></b><br /><span class="bluetext"><s:text name="web.label.OSCAction.form.osc.make.question"/></span></td>
																   </tr>
															   </table>
														   </div>

														   <div class="bottom_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>
													   </div>
													   <!---->
													   <div class="layout pn_main">
														<div class="top_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>

														   <div class="quick_answers clearfix">

															<table cellpadding="3" cellspacing="3" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_41.jpg"/>' /></a></td>
																	<td><b class="bluetext"><a href="#"><s:text name="web.label.OSCAction.form.osc.education.demos"/></a></b><br /><span class="bluetext"><s:text name="web.label.OSCAction.form.osc.education.description"/></span></td>
																   </tr>
															   </table>
														   </div>

														   <div class="bottom_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>
													   </div>
													   <!---->
													   <div class="layout pn_main">
														<div class="top_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>

														   <div class="quick_answers clearfix">
															<table cellpadding="3" cellspacing="3" border="0">
																<tr><td><a href="#"><img src='<s:url value="/images/img/img_42.jpg"/>' /></a></td>
																	<td><b class="bluetext"><a href="#"><s:text name="footer.label.contactUs"/></a></b><br /><span class="bluetext"><s:text name="footer.label.contactUs"/> VNDIRECT</span></td>
																   </tr>
															   </table>
														   </div>

														   <div class="bottom_inner clearfix">
															   <div class="left fl"></div>
															   <div class="right fr"></div>
														   </div>
													   </div>
												   </td>
											   </tr>
										   </table>
									   </div>
								   </div>

								   <div class="bottom_inner clearfix">
									<div class="left fl"></div>
									   <div class="right fr"></div>
								   </div>
							   </div> <!-- fragment-2-main -->
							   <%--<div id="fragment-2-ProductDetail">
							   </div>--%>
						   </div>
						<!--tab3-->
						<div class="ctTab_TTNY" id="fragment-3" align="left">
							<div class="top_inner clearfix">
									<div class="right fr"></div>
								</div>
								<div class="center_inner clearfix">
									<div class="smTable clearfix">
										<table cellpadding="0" cellspacing="0" border="0" width="100%" height="55" style="background:url(<s:url value="/images/img/app_small_tbl_02.gif"/>) repeat-x">
											<tr><td width="3"><img src='<s:url value="/images/img/app_small_tbl_01.gif"/>' width="3" height="55" /></td>
												<td width="64"><img src='<s:url value="/images/img/thum_11.gif"/>' height="47" width="64" hspace="7"  /></td>
												<td><div style="padding-left:10px">
													<s:text name="web.label.OSCAction.form.osc.FormAndApplication"/>
												</div></td>
												<td width="3"><img src='<s:url value="/images/img/app_small_tbl_03.gif"/>' width="3" height="55" /></td>
											</tr>
										</table>
									</div>
									<div class="clearfix">
										<div id="listFormAndApplication"></div>
									</div>
								</div>
								<div class="bottom_inner clearfix">
									<div class="left fl"></div>
									<div class="right fr"></div>
								</div>
						</div>
						<!--tab4-->
						<div class="ctTab_TTNY" id="fragment-4" align="left">
							<div class="top_inner clearfix">
								<div class="right fr"></div>
							</div>
							<div class="center_inner clearfix">
								<div class="center_inner clearfix">
									<div class="table_Market clearfix">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td><div id="listContactUs"></div></td>
											</tr>
										 </table>
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
				<!---->
			</td>
		</tr>
	</table>
</div>
</s:form>

<div id="osc_validation" title='Lưu ý' style="display: none;">
	<p><span>
		<s:text name="web.label.OSCAction.form.osc.for.customer"/>
	</span></p>
	<p><span>		
		<s:property value="%{getText('web.label.OSCAction.form.osc.login.openaccount', {'<web:url value="/osc/OSCAfterLogin.shtml" />','<web:url value="/mo-tai-khoan.shtml" />'})}" escape="false" />
	</span></p>
</div>