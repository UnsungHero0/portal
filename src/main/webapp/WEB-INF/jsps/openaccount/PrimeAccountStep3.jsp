<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="account">
	<div class="title3 clearfix">
		<div class="tab_infor">
			<a href="#" class="select_tab"><b>PRIME ACCOUNT</b> </a>
		</div>
		<div style="border: 1px solid #616D7E; border-top: none;">
			<div class="center_inner_2 clearfix">
				<!-- -->
				<div class="firstline_sector_prime">
					<div class="number">
						<s:text name="web.label.online.account.account.step3.ad"></s:text>
					</div>
				</div>
				<!--e:-->

				<div class="tableInfo clearfix" style="padding: 10px 5px">
					
					<%-- Back Form --%>
					<form method="post" id="back" action="<web:url value="/account/PrimeAccountStep2.shtml"/>">
						<s:hidden id="cacheData" name="cacheData"></s:hidden>
					</form>
					<%-- --%>
					
					<%-- --%>
					<form method="post" id="next" action="<web:url value="/account/PrimeAccountStep4.shtml"/>">
					<s:hidden id="cacheData" name="cacheData"></s:hidden>
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl">
						<tr>
							<td
								style="background: url(../images/front/bgr_title_b1.gif) repeat-x left bottom">
								<div style="padding: 4px 10px">
									<b style="color: #ff831d">
										<s:text name="web.label.online.account.prime.account.step3"></s:text>
									</b>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="clearfix" style="padding: 10px 0">
									<table cellpadding="2" cellspacing="2" border="0">
										<tr>
											<td>
												<input type="radio" class="option" checked="checked">
											</td>
											<td>
												<b class="uppercase">
													<s:text name="web.label.online.account.vndirect.appointed.care.workers.account"></s:text>
												</b>
											</td>
										</tr>
										<tr>
											<td>
												<input type="radio" class="option" disabled="disabled">
											</td>
											<td>
												<b class="uppercase">
													<s:text name="web.label.online.account.choose.a.staff.member.accounts"></s:text>
												</b>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
												<input type="button" onclick="$('#back').submit()" value="&laquo;&nbsp;Quay lại">
											</span> 
										</span> 
									</span>
									<span class="btn_left_inbox"> 
										<span class="btn_right_inbox"> 
											<span class="btn_center_inbox">
												<input type="button" onclick="$('#next').submit()" value="Tiếp tục&nbsp;&raquo;">
											</span> 
										</span> 
									</span>
								</div>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<!---->
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
	</div>
</div>
