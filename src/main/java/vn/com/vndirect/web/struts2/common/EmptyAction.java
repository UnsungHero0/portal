package vn.com.vndirect.web.struts2.common;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.struts2.common.EmptyModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * To return pageTitle, pageDesc, pageKeywords
 * 
 * @author minh.nguyen
 * 
 */
@SuppressWarnings("serial")
public class EmptyAction extends ActionSupport implements ModelDriven<EmptyModel> {
	private final Logger logger = Logger.getLogger(EmptyAction.class);

	private EmptyModel model = new EmptyModel();
	private String url;

	public String viewIRSnapshot() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("irSnapshot.topmenu"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// SEO
		model.setPageTitle(this.getText("home.ir.snapshot.title"));
		model.setPageKeywords(this.getText("home.ir.snapshot.key"));

		return SUCCESS;
	}

	public String viewAboutVndirectVision() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.vnd.about"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		model.setBreadcrumbs(breadcrumbs);
		return SUCCESS;
	}

	public String viewAboutVndirectHistory() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.vnd.history"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewAboutVndirectBoardOfDirectors() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.vnd.board"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewServiceAndProducts() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.about"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewOpenAccountDirect() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.direct"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewOpenAccountFree() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.free"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewOpenAccountRealEstate() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.realEstate"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewOpenAccountRealEstateBroker() throws Exception {
		doSEOpage();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.service.openaccount.realEstateBroker"));
		breadcrumbs.add(this.getText("br.service"));
		breadcrumbs.add("spdvRoot");
		breadcrumbs.add(this.getText("br.service.openaccount"));
		breadcrumbs.add("/mo-tai-khoan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewHelpGuides() throws Exception {
		doSEOpage();

		model.setPageTitle(this.getText("br.help.guide.title"));
		model.setPageDescription(this.getText("br.help.guide.desc"));
		model.setPageKeywords(this.getText("br.help.guide.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.help.guide"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.help"));
		breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewHelpForms() throws Exception {
		model.setPageTitle(this.getText("br.help.form.title"));
		model.setPageDescription(this.getText("br.help.form.desc"));
		model.setPageKeywords(this.getText("br.help.form.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.help.form"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.help"));
		breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String execute() throws Exception {

		doSEOpage();

		return SUCCESS;
	}

	public EmptyModel getModel() {
		return model;
	}

	public String viewIssuersOverview() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.overview"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.issuers"));
		breadcrumbs.add("/to-chuc-phat-hanh/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewIssuersProductsServices() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.productsAndServices"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.issuers"));
		breadcrumbs.add("/to-chuc-phat-hanh/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewIssuersConsultants() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.consultants"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.issuers"));
		breadcrumbs.add("/to-chuc-phat-hanh/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewInvestOverview() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.overview"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.investors"));
		breadcrumbs.add("/to-chuc-dau-tu/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewInvestProductsServices() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.productsAndServices"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.investors"));
		breadcrumbs.add("/to-chuc-dau-tu/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewInvestConsultants() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("institutions.consultants"));
		breadcrumbs.add(this.getText("home.topMenu.customerOrg"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("institutions.investors"));
		breadcrumbs.add("/to-chuc-dau-tu/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		doSEOpage();
		return SUCCESS;
	}

	public String viewDKSD() {
		// SEO
		model.setPageTitle(this.getText("common.dieuKhoanSuDung.title"));
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("common.dieuKhoanSuDung"));
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String viewPageNotFound() {
		model.setPageTitle(this.getText("PageNotFound.title"));
		return SUCCESS;
	}

	public String viewForBidden() {
		model.setPageTitle(this.getText("ForBidden.title"));
		return SUCCESS;
	}

	public String viewReleaseNoteList() {
		// SEO
		model.setPageTitle(this.getText("releaseNote.page.title"));
		model.setPageDescription(this.getText("releaseNote.page.desc"));
		model.setPageKeywords(this.getText("releaseNote.page.keys"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.help.releaseNote2"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.help"));
		breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public String pingPortalAjax() {
		final String location = "pingPortalAjax()";
		if (logger.isDebugEnabled())
			logger.debug(location + ":BEGIN");
		// if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
		// try {
		// ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		// } catch (IllegalStateException e) {
		// logger.error("invalid session object", e);
		// }
		// }
		try {
			final HttpSession session = ServletActionContext.getRequest().getSession();

			model.setIsActiveSession(session == null ? false : true);
		} catch (Exception e) {
			logger.error("invalid session", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(location + ":END");
		return SUCCESS;
	}

	public String viewSessionTimeout() {
		model.setPageTitle(this.getText("SessionTimePage.pageTitle"));
		
		SessionManager.clearSession(ServletActionContext.getRequest());
		
		return SUCCESS;
	}
	
	public String viewDemoCPTD() {
		return SUCCESS;
	}

	private void doSEOpage() {
		if (StringUtils.isNotEmpty(model.getPageTitle())) {
			model.setPageTitle(this.getText(model.getPageTitle()));
		}
		if (StringUtils.isNotEmpty(model.getPageDescription())) {
			model.setPageDescription(this.getText(model.getPageDescription()));
		}
		if (StringUtils.isNotEmpty(model.getPageKeywords())) {
			model.setPageKeywords(this.getText(model.getPageKeywords()));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String urlParameter) {
		this.url = urlParameter;
	}
}
