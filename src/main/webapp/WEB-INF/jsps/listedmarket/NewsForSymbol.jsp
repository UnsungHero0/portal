<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<input type="hidden" id="fNewsForSymbol_pagingInfo_indexPage" value="1" />
<input type="hidden" id="fNewsForSymbol_currentSymbol"
	value="${currentSymbol}" />
<input type="hidden" id="localNewsTitle"
	value="<s:text name='home.ir.disclosure'>Công bố thông tin</s:text>" />
<input type="hidden" id="totalPage">
<div id="content_ttpt">
	<a id="top"></a>
	<!-- nav -->
	<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/ir-news-nav.jsp"></jsp:include>

	<div class="content_small">
		<div class="content_tab">
			<!-- left -->
			<div class="content_ttpt">
				<!-- loc thong tin -->
				<div class="box_loc">
					<s:text name="analysis.news.fromDate">Lọc tin từ</s:text>
					<input type="text" style="width:90px;"
						name="searchIfoNews.strFromNewsDate"
						id="fNewsForSymbol_fromDateId" />&nbsp;&nbsp;&nbsp;
					<s:text name="analysis.news.toDate">đến</s:text>
					<input type="text" style="width:90px;" name="searchIfoNews.strToNewsDate"
						id="fNewsForSymbol_toDateId" /> <input type="button" style="width:88px;margin-left:20px;"
						class="iButton" id="fNewsForSymbol_searchButton"
						value='<s:text name="button.search"/>'>
				</div>
				<!-- end loc thong tin -->

				<!-- news content -->
				<div class="ds_news" id="fNewsForSymbol_content"
					rel="quan-he-co-dong-vndirect">
					<img src="<web:url value='/images/ajax-loader.gif'/>" />
				</div>
				<div class="clear"></div>
				<div class="content_bar">
					<div id="loadingMoreImage" style="text-align: center;"></div>
					<div class="barGoTop">
						<a href="#top"><s:text name="newsMoreNav.backToTop">Về đầu trang</s:text></a>
					</div>
					<div class="barLoadMore">
						<a id="loadMore"><s:text name="newsMoreNav.more">Xem thêm</s:text></a>
					</div>
				</div>
			</div>
		</div>

		<!-- right -->
		<jsp:include page="/WEB-INF/jsps/listedmarket/snippet/right.jsp"></jsp:include>
	</div>
	<!-- end .content_ttpt -->
</div>
<!-- end .content_small -->
