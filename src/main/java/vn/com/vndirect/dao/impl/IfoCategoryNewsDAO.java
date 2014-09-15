/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| 31 Oct 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCategoryNews;
import vn.com.vndirect.domain.IfoNews;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * @author thangnq
 * 
 */
@SuppressWarnings("unchecked")
public class IfoCategoryNewsDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoCategoryNewsDAO.class);

	private String INSERT_SQL = "INSERT INTO IFO_CATEGORY_NEWS (CATEGORY_ID, NEWS_ID) VALUES (:CATEGORY_ID, :NEWS_ID) ";

	/**
	 * 
	 * @param transientInstance
	 * @throws Exception
	 */
	public void insert(IfoCategoryNews transientInstance) throws Exception {
		log.debug("inserting IfoCategoryNews instance");
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("CATEGORY_ID", transientInstance.getCategoryId());
			paramMap.put("NEWS_ID", transientInstance.getNewsId());

			OracleDAOHelper.update(this.getDataSource(), INSERT_SQL, paramMap);

			log.debug("insert successful");
		} catch (Exception e) {
			log.error("insert failed", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param persistentInstance
	 * @throws Exception
	 */
	public void delete(IfoCategoryNews persistentInstance) throws Exception {
		log.debug("deleting IfoCompanyNews instance");
		try {
			String DELETE_SQL = "DELETE FROM IFO_CATEGORY_NEWS WHERE CATEGORY_ID = :CATEGORY_ID AND NEWS_ID = :NEWS_ID ";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("CATEGORY_ID", persistentInstance.getCategoryId());
			paramMap.put("NEWS_ID", persistentInstance.getNewsId());

			OracleDAOHelper.update(this.getDataSource(), DELETE_SQL, paramMap);

			log.debug("delete successful");
		} catch (Exception e) {
			log.error("delete failed", e);
			throw e;
		}
	}

	public static IfoCategoryNewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCategoryNewsDAO) ctx.getBean("IfoCategoryNewsDAO");
	}

	/**
	 * 
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
					this.deleteCategoryNews(arrIfoNews[i].getNewsId());
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
	public int deleteCategoryNews(Long newsId) throws SystemException {
		final String LOCATION = "deleteCategoryNews(newsId:" + newsId + ")";

		log.debug(LOCATION + ":: BEGIN");
		int results = -1;
		String DELETE_SQL = "DELETE FROM IFO_CATEGORY_NEWS WHERE NEWS_ID = :NEWS_ID ";
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("NEWS_ID", newsId);

			results = OracleDAOHelper.update(this.getDataSource(), DELETE_SQL, paramMap);

		} catch (Exception e) {
			log.error(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : results " + results);
		return results;
	}

	@SuppressWarnings("rawtypes")
	private final RowMapper objCategoryRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCategoryNews objCategory = new IfoCategoryNews();
			objCategory.setCategoryId(new Long(rs.getLong("CATEGORY_ID")));
			objCategory.setCategoryCode(rs.getString("CATEGORY_CODE"));
			objCategory.setCategoryDescription(rs.getString("CATEGORY_DESCRIPTION"));
			return objCategory;
		}
	};

	/**
	 * 
	 * @param newsId
	 * @return
	 * @throws SystemException
	 */
	public Collection<IfoCategoryNews> selectCategory(Long newsId) throws SystemException {
		final String LOCATION = "selectCategory(newsId:" + newsId + ")";
		log.debug(LOCATION + ":: BEGIN");

		String SELECT_SQL = "SELECT C.* FROM IFO_CATEGORY C, IFO_CATEGORY_NEWS CI WHERE C.CATEGORY_ID = CI.CATEGORY_ID  ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (newsId != null && newsId.longValue() > 0) {
			SELECT_SQL += " AND CI.NEWS_ID = :NEWS_ID ";
			paramMap.put("NEWS_ID", newsId);
		}
		SearchResult<IfoCategoryNews> results = new SearchResult<IfoCategoryNews>();
		try {
			// SearchResult results =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SELECT_SQL, paramMap, objCategoryRowMapper, null);
			results = OracleDAOHelper.query(this.getDataSource(), SELECT_SQL, paramMap, objCategoryRowMapper);

		} catch (Exception e) {
			log.error(LOCATION, e);
		}

		log.debug(LOCATION + ":: END : result ");
		return results;
	}

	/**
	 * 
	 * @param ifoCategoryNews
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult<IfoCategoryNews> searchCategory(IfoCategoryNews ifoCategoryNews, PagingInfo pagingInfo)
	        throws SystemException {
		final String LOCATION = "searchCategory(IfoCategoryNews:" + ifoCategoryNews + "; pagingInfo:" + pagingInfo + ")";
		SearchResult<IfoCategoryNews> results = new SearchResult<IfoCategoryNews>();
		String SQL_SEARCH = "SELECT CATEGORY_ID, CATEGORY_CODE, CATEGORY_DESCRIPTION FROM IFO_CATEGORY ";
		boolean hasCriteria = false;

		if (ifoCategoryNews.getCategoryCode() != null && ifoCategoryNews.getCategoryCode().trim().length() > 0) {
			SQL_SEARCH += " WHERE CATEGORY_CODE LIKE '%" + ifoCategoryNews.getCategoryCode().trim() + "%' ";
			hasCriteria = true;
		}
		if (ifoCategoryNews.getCategoryDescription() != null && ifoCategoryNews.getCategoryDescription().trim().length() > 0) {
			if (hasCriteria) {
				SQL_SEARCH += " AND CATEGORY_DESCRIPTION LIKE '%" + ifoCategoryNews.getCategoryDescription().trim() + "%' ";
			} else {
				SQL_SEARCH += " WHERE CATEGORY_DESCRIPTION LIKE '%" + ifoCategoryNews.getCategoryDescription().trim() + "%' ";
			}
		}
		SQL_SEARCH += " ORDER BY CATEGORY_CODE ASC ";
		log.debug(LOCATION + ":: END : SQL_SEARCH " + SQL_SEARCH);
		try {

			// results = OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SQL_SEARCH, new HashMap(), objCategoryRowMapper,
			// ifoCategoryNews);
			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), SQL_SEARCH, new HashMap(), objCategoryRowMapper,
			        pagingInfo);
			log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

}