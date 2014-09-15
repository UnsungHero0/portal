package vn.com.vndirect.web.struts2.analysistools;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.struts2.analysistools.FlashChartModel;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FlashChartAction extends ActionSupport implements ModelDriven<FlashChartModel> {
	private static final long serialVersionUID = -4879174341553776882L;

	private static Logger logger = Logger.getLogger(FlashChartAction.class);

	private FlashChartModel model = new FlashChartModel();

	@Autowired
	private IQuotesManager quotesManager;

	public FlashChartModel getModel() {
		return model;
	}

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * 
	 * @return
	 */
	public String showInitPage() {
		final String LOCATION = "showInitPage()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			doSEOpage();

			String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			CurrentCompanyForQuote companyForQuote = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (companyForQuote == null) {
				companyForQuote = quotesManager.quickSearchQuotes(VNDirectWebUtilities.getHOSTCIndex(), locale);
			}

			String symbol = SessionManager.getSymbolCompany();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + "::currentSymbol: " + symbol);

			model.setSymbol(symbol);

			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

	/**
	 * Init page for external flash chart.
	 */
	public String showInitExternalPage() {
		final String LOCATION = "showInitExternalPage()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String selectSymbol = request.getParameter("symbol") == null ? "" : request.getParameter("symbol").toUpperCase()
			        .trim();

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + "::selectSymbol: " + selectSymbol);

			String currentLocale = I18NUtility.getCurrentLocale(request.getSession());
			CurrentCompanyForQuote selectCompanyForQuote = quotesManager.quickSearchQuotes(selectSymbol, currentLocale);
			// default is VNIndex symbol.
			if (selectCompanyForQuote == null) {
				selectCompanyForQuote = quotesManager.quickSearchQuotes(VNDirectWebUtilities.getHOSTCIndex(), currentLocale);
			}

			if (selectCompanyForQuote != null) {
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: selectCompanyForQuote:" + selectCompanyForQuote);
				SessionManager.setSymbolCompany(selectCompanyForQuote.getSymbol());

				// ++++
				model.setSymbol(selectCompanyForQuote.getSymbol());
			}

			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String showInitPageForSymbol() {
		final String LOCATION = "showInitPageForSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			// do SEO on-page
			model.setPageTitle(this.getText("analysis.stockInfo.title"));
			model.setPageDescription(this.getText("analysis.stockInfo.desc"));
			model.setPageKeywords(this.getText("analysis.stockInfo.keywords"));

			// Start Company Snapshot Infomations
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			HttpServletRequest request = ServletActionContext.getRequest();
			String locale = I18NUtility.getCurrentLanguage(request.getSession()).getCode();

			IfoCompanySnapshotViewExt ifoComSnapshotViewExt = null;
			if (currentComp != null) {
				// set company
				model.setCurrentCompanyForQuote(currentComp);
				// set industry
				IfoCompanyIndustryView industryObj = currentComp.getIfoCompanyIndustryView(locale);
				if (industryObj != null && industryObj.getId() != null) {
					model.setIfoCompanyIndustryViewId(industryObj.getId());
				}
				// get CompanySnapshotView info
				currentComp.setLocale(I18NUtility.getCurrentLocale(request.getSession()));
				ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(currentComp);
				ifoComSnapshotViewExt = ifoComSnapshotViewExt == null ? new IfoCompanySnapshotViewExt() : ifoComSnapshotViewExt;

				model.setSymbol(currentComp.getSymbol());
			}

			// format company overview --> replace \n by <br>
			if (ifoComSnapshotViewExt != null && ifoComSnapshotViewExt.getId() != null) {
				String compOverview = ifoComSnapshotViewExt.getId().getCompanyOverview();
				compOverview = VNDirectWebUtilities.replaceString(compOverview, "\n", "<br/>");
				ifoComSnapshotViewExt.getId().setCompanyOverview(compOverview);
			}

			model.setIfoComSnapshotViewExt(ifoComSnapshotViewExt);

			// get intra day from Quotes Service
			if (currentComp != null && ifoComSnapshotViewExt != null && ifoComSnapshotViewExt.getId() != null) {
				vn.com.vndirect.wsclient.AuthenticationHeader authQuotes = SessionManager.OnlineUsers
				        .getVNDSAuthenticationHeader();
				IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();

				intradayPriceSearch.setListSymbols(new String[] { currentComp.getSymbol() });
				IntradayPriceSearchResult intradayPriceSearchResult = null;
				try {
					intradayPriceSearchResult = quotesManager.searchIntradayPrice(authQuotes, intradayPriceSearch);
				} catch (Exception e) {
					logger.error(LOCATION + "::intradayPriceSearchResult: " + e);
				}
				if (intradayPriceSearchResult != null && intradayPriceSearchResult.getListSecInfo() != null
				        && intradayPriceSearchResult.getListSecInfo().length > 0) {
					model.setSecInfo(intradayPriceSearchResult.getListSecInfo()[0]);
				}
				if (intradayPriceSearchResult != null && intradayPriceSearchResult.getListMarketInfo() != null
				        && intradayPriceSearchResult.getListMarketInfo().length > 0) {
					model.setMarketInfo(intradayPriceSearchResult.getListMarketInfo()[0]);
				}
			}
			// add company name
			if (currentComp != null) {
				if ("vn".equalsIgnoreCase(currentComp.getLocale())) {
					model.setTitle(currentComp.getCompanyName());
				} else {
					model.setTitle(currentComp.getCompanyFullName());
				}

				// Company Infomations
				model.setShowHoHaInfo(VNDirectWebUtilities.getHASTCIndex().equalsIgnoreCase(currentComp.getSymbol())
				        || VNDirectWebUtilities.getHOSTCIndex().equalsIgnoreCase(currentComp.getSymbol())
				        || VNDirectWebUtilities.getVN30Index().equalsIgnoreCase(currentComp.getSymbol())
				        || VNDirectWebUtilities.getHNX30Index().equalsIgnoreCase(currentComp.getSymbol()));
				// End Company Snapshot Infomations
			}

			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return SUCCESS;
		}
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.stockWizard.title"));
		model.setPageDescription(this.getText("analysis.stockWizard.desc"));
		model.setPageKeywords(this.getText("analysis.stockWizard.keywords"));
	}
}
