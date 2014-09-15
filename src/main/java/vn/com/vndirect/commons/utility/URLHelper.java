package vn.com.vndirect.commons.utility;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.UrlHelper;

import vn.com.web.commons.utility.Utilities;

public class URLHelper {
	/**
	 * 
	 * @param action
	 * @return
	 */
	public String buildURL(String action) {
		return UrlHelper.buildUrl(action, ServletActionContext.getRequest(), ServletActionContext.getResponse(), null);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String formatUrl(String url) {
		return rewriteUrl(url, true);
	}

	/**
	 * 
	 * @param url
	 * @param rewrite
	 * @return
	 */
	public String rewriteUrl(String url, boolean rewrite) {
		if (rewrite && url.indexOf(".shtml") > 0) {
			ServletContext ctx = ServletActionContext.getServletContext();
			String context = ctx.getContextPath();
			String str = Utilities.FormatURL.url(context, url);
			return Utilities.FormatURL.encodeURI(str, ServletActionContext.getRequest());
		}
		return url;
	}
}
