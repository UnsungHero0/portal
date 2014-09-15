/**
 *
 */
package vn.com.vndirect.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.IfoDataRef;
import vn.com.web.commons.utility.SpringUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author tungnq.nguyen
 * 
 */
public class RefDataTag extends TagSupport implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;
	private static Logger logger = Logger.getLogger(RefDataTag.class);

	private String var;
	private String group;
	private String scope = REQUEST;
	private String lang;

	private String orderBy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// ++++ reset attribute
		var = null;
		group = null;
		scope = REQUEST;
		lang = null;

		return EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		List<IfoDataRef> listRefData = null;
		try {
			var = (var == null ? "" : var.trim());
			group = (group == null ? "" : group.trim());
			lang = (lang == null ? "" : lang.trim());
			orderBy = (orderBy == null ? "" : orderBy.trim().toUpperCase());
			if (var.length() > 0 && group.length() > 0) {
				lang = (lang.length() == 0 ? I18NUtility.getCurrentLocale(pageContext.getSession()) : lang);
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: group=" + group + " -- lang=" + lang);

				ICommonManager commonManager = (ICommonManager) SpringUtils.getBean(Constants.SpringBeanNames.COMMON_MANAGER);
				logger.debug(LOCATION + ":: commonManager = " + commonManager);
				if (commonManager != null) {
					IfoDataRef dataRef = new IfoDataRef();
					dataRef.setGroupCode(group);
					if (StringUtils.isNotEmpty(lang))
						dataRef.setLocale(lang);

					if (StringUtils.isNotEmpty(orderBy)) {
						dataRef.setOrderby(orderBy);
					}
					listRefData = commonManager.getDataRefByGroupCode(dataRef);
				}
			}
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		listRefData = (listRefData == null ? new ArrayList<IfoDataRef>() : listRefData);
		if (PAGE.equalsIgnoreCase(scope)) {
			pageContext.setAttribute(var, listRefData);
			ActionContext.getContext().getValueStack().set(var, listRefData);
			// TagUtils.getStack(pageContext).setValue(var, listRefData);
		} else {
			pageContext.getRequest().setAttribute(var, listRefData);
			ActionContext.getContext().getValueStack().set(var, listRefData);
			// TagUtils.getStack(pageContext).setValue(var, listRefData);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		return SKIP_BODY;
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
	 * @return the groupCode
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param groupCode
	 *            the groupCode to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}
}
