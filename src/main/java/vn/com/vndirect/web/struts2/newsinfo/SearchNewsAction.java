package vn.com.vndirect.web.struts2.newsinfo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.newsinfo.NewsModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SearchNewsAction extends ActionSupport implements ModelDriven<NewsModel> {

	private static final long serialVersionUID = -8679966305032927642L;
	private static Logger logger = Logger.getLogger(SearchNewsAction.class);

	private NewsModel model = new NewsModel();
	private String currentIndex;

	@Autowired
	private INewsInfoManager newsInfoManager;

	public NewsModel getModel() {
		return model;
	}

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	public String viewSearchNews() throws Exception {
		final String LOCATION = "viewSearchNews()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			// SEO
			model.setPageTitle(this.getText("common.search.news.title"));

			final HttpServletRequest request = ServletActionContext.getRequest();
			final SearchIfoNews searchIfoNews = new SearchIfoNews();

			final String keyWord = model.getKeyWord() == null ? "" : model.getKeyWord();

			searchIfoNews.setNewsHeader(keyWord);
			searchIfoNews.setNewsAbstract(keyWord);
			searchIfoNews.setLocale(I18NUtility.getCurrentLanguage(request.getSession()).getCode());
			searchIfoNews.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);

			final PagingInfo pagingInfo = model.getPagingInfo();

			if (StringUtils.isNotEmpty(currentIndex)) {
				pagingInfo.setIndexPage(Integer.parseInt(currentIndex));
			}
			pagingInfo.setOffset(6);

			final SearchResult<IfoNews> searchResult = newsInfoManager.searchNews(searchIfoNews, pagingInfo);

			if (searchResult != null) {
				request.setAttribute(Constants.Paging.DEFAULT_KEY, searchResult);
				model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
				for (int i = 0; i < searchResult.size(); i++) {
					if(StringUtils.isEmpty(searchResult.get(i).getNewsHeader()) 
					   || StringUtils.isEmpty(searchResult.get(i).getNewsType())){
						searchResult.remove(i);
						continue;
					}
					final IfoNews ifoNews = newsInfoManager.getIfoNewsById(searchResult.get(i));
					searchResult.get(i).setNewsDate(ifoNews.getNewsDate());
					searchResult.get(i).setNewsResource(ifoNews.getNewsResource() == null ? "" : ifoNews.getNewsResource());
					searchResult.get(i).setUrlDetail(
							NewsUrlUtility.createUrl(ifoNews.getNewsType() == null ? "" : ifoNews.getNewsType(),
									ifoNews.getNewsHeader(), ifoNews.getNewsId(), ""));
				}
				model.setSearchResult(searchResult);
			}
			// add SearchIfoNews object to cache
			model.addToCache(SearchIfoNews.class.getName(), searchIfoNews);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("common.search.news.text"));
			model.setBreadcrumbs(breadcrumbs);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	public String getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(String currentIndex) {
		this.currentIndex = currentIndex;
	}
}
