<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
$(function() {
    FROM_MODULE = URL_FINANCIAL_REPORT;
});

function _goTo(index) {
	document.getElementById("pagingIndexId").value = index;
	document.fSSCFilling.submit();
	return;
}
</script>

<form name="fSSCFilling" id="fSSCFilling" method="post">
	<input type="hidden" name="pagingInfo.indexPage" id="pagingIndexId"
		value="0" />
	<div class="content_ttpt">
		<!-- nav -->
		<jsp:include
			page="/WEB-INF/jsps/listedmarket/snippet/si-company-nav.jsp"></jsp:include>

		<div class="content_small">
			<div class="content_tab">
				<div class="box_baotaichinh" id="fSearchSymbol_paging">
					<div class="bt_change"
						style="margin-top: 5px !important; margin-bottom: 15px;">
						<web:showPaging usingURLForNav="false" navAction="_goTo"
							pagingInfo="${pagingInfo}"></web:showPaging>
					</div>

					<div class="clear"></div>
					<div class="box_header clearfix">
						&nbsp;
					</div>
					<s:actionerror
						cssStyle="color: red; display: inline; text-align: left; font-weight: bold;"></s:actionerror>
					<ul class="listbc clearfix">
						<s:iterator value="model.searchResult" status="status">
							<li>
								<div class="time">
									<s:date name="releasedDate" format="dd/MM/yyyy" />
								</div>
								<div class="rowb">
									<s:if test="docType == -2">
										<span style="font-weight: bold;"><s:property
												value="#title" /> </span>
										<span
											style="font-weight: bold; font-size: 10px; width: 1px">(<s:property
												value="contributor" />)</span> &nbsp;
                                        </s:if>
									<s:else>
										<a
											href="javascript:download('<s:property value="filePath"/>', 'research', '<s:property value="fileName"/>');">
											<span style="font-weight: bold;"> <s:property
													value="title" /> </span> <span
											style="font-weight: bold; font-size: 10px; width: 1px">(<s:property
													value="contributor" />)</span> &nbsp; </a>
									</s:else>
								</div>
								<div class="dowloadtl">

									<s:if test="docType == -2">
										<s:text name="web.label.SSCFillingAction.form.Nofile" />
									</s:if>
									<s:if test="docType == 1">
										<a
											href="javascript:download('<s:property value="filePath"/>', 'research', '<s:property value="fileName"/>');">
											<img src="<web:url value='/images/icons/typedoc.gif'/>" border="0" />
											&nbsp; <s:text
												name="web.label.SSCFillingAction.form.Buttons.Download" />
										</a>
									</s:if>
									<s:if test="docType == 2">
										<a
											href="javascript:download('<s:property value="filePath"/>', 'research', '<s:property value="fileName"/>');">
											<img src="<web:url value='/images/icons/icon_pdf.png'/>" /> &nbsp; <s:text
												name="web.label.SSCFillingAction.form.Buttons.Download" />
										</a>
									</s:if>
									<s:if test="docType == 3">
										<a
											href="javascript:download('<s:property value="filePath"/>', 'research', '<s:property value="fileName"/>');">
											<img src="<web:url value='/images/icons/typeexcel.gif'/>" border="0" />
											&nbsp; <s:text
												name="web.label.SSCFillingAction.form.Buttons.Download" />
										</a>
									</s:if>
									<s:if test="docType == 4">
										<a
											href="javascript:download('<s:property value="filePath"/>', 'research', '<s:property value="fileName"/>');">
											<img src="<web:url value='/images/icons/typezip.gif'/>" border="0" />
											&nbsp; <s:text
												name="web.label.SSCFillingAction.form.Buttons.Download" />
										</a>
									</s:if>
								</div>
							</li>
						</s:iterator>
					</ul>
					<div class="bottom_bctc clearfix">
						&nbsp;
					</div>
				</div>
			</div>
			<!-- end .content_ttpt -->
		</div>
	</div>
</form>