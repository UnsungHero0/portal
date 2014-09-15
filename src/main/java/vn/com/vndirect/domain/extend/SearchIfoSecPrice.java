/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 7, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.Calendar;
import java.util.Date;

import com.google.code.ssm.api.CacheKeyMethod;

import vn.com.vndirect.domain.BaseBean;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings("serial")
public class SearchIfoSecPrice extends BaseBean {
	public static int SORT_ASC = 0;
	public static int SORT_DESC = 1;

	private int tradingTimeSortBy = SORT_DESC;

	private Long companyId;
	private String exchangeCode;
	private String floorCode;
	private String symbol;
	private Calendar calStartDate;
	private Calendar calEndDate;
	private int offsetItems = 0;

	private boolean volumeChartInSnapshot = true;

	public SearchIfoSecPrice() {
	}

	public SearchIfoSecPrice(String symbol, String exchangeCode) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
	}

	public SearchIfoSecPrice(String symbol, String exchangeCode, Date startDate) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
		setStartDate(startDate);
	}

	public SearchIfoSecPrice(String symbol, String exchangeCode, Date startDate, Date endDate) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
		setStartDate(startDate);
		setEndDate(endDate);
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the exchangeCode
	 */
	public String getExchangeCode() {
		return exchangeCode;
	}

	/**
	 * @param exchangeCode
	 *            the exchangeCode to set
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
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
	 * @return the calStartDate
	 */
	public Calendar getCalStartDate() {
		return calStartDate;
	}

	/**
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return (calStartDate == null ? null : calStartDate.getTime());
	}

	/**
	 * 
	 * @param date
	 */
	public void setStartDate(Date date) {
		if (date != null) {
			calStartDate = (calStartDate == null ? Calendar.getInstance() : calStartDate);
			calStartDate.setTime(date);
			calStartDate.set(Calendar.HOUR_OF_DAY, 0);
			calStartDate.set(Calendar.MINUTE, 0);
			calStartDate.set(Calendar.SECOND, 0);
			calStartDate.set(Calendar.MILLISECOND, 0);
		} else {
			calStartDate = null;
		}
	}

	/**
	 * add(Calendar.DATE, -5).
	 * 
	 * @param field
	 * @param amount
	 */
	public void addStartDate(int field, int amount) {
		if (calStartDate != null) {
			calStartDate.add(field, amount);
		}
	}

	/**
	 * @return the calEndDate
	 */
	public Calendar getCalEndDate() {
		return calEndDate;
	}

	public void setEndDate(Date date) {
		if (date != null) {
			calEndDate = (calEndDate == null ? Calendar.getInstance() : calEndDate);
			calEndDate.setTime(date);
			calEndDate.set(Calendar.HOUR_OF_DAY, 23);
			calEndDate.set(Calendar.MINUTE, 59);
			calEndDate.set(Calendar.SECOND, 59);
			calEndDate.set(Calendar.MILLISECOND, 0);
		} else {
			calEndDate = null;
		}
	}

	public Date getEndDate() {
		return (calEndDate == null ? null : calEndDate.getTime());
	}

	/**
	 * @return the volumeChartInSnapshot
	 */
	public boolean isVolumeChartInSnapshot() {
		return volumeChartInSnapshot;
	}

	/**
	 * @param volumeChartInSnapshot
	 *            the volumeChartInSnapshot to set
	 */
	public void setVolumeChartInSnapshot(boolean volumeChartInSnapshot) {
		this.volumeChartInSnapshot = volumeChartInSnapshot;
	}

	/**
	 * @return the offsetItems
	 */
	public int getOffsetItems() {
		return offsetItems;
	}

	/**
	 * @param offsetItems
	 *            the offsetItems to set
	 */
	public void setOffsetItems(int offsetItems) {
		this.offsetItems = offsetItems;
	}

	/**
	 * @return the tradingTimeSortBy
	 */
	public int getTradingTimeSortBy() {
		return this.tradingTimeSortBy;
	}

	/**
	 * @param tradingTimeSortBy
	 *            the tradingTimeSortBy to set
	 */
	public void setTradingTimeSortBy(int tradingTimeSortBy) {
		this.tradingTimeSortBy = tradingTimeSortBy;
	}

	public boolean isTradingTimeSortASC() {
		return (this.tradingTimeSortBy == SORT_ASC);
	}

	public String toString() {
		return "SearchIfoSecPrice-[exchangeCode:" + exchangeCode + ", symbol:" + symbol + ", companyId:" + companyId + ", startDate:" + getStartDate() + ", endDate:" + getEndDate() + ", offsetItems:"
				+ offsetItems + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.calEndDate == null) ? 0 : this.calEndDate.hashCode());
		result = prime * result + ((this.calStartDate == null) ? 0 : this.calStartDate.hashCode());
		result = prime * result + ((this.companyId == null) ? 0 : this.companyId.hashCode());
		result = prime * result + ((this.exchangeCode == null) ? 0 : this.exchangeCode.hashCode());
		result = prime * result + this.offsetItems;
		result = prime * result + ((this.symbol == null) ? 0 : this.symbol.hashCode());
		result = prime * result + this.tradingTimeSortBy;
		result = prime * result + (this.volumeChartInSnapshot ? 1231 : 1237);
		return result;
	}
	
	@CacheKeyMethod
	public String toHashcodeString(){
		return String.valueOf(hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof SearchIfoSecPrice))
			return false;
		final SearchIfoSecPrice other = (SearchIfoSecPrice) obj;
		if (this.calEndDate == null) {
			if (other.calEndDate != null)
				return false;
		} else if (!this.calEndDate.equals(other.calEndDate))
			return false;
		if (this.calStartDate == null) {
			if (other.calStartDate != null)
				return false;
		} else if (!this.calStartDate.equals(other.calStartDate))
			return false;
		if (this.companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!this.companyId.equals(other.companyId))
			return false;
		if (this.exchangeCode == null) {
			if (other.exchangeCode != null)
				return false;
		} else if (!this.exchangeCode.equals(other.exchangeCode))
			return false;
		if (this.offsetItems != other.offsetItems)
			return false;
		if (this.symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!this.symbol.equals(other.symbol))
			return false;
		if (this.tradingTimeSortBy != other.tradingTimeSortBy)
			return false;
		if (this.volumeChartInSnapshot != other.volumeChartInSnapshot)
			return false;
		return true;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}
}
