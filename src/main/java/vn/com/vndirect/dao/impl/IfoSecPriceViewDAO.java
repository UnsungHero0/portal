package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoSecPriceViewId;
import vn.com.vndirect.domain.MatchOrder;
import vn.com.vndirect.domain.MatchOrderId;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoSecPriceView.
 * 
 * @see vn.com.vndirect.dao.impl.IfoSecPriceView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoSecPriceViewDAO extends BaseDAOImpl {

	public static final String TRANS_DATE = "transDate";

	public static final String SYMBOL = "symbol";

	private static final Log log = LogFactory.getLog(IfoSecPriceViewDAO.class);

	// ++ Using SpringDAO ++ //
	private RowMapper<IfoSecPriceView> ifoSecPriceViewRowMapper = new RowMapper<IfoSecPriceView>() {
		public IfoSecPriceView mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoSecPriceView obj = new IfoSecPriceView();
			IfoSecPriceViewId objId = new IfoSecPriceViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setSymbol(rs.getString("SYMBOL"));
			objId.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			objId.setOpenPrice(new Double(rs.getDouble("OPEN_PRICE")));
			objId.setHighPrice(new Double(rs.getDouble("HIGH_PRICE")));
			objId.setLowPrice(new Double(rs.getDouble("LOW_PRICE")));
			objId.setClosePrice(new Double(rs.getDouble("CLOSE_PRICE")));
			objId.setVolume(new Double(rs.getDouble("VOLUME")));
			objId.setTransDate(rs.getDate("TRANS_DATE"));
			objId.setAveragePrice(new Double(rs.getDouble("AVERAGE_PRICE")));

			try {
				objId.setAdOpenPrice(new Double(rs.getDouble("AD_OPEN_PRICE")));
			} catch (Exception e) {
			}

			try {
				objId.setAdHighPrice(new Double(rs.getDouble("AD_HIGH_PRICE")));
			} catch (Exception e) {
			}

			try {
				objId.setAdLowPrice(new Double(rs.getDouble("AD_LOW_PRICE")));
			} catch (Exception e) {
			}

			try {
				objId.setAdClosePrice(new Double(rs.getDouble("AD_CLOSE_PRICE")));
			} catch (Exception e) {
			}

			try {
				objId.setAdAveragePrice(new Double(rs.getDouble("AD_AVERAGE_PRICE")));
			} catch (Exception e) {
			}

			try {
				objId.setRightsType(new String(rs.getString("RIGHTS_TYPE")));
			} catch (Exception e) {
			}
			try {
				objId.setPtVolume(new Double(rs.getDouble("PUT_THROUGH_VOLUME")));
			} catch (Exception e) {
			}
			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * get all price of a symbol on a exchange
	 * 
	 * @param searchObj
	 * @return collection of IfoSecPriceView object.
	 * @throws SystemException
	 */
	public SearchResult<IfoSecPriceView> findStockPrices(SearchIfoSecPrice searchObj, PagingInfo pagingInfo)
	        throws SystemException {
		final String LOCATION = "findStockPrices(searchObj:" + searchObj + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "searchObj is NULL...");
		}
		try {
			final String SELECT_SQL = "SELECT ifoSecPriceView.* FROM IFO_SEC_PRICE_VIEW ifoSecPriceView ";
			StringBuffer WHERE_SQL = new StringBuffer(" WHERE UPPER(ifoSecPriceView.SYMBOL) = :SYMBOL ");

			final String ORDER_SQL = " ORDER BY ifoSecPriceView.TRANS_DATE "
			        + (searchObj.isTradingTimeSortASC() ? "ASC" : "DESC");

			Map paramMap = new HashMap();
			// add SYMBOL
			String str = searchObj.getSymbol();
			str = (str == null ? "" : str.toUpperCase().trim());
			paramMap.put("SYMBOL", str.toUpperCase());

			// add EXCHANGE_CODE
			str = searchObj.getExchangeCode();

			str = (str == null || str.trim().length() == 0 ? "" : str.toUpperCase().trim());
			if (str.length() > 0) {
				WHERE_SQL.append(" AND  UPPER(ifoSecPriceView.EXCHANGE_CODE) = :EXCHANGE_CODE ");
				paramMap.put("EXCHANGE_CODE", str.toUpperCase());
			}

			if (searchObj.getOffsetItems() < 1) {
				// Start date
				Date startDate = searchObj.getStartDate();
				if (startDate != null) {
					WHERE_SQL.append(" AND ifoSecPriceView.TRANS_DATE >= :START_DATE ");
					paramMap.put("START_DATE", startDate);
				}

				// End Date
				Date endDate = searchObj.getEndDate();
				if (endDate != null) {
					WHERE_SQL.append(" AND ifoSecPriceView.TRANS_DATE <= :END_DATE ");
					paramMap.put("END_DATE", endDate);
				}

			}

			String sql = SELECT_SQL + WHERE_SQL.toString() + ORDER_SQL;
			SearchResult<IfoSecPriceView> searchResult = null;

			if (searchObj.getOffsetItems() < 1) {

				searchResult = OracleDAOHelper.query(this.getDataSource(), sql, paramMap, ifoSecPriceViewRowMapper);
			} else {

				// searchObj.setNumberItem(searchObj.getOffsetItems());
				pagingInfo = new PagingInfo();
				pagingInfo.setOffset(searchObj.getOffsetItems());
				searchResult = OracleDAOHelper.query(this.getDataSource(), sql, paramMap, ifoSecPriceViewRowMapper, pagingInfo);
			}

			log.debug(LOCATION + ":: END - searchResult:" + searchResult);

			return (searchResult == null ? null : searchResult);
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// -- Using SpringDAO -- //

	/**
	 * 
	 * @param searchObj
	 *            - contains sybmol, startDate and endDate
	 * @return
	 * @throws SystemException
	 */
	public Collection findStockPricesByPeriod(SearchIfoSecPrice searchObj, PagingInfo pagingInfo, boolean isChartDynamicPriceTime)
	        throws SystemException {
		final String LOCATION = "findStockPricesByPeriod(searchObj:" + searchObj + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "searchObj is NULL...");
		}
		try {
			final String SELECT_SQL = "SELECT * FROM (SELECT ifoSecPriceView.* FROM IFO_SEC_PRICE_VIEW ifoSecPriceView ";
			StringBuffer WHERE_SQL = new StringBuffer(" WHERE UPPER(ifoSecPriceView.SYMBOL) = :SYMBOL ");

			final String ORDER_SQL = " ORDER BY TRANS_DATE " + (searchObj.isTradingTimeSortASC() ? "ASC" : "DESC") + ") ";

			Map paramMap = new HashMap();

			// add SYMBOL
			String str = searchObj.getSymbol();
			str = (str == null ? "" : str.toUpperCase().trim());
			paramMap.put("SYMBOL", str.toUpperCase());

			// add EXCHANGE_CODE
			str = searchObj.getExchangeCode();
			str = (str == null || str.trim().length() == 0 ? "" : str.toUpperCase().trim());
			if (str.length() > 0) {
				WHERE_SQL.append(" AND  UPPER(ifoSecPriceView.EXCHANGE_CODE) = :EXCHANGE_CODE ");
				paramMap.put("EXCHANGE_CODE", str.toUpperCase());
			}

			// Start date
			Date startDate = searchObj.getStartDate();
			if (startDate != null) {
				WHERE_SQL.append(" AND ifoSecPriceView.TRANS_DATE >= :START_DATE ");
				paramMap.put("START_DATE", startDate);
			}

			// End Date
			Date endDate = searchObj.getEndDate();
			if (endDate != null) {
				WHERE_SQL.append(" AND ifoSecPriceView.TRANS_DATE <= :END_DATE ");
				paramMap.put("END_DATE", endDate);
			}

			StringBuilder UNION_SEC_INFO_SQL = new StringBuilder();
			if (isChartDynamicPriceTime) {
				if (StringUtils.isNotEmpty(searchObj.getFloorCode())) {
					String floorCode[] = searchObj.getFloorCode().trim().split(",");
					String inFloorCode = "('";
					for (int i = 0; i < floorCode.length; i++) {
						if (i < floorCode.length - 1) {
							inFloorCode += floorCode[i] + "','";
						} else {
							inFloorCode += floorCode[i] + "')";
						}
					}

					UNION_SEC_INFO_SQL.append(" UNION ALL SELECT * FROM (SELECT 0 COMPANY_ID, '' EXCHANGE_CODE, '' SYMBOL,");
					UNION_SEC_INFO_SQL
					        .append("0 OPEN_PRICE,0 HIGH_PRICE,0 LOW_PRICE,0 CLOSE_PRICE,0 AVERAGE_PRICE,TOTAL_QTTY VOLUME, TOTAL_VALUE TOTAL_VALUE,TRADING_DATE TRANS_DATE,");
					UNION_SEC_INFO_SQL
					        .append("(CASE WHEN OPEN_PRICE = 0 OR OPEN_PRICE IS NULL THEN CURRENT_INDEX ELSE OPEN_PRICE END) AD_OPEN_PRICE,");
					UNION_SEC_INFO_SQL
					        .append("(CASE WHEN HIGHEST_PRICE = 0 OR HIGHEST_PRICE IS NULL THEN CURRENT_INDEX ELSE HIGHEST_PRICE END) AD_HIGH_PRICE,");
					UNION_SEC_INFO_SQL
					        .append("(CASE WHEN LOWEST_PRICE = 0 OR LOWEST_PRICE IS NULL THEN CURRENT_INDEX ELSE LOWEST_PRICE END) AD_LOW_PRICE,CURRENT_INDEX AD_CLOSE_PRICE,");
					UNION_SEC_INFO_SQL.append("0 AD_AVERAGE_PRICE,0 PUT_THROUGH_VOLUME,0 PUT_THROUGH_VALUE,'' RIGHTS_TYPE"
					        + " FROM MARKET_INFO WHERE FLOOR_CODE IN " + inFloorCode + ")");
				} else {
					UNION_SEC_INFO_SQL
					        .append(" UNION ALL SELECT * FROM (SELECT STOCK_ID COMPANY_ID,'' EXCHANGE_CODE,CODE SYMBOL, 0 OPEN_PRICE,0 HIGH_PRICE,0 LOW_PRICE,0 CLOSE_PRICE,");
					UNION_SEC_INFO_SQL.append("0 AVERAGE_PRICE,TOTAL_TRADING_QTTY VOLUME,TOTAL_TRADING_VALUE TOTAL_VALUE,"
					        + "TRADING_DATE TRANS_DATE,");
					UNION_SEC_INFO_SQL.append("(CASE OPEN_PRICE WHEN 0 THEN BASIC_PRICE ELSE OPEN_PRICE END) AD_OPEN_PRICE,"
					        + "(CASE HIGHEST_PRICE WHEN 0 THEN BASIC_PRICE ELSE HIGHEST_PRICE END) AD_HIGH_PRICE,");
					UNION_SEC_INFO_SQL.append("(CASE LOWEST_PRICE WHEN 0 THEN BASIC_PRICE ELSE LOWEST_PRICE END) AD_LOW_PRICE,"
					        + "(CASE CURRENT_PRICE WHEN 0 THEN BASIC_PRICE ELSE CURRENT_PRICE END) AD_CLOSE_PRICE,");
					UNION_SEC_INFO_SQL.append("AVERAGE_PRICE AD_AVERAGE_PRICE," + "0 PUT_THROUGH_VOLUME,"
					        + "0 PUT_THROUGH_VALUE,'' RIGHTS_TYPE" + " FROM SEC_INFO WHERE UPPER(CODE) = :SYMBOL) ");
				}
			}

			String sql = SELECT_SQL + WHERE_SQL.toString() + ORDER_SQL + UNION_SEC_INFO_SQL.toString();
			SearchResult searchResult = null;
			if (searchObj.getOffsetItems() < 1) {
				searchResult = OracleDAOHelper.query(this.getDataSource(), sql, paramMap, ifoSecPriceViewRowMapper, pagingInfo);
			} else {
				searchObj.setNumberItem(searchObj.getOffsetItems());
				searchResult = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql, paramMap, ifoSecPriceViewRowMapper,
				        pagingInfo);
			}

			log.debug(LOCATION + ":: END - size:" + searchResult);
			return (searchResult == null ? null : searchResult);
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoSecPriceView> searchHistoricalPrice(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchHistoricalPrice(searchMarketStatisticsView: " + searchMarketStatisticsView + ")";
		try {
			log.debug(LOCATION + ":: BEGIN");
			StringBuffer strBuf = new StringBuffer("SELECT * FROM IFO_SEC_PRICE_VIEW WHERE ");
			Map paramMap = new HashMap();

			strBuf.append("upper(SYMBOL)").append("= :SYMBOL ");
			paramMap.put("SYMBOL", searchMarketStatisticsView.getSymbol() == null ? "" : searchMarketStatisticsView.getSymbol()
			        .toUpperCase());
			log.debug("searchMarketStatisticsView.getSymbol():" + searchMarketStatisticsView.getSymbol());

			if (searchMarketStatisticsView.getFromDate() != null) {
				strBuf.append(" AND TRANS_DATE >= :FROM_TRANS_DATE ");
				paramMap.put("FROM_TRANS_DATE", searchMarketStatisticsView.getFromDate());
				log.debug("searchIfoSecPriceView.getFromDate():" + searchMarketStatisticsView.getFromDate());
			}

			if (searchMarketStatisticsView.getToDate() != null) {
				strBuf.append(" AND TRANS_DATE <= :TO_TRANS_DATE ");
				paramMap.put("TO_TRANS_DATE", searchMarketStatisticsView.getToDate());
				log.debug("searchIfoSecPriceView.getToDate():" + searchMarketStatisticsView.getToDate());
			}

			strBuf.append(" ORDER BY TRANS_DATE DESC ");

			SearchResult<IfoSecPriceView> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), strBuf.toString(),
			        paramMap, ifoSecPriceViewRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END - result:" + result);
			return result;
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoSecPriceView> reportHistoricalPrice(SearchMarketStatisticsView searchMarketStatisticsView)
	        throws SystemException {
		final String LOCATION = "reportHistoricalPrice(searchMarketStatisticsView: " + searchMarketStatisticsView + ")";
		try {
			if (log.isDebugEnabled()) {
				log.debug(LOCATION + ":: BEGIN");
			}
			StringBuffer strBuf = new StringBuffer("SELECT * FROM IFO_SEC_PRICE_VIEW WHERE ");
			Map<String, Object> paramMap = new HashMap<String, Object>();

			strBuf.append("upper(SYMBOL)").append("= :SYMBOL ");
			paramMap.put("SYMBOL", searchMarketStatisticsView.getSymbol() == null ? "" : searchMarketStatisticsView.getSymbol()
			        .toUpperCase());
			if (log.isDebugEnabled()) {
				log.debug("searchMarketStatisticsView.getSymbol():" + searchMarketStatisticsView.getSymbol());
			}

			if (searchMarketStatisticsView.getFromDate() != null) {
				strBuf.append(" AND TRANS_DATE >= :FROM_DATE ");
				paramMap.put("FROM_DATE", searchMarketStatisticsView.getFromDate());
				if (log.isDebugEnabled()) {
					log.debug("searchIfoSecPriceView.getFromDate():" + searchMarketStatisticsView.getFromDate());
				}
			}

			if (searchMarketStatisticsView.getToDate() != null) {
				strBuf.append(" AND TRANS_DATE <= :TO_DATE ");
				paramMap.put("TO_DATE", searchMarketStatisticsView.getToDate());
				if (log.isDebugEnabled()) {
					log.debug("searchIfoSecPriceView.getToDate():" + searchMarketStatisticsView.getToDate());
				}
			}

			strBuf.append(" ORDER BY TRANS_DATE DESC ");

			SearchResult<IfoSecPriceView> result = OracleDAOHelper.query(this.getDataSource(), strBuf.toString(), paramMap,
			        ifoSecPriceViewRowMapper);

			if (log.isDebugEnabled()) {
				log.debug(LOCATION + ":: END - result:" + result);
			}
			return result;
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}

	public SearchResult searchPriceInDays(String symbol, int[] inDays) throws SystemException {
		final String LOCATION = "searchPriceInDays(symbol: " + symbol + ")";
		try {
			log.debug(LOCATION + ":: BEGIN");
			StringBuffer strBuf = new StringBuffer(
			        "SELECT * FROM(SELECT ROWNUM as COUNT_DATE, TBL_SEC_PRICE_TMP.* FROM (SELECT a.* FROM IFO_SEC_PRICE_VIEW a WHERE upper(a.SYMBOL)=:SYMBOL ORDER BY TRANS_DATE DESC) TBL_SEC_PRICE_TMP) ");

			Map paramMap = new HashMap();
			paramMap.put("SYMBOL", symbol == null ? "" : symbol.toUpperCase());

			strBuf.append(" WHERE COUNT_DATE IN (0");
			for (int day : inDays) {
				strBuf.append(", ").append(day);
			}
			strBuf.append(")");

			SearchResult result = OracleDAOHelper.query(this.getDataSource(), strBuf.toString(), paramMap,
			        ifoSecPriceViewRowMapper, null);

			log.debug(LOCATION + ":: END - result:" + result);
			return result;
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}

	private final RowMapper intradayPriceProcessor = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			MatchOrder obj = new MatchOrder();
			MatchOrderId objId = new MatchOrderId();
			objId.setFloorCode(rs.getString("floor_code"));
			objId.setCode(rs.getString("code"));
			objId.setTime(rs.getString("time"));
			objId.setMatchPrice(new Double(rs.getDouble("match_price")));
			objId.setMatchQuantity(new Double(rs.getDouble("match_qtty")));
			objId.setTradingDate(rs.getDate("trading_date"));

			obj.setId(objId);

			return obj;
		}
	};

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public Collection findIntradayStockPrices(SearchIfoSecPrice searchObj) throws SystemException {
		final String LOCATION = "findIntradayStockPrices(searchObj:" + searchObj + ")";
		log.debug(LOCATION + ":: BEGIN");

		if (searchObj == null) {
			throw new SystemException(LOCATION, "code is NULL...");
		}
		try {
			final String SELECT_SQL = "SELECT a.floor_code,  a.trading_date, a.time,a.code, a.match_price, a.match_qtty "
			        + " FROM match_orders a ";
			StringBuffer WHERE_SQL = new StringBuffer(" where code = :code ");

			final String ORDER_SQL = " ORDER by trading_date,time ASC";

			// List params = new ArrayList();
			Map paramMap = new HashMap();

			// add SYMBOL
			String str = searchObj.getSymbol();
			str = (str == null ? "" : str.toUpperCase().trim());
			// params.add(new Parameter(":code", str.toUpperCase()));
			paramMap.put("code", str.toUpperCase());

			Date startDate = searchObj.getStartDate();
			if (startDate != null) {
				WHERE_SQL.append(" AND a.trading_date >= :START_DATE ");
				// params.add(new Parameter(":START_DATE", startDate));
				paramMap.put("START_DATE", startDate);
			}

			// End Date
			Date endDate = searchObj.getEndDate();
			if (endDate != null) {
				WHERE_SQL.append(" AND a.trading_date <= :END_DATE ");
				// params.add(new Parameter(":END_DATE", endDate));
				paramMap.put("END_DATE", endDate);
			}

			String sql = SELECT_SQL + WHERE_SQL.toString() + ORDER_SQL;
			SearchResult searchResult = OracleDAOHelper.query(this.getDataSource(), sql, paramMap, intradayPriceProcessor);

			log.debug(LOCATION + ":: END : searchResult " + searchResult);
			return (searchResult == null ? new ArrayList() : searchResult);

		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}