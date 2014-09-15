package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoEstimateView;
import vn.com.vndirect.domain.IfoValuationMeasuresView;
import vn.com.vndirect.domain.IfoValuationMeasuresViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoValuationMeasuresView.
 * 
 * @see vn.com.vndirect.domain.IfoValuationMeasuresView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoValuationMeasuresViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoValuationMeasuresViewDAO.class);

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoValuationMeasuresViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoValuationMeasuresView obj = new IfoValuationMeasuresView();
	// IfoValuationMeasuresViewId objId = new IfoValuationMeasuresViewId();
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
	// objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
	// objId.setItemCode(new Long(rs.getLong("ITEM_CODE")));
	// objId.setItemName(rs.getString("ITEM_NAME"));
	// objId.setNumericValue(Utility.getDouble(rs.getDouble("NUMERIC_VALUE")));
	// objId.setTextValue(rs.getString("TEXT_VALUE"));
	// objId.setLocale(rs.getString("LOCALE"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	private final RowMapper ifoValuationMeasuresViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoValuationMeasuresView obj = new IfoValuationMeasuresView();
			IfoValuationMeasuresViewId objId = new IfoValuationMeasuresViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setDisplayLevel(new Long(rs.getLong("DISPLAY_LEVEL")));
			objId.setDisplayOrder(new Long(rs.getLong("DISPLAY_ORDER")));
			objId.setItemCode(new Long(rs.getLong("ITEM_CODE")));
			objId.setItemName(rs.getString("ITEM_NAME"));
			objId.setNumericValue(VNDirectWebUtilities.getDouble(rs.getDouble("NUMERIC_VALUE")));
			objId.setTextValue(rs.getString("TEXT_VALUE"));
			objId.setLocale(rs.getString("LOCALE"));

			obj.setId(objId);
			return obj;
		}
	};
	private final RowMapper ifoEstimateViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoEstimateView obj = new IfoEstimateView();
			obj.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			obj.setFiscalDate(rs.getDate("FISCAL_DATE"));
			obj.setItem21001(VNDirectWebUtilities.getDouble(rs.getDouble("L_21001")));
			obj.setItem23800(VNDirectWebUtilities.getDouble(rs.getDouble("L_23800")));
			obj.setItem23000(VNDirectWebUtilities.getDouble(rs.getDouble("L_23000")));
			obj.setItem400001(VNDirectWebUtilities.getDouble(rs.getDouble("L_400001")));
			obj.setItem40000(VNDirectWebUtilities.getDouble(rs.getDouble("L_40000")));
			obj.setItem40001(VNDirectWebUtilities.getDouble(rs.getDouble("L_40001")));
			obj.setItem51021(VNDirectWebUtilities.getDouble(rs.getDouble("L_51021")));

			obj.setL_analyst_21001(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_21001")));
			obj.setL_analyst_23800(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_23800")));
			obj.setL_analyst_23000(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_23000")));
			obj.setL_analyst_40000(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_40000")));
			obj.setL_analyst_40001(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_40001")));
			obj.setL_analyst_51021(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_51021")));
			obj.setL_analyst_400001(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_L_400001")));

			obj.setL_actual_21001(VNDirectWebUtilities.getDouble(rs.getDouble("L_ACTUAL_21001")));
			obj.setL_actual_23800(VNDirectWebUtilities.getDouble(rs.getDouble("L_ACTUAL_23800")));
			obj.setL_actual_23000(VNDirectWebUtilities.getDouble(rs.getDouble("L_ACTUAL_23000")));
			obj.setL_actual_400001(VNDirectWebUtilities.getDouble(rs.getDouble("L_ACTUAL_400001")));

			obj.setPct_result_21001(VNDirectWebUtilities.getDouble(rs.getDouble("PCT_RESULT_21001")));
			obj.setPct_result_23800(VNDirectWebUtilities.getDouble(rs.getDouble("PCT_RESULT_23800")));
			obj.setPct_result_23000(VNDirectWebUtilities.getDouble(rs.getDouble("PCT_RESULT_23000")));
			obj.setPct_result_400001(VNDirectWebUtilities.getDouble(rs.getDouble("PCT_RESULT_400001")));

			obj.setAnalyst_pct_result_21001(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_PCT_RESULT_21001")));
			obj.setAnalyst_pct_result_23800(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_PCT_RESULT_23800")));
			obj.setAnalyst_pct_result_23000(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_PCT_RESULT_23000")));
			obj.setAnalyst_pct_result_400001(VNDirectWebUtilities.getDouble(rs.getDouble("ANALYST_PCT_RESULT_400001")));

			return obj;
		}
	};

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public List<IfoValuationMeasuresView> findByCompanyId(IfoValuationMeasuresViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoValuationMeasuresView is NULL...");
		}
		try {
			final StringBuffer sqlBuffer = new StringBuffer(
			        "SELECT ifoValuationMeasuresView.* "
			                + " FROM IFO_VALUATION_MEASURES_VIEW ifoValuationMeasuresView "
			                + " WHERE (ifoValuationMeasuresView.COMPANY_ID = :COMPANY_ID) "
			                + " AND ((ifoValuationMeasuresView.display_level = 0)"
			                + " OR ((ifoValuationMeasuresView.numeric_value is not null) AND (ifoValuationMeasuresView.numeric_value <> 0))) ");

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());

			if (id.getLocale() != null && id.getLocale().trim().length() > 0) {
				sqlBuffer.append(" AND (upper(ifoValuationMeasuresView.LOCALE)= :LOCAL)");
				paramMap.put("LOCAL", id.getLocale().toUpperCase());
				// params.add(new Parameter(":LOCAL",
				// id.getLocale().toUpperCase()));
			}

			sqlBuffer.append(" ORDER by ifoValuationMeasuresView.DISPLAY_ORDER ASC ");

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(sqlBuffer.toString());
			//
			// Collection obj = stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoValuationMeasuresViewProcessor);

			// SearchResult result =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap,
			// ifoValuationMeasuresViewRowMapper, null);
			SearchResult<IfoValuationMeasuresView> result = OracleDAOHelper.query(this.getDataSource(), sqlBuffer.toString(),
			        paramMap, ifoValuationMeasuresViewRowMapper);

			log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoEstimateView getIfoEstimateView(IfoEstimateView ifoEstimateView) throws SystemException {
		final String LOCATION = "getIfoEstimateView(IfoEstimateView:" + ifoEstimateView + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (ifoEstimateView == null) {
			throw new SystemException(LOCATION, "IfoEstimateView is NULL...");
		}
		try {
			StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM IFO_ESTIMATE_VIEW A "
			        + "WHERE COMPANY_ID = :COMPANY_ID AND TO_CHAR(FISCAL_DATE,'yyyy') = :FISCAL_DATE");

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", ifoEstimateView.getCompanyId());
			paramMap.put("FISCAL_DATE", ifoEstimateView.getSearchDate());
			IfoEstimateView result = (IfoEstimateView) OracleDAOHelper.querySingle(this.getDataSource(), sqlBuffer.toString(),
			        paramMap, ifoEstimateViewRowMapper);
			result = result == null ? new IfoEstimateView() : result;

			sqlBuffer = new StringBuffer("SELECT ITEM_CODE, ITEM_NAME FROM IFO_ITEM_CODE "
			        + "WHERE ITEM_CODE IN (21001,23800,23000,400001,40000,40001,51021) " + "AND UPPER(LOCALE) = :LOCALE");
			paramMap = new HashMap<String, Object>();
			paramMap.put("LOCALE", ifoEstimateView.getLocale().toUpperCase());
			// SearchResult result1 =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// sqlBuffer.toString(), paramMap, ifoItemValueMapper, null);
			SearchResult result1 = OracleDAOHelper
			        .query(this.getDataSource(), sqlBuffer.toString(), paramMap, ifoItemValueMapper);
			if (result1 != null && result1.size() > 0) {
				ItemValue temOjb;
				for (int i = 0; i < result1.size(); i++) {
					temOjb = (ItemValue) result1.get(i);
					if (temOjb.getItem() == 21001) {
						result.setLabel21001(temOjb.getLabel());
					} else if (temOjb.getItem() == 23800) {
						result.setLabel23800(temOjb.getLabel());
					} else if (temOjb.getItem() == 23000) {
						result.setLabel23000(temOjb.getLabel());
					} else if (temOjb.getItem() == 400001) {
						result.setLabel400001(temOjb.getLabel());
					} else if (temOjb.getItem() == 40000) {
						result.setLabel40000(temOjb.getLabel());
					} else if (temOjb.getItem() == 40001) {
						result.setLabel40001(temOjb.getLabel());
					} else if (temOjb.getItem() == 51021) {
						result.setLabel51021(temOjb.getLabel());
					}
				}
			}
			log.debug(LOCATION + ":: END");
			return result;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	private final RowMapper ifoItemValueMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ItemValue obj = new ItemValue();
			obj.setItem(rs.getLong("ITEM_CODE"));
			obj.setLabel(rs.getString("ITEM_NAME"));
			return obj;
		}
	};

	private final RowMapper fiscalYearMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("FISCAL_YEAR");
		}
	};

	public List fiscalYearList(IfoEstimateView ifoEstimateView) throws SystemException {
		final String LOCATION = "fiscalYearList(criteriaObject:" + ifoEstimateView + " )";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			Map paramMap = new HashMap();
			StringBuffer sql = new StringBuffer("SELECT DISTINCT TO_CHAR(FISCAL_DATE, 'YYYY') AS FISCAL_YEAR "
			        + "FROM IFO_ESTIMATE_VIEW WHERE COMPANY_ID = :COMPANY_ID ORDER BY FISCAL_YEAR DESC");
			paramMap.put("COMPANY_ID", ifoEstimateView.getCompanyId());
			SearchResult result = OracleDAOHelper.query(this.getDataSource(), sql.toString(), paramMap, fiscalYearMapper);

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END - result: " + result.size());
			return result;
		} catch (RuntimeException sysex) {
			throw new SystemException(LOCATION, sysex);
		} catch (Exception ex) {
			throw new SystemException(LOCATION, ex);
		}
	}

	class ItemValue {
		private String label;
		private long item;

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public long getItem() {
			return item;
		}

		public void setItem(long item) {
			this.item = item;
		}

	}
}