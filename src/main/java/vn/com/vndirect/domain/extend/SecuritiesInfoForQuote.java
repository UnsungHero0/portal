/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 10 Oct 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.BaseBean;
import vn.com.vndirect.wsclient.streamquotes.OtcInfo;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author DucNM
 * 
 */
@SuppressWarnings("serial")
public class SecuritiesInfoForQuote extends BaseBean {

	private int timeType = 0;
	private int colourStyle = 0; // 1: red, 2: green, default: yellow
	private String exchangeCode = "";

	// securities info
	private String strReferencePrice = "";
	private String strCeilingPrice = "";
	private String strFloorPrice = "";
	private String strTotalVolumn = "";
	private String strOpenPrice = "";
	private String strClosePrice = "";
	private String strTodayChange = "";
	private String strTodayChangePercent = "";

	// securities info detail
	private String strBidP1 = "";
	private String strBidQ1 = "";
	private String strBidP2 = "";
	private String strBidQ2 = "";
	private String strBidP3 = "";
	private String strBidQ3 = "";

	private String strAskP1 = "";
	private String strAskQ1 = "";
	private String strAskP2 = "";
	private String strAskQ2 = "";
	private String strAskP3 = "";
	private String strAskQ3 = "";

	private String strClosedP = "";
	private String strClosedQ = "";

	private String strSeesion1P = "";
	private String strSeesion1Q = "";
	private String strSeesion2P = "";
	private String strSeesion2Q = "";

	private String strRecentTransactionP = "";
	private String strRecentTransactionQ = "";

	private String strBidVolume = "";
	private String strAskVolume = "";
	private String strTransactedVolume = "";
	private String strHighestPrice = "";
	private String strLowestPrice = "";
	private String strNetVolumn = "";
	private String strAveragePrice = "";

	List<OtcInfo> otcInfoList = new ArrayList<OtcInfo>();

	double currentPrice = 0;
	double todayChangePrice = 0;

	private String strMarketIndex = "";
	private String strTransactionPrice = "";
	private String strTransactionVolume = "";
	private String str52WeekHighestPrice = "";
	private String str52WeekLowestPrice = "";

