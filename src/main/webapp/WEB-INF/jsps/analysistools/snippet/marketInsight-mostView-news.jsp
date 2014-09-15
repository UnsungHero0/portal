<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- market insight right content -->
<div class="box_listnew">
	<div class="titleBar titleBar-margin-icon">
		<span id="title"><s:text name="">TIN XEM NHIỀU</s:text></span>
	</div>
	<hr class="hrline" />
	<div class="content_small">
		<ul class="ndttlist" id="most_viewd_news">
			<c:forEach var="item" items="${model.searchMostView}" varStatus="itemIndex">
				<li><a href="${item.urlDetail}"><span class="ndttVideoIcon"></span>${item.newsHeader}</a> <span
					class="textcreatnews"> (${item.newsDateStr})</span></li>
			</c:forEach>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var obj_li = $('#most_viewd_news').find('li');
		$.each(obj_li, function(item,i){
			$(this).find('a').hover(
			function() {
				$(this).find(".ndttVideoIcon").stop().animate({
					opacity : 1
				});
			}, function() {
				$(this).find(".ndttVideoIcon").stop().animate({
					opacity : 0.5
				});
			});

		});
	});
</script>