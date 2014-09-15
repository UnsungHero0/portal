/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE          AUTHOR     DESCRIPTION
 | ------------------------------------------------
 | 11 Nov 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.tag;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author DucNM
 * 
 */
public class FormatDate {
	private static Logger logger = Logger.getLogger(FormatDate.class);

	private static final String SEMICOLON = ";";

	private static final String EQUAL = "=";

	private static Map<String, String> mapLocaleFormating = new HashMap<String, String>();

	private static void initMapping(PageContext pageContext) {
		if (mapLocaleFormating.isEmpty()) {
			String formatType = ServerConfig.getOnlineValue(Constants.IServerConfig.FORMAT_DATE);
			formatType = (formatType == null || formatType.trim().length() == 0 ? "" : formatType);
			logger.debug(" formatType: " + formatType);

			StringTokenizer strToken = new StringTokenizer(formatType, SEMICOLON);
			String strLocale = "";
			String strFormat = "";
			StringTokenizer strToken2;
			while (strToken.hasMoreTokens()) {
				strToken2 = new StringTokenizer(strToken.nextToken(), EQUAL);
				if (strToken2.hasMoreElements()) {
					strLocale = strToken2.nextToken();
					if (strToken2.hasMoreElements()) {
						strFormat = strToken2.nextToken();
					} else {
						strFormat = DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY;
					}
					mapLocaleFormating.put(strLocale, strFormat);
				}
			}
			logger.debug("mapLocaleFormating: " + mapLocaleFormating);
		}
	}

	/**
	 * 
	 * @param date
	 * @param pageContext
	 * @return
	 */
	public static String toDate(Date date, PageContext pageContext) {
		initMapping(pageContext);
		String strDate = "";
		if (date != null) {
			String locale = VNDirectWebUtilities.getCurrentLocaleCode();
			String strFormat = (String) mapLocaleFormating.get(locale);
			strFormat = (strFormat == null || strFormat.trim().length() == 0 ? DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY : strFormat);
			try {
				strDate = DateUtils.dateToString(date, strFormat);
			} catch (Exception ex) {
				logger.debug("ex: " + ex);
				strDate = "";
			}
		}
		return strDate;
	}

	/**
	 * dd/MM/yyyy HH:mm:ss
	 * 
	 * @param cal
	 * @param pageContext
	 * @return
	 */
	public static String toDateTime(Calendar cal, PageContext pageContext) {
		initMapping(pageContext);
		String strDate = "";
		if (cal != null) {
			String locale = VNDirectWebUtilities.getCurrentLocaleCode();
			String strFormat = (String) mapLocaleFormating.get(locale + "_full");
			strFormat = (strFormat == null || strFormat.trim().length() == 0 ? DateUtils.Formats.STR_DATE_TIME_FORMAT : strFormat);
			try {
				strDate = DateUtils.dateToString(cal, strFormat);
			} catch (Exception ex) {
				logger.debug("ex: " + ex);
				strDate = "";
			}
		}
		return strDate;
	}

	/**
	 * 
	 * @param cal
	 * @param pageContext
	 * @return
	 */
	public static String toDate(Calendar cal, PageContext pageContext) {
		initMapping(pageContext);
		String strDate = "";
		if (cal != null) {
			String locale = VNDirectWebUtilities.getCurrentLocaleCode();
			String strFormat = (String) mapLocaleFormating.get(locale);

			strFormat = (strFormat == null || strFormat.trim().length() == 0 ? DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY : strFormat);
			try {
				strDate = DateUtils.dateToString(cal, strFormat);
			} catch (Exception ex) {
				logger.debug("ex: " + ex);
				strDate = "";
			}
		}
		return strDate;
	}

	/**
	 * 
	 * @param cal
	 * @param pageContext
	 * @return
	 */
	public static String toDateWithoutLocale(Calendar cal, PageContext pageContext) {
		initMapping(pageContext);
		String strDate = "";
		if (cal != null) {
			try {
				strDate = DateUtils.dateToString(cal, DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
			} catch (Exception ex) {
				logger.debug("ex: " + ex);
				strDate = "";
			}
		}
		return strDate;
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static String currentDate(PageContext pageContext) {
		initMapping(pageContext);
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(Calendar.getInstance(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
		} catch (Exception ex) {
			logger.debug("ex: " + ex);
			strDate = "";
		}
		return strDate;
	}
}
