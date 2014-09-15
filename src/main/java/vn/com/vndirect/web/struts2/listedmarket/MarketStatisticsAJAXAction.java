package vn.com.vndirect.web.struts2.listedmarket;

import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import vn.com.vndirect.domain.extend.IfoSecPriceViewExt;
import vn.com.vndirect.domain.extend.IfoTradingStatisticsViewExt;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.vndirect.domain.struts2.listedmarket.MarketStatisticsModel;
import vn.com.vndirectreports.IReportManager;
import vn.com.vndirectreports.trading.TradingStatistic;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.utility.Utilities;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarketStatisticsAJAXAction extends ActionSupport implements ModelDriven<MarketStatisticsModel> {

	private static final long serialVersionUID = 6579642189384179644L;
	private static Logger logger = Logger.getLogger(MarketStatisticsAJAXAction.class);

	private MarketStatisticsModel model = new MarketStatisticsModel();

	@Autowired
	private IListedMarketManager listedMarketManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public MarketStatisticsModel getModel() {
		return model;
	}

	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	public String executeSearchHistoricalPriceForSymbol() throws Exception {
		final String LOCATION = "executeSearchHistoricalPriceForSymbol()";
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
						// Get company info from session
						CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

						if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
							searchObj.setSymbol(currentComp.getSymbol().trim());
							this.searchHistoryPrice(model);
						}
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
		return SUCCESS;
	}

	/**
	 * @param model
	 * @throws Exception
	 */
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

	public String executeTradingForSymbol() throws Exception {
		final String LOCATION = "executeTradingForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateFomatDate()) {
				SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
						.getFromCache(SearchMarketStatisticsView.class.getName());
				SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();

				if (cacheSearchObj != null) {
					cacheSearchObj.setPagingIndex(searchObj.getPagingIndex());
					model.setSearchMarketStatisticsView(cacheSearchObj);
					this.searchTradingStatisticsForSymbol(model);
				} else {
					try {
						// Get company info from session
						CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

						if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
							searchObj.setSymbol(currentComp.getSymbol().trim());
							this.searchTradingStatisticsForSymbol(model);
						}
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
		return SUCCESS;
	}

	/**
	 * @param model
	 * @throws Exception
	 */
	private void searchTradingStatisticsForSymbol(MarketStatisticsModel model) throws Exception {
		final String LOCATION = "searchTradingStatisticsForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		HttpServletRequest request = ServletActionContext.getRequest();

		SearchMarketStatisticsView searchMarketStatisticsView = model.getSearchMarketStatisticsView();

		// Format and validate
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

		SearchResult<IfoTradingStatisticsView> searchResult = listedMarketManager.searchTradingStatistics4Symbol(
				searchMarketStatisticsView, pagingInfo);

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

	public String executeForeignTradingForSymbol() throws Exception {
		final String LOCATION = "executeForeignTradingForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			if (validateFomatDate()) {
				SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
						.getFromCache(SearchMarketStatisticsView.class.getName());
				SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();

				if (cacheSearchObj != null) {
					cacheSearchObj.setPagingIndex(searchObj.getPagingIndex());
					model.setSearchMarketStatisticsView(cacheSearchObj);
					this.searchForeignTradingForSymbol(model);

				} else {
					try {
						// Get company info from session
						CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

						if (currentComp != null && !Validation.isEmpty(currentComp.getSymbol())) {
							searchObj.setSymbol(currentComp.getSymbol().trim());
							this.searchForeignTradingForSymbol(model);
						}
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
		return SUCCESS;
	}

	/**
	 * @param model
	 * @throws Exception
	 */
	private void searchForeignTradingForSymbol(MarketStatisticsModel model) throws Exception {
		final String LOCATION = "searchForeignTradingForSymbol()";
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

		SearchResult<IfoForeignTradingView> searchResult = listedMarketManager.searchForeignTrading4Symbol(
				searchMarketStatisticsView, pagingInfo);

		if (searchResult != null && searchResult.size() != 0) {
			request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
			model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			model.setSearchResult(searchResult);
		}

		if (searchResult == null || searchResult.size() == 0) {
			this.addActionError(getText("web.label.messages.norecord"));
		}

		// add SearchResult object to cache
		model.addToCache(SearchMarketStatisticsView.class.getName(), searchMarketStatisticsView);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
	}

	public String executeTradingStatistics() throws Exception {
		final String LOCATION = "executeTradingStatistics()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			if (validateTradingDate()) {
				SearchMarketStatisticsView cacheSearchObj = (SearchMarketStatisticsView) model
						.getFromCache(SearchMarketStatisticsView.class.getName());
				SearchMarketStatisticsView searchObj = model.getSearchMarketStatisticsView();

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

	public String executeSearchHistoricalPrice() throws Exception {
		final String LOCATION = "executeSearchHistoricalPrice()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateFomatDate()) {
				// Check Symbol
				if (Validation.isEmpty(model.getSearchMarketStatisticsView().getSymbol())) {
					this.addActionError(getText("web.label.userProfile.RecoverUserInfo.Message.NoSymbol"));
				}
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
		return SUCCESS;
	}

	// fix vietpn
	public String executeTradingForeignInvestors() throws Exception {
		final String LOCATION = "executeTradingForeignInvestors()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateTradingDate()) {
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

	/**
	 * get data to download report for a company based on downloadType
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeDownloadReportForSymbol() throws FunctionalException, SystemException {
		final String LOCATION = "executeDownloadReportForSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			SearchMarketStatisticsView searchIfoSecPriceView = model.getSearchMarketStatisticsView();

			try {
				searchIfoSecPriceView.setToDate(VNDirectDateUtils.stringToDate(model.getStrToDate(),
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			} catch (Exception e) {
				String strCurrDate = DateUtils
						.dateToString(new java.util.Date(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
				model.setStrToDate(strCurrDate);
				searchIfoSecPriceView.setToDate(DateUtils.stringToDate(model.getStrToDate().trim(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}

			try {
				searchIfoSecPriceView.setFromDate(VNDirectDateUtils.stringToDate(model.getStrFromDate(),
						VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			} catch (Exception e) {
				Date prevDate = DateUtils.addMonth(searchIfoSecPriceView.getToDate(), -3);
				model.setStrFromDate(DateUtils.dateToString(prevDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
				searchIfoSecPriceView.setFromDate(DateUtils.stringToDate(model.getStrFromDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			}

			// +++ format symbol
			try {
				String symbol = searchIfoSecPriceView.getSymbol();
				if (symbol.indexOf("-") > -1) {
					symbol = symbol.substring(0, symbol.indexOf("-"));
					searchIfoSecPriceView.setSymbol(symbol.trim());
				}
			} catch (Exception e) {
			}

			SearchResult<IfoSecPriceViewExt> searchResult = listedMarketManager.reportHistoricalPrice(searchIfoSecPriceView);
			if (searchResult != null) {
				model.setSearchResult(searchResult);
			} else {
				model.setSearchResult(new SearchResult<IfoSecPriceViewExt>());
			}
			generateCSVFile(model);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return NONE;
	}

	/**
	 * get data to download report for trading statistics for all company in
	 * selected market ( HNX, HOSE )
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeDownloadTradingStatistics() throws FunctionalException, SystemException {
		final String LOCATION = "executeDownloadTradingStatistics()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			SearchMarketStatisticsView searchIfoTradingStatisticsView = model.getSearchMarketStatisticsView();

			try {
				if (model.getStrTradingDate() != null && model.getStrTradingDate().length() > 0) {
					searchIfoTradingStatisticsView.setTradingDate(DateUtils.stringToDate(model.getStrTradingDate(),
							DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
				}
			} catch (Exception e) {
				searchIfoTradingStatisticsView.setTradingDate(DateUtils.getCurrentDate());
			}

			searchIfoTradingStatisticsView.setNumberItem(Integer.parseInt(ServerConfig
					.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_OF_RESEARCH)));
			// --- call searchTradingStatistics function from quotesManager
			SearchResult<IfoTradingStatisticsViewExt> searchResult = listedMarketManager
					.reportTradingStatistics(searchIfoTradingStatisticsView);
			if (searchResult != null) {
				model.setSearchResult(searchResult);
			} else {
				model.setSearchResult(new SearchResult<IfoSecPriceViewExt>());
			}

			/* generate CSV file */
			generateCSVFile(model);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return NONE;
	}

	// download file
	@SuppressWarnings("unchecked")
	private void generateCSVFile(MarketStatisticsModel data) {
		SearchResult<IfoSecPriceViewExt> result = model.getSearchResult();
		String downloadType = data.getDownloadType();
		String fileName = "trading-statistics-";
		Object[] objs = result.toArray();
		String csvType = "1";
		SearchResult<TradingStatistic> tradingData = new SearchResult<TradingStatistic>();
		if (Constants.Statistics.HISTORICAL_PRICE_DOWNLOAD_TYPE.equals(downloadType)
				|| Constants.Statistics.TRADING_STATISTICS_4SYMBOL_DOWNLOAD_TYPE.equals(downloadType)) {
			fileName = "historical-price-";
			if (Constants.Statistics.TRADING_STATISTICS_4SYMBOL_DOWNLOAD_TYPE.equals(downloadType)) {
				fileName = "trading-statistics-";
			}
			String symbol = "";
			String fromDate = VNDirectWebUtilities.cleanString((String) data.getStrFromDate());
			String toDate = VNDirectWebUtilities.cleanString((String) data.getStrToDate());
			for (int i = 0; i < objs.length; i++) {
				IfoSecPriceViewExt ifoSecPriceViewExt = (IfoSecPriceViewExt) objs[i];
				if (i == 0) {
					symbol = ifoSecPriceViewExt.getSymbol();
				}
				TradingStatistic statistic = new TradingStatistic();
				statistic.setTicker(ifoSecPriceViewExt.getSymbol());

				statistic.setDate(ifoSecPriceViewExt.getStrTransDate());
				statistic.setOpenPrice(ifoSecPriceViewExt.getStrOpenPrice().replaceAll(",", ""));
				statistic.setHighestPrice(ifoSecPriceViewExt.getStrHighPrice().replaceAll(",", ""));
				statistic.setLowestPrice(ifoSecPriceViewExt.getStrLowPrice().replaceAll(",", ""));
				statistic.setTotalVolumn(ifoSecPriceViewExt.getStrVolume().replaceAll(",", ""));
				statistic.setClosePrice(ifoSecPriceViewExt.getStrClosePrice().replaceAll(",", ""));
				tradingData.add(statistic);
			}
			fileName += symbol;
			fileName += fromDate.replaceAll("/", "");
			fileName += toDate.replaceAll("/", "");
			fileName += ".csv";
		} else if (Constants.Statistics.TRADING_STATISTICS_DOWNLOAD_TYPE.equals(downloadType)) {
			String market = VNDirectWebUtilities.cleanString((String) model.getSearchMarketStatisticsView().getMarket());
			String tradingDate = "";
			for (int i = 0; i < objs.length; i++) {
				IfoTradingStatisticsViewExt ifoTradingStatisticsView = (IfoTradingStatisticsViewExt) objs[i];
				if (i == 0) {
					tradingDate = ifoTradingStatisticsView.getStrTransDate();
				}
				TradingStatistic statistic = new TradingStatistic();
				statistic.setTicker(ifoTradingStatisticsView.getSecCode());
				statistic.setDate(ifoTradingStatisticsView.getStrTransDate());
				statistic.setOpenPrice(ifoTradingStatisticsView.getStrOpenPrice().replaceAll(",", ""));
				statistic.setHighestPrice(ifoTradingStatisticsView.getStrHighPrice().replaceAll(",", ""));
				statistic.setLowestPrice(ifoTradingStatisticsView.getStrLowPrice().replaceAll(",", ""));
				statistic.setTotalVolumn(ifoTradingStatisticsView.getStrTotalVolumn().replaceAll(",", ""));
				statistic.setClosePrice(ifoTradingStatisticsView.getStrClosePrice().replaceAll(",", ""));
				tradingData.add(statistic);
			}
			fileName += market;
			fileName += tradingDate.replaceAll("/", "");
			fileName += ".csv";
		} else {
			fileName = "unknow_download.csv";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			IReportManager reportManager = (IReportManager) SpringUtils.getBean("VNDirectReportManager");
			byte[] _out = reportManager.tradingStatistic(tradingData);
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/csv");
			response.setHeader("Content-Disposition", "1".equals(csvType) ? "inline; fileName=" + fileName
					: "attachment; fileName=" + fileName);
			response.setContentLength(_out.length);
			outputStream.write(_out, 0, _out.length);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	protected boolean validateTradingDate() {
		if (model.getStrTradingDate() != null && model.getStrTradingDate().trim().length() > 0
				&& !Validation.isValidDate(model.getStrTradingDate())) {
			this.addActionError(getText("web.label.MarketStatisticsAJAXAction.form.Messages.TradingDateFieldInvalid"));
			return false;
		}
		return true;
	}
}
