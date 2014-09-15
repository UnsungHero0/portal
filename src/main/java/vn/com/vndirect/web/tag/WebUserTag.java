/**
 *
 */
package vn.com.vndirect.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.web.security.VNDSCasUser;

/**
 * @author tungnq.nguyen
 * 
 */
public class WebUserTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2646374827890006420L;

	private static Logger logger = Logger.getLogger(WebUserTag.class);

	/*
	 * userName, fullName, status, userId, idgId, userType, email, services
	 */
	private String code;

	/**
     *
     */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		String strOut = null;

		try {
			VNDSCasUser vndsUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			if (vndsUser != null && code != null) {
				strOut = vndsUser.getStr(code);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}

		try {
			pageContext.getOut().print(strOut == null ? "" : strOut);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
		return SKIP_BODY;
	}

	/**
     *
     */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
