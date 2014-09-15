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
public class DynamicMetaTable extends HashMap<String, String> implements Map<String, String> {
	public final static String PREFIX = "F";
	private String tableName;

	public DynamicMetaTable() {
	}

	public DynamicMetaTable(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String generateFieldName(String key) {
		return PREFIX + (key == null ? "" : key.trim());
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static boolean checkPrefix(String key) {
		if (key != null) {
			return key.toUpperCase().startsWith(PREFIX);
		}
		return false;
	}

}
