package vn.com.vndirect.web.struts2.portlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.portlet.MarketNewsAJAXModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MarketNewsAJAXAction extends ActionSupport implements ModelDriven<MarketNewsAJAXModel> {
	/* class logger */
	private static Logger logger = Logger.getLogger(MarketNewsAJAXAction.class);

	/* data model */
	private MarketNewsAJAXModel model = new MarketNewsAJAXModel();

	@Autowired
	private INewsInfoManager newsInfoManager;

	/**
	 * Setting the manager for NewsOnline
	 * 
	 * @param newsInfoManager
	 */
	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	public MarketNewsAJAXModel getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	public String executeMarketNewsHome() throws FunctionalException, SystemException {
		final String LOCATION = "executeMarketOverviewHome";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			SearchIfoNews searchObj = model.getSearchIfoNews();
			searchObj.setOrderByDate(true);
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setNewsType(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.MARKET_NEWS));

			searchObj.setLocale(I18NUtility.getCurrentLocale());
			searchObj.setNumberItem(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_PER_PAGE)));

			SearchResult result = newsInfoManager.searchMartketNews(searchObj, model.getPagingInfo());
			logger.debug("result:" + result.size());
			// request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setSearchResult(result);
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
