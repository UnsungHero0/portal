/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 6, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.embeddb;

import java.io.Serializable;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class SearchStockExchange implements Serializable {
	private String[] exchangeCodes;
	private String[] symbols;
	private String[] sectorGroupCodes;
	private String[] industryGroupCodes;
	private String[] sectorCodes;
	private String[] industryCodes;
	private long[] companyIds;
	private String[] ignoreSymbols;

	private String[] companyNames;
	private String locale;
	private String symbol;

	public SearchStockExchange() {
	}

	public SearchStockExchange(String[] symbols) {
		this.symbols = symbols;
	}

	/**
	 * @return the exchangeCodes
	 */
	public String[] getExchangeCodes() {
		return this.exchangeCodes;
	}

	/**
	 * @param exchangeCodes
	 *            the exchangeCodes to set
	 */
	public void setExchangeCodes(String[] exchangeCodes) {
		this.exchangeCodes = exchangeCodes;
	}

	/**
	 * @return the symbols
	 */
	public String[] getSymbols() {
		return this.symbols;
	}

	/**
	 * @param symbols
	 *            the symbols to set
	 */
	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}

	/**
	 * @return the sectorGroupCodes
	 */
	public String[] getSectorGroupCodes() {
		return this.sectorGroupCodes;
	}

	/**
	 * @param sectorGroupCodes
	 *            the sectorGroupCodes to set
	 */
	public void setSectorGroupCodes(String[] sectorGroupCodes) {
		this.sectorGroupCodes = sectorGroupCodes;
	}

	/**
	 * @return the industryGroupCodes
	 */
	public String[] getIndustryGroupCodes() {
		return this.industryGroupCodes;
	}

	/**
	 * @param industryGroupCodes
	 *            the industryGroupCodes to set
	 */
	public void setIndustryGroupCodes(String[] industryGroupCodes) {
		this.industryGroupCodes = industryGroupCodes;
	}

	/**
	 * @return the sectorCodes
	 */
	public String[] getSectorCodes() {
		return this.sectorCodes;
	}

	/**
	 * @param sectorCodes
	 *            the sectorCodes to set
	 */
	public void setSectorCodes(String[] sectorCodes) {
		this.sectorCodes = sectorCodes;
	}

	/**
	 * @return the industryCodes
	 */
	public String[] getIndustryCodes() {
		return this.industryCodes;
	}

	/**
	 * @param industryCodes
	 *            the industryCodes to set
	 */
	public void setIndustryCodes(String[] industryCodes) {
		this.industryCodes = industryCodes;
	}

	/**
	 * @return the companyIds
	 */
	public long[] getCompanyIds() {
		return this.companyIds;
	}

	/**
	 * @param companyIds
	 *            the companyIds to set
	 */
	public void setCompanyIds(long[] companyIds) {
		this.companyIds = companyIds;
	}

	/**
	 * @return the ignoreSymbols
	 */
	public String[] getIgnoreSymbols() {
		return ignoreSymbols;
	}

	/**
	 * @param ignoreSymbols
	 *            the ignoreSymbols to set
	 */
	public void setIgnoreSymbols(String[] ignoreSymbols) {
		this.ignoreSymbols = ignoreSymbols;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasSearchValues() {
		return ((exchangeCodes != null && exchangeCodes.length > 0) || (symbols != null && symbols.length > 0) || (ignoreSymbols != null && ignoreSymbols.length > 0)
				|| (sectorGroupCodes != null && sectorGroupCodes.length > 0) || (industryGroupCodes != null && industryGroupCodes.length > 0) || (sectorCodes != null && sectorCodes.length > 0)
				|| (industryCodes != null && industryCodes.length > 0) || (companyIds != null && companyIds.length > 0));
	}

	/**
	 * @return the companyNames
	 */
	public String[] getCompanyNames() {
		return companyNames;
	}

	/**
	 * @param companyNames
	 *            the companyNames to set
	 */
	public void setCompanyNames(String[] companyNames) {
		this.companyNames = companyNames;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
