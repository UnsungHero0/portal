/**
 * 
 */
package vn.com.vndirect.domain.embeddb;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Blue9Frog
 * 
 */
@SuppressWarnings("serial")
public class DynamicFieldItems<T> extends HashMap<String, T> implements Map<String, T> {
	private String symbol;
	private String industryCode;

	public DynamicFieldItems() {
	}

	public DynamicFieldItems(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public T put(String key, T value) {
		key = (key == null ? "" : key.trim());
		if (key.length() > 0) {
			return super.put(key, value);
		}
		return null;
	}

	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return industryCode;
	}

	/**
	 * @param industryCode
	 *            the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
}
