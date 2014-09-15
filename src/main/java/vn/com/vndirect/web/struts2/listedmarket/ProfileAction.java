package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompany;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.domain.IfoSecIndexViewId;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.listedmarket.ProfileModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class ProfileAction extends ActionSupport implements ModelDriven<ProfileModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057232217861923629L;

	private static Logger logger = Logger.getLogger(ProfileAction.class);

	private ProfileModel model = new ProfileModel();

	@Autowired
	private IQuotesManager quotesManager;

	public IQuotesManager getQuotesManager() {
		return quotesManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public ProfileModel getModel() {
		return model;
	}

	public String viewCompanyProfile() throws Exception {
		final String LOCATION = "viewCompanyProfile()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			CurrentCompanyForQuote currentComp;
			String symbolCompany = model.getSymbol();
			if (StringUtils.isEmpty(symbolCompany)) {
				symbolCompany = SessionManager.getSymbolCompany();
			}
			if(symbolCompany == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbolCompany,
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentComp == null) {
				return ERROR;
			}
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			String title = "VNDIRECT-" + this.getText("analysis.stockInfo.company.snapshot") + " | ";
			if (I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()).equalsIgnoreCase("VN")) {
				title += currentComp.getCompanyName();
			} else {
				title += currentComp.getCompanyFullName();
			}
			title += "-" + currentComp.getSymbol();
			model.setPageTitle(title);
			model.setPageDescription(this.getText("analysis.stockInfo.desc") + currentComp.getSymbol());
			String[] keywords = this.getText("analysis.stockInfo.keywords").trim().split(",");
			String dynamicKeyword = "";
			for (int i = 0; i < keywords.length; i++) {
				dynamicKeyword += keywords[i] + " " + currentComp.getSymbol();
				if (i != keywords.length - 1) {
					dynamicKeyword += ",";
				}
			}
			model.setPageKeywords(dynamicKeyword);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("analysis.stockInfo.company.profile"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.company"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			IfoCompany ifoCompany = model.getIfoCompany();

			if (ifoCompany == null || ifoCompany.getCompanyId() == null) {
				ifoCompany = new IfoCompany(currentComp.getCompanyId(), "", new Date());
			}

			model.setCompanyName(currentComp.getCompanyName());
			model.setSymbol(currentComp.getSymbol());

			// get user profile
			String usLocale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			ifoCompany.setLocale(usLocale);

			Map companyProfile = quotesManager.getCompanyProfile(ifoCompany);
			model.setIfoCompanyNameViewId((IfoCompanyNameViewId) companyProfile.get(Constants.COMPANY_PROFILE_ITEMS.COMPANY_NAME_VIEW));
			model.setOfficersViewIdList((List) companyProfile.get(Constants.COMPANY_PROFILE_ITEMS.COMPANY_OFFICE_VIEW));

			IfoCompanyProfileViewId ifoCompanyProfileViewId = (IfoCompanyProfileViewId) companyProfile.get(Constants.COMPANY_PROFILE_ITEMS.COMPANY_PROFILE_VIEW);
			if (ifoCompanyProfileViewId != null) {
				String str;

				str = ifoCompanyProfileViewId.getMainBusiness();
				ifoCompanyProfileViewId.setMainBusiness(VNDirectWebUtilities.replaceString(str, "\n", "<br/>"));

				str = ifoCompanyProfileViewId.getHistory();
				ifoCompanyProfileViewId.setHistory(VNDirectWebUtilities.replaceString(str, "\n", "<br/>"));

				str = ifoCompanyProfileViewId.getMarketPosition();
				ifoCompanyProfileViewId.setMarketPosition(VNDirectWebUtilities.replaceString(str, "\n", "<br/>"));

				str = ifoCompanyProfileViewId.getPlan();
				ifoCompanyProfileViewId.setPlan(VNDirectWebUtilities.replaceString(str, "\n", "<br/>"));
			}
			model.setIfoCompanyProfileViewId(ifoCompanyProfileViewId);

			model.setIfoSecIndexViewId((IfoSecIndexViewId) companyProfile.get(Constants.COMPANY_PROFILE_ITEMS.SEC_INDEX));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}
}
