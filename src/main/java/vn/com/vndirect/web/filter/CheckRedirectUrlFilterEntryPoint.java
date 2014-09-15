package vn.com.vndirect.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.web.tag.URLMappingTag;
import vn.com.vndirect.web.tag.URLTag;
import vn.com.web.commons.urlpattern.URLPattern;
import vn.com.web.commons.urlpattern.URLPatterns;
import vn.com.web.commons.utility.Utilities;

public class CheckRedirectUrlFilterEntryPoint {
	private static Logger logger = Logger.getLogger(CheckRedirectUrlFilterEntryPoint.class);

	public interface ReqParam {
		String REDIRECT_KEY_PARAM = "code";
		String FORWARD_KEY_PARAM = "fwd";
		String TICKET_PARAM = "ticket";

		String VIEW_FULL_WEB_PAGE = "vf";
	}

	// public interface MobileParam {
	// String MOBILE_DETECTOR = "mobileDetector";
	// String IS_MOBILE_DEVICE = "isMobileDevice";
	// }

	/**
	 *
	 */
	private String fwdSecUrl = "/home/ForwardSecUrl.shtml";

	private RequestCache requestCache = new HttpSessionRequestCache();
	// private final static String IS_SAVE_FORWARDSEC_KEY = "IS_SAVE_FORWARDSEC_KEY";

	protected RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private boolean applyForCheckFwd = false;
	private boolean applyForCheckRedirect = false;

	private final static String URL_REDIRECT_PATTERN_NAME = "checkRedirectUrl";
	private final static String URL_FORWARD_PATTERN_NAME = "checkForwardUrl";

	private String redirectUrlPatternName = URL_REDIRECT_PATTERN_NAME;
	private String forwardUrlPatternName = URL_FORWARD_PATTERN_NAME;

	private URLPattern objRedirectUrlPattern = null;
	private URLPattern objForwardUrlPattern = null;

	private boolean applyDetectMobile = true;
	private String mobileUrl = null;

	/**
	 * 
	 * @return
	 */
	private URLPattern getRedirectURLPattern() {
		// +++ get url pattern
		URLPatterns urlPatterns = URLPatterns.getInstance();
		if (objRedirectUrlPattern == null) {
			objRedirectUrlPattern = urlPatterns.getURLPattern(redirectUrlPatternName);
			if (objRedirectUrlPattern == null) {
				// get default
				objRedirectUrlPattern = urlPatterns.getURLPattern(URL_REDIRECT_PATTERN_NAME);
			}
		}
		// ---
		return objRedirectUrlPattern;
	}

