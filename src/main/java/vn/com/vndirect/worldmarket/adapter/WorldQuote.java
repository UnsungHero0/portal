package vn.com.vndirect.worldmarket.adapter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WorldQuote implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3910676517209930275L;
	private String symbol;
	private String name;
	private String time;
	private String strDate;
	private Date date;
	private Double index;
	private Double chgIdx;
	private Double pctIdx;

	private Map<String, String> props = new HashMap<String, String>();

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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the strDate
	 */
	public String getStrDate() {
		return strDate;
	}

	/**
	 * @param strDate
	 *            the strDate to set
	 */
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the index
	 */
	public Double getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Double index) {
		this.index = index;
	}

	/**
	 * @return the chgIdx
	 */
	public Double getChgIdx() {
		return chgIdx;
	}

	/**
	 * @param chgIdx
	 *            the chgIdx to set
	 */
	public void setChgIdx(Double chgIdx) {
		this.chgIdx = chgIdx;
	}

	/**
	 * @return the pctIdx
	 */
	public Double getPctIdx() {
		return pctIdx;
	}

	/**
	 * @param pctIdx
	 *            the pctIdx to set
	 */
	public void setPctIdx(Double pctIdx) {
		this.pctIdx = pctIdx;
	}

	/**
	 * @return the props
	 */
	public Map<String, String> getProps() {
		return props;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addProp(String key, String value) {
		if (key != null && value != null) {
			props.put(key, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorldQuote [chgIdx=" + chgIdx + ", date=" + date + ", index=" + index + ", pctIdx=" + pctIdx + ", strDate=" + strDate + ", symbol=" + symbol + ", time=" + time + ", props=" + props
				+ "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
