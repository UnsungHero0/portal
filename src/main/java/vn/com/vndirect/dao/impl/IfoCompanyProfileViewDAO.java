package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.IfoCompanyProfileView;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

/**
 * Data access object (DAO) for domain model class IfoCompanyProfileView.
 * 
 * @see vn.com.vndirect.domain.IfoCompanyProfileView
 * @author MyEclipse - Hibernate Tools
 */
@SuppressWarnings("rawtypes")
public class IfoCompanyProfileViewDAO extends BaseDAOImpl {

	private static final Log log = LogFactory.getLog(IfoCompanyProfileViewDAO.class);

	public static IfoCompanyProfileViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IfoCompanyProfileViewDAO) ctx.getBean("IfoCompanyProfileViewDAO");
	}

	// ////////////////////////////////// Using JDBC
	// /////////////////////////////////////

	/**
	 * process all attributes of table
	 */
	// private final ResultSetProcessor ifoCompanyProfileViewProcessor = new
	// ResultSetProcessor() {
	// public Object processRow(ResultSet rs) throws SQLException {
	// IfoCompanyProfileView obj = new IfoCompanyProfileView();
	// IfoCompanyProfileViewId objId = new IfoCompanyProfileViewId();
	// objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
	// objId.setAbbreviation(rs.getString("ABBREVIATION"));
	//
	// objId.setFax(rs.getString("FAX"));
	// objId.setFullEmployee(rs.getString("FULL_EMPLOYEE"));
	// objId.setHeadquarter(rs.getString("HEADQUARTER"));
	// objId.setIndexMembership(rs.getString("INDEX_MEMBERSHIP"));
	// objId.setIndustry(rs.getString("INDUSTRY"));
	// objId.setInternationalName(rs.getString("INTERNATIONAL_NAME"));
	// objId.setNumberOfBranches(rs.getString("NUMBER_OF_BRANCHES"));
	// objId.setIpoAverageBid(rs.getString("IPO_AVERAGE_BID"));
	// objId.setIpoHighestBid(rs.getString("IPO_HIGHEST_BID"));
	// objId.setIpoInformation(rs.getString("IPO_INFORMATION"));
	// objId.setIpoLocation(rs.getString("IPO_LOCATION"));
	// objId.setIpoLowestBid(rs.getString("IPO_LOWEST_BID"));
	// objId.setSector(rs.getString("SECTOR"));
	// objId.setTelephone(rs.getString("TELEPHONE"));
	// objId.setVietnameseName(rs.getString("VIETNAMESE_NAME"));
	// objId.setWebsite(rs.getString("WEBSITE"));
	// objId.setHistory(DAOHelper.getClobColumn(rs, "HISTORY"));
	// objId.setMainBusiness(DAOHelper.getClobColumn(rs, "MAIN_BUSINESS"));
	// objId.setMarketPosition(DAOHelper.getClobColumn(rs, "MARKET_POSITION"));
	// objId.setPlan(DAOHelper.getClobColumn(rs, "PLAN"));
	//
	// obj.setId(objId);
	// return obj;
	// }
	// };
	private final RowMapper ifoCompanyProfileViewRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			IfoCompanyProfileView obj = new IfoCompanyProfileView();
			IfoCompanyProfileViewId objId = new IfoCompanyProfileViewId();
			objId.setCompanyId(new Long(rs.getLong("COMPANY_ID")));
			objId.setAbbreviation(rs.getString("ABBREVIATION"));

			objId.setFax(rs.getString("FAX"));
			objId.setFullEmployee(rs.getString("FULL_EMPLOYEE"));
			objId.setHeadquarter(rs.getString("HEADQUARTER"));
			objId.setIndexMembership(rs.getString("INDEX_MEMBERSHIP"));
			objId.setIndustry(rs.getString("INDUSTRY"));
			objId.setInternationalName(rs.getString("INTERNATIONAL_NAME"));
			objId.setNumberOfBranches(rs.getString("NUMBER_OF_BRANCHES"));
			objId.setIpoAverageBid(rs.getString("IPO_AVERAGE_BID"));
			objId.setIpoHighestBid(rs.getString("IPO_HIGHEST_BID"));
			objId.setIpoInformation(rs.getString("IPO_INFORMATION"));
			objId.setIpoLocation(rs.getString("IPO_LOCATION"));
			objId.setIpoLowestBid(rs.getString("IPO_LOWEST_BID"));
			objId.setSector(rs.getString("SECTOR"));
			objId.setTelephone(rs.getString("TELEPHONE"));
			objId.setVietnameseName(rs.getString("VIETNAMESE_NAME"));
			objId.setWebsite(rs.getString("WEBSITE"));
			objId.setHistory(OracleDAOHelper.getClobColumn(rs, "HISTORY"));
			objId.setMainBusiness(OracleDAOHelper.getClobColumn(rs, "MAIN_BUSINESS"));
			objId.setMarketPosition(OracleDAOHelper.getClobColumn(rs, "MARKET_POSITION"));
			objId.setPlan(OracleDAOHelper.getClobColumn(rs, "PLAN"));

			obj.setId(objId);
			return obj;
		}
	};

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public IfoCompanyProfileView findByCompanyId(IfoCompanyProfileViewId id) throws SystemException {
		final String LOCATION = "findByCompanyId(id:" + id + ")";
		log.debug(LOCATION + ":: BEGIN");
		if (id == null) {
			throw new SystemException(LOCATION, "IfoCompanyProfileViewId is NULL...");
		}
		try {
			String SEARCH_SQL = "SELECT ifoCompanyProfileView.* " + " FROM IFO_COMPANY_PROFILE_VIEW ifoCompanyProfileView "
			        + " WHERE ifoCompanyProfileView.COMPANY_ID = :COMPANY_ID ";

			// List params = new ArrayList();
			// params.add(new Parameter(":COMPANY_ID",
			// id.getCompanyId().longValue()));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("COMPANY_ID", id.getCompanyId());

			if (id.getLocale() != null && id.getLocale().length() > 0) {
				SEARCH_SQL = SEARCH_SQL + " AND upper(ifoCompanyProfileView.LOCALE) = :LOCALE ";
				// params.add(new Parameter(":LOCALE",
				// id.getLocale().toUpperCase()));
				paramMap.put("LOCALE", id.getLocale().toUpperCase());
			}

			// Connection conn = this.getCurrentConnection();
			// EasyStatement stat = new EasyStatement(SEARCH_SQL);
			//
			// IfoCompanyProfileView obj =
			// (IfoCompanyProfileView)stat.getSingle(conn
			// , (Parameter[])params.toArray(new Parameter[params.size()])
			// , ifoCompanyProfileViewProcessor);

			IfoCompanyProfileView obj = (IfoCompanyProfileView) OracleDAOHelper.querySingle(this.getDataSource(), SEARCH_SQL,
			        paramMap, ifoCompanyProfileViewRowMapper);

			log.debug(LOCATION + ":: END - obj:" + obj);
			return obj;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new SystemException(LOCATION, re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(LOCATION, e);
		}
	}
}