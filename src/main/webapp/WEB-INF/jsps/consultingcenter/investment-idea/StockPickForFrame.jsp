<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script>
    $(function() {
        StockPick.initForOnlyBodyDemoNewsBox();
    });
</script>

<div style="width: 975px; margin: auto;">
	<c:if test="${not empty ifoNews}">
	    <div class="recommendBox"><h3 class="title">DEMO KHUYẾN NGHỊ MÃ ${ifoNews.strSymbol} ngày ${ifoNews.strNewsDate}</h3></div>
		<div id="newscontent1" class="boxAnalysis" style="width: 933px;margin-top: -3px;border-top:none;" ref="-demo-${ifoNews.strSymbol}">
			${ifoNews.newsContent }

			<jsp:include
				page="/WEB-INF/jsps/consultingcenter/investment-idea/khuyen-cao-snippet.jsp"></jsp:include>
		</div>
		<div class="clear"></div>
	</c:if>
</div>

<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2025955-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = 'https://ssl.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
