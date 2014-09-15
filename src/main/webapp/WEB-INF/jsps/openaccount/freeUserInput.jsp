<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
	function doSave() {
		document.fFreeRegistedUsers.submit();
		return;
	}
</script>
<web:url value="/mo-tai-khoan-mien-phi/dang-ky.shtml" var="urlCreateFreeUsersAccount"></web:url>
<div id="content_ttpt">
	<jsp:include page="/WEB-INF/jsps/openaccount/snippet/openAccountTop.jsp"></jsp:include>
	
	<div class="content_small">
		<div class="content_tab" id="tab-1"></div>
		<form name="fFreeRegistedUsers" id="fFreeRegistedUsers" action="${urlCreateFreeUsersAccount}" method="post">
			<div class="content_tab content_taikhoan " id="tab-2">
				<p>
					Tài khoản không giao dịch chứng khoán tại VNDIRECT nhưng có thể tiếp
					cận tra cứu các thông tin chứng khoán hữu ích do VNDIRECT cung cấp
					và quản lý danh mục để đầu tư hiệu quả:<br /> - Xem thông tin Chứng
					khoán các báo cáo thống kê thị trường<br /> - Xem các tính năng và
					dịch vụ chứng khoán của VNDIRECT<br />
				</p>
				<div class="clear"></div>
				<div class="content_left_635">
					<h2 class="title">
						<span>THÔNG TIN ĐĂNG KÝ</span>
					</h2>
					<ul class="list_dktk">						
						<li>
							<s:actionerror cssStyle="color: red; display: inline; text-align: left; font-weight: bold;"></s:actionerror>
						</li>
						<li>
							<input type="checkbox" class="input_chk" id="fFreeRegistedUsers_agreement" name="agreement" value="agreement" checked="checked" style="display: none;"/>
						</li>
						<li> 
						<span class="left margin-right15"> <strong>Tên đầy đủ</strong><br />
								<input type="text" class="input170" id="fFreeRegistedUsers_fullName" name="onlineUser.fullName" value="<c:out value='${model.onlineUser.fullName}'/>" style="width: 273px;"/>																	
						</span>
							<div class="note_dk">Lưu ý: Quý khách vui lòng ghi họ tên đầy đủ.</div>
						</li>
						<li>
							<div class="rowa">Giới tính:</div>
							<div class="rowc">							
								<c:choose>
									<c:when test="${onlineUser.sex=='MALE'}">
										<input type="radio" name="onlineUser.sex" value="MALE" class="styled" checked="checked"/> <label>Nam</label> 
										<input type="radio" name="onlineUser.sex" value="FEMALE" class="styled" /> <label>Nữ</label>
									</c:when>
									<c:when test="${onlineUser.sex=='FEMALE'}">
										<input type="radio" name="onlineUser.sex" value="MALE" class="styled" /> <label>Nam</label> 
										<input type="radio" name="onlineUser.sex" value="FEMALE" class="styled" checked="checked"/> <label>Nữ</label>
									</c:when>
									<c:otherwise>
										<input type="radio" name="onlineUser.sex" value="MALE" class="styled" /> <label>Nam</label> 
										<input type="radio" name="onlineUser.sex" value="FEMALE" class="styled" /> <label>Nữ</label>									
									</c:otherwise>
								</c:choose>						
							</div>
							<div class="rowb">Ngày sinh:</div>
							<div class="left">
								<input type="text" class="input100" id="fFreeRegistedUsers_dateofBirth" name="onlineUser.strDob" value="<c:out value='${model.onlineUser.strDob}'/>" />
							</div>
						</li>
	
						<li>
							<div class="rowa">Email cá nhân:</div>
							<div class="rowc">
								<input type="text" maxlength="250" class="input170" id="fFreeRegistedUsers_email" name="onlineUser.email" value="<c:out value='${model.onlineUser.email}'/>" />
							</div>
							<div class="rowb">Điện thoại Di động:</div>
							<div class="left">
								<input type="text" maxlength="250" class="input170" id="fFreeRegistedUsers_telephone" name="onlineUser.telephone" value="<c:out value='${model.onlineUser.telephone}'/>" />							
							</div>
	
						</li>
						<li class="margin-top40">
							<div class="rowd">Mật khẩu đăng nhập:</div>
							<div class="left">
								<input type="password" maxlength="250" class="input170" id="fFreeRegistedUsers_passWord" name="passWord" value="" />
							</div>
	
						</li>
						<li>
							<div class="rowd">Xác nhận mật khẩu:</div>
							<div class="left">
								<input type="password" maxlength="250" class="input170" id="fFreeRegistedUsers_confirmPass" name="confirmPass" value="" />
							</div>
	
						</li>
						<li class="margin-top40">
							<div align="center">
								<input type="submit" class="button" id="fFreeRegistedUsers_btSave" value='Mở tài khoản'>                       		
							</div>
						</li>
					</ul>
				</div>
				<!--right-->
				<div class="box_right_lichsu">
					<h2>
						<span>TÀI KHOẢN MIỄN PHÍ</span>
					</h2>
					<ul class="list_taikhoan motaikhoanmienphi">
						<li><strong>Sàng lọc cổ phiếu</strong><br /> Tìm kiếm cổ
							phiếu dựa theo các tiêu chí riêng:</li>
						<li><input type="checkbox" name="1" class="styled" checked="checked" disabled="disabled"/> <label>Phân
								đoạn thị trường</label></li>
						<li><input type="checkbox" name="2" class="styled" checked="checked" disabled="disabled"/> <label>Giá
								& Khối lượng</label></li>
						<li><input type="checkbox" name="3" class="styled" checked="checked" disabled="disabled"/> <label>Phân
								tích cơ bản</label></li>
						<li><input type="checkbox" name="4" class="styled" checked="checked" disabled="disabled"/> <label>Doanh
								thu & Cổ tức</label></li>
						<li><input type="checkbox" name="5" class="styled" checked="checked" disabled="disabled"/> <label>Phân
								tích kỹ thuật</label></li>
						<li><input type="checkbox" name="6" class="styled" checked="checked" disabled="disabled"/> <label>Quản
								lý danh mục</label></li>
						<li class="linetop"><strong>Tự tạo danh mục và quản
								lý tại mục Báo cáo quản lý:</strong></li>
						<li><input type="checkbox" name="7" class="styled" checked="checked" disabled="disabled"/> <label>Lãi
								lỗ danh mục</label></li>
						<li><input type="checkbox" name="8" class="styled" checked="checked" disabled="disabled"/> <label>Phân
								tích danh mục</label></li>
					</ul>
	
				</div>
				<!--end right-->
			</div>
		</form>
	</div>
</div>