	/**
	 * @return the timeType
	 */
	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int time) {
		this.timeType = time;
	}

	public int getColourStyle() {
		return colourStyle;
	}

	public void setColourStyle(int colourStyle) {
		this.colourStyle = colourStyle;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String symbol) {
		this.exchangeCode = symbol;
	}

	public String getStrAskP1() {
		if (strAskP1 == null)
			return "";
		String str = strAskP1.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strAskP1;
	}

	public void setStrAskP1(String strAskP1) {
		this.strAskP1 = strAskP1;
	}

	public String getStrAskP2() {
		if (strAskP2 == null)
			return "";
		String str = strAskP2.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strAskP2;
	}

	public void setStrAskP2(String strAskP2) {
		this.strAskP2 = strAskP2;
	}

	public String getStrAskP3() {
		if (strAskP3 == null)
			return "";
		String str = strAskP3.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strAskP3;
	}

	public void setStrAskP3(String strAskP3) {
		this.strAskP3 = strAskP3;
	}

	public String getStrAskQ1() {
		if (strAskQ1 == null)
			return "";
		String str = strAskQ1.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strAskQ1, "###,###,###,##0");
		} catch (Exception e) {
			return strAskQ1;
		}
	}

	public void setStrAskQ1(String strAskQ1) {
		this.strAskQ1 = strAskQ1;
	}

	public String getStrAskQ2() {
		if (strAskQ2 == null)
			return "";
		String str = strAskQ2.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strAskQ2, "###,###,###,##0");
		} catch (Exception e) {
			return strAskQ2;
		}
	}

	public void setStrAskQ2(String strAskQ2) {
		this.strAskQ2 = strAskQ2;
	}

	public String getStrAskQ3() {
		if (strAskQ3 == null)
			return "";
		String str = strAskQ3.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strAskQ3, "###,###,###,##0");
		} catch (Exception e) {
			return strAskQ3;
		}
	}

	public void setStrAskQ3(String strAskQ3) {
		this.strAskQ3 = strAskQ3;
	}

	public String getStrAskVolume() {
		return strAskVolume;
	}

	public void setStrAskVolume(String strAskVolume) {
		this.strAskVolume = strAskVolume;
	}

	public String getStrAveragePrice() {
		return strAveragePrice;
	}

	public void setStrAveragePrice(String strAveragePrice) {
		this.strAveragePrice = strAveragePrice;
	}

	public String getStrBidP1() {
		if (strBidP1 == null)
			return "";
		String str = strBidP1.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strBidP1;
	}

	public void setStrBidP1(String strBidP1) {
		this.strBidP1 = strBidP1;
	}

	public String getStrBidP2() {
		if (strBidP2 == null)
			return "";
		String str = strBidP2.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strBidP2;
	}

	public void setStrBidP2(String strBidP2) {
		this.strBidP2 = strBidP2;
	}

	public String getStrBidP3() {
		if (strBidP3 == null)
			return "";
		String str = strBidP3.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strBidP3;
	}

	public void setStrBidP3(String strBidP3) {
		this.strBidP3 = strBidP3;
	}

	public String getStrBidQ1() {
		if (strBidQ1 == null)
			return "";
		String str = strBidQ1.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strBidQ1, "###,###,###,##0");
		} catch (Exception e) {
			return strBidQ1;
		}

	}

	public void setStrBidQ1(String strBidQ1) {
		this.strBidQ1 = strBidQ1;
	}

	public String getStrBidQ2() {
		if (strBidQ2 == null)
			return "";
		String str = strBidQ2.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strBidQ2, "###,###,###,##0");
		} catch (Exception e) {
			return strBidQ2;
		}
	}

	public void setStrBidQ2(String strBidQ2) {
		this.strBidQ2 = strBidQ2;
	}

	public String getStrBidQ3() {
		if (strBidQ3 == null)
			return "";
		String str = strBidQ3.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strBidQ3, "###,###,###,##0");
		} catch (Exception e) {
			return strBidQ3;
		}
	}

	public void setStrBidQ3(String strBidQ3) {
		this.strBidQ3 = strBidQ3;
	}

	public String getStrBidVolume() {
		return strBidVolume;
	}

	public void setStrBidVolume(String strBidVolume) {
		this.strBidVolume = strBidVolume;
	}

	public String getStrCeilingPrice() {
		return strCeilingPrice;
	}

	public void setStrCeilingPrice(String strCeilingPrice) {
		this.strCeilingPrice = strCeilingPrice;
	}

	public String getStrClosedP() {
		if (strClosedP == null)
			return "";
		String str = strClosedP.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strClosedP;
	}

	public void setStrClosedP(String strClosedP1) {
		this.strClosedP = strClosedP1;
	}

	public String getStrClosedQ() {
		if (strClosedQ == null)
			return "";
		String str = strClosedQ.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strClosedQ, "###,###,###,##0");
		} catch (Exception e) {
			return strClosedQ;
		}
	}

	public void setStrClosedQ(String strClosedQ1) {
		this.strClosedQ = strClosedQ1;
	}

	public String getStrClosePrice() {
		return strClosePrice;
	}

	public void setStrClosePrice(String strClosePrice) {
		this.strClosePrice = strClosePrice;
	}

	public String getStrFloorPrice() {
		return strFloorPrice;
	}

	public void setStrFloorPrice(String strFloorPrice) {
		this.strFloorPrice = strFloorPrice;
	}

	public String getStrHighestPrice() {
		return strHighestPrice;
	}

	public void setStrHighestPrice(String strHighestPrice) {
		this.strHighestPrice = strHighestPrice;
	}

	public String getStrLowestPrice() {
		return strLowestPrice;
	}

	public void setStrLowestPrice(String strLowestPrice) {
		this.strLowestPrice = strLowestPrice;
	}

	public String getStrNetVolumn() {
		return strNetVolumn;
	}

	public void setStrNetVolumn(String strNetPrice) {
		this.strNetVolumn = strNetPrice;
	}

	public String getStrOpenPrice() {
		return strOpenPrice;
	}

	public void setStrOpenPrice(String strOpenPrice) {
		this.strOpenPrice = strOpenPrice;
	}

	public String getStrRecentTransactionP() {
		return strRecentTransactionP;
	}

	public void setStrRecentTransactionP(String strRecentTransactionP) {
		this.strRecentTransactionP = strRecentTransactionP;
	}

	public String getStrRecentTransactionQ() {
		return strRecentTransactionQ;
	}

	public void setStrRecentTransactionQ(String strRecentTransactionQ) {
		this.strRecentTransactionQ = strRecentTransactionQ;
	}

	public String getStrReferencePrice() {
		return strReferencePrice;
	}

	public void setStrReferencePrice(String strReferencePrice) {
		this.strReferencePrice = strReferencePrice;
	}

	public String getStrSeesion1P() {
		if (strSeesion1P == null)
			return "";
		String str = strSeesion1P.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strSeesion1P;
	}

	public void setStrSeesion1P(String strSeesion1P1) {
		this.strSeesion1P = strSeesion1P1;
	}

	public String getStrSeesion1Q() {
		if (strSeesion1Q == null)
			return "";
		String str = strSeesion1Q.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strSeesion1Q, "###,###,###,##0");
		} catch (Exception e) {
			return strSeesion1Q;
		}
	}

	public void setStrSeesion1Q(String strSeesion1Q1) {
		this.strSeesion1Q = strSeesion1Q1;
	}

	public String getStrSeesion2P() {
		if (strSeesion2P == null)
			return "";
		String str = strSeesion2P.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return strSeesion2P;
	}

	public void setStrSeesion2P(String strSeesion2P1) {
		this.strSeesion2P = strSeesion2P1;
	}

	public String getStrSeesion2Q() {
		if (strSeesion2Q == null)
			return "";
		String str = strSeesion2Q.replaceAll(",", "");
		try {
			if ((new Double(str)).doubleValue() == 0) {
				return "";
			}
			return VNDirectWebUtilities.formatNumber(strSeesion2Q, "###,###,###,##0");
		} catch (Exception e) {
			return strSeesion2Q;
		}
	}

	public void setStrSeesion2Q(String strSeesion2Q1) {
		this.strSeesion2Q = strSeesion2Q1;
	}

	public String getStrTodayChange() {
		return strTodayChange;
	}

	public void setStrTodayChange(String strTodayChange) {
		this.strTodayChange = strTodayChange;
	}

	public String getStrTodayChangePercent() {
		return strTodayChangePercent;
	}

	public void setStrTodayChangePercent(String strTodayChangePercent) {
		this.strTodayChangePercent = strTodayChangePercent;
	}

	public String getStrTotalVolumn() {
		return strTotalVolumn;
	}

	public void setStrTotalVolumn(String strTotalVolumn) {
		this.strTotalVolumn = strTotalVolumn;
	}

	public String getStrTransactedVolume() {
		return strTransactedVolume;
	}

	public void setStrTransactedVolume(String strTransactedVolume) {
		this.strTransactedVolume = strTransactedVolume;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getStrMarketIndex() {
		return strMarketIndex;
	}

	public void setStrMarketIndex(String strIndexPrice) {
		this.strMarketIndex = strIndexPrice;
	}

	public String getStr52WeekHighestPrice() {
		return str52WeekHighestPrice;
	}

	public void setStr52WeekHighestPrice(String str52WeekHighestPrice) {
		this.str52WeekHighestPrice = str52WeekHighestPrice;
	}

	public String getStr52WeekLowestPrice() {
		return str52WeekLowestPrice;
	}

	public void setStr52WeekLowestPrice(String str52WeekLowestPrice) {
		this.str52WeekLowestPrice = str52WeekLowestPrice;
	}

	public String getStrTransactionPrice() {
		return strTransactionPrice;
	}

	public void setStrTransactionPrice(String strTransactionPrice) {
		this.strTransactionPrice = strTransactionPrice;
	}

	public String getStrTransactionVolume() {
		return strTransactionVolume;
	}

	public void setStrTransactionVolume(String strTransactionVolume) {
		this.strTransactionVolume = strTransactionVolume;
	}

	public double getTodayChangePrice() {
		return todayChangePrice;
	}

	public void setTodayChangePrice(double todayChangePrice) {
		this.todayChangePrice = todayChangePrice;
	}

	public List<OtcInfo> getOtcInfoList() {
		return otcInfoList;
	}

	public void setOtcInfoList(List<OtcInfo> otcInfoList) {
		this.otcInfoList = otcInfoList;
	}

	public String getStrDate(OtcInfo otcInfo) {
		try {
			return DateUtils.dateToString(otcInfo.getTransDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
		} catch (Exception e) {
		}
		return "";
	}
}
