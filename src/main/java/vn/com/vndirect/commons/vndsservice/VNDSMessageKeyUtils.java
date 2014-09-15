package vn.com.vndirect.commons.vndsservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import vn.com.web.commons.boservice.BOMessageKey;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class VNDSMessageKeyUtils {
	private static Logger logger = Logger.getLogger(VNDSMessageKeyUtils.class);

	public interface Code {
		String UNKNOW_STATUS_CODE = "UNKNOW_STATUS";
		String SYSTEM_OK_CODE = "000000000";
	}

	private static Map mapKeys = new HashMap();
	private static String IGNORE_MESSAGES = "";

	public static void initConfig(InputStream io) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document dom = builder.build(io);
		mapKeys = loadConfig(dom);
		// System.out.println("mapKeys:" + mapKeys);
		logger.debug("+++++>>>>> mapKeys:" + mapKeys);
	}

	/**
	 * 
	 * @param secDOM
	 * @return
	 * @throws Exception
	 */
	private static Map loadConfig(Document dom) throws Exception {
		Map config = new Hashtable();
		Element elm;
		// get ignore-message
		elm = dom.getRootElement().getChild("ignore-message");
		IGNORE_MESSAGES = elm.getAttributeValue("code");

		// get all message keys
		List listModule = dom.getRootElement().getChildren("message-key");

		if (listModule != null) {
			Iterator iter = listModule.iterator();
			String code, message, i18nKey;
			BOMessageKey boMessageKey;
			while (iter.hasNext()) {
				elm = (Element) iter.next();
				code = elm.getAttributeValue("code");
				message = elm.getAttributeValue("message");
				i18nKey = elm.getAttributeValue("i18nKey");
				// System.out.println("+++++>>>>> code:" + code + ", message:" +
				// message + ", i18nKey:" + i18nKey);
				// logger.debug("+++++>>>>> code:" + code + ", message:" +
				// message + ", i18nKey:" + i18nKey);
				if (code != null) {
					boMessageKey = new BOMessageKey(code, message, i18nKey);
					config.put(code.toLowerCase(), boMessageKey);
				}
			}
		}
		return config;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static String getI18nKey(String code) {
		if (code != null && mapKeys != null) {
			BOMessageKey boMessageKey = (BOMessageKey) mapKeys.get(code.toLowerCase());
			return (boMessageKey == null ? null : boMessageKey.getI18nKey());
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static String getUnknowI18nKey() {
		return getI18nKey(Code.UNKNOW_STATUS_CODE);
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static boolean checkIgnoreMsg(String code) {
		return (IGNORE_MESSAGES != null && IGNORE_MESSAGES.indexOf("[" + code + "]") > -1);
	}
}
