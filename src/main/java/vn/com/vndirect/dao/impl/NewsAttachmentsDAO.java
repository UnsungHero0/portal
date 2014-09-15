package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.NewsAttachments;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NewsAttachmentsDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(NewsAttachmentsDAO.class);

	private String SELECT_SQL = "SELECT * FROM IFO_ATTACHMENTS WHERE NEWS_ID = :NEWS_ID";
	private String INSERT_SQL = "INSERT INTO ATTACHMENTS (ATTACMENT_ID , NEWS_ID, ORIGINAL_LINK, URI_LINK) VALUES (:ATTACMENT_ID, :NEWS_ID, :ORIGINAL_LINK, :URI_LINK) ";
	private String UPDATE_SQL = "UPDATE ATTACHMENTS SET NEWS_ID = :NEWS_ID, ORIGINAL_LINK = :ORIGINAL_LINK, URI_LINK = :URI_LINK WHERE ATTACMENT_ID = :ATTACMENT_ID";

	private final RowMapper objNewsAttachmentsRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			NewsAttachments newsAttachments = new NewsAttachments();
			newsAttachments.setNewsId(new Long(arg0.getLong("NEWS_ID")));
			newsAttachments.setAttachmentsId(new Long(arg0.getLong("ATTACHMENTS_ID")));

			newsAttachments.setOriginalLink(arg0.getString("FILE_PATH")); // ORIGINAL_LINK
			newsAttachments.setUriLink(""); // URI_LINK
			newsAttachments.setDescription(arg0.getString("TITLE")); // description
			newsAttachments.setFileName(arg0.getString("FILE_NAME"));
			newsAttachments.setAttachmentType(arg0.getString("ATTACHMENTS_TYPE"));
			newsAttachments.setHit(arg0.getLong("HIT"));

			return newsAttachments;
		}
	};

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws SystemException
	 */
	public List<NewsAttachments> getNewsAttachments(IfoNews ifoNews) throws SystemException {
		final String LOCATION = "getNewsAttachments(ifoNews:" + ifoNews + ")";
		log.debug(LOCATION + ":: BEGIN");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NEWS_ID", ifoNews.getNewsId());
		SearchResult<NewsAttachments> searchResult = new SearchResult<NewsAttachments>();
		try {
			searchResult = OracleDAOHelper.query(this.getDataSource(), SELECT_SQL, paramMap, objNewsAttachmentsRowMapper);
		} catch (Exception e) {
			log.error(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : result " + searchResult.size());
		return searchResult;

	}

	/**
	 * 
	 * @param newsAttachment
	 * @throws Exception
	 */
	public void insert(NewsAttachments newsAttachment) throws SystemException {
		final String LOCATION = "insert(newsAttachment:" + newsAttachment + ")";
		log.error(LOCATION + ":: BEGIN");
		try {
			long id = OracleDAOHelper.nextval(this.getDataSource(), "ATTACHMENTS_SEQ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ATTACMENT_ID", new Long(id));
			paramMap.put("NEWS_ID", newsAttachment.getNewsId());
			paramMap.put("ORIGINAL_LINK", newsAttachment.getOriginalLink());
			paramMap.put("URI_LINK", newsAttachment.getUriLink());

			OracleDAOHelper.update(this.getDataSource(), INSERT_SQL, paramMap);

			log.error(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + "::Exception " + e.getMessage());
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param newsAttachment
	 * @throws SystemException
	 */
	public void attachDirty(NewsAttachments newsAttachment) throws SystemException {
		final String LOCATION = "attachDirty(NewsAttachments: " + newsAttachment + ")";
		log.debug(LOCATION + ":: BEGIN");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NEWS_ID", newsAttachment.getNewsId());
		paramMap.put("ORIGINAL_LINK", newsAttachment.getOriginalLink());
		paramMap.put("URI_LINK", newsAttachment.getUriLink());

		String SQL = null;
		if (newsAttachment.getAttachmentsId() != null) {
			paramMap.put("ATTACMENT_ID", newsAttachment.getAttachmentsId());
			SQL = UPDATE_SQL;
		} else {
			long id = OracleDAOHelper.nextval(this.getDataSource(), "ATTACHMENTS_SEQ");
			paramMap.put("ATTACMENT_ID", new Long(id));
			SQL = INSERT_SQL;
		}

		try {
			OracleDAOHelper.update(this.getDataSource(), SQL, paramMap);

			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		log.debug(LOCATION + ":: END");
	}

	/**
	 * @param arrIfoNews
	 * @throws SystemException
	 */
	public void deleteNewsByIds(IfoNews[] arrIfoNews) throws SystemException {
		final String LOCATION = "deleteNewsByIds(arrIfoNews:" + arrIfoNews + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (arrIfoNews == null) {
			throw new SystemException(LOCATION, "arrIfoNews or status is NULL...");
		}
		try {
			if (arrIfoNews.length == 0)
				return;

			for (int i = 0; i < arrIfoNews.length; i++) {
				if (arrIfoNews[i] != null && arrIfoNews[i].getNewsId() != null) {
					this.deleteAttachmentsNews(arrIfoNews[i].getNewsId());
				}
			}

			log.debug(LOCATION + ":: END ");
			return;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param newsId
	 * @return
	 * @throws SystemException
	 */
	public int deleteAttachmentsNews(Long newsId) throws SystemException {
		final String LOCATION = "deleteAttachmentsNews(newsId:" + newsId + ")";

		log.debug(LOCATION + ":: BEGIN");
		int result = -1;

		String DELETE_SQL = "DELETE FROM ATTACHMENTS WHERE NEWS_ID = :NEWS_ID ";
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("NEWS_ID", newsId);
			result = OracleDAOHelper.update(this.getDataSource(), DELETE_SQL, paramMap);
		} catch (Exception e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : results " + result);
		return result;
	}

	private String SELECT_SQL_PDF = "SELECT * FROM ATTACHMENTS WHERE NEWS_ID = :NEWS_ID AND UPPER(ORIGINAL_LINK) LIKE '%.PDF' ORDER BY ORIGINAL_LINK ";

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public List<NewsAttachments> getPdfAttachments(IfoNews ifoNews, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getPdfAttachments(ifoNews:" + ifoNews + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NEWS_ID", ifoNews.getNewsId());
		SearchResult<NewsAttachments> searchResult = new SearchResult<NewsAttachments>();

		try {
			searchResult = OracleDAOHelper.queryWithPagging(this.getDataSource(), SELECT_SQL_PDF, paramMap,
			        objNewsAttachmentsRowMapper, pagingInfo);
		} catch (Exception e) {
			log.error(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : result " + searchResult.size());
		return searchResult;
	}

	/**
	 * @param newsIdList
	 * @return
	 * @throws SystemException
	 */
	public Map<Long, List<NewsAttachments>> getAttachmentsMap(SearchResult<SearchIfoNews> newsIdList) throws SystemException {
		final String LOCATION = "getAttachmentsMap(newsIdList:" + newsIdList.size() + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		StringBuffer sql = new StringBuffer("SELECT * FROM IFO_ATTACHMENTS a ");
		sql.append("WHERE a.news_id in (");

		StringBuffer inSQL = new StringBuffer();
		for (SearchIfoNews searchIfoNews : newsIdList) {
			inSQL.append(inSQL.length() == 0 ? "'" : ", '").append(searchIfoNews.getNewsId()).append("'");
		}
		sql.append(inSQL).append(")");
		sql.append(" ORDER BY a.news_id desc, a.attachments_id desc ");

		SearchResult result = OracleDAOHelper
		        .query(this.getDataSource(), sql.toString(), null, objNewsAttachmentsRowMapper, null);
		log.debug(LOCATION + "sql::" + sql.toString());

		Map<Long, List<NewsAttachments>> mapResult = new HashMap<Long, List<NewsAttachments>>();
		NewsAttachments e = null;
		List<NewsAttachments> attachList = null;
		Long newsId = new Long(0);
		for (Object object : result) {
			e = (NewsAttachments) object;
			if (!newsId.equals(e.getNewsId())) {
				if (newsId.longValue() != 0)
					mapResult.put(newsId, attachList);
				attachList = new ArrayList<NewsAttachments>();
				attachList.add(e);
				newsId = e.getNewsId();
			} else {
				attachList.add(e);
			}
		}
		if (attachList != null)
			mapResult.put(newsId, attachList);

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END");

		return mapResult;
	}
}
