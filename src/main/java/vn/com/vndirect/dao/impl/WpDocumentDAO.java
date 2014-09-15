package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("unchecked")
public class WpDocumentDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(WpDocumentDAO.class);

	public SearchResult<WpDocument> searchDocumentProduct(WpDocument wpDocument, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchDocumentProduct(wpDocument:" + wpDocument + ", pagingInfo:" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		SearchResult<WpDocument> searchResult;
		if (wpDocument != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer SEARCH_SELECT = new StringBuffer();
			StringBuffer SEARCH_FROM = new StringBuffer();
			StringBuffer SEARCH_WHERE = new StringBuffer();
			SEARCH_SELECT.append("SELECT wd.*, wp.PRODUCT_ID, wp.PRODUCT_CODE, wp.PRODUCT_NAME, wp.LANGUAGE ");
			SEARCH_FROM.append("FROM WP_DOCUMENT wd, WP_PRODUCT wp ");
			SEARCH_WHERE
			        .append("WHERE wd.PRODUCT_ID = wp.PRODUCT_ID AND (wd.IS_DELETED IS NULL OR wd.IS_DELETED != 1) AND (wp.IS_DELETED IS NULL OR wp.IS_DELETED != 1)");

			if (wpDocument.getDocumentId() != null && wpDocument.getDocumentId().longValue() > 0) { // search
				                                                                                    // with
				                                                                                    // documentId
				paramMap.put("DOCUMENT_ID", wpDocument.getDocumentId());
				SEARCH_WHERE.append("AND wd.DOCUMENT_ID = :DOCUMENT_ID ");
			}

			if (StringUtils.isNotBlank(wpDocument.getDocumentName())) { // search
				                                                        // with
				                                                        // documentName
				paramMap.put("DOCUMENT_NAME", wpDocument.getDocumentName().toUpperCase());
				SEARCH_WHERE.append("AND UPPER(wd.DOCUMENT_NAME) = :DOCUMENT_NAME ");
			}

			if (StringUtils.isNotBlank(wpDocument.getDocumentTitle())) { // search
				                                                         // with
				                                                         // documentTitle
				paramMap.put("DOCUMENT_TITLE", wpDocument.getDocumentTitle());
				SEARCH_WHERE.append("AND wd.DOCUMENT_TITLE = :DOCUMENT_TITLE ");
			}

			if (wpDocument.getProductId() != null && wpDocument.getProductId().longValue() > 0) { // search
				                                                                                  // with
				                                                                                  // productId
				paramMap.put("PRODUCT_ID", wpDocument.getProductId());
				SEARCH_WHERE.append("AND wd.PRODUCT_ID = :PRODUCT_ID ");
			}

			if (StringUtils.isNotBlank(wpDocument.getDocumentType())) { // search
				                                                        // with
				                                                        // documentType
				paramMap.put("DOCUMENT_TYPE", wpDocument.getDocumentType());
				SEARCH_WHERE.append("AND wd.DOCUMENT_TYPE = :DOCUMENT_TYPE ");
			}

			if (StringUtils.isNotBlank(wpDocument.getLanguage())) { // search
				                                                    // with
				                                                    // Language
				paramMap.put("LANGUAGE", wpDocument.getLanguage().toUpperCase());
				SEARCH_WHERE.append("AND UPPER(wp.LANGUAGE) = :LANGUAGE ");
			}

			WpProduct wpProduct = wpDocument.getWpProduct();
			if (wpProduct != null) {
				if (wpProduct.getWpProductGroup() != null && wpProduct.getWpProductGroup().getProductGroupType() != null) {
					paramMap.put("PRODUCT_GROUP_TYPE", wpProduct.getWpProductGroup().getProductGroupType());
					SEARCH_FROM.append(", WP_PRODUCT_GROUP wpg ");
					SEARCH_WHERE
					        .append("AND wp.PRODUCT_GROUP_ID = wpg.PRODUCT_GROUP_ID AND wpg.PRODUCT_GROUP_TYPE = :PRODUCT_GROUP_TYPE ");
				}
				if (StringUtils.isNotBlank(wpProduct.getProductCode())) {
					paramMap.put("PRODUCT_CODE", wpProduct.getProductCode().toUpperCase());
					SEARCH_WHERE.append("AND UPPER(wp.PRODUCT_CODE) = :PRODUCT_CODE ");
				}
			}
			SEARCH_WHERE.append("ORDER BY wp.PRODUCT_NAME, wd.DOCUMENT_TITLE");

			SEARCH_SELECT = SEARCH_SELECT.append(SEARCH_FROM).append(SEARCH_WHERE);

			searchResult = OracleDAOHelper.query(this.getDataSource(), SEARCH_SELECT.toString(), paramMap,
			        searchDocumentProductRowMapper);
		} else {
			searchResult = new SearchResult<WpDocument>();
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - result" + searchResult);

		return searchResult;
	}

	public SearchResult<WpDocument> getDocuments(WpDocument wpDocument) throws SystemException {
		final String LOCATION = "getDocuments(wpDocument:" + wpDocument + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		SearchResult<WpDocument> searchResult;
		if (wpDocument != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuffer documentSql = new StringBuffer();
			documentSql.append("SELECT wd.* FROM WP_DOCUMENT wd " + "WHERE (wd.IS_DELETED IS NULL OR wd.IS_DELETED != 1) ");

			if (wpDocument.getDocumentId() != null) { // search with documentId
				paramMap.put("DOCUMENT_ID", wpDocument.getDocumentId());
				documentSql.append("AND wd.DOCUMENT_ID = :DOCUMENT_ID ");
			}

			if (wpDocument.getDocumentName() != null) { // search with
				                                        // documentName
				paramMap.put("DOCUMENT_NAME", wpDocument.getDocumentName());
				documentSql.append("AND wd.DOCUMENT_NAME = :DOCUMENT_NAME ");
			}

			if (wpDocument.getDocumentTitle() != null) { // search with
				                                         // documentTitle
				paramMap.put("DOCUMENT_TITLE", wpDocument.getDocumentTitle());
				documentSql.append("AND wd.DOCUMENT_TITLE = :DOCUMENT_TITLE ");
			}

			if (wpDocument.getDocumentType() != null) { // search with
				                                        // documentType
				paramMap.put("DOCUMENT_TYPE", wpDocument.getDocumentType());
				documentSql.append("AND wd.DOCUMENT_TYPE = :DOCUMENT_TYPE ");
			}

			if (StringUtils.isNotBlank(wpDocument.getLanguage())) { // search
				                                                    // with
				                                                    // Language
				paramMap.put("LANGUAGE", wpDocument.getLanguage());
				documentSql.append("AND wd.LANGUAGE = :LANGUAGE ");
			}

			documentSql.append("ORDER BY wd.DOCUMENT_ID");
			searchResult = OracleDAOHelper.query(this.getDataSource(), documentSql.toString(), paramMap, getDocumentRowMapper);
		} else {
			searchResult = new SearchResult<WpDocument>();
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - result" + searchResult);

		return searchResult;
	}

	/**
	 * create objec documentRowMapper to using in querys for search
	 */
	@SuppressWarnings("rawtypes")
	private RowMapper searchDocumentProductRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			WpDocument obj = new WpDocument();
			// set value for properties of WP_DOCUMENT table
			obj.setDocumentId(arg0.getLong("DOCUMENT_ID"));
			obj.setIsDeleted(arg0.getBoolean("IS_DELETED"));
			obj.setCreatedBy(arg0.getString("CREATED_BY"));
			obj.setCreatedDate(arg0.getDate("CREATED_DATE"));
			obj.setModifiedBy(arg0.getString("MODIFIED_BY"));
			obj.setModifiedDate(arg0.getDate("MODIFIED_DATE"));
			obj.setDocumentName(arg0.getString("DOCUMENT_NAME"));
			obj.setDocumentUri(arg0.getString("DOCUMENT_URI"));
			obj.setDocumentThumbnailUri(arg0.getString("DOCUMENT_THUMBNAIL_URI"));
			obj.setDocumentType(arg0.getString("DOCUMENT_TYPE"));
			obj.setDocumentTitle(arg0.getString("DOCUMENT_TITLE"));
			obj.setProductId(arg0.getLong("PRODUCT_ID"));

			try {
				WpProduct wpProduct = new WpProduct();
				wpProduct.setProductId(arg0.getLong("PRODUCT_ID"));
				wpProduct.setProductCode(arg0.getString("PRODUCT_CODE"));
				wpProduct.setProductName(arg0.getString("PRODUCT_NAME"));
				wpProduct.setLanguage(arg0.getString("LANGUAGE"));
				obj.setWpProduct(wpProduct);
			} catch (Exception e) {
			}
			return obj;
		}
	};

	@SuppressWarnings("rawtypes")
	private RowMapper getDocumentRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			WpDocument obj = new WpDocument();
			// set value for properties of WP_DOCUMENT table
			obj.setDocumentId(arg0.getLong("DOCUMENT_ID"));
			obj.setIsDeleted(arg0.getBoolean("IS_DELETED"));
			obj.setCreatedBy(arg0.getString("CREATED_BY"));
			obj.setCreatedDate(arg0.getDate("CREATED_DATE"));
			obj.setModifiedBy(arg0.getString("MODIFIED_BY"));
			obj.setModifiedDate(arg0.getDate("MODIFIED_DATE"));
			obj.setDocumentName(arg0.getString("DOCUMENT_NAME"));
			obj.setDocumentTitle(arg0.getString("DOCUMENT_TITLE"));
			obj.setDocumentUri(arg0.getString("DOCUMENT_URI"));
			obj.setDocumentThumbnailUri(arg0.getString("DOCUMENT_THUMBNAIL_URI"));
			obj.setDocumentType(arg0.getString("DOCUMENT_TYPE"));
			obj.setProductId(arg0.getLong("PRODUCT_ID"));

			return obj;
		}
	};
}