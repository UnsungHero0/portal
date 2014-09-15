/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.AnalysisIndexOptions;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.StockWizardBean;
import vn.com.vndirect.domain.struts2.analysistools.StockWizardModel;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Huy
 * 
 */
@SuppressWarnings("serial")
public class StockWizardAction extends ActionSupport implements ModelDriven<StockWizardModel>, Preparable {

	private Logger logger = Logger.getLogger(getClass());
	private StockWizardModel model = new StockWizardModel();
	
	private String language;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;
	
	@Autowired
	private IQuotesManager quotesManager;
	

	/**
	 * @param analysisToolsManager
	 *            the analysisToolsManager to set
	 */
	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockWizard"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		this.analysisToolsManager = analysisToolsManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public StockWizardModel getModel() {
		return model;
	}

	/**
	 * To display a detail of a company
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCompanyDetail() throws Exception {
		final String LOCATION = "getCompanyDetail()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setSymbol(VNDirectWebUtilities.cleanString(model.getSymbol()).toUpperCase());
			String[] symbols = new String[] { model.getSymbol() };
			StockWizardBean[] stockWizardBean = analysisToolsManager.getStockWizardBean(symbols, language, false);
			if ((stockWizardBean == null) || (stockWizardBean.length == 0)) {
				return INPUT;
			}

			IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();
			intradayPriceSearch.setListSymbols(symbols);

			IntradayPriceSearchResult intradayPriceSearchResult = quotesManager.searchIntradayPrice(
			        SessionManager.OnlineUsers.getVNDSAuthenticationHeader(), intradayPriceSearch);
			String intraday = "";
			if (intradayPriceSearchResult != null && intradayPriceSearchResult.getListSecInfo() != null
			        && intradayPriceSearchResult.getListSecInfo().length > 0) {
				intraday = VNDirectWebUtilities.getStrDoubleWithScale2(intradayPriceSearchResult.getListSecInfo()[0]
				        .getCurrentPrice());
			}

			stockWizardBean[0].getAnalysisIndexingBean().setIntraday(intraday);
			Map<String, AnalysisCachingValueInfo> mapValues = stockWizardBean[0].getMapValues();
			model.setAnalysisIndexingBean(stockWizardBean[0].getAnalysisIndexingBean());
			model.setIfoCompanyProfileViewId(stockWizardBean[0].getIfoCompanyProfileView());
			try {
				model.setF1000030(mapValues.get("1000030").getNumericValue());
				model.setF1000031(mapValues.get("1000031").getNumericValue());
				model.setF1000033(mapValues.get("1000033").getNumericValue());
			} catch (Exception e) {
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockWizard"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpageSymbol(model.getSymbol());

		return SUCCESS;
	}

	/**
	 * To compare between some companies
	 * 
	 * @return
	 * @throws Exception
	 */
	public String compare() throws Exception {
		final String LOCATION = "compare()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			String symbol = VNDirectWebUtilities.cleanString(model.getSymbol()).toUpperCase();
			model.setSymbol(symbol);
			String symbol1 = VNDirectWebUtilities.cleanString(model.getSymbol1()).toUpperCase();
			model.setSymbol1(symbol1);
			String symbol2 = VNDirectWebUtilities.cleanString(model.getSymbol2()).toUpperCase();
			model.setSymbol2(symbol2);

			if (!StringUtils.isEmpty(symbol)) {
				StockWizardBean[] stockWizardBean = analysisToolsManager.getStockWizardBean(new String[] { symbol }, language,
				        true);
				if (!ArrayUtils.isEmpty(stockWizardBean)) {
					model.setAnalysisIndexingBean(stockWizardBean[0].getAnalysisIndexingBean());
					model.setIfoCompanyProfileViewId(stockWizardBean[0].getIfoCompanyProfileView());
				}
			} else {
				return INPUT;
			}

			if (!StringUtils.isEmpty(symbol1)) {
				StockWizardBean[] stockWizardBean1 = analysisToolsManager.getStockWizardBean(new String[] { symbol1 }, language,
				        true);
				if (!ArrayUtils.isEmpty(stockWizardBean1)) {
					model.setAnalysisIndexingBean1(stockWizardBean1[0].getAnalysisIndexingBean());
					model.setIfoCompanyProfileViewId1(stockWizardBean1[0].getIfoCompanyProfileView());
				}
			}

			if (!StringUtils.isEmpty(symbol2)) {
				StockWizardBean[] stockWizardBean2 = analysisToolsManager.getStockWizardBean(new String[] { symbol2 }, language,
				        true);
				if (!ArrayUtils.isEmpty(stockWizardBean2)) {
					model.setAnalysisIndexingBean2(stockWizardBean2[0].getAnalysisIndexingBean());
					model.setIfoCompanyProfileViewId2(stockWizardBean2[0].getIfoCompanyProfileView());
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockWizard"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpageSymbol(model.getSymbol());

		return SUCCESS;
	}

	public String getAnalysisValue(AnalysisIndexingBean bean, String itemName) {
		return new AnalysisIndexOptions(bean, true, itemName, false, false).getFormattedData();
	}

	public void prepare() throws Exception {
		language = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.stockWizard.title"));
		model.setPageDescription(this.getText("analysis.stockWizard.desc"));
		model.setPageKeywords(this.getText("analysis.stockWizard.keywords"));
	}

	private void doSEOpageSymbol(String Symbol) {
		model.setPageTitle(this.getText("analysis.stockWizardCompany.title") + " " + Symbol);
		model.setPageDescription(this.getText("analysis.stockWizardCompany.des") + " " + Symbol);
		model.setPageKeywords(this.getText("analysis.stockWizardCompany.keywords") + " " + Symbol);
	}
}
