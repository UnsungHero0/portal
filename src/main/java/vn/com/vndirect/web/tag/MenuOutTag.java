package vn.com.vndirect.web.tag;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.web.commons.urlpattern.URLPattern;
import vn.com.web.commons.urlpattern.URLPatterns;

public class MenuOutTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7730731665433693900L;

	private static Logger logger = Logger.getLogger(MenuOutTag.class);

	private String code;
	private String idValue;
	private String classValue;
	private String value;
	private String defaultText = "";
	private String pattern;
	private String requestUri;

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param defaultText
	 *            the defaultText to set
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * 
	 * @param requestUri
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	/**
	 * @param idValue
	 *            the idValue to set
	 */
	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	/**
	 * @param classValue
	 *            the classValue to set
	 */
	public void setClassValue(String classValue) {
		this.classValue = classValue;
	}

	/**
    *
    */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 *
	 */
	public int doStartTag() throws JspException {
		String strOut = null;
		code = (code == null ? "" : code.trim());
		pattern = (pattern == null ? "" : pattern.trim());
		try {
			if (requestUri == null) {
				final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				requestUri = (String) request.getAttribute("requestUri");
			}

			if (logger.isDebugEnabled()) {
				logger.debug("code: " + code + ", pattern:" + pattern + ", value:" + value + ", requestUri:" + requestUri);
			}

			if (code.length() > 0) {
				strOut = executeCode(requestUri);
			} else if (pattern.length() > 0) {
				strOut = executePattern(requestUri);
			} else {
				strOut = defaultText;
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		}

		// +++ reset value
		code = null;
		pattern = null;
		defaultText = "";
		value = null;
		requestUri = null;
		idValue = null;
		classValue = null;
		// ---

		try {
			pageContext.getOut().print(strOut == null ? "" : strOut);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		}
		return SKIP_BODY;
	}

	/**
	 * 
	 * @return
	 */
	private String executePattern(String requestUri) {
		if (pattern != null && pattern.length() > 0 && requestUri != null) {
			StringTokenizer strToken = new StringTokenizer(pattern, ";, ");
			String strRegex;
			while (strToken.hasMoreTokens()) {
				strRegex = strToken.nextToken();
				if (matches(requestUri, strRegex)) {
					return generateValue();
				}
			}
		}
		return defaultText;
	}

	/**
	 * 
	 * @return
	 */
	private String executeCode(String requestUri) {
		if (code != null && code.length() > 0 && requestUri != null) {
			// +++ get url pattern
			URLPatterns urlPatterns = URLPatterns.getInstance();
			URLPattern urlPattern = urlPatterns.getURLPattern(code);
			if (logger.isDebugEnabled()) {
				logger.debug("code: " + code + ", urlPattern:" + urlPattern);
			}
			if (urlPattern != null && urlPattern.matches(requestUri)) {
				return generateValue();
			}
			// ---
		}
		return defaultText;
	}

	/**
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	private boolean matches(String str, String regex) {
		if (str != null && regex != null) {
			try {
				return str.matches(regex);
			} catch (Exception e) {
			}
			return str.equals(regex);
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	private String generateValue() {
		if (value != null && value.length() > 0) {
			return value;
		} else {
			StringBuffer strBuf = new StringBuffer();
			if (idValue != null && idValue.length() > 0) {
				strBuf.append(" id='").append(idValue).append("' ");
			}

			if (classValue != null && classValue.length() > 0) {
				strBuf.append(" class='").append(classValue).append("' ");
			}
			return strBuf.toString();
		}
	}

}
