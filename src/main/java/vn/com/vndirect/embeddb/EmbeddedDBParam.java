/**
 * 
 */
package vn.com.vndirect.embeddb;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

/**
 * @author Blue9Frog
 * 
 */
public class EmbeddedDBParam implements InitializingBean, ServletContextAware {
	private static final Log log = LogFactory.getLog(EmbeddedDBParam.class);

	private String initDBScriptPath;
	private String cleanDBScriptPath;
	private String itemcodeMappingPath;

	private String initOption = "";
	private String defaultLang = "VN";
	private boolean rebuild = true;

	private ServletContext _ctx;

	public void afterPropertiesSet() throws Exception {
		if (_ctx != null) {
			initDBScriptPath = _ctx.getRealPath(initDBScriptPath);
			cleanDBScriptPath = _ctx.getRealPath(cleanDBScriptPath);
			itemcodeMappingPath = _ctx.getRealPath(itemcodeMappingPath);

		}
		log.debug("+++++>>>>> initDBScriptPath:" + initDBScriptPath);
		log.debug("+++++>>>>> cleanDBScriptPath:" + cleanDBScriptPath);
		log.debug("+++++>>>>> itemcodeMappingPath:" + itemcodeMappingPath);
		log.debug("+++++>>>>> initOption:" + initOption);
		log.debug("+++++>>>>> defaultLang:" + initOption);
	}

	public void setServletContext(ServletContext ctx) {
		this._ctx = ctx;
	}

	/**
	 * @param initOption
	 *            the initOption to set
	 */
	public void setInitOption(String initOption) {
		this.initOption = initOption;
	}

	/**
	 * @param initDBScriptPath
	 *            the initDBScriptPath to set
	 */
	public void setInitDBScriptPath(String initDBScriptPath) {
		this.initDBScriptPath = initDBScriptPath;
	}

	/**
	 * @param cleanDBScriptPath
	 *            the cleanDBScriptPath to set
	 */
	public void setCleanDBScriptPath(String cleanDBScriptPath) {
		this.cleanDBScriptPath = cleanDBScriptPath;
	}

	/**
	 * @return the initDBScriptPath
	 */
	public String getInitDBScriptPath() {
		return initDBScriptPath;
	}

	/**
	 * @return the cleanDBScriptPath
	 */
	public String getCleanDBScriptPath() {
		return cleanDBScriptPath;
	}

	/**
	 * @return the initOption
	 */
	public String getInitOption() {
		return initOption;
	}

	/**
	 * @return the defaultLang
	 */
	public String getDefaultLang() {
		return defaultLang;
	}

	/**
	 * @param defaultLang
	 *            the defaultLang to set
	 */
	public void setDefaultLang(String defaultLang) {
		this.defaultLang = defaultLang;
	}

	/**
	 * @return the rebuild
	 */
	public boolean isRebuild() {
		return rebuild;
	}

	/**
	 * @param rebuild
	 *            the rebuild to set
	 */
	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
	}

	/**
	 * @return the itemcodeMappingPath
	 */
	public String getItemcodeMappingPath() {
		return itemcodeMappingPath;
	}

	/**
	 * @param itemcodeMappingPath
	 *            the itemcodeMappingPath to set
	 */
	public void setItemcodeMappingPath(String itemcodeMappingPath) {
		this.itemcodeMappingPath = itemcodeMappingPath;
	}
}
