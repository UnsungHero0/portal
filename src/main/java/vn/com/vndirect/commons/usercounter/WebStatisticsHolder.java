/**
 * 
 */
package vn.com.vndirect.commons.usercounter;

/**
 * @author tungnq.nguyen
 * 
 */
public abstract class WebStatisticsHolder {
	public static String WEB_STATISTICS_KEY = "$$$WEB_STATISTICS_KEY%%%";

	private static WebStatistics _webStatistics = new WebStatistics();

	/**
	 * 
	 * @return
	 */
	public static WebStatistics getWebStatistics() {
		return _webStatistics;
	}

}
