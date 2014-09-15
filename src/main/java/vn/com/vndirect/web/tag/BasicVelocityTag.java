/**
 * 
 */
package vn.com.vndirect.web.tag;

import java.io.StringReader;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.velocity.context.Context;

import vn.com.vndirect.commons.utility.I18nContext;
import vn.com.vndirect.commons.utility.URLHelper;
import vn.com.vndirect.commons.utility.VelocityTemplateUtility;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.velocity.SpringVelocityTools;
import vn.com.web.commons.velocity.VelocityHelper;

/**
 * @author Nguyen Dang Quang
 * 
 */
public abstract class BasicVelocityTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8824530063219396408L;
	private static Logger logger = Logger.getLogger(BasicVelocityTag.class);
	private static final String KEY_I18N_CONTEXT = "i18n";
	private static final String KEY_URLHELPER = "urlhelper";
	protected boolean usingCache = false;
	protected I18nContext i18nCtx = I18nContext.newInstance();

	/**
	 * @return the usingCache
	 */
	public boolean isUsingCache() {
		return usingCache;
	}

	/**
	 * @param usingCache
	 *            the usingCache to set
	 */
	public void setUsingCache(boolean usingCache) {
		this.usingCache = usingCache;
	}

	/**
	 * get real template path of static page
	 */
	protected abstract String getTemplatePath();

	/**
	 * 
	 * @param context
	 */
	protected abstract void populateContextParam(Context context) throws SystemException, FunctionalException;

	/**
	 * 
	 * @return
	 */
	public String generate() {
		return generate(null);
	}

	/**
	 * 
	 * @return static page file
	 */
	public String generate(Context ctx) {
		try {
			// +++ step 1:get template path
			String tmpFilePath = this.getTemplatePath();
			if (tmpFilePath != null && tmpFilePath.length() > 0) {
				// +++ step 2:
				StringReader source = VelocityTemplateUtility.getTemplateContent(tmpFilePath, usingCache);

				ctx = (ctx == null ? SpringVelocityTools.createContext() : ctx);
				// +++ step 3: populate context Params
				this.populateContextParam(ctx);
				ctx.put(KEY_I18N_CONTEXT, i18nCtx);
				ctx.put(KEY_URLHELPER, new URLHelper());

				// +++ step 4: generate output
				return VelocityHelper.execute(source, ctx);
			}
		} catch (Exception e) {
			logger.warn("generate()", e);
		}
		return "";
	}
}
