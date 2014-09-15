/**
 * 
 */
package vn.com.vndirect.web.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.utility.Utilities;

/**
 * @author tungnq.nguyen
 * 
 */
public class ViewContentTag extends TagSupport implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;

	private String productCode;
	private boolean onlySubject = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {

		try {
			StringBuffer strNavBuf = new StringBuffer();

			if (StringUtils.isNotBlank(productCode)) {
				WpProduct wpProduct = new WpProduct();
				wpProduct.setProductCode(productCode);
				wpProduct.setLanguage(I18NUtility.getCurrentLocale(pageContext.getSession()));

				IOnlineServiceManager onlineServiceManager = (IOnlineServiceManager) SpringUtils.getBean(Constants.SpringBeanNames.ONLINE_SERVICE_MANAGER);
				WpProduct productResult = onlineServiceManager.getProductByCode(wpProduct);
				if (productResult != null) {
					List<WpSubject> subjectList = productResult.getWpSubjectList();
					WpSubject obj = null;

					if (onlySubject) {
						if (subjectList != null && subjectList.size() > 0) {
							obj = subjectList.get(0);
							strNavBuf.append("<p class=\"Title-general\"><b>");
							strNavBuf.append(obj.getSubjectName());
							strNavBuf.append("</b></p>");
							strNavBuf.append(obj.getSubjectContent());
						}
					} else {
						strNavBuf.append(productResult.getProductOverview());
						if (subjectList != null && subjectList.size() > 0) {
							ServletContext ctx = this.pageContext.getServletContext();
							String context = ctx.getContextPath();
							String str = Utilities.FormatURL.url(context, "/home/service/viewContentDetail.shtml");

							// if(str.indexOf(".shtml") > 0) {
							// str = Utilities.FormatURL.encodeURI(str, (HttpServletRequest)pageContext.getRequest());
							// }
							str += "?subjectId=";

							strNavBuf.append("<ul id=\"ulnews\">");
							for (int i = 0; i < subjectList.size(); i++) {
								obj = subjectList.get(i);
								strNavBuf.append("<li><a href=\"").append(str).append(obj.getSubjectId()).append("\">");
								strNavBuf.append(obj.getSubjectName());
								strNavBuf.append("</a></li>");
							}
							strNavBuf.append("</ul>");
						}
					}
				}
			}
			// System.out.println("strNavBuf.toString(): " + strNavBuf.toString());

			pageContext.getOut().print(strNavBuf.toString());
		} catch (Exception e) {
			System.out.println("\n\n Error: " + e);
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
	 * @return the onlySubject
	 */
	public boolean isOnlySubject() {
		return onlySubject;
	}

	/**
	 * @param onlySubject
	 *            the onlySubject to set
	 */
	public void setOnlySubject(boolean onlySubject) {
		this.onlySubject = onlySubject;
	}
}
