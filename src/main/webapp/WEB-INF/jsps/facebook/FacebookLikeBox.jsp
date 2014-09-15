<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div id="fb-root"></div>
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

<div class="fb-like-box" data-href="https://www.facebook.com/vndirect"
	data-height="250" data-width="292" data-show-faces="true"
	data-stream="false" data-header="false"
	style="margin-left: 35px; margin-top: 10px;"></div>