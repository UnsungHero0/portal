/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Nov 3, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.i18n;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tungnq
 * 
 */
public class I18NMessage {
	private HttpServletRequest httpRequest;

	public I18NMessage() {
	}

	public I18NMessage(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * @return the httpRequest
	 */
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * @param httpRequest
	 *            the httpRequest to set
	 */
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	// public String getMessage(String key) {
	// if(httpRequest != null && key != null) {
	// try {
	// return I18NUtility.getI18nMessage(key, httpRequest);
	// } catch (Exception e) {
	// }
	// }
	// return "";
	// }
}
