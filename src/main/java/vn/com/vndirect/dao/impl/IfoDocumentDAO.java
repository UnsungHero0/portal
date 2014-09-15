package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.SearchIfoDocument;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoDocument.
 * 
 * @see vn.com.vndirect.domain.IfoDocument
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoDocumentDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(IfoDocumentDAO.class);

	// //////////////////////////////////Using JDBC
	// /////////////////////////////////////

	public IfoDocument findById(java.lang.Long id) throws SystemException {
		final String LOCATION = "findById(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (VNDirectWebUtilities.getLongValue(id) < 1) {
			throw new SystemException(LOCATION, "companyId is NULL...");
		}

		try {
			String SEARCH_SQL = "SELECT distinct t0.* FROM IFO_DOCUMENT t0 " + " WHERE t0.COMPANY_ID = :COMPANY_ID";

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id);

			IfoDocument instance = (IfoDocument) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoDocumentRowMapper);

			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void attachDirty(IfoDocument ifoDocument) throws SystemException {
		final String LOCATION = "attachDirty(ifoCompany:" + ifoDocument + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoDocument == null) {
			throw new SystemException(LOCATION, "ifoDocument is NULL...");
		}
		String INSERT_SQL = "INSERT INTO IFO_DOCUMENT (DOCUMENT_ID, DOCUMENT_TYPE, TITLE, CONTRIBUTOR, "
		        + " AUTHOR, RELEASED_DATE, ABSTRACT, FILE_NAME, FILE_PATH, STATUS, ACCESS_LEVEL, ACCESS_TYPE, "
		        + " CREATED_DATE, CREATED_BY, LOCALE) "
		        + " VALUES (:DOCUMENT_ID, :DOCUMENT_TYPE, :TITLE, :CONTRIBUTOR, :AUTHOR, :RELEASED_DATE, :ABSTRACT, :FILE_NAME, "
		        + " :FILE_PATH, :STATUS, :ACCESS_LEVEL, :ACCESS_TYPE, :CREATED_DATE, :CREATED_BY, :LOCALE) ";

		String UPDATE_SQL = "UPDATE IFO_DOCUMENT "
		        + "SET DOCUMENT_TYPE = :DOCUMENT_TYPE, TITLE = :TITLE, CONTRIBUTOR = :CONTRIBUTOR, AUTHOR = :AUTHOR, "
		        + " RELEASED_DATE = :RELEASED_DATE, ABSTRACT = :ABSTRACT, FILE_NAME = :FILE_NAME, FILE_PATH = :FILE_PATH, "
		        + " STATUS = :STATUS, ACCESS_LEVEL = :ACCESS_LEVEL, ACCESS_TYPE = :ACCESS_TYPE, "
		        + " MODIFIED_DATE = :MODIFIED_DATE, MODIFIED_BY = :MODIFIED_BY, LOCALE = :LOCALE "
		        + " WHERE DOCUMENT_ID = :DOCUMENT_ID ";

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("DOCUMENT_TYPE", ifoDocument.getDocumentType());
			paramMap.put("TITLE", ifoDocument.getTitle());
			paramMap.put("CONTRIBUTOR", ifoDocument.getContributor());
			paramMap.put("AUTHOR", ifoDocument.getAuthor());
			paramMap.put("RELEASED_DATE", ifoDocument.getReleasedDate());
			paramMap.put("ABSTRACT", ifoDocument.getAbstract_());
			paramMap.put("FILE_NAME", ifoDocument.getFileName());
			paramMap.put("FILE_PATH", ifoDocument.getFilePath());
			paramMap.put("STATUS", ifoDocument.getStatus());
			paramMap.put("ACCESS_LEVEL", ifoDocument.getAccessLevel());
			if (ifoDocument.getIfoAccessType() != null) {
				paramMap.put("ACCESS_TYPE", ifoDocument.getIfoAccessType().getAccessType());
			} else {
				paramMap.put("ACCESS_TYPE", null);
			}
			paramMap.put("LOCALE", ifoDocument.getLocale());
			if (VNDirectWebUtilities.getLongValue(ifoDocument.getDocumentId()) < 1) {
				long nextVal = OracleDAOHelper.nextval(this.getDataSource(), DBConstants.SEQ.IFO_DOCUMENT_SEQ);
				paramMap.put("DOCUMENT_ID", VNDirectWebUtilities.getLong(nextVal));
				if (ifoDocument.getCreatedDate() != null) {
					paramMap.put("CREATED_DATE", ifoDocument.getCreatedDate());
				} else {
					paramMap.put("CREATED_DATE", new Date());
				}
				paramMap.put("CREATED_BY", ifoDocument.getCreatedBy());

				int intNum = OracleDAOHelper.update(this.getDataSource(), INSERT_SQL, paramMap);
				log.debug(LOCATION + "::END intNum" + intNum);

			} else {
				paramMap.put("DOCUMENT_ID", ifoDocument.getDocumentId());
				if (ifoDocument.getModifiedDate() != null) {
					paramMap.put("MODIFIED_DATE", ifoDocument.getModifiedDate());
				} else {
					paramMap.put("MODIFIED_DATE", new Date());
				}
				paramMap.put("MODIFIED_BY", ifoDocument.getModifiedBy());

				int intNum = OracleDAOHelper.update(this.getDataSource(), UPDATE_SQL, paramMap);
				log.debug(LOCATION + "::END intNum" + intNum);
			}

		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	public static IfoDocumentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoDocumentDAO) ctx.getBean("IfoDocumentDAO");
	}

	/**
	 * process all attributes of table
	 */
	private final RowMapper ifoDocumentRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {

			IfoDocument obj = new IfoDocument();
			obj.setDocumentId(VNDirectWebUtilities.getLong(rs.getLong("DOCUMENT_ID")));
			obj.setDocumentType(rs.getString("DOCUMENT_TYPE"));
			obj.setTitle(rs.getString("TITLE"));
			obj.setContributor(rs.getString("CONTRIBUTOR"));
			obj.setAuthor(rs.getString("AUTHOR"));
			try {
				obj.setReleasedDate(rs.getDate("RELEASED_DATE"));
			} catch (Exception e) {
			}
			obj.setAbstract_(rs.getString("ABSTRACT"));
			obj.setFileName(rs.getString("FILE_NAME"));
			obj.setFilePath(rs.getString("FILE_PATH"));
			obj.setStatus(rs.getString("STATUS"));
			obj.setAccessLevel(VNDirectWebUtilities.getLong(rs.getLong("ACCESS_LEVEL")));
			try {
				obj.setCreatedDate(rs.getDate("CREATED_DATE"));
			} catch (Exception e) {
			}
			obj.setCreatedBy(rs.getString("CREATED_BY"));
			try {
				obj.setModifiedDate(rs.getDate("MODIFIED_DATE"));
			} catch (Exception e) {
			}
			obj.setModifiedBy(rs.getString("MODIFIED_BY"));
			obj.setLocale(rs.getString("LOCALE"));

			return obj;
		}
	};

	/**
	 * @param ifoDocument
	 * @return
	 */
	public SearchResult<IfoDocument> searchIfoDocument(IfoDocument ifoDocument, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchIfoDocument(IfoDocument:" + ifoDocument + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoDocument == null) {
			throw new SystemException(LOCATION, "ifoDocument is NULL...");
		}
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT distinct t0.* FROM IFO_DOCUMENT t0 WHERE 1=1 ");

			Map<String, Object> paramMap = new HashMap<String, Object>();

			String str;
			// add TITLE
			str = VNDirectWebUtilities.cleanString(ifoDocument.getTitle());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.TITLE) like :TITLE ");
				paramMap.put("TITLE", str.toUpperCase() + "%");
			}
			// add DOCUMENT_TYPE
			str = VNDirectWebUtilities.cleanString(ifoDocument.getDocumentType());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.DOCUMENT_TYPE) like :DOCUMENT_TYPE ");
				paramMap.put("DOCUMENT_TYPE", str.toUpperCase() + "%");
			}
			// add CREATED_BY
			str = VNDirectWebUtilities.cleanString(ifoDocument.getCreatedBy());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.CREATED_BY) like :CREATED_BY ");
				paramMap.put("CREATED_BY", str.toUpperCase() + "%");
			}
			// add CONTRIBUTOR
			str = VNDirectWebUtilities.cleanString(ifoDocument.getContributor());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.CONTRIBUTOR) like :CONTRIBUTOR ");
				paramMap.put("CONTRIBUTOR", str.toUpperCase() + "%");
			}
			// add AUTHOR
			str = VNDirectWebUtilities.cleanString(ifoDocument.getAuthor());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.AUTHOR) like :AUTHOR ");
				paramMap.put("AUTHOR", str.toUpperCase() + "%");
			}
			// add RELEASE_DATE
			if (ifoDocument instanceof SearchIfoDocument) {
				SearchIfoDocument searchObj = (SearchIfoDocument) ifoDocument;

				if (searchObj.getFromReleaseDate() != null && searchObj.getToReleaseDate() != null) {
					sql.append(" AND t0.RELEASED_DATE > :FROM_RELEASED_DATE AND t0.RELEASED_DATE < :TO_RELEASED_DATE ");
					paramMap.put("FROM_RELEASED_DATE", searchObj.getFromReleaseDate());
					paramMap.put("TO_RELEASED_DATE", searchObj.getToReleaseDate());

				} else if (searchObj.getFromReleaseDate() == null && searchObj.getToReleaseDate() != null) {
					sql.append(" AND t0.RELEASED_DATE < :TO_RELEASED_DATE ");
					paramMap.put("TO_RELEASED_DATE", searchObj.getToReleaseDate());

				} else if (searchObj.getFromReleaseDate() != null && searchObj.getToReleaseDate() == null) {
					sql.append(" AND t0.RELEASED_DATE > :FROM_RELEASED_DATE ");
					paramMap.put("FROM_RELEASED_DATE", searchObj.getFromReleaseDate());
				}
				// Long companyId = searchObj.getCompanyId();
				// if (companyId != null && companyId.longValue() >0) {
				// // Restrictions
				// //TODO
				// }

				// System.out.println("searchObj.getStrFromReleaseDate()  = " +
				// searchObj.getStrFromReleaseDate());
				// System.out.println("searchObj.getFromReleaseDate()  = " +
				// searchObj.getFromReleaseDate());
			}
			// add STATUS
			str = VNDirectWebUtilities.cleanString(ifoDocument.getStatus());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.STATUS) like :STATUS ");
				paramMap.put("STATUS", str.toUpperCase() + "%");
			}
			// add ACCESS_LEVEL
			Long accessLevel = ifoDocument.getAccessLevel();
			if (accessLevel != null && accessLevel.longValue() != 0) {
				sql.append(" AND t0.ACCESS_LEVEL = :ACCESS_LEVEL ");
				paramMap.put("ACCESS_LEVEL", accessLevel);
			}
			// add ABSTRACT_
			str = VNDirectWebUtilities.cleanString(ifoDocument.getAbstract_());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.ABSTRACT) like :ABSTRACT ");
				paramMap.put("ABSTRACT", str.toUpperCase() + "%");
			}
			// add LOCALE
			str = VNDirectWebUtilities.cleanString(ifoDocument.getLocale());
			if (str != null && str.length() != 0) {
				sql.append(" AND upper(t0.LOCALE) like :LOCALE ");
				paramMap.put("LOCALE", str.toUpperCase() + "%");
			}

			// add order by
			sql.append(" ORDER BY t0.RELEASED_DATE ASC ");
			log.debug(LOCATION + ":: sql: " + sql.toString());
			// System.out.println("sql = " + sql.toString());
			// SearchResultBean result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sql.toString(), paramMap, ifoDocumentRowMapper, ifoDocument);
			SearchResult<IfoDocument> result = OracleDAOHelper.queryWithPagging(this.getDataSource(), sql.toString(), paramMap,
			        ifoDocumentRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	private RowMapper objResearchRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchIfoDocument objResearch = new SearchIfoDocument();
			objResearch.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objResearch.setTitle(rs.getString("TITLE"));
			objResearch.setContributor(rs.getString("CONTRIBUTOR"));
			objResearch.setReleasedDate(rs.getDate("RELEASED_DATE"));
			objResearch.setAbstract_(rs.getString("ABSTRACT"));
			objResearch.setFileName(rs.getString("FILE_NAME"));
			objResearch.setFilePath(rs.getString("FILE_PATH"));
			objResearch.setLocale(rs.getString("LOCALE"));
			objResearch.setDocumentId(new Long(rs.getLong("DOCUMENT_ID")));
			objResearch.setStrSymbol(rs.getString("SYMBOL"));
			return objResearch;
		}
	};

	/**
	 * @param ifoDocument
	 * @return
	 */
	public SearchResult<IfoDocument> searchResearchIfo(SearchIfoDocument ifoDocument, PagingInfo pagingInfo)
	        throws SystemException {
		final String LOCATION = "searchIfoDocument(SearchIfoDocument:" + ifoDocument + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoDocument == null) {
			throw new SystemException(LOCATION, "ifoDocument is NULL...");
		}
		SearchResult<IfoDocument> results = new SearchResult();
		try {
			StringBuffer sqlBuffer = new StringBuffer(
			        " select distinct d.document_id, d.title, d.contributor, d.author, d.released_date, ");
			sqlBuffer.append(" d.abstract, d.file_name, d.file_path,  d.locale, cd.company_id, cv.symbol ");
			sqlBuffer.append(" from ifo_document d  ");
			sqlBuffer.append(" left join ifo_company_document cd on d.document_id = cd.document_id (+) ");
			sqlBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cd.company_id=cv.company_id ");
			sqlBuffer.append(" where d.status=:STATUS ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", ifoDocument.getStatus());
			// params.add(new Parameter(":STATUS", ifoDocument.getStatus()));

			if (ifoDocument.getCompanyId() != null && ifoDocument.getCompanyId().longValue() != 0) {
				sqlBuffer.append(" and company_id= :COMPANY_ID");
				paramMap.put("COMPANY_ID", ifoDocument.getCompanyId());
				// params.add(new Parameter(":COMPANY_ID",
				// ifoDocument.getCompanyId()));
			}

			if (ifoDocument.getLocale() != null && ifoDocument.getLocale().trim().length() > 0) {
				sqlBuffer.append(" and d.locale= :LOCAL");
				paramMap.put("LOCAL", ifoDocument.getLocale());
				// params.add(new Parameter(":LOCAL", ifoDocument.getLocale()));
			}

			sqlBuffer.append(" order by released_date desc ");

			// results = OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap, objResearchRowMapper,
			// ifoDocument);
			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sqlBuffer.toString(), paramMap,
			        objResearchRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * @param arrIfoDocument
	 * @return
	 */
	public int deleteIfoDocumentByIds(IfoDocument[] arrIfoDocument) throws SystemException {
		final String LOCATION = "deleteFormApplicationByIds(arrIfoDocument:" + arrIfoDocument + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (arrIfoDocument == null) {
			throw new SystemException(LOCATION, "arrIfoDocument or status is NULL...");
		}
		try {
			if (arrIfoDocument.length == 0) {
				return 0;
			}

			// int intLength = arrIfoDocument.length;
			// Long[] ifoDocumentIds = new Long[intLength];
			// for (int i = 0; i < intLength; i++) {
			// if (arrIfoDocument[i] != null
			// && arrIfoDocument[i].getDocumentId() != null) {
			// ifoDocumentIds[i] = arrIfoDocument[i].getDocumentId();
			// } else {
			// ifoDocumentIds[i] = new Long(0);
			// }
			// }
			// final String HSQL = "delete IfoDocument where " + DOCUMENT_ID +
			// " in (:ifoDocumentIds)";
			// Query query = this.getSession().createQuery(HSQL);
			// query.setParameterList("ifoDocumentIds", ifoDocumentIds);
			// int intNum = query.executeUpdate();

			StringBuffer strBuf = new StringBuffer();
			int size = arrIfoDocument.length;
			for (int i = 0; i < size; i++) {
				if (arrIfoDocument[i] != null) {
					strBuf.append(strBuf.length() == 0 ? "" : ", ").append("'").append(arrIfoDocument[i]).append("'");
				}
			}

			String sql = "delete IfoDocument where document_id in (:ifoDocumentIds)";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ifoDocumentIds", strBuf.toString());

			int intNum = OracleDAOHelper.update(this.getDataSource(), sql, paramMap);

			log.debug(LOCATION + ":: END - intNum: " + intNum);
			return intNum;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	private final RowMapper objSSCFillingRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchIfoDocument objResearch = new SearchIfoDocument();
			objResearch.setTitle(rs.getString("TITLE"));
			objResearch.setContributor(rs.getString("CONTRIBUTOR"));
			objResearch.setReleasedDate(rs.getDate("RELEASED_DATE"));
			objResearch.setAbstract_(rs.getString("ABSTRACT"));
			objResearch.setFileName(rs.getString("FILE_NAME"));
			objResearch.setFilePath(rs.getString("FILE_PATH"));
			return objResearch;
		}
	};

	/**
	 * 
	 * @param ifoDocument
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<IfoDocument> searchSSCFilling(SearchIfoDocument ifoDocument, PagingInfo pagingInfo)
	        throws SystemException {
		final String LOCATION = "searchSSCFilling(SearchIfoDocument:" + ifoDocument + "; pagingInfo:" + pagingInfo + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoDocument == null) {
			throw new SystemException(LOCATION, "ifoDocument is NULL...");
		}
		SearchResult<IfoDocument> results = new SearchResult();
		try {
			StringBuffer sqlBuffer = new StringBuffer(" select distinct d.document_id, d.title, d.contributor, d.released_date, ");
			sqlBuffer.append(" d.abstract, d.file_name, d.file_path ");
			sqlBuffer.append(" from ifo_document d  ");
			sqlBuffer.append(" left join ifo_company_document cd on d.document_id = cd.document_id ");
			sqlBuffer.append(" left join IFO_COMPANY_NAME_VIEW cv on cd.company_id=cv.company_id ");
			sqlBuffer.append(" where d.status=:STATUS ");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("STATUS", ifoDocument.getStatus());

			if (ifoDocument.getCompanyId() != null && ifoDocument.getCompanyId().longValue() != 0) {
				sqlBuffer.append(" and cd.company_id= :COMPANY_ID");
				paramMap.put("COMPANY_ID", ifoDocument.getCompanyId());
			}

			if (ifoDocument.getStrSymbol() != null) {
				sqlBuffer.append(" and cv.symbol= :SYMBOL");
				paramMap.put("SYMBOL", ifoDocument.getStrSymbol());
			}

			if (ifoDocument.getLocale() != null && ifoDocument.getLocale().trim().length() > 0) {
				sqlBuffer.append(" and d.locale= :LOCAL");
				paramMap.put("LOCAL", ifoDocument.getLocale());
			}

			if (ifoDocument.getDocumentType() != null && ifoDocument.getDocumentType().trim().length() > 0) {
				sqlBuffer.append(" and d.DOCUMENT_TYPE= :DOCUMENT_TYPE");
				paramMap.put("DOCUMENT_TYPE", ifoDocument.getDocumentType());
			}

			sqlBuffer.append(" order by released_date desc ");

			results = OracleDAOHelper.queryWithPagging(this.getDataSource(), sqlBuffer.toString(), paramMap,
			        objSSCFillingRowMapper, pagingInfo);

			log.debug(LOCATION + ":: END : result " + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}