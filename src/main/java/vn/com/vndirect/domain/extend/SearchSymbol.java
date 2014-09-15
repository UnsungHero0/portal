package vn.com.vndirect.domain.extend;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.domain.BaseBean;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoStockExchangeViewId;

@SuppressWarnings("serial")
public class SearchSymbol extends BaseBean {
	public static final int START_WITH_ID = 0;
	public static final int CONTAINS_ID = 1;

	private String companyName;
	private String symbol;

	private List<String> exchangeCodes;

	private String sectorCode;
	private String locale;

	private int searchOption = CONTAINS_ID;

	private String[] listExcludeSymbol;

	private IfoCompanyNameViewId ifoCompanyNameViewId = new IfoCompanyNameViewId();
	private IfoStockExchangeViewId ifoStockExchangeViewId = new IfoStockExchangeViewId();

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 
	 * @param exchangeCode
	 */
	public void setExchangeCode(String exchangeCode) {
		this.addExchangeCode(exchangeCode);
	}

	/**
	 * 
	 * @return
	 */
	public String getExchangeCode() {
		return this.getFirstExchangeCode();
	}

	/**
	 * @return the exchangeCodes
	 */
	public List<String> getExchangeCodes() {
		return exchangeCodes;
	}

	/**
	 * @param exchangeCodes
	 *            the exchangeCodes to set
	 */
	public void setExchangeCodes(List<String> exchangeCodes) {
		this.exchangeCodes = exchangeCodes;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstExchangeCode() {
		String exchangeCode = null;
		if (this.exchangeCodes != null && this.exchangeCodes.size() > 0) {
			exchangeCode = this.exchangeCodes.get(0);
		}
		return exchangeCode;
	}

	/**
	 * 
	 * @param exchangeCode
	 */
	public void addExchangeCode(String exchangeCode) {
		if (exchangeCode != null) {
			if (this.exchangeCodes == null) {
				this.exchangeCodes = new ArrayList<String>();
			}
			this.exchangeCodes.add(exchangeCode);
		}
	}

	/**
	 * @return the searchOption
	 */
	public int getSearchOption() {
		return searchOption;
	}

	/**
	 * @param searchOption
	 *            the searchOption to set
	 */
	public void setSearchOption(int searchOption) {
		this.searchOption = searchOption;
	}

	/**
	 * @return the ifoCompanyNameViewId
	 */
	public IfoCompanyNameViewId getIfoCompanyNameViewId() {
		return ifoCompanyNameViewId;
	}

	/**
	 * @param ifoCompanyNameViewId
	 *            the ifoCompanyNameViewId to set
	 */
	public void setIfoCompanyNameViewId(IfoCompanyNameViewId ifoCompanyNameViewId) {
		this.ifoCompanyNameViewId = ifoCompanyNameViewId;
	}

	/**
	 * @return the ifoStockExchangeViewId
	 */
	public IfoStockExchangeViewId getIfoStockExchangeViewId() {
		return ifoStockExchangeViewId;
	}

	/**
	 * @param ifoStockExchangeViewId
	 *            the ifoStockExchangeViewId to set
	 */
	public void setIfoStockExchangeViewId(IfoStockExchangeViewId ifoStockExchangeViewId) {
		this.ifoStockExchangeViewId = ifoStockExchangeViewId;
	}

	/**
	 * @return the listExcludeSymbol
	 */
	public String[] getListExcludeSymbol() {
		return listExcludeSymbol;
	}

	/**
	 * @param listExcludeSymbol
	 *            the listExcludeSymbol to set
	 */
	public void setListExcludeSymbol(String[] listExcludeSymbol) {
		this.listExcludeSymbol = listExcludeSymbol;
	}

	/**
	 * @return the sectorCode
	 */
	public String getSectorCode() {
		return sectorCode;
	}

	/**
	 * @param sectorCode
	 *            the sectorCode to set
	 */
	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
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

	public String toString() {
		return "SearchSymbol-[companyName:" + companyName + ", exchangeCodes:" + exchangeCodes + ", sectorCode:" + sectorCode + ", locale:" + locale + "]";
	}
}
