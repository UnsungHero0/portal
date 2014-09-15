package vn.com.vndirect.commons.utility;

import java.util.Calendar;
import java.util.Date;

import vn.com.web.commons.utility.DateUtils;

public class VNDirectDateUtils extends DateUtils {

	/**
	 * check date is today
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return isToday(cal);
		}
		return false;
	}

	/**
	 * Check calendar is today
	 * 
	 * @param cal
	 * @return
	 */
	public static boolean isToday(Calendar cal) {
		if (cal != null) {
			Calendar curCal = Calendar.getInstance();
			cal.setTimeZone(curCal.getTimeZone());
			return cal.get(Calendar.YEAR) == curCal.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == curCal.get(Calendar.MONTH)
			        && cal.get(Calendar.DATE) == curCal.get(Calendar.DATE);
		}
		return false;
	}
}