	/**
	 * 
	 * @return
	 */
	private URLPattern getForwardURLPattern() {
		// +++ get url pattern
		URLPatterns urlPatterns = URLPatterns.getInstance();
		if (objForwardUrlPattern == null) {
			objForwardUrlPattern = urlPatterns.getURLPattern(forwardUrlPatternName);
			if (objForwardUrlPattern == null) {
				// get default
				objForwardUrlPattern = urlPatterns.getURLPattern(URL_FORWARD_PATTERN_NAME);
			}
		}
		// ---
		return objForwardUrlPattern;
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final String LOCATION = "doFilter(...)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		boolean isForward = false;

		String fwUrl = null;

		// +++ check view full webpage
		String strViewFull = request.getParameter(ReqParam.VIEW_FULL_WEB_PAGE);
		boolean isViewFull = false;
		if (strViewFull != null && strViewFull.trim().length() > 0) {
			isViewFull = ("t".equalsIgnoreCase(strViewFull) || "true".equalsIgnoreCase(strViewFull));
			request.getSession().setAttribute(ReqParam.VIEW_FULL_WEB_PAGE, Boolean.valueOf(isViewFull));
		} else {
			Boolean objViewFull = (Boolean) request.getSession().getAttribute(ReqParam.VIEW_FULL_WEB_PAGE);
			isViewFull = (objViewFull == null ? false : objViewFull.booleanValue());
		}

		// +++ process multi-language
		String locale = I18NUtility.getCurrentLocale(request.getSession());
		String localeReq = req.getParameter("locale");
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: locale: " + locale + ", localeReq:" + localeReq);
		if (StringUtils.isNotBlank(locale) && StringUtils.isNotBlank(localeReq) && !locale.equalsIgnoreCase(localeReq)) {
			I18NUtility.setCurrentLanguageByCode(localeReq, request.getSession());
		}
		// ---

		// +++ check mobile's browser
		/*
		 * UAgentInfo mobileDetector = (UAgentInfo)request.getSession().getAttribute(MobileParam.MOBILE_DETECTOR); if(mobileDetector == null) { String userAgent = request.getHeader("User-Agent");
		 * String httpAccept = request.getHeader("Accept"); mobileDetector = new UAgentInfo(userAgent, httpAccept); //add to session request.getSession().setAttribute(MobileParam.MOBILE_DETECTOR,
		 * mobileDetector); } if(!isViewFull && mobileDetector != null) { if(applyDetectMobile && mobileDetector.detectMobileQuick() && mobileUrl != null && mobileUrl.length()>0 ) { fwUrl = mobileUrl;
		 * isForward = true; } else { request.setAttribute(MobileParam.MOBILE_DETECTOR, mobileDetector); request.setAttribute(MobileParam.IS_MOBILE_DEVICE, mobileDetector.detectMobileQuick()); } }
		 */
		// ---

		if (isForward == false) {
			try {
				String requestUri = Utilities.FormatURL.getRequestUri(request);
				int pathParamIndex = requestUri.indexOf(59);
				if (pathParamIndex > 0) {
					requestUri = requestUri.substring(0, pathParamIndex);
				}
				URLPattern redirectUrlPattern = this.getRedirectURLPattern();
				URLPattern fwdUrlPattern = this.getForwardURLPattern();

				// ++++ check redirect
				if (applyForCheckRedirect && isForward == false && (redirectUrlPattern != null && redirectUrlPattern.matches(requestUri))) {
					String redirectCode = request.getParameter(ReqParam.REDIRECT_KEY_PARAM);
					isForward = true;

					boolean includeReqParams = true;
					final String removeParams = "code;ticket;";
					fwUrl = URLMappingTag.process(redirectCode, request, includeReqParams, removeParams);
					fwUrl = (fwUrl == null ? redirectUrlPattern.getDefaultUrl() : fwUrl);

					if (logger.isDebugEnabled())
						logger.debug(LOCATION + ":: redirectCode: " + redirectCode + ", fwUrl:" + fwUrl);
				}

				// +++ check forward
				if (applyForCheckFwd && isForward == false && (fwdUrlPattern != null && fwdUrlPattern.matches(requestUri))) {
					String forwadUrl = request.getParameter(ReqParam.FORWARD_KEY_PARAM);
					String strTicket = request.getParameter(ReqParam.TICKET_PARAM);
					isForward = true;

					// +++ check forward
					if (forwadUrl != null && forwadUrl.length() > 0) {
						// String isSaveForwardKey = (String)request.getSession().getAttribute(IS_SAVE_FORWARDSEC_KEY);
						// if(isSaveForwardKey == null && strTicket != null && strTicket.length() > 0) {
						if (strTicket != null && strTicket.length() > 0) {
							boolean includeReqParams = true;
							final String removeParams = "ticket;";
							fwUrl = URLTag.processParam(fwdSecUrl, request, includeReqParams, removeParams);

							// final String removeParams= "";
							// fwUrl = URLTag.processParam(fwdSecUrl, request, includeReqParams, removeParams);

							// requestCache.saveRequest(request, response);

							// request.getSession().setAttribute(IS_SAVE_FORWARDSEC_KEY, "OK");
						} else {
							boolean includeReqParams = true;
							final String removeParams = "ticket;fwd;";
							fwUrl = URLTag.processParam(forwadUrl, request, includeReqParams, removeParams);
							fwUrl = (fwUrl == null ? fwdUrlPattern.getDefaultUrl() : fwUrl);

							// request.getSession().removeAttribute(IS_SAVE_FORWARDSEC_KEY);
						}
					} else {
						// +++ forward to default
						fwUrl = fwdUrlPattern.getDefaultUrl();
					}

					if (logger.isDebugEnabled())
						logger.debug(LOCATION + ":: strTicket: " + strTicket + ", forwadUrl: " + forwadUrl + ", fwUrl:" + fwUrl);
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled())
					logger.debug(LOCATION, e);
			}
		}

		if (isForward && fwUrl != null) {
			// if(URLUtils.checkExt(fwUrl, ".shtml")) {
			// fwUrl = Utilities.FormatURL.encodeURI(fwUrl, request);
			// }
			redirectStrategy.sendRedirect(request, new SendRedirectOverloadedResponse(request, response), fwUrl);
		} else {
			chain.doFilter(request, new SendRedirectOverloadedResponse(request, response));
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	/**
	 * @return the fwdSecUrl
	 */
	public String getFwdSecUrl() {
		return fwdSecUrl;
	}

	/**
	 * @param fwdSecUrl
	 *            the fwdSecUrl to set
	 */
	public void setFwdSecUrl(String fwdSecUrl) {
		this.fwdSecUrl = fwdSecUrl;
	}

	/**
	 * @return the requestCache
	 */
	public RequestCache getRequestCache() {
		return requestCache;
	}

	/**
	 * @param requestCache
	 *            the requestCache to set
	 */
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	/**
	 * @return the redirectStrategy
	 */
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	/**
	 * @param redirectStrategy
	 *            the redirectStrategy to set
	 */
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	/**
	 * @return the applyForCheckFwd
	 */
	public boolean isApplyForCheckFwd() {
		return applyForCheckFwd;
	}

	/**
	 * @param applyForCheckFwd
	 *            the applyForCheckFwd to set
	 */
	public void setApplyForCheckFwd(boolean applyForCheckFwd) {
		this.applyForCheckFwd = applyForCheckFwd;
	}

	/**
	 * @return the applyForCheckRedirect
	 */
	public boolean isApplyForCheckRedirect() {
		return applyForCheckRedirect;
	}

	/**
	 * @param applyForCheckRedirect
	 *            the applyForCheckRedirect to set
	 */
	public void setApplyForCheckRedirect(boolean applyForCheckRedirect) {
		this.applyForCheckRedirect = applyForCheckRedirect;
	}

	/**
	 * @return the redirectUrlPatternName
	 */
	public String getRedirectUrlPatternName() {
		return redirectUrlPatternName;
	}

	/**
	 * @param redirectUrlPatternName
	 *            the redirectUrlPatternName to set
	 */
	public void setRedirectUrlPatternName(String redirectUrlPatternName) {
		this.redirectUrlPatternName = redirectUrlPatternName;
	}

	/**
	 * @return the forwardUrlPatternName
	 */
	public String getForwardUrlPatternName() {
		return forwardUrlPatternName;
	}

	/**
	 * @param forwardUrlPatternName
	 *            the forwardUrlPatternName to set
	 */
	public void setForwardUrlPatternName(String forwardUrlPatternName) {
		this.forwardUrlPatternName = forwardUrlPatternName;
	}

	/**
	 * @return the applyDetectMobile
	 */
	public boolean isApplyDetectMobile() {
		return applyDetectMobile;
	}

	/**
	 * @param applyDetectMobile
	 *            the applyDetectMobile to set
	 */
	public void setApplyDetectMobile(boolean applyDetectMobile) {
		this.applyDetectMobile = applyDetectMobile;
	}

	/**
	 * @return the mobileUrl
	 */
	public String getMobileUrl() {
		return mobileUrl;
	}

	/**
	 * @param mobileUrl
	 *            the mobileUrl to set
	 */
	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}
}
