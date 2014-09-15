package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.dao.IStockPickDAO;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

public class StockPickDAO extends JdbcDaoSupport implements IStockPickDAO {

	private static final Logger _LOGGER = Logger.getLogger(StockPickDAO.class);

	@Override
	public SearchResult<SearchIfoNews> getAllNewsByDate(Date date, PagingInfo pagingInfo) throws SystemException {
		final String location = "getAllNewsByDate(date:" + date + ")";
		_LOGGER.debug(location + ":: BEGIN");

		StringBuilder query = new StringBuilder();
		query.append(" SELECT IFON.NEWS_HEADER, IFON.NEWS_ID, IFON.NEWS_ABSTRACT, T.TYPE_CODE, IFON.NEWS_DATE, IFON.NEWS_SOURCE, S.SEC_CODE SYMBOL, ");
		query.append(" IFON.LOCALE, IFON.STATUS, IFON.HIT, IFON.NEWS_CONTENT ");
		query.append(" FROM IFO_NEWS IFON ");
		query.append(" INNER JOIN IFO_NEWS_TYPE T ON IFON.NEWS_ID = T.NEWS_ID ");
		query.append(" INNER JOIN IFO_COMPANY_NEWS_VIEW CN ON IFON.NEWS_ID = CN.NEWS_ID ");
		query.append(" INNER JOIN IFO_SEC_CODE S ON CN.COMPANY_ID = S.COMPANY_ID ");
		query.append(" WHERE T.TYPE_CODE = :TYPE_CODE ");
		// query.append(" AND IFON.STATUS = :STATUS");
		query.append(" AND S.SEC_TYPE IN ('STOCK') ");
		query.append(" AND TRUNC(IFON.NEWS_DATE) = :DATE");
		query.append(" AND S.EXCHANGE_CODE IN ('HOSTC','HNX','UPCOM') ");
		query.append(" ORDER BY S.SEC_CODE ");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TYPE_CODE", Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_PICK);
		// paramMap.put("STATUS",
		// Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		paramMap.put("DATE", DateUtils.dateToString(date, "dd-MMM-yy"));

		SearchResult<SearchIfoNews> result = null;
		try {
			result = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap,
					objNewsIfoWithContentRowMapper, pagingInfo);
		} catch (Exception e) {
			_LOGGER.error(location, e);
			throw new SystemException(location, e);
		}

		_LOGGER.debug(location + ":: END");

