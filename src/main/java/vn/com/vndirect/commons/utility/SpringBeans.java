/**
 *
 */
package vn.com.vndirect.commons.utility;

import net.sf.ehcache.Ehcache;

import org.springframework.security.core.session.SessionRegistry;

import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.IWebStatisticManager;
import vn.com.vndirect.commons.web.GuestSessionStatisticContext;
import vn.com.web.commons.urlmapping.ExternalUrlMapping;
import vn.com.web.commons.utility.SpringUtils;

/**
 * @author tungnq.nguyen
 * 
 */
public abstract class SpringBeans {
	public interface Names {
		String WEB_SESSION_REGISTRY = "WebSessionRegistry";
		String GUEST_SESSION_STATISTIC_CTX = "GuestSessionStatisticContext";

		// +++ cache Objects
		String DB_CACHE = "DBDataReferenceCache";

		// +++ Manager Objects
		String WEB_STATISTIC_MANAGER = "WebStatisticManager";

		String COMMON_MANAGER = "CommonManager";
		String ANALYSIS_TOOLS_MANAGER = "AnalysisToolsManager";
		String LISTED_MARKET_MANAGER = "ListedMarketManager";
		String NEWS_INFO_MANAGER = "NewsInfoManager";
		String QUOTES_MANAGER = "QuotesManager";

		String EXTERNAL_URL_MAPPING = "ExternalUrlMapping";
		String TICKET_PROXY_URL_MAPPING = "TicketProxyUrlMapping";
	}

	/**
	 * 
	 * @return SessionRegistry
	 */
	public static SessionRegistry getSessionRegistry() {
		try {
			return (SessionRegistry) SpringUtils.getBean(Names.WEB_SESSION_REGISTRY);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static GuestSessionStatisticContext getGuestSessionStatisticContext() {
		try {
			return (GuestSessionStatisticContext) SpringUtils.getBean(Names.GUEST_SESSION_STATISTIC_CTX);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Ehcache getDBEhcache() {
		try {
			return (Ehcache) SpringUtils.getBean(Names.DB_CACHE);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return IWebStatisticManager
	 */
	public static IWebStatisticManager getWebStatisticManager() {
		try {
			return (IWebStatisticManager) SpringUtils.getBean(Names.WEB_STATISTIC_MANAGER);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Get CommonManager spring bean
	 * 
	 * @return ICommonManager
	 */
	public static ICommonManager getCommonManager() {
		try {
			return (ICommonManager) SpringUtils.getBean(Names.COMMON_MANAGER);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static IQuotesManager getQuotesManager() {
		try {
			return (IQuotesManager) SpringUtils.getBean(Names.QUOTES_MANAGER);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return IWebStatisticManager
	 */
	public static ExternalUrlMapping getExternalUrlMapping() {
		try {
			return (ExternalUrlMapping) SpringUtils.getBean(Names.EXTERNAL_URL_MAPPING);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static ExternalUrlMapping getTicketProxyUrlMapping() {
		try {
			return (ExternalUrlMapping) SpringUtils.getBean(Names.TICKET_PROXY_URL_MAPPING);
		} catch (Exception e) {
			return null;
		}
	}
}
