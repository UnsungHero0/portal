package vn.com.vndirect.worldmarket.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVParser;
import org.apache.log4j.Logger;

import vn.com.web.commons.exception.SystemException;

/**
 * http://www.gummy-stuff.org/Yahoo-data.htm
 * 
 * @author tung.nguyen
 * 
 */
public class YahooWorldMarketDAO implements IWorldMarketAdapter {
	private static Logger logger = Logger.getLogger(YahooWorldMarketDAO.class);
	// US: Dow: DJI + Nasdaq: ^IXIC + S&P 500: ^GSPC + 10 Yr Bond(%): ^TNX + Oil: CLN10.NYM + Gold: GCM10.CMX
	// EUROPE: FTSE 100: ^FTSE + DAX: ^GDAXI + CAC 40: ^FCHI
	// ASIA: Nikkei 225: ^N225 + Hang Seng: ^HSI + Straits Times: ^STI
	private final static String SYMBOLS_DEFAULT = "^DJI+^IXIC+^GSPC+^TNX+CLN10.NYM+GCM10.CMX+^FTSE+^GDAXI+^FCHI+^N225+^HSI+^STI+EURUSD=X";

	// http://finance.yahoo.com/d/quotes.csv?s=%s&f=sl1d1t1c6b3b2n&e=.csv
	private String quoteUrl = "http://finance.yahoo.com/d/quotes.csv?e=.csv";
	private MapQuote mapSymbols = new MapQuote();

	// +++ use in internal
	private String symbols = "";
	private String yahooQuoteUrl = "";
	private Map<String, String> fields = new HashMap<String, String>();

	private List<String> fieldKeys = new ArrayList<String>();

	public void init() {
		try {
			if (mapSymbols.size() > 0) {
				int count = 0;
				StringBuffer strBuf = new StringBuffer();
				for (String s : mapSymbols.keySet()) {
					strBuf.append(count == 0 ? "" : "+").append(s);
					count++;
				}
				symbols = strBuf.toString();
			} else {
				symbols = SYMBOLS_DEFAULT;
			}

			// +++ add field
			fields.put("SYMBOL", "s");
			fields.put("PRICE", "l1");
			fields.put("TRADE_DATE", "d1");
			fields.put("TRADE_TIME", "t1");
			fields.put("CHANGE", "c6");
			fields.put("PERCENT", "p2");
			fields.put("NAME", "n");

			// ++++ s=xxx
			String _s = URLEncoder.encode(symbols, "utf-8");

			// +++ f=s l1 d1 t1 c6 b3 b2 n
			StringBuffer _f = new StringBuffer();
			for (Map.Entry<String, String> entryField : fields.entrySet()) {
				_f.append(entryField.getValue());
				fieldKeys.add(entryField.getKey());
			}
			yahooQuoteUrl = quoteUrl + "&s=" + _s + "&f=" + _f.toString();
		} catch (Exception e) {
			logger.error("init() - " + e);
		}
	}

	public List<WorldQuote> retrieve() {
		List<WorldQuote> lsWorldQuote = new ArrayList<WorldQuote>();
		try {
			URL url = new URI(yahooQuoteUrl).toURL();
			URLConnection conn = url.openConnection();
			BufferedReader cin = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			CSVParser cvsParser = new CSVParser(cin);
			String[][] quoteValues = cvsParser.getAllValues();

			WorldQuote worldQuote;
			if (cvsParser.getLineNumber() > 0) {
				for (String[] qValues : quoteValues) {
					worldQuote = new WorldQuote();
					;
					for (int i = 0; i < qValues.length; i++) {
						worldQuote.addProp(fieldKeys.get(i), qValues[i]);

						if ("SYMBOL".equalsIgnoreCase(fieldKeys.get(i))) {
							worldQuote.setSymbol(qValues[i]);
						} else if ("PRICE".equalsIgnoreCase(fieldKeys.get(i))) {
							worldQuote.setIndex(this.toDouble(qValues[i]));
						} else if ("CHANGE".equalsIgnoreCase(fieldKeys.get(i))) {
							worldQuote.setChgIdx(this.toDouble(qValues[i]));
						} else if ("TRADE_DATE".equalsIgnoreCase(fieldKeys.get(i))) {
							worldQuote.setStrDate(qValues[i]);
						} else if ("TRADE_TIME".equalsIgnoreCase(fieldKeys.get(i))) {
							worldQuote.setTime(qValues[i]);
						}
					}

					// +++ calc change percent
					try {
						if (worldQuote.getChgIdx() != null && worldQuote.getIndex() != null && worldQuote.getIndex() != 0) {
							worldQuote.setPctIdx((worldQuote.getChgIdx() / worldQuote.getIndex()) * 100);
						}
					} catch (Exception e) {
					}

					worldQuote.setName(mapSymbols.getQuoteName(worldQuote.getSymbol()));

					// +++ convert date
					worldQuote.setDate(this.stringToDateFormat(worldQuote.getStrDate(), worldQuote.getTime()));

					// +++ add quote to list
					lsWorldQuote.add(worldQuote);
				}
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
			return (strValue == null || strValue.length() == 0 || strValue.equals("N/A") ? null : Double.valueOf(strValue));
		} catch (Exception e) {
		}
		return null;
	}

	private final static String STR_DATE_TIME_FORMAT_DDMMYYYY_HHMMAM = "d/M/yyyy hh:mma";
	private final static String STR_DATE_TIME_FORMAT_DDMMYYYY = "d/M/yyyy";

	/**
	 * 
	 * @param strDate
	 *            : 6/3/2010 || 6/12/2010
	 * @param strTime
	 *            : 4:02pm
	 * @return
	 * @throws SystemException
	 */
	public Date stringToDateFormat(String strDate, String strTime) {
		strDate = (strDate == null ? "" : strDate.trim());
		if (strDate.length() == 0) {
			return null;
		} else {
			try {
				SimpleDateFormat fm;
				strTime = (strTime == null ? "" : strTime.trim());
				if (strTime.length() == 0) {
					fm = new SimpleDateFormat(STR_DATE_TIME_FORMAT_DDMMYYYY);
					fm.setLenient(false);
					return fm.parse(strDate);
				} else {
					fm = new SimpleDateFormat(STR_DATE_TIME_FORMAT_DDMMYYYY_HHMMAM);
					fm.setLenient(false);
					return fm.parse(strDate + " " + strTime);
				}
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new Date());
		YahooWorldMarketDAO yW = new YahooWorldMarketDAO();
		yW.init();
		List<WorldQuote> lsWorldQuote = yW.retrieve();
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
	 * @return the fields
	 */
	public Map<String, String> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
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
