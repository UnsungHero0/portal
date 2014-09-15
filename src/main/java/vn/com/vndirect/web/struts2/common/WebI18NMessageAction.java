/**
 * 
 */
package vn.com.vndirect.web.struts2.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;

import com.opensymphony.xwork2.Action;

/**
 * @author tungnq.nguyen
 * 
 */
public class WebI18NMessageAction implements Action, ServletContextAware {
	private static Logger logger = Logger.getLogger(WebI18NMessageAction.class);

	private ServletContext context = null;

	private final static String URI_PATTERN = "/js/i18n.jquery/";
	private final static String JS = ".js";

	private InputStream inputStream = null;
	private String filename = "messages_";

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		try {
			return (inputStream == null ? new ByteArrayInputStream(new byte[0]) : inputStream);
		} catch (Exception e) {
			logger.debug("getInputStream()", e);
			return new ByteArrayInputStream(new byte[0]);
		}
	}

	public String execute() throws Exception {
		final String LOCATION = "execute()";
		try {
			String lang = VNDirectWebUtilities.getCurrentLocale().toString();
			filename = filename + lang + JS;
			String path = URI_PATTERN + filename;
			logger.debug(LOCATION + ":: path:" + path);
			inputStream = context.getResourceAsStream(path);
		} catch (Exception e) {
			logger.debug(LOCATION, e);
		}
		return SUCCESS;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
