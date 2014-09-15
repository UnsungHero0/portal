/**
 *
 */
package vn.com.vndirect.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.web.commons.utility.SpringUtils;

/**
 * @author tungnq.nguyen
 * 
 */
public class WpProductSubjectTag extends TagSupport implements IWebTag {
	private static Logger logger = Logger.getLogger(WpProductSubjectTag.class);

	interface MapKeys {
		String PRODUCT = "product";
		String SUBJECT = "subject";
	}

	private String productCode;

	private String subjectId;
	private String subjectCode;
	private String isUsingCache = "true";

	protected String var;
	protected String scope = REQUEST;

	public int doEndTag() throws JspException {
		// ++++ reset attribute
		var = null;
		subjectId = null;
		scope = REQUEST;
		productCode = null;

		return EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		var = (var == null ? "" : var);
		if (var.length() > 0) {
			productCode = (productCode == null ? "" : productCode.trim());
			Map<String, Object> mapContent = new HashMap<String, Object>();
			try {
				IOnlineServiceManager onlineServiceManager = (IOnlineServiceManager) SpringUtils
						.getBean(Constants.SpringBeanNames.ONLINE_SERVICE_MANAGER);

				try {
					WpSubject subject = new WpSubject();
					if (StringUtils.isNotBlank(subjectCode)) {
						subject.setSubjectCode(subjectCode);
						subject.setLanguage(I18NUtility.getCurrentLocale(pageContext.getSession()));
						WpSubject wpSubject = onlineServiceManager.getSubject(subject);
						if (wpSubject != null) {
							mapContent.put(MapKeys.SUBJECT, wpSubject);
						}
					}
				} catch (Exception e) {
					logger.error("doStartTag()", e);
					e.printStackTrace();
				}

				try {
					if (StringUtils.isNotEmpty(productCode)) {
						// +++ get product detail
						WpProduct wpProduct = new WpProduct();
						wpProduct.setProductCode(productCode);
						wpProduct.setLanguage(I18NUtility.getCurrentLocale(pageContext.getSession()));
						
						WpProduct productResult = null;
						if(Boolean.valueOf(isUsingCache)){
							productResult = onlineServiceManager.getProductByCodeWithCache(wpProduct);
						} else {
							productResult = onlineServiceManager.getProductByCode(wpProduct);
						}

						if (productResult != null) {
							mapContent.put(MapKeys.PRODUCT, productResult);
						}
					}
				} catch (Exception e) {
					logger.error("doStartTag()", e);
					e.printStackTrace();
				}
			} catch (Exception e) {

			}
			// set to val
			if (PAGE.equalsIgnoreCase(scope)) {
				pageContext.setAttribute(var, mapContent);
			} else if (REQUEST.equalsIgnoreCase(scope)) {
				pageContext.getRequest().setAttribute(var, mapContent);
			} else {
				pageContext.setAttribute(var, mapContent);
			}
			return SKIP_BODY;
		}
		return SKIP_BODY;

	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	 * @return the subjectId
	 */
	public String getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * @param subjectCode
	 *            the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getIsUsingCache() {
		return isUsingCache;
	}

	public void setIsUsingCache(String isUsingCache) {
		this.isUsingCache = isUsingCache;
	}

}
