<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
$(function() {
	BasicAnalysis.initLocEPS();
});
</script>

<div class="content_ttpt">
	<!--Start All Tab menu  -->
	<div class="content_small">
		<div class="content_doanhnghiep" style="width: 300px;float:left">
           <span style="width:200px"> Mã CK</span> <input style="height: 30px;margin-bottom:10px;" type="text" id="symbolToCheck" />
            <br/>
            <span style="width:100px">EPS Cùng kỳ</span> <input style="height: 30px;margin-bottom:10px;" type="text" id="acceptableLastQuarter" value="25" />
            <br/>
           <span style="width:100px"> EPS Trước</span> <input style="height: 30px;margin-bottom:10px;" type="text" id="acceptableSameQuarterLastYear" value="25" />
            <br/>
            <input type="button" value="Check" id="checkEPSBtn" />
            <br/><br/>
            <input type="button" value="Filter HNX" id="filterEPSHNXBtn" />
            <input type="button" value="Filter HOSE" id="filterEPSHOSEBtn" />
            <br/>
            <br/>
            <span style="font-size: 14px;" id="checkEPSResult"></span>
		</div>
		<div class="" style="width: 670px;float:left;">
		  <span style="color:green" id="goodList"></span>
		</div>
		<div class="clear"></div>
	</div>

    <hr>

	<div style="width: 100%;">
		<p id="_hxnSymbols"></p>
		<br>
		<hr>
		<br>
		<p id="_hoseSymbols"></p>
	</div>
</div>