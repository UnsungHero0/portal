/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 13 Oct 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.BaseBean;
import vn.com.web.commons.servercfg.ServerConfig;

/**
 * @author DucNM
 * 
 */
public class FinanceReportForQuote extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845531134853737876L;

	private Long companyId;
	private String reportType;
	private String strFiscalDate1 = "";
	private String strFiscalDate2 = "";
	private String strFiscalDate3 = "";
	private String strFiscalDate4 = "";
	private String strFiscalDate5 = "";
	private String currencyCode = "";
	private String itemName = "";
	private String strNumericValue1 = "";
	private String strNumericValue2 = "";
	private String strNumericValue3 = "";
	private String strNumericValue4 = "";
	private String strNumericValue5 = "";
	private Long displayOrder;
	private int displayLevel;
	private String locale;

	private final String QUARTER = ServerConfig.getOnlineValue(Constants.IServerConfig.ReportType.QUARTER);
	private final String month123 = "01,02,03";
	private final String month456 = "04,05,06";
	private final String month789 = "07,08,09";
	private final String month101112 = "10,11,12";
	private final String Q1 = "Q1";
	private final String Q2 = "Q2";
	private final String Q3 = "Q3";
	private final String Q4 = "Q4";
	private boolean hasChild;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getQUARTER() {
		return QUARTER;
	}

	public String getMonth123() {
		return month123;
	}

	public String getMonth456() {
		return month456;
	}

	public String getMonth789() {
		return month789;
	}

	public String getMonth101112() {
		return month101112;
	}

	public String getQ1() {
		return Q1;
	}

	public String getQ2() {
		return Q2;
	}

	public String getQ3() {
		return Q3;
	}

	public String getQ4() {
		return Q4;
	}

	public void setStrFiscalDate1(String strFiscalDate1) {
		this.strFiscalDate1 = strFiscalDate1;
	}

	public void setStrFiscalDate2(String strFiscalDate2) {
		this.strFiscalDate2 = strFiscalDate2;
	}

	public void setStrFiscalDate3(String strFiscalDate3) {
		this.strFiscalDate3 = strFiscalDate3;
	}

	public void setStrFiscalDate4(String strFiscalDate4) {
		this.strFiscalDate4 = strFiscalDate4;
	}

	public void setStrFiscalDate5(String strFiscalDate5) {
		this.strFiscalDate5 = strFiscalDate5;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCurrencyCode() {
		return currencyCode == null ? "" : currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(int displayLevel) {
		this.displayLevel = displayLevel;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getItemName() {
		return itemName == null ? "" : itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLocale() {
		return locale == null ? "" : locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getReportType() {
		return reportType == null ? "" : reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getStrFiscalDate1() {
		return strFiscalDate1 == null ? "" : strFiscalDate1;
	}

	public void setStrFiscalDate1(String strFiscalDate1, String reportType) {
		String strDate = "";
		String strMonth = "";
		if (strFiscalDate1 != null && strFiscalDate1.trim().length() > 0) {
			strDate = strFiscalDate1.substring(0, 4);
			if (QUARTER.equalsIgnoreCase(reportType)) {
				strMonth = strFiscalDate1.substring(5, 7);
				if (month123.indexOf(strMonth) > -1) {
					strDate = Q1 + "/" + strDate;
				} else if (month456.indexOf(strMonth) > -1) {
					strDate = Q2 + "/" + strDate;
				} else if (month789.indexOf(strMonth) > -1) {
					strDate = Q3 + "/" + strDate;
				} else if (month101112.indexOf(strMonth) > -1) {
					strDate = Q4 + "/" + strDate;
				}
			}
		}
		this.strFiscalDate1 = strDate;
	}

	public String getStrFiscalDate2() {
		return strFiscalDate2 == null ? "" : strFiscalDate2;
	}

	public void setStrFiscalDate2(String strFiscalDate2, String reportType) {
		String strDate = "";
		String strMonth = "";
		if (strFiscalDate2 != null && strFiscalDate2.trim().length() > 0) {
			strDate = strFiscalDate2.substring(0, 4);
			if (QUARTER.equalsIgnoreCase(reportType)) {
				strMonth = strFiscalDate2.substring(5, 7);
				if (month123.indexOf(strMonth) > -1) {
					strDate = Q1 + "/" + strDate;
				} else if (month456.indexOf(strMonth) > -1) {
					strDate = Q2 + "/" + strDate;
				} else if (month789.indexOf(strMonth) > -1) {
					strDate = Q3 + "/" + strDate;
				} else if (month101112.indexOf(strMonth) > -1) {
					strDate = Q4 + "/" + strDate;
				}
			}
		}
		this.strFiscalDate2 = strDate;
	}

	public String getStrFiscalDate3() {
		return strFiscalDate3 == null ? "" : strFiscalDate3;
	}

	public void setStrFiscalDate3(String strFiscalDate3, String reportType) {
		String strDate = "";
		String strMonth = "";
		if (strFiscalDate3 != null && strFiscalDate3.trim().length() > 0) {
			strDate = strFiscalDate3.substring(0, 4);
			if (QUARTER.equalsIgnoreCase(reportType)) {
				strMonth = strFiscalDate3.substring(5, 7);
				if (month123.indexOf(strMonth) > -1) {
					strDate = Q1 + "/" + strDate;
				} else if (month456.indexOf(strMonth) > -1) {
					strDate = Q2 + "/" + strDate;
				} else if (month789.indexOf(strMonth) > -1) {
					strDate = Q3 + "/" + strDate;
				} else if (month101112.indexOf(strMonth) > -1) {
					strDate = Q4 + "/" + strDate;
				}
			}
		}
		this.strFiscalDate3 = strDate;
	}

	public String getStrFiscalDate4() {
		return strFiscalDate4 == null ? "" : strFiscalDate4;
	}

	public void setStrFiscalDate4(String strFiscalDate4, String reportType) {
		String strDate = "";
		String strMonth = "";
		if (strFiscalDate4 != null && strFiscalDate4.trim().length() > 0) {
			strDate = strFiscalDate4.substring(0, 4);
			if (QUARTER.equalsIgnoreCase(reportType)) {
				strMonth = strFiscalDate4.substring(5, 7);
				if (month123.indexOf(strMonth) > -1) {
					strDate = Q1 + "/" + strDate;
				} else if (month456.indexOf(strMonth) > -1) {
					strDate = Q2 + "/" + strDate;
				} else if (month789.indexOf(strMonth) > -1) {
					strDate = Q3 + "/" + strDate;
				} else if (month101112.indexOf(strMonth) > -1) {
					strDate = Q4 + "/" + strDate;
				}
			}
		}
		this.strFiscalDate4 = strDate;
	}

	public String getStrNumericValue1() {
		return strNumericValue1 == null ? "" : strNumericValue1;
	}

	public void setStrNumericValue1(String strNumericValue1) {
		this.strNumericValue1 = strNumericValue1;
	}

	public String getStrNumericValue2() {
		return strNumericValue2 == null ? "" : strNumericValue2;
	}

	public void setStrNumericValue2(String strNumericValue2) {
		this.strNumericValue2 = strNumericValue2;
	}

	public String getStrNumericValue3() {
		return strNumericValue3 == null ? "" : strNumericValue3;
	}

	public void setStrNumericValue3(String strNumericValue3) {
		this.strNumericValue3 = strNumericValue3;
	}

	public String getStrNumericValue4() {
		return strNumericValue4 == null ? "" : strNumericValue4;
	}

	public void setStrNumericValue4(String strNumericValue4) {
		this.strNumericValue4 = strNumericValue4;
	}

	public String getStrFiscalDate5() {
		return strFiscalDate5 == null ? "" : strFiscalDate5;
	}

	public void setStrFiscalDate5(String strFiscalDate5, String reportType) {
		String strDate = "";
		String strMonth = "";
		if (strFiscalDate5 != null && strFiscalDate5.trim().length() > 0) {
			strDate = strFiscalDate5.substring(0, 4);
			if (QUARTER.equalsIgnoreCase(reportType)) {
				strMonth = strFiscalDate5.substring(5, 7);
				if (month123.indexOf(strMonth) > -1) {
					strDate = Q1 + "/" + strDate;
				} else if (month456.indexOf(strMonth) > -1) {
					strDate = Q2 + "/" + strDate;
				} else if (month789.indexOf(strMonth) > -1) {
					strDate = Q3 + "/" + strDate;
				} else if (month101112.indexOf(strMonth) > -1) {
					strDate = Q4 + "/" + strDate;
				}
			}
		}
		this.strFiscalDate5 = strDate;
	}

	public String getStrNumericValue5() {
		return strNumericValue5;
	}

	public void setStrNumericValue5(String strNumericValue5) {
		this.strNumericValue5 = strNumericValue5;
	}

}
