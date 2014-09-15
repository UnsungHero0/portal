package vn.com.vndirect.worldmarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.vndirect.worldmarket.adapter.IWorldMarketAdapter;
import vn.com.vndirect.worldmarket.adapter.WorldQuote;

public class WorldMarketManager {
	// private static Logger logger = Logger.getLogger(WorldMarketManager.class);
	private IWorldMarketAdapter worldMarketYAHAdapter;
	private IWorldMarketAdapter worldMarketGOOAdapter;

	// private MapQuote mapQuoteYAH;
	// private MapQuote mapQuoteGOO;

	private Map<String, WorldQuote> mapWorldQuote = new HashMap<String, WorldQuote>();

	/**
	 * @param worldMarketAdapter
	 *            the worldMarketYAHAdapter to set
	 */
	public void setWorldMarketYAHAdapter(IWorldMarketAdapter worldMarketYAHAdapter) {
		this.worldMarketYAHAdapter = worldMarketYAHAdapter;
	}

	/**
	 * @param worldMarketAdapter
	 *            the worldMarketGOOAdapter to set
	 */
	public void setWorldMarketGOOAdapter(IWorldMarketAdapter worldMarketGOOAdapter) {
		this.worldMarketGOOAdapter = worldMarketGOOAdapter;
	}

	/**
	 * used by batch process to retrieve WorldQuote infomation from Yahoo,...
	 * 
	 * @throws Exception
	 */
	public void checkWorldIndex() throws Exception {
		List<WorldQuote> lstWorldQuotes = worldMarketGOOAdapter.retrieve();
		if (lstWorldQuotes != null && lstWorldQuotes.size() > 0) {
			for (WorldQuote worldQuote : lstWorldQuotes) {
				mapWorldQuote.put(worldQuote.getSymbol(), worldQuote);
			}
		}
		lstWorldQuotes = worldMarketYAHAdapter.retrieve();
		if (lstWorldQuotes != null && lstWorldQuotes.size() > 0) {
			for (WorldQuote worldQuote : lstWorldQuotes) {
				mapWorldQuote.put(worldQuote.getSymbol(), worldQuote);
			}
		}
	}

	/**
	 * 
	 * @param symbol
	 * @return
	 */
	public WorldQuote findWorldQuote(String symbol) {
		return (symbol == null ? null : mapWorldQuote.get(symbol));
	}

	/**
	 * 
	 * @param symbols
	 * @return
	 */
	public List<WorldQuote> findWorldQuotes(List<String> symbols) {
		if (symbols != null) {
			List<WorldQuote> lstWorldQuote = new ArrayList<WorldQuote>();
			WorldQuote worldQuote;
			for (String symbol : symbols) {
				worldQuote = (symbol == null ? null : mapWorldQuote.get(symbol));
				if (worldQuote != null) {
					lstWorldQuote.add(worldQuote);
				}
			}
			return lstWorldQuote;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<WorldQuote> findAllWorldQuotes() {
		List<WorldQuote> lstWorldQuote = new ArrayList<WorldQuote>();
		if (mapWorldQuote != null) {
			lstWorldQuote.addAll(mapWorldQuote.values());
		}
		return lstWorldQuote;
	}

	// /**
	// *
	// * @param symbol
	// * @return
	// */
	// public String getWorldQuoteName(String symbol) {
	// if (mapQuoteGOO.getQuoteName(symbol) != null) {
	// return mapQuoteGOO.getQuoteName(symbol);
	// } else {
	// return mapQuoteYAH.getQuoteName(symbol);
	// }
	// }
	//
	// /**
	// * @return the mapQuoteYAH
	// */
	// public MapQuote getMapQuoteYAH() {
	// return mapQuoteYAH;
	// }
	//
	// /**
	// * @param mapQuoteYAH the mapQuoteYAH to set
	// */
	// public void setMapQuoteYAH(MapQuote mapQuoteYAH) {
	// this.mapQuoteYAH = mapQuoteYAH;
	// }
	//	
	// /**
	// * @return the mapQuoteGOO
	// */
	// public MapQuote getMapQuoteGOO() {
	// return mapQuoteGOO;
	// }
	//
	// /**
	// * @param mapQuoteGOO the mapQuoteGOO to set
	// */
	// public void setMapQuoteGOO(MapQuote mapQuoteGOO) {
	// this.mapQuoteGOO = mapQuoteGOO;
	// }
}
