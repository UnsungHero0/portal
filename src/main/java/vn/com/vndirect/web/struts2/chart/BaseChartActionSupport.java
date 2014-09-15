package vn.com.vndirect.web.struts2.chart;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import vn.com.vndirect.commons.jfreechart.TAConstants;

import com.opensymphony.xwork2.ActionSupport;

public class BaseChartActionSupport extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8142383837669923167L;

	private final static Logger logger = Logger.getLogger(BaseChartActionSupport.class);

	protected final static String STR_DATE_TIME_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	protected HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 
	 * @param paramName
	 * @return
	 */
	protected String getReqParamValue(String paramName) {
		return (request == null || paramName == null ? "" : request.getParameter(paramName));
	}

	/**
	 * 
	 * @return
	 */
	protected Date getStartDateDefault() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2000);

		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		return cal1.getTime();
	}

	/**
	 * 
	 * @return
	 */
	protected Date getCurrentDateDefault() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		return cal1.getTime();
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @param key
	 * @return
	 */
	protected int getInt(String key) {
		int value = 0;
		try {
			value = Integer.parseInt(this.getString(key));
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug(key + ":" + e);
		}
		return value;
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @param key
	 * @return
	 */
	protected long getLong(String key) {
		long value = 0;
		try {
			value = Long.parseLong(this.getString(key));
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug(key + ":" + e);
		}
		return value;
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @param key
	 * @return
	 */
	protected double getDouble(String key) {
		double value = 0;
		try {
			value = Double.parseDouble(this.getString(key));
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug(key + ":" + e);
		}
		return value;
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @param key
	 * @return
	 */
	protected String getString(String key) {
		try {
			String str = getReqParamValue(key);
			// logger.debug("getString(" + key + "):" + str);
			return str == null ? null : str.trim();
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug(key + ":" + e);
		}
		return "";
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @return
	 */
	protected String getUserAction() {
		return getString(TAConstants.ChartParams.USER_ACTION);
	}

	/**
	 * 
	 * @param ajaxRequest
	 * @return
	 */
	protected String getChartAction() {
		return getString(TAConstants.ChartParams.CHART_ACTION);
	}
}
