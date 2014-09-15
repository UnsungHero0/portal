package vn.com.vndirect.domain.extend;

import java.util.Date;

import vn.com.vndirect.domain.BaseBean;

public class SearchMarketStatisticsView extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7876043014013709372L;

	private String symbol;
	private Date fromDate = null;
	private Date toDate = null;

	private Date tradingDate;
	private String market;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getTradingDate() {
		return tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
}
