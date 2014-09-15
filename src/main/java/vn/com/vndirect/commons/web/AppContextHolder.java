/**
 * 
 */
package vn.com.vndirect.commons.web;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * @author tungnq.nguyen
 * 
 */
public class AppContextHolder {
	public final static String REQUEST_APP_CONTEXT_KEY = "$_AppContext@01055503484_%";
	public final static String SESSION_APP_CONTEXT_KEY = "$_AppContext@01055503484_%";
	public static String serverName;
	public static String serverNumber;
	static {
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			serverName = hostName;
			if (StringUtils.isNotBlank(hostName) && hostName.indexOf(".") > -1) {
				hostName = hostName.substring(0, hostName.indexOf("."));
				int www = hostName.indexOf("www");
				if (www > -1)
					hostName = hostName.substring(www + 3);
			}
			serverNumber = hostName;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 
     */
	private AppContextHolder() {
	}

	/**
     * 
     */
	private static ThreadLocal<AppContext> tLocal = new ThreadLocal<AppContext>();

	/**
	 * 
	 * @param appCtx
	 */
	public static void set(AppContext appCtx) {
		set(appCtx, null, null, true);
	}

	/**
	 * 
	 * @param appCtx
	 * @param request
	 *            allows NULL
	 * @param session
	 *            allows NULL
	 */
	public static void set(AppContext appCtx, HttpServletRequest request, HttpSession session, boolean isChangedAppContext) {
		// System.out.println("+++++ AppContextHolder:: --->>> appCtx:" + appCtx
		// + ", isChangedAppContext:" + isChangedAppContext);

		tLocal.set(appCtx);
		if (request != null && appCtx != null) {
			request.setAttribute(REQUEST_APP_CONTEXT_KEY, appCtx);
		}

		if (session != null && appCtx != null && isChangedAppContext) {
			session.setAttribute(SESSION_APP_CONTEXT_KEY, appCtx);
		}
	}

	/**
	 * 
	 * @param appCtx
	 * @param request
	 * @param isStoredInSession
	 * @param isChangedAppContext
	 */
	public static void set(AppContext appCtx, HttpServletRequest request, boolean isStoredInSession) {
		// System.out.println("+++++ AppContextHolder:: --->>> appCtx:" + appCtx
		// + ", isChangedAppContext:" + isChangedAppContext);

		tLocal.set(appCtx);
		if (request != null && appCtx != null) {
			request.setAttribute(REQUEST_APP_CONTEXT_KEY, appCtx);

			if (isStoredInSession) {
				request.getSession().setAttribute(SESSION_APP_CONTEXT_KEY, appCtx);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static AppContext get() {
		return find(null, null);
	}

	/**
	 * find application context from Thread Local, Request or Session
	 * 
	 * @param request
	 *            allows NULL
	 * @param session
	 *            allows NULL
	 * @return
	 */
	public static AppContext find(HttpServletRequest request, HttpSession session) {
		AppContext appCtx = tLocal.get();

		if (request != null && appCtx == null) {
			try {
				// System.out.println("--- find appCtx in Request");
				appCtx = (AppContext) request.getAttribute(REQUEST_APP_CONTEXT_KEY);
			} catch (Exception e) {
			}
		}

		if (session != null && appCtx == null) {
			try {
				// System.out.println("--- find appCtx in Session");
				appCtx = (AppContext) session.getAttribute(SESSION_APP_CONTEXT_KEY);
			} catch (Exception e) {
			}
		}
		// System.out.println("--->>> appCtx:" + appCtx);
		return appCtx;
	}

	/**
     * 
     */
	public static void clearUp() {
		tLocal.remove();
	}
}
