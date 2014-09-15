<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="content_ttpt" style="min-height: 500px;">
	<div class="content_tab">
		<div class="center_inner_2 clearfix content_ttpt" id="account">
			<s:if test="%{!#finish}">
				<div class="tableInfo clearfix box_content_mtk" style="">
					<h3 class="title_b">
						<span><tiles:insertAttribute name="title"></tiles:insertAttribute>
						</span>
					</h3>
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl">
						<tr>
							<td width="100%">
								<tiles:insertAttribute name="content"></tiles:insertAttribute>
							</td>
						</tr>
						<tr>
							<td>
								<s:if test="%{#tip}">
									<div class="tip">
										<b>Tip:</b>
										<s:text name="web.label.online.account.tip1">
											<s:param>
												<font style="color: red;">*</font>
											</s:param>
										</s:text>
									</div>
								</s:if>
								<s:if test="%{#tip1}">
									<div class="tip">
										<b>Tip:</b>
										<s:text name="web.label.online.account.tip2"></s:text>
									</div>
								</s:if>
								<div align="center">
									<input type="button" class="iButton" style="width:88px;" id="backButton"
										onclick="$('#back').submit()"
										value='&laquo;&nbsp;<s:text name="web.label.online.account.back">Quay lại</s:text>'>
									&nbsp;&nbsp;&nbsp;
									<input type="button" class="iButton" style="width:88px;" id="nextButton"
										onclick="$('#next').submit()"
										value='<s:text name="web.label.online.account.continue">Tiếp tục</s:text>&nbsp;&raquo;'>
								</div>
							</td>
						</tr>

					</table>
				</div>
			</s:if>

			<!-- not finish -->
			<s:else>
				<tiles:insertAttribute name="content"></tiles:insertAttribute>
			</s:else>
		</div>
		<!-- end left -->

		<!-- right -->
		<jsp:include
			page="/WEB-INF/jsps/openaccount/snippet/openAccountRightColumn.jsp"></jsp:include>
		<!-- End right -->
	</div>
</div>
