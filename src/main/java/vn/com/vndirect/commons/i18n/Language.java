/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 7, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings("serial")
public class Language implements Serializable {
	private transient Locale locale;
	private String displayName = "";
	private String lang = "";
	private String country = "";

	public Language() {
	}

	public Language(Locale locale, String displayName) {
		this.locale = locale;
		this.displayName = displayName;
		if (this.locale != null) {
			country = this.locale.getCountry();
			lang = this.locale.getLanguage();
		}
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		if (this.locale == null) {
			if (lang != null && lang.length() > 0 && country != null && country.length() > 0) {
				locale = new Locale(lang, country);
			} else if (lang != null && lang.length() > 0) {
				locale = new Locale(lang);
			}
		}
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return convert(locale);
	}

	public String toString() {
		return "Language-[locale:" + locale + ", displayName:" + displayName + "]";
	}

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public static String convert(Locale locale) {
		if (locale == null) {
			return null;
		}
		return locale.toString();
		// String str = locale.getLanguage();
		// if(!"".equalsIgnoreCase(locale.getCountry())) {
		// str = str + "_" + locale.getCountry();
		// }
		// return str;
	}

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public static Locale convert(String locale) {
		if (locale == null) {
			return null;
		}
		try {
			StringTokenizer token = new StringTokenizer(locale, "_");
			String lang = token.nextToken();
			String contry = null;
			if (token.hasMoreTokens()) {
				contry = token.nextToken();
				return new Locale(lang, contry);
			} else {
				return new Locale(lang);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String args[]) {
		System.out.println(convert(Locale.UK));
		System.out.println(convert(Locale.JAPAN));
		System.out.println(convert("en_GB"));
		System.out.println(convert("vn"));
		System.out.println(convert("jp"));
	}
}
