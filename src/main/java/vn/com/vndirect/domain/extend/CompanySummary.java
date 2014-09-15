/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 21, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.Date;

import vn.com.vndirect.domain.BaseBean;
import vn.com.vndirect.domain.SecInfo;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings("serial")
public class CompanySummary extends BaseBean {
	private String symbol;
	private String companyName;
	private double priceChange;
	private double percentChange;
	private double volume;
	private double lastPrice;
	private double basicPrice;
	private Date timeTrade;
	private double highestPrice;
	private double lowestPrice;

	private Date firstTradingDate;

	private double secPrice;

	private String locale;

	private String exchangeCode;

	private SecInfo secInfo;

	public CompanySummary() {
	}

	public CompanySummary(String symbol) {
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
	 * @return the percentChange
	 */
	public double getPercentChange() {
		return percentChange;
	}

	/**
	 * @param percentChange
	 *            the percentChange to set
	 */
	public void setPercentChange(double percentChange) {
		this.percentChange = percentChange;
	}

	/**
	 * @return the priceChange
	 */
	public double getPriceChange() {
		return priceChange;
	}

	/**
	 * @param priceChange
	 *            the priceChange to set
	 */
	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
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
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * @return the displayCompanyName
	 */
	public String getDisplayCompanyName() {
		String str = symbol;
		if (companyName != null && companyName.trim().length() > 0) {
			str = companyName;
		}
		return str;
	}

	/**
	 * @return the lastPrice
	 */
	public double getLastPrice() {
		return lastPrice;
	}

	/**
	 * @param lastPrice
	 *            the lastPrice to set
	 */
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 * @return the lastTrade
	 */
	public Date getTimeTrade() {
		return timeTrade;
	}

	/**
	 * @param lastTrade
	 *            the lastTrade to set
	 */
	public void setTimeTrade(Date lastTrade) {
		this.timeTrade = lastTrade;
	}

	public String getStrTradingDate() {
		try {
			return DateUtils.dateToString(this.timeTrade, DateUtils.Formats.STR_DATE_TIME_FORMAT);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @return the basicPrice
	 */
	public double getBasicPrice() {
		return basicPrice;
	}

	/**
	 * @param basicPrice
	 *            the basicPrice to set
	 */
	public void setBasicPrice(double basicPrice) {
		this.basicPrice = basicPrice;
	}

	/**
	 * @return the marketCode
	 */
	public String getExchangeCode() {
		return exchangeCode;
	}

	/**
	 * @param marketCode
	 *            the marketCode to set
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public SecInfo getSecInfo() {
		return secInfo;
	}

	public void setSecInfo(SecInfo secInfo) {
		this.secInfo = secInfo;
	}

	/**
	 * @return the secPrice
	 */
	public double getSecPrice() {
		return secPrice;
	}

	/**
	 * @param secPrice
	 *            the secPrice to set
	 */
	public void setSecPrice(double secPrice) {
		this.secPrice = secPrice;
	}

	/**
	 * @return the firstTradingDate
	 */
	public Date getFirstTradingDate() {
		return firstTradingDate;
	}

	/**
	 * @param firstTradingDate
	 *            the firstTradingDate to set
	 */
	public void setFirstTradingDate(Date firstTradingDate) {
		this.firstTradingDate = firstTradingDate;
	}

	public double getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}

	public double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
}
