package vn.com.vndirect.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchAnalysisIndexingBean implements Serializable {
	private String[] symbols;

	public SearchAnalysisIndexingBean() {
	}

	public SearchAnalysisIndexingBean(String[] symbols) {
		this.symbols = symbols;
	}

	/**
	 * @return the symbols
	 */
	public String[] getSymbols() {
		return this.symbols;
	}

	/**
	 * @param symbols
	 *            the symbols to set
	 */
	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}

}
