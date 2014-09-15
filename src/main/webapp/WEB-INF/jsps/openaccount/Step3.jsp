<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="list_b2 clearfix">
	<li style="border-top: 0px;">
		<div class="rowa">
			1.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.relevant.information.requested.by.ssc">Thông tin liên quan theo yêu cầu của UBCK</s:text>
			</strong>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowc">
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:text
						name="web.label.online.account.name.of.public.companies.that.you.hold.management.positions">Tên công ty đại chúng mà quý khách nắm chức danh quản lý</s:text>
				</div>
				<div class="left padding-top10">
					<s:textfield theme="simple" name="account.mangName" maxlength="99"
						cssStyle="width: 250px" cssClass="txt input280"></s:textfield>
				</div>
			</div>
			<div class="content_smal">
				<div class="rowc-a">
					<s:text
						name="web.label.online.account.name.of.public.companies.that.the.customer.owns.or.more.capital">Tên công ty đại chúng mà khách hàng sở hữu từ 5% vốn điều lệ trở
					lên</s:text>
				</div>
				<div class="left padding-top10">
					<s:textfield theme="simple" name="account.capitalName"
						maxlength="99" cssStyle="width: 250px" cssClass="txt input280"></s:textfield>
				</div>
			</div>
		</div>
	</li>
	<li>
		<div class="rowa">
			2.
		</div>
		<div class="rowb">
			<strong><s:text
					name="web.label.online.account.other.related.Information">Thông tin liên quan khác</s:text>
			</strong>
		</div>
	</li>
	<li>
		<div class="rowa">
			&nbsp;
		</div>
		<div class="rowc">
			<div class="content_smal line_bt">
				<span class="padding5 left"><strong><s:text
							name="web.label.online.account.included.in.trading.account.securities.company">Đã có tài khoản
						giao dịch tại công ty chứng khoán khác</s:text> </strong> </span>
			</div>
			<div class="content_smal line_bt">
				<span class="padding5 left"><s:if
						test='%{account.otherAccount eq "0"}'>
						<input type="radio" class="option" value="0"
							name="account.otherAccount" checked="checked" />
					</s:if> <s:else>
						<input type="radio" class="option" value="0"
							name="account.otherAccount" />
					</s:else> <s:text name="web.label.online.account.no">Không</s:text> <s:if
						test='%{account.otherAccount eq "1"}'>
						<input type="radio" class="option" value="1"
							name="account.otherAccount" checked="checked" />
					</s:if> <s:else>
						<input type="radio" class="option" value="1"
							name="account.otherAccount" />
					</s:else> <s:text name="web.label.online.account.yes">Có</s:text> </span>
				<div id="otherAccNameArea"
					style="display: <s:if test='%{account.otherAccount eq "1"}'>block</s:if><s:else>none</s:else>; float:left;">
					<s:textfield theme="simple" name="account.otherAccName"
						placeholder="Nhập tên Công ty chứng khoán" maxlength="199" cssStyle="width: 250px; margin-top: 3px;"
						cssClass="txt input280 fl"></s:textfield>
				</div>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<strong><s:text
							name="web.label.online.account.year.of.market.entry">Năm gia nhập thị trường</s:text>
					</strong>
				</div>
				<div class="left padding-top5 padding-left20">
					<s:select theme="simple" cssStyle="width: 100px"
						name="account.year" list="years">
					</s:select>
				</div>
			</div>
			<div class="content_smal line_bt">
				<span class="padding5 left"><strong><s:text
							name="web.label.online.account.investment.experience.securities">Kinh nghiệm đầu
						tư chứng khoán</s:text> </strong> </span>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a" style="width: 25%;">
					<s:if test='%{account.exprs.contains("1")}'>
						<input type="checkbox" value="1" name="account.exprs[0]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="1" name="account.exprs[0]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.experienced">Đã có kinh nghiệm</s:text>
				</div>
				<div class="rowc-ab" style="width: 25%;">
					<s:if test='%{account.exprs.contains("2")}'>
						<input type="checkbox" value="2" name="account.exprs[1]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="2" name="account.exprs[1]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.unseasoned">Chưa có kinh nghiệm</s:text>
				</div>
				<div class="rowc-ac" style="width: 25%;">
					<s:if test='%{account.exprs.contains("3")}'>
						<input type="checkbox" value="3" name="account.exprs[2]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="3" name="account.exprs[2]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.inexperienced">Ít kinh nghiệm</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<span class="padding5 left"><strong><s:text
							name="web.label.online.account.investment.in">Đầu tư vào</s:text>
				</strong> </span>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invs.contains("1")}'>
						<input type="checkbox" value="1" name="account.invs[0]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="1" name="account.invs[0]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.stocks">Cổ phiếu</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invs.contains("2")}'>
						<input type="checkbox" value="2" name="account.invs[1]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="2" name="account.invs[1]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.gold">Vàng</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invs.contains("3")}'>
						<input type="checkbox" value="3" name="account.invs[2]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="3" name="account.invs[2]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.foreign.currency">Ngoại tệ</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invs.contains("4")}'>
						<input type="checkbox" value="4" name="account.invs[3]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="4" name="account.invs[3]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.real.estate">Bất động sản</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<span class="padding5 left"><strong><s:text
							name="web.label.online.account.investment.criteria">Tiêu chí đầu tư</s:text>
				</strong> </span>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invCriteria.contains("1")}'>
						<input type="checkbox" value="1" name="account.invCriteria[0]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="1" name="account.invCriteria[0]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.CP.value">CP giá trị</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invCriteria.contains("2")}'>
						<input type="checkbox" value="2" name="account.invCriteria[1]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="2" name="account.invCriteria[1]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.growth.stocks">CP tăng trưởng</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invCriteria.contains("3")}'>
						<input type="checkbox" value="3" name="account.invCriteria[2]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="3" name="account.invCriteria[2]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.short.stock">CP ngắn hạn</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invCriteria.contains("4")}'>
						<input type="checkbox" value="4" name="account.invCriteria[3]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="4" name="account.invCriteria[3]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.stable.income">Thu nhập ổn định</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<span class="padding5 left"><strong><s:text
							name="web.label.online.account.investment.capital.availability">Vốn sẵn sàng đầu
						tư</s:text> </strong> </span>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invCapAvail.contains("1")}'>
						<input type="checkbox" value="1" name="account.invCapAvail[0]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="1" name="account.invCapAvail[0]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.under.100.million">Dưới 100 triệu</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invCapAvail.contains("2")}'>
						<input type="checkbox" value="2" name="account.invCapAvail[1]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="2" name="account.invCapAvail[1]"
							class="input_chk">
					</s:else>
					<s:text
						name="web.label.online.account.from.100.million.to.500.million">Từ 100 triệu đến 500 triệu</s:text>
				</div>
			</div>
			<div class="content_smal line_bt">
				<div class="rowc-a">
					<s:if test='%{account.invCapAvail.contains("3")}'>
						<input type="checkbox" value="3" name="account.invCapAvail[2]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="3" name="account.invCapAvail[2]"
							class="input_chk">
					</s:else>
					<s:text
						name="web.label.online.account.from.500.million.to.1.billion">Từ 500 triệu đến 1 tỷ</s:text>
				</div>
				<div class="rowc-ab">
					<s:if test='%{account.invCapAvail.contains("4")}'>
						<input type="checkbox" value="4" name="account.invCapAvail[3]"
							class="input_chk" checked="checked">
					</s:if>
					<s:else>
						<input type="checkbox" value="4" name="account.invCapAvail[3]"
							class="input_chk">
					</s:else>
					<s:text name="web.label.online.account.over.1.billion">Trên 1 tỷ</s:text>
				</div>
			</div>
		</div>
	</li>
</ul>