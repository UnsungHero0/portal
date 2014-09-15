<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<div id="content_ttpt">
	<div class="content_small" style="margin-bottom: 20px;">
		<!-- nav -->
		<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-si-nav.jsp"></jsp:include>
		<div class="content_doanhnghiep">
			<div class="content_left">
				<!-- statistic and chart -->
				<!-- column statistic 1 -->
				<div class="width255">
					<ul class="list14">
						<li>
							<div class="rowa">
								<s:text name="web.label.weeklow" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strWeekLow" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.weekhigh" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strWeekHigh" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="ir.si.thongtincoban.TenDayAverageVolumn" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strAverageVolumn" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.market.capital" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strMarketCapital" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="ir.si.thongtincoban.ShareOutstanding" />
							</div>
							<div class="rowc">
								<s:property
									value="model.ifoComSnapshotViewExt.strShareOutstanding" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="ir.si.thongtincoban.ShareRelease" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strListedShares" />
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="ir.si.thongtincoban.DividendYield" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strDividendYield" />
								%
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="ir.si.thongtincoban.NoRightsDate" />
							</div>
							<div class="rowc">
								<s:property
									value="model.ifoComSnapshotViewExt.strExDividendDate" />
							</div>
						</li>
					</ul>
				</div>
				<!-- end column statistic 1 -->

				<!-- column statistic 2 -->
				<div class="width190">
					<ul class="list14">
						<li>
							<div class="rowb">
								<s:text name="web.label.EPS" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strEps" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="ir.si.thongtincoban.AnnualEPS" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strAnnualEPS" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.ROA" />
							</div>
							<div class="rowc">
								<s:property value="model.strRoa" />
								%
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.ROE" />
							</div>
							<div class="rowc">
								<s:property value="model.strRoe" />
								%
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.quotes.cellnames.leverage" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strLeverage" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="web.label.PE" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strPe" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="ir.si.thongtincoban.PriceToBook" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strPB" />
							</div>
						</li>
						<li>
							<div class="rowb">
								<s:text name="ir.si.thongtincoban.Beta" />
							</div>
							<div class="rowc">
								<s:property value="model.ifoComSnapshotViewExt.strBeta" />
							</div>
						</li>
					</ul>
				</div>
				<!-- end column statistic 2 -->
				<!-- chart -->
				<div class="bieudokythuat">
					<div id="hsChartOnSnapshot">
						<img src="<web:url value='/images/ajax-loader.gif'/>" />
					</div>
					<div style="float: left; margin-left: 10px; display: inline-block;">
						<a style="float: left;"
							href="<web:url value="/quan-he-co-dong-vndirect/bieu-do-ky-thuat.shtml"/>"> <input
								class="iButton" type="button"
								style="width: 245px;"
								value='<s:text
                                    name="irSnapshot.content.viewChart">Xem biểu đồ chi tiết</s:text>' />
						</a>
						<input
							style="position: absolute; right: 5px; top: 0px; z-index: 1; padding: 1px;"
							class="hsButton" type="button" value="all"
							onclick="drawAllHSChartOnIRSISnapshot();" />
						<input
							style="position: absolute; right: 30px; top: 0px; z-index: 1; padding: 1px;"
							class="hsButton" type="button" value="3m"
							onclick="draw3mHSChartOnIRSISnapshot();" />
					</div>
				</div>
				<!-- end chart -->
			</div>
			<!-- End left content -->

			<!-- right content -->
			<div id="c-column" class="width240">
				<div class="ir-si-vndIndex">
					<span id="currentPrice"></span>&nbsp;&nbsp;
					<span id="strTodayChange"></span>
				</div>
				<!-- Chi so pwr -->
				<div class="box_chiso_PR">
					<div class="title">
						<a style="color: #f39200;"
							href="<web:url value='y-tuong-dau-tu/power-ratings.shtml' />"><s:text
								name="analysis.stockInfo.company.snapshot.pwr">Chí số Power Rating</s:text>
						</a>
					</div>
					<ul id="index">
						<li>
							<s:text name="analysis.stockInfo.company.snapshot.point">Điềm Pwr</s:text>
							:
							<span class="orange" id="todayPwr"></span>
						</li>
						<li>
							<s:text name="analysis.stockInfo.company.snapshot.pointLast4">Pwr 4 hôm trước</s:text>
							:
							<span class="orange" id="lastFourPwr"></span>
						</li>
					</ul>
					<span id="message"></span>
				</div>
				<!-- End Chi so pwr -->
			</div>
			<!-- End right content -->

			<!-- giao dich noi bo -->
			<div class="box_giaodichnoibo"
				style="margin-top: 25px; float: left; width: 100%;">
				<div class="title_tab">
					<div class="bg_righttab">
						<h2>
							<s:text name="web.label.InsiderTransactions.InsiderTrans">Giao dịch cổ phiếu nội bộ</s:text>
						</h2>
					</div>
				</div>
				<table cellpadding="0" cellspacing="0" class="boxlist" width="100%">
					<tr class="title">
						<th class="row_time">
							<s:text name="web.label.date">Ngày</s:text>
						</th>
						<th class="row_name">
							<s:text name="web.label.InsiderTransactions.Insider">Giạo dịch cổ đông nội bộ</s:text>
						</th>
						<th class="row_cv">
							<s:text name="web.label.InsiderTransactions.Position">Chức vụ</s:text>
						</th>
						<th class="row_a">
							<s:text name="web.label.InsiderTransactions.SharesHeld">Số cổ phần giao dịch</s:text>
						</th>
						<th class="row_cv">
							<s:text name="web.label.InsiderTransactions.Transaction">Loại giao dịch</s:text>
						</th>
						<th class="row_b">
							<s:text name="web.label.InsiderTransactions.Price">Giá</s:text>
						</th>
					</tr>
					<s:iterator value="model.ifoInsiderTransactionViewList"
						status="status">
						<tr>
							<td class="row_time">
								<s:property value="id.transactionDateStr" />
							</td>
							<td class="row_name">
								<s:property value="id.insider" />
							</td>
							<td class="row_cv">
								<s:property value="id.position" />
							</td>
							<td class="row_a">
								<fmt:formatNumber pattern="##,###,###.##">
									<s:property value="id.sharesHeld" />
								</fmt:formatNumber>
							</td>
							<td class="row_cv">
								<s:text name="web.label.InsiderTransactions.%{id.transaction.toLowerCase()}"></s:text>
							</td>
							<td class="row_b">
								<s:if test="%{id.price >= 0}">
									<fmt:formatNumber pattern="##,###,###.##">
										<s:property value="id.price" />
									</fmt:formatNumber>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>