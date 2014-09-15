package vn.com.vndirect.commons.utility;

import java.util.Map;

public class LocaleUtil {
	public static Map<String, String> localeISO6391;

	public static Map<String, String> getLocaleISO6391() {
		return localeISO6391;
	}

	public static void setLocaleISO6391(Map<String, String> localeISO6391) {
		LocaleUtil.localeISO6391 = localeISO6391;
	}
}
