package vn.com.vndirect.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

/**
 * @author Spring
 */
public class SavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static Logger logger = Logger.getLogger(SavedRequestAwareAuthenticationSuccessHandler.class);

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			if (logger.isDebugEnabled())
				logger.debug("savedRequest: " + savedRequest);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
			if (logger.isDebugEnabled())
				logger.debug("getTargetUrlParameter(): " + getTargetUrlParameter());
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		if (logger.isDebugEnabled())
			logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);

		getRedirectStrategy().sendRedirect(request, response, toHTTPS(targetUrl));
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	/**
	 * @param targetUrl
	 * @return
	 */
	private String toHTTPS(String targetUrl) {
		if (targetUrl.startsWith("http://"))
			return targetUrl.replaceAll("http://", "https://");
		return targetUrl;
	}
}
