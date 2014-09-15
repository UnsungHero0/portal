<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://www.webapp.com.vn/tags-fmt" prefix="webFmt" %>

<form name="fExpertDetail" id="fExpertDetail" method="post">
	<input type="hidden" id="fExperts_indexPage" name="fExperts_indexPage" value="1"/>
    <div class="pn_main" id="phan_tich_co_ban">
        <div class="tab clearfix">
			<div class="tab_infor">
				<a class="select_tab"><b><s:text name="leftmenu.label.analysisTool.Experts"/></b></a>
			</div>
			<div style="border:1px solid #616D7E; border-top:none;">
				<div class="top_inner clearfix">
					<div class="right fr"></div>
				</div>
				<div class="center_inner clearfix">
		            <div class="clearfix" style="padding:5px">
		            	<p>${subject.subjectName}</p>
						<p></p>
                        <p>${subject.subjectContent}</p>
		            </div>
		        </div>
				<div class="bottom_inner clearfix">
					<div class="left fl"></div>
					<div class="right fr"></div>
				</div>
			</div>
		</div>
   	</div>
	
</form>