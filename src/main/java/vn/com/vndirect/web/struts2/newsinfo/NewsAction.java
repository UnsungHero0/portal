package vn.com.vndirect.web.struts2.newsinfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.newsinfo.NewsMappingModel;
import vn.com.vndirect.domain.struts2.newsinfo.NewsModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class NewsAction extends ActionSupport implements ModelDriven<NewsModel> {

	private static final String THONG_TIN_CO_PHIEU_URL = "thong-tin-co-phieu";

	private static final String QUAN_HE_CO_DONG_VNDIRECT_URL = "quan-he-co-dong-vndirect";

	private static final String VND_SYMBOL = "VND";

	protected static final String EVENT_NEWSTYPE = "Event";

	protected static final String EXPERTS_NEWSTYPE = "Experts";

	protected static final String EXPERT_NEWSTYPE = "Expert";

	protected static final String MAC_WORLD_NEWSTYPE = "MacWorld";

	protected static final String MAC_VN_NEWSTYPE = "MacVN";

	protected static final String DISCLOSURE_NEWSTYPE = "Disclousure";

	protected static final String VNDIRECT_NEWSTYPE = "VNDIRECT";

	private static final String DAILYREPORT = "DailyReport";

	private static Logger logger = Logger.getLogger(NewsAction.class);

	protected NewsModel model = new NewsModel();
	
	/** current index page (use for capture index of page on url) */
	private String currentIndex;
	/** use this to capture news title on the url */
	private String newsURL;
	/** current company symbol */
	private String currentSymbol;

	private String strFromDate;
	private String strToDate;
	private final int limitWords = 15;
	private final int dayExpand = 180;
	private static Map<NewsMappingModel, Integer> mapDayExpandForNewsHomePage = new HashMap<NewsMappingModel, Integer>();
	@Autowired
	private INewsInfoManager newsInfoManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	public NewsModel getModel() {
		return model;
	}

	/**
	 * Setting the manager for NewsOnline
	 * 
	 * @param newsInfoManager
	 */
	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	public INewsInfoManager getNewsInfoManager() {
		return newsInfoManager;
	}

	/**
	 * Get news list by NewsType = Event
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	@SuppressWarnings("rawtypes")
	public String executeGetEvent() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetEvent";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EVENT);
			SearchResult result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String executeGetNewsByTypes() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNewsByTypes";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(null);
			if (model.getType() != null) {
				if (model.getType().indexOf(",") != -1) {
					Collection newsTypeList = new ArrayList();
					StringTokenizer st = new StringTokenizer(model.getType(), ",");
					while (st.hasMoreTokens()) {
						newsTypeList.add(st.nextToken());
					}
					searchObj.setNewsTypeList(newsTypeList);
				} else {
					searchObj.setNewsType(model.getType());
				}
			}

			SearchResult result = newsInfoManager.searchExpertNews(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String executeGetMostViewedNewsByTypes() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetMostViewedNewsByTypes";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(null);
			if (model.getType().indexOf(",") != -1) {
				Collection newsTypeList = new ArrayList();
				StringTokenizer st = new StringTokenizer(model.getType(), ",");
				while (st.hasMoreTokens()) {
					newsTypeList.add(st.nextToken());
				}
				searchObj.setNewsTypeList(newsTypeList);
				searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -1),
						"dd-MMM-yy"));
			} else {
				searchObj.setNewsType(model.getType());
			}

			SearchResult result = newsInfoManager.getMostViewedNews(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Get news list by NewsType = News
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 *             fix by vietpn
	 */
	public String executeGetNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());

			newsInfoManager.createNewsUrl(result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetMacWorld() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetMacWorld";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);

			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String executeGetIPONews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetIPONews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIPO(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);

			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String executeGetStockInformation() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetStockInformation";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.PUBLIC_INFO);
			SearchIfoNews searchObj = buildCommonCriteria(null);
			List<String> types = new ArrayList<String>() {
				{
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS));
				}
			};
			searchObj.setNewsTypeList(types);
			searchObj.setIsHotNews(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IS_HOT_NEWS));
			SearchResult<SearchIfoNews> result = newsInfoManager.searchStockNews(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String executeGetCenterAnalysisOfVNDirect() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetCenterAnalysisOfVNDirect";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoNews searchObj = buildCommonCriteria(null);
			final List<String> types = new ArrayList<String>() {
				{
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STRATEGIC));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_COMPANY));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_QUARTER));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_RISKALERT));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_QUICKREPORT));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STASTIC));
				}
			};
			searchObj.setNewsTypeList(types);
			final SearchResult<SearchIfoNews> result = newsInfoManager.searchCenterAnalysisOfVNDirect(searchObj,
					model.getPagingInfo());
			model.setPagingInfo((PagingInfo) result.getPagingInfo());
			logger.debug("result:" + result.size());

			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	/**
	 * Get news list by NewsType = MarketNews
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetMarketNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetMarketNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = new SearchIfoNews();
			String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			searchObj.setLocale(locale);

			List<String> types = new ArrayList<String>() {
				{
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_RESEARCH_MARKET_NEWS));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_RESEARCH_NEWS));
				}
			};
			searchObj.setNewsTypeList(types);

			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);

			SearchResult<SearchIfoNews> result = newsInfoManager.searchMartketNews(searchObj, model.getPagingInfo());

			logger.debug("executeGetMarketNews_result:" + result.size());

			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Get lastest news list by NewsType = MarketNews
	 * 
	 * @return NewsInfo
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetLatestMarketNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetLatestMarketNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.MARKET_NEWS);
			model.setIfoNews(newsInfoManager.getLatestMartketNews(searchObj));

			// Removed <p> tag of new abstract
			String newsAbstract = model.getIfoNews().getNewsAbstract();
			if (newsAbstract.indexOf("<p") == 0) {
				newsAbstract = newsAbstract.substring(newsAbstract.indexOf(">") + 1, newsAbstract.length());
			}
			if (newsAbstract.indexOf("</p>") > 0) {
				newsAbstract = newsAbstract.replaceAll("</p>", "");
			}

			model.getIfoNews().setNewsAbstract(newsAbstract);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Get news list by NewsType = PublicInfo
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetPublicInfo() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetPublicInfo";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.PUBLIC_INFO);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);

			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Get news list by NewsType = VNDS News
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetVNDSNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetVNDSNews";
		boolean isSplited = false;
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
			model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
			model.getPagingInfo().setOffset(10);
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS);

			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			newsInfoManager.processNewsForView(result);
			model.setSearchResult(result);
			model.setPagingInfo((PagingInfo) result.getPagingInfo());
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}
	
	
	
	
	public String executeGetNewsHomePage(){
		final String LOCATION = "executeGetNewsHomePage";
		boolean isSplited = false;
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		
		//Hardcode 10 records for loading news in home page
		NewsMappingModel mapping = new NewsMappingModel(model.getTotalPage(), 10);
		Integer dayExpandTmp = mapDayExpandForNewsHomePage.get(mapping);
		if (dayExpandTmp == null) {
			dayExpandTmp = dayExpand;
		}
		if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim()) ) {
			if(Integer.valueOf(currentIndex.trim()) < model.getTotalPage()) {
				model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
				model.getPagingInfo().setOffset(10);
			} else {
				dayExpandTmp += dayExpand; 
				model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
				model.getPagingInfo().setOffset(10);
			}
		}
		
		try {
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfoHomePage(model.getPagingInfo(), dayExpandTmp);
			newsInfoManager.processNewsForView(result);
			model.setSearchResult(result);
			model.setPagingInfo((PagingInfo) result.getPagingInfo());
			mapping.setTotalPage(result.getPagingInfo().getTotalPage());
			mapDayExpandForNewsHomePage.put(mapping, dayExpandTmp);
		} catch (SystemException e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}
	/**
	 * Get news list by NewsType = Not VNDS News
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	@SuppressWarnings("rawtypes")
	public String executeGetNotVNDSNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNotVNDSNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NOT_VNDS_NEWS);

			SearchResult result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Get news list by NewsType and Symbol (disclosure)
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String executeGetNewsForSymbolAndTypeList() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNewsForSymbolAndTypeList";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateNewsDate()) {
				if (model.getType() != null) {
					SearchIfoNews searchObj = new SearchIfoNews();
					if (model.getType().indexOf(",") != -1) {
						Collection newsTypeList = new ArrayList();
						StringTokenizer st = new StringTokenizer(model.getType(), ",");
						while (st.hasMoreTokens()) {
							newsTypeList.add(st.nextToken());
						}
						searchObj.setNewsTypeList(newsTypeList);

					} else {
						searchObj.setNewsType(model.getType());
					}

					CurrentCompanyForQuote currentComp = null;
					if (StringUtils.isEmpty(currentSymbol)) {
						currentSymbol = SessionManager.getSymbolCompany();
					}
					currentComp = quotesManager.quickSearchQuotes(currentSymbol,
							I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					SessionManager.setSymbolCompany(currentComp.getSymbol());
					

					if (currentComp != null) {
						searchObj.setCompanyId(currentComp.getCompanyId());
					}
					searchObj.setOrderByDate(true);
					searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
					searchObj.setStrSymbol(currentSymbol);
					searchObj.setStrFromNewsDate(model.getSearchIfoNews().getStrFromNewsDate());
					searchObj.setStrToNewsDate(model.getSearchIfoNews().getStrToNewsDate());
					final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
					newsInfoManager.createNewsUrlWithPageUrl(result, model.getPageUrl());

					model.setSearchResult(result);
					model.setPagingInfo((PagingInfo) result.getPagingInfo());
				}
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

	/**
	 * Get news list by NewsType and Symbol (disclosure)
	 * 
	 * @return News list
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String executeGetNewsForSymbolAndTypeList_CompanyEvent() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNewsForSymbolAndTypeList_CompanyEvent";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateNewsDate()) {
				if (model.getType() != null) {
					SearchIfoNews searchObj = new SearchIfoNews();
					if (model.getType().indexOf(",") != -1) {
						Collection newsTypeList = new ArrayList();
						StringTokenizer st = new StringTokenizer(model.getType(), ",");
						while (st.hasMoreTokens()) {
							newsTypeList.add(st.nextToken());
						}
						searchObj.setNewsTypeList(newsTypeList);

					} else {
						searchObj.setNewsType(model.getType());
					}

					CurrentCompanyForQuote currentComp = null;
					if (StringUtils.isEmpty(currentSymbol)) {
						currentSymbol = SessionManager.getSymbolCompany();
					}
					currentComp = quotesManager.quickSearchQuotes(currentSymbol,
							I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					SessionManager.setSymbolCompany(currentComp.getSymbol());

					if (currentComp != null) {
						searchObj.setCompanyId(currentComp.getCompanyId());
					}
					searchObj.setOrderByDate(true);
					searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					searchObj
							.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
					searchObj.setStrFromNewsDate(model.getSearchIfoNews().getStrFromNewsDate());
					searchObj.setStrToNewsDate(model.getSearchIfoNews().getStrToNewsDate());
					final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
					newsInfoManager.createNewsUrlWithPageUrl(result, model.getPageUrl());

					model.setSearchResult(result);
					model.setPagingInfo((PagingInfo) result.getPagingInfo());
				}
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String executeGetCompanyEventNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeCompanyEventNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (validateNewsDate()) {
				if (model.getType() != null) {
					SearchIfoNews searchObj = new SearchIfoNews();
					if (model.getType().indexOf(",") != -1) {
						Collection newsTypeList = new ArrayList();
						StringTokenizer st = new StringTokenizer(model.getType(), ",");
						while (st.hasMoreTokens()) {
							newsTypeList.add(st.nextToken());
						}
						searchObj.setNewsTypeList(newsTypeList);

					} else {
						searchObj.setNewsType(model.getType());
					}

					CurrentCompanyForQuote currentComp = null;
					if (StringUtils.isEmpty(currentSymbol)) {
						currentSymbol = SessionManager.getSymbolCompany();
					}
					currentComp = quotesManager.quickSearchQuotes(currentSymbol,
							I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					SessionManager.setSymbolCompany(currentComp.getSymbol());

					if (currentComp != null) {
						searchObj.setCompanyId(currentComp.getCompanyId());
					}
					searchObj.setOrderByDate(true);
					searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
					searchObj.setStrFromNewsDate(model.getSearchIfoNews().getStrFromNewsDate());
					searchObj.setStrToNewsDate(model.getSearchIfoNews().getStrToNewsDate());
					final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
					logger.debug("result:" + result.size());
					SearchIfoNews ifoNews;
					for (int i = 0; i < result.size(); i++) {
						ifoNews = result.get(i);
						if(StringUtils.isEmpty(ifoNews.getNewsHeader()) || StringUtils.isEmpty(ifoNews.getNewsType())){
							result.remove(i);
							continue;
						}
						result.get(i).setUrlDetail(
								NewsUrlUtility.createUrl(model.getPageUrl(), EVENT_NEWSTYPE, ifoNews.getNewsHeader(),
										ifoNews.getNewsId(), currentSymbol));
					}

					model.setSearchResult(result);
					model.setPagingInfo((PagingInfo) result.getPagingInfo());
				}
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

	/**
	 * View Detail news by newsId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewNewsDetail_analysis() throws FunctionalException, SystemException {
		final String LOCATION = "viewNewsDetail_analysis";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (StringUtils.isNotEmpty(newsURL)) {
				final IfoNews inputIfoNews = model.getIfoNews() == null ? new IfoNews() : model.getIfoNews();
				final String newsId = newsURL.substring(newsURL.lastIndexOf("-") + 1, newsURL.length());
				inputIfoNews.setNewsId(Long.parseLong(newsId));
				inputIfoNews.setNewsType(model.getNewsType());

				final IfoNews ifoNews = newsInfoManager.getIfoNewsById(inputIfoNews);
				if (ifoNews != null) {
					try {
						newsInfoManager.updateNewsHit(inputIfoNews.getNewsId());
					} catch (Exception e) {
						logger.error(LOCATION + ":: Exception: " + e);
					}
				} else {
					throw new FunctionalException(LOCATION + "::Invalid parameter newsId = " + newsId);
				}
				model.setIfoNews(ifoNews);

				// SEO
				// Chinh lai Disclousure cho dung chinh ta (Disclosure)
				String newsTitle = ifoNews.getNewsHeader().trim() + " - "
						+ (model.getNewsType().equals(DISCLOSURE_NEWSTYPE) ? "Disclosure" : model.getNewsType()) + " - VNDIRECT";
				model.setPageTitle(newsTitle);
				// ??? ifoNews ko co newsAbtract
				model.setPageDescription(ifoNews.getNewsHeader().trim());
				model.setPageKeywords(model.getNewsType());

				// breadcrumbs
				ArrayList<String> breadcrumbs = new ArrayList<String>();
				breadcrumbs.add(ifoNews.getNewsHeader());
				breadcrumbs.add(this.getText("home.topMenu.analysis"));
				breadcrumbs.add("ttptRoot");
				breadcrumbs.add(this.getText("home.topMenu.analysis.news"));
				breadcrumbs.add("/tin-trong-nuoc.shtml");

				// get most news
				if (MAC_VN_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.news.localNews"));
					breadcrumbs.add("/tin-trong-nuoc.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
					SearchResult<SearchIfoNews> mostViewsNews = getNewsInfoManager().getMostViewedNews(searchObj,
							new PagingInfo());
					getNewsInfoManager().createNewsUrl(mostViewsNews);
					model.setSearchMostView(mostViewsNews);
				} else if (MAC_WORLD_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.news.internationalNews"));
					breadcrumbs.add("/tin-quoc-te.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					newsInfoManager.createNewsUrl(mostViewsNews);
					model.setSearchMostView(mostViewsNews);
				} else if (EXPERT_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.news.expertOpinions"));
					breadcrumbs.add("/y-kien-chuyen-gia.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					newsInfoManager.createNewsUrl(mostViewsNews);
					model.setSearchMostView(mostViewsNews);
				} else if (DISCLOSURE_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.news.disclosureInformation"));
					breadcrumbs.add("/cong-bo-thong-tin.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					newsInfoManager.createNewsUrl(mostViewsNews);
					model.setSearchMostView(mostViewsNews);
				} else if (DAILYREPORT.equals(model.getNewsType())) {
					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS);
					PagingInfo tmp = new PagingInfo();
					tmp.setOffset(5);
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getRelatedVideoNews(searchObj, tmp);
					newsInfoManager.createNewsUrl(mostViewsNews);
					model.setSearchMostView(mostViewsNews);
				}

				// breadcrumbs
				model.setBreadcrumbs(breadcrumbs);
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

	public String viewNewsDetail_si() throws FunctionalException, SystemException {
		final String LOCATION = "viewNewsDetail_si";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (StringUtils.isNotEmpty(newsURL)) {
				final CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

				final IfoNews inputIfoNews = model.getIfoNews() == null ? new IfoNews() : model.getIfoNews();
				final String newsId = newsURL.substring(newsURL.lastIndexOf("-") + 1, newsURL.length());
				inputIfoNews.setNewsId(Long.parseLong(newsId));
				inputIfoNews.setNewsType(model.getNewsType());

				final IfoNews ifoNews = newsInfoManager.getIfoNewsById(inputIfoNews);
				if (ifoNews != null) {
					try {
						newsInfoManager.updateNewsHit(inputIfoNews.getNewsId());
					} catch (Exception e) {
						logger.error(LOCATION + ":: Exception: " + e);
					}
				} else {
					throw new FunctionalException(LOCATION + "::Invalid parameter newsId = " + newsId);
				}
				model.setIfoNews(ifoNews);

				// set page title, desc, keywords
				String newsTitle = ifoNews.getNewsHeader().trim() + " - "
						+ (model.getNewsType().equals(DISCLOSURE_NEWSTYPE) ? "Disclosure" : model.getNewsType()) + " - VNDIRECT";
				model.setPageTitle(newsTitle);
				// ??? ifoNews ko co newsAbtract
				model.setPageDescription(ifoNews.getNewsHeader().trim());
				model.setPageKeywords(model.getNewsType());

				// breadcrumbs
				ArrayList<String> breadcrumbs = new ArrayList<String>();
				breadcrumbs.add(ifoNews.getNewsHeader());
				breadcrumbs.add(this.getText("home.topMenu.analysis"));
				breadcrumbs.add("ttptRoot");
				breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
				breadcrumbs.add("tong-quan/" + currentComp.getSymbol().toLowerCase() + ".shtml");

				// get most news
				if (DISCLOSURE_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.stockInfo.companyNews"));
					breadcrumbs.add("tin-doanh-nghiep/" + currentComp.getSymbol().toLowerCase() + ".shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
					if (currentComp != null) {
						searchObj.setListSymbols(new String[] { currentComp.getSymbol() });
					}
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					newsInfoManager.createNewsUrlWithPageUrl(mostViewsNews, THONG_TIN_CO_PHIEU_URL);
					model.setSearchMostView(mostViewsNews);
				} else if (MAC_VN_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("analysis.stockInfo.news.relate"));
					breadcrumbs.add("tin-lien-quan/" + currentComp.getSymbol().toLowerCase() + ".shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
					if (currentComp != null) {
						searchObj.setListSymbols(new String[] { currentComp.getSymbol() });
					}
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					logger.debug("most view result:" + mostViewsNews.size());
					for (int i = 0; i < mostViewsNews.size(); i++) {
						SearchIfoNews news = mostViewsNews.get(i);
						if(StringUtils.isEmpty(news.getNewsHeader()) || StringUtils.isEmpty(news.getNewsType())){
							mostViewsNews.remove(i);
							continue;
						}
						mostViewsNews.get(i).setUrlDetail(
								NewsUrlUtility.createUrl(THONG_TIN_CO_PHIEU_URL, EVENT_NEWSTYPE, news.getNewsHeader(),
										news.getNewsId(), ""));
					}
					model.setSearchMostView(mostViewsNews);
				}

				// breadcrumbs
				model.setBreadcrumbs(breadcrumbs);
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

	public String executeViewNewsDetail_vndirectNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeViewNewsDetail_vndirectNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (StringUtils.isNotEmpty(newsURL)) {
				final IfoNews inputIfoNews = model.getIfoNews() == null ? new IfoNews() : model.getIfoNews();
				final String newsId = newsURL.substring(newsURL.lastIndexOf("-") + 1, newsURL.length());
				inputIfoNews.setNewsId(Long.parseLong(newsId));
				inputIfoNews.setNewsType(model.getNewsType());

				final IfoNews ifoNews = newsInfoManager.getIfoNewsById(inputIfoNews);
				if (ifoNews != null) {
					try {
						newsInfoManager.updateNewsHit(inputIfoNews.getNewsId());
					} catch (Exception e) {
						logger.error(LOCATION + ":: Exception: " + e);
					}
				} else {
					throw new FunctionalException(LOCATION + "::Invalid parameter newsId = " + newsId);
				}
				model.setIfoNews(ifoNews);

				// set page title, desc, keywords
				String newsTitle = ifoNews.getNewsHeader().trim() + " - "
						+ (model.getNewsType().equals(DISCLOSURE_NEWSTYPE) ? "Disclosure" : model.getNewsType()) + " - VNDIRECT";
				model.setPageTitle(newsTitle);
				// ??? ifoNews ko co newsAbtract
				model.setPageDescription(ifoNews.getNewsHeader().trim());
				model.setPageKeywords(model.getNewsType());

				// breadcrumbs
				ArrayList<String> breadcrumbs = new ArrayList<String>();
				breadcrumbs.add(ifoNews.getNewsHeader());
				breadcrumbs.add(this.getText("br.vnd"));
				breadcrumbs.add("ttvndRoot");
				breadcrumbs.add(this.getText("home.topMenu.about.news"));
				breadcrumbs.add("/vndirect/tin-vndirect.shtml");
				model.setBreadcrumbs(breadcrumbs);
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

	public String executeViewNewsDetail_IR() throws FunctionalException, SystemException {
		final String LOCATION = "executeViewNewsDetail_IR";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			if (StringUtils.isNotEmpty(newsURL)) {
				CurrentCompanyForQuote companyForQuote = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				if (!companyForQuote.getSymbol().equals("VND")) {
					companyForQuote = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					SessionManager.setSymbolCompany(companyForQuote.getSymbol());
				}

				final IfoNews inputIfoNews = model.getIfoNews() == null ? new IfoNews() : model.getIfoNews();
				final String newsId = newsURL.substring(newsURL.lastIndexOf("-") + 1, newsURL.length());
				inputIfoNews.setNewsId(Long.parseLong(newsId));
				inputIfoNews.setNewsType(model.getNewsType());

				final IfoNews ifoNews = newsInfoManager.getIfoNewsById(inputIfoNews);
				if (ifoNews != null) {
					try {
						newsInfoManager.updateNewsHit(inputIfoNews.getNewsId());
					} catch (Exception e) {
						logger.error(LOCATION + ":: Exception: " + e);
					}
				} else {
					throw new FunctionalException(LOCATION + "::Invalid parameter newsId = " + newsId);
				}
				model.setIfoNews(ifoNews);

				// set page title, desc, keywords
				String newsTitle = ifoNews.getNewsHeader().trim() + " - "
						+ (model.getNewsType().equals(DISCLOSURE_NEWSTYPE) ? "Disclosure" : model.getNewsType()) + " - VNDIRECT";
				model.setPageTitle(newsTitle);
				// ??? ifoNews ko co newsAbtract
				model.setPageDescription(ifoNews.getNewsHeader().trim());
				model.setPageKeywords(model.getNewsType());

				ArrayList<String> breadcrumbs = new ArrayList<String>();
				breadcrumbs.add(ifoNews.getNewsHeader());
				breadcrumbs.add(this.getText("br.vnd"));
				breadcrumbs.add("ttvndRoot");
				breadcrumbs.add(this.getText("br.ir"));
				breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");

				// get most VNDIRECT news
				if (MAC_VN_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("br.ir.news.disclosure"));
					breadcrumbs.add("/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
					searchObj.setListSymbols(new String[] { companyForQuote.getSymbol() });
					SearchResult<SearchIfoNews> mostViewsNews = getNewsInfoManager().getMostViewedNews(searchObj,
							new PagingInfo());
					logger.debug("most view result:" + mostViewsNews.size());
					for (int i = 0; i < mostViewsNews.size(); i++) {
						SearchIfoNews news = mostViewsNews.get(i);
						if(StringUtils.isEmpty(news.getNewsHeader()) || StringUtils.isEmpty(news.getNewsType())){
							mostViewsNews.remove(i);
							continue;
						}
						mostViewsNews.get(i).setUrlDetail(
								NewsUrlUtility.createUrl(QUAN_HE_CO_DONG_VNDIRECT_URL, EVENT_NEWSTYPE, news.getNewsHeader(),
										news.getNewsId(), ""));
					}
					model.setSearchMostView(mostViewsNews);
				} else if (DISCLOSURE_NEWSTYPE.equals(model.getNewsType())) {
					breadcrumbs.add(this.getText("br.ir.news.companyEvents"));
					breadcrumbs.add("/quan-he-co-dong-vndirect/tin-doanh-nghiep.shtml");

					final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
					searchObj.setListSymbols(new String[] { companyForQuote.getSymbol() });
					SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
					newsInfoManager.createNewsUrlWithPageUrl(mostViewsNews, QUAN_HE_CO_DONG_VNDIRECT_URL);
					model.setSearchMostView(mostViewsNews);
				}
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

	public String executeGetOtherNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetOtherNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			long newsId = model.getNewsId();
			String newsType = model.getType();
			String status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;
			String locale = I18NUtility.getCurrentLocale();
			PagingInfo pagingInfo = model.getPagingInfo();
			//If need loading news related to symbol, can pass symbol variable
			String symbol = StringUtils.EMPTY;
			SearchResult<SearchIfoNews> searchResult = newsInfoManager.getOtherRelateNews(newsId, newsType, status, locale,
					pagingInfo, symbol);

			if (searchResult != null) {
				request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
				newsInfoManager.createNewsUrl(searchResult);
				model.setSearchResult(searchResult);
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

	public String executeGetOtherInDayNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetOtherInDayNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "::BEGIN");

		try {

			HttpServletRequest request = ServletActionContext.getRequest();

			long newsId = model.getNewsId();
			String newsType = model.getType();
			String status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;
			PagingInfo pagingInfo = model.getPagingInfo();
			String locale = I18NUtility.getCurrentLocale();
			
			//If need loading news related to symbol, can pass symbol variable
			String symbol = StringUtils.EMPTY;
			
			SearchResult<SearchIfoNews> searchResult = newsInfoManager.getOtherRelateInDayNews(newsId, newsType, status, locale,
					pagingInfo, symbol);
			
			if (searchResult != null) {
				request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
				newsInfoManager.createNewsUrl(searchResult);
				model.setSearchResult(searchResult);
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

	/**
	 * Show detail information of a attachment
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeViewAttDetail() throws FunctionalException, SystemException {
		final String LOCATION = "executeViewAttDetail";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {

			model.setIfoNews(newsInfoManager.getAttachmentById(model.getNewsId()));

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	protected boolean validateNewsDate() {
		String StrFromNewsDate = model.getSearchIfoNews().getStrFromNewsDate();
		String StrToNewsDate = model.getSearchIfoNews().getStrToNewsDate();

		if ((StrFromNewsDate != null && StrFromNewsDate.trim().length() > 0 && !Validation.isValidDate(StrFromNewsDate))
				&& (StrToNewsDate != null && StrToNewsDate.trim().length() > 0 && !Validation.isValidDate(StrToNewsDate))) {
			model.setMessageFromNewsDate(this.getText("web.label.NewsAction.form.Messages.FromNewsDateFieldInvalid"));
			model.setMessageToNewsDate(this.getText("web.label.NewsAction.form.Messages.ToNewsDateFieldInvalid"));
			return false;
		} else if (StrToNewsDate != null && StrToNewsDate.trim().length() > 0 && !Validation.isValidDate(StrToNewsDate)) {
			model.setMessageToNewsDate(this.getText("web.label.NewsAction.form.Messages.ToNewsDateFieldInvalid"));
			return false;
		} else if (StrFromNewsDate != null && StrFromNewsDate.trim().length() > 0 && !Validation.isValidDate(StrFromNewsDate)) {
			model.setMessageFromNewsDate(this.getText("web.label.NewsAction.form.Messages.FromNewsDateFieldInvalid"));
			return false;
		}
		return true;
	}

	public String viewNewsOfVNDIRECT() throws Exception {
		final String LOCATION = "viewNewsOfVNDIRECT()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// set page title, desc, keywords
			model.setPageTitle(this.getText("vndirectNews.title"));
			model.setPageDescription(this.getText("vndirectNews.desc"));
			model.setPageKeywords(this.getText("vndirectNews.keywords"));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("home.topMenu.about.news"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			model.setBreadcrumbs(breadcrumbs);

			// set paging-info
			if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
				model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
			}
			model.getPagingInfo().setOffset(6);

			// build search conditions
			final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS);

			// get most views
			SearchResult<SearchIfoNews> mostViewsNews = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo());
			newsInfoManager.createNewsUrl(mostViewsNews);
			model.setSearchMostView(mostViewsNews);

			// get recent news
			searchObj.setStrFromNewsDate(!StringUtils.isEmpty(strFromDate) ? strFromDate : "");
			searchObj.setStrToNewsDate(!StringUtils.isEmpty(strToDate) ? strToDate : "");
			final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);
			model.setSearchResult(result);
			model.setPagingInfo((PagingInfo) result.getPagingInfo());

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getVnDirectNewsAjax() throws Exception {
		final String LOCATION = "getVnDirectNewsAjax()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// set paging-info
			if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
				model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
			}
			model.getPagingInfo().setOffset(6);

			// build search conditions
			final SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS);

			// get recent news
			searchObj.setStrFromNewsDate(!StringUtils.isEmpty(strFromDate) ? strFromDate : "");
			searchObj.setStrToNewsDate(!StringUtils.isEmpty(strToDate) ? strToDate : "");
			final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String viewShareholderNews() throws Exception {
		final String LOCATION = "viewShareholderNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetShareholderNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetShareholderNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.SHAREHOLDER_NEWS);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			model.setSearchResult(result);
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetSectorNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetSectorNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.PUBLIC_INFO);
			searchObj.setSectorGroupCode(model.getSectorGroupCode());

			List<String> types = new ArrayList<String>() {
				{
					// add(ServerConfig.getOnlineValue(Constants.ServerConfig.DataRef.ItemCodes.NewsType.NEWS));
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS));
				}
			};
			searchObj.setNewsTypeList(types);

			SearchResult<SearchIfoNews> result = newsInfoManager.getSectorNews(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);

			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetIndustryNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetIndustryNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.PUBLIC_INFO);
			searchObj.setIndustryGroupCode(model.getIndustryGroupCode());
			searchObj.setSectorGroupCode(model.getSectorGroupCode());
			List<String> types = new ArrayList<String>() {
				{
					add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS));
				}
			};
			searchObj.setNewsTypeList(types);
			SearchResult<SearchIfoNews> result = newsInfoManager.getSectorNews(searchObj, model.getPagingInfo());
			newsInfoManager.createNewsUrl(result);

			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String viewCommentariesNews() throws Exception {
		final String LOCATION = "viewCommentariesNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String viewBrokersNews() throws Exception {
		final String LOCATION = "viewBrokersNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String viewExpertsNews() throws Exception {
		final String LOCATION = "viewExpertsNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String viewVndCornerNews() throws Exception {
		final String LOCATION = "viewVndCornerNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String executeGetExpertsNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetExpertsNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchExpertNews(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			model.setSearchResult(result);
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeViewPMNewsDetail() throws FunctionalException, SystemException {
		final String LOCATION = "executeViewPMNewsDetail";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			IfoNews ifoNews;
			String newTypeKey = Constants.IServerConfig.DataRef.ItemCodes.NewsType.MARTKET_REVIEW;
			if (model.getIfoNews() == null || model.getIfoNews().getNewsId() <= 0) {

				SearchIfoNews searchObj = buildCommonCriteria(newTypeKey);

				ifoNews = newsInfoManager.getLatestMartketNews(searchObj);
				ifoNews.setNewsType(ServerConfig.getOnlineValue(newTypeKey));
				ifoNews = newsInfoManager.getIfoNewsById(ifoNews);

			} else {
				model.getIfoNews().setNewsType(ServerConfig.getOnlineValue(newTypeKey));
				ifoNews = newsInfoManager.getIfoNewsById(model.getIfoNews());
			}
			if (ifoNews == null) {
				throw new FunctionalException(LOCATION + "::Invalid parameter newsId = " + model.getNewsId());
			} else {
				try {
					newsInfoManager.updateNewsHit(model.getIfoNews().getNewsId());
				} catch (Exception e) {
					logger.error(LOCATION + ":: Exception: " + e);
				}
			}
			model.setIfoNews(ifoNews);
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String viewSIDisclosure() {
		final String LOCATION = "viewSIDisclosure";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp;
			if (StringUtils.isEmpty(currentSymbol)) {
				currentSymbol = SessionManager.getSymbolCompany();
			}
			if(currentSymbol == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(currentSymbol,
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
			breadcrumbs.add(this.getText("analysis.stockInfo.news.disclosure"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + currentComp.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.companyNews"));
			breadcrumbs.add("tin-doanh-nghiep/" + currentComp.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			final SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setNewsType(DISCLOSURE_NEWSTYPE);
			searchObj.setOrderByDate(true);
			searchObj.setListSymbols(new String[] { currentComp.getSymbol() });

			// most viewed. get only 6 news
			final SearchResult<SearchIfoNews> mostViewResult = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo(6));
			newsInfoManager.createNewsUrlWithPageUrl(mostViewResult, THONG_TIN_CO_PHIEU_URL);

			model.setSearchMostView(mostViewResult);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String viewSICompanyEvents() {
		final String LOCATION = "viewSICompanyEvents";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp;
			if (StringUtils.isEmpty(currentSymbol)) {
				currentSymbol = SessionManager.getSymbolCompany();
			}
			if(currentSymbol == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(currentSymbol,
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
			breadcrumbs.add(this.getText("analysis.stockInfo.news.relate"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + currentComp.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.companyNews"));
			breadcrumbs.add("tin-doanh-nghiep/" + currentComp.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			final SearchIfoNews searchObj = new SearchIfoNews();
			final ArrayList<String> newsTypeList = new ArrayList<String>();
			newsTypeList.add(EVENT_NEWSTYPE);
			newsTypeList.add(MAC_VN_NEWSTYPE);
			searchObj.setNewsTypeList(newsTypeList);
			searchObj.setOrderByDate(true);
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setListSymbols(new String[] { currentComp.getSymbol() });

			// most viewed. get only 6 news
			final SearchResult<SearchIfoNews> mostViewResult = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo(6));
			for (int i = 0; i < mostViewResult.size(); i++) {
				SearchIfoNews ifoNews = mostViewResult.get(i);
				if(StringUtils.isEmpty(ifoNews.getNewsHeader()) || StringUtils.isEmpty(ifoNews.getNewsType())){
					mostViewResult.remove(i);
					continue;
				}
				mostViewResult.get(i).setUrlDetail(
						NewsUrlUtility.createUrl(THONG_TIN_CO_PHIEU_URL, EVENT_NEWSTYPE, ifoNews.getNewsHeader(),
								ifoNews.getNewsId(), ""));
			}
			model.setSearchMostView(mostViewResult);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String viewIRDisclosure() {
		final String LOCATION = "viewIRDisclosure";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// SEO
			model.setPageTitle(this.getText("home.ir.title"));
			model.setPageKeywords(this.getText("home.ir.keywords"));

			final SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setNewsType(DISCLOSURE_NEWSTYPE);
			searchObj.setOrderByDate(true);
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			final CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(VND_SYMBOL, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, new PagingInfo(2));
			if (result.size() > 0) {
				String desc = result.get(0).getNewsHeader() + " | " + result.get(1).getNewsHeader();
				// setting page desc
				model.setPageDescription(desc);
			}

			// most viewed. get only 6 news
			searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
			searchObj.setListSymbols(new String[] { VND_SYMBOL });
			final SearchResult<SearchIfoNews> mostViewResult = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo(6));
			newsInfoManager.createNewsUrlWithPageUrl(mostViewResult, QUAN_HE_CO_DONG_VNDIRECT_URL);
			model.setSearchMostView(mostViewResult);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("br.ir.news.disclosure"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("br.ir.news"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml");
			model.setBreadcrumbs(breadcrumbs);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	public String viewIRCompanyEvents() {
		final String LOCATION = "viewIRCompanyEvents";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// SEO
			model.setPageTitle(this.getText("home.ir.companyEvents.title"));
			model.setPageKeywords(this.getText("home.ir.companyEvents.key"));

			final SearchIfoNews searchObj = new SearchIfoNews();
			final ArrayList<String> newsTypeList = new ArrayList<String>();
			newsTypeList.add(EVENT_NEWSTYPE);
			newsTypeList.add(MAC_VN_NEWSTYPE);
			searchObj.setNewsTypeList(newsTypeList);
			searchObj.setOrderByDate(true);
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setListSymbols(new String[] { VND_SYMBOL });
			final CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(VND_SYMBOL, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			final SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, new PagingInfo(2));
			if (result.size() > 0) {
				String desc = result.get(0).getNewsHeader() + " | " + result.get(1).getNewsHeader();
				// setting page desc
				model.setPageDescription(desc);
			}

			// most viewed. get only 6 news
			searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
			final SearchResult<SearchIfoNews> mostViewResult = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo(6));
			for (int i = 0; i < mostViewResult.size(); i++) {
				SearchIfoNews ifoNews = mostViewResult.get(i);
				if(StringUtils.isEmpty(ifoNews.getNewsHeader()) || StringUtils.isEmpty(ifoNews.getNewsType())){
					mostViewResult.remove(i);
					continue;
				}
				mostViewResult.get(i).setUrlDetail(
						NewsUrlUtility.createUrl(QUAN_HE_CO_DONG_VNDIRECT_URL, EVENT_NEWSTYPE, ifoNews.getNewsHeader(),
								ifoNews.getNewsId(), ""));
			}
			model.setSearchMostView(mostViewResult);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("br.ir.news.companyEvents"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("br.ir.news"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml");
			model.setBreadcrumbs(breadcrumbs);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + "::END");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		return SUCCESS;
	}

	/**
	 * Build the common criteria for NewsInfo search
	 * 
	 * @param newsTypeConstant
	 *            news type constant value
	 * @return <code>SearchIfoNews</code> instance
	 */
	protected SearchIfoNews buildCommonCriteria(String newsTypeConstant) {
		SearchIfoNews searchObj = new SearchIfoNews();
		searchObj.setOrderByDate(true);
		String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		searchObj.setLocale(locale);
		searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		if (StringUtils.isNotEmpty(newsTypeConstant))
			searchObj.setNewsType(ServerConfig.getOnlineValue(newsTypeConstant));
		// call when show in home
		buildModelShowInHome(searchObj);
		return searchObj;
	}

	/**
	 * Making the parameters return show in home
	 * 
	 * @param searchObj
	 */
	private void buildModelShowInHome(SearchIfoNews searchIfoNews) {
		if (model.isShowInHome() == false)
			return;

		if ("Home".equals(model.getShowin())) {
			if ("NOT-VNDS-NEWS".equals(model.getType())) {
				searchIfoNews.setIsHotNews(ServerConfig
						.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IS_HOT_NEWS));
			}
			model.setDisplay(model.getShowin());
		}
		String display = model.getDisplay();
		model.setDisplay("short".equalsIgnoreCase(display) ? "short" : display);
	}

	public String getNewsURL() {
		return newsURL;
	}

	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public String getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(String currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getCurrentSymbol() {
		return currentSymbol;
	}

	public void setCurrentSymbol(String currentSymbol) {
		this.currentSymbol = currentSymbol;
	}

	public String getStrFromDate() {
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	public String getStrToDate() {
		return strToDate;
	}

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
}
