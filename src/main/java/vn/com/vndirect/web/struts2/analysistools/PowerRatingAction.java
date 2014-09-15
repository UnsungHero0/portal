package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.analysistools.PowerRatingModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class PowerRatingAction extends ActionSupport implements ModelDriven<PowerRatingModel> {

	private Logger logger = Logger.getLogger(getClass());
	private PowerRatingModel model = new PowerRatingModel();
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public PowerRatingModel getModel() {
		return model;
	}

	public String execute() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.PowerRating"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
	}

	public String viewPowerRatingBreakdown() {
		final String LOCATION = "viewPowerRatingBreakdown()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public String searchPrSymbol() {
		final String LOCATION = "searchPrSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
			String currentSymbol = model.getSymbol();
			CurrentCompanyForQuote currentCompanyForQuote = quotesManager.quickSearchQuotes(currentSymbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentCompanyForQuote == null) {
				model.setExistSymbol("N");
			} else
				model.setExistSymbol("Y");
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public String viewPowerRatingLevel() {
		final String LOCATION = "viewPowerRatingLevel()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
			model.setLevelPrs();
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public String viewCompanyPowerRating() {
		final String LOCATION = "viewCompanyPowerRating()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			model.setCurrentComp(currentComp);
			model.initCurrentCompanyPr();
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public String viewPowerRatingWatchList() {
		final String LOCATION = "viewPowerRatingWatchList()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
			model.setWatchListPrs();
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.powerRating.title"));
		model.setPageDescription(this.getText("analysis.powerRating.des"));
		model.setPageKeywords(this.getText("analysis.powerRating.keywords"));
	}
}
