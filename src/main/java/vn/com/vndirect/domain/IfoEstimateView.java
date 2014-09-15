package vn.com.vndirect.domain;

import java.sql.Date;

@SuppressWarnings("serial")
public class IfoEstimateView extends BaseBean implements java.io.Serializable {

	// Fields
	private long companyId;
	private Date fiscalDate;
	private String searchDate;
	private double item21001;
	private double item23800;
	private double item23000;
	private double item400001;
	private double item40000;
	private double item40001;

	private double l_analyst_21001;
	private double l_analyst_23800;
	private double l_analyst_23000;
	private double l_analyst_400001;
	private double l_analyst_40000;
	private double l_analyst_40001;
	private double l_analyst_51021;

	private String label21001;
	private String label23800;
	private String label23000;
	private String label400001;
	private String label40000;
	private String label40001;

	private String label51021;
	private double item51021;

	private double l_actual_21001;
	private double l_actual_23800;
	private double l_actual_23000;
	private double l_actual_400001;

	private double pct_result_21001;
	private double pct_result_23800;
	private double pct_result_23000;
	private double pct_result_400001;
	private double analyst_pct_result_21001;
	private double analyst_pct_result_23800;
	private double analyst_pct_result_23000;
	private double analyst_pct_result_400001;

	private String locale;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public Date getFiscalDate() {
		return fiscalDate;
	}

	public void setFiscalDate(Date fiscalDate) {
		this.fiscalDate = fiscalDate;
	}

	public double getItem21001() {
		return item21001;
	}

	public void setItem21001(double item21001) {
		this.item21001 = item21001;
	}

	public double getItem23800() {
		return item23800;
	}

	public void setItem23800(double item23800) {
		this.item23800 = item23800;
	}

	public double getItem23000() {
		return item23000;
	}

	public void setItem23000(double item23000) {
		this.item23000 = item23000;
	}

	public double getItem400001() {
		return item400001;
	}

	public void setItem400001(double item400001) {
		this.item400001 = item400001;
	}

	public double getItem40000() {
		return item40000;
	}

	public void setItem40000(double item40000) {
		this.item40000 = item40000;
	}

	public double getItem40001() {
		return item40001;
	}

	public void setItem40001(double item40001) {
		this.item40001 = item40001;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLabel21001() {
		return label21001;
	}

	public void setLabel21001(String label21001) {
		this.label21001 = label21001;
	}

	public String getLabel23800() {
		return label23800;
	}

	public void setLabel23800(String label23800) {
		this.label23800 = label23800;
	}

	public String getLabel23000() {
		return label23000;
	}

	public void setLabel23000(String label23000) {
		this.label23000 = label23000;
	}

	public String getLabel400001() {
		return label400001;
	}

	public void setLabel400001(String label400001) {
		this.label400001 = label400001;
	}

	public String getLabel40000() {
		return label40000;
	}

	public void setLabel40000(String label40000) {
		this.label40000 = label40000;
	}

	public String getLabel40001() {
		return label40001;
	}

	public void setLabel40001(String label40001) {
		this.label40001 = label40001;
	}

	public String getLabel51021() {
		return label51021;
	}

	public void setLabel51021(String label51021) {
		this.label51021 = label51021;
	}

	public double getItem51021() {
		return item51021;
	}

	public void setItem51021(double item51021) {
		this.item51021 = item51021;
	}

	public double getL_analyst_21001() {
		return l_analyst_21001;
	}

	public void setL_analyst_21001(double lAnalyst_21001) {
		l_analyst_21001 = lAnalyst_21001;
	}

	public double getL_analyst_23800() {
		return l_analyst_23800;
	}

	public void setL_analyst_23800(double lAnalyst_23800) {
		l_analyst_23800 = lAnalyst_23800;
	}

	public double getL_analyst_23000() {
		return l_analyst_23000;
	}

	public void setL_analyst_23000(double lAnalyst_23000) {
		l_analyst_23000 = lAnalyst_23000;
	}

	public double getL_analyst_400001() {
		return l_analyst_400001;
	}

	public void setL_analyst_400001(double lAnalyst_400001) {
		l_analyst_400001 = lAnalyst_400001;
	}

	public double getL_analyst_40000() {
		return l_analyst_40000;
	}

	public void setL_analyst_40000(double lAnalyst_40000) {
		l_analyst_40000 = lAnalyst_40000;
	}

	public double getL_analyst_40001() {
		return l_analyst_40001;
	}

	public void setL_analyst_40001(double lAnalyst_40001) {
		l_analyst_40001 = lAnalyst_40001;
	}

	public double getL_analyst_51021() {
		return l_analyst_51021;
	}

	public void setL_analyst_51021(double lAnalyst_51021) {
		l_analyst_51021 = lAnalyst_51021;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public double getL_actual_21001() {
		return l_actual_21001;
	}

	public void setL_actual_21001(double lActual_21001) {
		l_actual_21001 = lActual_21001;
	}

	public double getL_actual_23800() {
		return l_actual_23800;
	}

	public void setL_actual_23800(double lActual_23800) {
		l_actual_23800 = lActual_23800;
	}

	public double getL_actual_23000() {
		return l_actual_23000;
	}

	public void setL_actual_23000(double lActual_23000) {
		l_actual_23000 = lActual_23000;
	}

	public double getL_actual_400001() {
		return l_actual_400001;
	}

	public void setL_actual_400001(double lActual_400001) {
		l_actual_400001 = lActual_400001;
	}

	public double getPct_result_21001() {
		return pct_result_21001;
	}

	public void setPct_result_21001(double pctResult_21001) {
		pct_result_21001 = pctResult_21001;
	}

	public double getPct_result_23800() {
		return pct_result_23800;
	}

	public void setPct_result_23800(double pctResult_23800) {
		pct_result_23800 = pctResult_23800;
	}

	public double getPct_result_23000() {
		return pct_result_23000;
	}

	public void setPct_result_23000(double pctResult_23000) {
		pct_result_23000 = pctResult_23000;
	}

	public double getPct_result_400001() {
		return pct_result_400001;
	}

	public void setPct_result_400001(double pctResult_400001) {
		pct_result_400001 = pctResult_400001;
	}

	public double getAnalyst_pct_result_21001() {
		return analyst_pct_result_21001;
	}

	public void setAnalyst_pct_result_21001(double analystPctResult_21001) {
		analyst_pct_result_21001 = analystPctResult_21001;
	}

	public double getAnalyst_pct_result_23800() {
		return analyst_pct_result_23800;
	}

	public void setAnalyst_pct_result_23800(double analystPctResult_23800) {
		analyst_pct_result_23800 = analystPctResult_23800;
	}

	public double getAnalyst_pct_result_23000() {
		return analyst_pct_result_23000;
	}

	public void setAnalyst_pct_result_23000(double analystPctResult_23000) {
		analyst_pct_result_23000 = analystPctResult_23000;
	}

	public double getAnalyst_pct_result_400001() {
		return analyst_pct_result_400001;
	}

	public void setAnalyst_pct_result_400001(double analystPctResult_400001) {
		analyst_pct_result_400001 = analystPctResult_400001;
	}

}
