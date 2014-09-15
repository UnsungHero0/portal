package vn.com.vndirect.web.struts2.home.history;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.domain.struts2.home.history.HistoryModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HistoryAction extends ActionSupport implements ModelDriven<HistoryModel> {

	private static Logger LOGGER = Logger.getLogger(HistoryAction.class);

	private HistoryModel model = new HistoryModel();

	@Autowired
	private IOnlineServiceManager onlineServiceManager;

	public HistoryModel getModel() {
		return model;
	}

	public String execute() throws Exception {
		final String LOCATION = "execute()";
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(LOCATION + "::BEGIN");
		}
		try {
			// do SEO
			model.setPageTitle(this.getText(model.getPageTitle()));
			model.setPageDescription(this.getText(model.getPageDescription()));
			model.setPageKeywords(this.getText(model.getPageKeywords()));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("br.vnd.history"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			model.setBreadcrumbs(breadcrumbs);

			WpSubject subject = new WpSubject();
			if (StringUtils.isNotEmpty(model.getSubjectCode())) {
				subject.setSubjectCode(model.getSubjectCode());
				subject.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				subject = onlineServiceManager.getSubject(subject);
			}

			model.setWpSubject(subject);
		} catch (Exception e) {
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	public String getHistory() throws Exception {
		final String LOCATION = "getHistory()";
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

			model.setWpSubject(subject);
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
