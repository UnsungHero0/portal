/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 7, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.Calendar;
import java.util.Date;

import vn.com.vndirect.domain.BaseBean;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings("serial")
public class SearchIfoMarketCalendar extends BaseBean {
	private Long companyId;
	private String exchangeCode;
	private String symbol;
	private Calendar calStartDate;
	private Calendar calEndDate;
	private int offsetItems = 0;
	private String lang;

	public SearchIfoMarketCalendar() {
	}

	/**
	 * @param date
	 * @param date2
	 * @param id
	 * @param lang
	 * @param symbol
	 */
	public SearchIfoMarketCalendar(Calendar date, Calendar date2, Long id, String lang, String symbol) {
		super();
		// TODO Auto-generated constructor stub
		calEndDate = date;
		calStartDate = date2;
		companyId = id;
		this.lang = lang;
		this.symbol = symbol;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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
	 * @param calStartDate
	 *            the calStartDate to set
	 */
	public void setCalStartDate(Calendar calStartDate) {
		this.calStartDate = calStartDate;
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

	/**
	 * @param calEndDate
	 *            the calEndDate to set
	 */
	public void setCalEndDate(Calendar calEndDate) {
		this.calEndDate = calEndDate;
	}

	public void setEndDate(Date date) {
		if (date != null) {
			calEndDate = (calEndDate == null ? Calendar.getInstance() : calEndDate);
			calEndDate.setTime(date);
			calEndDate.set(Calendar.HOUR_OF_DAY, 23);
			calEndDate.set(Calendar.MINUTE, 59);
			calEndDate.set(Calendar.SECOND, 59);
		} else {
			calEndDate = null;
		}
	}

	public Date getEndDate() {
		return (calEndDate == null ? null : calEndDate.getTime());
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
		result = prime * result + ((this.lang == null) ? 0 : this.lang.hashCode());
		result = prime * result + this.offsetItems;
		result = prime * result + ((this.symbol == null) ? 0 : this.symbol.hashCode());
		return result;
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
		if (!(obj instanceof SearchIfoMarketCalendar))
			return false;
		final SearchIfoMarketCalendar other = (SearchIfoMarketCalendar) obj;
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
		if (this.lang == null) {
			if (other.lang != null)
				return false;
		} else if (!this.lang.equals(other.lang))
			return false;
		if (this.offsetItems != other.offsetItems)
			return false;
		if (this.symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!this.symbol.equals(other.symbol))
			return false;
		return true;
	}
}
