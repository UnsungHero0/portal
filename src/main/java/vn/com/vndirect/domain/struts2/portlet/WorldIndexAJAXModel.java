package vn.com.vndirect.domain.struts2.portlet;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.worldmarket.adapter.WorldQuote;

@SuppressWarnings("serial")
public class WorldIndexAJAXModel extends BaseModel {
	// USA|EUR|ASIA|VN
	private String market;
	private String symbols;

	private List<WorldQuote> lstWorldQuote;

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @param market
	 *            the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * @return the lstWorldQuote
	 */
	public List<WorldQuote> getLstWorldQuote() {
		return lstWorldQuote;
	}

	/**
	 * @param lstWorldQuote
	 *            the lstWorldQuote to set
	 */
	public void setLstWorldQuote(List<WorldQuote> lstWorldQuote) {
		this.lstWorldQuote = lstWorldQuote;
	}

	/**
	 * @return the symbols
	 */
	public String getSymbols() {
		return symbols;
	}

	/**
	 * @param symbols
	 *            the symbols to set
	 */
	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}
}
