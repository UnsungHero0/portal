package vn.com.vndirect.embeddb.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.domain.embeddb.SecPriceView;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.exception.SystemException;

public class SecLastPriceViewDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(SecLastPriceViewDAO.class);

	public interface Field {
		String COMPANY_ID = "COMPANY_ID";
		String EXCHANGE_CODE = "EXCHANGE_CODE";
		String SYMBOL = "SYMBOL";
		String OPEN_PRICE = "OPEN_PRICE";
		String HIGH_PRICE = "HIGH_PRICE";
		String LOW_PRICE = "LOW_PRICE";
		String CLOSE_PRICE = "CLOSE_PRICE";
		String AVERAGE_PRICE = "AVERAGE_PRICE";
		String VOLUME = "VOLUME";
		String TRANS_DATE = "TRANS_DATE";
		String AD_OPEN_PRICE = "AD_OPEN_PRICE";
		String AD_HIGH_PRICE = "AD_HIGH_PRICE";
		String AD_LOW_PRICE = "AD_LOW_PRICE";
		String AD_CLOSE_PRICE = "AD_CLOSE_PRICE";
		String AD_AVERAGE_PRICE = "AD_AVERAGE_PRICE";
		String RIGHTS_TYPE = "RIGHTS_TYPE";
	}

	private final static String SQL_INSERT_SEC_LAST_PRICE_VIEW = "INSERT INTO SEC_LAST_PRICE_VIEW(COMPANY_ID, EXCHANGE_CODE, SYMBOL, OPEN_PRICE, HIGH_PRICE, LOW_PRICE, CLOSE_PRICE, AVERAGE_PRICE, VOLUME, TRANS_DATE, AD_OPEN_PRICE, AD_HIGH_PRICE, AD_LOW_PRICE, AD_CLOSE_PRICE, AD_AVERAGE_PRICE, RIGHTS_TYPE) "
			+ "VALUES (:COMPANY_ID, :EXCHANGE_CODE, :SYMBOL, :OPEN_PRICE, :HIGH_PRICE, :LOW_PRICE, :CLOSE_PRICE, :AVERAGE_PRICE, :VOLUME, :TRANS_DATE, :AD_OPEN_PRICE, :AD_HIGH_PRICE, :AD_LOW_PRICE, :AD_CLOSE_PRICE, :AD_AVERAGE_PRICE, :RIGHTS_TYPE)";

	/**
	 * 
	 * @param bean
	 * @throws SystemException
	 */
	public void insert(SecPriceView bean) throws SystemException {
		final String LOCATION = "insert(SecPriceView)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Field.COMPANY_ID, bean.getCompanyId());
			paramMap.put(Field.EXCHANGE_CODE, bean.getExchangeCode());
			paramMap.put(Field.SYMBOL, bean.getSymbol());
			paramMap.put(Field.OPEN_PRICE, bean.getOpenPrice());
			paramMap.put(Field.HIGH_PRICE, bean.getHighPrice());
			paramMap.put(Field.LOW_PRICE, bean.getLowPrice());
			paramMap.put(Field.CLOSE_PRICE, bean.getClosePrice());
			paramMap.put(Field.AVERAGE_PRICE, bean.getAveragePrice());
			paramMap.put(Field.VOLUME, bean.getVolume());
			paramMap.put(Field.TRANS_DATE, bean.getTransDate());
			paramMap.put(Field.AD_OPEN_PRICE, bean.getAdOpenPrice());
			paramMap.put(Field.AD_HIGH_PRICE, bean.getAdHighPrice());
			paramMap.put(Field.AD_LOW_PRICE, bean.getAdLowPrice());
			paramMap.put(Field.AD_CLOSE_PRICE, bean.getAdClosePrice());
			paramMap.put(Field.AD_AVERAGE_PRICE, bean.getAdAveragePrice());
			paramMap.put(Field.RIGHTS_TYPE, bean.getRightsType());

			OracleDAOHelper.update(this.getDataSource(), SQL_INSERT_SEC_LAST_PRICE_VIEW, paramMap);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
