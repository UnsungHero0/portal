package vn.com.vndirect.embeddb.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.domain.embeddb.SectorCalcView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

public class SectorCalcViewDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(SectorCalcViewDAO.class);

	public interface Field {
		String SECTOR_CODE = "SECTOR_CODE";
		String PE = "PE";
		String PB = "PB";
		String SCOPE_MAKET_CAP = "SCOPE_MAKET_CAP";
		String SCOPE_ASSET = "SCOPE_ASSET";
		String SCOPE_EQUITY = "SCOPE_EQUITY";
		String GROWTH_ASSET = "GROWTH_ASSET";
		String GROWTH_REVENUE = "GROWTH_REVENUE";
		String ROA = "ROA";
		String ROE = "ROE";
		String PROFIT_MARGIN = "PROFIT_MARGIN";
		String DEBT_EQUITY = "DEBT_EQUITY";
		String CURRENT_RATIO = "CURRENT_RATIO";
		String EBITDA = "EBITDA";
	}

	private final static String SQL_INSERT_SECTOR_CALC_VIEW = "INSERT INTO SECTOR_CALC_VIEW(SECTOR_CODE, PE, PB, SCOPE_MAKET_CAP, SCOPE_ASSET, SCOPE_EQUITY, GROWTH_ASSET, GROWTH_REVENUE, ROA, ROE, PROFIT_MARGIN, DEBT_EQUITY, CURRENT_RATIO, EBITDA) "
			+ "VALUES (:SECTOR_CODE, :PE, :PB, :SCOPE_MAKET_CAP, :SCOPE_ASSET, :SCOPE_EQUITY, :GROWTH_ASSET, :GROWTH_REVENUE, :ROA, :ROE, :PROFIT_MARGIN, :DEBT_EQUITY, :CURRENT_RATIO, :EBITDA)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(SectorCalcView bean) throws SystemException {
		final String LOCATION = "insert(SectorCalcView)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.SECTOR_CODE, bean.getSectorCode());
			paramMap.put(Field.PE, bean.getPe());
			paramMap.put(Field.PB, bean.getPb());
			paramMap.put(Field.SCOPE_MAKET_CAP, bean.getScopeMaketCap());
			paramMap.put(Field.SCOPE_ASSET, bean.getScopeAsset());
			paramMap.put(Field.SCOPE_EQUITY, bean.getScopeEquity());
			paramMap.put(Field.GROWTH_ASSET, bean.getScopeAsset());
			paramMap.put(Field.GROWTH_REVENUE, bean.getGrowthRevenue());
			paramMap.put(Field.ROA, bean.getRoa());
			paramMap.put(Field.ROE, bean.getRoe());
			paramMap.put(Field.PROFIT_MARGIN, bean.getProfitMargin());
			paramMap.put(Field.DEBT_EQUITY, bean.getDebtEquity());
			paramMap.put(Field.CURRENT_RATIO, bean.getCurrentRatio());
			paramMap.put(Field.EBITDA, bean.getEbitda());

			OracleDAOHelper.update(this.getDataSource(), SQL_INSERT_SECTOR_CALC_VIEW, paramMap);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
