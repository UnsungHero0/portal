/**
 * 
 */
package vn.com.vndirect.commons.usercounter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author tungnq.nguyen
 * 
 */
public class UserCounterHttpSessionListener implements HttpSessionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		WebStatisticsHolder.getWebStatistics().increaseActiveSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
	 * .http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		WebStatisticsHolder.getWebStatistics().decreaseActiveSession();
	}

}
