<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="content_ttpt">
	<div class="content_tab">
		<!-- sub menus -->
		<jsp:include page="/WEB-INF/jsps/about/snippet/aboutNav.jsp"></jsp:include>

		<!-- left content -->
		<div class="content_ttpt">
			<div class="content_lichsu">
				<div class="box_content" id="historyContent">
					${wpSubject.subjectContent}</div>
				<div class="clear"></div>

				<div class="box_slide_lichsupt">
					<div class="prev">
						<img src="<web:url value='/images/button/prev.png'/>"
							alt="Previous">
					</div>
					<div class="next">
						<img src="<web:url value='/images/button/next.png'/>" alt="Next">
					</div>
					<div class="slide" id="sequence">
						<ul>
							<li>
								<div class="box_file">
									<h3 class="active">
										<s:text name="aboutVndirect.history.snapshot">Tổng quan</s:text>
									</h3>
									<div class="photo" ref="history-tongquan">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/tongquan.png'/>" />
									</div>
								</div>
								<div class="box_file">
									<h3>07/11/2006</h3>
									<div class="photo" ref="history-thanhlap">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/thanhlap.png'/>" />
									</div>
								</div>
								<div class="box_file">
									<h3>04/2007</h3>
									<div class="photo" ref="history-HCM">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/thanhlap-hcm.png'/>" />
									</div>
								</div>
								<div class="box_file">
									<h3>06/2007</h3>
									<div class="photo" ref="history-GDTT">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/gdtt.png'/>" />
									</div>
								</div>
							</li>
							<li>
								<div class="box_file">
									<h3>11/2007</h3>
									<div class="photo" ref="history-300">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/300.png'/>" />
									</div>
								</div>
								<div class="box_file">
									<h3>12/2008</h3>
									<div class="photo" ref="history-truso">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/truso.png'/>" />
									</div>
								</div>
								<div class="box_file">
                                    <h3>09/2009</h3>
                                    <div class="photo" ref="history-CK2009">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/ckuytin.png'/>" />
                                    </div>
                                </div>
                                <div class="box_file">
                                    <h3>01/2010</h3>
                                    <div class="photo" ref="history-Tin&dung">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/dvTinDungVN.png'/>" />
                                    </div>
                                </div>
							</li>
							<li>
							    <div class="box_file">
                                    <h3>02/2010</h3>
                                    <div class="photo" ref="history-450-new">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/450.png'/>" />
                                    </div>
                                </div>
                                <div class="box_file">
                                    <h3>30/03/2010</h3>
                                    <div class="photo" ref="history-HNX">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/hnx.png'/>" />
                                    </div>
                                </div>
								<div class="box_file">
									<h3>27/08/2010</h3>
									<div class="photo" ref="history-1000">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/1000.png'/>" />
									</div>
								</div>
								<div class="box_file">
                                    <h3>09/2010</h3>
                                    <div class="photo" ref="history-CK2010">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/gdttTinCayNhat.png'/>" />
                                    </div>
                                </div>
                            </li>
                            <li>
								<div class="box_file">
									<h3>
										<s:text name="aboutVndirect.history.thirdQuater">Quý 3 - 2011</s:text>
									</h3>
									<div class="photo" ref="history-MG">
										<img
											src="<web:url value='images/thumbs/lichSuPhatTrien/mg.png'/>" />
									</div>
								</div>
								<div class="box_file">
                                    <h3>12/2011</h3>
                                    <div class="photo" ref="history-TTtincay">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/ckuytin2.png'/>" />
                                    </div>
                                </div>
                                <div class="box_file">
                                    <h3>04/2013</h3>
                                    <div class="photo" ref="history-Saokhue">
                                        <img
                                            src="<web:url value='images/thumbs/lichSuPhatTrien/saokhue.png'/>" />
                                    </div>
                                </div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- end left content -->

		<!-- Right content -->
		<div class="box_right_lichsu">
			<web:productSubject var="objProdSub" productCode="HISTORY_RIGHT_SIDE" />
			<c:out value="${objProdSub['product'].productOverview}"
				escapeXml="false" />
		</div>
		<!-- End right content -->
	</div>
</div>