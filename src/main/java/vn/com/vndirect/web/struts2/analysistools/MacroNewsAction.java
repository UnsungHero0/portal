package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.newsinfo.NewsModel;
import vn.com.vndirect.web.struts2.newsinfo.NewsAction;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

/**
 * @author Viet
 * 
 */
@SuppressWarnings("serial")
public class MacroNewsAction extends NewsAction {
	private static Logger logger = Logger.getLogger(MacroNewsAction.class);
	private String strFromDate;
	private String strToDate;

	private String currentIndex;

	public String getLatestNews() throws Exception {
		final String LOCATION = "getLatestNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			if (logger.isDebugEnabled())
				logger.debug("model.getNewsType(): " + model.getNewsType());
			if (StringUtils.isNotBlank(model.getNewsType())) {
				// set paging-info
				if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
					model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
				}

				// create new object search news
				SearchIfoNews searchObj = null;
				// create objectMostview
				SearchIfoNews searchMostViewNews = null;
				SearchResult<SearchIfoNews> result = null;

				if (model.getNewsType().equals(MAC_VN_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
					searchMostViewNews.setStrFromNewsDate(DateUtils.dateToString(
							DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
				} else if (model.getNewsType().equals(MAC_WORLD_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
					searchMostViewNews.setStrFromNewsDate(DateUtils.dateToString(
							DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
				} else if (model.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
					searchMostViewNews.setStrFromNewsDate(DateUtils.dateToString(
							DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
				} else if (model.getNewsType().equals(EXPERTS_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
					searchMostViewNews.setStrFromNewsDate(DateUtils.dateToString(
							DateUtils.addMonth(DateUtils.getCurrentDate(), -6), "dd-MMM-yy"));
				}

				// get 6 news
				model.getPagingInfo().setOffset(6);
				// search with date
				searchObj.setStrFromNewsDate(!StringUtils.isEmpty(strFromDate) ? strFromDate : "");
				searchObj.setStrToNewsDate(!StringUtils.isEmpty(strToDate) ? strToDate : "");
				result = getNewsInfoManager().searchNewsIfo(searchObj, model.getPagingInfo());
				getNewsInfoManager().createNewsUrl(result);
				
				model.setSearchResult(result);
				model.setPagingInfo((PagingInfo) result.getPagingInfo());

				// breadcrumbs
				ArrayList<String> breadcrumbs = new ArrayList<String>();
				if (model.getNewsType().equals(MAC_VN_NEWSTYPE)) {
					breadcrumbs.add(this.getText("analysis.news.localNews"));
				} else if (model.getNewsType().equals(MAC_WORLD_NEWSTYPE)) {
					breadcrumbs.add(this.getText("analysis.news.internationalNews"));
				} else if (model.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
					breadcrumbs.add(this.getText("analysis.news.disclosureInformation"));
				} else if (model.getNewsType().equals(EXPERTS_NEWSTYPE)) {
					breadcrumbs.add(this.getText("analysis.news.expertOpinions"));
				}
				breadcrumbs.add(this.getText("home.topMenu.analysis"));
				breadcrumbs.add("ttptRoot");
				breadcrumbs.add(this.getText("home.topMenu.analysis.news"));
				breadcrumbs.add("/tin-trong-nuoc.shtml");
				model.setBreadcrumbs(breadcrumbs);

				doSEOpage();
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Error: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		return SUCCESS;
	}

	public String getMostViewedNewsAjax() throws Exception {
		final String LOCATION = "getMostViewedNewsAjax()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			if (logger.isDebugEnabled())
				logger.debug("model.getNewsType(): " + model.getNewsType());
			if (StringUtils.isNotBlank(model.getNewsType())) {
				SearchIfoNews searchMostViewNews = null;
				if (model.getNewsType().equals(MAC_VN_NEWSTYPE)) {
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
				} else if (model.getNewsType().equals(MAC_WORLD_NEWSTYPE)) {
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
				} else if (model.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
				} else if (model.getNewsType().equals(EXPERTS_NEWSTYPE) || model.getNewsType().equals(EXPERT_NEWSTYPE)) {
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
				} else if (model.getNewsType().equals(VNDIRECT_NEWSTYPE)) {
					searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS);
				}

				// get most views (10 views in last 6 months)
				final SearchResult<SearchIfoNews> mostViewsNews = getNewsInfoManager().getMostViewedNews(searchMostViewNews,
						new PagingInfo());
				getNewsInfoManager().createNewsUrl(mostViewsNews);
				model.setSearchMostView(mostViewsNews);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Error: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		return SUCCESS;
	}

	public String ajaxLoadMoreNews() throws Exception {
		final String LOCATION = "ajaxLoadMoreNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			if (logger.isDebugEnabled())
				logger.debug("model.getNewsType(): " + model.getNewsType());
			if (StringUtils.isNotBlank(model.getNewsType())) {
				// set paging-info, currentIndex
				if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
					model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
				}

				// create new object search news
				SearchIfoNews searchObj = null;
				// create objectMostview
				SearchResult<SearchIfoNews> result = null;

				if (model.getNewsType().equals(MAC_VN_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
				} else if (model.getNewsType().equals(MAC_WORLD_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.WORLD_NEWS);
				} else if (model.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
				} else if (model.getNewsType().equals(EXPERTS_NEWSTYPE)) {
					searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EXPERT_NEWS);
				}

				// get 6 news
				model.getPagingInfo().setOffset(6);
				searchObj.setStrFromNewsDate(!StringUtils.isEmpty(strFromDate) ? strFromDate : "");
				searchObj.setStrToNewsDate(!StringUtils.isEmpty(strToDate) ? strToDate : "");
				result = getNewsInfoManager().searchNewsIfo(searchObj, model.getPagingInfo());
				getNewsInfoManager().createNewsUrl(result);
				model.setSearchResult(result);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Error: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		return SUCCESS;
	}

	public String getLatestDisclosureNews() throws Exception {
		final String LOCATION = "getLatestDisclosureNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			if (logger.isDebugEnabled())
				logger.debug("model.getNewsType(): " + model.getNewsType());
			if (StringUtils.isNotBlank(model.getNewsType())) {

				// set paging-info
				if (StringUtils.isNotBlank(currentIndex) && StringUtils.isNumeric(currentIndex.trim())) {
					model.getPagingInfo().setIndexPage(Integer.valueOf(currentIndex.trim()));
				}

				// create new object search news
				SearchIfoNews searchObj = null;
				// create objectMostview
				SearchIfoNews searchMostViewNews = null;
				SearchResult<SearchIfoNews> result = null;

				searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
				searchMostViewNews = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS);
				searchMostViewNews.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -6),
						"dd-MMM-yy"));

				// get most views (10 views)
				SearchResult<SearchIfoNews> mostViewsNews = getNewsInfoManager().getMostViewedNews(searchMostViewNews,
						new PagingInfo());
				getNewsInfoManager().createNewsUrl(mostViewsNews);
				model.setSearchMostView(mostViewsNews);

				// get 6 news
				model.getPagingInfo().setOffset(6);

				searchObj.setStrFromNewsDate(StringUtils.isNotEmpty(strFromDate) ? strFromDate : "");
				searchObj.setStrToNewsDate(StringUtils.isNotEmpty(strToDate) ? strToDate : "");

				result = getNewsInfoManager().searchNewsIfo(searchObj, model.getPagingInfo());
				getNewsInfoManager().createNewsUrl(result);
				model.setSearchResult(result);
				model.setPagingInfo((PagingInfo) result.getPagingInfo());
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Error: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();

		breadcrumbs.add(this.getText("analysis.news.disclosureInformation"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.news"));
		breadcrumbs.add("/tin-trong-nuoc.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
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

	public String getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(String currentIndex) {
		this.currentIndex = currentIndex;
	}

	private void doSEOpage() {
		if (model.getNewsType().equals(MAC_VN_NEWSTYPE)) {
			model.setPageTitle(this.getText("analysis.news.vn.title"));
			model.setPageKeywords(this.getText("analysis.news.vn.keyWords"));
		} else if (model.getNewsType().equals(MAC_WORLD_NEWSTYPE)) {
			model.setPageTitle(this.getText("analysis.news.world.title"));
			model.setPageKeywords(this.getText("analysis.news.world.keyWords"));
		} else if (model.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
			model.setPageTitle(this.getText("analysis.news.disclosure.title"));
			model.setPageKeywords(this.getText("analysis.news.disclosure.keyWords"));
		} else if (model.getNewsType().equals(EXPERTS_NEWSTYPE)) {
			model.setPageTitle(this.getText("analysis.news.experts.title"));
			model.setPageKeywords(this.getText("analysis.news.experts.keyWords"));
		}

		model.setPageDescription(this.getText("analysis.news.description"));
		model.setPageKeywords(this.getText("analysis.news.keyWords"));
	}
}
