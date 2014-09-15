package vn.com.vndirect.web.struts2.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoSectorCodeId;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchSymbol;
import vn.com.vndirect.domain.struts2.common.SymbolProcessingModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class SymbolProcessingAction extends ActionSupport implements ModelDriven<SymbolProcessingModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437465311708237934L;

	private static Logger logger = Logger.getLogger(SymbolProcessingAction.class);

	interface SearchPrefix {
		String FOR_COMPANY_NAME = "~";
		String FOR_INDYSTRY = "^";
	}

	private SymbolProcessingModel model = new SymbolProcessingModel();

	@Autowired
	private IQuotesManager quotesManager;

	public SymbolProcessingModel getModel() {
		return model;
	}

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public String executeQuickSearch() {
		final String LOCATION = "executeQuickSearch()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			String currentSymbol = model.getSymbol();
			currentSymbol = currentSymbol == null ? SessionManager.getSymbolCompany() : currentSymbol;
			// default is VNIndex symbol.
			currentSymbol = currentSymbol == null ? VNDirectWebUtilities.getHOSTCIndex() : currentSymbol;
			CurrentCompanyForQuote currentCompanyForQuote = quotesManager.quickSearchQuotes(currentSymbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentCompanyForQuote != null) {
				// logger.debug(LOCATION + ":: currentCompanyForQuote:" + currentCompanyForQuote);
				SessionManager.setSymbolCompany(currentCompanyForQuote.getSymbol());
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

	public String executeInitSearchSymbol() {
		final String LOCATION = "executeInitSearchSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		// do SEO on-page
		model.setPageTitle(this.getText("analysis.stockInfo.company.searchSymbol.title"));
		model.setPageDescription(this.getText("analysis.stockInfo.company.searchSymbol.desc"));
		model.setPageKeywords(this.getText("analysis.stockInfo.company.searchSymbol.keywords"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.stockInfo.company.searchSymbol.br"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
		breadcrumbs.add("tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		try {
			// populate data for combobox
			model.setListIfoExchange(quotesManager.getAllIfoExchange());

			IfoSectorCodeId searchObj = new IfoSectorCodeId();
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			model.setListSectorCode(quotesManager.getAllIfoSectorCode(searchObj));
			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

	public String executeSearchSymbol() {
		final String LOCATION = "executeSearchSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			SearchSymbol searchObj = model.getSearchSymbol();

			if (logger.isDebugEnabled()) {
				logger.debug("searchObj.getCompanyName():" + searchObj.getCompanyName());
				logger.debug("searchObj.getExchangeCode():" + searchObj.getFirstExchangeCode());
			}
			String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			searchObj.setLocale(locale);

			SearchResult<IfoCompanyNameView> result = quotesManager.searchSymbol(searchObj, model.getPagingInfo());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + "::END -- result:" + result);

			for (int i = 0; i < result.size(); i++) {
				logger.debug(i + " -- " + ((IfoCompanyNameView) result.get(i)).getId().getSymbol());
			}

			// model.setSearchResult(result);
			model.setResult(result);
			model.setPagingInfo((PagingInfo)result.getPaging());
			model.setLang(locale);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

	public String executeCheckExistedSymbol() {
		final String LOCATION = "executeCheckExistedSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			String currentSymbol = SessionManager.getSymbolCompany();
			model.setSymbol(currentSymbol);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
			return INPUT;
		}
	}

}
