package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoTradingStatisticsView;
import vn.com.vndirect.domain.IfoTradingStatisticsViewId;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * A data access object (DAO) providing persistence and search support for
 * IfoTradingStatisticsView entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.vndirect.IfoTradingStatisticsView
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class IfoTradingStatisticsViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoTradingStatisticsViewDAO.class);

	// private final ResultSetProcessor ifoTradingStatisticsViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	//
	// IfoTradingStatisticsView obj = new IfoTradingStatisticsView();
	// IfoTradingStatisticsViewId objId = new IfoTradingStatisticsViewId();
	// // objId.setFloorCode(rs.getString("FLOOR_CODE"));
	// objId.setSecCode(rs.getString("SEC_CODE"));
	// objId.setTransDate(rs.getDate("TRANS_DATE"));
	//
	// objId.setOpenPrice(new Long(rs.getLong("OPEN_PRICE")));
	// objId.setHighPrice(new Long(rs.getLong("HIGH_PRICE")));
	// objId.setLowPrice(new Long(rs.getLong("LOW_PRICE")));
	// objId.setClosePrice(new Long(rs.getLong("CLOSE_PRICE")));
	// objId.setEveragePrice(new Long(rs.getLong("EVERAGE_PRICE")));
	// objId.setBidOrder(new Long(rs.getLong("BID_ORDER")));
	// objId.setBidVolumn(new Long(rs.getLong("BID_VOLUMN")));
	// objId.setOfferOrder(new Long(rs.getLong("OFFER_ORDER")));
	// objId.setOfferVolumn(new Long(rs.getLong("OFFER_VOLUMN")));
	// objId.setTotalVolumn(new Long(rs.getLong("TOTAL_VOLUMN")));
	// objId.setTotalValue(new Long(rs.getLong("TOTAL_VALUE")));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };

	@SuppressWarnings("rawtypes")
	private final RowMapper ifoTradingStatisticsViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoTradingStatisticsView obj = new IfoTradingStatisticsView();
			IfoTradingStatisticsViewId objId = new IfoTradingStatisticsViewId();
			// objId.setFloorCode(rs.getString("FLOOR_CODE"));
			objId.setSecCode(rs.getString("SEC_CODE"));
			objId.setTransDate(rs.getDate("TRANS_DATE"));

			objId.setOpenPrice(new Double(rs.getDouble("OPEN_PRICE")));
			objId.setHighPrice(new Double(rs.getDouble("HIGH_PRICE")));
			objId.setLowPrice(new Double(rs.getDouble("LOW_PRICE")));
			objId.setClosePrice(new Double(rs.getDouble("CLOSE_PRICE")));
			objId.setEveragePrice(new Double(rs.getDouble("EVERAGE_PRICE")));
			objId.setBidOrder(new Double(rs.getDouble("BID_ORDER")));
			objId.setBidVolumn(new Double(rs.getDouble("BID_VOLUMN")));
			objId.setOfferOrder(new Double(rs.getDouble("OFFER_ORDER")));
			objId.setOfferVolumn(new Double(rs.getDouble("OFFER_VOLUMN")));
			objId.setTotalVolumn(new Double(rs.getDouble("TOTAL_VOLUMN")));
			objId.setTotalValue(new Double(rs.getDouble("TOTAL_VALUE")));
			obj.setId(objId);
			return obj;
		}
	};

	@SuppressWarnings("rawtypes")
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchTradingStatistics(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoTradingStatisticsView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_TRADING_STATISTICS_VIEW ts "
			        + " where ts.FLOOR_CODE in (:FLOOR_CODE) and ts.TRANS_DATE = :TRANS_DATE " + " ORDER BY UPPER(ts.SEC_CODE) ";

			String market = searchMarketStatisticsView.getMarket();
			market = (market == null ? "" : market);
			log.debug(LOCATION + ":: market = " + market);
			// params.add(new Parameter(":EXCHANGE_CODE",
			// market.toUpperCase()));

			String[] listFloorCodes = null;
			if (market.equalsIgnoreCase(VNDirectWebUtilities.getHASTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHASTCFloorCode();
			} else if (market.equalsIgnoreCase(VNDirectWebUtilities.getHOSTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHOSTCFloorCode();
			}

			SearchResult<IfoTradingStatisticsView> result = null;
			if (listFloorCodes != null && listFloorCodes.length > 0) {
				listFloorCodes = (listFloorCodes == null ? new String[0] : listFloorCodes);
				StringBuffer strBuf = new StringBuffer();
				int size = listFloorCodes.length;
				for (int i = 0; i < size; i++) {
					strBuf.append(strBuf.length() == 0 ? "" : ",").append("'").append(listFloorCodes[i]).append("'");
				}

				log.debug(LOCATION + ":: strBuf = " + strBuf.toString());
				// params.add(new Parameter(":FLOOR_CODE", strBuf.toString()));
				// SEARCH_SQL = Utility.replaceString(SEARCH_SQL, ":FLOOR_CODE",
				// strBuf.toString());
				SEARCH_SQL = SEARCH_SQL.replaceAll(":FLOOR_CODE", strBuf.toString());
				log.debug(LOCATION + ":: SEARCH_SQL = " + SEARCH_SQL);

				// List params = new ArrayList();
				// params.add(new Parameter(":TRANS_DATE",
				// searchIfoTradingStatisticsView.getTradingDate()));
				// log.debug(LOCATION + ":: TRANS_DATE" +
				// searchIfoTradingStatisticsView.getTradingDate());
				// ReplaceableParams replaceableParams = new
				// ReplaceableParams();
				//
				// Connection conn = this.getCurrentConnection();
				// EasyStatement stat = new EasyStatement(SEARCH_SQL);
				//
				// result =
				// DAOHelper.searchByPaging(searchIfoTradingStatisticsView,
				// conn, stat
				// , (Parameter[])params.toArray(new Parameter[params.size()])
				// , ifoTradingStatisticsViewProcessor
				// , replaceableParams);

				Map paramMap = new HashMap();
				paramMap.put("TRANS_DATE", searchMarketStatisticsView.getTradingDate());
				log.debug(LOCATION + ":: TRANS_DATE" + searchMarketStatisticsView.getTradingDate());

				result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL, paramMap,
				        ifoTradingStatisticsViewRowMapper, pagingInfo);
			}

			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoTradingStatisticsView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchIfoTradingStatisticsView
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult<IfoTradingStatisticsView> reportTradingStatistics(SearchMarketStatisticsView searchMarketStatisticsView)
	        throws SystemException {
		final String LOCATION = "reportTradingStatistics(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoTradingStatisticsView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_TRADING_STATISTICS_VIEW ts "
			        + " where ts.FLOOR_CODE in (:FLOOR_CODE) and ts.TRANS_DATE = :TRANS_DATE " + " ORDER BY UPPER(ts.SEC_CODE) ";

			String market = searchMarketStatisticsView.getMarket();
			market = (market == null ? "" : market);
			log.debug(LOCATION + ":: market = " + market);
			// params.add(new Parameter(":EXCHANGE_CODE",
			// market.toUpperCase()));

			String[] listFloorCodes = null;
			if (market.equalsIgnoreCase(VNDirectWebUtilities.getHASTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHASTCFloorCode();
			} else if (market.equalsIgnoreCase(VNDirectWebUtilities.getHOSTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHOSTCFloorCode();
			}
			SearchResult<IfoTradingStatisticsView> result = null;
			if (listFloorCodes != null && listFloorCodes.length > 0) {
				listFloorCodes = (listFloorCodes == null ? new String[0] : listFloorCodes);
				StringBuffer strBuf = new StringBuffer();
				int size = listFloorCodes.length;
				for (int i = 0; i < size; i++) {
					strBuf.append(strBuf.length() == 0 ? "" : ",").append("'").append(listFloorCodes[i]).append("'");
				}

				log.debug(LOCATION + ":: strBuf = " + strBuf.toString());
				// params.add(new Parameter(":FLOOR_CODE", strBuf.toString()));
				// SEARCH_SQL = Utility.replaceString(SEARCH_SQL, ":FLOOR_CODE",
				// strBuf.toString());
				SEARCH_SQL = SEARCH_SQL.replaceAll(":FLOOR_CODE", strBuf.toString());
				log.debug(LOCATION + ":: SEARCH_SQL = " + SEARCH_SQL);

				// List params = new ArrayList();
				// params.add(new Parameter(":TRANS_DATE",
				// searchIfoTradingStatisticsView.getTradingDate()));
				// log.debug(LOCATION + ":: TRANS_DATE" +
				// searchIfoTradingStatisticsView.getTradingDate());
				// ReplaceableParams replaceableParams = new
				// ReplaceableParams();
				//
				// Connection conn = this.getCurrentConnection();
				// EasyStatement stat = new EasyStatement(SEARCH_SQL);
				//
				// result = DAOHelper.searchByPaging(null, conn, stat
				// , (Parameter[])params.toArray(new Parameter[params.size()])
				// , ifoTradingStatisticsViewProcessor
				// , replaceableParams);

				Map paramMap = new HashMap();
				paramMap.put("TRANS_DATE", searchMarketStatisticsView.getTradingDate());
				log.debug(LOCATION + ":: TRANS_DATE" + searchMarketStatisticsView.getTradingDate());

				result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap, ifoTradingStatisticsViewRowMapper,
				        null);
			}

			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoTradingStatisticsView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchIfoTradingStatisticsView
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics4Symbol(
	        SearchMarketStatisticsView searchMarketStatisticsView, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchTradingStatistics4Symbol(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null || searchMarketStatisticsView.getSymbol() == null
		        || searchMarketStatisticsView.getSymbol().trim().length() == 0) {
			throw new SystemException(LOCATION, "searchMarketStatisticsView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_TRADING_STATISTICS_VIEW where UPPER(SEC_CODE) = :SEC_CODE ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SEC_CODE", searchMarketStatisticsView.getSymbol().toUpperCase());

			if (searchMarketStatisticsView.getFromDate() != null) {
				SEARCH_SQL += " and TRANS_DATE >= :TRANS_DATE_FROM ";
				paramMap.put("TRANS_DATE_FROM", searchMarketStatisticsView.getFromDate());
			}

			if (searchMarketStatisticsView.getToDate() != null) {
				SEARCH_SQL += " and TRANS_DATE <= :TRANS_DATE_TO ";
				paramMap.put("TRANS_DATE_TO", searchMarketStatisticsView.getToDate());
			}

			SEARCH_SQL += " ORDER BY TRANS_DATE DESC";

			SearchResult<IfoTradingStatisticsView> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoTradingStatisticsViewRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoTradingStatisticsView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}