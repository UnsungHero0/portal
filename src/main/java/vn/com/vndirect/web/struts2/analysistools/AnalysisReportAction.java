/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.analysistools.AnalysisReportModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.MapWebCache;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author minh.nguyen
 * 
 */
public class AnalysisReportAction extends ActionSupport implements ModelDriven<AnalysisReportModel>, Preparable, Serializable {
	private static final int PAGING_INFO_OFFSET = 10;
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(AnalysisReportAction.class);
	private AnalysisReportModel model = new AnalysisReportModel();

	private List<String> types = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2849252542930397016L;

		{
			add(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS);
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STRATEGIC));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_COMPANY));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_QUARTER));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_RISKALERT));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_QUICKREPORT));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STASTIC));
		}
	};
	private String locale;
	private String status;

	@Autowired
	private INewsInfoManager newsInfoManager;

	public String getMarketInsights() throws FunctionalException {
		final String location = "getMarketInsights()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}

		try {
			final String _newsType = Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS;

			// get newsId from url
			long newsId = 0L;
			String newsUrl = model.getNewsUrl();
			if (StringUtils.isNotEmpty(newsUrl)) {
				newsId = Long.valueOf(newsUrl.substring(newsUrl.lastIndexOf('-') + 1, newsUrl.length()));
			}

			// get first newest market insight news to display
			final SearchIfoNews _searchIfoNews = new SearchIfoNews();
			_searchIfoNews.setStatus(status);
			_searchIfoNews.setNewsType(_newsType);
			_searchIfoNews.setLocale(locale);
			_searchIfoNews.setNewsId(newsId);

			model.getPagingInfo().setOffset(PAGING_INFO_OFFSET);
			final SearchResult<SearchIfoNews> result = newsInfoManager
					.getMartketNews(_searchIfoNews, null, model.getPagingInfo());

			if (result != null && result.size() > 0) {
				// re-assign newsId if not yet assign from the URL
				if (newsId == 0) {
					newsId = result.get(0).getNewsId();
				}
				//Create url for news, remove news if header or type is null
				newsInfoManager.createNewsUrl(result);
				model.setSearchResult(result);
			}

			final IfoNews _inputIfoNews = new IfoNews();
			_inputIfoNews.setNewsType(_newsType);
			_inputIfoNews.setNewsId(newsId);

			final IfoNews ifoNews = newsInfoManager.getIfoNewsById(_inputIfoNews);

			// update news hit
			if (ifoNews != null) {
				try {
					newsInfoManager.updateNewsHit(newsId);
				} catch (Exception e) {
					logger.error(location + ":: Exception: " + e);
				}
			} else {
				throw new FunctionalException(location + "::Invalid parameter newsId = " + newsId);
			}
			model.setIfoNews(ifoNews);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(ifoNews.getNewsHeader());
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.news"));
			breadcrumbs.add("/tin-trong-nuoc.shtml");
			model.setBreadcrumbs(breadcrumbs);

			// SEO
			model.setPageTitle(ifoNews.getNewsHeader().trim() + " - DailyReport - VNDIRECT");
			model.setPageDescription(ifoNews.getNewsHeader().trim());
			model.setPageKeywords(model.getNewsType());
		} catch (SystemException e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}

		return SUCCESS;
	}

	public String getMostViewMarketInsightNewsAjax() {
		final String location = "getMostViewMarketInsightNewsAjax()";
		boolean isSplited = false;
		final int limitWords = 13;
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			SearchIfoNews _ifoNews = new SearchIfoNews();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -3);
			cal.set(Calendar.HOUR_OF_DAY, 0);

			_ifoNews.setFromNewsDate(cal.getTime());
			_ifoNews.setLocale(locale);
			_ifoNews.setStatus(status);

			model.getPagingInfo().setOffset(PAGING_INFO_OFFSET);
			_ifoNews.setNewsType(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS);
			SearchResult<SearchIfoNews> resultMostView = newsInfoManager.getRelatedVideoNews(_ifoNews, model.getPagingInfo());

			if (resultMostView != null && resultMostView.size() > 0) {
				newsInfoManager.processNewsForView(resultMostView);
			}

			model.setSearchMostView(resultMostView);
		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}

		return SUCCESS;
	}

	public String ajaxLoadMoreVideos() {
		final String location = "ajaxLoadMoreNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			// set data for search object
			String newType = model.getNewsType();
			if (StringUtils.isEmpty(newType)) {
				newType = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STRATEGIC);
			}

			SearchIfoNews news = new SearchIfoNews();
			news.setStatus(status);
			news.setNewsType(newType);
			news.setLocale(locale);

			// get 5 videos
			model.getPagingInfo().setOffset(5);

			SearchResult<SearchIfoNews> result = null;

			result = newsInfoManager.getMartketNews(news, model.getDate(), model.getPagingInfo());

			newsInfoManager.createNewsUrl(result);
			
			model.setSearchResult(result);

		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}

		return SUCCESS;
	}

	/**
	 * Get news list by NewsType
	 * 
	 * @return News list
	 */
	public String getMarketNews() {
		final String location = "getMarketNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			// set data for search object
			String newType = model.getNewsType();
			if (StringUtils.isEmpty(newType)) {
				newType = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STRATEGIC);
			}

			SearchIfoNews news = new SearchIfoNews();
			news.setStatus(status);
			news.setNewsType(newType);
			news.setLocale(locale);

			model.setSearchResult(newsInfoManager.getMartketNews(news, model.getDate(), model.getPagingInfo()));

			// grab data from database
			/*
			 * if
			 * (ServerConfig.getOnlineValue(Constants.ServerConfig.DataRef.ItemCodes
			 * .NewsType.VNDS_DAILY_NEWS).equals(newType) ||
			 * ServerConfig.getOnlineValue
			 * (Constants.ServerConfig.DataRef.ItemCodes
			 * .NewsType.VNDS_WEEKLY_NEWS).equals(newType) ||
			 * ServerConfig.getOnlineValue
			 * (Constants.ServerConfig.DataRef.ItemCodes
			 * .NewsType.VNDS_MONTHLY_NEWS).equals(newType)) { // process for
			 * ifo_news
			 * model.setSearchResult(newsInfoManager.getMartketNews(news,
			 * model.getDate(), model.getPagingInfo())); } else { // process for
			 * ifo_attachment
			 * model.setSearchResult(newsInfoManager.getMarketNews(newType,
			 * model.getDate(), locale, status, model.getPagingInfo())); }
			 */

		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * Get news list by NewsType = VNDS_Strategic || VNDS_Company ||
	 * VNDS_Quarter || VNDS_RiskAlert || VNDS_QuickReport || VNDS_Stastic
	 * 
	 * @return News list
	 */
	public String getLatestReport() {
		final String location = "getLatestReport()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			SearchIfoNews ifoNews = new SearchIfoNews();
			ifoNews.setLocale(locale);
			ifoNews.setStatus(status);
			ifoNews.setNewsTypeList(types);
			SearchResult<SearchIfoNews> result = newsInfoManager.getAttachments(ifoNews, model.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * Get most viewed news list by NewsType = VNDS_Strategic || VNDS_Company ||
	 * VNDS_Quarter || VNDS_RiskAlert || VNDS_QuickReport || VNDS_Stastic
	 * 
	 * @return
	 */
	public String getMostViewedReports() {
		final String location = "getMostViewedReports()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			SearchIfoNews ifoNews = new SearchIfoNews();
			ifoNews.setLocale(locale);
			ifoNews.setStatus(status);
			ifoNews.setNewsTypeList(types);
			ifoNews.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -3), "dd-MMM-yy"));
			SearchResult<SearchIfoNews> result = newsInfoManager.getMostViewedAttachments(ifoNews, model.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * Get most viewed video by NewsType = DailyReport
	 * 
	 * @return
	 */
	// public String getRelatedVideos() {
	// final String location = "getRelatedVideos()";
	// if (logger.isDebugEnabled()) {
	// logger.debug(location + ":: BEGIN");
	// }
	// try {
	// SearchIfoNews ifoNews = new SearchIfoNews();
	// ifoNews.setLocale(locale);
	// ifoNews.setStatus(status);
	// logger.error(location + ":: model.getNews(): " + model.getNews());
	// if (model.getNews() != null && model.getNews().getNewsId() != null) {
	// ifoNews.setNewsId(model.getNews().getNewsId());
	// }
	// // ifoNews.setNewsTypeList(types);
	// ifoNews.setNewsType(ServerConfig.getOnlineValue(Constants.ServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS));
	//
	// SearchResult<SearchIfoNews> result =
	// newsInfoManager.getRelatedVideoNews(ifoNews, model.getPagingInfo());
	// model.setSearchResult(result);
	// } catch (Exception e) {
	// logger.error(location + ":: Exception: " + e);
	// Utilities.processErrors(this, e);
	// }
	// if (logger.isDebugEnabled()) {
	// logger.debug(location + ":: END");
	// }
	// return SUCCESS;
	// }

	public AnalysisReportModel getModel() {
		return model;
	}

	/**
	 * 
	 * @param attachment
	 * @return
	 */
	public String getURL(String url) {
		MapWebCache webCache = new MapWebCache();
		webCache.addToCache("catParam", (StringUtils.isEmpty(category) ? "newsAttch" : category));
		webCache.addToCache("uriParam", url);
		webCache.addToCache("authUs", "vnds");
		webCache.addToCache("authPw", "vnds");
		webCache.addToCache("authKey", "vnds");
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNDS_RESOURCE_DOWNLOADER) + "?_ResourceInfo="
				+ webCache.doCache();
	}

	private String category;

	public void setCategory(String category) {
		this.category = category;
	}

	private String filename;

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String inputPath;

	public void setInputPath(String value) {
		inputPath = value;
	}

	public String getFilename() {
		if (!StringUtils.isEmpty(filename)) {
			return filename;
		}

		if (!StringUtils.isEmpty(inputPath)) {
			return inputPath.substring(inputPath.indexOf('/') + 1);
		}
		return "";
	}

	private int length;

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	public String download() {
		final String location = "download()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			URL url = new URL(getURL(inputPath));
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			byte[] bytes = IOUtils.toByteArray(in);
			length = bytes.length;
			inputStream = new ByteArrayInputStream(bytes);
		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}
		return SUCCESS;
	}

	private InputStream inputStream;

	public InputStream getInputStream() throws Exception {
		return inputStream;
	}

	public void prepare() throws Exception {
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;
	}

	/**
	 * Ajax action which update hit field in ifo_attachments table
	 * 
	 * @return "success" will be returned
	 * @throws Exception
	 */
	public String hit() {
		final String location = "hit()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}
		try {
			newsInfoManager.updateAttachmentsHit(NumberUtils.toLong(model.getAttachmentId()));
		} catch (Exception e) {
			logger.error(location + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}
		return SUCCESS;
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.news.commentary.title"));
		model.setPageDescription(this.getText("analysis.news.description"));
		model.setPageKeywords(this.getText("analysis.news.commentary.keyWords"));
	}

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}
}
