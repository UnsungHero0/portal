package vn.com.vndirect.web.struts2.listedmarket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.FinanceReportForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.struts2.listedmarket.CashFlowModel;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({"unchecked", "serial"})
public class CashFlowAJAXAction extends ActionSupport implements ModelDriven<CashFlowModel> {

	private static Logger logger = Logger.getLogger(CashFlowAJAXAction.class);

	private CashFlowModel model = new CashFlowModel();

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
	public CashFlowModel getModel() {
		return model;
	}

	/**
	 * @param listedMarketManager
	 *            the listedMarketManager to set
	 */
	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	public String executeSearchCashFlow() throws Exception {
		final String LOCATION = "executeSearchCashFlow()";
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
			if (!"IN_YEAR".equals(model.getSearchObject().getFiscalQuarter())) {
				reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.QUARTER);
			} else {
				reportType = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.ANNUAL);
				searchObject.setAnnual(true);
			}

			searchObject.setCompanyId(currentComp.getCompanyId());
			searchObject.setLocale(I18NUtility.getCurrentLocale(request.getSession()));
			searchObject.setReportType(reportType);

			// get CashflowView info
			List ifoCashflowList = listedMarketManager.getCashflowViewInfo(searchObject);

			model.setFinanceInfoList(ifoCashflowList);

			FinanceReportForQuote financeInfoFirst;
			if (ifoCashflowList.size() > 0) {
				financeInfoFirst = (FinanceReportForQuote) ifoCashflowList.get(0);
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
