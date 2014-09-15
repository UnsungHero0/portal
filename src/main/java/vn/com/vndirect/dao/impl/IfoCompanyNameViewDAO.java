package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.extend.SearchForumStatus;
import vn.com.vndirect.domain.extend.SearchSymbol;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanyNameView.
 * 
 * @see vn.com.vndirect.domain.IfoCompanyNameView
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoCompanyNameViewDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoCompanyNameViewDAO.class);
	/**
	 * process all attributes of table
	 */
	private final RowMapper ifoCompanyNameRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyNameView obj = new IfoCompanyNameView();
			IfoCompanyNameViewId objId = new IfoCompanyNameViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setSymbol(rs.getString("SYMBOL"));
			objId.setCompanyName(rs.getString("COMPANY_NAME"));
			objId.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
			objId.setAbbName(rs.getString("ABB_NAME"));

			try {
				objId.setFirstTradingDate(rs.getDate("FIRST_TRADING_DATE"));
			} catch (Exception e) {
			}

			obj.setId(objId);
			try {
				obj.setSectorName(rs.getString("SECTOR_NAME"));
			} catch (Exception e) {
			}

			try {
				obj.setExchangeName(rs.getString("EXCHANGE_CODE"));
			} catch (Exception e) {
			}

			return obj;
		}
	};

	/**
	 * process all attributes of table
	 */
	private final RowMapper ifoCompanyNameViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyNameView obj = new IfoCompanyNameView();
			IfoCompanyNameViewId objId = new IfoCompanyNameViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setSymbol(rs.getString("SYMBOL"));
			objId.setCompanyName(rs.getString("COMPANY_NAME"));
			objId.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));

			try {
				objId.setFirstTradingDate(rs.getDate("FIRST_TRADING_DATE"));
			} catch (Exception e) {
				// System.err.println("+++ ifoCompanyNameViewProcessor():: " +
				// e.getMessage());
			}

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * process attributes of tables
	 */
	private final RowMapper searchForumStatusRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchForumStatus obj = new SearchForumStatus();
			IfoCompanyNameViewId objId = new IfoCompanyNameViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setSymbol(rs.getString("SYMBOL"));
			objId.setCompanyName(rs.getString("COMPANY_NAME"));
			objId.setCompanyFullName(rs.getString("COMPANY_FULL_NAME"));
			obj.setForumStatus(new Long(rs.getLong("FORUM_STATUS")));

			try {
				objId.setFirstTradingDate(rs.getDate("FIRST_TRADING_DATE"));
			} catch (Exception e) {
			}

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
	public SearchResult searchStockExchange(SearchSymbol searchObj, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchStockExchange(searchObj:" + searchObj + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "searchObj is NULL...");
		}
		try {
			final String SEARCH_SQL = "select distinct compname.*, stock.EXCHANGE_CODE, indus.SECTOR_NAME "
			        + " from IFO_COMPANY_NAME_VIEW compname, IFO_STOCK_EXCHANGE_VIEW stock, IFO_COMPANY_INDUSTRY_VIEW indus "
			        + " where compname.COMPANY_ID = indus.COMPANY_ID and compname.SYMBOL = stock.SYMBOL "
			        + " and (indus.SECTOR_CODE like :SECTOR_CODE and upper(indus.LOCALE) like :LOCALE) and (stock.EXCHANGE_CODE like :EXCHANGE_CODE) "
			        + " and (upper(compname.SYMBOL) like :SYMBOL) "
			        + " and (upper(compname.COMPANY_NAME) like :COMPANY_NAME or upper(compname.ABB_NAME) like :ABB_NAME or upper(compname.COMPANY_FULL_NAME) like :COMPANY_FULL_NAME)";
			// + " %EXCLUDE_SYMBOL% "
			final String ORDER_SQL = " ORDER BY UPPER(compname.SYMBOL), UPPER(compname.ABB_NAME), UPPER(compname.COMPANY_NAME) ";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// add COMPANY_NAME
			String str = searchObj.getCompanyName();
			str = "%" + (str == null ? "" : str.toUpperCase().trim()) + "%";

			paramMap.put("COMPANY_NAME", str);
			paramMap.put("ABB_NAME", str);
			paramMap.put("COMPANY_FULL_NAME", str);

			// add EXCHANGE_CODE
			str = searchObj.getFirstExchangeCode();
			str = (str == null || str.trim().length() == 0 ? "%" : str.toUpperCase().trim());
			paramMap.put("EXCHANGE_CODE", str);

			// add LOCALE & SECTOR_CODE
			str = searchObj.getSectorCode();
			str = (str == null || str.trim().length() == 0 ? "%" : str.toUpperCase().trim());
			paramMap.put("SECTOR_CODE", str);

			str = searchObj.getLocale();
			str = (str == null || str.trim().length() == 0 ? "%" : str.toUpperCase().trim());
			paramMap.put("LOCALE", str);

			str = searchObj.getSymbol();
			str = (str == null || str.trim().length() == 0 ? "%" : str.toUpperCase().trim()) + "%";

			paramMap.put("SYMBOL", str);

			//
			// ReplaceableParams replaceableParams = new ReplaceableParams();
			String excludeSql = "";
			if (searchObj.getListExcludeSymbol() != null) {
				StringBuffer strBuf = new StringBuffer();
				int size = searchObj.getListExcludeSymbol().length;
				for (int i = 0; i < size; i++) {
					if (searchObj.getListExcludeSymbol()[i] != null) {
						strBuf.append(strBuf.length() == 0 ? "" : ", ").append("'").append(searchObj.getListExcludeSymbol()[i])
						        .append("'");
					}
				}
				excludeSql = " AND compname.SYMBOL NOT IN (" + strBuf.toString() + ")";
			}

			StringBuffer sql = new StringBuffer();
			sql.append(SEARCH_SQL).append(excludeSql).append(ORDER_SQL);
			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sql.toString(), paramMap, ifoCompanyNameRowMapper, searchObj);
			SearchResult result = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap,
			        ifoCompanyNameRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyNameView> searchStockExchange4AutoSuggest(SearchSymbol searchObj, PagingInfo pagingInfo)
	        throws SystemException {
		final String LOCATION = "searchStockExchange4AutoSuggest(searchObj:" + searchObj + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "searchObj is NULL...");
		}
		try {
			final String SEARCH_SQL = "select distinct compname.* from IFO_COMPANY_NAME_VIEW compname "
			        + " WHERE upper(compname.SYMBOL) like :SYMBOL "
			        + " ORDER BY UPPER(compname.SYMBOL), UPPER(compname.ABB_NAME) ";
			// List params = new ArrayList();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// add COMPANY_NAME
			String str = searchObj.getCompanyName();
			str = (str == null ? "%" : str.toUpperCase().trim()) + "%";
			paramMap.put("SYMBOL", str);

			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoCompanyNameRowMapper, searchObj);
			SearchResult<IfoCompanyNameView> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoCompanyNameRowMapper, pagingInfo);
			log.debug(LOCATION + ":: END - result: " + result);
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoCompanyNameView findByCompanyId(IfoCompanyNameViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoCompanyNameViewId is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoCompanyNameView.* " + " FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView "
			        + " WHERE ifoCompanyNameView.COMPANY_ID = :COMPANY_ID  "
			        + " AND UPPER(ifoCompanyNameView.SYMBOL) LIKE :SYMBOL ";

			String str;
			// List params = new ArrayList();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			str = (id.getSymbol() == null ? "" : id.getSymbol().trim().toUpperCase());
			paramMap.put("SYMBOL", str);
			paramMap.put("COMPANY_ID", id.getCompanyId());

			IfoCompanyNameView obj = (IfoCompanyNameView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyNameViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return obj;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Only using before clarify above method
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoCompanyNameView findByJustCompanyId(IfoCompanyNameViewId id) throws SystemException {
		final String LOCATION = "findByJustCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoCompanyNameViewId is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoCompanyNameView.* " + " FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView "
			        + " WHERE ifoCompanyNameView.COMPANY_ID = :COMPANY_ID  ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());

			IfoCompanyNameView obj = (IfoCompanyNameView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyNameViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return obj;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoCompanyNameView findBySymbol(String symbol) throws SystemException {
		final String LOCATION = "findBySymbol(symbol:" + symbol + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (symbol == null) {
			throw new SystemException(LOCATION, "symbol is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoCompanyNameView.* " + " FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView "
			        + " WHERE UPPER(ifoCompanyNameView.SYMBOL) LIKE :SYMBOL ";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			symbol = (symbol == null ? "" : symbol.trim().toUpperCase());
			paramMap.put("SYMBOL", symbol);

			IfoCompanyNameView obj = (IfoCompanyNameView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyNameViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return obj;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param symbols
	 * @return
	 * @throws SystemException
	 */
	public Collection<IfoCompanyNameView> findBySymbols(String[] symbols) throws SystemException {
		final String LOCATION = "findBySymbols(symbols:" + symbols + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (symbols == null) {
			throw new SystemException(LOCATION, "symbol is NULL...");
		}
		try {
			final String SEARCH_SQL = "SELECT distinct ifoCompanyNameView.* " + " FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView "
			        + " WHERE UPPER(ifoCompanyNameView.SYMBOL) IN (:SYMBOLS) ";

			StringBuffer strBuf = new StringBuffer();
			int size = symbols.length;
			for (int i = 0; i < size; i++) {
				if (symbols[i] != null) {
					strBuf.append(strBuf.length() == 0 ? "" : ", ").append("'").append(symbols[i]).append("'");
				}
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("SYMBOLS", strBuf.toString());

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoCompanyNameViewRowMapper, null);
			SearchResult<IfoCompanyNameView> result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyNameViewRowMapper);

			log.debug(LOCATION + ":: END - size:" + result.size());
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoCompany
	 * @return
	 * @throws SystemException
	 */
	public SearchResult searchBySymbolStatus(IfoCompanyNameView ifoCompanyNameView, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchBySymbolStatus(ifoCompanyNameView:" + ifoCompanyNameView + "; pagingInfo:" + pagingInfo
		        + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyNameView == null) {
			throw new SystemException(LOCATION, "ifoCompanyNameViewId is NULL...");
		}
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuffer statement = new StringBuffer();
			statement.append("SELECT distinct ifoCompanyNameView.*, ifoCompany.FORUM_STATUS ");
			statement.append("FROM IFO_COMPANY_NAME_VIEW ifoCompanyNameView, IFO_COMPANY ifoCompany ");

			// add SYMBOL's value
			String str = "";
			if (ifoCompanyNameView.getId() != null) {
				str = ifoCompanyNameView.getId().getSymbol();
			}
			str = (str == null ? "" : str.toUpperCase().trim());
			str = str + "%";

			statement.append("WHERE UPPER(ifoCompanyNameView.SYMBOL) LIKE :SYMBOL ");
			paramMap.put("SYMBOL", str);
			// params.add(new Parameter(":SYMBOL", str));

			// add Forum Status's value
			if (ifoCompanyNameView instanceof SearchForumStatus) {
				SearchForumStatus searchForumStatus = (SearchForumStatus) ifoCompanyNameView;

				if (Constants.Forums.UNPUBLISH_STATUS.equals(searchForumStatus.getForumStatus())) {
					statement.append("AND (ifoCompany.FORUM_STATUS IS NULL ");
					statement.append("OR ifoCompany.FORUM_STATUS = :FORUM_STATUS) ");
					paramMap.put("FORUM_STATUS", searchForumStatus.getForumStatus());

				} else if (Constants.Forums.PUBLISH_STATUS.equals(searchForumStatus.getForumStatus())) {
					statement.append("AND ifoCompany.FORUM_STATUS = :FORUM_STATUS ");
					paramMap.put("FORUM_STATUS", searchForumStatus.getForumStatus());

				}
			}

			statement.append("AND ifoCompanyNameView.COMPANY_ID = ifoCompany.COMPANY_ID ");
			statement.append("ORDER BY ifoCompanyNameView.SYMBOL, ifoCompanyNameView.COMPANY_NAME ");

			log.debug(LOCATION + ":: " + statement.toString());

			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// statement.toString(), paramMap, searchForumStatusRowMapper,
			// ifoCompanyNameView);
			SearchResult result = OracleDAOHelper.queryWithPagging(this.getDataSource(), statement.toString(), paramMap,
			        searchForumStatusRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}