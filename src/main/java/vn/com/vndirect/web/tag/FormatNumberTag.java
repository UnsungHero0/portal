/**
 * 
 */
package vn.com.vndirect.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.web.commons.utility.NumberFormatter;

/**
 * @author Duc Nguyen
 * 
 */
public class FormatNumberTag extends TagSupport {
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(FormatNumberTag.class);

	private String value;
	/** pattern: "#,###", "#,##0", "#,##0.##", "#,##0.0#", "#,##0.00" */
	private String pattern;
	/** type: "CURRENCY", "NUMBER", "PERCENT" */
	private String type;

	private interface FormatType {
		String CURRENCY = "CURRENCY";
		// String NUMBER = "NUMBER";
		String PERCENT = "PERCENT";
	}

	public int doStartTag() throws JspException {

		try {
			String result = "";
			if (StringUtils.isEmpty(pattern)) {
				pattern = NumberFormatter.Pattern.PATTERN_1;
			}
			if (FormatType.PERCENT.equalsIgnoreCase(type)) {
				result = NumberFormatter.format(value, NumberFormatter.Type.PERCENT, pattern);
			} else if (FormatType.CURRENCY.equalsIgnoreCase(type)) {
				result = NumberFormatter.format(value, NumberFormatter.Type.CURRENCY, pattern);
			} else {
				result = NumberFormatter.format(value, NumberFormatter.Type.NUMBER, pattern);
			}
			pageContext.getOut().print(result);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("doStartTag()", e);
			}
		}
		return SKIP_BODY;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
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
