<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<s:set var="btnClassification" value="true" scope="action"></s:set>
<s:set var="btnIndex" value="true" scope="action"></s:set>
<s:set var="btnOverview" value="false" scope="action"></s:set>

<c:if test='${not isAuth}'>
<div id="guess" title='' style="display: none;">
		<table>
			<tr>
				<td>
					<div id="warning">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<script>
		$().ready(function() {
			var warning = '<s:text name="web.message.not.log.in"/>';
			$('.auth').click(function(event){
				$('#guess').dialog({
					width: 500,
					resizable: false,
					buttons: {
						Close: function(){
							$('#guess').dialog('close');
						}
					}
				});
				
				$('#warning').html($.web_utils.format(warning, $(this).attr("href"), URL_OPEN_ACCOUNT));
				$('#guess').dialog('open');
				event.preventDefault(); 
			})
		})
	</script>
</c:if>

<tiles:insertDefinition name="Analysis.FundamentalityTmpl">
	<tiles:putAttribute name="MainContent">
		<!--twocols-->
		<div class="content_phantichnganh" style="margin-bottom: 15px;">		
			<div class="box_left">
					<div class="hd">
						<div class="hd-center">
							<div class="heading_pr">
								<div class="rowlv">
									<strong><s:text name="web.label.analysis.sector.overview.sector"></s:text></strong>
								</div>
								<div class="rowcs">
									<strong><s:text name="web.label.analysis.sector.overview.index"></s:text></strong>
								</div>
								<div class="rowtoday">
									<strong><s:text name="web.label.analysis.sector.overview.today.change"></s:text></strong>
								</div>
								<div class="rowm">
									<strong><s:text name="web.label.analysis.sector.overview.3month"></s:text></strong>
								</div>
								<div class="rowm3">
									<strong><s:text name="web.label.analysis.sector.overview.6month"></s:text></strong>
								</div>
							</div>
						</div>
					</div>

					<ul class="list_tt">
						<s:iterator value="sectors" var="sector">						
						<li>						
							<div class="rowlv">
							    <a href="<web:url value="/phan-tich-nganh/chi-tiet-linh-vuc.shtml"/>?sectorCode=<s:property value="#sector.values.iterator().next().sectorCode"/>">
                                    <s:property value="#sector.values.iterator().next().sectorName"/>
                                </a>
							</div>
							<div class="rowcs">
								<strong>
								    <s:if test="#sector.get('1000017').numericValue != null">
		                                <s:push value="#sector.get('1000017').numericValue">
	                                        <s:text name="format.double" var="num">
	                                            <s:param name="value" value="[0].top"/>
	                                        </s:text>
	                                        <s:property value='#num'/>
		                                </s:push>
		                            </s:if>
		                            <s:else>
		                                <span class="orange_color">0.00</span>
		                            </s:else>
								</strong>
							</div>
							<div class="rowtoday">
								<s:if test="#sector.get('1000028').numericValue != null">
	                            <s:push value="#sector.get('1000028').numericValue * 100">
	                                <s:text name="format.double" var="num">
	                                    <s:param name="value" value="[0].top"/>
	                                </s:text>
	                                <s:if test="[0].top > 0">
	                                	<span class="green"><s:property value='"+" + #num'/>%</span>
	                                </s:if>
	                                <s:elseif test="[0].top < 0"><span class="red"><s:property value='#num'/>%</span></s:elseif>
	                                <s:else><span class="orange_color">0.00%</span></s:else>
	                            </s:push>
	                            </s:if>
	                            <s:else>
	                               <span class="orange_color">0.00%</span>
	                            </s:else>
							</div>
							<div class="rowm">
								<s:if test="#sector.get('1000030').numericValue != null">
		                            <s:push value="#sector.get('1000030').numericValue * 100">	                            
		                                <s:text name="format.double" var="num">
		                                    <s:param name="value" value="[0].top"/>
		                                </s:text>
		                                <s:if test="[0].top > 0">
	                                		<span class="green"><s:property value='"+" + #num'/>%</span>
		                                </s:if>
		                                <s:elseif test="[0].top < 0"><span class="red"><s:property value='#num'/>%</span></s:elseif>
		                                <s:else><span class="orange_color">0.00%</span></s:else>	                            
		                            </s:push>
		                            </s:if>
		                            <s:else>
		                               <span class="orange_color">0.00%</span>
		                            </s:else>
						    </div>
							<div class="rowm3">
							    <s:if test="#sector.get('1000031').numericValue != null">
	                            <s:push value="#sector.get('1000031').numericValue * 100">
	                                <s:text name="format.double" var="num">
	                                    <s:param name="value" value="[0].top"/>
	                                </s:text>
	                                <s:if test="[0].top > 0">
	                                	<span class="green"><s:property value='"+" + #num'/>%</span>
	                                </s:if>
	                                <s:elseif test="[0].top < 0"><span class="red"><s:property value='#num'/>%</span></s:elseif>
	                                <s:else><span class="orange_color">0.00%</span></s:else>
	                            </s:push>
	                            </s:if>
	                            <s:else>
	                               <span class="orange_color">0.00%</span>
	                            </s:else>
							</div>
						</li>	
				        </s:iterator>										
					</ul>
			</div>
			
			<div class="box_right" id="ctn-general">
	            <div class="content">
	               <ul class="list">
	                   <li class="title">
	                       <h2><s:text name="web.label.analysis.sector.overview.hot.sector">Hot Sectors</s:text></h2>
	                       <div class="box">
                                <s:text name="web.label.analysis.sector.overview.hot.subSector"></s:text>
                                <span class="text"><s:text name="web.label.analysis.sector.overview.5days.perf"></s:text></span>
                           </div>
	                   </li>
			            <s:iterator value="hotSectors">
                            <li>
								<div class="box">
									<a class='auth' href="<web:url value="/phan-tich-nganh/chi-tiet-linh-vuc.shtml"/>?sectorCode=<s:property value="sectorCode"/>">
                                        <s:property value="sectorName"/>
                                    </a>
								    <s:if test="numericValue != null">
								        <span class="text">
                                        <s:push value="numericValue * 100">
                                            <span class="text">                                    
                                            <s:text name="format.double" var="num">
                                                <s:param name="value" value="[0].top"/>
                                            </s:text>
                                            <s:if test="[0].top > 0">
			                                	<span class="green"><s:property value='"+" + #num'/>%</span>
			                                </s:if>
			                                <s:elseif test="[0].top < 0"><span class="red"><s:property value='#num'/>%</span></s:elseif>
			                                <s:else><span class="orange_color">0.00%</span></s:else>                                  
                                        </s:push>
                                        </span>
                                    </s:if>
                                    <s:else>
                                        <span class="orange_color">0.00%</span>
                                    </s:else>
								</div>
								<div class="box">
									<s:text name="web.label.analysis.sector.overview.top.industries"></s:text>:
								    <a class="orange" href="<web:url value="/phan-tich-nganh/chi-tiet-nganh.shtml"/>?sectorCode=<s:property value="sectorCode"/>&industryCode=<s:property value="industryCode"/>">
                                        <s:property value="industryName"/>
                                    </a> 									
								</div>
							</li>
                        </s:iterator>         						
					</ul>
	                <ul class="list">
                       <li class="title">
                           <h2><s:text name="web.label.analysis.sector.overview.hot.industries"></s:text></h2>
                           <div class="box">
                                <s:text name="web.label.analysis.sector.overview.top.industries"></s:text>
                                <span class="text"><s:text name="web.label.analysis.sector.overview.5days.perf"></s:text></span>
                           </div>
                       </li>

						<s:iterator value="hotIndustries">
	                        <li>
	                            <div class="box">
	                                <a class='auth' href="<web:url value="/phan-tich-nganh/chi-tiet-nganh.shtml"/>?sectorCode=<s:property value="sectorCode"/>&industryCode=<s:property value="industryCode"/>"><s:property value="industryName"/></a>	                                
	                                <s:if test="numericValue != null">
                                         <s:push value="numericValue * 100">
                                         <span class="text">
                                             <s:text name="format.double" var="num">
                                                 <s:param name="value" value="[0].top"/>
                                             </s:text>
                                             <s:if test="[0].top > 0">
			                                	<span class="green"><s:property value='"+" + #num'/>%</span>
			                                </s:if>
			                                <s:elseif test="[0].top < 0"><span class="red"><s:property value='#num'/>%</span></s:elseif>
			                                <s:else><span class="orange_color">0.00%</span></s:else>
                                         </span>
                                         </s:push>
                                     </s:if>
                                     <s:else>
                                         <span class="orange_color">0.00%</span>
                                     </s:else>
	                            </div>
	                            <div class="box">
	                                <s:text name="web.label.analysis.sector.overview.top.companies"></s:text>:
	                                <a class="orange" href="<web:url value='/tong-quan/'/><s:property value="secCode"/>.shtml">                                                      
                                        <s:property value="secCode"/>                                                     
                                    </a>
	                            </div>
	                        </li>
						</s:iterator>                                                              						
					</ul>	                 
	           </div>
	        </div>
		</div>
		<!--twocols-->
		<!---->
		<!---->
	</tiles:putAttribute>
</tiles:insertDefinition>