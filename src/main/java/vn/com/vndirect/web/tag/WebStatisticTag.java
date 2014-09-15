/**
 * 
 */
package vn.com.vndirect.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.security.core.session.SessionRegistry;

import vn.com.vndirect.business.IWebStatisticManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SpringBeans;
import vn.com.vndirect.commons.web.GuestSessionStatisticContext;
import vn.com.vndirect.commons.web.IWebStatistic;
import vn.com.web.commons.utility.NumberFormatter;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("unchecked")
public class WebStatisticTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1575901765995964749L;

	private static Logger logger = Logger.getLogger(WebStatisticTag.class);

	private interface FormatType {
		String STRING = "string";
		String INT = "int";
		String DOUBLE = "double";
	}

	private String code;

	private String type = FormatType.INT;

	/**
     * 
     */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		String strOut = null;

		if (Constants.WebStatistic.CURRENT_ONLINE.equalsIgnoreCase(code) || Constants.WebStatistic.CURRENT_GUEST.equalsIgnoreCase(code)) {
			strOut = getSessionStatistic();
		} else {
			strOut = getWebStatistic();
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
	 * @return
	 */
	private String getSessionStatistic() {
		StringBuilder strOut = new StringBuilder();
		if (Constants.WebStatistic.CURRENT_ONLINE.equalsIgnoreCase(code)) {
			SessionRegistry sessionRegistry = SpringBeans.getSessionRegistry();
			if (sessionRegistry != null) {
				List allPrincipals = sessionRegistry.getAllPrincipals();
				strOut.append(allPrincipals == null ? 0 : allPrincipals.size());
			}
		} else if (Constants.WebStatistic.CURRENT_GUEST.equalsIgnoreCase(code)) {
			GuestSessionStatisticContext guestSessionStatisticContext = SpringBeans.getGuestSessionStatisticContext();
			if (guestSessionStatisticContext != null) {
				strOut.append(guestSessionStatisticContext.totalGuest());
			}
		}
		return strOut.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String getWebStatistic() {
		final String LOCATION = "getWebStatistic()";
		String strOut = null;
		IWebStatisticManager webStatisticManager = SpringBeans.getWebStatisticManager();
		code = (code == null ? "" : code.trim());
		if (webStatisticManager != null && code.length() > 0) {
			try {
				IWebStatistic webStatistic = webStatisticManager.getByCode(code);
				if (webStatistic != null) {
					if (FormatType.INT.equalsIgnoreCase(type)) {
						strOut = NumberFormatter.format(webStatistic.getNumberValue(), NumberFormatter.Type.NUMBER, NumberFormatter.Pattern.PATTERN_0);

					} else if (FormatType.STRING.equalsIgnoreCase(type)) {
						strOut = webStatistic.getStringValue();

					} else if (FormatType.DOUBLE.equalsIgnoreCase(type)) {
						strOut = NumberFormatter.format(webStatistic.getNumberValue(), NumberFormatter.Type.NUMBER, NumberFormatter.Pattern.PATTERN_3);
					} else {
						strOut = NumberFormatter.format(webStatistic.getNumberValue(), NumberFormatter.Type.NUMBER, NumberFormatter.Pattern.PATTERN_0);
					}
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug(LOCATION, e);
				}
			}
		}
		return strOut;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
