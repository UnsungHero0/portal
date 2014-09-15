package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
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
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.listedmarket.KeyStatisticModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class KeyStatisticAction extends ActionSupport implements ModelDriven<KeyStatisticModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3957211063283526928L;

	private static Logger logger = Logger.getLogger(KeyStatisticAction.class);

	private KeyStatisticModel model = new KeyStatisticModel();

	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @return the model
	 */
	public KeyStatisticModel getModel() {
		return model;
	}

	/**
	 * @return the quotesManager
	 */
	public IQuotesManager getQuotesManager() {
		return quotesManager;
	}

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public String viewIRKeyStatic() throws Exception {
		final String LOCATION = "viewIRKeyStatic()";
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
			currentComp = quotesManager.quickSearchQuotes(symbolCompany, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentComp == null) {
				return ERROR;
			}
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			
			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.financials.basicStatistic.title"));
			model.setPageDescription(this.getText("home.ir.financials.basicStatistic.desc"));
			model.setPageKeywords(this.getText("home.ir.financials.basicStatistic.key"));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("br.ir.finance.basic"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("br.ir.finance"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			IfoCompanyNameViewId ifoCompanyNameViewId = model.getIfoCompanyNameViewId();
			if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
				ifoCompanyNameViewId = new IfoCompanyNameViewId(currentComp.getCompanyId());
			}

			String usLocale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			ifoCompanyNameViewId.setLocale(usLocale);

			Map keyStaticMap = quotesManager.getKeyStatic(ifoCompanyNameViewId);
			model.setIfoValuationMeasuresViewList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.VALUATION_MEASURES));
			model.setIfoInvestorRightsViewList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.TRADING_INFORMATION));
			model.setKeyRatioList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.KEY_RATIO));
			model.setFinancialHighlightList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.FINANCIAL_HIGHLIGHT));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String viewSIKeyStatic() throws Exception {
		final String LOCATION = "viewSIKeyStatic()";
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
			breadcrumbs.add(this.getText("analysis.stockInfo.company.keyStatistics"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.company"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			IfoCompanyNameViewId ifoCompanyNameViewId = model.getIfoCompanyNameViewId();
			if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
				ifoCompanyNameViewId = new IfoCompanyNameViewId(currentComp.getCompanyId());
			}

			String usLocale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			ifoCompanyNameViewId.setLocale(usLocale);

			Map keyStaticMap = quotesManager.getKeyStatic(ifoCompanyNameViewId);
			model.setIfoValuationMeasuresViewList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.VALUATION_MEASURES));
			model.setIfoInvestorRightsViewList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.TRADING_INFORMATION));
			model.setKeyRatioList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.KEY_RATIO));
			model.setFinancialHighlightList((List) keyStaticMap.get(Constants.KEY_STATIC_ITEMS.FINANCIAL_HIGHLIGHT));
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
