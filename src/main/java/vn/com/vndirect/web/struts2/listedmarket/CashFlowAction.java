package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoDataRef;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.struts2.listedmarket.CashFlowModel;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class CashFlowAction extends ActionSupport implements ModelDriven<CashFlowModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4038055609617910581L;

	private static Logger logger = Logger.getLogger(CashFlowAction.class);
	// Define Cache Key
	public static final String MONEY_RATE_COLLECTION = "$MONEY_RATE_COLLECTION$";
	public static final String LIST_QUARTER_KEY = "$LIST_QUARTER_KEY$";
	public static final String LIST_YEAR_KEY = "$LIST_YEAR_KEY$";

	private CashFlowModel model = new CashFlowModel();

	@Autowired
	private IListedMarketManager listedMarketManager;

	@Autowired
	private ICommonManager commonManager;
	
	@Autowired
	private IQuotesManager quotesManager;

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

	/**
	 * @param commonManager
	 *            the commonManager to set
	 */
	public void setCommonManager(ICommonManager commonManager) {
		this.commonManager = commonManager;
	}

	public String viewCashFlow() throws Exception {
		final String LOCATION = "viewCashFlow()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// get company info from session
			CurrentCompanyForQuote currentComp;
			String symbolCompany = model.getSymbol();
			if (StringUtils.isEmpty(symbolCompany)) {
				symbolCompany = SessionManager.getSymbolCompany();
			}
			if(symbolCompany == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbolCompany, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
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
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.Financial.Button.CashFlow"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.financialStatements"));
			breadcrumbs.add("bang-can-doi-ke-toan/" + currentComp.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			// get BalancesheetView info
			IfoBalanceSheetSearch searchObject = model.getSearchObject();
			searchObject.setCompanyId(currentComp.getCompanyId());
			searchObject.setLocale(I18NUtility.getCurrentLocale(request.getSession()));

			// Start Init data and added to cache
			Collection moneyRateCol = new ArrayList();

			String strMoney = ServerConfig.getOnlineValue(Constants.IServerConfig.MONEY_RATE);
			strMoney = strMoney == null ? "" : strMoney.trim();
			StringTokenizer strToken = new StringTokenizer(strMoney, ";");
			while (strToken.hasMoreTokens()) {
				moneyRateCol.add(strToken.nextToken());
			}
			model.addToCache(MONEY_RATE_COLLECTION, moneyRateCol);

			moneyRateCol = moneyRateCol == null ? new ArrayList() : moneyRateCol;
			model.setMoneyRateCol(moneyRateCol);

			List listQuarter = (List) model.getFromCache(LIST_QUARTER_KEY);
			if (listQuarter == null) {
				IfoDataRef ifoDataRef = new IfoDataRef();
				ifoDataRef.setLocale(I18NUtility.getCurrentLocale(request.getSession()));
				ifoDataRef.setGroupCode(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.GroupCodes.QUARTER_TYPE));
				listQuarter = this.commonManager.getDataRefByGroupCode(ifoDataRef);

				listQuarter = (listQuarter == null ? new ArrayList() : listQuarter);
			}
			model.addToCache(LIST_QUARTER_KEY, listQuarter);
			model.setQuarterList(listQuarter);

			List listYear = (List) model.getFromCache(LIST_YEAR_KEY);
			if (listYear == null) {
				listYear = listedMarketManager.fiscalYearListCashFlow(searchObject);
			}
			if (listYear.size() > 0) {
				model.getSearchObject().setFiscalYear((String) listYear.get(0));
			}
			model.addToCache(LIST_YEAR_KEY, listYear);
			model.setYearList(listYear);

			if (searchObject.getFiscalQuarter() == null || searchObject.getFiscalQuarter().trim().length() == 0) {
				model.getSearchObject().setFiscalQuarter("IN_YEAR");
				searchObject.setAnnual(true);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}
}
