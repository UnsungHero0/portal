/**
 * 
 */
package vn.com.vndirect.commons.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tungnq.nguyen
 * 
 */
public class AppContext implements Serializable {
	public final static String ROOT_CXT = "/";

	/**
     * 
     */
	private static final long serialVersionUID = 1359981975226222179L;

	private transient HttpServletRequest request;

	private Map<String, Serializable> mapAttr = new HashMap<String, Serializable>();

	private String captchaText;

	private String appContextPath = ROOT_CXT;
	private String sessionId;

	private Map<String, String> serviceTicketMap = new HashMap<String, String>();

	/**
	 * Define Display Option
	 * 
	 */
	public final static String DISPLAY_OPTION_LIST = "list";
	public final static String DISPLAY_OPTION_GALLERY = "gallery";

	private String displayOption = DISPLAY_OPTION_LIST;

	public void setServiceTicket(String service, String ticket) {
		serviceTicketMap.put(service, ticket);
	}

	public String getServiceTicket(String service) {
		return serviceTicketMap.get(service);
	}

	/**
	 * @return the captchaText
	 */
	public String getCaptchaText() {
		return captchaText;
	}

	/**
	 * @param captchaText
	 *            the captchaText to set
	 */
	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	/**
	 * @return the appContextPath
	 */
	public String getAppContextPath() {
		return appContextPath;
	}

	/**
	 * @param appContextPath
	 *            the appContextPath to set
	 */
	public void setAppContextPath(String appContextPath) {
		this.appContextPath = appContextPath;
	}

	/**
	 * @return the displayOption
	 */
	public String getDisplayOption() {
		return displayOption;
	}

	/**
	 * @param displayOption
	 *            the displayOption to set
	 */
	public void setDisplayOption(String displayOption) {
		this.displayOption = displayOption;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		if (request != null && request.getSession(false) != null) {
			sessionId = request.getSession().getId();
		}
	}

	/**
	 * @return the mapAttr
	 */
	public Map<String, Serializable> getMapAttr() {
		return mapAttr;
	}

	/**
	 * @param mapAttr
	 *            the mapAttr to set
	 */
	public void setMapAttr(Map<String, Serializable> mapAttr) {
		if (mapAttr != null) {
			this.mapAttr = mapAttr;
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addAttr(String key, Serializable value) {
		if (key != null && value != null) {
			if (this.mapAttr == null) {
				this.mapAttr = new HashMap<String, Serializable>();
			}
			this.mapAttr.put(key, value);
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Serializable getAttr(String key) {
		if (this.mapAttr != null) {
			return this.mapAttr.get(key);
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getAttrStr(String key) {
		if (this.mapAttr != null) {
			Object obj = this.mapAttr.get(key);
			if (obj != null && obj instanceof String) {
				return (String) obj;
			}
		}
		return null;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "AppContext-[appContextPath:" + appContextPath + ", displayOption: " + displayOption + "]";
	}
}
