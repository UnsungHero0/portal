<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function(){
    if(!(${model.ifoBreakdownViewId.stateOwnership} == 0 && ${model.ifoBreakdownViewId.foreignOwnership} == 0 && ${model.ifoBreakdownViewId.other} == 0)){
	    chart = new Highcharts.Chart({
	        chart: {
	            renderTo: 'companyBreakdownChart',
	            plotBackgroundColor: '#fafafa',
	            plotBorderWidth: null,
	            plotShadow: false,
	            height:350,
	            backgroundColor: '#fafafa',
	        },
	        credits: {
	        	enabled: false
	        },
	        title: {
	            text: $('#fMaijorHolders_companyBreakdown').val()
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage}%</b>',
	            percentageDecimals: 2
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#333333',
	                    connectorColor: '#F39200',
	                },
	                //showInLegend: true
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: ' ',
	            data: [
	            	[$('#fMaijorHolders_state').val(), ${model.ifoBreakdownViewId.stateOwnership}],
	                [$('#fMaijorHolders_foreign').val(), ${model.ifoBreakdownViewId.foreignOwnership}],
	                [$('#fMaijorHolders_other').val(), ${model.ifoBreakdownViewId.other}]
	            ]
	        }]
	    });
    }
});
</script>
<input type="hidden" id="fMaijorHolders_companyBreakdown"
	value='<s:text name="web.label.MaijorHoldersAction.Breakdown">Cơ cấu cổ đông</s:text>' />
<input type="hidden" id="fMaijorHolders_state"
	value='<s:text name="web.label.MaijorHoldersAction.StateOwnership">Sở hữu nhà nước</s:text>' />
<input type="hidden" id="fMaijorHolders_foreign"
	value='<s:text name="web.label.MaijorHoldersAction.ForeignOwnership">Sở hữu của nhà đầu tư nước ngoài</s:text>' />
<input type="hidden" id="fMaijorHolders_other"
	value='<s:text name="web.label.MaijorHoldersAction.Other">Sở hữu khác</s:text>' />
<form name="fMaijorHolders" action="" method="post">
	<div id="content_ttpt">
		<!-- nav -->
		<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-si-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab" style="margin-top: 15px;">
				<div class="left_content450">
					<div class="title_tab">
						<div class="bg_righttab">
							<h2>
								<s:text name="web.label.MaijorHoldersAction.Breakdown">Cơ cấu cổ đông</s:text>
							</h2>
						</div>
					</div>
					<ul class="box_cocau_codong">
						<li>
							<div class="rowa">
								<s:text name="web.label.MaijorHoldersAction.StateOwnership">Sở hữu nhà nước</s:text>
							</div>
							<div class="rowb">
								<fmt:formatNumber pattern="##,###,###.##">
									<s:property value="model.ifoBreakdownViewId.stateOwnership" />
								</fmt:formatNumber>
								%
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.MaijorHoldersAction.ForeignOwnership">Sở hữu của nhà đầu tư nước ngoài</s:text>
							</div>
							<div class="rowb">
								<fmt:formatNumber pattern="##,###,###.##">
									<s:property value="model.ifoBreakdownViewId.foreignOwnership" />
								</fmt:formatNumber>
								%
							</div>
						</li>
						<li>
							<div class="rowa">
								<s:text name="web.label.MaijorHoldersAction.Other">Sở hữu khác</s:text>
							</div>
							<div class="rowb">
								<fmt:formatNumber pattern="##,###,###.##">
									<s:property value="model.ifoBreakdownViewId.other" />
								</fmt:formatNumber>
								%
							</div>
						</li>
					</ul>
				</div>
				<div class="right">
					<div id="companyBreakdownChart">
					</div>
				</div>
				<div class="clear"></div>

				<div class="box_codong_big">
					<div class="title_tab">
						<div class="bg_righttab">
							<h2>
								<s:text name="ir.si.ownerShip.header2">Các cổ đông lớn</s:text>
							</h2>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0" class="boxlist" width="100%">
						<tr class="title">
							<th class="row_name">
								<s:text name="web.label.MaijorHoldersAction.Holder">Họ và tên</s:text>
							</th>
							<th class="row_cv">
								<s:text name="web.label.MaijorHoldersAction.Position">Chức vụ</s:text>
							</th>
							<th class="row_a">
								<s:text name="web.label.MaijorHoldersAction.Shares">Số cổ phần</s:text>
							</th>
							<th class="row_a">
								<s:text name="web.label.MaijorHoldersAction.OwnerRatio">Tỷ lệ sở hữu</s:text>
							</th>
							<th class="row_time">
								<s:text name="web.label.MaijorHoldersAction.Reported">Ngày cập nhật</s:text>
							</th>
						</tr>
						<s:iterator value="model.ifoMaijorHolderViewList" status="status">
							<tr>
								<td class="row_name">
									<s:property value="id.holders" />
								</td>
								<td class="row_cv">
									<s:property value="id.position" />
								</td>
								<td class="row_a">
									<fmt:formatNumber pattern="###,##0.##">
										<s:property value="id.shares" />
									</fmt:formatNumber>
								</td>
								<td class="row_a">
									<fmt:formatNumber pattern="###,##0.##">
										<s:property value="id.ownerRatio" />
									</fmt:formatNumber>
									%
								</td>
								<td class="row_time">
									<s:property value="id.reportedStr" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>