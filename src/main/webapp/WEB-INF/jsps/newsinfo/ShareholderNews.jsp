<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<form id="fShareholderNews" name="fShareholderNews" method="post">
<input type="hidden" id="fShareholderNews_pagingInfo_indexPage" name="pagingInfo.indexPage" value="1" />
<div id="main" class="clearfix">
    	<table cellpadding="0" cellspacing="0" border="0" width="975">
        	<tr><td valign="top" width="696">
            		<div class="introduction clearfix">
                    	<ul class="hp">
                        	<li class="hp-left"></li>
                            <li class="hp-right"></li>
                            <li class="hp-center">
                            	<span>
                            		<a href="<web:url value="/vndirect/gioi-thieu-vndirect.shtml"/>"><s:text name="submenu.label.vndirect-info.vision">Mission, Vision</s:text></a>
                            	</span>|<span>
                            		<a href="<web:url value="/home/info/value.shtml"/>"><s:text name="submenu.label.vndirect-info.value">Value</s:text></a>
                            	</span>|<span>
                            		<a href="<web:url value="/home/info/team.shtml"/>"><s:text name="submenu.label.vndirect-info.team">Team</s:text></a>
                            	</span>|<span>
                            		<a target="_blank" href="https://www.vndirect.com.vn/careers"><s:text name="submenu.label.vndirect-info.career">Career</s:text></a>
                            	</span>|<span>
                            		<a href="<web:url value="/vndirect/tin-vndirect.shtml"/>"><s:text name="submenu.label.vndirect-info.newsVndirect">VNDIRECT News</s:text></a>
                            	</span>|<span>
                            		<b>&nbsp;&nbsp;&nbsp;<s:text name="submenu.label.vndirect-info.investorRelation">Investor Relations</s:text></b>
                            	</span>
                            </li>
                        </ul>
                        <div class="ct-textnone">
                            <p class="Title-general"><b><s:text name="submenu.label.vndirect-info.investorRelation">Investor Relations</s:text></b></p>
                            <div id="divShareholderNews.id"><div id="imageShareholderNews" style="height: 300px;"><img src="<web:url value='/images/ajax-loader.gif'/>" /></div></div>
                        </div>
                        <!--ft-->
                        <div class="ft" align="right" id="web_navShareholderNews">
                        </div>
                        <!---->
                    </div>
                    <!---->       
            	</td>
            	<td width="9">&nbsp;</td>
            	<td width="270" valign="top">
                	<jsp:include page="../info/InfoRightContainer.jsp" />
                </td>
            </tr>
        </table>
    </div>
</form>    