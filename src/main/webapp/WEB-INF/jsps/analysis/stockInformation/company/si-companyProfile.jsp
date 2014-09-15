<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt"%>

<script type="text/javascript">
$(function() {
    FROM_MODULE = URL_PROFILE;
});
</script>

<form name="fProfile" id="fProfile"
	action='<s:url value="/listed/Profile"/>' method="post">
	<div class="content_ttpt">
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-company-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab">
				<div class="box_thongkecoban">
					<div class="box_left wl">
						<div class="tab_new">
							<div>
								<h2>
									<span> <s:text
											name="web.label.ProfileAction.form.NameAddress">TÊN CÔNG TY VÀ ĐỊA CHỈ</s:text>
									</span>
								</h2>
							</div>
						</div>
						<!-- ten cong ty va dia chi -->
						<ul class="listct clearfix wl-2">
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.VietnameseName">Tên pháp định</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.vietnameseName}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.InternationalName">Tên quốc tế</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out
										value="${model.ifoCompanyProfileViewId.internationalName}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.Abbreviation">Viết tắt</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.abbreviation}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.Headquarter">Trụ sở chính</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.headquarter}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.Telephone">Điện thoại</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.telephone}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.Fax">Fax</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.fax}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.Website">Website</s:text>
								</div>
								<div class="rowb_hsdn">
									<a
										href="javascript:openURL('<c:out value='${model.ifoCompanyProfileViewId.website}'/>');">
										<c:out value="${model.ifoCompanyProfileViewId.website}"
											escapeXml="false" /> </a>
								</div>
							</li>
						</ul>

						<div class="tab_new">
							<div>
								<h2>
									<span> <s:text
											name="web.label.ProfileAction.form.Details">CHI TIẾT</s:text>
									</span>
								</h2>
							</div>
						</div>
						<!-- chi tiet -->
						<ul class="listct clearfix wl-2">
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.IndexMembership">Thành viên của TTGD</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.indexMembership}"
										escapeXml="false">&nbsp;</c:out>
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.sector">Lĩnh vực</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.sector}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.industry">Ngành</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.industry}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.NumberOfBranches">Số chi nhánh</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out
										value="${model.ifoCompanyProfileViewId.numberOfBranches}"
										escapeXml="false" />
								</div>
							</li>
							<li>
								<div class="rowa_hsdn">
									<s:text name="web.label.ProfileAction.form.FullEmployee">Số nhân viên</s:text>
								</div>
								<div class="rowb_hsdn">
									<c:out value="${model.ifoCompanyProfileViewId.fullEmployee}"
										escapeXml="false" />
								</div>
							</li>
						</ul>
					</div>
					<div class="box_right wr">
						<div class="tab_new">
							<div>
								<h2>
									<span> <s:text
											name="web.label.ProfileAction.form.CompanyOfficers">BAN LÃNH ĐẠO</s:text>
									</span>
								</h2>
							</div>
						</div>
						<!-- ban lanh dao -->
						<ul class="listct clearfix wr-2">
							<li style="background-color: #f3f5f6;">
								<div class="rowa_hsdn">
									<strong><s:text name="web.label.ProfileAction.form.Name">Tên</s:text></strong>
								</div>
								<div class="rowb_hsdn">
									<strong><s:text name="web.label.ProfileAction.form.Position">Chức vụ</s:text></strong>
								</div>
							</li>
							<c:choose>
								<c:when test="${not empty model.officersViewIdList}">
									<c:forEach var="officerItem"
										items="${model.officersViewIdList}" varStatus="status">
										<li>
											<div class="rowa_hsdn" style="background: transparent;">
												<c:out value="${officerItem.id.name}" escapeXml="false" />
											</div>
											<div class="rowb_hsdn">
												<c:out value="${officerItem.id.position}" escapeXml="false" />
											</div>
										</li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li>
										<span class="no-record"><s:text
												name="web.label.messages.norecord" /> </span>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div class="clear"></div>
					<input type="hidden" id="hiddendiv" value="${model.ifoCompanyProfileViewId.abbreviation}"/>
					<div class="box_center_hsdn" id="${model.ifoCompanyProfileViewId.abbreviation}">
						<!-- lich su hinh thanh -->
						<div id="history_hsdn" style="margin-top: 10px;">
							<div class="tab_new" id="history_hsdn_tab">
								<div>
									<h2>
										<span> <s:text
												name="web.label.ProfileAction.form.History">Lịch sử hình thành</s:text>
										</span>
									</h2>
								</div>
							</div>
							<div class="content" id="history_hsdn_content" style="display: none;">
								<c:out value="${model.ifoCompanyProfileViewId.history}"
									escapeXml="false" />
							</div>
						</div>
						<!-- linh vuc kinh doanh -->
						<div id="main_business_hsdn" style="margin-top: 10px;">
							<div class="tab_new" id="main_business_hsdn_tab">
								<div>
									<h2>
										<span> <s:text
												name="web.label.ProfileAction.form.MainBusiness">Lĩnh vực kinh doanh</s:text>
										</span>
									</h2>
								</div>
							</div>
							<div class="content" id="main_business_content" style="display: none;">
								<c:out value="${model.ifoCompanyProfileViewId.mainBusiness}"
									escapeXml="false" />
							</div>
						</div>
						<!-- vi the -->
						<div id="market_position_hsdn" style="margin-top: 10px;">
							<div class="tab_new" id="market_position_hsdn_tab">
								<div>
									<h2>
										<span> <s:text
												name="web.label.ProfileAction.form.MarketPosition">Vị thế</s:text>
										</span>
									</h2>
								</div>
							</div>
							<div class="content" id="market_position_content" style="display: none;">
								<c:out value="${model.ifoCompanyProfileViewId.marketPosition}"
									escapeXml="false" />
							</div>
						</div>
						<!-- chien luoc -->
						<div id="plan_hsdn" style="margin-top: 10px;">
							<div class="tab_new" id="plan_hsdn_tab">
								<div>
									<h2>
										<span> <s:text name="web.label.ProfileAction.form.Plan">Chiến lược phát triển và đầu tư</s:text>
										</span>
									</h2>
								</div>
							</div>
							<div class="content" id="plan_content" style="display: none;">
								<c:out value="${model.ifoCompanyProfileViewId.plan}"
									escapeXml="false" />
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</form>