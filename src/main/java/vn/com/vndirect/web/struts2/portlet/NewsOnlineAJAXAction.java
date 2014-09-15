package vn.com.vndirect.web.struts2.portlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.portlet.NewsOnlineAJAXModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class NewsOnlineAJAXAction extends ActionSupport implements ModelDriven<NewsOnlineAJAXModel>, ServletRequestAware {
	/* class logger */
	private static Logger logger = Logger.getLogger(NewsOnlineAJAXAction.class);

	/* data model */
	private NewsOnlineAJAXModel model = new NewsOnlineAJAXModel();

	@Autowired
	private INewsInfoManager newsInfoManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/* Request: note implements ServletRequestAware */
	private HttpServletRequest request;

	public NewsOnlineAJAXModel getModel() {
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

	/**
	 * Getting the news base on the criteria from model.
	 */
	@SuppressWarnings("unchecked")
	public String executeGetNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			if (model.getType() != null) {
				SearchIfoNews searchIfoNews = new SearchIfoNews();
				// Query news more than one news types
				if (model.getType().indexOf(",") != -1) {
					Collection<String> newsTypeList = new ArrayList<String>();
					StringTokenizer st = new StringTokenizer(model.getType(), ",");
					while (st.hasMoreTokens()) {
						newsTypeList.add(st.nextToken());
					}
					searchIfoNews.setNewsTypeList(newsTypeList);
				} else {
					searchIfoNews.setNewsType(model.getType());
				}
				searchIfoNews
				        .setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);

				if (!("VNMarket".equalsIgnoreCase(model.getShowin()) || "Home".equals(model.getShowin()))) {
					CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
					// Map<String, Object> session =
					// ActionContext.getContext().getSession();

					searchIfoNews.setCompanyId(currentComp.getCompanyId());
					searchIfoNews.setStrSymbol(currentComp.getSymbol());
				}

				if ("Home".equals(model.getShowin())) {
					if ("NOT-VNDS-NEWS".equals(model.getType())) {
						searchIfoNews.setIsHotNews("Y");
					}
					model.setDisplay(model.getShowin());
				}
				searchIfoNews.setLocale(I18NUtility.getCurrentLocale());
				searchIfoNews.setNumberItem(model.getNumberItem());

				if (model.getListSymbol() != null && model.getListSymbol().length() > 0) {
					StringTokenizer token = new StringTokenizer(model.getListSymbol(), "[],; ");
					List<String> temp = new ArrayList<String>();
					while (token.hasMoreElements()) {
						temp.add((String) token.nextElement());
					}
					if (temp.size() > 0) {
						searchIfoNews.setListSymbols((String[]) temp.toArray(new String[temp.size()]));
					}
				}

				// +++ default is check in cache
				boolean checkInCache = false;
				if ("false".equalsIgnoreCase(model.getCheckInCache())) {
					checkInCache = false;
				}
				searchIfoNews.setCheckInCache(checkInCache);
				// ---
				searchIfoNews.setPagingIndex(model.getPagingIndex());
				SearchResult result = newsInfoManager.searchNewsIfo(searchIfoNews, model.getPagingInfo());

				model.setListIfoNews(result == null ? new ArrayList() : result);

				request.setAttribute(Constants.Paging.DEFAULT_KEY, result);

				String display = model.getDisplay();
				model.setDisplay("short".equalsIgnoreCase(display) ? "short" : display);
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
