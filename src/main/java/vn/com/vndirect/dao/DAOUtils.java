/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Oct 6, 2005   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.dao;

/**
 * @author tungnq
 * 
 */
public class DAOUtils {
	/**
	 * Replace all occurences of a substring in a string by new substring.
	 * 
	 * @param sourceString
	 * @param strOldValue
	 * @param strNewValue
	 * @return new String after replacing the old one with the new one; return
	 *         null if any paremeter is null; return empty string if any error;
	 *         return original string if no match found
	 */
	public static String replaceString(String sourceString, String strOldValue, String strNewValue) {
		try {
			// check for null arguments
			if (sourceString == null || strOldValue == null || strNewValue == null) {
				return sourceString;
			}
			int iBeginIndex;
			int iOldValLen = strOldValue.length();
			String strTemp;
			StringBuffer strReturn = new StringBuffer();

			while ((iBeginIndex = sourceString.indexOf(strOldValue)) != -1) {
				strTemp = sourceString.substring(0, iBeginIndex);
				strReturn.append(strTemp).append(strNewValue);
				sourceString = sourceString.substring(iBeginIndex + iOldValLen);
			}
			strReturn.append(sourceString);
			return strReturn.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static Double valueOfDouble(String d) {
		if (d != null) {
			try {
				return Double.valueOf(d);
			} catch (Exception e) {
			}
		}
		return new Double(0);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static Long valueOfLong(String d) {
		if (d != null) {
			try {
				return Long.valueOf(d);
			} catch (Exception e) {
			}
		}
		return new Long(0);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Double getDouble(double value) {
		return (value == Double.MIN_VALUE) ? null : new Double(value);
	}
}
