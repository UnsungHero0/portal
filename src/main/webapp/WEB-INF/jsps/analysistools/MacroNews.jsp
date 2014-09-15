<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

 
<div class="pn_main" id="page_TTNY">	
	<div class="tabs_TTNY">		
		<ul id="ui-tabs-nav" class="ui-tabs-nav clearfix">
			            		
            <li class=""><a href="<web:url value='/analysis/AnalysisHome.shtml'/>"><b>Tin trong nước</b></a></li>
        	<li class=""><a href="<web:url value='/analysis/MacroNews_MacWorld.shtml'/>"><b>Tin quốc tế</b></a></li>
        	<li class=""><a href="<web:url value='/analysis/Commentaries.shtml'/>"><b>Ý kiến chuyên gia</b></a></li>
        	<li class=""><a href="<web:url value='/analysis/MacroNews_Disclousure.shtml'/>"><b>Công bố thông tin</b></a></li>
       		<li class=""><a href="<web:url value='/analysis/MarketCalendar.shtml'/>"><b>Lịch sự kiện</b></a></li>       		            					
       					
		</ul>
		<!--tab1-->
		
		<div class="ctTab_TTNY" id="fragment-1" align="left">
		
			<div class="top_inner clearfix">
				<div class="right fr"></div>
			</div>			
			<div class="center_inner clearfix">
	
			<div>
				<div class="general pn_main" id="ctn-general">
					<div class="bd">						
						<div class="padding0px">
							<div class="aritcle-top">
								<s:if test="news.imagesList[0].length() > 0">								
                             		<img src='<s:url value="%{@vn.com.vndirect.commons.utility.VNDirectWebUtilities@getWebResourceDownloadThunbnail()}image/%{news.imagesList[0].substring(7)}&catType=REPORT&thumbWidth=198&thumbHeight=162"/>' class="img-aritcle-top" />
                             	</s:if><s:else>
                             		<img src='<s:url value="/images/img/directNews.jpg" />' class="img-aritcle-top" />
                             	</s:else>
                             	
                             	<s:if test="#text == 'MacVN'">
                             		<s:set var="url"><web:url value="/listed/NewsDetail_MacVN.shtml?ifoNews.newsId="/><s:property value="news.newsId"/>&newsType=<s:property value="#text"/></s:set>
                             	</s:if>
                             	<s:else>
                             		<s:set var="url"><web:url value="/listed/NewsDetail_MacWorld.shtml?ifoNews.newsId="/><s:property value="news.newsId"/>&newsType=<s:property value="#text"/></s:set>
                             	</s:else>                             	                             	
                             	<p class="TitleAR"><a href='<s:property value="#url"/>'>${news.newsHeader}</a></p> 
                                <p class="textcreatnews"><s:date name="news.newsDate" format="hh:mm a dd/MM/yyyy" /> | <b>${news.newsResource}</b></p>
                                <div class="descAR">
                                  	<p>${news.newsAbstract}</p>
                                </div>
                                <div class="ClearBoth"></div>
                            </div>  <!-- //aritcle-top -->                            
                            <div class="list-aritcle-bottom" id="aritcleListNews"></div>                            
                            <div id="web_navAritcleList" style="width: 98%; margin-left: 5px;" align="right"></div>                             
                            <div class="DivBoxBottom">
	                       		<div style="float:left; width:58%; padding-left:10px;" id="otherNews"></div>
	                            <div style="float:right; width:38%; padding-right:10px;">
	                            	<div class="general pn_main" id="ctn-general">
	                                    <ul class="hd">
	                                        <li class="hd-left"></li>
	                                        <li class="hd-right"></li>
	                                        <li class="hd-center"><div class="clearfix"><h5><a href="#"><s:text name="web.label.analysisTool.mostViewedNews">Most View News</s:text></a></h5></div></li>
	                                    </ul>
	                                    <div class="bd">
	                                        <div class="padding0px">
	                                        	<div class="newsmost" id="mostViewNews"></div>
	                                        	<div class="bottom_inner clearfix">
	                                            	<div class="left fl"></div>
	                                            	<div class="right fr"></div>
	                                        	</div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="ClearBoth"></div>
	                       </div><br/>	                       
						</div>
					</div>
				</div>		
			</div> 
		</div>		
	</div>	
</div>