		return result;
	}

	private final RowMapper objNewsIfoWithContentRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setHit(new Long(arg0.getLong("HIT")));
			objNews.setStrSymbol(arg0.getString("SYMBOL"));

			try {
				objNews.setNewsContent(OracleDAOHelper.getClobColumn(arg0, "NEWS_CONTENT"));
			} catch (Exception e) {
			}

			return objNews;
		}
	};

	@Override
	public SearchResult<Date> getStockPickReportDates(PagingInfo pagingInfo, Date date, boolean isNewer) throws SystemException {
		final String location = "getStockPickReportDates(date: " + date + ", isNewer: " + isNewer + ")";
		_LOGGER.debug(location + ":: BEGIN");

		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT TO_DATE(IFON.NEWS_DATE,'DD-MON-YYYY') NEWS_DATE ");
		query.append(" FROM IFO_NEWS IFON ");
		query.append(" INNER JOIN IFO_NEWS_TYPE T ON IFON.NEWS_ID = T.NEWS_ID ");
		query.append(" WHERE T.TYPE_CODE = :TYPE_CODE ");
		// query.append(" AND IFON.STATUS = :STATUS");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TYPE_CODE", Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_PICK);
		// paramMap.put("STATUS",
		// Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);

		if (date != null) {
			query.append(" AND TO_DATE(TRUNC(IFON.NEWS_DATE), 'dd-mon-yyyy') ");
			query.append(isNewer ? ">" : "<");
			query.append(" :DATE ");
			paramMap.put("DATE", date);
		}

		query.append(" ORDER BY NEWS_DATE DESC ");

		SearchResult<Date> result = null;
		try {
			result = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap, objDateRowMapper,
					pagingInfo);
		} catch (Exception e) {
			_LOGGER.error(location, e);
			throw new SystemException(location, e);
		}

		_LOGGER.debug(location + ":: END");

		return result;
	}

	private final RowMapper objDateRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {

			Date date = arg0.getTimestamp("NEWS_DATE", Calendar.getInstance());

			return date;
		}
	};

	@Override
	public SearchIfoNews getNewsById(Long newsId) throws SystemException {
		final String location = "getNewsById(id:" + newsId + ")";
		_LOGGER.debug(location + ":: BEGIN");

		StringBuilder query = new StringBuilder();
		query.append(" SELECT IFON.NEWS_HEADER, IFON.NEWS_ID, IFON.NEWS_ABSTRACT, T.TYPE_CODE, IFON.NEWS_DATE, IFON.NEWS_SOURCE, S.SEC_CODE SYMBOL, ");
		query.append(" IFON.LOCALE, IFON.STATUS, IFON.HIT, IFON.NEWS_CONTENT ");
		query.append(" FROM IFO_NEWS IFON ");
		query.append(" INNER JOIN IFO_NEWS_TYPE T ON IFON.NEWS_ID = T.NEWS_ID ");
		query.append(" INNER JOIN IFO_COMPANY_NEWS_VIEW CN ON IFON.NEWS_ID = CN.NEWS_ID ");
		query.append(" INNER JOIN IFO_SEC_CODE S ON CN.COMPANY_ID = S.COMPANY_ID ");
		query.append(" WHERE T.TYPE_CODE = :TYPE_CODE ");
		query.append(" AND IFON.NEWS_ID = :NEWS_ID");
		// query.append(" AND IFON.STATUS = :STATUS");
		query.append(" AND S.EXCHANGE_CODE IN ('HOSTC','HNX','UPCOM') ");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TYPE_CODE", Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_PICK);
		// paramMap.put("STATUS",
		// Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		paramMap.put("NEWS_ID", newsId);

		SearchIfoNews result = null;
		try {
			result = (SearchIfoNews) OracleDAOHelper.querySingle(this.getDataSource(), query.toString(), paramMap,
					objNewsIfoWithContentRowMapper);
		} catch (Exception e) {
			_LOGGER.error(location, e);
			throw new SystemException(location, e);
		}

		_LOGGER.debug(location + ":: END");

		return result;
	}

	@Override
	public SearchResult<SearchIfoNews> getCommonNewsByDate(Date date, PagingInfo pagingInfo) throws SystemException {
		final String location = "getCommonNewsByDate(date:" + date + ")";
		_LOGGER.debug(location + ":: BEGIN");

		StringBuilder query = new StringBuilder();
		query.append(" SELECT IFON.NEWS_HEADER, IFON.NEWS_ID, IFON.NEWS_ABSTRACT, T.TYPE_CODE, IFON.NEWS_DATE, IFON.NEWS_SOURCE, '' SYMBOL, ");
		query.append(" IFON.LOCALE, IFON.STATUS, IFON.HIT, IFON.NEWS_CONTENT ");
		query.append(" FROM IFO_NEWS IFON ");
		query.append(" INNER JOIN IFO_NEWS_TYPE T ON IFON.NEWS_ID = T.NEWS_ID ");
		query.append(" WHERE T.TYPE_CODE = :TYPE_CODE ");
		// query.append(" AND IFON.STATUS = :STATUS");
		query.append(" AND TRUNC(IFON.NEWS_DATE) = :DATE");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TYPE_CODE", Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_PICK);
		// paramMap.put("STATUS",
		// Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		paramMap.put("DATE", DateUtils.dateToString(date, "dd-MMM-yy"));

		SearchResult<SearchIfoNews> result = null;
		try {
			result = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap,
					objNewsIfoWithContentRowMapper, pagingInfo);
		} catch (Exception e) {
			_LOGGER.error(location, e);
			throw new SystemException(location, e);
		}

		_LOGGER.debug(location + ":: END");

		return result;
	}

	@Override
	public String getDemoStockPickNewsId() throws SystemException {
		final String location = "getDemoStockPickNewsId()";
		_LOGGER.debug(location + ":: BEGIN");

		StringBuilder query = new StringBuilder();
		query.append(" SELECT ITEM_CODE FROM IFO_DATA_REF WHERE GROUP_CODE=:GROUP_CODE ");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GROUP_CODE", "DEMO_STOCK_PICK_ID");

		String result = StringUtils.EMPTY;
		try {
			result = (String) OracleDAOHelper.querySingle(this.getDataSource(), query.toString(), paramMap, new RowMapper() {
				public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
					return arg0.getString("ITEM_CODE");
				}
			});
		} catch (Exception e) {
			_LOGGER.error(location, e);
			throw new SystemException(location, e);
		}

		_LOGGER.debug(location + ":: END");

		return result;
	}
}
