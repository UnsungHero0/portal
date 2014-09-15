package vn.com.vndirect.worldmarket.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.VNDirectDateUtils;

/**
 * 
 * @author duc.nguyen
 * 
 */
public class GoogleWorldMarketDAO implements IWorldMarketAdapter {
	private static Logger logger = Logger.getLogger(GoogleWorldMarketDAO.class);

	private final static String SYMBOLS_DEFAULT = ".IXIC,.INX,.DJI";
	private String quoteUrl = "http://finance.google.com/finance/info?client=ig";
	private MapQuote mapSymbols = new MapQuote();

	// +++ use in internal
	private String symbols = "";
	private String googleQuoteUrl = "";
	private final static String STR_DATE_TIME_FORMAT_YYYYMMMDD_HHMMAM = "yyyy MMM dd, hh:mma";

	public void init() {
		try {
			if (mapSymbols.size() > 0) {
				int count = 0;
				StringBuffer strBuf = new StringBuffer();
				for (String s : mapSymbols.keySet()) {
					strBuf.append(count == 0 ? "" : ",").append(s);
					count++;
				}
				symbols = strBuf.toString();
			} else {
				symbols = SYMBOLS_DEFAULT;
			}

			// ++++ s=xxx
			String _s = URLEncoder.encode(symbols, "utf-8");
			googleQuoteUrl = quoteUrl + "&q=" + _s;
		} catch (Exception e) {
			logger.error("init() - " + e);
		}
	}

	public List<WorldQuote> retrieve() {
		List<WorldQuote> lsWorldQuote = new ArrayList<WorldQuote>();
		try {
			URL url = new URI(googleQuoteUrl).toURL();
			URLConnection conn = url.openConnection();
			BufferedReader cin = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			StringBuffer strBuffer = new StringBuffer();
			while ((line = cin.readLine()) != null) {
				strBuffer.append(line);
			}

			JSONArray jsonArray = JSONArray.fromObject(strBuffer.toString().substring(3));
			JSONObject json = null;
			WorldQuote worldQuote;
			String currentYear = "" + VNDirectDateUtils.getCurrentYear();
			for (int i = 0; i < jsonArray.size(); i++) {
				json = (JSONObject) jsonArray.get(i);
				worldQuote = new WorldQuote();

				if ("SHA".equalsIgnoreCase(json.getString("e"))) {
					worldQuote.setSymbol(json.getString("e") + ":" + json.getString("t"));
				} else {
					worldQuote.setSymbol(json.getString("t"));
				}
				worldQuote.setName(mapSymbols.getQuoteName(worldQuote.getSymbol()));
				worldQuote.setIndex(this.toDouble(json.getString("l")));
				worldQuote.setChgIdx(this.toDouble(json.getString("c")));
				worldQuote.setPctIdx(this.toDouble(json.getString("cp")));
				try {
					worldQuote.setDate(VNDirectDateUtils.stringToDate(currentYear + " " + json.getString("lt"), STR_DATE_TIME_FORMAT_YYYYMMMDD_HHMMAM));
				} catch (Exception e) {
				}
				worldQuote.setTime(json.getString("ltt"));

				// +++ add quote to list
				lsWorldQuote.add(worldQuote);
			}

		} catch (Exception e) {
			logger.error("retrieve() - ", e);
		}
		return lsWorldQuote;
	}

	/**
	 * 
	 * @param strValue
	 * @return
	 */
	private Double toDouble(String strValue) {
		try {
			return (strValue == null || strValue.length() == 0 || strValue.equals("N/A") ? null : Double.valueOf(strValue.replaceAll(",", "")));
		} catch (Exception e) {
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(new Date());
		GoogleWorldMarketDAO googWorldDAO = new GoogleWorldMarketDAO();
		googWorldDAO.init();
		List<WorldQuote> lsWorldQuote = googWorldDAO.retrieve();
		System.out.println(new Date());
		for (WorldQuote worldQuote : lsWorldQuote) {
			System.out.println(worldQuote);
		}

	}

	/**
	 * @return the quoteUrl
	 */
	public String getQuoteUrl() {
		return quoteUrl;
	}

	/**
	 * @param quoteUrl
	 *            the quoteUrl to set
	 */
	public void setQuoteUrl(String quoteUrl) {
		this.quoteUrl = quoteUrl;
	}

	/**
	 * @return the mapSymbols
	 */
	public MapQuote getMapSymbols() {
		return mapSymbols;
	}

	/**
	 * @param mapSymbols
	 *            the mapSymbols to set
	 */
	public void setMapSymbols(MapQuote mapSymbols) {
		this.mapSymbols = mapSymbols;
	}
}
