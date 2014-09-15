package vn.com.vndirect.web.struts2.common;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.commons.utility.SessionManager;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InvalidateSessionAJAXAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(InvalidateSessionAJAXAction.class);

	// @SuppressWarnings("unchecked")
	// private Map session;

	public String execute() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		// if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
		// try {
		// ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		// } catch (IllegalStateException e) {
		// logger.error("invalid session object", e);
		// }
		// }
		try {
			SessionManager.clearSession(ServletActionContext.getRequest());
		} catch (Exception e) {
			logger.error("invalid session object", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
		return SUCCESS;
	}
	//
	// @SuppressWarnings("unchecked")
	// public void setSession(Map session) {
	// this.session = session;
	// }
	//
	// @SuppressWarnings("unchecked")
	// public Map getSession() {
	// return session;
	// }
}
