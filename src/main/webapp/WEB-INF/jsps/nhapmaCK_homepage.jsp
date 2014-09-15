<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<!--++nhap ma CK--->
<div id="box_bangck_new">
	<div class="content_mck">
		<div class="boxDefault_ck">
			<div id="MarketTray">
				<div class="searchMaCKHomepage">
					<div class="box_search_global_new">
						<input type="text" id="symbolSuggestionId"
							onfocus="if (this.value=='<s:text name="commons.symbolsearch.textbox"></s:text>') this.value='';"
							onblur="if (this.value=='') this.value='<s:text name="commons.symbolsearch.textbox"></s:text>';"
							value="<s:text name='commons.symbolsearch.textbox'></s:text>"
							class="input" name=""> <input type="button"
							class="button" id="fHome_btnSymbolSearch" name="Enter" />
					</div>
				</div>
				<div class="marketIndexHomepage">
					<ul class="box_bangCK">
						<li class="line">
							<div class="rowa nopadding" id="hose">
								<span class="right"><a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=1' />">HOSE</a>
								</span> <label class="print right" id="hoseIcon"></label>
							</div>
							<div class="rowa" id="vn30">
								<span class="right"><a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=2' />">VN30</a>
								</span> <label class="print right" id="vn30Icon"></label>
							</div>
							<div class="rowa" id="hnx">
								<span class="right"><a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=3' />">HNX</a>
								</span> <label class="print right" id="hnxIcon"></label>
							</div>
							<div class="rowa" id="hnx30">
								<span class="right"><a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=4' />">HNX30</a>
								</span> <label class="print right" id="hnx30Icon"></label>
							</div>
							<div class="rowa" id="upcom">
								<span class="right"><a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=5' />">UPCOM</a>
								</span> <label class="print right" id="upcomIcon"></label>
							</div>
						</li>
						<li class="line2">
							<div class="rowa nopadding" id="hose">
								<a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=1' />"><span
									id="currentIndex"></span> <br /> <span id="change"></span> </a>
							</div>
							<div class="rowa" id="vn30">
								<a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=2' />"><span
									id="currentIndex"></span> <br /> <span id="change"></span> </a>
							</div>
							<div class="rowa" id="hnx">
								<a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=3' />"><span
									id="currentIndex"></span> <br /> <span id="change"></span> </a>
							</div>
							<div class="rowa" id="hnx30">
								<a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=4' />"><span
									id="currentIndex"></span> <br /> <span id="change"></span> </a>
							</div>
							<div class="rowa" id="upcom">
								<a
									href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml?id=5' />"><span
									id="currentIndex"></span> <br /> <span id="change"></span> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="box_button_mtk">
					<c:choose>
						<c:when test="${isAuth}">
							<c:choose>
								<c:when test="${isFreeUser}">
									<button class="button" onclick='window.location.href="<web:url value="/thong-tin-co-ban-tai-khoan-mien-phi.shtml"/>";'><s:text name="homepage.openaccount.title">Mở tài khoản</s:text></button>
								</c:when>
								<c:otherwise>
									<button class="button" onclick='window.location.href="<web:url value="/thong-tin-co-ban.shtml"/>";'><s:text name="homepage.openaccount.title">Mở tài khoản</s:text></button>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<button class="button" onclick='window.location.href="<web:url value="/mo-tai-khoan-nha-dau-tu.shtml"/>";'><s:text name="homepage.openaccount.title">Mở tài khoản</s:text></button>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
			<div id="pull">
				<div style="height: 0px;" id="equities">
					<div class="pullup hidden"
						onclick="s.pageName='us.etrade.com:home';var s_code=s.t();if(s_code) document.write(s_code);">
						THU GỌN</div>
					<div class="content_mck_new hidden">
						<h1 class="titleh1">
							<s:text name="home.topMenu.analysis.marketOverview"></s:text><br /> <a href="<web:url value='/thong-ke-thi-truong-chung-khoan/tong-quan.shtml'/>" class="link">(Xem
								thêm tại đây)</a>
						</h1>
						<jsp:include
							page="/WEB-INF/jsps/home/market-snapshot/ListedMarketOnHomepage.jsp"></jsp:include>
					</div>
				</div>
				<div class="pulldown"
					onclick="s.pageName='us.etrade.com:home:marketsnapshot'; var s_code=s.t(); if(s_code) document.write(s_code);"><s:text 
				    name="home.topMenu.analysis.marketOverview"></s:text></div>
				<div class="pullup hidden"
					onclick="s.pageName='us.etrade.com:home';var s_code=s.t();if(s_code) document.write(s_code);"><s:text 
				    name="home.topMenu.analysis.marketOverview"></s:text></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/**/

	showhint = function showhint() {
		$("#customer_hint span, #customer_hint strong").addClass("home")
	};

	function wsodwidget() {
		var url = "//etrade.wsod.com/embed/6dff618a4426d4ef3931d4e373e86b4d/100.0.iframe.978x594/";
		$("#equities iframe").attr("src", url);

		/* wsodwidget = function() {
			return;
		}; */
	}

	$(".pullup, .pulldown").click(function() {
					$(
				"#equities > div.content_mck_new, #equities > h3, #equities iframe, #equities .quotes_field, #equities .quotes_go, #promo-full-width, .pullup, .pulldown, #wsod_canvas")
				.toggleClass("hidden");
	});

	var trayheight = 680; /* 70px less than wsod_canvas height offset */

	$(".pulldown").click(function() {
		$("#quote_canvas").animate({
			bottom : "-=".concat(trayheight)
		});
		var dyn_height = (trayheight - 20);
		$("#equities").addClass("tray_open").animate({
			height : dyn_height
		}, function(){
			$.publish("pullDownUpMCK");
		});
		//wsodwidget();
	});
	$(".pullup").click(function() {
		$("#equities").animate({
			height : 0
		}, function(){
			$.publish("pullDownUpMCK");
		});
		$("#quote_canvas").animate({
			bottom : "+=".concat(trayheight)
		});
		$("#MarketTray, #equities").removeAttr("class");
	});

	/**/
</script>
<!--++ end nhap ma chung khoan--->