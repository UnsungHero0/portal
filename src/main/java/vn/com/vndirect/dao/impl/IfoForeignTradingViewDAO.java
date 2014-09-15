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
import vn.com.vndirect.domain.IfoForeignTradingView;
import vn.com.vndirect.domain.IfoForeignTradingViewId;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * A data access object (DAO) providing persistence and search support for
 * IfoForeignTradingView entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vndirect.IfoForeignTradingView
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoForeignTradingViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoForeignTradingViewDAO.class);

	// private final ResultSetProcessor ifoForeignTradingViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	//
	// IfoForeignTradingView obj = new IfoForeignTradingView();
	// IfoForeignTradingViewId objId = new IfoForeignTradingViewId();
	// objId.setFloorCode(rs.getString("FLOOR_CODE"));
	// objId.setTradingDate(rs.getDate("TRADING_DATE"));
	// objId.setSecCode(rs.getString("SEC_CODE"));
	// objId.setTotalRoom(new Long(rs.getLong("TOTAL_ROOM")));
	// objId.setCurrentRoom(new Long(rs.getLong("CURRENT_ROOM")));
	// objId.setBuyingVolume(new Long(rs.getLong("BUYING_VOLUME")));
	// objId.setBuyingValue(new Long(rs.getLong("BUYING_VALUE")));
	// objId.setSellingVolume(new Long(rs.getLong("SELLING_VOLUME")));
	// objId.setSellingValue(new Long(rs.getLong("SELLING_VALUE")));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };

	private final RowMapper ifoForeignTradingViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoForeignTradingView obj = new IfoForeignTradingView();
			IfoForeignTradingViewId objId = new IfoForeignTradingViewId();
			objId.setFloorCode(rs.getString("FLOOR_CODE"));
			objId.setTradingDate(rs.getDate("TRADING_DATE"));
			objId.setSecCode(rs.getString("SEC_CODE"));
			objId.setTotalRoom(new Long(rs.getLong("TOTAL_ROOM")));
			objId.setCurrentRoom(new Long(rs.getLong("CURRENT_ROOM")));
			objId.setBuyingVolume(new Long(rs.getLong("BUYING_VOLUME")));
			objId.setBuyingValue(new Long(rs.getLong("BUYING_VALUE")));
			objId.setSellingVolume(new Long(rs.getLong("SELLING_VOLUME")));
			objId.setSellingValue(new Long(rs.getLong("SELLING_VALUE")));

			obj.setId(objId);
			return obj;
		}
	};

	public SearchResult<IfoForeignTradingView> searchForeignTrading(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchForeignTrading(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchMarketStatisticsView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_FOREIGN_TRADING_VIEW ft "
			        + " where ft.FLOOR_CODE in (:FLOOR_CODE) and ft.TRADING_DATE = :TRADING_DATE "
			        + " ORDER BY UPPER(ft.SEC_CODE) ";

			String market = searchMarketStatisticsView.getMarket();
			market = (market == null ? "" : market);
			log.debug(LOCATION + ":: market = " + market);

			String[] listFloorCodes = null;
			if (market.equalsIgnoreCase(VNDirectWebUtilities.getHASTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHASTCFloorCode();
			} else if (market.equalsIgnoreCase(VNDirectWebUtilities.getHOSTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHOSTCFloorCode();
			}
			SearchResult<IfoForeignTradingView> result = null;
			if (listFloorCodes != null && listFloorCodes.length > 0) {
				listFloorCodes = (listFloorCodes == null ? new String[0] : listFloorCodes);
				StringBuffer strBuf = new StringBuffer();
				int size = listFloorCodes.length;
				for (int i = 0; i < size; i++) {
					strBuf.append(strBuf.length() == 0 ? "" : ",").append("'").append(listFloorCodes[i]).append("'");
				}

				log.debug(LOCATION + ":: strBuf = " + strBuf.toString());
				// SEARCH_SQL = Utility.replaceString(SEARCH_SQL, ":FLOOR_CODE",
				// strBuf.toString());
				SEARCH_SQL = SEARCH_SQL.replaceAll(":FLOOR_CODE", strBuf.toString());
				log.debug(LOCATION + ":: SEARCH_SQL = " + SEARCH_SQL);

				// List params = new ArrayList();
				// params.add(new Parameter(":TRADING_DATE",
				// searchIfoForeignTradingView.getTradingDate()));
				// log.debug(LOCATION + ":: TRADING_DATE" +
				// searchIfoForeignTradingView.getTradingDate());
				//
				// ReplaceableParams replaceableParams = new
				// ReplaceableParams();
				//
				// Connection conn = this.getCurrentConnection();
				// EasyStatement stat = new EasyStatement(SEARCH_SQL);
				//
				// result =
				// DAOHelper.searchByPaging(searchIfoForeignTradingView, conn,
				// stat
				// , (Parameter[])params.toArray(new Parameter[params.size()])
				// , ifoForeignTradingViewProcessor
				// , replaceableParams);

				Map paramMap = new HashMap();
				paramMap.put("TRADING_DATE", searchMarketStatisticsView.getTradingDate());
				log.debug(LOCATION + ":: TRADING_DATE" + searchMarketStatisticsView.getTradingDate());

				result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL, paramMap,
				        ifoForeignTradingViewRowMapper, pagingInfo);
			}
			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoForeignTradingView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult<IfoForeignTradingView> reportForeignTrading(SearchMarketStatisticsView searchMarketStatisticsView)
	        throws SystemException {
		final String LOCATION = "reportForeignTrading(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoForeignTradingView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_FOREIGN_TRADING_VIEW ft "
			        + " where ft.FLOOR_CODE in (:FLOOR_CODE) and ft.TRADING_DATE = :TRADING_DATE "
			        + " ORDER BY UPPER(ft.SEC_CODE) ";

			String market = searchMarketStatisticsView.getMarket();
			market = (market == null ? "" : market);
			log.debug(LOCATION + ":: market = " + market);

			String[] listFloorCodes = null;
			if (market.equalsIgnoreCase(VNDirectWebUtilities.getHASTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHASTCFloorCode();
			} else if (market.equalsIgnoreCase(VNDirectWebUtilities.getHOSTCExchange())) {
				listFloorCodes = VNDirectWebUtilities.getHOSTCFloorCode();
			}
			SearchResult<IfoForeignTradingView> result = null;
			if (listFloorCodes != null && listFloorCodes.length > 0) {
				listFloorCodes = (listFloorCodes == null ? new String[0] : listFloorCodes);
				StringBuffer strBuf = new StringBuffer();
				int size = listFloorCodes.length;
				for (int i = 0; i < size; i++) {
					strBuf.append(strBuf.length() == 0 ? "" : ",").append("'").append(listFloorCodes[i]).append("'");
				}

				log.debug(LOCATION + ":: strBuf = " + strBuf.toString());
				// SEARCH_SQL = Utility.replaceString(SEARCH_SQL, ":FLOOR_CODE",
				// strBuf.toString());
				SEARCH_SQL = SEARCH_SQL.replaceAll(":FLOOR_CODE", strBuf.toString());
				log.debug(LOCATION + ":: SEARCH_SQL = " + SEARCH_SQL);

				// List params = new ArrayList();
				// params.add(new Parameter(":TRADING_DATE",
				// searchIfoForeignTradingView.getTradingDate()));
				// log.debug(LOCATION + ":: TRADING_DATE" +
				// searchIfoForeignTradingView.getTradingDate());
				//
				// ReplaceableParams replaceableParams = new
				// ReplaceableParams();
				//
				// Connection conn = this.getCurrentConnection();
				// EasyStatement stat = new EasyStatement(SEARCH_SQL);
				//
				// result = DAOHelper.searchByPaging(null, conn, stat
				// , (Parameter[])params.toArray(new Parameter[params.size()])
				// , ifoForeignTradingViewProcessor
				// , replaceableParams);

				Map paramMap = new HashMap();
				paramMap.put("TRADING_DATE", searchMarketStatisticsView.getTradingDate());
				log.debug(LOCATION + ":: TRADING_DATE" + searchMarketStatisticsView.getTradingDate());

				result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap, ifoForeignTradingViewRowMapper);
			}

			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoForeignTradingView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchIfoForeignTradingView
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoForeignTradingView> searchForeignTrading4Symbol(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchForeignTrading4Symbol(searchIfoForeignTradingView:" + searchMarketStatisticsView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchMarketStatisticsView == null || searchMarketStatisticsView.getSymbol() == null
		        || searchMarketStatisticsView.getSymbol().trim().length() == 0) {
			throw new SystemException(LOCATION, "searchMarketStatisticsView is NULL...");
		}
		try {
			String SEARCH_SQL = "select * from IFO_FOREIGN_TRADING_VIEW where UPPER(SEC_CODE) = :SEC_CODE ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SEC_CODE", searchMarketStatisticsView.getSymbol().toUpperCase());

			if (searchMarketStatisticsView.getFromDate() != null) {
				SEARCH_SQL += " and TRADING_DATE >= :TRADING_DATE_FROM ";
				paramMap.put("TRADING_DATE_FROM", searchMarketStatisticsView.getFromDate());
			}

			if (searchMarketStatisticsView.getToDate() != null) {
				SEARCH_SQL += " and TRADING_DATE <= :TRADING_DATE_TO ";
				paramMap.put("TRADING_DATE_TO", searchMarketStatisticsView.getToDate());
			}

			SEARCH_SQL += " ORDER BY TRADING_DATE DESC";
			SearchResult<IfoForeignTradingView> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoForeignTradingViewRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result == null ? new SearchResult<IfoForeignTradingView>() : result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}