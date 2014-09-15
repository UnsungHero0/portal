package vn.com.vndirect.web.tag;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.context.Context;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.i18n.Language;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.web.commons.utility.MapWebCache;
import vn.com.web.commons.utility.URLUtils;
import vn.com.web.commons.velocity.VelocityTemplate;

public class CurrentLangTag extends BasicVelocityTag implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(CurrentLangTag.class);
	protected String var;
	protected String scope = REQUEST;

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
			logger.error("doStartTag()", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SKIP_BODY;
	}

	@Override
	protected String getTemplatePath() {
		return this.pageContext.getServletContext().getRealPath(VelocityTemplate.getTempName(Constants.VelocityTemplate.MULTI_LANGUAGE_TAG_KEY));
	}

	@Override
	protected void populateContextParam(Context context) {
		final String LOCATION = "populateContextParam(context: " + context + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		Collection<Language> langs = I18NUtility.getLanguageSupported();
		Language curLang = I18NUtility.getCurrentLanguage(pageContext.getSession());

		if (curLang == null) {
			// get currentLang from request
			curLang = (Language) request.getAttribute(Language.class.getName());
			curLang = (curLang == null ? new Language(new Locale("vn"), "Viet Nam") : curLang);
		}

		// if (logger.isDebugEnabled()) logger.debug("+++>>> curLang:" + curLang + ", langs:" + langs);
		String previousUri = (String) request.getAttribute(Constants.RequestKeys.REQUEST_URL);
		String reqActionFormUrl = (String) request.getAttribute(Constants.RequestKeys.REQUEST_ACTION_FORM_URL);

		String strCaller = request.getParameter(Constants.RequestParams.CALLER);
		String strActionFor = request.getParameter(Constants.RequestParams.ACTION_FOR);
		String strForMarket = request.getParameter(Constants.RequestParams.FOR_MARKET);
		String uriParam = request.getParameter(Constants.RequestParams.URI_PARAM);

		/*
		 * The flowing data will be store in cache: - PREVIOUS_URI - caller - actionFor - forMarket
		 */
		MapWebCache webCache = new MapWebCache();
		webCache.addToCache(Constants.CacheKey4ChangeLang.PREVIOUS_URI, previousUri == null ? "" : previousUri);
		webCache.addToCache(Constants.CacheKey4ChangeLang.ACTION_FOR, strActionFor == null ? "" : strActionFor);
		webCache.addToCache(Constants.CacheKey4ChangeLang.CALLER, strCaller == null ? "" : strCaller);
		webCache.addToCache(Constants.CacheKey4ChangeLang.FOR_MARKET, strForMarket == null ? "" : strForMarket);
		webCache.addToCache(Constants.CacheKey4ChangeLang.URI_PARAM, uriParam == null ? "" : uriParam);

		if (curLang != null && langs != null) {
			context.put("cacheChangeLang", webCache.doCache());
			context.put("current_lang", curLang);
			context.put("langs", langs);
			context.put("contextPath", ServletActionContext.getRequest().getContextPath());

			// reqActionFormUrl = URLUtils.encodeURI(reqActionFormUrl, request);

			String strPageId = URLUtils.getPageUUID(request);
			reqActionFormUrl = URLUtils.addParam(reqActionFormUrl, URLUtils.PAGE_UUID_PARAM, strPageId);
			reqActionFormUrl = response.encodeRedirectURL(reqActionFormUrl);

			context.put("reqActionFormUrl", reqActionFormUrl);
			context.put("contextPath", request.getContextPath());
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var
	 *            the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
}
