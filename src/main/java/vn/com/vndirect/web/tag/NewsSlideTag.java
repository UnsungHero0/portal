package vn.com.vndirect.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.context.Context;

import vn.com.vndirect.business.NewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.velocity.VelocityTemplate;

@SuppressWarnings("serial")
public class NewsSlideTag extends BasicVelocityTag implements IWebTag {
	private static Logger logger = Logger.getLogger(NewsSlideTag.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			pageContext.getOut().print(generate());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("doStartTag()", e);
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SKIP_BODY;
	}

	@Override
	protected String getTemplatePath() {
		return this.pageContext.getServletContext().getRealPath(
				VelocityTemplate.getTempName(Constants.VelocityTemplate.SLIDE_NEWS));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void populateContextParam(Context context) {
		final String LOCATION = "populateContextParam(context: " + context + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {

			SearchIfoNews searchObj = buildCommonCriteria(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS);

			NewsInfoManager newsInfoManager = (NewsInfoManager) SpringUtils.getBean(Constants.SpringBeanNames.NEWS_INFO_MANAGER);
			SearchResult<SearchIfoNews> result = newsInfoManager.searchNewsIfo(searchObj, new PagingInfo(5));
			newsInfoManager.createNewsUrl(result);
			if (result != null) {
				context.put("vndsNews", result);
				HttpServletRequest request = ServletActionContext.getRequest();
				context.put("contextPath", request.getContextPath());
			}

		} catch (Exception e) {
			logger.error("NewsSlideTag", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected SearchIfoNews buildCommonCriteria(String newsTypeConstant) {
		SearchIfoNews searchObj = new SearchIfoNews();
		searchObj.setOrderByDate(true);
		String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		searchObj.setLocale(locale);
		searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		if (StringUtils.isNotEmpty(newsTypeConstant))
			searchObj.setNewsType(ServerConfig.getOnlineValue(newsTypeConstant));

		return searchObj;
	}
}
