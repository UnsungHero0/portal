/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Jun 12, 2007   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.commons.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class MarketTime implements Serializable {
	private Calendar openTime;
	private Calendar closeTime;

	@SuppressWarnings("rawtypes")
	private List orderTypes = new ArrayList();

	public MarketTime() {
	}

	/**
	 * 
	 * @param openTime
	 * @param closeTime
	 */
	public MarketTime(Calendar openTime, Calendar closeTime) {
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	/**
	 * 
	 * @param openTime
	 * @param closeTime
	 */
	public MarketTime(Date openTime, Date closeTime) {
		if (openTime != null) {
			this.openTime = Calendar.getInstance();
			this.openTime.setTime(openTime);
		}
		if (closeTime != null) {
			this.closeTime = Calendar.getInstance();
			this.closeTime.setTime(closeTime);
		}
	}

	/**
	 * @return the closeTime
	 */
	public Calendar getCloseTime() {
		return this.closeTime;
	}

	/**
	 * @param closeTime
	 *            the closeTime to set
	 */
	public void setCloseTime(Calendar closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * @return the openTime
	 */
	public Calendar getOpenTime() {
		return this.openTime;
	}

	/**
	 * @param openTime
	 *            the openTime to set
	 */
	public void setOpenTime(Calendar openTime) {
		this.openTime = openTime;
	}

	/**
	 * 
	 * @return
	 */
	public Date getOpenDateTime() {
		return (this.openTime == null ? null : this.openTime.getTime());
	}

	/**
	 * 
	 * @return
	 */
	public Date getCloseDateTime() {
		return (this.closeTime == null ? null : this.closeTime.getTime());
	}

	public String getStringOpenTime() {
		StringBuffer strBuf = new StringBuffer();
		if (this.openTime != null) {
			strBuf.append(formatTimeNumber(this.openTime.get(Calendar.HOUR_OF_DAY))).append(":")
			        .append(formatTimeNumber(this.openTime.get(Calendar.MINUTE))).append(":")
			        .append(formatTimeNumber(this.openTime.get(Calendar.SECOND)));
		}
		return strBuf.toString();
	}

	public String getStringCloseTime() {
		StringBuffer strBuf = new StringBuffer();
		if (this.closeTime != null) {
			strBuf.append(formatTimeNumber(this.closeTime.get(Calendar.HOUR_OF_DAY))).append(":")
			        .append(formatTimeNumber(this.closeTime.get(Calendar.MINUTE))).append(":")
			        .append(formatTimeNumber(this.closeTime.get(Calendar.SECOND)));
		}
		return strBuf.toString();
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	private String formatTimeNumber(int number) {
		return ((number < 10 ? "0" : "") + number);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public boolean isBetween(Date date) {
		if (date == null || this.openTime == null || this.closeTime == null) {
			return false;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isBetween(cal);

	}

	/**
	 * 
	 * @param cal
	 * @return
	 */
	public boolean isBetween(Calendar cal) {
		if (cal == null || this.openTime == null || this.closeTime == null) {
			return false;
		}
		return (this.openTime.before(cal) && cal.before(this.closeTime));

	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public boolean isBeforOpenTime(Date date) {
		if (date == null || this.openTime == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.before(this.openTime);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public boolean isAfterCloseTime(Date date) {
		if (date == null || this.closeTime == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isAfterCloseTime(cal);
	}

	/**
	 * 
	 * @param cal
	 * @return
	 */
	public boolean isAfterCloseTime(Calendar cal) {
		if (cal == null || this.closeTime == null) {
			return false;
		}
		return cal.after(this.closeTime);
	}

	/**
	 * @return the orderTypes
	 */
	@SuppressWarnings("unchecked")
	public String[] getOrderTypes() {
		return (String[]) this.orderTypes.toArray(new String[this.orderTypes.size()]);
	}

	/**
	 * @param orderTypes
	 *            the orderTypes to set
	 */
	public void setOrderTypes(@SuppressWarnings("rawtypes") List orderTypes) {
		this.orderTypes = orderTypes;
	}

	/**
	 * 
	 * @param strListOrderType
	 */
	@SuppressWarnings("unchecked")
	public void setOrderTypes(String strListOrderType) {
		StringTokenizer strToken = new StringTokenizer(strListOrderType == null ? "" : strListOrderType, ",; ");
		Object value;
		while (strToken.hasMoreElements()) {
			value = strToken.nextElement();
			if (!this.orderTypes.contains(value)) {
				this.orderTypes.add(value);
			}
		}
	}

	/**
	 * 
	 * @param orderType
	 * @return
	 */
	public boolean isContainOrderType(String orderType) {
		return (orderType == null ? false : this.orderTypes.contains(orderType));
	}
}
