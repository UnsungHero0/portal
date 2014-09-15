package vn.com.vndirect.web.struts2.listedmarket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.FinanceReportForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.struts2.listedmarket.IncomeStatementModel;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class IncomeStatementAJAXAction extends ActionSupport implements ModelDriven<IncomeStatementModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353545398162496209L;

	private static Logger logger = Logger.getLogger(IncomeStatementAJAXAction.class);

	private IncomeStatementModel model = new IncomeStatementModel();

	@Autowired
	private IListedMarketManager listedMarketManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @return the model
	 */
	public IncomeStatementModel getModel() {
		return model;
	}

	/**
	 * @return the listedMarketManager
	 */
	public IListedMarketManager getListedMarketManager() {
		return listedMarketManager;
	}

	/**
	 * @param listedMarketManager
	 *            the listedMarketManager to set
	 */
	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	public String executeSearchIncomeStatement() throws Exception {
		final String LOCATION = "executeIncomeStatement()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			IfoBalanceSheetSearch searchObject = new IfoBalanceSheetSearch();
			searchObject = model.getSearchObject();

			String reportType;
			if ("IN_YEAR".equals(model.getSearchObject().getFiscalQuarter())) {
				reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.ANNUAL);
				searchObject.setAnnual(true);
			} else {
				reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.QUARTER);
			}

			searchObject.setCompanyId(currentComp.getCompanyId());
			searchObject.setLocale(I18NUtility.getCurrentLocale(request.getSession()));
			searchObject.setReportType(reportType);

			// List all data for view page
			List ifoIncomeList = listedMarketManager.getIncomeViewInfo(searchObject);

			model.setFinanceInfoList(ifoIncomeList);

			FinanceReportForQuote financeInfoFirst;
			if (ifoIncomeList.size() > 0) {
				financeInfoFirst = (FinanceReportForQuote) ifoIncomeList.get(0);
			} else {
				financeInfoFirst = new FinanceReportForQuote();
			}
			model.setFinanceInfoFirst(financeInfoFirst);
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
