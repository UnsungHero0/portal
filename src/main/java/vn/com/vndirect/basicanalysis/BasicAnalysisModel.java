package vn.com.vndirect.basicanalysis;

import java.io.Serializable;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.struts2.common.EmptyModel;

import com.opensymphony.xwork2.ModelDriven;

public class BasicAnalysisModel extends BaseModel {

	private PasicAnalysis pasicAnalysis;
	private String symbol;
	private List<String> hnxSymbols;
	private List<String> hoseSymbols;
	private String acceptableLastQuarter;
	private String acceptableSameQuarterLastYear;
	private Boolean isGoodEPSByCanslim;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public PasicAnalysis getPasicAnalysis() {
		return pasicAnalysis;
	}

	public void setPasicAnalysis(PasicAnalysis pasicAnalysis) {
		this.pasicAnalysis = pasicAnalysis;
	}

	public List<String> getHnxSymbols() {
		return hnxSymbols;
	}

	public List<String> getHoseSymbols() {
		return hoseSymbols;
	}

	public void setHnxSymbols(List<String> hnxSymbols) {
		this.hnxSymbols = hnxSymbols;
	}

	public void setHoseSymbols(List<String> hoseSymbols) {
		this.hoseSymbols = hoseSymbols;
	}

	public String getAcceptableLastQuarter() {
		return acceptableLastQuarter;
	}

	public String getAcceptableSameQuarterLastYear() {
		return acceptableSameQuarterLastYear;
	}

	public void setAcceptableLastQuarter(String acceptableLastQuarter) {
		this.acceptableLastQuarter = acceptableLastQuarter;
	}

	public void setAcceptableSameQuarterLastYear(String acceptableSameQuarterLastYear) {
		this.acceptableSameQuarterLastYear = acceptableSameQuarterLastYear;
	}

	public Boolean getIsGoodEPSByCanslim() {
		return isGoodEPSByCanslim;
	}

	public void setIsGoodEPSByCanslim(Boolean isGoodEPSByCanslim) {
		this.isGoodEPSByCanslim = isGoodEPSByCanslim;
	}
}
