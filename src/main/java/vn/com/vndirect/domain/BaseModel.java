/**
 * 
 */
package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.BeansException;
import org.springframework.util.StringValueResolver;

import vn.com.vndirect.commons.utility.LocaleUtil;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.MapWebCache;
import vn.com.web.commons.utility.SpringUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {
	protected MapWebCache mapWebCache = new MapWebCache();
	private String callbackFunc;
	private String callerKey;
	private String callerValue;

	private String pageTitle;
	private String pageDescription;
	private String pageKeywords;

	private ArrayList<String> breadcrumbs;

	private PagingInfo pagingInfo = new PagingInfo();
	
	private String symbol;
	
	private String locale;
	
	
	/**
	 * 
	 * @param value
	 */
	public void setKeepThis(String value) {
		// nothing
	}

	/**
	 * @param cacheData
	 *            the cacheData to set
	 */
	public final void setCacheData(String cacheData) {
		mapWebCache.loadCache(cacheData);
	}

	/**
	 * 
	 * @return
	 */
	public final String getCacheData() {
		return mapWebCache.doCache();
	}

	/**
	 * @return the callbackFunc
	 */
	public String getCallbackFunc() {
		return (callbackFunc == null || callbackFunc.length() == 0 ? null : callbackFunc);
	}

	/**
	 * @param callbackFunc
	 *            the callbackFunc to set
	 */
	public void setCallbackFunc(String callbackFunc) {
		this.callbackFunc = (callbackFunc == null ? null : callbackFunc.trim());
	}

	/**
	 * @return the callerKey
	 */
	public String getCallerKey() {
		return callerKey;
	}

	/**
	 * @param callerKey
	 *            the callerKey to set
	 */
	public void setCallerKey(String callerKey) {
		this.callerKey = callerKey;
	}

	/**
	 * @return the callerValue
	 */
	public String getCallerValue() {
		return callerValue;
	}

	/**
	 * @param callerValue
	 *            the callerValue to set
	 */
	public void setCallerValue(String callerValue) {
		this.callerValue = callerValue;
	}

	/**
	 * add a Object to cache and pass to web client
	 * 
	 * @param key
	 * @param value
	 */
	public final void addToCache(Object key, Object value) {
		mapWebCache.addToCache((String) key, value);
	}

	public final Object getFromCache(Object key) {
		return mapWebCache.getFromCache((String) key);
	}

	public final void removeFromCache(Object key) {
		mapWebCache.removeFromCache(key);
	}

	public final void clearAllCache() {
		mapWebCache.clearAllCache();
	}

	/**
	 * 
	 * @param request
	 */
	public final String doCache() {
		return mapWebCache.doCache();
	}

	/**
	 * 
	 * @param request
	 */
	public final void loadCache(String cacheData) {
		mapWebCache.loadCache(cacheData);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * @param pagingInfo
	 *            the pagingInfo to set
	 */
	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	/**
	 * @return the pagingInfo
	 */
	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public String getPageKeywords() {
		return pageKeywords;
	}

	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
	}

	public ArrayList<String> getBreadcrumbs() {
		return breadcrumbs;
	}

	public void setBreadcrumbs(ArrayList<String> breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getLocale() {
		String localeKey = ActionContext.getContext().getLocale().toString();
		LocaleUtil localeUtil = new LocaleUtil();
		locale = localeUtil.getLocaleISO6391().get(localeKey);
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
