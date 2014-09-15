/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 1, 2005      tungnq     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.i18n.Language;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.vndsservice.VNDSMessageKeyUtils;
import vn.com.web.commons.boservice.BOCodeMappingUtils;
import vn.com.web.commons.boservice.BOMessageKeyUtils;
import vn.com.web.commons.servercfg.ServerConfig;

public class VNDirectServletContextListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(VNDirectServletContextListener.class);

	private final static String SERVER_CONFIG_PARAM = "server-config";

	public VNDirectServletContextListener() {
		super();
	}

	/**
	 *  
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		final String LOCATION = "contextInitialized(...)";
		logger.debug(LOCATION + ":: BEGIN");
		ServletContext scxt = arg0.getServletContext();

		// Save ServletContext instance into Utility
		ServerConfig.setServletContext(scxt);

		// Init business object locator
		logger.info("Init Business Object Locator..." + arg0.getServletContext().getServletContextName());
		String basename = scxt.getInitParameter(SERVER_CONFIG_PARAM);
		if (basename.endsWith(".xml")) {
			basename = basename.substring(0, basename.length() - 4);
		}

		basename = basename.replace('/', '.');
		ServerConfig.setupOnlineResource(basename, scxt);

		// +++ init paging nuber
		try {
			String strNum = ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_PER_PAGE);
			int intNum = Integer.parseInt(strNum);
			Constants.Paging.NUMBER_ITEMS = (intNum < 1 ? 1 : intNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(LOCATION, e);
		}

		// +++ init velocity
		// try {
		// VelocityHelper.);
		// } catch (SystemException sysex) {
		// sysex.printStackTrace();
		// // logger.debug(LOCATION, sysex);
		// }
		// ---

		// +++ init uriFilterConfig
		InputStream io = null;
		/*
		 * try { String uriFilterFile = scxt.getInitParameter("uri-filter-config"); System.out.println("uriFilterFile:" + uriFilterFile); io = scxt.getResourceAsStream(uriFilterFile);
		 * URIFilters.init(io); } catch (Exception e) { e.printStackTrace(); // logger.debug("init()", e); } finally { if(io != null) { try { io.close(); } catch (Exception e){} } io = null; }
		 */
		// ---

		// +++ init BOService Message keys
		try {
			String uriBOServiceKeyFile = scxt.getInitParameter("boservice-msgkey-config");
			logger.debug("uriBOServiceKeyFile:" + uriBOServiceKeyFile);
			io = scxt.getResourceAsStream(uriBOServiceKeyFile);
			BOMessageKeyUtils.initConfig(io);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("init()", e);
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (Exception e) {
				}
			}
			io = null;
		}

		// +++ init VNDSService Message keys
		try {
			String uriVNDSServiceKeyFile = scxt.getInitParameter("vndsservice-msgkey-config");
			logger.debug("uriVNDSServiceKeyFile:" + uriVNDSServiceKeyFile);
			io = scxt.getResourceAsStream(uriVNDSServiceKeyFile);
			VNDSMessageKeyUtils.initConfig(io);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (Exception e) {
				}
			}
			io = null;
		}

		// +++ init VNDSService Message keys
		try {
			String uriBOCodeMappingFile = scxt.getInitParameter("bocode-mapping-config");
			logger.debug("uriBOCodeMappingFile:" + uriBOCodeMappingFile);
			io = scxt.getResourceAsStream(uriBOCodeMappingFile);
			BOCodeMappingUtils.initConfig(io);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (Exception e) {
				}
			}
			io = null;
		}
		
		// +++ init StaticPageUtils
		/*
		 * try { System.out.println("StaticPageUtils.init()"); StaticPageUtils.init(scxt); } catch (Exception e) { e.printStackTrace(); }
		 */

		// +++ init Language Message keys
		try {
			logger.debug("Start::initLanguageSupported()..................");
			this.initLanguageSupported();
			logger.debug("Finish::initLanguageSupported()..................");
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug(LOCATION + ":: END");
	}

	/**
	 *  
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void initLanguageSupported() throws Exception {
		Language[] langs = new Language[3];
		langs[0] = new Language(Language.convert("vn"), "Viet Nam");
		logger.debug("---> langs[" + 0 + "]: " + langs[0] + ", code: " + langs[0].getCode());
		langs[1] = new Language(Language.convert("en_GB"), "English");
		logger.debug("---> langs[" + 1 + "]: " + langs[1] + ", code: " + langs[1].getCode());
		langs[2] = new Language(Language.convert("jp"), "Japan");
		logger.debug("---> langs[" + 2 + "]: " + langs[2] + ", code: " + langs[2].getCode());
		I18NUtility.addLanguageSupported(langs);
	}
}
