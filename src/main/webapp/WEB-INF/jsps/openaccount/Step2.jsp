<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div class="error" style="display: none;">
	<span></span>.
	<br clear="all" />
</div>

<ul class="list_b2 clearfix">
	<li style="border-top: 0px;">
		<div class="rowa">
			1.
		</div>
		<div class="rowb">
			<strong><s:text name="web.label.online.account.account.type">Loại tài khoản </s:text>
			</strong>
			<span class="red"> * </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			2.
		</div>
		<div class="rowb">
			<s:if test='%{account.tradingAccounts.contains("1")}'>
				<input type="checkbox" value="1" name="account.tradingAccounts[0]"
					class="input_chk tradingAccounts" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="1" name="account.tradingAccounts[0]"
					class="input_chk tradingAccounts">
			</s:else>
			<s:text name="web.label.online.account.transaction.listing">TK giao dịch niêm yết</s:text>
		</div>
		<div class="rowb">
			<s:if test='%{account.tradingAccounts.contains("2")}'>
				<input type="checkbox" value="2" name="account.tradingAccounts[1]"
					class="input_chk tradingAccounts" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="2" name="account.tradingAccounts[1]"
					class="input_chk tradingAccounts">
			</s:else>
			<s:text name="web.label.online.account.trading.otc">TK giao dịch OTC</s:text>
		</div>
	</li>
	<li>
		<div class="rowa">
			3.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.method.of.transaction">Phương thức giao dịch (Lựa chọn 1 hoặc nhiều phương thức):</s:text>
			</strong>
			<span class="red"> * </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<input type="checkbox" checked="checked" disabled="disabled"
				class="input_chk">
			<s:hidden name="account.tradingMethods[0]" value="Property"></s:hidden>
			<s:text name="web.label.online.account.in.the.trading.floor">Tại sàn giao dịch</s:text>
		</div>
		<div class="rowb">
			<s:if test='%{account.tradingMethods.contains("Online")}'>
				<input type="checkbox" value="Online"
					name="account.tradingMethods[1]" class="input_chk"
					checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="Online"
					name="account.tradingMethods[1]" class="input_chk">
			</s:else>
			<s:text name="web.label.online.account.online.trading">Giao dịch trực tuyến</s:text>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<s:if test='%{account.tradingMethods.contains("Phone")}'>
				<input type="checkbox" value="Phone"
					name="account.tradingMethods[2]" class="input_chk"
					checked="checked" id="phone">
			</s:if>
			<s:else>
				<input type="checkbox" value="Phone"
					name="account.tradingMethods[2]" class="input_chk" id="phone">
			</s:else>
			<s:text name="web.label.online.account.transactions.over.the.phone">Giao dịch qua điện thoại</s:text>
		</div>
		<div class="rowb">
			<div id="phoneArea"
				style="display: <s:if test='%{account.tradingMethods.contains("Phone")}'>block</s:if><s:else>none</s:else>;">
				<p>
					<s:if test='%{account.tradingPhone eq "any"}'>
						<input type="radio" class="option" value="any"
							name="account.tradingPhone" checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="any"
							name="account.tradingPhone" />
					</s:else>
					<s:text name="web.label.online.account.from.any.phone.number"></s:text>
				</p>
				<p>
					<s:if test='%{account.tradingPhone eq "phoneNo"}'>
						<input type="radio" class="option" value="phoneNo"
							name="account.tradingPhone" checked="checked" id="phoneNo" />
					</s:if>
					<s:else>
						<input type="radio" class="option" value="phoneNo"
							name="account.tradingPhone" id="phoneNo" />
					</s:else>
					<s:text
						name="web.label.online.account.from.one.of.the.following.telephone.number"></s:text>
				</p>
				<div
					style="padding-left: 30px; display: <s:if test='%{account.tradingPhone eq "phoneNo"}'>block</s:if><s:else>none</s:else>;"
					id="twoPhonesArea">
					<s:text name="web.label.online.account.phone.number"></s:text>
					1:
					<s:textfield name="account.phoneNo"
						cssErrorStyle="width: 120px; margin: 2px 0 2px 5px" maxlength="14"
						cssClass="txt"></s:textfield>
					<br />
					<s:text name="web.label.online.account.phone.number"></s:text>
					2:
					<s:textfield name="account.mobilePhoneNo"
						cssErrorStyle="width: 120px; margin: 2px 0 2px 5px" maxlength="14"
						cssClass="txt"></s:textfield>
				</div>
			</div>
		</div>
	</li>
	<li>
		<div class="rowa">
			4.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.statement.cycle.to.receive"></s:text>:
			</strong>
			<span class="red"> * </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<s:if test='%{account.cycleToReceive.contains("1")}'>
				<input type="checkbox" value="1" name="account.cycleToReceive[0]"
					class="input_chk" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="1" name="account.cycleToReceive[0]"
					class="input_chk">
			</s:else>
			<s:text name="web.label.online.account.month">Tháng</s:text>
		</div>
		<div class="rowb">
			<s:if test='%{account.cycleToReceive.contains("2")}'>
				<input type="checkbox" value="2" name="account.cycleToReceive[1]"
					class="input_chk" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="2" name="account.cycleToReceive[1]"
					class="input_chk">
			</s:else>
			<s:text name="web.label.online.account.upon.request">Khi có yêu cầu (tối đa 1 lần/ tháng)</s:text>
		</div>
	</li>
	<li>
		<div class="rowa">
			5.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.method.of.receiving.account.statements">Phương thức nhận sao kê tài khoản</s:text>
			</strong>
			<span class="red"> * </span>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<s:if test='%{account.receiveMethods.contains("1")}'>
				<input type="checkbox" value="1" name="account.receiveMethods[0]"
					class="input_chk" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="1" name="account.receiveMethods[0]"
					class="input_chk">
			</s:else>
			<s:text
				name="web.label.online.account.at.the.information.counter.transactions">Tại quầy thông tin giao dịch</s:text>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<s:if test='%{account.receiveMethods.contains("2")}'>
				<input type="checkbox" value="2" name="account.receiveMethods[1]"
					class="input_chk" checked="checked">
			</s:if>
			<s:else>
				<input type="checkbox" value="2" name="account.receiveMethods[1]"
					class="input_chk">
			</s:else>
			Email
		</div>
		<div class="rowb">
			<s:textfield theme="simple" cssStyle="width: 150px" cssClass="txt"
				name="account.email" disabled="true"></s:textfield>
		</div>
	</li>
	<li>
		<div class="rowa">
			6.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.account.transactions">Tài khoản giao dịch tiền (dành cho giao dịch trực
				tuyến và qua điện thoại) </s:text> </strong>
			<%-- <span class="red"> * </span> --%>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.account.number">Số tài khoản </s:text>
				</span>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[0]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[1]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[2]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[3]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[4]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[5]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[6]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[7]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[8]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[9]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[10]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[11]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[12]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo1[13]" maxlength="1"></s:textfield>
			</div>
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.account.name">Tên tài khoản</s:text>
				</span>
				<s:textfield cssClass="txt" cssStyle="width: 200px" maxlength="49"
					name="account.accountName1"></s:textfield>
			</div>
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.bank">Tại ngân hàng</s:text> </span>
				<s:textfield cssClass="txt" cssStyle="width: 200px" maxlength="49"
					name="account.bankName1"></s:textfield>
			</div>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.account.number">Số tài khoản </s:text>
				</span>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[0]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[1]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[2]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[3]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[4]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[5]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[6]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[7]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[8]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[9]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[10]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[11]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[12]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.accountNo2[13]" maxlength="1"></s:textfield>
			</div>
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.account.name">Tên tài khoản</s:text>
				</span>
				<s:textfield cssClass="txt" cssStyle="width: 200px" maxlength="49"
					name="account.accountName2"></s:textfield>
			</div>
			<div class="content_smal">
				<span class="rowb-b"><s:text
						name="web.label.online.account.bank">Tại ngân hàng </s:text> </span>
				<s:textfield cssClass="txt" cssStyle="width: 200px" maxlength="49"
					name="account.bankName2"></s:textfield>
			</div>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowb">
			<div class="content_smal">
				<span class="rowb-c"><s:text
						name="web.label.online.account.beneficiary.account.number.in.reciprocal.vndirect">Số tài khoản nhận tiền đối ứng tại
					VNDIRECT </s:text> </span>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[0]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[1]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[2]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[3]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[4]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[5]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[6]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[7]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[8]" maxlength="1"></s:textfield>
				<s:textfield cssStyle="margin-right: 3px; width: 18px"
					name="account.vndirectAccNo[9]" maxlength="1"></s:textfield>
			</div>
			<div class="content_smal">
				<span class="rowb-c"><s:text
						name="web.label.online.account.account.name">Tên tài khoản</s:text>
				</span>
				<s:textfield cssClass="txt" cssStyle="width: 200px" maxlength="49"
					name="account.vndirectAccName"></s:textfield>
			</div>

		</div>
	</li>
</ul>

