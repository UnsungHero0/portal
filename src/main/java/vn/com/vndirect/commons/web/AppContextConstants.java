/**
 * 
 */
package vn.com.vndirect.commons.web;

/**
 * @author tungnq.nguyen
 * 
 */
public interface AppContextConstants {
	public interface Keys {
		String LOADING_DATA = "$LOADING_DATA@";

		String ACTIVE_CUSTOMER_ACCOUNT_KEY = "$ACTIVE_CUSTOMER_ACCOUNT_KEY$";
		String LIST_CUSTOMER_ACCOUNT_KEY = "$LIST_CUSTOMER_ACCOUNT_KEY$";
		String ACTIVE_ONLINE_TRADING_ACCOUNT_KEY = "$ACTIVE_ONLINE_TRADING_ACCOUNT_KEY$";

		String CURRENT_REQUEST_URI = "$CURRENT_REQUEST_URI@";

		String FORWARD_REQUEST_URI = "$FORWARD_REQUEST_URI@";
	}
}
