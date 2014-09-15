package vn.com.vndirect.domain.extend;

import vn.com.vndirect.domain.BaseBean;

public class IfoBalanceSheetSearch extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2169525731880461638L;

	private int numberTerm = 5;
	private String reportType;
	private Long companyId;
	private String fiscalQuarter;
	private String fiscalYear;
	private String moneyRate = "1,000,000";
	private String locale;
	private boolean isAnnual;

	public boolean isAnnual() {
		return isAnnual;
	}

	public void setAnnual(boolean isAnnual) {
		this.isAnnual = isAnnual;
	}

	public int getNumberTerm() {
		return numberTerm;
	}

	public void setNumberTerm(int numberTerm) {
		this.numberTerm = numberTerm;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getFiscalQuarter() {
		return fiscalQuarter;
	}

	public void setFiscalQuarter(String fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getMoneyRate() {
		return moneyRate;
	}

	public void setMoneyRate(String moneyRate) {
		this.moneyRate = moneyRate;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
