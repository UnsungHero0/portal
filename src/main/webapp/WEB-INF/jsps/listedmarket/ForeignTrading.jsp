<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form name="fForeignTrading" id="fForeignTrading" method="post">
	<input type="hidden" name="model.downloadType" id="downloadType">
	<input id="pagingInfo_indexPage" type="hidden"
		name="pagingInfo.indexPage" value="1" />

	<div id="content_ttpt">
		<!-- nav -->
        <jsp:include page="/WEB-INF/jsps/listedmarket/snippet/market-overview-nav.jsp"></jsp:include>

		<!--Start All Tab menu  -->
		<div class="content_small">
			<div class="content_tab" id="tab-1">
				<div class="paging">
					<web:showPaging usingURLForNav="false" navAction="_goTo"
						pagingInfo="${pagingInfo}" usingPageOverTotal="true"
						usingLastPage="true"></web:showPaging>
				</div>

				<div class="box_content_tktt">
					<div class="box_tienich">
						<div class="left">
							<div class="mck_2">
								<%--								
								Nhập mã CK để đưa lên đầu bảng
								<input type="text" id="fForeignTrading_Filter" value="">								
								--%>
								<span class="padding-left20"> <strong><s:text
											name="web.label.MarketStatisticsAction.form.Market" />
								</strong> <select name="searchMarketStatisticsView.market"
										id="fTradingStatistics_market" style="height: 20px;">
										<c:forEach var="item" items="${model.listMarket}">
											<c:set var="selectedOption" value="" />
											<c:if
												test="${item == model.searchMarketStatisticsView.market}">
												<c:set var="selectedOption" value="selected='true'" />
											</c:if>
											<option value="<c:out value='${item}'/>"
												<c:out value="${selectedOption}" escapeXml="false"/>>
												<c:out value='${item}' />
											</option>
										</c:forEach>
									</select> </span>
								<span class="padding-left20"> <s:text
										name="web.label.date" /> <input name="strTradingDate"
										type="text" id="fForeignTrading_TradingDate"
										value="<c:out value='${model.strTradingDate}'/>" /> </span>
                                <input type="submit" id="fForeignTrading_Search" class="iButton autoHeight" value="<s:text name="button.search"/>">

							</div>

						</div>
						<div class="box_taidulieu">
							<%--<a href="#"><span class="icon_download left"></span> <span class="text">Tải dữ liệu</span></a>--%>
						</div>
					</div>
					<ul class="list_tktt giaodichndtnn">
						<li class="header box_kqgd_header">
							<div class="row_mck">
								<s:text name="web.label.symbol" />
							</div>
							<div class="row1">
								<s:text
									name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.TotalRoom" />
							</div>
							<div class="row2">
								<s:text
									name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.CurrentRoom" />
							</div>
							<div class="box-1">
								<div class="rowa">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.BuyingVolume" />
								</div>
								<div class="rowb">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Bids" />
								</div>
								<div class="rowc">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value" />
								</div>
							</div>
							<div class="box-1">
								<div class="rowa">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.SellingVolume" />
								</div>
								<div class="rowb">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Offers" />
								</div>
								<div class="rowc">
									<s:text
										name="web.label.MarketStatisticsAction.form.ForeignTrading.CellNames.Value" />
								</div>
							</div>
						</li>

						<c:forEach var="item" items="${model.searchResult}" varStatus="i">
							<li>
								<div class="row_mck">
									<c:set var="symbolLowerCase"
										value="${fn:toLowerCase(item.id.secCode)}" />
									<a
										href="<web:url value='/tong-quan/${symbolLowerCase}.shtml'/>">
										<c:out value="${item.id.secCode}" /> </a>
								</div>
								<div class="row1">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.currentRoom}" />
									</fmt:formatNumber>
								</div>
								<div class="row2">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.totalRoom}" />
									</fmt:formatNumber>
								</div>
								<div class="row3">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.buyingVolume}" />
									</fmt:formatNumber>
								</div>
								<div class="row4">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.buyingValue}" />
									</fmt:formatNumber>
								</div>
								<div class="row3">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.sellingVolume}" />
									</fmt:formatNumber>
								</div>
								<div class="row4">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.sellingValue}" />
									</fmt:formatNumber>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!--End All Tab menu  -->
</form>