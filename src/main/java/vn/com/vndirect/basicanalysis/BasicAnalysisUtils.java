package vn.com.vndirect.basicanalysis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import vn.com.vndirect.domain.embeddb.StockExchange;

public abstract class BasicAnalysisUtils {
	private static Logger logger = Logger.getLogger(BasicAnalysisUtils.class);
	private static Map<String, StockExchange> listHoseSymbol = new HashMap<String, StockExchange>();
	private static Map<String, StockExchange> listHnxSymbol = new HashMap<String, StockExchange>();
	private static Map<String, StockExchange> listUpcomSymbol = new HashMap<String, StockExchange>();
	private static Map<String, StockExchange> listAllSymbol = new HashMap<String, StockExchange>();

	public static Map<String, StockExchange> getListHoseSymbol() {
		return listHoseSymbol;
	}

	public static Map<String, StockExchange> getListHnxSymbol() {
		return listHnxSymbol;
	}

	public static Map<String, StockExchange> getListUpcomSymbol() {
		return listUpcomSymbol;
	}

	public static Map<String, StockExchange> getListAllSymbol() {
		return listAllSymbol;
	}

	public static void setListAllSymbol(Map<String, StockExchange> listAllSymbol) {
		BasicAnalysisUtils.listAllSymbol = listAllSymbol;
		if (!listAllSymbol.isEmpty()) {
			String HOSE_EXCHANGE = "HOSTC";
			String HNX_EXCHANGE = "HNX";
			String UPCOM_EXCHANGE = "UPCOM";
			for (StockExchange stockExchange : listAllSymbol.values()) {
				if (HOSE_EXCHANGE.equalsIgnoreCase(stockExchange.getExchangeCode())) {
					listHoseSymbol.put(stockExchange.getSymbol().toUpperCase(), stockExchange);
				} else if (HNX_EXCHANGE.equalsIgnoreCase(stockExchange.getExchangeCode())) {
					listHnxSymbol.put(stockExchange.getSymbol().toUpperCase(), stockExchange);
				} else if (UPCOM_EXCHANGE.equalsIgnoreCase(stockExchange.getExchangeCode())) {
					listUpcomSymbol.put(stockExchange.getSymbol().toUpperCase(), stockExchange);
				}
			}
		}
	}

}
