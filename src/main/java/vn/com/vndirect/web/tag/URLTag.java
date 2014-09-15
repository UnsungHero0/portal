/**
 *
 */
package vn.com.vndirect.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.web.commons.utility.Utilities;

/**
 * @author tungnq.nguyen
 * 
 */
public class URLTag extends TagSupport implements IWebTag {
	/**
     *
     */
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(URLTag.class);

	protected String var;
	protected String scope = REQUEST;
	protected String value;

	protected boolean rewrite = true;

	// process request's params
	protected boolean includeParam = false;
	protected String removeParams = "";

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
	 * @return the rewrite
	 */
	public boolean getRewrite() {
		return rewrite;
	}

	/**
	 * @param rewrite
	 *            the rewrite to set
	 */
	public void setRewrite(boolean rewrite) {
		this.rewrite = rewrite;
	}

	/**
	 * @param includeParam
	 *            the includeParam to set
	 */
	public void setIncludeParam(boolean includeParam) {
		this.includeParam = includeParam;
	}

	/**
	 * @param removeParams
	 *            the removeParams to set
	 */
	public void setRemoveParams(String removeParams) {
		this.removeParams = removeParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		String str = null;
		try {
			ServletContext ctx = this.pageContext.getServletContext();
			String context = ctx.getContextPath();
			str = Utilities.FormatURL.url(context, value);

			// if(rewrite && URLUtils.checkExt(str, ".shtml")) {
			// str = Utilities.FormatURL.encodeURI(str, (HttpServletRequest)pageContext.getRequest());
			// }
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
		try {
			str = (str == null ? "" : processParam(str, (HttpServletRequest) pageContext.getRequest(), includeParam, removeParams));
			var = (var == null ? "" : var);
			if (var.length() > 0) {
				if (PAGE.equalsIgnoreCase(scope)) {
					pageContext.setAttribute(var, str);
				} else if (REQUEST.equalsIgnoreCase(scope)) {
					pageContext.getRequest().setAttribute(var, str);
				} else {
					pageContext.setAttribute(var, str);
				}
			} else {
				pageContext.getOut().print(str);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String processParam(String url, HttpServletRequest request, boolean includeParam, String removeParams) {
		if (includeParam) {
			Map<String, String> params = new HashMap<String, String>();
			String strKey;
			for (Object key : request.getParameterMap().keySet()) {
				strKey = (String) key;
				if (removeParams.indexOf(strKey + ";") < 0) {
					params.put(strKey, request.getParameter(strKey));
				}
			}
			url = Utilities.FormatURL.addPram(url, params);
		}
		return url;
	}
}
