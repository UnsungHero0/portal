package vn.com.vndirect.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.web.commons.captcha.CaptchaEngineProxy;
import vn.com.web.commons.utility.Utilities;

public class HttpSessionAppContextIntegrationFilter implements Filter {
	private static Logger logger = Logger.getLogger(HttpSessionAppContextIntegrationFilter.class);

	// private final static String REQUEST_SYSTEM_USING_FOR = "_app_for";
	private final static String REQUEST_PARAM = "displayOpt";
	private final static String REQUEST_URI_PARAM = "requestUri";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final String LOCATION = "doFilter(...)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();

		/* set request encoding */
		request.setCharacterEncoding("UTF-8");

		String requestUri = (String) request.getAttribute(REQUEST_URI_PARAM);
		if (requestUri == null) {
			requestUri = Utilities.FormatURL.getRequestUri(request);
			request.setAttribute(REQUEST_URI_PARAM, requestUri);
		}

		/* +++++++++++++++ For Test +++++++++++++++ */
		/*
		 * String acceptVal = request.getHeader("Accept"); String xReqVal = request.getHeader("X-Requested-With"); System.out.println(":: acceptVal: " + acceptVal + ", xReqVal: " + xReqVal);
		 */
		/* --------------- For Test --------------- */

		boolean isChangedAppContext = false;
		// +++ get AppContext
		AppContext appContext = AppContextHolder.find(request, session);
		if (appContext == null) {
			isChangedAppContext = true;

			appContext = new AppContext();
			// +++ Add auth for
			// appContext.addAttr(VNDSCasUser.ServiceParamName.AUTH_FOR, VNDSCasUser.AuthFor.ONLINE);
			// ---

			// +++ set app context path
			appContext.setAppContextPath(request.getContextPath());
		}

		// +++ get captcha text
		try {
			String captchaText = (String) session.getAttribute(CaptchaEngineProxy.PARAM_CAPTCHA_SCODE);
			appContext.setCaptchaText(captchaText);
		} catch (Exception e) {
		}
		// ---

		// +++ process display option
		try {
			String displayOpt = request.getParameter(REQUEST_PARAM);
			if (displayOpt != null) {
				if (AppContext.DISPLAY_OPTION_GALLERY.equalsIgnoreCase(displayOpt)) {
					appContext.setDisplayOption(AppContext.DISPLAY_OPTION_GALLERY);
				} else {
					appContext.setDisplayOption(AppContext.DISPLAY_OPTION_LIST);
				}
			}
			request.setAttribute(REQUEST_PARAM, appContext.getDisplayOption());
		} catch (Exception e) {

		}
		// ---
		try {
			appContext.setAppContextPath(request.getContextPath());

			appContext.setRequest(request);

			AppContextHolder.set(appContext, request, session, isChangedAppContext);

			chain.doFilter(request, response);
		} finally {
			AppContextHolder.clearUp();
		}

		// init I18NContext
		try {
			I18NUtility.setI18NContext(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
