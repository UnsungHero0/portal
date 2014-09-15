<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<s:form action="thong-tin-giao-dich" namespace="/" method="POST">

	<div id="content_ttpt">
	    <ul class="ui-tabs-nav tab_ttpt" id="container-thongtincanhan">
	        <c:choose>
	            <c:when test="${isFreeUser}">
	                <li class=""><a href="<web:url value="/thong-tin-co-ban-tai-khoan-mien-phi.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
	            </c:when>
	            <c:otherwise>
	                <li class=""><a href="<web:url value="/thong-tin-co-ban.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.basic"/></a></li>
	            </c:otherwise>
	        </c:choose> 	        
	        <li class="ui-tabs-selected"><a href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label class="icon_active"></label><s:text name="myPage.infor.trading"/></a></li>
	    </ul>
	</div>
	<div class="clear"></div>    
        <div id="tab-1" class="ui-tabs-container" style=""> 
        <!--++ content left-->        
        <div class="content_mypage" id="page2">
            <div style="margin-top: 30px; text-align: center;">
                <b><font size = "5"><s:text name="successKey"></s:text></font></b>
            </div>    
            <div style="text-align: center; margin-top: 10px;">	            
	            <input type="submit" id="fChangeTeleTradeFones_change" class="iButton" value='<s:text name="web.button.userProfile.Continues"/>'>
            </div>                  
        </div>        
        <!--//end content left-->
        
        <!--++c column-->
        <div class="width315" id="c-column">
            <div class="boxcontentcolumn">
                <img src='<s:url value="/images/thumbs/img_qc.png" />'/>
            </div>
            <div class="boxcontentcolumn">
                <ul>
                    <li>
                        <span class="icon_question_fast left"></span>
                        <div class="box_text_tienich line">
                            <a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text name="myPage.leftColumn.qA"></s:text></a>
                        </div>
                    </li>
                    <li>
                        <span class="icon_guide left "></span>
                        <div class="line box_text_tienich">
                            <a href="<web:url value="/tro-giup/hoi-dap-huong-dan.shtml"/>"><s:text name="myPage.leftColumn.guide"></s:text></a>
                        </div>
                    </li>
                     <li>
                        <span class="icon_logo_vnd left"></span>
                        <div class="box_text_tienich">
                            <a href="<web:url value="/vndirect/san-pham-dich-vu.shtml"/>"><s:text name="myPage.leftColumn.newsVnds"></s:text></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!--//c column-->
    </div>
    
    
<!-- Begin Content -->
<%--<table width="750"><tr><td><div class="tab clearfix">
	<div class="tab_infor">
		<a href='<web:url value="/thong-tin-co-ban.shtml"/>' class="select_tab"><b><s:text name="web.label.userProfile.tab.myInfo"></s:text></b> </a>
	</div>
	<div style="border: 1px solid #616D7E;border-top: none;">
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<tr>
				<td valign="top">
					<table class="n-online" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th style="border-top:#fff solid 1px;">
								<table width="550" align="center" style="margin-bottom:15px; background-color:#FFFFFF;" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><div class="bg2"></div>
										<div style="padding-left:30px;">
											<br />
											<table width="85%" align="center" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td valign="middle">
														<table style="text-align: left;" cellpadding="1" cellspacing="4" width="100%">
															<tbody>
																	<tr>
																	<td colspan="2" align="center">&nbsp;</td>
																	</tr>
																<tr>
																	<td colspan="2" align="center"><b><font size = "5"><s:text name="successKey"></s:text></font></b></td>
																</tr>
																<tr>
																	<td colspan="2" align="center">&nbsp;</td>
																	</tr>
																<tr>
																	<td colspan="2" align="center">
																		<span class="btn_left_inbox"> <span class="btn_right_inbox"> <span class="btn_center_inbox">
																			<input type="submit" id="fChangeTeleTradeFones_change" value='<s:text name="web.button.userProfile.Continues"/>'>
																		</span></span></span>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</table>
											<br />
										</div>
										</td>
									</tr>
								</table>
							</th>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div></div></td></tr>
</table>
--%></s:form>