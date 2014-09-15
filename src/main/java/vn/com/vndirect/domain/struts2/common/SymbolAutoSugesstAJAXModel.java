package vn.com.vndirect.domain.struts2.common;

import java.util.List;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.embeddb.StockExchange;

@SuppressWarnings("serial")
public class SymbolAutoSugesstAJAXModel extends BaseModel {
	// +++ for request params
	private String text;
	private int items = Constants.Paging.NUMBER_ITEMS;
	private String markets;

	private String exclusedSymbols;
	private String locale;

	// +++ for result data
	private List<StockExchange> stockExchanges;

	public void setQ(String q) {
		this.text = q;
	}

	public void setLimit(int limit) {
		this.items = limit;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the items
	 */
	public int getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(int items) {
		this.items = items;
	}

	/**
	 * @return the markets
	 */
	public String getMarkets() {
		return markets;
	}

	/**
	 * @param markets
	 *            the markets to set
	 */
	public void setMarkets(String markets) {
		this.markets = markets;
	}

	/**
	 * @return the stockExchanges
	 */
	public List<StockExchange> getStockExchanges() {
		return stockExchanges;
	}

	/**
	 * @param stockExchanges
	 *            the stockExchanges to set
	 */
	public void setStockExchanges(List<StockExchange> stockExchanges) {
		this.stockExchanges = stockExchanges;
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

	/**
	 * @return the exclusedSymbols
	 */
	public String getExclusedSymbols() {
		return exclusedSymbols;
	}

	/**
	 * @param exclusedSymbols
	 *            the exclusedSymbols to set
	 */
	public void setExclusedSymbols(String exclusedSymbols) {
		this.exclusedSymbols = exclusedSymbols;
	}
}
