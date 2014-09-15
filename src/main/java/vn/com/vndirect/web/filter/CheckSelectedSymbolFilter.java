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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.SpringBeans;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.web.commons.urlpattern.URLPattern;
import vn.com.web.commons.urlpattern.URLPatterns;
import vn.com.web.commons.utility.Utilities;

public class CheckSelectedSymbolFilter implements Filter {
	private static Logger logger = Logger.getLogger(CheckSelectedSymbolFilter.class);

	public interface KeyCfg {
		String URL_PATTERN_NAME = "urlPatternName";
	}

	private final String VIEW_SYMBOL_REQUEST_PARAM = "viewSymbol";

	private IQuotesManager quotesManager;

	protected RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private final static String URL_PATTERN_NAME = "checkSelectdSymbol";

	private String urlPatternName = URL_PATTERN_NAME;

	private final String SNAPSHOT = "/tong-quan";
	private final String PROFILE = "/ho-so-doanh-nghiep";
	private final String KEY_STATISTIC = "/thong-ke-co-ban";
	private final String INCOME_FORECAST = "/chi-tieu-ke-hoach";
	private final String BALANCE_SHEET = "/bao-cao-tai-chinh";

	private final String HIST_PRICE = "/lich-su-gia";

	private final String FLASH_CHART = "/bieu-do-ky-thuat";

