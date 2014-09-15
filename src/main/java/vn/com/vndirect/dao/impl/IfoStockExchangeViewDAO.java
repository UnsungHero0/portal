package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoStockExchangeView;
import vn.com.vndirect.domain.IfoStockExchangeViewId;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoStockExchangeView.
 * 
 * @see vn.com.vndirect.domain.IfoStockExchangeView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("unchecked")
public class IfoStockExchangeViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoStockExchangeViewDAO.class);

	// //////////////////////////////////Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	@SuppressWarnings("rawtypes")
	private final RowMapper ifoStockExchangeViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoStockExchangeView obj = new IfoStockExchangeView();
			IfoStockExchangeViewId objId = new IfoStockExchangeViewId();

			objId.setSymbol(rs.getString("SYMBOL"));
			objId.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			objId.setExchangeName(rs.getString("EXCHANGE_NAME"));

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws SystemException
	 */
	public List<IfoStockExchangeView> findByCompanyId(long companyId) throws SystemException {
		final String LOCATION = "findByCompanyId(companyId:" + companyId + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (companyId < 1) {
			throw new SystemException(LOCATION, "companyId is NULL...");
		}
		try {
			String SEARCH_SQL = "SELECT distinct ifoStockExchangeView.* "
			        + " FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView, IFO_STOCK_EXCHANGE_VIEW ifoStockExchangeView "
			        + " WHERE ifoCompanyNameView.SYMBOL = ifoStockExchangeView.SYMBOL  "
			        + " AND ifoCompanyNameView.COMPANY_ID = :COMPANY_ID";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", VNDirectWebUtilities.getLong(companyId));

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoStockExchangeViewRowMapper, null);
			SearchResult<IfoStockExchangeView> result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoStockExchangeViewRowMapper);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	// ///////////////////////////////////// Get for Caching ///////
	private final RowMapper ifoStockExchangeCachingInfoRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			StockExchangeIndexingBean stockExchangeCachingInfo = new StockExchangeIndexingBean();

			// +++ get IfoStockExchangeView information
			IfoStockExchangeViewId stockExchangeObjId = new IfoStockExchangeViewId();
			stockExchangeObjId.setSymbol(rs.getString("SYMBOL"));
			stockExchangeObjId.setExchangeCode(rs.getString("EXCHANGE_CODE"));
			stockExchangeObjId.setExchangeName(rs.getString("EXCHANGE_NAME"));

			stockExchangeCachingInfo.setIfoStockExchangeView(new IfoStockExchangeView(stockExchangeObjId));

			// +++ get IfoCompanyNameView information
			long compId = rs.getLong("COMPANY_ID");
			if (compId != 0) {
				// ++++ get sector & industry codes info
				stockExchangeCachingInfo.setSectorGroupCode(rs.getString("SECTOR_GROUP_CODE"));
				stockExchangeCachingInfo.setSectorCode(rs.getString("SECTOR_CODE"));
				stockExchangeCachingInfo.setIndustryGroupCode(rs.getString("INDUSTRY_GROUP_CODE"));
				stockExchangeCachingInfo.setIndustryCode(rs.getString("INDUSTRY_CODE"));

				// ++++ get IfoCompanyNameView info
				IfoCompanyNameViewId compNameObjId = new IfoCompanyNameViewId();
				compNameObjId.setCompanyId(compId);
				compNameObjId.setSymbol(stockExchangeObjId.getSymbol());
				compNameObjId.setCompanyName(rs.getString("COMPANY_NAME"));
				compNameObjId.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
				compNameObjId.setAbbName(rs.getString("ABB_NAME"));
				compNameObjId.setFirstTradingDate(rs.getDate("FIRST_TRADING_DATE"));

				stockExchangeCachingInfo.setIfoCompanyNameView(new IfoCompanyNameView(compNameObjId));
				stockExchangeCachingInfo.getIfoCompanyNameView().setExchangeName(stockExchangeObjId.getExchangeName());
				stockExchangeCachingInfo.getIfoCompanyNameView().setSectorName(stockExchangeCachingInfo.getSectorCode());
			}
			return stockExchangeCachingInfo;
		}
	};

	private static final String SQL_GET_ALL_STOCK_EXCHANGE = "SELECT DISTINCT s.EXCHANGE_CODE, s.SYMBOL, s.EXCHANGE_NAME"
	        + ", com.COMPANY_ID, com.COMPANY_NAME, com.COMPANY_FULL_NAME, com.ABB_NAME, com.FIRST_TRADING_DATE"
	        + ", i.INDUSTRY_CODE, i.INDUSTRY_GROUP_CODE, i.SECTOR_CODE, i.SECTOR_GROUP_CODE"
	        + " FROM (IFO_STOCK_EXCHANGE_VIEW s LEFT OUTER JOIN IFO_COMPANY_NAME_VIEW com  ON s.SYMBOL = com.SYMBOL)"
	        + " LEFT OUTER JOIN IFO_COMPANY_INDUSTRY_VIEW i ON com.COMPANY_ID=i.COMPANY_ID" + " ORDER BY EXCHANGE_CODE";

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<StockExchangeIndexingBean> getAllStockExchange() throws SystemException {
		final String LOCATION = "getAllStockExchange()";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult result = OracleDAOHelper.query(this.getDataSource(), SQL_GET_ALL_STOCK_EXCHANGE, null,
			        ifoStockExchangeCachingInfoRowMapper);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (SystemException syse) {
			throw new SystemException(LOCATION, syse);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}