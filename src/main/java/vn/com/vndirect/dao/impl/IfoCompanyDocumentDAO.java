package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyDocument;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanyDocument.
 * 
 * @see vn.com.vndirect.domain.IfoCompanyDocument
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoCompanyDocumentDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanyDocumentDAO.class);

	// property constants
	public static final String CREATED_BY = "createdBy";

	public static final String MODIFIED_BY = "modifiedBy";

	public void attachDirty(IfoCompanyDocument ifoCompanyDocument) throws SystemException {
		final String LOCATION = "attachDirty(ifoCompanyDocument:" + ifoCompanyDocument + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyDocument == null) {
			throw new SystemException(LOCATION, "ifoCompanyDocument is NULL...");
		}
		String INSERT_SQL = "INSERT INTO IFO_COMPANY_DOCUMENT " + "(COMPANY_ID, DOCUMENT_ID, CREATED_DATE, CREATED_BY) "
		        + " VALUES (:COMPANY_ID, :DOCUMENT_ID, :CREATED_DATE, :CREATED_BY) ";

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			if (ifoCompanyDocument.getIfoCompany() != null
			        && VNDirectWebUtilities.getLongValue(ifoCompanyDocument.getIfoCompany().getCompanyId()) > 0
			        && ifoCompanyDocument.getIfoDocument() != null
			        && VNDirectWebUtilities.getLongValue(ifoCompanyDocument.getIfoDocument().getDocumentId()) > 0) {
				paramMap.put("COMPANY_ID", ifoCompanyDocument.getIfoCompany().getCompanyId());
				paramMap.put("DOCUMENT_ID", ifoCompanyDocument.getIfoDocument().getDocumentId());
				if (ifoCompanyDocument.getCreatedDate() != null) {
					paramMap.put("CREATED_DATE", ifoCompanyDocument.getCreatedDate());
				} else {
					paramMap.put("CREATED_DATE", new Date());
				}
				paramMap.put("CREATED_BY", ifoCompanyDocument.getCreatedBy());

				int intNum = OracleDAOHelper.update(this.getDataSource(), INSERT_SQL, paramMap);
				log.debug(LOCATION + "::END intNum" + intNum);
			}
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	public static IfoCompanyDocumentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCompanyDocumentDAO) ctx.getBean("IfoCompanyDocumentDAO");
	}

	/**
	 * 
	 * @param arrIfoNews
	 * @throws SystemException
	 */

	public void deleteDocumentByIds(IfoDocument[] arrIfoDoc) throws SystemException {
		final String LOCATION = "deleteDocumentByIds(arrIfoDoc:" + arrIfoDoc + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (arrIfoDoc == null) {
			throw new SystemException(LOCATION, "arrIfoDoc or status is NULL...");
		}
		try {
			if (arrIfoDoc.length == 0)
				return;

			for (int i = 0; i < arrIfoDoc.length; i++) {
				if (arrIfoDoc[i] != null && arrIfoDoc[i].getDocumentId() != null) {
					this.deleteDocSymbol(arrIfoDoc[i].getDocumentId());
				}
			}

			log.debug(LOCATION + ":: END ");
			return;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

	}

	// private final ResultSetProcessor objCompany = new ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoCompanyNameViewId objCompany = new IfoCompanyNameViewId();
	// objCompany.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objCompany.setSymbol(rs.getString("SYMBOL"));
	// return objCompany;
	// }
	// };

	private final RowMapper objCompanyRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyNameViewId objCompany = new IfoCompanyNameViewId();
			objCompany.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objCompany.setSymbol(rs.getString("SYMBOL"));
			return objCompany;
		}
	};

	/**
	 * @param documentId
	 * @return
	 */
	public Set selectSymbol(Long documentId) throws SystemException {
		final String LOCATION = "selectSymbol(documentId:" + documentId + ")";

		log.debug(LOCATION + ":: BEGIN");
		Set result = new HashSet();
		String SEARCH_SQL = "select SYMBOL, b.company_id from IFO_COMPANY_NAME_VIEW a "
		        + "join IFO_COMPANY_DOCUMENT  b on a.company_id = b.company_id and b.document_id=:DOCUMENT_ID";

		try {
			// List params = new ArrayList();
			// params.add(new Parameter(":DOCUMENT_ID", documentId));
			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(SEARCH_SQL);
			// result = (Set) stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , new HashSet()
			// , objCompany);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("DOCUMENT_ID", documentId);

			// SearchResult results =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, objCompanyRowMapper, null);
			SearchResult results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap, objCompanyRowMapper);

			result = new HashSet(results);
		} catch (Exception e) {
			log.error(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : result " + result.size());
		return result;
	}

	/**
	 * @param documentId
	 * @return
	 */
	public int deleteDocSymbol(Long documentId) throws SystemException {
		final String LOCATION = "deleteDocSymbol(documentId:" + documentId + ")";

		log.debug(LOCATION + ":: BEGIN");
		int results = -1;
		String DELETE_SQL = "DELETE FROM IFO_COMPANY_DOCUMENT WHERE IFO_COMPANY_DOCUMENT.DOCUMENT_ID=:DOCUMENT_ID";

		try {
			// List params = new ArrayList();
			// params.add(new Parameter(":DOCUMENT_ID", documentId));
			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(DELETE_SQL);
			// results = stat.executeUpdate(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()]));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("DOCUMENT_ID", documentId);

			results = OracleDAOHelper.update(this.getDataSource(), DELETE_SQL, paramMap);

		} catch (Exception e) {
			log.error(LOCATION, e);
		}
		log.debug(LOCATION + ":: END : results " + results);
		return results;
	}
}