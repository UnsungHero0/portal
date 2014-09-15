<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<div id="content">

    <ul class="ui-tabs-nav tab_ttpt" id="container-5">
        <li class="">
            <a href="<web:url value='/to-chuc-dau-tu/tong-quan.shtml'/>"><label class="icon_active"></label><s:text
                                        name="institutions.overview"/></a>
        </li>
        <li class="">
            <a href="<web:url value='/to-chuc-dau-tu/san-pham-dich-vu.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.productsAndServices"/></a>
        </li>
        <li class="ui-tabs-selected">
            <a href="<web:url value='/to-chuc-dau-tu/doi-ngu-chuyen-gia.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.consultants"/></a>
        </li>
    </ul>
    <div class="clear"></div>
    
    <div class="content_khtc ui-tabs-container" id="tab-3" style="">
        <div class="content_left boardOfExpertsContent">
            <div id="b1" style="display: block;">
                <web:productSubject var="objProdSub" productCode="EXPERT_BOARD_INVEST_1" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>

            <div id="b2" style="display: none;">
                <web:productSubject var="objProdSub" productCode="EXPERT_BOARD_INVEST_2" />
                <c:out value="${objProdSub['product'].productOverview}"
                    escapeXml="false" />
            </div>
                                                          
        </div>


		<div id="c-column" class="width340">
			<div class="box_listnew">
				<div class="tab_new">
					<div>
						<h2><span>ĐỘI NGŨ CHUYÊN GIA</span></h2>
					</div>
				</div>
				
				<div class="content_small boardOfExperts">
			        <web:productSubject var="objProdSub"
                        productCode="INFO_BOARD_OF_INVEST_EXPERTS" />
                    <c:out value="${objProdSub['product'].productOverview}"
                        escapeXml="false" />
				</div>
			</div>
		</div>
	</div>
</div>