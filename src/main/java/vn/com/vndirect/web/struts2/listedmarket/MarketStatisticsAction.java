package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoForeignTradingView;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoTradingStatisticsView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.vndirect.domain.struts2.listedmarket.MarketStatisticsModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class MarketStatisticsAction extends ActionSupport implements ModelDriven<MarketStatisticsModel> {
	private static final long serialVersionUID = 6391591552578294270L;

	private static Logger logger = Logger.getLogger(MarketStatisticsAction.class);

	// +++ define cache keys
	private static final String LIST_MARKET_KEY = "$LIST_MARKET_KEY$";
	
	@Autowired
	private IListedMarketManager listedMarketManager;

	private MarketStatisticsModel model = new MarketStatisticsModel();

	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public MarketStatisticsModel getModel() {
		return model;
	}

	public String viewHistoricalPriceForSymbol() throws Exception {
		final String LOCATION = "viewHistoricalPriceForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			// Get company info from session
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
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.marketStatistics"));
			breadcrumbs.add("lich-su-gia/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
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

	public String viewTradingStatisticsForSymbol() {
		final String LOCATION = "viewTradingStatisticsForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// Get company info from session
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
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.marketStatistics"));
			breadcrumbs.add("lich-su-gia/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
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

	public String viewForeignTradingForSymbol() {
		final String LOCATION = "viewForeignTradingForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// Get company info from session
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
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.marketStatistics"));
			breadcrumbs.add("lich-su-gia/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
			}

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	// fix vietpn
	public String viewTradingStatistics() throws Exception {
		final String LOCATION = "viewTradingStatistics()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			List listMarket = (List) model.getFromCache(LIST_MARKET_KEY);

			String HOSTC = VNDirectWebUtilities.getHOSTCExchange();
			String HASTC = VNDirectWebUtilities.getHASTCExchange();
			if (listMarket == null) {
				listMarket = new ArrayList();
				listMarket.add(HOSTC);
				listMarket.add(HASTC);
			}
			model.addToCache(LIST_MARKET_KEY, listMarket);
			model.setListMarket(listMarket);

			String market = model.getSearchMarket();
			if (market == null || !(HOSTC.equalsIgnoreCase(market) || HASTC.equalsIgnoreCase(market))) {
				model.setSearchMarket(HOSTC);
			}

			if (StringUtils.isEmpty(model.getStrTradingDate()) || !validateTradingDate()) {
				Date lastTradingTime = VNDirectWebUtilities.getLastTradingTime(model.getSearchMarket());
				model.setStrTradingDate(VNDirectDateUtils.dateToString(lastTradingTime,
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}

			final SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
					.getFromCache(SearchMarketStatisticsView.class.getName());
			final SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();

			if (cacheSearchObj != null) {
				cacheSearchObj.setPagingIndex(searchObj.getPagingIndex());
				model.setSearchMarketStatisticsView(cacheSearchObj);
				this.searchTradingStatistics(model);
			} else {
				try {
					this.searchTradingStatistics(model);
				} catch (Exception e) {
					logger.error(LOCATION + ":: Exception: " + e);
				}
			}

		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.marketStatistics"));
		breadcrumbs.add("/thong-ke-thi-truong-chung-khoan/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage("viewTradingStatistics");

		return SUCCESS;
	}

	public String viewHistoricalPrice() throws Exception {
		final String LOCATION = "viewHistoricalPrice()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			if (validateFomatDate()) {
				// Get Price View info
				SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
				// Get Price View info from cache
				SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
						.getFromCache(SearchMarketStatisticsView.class.getName());

				if (cacheSearchObj != null) {
					cacheSearchObj.setPagingIndex(searchObj.getPagingIndex());
					model.setSearchMarketStatisticsView(cacheSearchObj);
					this.searchHistoryPrice(model);
				} else {
					try {
						this.searchHistoryPrice(model);
					} catch (Exception e) {
						logger.error(LOCATION + ":: Exception: " + e);
					}
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.marketStatistics"));
		breadcrumbs.add("/thong-ke-thi-truong-chung-khoan/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage("viewHistoricalPrice");

		return SUCCESS;
	}

	public String viewForeignTrading() throws Exception {
		final String LOCATION = "viewForeignTrading()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			List listMarket = (List) model.getFromCache(LIST_MARKET_KEY);

			String HOSTC = VNDirectWebUtilities.getHOSTCExchange();
			String HASTC = VNDirectWebUtilities.getHASTCExchange();
			if (listMarket == null) {
				listMarket = new ArrayList();
				listMarket.add(HOSTC);
				listMarket.add(HASTC);
			}
			model.addToCache(LIST_MARKET_KEY, listMarket);
			model.setListMarket(listMarket);

			String market = model.getSearchMarket();
			if (market == null || !(HOSTC.equalsIgnoreCase(market) || HASTC.equalsIgnoreCase(market))) {
				model.setSearchMarket(HOSTC);
			}

			if (StringUtils.isEmpty(model.getStrTradingDate()) || !validateTradingDate()) {
				Date lastTradingTime = VNDirectWebUtilities.getLastTradingTime(model.getSearchMarket());
				model.setStrTradingDate(VNDirectDateUtils.dateToString(lastTradingTime,
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}

			SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
					.getFromCache(SearchMarketStatisticsView.class.getName());
			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();

			if (cacheSearchObj != null) {
				cacheSearchObj.setPagingIndex(searchObj.getPagingIndex());
				model.setSearchMarketStatisticsView(cacheSearchObj);
				this.searchForeignTrading(model);
			} else {
				try {
					this.searchForeignTrading(model);
				} catch (Exception e) {
					logger.error(LOCATION + ":: Exception: " + e);
				}
			}

		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.marketStatistics"));
		breadcrumbs.add("/thong-ke-thi-truong-chung-khoan/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage("viewForeignTrading");

		return SUCCESS;
	}

	protected boolean validateTradingDate() {
		if (model.getStrTradingDate() != null && model.getStrTradingDate().trim().length() > 0
				&& !Validation.isValidDate(model.getStrTradingDate())) {
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.TradingDateFieldInvalid"));
			return false;
		}
		return true;
	}

	protected boolean validateFomatDate() {
		String StrFromDate = model.getStrFromDate();
		String StrToDate = model.getStrToDate();

		if ((StrFromDate != null && StrFromDate.trim().length() > 0 && !Validation.isValidDate(StrFromDate))
				&& (StrToDate != null && StrToDate.trim().length() > 0 && !Validation.isValidDate(StrToDate))) {
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.FromDateFieldInvalid"));
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.ToDateFieldInvalid"));
			return false;
		} else if (StrFromDate != null && StrFromDate.trim().length() > 0 && !Validation.isValidDate(StrFromDate)) {
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.FromDateFieldInvalid"));
			return false;
		} else if (StrToDate != null && StrToDate.trim().length() > 0 && !Validation.isValidDate(StrToDate)) {
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.ToDateFieldInvalid"));
			return false;
		}
		return true;
	}

	private void searchHistoryPrice(MarketStatisticsModel model) throws Exception {
		final String LOCATION = "searchHistoryPrice()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		SearchMarketStatisticsView searchMarketStatisticsView = model.getSearchMarketStatisticsView();

		try {
			searchMarketStatisticsView.setFromDate(VNDirectDateUtils.stringToDate(model.getStrFromDate(),
					VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
		} catch (Exception e) {
			searchMarketStatisticsView.setFromDate(VNDirectDateUtils.getFromDate());
		}

		try {
			searchMarketStatisticsView.setToDate(VNDirectDateUtils.stringToDate(model.getStrToDate(),
					VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
		} catch (Exception e) {
			searchMarketStatisticsView.setToDate(VNDirectDateUtils.getToDate());
		}

		// +++ format symbol
		try {
			String symbol = searchMarketStatisticsView.getSymbol();
			if (symbol.indexOf("-") > -1) {
				symbol = symbol.substring(0, symbol.indexOf("-"));
				searchMarketStatisticsView.setSymbol(symbol.trim());
			}
		} catch (Exception e) {
		}

		PagingInfo pagingInfo = model.getPagingInfo();
		pagingInfo.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_OF_RESEARCH)));

		SearchResult<IfoSecPriceView> searchResult = listedMarketManager.searchHistoricalPrice(searchMarketStatisticsView,
				pagingInfo);

		if (searchResult != null) {
			request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
			model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			model.setSearchResult(searchResult);
		}

		if (searchResult == null || searchResult.size() == 0) {
			this.addActionError(getText("web.label.messages.norecord"));
		}

		// add SearchMarketStatisticsView object to cache
		model.addToCache(SearchMarketStatisticsView.class.getName(), searchMarketStatisticsView);
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
	}

	private void searchForeignTrading(MarketStatisticsModel model) throws Exception {
		final String LOCATION = "searchForeignTrading()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		SearchMarketStatisticsView searchMarketStatisticsView = model.getSearchMarketStatisticsView();

		try {
			if (model.getStrTradingDate() != null && model.getStrTradingDate().length() > 0) {
				searchMarketStatisticsView.setTradingDate(VNDirectDateUtils.stringToDate(model.getStrTradingDate(),
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}
		} catch (Exception e) {
			searchMarketStatisticsView.setTradingDate(VNDirectDateUtils.getCurrentDate());
		}

		PagingInfo pagingInfo = model.getPagingInfo();
		pagingInfo.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_OF_RESEARCH)));
		// pagingInfo.setOffset(10);

		SearchResult<IfoForeignTradingView> searchResult = listedMarketManager.searchForeignTrading(searchMarketStatisticsView,
				pagingInfo);

		if (searchResult != null) {
			request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
			model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			model.setSearchResult(searchResult);
		}

		if (searchResult == null || searchResult.size() == 0) {
			this.addActionError(getText("web.label.messages.norecord"));
		}

		// add SearchMarketStatisticsView object to cache
		model.addToCache(SearchMarketStatisticsView.class.getName(), searchMarketStatisticsView);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
	}

	private void searchTradingStatistics(MarketStatisticsModel model) throws Exception {
		final String LOCATION = "searchTradingStatistics()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		SearchMarketStatisticsView searchMarketStatisticsView = model.getSearchMarketStatisticsView();

		try {
			if (model.getStrTradingDate() != null && model.getStrTradingDate().length() > 0) {
				searchMarketStatisticsView.setTradingDate(VNDirectDateUtils.stringToDate(model.getStrTradingDate(),
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}
		} catch (Exception e) {
			searchMarketStatisticsView.setTradingDate(VNDirectDateUtils.getCurrentDate());
		}

		PagingInfo pagingInfo = model.getPagingInfo();
		pagingInfo.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_OF_RESEARCH)));

		SearchResult<IfoTradingStatisticsView> searchResult = listedMarketManager.searchTradingStatistics(
				searchMarketStatisticsView, pagingInfo);

		if (searchResult != null && searchResult.size() != 0) {
			request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
			model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			model.setSearchResult(searchResult);
		}

		if (searchResult == null || searchResult.size() == 0) {
			this.addActionError(getText("web.label.messages.norecord"));
		}
		// add SearchMarketStatisticsView object to cache
		model.addToCache(SearchMarketStatisticsView.class.getName(), searchMarketStatisticsView);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
	}

	public String viewIRSIHistoricalPrice() throws Exception {
		final String LOCATION = "viewIRSIHistoricalPrice()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			// Get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND",
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.historicalPrice"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.HistoricalPrice"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
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

	public String viewIRSITradingStatisticsForSymbol() {
		final String LOCATION = "viewIRSITradingStatisticsForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// Get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND",
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.tradingStatistic"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
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

	public String viewIRSIForeignTrading() {
		final String LOCATION = "viewIRSIForeignTrading()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// Get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND",
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.foreignTrading"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.ForeignTrading"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
			}

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String viewIRSITradingStatistics() {
		final String LOCATION = "viewIRSITradingStatistics()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// Get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND",
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.tradingStatistic"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("web.label.ListedMarket.Tab.MarketStatistics.Button.TradingStatistics"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();
			if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
				searchObj.setSymbol(currentComp.getSymbol().trim());
			}
			model.setSymbol(currentComp.getSymbol());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	private void doSEOpage(String nameMethod) {
		if (nameMethod.equals("viewForeignTrading")) {
			model.setPageTitle(this.getText("analysis.marketStatistics.foreignTrading.title"));
			model.setPageDescription(this.getText("analysis.marketStatistics.foreignTrading.desc"));
			model.setPageKeywords(this.getText("analysis.marketStatistics.foreignTrading.keyWords"));
		} else if (nameMethod.equals("viewHistoricalPrice")) {
			model.setPageTitle(this.getText("analysis.marketStatistics.priceHistory.title"));
			model.setPageDescription(this.getText("analysis.marketStatistics.priceHistory.desc"));
			model.setPageKeywords(this.getText("analysis.marketStatistics.priceHistory.keyWords"));
		} else if (nameMethod.equals("viewTradingStatistics")) {
			model.setPageTitle(this.getText("analysis.marketStatistics.tradingStatistics.title"));
			model.setPageDescription(this.getText("analysis.marketStatistics.tradingStatistics.desc"));
			model.setPageKeywords(this.getText("analysis.marketStatistics.tradingStatistics.keyWords"));
		}
	}
}
