<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="vndirectFmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
<!--
	var EDIT = '<s:text name="button.edit"/>';
	var DELETE = '<s:text name="button.delete"/>'
//-->
</script>
<s:set name="stockScreener" value="true" scope="action"></s:set>
<s:set name="listedScreenEnable" value="true" scope="action"></s:set>
<s:set name="listedScreen" value="false" scope="action"></s:set>
<s:set name="stockWizard" value="true" scope="action"></s:set>
<tiles:insertDefinition name="Analysis.InvestmentIdeasNavTmpl">
	<tiles:putAttribute name="MainContent">
		<s:actionerror cssStyle="color: red"/>
	  		  	
	   <div class="clearfixpadding-top10 padding-bottom10">
             <div class="hd">
                 <div class="hd-center">
                     <div class="heading_pr">
                         <h2><s:text name="Messages.Commons.Buttons.Saved">Tiêu chí đã lưu</s:text></h2>
                     </div>
                 </div>
             </div>
            <div class="bd">
            <div class="content_dv">
		  	<div id="listOfStockScreener" class="clearfix">
	        	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="tbl_sangloc">
	            	<thead>
	                	<tr class="title">
	                    	<th class="rowtt">
	                      		<s:text name="web.label.Result_View.ScreenName"/>
	                      	</th>
	                      	<th class="rowcompany_2">
                                <s:text name="web.label.Result_View.CurrentMatches"/>
	                      	</th>
	                      	<th class="rowtime">
	                      		<s:text name="web.label.Result_View.DateSaved"/>
	                      	</th>
	                      	<th class="row_xoa_edit"></th>
	                  	</tr>
	                </thead>
	                <tbody>
	                </tbody>     
	            </table>
				<div class="fpCalendar">
					<div class="fpCalendar-left"></div>
					<div class="fpCalendar-right"></div>
					<div align="right" class="fpCalendar-center" id="listOfStockScreenerNavigator"></div>
				</div>	               
	           </div>
	           </div>
	           </div>
       </div>
	</tiles:putAttribute>
</tiles:insertDefinition>