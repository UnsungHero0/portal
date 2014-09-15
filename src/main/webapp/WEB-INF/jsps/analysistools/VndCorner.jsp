<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<form name="fVndCorner" id="fVndCorner" method="post">
  	<input id="fVndCorner_indexPage" type="hidden" name="fVndCorner_indexPage" value="1"/> 	
         
   	<div class="pn_main" id="page_TTNY">
	   	<div class="tabs_TTNY" id="container-1" >
	      	<div style="clear:both"></div>
	        <ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">	         	
	           	<li class="ui-tabs-selected"><a><b><s:text name="leftmenu.label.analysisTool.Vndirect.Corner">Corner view of VNDirect</s:text></b></a></li>
	       	</ul>
	        <!--tabs 1-->
	        <div class="ctTab_TTNY" id="fragment-1" align="left">
	        	<div class="top_inner clearfix">
	            	<div class="right fr"></div>
	          	</div>
	          	<div class="center_inner clearfix">	              	
			      	<div class="center_inner">
			      		<div class="clearfix" style="padding:5px" id="fVndCorner_content"></div>
   						<div id="web_navVndCorner"></div>
		  			</div>		  			
		 		</div>
		 	</div>
		</div>
	</div>
</form>