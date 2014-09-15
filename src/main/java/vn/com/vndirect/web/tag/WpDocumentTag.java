/**
 *
 */
package vn.com.vndirect.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.SpringUtils;

/**
 * @author duc.nguyenminh
 * 
 */
public class WpDocumentTag extends TagSupport implements IWebTag {
	private static final long serialVersionUID = -8037269913005041825L;

	private static Logger logger = Logger.getLogger(WpDocumentTag.class);

	interface MapKeys {
		String DOCUMENT = "document";
	}

	private String productCode;
	private boolean download = false;

	protected String var;
	protected String scope = REQUEST;

	public int doEndTag() throws JspException {
		// ++++ reset attribute
		var = null;
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
				IOnlineServiceManager onlineServiceManager = (IOnlineServiceManager) SpringUtils.getBean(Constants.SpringBeanNames.ONLINE_SERVICE_MANAGER);

				try {
					if (productCode.length() > 0) {
						String locale = I18NUtility.getCurrentLocale(pageContext.getSession());
						// +++ get product detail
						WpProduct wpProduct = new WpProduct();
						wpProduct.setProductCode(productCode);
						wpProduct.setLanguage(locale);
						WpDocument wpDocument = new WpDocument();
						wpDocument.setWpProduct(wpProduct);
						wpDocument.setLanguage(locale);

						SearchResult<WpDocument> documentResult = onlineServiceManager.searchDocumentProduct(wpDocument, null);

						if (documentResult != null && documentResult.size() > 0) {
							WpDocument wpDoc = documentResult.get(VNDirectWebUtilities.getRandomDisplay(documentResult.size()));
							wpDoc.setDocumentUri(getURLResource(download, wpDoc.getDocumentUri()));
							mapContent.put(MapKeys.DOCUMENT, wpDoc);
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

	private String getURLResource(boolean download, String uri) {
		String urlResource = "";
		if (download)
			urlResource = VNDirectWebUtilities.getWebResourceDownload();
		else
			urlResource = VNDirectWebUtilities.getWebResourceDownloadThunbnail();
		if (uri.indexOf(urlResource) < 0)
			return urlResource + uri;
		else
			return uri;
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
	 * @return the download
	 */
	public boolean isDownload() {
		return download;
	}

	/**
	 * @param download
	 *            the download to set
	 */
	public void setDownload(boolean download) {
		this.download = download;
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

}
