/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 7, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.utility;

import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.SpringUtils;

import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @author tungnq.nguyen
 * 
 */
public abstract class OSCacheUtils {
	/**
	 * 
	 * @return
	 */
	public static GeneralCacheAdministrator getOSCache() throws Exception {
		return (GeneralCacheAdministrator) SpringUtils.getBean(Constants.SpringBeanNames.OSCACHE_BEAN);
	}

	/**
	 * 
	 * @return
	 */
	public static boolean usingCacheStaticPage() {
		String str = ServerConfig.getOnlineValue(Constants.IServerConfig.FileServer.OSCache.USING_CACHE_STATIC_PAGES);
		str = (str == null ? "" : str.trim());
		return "true".equalsIgnoreCase(str);
	}

	/**
	 * 
	 * @return default value is 1h = 1*60*60 (seconds)
	 */
	public static int getStaticPageRefreshPeriod() {
		int refreshPeriod = ServerConfig.getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.STATIC_PAGES_REFRESH_PERIOD);
		return (refreshPeriod < 60 ? 1 * 60 * 60 : refreshPeriod);
	}

	/**
	 * default value is 30 minutes = 30*60 (seconds)
	 * 
	 * @return
	 */
	public static int getChartRefreshPeriod() {
		int chartRefreshPeriod = ServerConfig.getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.CHARTS_REFRESH_PERIOD);
		return (chartRefreshPeriod < 60 ? 30 * 60 : chartRefreshPeriod);
	}

	/**
	 * default value is 15 minutes = 15*60 (seconds)
	 * 
	 * @return
	 */
	public static int getTAChartRefreshPeriod() {
		int chartRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.TACHARTS_REFRESH_PERIOD);
		return (chartRefreshPeriod < 60 ? 15 * 60 : chartRefreshPeriod);
	}

	/**
	 * default value is 10 minutes = 10*60 (seconds)
	 * 
	 * @return
	 */
	public static int getLatestNewsRefreshPeriod() {
		int latestNewsRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.LATEST_NEWS_REFRESH_PERIOD);
		return (latestNewsRefreshPeriod < 60 ? 10 * 60 : latestNewsRefreshPeriod);
	}

	/**
	 * default value is 15 minutes = 10*60 (seconds)
	 * 
	 * @return
	 */
	public static int getIndustryCenterRefreshPeriod() {
		int industryCenterNewsRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.INDUSTRY_CENTER_REFRESH_PERIOD);
		return (industryCenterNewsRefreshPeriod < 60 ? 15 * 60 : industryCenterNewsRefreshPeriod);
	}

	/**
	 * default value is 30 minutes = 30*60 (seconds)
	 * 
	 * @return
	 */
	public static int getFlashTAChartRefreshPeriod() {
		int chartRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.FLASH_TACHARTS_REFRESH_PERIOD);
		return (chartRefreshPeriod < 60 ? 30 * 60 : chartRefreshPeriod);
	}

	/**
	 * default value is 15 minutes = 10*60 (seconds)
	 * 
	 * @return
	 */
	public static int getFlashEventsRefreshPeriod() {
		int chartRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.FLASH_EVENTS_REFRESH_PERIOD);
		return (chartRefreshPeriod < 60 ? 15 * 60 : chartRefreshPeriod);
	}

	/**
	 * default value is 1 hours = 1*60*60 (seconds)
	 * 
	 * @return
	 */
	public static int getDataRefsRefreshPeriod() {
		int chartRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.DATA_REF_REFRESH_PERIOD);
		return (chartRefreshPeriod < 60 ? 1 * 60 * 60 : chartRefreshPeriod);
	}

	/**
	 * default value is 15 minutes = 15*60 (seconds)
	 * 
	 * @return
	 */
	public static int getPorfolioRefreshPeriod() {
		int porfolioRefreshPeriod = ServerConfig
		        .getOnlineIntValue(Constants.IServerConfig.FileServer.OSCache.PORFOLIO_REFRESH_PERIOD);
		return (porfolioRefreshPeriod < 60 ? 15 * 60 : porfolioRefreshPeriod);
	}
}
