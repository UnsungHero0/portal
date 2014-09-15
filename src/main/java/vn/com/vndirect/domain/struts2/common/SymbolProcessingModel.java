package vn.com.vndirect.domain.struts2.common;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoExchangeCode;
import vn.com.vndirect.domain.IfoSectorCode;
import vn.com.vndirect.domain.extend.SearchSymbol;

@SuppressWarnings("serial")
public class SymbolProcessingModel extends BaseModel {
	private String symbol;
	private String lang;

	// for search
	private SearchSymbol searchSymbol = new SearchSymbol();
	private List<IfoCompanyNameView> result;

	/**
	 * @return the result
	 */
	public List<IfoCompanyNameView> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<IfoCompanyNameView> result) {
		this.result = result;
	}

	private List<IfoExchangeCode> listIfoExchange;
	private List<IfoSectorCode> listSectorCode;

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
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the searchSymbol
	 */
	public SearchSymbol getSearchSymbol() {
		return searchSymbol;
	}

	/**
	 * @param searchSymbol
	 *            the searchSymbol to set
	 */
	public void setSearchSymbol(SearchSymbol searchSymbol) {
		this.searchSymbol = searchSymbol;
	}

	/**
	 * @return the listIfoExchange
	 */
	public List<IfoExchangeCode> getListIfoExchange() {
		return listIfoExchange;
	}

	/**
	 * @param listIfoExchange
	 *            the listIfoExchange to set
	 */
	public void setListIfoExchange(List<IfoExchangeCode> listIfoExchange) {
		this.listIfoExchange = listIfoExchange;
	}

	/**
	 * @return the listSectorCode
	 */
	public List<IfoSectorCode> getListSectorCode() {
		return listSectorCode;
	}

	/**
	 * @param listSectorCode
	 *            the listSectorCode to set
	 */
	public void setListSectorCode(List<IfoSectorCode> listSectorCode) {
		this.listSectorCode = listSectorCode;
	}
}
