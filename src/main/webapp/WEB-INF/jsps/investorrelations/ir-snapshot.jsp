<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".bieudokythuat>input.hsButton").click(function(){
		var buttonElements = $(".bieudokythuat").find("input.hsButton");
		$.each(buttonElements, function(index, item){$("#"+item.id).removeClass("button-active");});
		var buttonClickId = this.id;
		$("#"+buttonClickId).addClass("button-active");
		if(buttonClickId == "drawAll"){
			drawAllHSChartOnIRSnapshot();
		} else if(buttonClickId == "draw3mHS"){
			draw3mHSChartOnIRSnapshot();
		}
	});
});
</script>
<div id="content_ttpt">
	<div class="content_small" style="margin-bottom: 20px;">
		<div class="content_tab">
			<!-- nav -->
			<jsp:include
				page="/WEB-INF/jsps/listedmarket/snippet/ir-main-nav.jsp"></jsp:include>

			<span style="font-size: 19px; color: #F39200;"><s:text
					name="irSnapshot.content.title">QUAN HỆ CỔ ĐÔNG
				VNDIRECT</s:text> </span>
			<br />
			<span style="font-style: italic;"><s:text
					name="irSnapshot.content.subtitle">Cùng tạo nên giá trị
				cao hơn tại VNDIRECT</s:text> </span>
			<br />

			<div class="ir-snapshot-left1">
				<!-- Bieu do ky thuat -->
				<div class="bieudokythuat"
					style="border-bottom: 1px dotted #696969; padding-bottom: 20px;">
					<div id="hsChartOnSnapshot">
						<img src="<web:url value='/images/ajax-loader.gif'/>" />
					</div>
					<a style="float: left;"
						href="<web:url value="/quan-he-co-dong-vndirect/bieu-do-ky-thuat.shtml"/>">
						<input class="iButton" type="button"
							style="width: 204px;margin-left:10px;"
							value='<s:text name="irSnapshot.content.viewChart">Xem biểu đồ chi tiết</s:text>' />
					</a>
					<input id="drawAll"
						style="position: absolute; right: 5px; top: 0px; z-index: 1; padding: 1px;"
						class="hsButton" type="button" value="all"/>
					<input id="draw3mHS"
						style="position: absolute; right: 30px; top: 0px; z-index: 1; padding: 1px;"
						class="hsButton button-active" type="button" value="3m"/>
				</div>

				<!-- Gia -->
				<div class="companyQuote">
					<p>
						<span id="currentPrice"></span>&nbsp;&nbsp;
						<span id="strTodayChange"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">
						<span class="title"> + <s:text
								name="irSnapshot.content.volume">Khối lượng</s:text>:</span>
						<span class="index" id="strTotalVolumn"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">
						<span class="title"> + <s:text
								name="irSnapshot.content.marketPrice">Thị giá vốn</s:text>:</span>
						<span class="index" id="strMarketIndex"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">
						<span class="title"> + <s:text
								name="irSnapshot.content.52high">Cao 52 tuần</s:text>:</span>
						<span class="index" id="str52WeekHighestPrice"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">
						<span class="title"> + <s:text
								name="irSnapshot.content.52low">Thấp 52 tuần</s:text>:</span>
						<span class="index" id="str52WeekLowestPrice"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">

						<span class="title"> + <s:text
								name="irSnapshot.content.openPrice">Giá mở cửa</s:text>:</span>
						<span class="index" id="strOpenPrice"></span>
					</p>
					<p style="display: inline-block; width: 100%; margin-top: 3px;">
						<span class="title"> + <s:text
								name="irSnapshot.content.closePrice">Giá đóng cửa</s:text>:</span>
						<span class="index" id="strClosePrice"></span>
					</p>
					<a style="float: left; margin-top: 15px;"
						href="<web:url value="/quan-he-co-dong-vndirect/lich-su-gia.shtml"/>">
						<input class="iButton" type="button"
							style="width: 204px; margin-left: 10px;"
							value='<s:text
                                    name="irSnapshot.content.historicalPrice">Xem lịch sử giá</s:text>' />
					</a>
				</div>
			</div>

			<!-- Cot 2 -->
			<div class="ir-snapshot-left2">
				<div class="mostnews">
					<div class="titleBar bgTitleBar text-upper">
						<span id="margin-title"><a style="color: #f39200;"
							href="<web:url value='/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml'/>"><s:text
									name="irSnapshot.content.mostNews">TIN NỔI BẬT</s:text> </a> </span>
					</div>
					<ul class="n_list_other list_detail_tt_nd">
						<div class="n_list_other_detail" id="contents">
							<img src="<web:url value='/images/ajax-loader.gif'/>" />
						</div>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="reports">
					<div class="titleBar bgTitleBar text-upper">
						<span id="margin-title"><a style="color: #f39200;"
							href="<web:url value='/quan-he-co-dong-vndirect/bao-cao-tai-chinh.shtml'/>"><s:text
									name="irSnapshot.content.newsestReport">BÁO CÁO MỚI NHẤT</s:text>
						</a> </span>
					</div>
					<ul class="n_list_other list_detail_tt_nd">
						<div class="n_list_other_detail" id="contents">
							<img src="<web:url value='/images/ajax-loader.gif'/>" />
						</div>
					</ul>
					<div class="clear"></div>
				</div>
			</div>

			<!-- Cot 3 -->
			<div class="ir-snapshot-left3">
				<!-- Bao cao thuong nien -->
				<div class="baocaothuongnien">
					<div class="titleBar bgTitleBar text-upper">
						<span id="margin-title"><a style="color: #f39200;"
							href="<web:url value='/quan-he-co-dong-vndirect/bao-cao-thuong-nien-cao-bach.shtml'/>"><s:text
									name="irSnapshot.content.annualReport">BÁO CÁO THƯỜNG NIÊN</s:text>
						</a> </span>
					</div>
					<div class="baocao" style="margin-left: 10px; margin-top: 10px;">
						<a
							href="javascript:download('2012-04-23/2c565563-65ae-43b4-b37b-3692a83f69a0.pdf',%20'newsAttach',%20'VNDS_BCTN.pdf',%20'102326');">
							<img
								src="<web:url value='/images/thumbs/baocaothuongnien2011.png'/>"
								title="Báo cáo thường niên 2011" /> </a>
						<a style="font-size: 13px; font-weight: bold; margin-left: 10px;"
							href="javascript:download('2012-04-23/2c565563-65ae-43b4-b37b-3692a83f69a0.pdf',%20'newsAttach',%20'VNDS_BCTN.pdf',%20'102326');"><s:text
								name="irSnapshot.content.annualReport.2011">Báo cáo thường niên 2011</s:text></span>
					</div>
				</div>				
				<div style="width: 100%;">
					<!-- Lich su kien -->
					<a href="<web:url value="/quan-he-co-dong-vndirect/lich-su-kien.shtml"/>"> <img style="margin-top:5px; margin-left: 15px;"
							src="<web:url value='/images/thumbs/iconLSK_QHCD.png'/>"
							title="Lịch sự kiện" /> </a>				
					<a style="float: left; margin-top: 15px;"
						href="<web:url value="/quan-he-co-dong-vndirect/lich-su-kien.shtml"/>"> <input
							class="iButton" type="button"
							style="width: 175px; margin-left: 15px;"
							value='<s:text
	                                    name="irSnapshot.content.marketCalendar">Tra cứu lịch sự kiện</s:text>' />
					</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>