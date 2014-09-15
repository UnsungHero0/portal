<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vn.com.vndirect.commons.utility.VNDirectWebUtilities"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<ul class="ui-tabs-nav tab_ttpt">
	<li <web:menuOut code='home_help_qa' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml' />"><label
				class="icon_active"></label>
			<s:text name="br.help.guide">Hỏi đáp và hướng dẫn</s:text> </a>
	</li>
	<li
		<web:menuOut code='home_help_downloadForm' classValue='ui-tabs-selected'/>>
		<a href="<web:url value='/bieu-mau-ung-dung.shtml' />"><label
				class="icon_active"></label>
			<s:text name="br.help.form">Biểu mẫu</s:text> </a>
	</li>
	<li
        <web:menuOut code='home_help_releaseNote' classValue='ui-tabs-selected'/>>
        <a href="<web:url value='/release-note.shtml' />"><label
                class="icon_active"></label>
            <s:text name="br.help.releaseNote">Cập nhật các phiên bản website</s:text> </a>
    </li>
</ul>
<div class="clear"></div>
