package vn.com.vndirect.web.struts2.portlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.struts2.portlet.LastestMarketNewsAJAXModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LastestMarketNewsAJAXAction extends ActionSupport implements ModelDriven<LastestMarketNewsAJAXModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(LastestMarketNewsAJAXAction.class);

	/* data model */
	private LastestMarketNewsAJAXModel model = new LastestMarketNewsAJAXModel();

	@Autowired
	private INewsInfoManager newsInfoManager;

	public LastestMarketNewsAJAXModel getModel() {
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
	public String executeGetLastestNews() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetLastestNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			IfoNews ifoNews = new IfoNews();
			ifoNews.setNewsType(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.MARKET_NEWS));
			ifoNews.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			// TODO Hardcode locale
			ifoNews.setLocale(I18NUtility.getCurrentLocale());
			model.setIfoNews(newsInfoManager.getCommentsMarketNews(ifoNews));

			// Removed <p> tag of new abstract
			String newsAbstract = model.getIfoNews().getNewsAbstract();
			if (StringUtils.isNotBlank(newsAbstract)) {
				if (newsAbstract.indexOf("<p") == 0) {
					newsAbstract = newsAbstract.substring(newsAbstract.indexOf(">") + 1, newsAbstract.length());
				}
				if (newsAbstract.indexOf("</p>") > 0) {
					newsAbstract = newsAbstract.replaceAll("</p>", "");
				}

				model.getIfoNews().setNewsAbstract(newsAbstract);
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
}
