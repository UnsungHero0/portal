package vn.com.vndirect.web.struts2.chart;

import vn.com.vndirect.domain.BaseModel;

@SuppressWarnings("serial")
public class HighStockModel extends BaseModel {
	private HighStock highStock;
	private String symbol;
	private Integer limitMonths;

	public HighStock getHighStock() {
		return highStock;
	}

	public void setHighStock(HighStock highStock) {
		this.highStock = highStock;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getLimitMonths() {
		return limitMonths;
	}

	public void setLimitMonths(Integer limitMonths) {
		this.limitMonths = limitMonths;
	}
}
