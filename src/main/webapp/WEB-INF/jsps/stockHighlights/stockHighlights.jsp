<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$().ready(function() {
		loadReports('newest');
		loadListSymbolsHaveReport();
	});
</script>

<jsp:include page="/WEB-INF/jsps/analysistools/snippet/analysis-news-nav.jsp"></jsp:include>

<div class="stockCenter">
	<div class="listReport" id="listSymbolsHaveReports">
	   <span class="ajaxLoadingIcon"></span>
	</div>
	<!--/listReport-->

	<div class="boxStockFocus">
		<div class="newReport">
			<h2>Báo cáo mới nhất</h2>
			<ul id="listReportsContent">
			</ul>
			<a id="loadMoreReportsBtn" class="viewmore" onclick="loadMoreReports();">Xem thêm...</a>
		</div>
		<!--/newReport-->

		<div class="stockFocusRight">
			<img src="<web:url value='/images/cophieutamdiem.jpg' />" alt="Cổ phiếu tâm điểm" />
			
			<!-- plugin_social -->
			<div class="plugin_social">
		        <div class="facebook">
		            <!-- like facebook -->
		            <div class="fb-like" data-send="false" data-layout="button_count"
		                data-width="450" data-show-faces="false"></div>
		            <!-- end like facebook -->
		        </div>
		        <script>
	                (function(d, s, id) {
	                    var js, fjs = d.getElementsByTagName(s)[0];
	                    if (d.getElementById(id))
	                        return;
	                    js = d.createElement(s);
	                    js.id = id;
	                    js.src = "//connect.facebook.net/vi_VN/all.js#xfbml=1&appId=367667496651948";
	                    fjs.parentNode.insertBefore(js, fjs);
	                }(document, 'script', 'facebook-jssdk'));
	            </script>
		
		        <div class="googlePlus">
		            <!--  +1 button google -->
		            <div class="g-plusone" data-size="medium"></div>
		            <script type="text/javascript">
						window.___gcfg = {
						    lang : 'vi'
						};
						
						(function() {
						    var po = document.createElement('script');
						    po.type = 'text/javascript';
						    po.async = true;
						    po.src = 'https://apis.google.com/js/plusone.js';
						    var s = document.getElementsByTagName('script')[0];
						    s.parentNode.insertBefore(po, s);
						})();
						</script>
		            <!-- end +1 button google -->
		        </div>
		    </div>
		    <!-- end plugin_social -->

			<form onsubmit="return isAvailableSubmitVoteProduct();"
				action="https://docs.google.com/forms/d/1eMAUU2zrqQOfvyBhDK9oSeofPQ7L75G1Vq3V29VXmDc/formResponse"
				method="POST">
				<div class="textareaReply">
					<textarea class="reviewTextarea" name="entry.2125613608"
						id="entry2125613608"
						onfocus="if (this.value=='Gửi bình luận/ góp ý về sản phẩm') this.value='';"
						onblur="if (this.value=='') this.value='Gửi bình luận/ góp ý về sản phẩm';">Gửi bình luận/ góp ý về sản phẩm</textarea>
					<input type="submit" value="Gửi" class="iButton reviewBtn" />
				</div>
			</form>
		</div>
		<!--/stockFocusRight-->
	</div>
	<!--/boxStockFocus-->
	<div class="clear"></div>
</div>

<div class="flexPaperViewer">
    <div id="flexPaperViewerContent"></div>
</div>