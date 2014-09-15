<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>


 <table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tbody>
		<tr>
			<td>
				<form name="fNewsViewDetail" id="fNewsViewDetail" action='' method="post">
					<table class="n-dn" width="95%" align="center" border="0" cellspacing="0" cellpadding="0">
		          		<tr>
		            		<td valign="top">
								<p><c:out value='${model.ifoNews.displayNewsDate}' /> <s:property value="ifoNews.displayNewsDate" /> - <strong><s:property value="model.ifoNews.newsResource" /></strong> </p>
								<p> <s:property value="ifoNews.newsHeader" /></p>
								<p>
									<c:out value='${model.ifoNews.newsContent}' escapeXml="false"/>  
								</p>
								<div>
			                        <span>
			                        <c:forEach var="item" items="${model.ifoNews.newsAttWithoutVideos}">
			                            <a href = "javascript:doDownloadFile('<c:out value = '${item.originalLink}'/>', '<c:out value = '${item.uriLink}'/>');">
			                                <c:out value='${item.originalLink}' /> <br>
			                            </a>
			                        </c:forEach>
			                        </span>
			                    </div>
			                    <br/>
							</td>
		          		</tr>
        			</table>
				</form>
			</td>
		</tr>
	</tbody>
</table>
