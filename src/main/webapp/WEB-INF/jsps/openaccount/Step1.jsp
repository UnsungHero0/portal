<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="list_b1 clearfix">
	<li>
		<s:actionerror />
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.investors">Nhà đầu tư</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<label class="">
				<input type="radio" class="option" value="local"
					name="account.investorType" checked="checked" />
				<s:text name="web.label.online.account.domestic">Trong nước</s:text>
			</label>
			<label class="padding-left30">
				<input type="radio" value="" class="option" disabled="disabled" />
				<s:text name="web.label.online.account.foreign">Ngoài nước</s:text>
			</label>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.account.type">Loại tài khoản</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<label class="">
				<input type="radio" class="option" checked="checked" />
				<s:text name="web.label.online.account.personal.account">Tài khoản cá nhân</s:text>
			</label>
			<label class=" padding-left10">
				&nbsp;
				<s:if test="%{account.accountType eq 'organized'}">
					<input type="radio" class="option" value="organized"
						name="account.accountType" checked="checked" disabled="disabled" />
				</s:if>
				<s:else>
					<input type="radio" class="option" value="organized"
						name="account.accountType" disabled="disabled" />
				</s:else>
				<s:text name="web.label.online.account.accounts.held">Tài khoản tổ chức</s:text>
			</label>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.personal.name">Tên cá nhân</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" cssStyle="width: 280px;" maxlength="49"
				cssClass="txt" name="account.name"></s:textfield>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.nationality">Quốc tịch</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:select theme="simple" name="account.national"
				cssStyle="width: 120px"
				list="#{'VNM' : getText('web.label.account.national')}">
			</s:select>
			<span class="padding-left30"> <span class=""><strong><s:text
							name="web.label.online.account.sex">Giới
                            tính</s:text> </strong> <span class="red"> * </span> </span> <label
					class="padding-left30">
					<s:if test="%{account.sex eq 'MALE'}">
						<input type="radio" class="option" value="MALE" id="male"
							name="account.sex" checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="MALE" id="male"
							name="account.sex" />
					</s:else>
					<s:text name="web.label.online.account.male">Nam</s:text>

				</label> <label class="padding-left30">
					<s:if test="%{account.sex eq 'FEMALE'}">
						<input type="radio" class="option" value="FEMALE" id="female"
							name="account.sex" checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="FEMALE" id="female"
							name="account.sex" />
					</s:else>
					<s:text name="web.label.online.account.female">Nữ</s:text>
				</label> </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.birthdate">Ngày sinh</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" cssStyle="width: 80px;" cssClass="txt"
				name="account.birthday"></s:textfield>

			<span class="" style='padding-left: 10px;'> <span class="padding-right5"><strong><s:text
							name="web.label.online.account.marital.status">Tình
							trạng hôn nhân</s:text> </strong> <span class="red"> * </span> </span> <label
					class="padding-left5">
					<s:if test='%{account.married eq "SINGLE"}'>
						<input type="radio" class="option" value="SINGLE"
							name="account.married" checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="SINGLE"
							name="account.married" />
					</s:else>
					<s:text name="web.label.online.account.single">Độc thân</s:text>

				</label> <label class="padding-left20">
					<s:if test='%{account.married eq "MARRIED"}'>
						<input type="radio" class="option" value="MARRIED"
							name="account.married" checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="MARRIED"
							name="account.married" />
					</s:else>
					<s:text name="web.label.online.account.married">Có gia đình</s:text>

				</label> </span>
				<div id="married"
                    style="display: <s:if test='%{account.married eq "MARRIED"}'>block</s:if><s:else>none</s:else>; float: right; margin-right: 3px;">
                    <table cellpadding="0" cellspacing="5px" border="0">
                        <tr>
                            <td>
                                <s:text name="web.label.online.account.name.of.spouse"></s:text>
                            </td>
                            <td>
                                <s:textfield name="account.spouseName" cssClass="txt"
                                    maxlength="49" cssStyle="width: 160px;"></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="web.label.online.account.company.of.spouse"></s:text>
                            </td>
                            <td>
                                <s:textfield name="account.spouseCompany" cssClass="txt"
                                    maxlength="49" cssStyle="width: 160px;"></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="web.label.online.account.position"></s:text>
                            </td>
                            <td>
                                <s:textfield name="account.spousePosition" cssClass="txt"
                                    maxlength="49" cssStyle="width: 160px;"></s:textfield>
                            </td>
                        </tr>
                    </table>
                </div>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.household.id">CMND/Hộ chiếu </s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<%--<input type="text" name="" value="" id="" class="input155" />
                                    --%>
			<s:textfield theme="simple" maxlength="19" name="account.identityNo"
				cssClass="txt" cssStyle="width: 160px;"></s:textfield>

			<span class="padding-left20"> <strong><s:text
						name="web.label.online.account.papers">Loại giấy tờ</s:text> </strong><span
				class="red"> * </span> &nbsp; <%--<input type="text" name="" value="" id="" class="input155" />
                                    --%> <web:refData var="identities"
					group="IDENTIFICATION_TYPE" /> <s:select id="newsType"
					name="account.identityType" list="identities"
					listValue="description" listKey="itemCode" theme="simple" /> </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.date.of.issue">Ngày cấp</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" name="account.identityDate"
				cssClass="txt" cssStyle="width: 80px;"></s:textfield>
			<span class="" style='padding-left: 10px;'> <strong><s:text
						name="web.label.online.account.place.of.issue">Nơi cấp</s:text> </strong><span
				class="red"> * </span> <span class="padding-left20"><s:textfield
						theme="simple" maxlength="29" name="account.identityPlace"
						cssClass="txt" cssStyle="width: 250px"></s:textfield> </span> </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.address">Địa chỉ</s:text>
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" maxlength="99" cssClass="txt"
				cssStyle="width : 435px" name="account.address"></s:textfield>
		</div>
	</li>
	<li>
		<div class="rowa">
			Email
			<span class="red"> * </span>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" maxlength="49" name="account.email"
				cssClass="txt" cssStyle="width : 250px"></s:textfield>
		</div>
	</li>
	<li>
		<div class="rowa">
			<s:text name="web.label.online.account.phone">Điện thoại cố định</s:text>
		</div>
		<div class="rowb">
			<s:textfield theme="simple" maxlength="14" cssClass="txt"
				name="account.phoneNo" cssStyle="width : 120px;"></s:textfield>
			<span class="padding-left20"> <strong><s:text
						name="web.label.online.account.mobile">Điện thoại di
						động</s:text> </strong><span class="red"> * </span> <s:textfield theme="simple"
					maxlength="14" name="account.mobilePhoneNo" cssClass="txt" cssStyle="width : 120px;"
					cssErrorStyle="width: 120px;"></s:textfield> </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			Fax
		</div>
		<div class="rowb">
			<s:textfield theme="simple" maxlength="29" cssClass="txt"
				name="account.fax" cssStyle="width : 160px;"></s:textfield>
			<span class="padding-left20"><strong><s:text
						name="web.label.online.account.tax.code">Mã số thuế</s:text> </strong> <span
				class="padding-left20"> <s:textfield theme="simple"
						maxlength="29" cssStyle="width: 120px;" name="account.taxCode"
						cssClass="txt"></s:textfield> </span> </span>
		</div>
	</li>
</ul>
