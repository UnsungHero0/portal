package vn.com.vndirect.commons.utility;

import java.util.Date;

import javax.persistence.Query;

import vn.com.web.commons.domain.db.PagingInfo;

public abstract class DBUtils {
	public static final String WILDCARD = "%";

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String applyWildcard(String str) {
		str = (str == null ? "" : str.trim());
		return (str.endsWith(WILDCARD) ? str : str + WILDCARD);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String applyFreeSearch(String str) {
		StringBuilder strBuf = new StringBuilder();
		str = (str == null ? "" : str.trim());
		strBuf.append(str.startsWith(WILDCARD) ? "" : WILDCARD).append(str).append(str.endsWith(WILDCARD) ? "" : WILDCARD);
		return strBuf.toString();
	}

	/**
	 * 
	 * @param query
	 * @param pagingInfo
	 */
	public static void addPaging(Query query, PagingInfo pagingInfo) {
		if (query != null && pagingInfo != null) {
			query.setFirstResult(pagingInfo.getIndex());
			query.setMaxResults(pagingInfo.getOffset());
		}
	}

	/**
	 * 
	 * @param pagingInfo
	 * @param objTotal
	 */
	public static void setTotalValue(PagingInfo pagingInfo, Object objTotal) {
		if (pagingInfo != null && objTotal != null) {
			if (objTotal instanceof Long) {
				pagingInfo.setTotal(((Long) objTotal).longValue());
			} else if (objTotal instanceof Integer) {
				pagingInfo.setTotal(((Integer) objTotal).longValue());
			}
		}
	}

	/**
	 * 
	 * @param date
	 * @param defaultDate
	 * @return
	 */
	public static Date getDate(Date date, Date defaultDate) {
		return (date == null ? defaultDate : date);
	}

	/**
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Integer getNumber(Integer value, Integer defaultValue) {
		return (value == null ? defaultValue : value);
	}

	/**
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Long getNumber(Long value, Long defaultValue) {
		return (value == null ? defaultValue : value);
	}

	/**
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Double getNumber(Double value, Double defaultValue) {
		return (value == null ? defaultValue : value);
	}
}