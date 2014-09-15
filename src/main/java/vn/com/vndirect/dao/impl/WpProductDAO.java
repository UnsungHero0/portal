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
import vn.com.vndirect.domain.WpProduct;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 *
 */
@SuppressWarnings("unchecked")
public class WpProductDAO extends BaseDAOImpl {
	private static final Log log = LogFactory.getLog(WpProductDAO.class);

	/**
	 * @param wpProduct
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<WpProduct> searchProduct(WpProduct wpProduct, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchProduct(wpProduct:" + wpProduct + ", pagingInfo: )" + pagingInfo + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		SearchResult<WpProduct> result;
		if (wpProduct != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			StringBuilder SEARCH_PRODUCT = new StringBuilder();
			SEARCH_PRODUCT.append("SELECT wp.* FROM WP_PRODUCT wp WHERE (wp.IS_DELETED is NULL OR wp.IS_DELETED = 0) ");

			if (wpProduct.getProductId() != null) {
				paramMap.put("PRODUCT_ID", wpProduct.getProductId());
				SEARCH_PRODUCT.append("AND wp.PRODUCT_ID = :PRODUCT_ID ");
			}

			if (wpProduct.getProductGroupId() != null) {
				paramMap.put("PRODUCT_GROUP_ID", wpProduct.getProductGroupId());
				SEARCH_PRODUCT.append("AND wp.PRODUCT_GROUP_ID = :PRODUCT_GROUP_ID ");
			}

			if (!StringUtils.isBlank(wpProduct.getProductCode())) {
				paramMap.put("PRODUCT_CODE", wpProduct.getProductCode());
				SEARCH_PRODUCT.append("AND wp.PRODUCT_CODE = :PRODUCT_CODE ");
			}

			if (!StringUtils.isBlank(wpProduct.getProductName())) {
				paramMap.put("PRODUCT_NAME", wpProduct.getProductName() + "%");
				SEARCH_PRODUCT.append("AND wp.PRODUCT_NAME LIKE :PRODUCT_NAME ");
			}

			if (!StringUtils.isBlank(wpProduct.getLanguage())) {
				paramMap.put("LANGUAGE", wpProduct.getLanguage().toUpperCase());
				SEARCH_PRODUCT.append("AND UPPER(wp.LANGUAGE) LIKE :LANGUAGE ");
			}

			SEARCH_PRODUCT.append("ORDER BY wp.PRODUCT_NAME, wp.PRODUCT_CODE "); // order

			result = OracleDAOHelper.query(this.getDataSource(), SEARCH_PRODUCT.toString(), paramMap, productRowMapper,
			        pagingInfo);
		} else {
			result = new SearchResult<WpProduct>();
		}

		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - result" + result);
		return result;
	}

	/**
	 * create objec productRowMapper to using in querys for search
	 */
	@SuppressWarnings("rawtypes")
	private RowMapper productRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			WpProduct obj = new WpProduct();
			// set value for properties of WP_PRODUCT table
			obj.setProductId(arg0.getLong("PRODUCT_ID"));
			obj.setProductGroupId(arg0.getLong("PRODUCT_GROUP_ID"));
			obj.setProductCode(arg0.getString("PRODUCT_CODE"));
			obj.setProductName(arg0.getString("PRODUCT_NAME"));
			try {
				obj.setProductOverview(OracleDAOHelper.getClobColumn(arg0, "PRODUCT_OVERVIEW"));
			} catch (Exception e) {
			}
			obj.setLanguage(arg0.getString("LANGUAGE"));
			obj.setIsDeleted(arg0.getBoolean("IS_DELETED"));
			// obj.setCreatedBy(arg0.getString("CREATED_BY"));
			// obj.setCreatedDate(arg0.getDate("CREATED_DATE"));
			// obj.setModifiedBy(arg0.getString("MODIFIED_BY"));
			// obj.setModifiedDate(arg0.getDate("MODIFIED_DATE"));

			return obj;
		}
	};

}