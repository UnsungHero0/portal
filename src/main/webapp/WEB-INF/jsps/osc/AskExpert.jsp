<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<style>
<!--
#yan-mystatus {
	background:
		url("../images/question-bg-top.gif")
		repeat-x scroll left top transparent;
	border: 1px solid #CCCCCC;
	padding: 10px;
	margin: 20px 10px 20px
}

.answer {
	background:
		url("../images/question-bg-top.gif")
		repeat-x scroll left top transparent;
	border: 1px solid #CCCCCC;
	padding: 5px 25px 5px;
	margin: 20px 10px 20px
}

.mod {
	background-color: #F0F8E6;
	border-top: 1px solid #CCCCCC;
	font-size: 93%;
	margin: 0 0 1em;
	padding: 0 12px 1em;
	position: relative;
}

.questions {
	border: 1px solid #CCCCCC;
	margin: 20px 10px 20px
}

.questions li {
	background: none repeat scroll 0 0 #FFFFFF;
	color: #666666;
	overflow: hidden;
	padding: 6px;
	position: relative;
}

li {
	list-style: none outside none;
}

.folder-news {
	margin-bottom: 10px;
	padding: 0 11px;
}

.label {
	text-transform: none;
	margin-top: 5px;
	color: #333333;
	display: block;
	font-size: 108%;
	font-weight: bold;
	background: none;
	padding: 0;
}
-->
</style>
<script type="text/javascript">
<!--
	var topicId = '<s:property value="topic.topicId"/>';
//-->
</script>
<div id="page_TTNY">
	<div class="tabs_TTNY" id="container-1">
		<div style="clear: both"></div>
		<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
			<li class="ui-tabs-selected">
				<a href="#fragment-1">
					<b>
						<s:text name="web.label.OSCAction.form.discussion"></s:text>
				 	</b> 
				</a>
			</li>
		</ul>
		<!--tabs 1-->
		<div class="ctTab_TTNY" id="fragment-1" align="left">
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>
			<div class="center_inner clearfix">
				<div class="mod" id="yan-mystatus">
					<div class="folder-news">
						<p>
							<b><s:property value="topic.topicName"/></b>
							<br>
							<label class="item-time">
								<s:date name="topic.effDate" format="hh:mm"/>
							</label>
							<label class="item-date">
								&nbsp;&nbsp;|&nbsp;&nbsp;<s:date name="topic.effDate" format="dd/MM/yyyy"/>
							</label>
							
						</p>
						<p>
							<s:property value="topic.topicContent" escape="false"/>
						</p>
					</div>
				</div>

				<ul class="questions"
					style="height: 400px; overflow-x: hidden; overflow-y: auto; padding: 0 8px;">
				</ul>
				
				<div align="right" id="navigator"
					style="margin: 20px 10px 20px">
					
				</div>

				<div class="answer">
					<label class="label" for="yan-answer-answer">
						<s:text name="web.label.OSCAction.form.your.question"></s:text>
					</label>
					<br />
					
					<textarea id="questionContent" cols="80" rows="9" name="questionContent"></textarea>
					<span class="btn_left_inbox"> <span class="btn_right_inbox">
							<span class="btn_center_inbox"> <input type="button" id="send"
									value="<s:text name="button.save"></s:text>"> </span> </span> </span>
					<span class="btn_left_inbox"> <span class="btn_right_inbox">
							<span class="btn_center_inbox"> <input type="button" id="close"
									value="<s:text name="button.close"></s:text>"> </span> </span> </span>
				</div>
			</div>

			<div class="bottom_inner clearfix">
				<div class="left fl"></div>
				<div class="right fr"></div>
			</div>
		</div>

		<!--end tabs-->
	</div>
</div>
