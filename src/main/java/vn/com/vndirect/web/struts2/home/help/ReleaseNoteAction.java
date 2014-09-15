package vn.com.vndirect.web.struts2.home.help;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.struts2.newsinfo.NewsModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReleaseNoteAction extends ActionSupport implements ModelDriven<NewsModel> {
	
	private static Logger logger = Logger.getLogger(ReleaseNoteAction.class);
	
	protected static final String DISCLOSURE_NEWSTYPE = "Disclousure";
	private String newsURL;
	
	@Autowired
	private INewsInfoManager newsInfoManager;
	
	private NewsModel model = new NewsModel();
	
	@Override
	public NewsModel getModel() {
		return model;
	}
	
	public String releaseNoteDetails() throws FunctionalException, SystemException{
		final String LOCATION = "release note details";
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
				model.setIfoNews(ifoNews);

				// SEO
				model.setPageTitle(this.getText("releaseNote.page.title"));
				model.setPageDescription(this.getText("releaseNote.page.desc"));
				model.setPageKeywords(this.getText("releaseNote.page.keys"));
				// breadcrumbs
				ArrayList<String> breadcrumbs = new ArrayList<String>();
				breadcrumbs.add(this.getText("br.help.releaseNote2"));
				breadcrumbs.add(this.getText("br.vnd"));
				breadcrumbs.add("ttvndRoot");
				breadcrumbs.add(this.getText("br.help"));
				breadcrumbs.add("/tro-giup/hoi-dap-huong-dan.shtml");
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
	
	/*
	 * GETTERS AND SETTERS
	 */
	public INewsInfoManager getNewsInfoManager() {
		return newsInfoManager;
	}


	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}


	public String getNewsURL() {
		return newsURL;
	}


	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}
}
