package vn.com.vndirect.worldmarket.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MapQuote extends HashMap<String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5112434394639780994L;
	public final static String USA_MARKET = "USA";
	public final static String ASIA_MARKET = "ASIA";
	public final static String EUROPE_MARKET = "EUR";

	private Map<String, List<String>> marketMap = new HashMap<String, List<String>>();

	/**
	 * 
	 * @param market
	 * @return
	 */
	public List<String> getMarketQuotes(String market) {
		return marketMap.get(market == null ? "" : market);
	}

	/**
	 * 
	 * @param quotes
	 */
	public void setAddUsaQuotes(List<String> quotes) {
		if (quotes != null) {
			List<String> lstSymbol = new ArrayList<String>();
			StringTokenizer strToken;
			for (String quote : quotes) {
				strToken = new StringTokenizer(quote, "=");
				if (strToken.countTokens() > 1) {
					String key = strToken.nextToken();
					String value = strToken.nextToken();

					lstSymbol.add(key);
					super.put(key, value);
				}
			}
			marketMap.put(USA_MARKET, lstSymbol);
		}
	}

	/**
	 * 
	 * @param quotes
	 */
	public void setAddAsiaQuotes(List<String> quotes) {
		if (quotes != null) {
			List<String> lstSymbol = new ArrayList<String>();
			StringTokenizer strToken;
			for (String quote : quotes) {
				strToken = new StringTokenizer(quote, "=");
				if (strToken.countTokens() > 1) {
					String key = strToken.nextToken();
					String value = strToken.nextToken();

					lstSymbol.add(key);
					super.put(key, value);
				}
			}
			marketMap.put(ASIA_MARKET, lstSymbol);
		}
	}

	/**
	 * 
	 * @param quotes
	 */
	public void setAddEuropeQuotes(List<String> quotes) {
		if (quotes != null) {
			List<String> lstSymbol = new ArrayList<String>();
			StringTokenizer strToken;
			for (String quote : quotes) {
				strToken = new StringTokenizer(quote, "=");
				if (strToken.countTokens() > 1) {
					String key = strToken.nextToken();
					String value = strToken.nextToken();

					lstSymbol.add(key);
					super.put(key, value);
				}
			}
			marketMap.put(EUROPE_MARKET, lstSymbol);
		}
	}

	/**
	 * 
	 * @param symbol
	 * @return
	 */
	public String getQuoteName(String symbol) {
		return (symbol == null ? null : super.get(symbol));
	}
}
