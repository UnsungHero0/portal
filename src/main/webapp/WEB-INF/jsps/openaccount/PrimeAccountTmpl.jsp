<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="account">
	<div class="title3 clearfix">
		<div class="tab_infor">
			<a href="#" class="select_tab"><b>PRIME ACCOUNT</b>
			</a>
		</div>

		<div style="border: 1px solid #616D7E; border-top: none;">
			<div class="center_inner_2 clearfix">
				<!---->
				<s:if test="%{#step1}">
				<div class="" style="padding: 12px 0 0 14px">
					<table cellpadding="0" cellspacing="0" border="0"
						class="table_1_prime">
						<tr>
							<td>
								<div style="padding: 5px 175px 5px 15px">
									<p>
										<b>Cảm ơn Quý khách đã lựa chọn loại hình tài khoản <font
											style="color: #0065c1">PRIME ACCOUNT!</font>
										</b>
									</p>
									<b style="color: #53a5fd">PRIME ACCOUNT</b> là loại hình tài
									khoản dành cho nhà đầu tư có nhu cầu sử dụng chuyên viên chăm
									sóc tài khoản riêng hỗ trợ thiết lập mục tiêu và lựa chọn chiến
									lược đầu tư phù hợp, cập nhật thông tin thị trường và tiếp cận
									thông tin về cơ hội đầu tư, hỗ trợ thực hiện các giao dịch mua
									bán và sử dụng các sản phẩm tài chính .
								</div>
							</td>
						</tr>
					</table>
				</div>
				</s:if>
				<s:if test="%{!#finish}">
				<!---->
				<div class="tableInfo clearfix" style="padding: 10px 5px">
					<s:if test="%{#step1}">
					<p>
						<b>Để mở tài khoản được nhanh chóng, chính xác, Quý khách vui
							lòng điền đầy đủ các thông tin được yêu cầu:</b>
					</p>
					</s:if>
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						class="tbl">
						<tr>
							<td
								style="background: url(../images/front/bgr_title_b1.gif) repeat-x left bottom">
								<div style="padding: 4px 10px">
									<b style="color: #ff831d">
										<tiles:insertAttribute name="title"></tiles:insertAttribute>
									</b>
								</div>
							</td>
						</tr>
						<tr>
							<td width="100%">
								<tiles:insertAttribute name="content"></tiles:insertAttribute>
							</td>
						</tr>
						<tr>
							<td>
								<s:if test="%{#tip}">
									<div class="tip">
										<b>Tip:</b> Những mục có dấu (
										<font style="color: red;">*</font>) là mục bắt buộc phải điền
									</div>
								</s:if>
								<s:if test="%{#tip1}">
									<div class="tip">
										<b>Tip:</b> Những thông tin trên đây không mang tính bắt buộc mà chỉ để chúng tôi tham khảo nhằm mục đích phục vụ quý khách tốt hơn.
									</div>
								</s:if>
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
				</div>
				</s:if>
				<s:else>
					<tiles:insertAttribute name="content"></tiles:insertAttribute>
				</s:else>
				<!---->
			</div>
			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>
	</div>

</div>
