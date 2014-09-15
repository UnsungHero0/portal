/**
 * 
 */
package vn.com.vndirect.commons.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author tungnq.nguyen
 * 
 */
public abstract class MarketCodeUtils {
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
		int UPCOM = 4;
		int VN30 = 5;
		int HNX30 = 6;
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
		case MarketCodeKey.VN30:
			return getVN30Index();
		case MarketCodeKey.HNX30:
			return getHNX30Index();
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
		case MarketCodeKey.VN30:
			return getVN30Exchange();
		case MarketCodeKey.HNX30:
			return getHNX30Exchange();
		case MarketCodeKey.OTC:
			return getOTCExchange();
		case MarketCodeKey.UPCOM:
			return getUPCOMExchange();
		default:
			return null;
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
				// +++ check HA
				String[] hastcFloors = getHASTCFloorCode();
				if (code.equalsIgnoreCase(getHASTCExchange()) || code.equalsIgnoreCase(getHASTCIndex())
				        || Arrays.binarySearch(hastcFloors, code) > -1) {
					return MarketCodeKey.HASTC;
				}

				// +++ check HO
				String[] hostcFloors = getHOSTCFloorCode();
				if (code.equalsIgnoreCase(getHOSTCExchange()) || code.equalsIgnoreCase(getHOSTCIndex())
				        || Arrays.binarySearch(hostcFloors, code) > -1) {
					return MarketCodeKey.HOSTC;
				}

				// +++ check HO
				String[] vn30Floors = getVN30FloorCode();
				if (code.equalsIgnoreCase(getVN30Exchange()) || code.equalsIgnoreCase(getVN30Index())
				        || Arrays.binarySearch(vn30Floors, code) > -1) {
					return MarketCodeKey.VN30;
				}

				// +++ check HNX
				String[] hnx30Floors = getHNX30FloorCode();
				if (code.equalsIgnoreCase(getHNX30Exchange()) || code.equalsIgnoreCase(getHNX30Index())
				        || Arrays.binarySearch(hnx30Floors, code) > -1) {
					return MarketCodeKey.HNX30;
				}

				// +++ check OTC
				String[] otcFloors = getOTCFloorCode();
				if (code.equalsIgnoreCase(getOTCExchange()) || code.equalsIgnoreCase(getOTCIndex())
				        || Arrays.binarySearch(otcFloors, code) > -1) {
					return MarketCodeKey.OTC;
				}

