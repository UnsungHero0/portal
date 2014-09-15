<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>

<div class="box_load">
	<ul class="list_menu_sup">
		<li class="title">
			<a href="<web:url value='/home.shtml'/>"><s:text
					name="home.topMenu.about.about">Trang chủ VNDIRECT</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_Info_Vision' classValue='active'/>
				href="<web:url value='/vndirect/gioi-thieu-vndirect.shtml'/>"><s:text
					name="aboutVndirect.aboutUs"> Giới thiệu
            VNDIRECT</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_Info_History' classValue='active'/>
				href="<web:url value='/vndirect/lich-su-phat-trien.shtml'/>"><s:text
					name="aboutVndirect.history">Lịch sử phát
            triển</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_Info_Board' classValue='active'/>
				href="<web:url value='/vndirect/doi-ngu-lanh-dao.shtml'/>"><s:text
					name="aboutVndirect.boards">Đội
            ngũ lãnh đạo</s:text> </a>
		</li>
		<li>
            <a <web:menuOut code='subMenuHome_VnDirect_news' classValue='active'/>
                href="<web:url value='/vndirect/tin-vndirect.shtml'/>"><s:text
                    name="home.topMenu.about.news">Tin
                VNDIRECT</s:text> </a>
        </li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a href="<web:url value='/vndirect/san-pham-dich-vu.shtml'/>"><s:text
					name="home.topMenu.about.products">Sản phẩm dịch vụ</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_Service' classValue='active'/>
				href="<web:url value='/vndirect/san-pham-dich-vu.shtml'/>"><s:text
					name="br.service.about">Giới
                thiệu</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='subMenuHome_OpenAccount' classValue='active'/>
				href="<web:url value='/mo-tai-khoan.shtml'/>"><s:text
					name="br.service.openaccount">Mở tài khoản</s:text> </a>
		</li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a href="<web:url value='/quan-he-co-dong-vndirect/tong-quan.shtml'/>"><s:text
					name="home.topMenu.about.investorRelation">
					<s:text name="">Quan hệ cổ đông</s:text>
				</s:text> </a>
		</li>
		<li>
            <a <web:menuOut code='home_ir_snapshot' classValue='active'/>
                href="<web:url value='/quan-he-co-dong-vndirect/tong-quan.shtml'/>"><s:text
                    name="irSnapshot.topmenu">Tổng quan</s:text> </a>
        </li>
		<li>
			<a <web:menuOut code='home_ir_news' classValue='active'/>
				href="<web:url value='/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml'/>"><s:text
					name="br.ir.news">Tin tức sự kiện</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='home_ir_si' classValue='active'/>
				href="<web:url value='/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml'/>"><s:text
					name="home.topMenu.analysis.stockInfo">Thông
				tin cổ phiếu</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='home_ir_financial' classValue='active'/>
				href="<web:url value='/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml'/>">
				<s:text name="br.ir.finance">Thông tin tài chính</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='home_ir_management' classValue='active'/>
				href="<web:url value='/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml'/>"><s:text
					name="br.ir.management">Thông
				tin quản trị</s:text> </a>
		</li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml'/>"><s:text
					name="br.help">Trợ giúp</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='home_help_qa' classValue='active'/>
				href="<web:url value='/tro-giup/hoi-dap-huong-dan.shtml'/>"><s:text
					name="br.help.guide">Hỏi đáp và hướng
				dẫn</s:text> </a>
		</li>
		<li>
			<a <web:menuOut code='home_help_downloadForm' classValue='active'/>
				href="<web:url value='/bieu-mau-ung-dung.shtml'/>"><s:text
					name="br.help.form">Biểu
				mẫu và ứng dụng</s:text> </a>
		</li>
		<li>
            <a <web:menuOut code='home_help_releaseNote' classValue='active'/>
                href="<web:url value='/release-note.shtml'/>"><s:text
                    name="br.help.releaseNote2">Các phiên bản website</s:text> </a>
        </li>
	</ul>
	<ul class="list_menu_sup line">
		<li class="title">
			<a target="_blank"
				href="https://www.vndirect.com.vn/careers/a-gioi-thieu"><s:text
					name="home.topMenu.about.career">TUYỂN
				DỤNG</s:text> </a>
		</li>
		<li>
			<a target="_blank"
				href="https://www.vndirect.com.vn/careers/a-gioi-thieu"><s:text
					name="home.topMenu.about.career.about">Giới
				thiệu</s:text> </a>
		</li>
		<li>
			<a target="_blank"
				href="https://www.vndirect.com.vn/careers/b_moi_truong"><s:text
					name="home.topMenu.about.career.environment">Môi
				trường làm việc</s:text> </a>
		</li>
		<li>
			<a target="_blank"
				href="https://www.vndirect.com.vn/careers/co-hoi-nghe-nghiep"><s:text
					name="home.topMenu.about.career.opportunity">Cơ
				hội nghề nghiệp</s:text> </a>
		</li>
		<li>
			<a target="_blank" href="https://www.vndirect.com.vn/careers/y_lhoso"><s:text
					name="home.topMenu.about.career.forApplicants">Dành
				cho ứng viên</s:text> </a>
		</li>
		<li>
			<a target="_blank" href="https://www.vndirect.com.vn/careers/ho-tro"><s:text
					name="home.topMenu.about.career.help">Hỗ
				trợ</s:text> </a>
		</li>
	</ul>
	
	<a class="icon_dow_supmenu"></a>
</div>