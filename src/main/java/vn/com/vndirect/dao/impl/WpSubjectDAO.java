package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.WpSubject;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 *
 */
@SuppressWarnings("unchecked")
public class WpSubjectDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(WpSubjectDAO.class);

	public WpSubject getSubjectById(Long subjectId) throws SystemException {
		final String LOCATION = "getSubjectById(subjectId:" + subjectId + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		WpSubject result = null;
		if (subjectId != null && subjectId.longValue() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuilder SEARCH_SUBJECT = new StringBuilder();
			SEARCH_SUBJECT.append("SELECT ws.* FROM WP_SUBJECT ws ");
			SEARCH_SUBJECT.append("WHERE ws.SUBJECT_ID = :SUBJECT_ID AND (ws.IS_DELETED is NULL OR ws.IS_DELETED = 0) ");

			paramMap.put("SUBJECT_ID", subjectId);

			result = (WpSubject) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SUBJECT.toString(), paramMap,
			        subjectRowMapper);
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - result" + result);
		return result;
	}

	/**
	 * @param wpSubject
	 * @param pagingInfo
	 * @return
	 */
	public SearchResult<WpSubject> searchSubject(WpSubject wpSubject, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchSubject(wpSubject:" + wpSubject + ", pagingInfo: " + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		SearchResult<WpSubject> result;
		if (wpSubject != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuilder SEARCH_SUBJECT = new StringBuilder();
			SEARCH_SUBJECT.append("SELECT ws.* FROM WP_SUBJECT ws, WP_PRODUCT wp ");
			SEARCH_SUBJECT.append("WHERE ws.PRODUCT_ID = wp.PRODUCT_ID AND (ws.IS_DELETED is NULL OR ws.IS_DELETED = 0) ");

			if (wpSubject.getSubjectId() != null) {
				paramMap.put("SUBJECT_ID", wpSubject.getSubjectId());
				SEARCH_SUBJECT.append("AND ws.SUBJECT_ID = :SUBJECT_ID ");
			}

			if (wpSubject.getProductId() != null) {
				paramMap.put("PRODUCT_ID", wpSubject.getProductId());
				SEARCH_SUBJECT.append("AND ws.PRODUCT_ID = :PRODUCT_ID ");
			}

			if (!StringUtils.isBlank(wpSubject.getLanguage())) {
				paramMap.put("LANGUAGE", wpSubject.getLanguage().toUpperCase());
				SEARCH_SUBJECT.append("AND UPPER(ws.LANGUAGE) = :LANGUAGE ");
			}

			if (!StringUtils.isBlank(wpSubject.getSubjectCode())) {
				paramMap.put("SUBJECT_CODE", wpSubject.getSubjectCode() + "%");
				SEARCH_SUBJECT.append("AND ws.SUBJECT_CODE LIKE :SUBJECT_CODE ");
			}

			if (!StringUtils.isBlank(wpSubject.getSubjectName())) {
				paramMap.put("SUBJECT_NAME", wpSubject.getSubjectName() + "%");
				SEARCH_SUBJECT.append("AND ws.SUBJECT_NAME LIKE :SUBJECT_NAME ");
			}

			if (wpSubject.getWpProduct() != null && !StringUtils.isBlank(wpSubject.getWpProduct().getProductCode())) { // search
																													   // subject
																													   // with
																													   // name
				paramMap.put("PRODUCT_CODE", wpSubject.getWpProduct().getProductCode());
				SEARCH_SUBJECT.append("AND wp.PRODUCT_CODE = :PRODUCT_CODE ");
			}

			SEARCH_SUBJECT.append("ORDER BY wp.PRODUCT_NAME, ws.SUBJECT_NAME");
			result = OracleDAOHelper.query(this.getDataSource(), SEARCH_SUBJECT.toString(), paramMap, subjectRowMapper,
			        pagingInfo);
		} else {
			result = new SearchResult<WpSubject>();
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - result" + result);
		return result;
	}

	/**
	 * create objec subjectRowMapper to using in querys for search
	 */
	@SuppressWarnings("rawtypes")
    private RowMapper subjectRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			WpSubject obj = new WpSubject();
			// set value for properties of WP_SUBJECT table
			obj.setSubjectId(arg0.getLong("SUBJECT_ID"));
			obj.setProductId(arg0.getLong("PRODUCT_ID"));
			obj.setSubjectCode(arg0.getString("SUBJECT_CODE"));
			obj.setSubjectName(arg0.getString("SUBJECT_NAME"));
			try {
				obj.setSubjectContent(OracleDAOHelper.getClobColumn(arg0, "SUBJECT_CONTENT"));
				obj.setSubjectOverview(OracleDAOHelper.getClobColumn(arg0, "SUBJECT_OVERVIEW"));
			} catch (Exception e) {
			}
			obj.setIsDeleted(arg0.getBoolean("IS_DELETED"));
			// obj.setCreatedBy(arg0.getString("CREATED_BY"));
			// obj.setCreatedDate(arg0.getDate("CREATED_DATE"));
			// obj.setModifiedBy(arg0.getString("MODIFIED_BY"));
			// obj.setModifiedDate(arg0.getDate("MODIFIED_DATE"));
			obj.setLanguage(arg0.getString("LANGUAGE"));
			obj.setIsPageStatic(arg0.getBoolean("IS_PAGE_STATIC"));

			return obj;
		}
	};
}