				// +++ check UPCOM
				String[] upcomFloors = getUPCOMFloorCode();
				if (code.equalsIgnoreCase(getUPCOMExchange()) || code.equalsIgnoreCase(getUPCOMIndex())
				        || Arrays.binarySearch(upcomFloors, code) > -1) {
					return MarketCodeKey.UPCOM;
				}
			}
		} catch (Exception e) {
		}

		return MarketCodeKey.UNKNOWN;
	}

	/*
	 * Cache exchange code
	 */
	private static String HA_EX_INDEX = null;
	private static String HO_EX_INDEX = null;
	private static String VN30_EX_INDEX = null;
	private static String HNX30_EX_INDEX = null;
	private static String OTC_EX_INDEX = null;
	private static String UPCOM_EX_INDEX = null;

	/**
	 * 
	 * @return
	 */
	public static String getHASTCExchange() {
		if (HA_EX_INDEX == null) {
			HA_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HANOI_STC_INDEX);
		}
		return HA_EX_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHOSTCExchange() {
		if (HO_EX_INDEX == null) {
			HO_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HCM_STC_INDEX);
		}
		return HO_EX_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getVN30Exchange() {
		if (VN30_EX_INDEX == null) {
			VN30_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.VN30_INDEX);
		}
		return VN30_EX_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHNX30Exchange() {
		if (HNX30_EX_INDEX == null) {
			HNX30_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.HNX30_INDEX);
		}
		return HNX30_EX_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getOTCExchange() {
		if (OTC_EX_INDEX == null) {
			OTC_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.OTC_INDEX);
		}
		return OTC_EX_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getUPCOMExchange() {
		if (UPCOM_EX_INDEX == null) {
			UPCOM_EX_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.ExchangeCode.UPCOM_INDEX);
		}
		return UPCOM_EX_INDEX;
	}

	/*
	 * Cache index code
	 */
	private static String HA_INDEX = null;
	private static String HO_INDEX = null;
	private static String VN30_INDEX = null;
	private static String HNX30_INDEX = null;
	private static String OTC_INDEX = null;
	private static String UPCOM_INDEX = null;

	/**
	 * 
	 * @return
	 */
	public static String getHASTCIndex() {
		if (HA_INDEX == null) {
			HA_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HANOI_STC_INDEX);
		}
		return HA_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHOSTCIndex() {
		if (HO_INDEX == null) {
			HO_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HCM_STC_INDEX);
		}
		return HO_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getVN30Index() {
		if (VN30_INDEX == null) {
			VN30_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.VN30_INDEX);
		}
		return VN30_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHNX30Index() {
		if (HNX30_INDEX == null) {
			HNX30_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.HNX30_INDEX);
		}
		return HNX30_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getOTCIndex() {
		if (OTC_INDEX == null) {
			OTC_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.OTC_INDEX);
		}
		return OTC_INDEX;
	}

	/**
	 * 
	 * @return
	 */
	public static String getUPCOMIndex() {
		if (UPCOM_INDEX == null) {
			UPCOM_INDEX = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.IndexCode.UPCOM_INDEX);
		}
		return UPCOM_INDEX;
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
	}

	/**
	 * 
	 * @return
	 */
	public static String[] getHOSTCFloorCode() {
		if (arrHOSTCFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HCM_STC_INDEX);
			arrHOSTCFloorCode = getFloorCode(str);
		}
		return arrHOSTCFloorCode;
	}

	/**
	 * 
	 * @return
	 */
	public static String[] getVN30FloorCode() {
		if (arrVN30FloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.VN30_INDEX);
			arrVN30FloorCode = getFloorCode(str);
		}
		return arrVN30FloorCode;
	}

	/**
	 * 
	 * @return
	 */
	public static String[] getHNX30FloorCode() {
		if (arrHNX30FloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HNX30_INDEX);
			arrHNX30FloorCode = getFloorCode(str);
		}
		return arrHNX30FloorCode;
	}

	/**
	 * 
	 * @return
	 */
	public static String[] getOTCFloorCode() {
		if (arrOTCFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.OTC_INDEX);
			arrOTCFloorCode = getFloorCode(str);
		}
		return arrOTCFloorCode;
	}

	/**
	 * 
	 * @return
	 */
	public static String[] getUPCOMFloorCode() {
		if (arrUPCOMFloorCode == null) {
			String str = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.UPCOM_INDEX);
			arrUPCOMFloorCode = getFloorCode(str);
		}
		return arrUPCOMFloorCode;
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

	// TODO
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
			cal8 = DateUtils.convert2CalTime(strTime);

			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HCMSTC.CLOSE_TIME);
			cal11 = DateUtils.convert2CalTime(strTime);
		} else if (HASTC.equalsIgnoreCase(exchangeCode)) {
			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.OPEN_TIME);
			cal8 = DateUtils.convert2CalTime(strTime);

			strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.TradingTime.HanoiSTC.CLOSE_TIME);
			cal11 = DateUtils.convert2CalTime(strTime);
		}

		cal8 = (cal8 == null ? DateUtils.convert2CalTime("08:00:00") : cal8);
		cal11 = (cal11 == null ? DateUtils.convert2CalTime("11:00:00") : cal11);

		cal8.set(Calendar.MILLISECOND, 0);
		cal11.set(Calendar.MILLISECOND, 0);

		return new MarketTime(cal8, cal11);
	}

	/**
	 * @param exchangeCode
	 * @return
	 */
	public static boolean isOpen(String exchangeCode) {
		if (exchangeCode == null || exchangeCode.trim().length() == 0) {
			return false;
		}

		Date currentDate = DateUtils.getCurrentDate();
		if (isHoliday(currentDate)) {
			return false;
		} else {
			MarketTime marketTime = getMarketTime(exchangeCode);
			return (marketTime == null ? false : marketTime.isBetween(currentDate));
		}
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
		// String UPCOM = getUPCOMExchange();
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
		cal1 = DateUtils.convert2CalTime(ServerConfig.getOnlineValue(strTime1));
		cal1 = (cal1 == null ? DateUtils.convert2CalTime("08:00:00") : cal1);

		cal2 = DateUtils.convert2CalTime(ServerConfig.getOnlineValue(strTime2));
		cal2 = (cal2 == null ? DateUtils.convert2CalTime("11:00:00") : cal2);

		cal1.set(Calendar.MILLISECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		marketTime.setOpenTime(cal1);
		marketTime.setCloseTime(cal2);

		/* get order types */
		marketTime.setOrderTypes(ServerConfig.getOnlineValue(strListOrderType));

		return marketTime;
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
	 * get time for update or cancel sending order.
	 * 
	 * @return
	 */
	public static MarketTime getSendingUCTime() {
		String strTime;
		Calendar calOpen = null;
		Calendar calClose = null;
		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.SendingOrder.OPEN_TIME);
		calOpen = DateUtils.convert2CalTime(strTime);

		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.SendingOrder.CLOSE_TIME);
		calClose = DateUtils.convert2CalTime(strTime);

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
		calOpen = DateUtils.convert2CalTime(strTime);

		strTime = ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.UpdateCancelTime.ForbiddenCancelATO.CLOSE_TIME);
		calClose = DateUtils.convert2CalTime(strTime);

		return new MarketTime(calOpen, calClose);
	}

	/**
	 * Get the 1st session market is specified in server-config.xml Its
	 * information as follows: &lt;OpenTime&gt;08:00:00&lt;/OpenTime&gt;<br/>
	 * &lt;CloseTime&gt;11:30:00&lt;/CloseTime&gt;<br/>
	 * &lt;OrderTypes&gt;LO&lt;/OrderTypes&gt;
	 * 
	 * @return <code>MarketTime</code>
	 */
	// public static MarketTime getUPCOMMarketSessionTimeP1() {
	// return getMarketSessionTime(getUPCOMExchange(),
	// Constants.ServerConfig.VNMarket.TradingTime.UPCOM.Session.P1.NUMBER);
	// }

	/**
	 * Get the 2nd session market is specified in server-config.xml Its
	 * information as follows: &lt;OpenTime&gt;11:30:00&lt;/OpenTime&gt;<br/>
	 * &lt;CloseTime&gt;15:00:00&lt;/CloseTime&gt;<br/>
	 * &lt;OrderTypes&gt;LO&lt;/OrderTypes&gt;
	 * 
	 * @return <code>MarketTime</code>
	 */
	// public static MarketTime getUPCOMMarketSessionTimeP2() {
	// return getMarketSessionTime(getUPCOMExchange(),
	// Constants.ServerConfig.VNMarket.TradingTime.UPCOM.Session.P2.NUMBER);
	// }
}
