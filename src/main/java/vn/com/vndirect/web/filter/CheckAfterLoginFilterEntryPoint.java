package vn.com.vndirect.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import vn.com.vndirect.commons.utility.SpringSecurityAuthorize;
import vn.com.web.commons.urlpattern.URLPattern;
import vn.com.web.commons.urlpattern.URLPatterns;
import vn.com.web.commons.utility.Utilities;

public class CheckAfterLoginFilterEntryPoint {
	private static Logger logger = Logger.getLogger(CheckAfterLoginFilterEntryPoint.class);

	private final static String URL_BLOCK_FREE_USER_PATTERN_NAME = "checkBlockFreeUserUrl";
	private final static String URL_BLOCK_NORMAL_USER_PATTERN_NAME = "checkBlockNormalUserUrl";

	private String blockFreeUserUrlPattern = URL_BLOCK_FREE_USER_PATTERN_NAME;
	private String blockNormalUserUrlPattern = URL_BLOCK_NORMAL_USER_PATTERN_NAME;

	private URLPattern objBlockFreeUserUrlPattern = null;
	private URLPattern objBlockNormalUserUrlPattern = null;

	protected RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private String freeUserRoles = "ROLE_ONLINE_FREE_REGISTER";
	private String normalUserRoles = "ROLE_ONLINE_UPDATE_ACCOUNT_INFO,ROLE_OPEN_AN_ACCOUNT,ROLE_ONLINE_VIEW_ACCOUNT_INFO,ROLE_ONLINE_TRADING";

	/**
	 * 
	 * @return
	 */
	private URLPattern getBlockFreeUserUrlPattern() {
		// +++ get url pattern
		URLPatterns urlPatterns = URLPatterns.getInstance();
		if (objBlockFreeUserUrlPattern == null) {
			objBlockFreeUserUrlPattern = urlPatterns.getURLPattern(blockFreeUserUrlPattern);
			if (objBlockFreeUserUrlPattern == null) {
				// get default
				objBlockFreeUserUrlPattern = urlPatterns.getURLPattern(URL_BLOCK_FREE_USER_PATTERN_NAME);
			}
		}
		// ---
		return objBlockFreeUserUrlPattern;
	}

	/**
	 * 
	 * @return
	 */
	private URLPattern getBlockNormalUserUrlPattern() {
		// +++ get url pattern
		URLPatterns urlPatterns = URLPatterns.getInstance();
		if (objBlockNormalUserUrlPattern == null) {
			objBlockNormalUserUrlPattern = urlPatterns.getURLPattern(blockNormalUserUrlPattern);
			if (objBlockNormalUserUrlPattern == null) {
				// get default
				objBlockNormalUserUrlPattern = urlPatterns.getURLPattern(URL_BLOCK_NORMAL_USER_PATTERN_NAME);
			}
		}
		// ---
		return objBlockNormalUserUrlPattern;
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
		
		//System.out.println(Utilities.FormatURL.getRequestUri(request));

		if (SpringSecurityAuthorize.isAuth()) {
			boolean isForward = false;
			String fwUrl = null;

			boolean isFreeUserGranted = SpringSecurityAuthorize.authzIfAnyGranted(freeUserRoles);
			boolean isNormalUserGranted = SpringSecurityAuthorize.authzIfAnyGranted(normalUserRoles);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: isFreeUserGranted:" + isFreeUserGranted + ", isNormalUserGranted:" + isNormalUserGranted);

			// System.out.println(LOCATION + ":: isFreeUserGranted:" + isFreeUserGranted + ", isNormalUserGranted:" + isNormalUserGranted);

			if (isFreeUserGranted || isNormalUserGranted) {
				String requestUri = Utilities.FormatURL.getRequestUri(request);
				int pathParamIndex = requestUri.indexOf(59);
				if (pathParamIndex > 0) {
					requestUri = requestUri.substring(0, pathParamIndex);
				}

				if (isNormalUserGranted) {
					URLPattern _blockNormalUserUrlPattern = this.getBlockNormalUserUrlPattern();
					if (_blockNormalUserUrlPattern != null && blockNormalUserUrlPattern.matches(requestUri)) {
						fwUrl = _blockNormalUserUrlPattern.getDefaultUrl();
						isForward = true;
					}
				} else {
					URLPattern _blockFreeUserUrlPattern = this.getBlockFreeUserUrlPattern();
					if (_blockFreeUserUrlPattern != null && _blockFreeUserUrlPattern.matches(requestUri)) {
						fwUrl = _blockFreeUserUrlPattern.getDefaultUrl();
						isForward = true;
					}
				}
			}
			if (isForward && fwUrl != null) {
				// if(URLUtils.checkExt(fwUrl, ".shtml")) {
				// fwUrl = Utilities.FormatURL.encodeURI(fwUrl, request);
				// }
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: fwUrl:" + fwUrl);

				// System.out.println(LOCATION + ":: fwUrl:" + fwUrl);
				redirectStrategy.sendRedirect(request, new SendRedirectOverloadedResponse(request, response), fwUrl);
			} else {
				chain.doFilter(request, new SendRedirectOverloadedResponse(request, response));
			}
		} else {
			chain.doFilter(request, new SendRedirectOverloadedResponse(request, response));
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	/**
	 * @return the freeUserRoles
	 */
	public String getFreeUserRoles() {
		return freeUserRoles;
	}

	/**
	 * @param freeUserRoles
	 *            the freeUserRoles to set
	 */
	public void setFreeUserRoles(String freeUserRoles) {
		this.freeUserRoles = freeUserRoles;
	}

	/**
	 * @return the normalUserRoles
	 */
	public String getNormalUserRoles() {
		return normalUserRoles;
	}

	/**
	 * @param normalUserRoles
	 *            the normalUserRoles to set
	 */
	public void setNormalUserRoles(String normalUserRoles) {
		this.normalUserRoles = normalUserRoles;
	}

	/**
	 * @param blockFreeUserUrlPattern
	 *            the blockFreeUserUrlPattern to set
	 */
	public void setBlockFreeUserUrlPattern(String blockFreeUserUrlPattern) {
		this.blockFreeUserUrlPattern = blockFreeUserUrlPattern;
	}

	/**
	 * @param blockNormalUserUrlPattern
	 *            the blockNormalUserUrlPattern to set
	 */
	public void setBlockNormalUserUrlPattern(String blockNormalUserUrlPattern) {
		this.blockNormalUserUrlPattern = blockNormalUserUrlPattern;
	}
}
