/**
 * 
 */
package vn.com.vndirect.commons.utility;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;

/**
 * @author
 * 
 */
public class I18nContext {
	private transient TextProvider textProvider = null;

	/**
	 * 
	 * @return
	 */
	public static I18nContext newInstance() {
		return new I18nContext();
	}

	/**
	 * 
	 */
	private I18nContext() {
		ActionContext atx = ActionContext.getContext();
		try {
			Object objAction = atx.getActionInvocation().getAction();
			if (objAction instanceof TextProvider) {
				textProvider = (TextProvider) objAction;
			}
		} catch (Exception e) {
			textProvider = null;
		}
		final Locale currLocale = atx.getLocale();
		textProvider = (textProvider != null ? textProvider : new TextProviderFactory().createInstance(getClass(),
		        new LocaleProvider() {
			        public Locale getLocale() {
				        return currLocale;
			        }
		        }));
	}

	/**
	 * 
	 * @param aTextName
	 * @return i18n message
	 */
	public String getText(String aTextName) {
		return textProvider == null ? "" : textProvider.getText(aTextName);
	}

	/**
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @return i18n message
	 */
	public String getText(String aTextName, String defaultValue) {
		return textProvider == null ? "" : textProvider.getText(aTextName, defaultValue);
	}

	/**
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @param obj
	 * @return i18n message
	 */
	public String getText(String aTextName, String defaultValue, String obj) {
		return textProvider == null ? "" : textProvider.getText(aTextName, defaultValue, obj);
	}

	/**
	 * 
	 * @param key
	 * @param args
	 * @return i18n message
	 */
	public String getText(String key, String[] args) {
		return textProvider == null ? "" : textProvider.getText(key, args);
	}

	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @param args
	 * @return
	 */
	public String getText(String key, String defaultValue, String[] args) {
		return textProvider == null ? "" : textProvider.getText(key, defaultValue, args);
	}

	// Search Engine friendly text
	/**
	 * 
	 * @param aTextName
	 * @return i18n message
	 */
	public String getSEText(String aTextName) {
		return VNDirectWebUtilities.utf8ToAsciiUrl(getText(aTextName));
	}

	/**
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @return i18n message
	 */
	public String getSEText(String aTextName, String defaultValue) {
		return VNDirectWebUtilities.utf8ToAsciiUrl(getText(aTextName, defaultValue));
	}

	/**
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @param obj
	 * @return i18n message
	 */
	public String getSEText(String aTextName, String defaultValue, String obj) {
		return VNDirectWebUtilities.utf8ToAsciiUrl(getText(aTextName, defaultValue, obj));
	}

	/**
	 * 
	 * @param key
	 * @param args
	 * @return i18n message
	 */
	public String getSEText(String key, String[] args) {
		return VNDirectWebUtilities.utf8ToAsciiUrl(getText(key, args));
	}

	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @param args
	 * @return
	 */
	public String getSEText(String key, String defaultValue, String[] args) {
		return VNDirectWebUtilities.utf8ToAsciiUrl(getText(key, defaultValue, args));
	}

}
