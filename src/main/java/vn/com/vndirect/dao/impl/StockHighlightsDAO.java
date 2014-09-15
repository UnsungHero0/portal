package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.NewsAttachments;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public class StockHighlightsDAO extends BaseDAOImpl implements IStockHighlightsDAO {

	private static final Logger logger = Logger.getLogger(StockHighlightsDAO.class);

	public SearchResult getReports(SearchIfoNews ifoNews, PagingInfo pagingInfo, boolean isOrderByHit, boolean isGetSymbol,
			Integer sysdateFrom) throws SystemException {
		final String location = "getReports(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";

		logger.debug(location + ":: BEGIN");

		if (ifoNews == null) {
			throw new SystemException(location, "ifoNews is NULL...");
		}

		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuilder selectQuery = new StringBuilder();
			StringBuilder joinQuery = new StringBuilder();

			StringBuilder whereQuery = new StringBuilder("WHERE A.STATUS=:STATUS ");
			whereQuery.append("AND A.NEWS_DATE >= sysdate - :SYSDATE_FROM ");
			paramMap.put("STATUS", ifoNews.getStatus());
			paramMap.put("SYSDATE_FROM", sysdateFrom == null ? 0 : sysdateFrom);

			selectQuery.append("SELECT DISTINCT A.NEWS_ID NEWS_ID, ");
			selectQuery.append("D.TYPE_CODE NEWS_TYPE, ");
			selectQuery.append("A.NEWS_HEADER NEWS_HEADER, ");
			selectQuery.append("A.NEWS_DATE NEWS_DATE, ");
			selectQuery.append("E.FILE_PATH FILE_PATH, ");
			selectQuery.append("A.HIT NEWS_HIT, ");

			if (isGetSymbol) {
				selectQuery.append("C.SYMBOL SYMBOL, ");

				joinQuery.append("LEFT JOIN IFO_COMPANY_NEWS_VIEW B ON A.NEWS_ID = B.NEWS_ID(+) ");
				joinQuery.append("LEFT JOIN IFO_COMPANY_NAME_VIEW C ON B.COMPANY_ID = C.COMPANY_ID ");

				if (!ArrayUtils.isEmpty(ifoNews.getListSymbols())) {
					whereQuery.append("AND C.SYMBOL IN ").append(createInSQLSyntax(ifoNews.getListSymbols()));
				}
			}

			selectQuery.append("A.LOCALE LOCALE ");
			selectQuery.append("FROM IFO_NEWS A ");

			joinQuery.append("LEFT JOIN IFO_NEWS_TYPE D ON A.NEWS_ID = D.NEWS_ID ");
			joinQuery.append("INNER JOIN IFO_ATTACHMENTS E ON A.NEWS_ID = E.NEWS_ID ");

			if (StringUtils.isNotEmpty(ifoNews.getLocale())) {
				whereQuery.append("AND A.LOCALE=:LOCALE ");
				paramMap.put("LOCALE", ifoNews.getLocale());
			}
			whereQuery.append("AND D.TYPE_CODE=:TYPE_CODE ");
			paramMap.put("TYPE_CODE", ifoNews.getNewsType());

			String query = selectQuery.toString() + joinQuery.toString() + whereQuery.toString();
			if (isOrderByHit) {
				query += "ORDER BY A.HIT DESC ";
			} else {
				query += "ORDER BY A.NEWS_DATE DESC ";
			}
			logger.debug(location + "getStockHighlightsReport SQL: " + query);

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), query, paramMap, reportsRowMapper, pagingInfo);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

	private final RowMapper reportsRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			// objNews.setNewsType(arg0.getString("NEWS_TYPE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			// objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setHit(new Long(arg0.getLong("NEWS_HIT")));
			objNews.setFilePath(arg0.getString("FILE_PATH"));

			try {
				objNews.setStrSymbol(arg0.getString("SYMBOL"));
			} catch (Exception e) {
			}

			return objNews;
		}
	};

	private final RowMapper relatedReportsRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setStrSymbol(arg0.getString("SYMBOL"));
			objNews.setIndustryGroupCode(arg0.getString("INDUSTRY_CODE"));

			return objNews;
		}
	};

	private final RowMapper outstandingSymbolsRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setStrSymbol(arg0.getString("SYMBOL"));
			objNews.setHit(arg0.getLong("NEWS_HIT"));

			return objNews;
		}
	};

	private String createInSQLSyntax(String[] list) {
		StringBuilder res = new StringBuilder(" (");

		if (!ArrayUtils.isEmpty(list)) {
			for (int i = 0; i < list.length; i++) {
				res.append("'").append(list[i]).append("'");
				if (i != list.length - 1) {
					res.append(",");
				}
			}
		}

		res.append(") ");

		return res.toString();
	}

	@Override
	public SearchResult getRelatedReports(SearchIfoNews ifoNews, PagingInfo pagingInfo, Integer sysdateFrom)
			throws SystemException {

		final String location = "getRelatedReports(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";

		logger.debug(location + ":: BEGIN");

		if (ifoNews == null) {
			throw new SystemException(location, "ifoNews is NULL...");
		}

		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuilder query = new StringBuilder();

			query.append("SELECT A.NEWS_ID NEWS_ID, ");
			query.append("A.NEWS_HEADER NEWS_HEADER, ");
			query.append("A.NEWS_DATE NEWS_DATE, ");
			query.append("E.FILE_PATH FILE_PATH, ");
			query.append("A.HIT NEWS_HIT ");
			query.append("FROM IFO_NEWS A, IFO_NEWS_TYPE D, IFO_ATTACHMENTS E ");
			query.append("WHERE A.NEWS_ID=E.NEWS_ID ");
			query.append("AND A.NEWS_ID=D.NEWS_ID ");
			query.append("AND D.TYPE_CODE=:TYPE_CODE ");

			// optional date
			query.append("AND A.NEWS_DATE >= sysdate - :SYSDATE_FROM ");

			query.append("AND A.NEWS_ID IN (SELECT B.NEWS_ID FROM ");
			query.append("IFO_COMPANY_NEWS_VIEW B ");
			query.append("LEFT JOIN IFO_COMPANY_NAME_VIEW C ON B.COMPANY_ID = C.COMPANY_ID ");
			query.append("LEFT JOIN IFO_COMPANY_INDUSTRY_VIEW F ON F.COMPANY_ID = B.COMPANY_ID ");
			query.append("WHERE ");
			query.append("C.COMPANY_ID IN (");
			query.append("SELECT B.COMPANY_ID FROM IFO_COMPANY_NAME_VIEW B ");
			query.append("JOIN IFO_COMPANY_NEWS_VIEW C ON B.COMPANY_ID = C.COMPANY_ID ");
			query.append("WHERE C.NEWS_ID=:NEWS_ID) ");
			query.append("OR F.INDUSTRY_CODE IN (");
			query.append("SELECT B.INDUSTRY_CODE FROM IFO_COMPANY_INDUSTRY_VIEW B ");
			query.append("JOIN IFO_COMPANY_NEWS_VIEW C ON B.COMPANY_ID = C.COMPANY_ID ");
			query.append("JOIN IFO_COMPANY_NAME_VIEW D ON D.COMPANY_ID = C.COMPANY_ID ");
			query.append("WHERE C.NEWS_ID=:NEWS_ID) ");
			query.append(")");

			paramMap.put("TYPE_CODE", ifoNews.getNewsType());
			paramMap.put("NEWS_ID", ifoNews.getNewsId());
			paramMap.put("SYSDATE_FROM", sysdateFrom == null ? 0 : sysdateFrom);

			logger.debug(location + "getStockHighlightsReport SQL: " + query.toString());

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap, reportsRowMapper,
					pagingInfo);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

	@Override
	@Deprecated
	public SearchResult getOutstandingSymbols(SearchIfoNews ifoNews, PagingInfo pagingInfo, Integer sysdateFrom)
			throws SystemException {

		final String location = "getOutstandingReports(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		logger.debug(location + ":: BEGIN");

		if (ifoNews == null) {
			throw new SystemException(location, "ifoNews is NULL...");
		}

		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuilder query = new StringBuilder();

			query.append("SELECT DISTINCT A.SYMBOL SYMBOL, SUM(C.HIT) NEWS_HIT ");
			query.append("FROM IFO_COMPANY_NAME_VIEW A, IFO_COMPANY_NEWS_VIEW B, IFO_NEWS C, IFO_NEWS_TYPE D ");
			query.append("WHERE C.NEWS_ID=B.NEWS_ID ");
			query.append("AND C.NEWS_ID=D.NEWS_ID ");
			query.append("AND A.COMPANY_ID=B.COMPANY_ID ");
			query.append("AND D.TYPE_CODE=:TYPE_CODE ");
			query.append("AND C.STATUS=:STATUS ");

			// optional
			// query.append("AND C.HIT > 0 ");
			query.append("AND C.NEWS_DATE >= sysdate - :SYSDATE_FROM ");

			query.append("GROUP BY A.SYMBOL ");
			query.append("ORDER BY sum(C.HIT) DESC");

			paramMap.put("STATUS", ifoNews.getStatus());
			paramMap.put("TYPE_CODE", ifoNews.getNewsType());
			paramMap.put("SYSDATE_FROM", sysdateFrom == null ? 0 : sysdateFrom);

			logger.debug(location + "getStockHighlightsReport SQL: " + query.toString());

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap,
					outstandingSymbolsRowMapper, pagingInfo);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

	public SearchResult getSymbolsListHaveReports(PagingInfo pagingInfo) throws SystemException {
		final String location = "getSymbolsListHaveReports(pagingInfo:" + pagingInfo + ")";

		logger.debug(location + ":: BEGIN");

		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuilder query = new StringBuilder();

			query.append("SELECT DISTINCT C.SYMBOL FROM IFO_NEWS A ");
			query.append("LEFT JOIN IFO_COMPANY_NEWS_VIEW B ON A.NEWS_ID = B.NEWS_ID(+) ");
			query.append("LEFT JOIN IFO_COMPANY_NAME_VIEW C ON B.COMPANY_ID = C.COMPANY_ID ");
			query.append("LEFT JOIN IFO_NEWS_TYPE D ON A.NEWS_ID = D.NEWS_ID ");

			query.append("WHERE D.TYPE_CODE=:TYPE_CODE ");
			paramMap.put("TYPE_CODE", Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_HIGHLIGHTS);
			query.append("AND A.LOCALE=:LOCALE ");
			paramMap.put("LOCALE", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()).toUpperCase());
			
			String[] freeReports = Constants.IServerConfig.STOCK_HIGHLIGHTS_FREE_REPORTS.split(",");
			StringBuilder notInCondition = new StringBuilder();
			for(int i=0;i<freeReports.length; i++){
				if(i==0){
					notInCondition.append("(");
				}
				notInCondition.append("'").append(freeReports[i]).append("'");
				if(i==freeReports.length-1){
					notInCondition.append(") ");
				} else {
					notInCondition.append(",");
				}
			}
			query.append("AND C.SYMBOL NOT IN ").append(notInCondition.toString());

			query.append("ORDER BY C.SYMBOL");

			logger.debug(location + "getSymbolsListHaveReports SQL: " + query);

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), query.toString(), paramMap, symbolsListHaveReports,
					pagingInfo);

			if (logger.isDebugEnabled())
				logger.debug(location + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

	private final RowMapper symbolsListHaveReports = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			return arg0.getString("SYMBOL") == null ? "" : arg0.getString("SYMBOL");
		}
	};

	@Override
	public SearchIfoNews getNews(Long newsId) throws SystemException {
		final String location = "getNews(newsId:" + newsId + ")";
		logger.debug(location + ":: BEGIN");

		SearchIfoNews result = new SearchIfoNews();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuilder query = new StringBuilder();

			query.append("SELECT A.NEWS_HEADER, E.FILE_PATH ");
			
			query.append("FROM IFO_NEWS A ");
			query.append("LEFT JOIN IFO_ATTACHMENTS E ON A.NEWS_ID = E.NEWS_ID ");
			
			query.append("WHERE A.NEWS_ID=:NEWS_ID ");
			query.append("AND A.STATUS=:STATUS ");

			paramMap.put("STATUS", Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			paramMap.put("NEWS_ID", newsId);

			logger.debug(location + "getNews SQL: " + query.toString());

			result = (SearchIfoNews) OracleDAOHelper.querySingle(this.getDataSource(), query.toString(), paramMap, newsWithFilePathRowMapper);

			return result;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}
	
	private final RowMapper newsWithFilePathRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setFilePath(arg0.getString("FILE_PATH"));

			return objNews;
		}
	};
}
