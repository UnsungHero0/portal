/**
 * 
 */
package vn.com.vndirect.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import vn.com.vndirect.commons.utility.SpringBeans;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.vndirect.commons.web.GuestSessionStatisticContext;

/**
 * @author tungnq.nguyen
 * 
 */
public class GuestCounterListener implements HttpSessionListener, HttpSessionAttributeListener {
	// private final transient Log log = LogFactory.getLog(GuestCounterListener.class);

	public static final String SPRING_SECURITY_EVENT_KEY = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
	public static final String APPCTX_EVENT_KEY = AppContextHolder.SESSION_APP_CONTEXT_KEY;

	/**
	 * This method is designed to catch when user's login and record their name
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession httpSession = event.getSession();
		// if (log.isDebugEnabled()) {
		// log.debug("attributeAdded:: ---->>> " + httpSession.getId()+ ",
		// event.name: " + event.getName());
		// }
		if (event.getName().equals(SPRING_SECURITY_EVENT_KEY)) {
			GuestSessionStatisticContext guestSessionStatisticContext = SpringBeans.getGuestSessionStatisticContext();
			if (guestSessionStatisticContext != null) {
				guestSessionStatisticContext.remove(httpSession.getId());
			}
		}
	}

	/**
	 * When user's logout, remove their name from the hashMap
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession httpSession = event.getSession();
		// if (log.isDebugEnabled()) {
		// log.debug("attributeRemoved:: ---->>> " + httpSession.getId()+ ",
		// event.name: " + event.getName());
		// }

		if (SPRING_SECURITY_EVENT_KEY.equals(event.getName()) || APPCTX_EVENT_KEY.equals(event.getName())) {
			GuestSessionStatisticContext guestSessionStatisticContext = SpringBeans.getGuestSessionStatisticContext();
			if (guestSessionStatisticContext != null) {
				guestSessionStatisticContext.remove(httpSession.getId());
			}
		}
	}

	/**
	 * Needed for Acegi Security 1.0, as it adds an anonymous user to the session and then replaces it after authentication. http://forum.springframework.org/showthread.php?p=63593
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSession httpSession = event.getSession();
		// if (log.isDebugEnabled()) {
		// log.debug("attributeReplaced:: ---->>> " + httpSession.getId()+ ",
		// event.name: " + event.getName());
		// System.out.println("attributeReplaced:: ---->>> " +
		// httpSession.getId()+ ", event.name: " + event.getName());
		// }

		if (event.getName().equals(SPRING_SECURITY_EVENT_KEY)) {
			GuestSessionStatisticContext guestSessionStatisticContext = SpringBeans.getGuestSessionStatisticContext();
			if (guestSessionStatisticContext != null) {
				guestSessionStatisticContext.remove(httpSession.getId());
			}
		}
	}

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession httpSession = event.getSession();
		GuestSessionStatisticContext guestSessionStatisticContext = SpringBeans.getGuestSessionStatisticContext();
		if (guestSessionStatisticContext != null) {
			guestSessionStatisticContext.addNewSession(httpSession.getId());
		}
		// if (log.isDebugEnabled()) {
		// log.debug("sessionCreated:: ---->>> " + httpSession.getId());
		// }
	}

	public void sessionDestroyed(HttpSessionEvent event) {
	}
}