	private final String MAIJOR_HOLDERS = "/co-dong-chinh";
	private final String NEWS_4_SYMMBOL = "/tin-doanh-nghiep";
	private final String TRADING_STATISTC_SYMBOL = "/ket-qua-giao-dich";
	private final String FOREIGN_TRADING_SYMBOL = "/giao-dich-nha-dau-tu-nuoc-ngoai";
	private final String INCOME_STATEMENT = "/bao-cao-ket-qua-kinh-doanh";
	private final String CASH_FLOW = "/bao-cao-luu-chuyen-tien-te";
	private final String COMPANY_EVENTS = "/tin-lien-quan";
	private final String INSIDE_TRANSACTION = "/giao-dich-noi-bo";
	private final String SSCFILLING = "/bang-can-doi-ke-toan";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// +++ init
		String _urlPatternName = filterConfig.getInitParameter(KeyCfg.URL_PATTERN_NAME);
		if (_urlPatternName != null && _urlPatternName.trim().length() > 0) {
			urlPatternName = _urlPatternName.trim();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("urlPatternName=" + urlPatternName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * 
	 * @return
	 */
	private URLPattern getURLPattern() {
		// +++ get url pattern
		URLPatterns urlPatterns = URLPatterns.getInstance();
		URLPattern urlPattern = urlPatterns.getURLPattern(urlPatternName);
		if (urlPattern == null) {
			// get default
			urlPattern = urlPatterns.getURLPattern(URL_PATTERN_NAME);
		}
		// ---
		return urlPattern;
	}

	/**
	 * 
	 * @return
	 */
	private IQuotesManager getQuotesManager() {
		if (quotesManager == null) {
			quotesManager = SpringBeans.getQuotesManager();
		}
		return quotesManager;
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

		boolean isForward = false;

		String fwUrl = null;

		try {
			String requestUri = Utilities.FormatURL.getRequestUri(request);
			int pathParamIndex = requestUri.indexOf(59);
			if (pathParamIndex > 0) {
				requestUri = requestUri.substring(0, pathParamIndex);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: " + requestUri);
			}

			HttpSession session = request.getSession();
			URLPattern urlPattern = this.getURLPattern();
			if(urlPattern.matches(requestUri)){
				if (requestUri.startsWith(PROFILE)) {
					session.setAttribute("LISTED_MARKET_FROM", "ho-so-doanh-nghiep/");
				} else if (requestUri.startsWith(KEY_STATISTIC)) {
					session.setAttribute("LISTED_MARKET_FROM", "thong-ke-co-ban/");
				} else if (requestUri.startsWith(INCOME_FORECAST)) {
					session.setAttribute("LISTED_MARKET_FROM", "chi-tieu-ke-hoach/");
				} else if (requestUri.startsWith(BALANCE_SHEET)) {
					session.setAttribute("LISTED_MARKET_FROM", "bao-cao-tai-chinh/");
				} else if (requestUri.startsWith(HIST_PRICE)) {
					session.setAttribute("LISTED_MARKET_FROM", "lich-su-gia/");
				} else if (requestUri.startsWith(SNAPSHOT)) {
					session.setAttribute("LISTED_MARKET_FROM", "tong-quan/");
				} else if (requestUri.startsWith(FLASH_CHART)) {
					session.setAttribute("LISTED_MARKET_FROM", "bieu-do-ky-thuat/");
				} else if (requestUri.startsWith(MAIJOR_HOLDERS)) {
					session.setAttribute("LISTED_MARKET_FROM", "co-dong-chinh/");
				} else if (requestUri.startsWith(NEWS_4_SYMMBOL)) {
					session.setAttribute("LISTED_MARKET_FROM", "tin-doanh-nghiep/");
				} else if (requestUri.startsWith(TRADING_STATISTC_SYMBOL)) {
					session.setAttribute("LISTED_MARKET_FROM", "ket-qua-giao-dich/");
				} else if (requestUri.startsWith(FOREIGN_TRADING_SYMBOL)) {
					session.setAttribute("LISTED_MARKET_FROM", "giao-dich-nha-dau-tu-nuoc-ngoai/");
				} else if (requestUri.startsWith(INCOME_STATEMENT)) {
					session.setAttribute("LISTED_MARKET_FROM", "bao-cao-ket-qua-kinh-doanh/");
				} else if (requestUri.startsWith(CASH_FLOW)) {
					session.setAttribute("LISTED_MARKET_FROM", "bao-cao-luu-chuyen-tien-te/");
				} else if (requestUri.startsWith(COMPANY_EVENTS)) {
					session.setAttribute("LISTED_MARKET_FROM", "tin-lien-quan/");
				} else if (requestUri.startsWith(INSIDE_TRANSACTION)) {
					session.setAttribute("LISTED_MARKET_FROM", "giao-dich-noi-bo/");
				} else if (requestUri.startsWith(SSCFILLING)) {
					session.setAttribute("LISTED_MARKET_FROM", "bang-can-doi-ke-toan/");
				}
	
				// ++++ change selected symbol [viewSymbol=???]
				//this.changeSelectedSymbol(request);
				// ----
				String symbol = (String) request.getSession().getAttribute("symbolCompany"); 
				CurrentCompanyForQuote currentComp = null;
				if (symbol != null) {
					currentComp = getQuotesManager().quickSearchQuotes(symbol, I18NUtility.getCurrentLocale(request.getSession()));
				}
				
				if ((currentComp == null || currentComp.isExchange()) && urlPattern != null) {
					isForward = true;
					fwUrl = urlPattern.getDefaultUrl();
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION, e);
		}

		if (isForward && fwUrl != null) {
			// fwUrl = Utilities.FormatURL.encodeURI(fwUrl, request);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: fwUrl: " + fwUrl);
			redirectStrategy.sendRedirect(request, new SendRedirectOverloadedResponse(request, response), fwUrl);
		} else {
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: next chain...");
			chain.doFilter(request, new SendRedirectOverloadedResponse(request, response));
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	/**
     *
     */
	private void changeSelectedSymbol(HttpServletRequest request) {
		try {
			IQuotesManager quotesMgmt = this.getQuotesManager();
			if (quotesMgmt != null && request != null) {
				String symbol = request.getParameter(VIEW_SYMBOL_REQUEST_PARAM);
				String curSymbol = (String) request.getSession().getAttribute("symbolCompany"); 
				if (logger.isDebugEnabled()) {
					logger.debug("changeSelectedSymbol():: symbol:" + symbol);
					logger.debug("changeSelectedSymbol():: curSymbol:" + curSymbol);
				}
				if (symbol != null) {
					if (curSymbol == null || !symbol.equalsIgnoreCase(curSymbol)) {
						CurrentCompanyForQuote currentCompanyForQuote = quotesManager.quickSearchQuotes(symbol, I18NUtility.getCurrentLocale(request.getSession()));
						if (currentCompanyForQuote != null) {
							if (logger.isDebugEnabled())
								logger.debug("changeSelectedSymbol():: currentCompanyForQuote:" + currentCompanyForQuote);
							SessionManager.setSymbolCompany(symbol);
						}
					} else {
						// nothing symbol==curSymbol
					}
				} else {
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("changeSelectedSymbol()", e);
		}
	}
}
