package vn.com.vndirect.web.struts2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.MarketCodeUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.embeddb.SearchStockExchange;
import vn.com.vndirect.domain.embeddb.StockExchange;
import vn.com.vndirect.domain.struts2.common.SymbolAutoSugesstAJAXModel;
import vn.com.vndirect.embeddb.EmbeddedDBManager;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SymbolAutoSuggestAJAXAction extends ActionSupport implements ModelDriven<SymbolAutoSugesstAJAXModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8868798680477500707L;

	private static Logger logger = Logger.getLogger(SymbolAutoSuggestAJAXAction.class);

	interface SearchPrefix {
		String FOR_COMPANY_NAME = "~";
		String FOR_INDYSTRY = "^";
	}

	private SymbolAutoSugesstAJAXModel model = new SymbolAutoSugesstAJAXModel();

	@Autowired
	private EmbeddedDBManager embeddedDBManager;

	public SymbolAutoSugesstAJAXModel getModel() {
		return model;
	}

	/**
	 * @param localDBManager
	 *            the localDBManager to set
	 */
	public void setEmbeddedDBManager(EmbeddedDBManager embeddedDBManager) {
		this.embeddedDBManager = embeddedDBManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			model.setStockExchanges(this.searchStockExchange());
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
	 * @throws Exception
	 */
	private SearchResult<StockExchange> searchStockExchange() throws Exception {
		final String LOCATION = "searchStockExchange()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");
		SearchStockExchange searchStockExchange = new SearchStockExchange();

		String locale = VNDirectWebUtilities.getCurrentLocaleCode();
		model.setLocale(locale);

		// +++ get text
		String txt = model.getText();
		txt = (txt == null ? "" : txt.trim());
		txt += txt.endsWith("%") ? "" : "%";

		if (txt.startsWith(SearchPrefix.FOR_COMPANY_NAME)) {
			searchStockExchange.setCompanyNames(new String[] { txt.substring(1) });
			searchStockExchange.setLocale(locale);
		} else {
			searchStockExchange.setSymbols(new String[] { txt });
		}

		// +++ get exchange code
		String markets = model.getMarkets();
		if (markets != null && markets.length() > 0) {
			StringTokenizer tokenMarkets = new StringTokenizer(markets, "|;, ");
			if (tokenMarkets.countTokens() > 0) {
				final String HASTC = MarketCodeUtils.getHASTCExchange();
				final String HOSTC = MarketCodeUtils.getHOSTCExchange();
				final String VN30 = MarketCodeUtils.getVN30Exchange();
				final String HNX30 = MarketCodeUtils.getHNX30Exchange();
				final String OTC = MarketCodeUtils.getOTCExchange();
				final String UpCOM = MarketCodeUtils.getUPCOMExchange();

				String str;
				List<String> exchangeCodes = new ArrayList<String>();

				while (tokenMarkets.hasMoreElements()) {
					str = (String) tokenMarkets.nextElement();
					if (Constants.ShortExchangeCode.HA.equalsIgnoreCase(str) || HASTC.equalsIgnoreCase(str)) {
						exchangeCodes.add(HASTC);
					} else if (Constants.ShortExchangeCode.HO.equalsIgnoreCase(str) || HOSTC.equalsIgnoreCase(str)) {
						exchangeCodes.add(HOSTC);
					} else if (Constants.ShortExchangeCode.VN30.equalsIgnoreCase(str) || VN30.equalsIgnoreCase(str)) {
						exchangeCodes.add(VN30);
					} else if (Constants.ShortExchangeCode.HNX30.equalsIgnoreCase(str) || HNX30.equalsIgnoreCase(str)) {
						exchangeCodes.add(HNX30);
					} else if (Constants.ShortExchangeCode.OTC.equalsIgnoreCase(str) || OTC.equalsIgnoreCase(str)) {
						exchangeCodes.add(OTC);
					} else if (Constants.ShortExchangeCode.UPCOM.equalsIgnoreCase(str) || UpCOM.equalsIgnoreCase(str)) {
						exchangeCodes.add(UpCOM);
					}
				}
				searchStockExchange.setExchangeCodes(exchangeCodes.toArray(new String[exchangeCodes.size()]));
			}
		}

		// +++ get ExclusedSymbols
		String exclusedSymbols = model.getExclusedSymbols();
		if (exclusedSymbols != null && exclusedSymbols.length() > 0) {
			StringTokenizer tokenExclusedSymbols = new StringTokenizer(exclusedSymbols, "|;, ");
			if (tokenExclusedSymbols.countTokens() > 0) {
				List<String> listExclusedSymbols = new ArrayList<String>();
				while (tokenExclusedSymbols.hasMoreElements()) {
					listExclusedSymbols.add((String) tokenExclusedSymbols.nextElement());
				}
				searchStockExchange.setIgnoreSymbols(listExclusedSymbols.toArray(new String[listExclusedSymbols.size()]));
			}
		}

		PagingInfo pagingInfo = new PagingInfo();
		pagingInfo.setOffset(model.getItems() < 3 ? Constants.Paging.NUMBER_ITEMS : model.getItems());

		SearchResult<StockExchange> result = embeddedDBManager.searchStockExchange(searchStockExchange, pagingInfo);

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::END");
		return result;
	}
}
