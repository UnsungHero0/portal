package vn.com.vndirect.domain.extend;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanySnapshotView;
import vn.com.web.commons.utility.DateUtils;

public class IfoCompanySnapshotViewExt extends IfoCompanySnapshotView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3682389122483363189L;

	public String getStrBeta() {
		String strBeta = "";
		if (this.getId() != null) {
			strBeta = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getBeta());
		}
		return strBeta == null ? "" : strBeta;
	}

	public String getStrBookValue() {
		String strBookValue = "";
		if (this.getId() != null) {
			strBookValue = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getBookValue());
		}
		return strBookValue == null ? "" : strBookValue;
	}

	public String getStrDeclaredDividend() {
		String strDeclaredDividend = "";
		if (this.getId() != null) {
			strDeclaredDividend = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getDeclaredDividend(), 0);
		}
		return strDeclaredDividend == null ? "" : strDeclaredDividend;
	}

	public String getStrDividendPayableDate() {
		String strDate = "";
		try {
			if (this.getId() != null) {
				strDate = DateUtils.dateToString(this.getId().getDividendPayableDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
			}
		} catch (Exception ex) {
		}
		return strDate == null ? "" : strDate;

	}

	public String getStrDividendYield() {
		String strDividendYield = "";
		if (this.getId() != null) {
			strDividendYield = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getDividendYield());
		}
		return strDividendYield == null ? "" : strDividendYield;
	}

	public String getStrDividendYieldWithoutScale() {
		String strDividendYield = "";
		if (this.getId() != null) {
			strDividendYield = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getDividendYield(), 0);
		}
		return strDividendYield == null ? "" : strDividendYield;
	}

	public String getStrEarningsValue() {
		String strEarningsValue = "";
		if (this.getId() != null) {
			strEarningsValue = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getEarningsValue());
		}
		return strEarningsValue == null ? "" : strEarningsValue;
	}

	public String getStrEps() {
		String strEps = "";
		if (this.getId() != null) {
			strEps = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getEps(), 0);
		}
		return strEps == null ? "" : strEps;
	}

	public String getStrExDividendDate() {
		String strDate = "";
		try {
			if (this.getId() != null) {
				strDate = DateUtils.dateToString(this.getId().getExDividendDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
			}
		} catch (Exception ex) {
		}
		return strDate == null ? "" : strDate;
	}

	public String getStrLeverage() {
		String strLeverage = "";
		if (this.getId() != null) {
			strLeverage = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getLeverage());
		}
		return strLeverage == null ? "" : strLeverage;
	}

	public String getStrMarketCapital() {
		String strMarketCapital = "";
		if (this.getId() != null) {
			strMarketCapital = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getMarketCapital(), 0);
		}
		return strMarketCapital == null ? "" : strMarketCapital;
	}

	public String getStrRoa() {
		String strRoa = "";
		if (this.getId() != null) {
			strRoa = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getRoa());
		}
		return strRoa == null ? "" : strRoa;
	}

	public String getStrRoe() {
		String strRoe = "";
		if (this.getId() != null) {
			strRoe = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getRoe());
		}
		return strRoe == null ? "" : strRoe;
	}

	public String getStrShareOutstanding() {
		String strShareOutstanding = "";
		if (this.getId() != null) {
			strShareOutstanding = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getShareOutstanding(), 0);
		}
		return strShareOutstanding == null ? "" : strShareOutstanding;
	}

	public String getStrListedShares() {
		String strListedShares = "";
		if (this.getId() != null) {
			strListedShares = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getListedShares(), 0);
		}
		return strListedShares == null ? "" : strListedShares;
	}

	public String getStrWeekHigh() {
		String strWeekHigh = "";
		if (this.getId() != null) {
			strWeekHigh = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getWeekHigh());
		}
		return strWeekHigh == null ? "" : strWeekHigh;
	}

	public String getStrWeekLow() {
		String strWeekLow = "";
		if (this.getId() != null) {
			strWeekLow = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getWeekLow());
		}
		return strWeekLow == null ? "" : strWeekLow;
	}

	public String getStrAverageVolumn() {
		String strAverageVolumn = "";
		if (this.getId() != null) {
			strAverageVolumn = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getAverageVolumn(), 0);
		}
		return strAverageVolumn == null ? "" : strAverageVolumn;
	}

	public String getStrPe() {
		String strPe = "";
		if (this.getId() != null) {
			strPe = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getPe());
		}
		return strPe == null ? "" : strPe;
	}

	public String getStrForeignOwnership() {
		String strForeignOwnership = "";
		if (this.getId() != null) {
			strForeignOwnership = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getForeignOwnership());
		}
		return strForeignOwnership == null ? "" : strForeignOwnership;
	}

	public String getStrForeignSoldVol() {
		String strForeignSoldVol = "";
		if (this.getId() != null) {
			strForeignSoldVol = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getForeignSoldVol(), 0);
		}
		return strForeignSoldVol == null ? "" : strForeignSoldVol;
	}

	public String getStrForeignBoughtVol() {
		String strForeignBoughtVol = "";
		if (this.getId() != null) {
			strForeignBoughtVol = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getForeignBoughtVol(), 0);
		}
		return strForeignBoughtVol == null ? "" : strForeignBoughtVol;
	}

	public String getStrRoaExt() {
		String strRoa = "";
		if (this.getId() != null) {
			strRoa = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getRoa() * 100);
		}
		return strRoa == null ? "" : strRoa;
	}

	public String getStrRoeExt() {
		String strRoe = "";
		if (this.getId() != null) {
			strRoe = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getRoe() * 100);
		}
		return strRoe == null ? "" : strRoe;
	}

	public String getStrPB() {
		String strPB = "";
		if (this.getId() != null) {
			strPB = VNDirectWebUtilities.getStrDoubleWithScale2(this.getId().getPb());
		}
		return strPB == null ? "" : strPB;
	}

	public String getStrAnnualEPS() {
		String strAnnualEPS = "";
		if (this.getId() != null) {
			strAnnualEPS = VNDirectWebUtilities.getStrDoubleWithScale(this.getId().getEpsYear(), 0);
		}
		return strAnnualEPS == null ? "" : strAnnualEPS;
	}

}
