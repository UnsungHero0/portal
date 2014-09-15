<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<!-- This page is the top content of each page of Open Account Module -->
	<ul id="lichsuphattrien" class="ui-tabs-nav tab_ttpt">
		<li <web:menuOut code='subMenuHome_OpenAccount_DirectAccount' classValue='ui-tabs-selected'/>><a href="<web:url value='/mo-tai-khoan-nha-dau-tu.shtml'/>"><label class="icon_active"></label>Tài khỏan nhà đầu tư</a></li>
		<li <web:menuOut code='subMenuHome_OpenAccount_FreeUser' classValue='ui-tabs-selected'/>><a class="" href="<web:url value='/mo-tai-khoan-mien-phi.shtml'/>"><label class="icon_active"></label> Tài khoản miễn phí </a></li>
	</ul>
	<div class="clear"></div>
	

