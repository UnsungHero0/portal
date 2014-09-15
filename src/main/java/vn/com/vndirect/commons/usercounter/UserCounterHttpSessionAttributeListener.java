/**
 * 
 */
package vn.com.vndirect.commons.usercounter;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class UserCounterHttpSessionAttributeListener implements HttpSessionBindingListener, Serializable {

	private WebUserType webUserType = WebUserType.GUEST;

	public UserCounterHttpSessionAttributeListener(WebUserType _webUserType) {
		webUserType = _webUserType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent arg0) {
		if (webUserType != null && webUserType.key == WebUserType.ONLINE_USER.key) {
			WebStatisticsHolder.getWebStatistics().increaseOnlineUser();

		} else if (webUserType != null && webUserType.key == WebUserType.AGENT_USER.key) {
			WebStatisticsHolder.getWebStatistics().increaseAgentUser();

		} else if (webUserType != null && webUserType.key == WebUserType.FREE_USER.key) {
			WebStatisticsHolder.getWebStatistics().increaseFreeUser();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		if (webUserType != null && webUserType.key == WebUserType.ONLINE_USER.key) {
			WebStatisticsHolder.getWebStatistics().decreaseOnlineUser();

		} else if (webUserType != null && webUserType.key == WebUserType.AGENT_USER.key) {
			WebStatisticsHolder.getWebStatistics().decreaseAgentUser();

		} else if (webUserType != null && webUserType.key == WebUserType.FREE_USER.key) {
			WebStatisticsHolder.getWebStatistics().decreaseFreeUser();
		}
	}

}
