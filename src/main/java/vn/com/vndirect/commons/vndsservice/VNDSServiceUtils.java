package vn.com.vndirect.commons.vndsservice;

import org.apache.log4j.Logger;

import vn.com.vndirect.wsclient.MessageStatus;
import vn.com.web.commons.exception.FunctionalException;

/**
 * @author nqtung
 * 
 */
public abstract class VNDSServiceUtils {
	private static Logger logger = Logger.getLogger(VNDSServiceUtils.class);

	/**
	 * 
	 * @param status
	 * @throws FunctionalException
	 */
	public static void processMessageStatus(MessageStatus status) throws FunctionalException {
		final String LOCATION = "processMessageStatus(...)";
		String code = (status == null ? "" : status.getCode());
		code = (code == null ? "" : code.trim());
		String message = (status == null ? "" : status.getMessage());
		message = (message == null ? "" : message.trim());
		logger.debug(LOCATION + ":: BEGIN");
		logger.debug(LOCATION + ":: code:" + code + ", msg:" + message);

		FunctionalException fex = null;
		if (code.length() > 0) {
			String key = VNDSMessageKeyUtils.getI18nKey(code);
			logger.debug(LOCATION + ":: key:" + key);
			if (key == null) {
				fex = new FunctionalException(LOCATION, message, VNDSMessageKeyUtils.getUnknowI18nKey(), new String[] { code,
				        message });
				fex.setErrorCode(code);
				throw fex;
			} else {
				if (VNDSMessageKeyUtils.Code.SYSTEM_OK_CODE.equalsIgnoreCase(code) || VNDSMessageKeyUtils.checkIgnoreMsg(code)) {
					// Good for processing
				} else {
					fex = new FunctionalException(LOCATION, message, key);
					fex.setErrorCode(code);
					throw fex;
				}
			}
		} else {
			fex = new FunctionalException(LOCATION, message, VNDSMessageKeyUtils.getUnknowI18nKey(),
			        new String[] { code, message });
			fex.setErrorCode(code);
			throw fex;
		}

		logger.debug(LOCATION + ":: END");
	}
}
