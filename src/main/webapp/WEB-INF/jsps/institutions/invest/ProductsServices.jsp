<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>

<script type="text/javascript">
    $(document).ready(function(){
        /* This code is executed after the DOM has been completely loaded */
        
        var totWidth=0;
        var positions = new Array();
        
        $('#slides .slide').each(function(i){
            
            /* Traverse through all the slides and store their accumulative widths in totWidth */
            
            positions[i]= totWidth;
            totWidth += $(this).width();
            
            /* The positions array contains each slide's commulutative offset from the left part of the container */
            
            if(!$(this).width())
            {
                alert("Please, fill in width & height for all your images!");
                return false;
            }
            
        });
        
        $('#slides').width(totWidth);
    
        /* Change the cotnainer div's width to the exact width of all the slides combined */
    
        $('#menu ul li a').click(function(e,keepScroll){
    
                /* On a thumbnail click */
    
                $('li.menuItem').removeClass('act').addClass('inact');
                $(this).parent().addClass('act');
                
                var pos = $(this).parent().prevAll('.menuItem').length;
                
                $('#slides').stop().animate({marginLeft:-positions[pos]+'px'},450);
                /* Start the sliding animation */
                
                e.preventDefault();
                /* Prevent the default action of the link */
                
                
                // Stopping the auto-advance if an icon has been clicked:
                if(!keepScroll) clearInterval(itvl);
        });
        
        $('#menu ul li.menuItem:first').addClass('act').siblings().addClass('inact');
        /* On page load, mark the first thumbnail as active */
        
        
        
        /*****
         *
         *  Enabling auto-advance.
         *
         ****/
         
        var current=1;
        function autoAdvance()
        {
            if(current==-1) return false;
            
            $('#menu ul li a').eq(current%$('#menu ul li a').length).trigger('click',[true]);   // [true] will be passed as the keepScroll parameter of the click function on line 28
            current++;
        }
    
        // The number of seconds that the slider will auto-advance in:
        
        var changeEvery = 10;
    
        var itvl = setInterval(function(){autoAdvance()},changeEvery*1000);
    
        /* End of customizations */
    });
</script>

<div id="content">

    <ul class="ui-tabs-nav tab_ttpt" id="container-5">
        <li class="">
            <a href="<web:url value='/to-chuc-dau-tu/tong-quan.shtml'/>"><label class="icon_active"></label><s:text
                                        name="institutions.overview"/></a>
        </li>
        <li class="ui-tabs-selected">
            <a href="<web:url value='/to-chuc-dau-tu/san-pham-dich-vu.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.productsAndServices"/></a>
        </li>
        <li class="">
            <a href="<web:url value='/to-chuc-dau-tu/doi-ngu-chuyen-gia.shtml'/>" class=""><label class="icon_active"></label><s:text
                                        name="institutions.consultants"/></a>
        </li>
    </ul>
    <div class="clear"></div>
    
    <div  id="tab-2" class="content_khtc">
            <div class="bg_bo_ct_product">
                <div class="bg_bo_top_product">
                    <div class="box_product  bg_bo_bt_product" id="gallery">
                        <div id="menu"  class="list_product">
                            <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_MENU" />
                            <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                        </div>
                        <div   class="box_left_product_khtc">
                            <div class="box_list_slide">
                                <div  id="slides" >
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_1" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_2" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_3" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_4" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_5" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_6" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_7" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>
                                    <div class="slide">
                                        <web:productSubject var="objProdSub" productCode="INSTITUTIONS_PUBLISH_SERVICES_CONTENT_8" />
                                        <c:out value="${objProdSub['product'].productOverview}" escapeXml="false" />
                                    </div>                                                                                                                                                                                                                        
                                 </div> 
                             </div>  
                        </div>
                    </div>
                </div>
            </div>
      </div>
</div>