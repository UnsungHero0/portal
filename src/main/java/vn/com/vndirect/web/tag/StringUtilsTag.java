/**
 * 
 */
package vn.com.vndirect.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author tungnq.nguyen
 * 
 */
public class StringUtilsTag extends TagSupport implements IWebTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3480064686583764391L;
	private String var;
	private String scope = PAGE;
	private String str;
	private String str1;
	private String str2;

	// +++ support compare between 2 strings
	private boolean compare;
	// +++
	private boolean toLowerCase;
	// +++
	private boolean concat;

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var
	 *            the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str
	 *            the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * @return the compare
	 */
	public boolean isCompare() {
		return compare;
	}

	/**
	 * @param compare
	 *            the compare to set
	 */
	public void setCompare(boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the strComp
	 */
	public String getStr1() {
		return str1;
	}

	/**
	 * @param strComp
	 *            the strComp to set
	 */
	public void setStr1(String strComp) {
		this.str1 = strComp;
	}

	/**
	 * @return the toLowerCase
	 */
	public boolean isToLowerCase() {
		return toLowerCase;
	}

	/**
	 * @param toLowerCase
	 *            the toLowerCase to set
	 */
	public void setToLowerCase(boolean toLowerCase) {
		this.toLowerCase = toLowerCase;
	}

	/**
	 * @return the concat
	 */
	public boolean isConcat() {
		return concat;
	}

	/**
	 * @param concat
	 *            the concat to set
	 */
	public void setConcat(boolean concat) {
		this.concat = concat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Object rs = null;

		str = (str == null ? "" : str);
		if (this.compare) {
			str1 = (str1 == null ? "" : str1);
			rs = new Boolean(str.equalsIgnoreCase(str1));
		} else if (this.concat) {
			rs = (str == null ? "" : str.trim()) + (str1 == null ? "" : str1.trim()) + (str2 == null ? "" : str2.trim());
		} else {
			if (this.toLowerCase) {
				rs = str.toLowerCase();
			} else {
				rs = str.toUpperCase();
			}
		}

		if (rs != null) {
			if (PAGE.equalsIgnoreCase(scope)) {
				pageContext.setAttribute(var, rs);
				ActionContext.getContext().getValueStack().set(var, rs);
			} else {
				pageContext.getRequest().setAttribute(var, rs);
				ActionContext.getContext().getValueStack().set(var, rs);
			}
		}
		return SKIP_BODY;
	}

	/**
	 * @return the str2
	 */
	public String getStr2() {
		return str2;
	}

	/**
	 * @param str2
	 *            the str2 to set
	 */
	public void setStr2(String str2) {
		this.str2 = str2;
	}
}
