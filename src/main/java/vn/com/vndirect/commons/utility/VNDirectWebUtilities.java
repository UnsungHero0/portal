/**
 *
 */
package vn.com.vndirect.commons.utility;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author Duc Nguyen
 * 
 */
public abstract class VNDirectWebUtilities extends Utilities {

	public static AppContext getAppContext() {
		return AppContextHolder.get();
	}

	/**
	 * 
	 * @return
	 */
	public static ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	public static String getServerName() {
		try {
			return AppContextHolder.serverName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getServerNumber() {
		try {
			return AppContextHolder.serverNumber;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Locale getCurrentLocale() {
		ActionContext ctx = ActionContext.getContext();
		return ctx.getLocale();
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentLocaleCode() {
		Locale locale = getCurrentLocale();
		return (locale == null ? "" : locale.toString());
	}

	private static Random randomGenerator = new Random();

	public static int getRandomDisplay(int length) {
		int randomInt = 0;
		if (length > 0)
			randomInt = randomGenerator.nextInt(length);
		return randomInt;
	}

	private static String webResourceDownload = "";
	private static String webResourceDownloadThunbnail = "";

	public static String getWebResourceDownload() {
		if (StringUtils.isBlank(webResourceDownload)) {
			webResourceDownload = ServerConfig.getOnlineValue(Constants.IServerConfig.WEB_RESOURCE_DOWNLOAD);
		}
		return webResourceDownload;
	}

	public static String getWebResourceDownloadThunbnail() {
		if (StringUtils.isBlank(webResourceDownloadThunbnail)) {
			webResourceDownloadThunbnail = ServerConfig.getOnlineValue(Constants.IServerConfig.WEB_RESOURCE_DOWNLOAD_THUMBNAIL);
		}
		return webResourceDownloadThunbnail;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private final static String[] lowerCaseList = new String[] { "<a", "</a", "<b", "</b", "<c", "</c", "<d", "</d", "<e", "</e",
			"<f", "</f", "<g", "</g", "<h", "</h", "<i", "</i", "<k", "</k", "<l", "</l", "<m", "</m", "<n", "</n", "<o", "</o",
			"<p", "</p", "<q", "</q", "<r", "</r", "<s", "</s", "<t", "</t", "<u", "</u", "<w", "</w", "<x", "</x", "<y", "</y",
			"<z", "</z" };

	private final static String[] upperCaseList = new String[] { "<A", "</A", "<B", "</B", "<C", "</C", "<D", "</D", "<E", "</E",
			"<F", "</F", "<G", "</G", "<H", "</H", "<I", "</I", "<K", "</K", "<L", "</L", "<M", "</M", "<N", "</N", "<O", "</O",
			"<P", "</P", "<Q", "</Q", "<R", "</R", "<S", "</S", "<T", "</T", "<U", "</U", "<W", "</W", "<X", "</X", "<Y", "</Y",
			"<Z", "</Z" };

	/**
	 * Convert a utf8 String to search engine friendly URL style
	 * 
	 * @param str
	 *            utf8 String
	 * @return friendly URL
	 */

	public static String lowerCase2UpperCase(String str) {
		if (str == null) {
			return str;
		}
		// Replace all lower case character to upper case
		String temp = StringUtils.replaceEach(str, lowerCaseList, upperCaseList);
		return temp;
	}

	private final static String[] utf8List = new String[] { "\u00E0", "\u00E1", "\u1EA3", "\u00E3", "\u1EA1", "\u00C0", "\u00C1",
			"\u1EA2", "\u00C3", "\u1EA0", "\u00E2", "\u1EA7", "\u1EA5", "\u1EA9", "\u1EAB", "\u1EAD", "\u00C2", "\u1EA6",
			"\u1EA4", "\u1EA8", "\u1EAA", "\u1EAC", "\u0103", "\u1EB1", "\u1EAF", "\u1EB3", "\u1EB5", "\u1EB7", "\u0102",
			"\u1EB0", "\u1EAE", "\u1EB2", "\u1EB4", "\u1EB6", "\u0111", "\u0110", "\u00E8", "\u00E9", "\u1EBB", "\u1EBD",
			"\u1EB9", "\u00C8", "\u00C9", "\u1EBA", "\u1EBC", "\u1EB8", "\u00EA", "\u1EC1", "\u1EBF", "\u1EC3", "\u1EC5",
			"\u1EC7", "\u00CA", "\u1EC0", "\u1EBE", "\u1EC2", "\u1EC4", "\u1EC6", "\u00EC", "\u00ED", "\u1EC9", "\u0129",
			"\u1ECB", "\u00CC", "\u00CD", "\u1EC8", "\u0128", "\u1ECA", "\u00F2", "\u00F3", "\u1ECF", "\u00F5", "\u1ECD",
			"\u00D2", "\u00D3", "\u1ECE", "\u00D5", "\u1ECC", "\u00F4", "\u1ED3", "\u1ED1", "\u1ED5", "\u1ED7", "\u1ED9",
			"\u1ED2", "\u1ED2", "\u1ED0", "\u1ED4", "\u1ED6", "\u1ED8", "\u01A1", "\u1EDD", "\u1EDB", "\u1EDF", "\u1EE1",
			"\u1EE3", "\u01A0", "\u1EDC", "\u1EDA", "\u1EDE", "\u1EE0", "\u1EE2", "\u00F9", "\u00FA", "\u1EE7", "\u0169",
			"\u1EE5", "\u00D9", "\u00DA", "\u1EE6", "\u0168", "\u1EE4", "\u01B0", "\u1EEB", "\u1EE9", "\u1EED", "\u1EEF",
			"\u1EF1", "\u01AF", "\u1EEA", "\u1EE8", "\u1EEC", "\u1EEE", "\u1EF0", "\u1EF3", "\u00FD", "\u1EF7", "\u1EF9",
			"\u1EF5", "\u1EF2", "\u00DD", "\u1EF6", "\u1EF8", "\u1EF4" };

	private final static String[] asciiList = new String[] { "a", "a", "a", "a", "a", "A", "A", "A", "A", "A", "a", "a", "a",
			"a", "a", "a", "A", "A", "A", "A", "A", "A", "a", "a", "a", "a", "a", "a", "A", "A", "A", "A", "A", "A", "d", "D",
			"e", "e", "e", "e", "e", "E", "E", "E", "E", "E", "e", "e", "e", "e", "e", "e", "E", "E", "E", "E", "E", "E", "i",
			"i", "i", "i", "i", "I", "I", "I", "I", "I", "o", "o", "o", "o", "o", "O", "O", "O", "O", "O", "o", "o", "o", "o",
			"o", "o", "O", "O", "O", "O", "O", "O", "o", "o", "o", "o", "o", "o", "O", "O", "O", "O", "O", "O", "u", "u", "u",
			"u", "u", "U", "U", "U", "U", "U", "u", "u", "u", "u", "u", "u", "U", "U", "U", "U", "U", "U", "y", "y", "y", "y",
			"y", "Y", "Y", "Y", "Y", "Y" };

	/**
	 * Convert a utf8 String to search engine friendly URL style
	 * 
	 * @param str
	 *            utf8 String
	 * @return friendly URL
	 */

	public static String utf8ToAsciiUrl(String str) {
		if (str == null) {
			return str;
		}
		// Replace all utf-8 character by ascii
		String temp = StringUtils.replaceEach(str, utf8List, asciiList);

		Pattern p = Pattern.compile("[^0-9a-zA-Z]+");
		Matcher m = p.matcher(temp);

		return m.replaceAll("-");
	}

	/**
	 * 
	 * @param inString
	 * @return
	 */
	public static String removeNonUtf8CompliantCharacters(final String inString) {
		if (null == inString)
			return null;
		byte[] byteArr = inString.getBytes();
		for (int i = 0; i < byteArr.length; i++) {
			byte ch = byteArr[i];
			// remove any characters outside the valid UTF-8 range as well as
			// all control characters
			// except tabs and new lines
			if (!((ch > 31 && ch < 253) || ch == '\t' || ch == '\n' || ch == '\r')) {
				byteArr[i] = '-';
			}
		}
		return new String(byteArr);
	}

	/**
	 * 
	 * @param strUnicode
	 * @return
	 */
	public static String unicode2Utf8(String strUnicode) {
		try {
			// Convert from Unicode to UTF-8
			// String strUnicode = "\u0110\u01A1n v\u1ECB";
			byte[] utf8 = strUnicode.getBytes("UTF-8");
			// Convert from UTF-8 to Unicode
			return new String(utf8, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String getCacheKey(Object obj) {
		StringBuffer result = new StringBuffer();
		if (obj == null) {
			result.append("null");
		} else {
			Field fieldArr[] = obj.getClass().getDeclaredFields();
			for (Field field : fieldArr) {
				try {
					result.append(field.getName()).append(":").append(BeanUtils.getProperty(obj, field.getName()));
				} catch (Exception e) {
				}
			}
		}
		return "[" + result.toString() + "]";
	}

	/**
	 * @param str
	 * @return
	 */
	public static Double string2Double(String str) {
		if (StringUtils.isEmpty(str))
			return null;
		str = str.replaceAll(",", "");
		return new Double(str);
	}

	/**
	 * format of String : "1,2,3..."
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> convertStringToLongList(String str) {
		if (!StringUtils.isEmpty(str)) {
			String[] strArray = str.split(",");
			int length = strArray.length;
			List<Long> list = new ArrayList<Long>();
			for (int i = 0; i < length; i++) {
				list.add(NumberUtils.toLong(strArray[i]));
			}
			return list;
		}
		return null;
	}

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
	 * @param s
	 * @return
	 */
	public static String cleanString(String s) {
		return s == null || s.equalsIgnoreCase("NULL") ? "" : s.trim();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Long getLong(long value) {
		return (value == Long.MIN_VALUE) ? null : new Long(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Double getDouble(double value) {
		return (value == Double.MIN_VALUE) ? null : new Double(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Integer getInteger(int value) {
		return (value == Integer.MIN_VALUE) ? null : new Integer(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Double getDouble(long value) {
		return (value == Double.MIN_VALUE) ? null : new Double((double) value);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static double getDoubleValue(Double obj) {
		return (obj == null) ? 0 : obj.doubleValue();
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static long getLongValue(Long obj) {
		return (obj == null) ? 0 : obj.longValue();
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static long getLongValue(Double obj) {
		return (obj == null) ? 0 : obj.longValue();
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static int getIntValue(Integer obj) {
		return (obj == null) ? 0 : obj.intValue();
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static int getIntValue(Long obj) {
		return (obj == null) ? 0 : obj.intValue();
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static int getIntValuePositive(Long obj) {
		return (obj == null || obj.intValue() < 0 ? 0 : obj.intValue());
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static int getIntValue(Double obj) {
		return (obj == null) ? 0 : obj.intValue();
	}

	public static String toStringValue(Clob fromClob) {

		try {

			Reader reader = fromClob.getCharacterStream();
			char[] buf = new char[4000];
			StringBuffer stringBuff = new StringBuffer();

			for (;;) {
				int dataSize = reader.read(buf);
				if (dataSize == -1)
					break;
				stringBuff.append(buf, 0, dataSize);
			}

			return stringBuff.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}

		return "";
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static String getStrDoubleWithScale2(Double d) {
		return getStrDoubleWithScale(d == null ? 0 : d.doubleValue(), 2);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static String getStrDoubleWithScale2(double d) {
		return getStrDoubleWithScale(d, 2);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static String getStrDoubleWithScale(Double d, int scale) {
		return getStrDoubleWithScale(d == null ? 0 : d.doubleValue(), scale);
	}

	/**
	 * 
	 * @param d
	 * @param scale
	 * @return
	 */
	public static String getStrDoubleWithScale(double d, int scale) {
		/* build pattern */
		StringBuffer strPattern = new StringBuffer("###,##0");
		for (int i = 0; i < scale; i++) {
			strPattern.append(i == 0 ? "." : "").append("0");
		}
		return formatNumber(String.valueOf(d), strPattern.toString());
	}

	/**
	 * 
	 * @param numTxt
	 * @param pattern
	 * @return
	 */
	private static String formatNumber2(String numTxt, String pattern) {
		try {
			pattern = (pattern == null ? "###,##0.##" : pattern);
			int index = pattern.indexOf(".");
			int scale = (index > 0 ? (pattern.length() - (index + 1)) : 0);
			// System.out.println("numTxt: " + numTxt + ", pattern: " + pattern
			// + ", scale:" + scale);
			String strPattern;

			String strNumTxt1, strNumTxt2;
			int index2 = numTxt.indexOf(".");
			if (scale > 0 && index2 > 0) {
				strPattern = pattern.substring(0, index);
				strNumTxt1 = numTxt.substring(0, index2);
				strNumTxt2 = numTxt.substring(index2 + 1);

				StringBuffer strBuf = new StringBuffer();
				int txt2Length = strNumTxt2.length();
				// int size = (scale > txt2Length ? scale : txt2Length);
				for (int i = 0; i < scale; i++) {
					strBuf.append(i < txt2Length ? String.valueOf(strNumTxt2.charAt(i)) : "0");
				}
				strNumTxt2 = strBuf.toString();
			} else {
				strPattern = pattern;
				strNumTxt1 = numTxt;
				strNumTxt2 = "";
			}
			if ("-0".equals(strNumTxt1) || "0".equals(strNumTxt1) || "+0".equals(strNumTxt1)) {
				return (strNumTxt1 + (strNumTxt2.length() > 0 ? ("." + strNumTxt2) : ""));
			} else {
				BigDecimal bigDecimal = new BigDecimal(strNumTxt1);
				DecimalFormat decimalFormat = new DecimalFormat(strPattern);
				return (decimalFormat.format(bigDecimal) + (strNumTxt2.length() > 0 ? ("." + strNumTxt2) : ""));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return numTxt;
		}
	}

	public static String formatNumber(String numTxt, String pattern) {
		try {
			int indexE = numTxt.indexOf("E");
			if (indexE > 0) {
				int index = numTxt.indexOf(".");
				String strNumTxt1, strNumTxt2, strNumTxt3;
				StringBuffer strBuf1, strBuf2;
				if (index > 0 && index < indexE) {
					strNumTxt1 = numTxt.substring(0, index);
					strNumTxt2 = numTxt.substring(index + 1, indexE);
					strNumTxt3 = numTxt.substring(indexE + 1);
					int numTxt3 = string2Integer(strNumTxt3);
					int numberZero = numTxt3 - strNumTxt2.length();
					strBuf1 = new StringBuffer(strNumTxt1);
					strBuf2 = new StringBuffer(strNumTxt2);
					if (numberZero > 0) {
						for (int i = 0; i < numberZero; i++) {
							strBuf2.append("0");
						}
						strBuf1.append(strBuf2).append(".0");
					} else if (numberZero == 0) {
						strBuf1.append(strBuf2);
					} else {
						String strNumTxt4 = strNumTxt2.substring(0, numTxt3);
						String strNumTxt5 = strNumTxt2.substring(numTxt3 + 1);
						strBuf1.append(new StringBuffer(strNumTxt4)).append(".").append(new StringBuffer(strNumTxt5));
					}
					return formatNumber2(strBuf1.toString(), pattern);
				} else {
					return numTxt;
				}
			} else {
				return formatNumber2(numTxt, pattern);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			return numTxt;
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public static int string2Integer(String str) {

		str = str.replaceAll(",", "");
		return (new Integer(str)).intValue();
	}

	/**
	 * @param str
	 * @return
	 */
	public static long string2Long(String str) {

		str = str.replaceAll(",", "");
		return (new Long(str)).longValue();
	}

	/**
	 * @param number
	 * @return
	 */
	public static String getStrLong(Long number) {
		return getStrLong(number == null ? 0 : number.longValue());
	}

	/**
	 * @param number
	 * @return
	 */
	public static String getStrLong(long number) {
		if (number == 0) {
			return "0";
		}
		DecimalFormat fomatter = new DecimalFormat();
		fomatter.applyPattern("###,###,###,###,###");
		String sReturn = fomatter.format(number).toString();
		return sReturn;
	}

	public static String getHASTCIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HANOI_STC_INDEX);
	}

	public static String getHOSTCIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HCM_STC_INDEX);
	}

	public static String getVN30Index() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.VN30_INDEX);
	}

	public static String getHNX30Index() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HNX30_INDEX);
	}

	public static String getUPCOMIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.UPCOM_INDEX);
	}

	public static String getOTCIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.OTC_INDEX);
	}

	public static String getDowJoneIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.DOW_JONE_INDEX);
	}

	public static String getNasdaqIndex() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.NASDAQ_INDEX);
	}

	public static String getSP500Index() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.SP_500_INDEX);
	}

	public static String[] getIndexList() {
		String indexListStr = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.LIST_INDEX);
		return indexListStr.split(",");
	}

	public static String getHASTCExchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HANOI_STC_INDEX);
	}

	public static String getHOSTCExchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HCM_STC_INDEX);
	}

	public static String getVN30Exchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.VN30_INDEX);
	}

	public static String getHNX30Exchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HNX30_INDEX);
	}

	public static String getOTCExchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.OTC_INDEX);
	}

	public static String getUPCOMExchange() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.UPCOM_INDEX);
	}

	/**
	 * 
	 * @param fllorCode
	 * @return
	 */
	public static String getMarketIndexByFloorCode(String fllorCode) {
		switch (getMarketCodeKey(fllorCode)) {
		case MarketCodeKey.HASTC:
			return getHASTCIndex();
		case MarketCodeKey.HOSTC:
			return getHOSTCIndex();
		case MarketCodeKey.OTC:
			return getOTCIndex();
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param fllorCode
	 * @return
	 */
	public static String getExchangeByFloorCode(String fllorCode) {
		switch (getMarketCodeKey(fllorCode)) {
		case MarketCodeKey.HASTC:
			return getHASTCExchange();
		case MarketCodeKey.HOSTC:
			return getHOSTCExchange();
		case MarketCodeKey.OTC:
			return getOTCExchange();
		default:
			return null;
		}
	}

	/**
	 * 
	 * @author tungnq.nguyen
	 * 
	 */
	public interface MarketCodeKey {
		int UNKNOWN = 0;
		int HASTC = 1;
		int HOSTC = 2;
		int OTC = 3;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isMarketCode(String code) {
		switch (getMarketCodeKey(code)) {
		case MarketCodeKey.HASTC:
		case MarketCodeKey.HOSTC:
		case MarketCodeKey.OTC:
			return true;
		default:
			return false;
		}
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static int getMarketCodeKey(String code) {
		try {
			if (code != null) {
				String[] hastcFloors = getHASTCFloorCode();
				if (code.equalsIgnoreCase(getHASTCExchange()) || code.equalsIgnoreCase(getHASTCIndex())
						|| Arrays.binarySearch(hastcFloors, code) > -1) {
					return MarketCodeKey.HASTC;
				}

				String[] hostcFloors = getHOSTCFloorCode();
				if (code.equalsIgnoreCase(getHOSTCExchange()) || code.equalsIgnoreCase(getHOSTCIndex())
						|| Arrays.binarySearch(hostcFloors, code) > -1) {
					return MarketCodeKey.HOSTC;
				}

				String[] otcFloors = getOTCFloorCode();
				if (code.equalsIgnoreCase(getOTCExchange()) || code.equalsIgnoreCase(getOTCIndex())
						|| Arrays.binarySearch(otcFloors, code) > -1) {
					return MarketCodeKey.OTC;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return MarketCodeKey.UNKNOWN;
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static boolean isHASTCIndex(HttpServletRequest request) {
		return isSymbol(request, getHASTCIndex());
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static boolean isHOSTCIndex(HttpServletRequest request) {
		return isSymbol(request, getHOSTCIndex());
	}

	/**
	 * 
	 * @param pageContext
	 * @param symbol
	 * @return
	 */
	public static boolean isSymbol(HttpServletRequest request, String symbol) {
		try {
			String currentSymbol = SessionManager.getSymbolCompany();
			return symbol.equalsIgnoreCase(currentSymbol);
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Cache floor code
	 */
	private static String[] arrHASTCFloorCode = null;
	private static String[] arrHOSTCFloorCode = null;
	private static String[] arrVN30FloorCode = null;
	private static String[] arrHNX30FloorCode = null;
	private static String[] arrOTCFloorCode = null;
	private static String[] arrUPCOMFloorCode = null;

	/**
	 * 
	 * @return
	 */
	public static String[] getHASTCFloorCode() {
		if (arrHASTCFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HANOI_STC_INDEX);
			arrHASTCFloorCode = getFloorCode(str);
		}
		return arrHASTCFloorCode;
		/*
		 * String str =
		 * ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket
		 * .FloorCode.HANOI_STC_INDEX); return getFloorCode(str);
		 */
	}

	public static String[] getHOSTCFloorCode() {
		if (arrHOSTCFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HCM_STC_INDEX);
			arrHOSTCFloorCode = getFloorCode(str);
		}
		return arrHOSTCFloorCode;

		/*
		 * String str =
		 * ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket
		 * .FloorCode.HCM_STC_INDEX); return getFloorCode(str);
		 */
	}

	public static String[] getVN30FloorCode() {
		if (arrVN30FloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.VN30_INDEX);
			arrVN30FloorCode = getFloorCode(str);
		}
		return arrVN30FloorCode;

		/*
		 * String str =
		 * ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket
		 * .FloorCode.HCM_STC_INDEX); return getFloorCode(str);
		 */
	}

	public static String[] getHNX30FloorCode() {
		if (arrHNX30FloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HNX30_INDEX);
			arrHNX30FloorCode = getFloorCode(str);
		}

		return arrHNX30FloorCode;
	}

	public static String[] getOTCFloorCode() {
		if (arrOTCFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.OTC_INDEX);
			arrOTCFloorCode = getFloorCode(str);
		}
		return arrOTCFloorCode;
		/*
		 * String str =
		 * ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket
		 * .FloorCode.OTC_INDEX); return getFloorCode(str);
		 */
	}

	public static String[] getUPCOMFloorCode() {
		if (arrUPCOMFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.UPCOM_INDEX);
			arrUPCOMFloorCode = getFloorCode(str);
		}
		return arrUPCOMFloorCode;
		/*
		 * String str =
		 * ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket
		 * .FloorCode.HANOI_STC_INDEX); return getFloorCode(str);
		 */
	}

	/**
	 * 
	 * @param strFloorCode
	 * @return
	 */
	private static String[] getFloorCode(String strFloorCode) {
		StringTokenizer token = new StringTokenizer(strFloorCode, ",| ");
		List<String> list = new ArrayList<String>();
		while (token.hasMoreTokens()) {
			list.add(token.nextToken());
		}
		Collections.sort(list);
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 
	 * @return
	 */
	private static List<Integer> listResultPerPage = null;

	/**
	 * 
	 * @return
	 */
	public static List<Integer> getTradingResultPerPage() {
		if (listResultPerPage == null || listResultPerPage.size() == 0) {
			listResultPerPage = new ArrayList<Integer>();
			try {
				String str = ServerConfig.getOnlineValue(Constants.IServerConfig.Trading.RESULT_PER_PAGE);
				StringTokenizer token = new StringTokenizer(str, ";, ");
				while (token.hasMoreTokens()) {
					str = token.nextToken();
					if (str != null && str.length() > 0) {
						try {
							listResultPerPage.add(new Integer(str));
						} catch (Exception e) {
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return listResultPerPage;
	}

	/**
	 * 
	 * @return
	 */
	public static int getFistResultPerPage() {
		if (listResultPerPage != null && listResultPerPage.size() > 0) {
			return ((Integer) listResultPerPage.get(0)).intValue();
		} else {
			return 10;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static int getFistResultPerPage2() {
		if (listResultPerPage != null && listResultPerPage.size() > 0) {
			return ((Integer) listResultPerPage.get(2)).intValue();
		} else {
			return 20;
		}
	}

	/**
	 * @param exchangeCode
	 * @return
	 */
	public static boolean isOpen(String exchangeCode) {
		if (exchangeCode == null || exchangeCode.trim().length() == 0) {
			return false;
		}

		Date currentDate = VNDirectDateUtils.getCurrentDate();
		if (isHoliday(currentDate)) {
			return false;
		} else {
			MarketTime marketTime = getMarketTime(exchangeCode);
			return (marketTime == null ? false : marketTime.isBetween(currentDate));
		}
	}

	/**
	 * Get UTTB time
	 * 
	 * @return
	 */
	public static MarketTime getTransferTime() {

		String strTime;
		Calendar calOpen = null;
		Calendar calClose = null;
		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TransferTime.OPEN_TIME);
		calOpen = VNDirectDateUtils.convert2CalTime(strTime);

		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TransferTime.CLOSE_TIME);
		calClose = VNDirectDateUtils.convert2CalTime(strTime);
		calOpen = (calOpen == null ? VNDirectDateUtils.convert2CalTime("08:00:00") : calOpen);
		calClose = (calClose == null ? VNDirectDateUtils.convert2CalTime("15:30:00") : calClose);

		calOpen.set(Calendar.MILLISECOND, 0);
		calClose.set(Calendar.MILLISECOND, 0);

		return new MarketTime(calOpen, calClose);
	}

	/**
	 * 
	 * @param exchangeCode
	 * @return
	 */
	public static MarketTime getMarketTime(String exchangeCode) {
		String HOSTC = getHOSTCExchange();
		String HASTC = getHASTCExchange();

		String strTime;
		Calendar cal8 = null;
		Calendar cal11 = null;
		if (HOSTC.equalsIgnoreCase(exchangeCode)) {
			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.OPEN_TIME);
			cal8 = VNDirectDateUtils.convert2CalTime(strTime);

			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.CLOSE_TIME);
			cal11 = VNDirectDateUtils.convert2CalTime(strTime);
		} else if (HASTC.equalsIgnoreCase(exchangeCode)) {
			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.OPEN_TIME);
			cal8 = VNDirectDateUtils.convert2CalTime(strTime);

			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.CLOSE_TIME);
			cal11 = VNDirectDateUtils.convert2CalTime(strTime);
		}

		cal8 = (cal8 == null ? VNDirectDateUtils.convert2CalTime("08:00:00") : cal8);
		cal11 = (cal11 == null ? VNDirectDateUtils.convert2CalTime("11:00:00") : cal11);

		cal8.set(Calendar.MILLISECOND, 0);
		cal11.set(Calendar.MILLISECOND, 0);

		return new MarketTime(cal8, cal11);
	}

	/**
	 * get time for update or cancel sending order.
	 * 
	 * @return
	 */
	public static MarketTime getSendingUCTime() {
		String strTime;
		Calendar calOpen = null;
		Calendar calClose = null;
		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.SendingOrder.OPEN_TIME);
		calOpen = VNDirectDateUtils.convert2CalTime(strTime);

		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.SendingOrder.CLOSE_TIME);
		calClose = VNDirectDateUtils.convert2CalTime(strTime);

		return new MarketTime(calOpen, calClose);
	}

	/**
	 * get time for update or cancel sending order.
	 * 
	 * @return
	 */
	public static MarketTime getForbiddenCancelATO() {
		String strTime;
		Calendar calOpen = null;
		Calendar calClose = null;
		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.ForbiddenCancelATO.OPEN_TIME);
		calOpen = VNDirectDateUtils.convert2CalTime(strTime);

		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.ForbiddenCancelATO.CLOSE_TIME);
		calClose = VNDirectDateUtils.convert2CalTime(strTime);

		return new MarketTime(calOpen, calClose);
	}

	/**
	 * 
	 * @return
	 */
	public static MarketTime getHASTCMarketSessionTime() {
		return getMarketSessionTime(getHASTCExchange(), 0);
	}

	/**
	 * 
	 * @return
	 */
	public static MarketTime getHOSTCMarketSessionTimeP1() {
		return getHOSTCMarketSessionTime(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.NUMBER);
	}

	/**
	 * 
	 * @return
	 */
	public static MarketTime getHOSTCMarketSessionTimeP2() {
		return getHOSTCMarketSessionTime(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.NUMBER);
	}

	/**
	 * 
	 * @return
	 */
	public static MarketTime getHOSTCMarketSessionTimeP3() {
		return getHOSTCMarketSessionTime(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.NUMBER);
	}

	/**
	 * 
	 * @param sessionNo
	 * @return
	 */
	private static MarketTime getHOSTCMarketSessionTime(int sessionNo) {
		return getMarketSessionTime(getHOSTCExchange(), sessionNo);
	}

	/**
	 * 
	 * @param exchangeCode
	 * @param sessionNo
	 *            the sessionNo only use for HOSTC market <br/>
	 *            Constants.ServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.
	 *            NUMBER <br/>
	 *            Constants.ServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.
	 *            NUMBER <br/>
	 *            Constants.ServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.
	 *            NUMBER <br/>
	 * @return
	 */
	private static MarketTime getMarketSessionTime(String exchangeCode, int sessionNo) {
		MarketTime marketTime = new MarketTime();

		String HOSTC = getHOSTCExchange();
		String HASTC = getHASTCExchange();
		String strTime1 = null;
		String strTime2 = null;
		String strListOrderType = null;
		Calendar cal1 = null;
		Calendar cal2 = null;
		if (HOSTC.equalsIgnoreCase(exchangeCode)) {
			switch (sessionNo) {
			case Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.NUMBER:
				strTime1 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.OPEN_TIME;
				strTime2 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.CLOSE_TIME;
				strListOrderType = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P3.ORDER_TYPES;
				break;
			case Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.NUMBER:
				strTime1 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.OPEN_TIME;
				strTime2 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.CLOSE_TIME;
				strListOrderType = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P2.ORDER_TYPES;
				break;
			case Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.NUMBER:
			default:
				strTime1 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.OPEN_TIME;
				strTime2 = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.CLOSE_TIME;
				strListOrderType = Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.Session.P1.ORDER_TYPES;
				break;
			}
		} else if (HASTC.equalsIgnoreCase(exchangeCode)) {
			strTime1 = Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.Session.P.OPEN_TIME;
			strTime2 = Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.Session.P.CLOSE_TIME;
			strListOrderType = Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.Session.P.ORDER_TYPES;
		}

		/* get trading time */
		cal1 = VNDirectDateUtils.convert2CalTime(ServerConfig.getOnlineValue(strTime1));
		cal1 = (cal1 == null ? VNDirectDateUtils.convert2CalTime("08:00:00") : cal1);

		cal2 = VNDirectDateUtils.convert2CalTime(ServerConfig.getOnlineValue(strTime2));
		cal2 = (cal2 == null ? VNDirectDateUtils.convert2CalTime("11:00:00") : cal2);

		cal1.set(Calendar.MILLISECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		marketTime.setOpenTime(cal1);
		marketTime.setCloseTime(cal2);

		/* get order types */
		marketTime.setOrderTypes(ServerConfig.getOnlineValue(strListOrderType));

		return marketTime;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isHoliday(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return isHoliday(cal);
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isHoliday(Calendar cal) {
		String HOLIDAY = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HOLIDAY);
		// String HOLIDAY = "SATURDAY=7;SUNDAY=1";
		if (cal != null && HOLIDAY != null && HOLIDAY.length() > 0) {
			try {
				String currDayOfWeek = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));

				StringTokenizer strToken = new StringTokenizer(HOLIDAY, ";,");
				String str;
				String strTmp;
				while (strToken.hasMoreTokens()) {
					str = strToken.nextToken();
					try {
						strTmp = str.substring(str.indexOf("=") + 1);
						if (currDayOfWeek.equalsIgnoreCase(strTmp.trim())) {
							return true;
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * ATC_BOCODE = "SL";
	 * 
	 * @return
	 */
	public static String getATCBOCode() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.Trading.BOCode.ATC);
	}

	/**
	 * ATO_BOCODE = "SM";
	 * 
	 * @return
	 */
	public static String getATOBOCode() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.Trading.BOCode.ATO);
	}

	public static String getLIMITBOCode() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.Trading.BOCode.LO);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFriday(Date date) {
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return (Calendar.FRIDAY == cal.get(Calendar.DAY_OF_WEEK));
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @return
	 */
	public static boolean isOpenHOSTC() {
		String HOSTC = getHOSTCExchange();
		return isOpen(HOSTC);
	}

	/**
	 * @return
	 */
	public static boolean isOpenHASTC() {
		String HASTC = getHASTCExchange();
		return isOpen(HASTC);
	}

	// /**
	// * @param exchangeCode
	// * @return
	// */
	// public static boolean isPlaceOrderOn(String exchangeCode) {
	// if (exchangeCode == null || exchangeCode.trim().length() == 0) {
	// return false;
	// }
	// Calendar currentCal = Calendar.getInstance();
	// if(isHoliday(currentCal)) {
	// return false;
	// } else {
	// String HOSTC = getHOSTCExchange();
	// String HASTC = getHASTCExchange();
	//
	// //+++ Exclude holiday date
	// if((HOSTC.equalsIgnoreCase(exchangeCode) &&
	// TradingTimeManager.checkHOSTCUnTradingTime(currentCal))
	// || (HASTC.equalsIgnoreCase(exchangeCode) &&
	// TradingTimeManager.checkHASTCUnTradingTime(currentCal))) {
	// return false;
	// }
	// //---
	//
	// String strTime;
	// Calendar cal8 = null;
	// Calendar cal11 = null;
	// if (HOSTC.equalsIgnoreCase(exchangeCode)) {
	// strTime =
	// ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket.TradingTime.HCMSTC.PLACE_ORDER_OPEN_TIME);
	// cal8 = VNDirectDateUtils.convert2CalTime(strTime);
	//
	// strTime =
	// ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket.TradingTime.HCMSTC.PLACE_ORDER_CLOSE_TIME);
	// cal11 = VNDirectDateUtils.convert2CalTime(strTime);
	// } else if (HASTC.equalsIgnoreCase(exchangeCode)) {
	// strTime =
	// ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket.TradingTime.HanoiSTC.PLACE_ORDER_OPEN_TIME);
	// cal8 = VNDirectDateUtils.convert2CalTime(strTime);
	//
	// strTime =
	// ServerConfig.getOnlineValue(Constants.ServerConfig.VNMarket.TradingTime.HanoiSTC.PLACE_ORDER_CLOSE_TIME);
	// cal11 = VNDirectDateUtils.convert2CalTime(strTime);
	// }
	//
	// cal8 = (cal8 == null ? VNDirectDateUtils.convert2CalTime("07:45:00") :
	// cal8);
	// cal11 = (cal11 == null ? VNDirectDateUtils.convert2CalTime("11:00:00") :
	// cal11);
	//
	// cal8.set(Calendar.MILLISECOND, 0);
	//
	// cal11.set(Calendar.MILLISECOND, 0);
	//
	// return (new MarketTime(cal8, cal11)).isBetween(currentCal);
	// }
	// }
	//
	// /**
	// * @return
	// */
	// public static boolean isPlaceOrderOnHOSTC() {
	// String HOSTC = getHOSTCExchange();
	// return isPlaceOrderOn(HOSTC);
	// }
	//
	// /**
	// * @return
	// */
	// public static boolean isPlaceOrderOnHASTC() {
	// String HASTC = getHASTCExchange();
	// return isPlaceOrderOn(HASTC);
	// }

	/**
	 * 
	 * @return
	 */
	public static Date getLastTradingTime(String exchangeCode) {
		if (exchangeCode == null || exchangeCode.trim().length() == 0) {
			return null;
		}
		try {
			MarketTime marketTime = getMarketTime(exchangeCode);
			Calendar currentCal = Calendar.getInstance();
			if (!marketTime.isAfterCloseTime(currentCal)) {
				currentCal.add(Calendar.DATE, -1);
			}
			/*
			 * String HOSTC = getHOSTCExchange(); String HASTC =
			 * getHASTCExchange();
			 * 
			 * while(true) { if(isHoliday(currentCal) ||
			 * (HOSTC.equalsIgnoreCase(exchangeCode) &&
			 * TradingTimeManager.checkHOSTCUnTradingTime(currentCal)) ||
			 * (HASTC.equalsIgnoreCase(exchangeCode) &&
			 * TradingTimeManager.checkHASTCUnTradingTime(currentCal))) {
			 * currentCal.add(Calendar.DATE, -1); } else { return
			 * currentCal.getTime(); } }
			 */

			if (isHoliday(currentCal)) {
				currentCal.add(Calendar.DATE, -1);
			}

			return currentCal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * check string is null or ""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStringEmpty(String str) {
		return str == null ? true : str.length() == 0 ? true : false;
	}

	/**
	 * 
	 * @return
	 */
	public static String getTradingOnlineModel() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.DeploymentModel.TRAING_ONLINE_MODEL);
	}

	/**
	 * 
	 * @return
	 */
	public static String getWebAgentModel() {
		return ServerConfig.getOnlineValue(Constants.IServerConfig.DeploymentModel.WEBAGENT_MODEL);
	}

	/**
	 * 
	 * @param tradingDate
	 * @return
	 */
	public static boolean isFirstTradingDate(Date tradingDate) {
		if (tradingDate != null) {
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(new Date());

			Calendar firstDate = Calendar.getInstance();

			firstDate.setTime(tradingDate);
			firstDate.set(Calendar.HOUR_OF_DAY, 23);
			firstDate.set(Calendar.MINUTE, 58);
			firstDate.set(Calendar.SECOND, 58);

			return currentDate.before(firstDate);
		}
		return false;
	}

	public static PagingInfo getDefaultPagingInfo() {
		PagingInfo pagingInfo = new PagingInfo();
		// pagingInfo.setOffsetNumber(getDefaultOffsetNumber());
		pagingInfo.setOffset(getDefaultOffsetNumber());
		return pagingInfo;
	}

	public static int getDefaultOffsetNumber() {
		int offsetNumber = ServerConfig.getOnlineIntValue(Constants.IServerConfig.PagingInfo.OFFSET_NUMBER);
		return (offsetNumber < 5 ? 20 : offsetNumber);
	}

	public static boolean isSendingOrder(String orderStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.SENDING);
		return (status != null && status.equalsIgnoreCase(orderStatus));
	}

	public static boolean isModifiedOrder(String orderStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.MODIFIED);
		return (status != null && status.equalsIgnoreCase(orderStatus));
	}

	public static boolean isSentOrder(String orderStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.SENT);
		return (status != null && status.equalsIgnoreCase(orderStatus));
	}

	public static boolean isPartlyMatched(int orderQuantity, int executedQuatity, String orderStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.EXECUTED);
		return (status != null && status.equalsIgnoreCase(orderStatus) && orderQuantity > executedQuatity);
	}

	public static boolean isNewOrderPlaceBPS(String orderBPSStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.NEW_ORDER_PBS);
		return (status != null && status.equalsIgnoreCase(orderBPSStatus));
	}

	public static String getNewOrderPlaceBPS() {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.NEW_ORDER_PBS);
		return status;
	}

	public static boolean isPartlyExecutedBPS(String orderBPSStatus) {
		String status = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.Order.Status.PARTLY_EXECUTE_PBS);
		return (status != null && status.equalsIgnoreCase(orderBPSStatus));
	}

	public static boolean isOnTradingTime() {
		Calendar curCal = Calendar.getInstance();
		if (isHoliday(curCal)) {
			return false;
		}
		boolean result = getHASTCMarketSessionTime().isBetween(curCal) || getHOSTCMarketSessionTimeP1().isBetween(curCal)
				|| getHOSTCMarketSessionTimeP2().isBetween(curCal) || getHOSTCMarketSessionTimeP3().isBetween(curCal);
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isAdvancePlaceOrderTime() {
		Calendar cal = Calendar.getInstance();
		if (isHoliday(cal)) {
			return true;
		}

		String advanceTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.ADVANCE_PLACE_ORDER_TIME);
		if (cal != null && advanceTime != null && advanceTime.length() > 0) {
			try {
				StringTokenizer strToken = new StringTokenizer(advanceTime, ";");
				String str;
				String fromTime;
				String toTime;
				Calendar fromCal;
				Calendar toCal;
				while (strToken.hasMoreTokens()) {
					str = strToken.nextToken();
					try {
						fromTime = str.substring(0, str.indexOf("-"));
						toTime = str.substring(str.indexOf("-") + 1);
						fromCal = VNDirectDateUtils.convert2CalTime(fromTime);
						toCal = VNDirectDateUtils.convert2CalTime(toTime);
						if ((fromCal.before(cal) && cal.before(toCal))) {
							return true;
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isAdvancePaymentTime() {
		Calendar cal = Calendar.getInstance();
		if (isHoliday(cal)) {
			return true;
		}

		String advanceTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.PaymentAdvanceTime.PAYMENT_ADVANCE_TIME);
		if (cal != null && advanceTime != null && advanceTime.length() > 0) {
			try {
				StringTokenizer strToken = new StringTokenizer(advanceTime, ";");
				String str;
				String fromTime;
				String toTime;
				Calendar fromCal;
				Calendar toCal;
				while (strToken.hasMoreTokens()) {
					str = strToken.nextToken();
					try {
						fromTime = str.substring(0, str.indexOf("-"));
						toTime = str.substring(str.indexOf("-") + 1);
						fromCal = VNDirectDateUtils.convert2CalTime(fromTime);
						toCal = VNDirectDateUtils.convert2CalTime(toTime);
						if ((fromCal.before(cal) && cal.before(toCal))) {
							return true;
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	public static void setServiceTickets(String service, String ticket) {
		getAppContext().setServiceTicket(service, ticket);
	}

	public static String getServiceTicket(String service) {
		return getAppContext().getServiceTicket(service);
	}

	public static double roundDouble(double number, int scale) {
		BigDecimal bd = new BigDecimal(Double.toString(number));
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();

	}

	public static boolean isChartDynamicPriceTime() {
		Calendar cal = Calendar.getInstance();
		String time = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.CHART_DYNAMIC_PRICE_TIME);

		if (cal != null && time != null && time.length() > 0) {
			try {
				StringTokenizer strToken = new StringTokenizer(time, ";");
				String str;
				String fromTime;
				String toTime;
				Calendar fromCal;
				Calendar toCal;
				while (strToken.hasMoreTokens()) {
					str = strToken.nextToken();
					try {
						fromTime = str.substring(0, str.indexOf("-"));
						toTime = str.substring(str.indexOf("-") + 1);
						fromCal = VNDirectDateUtils.convert2CalTime(fromTime);
						toCal = VNDirectDateUtils.convert2CalTime(toTime);
						if ((fromCal.before(cal) && cal.before(toCal))) {
							return true;
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * check if banggia online is running
	 */
	public static boolean isDataFromDatabase() {
		if (Boolean.valueOf(ServerConfig.getOnlineValue(Constants.PriceInfo.PRICE_FROM_DATABASE))) {
			return true;
		} else {
			String time = ServerConfig.getOnlineValue(Constants.PriceInfo.RUNNING_BOARD_TIME);

			try {
				Calendar cal = Calendar.getInstance();
				StringTokenizer strToken = new StringTokenizer(time, ";");
				String str;
				String fromTime;
				String toTime;
				Calendar fromCal;
				Calendar toCal;
				while (strToken.hasMoreTokens()) {
					str = strToken.nextToken();
					try {
						fromTime = str.substring(0, str.indexOf("-"));
						toTime = str.substring(str.indexOf("-") + 1);
						fromCal = DateUtils.convert2CalTime(fromTime);
						toCal = DateUtils.convert2CalTime(toTime);
						if ((fromCal.before(cal) && cal.before(toCal))) {
							return true;
						}
					} catch (Exception e) {
						return false;
					}
				}
			} catch (Exception e) {
				return false;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		// System.out.println(removeLuceneWildCard("#hahahah"));
		/*
		 * Calendar cal = Calendar.getInstance(); cal.add(Calendar.DATE, -3);
		 * Date date; for(int i=0; i<7; i++) { date = cal.getTime();
		 * System.out.println("date:" + date);
		 * System.out.println(isHoliday(date)); cal.add(Calendar.DATE, 1); }
		 */
		// System.out.print(String.valueOf(0.018));
		String strPrice = "123.12";
		int dotIndex = strPrice.indexOf(".");
		if (dotIndex > 0) {
			try {
				strPrice = strPrice.substring(0, dotIndex + 2);
			} catch (Exception ex) {
				ex.printStackTrace();
				strPrice = strPrice + "0";
			}
		}
		System.out.println(formatNumber("1200000.0", "###,###"));

		System.out.println("hanhbq:: " + getStrDoubleWithScale(123213.088888, 1));

		// System.out.println("strPrice: " + strPrice);
		// System.out.println("Is valie: " + Validation.isNumber("12."));
		// System.out.println(".".indexOf("."));
		// System.out.println(formatNumber("-0.5", "###,###.#"));
		// System.out.println(formatNumber("-1.5", "###,###.#"));
		//
		// System.out.println(getStrDoubleWithScale(123213.018, 2));
		// System.out.println(getStrDoubleWithScale(342342.14234, 2));
		// System.out.println(getStrDoubleWithScale(43242.018, 1));
		// System.out.println(getStrDoubleWithScale(43242.018, 0));
		// System.out.println(formatNumber("1.6401828999999998E7", ""));
		// System.out.println(getStrDoubleWithScale(1.6401828999999998E7, 0));
	}

}