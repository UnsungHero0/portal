<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form name="fTradingStatistics" id="fTradingStatistics" method="post">
	<input type="hidden" name="model.downloadType" id="downloadType">
	<input id="pagingInfo_indexPage" type="hidden"
		name="pagingInfo.indexPage" value="1" />

	<div id="content_ttpt">
		<!-- nav -->
        <jsp:include page="/WEB-INF/jsps/listedmarket/snippet/market-overview-nav.jsp"></jsp:include>

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
										type="text" id="fTradingStatistics_TradingDate"
										value="<c:out value='${model.strTradingDate}'/>" /> </span>

	                             <input type="submit" class="iButton autoHeight" id="fTradingStatistics_Search" value="<s:text name="button.search"/>">

							</div>

						</div>
						<div class="box_taidulieu">
							<a href="javascript:doDownload();"> <span
								class="icon_download left"></span> <span class="text"> <s:text
										name="button.downloadFile" /> </span> </a>
						</div>
					</div>
					<ul class="list_tktt ketquagiaodich">
						<li class="header box_kqgd_header">
							<div class="row_mck">
								<s:text name="web.label.symbol" />
							</div>
							<div class="row1">
								<s:text
									name="web.label.MarketStatisticsAction.form.CellNames.Close" />
							</div>
							<div class="box-1">
								<div class="rowa">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Bid" />
								</div>
								<div class="rowb">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidOrder" />
								</div>
								<div class="rowc">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidVolume" />
								</div>
							</div>
							<div class="box-1">
								<div class="rowa">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Offer" />
								</div>
								<div class="rowb" style="">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferOrder" />
								</div>
								<div class="rowc">
									<s:text
										name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferVolume" />
								</div>
							</div>
							<div class="row1">
								<s:text
									name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.BidAverage" />
							</div>
							<div class="row1">
								<s:text
									name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.OfferAverage" />
							</div>
							<div class="row1">
								<s:text
									name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.Change" />
							</div>
							<div class="row_4" style="">
								<s:text
									name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.TotalVolume" />
							</div>
							<div class="row3">
								<s:text
									name="web.label.MarketStatisticsAction.form.TradingStatistics.CellNames.TotalValue" />
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
										<c:out value="${item.id.closePrice}" />
									</fmt:formatNumber>
								</div>
								<div class="row1">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.bidOrder}" />
									</fmt:formatNumber>
								</div>
								<div class="row2">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.bidVolumn}" />
									</fmt:formatNumber>
								</div>
								<div class="row1">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.offerOrder}" />
									</fmt:formatNumber>
								</div>
								<div class="row2">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.offerVolumn}" />
									</fmt:formatNumber>
								</div>

								<div class="row1">
									<c:if test="${item.id.bidVolumn != 0}">
										<fmt:formatNumber pattern="###,###.#">
											<c:out value="${item.id.bidVolumn / item.id.bidOrder}" />
										</fmt:formatNumber>
									</c:if>
									<c:if test="${item.id.bidVolumn == 0}">
										<fmt:formatNumber pattern="###,###.#">
											<c:out value="${item.id.bidVolumn}" />
										</fmt:formatNumber>
									</c:if>
								</div>

								<div class="row1">
									<c:if test="${item.id.offerVolumn != 0}">
										<fmt:formatNumber pattern="###,###.#">
											<c:out value="${item.id.offerVolumn / item.id.offerOrder}" />
										</fmt:formatNumber>
									</c:if>
									<c:if test="${item.id.offerVolumn == 0}">
										<fmt:formatNumber pattern="###,###.#">
											<c:out value="${item.id.offerVolumn}" />
										</fmt:formatNumber>
									</c:if>
								</div>

								<div class="row1">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.bidVolumn - item.id.offerVolumn}" />
									</fmt:formatNumber>
								</div>
								<div class="row1">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.totalVolumn}" />
									</fmt:formatNumber>
								</div>
								<div class="row3">
									<fmt:formatNumber pattern="###,###.#">
										<c:out value="${item.id.totalValue}" />
									</fmt:formatNumber>
								</div>
							</li>
						</c:forEach>

					</ul>

				</div>
			</div>
		</div>
	</div>
	</div>
	<!--End All Tab menu  -->
</form>
