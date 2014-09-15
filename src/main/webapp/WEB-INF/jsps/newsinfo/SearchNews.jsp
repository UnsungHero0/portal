<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
function _goTo(index) {
	$('#fSearchNews_pagingInfo_indexPage').val(index);
	var url = "";
	if (index == 1) {
		url = $.web_resource.getContext() + "tim-kiem-tin-tuc.shtml";
	} else if (index > 1) {
		url = $.web_resource.getContext() + "tim-kiem-tin-tuc-" + index + ".shtml";
	}
	document.fSearchNews.action = url;
	fSearchNews.submit();
}
</script>

<div id="content_ttpt" id="page_searchNews">
	<div class="content_small" style="width: 99%;">
		<div class="content_tab">
			<form id="fSearchNews" name="fSearchNews" method="post" action="">
				<input type="hidden" id="fSearchNews_pagingInfo_indexPage"
					name="pagingInfo.indexPage" value="1" />
				<input name="keyWord" type="hidden" id="fSearchNews_keyWord"
					value="${model.keyWord}" />
				<!-- display action error -->
				<s:actionerror
					cssStyle="color: red; display: inline; text-align: left; font-weight: bold;"></s:actionerror>
				<div class="clear"></div>
				<!-- header -->
				<div class="titleBar text-upper bgTitleBar">
					<span id="margin-title"><s:text
							name="web.label.SearchNewsAction.form.SearchAtVNDIRECT">Finding information at VNDIRECT</s:text>
					</span>
				</div>
				<!-- result -->
				<ul class="listnew_s">
					<li style="position: relative;">
						<h2 class="title">
							<span style="font-weight: bold;">[${pagingInfo.total}]</span>
							<span style="color: #333333;"><s:text
									name="web.label.SearchNewsAction.form.Messages.Resultbykeyword"></s:text>
							</span>
							<span>[${keyWord}]</span>
						</h2>
						<div class="paging"
							style="position: absolute; right: 0px; top: 5px;">
							<web:showPaging usingURLForNav="false" navAction="_goTo"
								pagingInfo="${pagingInfo}"></web:showPaging>
						</div>
					</li>
					<c:if test="${not empty model.searchResult}">
						<c:forEach var="item" items="${searchResult}">
							<li>
								<h2 class="title">
									<a href="${item.urlDetail}" />${item.newsHeader}</a>
								</h2>
								<p class="time">
									${item.newsDate}&nbsp;&nbsp;-
									<b> ${item.newsResource}</b>
								</p>
								<p class="newsAbstract">
									${item.newsAbstract}
								</p>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</form>
		</div>
	</div>
</div>


