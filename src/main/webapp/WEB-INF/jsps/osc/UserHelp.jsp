<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>

<div id="content_ttpt">            
      <ul class="ui-tabs-nav tab_ttpt">
          <li class="">
              <a href="<web:url value="/thong-tin-co-ban.shtml"/>"><label class="icon_active"></label>Thông tin cơ bản </a>
          </li>
          <li class="">
              <a href="<web:url value="/thong-tin-giao-dich.shtml"/>"><label class="icon_active"></label> Thông tin giao dịch </a>
          </li>
          <li class="ui-tabs-selected">        
              <a href="<web:url value="/user/help.shtml"/>"><label class="icon_active"></label>Gửi phản hồi của bạn tới VNDIRECT</a>
          </li>
      </ul>
      
      <div class="clear"></div>
      
      <div>
          <!--++ content left-->
          <div class="content_mypage" style="margin-bottom: 10px;">
              <div class="content_trogiup_mypage">
                  <div class="bg_top">
                      <div class="bg_bt">
                      <script type="text/javascript">
                        window.onload = (function(){try{
                      
                          $("select").change(function () {
                      
                                var str = $("option:selected", this).html();
                      
                                  $(this).parent().find("span.text").text(str);
                              })
                      
                              .trigger('change');
                              
                        }catch(e){}});
                        </script>
                          <ul class="list">
                              <li>
                                  <div class="rowa">Tiêu đề</div>
                                  <div class="rowb">
                                      <input class="input">
                                  </div>
                              </li>
                              <li>
                                  <div class="rowa">Phản hồi về</div>
                                  <div class="rowb">
                                       <div class="select">
                                           <select>
                                              <option value="0">Flowers</option>
                                              <option value="1">Shrubs</option>
                                              <option>Trees</option>
                                              <option>Bushes</option>
                                              <option>Grass</option>
                                              <option>Dirt</option>
                                           </select>
                                           <span class="text">Shrubs</span>
                                      </div>
                                  </div>
                              </li>
                               <li>
                                  <div class="rowa">Nội dung</div>
                                  <div class="rowb">
                                      <textarea class="textarea" name="textarea" style="width: 423px; height: 354px;">                                                </textarea>
                                  </div>
                              </li>
                               <li class="padding-top14">
                                  <div class="rowa">&nbsp;</div>
                                  <div class="rowb">
                                      <span class="buttons"><input type="button" class="width75" value="Gửi"></span>
                                  </div>
                              </li>
                          </ul>
                       </div>
                  </div>
              </div>  
          </div>
          <!--//end content left-->
          <!--++c column-->
          <div class="width315" id="c-column" style="margin-top: 10px;">
              <div class="boxcontentcolumn">
                  <img src='<s:url value="/images/thumbs/img_qc.png" />'/>
              </div>
              <div class="boxcontentcolumn">
                  <ul>
                      <li>
                          <span class="icon_question_fast left"></span>
                          <div class="box_text_tienich line">
                              <a href="#">TRẢ LỜI NHANH CÂU HỎI THƯỜNG GẶP</a>
                          </div>
                      </li>
                      <li>
                          <span class="icon_guide left "></span>
                          <div class="line box_text_tienich">
                              <a href="#">HƯỚNG DẪN SỬ DỤNG SẢN PHẨM DỊCH VỤ</a>
                          </div>
                      </li>
                       <li>
                          <span class="icon_logo_vnd left"></span>
                          <div class="box_text_tienich">
                              <a href="#">VNDIRECT CÓ GÌ MỚI?</a>
                          </div>
                      </li>
                  </ul>
              </div>
          </div>
          <!--//c column-->
        </div>
</div>