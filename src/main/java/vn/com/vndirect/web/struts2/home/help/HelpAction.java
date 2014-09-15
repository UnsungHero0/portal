package vn.com.vndirect.web.struts2.home.help;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.domain.struts2.home.help.HelpModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HelpAction extends ActionSupport implements ModelDriven<HelpModel> {

	private static Logger LOGGER = Logger.getLogger(HelpAction.class);

	private HelpModel model = new HelpModel();

	@Autowired
	private IOnlineServiceManager onlineServiceManager;

	public HelpModel getModel() {
		return model;
	}

	@Override
	public String execute() throws Exception {
		final String LOCATION = "executeHelpAction()";
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(LOCATION + "::BEGIN");
		}
		try {
			WpSubject subject = new WpSubject();
			if (StringUtils.isNotEmpty(model.getSubjectCode())) {
				subject.setSubjectCode(model.getSubjectCode());
				subject.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				subject = onlineServiceManager.getSubject(subject);
			}

			WpProduct product = new WpProduct();
			if (StringUtils.isNotEmpty(model.getProductCode())) {
				product.setProductCode(model.getProductCode());
				product.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				product = onlineServiceManager.getProductByCode(product);
			}

			model.setWpProduct(product);
			model.setWpSubject(subject);

			// SEO
			model.setPageTitle(subject.getSubjectName());
			model.setPageDescription(subject.getSubjectName());
			model.setPageKeywords(model.getProductCode() + "," + model.getSubjectCode());

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(subject.getSubjectName());
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.help"));
			breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
			breadcrumbs.add(this.getText("br.help.guide"));
			breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
			model.setBreadcrumbs(breadcrumbs);
		} catch (Exception e) {
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	public void setOnlineServiceManager(IOnlineServiceManager onlineServiceManager) {
		this.onlineServiceManager = onlineServiceManager;
	}
}
