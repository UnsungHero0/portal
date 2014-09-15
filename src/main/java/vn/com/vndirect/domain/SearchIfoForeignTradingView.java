package vn.com.vndirect.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class SearchIfoForeignTradingView extends BaseBean {
	private Date tradingDate;
	private String market;
	private String symbol;
	private Date fromDate;
	private Date toDate;

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

}