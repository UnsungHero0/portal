<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<input type="hidden" value='<s:text name="empty.input.error.message"/>' id="emptyInputErrorMessage">
<input type="hidden" value='<s:text name="web.error.openaccount.invalid.identification"/>' id="errorDuplicateIndentityMessage">
<input type="hidden" value='<s:text name="web.error.openaccount.invalid.email"/>' id="errorDuplicateEmailMessage">
<input type="hidden" value='<s:text name="web.error.openaccount.invalid.username"/>' id="errorDuplicateUsernameMessage">
<input type="hidden" value='<s:text name="web.error.openaccount.agree.regulation"/>' id="errorAgreeRegulation">
<div class="openaccInfo-wp">
	<h1 class="title">MỞ TÀI KHOẢN TRỰC TUYẾN</h1>
    <s:actionerror/>
    <div class="action_message_box">
    <s:actionmessage/>
    </div>
    <div class="error" id="validate_input_error_message" style="margin: 10px; display: none;"></div>
    <form id="openOnlineAccountForm" action="<web:url value='/mo-tai-khoan-nha-dau-tu/dang-ky-tai-khoan.shtml'/>" method="POST" >
	<div class="boxInfo">
		<div class="step_info">
		   <img src='<web:url value="/images/icons/mtk_step1.png"/>'/>
		</div>
		<h2>THÔNG TIN TÀI KHOẢN GD CHỨNG KHOÁN</h2>
		<ul>
			<li><label>
					<p>
						Họ(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" id="lastname" name="account.lastName" value="%{account.lastName}"/>
			</label></li>
			<li><label>
					<p>
						Tên đệm:
					</p> <s:textfield cssClass="openAcc-textboxM" id="middlename" name="account.middleName" value="%{account.middleName}"/>
			</label></li>
			<li><label>
					<p>
						Tên(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" id="name" name="account.name" value="%{account.name}"/>
			</label></li>
			<li>
				<p>
					<s:text name="web.label.online.account.sex">Giới tính</s:text>
					(<span class="txt-Red">*</span>):
				</p>
				<p class="marginRadiowp">
                        <s:if test="%{account.sex eq 'FEMALE'}">
							<label class="marginRadio"> 
                                <input type="radio" class="option" value="Male" id="gender" name="account.sex"/>
								<s:text	name="web.label.online.account.male">Nam</s:text>
							</label> 
							<label> 
							     <input type="radio" value="Female" id="gender"	name="account.sex" checked="checked"/> 
							     <s:text name="web.label.online.account.female">Nữ</s:text>
							</label>
	                    </s:if>
	                    <s:else>
	                        <label class="marginRadio"> 
                                <input type="radio" class="option" value="MALE" id="gender" name="account.sex" checked="checked"/>
                                <s:text name="web.label.online.account.male">Nam</s:text>
                            </label> 
                            <label> 
                                 <input type="radio" value="FEMALE" id="gender" name="account.sex"/> 
                                 <s:text name="web.label.online.account.female">Nữ</s:text>
                            </label>
	                    </s:else> 
				</p>
			</li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.birthdate">Ngày sinh</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" name="account.birthday" id="birthday" value="%{account.birthday}" placeHolder="dd/mm/yyyy"/>
			</label></li>
			<li class="clear"><label id="identifyNumberId">
					<p>
						<s:text name="web.label.online.account.household.id">CMTND</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" name="account.identityNo" id="identityNo" value="%{account.identityNo}"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.date.of.issue">Ngày cấp</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" name="account.identityDate" id="identityDate" value="%{account.identityDate}" placeHolder="dd/mm/yyyy"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.place.of.issue">Nơi cấp</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM" name="account.identityPlace" id="identityPlace" value="%{account.identityPlace}"/>
			</label></li>
			<li class="w295"><label>
					<p>
						<s:text name="web.label.online.account.address">Địa chỉ liên lạc</s:text>(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="w265" name="account.address" id="address" value="%{account.address}"/>
			</label></li>
			<li class=""><label>
					<p>
						Tỉnh thành(<span class="txt-Red">*</span>):
					</p> 
					<select id="province_id" name="account.province" class="openAcc-textboxM">
                       <option value="">Chọn tỉnh thành</option>
                       <s:iterator var="provinceItem" value="provinceList">
                           <option value="<s:property value='#provinceItem.id'/>"><s:property value="#provinceItem.name"/></option>
                       </s:iterator>
                    </select> 
			</label></li>
		</ul>
		<ul class="boxInfo03 clear">
			<li><label id="emailInfoId">
					<p>
						Email(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM2" name="account.email" id="email" value="%{account.email}"/>
			</label></li>
			<li><label id="usernameInfoId">
					<p>
						<s:text name="web.label.online.account.username">Tên đăng nhập</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM2" name="account.username" id="username" value="%{account.username}"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.password">Mật khẩu GD trực tuyến</s:text>
						(<span class="txt-Red">*</span>):
					</p> <input type="password" class="openAcc-textboxM2" name="account.password" id="password"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.retype.password">Nhập lại mật khẩu</s:text>
						(<span class="txt-Red">*</span>):
					</p> <input type="password" class="openAcc-textboxM2" name="account.retypePassword" id="retypePassword"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.phoneNumber">Di động 1</s:text>
						(<span class="txt-Red">*</span>):
					</p> <s:textfield cssClass="openAcc-textboxM2" name="account.mobilePhoneNo" id="mobilePhoneNo" value="%{account.mobilePhoneNo}"/>
			</label></li>
			<li><label>
					<p>
						<s:text name="web.label.online.account.phoneNumber.password">Mật khẩu GD điện thoại</s:text>
						(<span class="txt-Red">*</span>):
					</p> 
					<s:password cssClass="mobilePassBox" id="mobilePass0" 
                                 name="account.mobilePassword[0]" maxlength="1"/>
	                <s:password cssClass="mobilePassBox" id="mobilePass1"
	                             name="account.mobilePassword[1]" maxlength="1"/>
	                <s:password cssClass="mobilePassBox" id="mobilePass2"
	                             name="account.mobilePassword[2]" maxlength="1"/>
	                <s:password cssClass="mobilePassBox" id="mobilePass3"
	                             name="account.mobilePassword[3]" maxlength="1"/>
					
			</label></li>
		</ul>
		<h2>THÔNG TIN TÀI KHOẢN GD TIỀN</h2>
		<p class="noteCustomer">
			Lưu ý: Quý khách chỉ có thể chuyển tiền ra khỏi tài khoản sau khi
			hoàn thiện<br /> Hợp đồng mở tài khoản.
		</p>
		<ul class="boxInfo02">
			<li><label id="bankNumberLabelId">
					<p>Số tài khoản:</p> 
					<s:textfield cssClass="openAcc-textboxL" id="transferMoneyNumber" name="account.bankAccountNo" value="%{account.bankAccountNo}"/>
			</label></li>
			<li><label>
					<p>Tên chủ tài khoản:</p> 
					<s:textfield cssClass="openAcc-textboxL" id="transferMoneyFullName" name="account.bankAccountName" value="%{account.bankAccountName}"/>
			</label></li>
			<li><label>
					<p>Tại ngân hàng:</p>
					<select id="bank_id" name="account.bankCode" class="openAcc-selectboxL">
					   <option value="">Chọn tên ngân hàng</option>
					   <s:iterator var="bankItem" value="banksList">
					       <option value="<s:property value='#bankItem.bankCode'/>"><s:property value="#bankItem.bankFullName"/></option>
					   </s:iterator>
					   <%-- <s:iterator var="bankItem" value="bankListFromFile">
					       <option value="<s:property value='#bankItem.bankCode'/>"><s:property value="#bankItem.bankFullName"/></option>
					   </s:iterator> --%>
					</select> 
			</label></li>
			<li>
			     <label>
                    <p>Chi nhánh:</p>
                    <select class="openAcc-selectboxL" id="bank_branches_id" class="openAcc-selectboxL" name="account.bankBranchCode">
                        <option value="">Chọn chi nhánh</option>
                    </select>
                 </label>
			</li>
			<li>
			     <tags:captcha privateKey="6LccgewSAAAAAIvbVL6Li4wCfJUd3obdaiH-y3ey" 
              publicKey="6LccgewSAAAAACZd703KR8ms4KNkDRSPg6F3zWwE"></tags:captcha>
			</li>
		</ul>
		
              
		<div class="openAcc-agree clear">
			<p class="padding-bottom15">
				<input type="checkbox" checked="checked" id="agreeOpenAccount"/>Tôi đồng ý với <span
					class="txt-Green"><a href="javascript:void(0);" onclick="OnlineAccountMain.viewContract();">các điều khoản, quy định về mở tài khoản
					giao dịch chứng khoán tại VNDIRECT.</a></span>
			</p>
			<input type="button" class="btnOrder" value="Mở tài khoản" id="openAccountButton"/> 
			<input type="button" class="btnCancel" value="Hủy" id="cancelButton"/>
		</div>
	</div>
	</form>
	<!--/boxInfo-->
	<div class="openAccR">
	    <div class="opentAccNote">
	        <h2>LƯU Ý</h2>
	        <ul>
	            <li>Dịch vụ mở tài khoản Giao dịch chứng khoán trực tuyến chỉ
	                dành cho Nhà đầu tư CÁ NHÂN - TRONG NƯỚC - Quốc tịch VIỆT NAM.</li>
	            <li>Khách hàng tổ chức, nhà đầu tư nước ngoài vui lòng liên hệ
	                tổng đài <strong>1900 54 54 09</strong> hoặc chọn hỗ trợ trực tuyến
	                tại thanh Direct Bar để được trợ giúp
	            </li>
	        </ul>
	    </div>
	    <jsp:include page="snippet/RightOpenAccountContent.jsp"></jsp:include>
	</div>
	<div class="clear"></div>
</div>
<!--/openaccInfo-wp-->
<!-- popup -->
<div id="processingOpenAccountDialog" title="Đang xử lý" style="display: none; position: relative; z-index: 999;">
    <p style="text-align: center">Xin vui lòng chờ trong giây lát.Yêu cầu của quý khách hiện đang được hệ thống xử lý.</p>
    <br/>
    <p style="text-align: center"><img src="<web:url value='/images/icons/processingOpenAcc.gif'/>"/></p>
</div>