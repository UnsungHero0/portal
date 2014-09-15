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
import org.springframework.jdbc.core.RowMapper;

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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoNewsDAO extends BaseDAOImpl {
	
	protected static final String MAC_WORLD_NEWSTYPE = "MacWorld";

	protected static final String MAC_VN_NEWSTYPE = "MacVN";
	
	protected static final String EVENT_NEWSTYPE = "Event";
	
	protected static final String DISCLOSURE_NEWSTYPE = "Disclousure";
	
	private static final Log log = LogFactory.getLog(IfoNewsDAO.class);

	public String keyWord = "";

	// ++ Using SpringDAO ++ //
	// private final RowMapper objNewsIdRowMapper = new RowMapper() {
	// public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
	// Long newsId = new Long(arg0.getLong("NEWS_ID"));
	// return newsId;
	// }
	// };

	private final RowMapper objNewsIfoRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsRank(new Long(arg0.getLong("NEWS_RANK")));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setResource(arg0.getString("NEWS_RESOURCE"));

			try {
				objNews.setIsHotNews(arg0.getString("IS_HOT_NEWS"));
				objNews.setDescription(arg0.getString("DESCRIPTION"));
			} catch (Exception e) {
			}
			objNews.setIsFlvNews(arg0.getString("IS_FLV_NEWS"));
			objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setHit(new Long(arg0.getLong("HIT")));
			return objNews;
		}
	};

	private final RowMapper objStockNewsIfoRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsRank(new Long(arg0.getLong("NEWS_RANK")));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			try {
				objNews.setIsHotNews(arg0.getString("IS_HOT_NEWS"));
				objNews.setDescription(arg0.getString("DESCRIPTION"));
			} catch (Exception e) {
			}
			objNews.setIsFlvNews(arg0.getString("IS_FLV_NEWS"));
			objNews.setLocale(arg0.getString("LOCALE"));
			return objNews;
		}
	};

	private final RowMapper objSearchNewsIfoRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER").replaceAll("(?i)" + keyWord,
					"<span style='font-weight:bold; background-color: yellow'>" + keyWord + "</span>"));

			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			return objNews;
		}
	};

	private final RowMapper objNewsIfoWithContentRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();

			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setNewsHeader(arg0.getString("news_header"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setNewsRank(new Long(arg0.getLong("NEWS_RANK")));
			objNews.setIsFlvNews(arg0.getString("IS_FLV_NEWS"));
			objNews.setHit(new Long(arg0.getLong("HIT")));
			try {
				objNews.setIsHotNews(arg0.getString("IS_HOT_NEWS"));
			} catch (Exception e) {
			}

			try {
				objNews.setNewsContent(OracleDAOHelper.getClobColumn(arg0, "NEWS_CONTENT"));
			} catch (Exception e) {
			}

			// NewsAttachments att = new NewsAttachments();
			// att.setOriginalLink(arg0.getString("FILE_PATH")); //ORIGINAL_LINK
			// att.setFileName(arg0.getString("FILE_NAME"));
			// att.setUriLink(""); // URI_LINK
			// att.setDescription(""); //description
			//
			// objNews.setAttachmentNews(Arrays.asList(new
			// NewsAttachments[]{att}));

			return objNews;
		}
	};

	private final RowMapper objMarketNews = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			// NewsAttachments att = new NewsAttachments();
			// att.setOriginalLink(arg0.getString("FILE_PATH")); //ORIGINAL_LINK
			// att.setFileName(arg0.getString("FILE_NAME"));
			// att.setUriLink(""); // URI_LINK
			// att.setDescription(""); //description

			// objNews.setAttachmentNews(Arrays.asList(new
			// NewsAttachments[]{att}));
			return objNews;
		}
	};

	// -- Using SpringDAO -- //
	public IfoNews findById(Long newsId, String newsType) throws SystemException {
		final String LOCATION = "findById(id:" + newsId + ", newsType:" + newsType + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		IfoNews result = null;
		if (newsId != null && newsId.longValue() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuilder SQL = new StringBuilder();

			SQL.append("SELECT ifon.news_header, ifon.news_id, ifon.news_abstract, t.type_code, ifon.news_date, ifon.news_source, ");
			SQL.append("ifon.locale, ifon.status, ifon.news_rank, ifon.is_flv_news, ifon.is_hot_news, ifon.hit, ifon.news_content ");
			SQL.append("FROM ifo_news ifon ");
			SQL.append("left join ifo_news_type t on ifon.news_id = t.news_id ");
			SQL.append("where ifon.news_id = :NEWS_ID ");
			if (StringUtils.isNotBlank(newsType)) {
				SQL.append("and t.type_code = :TYPE_CODE ");
				paramMap.put("TYPE_CODE", newsType);
			}

			paramMap.put("NEWS_ID", newsId);
			try {
				result = (IfoNews) OracleDAOHelper.querySingle(this.getDataSource(), SQL.toString(), paramMap,
						objNewsIfoWithContentRowMapper);
				log.debug(LOCATION + "findById_sql::" + SQL.toString());
			} catch (Exception e) {
				log.error(LOCATION, e);
				throw new SystemException(LOCATION, e);
			}
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");
		return result;
	}

	/**
	 * 
	 * @param ifoCompany
	 * @return
	 * @throws SystemException
	 */
	// public SearchResult searchIfoNews(IfoNews ifoNews, PagingInfo pagingInfo)
	// throws SystemException {
	// final String LOCATION = "searchIfoNews(ifoNews:" + ifoNews +
	// "; pagingInfo:" + pagingInfo + ")";
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: BEGIN");
	//
	// if (ifoNews == null) {
	// throw new SystemException(LOCATION, "ifoNews is NULL...");
	// }
	//
	// StringBuffer sql = new
	// StringBuffer(" SELECT * FROM IFO_NEWS ifon, IFO_ACCESS_TYPE ");
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	//
	// StringBuffer WHERE_SQL = new
	// StringBuffer(" WHERE ifon.ACCESS_TYPE = ifoat.ACCESS_TYPE ");
	// try {
	// String str;
	//
	// // add News's Header
	// str = (ifoNews.getNewsHeader() == null ? null :
	// ifoNews.getNewsHeader().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.NEWS_HEADER like :NEWS_HEADER");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("NEWS_HEADER", str);
	// }
	//
	// // add News's Abstract value
	// str = (ifoNews.getNewsAbstract() == null ? null :
	// ifoNews.getNewsAbstract().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.NEWS_ABSTRACT like :NEWS_ABSTRACT ");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("NEWS_ABSTRACT", str);
	// }
	//
	// // add News's Status value
	// str = (ifoNews.getStatus() == null ? null : ifoNews.getStatus().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.STATUS like :STATUS ");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("STATUS", str);
	// }
	//
	// // add News's Date
	// SearchIfoNews searchObj = null;
	// if (ifoNews instanceof SearchIfoNews) {
	// searchObj = (SearchIfoNews) ifoNews;
	// if (log.isDebugEnabled())
	// log.debug("searchObj.getFromNewsDate():" + searchObj.getFromNewsDate() +
	// ", searchObj.getToNewsDate():" + searchObj.getToNewsDate());
	// if (searchObj.getFromNewsDate() != null && searchObj.getToNewsDate() !=
	// null) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" :FROM_NEWS_DATE <= ifon.NEWS_DATE AND ifon.NEWS_DATE <= :TO_NEWS_DATE ");
	// paramMap.put("FROM_NEWS_DATE", searchObj.getFromNewsDate());
	// paramMap.put("TO_NEWS_DATE", searchObj.getToNewsDate());
	//
	// } else if (searchObj.getFromNewsDate() == null &&
	// searchObj.getToNewsDate() != null) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.NEWS_DATE <= :TO_NEWS_DATE ");
	// paramMap.put("TO_NEWS_DATE", searchObj.getToNewsDate());
	//
	// } else if (searchObj.getFromNewsDate() != null &&
	// searchObj.getToNewsDate() != null) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" :FROM_NEWS_DATE <= ifon.NEWS_DATE ");
	// paramMap.put("FROM_NEWS_DATE", searchObj.getFromNewsDate());
	//
	// }
	// }
	//
	// // Symbol:
	// // Created By :
	// str = (ifoNews.getCreatedBy() == null ? null :
	// ifoNews.getCreatedBy().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.CREATED_BY like :CREATED_BY ");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("CREATED_BY", str);
	// }
	//
	// // Types:
	// str = (ifoNews.getNewsType() == null ? null :
	// ifoNews.getNewsType().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.NEWS_TYPE like :NEWS_TYPE ");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("NEWS_TYPE", str);
	// }
	//
	// // Hot news:
	// str = (ifoNews.getIsHotNews() == null ? null :
	// ifoNews.getIsHotNews().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.IS_HOT_NEWS = :IS_HOT_NEWS ");
	// paramMap.put("IS_HOT_NEWS", str);
	// }
	//
	// // Access Level :
	// if (ifoNews.getIfoAccessType() != null &&
	// ifoNews.getIfoAccessType().getAccessType() != null &&
	// ifoNews.getIfoAccessType().getAccessType().longValue() > 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifoat.ACCESS_TYPE = :ACCESS_TYPE ");
	// paramMap.put("ACCESS_TYPE", ifoNews.getIfoAccessType().getAccessType());
	// }
	//
	// // Types:
	// str = (ifoNews.getLocale() == null ? null : ifoNews.getLocale().trim());
	// if (str != null && str.length() != 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.LOCALE like :LOCALE ");
	// // add wildcard
	// str = "%" + str + "%";
	// paramMap.put("LOCALE", str);
	// }
	//
	// // News Id
	// if (VNDirectWebUtilities.getLongValue(ifoNews.getNewsId()) > 0) {
	// WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " :
	// " ").append(" ifon.NEWS_ID = :NEWS_ID ");
	// paramMap.put("NEWS_ID", ifoNews.getNewsId());
	// }
	//
	// sql.append(WHERE_SQL);
	//
	// // add order by
	// if (searchObj != null && searchObj.isOrderByDate()) {
	// sql.append(" ORDER BY NEWS_DATE DESC ");
	// } else {
	// sql.append(" ORDER BY NEWS_HEADER ASC ");
	// }
	//
	// SearchResult result =
	// OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(),
	// paramMap, objNewsIfoRowMapper, pagingInfo);
	//
	// log.debug(LOCATION + "searchIfoNews_sql::" + sql.toString());
	//
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: END");
	// return result;
	// } catch (Exception e) {
	// log.error(LOCATION, e);
	// throw new SystemException(LOCATION, e);
	// }
	// }

	public SearchResult searchNews(IfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchNews(ifoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		keyWord = ifoNews.getNewsHeader();

		StringBuffer sql = new StringBuffer(" SELECT  ifon.NEWS_HEADER,	ifon.NEWS_ABSTRACT, ifon.NEWS_ID FROM IFO_NEWS ifon ");
		Map<String, Object> paramMap = new HashMap<String, Object>();

		StringBuffer WHERE_SQL = new StringBuffer(" WHERE 1= 1 ");
		try {
			String str;

			// add News's Header
			str = (ifoNews.getNewsHeader() == null ? null : ifoNews.getNewsHeader().trim());
			if (str != null && str.length() != 0) {
				WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " : " ").append(" upper(ifon.NEWS_HEADER) like :NEWS_HEADER");
				// add wildcard
				str = "%" + str.toUpperCase() + "%";
				paramMap.put("NEWS_HEADER", str);
			}

			// add News's Abstract value
			str = (ifoNews.getNewsAbstract() == null ? null : ifoNews.getNewsAbstract().trim());

			if (str != null && str.length() != 0) {
				WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " : " ")
						.append(" upper(ifon.NEWS_ABSTRACT) like :NEWS_ABSTRACT ");
				// add wildcard

				str = "%" + str.toUpperCase() + "%";
				paramMap.put("NEWS_ABSTRACT", str);
			}

			// Types:
			str = (ifoNews.getLocale() == null ? null : ifoNews.getLocale().trim());
			if (str != null && str.length() != 0) {
				WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " : " ").append(" ifon.LOCALE = :LOCALE ");
				paramMap.put("LOCALE", str);
			}

			// News Date

			Date newsDate = ifoNews.getNewsDate();
			if (newsDate != null) {
				WHERE_SQL.append(WHERE_SQL.length() > 0 ? " AND " : " ").append(" ifon.NEWS_DATE < :NEWS_DATE ");
				paramMap.put("NEWS_DATE", str);

				sql.append(WHERE_SQL);
				// add order by
				sql.append(" ORDER BY ifon.NEWS_DATE ASC ");

			} else {
				sql.append(WHERE_SQL);
				// add order by
				sql.append(" ORDER BY ifon.NEWS_HEADER ASC ");
			}

			SearchResult result = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap,
					objSearchNewsIfoRowMapper, pagingInfo);
			log.debug(LOCATION + "searchNews_sql::" + sql.toString());
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult getOtherRelateNews(long relateNewsId, String newsType, String status, String locale, PagingInfo pagingInfo, String symbol)
			throws SystemException {
		final String LOCATION = "getOtherRelateNews(relateNewsId:" + relateNewsId + ", newsType:" + newsType + ",status" + status
				+ ", locale:" + locale + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		
		boolean isOwnerCompanyNews = StringUtils.isNotEmpty(symbol);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(
				" SELECT  ifon.NEWS_HEADER, ifon.NEWS_DATE, ifon.IS_HOT_NEWS, ifon.NEWS_ID, ifot.TYPE_CODE ");
		sql.append("FROM IFO_NEWS ifon ");
		
		if(isOwnerCompanyNews){
			sql.append("left join ifo_company_news_view cn on ifon.news_id = cn.news_id(+) ");
			sql.append("left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id ");
		}
		
		sql.append("INNER JOIN ifo_news_type ifot on ifot.news_id = ifon.news_id ");
		sql.append("WHERE ifon.NEWS_DATE <= (SELECT NEWS_DATE FROM IFO_NEWS WHERE NEWS_ID=:NEWS_ID) ");
		
		if(isOwnerCompanyNews){
			sql.append("AND cv.symbol=:SYMBOL ");
			paramMap.put("SYMBOL", symbol);
		}
		
		sql.append("AND ifot.type_code = :NEWS_TYPE AND ifon.status=:STATUS AND ifon.LOCALE = :LOCALE AND ifon.NEWS_ID < :CURR_NEWS_ID");
		paramMap.put("NEWS_ID", relateNewsId);
		paramMap.put("NEWS_TYPE", newsType);
		paramMap.put("STATUS", status);
		
		if (StringUtils.isBlank(locale)) {
			locale = "VN";
		} else {
			locale = locale.toUpperCase();
		}
		paramMap.put("LOCALE", locale);
		paramMap.put("CURR_NEWS_ID", relateNewsId);

		sql.append(" ORDER BY ifon.NEWS_DATE DESC NULLS LAST, ifon.NEWS_ID DESC ");

		SearchResult result = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap, new RowMapper() {

			public Object mapRow(ResultSet rs, int index) throws SQLException {
				SearchIfoNews objNews = new SearchIfoNews();

				objNews.setNewsHeader(rs.getString("NEWS_HEADER"));
				objNews.setNewsId(rs.getLong("NEWS_ID"));
				objNews.setNewsDate(rs.getDate("NEWS_DATE"));
				objNews.setIsHotNews(rs.getString("IS_HOT_NEWS"));
				objNews.setNewsType(rs.getString("TYPE_CODE"));
				return objNews;
			}
		}, pagingInfo);
		log.debug(LOCATION + "sql::" + sql.toString());

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");

		return result;

	}

	// public SearchResult getOtherRelateInDayNews(String newsType, String
	// locale, PagingInfo pagingInfo) throws SystemException {
	// final String LOCATION = "getOtherRelateInDayNews(newsType:" + newsType
	// +")";
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: BEGIN");
	//
	// StringBuffer sql = new
	// StringBuffer(" SELECT  ifon.NEWS_HEADER, ifon.NEWS_DATE, ifon.IS_HOT_NEWS, ifon.NEWS_ID FROM IFO_NEWS ifon ");
	// sql.append("INNER JOIN ifo_news_type ifot on ifot.news_id = ifon.news_id "
	// );
	// sql.append(" WHERE TO_CHAR(ifon.NEWS_DATE, 'YYYY/MM/DD') = TO_CHAR(current_date, 'YYYY/MM/DD') AND ifot.type_code = :NEWS_TYPE AND ifon.LOCALE=:LOCALE"
	// );
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	//
	// paramMap.put("NEWS_TYPE", newsType);
	// paramMap.put("LOCALE", locale);
	//
	// sql.append(" ORDER BY ifon.NEWS_DATE DESC ");
	//
	// SearchResult result =
	// OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(),
	// paramMap, new RowMapper() {
	//
	// @Override
	// public Object mapRow(ResultSet rs, int index) throws SQLException {
	// SearchIfoNews objNews = new SearchIfoNews();
	//
	// objNews.setNewsHeader(rs.getString("NEWS_HEADER"));
	// objNews.setNewsId(rs.getLong("NEWS_ID"));
	// objNews.setNewsDate(rs.getDate("NEWS_DATE"));
	// objNews.setIsHotNews(rs.getString("IS_HOT_NEWS"));
	//
	// return objNews;
	// }
	// }, pagingInfo);
	// log.debug(LOCATION + "sql::" + sql.toString());
	//
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: END");
	//
	// return result;
	// }

	public SearchResult getOtherNewerRelateNews(long relateNewsId, String newsType, String status, String locale,
			PagingInfo pagingInfo, String symbol) throws SystemException {
		final String LOCATION = "getOtherNewerRelateNews(relateNewsId:" + relateNewsId + ", newsType:" + newsType + ",status"
				+ status + ", locale:" + locale + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		boolean isOwnerCompanyNews = StringUtils.isNotEmpty(symbol);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(
				" SELECT  ifon.NEWS_HEADER, ifon.NEWS_DATE, ifon.IS_HOT_NEWS, ifon.NEWS_ID, ifot.TYPE_CODE ");

		sql.append("FROM IFO_NEWS ifon ");
		if(isOwnerCompanyNews){
			sql.append("left join ifo_company_news_view cn on ifon.news_id = cn.news_id(+) ");
			sql.append("left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id ");
		}
		sql.append("INNER JOIN ifo_news_type ifot on ifot.news_id = ifon.news_id ");
		sql.append("WHERE ifon.NEWS_DATE >= (SELECT NEWS_DATE FROM IFO_NEWS WHERE NEWS_ID=:NEWS_ID) ");
		if(isOwnerCompanyNews){
			sql.append("AND cv.symbol=:SYMBOL ");
			paramMap.put("SYMBOL", symbol);
		}
		sql.append("AND ifot.type_code = :NEWS_TYPE AND ifon.status=:STATUS AND ifon.LOCALE = :LOCALE AND ifon.NEWS_ID > :CURR_NEWS_ID");

		paramMap.put("NEWS_ID", relateNewsId);
		paramMap.put("NEWS_TYPE", newsType);
		paramMap.put("STATUS", status);
		if (StringUtils.isBlank(locale)) {
			locale = "VN";
		} else {
			locale = locale.toUpperCase();
		}
		paramMap.put("LOCALE", locale);
		paramMap.put("CURR_NEWS_ID", relateNewsId);

		sql.append(" ORDER BY ifon.NEWS_DATE ASC, ifon.NEWS_ID ASC ");

		SearchResult result = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap, new RowMapper() {
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				SearchIfoNews objNews = new SearchIfoNews();

				objNews.setNewsHeader(rs.getString("NEWS_HEADER"));
				objNews.setNewsId(rs.getLong("NEWS_ID"));
				objNews.setNewsDate(rs.getDate("NEWS_DATE"));
				objNews.setIsHotNews(rs.getString("IS_HOT_NEWS"));
				objNews.setNewsType(rs.getString("TYPE_CODE"));
				return objNews;
			}
		}, pagingInfo);

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");

		return result;

	}

	/**
	 * 
	 * @param arrIfoNews
	 * @param status
	 * @throws SystemException
	 */
	public int updateStatus(IfoNews[] arrIfoNews, String status) throws SystemException {
		final String LOCATION = "searchIfoNews(arrIfoNews:" + arrIfoNews + ", status:" + status + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (arrIfoNews == null || status == null) {
			throw new SystemException(LOCATION, "arrIfoNews or status is NULL...");
		}
		try {
			if (arrIfoNews.length == 0) {
				return 0;
			}

			int intLength = arrIfoNews.length;
			Long[] newsIds = new Long[intLength];
			for (int i = 0; i < intLength; i++) {
				if (arrIfoNews[i] != null && arrIfoNews[i].getNewsId() != null) {
					newsIds[i] = arrIfoNews[i].getNewsId();
				} else {
					newsIds[i] = new Long(0);
				}
			}
			final String SQL = "UPDATE IFO_NEWS SET STATUS=:STATUS WHERE NEWS_ID IN (:NEWS_IDS)";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", status);
			paramMap.put("NEWS_IDS", newsIds);
			int intNum = OracleDAOHelper.update(this.getDataSource(), SQL, paramMap);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - intNum: " + intNum);
			return intNum;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param arrIfoNews
	 * @param status
	 * @return
	 * @throws SystemException
	 */
	public int deleteNewsByIds(IfoNews[] arrIfoNews) throws SystemException {
		final String LOCATION = "deleteNewsByIds(arrIfoNews:" + arrIfoNews + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (arrIfoNews == null) {
			throw new SystemException(LOCATION, "arrIfoNews or status is NULL...");
		}
		try {
			if (arrIfoNews.length == 0) {
				return 0;
			}

			int intLength = arrIfoNews.length;
			Long[] newsIds = new Long[intLength];
			for (int i = 0; i < intLength; i++) {
				if (arrIfoNews[i] != null && arrIfoNews[i].getNewsId() != null) {
					newsIds[i] = arrIfoNews[i].getNewsId();
				} else {
					newsIds[i] = new Long(0);
				}
			}
			final String SQL = "DELETE IFO_NEWS WHERE NEWS_ID IN (:NEWS_IDS)";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("NEWS_IDS", newsIds);

			int intNum = OracleDAOHelper.update(this.getDataSource(), SQL, paramMap);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - intNum: " + intNum);
			return intNum;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param ifoDocument
	 * @return
	 */
	public SearchResult searchNewsIfo(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchNewsIfo(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		SearchResult results = new SearchResult();
		try {
			StringBuffer sqlBuffer = new StringBuffer(
					" SELECT a.news_id, t.type_code, a.is_hot_news, a.source_id, a.news_header, ");
			sqlBuffer
			.append(" a.news_abstract, a.news_date, a.news_rank, a.NEWS_SOURCE, a.NEWS_RESOURCE, a.is_flv_news, a.locale, a.hit ");
			
			StringBuffer sqlFromBuffer = new StringBuffer(" FROM ifo_news a  ");
			StringBuffer sqlWhereBuffer = new StringBuffer(" where a.status=:STATUS ");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", ifoNews.getStatus());
			
			if (ifoNews.getLocale() != null && ifoNews.getLocale().trim().length() > 0) {
				sqlWhereBuffer.append(" and a.locale= :LOCAL");
				paramMap.put("LOCAL", ifoNews.getLocale());
			}
			
			// Hot news:
			if (ifoNews.getIsHotNews() != null && ifoNews.getIsHotNews().length() != 0) {
				sqlWhereBuffer.append(" and a.is_hot_news= :IS_HOT_NEWS");
				paramMap.put("IS_HOT_NEWS", ifoNews.getIsHotNews().toUpperCase());
			}
			
			if (ifoNews.getNewsType() != null && ifoNews.getNewsType().trim().length() > 0) {
				if ("NOT-VNDS-NEWS".equals(ifoNews.getNewsType())) {
					sqlWhereBuffer.append(" and t.type_code <> :TYPE_CODE");
					paramMap.put("TYPE_CODE", "VNDIRECT");
				} else {
					sqlWhereBuffer.append(" and t.type_code = :TYPE_CODE");
					paramMap.put("TYPE_CODE", ifoNews.getNewsType());
				}
			} else if (ifoNews.getNewsTypeList() != null && ifoNews.getNewsTypeList().size() > 0) {
				StringBuffer inSQL = new StringBuffer();
				for (Iterator inter = ifoNews.getNewsTypeList().iterator(); inter.hasNext();) {
					inSQL.append(inSQL.length() == 0 ? "'" : ",'").append(inter.next()).append("'");
				}
				sqlWhereBuffer.append(" and t.type_code in (" + inSQL.toString() + ") ");
			}
			
			if (ifoNews.getStrFromNewsDate() != null && ifoNews.getStrFromNewsDate().trim().length() > 0) {
				sqlWhereBuffer.append(" and a.news_date >= :NEWS_DATE_FROM");
				//paramMap.put("NEWS_DATE_FROM", VNDirectDateUtils.stringToDate(ifoNews.getStrFromNewsDate().trim(),
				//		VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
				paramMap.put("NEWS_DATE_FROM", vn.com.web.commons.utility.DateUtils.addDay(VNDirectDateUtils.stringToDate(ifoNews
						.getStrFromNewsDate().trim(), VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY), 0));
				// paramMap.put("NEWS_DATE_FROM",
				// ifoNews.getStrFromNewsDate().trim());
			}
			
			if (ifoNews.getStrToNewsDate() != null && ifoNews.getStrToNewsDate().trim().length() > 0) {
				sqlWhereBuffer.append(" and a.news_date < :NEWS_DATE_TO");
				paramMap.put("NEWS_DATE_TO", vn.com.web.commons.utility.DateUtils.addDay(VNDirectDateUtils.stringToDate(ifoNews
						.getStrToNewsDate().trim(), VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY), 1));
			}
			
			boolean hasSymbol = false;
			
			if (ifoNews.getListSymbols() != null && ifoNews.getListSymbols().length > 0) {
				StringBuffer inSQL = new StringBuffer();
				for (int i = 0; i < ifoNews.getListSymbols().length; i++) {
					inSQL.append(inSQL.length() == 0 ? "'" : ", '").append(ifoNews.getListSymbols()[i].toUpperCase().trim())
					.append("'");
				}
				sqlWhereBuffer.append(" and upper(cv.symbol) in (" + inSQL.toString() + ") ");
				
				sqlFromBuffer.append(" left join ifo_company_news_view cn on a.news_id = cn.news_id (+) ");
				sqlFromBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id  ");
				hasSymbol = true;
			}
			
			if (ifoNews.getCompanyId() != null && ifoNews.getCompanyId().longValue() != 0) {
				sqlWhereBuffer.append(" and cn.company_id= :COMPANY_ID ");
				paramMap.put("COMPANY_ID", ifoNews.getCompanyId());
				if (!hasSymbol) {
					sqlFromBuffer.append(" left join ifo_company_news_view cn on a.news_id = cn.news_id (+) ");
					sqlFromBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id  ");
				}
			}
			
			sqlWhereBuffer.append(" order by news_date desc NULLS LAST, news_id desc ");
			
			sqlFromBuffer.append(" left join ifo_news_type t on a.news_id = t.news_id ");
			sqlBuffer.append(sqlFromBuffer).append(sqlWhereBuffer);
			if (log.isDebugEnabled())
				log.debug(LOCATION + " sqlBuffer :: " + sqlBuffer);
			
			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sqlBuffer.toString(), paramMap, objNewsIfoRowMapper,
					pagingInfo);
			log.debug(LOCATION + "searchNewsIfo_ sql" + sqlBuffer.toString());
			
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
	
	public SearchResult searchNewsIfoHomePage(PagingInfo pagingInfo, int dayExpand) throws SystemException {
		final String LOCATION = "searchNewsIfoHomePage";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		SearchResult results = new SearchResult();
		try {
			StringBuilder sql = new StringBuilder(
					" SELECT a.news_id, a.news_header, ");
			sql.append(" a.news_abstract, t.type_code, a.news_date, a.NEWS_SOURCE ");
			sql.append(" FROM ifo_news a left join ifo_news_type t on a.news_id = t.news_id ");
			sql.append(" where a.status=:STATUS AND a.locale = :LOCALE ");
			sql.append(" AND a.news_date >= (sysdate-").append(dayExpand).append(")");
			sql.append(" ORDER BY a.news_date desc NULLS LAST, a.news_id desc ");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", "APP");
			paramMap.put("LOCALE", "VN");
			
			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap, objNewsIfoHomePageRowMapper,
					pagingInfo);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
	
	public SearchResult searchStockNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchStockNews(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		SearchResult results = new SearchResult();
		try {
			StringBuffer sqlBuffer = new StringBuffer(" SELECT distinct a.news_id, a.is_hot_news, a.source_id, a.news_header, ");
			sqlBuffer.append(" a.news_abstract, a.news_date, a.news_rank, a.NEWS_SOURCE, a.is_flv_news, a.locale ");
			sqlBuffer.append(" FROM ifo_news a  ");
			// sqlBuffer.append(" left join ifo_company_news_view cn on a.news_id = cn.news_id (+) ");
			// sqlBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id  ");
			sqlBuffer.append(" left join ifo_news_type t on a.news_id = t.news_id ");
			sqlBuffer.append(" WHERE a.status=:STATUS ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", ifoNews.getStatus());

			// if (ifoNews.getCompanyId() != null &&
			// ifoNews.getCompanyId().longValue() != 0) {
			// sqlBuffer.append(" and company_id= :COMPANY_ID");
			// paramMap.put("COMPANY_ID", ifoNews.getCompanyId());
			// }

			if (ifoNews.getLocale() != null && ifoNews.getLocale().trim().length() > 0) {
				sqlBuffer.append(" and a.locale= :LOCAL");
				paramMap.put("LOCAL", ifoNews.getLocale());
			}

			// Hot news:
			if (ifoNews.getIsHotNews() != null && ifoNews.getIsHotNews().length() != 0) {
				sqlBuffer.append(" and a.is_hot_news= :IS_HOT_NEWS");
				paramMap.put("IS_HOT_NEWS", ifoNews.getIsHotNews());
			}

			if (ifoNews.getNewsTypeList() != null && ifoNews.getNewsTypeList().size() > 0) {
				StringBuffer inSQL = new StringBuffer();
				for (Iterator inter = ifoNews.getNewsTypeList().iterator(); inter.hasNext();) {
					inSQL.append(inSQL.length() == 0 ? "'" : ",'").append(inter.next()).append("'");
				}
				sqlBuffer.append(" and t.type_code in (" + inSQL.toString() + ") ");
			}

			// if (ifoNews.getStrFromNewsDate() != null &&
			// ifoNews.getStrFromNewsDate().trim().length() > 0) {
			// sqlBuffer.append(" and a.news_date >= :NEWS_DATE_FROM");
			// paramMap.put("NEWS_DATE_FROM",
			// VNDirectDateUtils.stringToDate(ifoNews.getStrFromNewsDate().trim(),
			// VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			// }
			//
			// if (ifoNews.getStrToNewsDate() != null &&
			// ifoNews.getStrToNewsDate().trim().length() > 0) {
			// sqlBuffer.append(" and a.news_date <= :NEWS_DATE_TO");
			// paramMap.put("NEWS_DATE_TO",
			// VNDirectDateUtils.stringToDate(ifoNews.getStrToNewsDate().trim(),
			// VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			// }

			// if (ifoNews.getListSymbols() != null &&
			// ifoNews.getListSymbols().length > 0) {
			// StringBuffer inSQL = new StringBuffer();
			// for (int i = 0; i < ifoNews.getListSymbols().length; i++) {
			// inSQL.append(inSQL.length() == 0 ? "'" :
			// ", '").append(ifoNews.getListSymbols()[i].toUpperCase().trim()).append("'");
			// }
			// sqlBuffer.append(" and upper(cv.symbol) in (" + inSQL.toString()
			// + ") ");
			// }

			sqlBuffer.append(" order by news_date desc NULLS LAST, news_id desc ");
			if (log.isDebugEnabled())
				log.debug(LOCATION + " sqlBuffer :: " + sqlBuffer);

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sqlBuffer.toString(), paramMap,
					objStockNewsIfoRowMapper, pagingInfo);
			log.debug(LOCATION + "searchNewsIfo_ sql" + sqlBuffer.toString());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param newsIdList
	 * @return
	 * @throws SystemException
	 */
	public Map<Long, String> getNewsTypeMap(SearchResult<SearchIfoNews> newsIdList, SearchIfoNews ifoNews) throws SystemException {
		final String LOCATION = "getNewsTypeMap(newsIdList:" + newsIdList.size() + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		StringBuffer sql = new StringBuffer("SELECT distinct a.news_id, b.type_code ");
		sql.append("FROM ifo_news a left join ifo_news_type b on a.news_id = b.news_id ");
		sql.append("WHERE a.news_id in (");

		StringBuffer inSQL = new StringBuffer();
		for (SearchIfoNews searchIfoNews : newsIdList) {
			inSQL.append(inSQL.length() == 0 ? "'" : ", '").append(searchIfoNews.getNewsId()).append("'");
		}
		sql.append(inSQL).append(")");

		if (ifoNews.getNewsTypeList() != null && ifoNews.getNewsTypeList().size() > 0) {
			inSQL = new StringBuffer();
			for (Iterator inter = ifoNews.getNewsTypeList().iterator(); inter.hasNext();) {
				inSQL.append(inSQL.length() == 0 ? "'" : ",'").append(inter.next()).append("'");
			}
			sql.append(" and b.type_code in (" + inSQL.toString() + ") ");
		}

		SearchResult result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), null, new RowMapper() {
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				SearchIfoNews objNews = new SearchIfoNews();
				objNews.setNewsId(rs.getLong("NEWS_ID"));
				objNews.setNewsType(rs.getString("TYPE_CODE"));
				return objNews;
			}
		}, null);
		log.debug(LOCATION + "sql::" + sql.toString());

		Map<Long, String> mapResult = new HashMap<Long, String>();
		SearchIfoNews t = null;
		for (Object object : result) {
			t = (SearchIfoNews) object;
			mapResult.put(t.getNewsId(), t.getNewsType());
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");

		return mapResult;
	}

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws SystemException
	 */
	public IfoNews getVideoNewsInHomePage(IfoNews ifoNews) throws SystemException {
		final String LOCATION = "getVideoNewsInHomePage()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			IfoNews result = new IfoNews();
			StringBuffer sql = new StringBuffer(
					"SELECT * FROM (SELECT * FROM IFO_NEWS WHERE NEWS_TYPE IN ('VNDIRECT','DailyReport','Research') AND IS_FLV_NEWS = '1' AND NEWS_DATE < SYSDATE-0.5 ");
			sql.append(" AND STATUS = '"
					+ vn.com.web.commons.servercfg.ServerConfig
							.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED) + "'");
			sql.append(" ORDER BY NEWS_DATE DESC NULLS LAST) WHERE ROWNUM < 2 ");
			result = (IfoNews) OracleDAOHelper.querySingle(this.getDataSource(), sql.toString(), new HashMap(),
					objNewsIfoRowMapper);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public IfoNews getCommentsMarketNews(IfoNews ifoNews) throws SystemException {
		final String LOCATION = "getCommentsMarketNews(ifoNews:" + ifoNews + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}

		try {
			IfoNews result = new IfoNews();
			StringBuffer sqlBuffer = new StringBuffer(" select distinct a.news_id, t.type_code, a.news_date, a.news_header, ");
			sqlBuffer
					.append(" a.news_abstract, a.locale, a.news_source, a.NEWS_RESOURCE, a.is_hot_news, a.is_flv_news, a.news_rank, a.hit ");
			sqlBuffer.append(" from ifo_news a ");
			sqlBuffer.append(" left join ifo_news_type t on a.news_id = t.news_id ");
			sqlBuffer.append(" where a.status=:STATUS ");

			Map<String, Object> paramMap = new HashMap<String, Object>();

			// Status:
			paramMap.put("STATUS", ifoNews.getStatus());
			// Locale:
			if (ifoNews.getLocale() != null && ifoNews.getLocale().trim().length() > 0) {
				sqlBuffer.append(" and a.locale= :LOCAL");
				paramMap.put("LOCAL", ifoNews.getLocale());
			}
			// Types:
			if (ifoNews.getNewsType() != null && ifoNews.getNewsType().trim().length() > 0) {
				sqlBuffer.append(" and t.type_code = :TYPE_CODE");
				paramMap.put("TYPE_CODE", ifoNews.getNewsType());
			}

			sqlBuffer.append(" order by news_date desc NULLS LAST, news_id desc ");
			result = (IfoNews) OracleDAOHelper.querySingle(this.getDataSource(), sqlBuffer.toString(), paramMap,
					objNewsIfoRowMapper);

			log.debug(LOCATION + "getCommentsMarketNews_sql::" + sqlBuffer.toString());
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoNews
	 * @return
	 */
	// public List getNewsIDList(SearchIfoNews ifoNews, PagingInfo pagingInfo)
	// throws SystemException {
	// final String LOCATION = "getNewsIDList(SearchIfoNews:" + ifoNews +
	// "; pagingInfo:" + pagingInfo + ")";
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: BEGIN");
	// if (ifoNews == null) {
	// throw new SystemException(LOCATION, "ifoNews is NULL...");
	// }
	//
	// try {
	// StringBuffer sqlBuffer = new
	// StringBuffer(" SELECT distinct a.news_id, t.type_code, a.source_id, a.news_header, ");
	// sqlBuffer.append(" a.news_abstract, a.news_date, a.news_rank, a.NEWS_SOURCE ");
	// sqlBuffer.append(" FROM ifo_news a  ");
	// sqlBuffer.append(" left join ifo_company_news_view cn on a.news_id = cn.news_id (+) ");
	// sqlBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id  ");
	// sqlBuffer.append(" left join ifo_news_type t on a.news_id = t.news_id  ");
	// sqlBuffer.append(" where a.status=:STATUS ");
	//
	// Map<String, Object> paramMap = new HashMap<String, Object>();
	// paramMap.put("STATUS", ifoNews.getStatus());
	//
	// if (ifoNews.getCompanyId() != null && ifoNews.getCompanyId().longValue()
	// != 0) {
	// sqlBuffer.append(" and company_id= :COMPANY_ID");
	// paramMap.put("COMPANY_ID", ifoNews.getCompanyId());
	// }
	//
	// if (ifoNews.getLocale() != null && ifoNews.getLocale().trim().length() >
	// 0) {
	// sqlBuffer.append(" and upper(a.locale)= :LOCAL");
	// paramMap.put("LOCAL", ifoNews.getLocale().toUpperCase());
	// }
	//
	// if (ifoNews.getNewsType() != null &&
	// ifoNews.getNewsType().trim().length() > 0) {
	// sqlBuffer.append(" and t.type_code = :TYPE_CODE");
	// paramMap.put("TYPE_CODE", ifoNews.getNewsType());
	//
	// }
	// sqlBuffer.append(" order by news_date desc NULLS LAST ");
	//
	// // SearchResult results =
	// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
	// sqlBuffer.toString(), paramMap, objNewsIdRowMapper, ifoNews);
	// SearchResult results =
	// OracleDAOHelper.queryWithPagging(this.getDataSource(),
	// sqlBuffer.toString(), paramMap, objNewsIdRowMapper, pagingInfo);
	//
	// if (log.isDebugEnabled())
	// log.debug(LOCATION + ":: END : result " + results.size());
	//
	// return results;
	// } catch (Exception e) {
	// log.error(LOCATION, e);
	// throw new SystemException(LOCATION, e);
	// }
	// }

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> searchMartketNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchMartketNews(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuffer buffer = new StringBuffer();
			buffer.append("select distinct ");
			buffer.append("a.news_id, t.type_code, a.news_date, a.news_header, ");
			buffer.append("a.news_abstract, a.locale, a.news_source ");
			buffer.append("from ");
			buffer.append("ifo_news a left join ifo_news_type t on a.news_id = t.news_id ");
			buffer.append("where ");
			buffer.append("a.status=:STATUS	");
			buffer.append("and a.locale = :LOCALE ");

			buffer.append("	and t.type_code in ");
			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");
			buffer.append(" and t.type_code IS NOT NULL order by news_date desc NULLS LAST");

			paramMap.put("LOCALE", ifoNews.getLocale());
			paramMap.put("STATUS", ifoNews.getStatus());

			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, objMarketNews, pagingInfo);

			log.error(LOCATION + "searchMartketNews_sql " + buffer.toString());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());

			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult<SearchIfoNews> searchCenterAnalysisOfVNDirect(SearchIfoNews ifoNews, PagingInfo pagingInfo)
			throws SystemException {
		final String LOCATION = "searchCenterAnalysisOfVNDirect(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		try {
			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT a.ATTACHMENTS_ID NEWS_ID, a.TITLE NEWS_HEADER, a.ABSTRACT NEWS_ABSTRACT, a.RELEASED_DATE NEWS_DATE, ");
			buffer.append("a.SOURCE NEWS_SOURCE, a.FILE_NAME, a.FILE_PATH, a.LOCALE ");
			buffer.append("FROM IFO_ATTACHMENTS a ");
			buffer.append("WHERE a.STATUS=:STATUS ");
			buffer.append("AND a.LOCALE = :LOCALE ");
			buffer.append("AND a.ATTACHMENTS_TYPE in ");
			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");

			buffer.append("ORDER BY a.RELEASED_DATE DESC ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoNews.getLocale());
			paramMap.put("STATUS", ifoNews.getStatus());

			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new MarketNewsRowMapper(), pagingInfo);
			log.debug(LOCATION + "searchCenterAnalysisOfVNDirect_sql " + buffer.toString());
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 */
	public SearchResult<SearchIfoNews> getAttachments(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getAttachments(pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT ");
			buffer.append("	A.ATTACHMENTS_ID NEWS_ID, ");
			buffer.append("	A.TITLE NEWS_HEADER, ");
			buffer.append("	A.ABSTRACT NEWS_ABSTRACT, ");
			buffer.append("	A.RELEASED_DATE NEWS_DATE, ");
			buffer.append("	A.SOURCE NEWS_SOURCE, ");
			buffer.append("	A.FILE_NAME, ");
			buffer.append("	A.FILE_PATH, ");
			buffer.append("	A.LOCALE, ");
			buffer.append("	A.ATTACHMENTS_TYPE NEWS_TYPE, ");
			buffer.append("	(select ");
			buffer.append("		description ");
			buffer.append("	 from ");
			buffer.append("		ifo_data_ref ");
			buffer.append("	 where ");
			buffer.append("	 	GROUP_CODE = 'ATTACHMENTS' ");
			buffer.append("		and item_code = A.ATTACHMENTS_TYPE ");
			buffer.append("		AND LOCALE = :LOCALE ");
			buffer.append("	) NEW_TYPE_DESC ");
			buffer.append("FROM  		");
			buffer.append("    IFO_ATTACHMENTS A ");
			buffer.append("WHERE ");
			buffer.append("	A.NEWS_ID = 0 AND A.STATUS=:STATUS	");
			buffer.append("	AND A.ATTACHMENTS_TYPE IN ");

			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");

			if (!StringUtils.isEmpty(ifoNews.getLocale())) {
				buffer.append("AND A.LOCALE = :LOCALE ");
				paramMap.put("LOCALE", ifoNews.getLocale());
			}
			buffer.append(" ORDER BY A.RELEASED_DATE DESC");
			paramMap.put("STATUS", ifoNews.getStatus());
			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new LatestReportRowMapper(), pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult getMostViewedNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getMostViewedNews(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		// get most viewed news in 3 months
		String intervalDate = "180";
		if (MAC_VN_NEWSTYPE.equals(ifoNews.getNewsType()) || MAC_WORLD_NEWSTYPE.equals(ifoNews.getNewsType()) 
				|| DISCLOSURE_NEWSTYPE.equals(ifoNews.getNewsType()) || (ifoNews.getNewsTypeList() != null && ifoNews.getNewsTypeList().contains(EVENT_NEWSTYPE))) {
			if(!(ifoNews.getListSymbols() != null && ifoNews.getListSymbols().length > 0)){
				intervalDate = "7";
			}
		}
		
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		SearchResult results = new SearchResult();
		try {
			StringBuffer sqlBuffer = new StringBuffer(
					" SELECT a.news_id, t.type_code, a.is_hot_news, a.source_id, a.news_header, ");
			sqlBuffer
					.append(" a.news_abstract, a.news_date, a.news_rank, a.NEWS_SOURCE, a.NEWS_RESOURCE, a.is_flv_news, a.locale, a.hit ");

			StringBuffer sqlFromBuffer = new StringBuffer(" FROM ifo_news a  ");
			StringBuffer sqlWhereBuffer = new StringBuffer(" where a.status=:STATUS ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", ifoNews.getStatus());

			if (ifoNews.getLocale() != null && ifoNews.getLocale().trim().length() > 0) {
				sqlWhereBuffer.append(" and a.locale= :LOCAL");
				paramMap.put("LOCAL", ifoNews.getLocale());
			}

			if (ifoNews.getNewsType() != null && ifoNews.getNewsType().trim().length() > 0) {
				sqlWhereBuffer.append(" and t.type_code = :TYPE_CODE");
				paramMap.put("TYPE_CODE", ifoNews.getNewsType());
			} else if (ifoNews.getNewsTypeList() != null && ifoNews.getNewsTypeList().size() > 0) {
				StringBuffer inSQL = new StringBuffer();
				for (Iterator inter = ifoNews.getNewsTypeList().iterator(); inter.hasNext();) {
					inSQL.append(inSQL.length() == 0 ? "'" : ",'").append(inter.next()).append("'");
				}
				sqlWhereBuffer.append(" and t.type_code in (" + inSQL.toString() + ") ");
			}

			// FIXME
			// if (ifoNews.getStrFromNewsDate() != null &&
			// ifoNews.getStrFromNewsDate().trim().length() > 0) {
			// sqlWhereBuffer.append(" and to_date(a.news_date, 'dd-Mon-yy') >= to_date('").append(ifoNews.getStrFromNewsDate().trim()).append("', 'dd-Mon-yy') ");
			// }
			sqlWhereBuffer.append(" and a.news_date >= (sysdate-"+intervalDate+")");

			// filter with symbol
			if (ifoNews.getListSymbols() != null && ifoNews.getListSymbols().length > 0) {
				StringBuffer inSQL = new StringBuffer();
				for (int i = 0; i < ifoNews.getListSymbols().length; i++) {
					inSQL.append(inSQL.length() == 0 ? "'" : ", '").append(ifoNews.getListSymbols()[i].toUpperCase().trim())
							.append("'");
				}
				sqlWhereBuffer.append(" and upper(cv.symbol) in (" + inSQL.toString() + ") ");

				sqlFromBuffer.append(" left join ifo_company_news_view cn on a.news_id = cn.news_id (+) ");
				sqlFromBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cn.company_id=cv.company_id  ");
			}

			sqlWhereBuffer.append(" order by hit desc, news_date desc NULLS LAST, news_id desc ");

			sqlFromBuffer.append(" left join ifo_news_type t on a.news_id = t.news_id ");
			sqlBuffer.append(sqlFromBuffer).append(sqlWhereBuffer);
			if (log.isDebugEnabled())
				log.debug(LOCATION + " sqlBuffer :: " + sqlBuffer);

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sqlBuffer.toString(), paramMap, objNewsIfoRowMapper,
					pagingInfo);
			log.debug(LOCATION + "searchNewsIfo_ sql" + sqlBuffer.toString());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 */
	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getMostViewedAttachments(SearchIfoNews ifoNews, PagingInfo pagingInfo)
			throws SystemException {
		final String LOCATION = "getMostViewedAttachments(pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT ");
			buffer.append("	A.ATTACHMENTS_ID NEWS_ID, ");
			buffer.append("	A.TITLE NEWS_HEADER, ");
			buffer.append("	A.ABSTRACT NEWS_ABSTRACT, ");
			buffer.append("	A.RELEASED_DATE NEWS_DATE, ");
			buffer.append("	A.SOURCE NEWS_SOURCE, ");
			buffer.append("	A.FILE_NAME, ");
			buffer.append("	A.FILE_PATH, ");
			buffer.append("	A.LOCALE, ");
			buffer.append("	A.ATTACHMENTS_TYPE NEWS_TYPE, A.HIT, ");
			buffer.append("	(select ");
			buffer.append("		description ");
			buffer.append("	 from ");
			buffer.append("		ifo_data_ref ");
			buffer.append("	 where ");
			buffer.append("	 	GROUP_CODE = 'ATTACHMENTS' ");
			buffer.append("		and item_code = A.ATTACHMENTS_TYPE ");
			buffer.append("		AND LOCALE = :LOCALE ");
			buffer.append("	) NEW_TYPE_DESC ");
			buffer.append("FROM  		");
			buffer.append("    IFO_ATTACHMENTS A ");
			buffer.append("WHERE ");
			buffer.append("	A.NEWS_ID = 0 AND A.STATUS=:STATUS	");
			buffer.append("	AND A.ATTACHMENTS_TYPE IN ");

			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");

			if (ifoNews.getStrFromNewsDate() != null && ifoNews.getStrFromNewsDate().trim().length() > 0) {
				buffer.append(" and to_date(A.RELEASED_DATE, 'dd-Mon-yy') >= to_date('")
						.append(ifoNews.getStrFromNewsDate().trim()).append("', 'dd-Mon-yy') ");
			}

			if (!StringUtils.isEmpty(ifoNews.getLocale())) {
				buffer.append("AND A.LOCALE = :LOCALE ");
				paramMap.put("LOCALE", ifoNews.getLocale());
			}
			buffer.append(" ORDER BY A.HIT DESC, A.RELEASED_DATE DESC");
			paramMap.put("STATUS", ifoNews.getStatus());
			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new LatestReportRowMapper(), pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * get related video by news
	 * 
	 * @param searchObj
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getRelatedVideoNews(SearchIfoNews searchObj, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getRelatedVideoNews()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", searchObj.getLocale());
			paramMap.put("STATUS", searchObj.getStatus());// status
			paramMap.put("NEWS_TYPE", searchObj.getNewsType());// newsType

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("	DISTINCT A.NEWS_ID, ");
			buffer.append("	A.NEWS_HEADER, ");
			buffer.append("	A.NEWS_DATE, ");
			buffer.append("	A.NEWS_SOURCE, ");
			buffer.append("	A.LOCALE, ");
			buffer.append("	B.TYPE_CODE, ");
			buffer.append("	A.HIT ");
			// buffer.append("	B.CAPACITY ");
			buffer.append("FROM  		");
			buffer.append(" IFO_NEWS A, IFO_NEWS_TYPE B ");
			buffer.append("WHERE ");
			buffer.append("	A.STATUS = :STATUS	");
			buffer.append("	AND B.TYPE_CODE = :NEWS_TYPE ");
			buffer.append("	AND A.LOCALE = :LOCALE ");

			if (searchObj.getNewsId() != null) {
				buffer.append("	AND A.NEWS_DATE <= (SELECT t.NEWS_DATE FROM IFO_NEWS t WHERE t.NEWS_ID=:CURR_NEWS_ID) ");
				buffer.append("	AND A.NEWS_ID <> :CURR_NEWS_ID ");
				paramMap.put("CURR_NEWS_ID", searchObj.getNewsId());
			} else {
				buffer.append("	AND A.NEWS_DATE >= :START_DATE ");
				paramMap.put("START_DATE", searchObj.getFromNewsDate());
				System.out.println(searchObj.getFromNewsDate());
			}
			buffer.append("	AND A.NEWS_ID = B.NEWS_ID ");

			if (searchObj.getNewsId() != null) {
				buffer.append(" ORDER BY A.NEWS_DATE DESC NULLS LAST ");
			} else {
				buffer.append(" ORDER BY A.HIT DESC, A.NEWS_DATE DESC NULLS LAST");
			}

			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new NewsRowMapper2(), pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 */
	public SearchResult<IfoNews> getMarketNews(String newsType, Date date, String locale, String status, PagingInfo pagingInfo)
			throws SystemException {

		final String LOCATION = "getMarketNews()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (locale != null && "vn".equalsIgnoreCase(locale))
				locale = "VN";
			paramMap.put("LOCALE", locale);
			paramMap.put("STATUS", status);// status
			paramMap.put("NEWS_TYPE", newsType);// newsType

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("	A.ATTACHMENTS_ID NEWS_ID, ");
			buffer.append("	A.TITLE NEWS_HEADER, ");
			buffer.append("	A.ABSTRACT NEWS_ABSTRACT, ");
			buffer.append("	A.RELEASED_DATE NEWS_DATE, ");
			buffer.append("	A.SOURCE NEWS_SOURCE, ");
			buffer.append("	A.FILE_NAME, ");
			buffer.append("	A.FILE_PATH, ");
			buffer.append("	A.LOCALE ");
			buffer.append("FROM  		");
			buffer.append("    IFO_ATTACHMENTS A ");
			buffer.append("WHERE ");
			buffer.append("	A.STATUS=:STATUS	");
			buffer.append("	AND A.ATTACHMENTS_TYPE=:NEWS_TYPE	");
			buffer.append("	AND A.LOCALE = :LOCALE ");
			if (date == null) {
				buffer.append("ORDER BY A.RELEASED_DATE DESC");
			} else {
				buffer.append("AND A.RELEASED_DATE BETWEEN :DATE1 AND :DATE2");
				paramMap.put("DATE1", date);
				Date date2 = DateUtils.setHours(date, 23);
				date2 = DateUtils.setMinutes(date2, 59);
				date2 = DateUtils.setSeconds(date2, 59);
				paramMap.put("DATE2", date2);
			}

			SearchResult<IfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(), paramMap,
					new MarketNewsRowMapper(), pagingInfo);
			log.debug(LOCATION + "getMarketNews_sql " + buffer.toString());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	class MarketNewsRowMapper implements RowMapper<SearchIfoNews> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		public SearchIfoNews mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			Long id = arg0.getLong("NEWS_ID");
			objNews.setNewsId(id);
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			NewsAttachments att = new NewsAttachments();
			att.setAttachmentsId(id);
			att.setOriginalLink(arg0.getString("FILE_PATH")); // ORIGINAL_LINK
			att.setFileName(arg0.getString("FILE_NAME"));
			att.setUriLink(""); // URI_LINK
			att.setDescription(""); // description

			objNews.setAttachmentNews(Arrays.asList(new NewsAttachments[] { att }));
			return objNews;
		}
	}

	class LatestReportRowMapper extends MarketNewsRowMapper {
		@Override
		public SearchIfoNews mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews news = (SearchIfoNews) super.mapRow(arg0, arg1);
			news.setNewsTypeDesc(arg0.getString("NEW_TYPE_DESC"));
			news.setNewsType(arg0.getString("NEWS_TYPE"));
			return news;
		}
	}

	/**
	 * get all newsday o a news type
	 * 
	 * @param ifoNews
	 * @return
	 * @throws SystemException
	 */
	public SearchResult getAllNewsDay(IfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getAllNewsDay(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String SQL = " SELECT NEWS_DATE, NEWS_ID, NEWS_HEADER, LOCALE FROM IFO_NEWS WHERE LOCALE = :LOCAL AND NEWS_TYPE = :NEWS_TYPE AND STATUS=:STATUS ORDER BY NEWS_DATE DESC NULLS LAST";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCAL", ifoNews.getLocale());
			paramMap.put("NEWS_TYPE", ifoNews.getNewsType());
			paramMap.put("STATUS", ifoNews.getStatus());

			SearchResult results = OracleDAOHelper.query(this.getDataSource(), SQL, paramMap, new MarketNewsRowMapper(), null);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws SystemException
	 */
	public SearchResult getNewsInADay(IfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getAllNewsDay(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String SQL = " SELECT NEWS_DATE, NEWS_ID, NEWS_HEADER, NEWS_CONTENT, NEWS_SOURCE  FROM IFO_NEWS "
					+ " WHERE LOCALE = :LOCAL AND NEWS_TYPE = :NEWS_TYPE AND STATUS=:STATUS  AND NEWS_DATE < :NEWS_DATE1 AND NEWS_DATE >= :NEWS_DATE2";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCAL", ifoNews.getLocale());
			paramMap.put("NEWS_TYPE", ifoNews.getNewsType());
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(ifoNews.getNewsDate());
			cal2.setTime(ifoNews.getNewsDate());
			cal2.add(Calendar.DATE, 1);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			paramMap.put("NEWS_DATE1", cal2.getTime());
			paramMap.put("NEWS_DATE2", cal.getTime());
			paramMap.put("STATUS", ifoNews.getStatus());

			SearchResult results = OracleDAOHelper.queryWithPagging(this.getDataSource(), SQL, paramMap, objNewsInADay,
					pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	private final RowMapper objNewsInADay = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsDate(arg0.getDate("NEWS_DATE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setNewsContent(OracleDAOHelper.getClobColumn(arg0, "NEWS_CONTENT"));
			return objNews;
		}
	};

	private final RowMapper objNewsSectorIndustryRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("NEWS_TYPE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));

			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));

			objNews.setNewsRank(new Long(arg0.getLong("NEWS_RANK")));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			try {
				objNews.setIsHotNews(arg0.getString("IS_HOT_NEWS"));
				objNews.setDescription(arg0.getString("DESCRIPTION"));
			} catch (Exception e) {
			}
			objNews.setIsFlvNews(arg0.getString("IS_FLV_NEWS"));
			objNews.setLocale(arg0.getString("LOCALE"));

			return objNews;
		}
	};

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getNewsBySector(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getNewsBySector(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT ");
			buffer.append("  AB.NEWS_ID, ");
			buffer.append("  AB.NEWS_HEADER, ");

			buffer.append("	'' NEWS_TYPE,");
			buffer.append("	'' NEWS_ABSTRACT,");
			buffer.append("	AB.NEWS_DATE,");
			buffer.append("	'' NEWS_RANK,");
			buffer.append("	'' NEWS_SOURCE,");
			buffer.append("	'' IS_HOT_NEWS,");
			buffer.append("	'' IS_FLV_NEWS,");
			buffer.append("	'' LOCALE	");

			buffer.append("FROM (");
			buffer.append("  SELECT ");
			buffer.append("    A.NEWS_ID, ");
			buffer.append("    A.NEWS_HEADER, ");
			buffer.append("    A.NEWS_DATE ");
			buffer.append("  FROM ");
			buffer.append("    IFO_NEWS A, ");
			buffer.append("    IFO_NEWS_TYPE B");
			buffer.append("  WHERE ");
			buffer.append("    A.NEWS_ID=B.NEWS_ID ");

			buffer.append("    AND B.TYPE_CODE IN ");
			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");

			buffer.append("    AND A.LOCALE = :LOCALE ");
			buffer.append("    AND A.STATUS = :STATUS ");
			buffer.append(") AB, ");
			buffer.append("(");
			buffer.append("  SELECT ");
			buffer.append("    DISTINCT C.NEWS_ID ");
			buffer.append("  FROM ");
			buffer.append("    IFO_COMPANY_NEWS_VIEW C, ");
			buffer.append("    IFO_COMPANY_INDUSTRY_VIEW D ");
			buffer.append("  WHERE ");
			buffer.append("    C.COMPANY_ID=D.COMPANY_ID ");
			buffer.append("    AND D.SECTOR_GROUP_CODE = :SECTOR_GROUP_CODE ");
			if(ifoNews.getIndustryGroupCode() != null){
				buffer.append("    AND D.INDUSTRY_GROUP_CODE = :INDUSTRY_GROUP_CODE ");
			}
			buffer.append(") CD ");
			buffer.append("WHERE AB.NEWS_ID = CD.NEWS_ID ");

			buffer.append("ORDER BY AB.NEWS_DATE DESC NULLS LAST ");

			paramMap.put("STATUS", ifoNews.getStatus());
			paramMap.put("LOCALE", ifoNews.getLocale());
			paramMap.put("SECTOR_GROUP_CODE", ifoNews.getSectorGroupCode());
			paramMap.put("INDUSTRY_GROUP_CODE", ifoNews.getIndustryGroupCode());

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(), paramMap,
					objNewsSectorIndustryRowMapper, pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getNewsByIndustry(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getNewsByIndustry(SearchIfoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (ifoNews == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		SearchResult<SearchIfoNews> results = new SearchResult<SearchIfoNews>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT ");
			buffer.append("  AB.NEWS_ID, ");
			buffer.append("  AB.NEWS_HEADER, ");

			buffer.append("	'' NEWS_TYPE,");
			buffer.append("	'' NEWS_ABSTRACT,");
			buffer.append("	AB.NEWS_DATE,");
			buffer.append("	'' NEWS_RANK,");
			buffer.append("	'' NEWS_SOURCE,");
			buffer.append("	'' IS_HOT_NEWS,");
			buffer.append("	'' IS_FLV_NEWS,");
			buffer.append("	'' LOCALE	");

			buffer.append("FROM (");
			buffer.append("  SELECT ");
			buffer.append("    A.NEWS_ID, ");
			buffer.append("    A.NEWS_HEADER, ");
			buffer.append("    A.NEWS_DATE ");
			buffer.append("  FROM ");
			buffer.append("    IFO_NEWS A, ");
			buffer.append("    IFO_NEWS_TYPE B");
			buffer.append("  WHERE ");
			buffer.append("    A.NEWS_ID=B.NEWS_ID ");

			buffer.append("    AND B.TYPE_CODE IN ");
			buffer.append("(");
			Object[] types = ifoNews.getNewsTypeList().toArray();
			for (int i = 0; i < types.length; i++) {
				buffer.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
			}
			buffer.append(") ");

			buffer.append("    AND A.LOCALE = :LOCALE ");
			buffer.append("    AND A.STATUS = :STATUS ");
			buffer.append(") AB, ");
			buffer.append("(");
			buffer.append("  SELECT ");
			buffer.append("    DISTINCT C.NEWS_ID ");
			buffer.append("  FROM ");
			buffer.append("    IFO_COMPANY_NEWS_VIEW C, ");
			buffer.append("    IFO_COMPANY_INDUSTRY_VIEW D ");
			buffer.append("  WHERE ");
			buffer.append("    C.COMPANY_ID=D.COMPANY_ID ");
			buffer.append("    AND D.INDUSTRY_GROUP_CODE = :INDUSTRY_GROUP_CODE ");
			buffer.append(") CD ");
			buffer.append("WHERE AB.NEWS_ID = CD.NEWS_ID ");

			buffer.append("ORDER BY AB.NEWS_DATE DESC  NULLS LAST ");

			paramMap.put("STATUS", ifoNews.getStatus());
			paramMap.put("LOCALE", ifoNews.getLocale());
			// paramMap.put("NEWS_TYPE", ifoNews.getNewsType());
			paramMap.put("INDUSTRY_GROUP_CODE", ifoNews.getIndustryGroupCode());

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(), paramMap,
					objNewsSectorIndustryRowMapper, pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult<SearchIfoNews> getReportAnalysis(SearchIfoNews searchObj, PagingInfo pagingInfo) throws SystemException {

		return getReportAnalysisWithOrderByCondition(searchObj, pagingInfo, false);
	}
	
	public SearchResult<SearchIfoNews> getReportAnalysisWithOrderByCondition(SearchIfoNews searchObj, PagingInfo pagingInfo, Boolean orderByHit) throws SystemException {

		final String LOCATION = "getReportAnalysisWithOrderByCondition(SearchIfoNews:" + searchObj + "; pagingInfo:" + pagingInfo + "; orderByHit:" + orderByHit + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new SystemException(LOCATION, "ifoNews is NULL...");
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", searchObj.getLocale());
			paramMap.put("STATUS", searchObj.getStatus());// status

			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferWhere = new StringBuffer();
			StringBuffer bufferCondition = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("DISTINCT A.ATTACHMENTS_ID NEWS_ID,		");
			buffer.append("A.TITLE NEWS_HEADER,						");
			buffer.append("A.ABSTRACT NEWS_ABSTRACT,				");
			buffer.append("A.HIT HIT,								");
			buffer.append("A.RELEASED_DATE NEWS_DATE,				");
			buffer.append("A.SOURCE NEWS_SOURCE,					");
			buffer.append("A.FILE_NAME,								");
			buffer.append("A.FILE_PATH, 							");
			buffer.append("A.LOCALE, 								");
			buffer.append("B.SYMBOL 								");

			bufferWhere.append("FROM IFO_ATTACHMENTS A  			");
			bufferWhere.append(", IFO_COMPANY_NAME_VIEW B	");

			bufferCondition.append("WHERE A.STATUS=:STATUS			");
			bufferCondition.append("AND A.LOCALE = :LOCALE 	");
			bufferCondition.append(" AND A.COMPANY_ID = B.COMPANY_ID	");

			if (!CollectionUtils.isEmpty(searchObj.getNewsTypeList())) {
				Object[] types = searchObj.getNewsTypeList().toArray();
				bufferCondition.append("AND A.ATTACHMENTS_TYPE IN 	");
				bufferCondition.append("(");
				for (int i = 0; i < types.length; i++) {
					bufferCondition.append("'" + types[i] + (i != types.length - 1 ? "', " : "'"));
				}
				bufferCondition.append(") ");
			}

			if (!ArrayUtils.isEmpty(searchObj.getListSymbols())) {
				bufferCondition.append("AND B.SYMBOL IN ");
				bufferCondition.append("(");
				for (int i = 0; i < searchObj.getListSymbols().length; i++) {
					bufferCondition.append("'" + searchObj.getListSymbols()[i]
							+ (i != searchObj.getListSymbols().length - 1 ? "', " : "'"));
				}
				bufferCondition.append(") ");
			}

			buffer.append(bufferWhere).append(bufferCondition);
			
			if(orderByHit){
				buffer.append("ORDER BY A.HIT DESC");
			} else {
				buffer.append("ORDER BY A.RELEASED_DATE DESC");
			}
			
			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new MarketNewsRowMapper(), pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: SQL--> " + buffer.toString());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results.size());

			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Find list of news object
	 * 
	 * @param searchObj
	 * @param date
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getMarketNews(SearchIfoNews searchObj, Date date, PagingInfo pagingInfo)
			throws SystemException {
		final String LOCATION = "getMarketNews()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", searchObj.getLocale());
			paramMap.put("STATUS", searchObj.getStatus());// status
			paramMap.put("NEWS_TYPE", searchObj.getNewsType());// newsType

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("	DISTINCT A.NEWS_ID, ");
			buffer.append("	A.NEWS_HEADER, ");
			buffer.append("	A.NEWS_ABSTRACT, ");
			buffer.append("	A.NEWS_DATE, ");
			buffer.append("	A.NEWS_SOURCE, ");
			buffer.append("	A.LOCALE, ");
			buffer.append("	B.TYPE_CODE ");
			buffer.append("FROM  		");
			buffer.append(" IFO_NEWS A, IFO_NEWS_TYPE B  ");
			buffer.append("WHERE ");
			if (searchObj.getNewsId() != null && searchObj.getNewsId() > 0) {
				buffer.append("	A.NEWS_ID <= :NEWS_ID AND ");
				paramMap.put("NEWS_ID", searchObj.getNewsId());
			}
			buffer.append("	A.NEWS_ID = B.NEWS_ID 	");
			buffer.append("	AND A.STATUS=:STATUS	");
			buffer.append("	AND B.TYPE_CODE=:NEWS_TYPE	");
			buffer.append("	AND A.LOCALE = :LOCALE ");

			if (date == null) {
				buffer.append("ORDER BY A.NEWS_DATE DESC NULLS LAST");
			} else {
				buffer.append("AND A.NEWS_DATE BETWEEN :DATE1 AND :DATE2");
				paramMap.put("DATE1", date);
				Date date2 = DateUtils.setHours(date, 23);
				date2 = DateUtils.setMinutes(date2, 59);
				date2 = DateUtils.setSeconds(date2, 59);
				paramMap.put("DATE2", date2);
			}

			SearchResult<SearchIfoNews> results = OracleDAOHelper.queryWithPagging(this.getDataSource(), buffer.toString(),
					paramMap, new NewsRowMapper(), pagingInfo);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + results);
			return results;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Get latest news object
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public SearchIfoNews getLatestMartketNews(SearchIfoNews searchObj) throws SystemException {
		final String LOCATION = "getLatestMartketNews()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", searchObj.getLocale());
			paramMap.put("STATUS", searchObj.getStatus());// status
			paramMap.put("NEWS_TYPE", searchObj.getNewsType());// newsType

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("	DISTINCT A.NEWS_ID, ");
			buffer.append("	A.NEWS_HEADER, ");
			buffer.append("	A.NEWS_ABSTRACT, ");
			buffer.append("	A.NEWS_DATE, ");
			buffer.append("	A.NEWS_SOURCE, ");
			buffer.append("	A.LOCALE, ");
			buffer.append("	B.TYPE_CODE ");
			buffer.append("FROM  		");
			buffer.append(" IFO_NEWS A, IFO_NEWS_TYPE B ");
			buffer.append("WHERE ");
			buffer.append("	A.NEWS_ID = B.NEWS_ID 	");
			buffer.append("	AND A.STATUS=:STATUS	");
			buffer.append("	AND B.TYPE_CODE=:NEWS_TYPE	");
			// buffer.append("	AND A.NEWS_ID=154260	");
			buffer.append("	AND A.LOCALE = :LOCALE ");
			buffer.append("ORDER BY A.NEWS_DATE DESC NULLS LAST, A.NEWS_ID DESC");

			SearchIfoNews result = (SearchIfoNews) OracleDAOHelper.querySingle(getDataSource(), buffer.toString(), paramMap,
					new NewsRowMapper());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + result);
			return result;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	class NewsRowMapper implements RowMapper<SearchIfoNews> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		public SearchIfoNews mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			return objNews;
		}
	}

	class NewsRowMapper2 implements RowMapper<SearchIfoNews> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		public SearchIfoNews mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE"));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			objNews.setLocale(arg0.getString("LOCALE"));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setHit(new Long(arg0.getLong("HIT")));
			return objNews;
		}
	}

	/**
	 * Get an attachment object by its id
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public SearchIfoNews getAttachmentById(Long id) throws SystemException {
		final String LOCATION = "getAttachmentById()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ATTACHMENTS_ID", id);

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT ");
			buffer.append("	A.ATTACHMENTS_ID NEWS_ID, ");
			buffer.append("	A.TITLE NEWS_HEADER, ");
			buffer.append("	A.ABSTRACT NEWS_ABSTRACT, ");
			buffer.append("	A.RELEASED_DATE NEWS_DATE, ");
			buffer.append("	A.SOURCE NEWS_SOURCE, ");
			buffer.append("	A.FILE_NAME, ");
			buffer.append("	A.FILE_PATH, ");
			buffer.append("	A.LOCALE ");
			buffer.append("FROM  		");
			buffer.append("    IFO_ATTACHMENTS A ");
			buffer.append("WHERE ");
			buffer.append("	A.ATTACHMENTS_ID=:ATTACHMENTS_ID");

			SearchIfoNews result = (SearchIfoNews) OracleDAOHelper.querySingle(getDataSource(), buffer.toString(), paramMap,
					new MarketNewsRowMapper());

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END : result " + result);
			return result;
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Update hit field in ifo_news table
	 * 
	 * @param id
	 * @param key
	 * @throws SystemException
	 */
	@SuppressWarnings("serial")
	public void updateNewsHit(final Long id) throws SystemException {
		final String LOCATION = "updateNewsHit()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			int n = OracleDAOHelper.update(getDataSource(), "UPDATE IFO_NEWS SET HIT = (HIT + 1) WHERE NEWS_ID = :ID",
					new HashMap<String, Object>() {
						{
							put("ID", id);
						}
					});

			if (log.isDebugEnabled()) {
				log.debug(n + " rows has been updated");
				log.debug(LOCATION + ":: END : ");
			}
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * Update hit field in ifo_attachments table
	 * 
	 * @param id
	 * @param key
	 * @throws SystemException
	 */
	@SuppressWarnings("serial")
	public void updateAttachmentsHit(final Long id) throws SystemException {
		final String LOCATION = "updateNewsHit()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			int n = OracleDAOHelper.update(getDataSource(), "UPDATE IFO_ATTACHMENTS SET HIT = (HIT + 1) WHERE NEWS_ID = :ID",
					new HashMap<String, Object>() {
						{
							put("ID", id);
						}
					});

			if (log.isDebugEnabled()) {
				log.debug(n + " rows has been updated");
				log.debug(LOCATION + ":: END : ");
			}
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
	private final RowMapper objNewsIfoHomePageRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			SearchIfoNews objNews = new SearchIfoNews();
			objNews.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			objNews.setNewsType(arg0.getString("TYPE_CODE"));
			objNews.setNewsHeader(arg0.getString("NEWS_HEADER"));
			objNews.setNewsAbstract(arg0.getString("NEWS_ABSTRACT"));
			objNews.setNewsDate(arg0.getTimestamp("NEWS_DATE", Calendar.getInstance()));
			objNews.setNewsResource(arg0.getString("NEWS_SOURCE"));
			return objNews;
		}
	};
}
