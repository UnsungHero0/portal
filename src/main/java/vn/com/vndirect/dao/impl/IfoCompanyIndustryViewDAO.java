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
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.IfoCompanyIndustryViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanyIndustryView.
 * 
 * @see vn.com.vndirect.domain.IfoCompanyIndustryView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IfoCompanyIndustryViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanyIndustryViewDAO.class);

	private final RowMapper ifoCompanyIndustryViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyIndustryView obj = new IfoCompanyIndustryView();
			IfoCompanyIndustryViewId objId = new IfoCompanyIndustryViewId();

			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setIndustryName(rs.getString("INDUSTRY_NAME"));
			objId.setSectorName(rs.getString("SECTOR_NAME"));

			objId.setIndustryCode(rs.getString("INDUSTRY_CODE"));
			objId.setSectorCode(rs.getString("SECTOR_CODE"));

			objId.setIndustryGroupCode(rs.getString("INDUSTRY_GROUP_CODE"));
			objId.setSectorGroupCode(rs.getString("SECTOR_GROUP_CODE"));

			try {
				objId.setIndustryGroupName(rs.getString("INDUSTRY_GROUP_NAME"));
			} catch (Exception e) {
				objId.setIndustryGroupName("");
			}

			try {
				objId.setSectorGroupName(rs.getString("SECTOR_GROUP_NAME"));
			} catch (Exception e) {
				objId.setSectorGroupName("");
			}

			objId.setLocale(rs.getString("LOCALE"));

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws SystemException
	 */
	public List<IfoCompanyIndustryView> findByCompanyId(long companyId, String locale) throws SystemException {
		final String LOCATION = "findByCompanyId(companyId:" + companyId + ")";
		log.debug(LOCATION + ":: BEGIN");
		try {
			String SEARCH_SQL = "SELECT distinct ifoCompanyIndustryView.* FROM IFO_COMPANY_INDUSTRY_VIEW ifoCompanyIndustryView WHERE ifoCompanyIndustryView.COMPANY_ID = :COMPANY_ID ";

			// List params = new ArrayList();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// add COMPANY_ID
			// params.add(new Parameter(":COMPANY_ID", companyId));
			paramMap.put("COMPANY_ID", VNDirectWebUtilities.getLong(companyId));

			// add LOCALE
			if (locale != null) {
				SEARCH_SQL = SEARCH_SQL + " AND UPPER(ifoCompanyIndustryView.LOCALE) = :LOCALE ";
				// params.add(new Parameter(":LOCALE", locale == null ? "" :
				// locale.toUpperCase()));
				paramMap.put("LOCALE", locale == null ? "" : locale.toUpperCase());
			}

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(SEARCH_SQL);
			//
			// List results = (List)stat.getList(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoCompanyIndustryViewProcessor);

			// SearchResult results =
			// OracleDAOHelper.queryWithBaseBean(this.getDataSource(),
			// SEARCH_SQL, paramMap, ifoCompanyIndustryViewRowMapper, null);
			SearchResult<IfoCompanyIndustryView> results = OracleDAOHelper.query(this.getDataSource(), SEARCH_SQL, paramMap,
			        ifoCompanyIndustryViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + results.size());
			return results;
		} catch (RuntimeException re) {
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}