/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 4, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.lucene.finfodb.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vn.com.vndirect.domain.embeddb.DynamicMetaTable;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class AnalysisIndexingBean implements Serializable {
	private long companyId;
	private String symbol;
	private String exchangeCode;
	private String sectorCode;
	private String industryCode;
	private String intraday;

	private double closePrice;
	private String companyName;
	private String companyFullName;
	private String abbName;
	private Date firstTradingDate;
	private String industryName;
	private String locale;

	private String exchangeName;
	private String sectorGroupCode;
	private String industryGroupCode;

	private double pctAbove52WeekLow;
	private double pctBelow52WeekHigh;

	private double vsSma13Day;
	private double vsSma50Day;
	private Map<String, AnalysisCachingValueInfo> mapValues = new HashMap<String, AnalysisCachingValueInfo>();

	public AnalysisIndexingBean() {
	}

	public AnalysisIndexingBean(long companyId, String symbol) {
		this.companyId = companyId;
		this.symbol = symbol;
	}

	/**
	 * @return the companyId
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * 
	 * @param itemCode
	 * @param value
	 */
	public void addValue(String itemCode, AnalysisCachingValueInfo value) {
		this.mapValues.put(converKey(itemCode), value);
	}

	/**
	 * 
	 * @param itemCode
	 * @return
	 */
	public AnalysisCachingValueInfo getValue(String itemCode) {
		return this.mapValues.get(converKey(DynamicMetaTable.checkPrefix(itemCode) ? itemCode : DynamicMetaTable.generateFieldName(itemCode)));
	}

	/**
	 * 
	 * @return
	 */
	public String[] getValueKeys() {
		return (String[]) this.mapValues.keySet().toArray(new String[this.mapValues.size()]);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private String converKey(String key) {
		return (key == null ? "" : key.toLowerCase());
	}

	/**
	 * 
	 */
	public String toString() {
		return "AnalysisBean-[companyId:" + companyId + ", symbol: " + symbol + ", mapValues: " + mapValues + "]";
	}

	/**
	 * @return the exchangeCode
	 */
	public String getExchangeCode() {
		return this.exchangeCode;
	}

	/**
	 * @param exchangeCode
	 *            the exchangeCode to set
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	/**
	 * @return the sectorCode
	 */
	public String getSectorCode() {
		return this.sectorCode;
	}

	/**
	 * @param sectorCode
	 *            the sectorCode to set
	 */
	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return this.industryCode;
	}

	/**
	 * @param industryCode
	 *            the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	/**
	 * @return the mapValues
	 */
	public Map<String, AnalysisCachingValueInfo> getMapValues() {
		return mapValues;
	}

	/**
	 * @return the intraday
	 */
	public String getIntraday() {
		return intraday;
	}

	/**
	 * @param intraday
	 *            the intraday to set
	 */
	public void setIntraday(String intraday) {
		this.intraday = intraday;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getSectorGroupCode() {
		return sectorGroupCode;
	}

	public void setSectorGroupCode(String sectorGroupCode) {
		this.sectorGroupCode = sectorGroupCode;
	}

	public String getIndustryGroupCode() {
		return industryGroupCode;
	}

	public void setIndustryGroupCode(String industryGroupCode) {
		this.industryGroupCode = industryGroupCode;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getFirstTradingDate() {
		return firstTradingDate;
	}

	public void setFirstTradingDate(Date firstTradingDate) {
		this.firstTradingDate = firstTradingDate;
	}

	public String getAbbName() {
		return abbName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public void setMapValues(Map<String, AnalysisCachingValueInfo> mapValues) {
		this.mapValues = mapValues;
	}

	public double getPctAbove52WeekLow() {
		return pctAbove52WeekLow;
	}

	public void setPctAbove52WeekLow(double pctAbove52WeekLow) {
		this.pctAbove52WeekLow = pctAbove52WeekLow;
	}

	public double getPctBelow52WeekHigh() {
		return pctBelow52WeekHigh;
	}

	public void setPctBelow52WeekHigh(double pctBelow52WeekHigh) {
		this.pctBelow52WeekHigh = pctBelow52WeekHigh;
	}

	public double getVsSma13Day() {
		return vsSma13Day;
	}

	public void setVsSma13Day(double vsSma13Day) {
		this.vsSma13Day = vsSma13Day;
	}

	public double getVsSma50Day() {
		return vsSma50Day;
	}

	public void setVsSma50Day(double vsSma50Day) {
		this.vsSma50Day = vsSma50Day;
	}
}
