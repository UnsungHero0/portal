/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| June 08, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.i18n;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.interceptor.I18nInterceptor;

public class I18NUtility {
	private static Logger logger = Logger.getLogger(I18NUtility.class);

	private static Map<String, Language> mapLanguageSupported = new Hashtable<String, Language>();

	/**
	 * 
	 * @param session
	 * @return
	 */
	private static Locale getLocaleFromSession(HttpSession session) {
		if (session != null) {
			Object sessionLocale = session.getAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
			if (sessionLocale != null && sessionLocale instanceof Locale) {
				return (Locale) sessionLocale;
			}
		}
		return null;

		// try {
		// ActionContext ctx = ActionContext.getContext();
		// locale = ctx.getLocale();
		// } catch (Exception e) {
		// logger.debug("getCurrentLanguage()" + e);
		// locale = null;
		// }
	}

	/**
	 * 
	 * @param session
	 * @param locale
	 */
	private static void setLocaleToSession(HttpSession session, Locale locale) {
		if (session != null && locale != null) {
			session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
		}

		// try {
		// ActionContext ctx = ActionContext.getContext();
		// ctx.setLocale(locale);
		// } catch (Exception e) {
		// logger.debug("setDefaultLanguage()" + e);
		// }
	}

	/**
	 *
	 */
	public static Language setDefaultLanguage(HttpSession session) {
		Locale locale = new Locale("vn");
		Language lang = new Language(locale, "Viet Nam");

		setLocaleToSession(session, locale);

		if (!checkLanguageSupported(lang.getLocale())) {
			addLanguageSupported(lang);
		}
		return lang;
	}

	/**
	 * 
	 * @param langCode
	 */
	public static boolean setCurrentLanguageByCode(String langCode, HttpSession session) {
		Language lang = getLanguageSupportedByCode(langCode);
		if (lang != null && lang.getLocale() != null) {
			setLocaleToSession(session, lang.getLocale());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public static Language getCurrentLanguage(HttpSession session) {
		Locale locale = getLocaleFromSession(session);
		Language lang = null;
		if (locale != null) {
			lang = getLanguageSupportedByCode(Language.convert(locale));
		} else {
			lang = setDefaultLanguage(session);
		}

		return lang;
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentLocale(HttpSession session) {
		Language lang = getCurrentLanguage(session);
		return (lang == null ? "" : lang.getCode());
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentLocale() {
		Language lang = getCurrentLanguage(ServletActionContext.getRequest().getSession());
		return (lang == null ? "" : lang.getCode());
	}

	/**
	 * 
	 * @param lang
	 */
	public static void addLanguageSupported(Language lang) {
		String langCode = (lang == null ? null : Language.convert(lang.getLocale()));
		if (langCode != null && !mapLanguageSupported.containsKey(langCode)) {
			mapLanguageSupported.put(langCode, lang);
		}
	}

	/**
	 * 
	 * @param langs
	 */
	public static void addLanguageSupported(Language[] langs) {
		if (langs != null && langs.length > 0) {
			String langCode = null;
			int i, size = langs.length;
			for (i = 0; i < size; i++) {
				langCode = (langs[i] == null ? null : langs[i].getCode());
				if (langCode != null && !mapLanguageSupported.containsKey(langCode)) {
					mapLanguageSupported.put(langCode, langs[i]);
				}
			}
		}
	}

	/**
	 * 
	 * @return collection of Language object.
	 */
	public static Collection<Language> getLanguageSupported() {
		return (mapLanguageSupported == null ? new ArrayList<Language>() : mapLanguageSupported.values());
	}

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public static boolean checkLanguageSupported(Locale locale) {
		return checkLanguageSupported(Language.convert(locale));
	}

	/**
	 * 
	 * @param langCode
	 * @return
	 */
	public static boolean checkLanguageSupported(String langCode) {
		if (langCode != null) {
			return (mapLanguageSupported != null && mapLanguageSupported.get(langCode) != null);
		}
		return false;
	}

	/**
	 * 
	 * @param langCode
	 * @return
	 */
	public static Language getLanguageSupportedByCode(String langCode) {
		if (langCode != null) {
			return (mapLanguageSupported != null ? (Language) mapLanguageSupported.get(langCode) : null);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static Locale getUserLocale(HttpSession session) {
		Language lang = getCurrentLanguage(session);
		return (lang == null ? null : lang.getLocale());
	}

	/**
	 * 
	 * @param request
	 */
	public static void setI18NContext(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();

			Locale userLocale = I18NUtility.getUserLocale(session);
			if (userLocale == null) {
				// +++ retrieve user locale from cookie
				String langCookie = I18NUtility.getLocaleFromCookie(request);
				// System.out.println("--->>> Lang from Cookie: " + langCookie);
				// ---
				boolean rs = false;

				if (langCookie != null && langCookie.length() > 0) {
					rs = I18NUtility.setCurrentLanguageByCode(langCookie, session);
				}
				if (!rs) {
					I18NUtility.setDefaultLanguage(session);
				}
				userLocale = I18NUtility.getUserLocale(session);
			}
		} catch (Exception e) {
			logger.info("setI18NContext", e);
		}
	}

	// +++++
	private static String LANGUAGE_COOKIE = "LANGUAGE_COOKIE";

	/**
     *
     */
	public static void setLocaleToCookie(HttpServletResponse response, HttpSession session) {
		try {
			String lang = I18NUtility.getCurrentLocale(session);
			lang = (lang == null ? "" : lang.trim());
			Cookie cookie = new Cookie(LANGUAGE_COOKIE, lang);
			cookie.setPath("/");
			cookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(cookie);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getLocaleFromCookie(HttpServletRequest request) {
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (LANGUAGE_COOKIE.equalsIgnoreCase(cookie.getName())) {
						return cookie.getValue();
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
}
