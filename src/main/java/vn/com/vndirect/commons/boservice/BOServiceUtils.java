/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Nov 21, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.boservice;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import vn.com.vndirect.boproxyclient.MessageStatus;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.web.commons.boservice.BOMessageKeyUtils;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.servercfg.ServerConfig;

/**
 * @author tungnq
 * 
 */
public abstract class BOServiceUtils {
	private static Logger logger = Logger.getLogger(BOServiceUtils.class);

	/**
	 * 
	 * @return
	 */
	public static Calendar getDefaultCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal;
	}

	/**
	 * back 3 months
	 * 
	 * @return
	 */
	public static Calendar getDefaultFromDate() {
		Calendar cal = Calendar.getInstance();
		int numberOfMonths = Integer.parseInt(ServerConfig
		        .getOnlineValue(Constants.IServerConfig.DefaulValueInSearchingPage.NUMBER_OF_MONTHS));
		cal.add(Calendar.MONTH, -numberOfMonths);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		/*
		 * int year = 1900;
		 * 
		 * int fromYear =
		 * ServerConfig.getOnlineIntValue(Constants.ServerConfig.Trading
		 * .Search.FROM_YEAR); year = (fromYear > 0 ? fromYear : year);
		 * 
		 * int month = 1; int date = 1; int hourOfDay = 0; int minute = 0;
		 * cal.set(year, month, date, hourOfDay, minute);
		 */
		return cal;
	}

	/**
	 * 
	 * @return
	 */
	public static Calendar getDefaultToDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		/*
		 * int year = 2099;
		 * 
		 * int toYear =
		 * ServerConfig.getOnlineIntValue(Constants.ServerConfig.Trading
		 * .Search.TO_YEAR); year = (toYear > 0 ? toYear : year);
		 * 
		 * int month = 1; int date = 1; int hourOfDay = 0; int minute = 0;
		 * cal.set(year, month, date, hourOfDay, minute);
		 */
		return cal;
	}

	/**
	 * 
	 * @param status
	 * @throws FunctionalException
	 */
	public static String processMessageStatus(MessageStatus status) throws FunctionalException {
		final String LOCATION = "processMessageStatus(...)";
		String code = (status == null ? "" : status.getCode());
		code = (code == null ? "" : code.trim());
		String message = (status == null ? "" : status.getMessage());
		message = (message == null ? "" : message.trim());
		logger.debug(LOCATION + ":: BEGIN");
		logger.debug(LOCATION + ":: code:" + code + ", msg:" + message);
		String key = null;
		FunctionalException fex = null;
		if (code.length() > 0) {
			key = BOMessageKeyUtils.getI18nKey(code);
			logger.debug(LOCATION + ":: key:" + key);
			if (key == null) {
				fex = new FunctionalException(LOCATION, message, BOMessageKeyUtils.getUnknowI18nKey(), new String[] { code,
				        message });
				fex.setErrorCode(code);
				throw fex;
			} else {
				if (isOKMessage(status) || BOMessageKeyUtils.checkIgnoreMsg(code)) {
					// Good for processing
				} else {
					fex = new FunctionalException(LOCATION, message, key);
					fex.setErrorCode(code);
					throw fex;
				}
			}
		} else {
			fex = new FunctionalException(LOCATION, message, BOMessageKeyUtils.getUnknowI18nKey(), new String[] { code, message });
			fex.setErrorCode(code);
			throw fex;
		}

		logger.debug(LOCATION + ":: END");
		return key;
	}

	/**
	 * 
	 * @param status
	 * @return
	 */
	public static String getI18nKey(MessageStatus status) {
		String code = (status == null ? "" : status.getCode());
		return BOMessageKeyUtils.getI18nKey(code == null ? "" : code.trim());
	}

	/**
	 * 
	 * @param status
	 * @return
	 */
	public static boolean isOKMessage(MessageStatus status) {
		return BOMessageKeyUtils.Code.SYSTEM_OK_CODE.equalsIgnoreCase(status == null ? "" : status.getCode());
	}

	public static void main(String[] args) {
		// System.out.println(getDefaultFromDate().getTime());
		System.out.println(getDefaultToDate().getTime());
	}
}
