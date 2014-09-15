/**
 * 
 */
package vn.com.vndirect.embeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.embeddb.DynamicFieldItems;
import vn.com.vndirect.domain.embeddb.DynamicMetaTable;
import vn.com.vndirect.embeddb.EDBConstants;
import vn.com.vndirect.embeddb.EDBItemCodeMapping;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.web.commons.dao.h2db.H2DBUtils;
import vn.com.web.commons.dao.h2db.H2DBUtils.Condition;
import vn.com.web.commons.dao.h2db.H2DBUtils.SearchOption;
import vn.com.web.commons.dao.jdbc.H2DAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Validation;

/**
 * @author Blue9Frog
 * 
 */
@SuppressWarnings("unchecked")
public class DynamicTableDAO extends JdbcDaoSupport {
	private static final Log log = LogFactory.getLog(DynamicTableDAO.class);

	/**
	 * 
	 */
	private final RowMapper analysisIndexingRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			AnalysisIndexingBean analysisIndexing = new AnalysisIndexingBean();
			int size = rs.getMetaData().getColumnCount();
			String colName;
			String colValue;
			for (int i = 0; i < size; i++) {
				try {
					colName = rs.getMetaData().getColumnName(i + 1);
					if (EDBConstants.MappingItems.SYMBOL.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSymbol(rs.getString(colName));
					} else if (EDBConstants.MappingItems.COMPANY_ID.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setCompanyId(rs.getLong(colName));
					} else if (EDBConstants.MappingItems.EXCHANGE_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSectorCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.SECTOR_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSectorCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.INDUSTRY_CODE.fieldName.equalsIgnoreCase(colName)) {
						colValue = rs.getString(colName);
						if (colValue != null) {
							analysisIndexing.setIndustryCode(colValue);
						}
					} else {
						analysisIndexing.addValue(colName, new AnalysisCachingValueInfo(colName, rs.getDouble(colName)));
					}
				} catch (Exception e) {
					logger.warn("analysisIndexingRowMapper - " + e.getMessage());
				}
			}
			return analysisIndexing;
		}
	};

	private final RowMapper analysisIndexing4StockRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			AnalysisIndexingBean analysisIndexing = new AnalysisIndexingBean();
			int size = rs.getMetaData().getColumnCount();
			String colName;
			String colValue;
			for (int i = 0; i < size; i++) {
				try {
					colName = rs.getMetaData().getColumnName(i + 1);
					if (EDBConstants.MappingItems.SYMBOL.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSymbol(rs.getString(colName));
					} else if (EDBConstants.MappingItems.COMPANY_ID.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setCompanyId(rs.getLong(colName));
					} else if (EDBConstants.MappingItems.EXCHANGE_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSectorCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.SECTOR_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSectorCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.INDUSTRY_CODE.fieldName.equalsIgnoreCase(colName)) {
						colValue = rs.getString(colName);
						if (colValue != null) {
							analysisIndexing.setIndustryCode(colValue);
						}
					} else if (EDBConstants.MappingItems.CLOSE_PRICE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setClosePrice(rs.getDouble(colName));
					} else if (EDBConstants.MappingItems.COMPANY_NAME.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setCompanyName(rs.getString(colName));
					} else if (EDBConstants.MappingItems.COMPANY_FULL_NAME.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setCompanyFullName(rs.getString(colName));
					} else if (EDBConstants.MappingItems.ABB_NAME.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setAbbName(rs.getString(colName));
					} else if (EDBConstants.MappingItems.FIRST_TRADING_DATE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setFirstTradingDate(rs.getDate(colName));
					} else if (EDBConstants.MappingItems.EXCHANGE_NAME.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setExchangeName(rs.getString(colName));
					} else if (EDBConstants.MappingItems.INDUSTRY_GROUP_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setIndustryGroupCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.SECTOR_GROUP_CODE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setSectorGroupCode(rs.getString(colName));
					} else if (EDBConstants.MappingItems.ITEM_NAME.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setIndustryName(rs.getString(colName));
					} else if (EDBConstants.MappingItems.LOCALE.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setLocale(rs.getString(colName));
					} else if (EDBConstants.MappingItems.PCT_ABOVE_52_WEEK_LOW.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setPctAbove52WeekLow(rs.getDouble(colName));
					} else if (EDBConstants.MappingItems.PCT_BELOW_52_WEEK_HIGH.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setPctBelow52WeekHigh(rs.getDouble(colName));
					} else if (EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setVsSma13Day(rs.getDouble(colName));
					} else if (EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName.equalsIgnoreCase(colName)) {
						analysisIndexing.setVsSma50Day(rs.getDouble(colName));
					} else {
						analysisIndexing.addValue(colName, new AnalysisCachingValueInfo(colName, rs.getDouble(colName)));
					}
				} catch (Exception e) {
					logger.warn("analysisIndexingRowMapper - " + e.getMessage());
				}
			}
			return analysisIndexing;
		}
	};

	/**
	 * 
	 * @param toTable
	 * @param fromTable
	 * @throws SystemException
	 */
	public void insertInit4CompanyItemCalcViewTemp() throws SystemException {
		final String LOCATION = "insertInit4CompanyItemCalcViewTemp(toTable, fromTable)";
		try {
			StringBuilder SQL = new StringBuilder("INSERT INTO ")
					.append(EDBConstants.Tables.Temp.COMPANY_ITEM_CALC_VIEW)
					.append(
							"(COMPANY_ID, SYMBOL, INDUSTRY_CODE, SECTOR_CODE, EXCHANGE_CODE, CLOSE_PRICE) (select DISTINCT a.COMPANY_ID , a.SYMBOL, a.INDUSTRY_GROUP_CODE, a.SECTOR_GROUP_CODE, a.EXCHANGE_CODE, sec.CLOSE_PRICE  from STOCK_EXCHANGE a INNER JOIN COMPANY_ITEM_CALC_VIEW b ON a.SYMBOL=b.SYMBOL LEFT OUTER JOIN SEC_LAST_PRICE_VIEW sec ON a.SYMBOL=sec.SYMBOL)");
			// .append("(COMPANY_ID, SYMBOL, INDUSTRY_CODE, SECTOR_CODE, EXCHANGE_CODE) (select DISTINCT a.COMPANY_ID , a.SYMBOL, a.INDUSTRY_GROUP_CODE, a.SECTOR_GROUP_CODE, a.EXCHANGE_CODE from STOCK_EXCHANGE a, COMPANY_ITEM_CALC_VIEW b WHERE a.SYMBOL=b.SYMBOL)");
			// .append("(SYMBOL, INDUSTRY_CODE) (select DISTINCT a.SYMBOL, a.INDUSTRY_CODE from STOCK_EXCHANGE a, COMPANY_ITEM_CALC_VIEW b WHERE
			// a.SYMBOL=b.SYMBOL)");
			H2DAOHelper.update(this.getDataSource(), SQL.toString(), null);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @throws SystemException
	 */
	public void insertInit4IndustryItemCalcViewTempCode() throws SystemException {
		final String LOCATION = "insertInit4IndustryItemCalcViewTempCode()";
		try {
			StringBuilder SQL = new StringBuilder("INSERT INTO ").append(EDBConstants.Tables.Temp.INDUSTRY_ITEM_CALC_VIEW).append(
					"(INDUSTRY_CODE) (SELECT DISTINCT INDUSTRY_CODE FROM INDUSTRY_ITEM_CALC_VIEW)");
			H2DAOHelper.update(this.getDataSource(), SQL.toString(), null);
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param table
	 * @param dynamicFieldItems
	 * @throws SystemException
	 */
	public void updateFieldItems4CompanyItemCalcViewTemp(String table, DynamicFieldItems<Double> dynamicFieldItems) throws SystemException {
		final String LOCATION = "updateFieldItems4CompanyItemCalcViewTemp(table, DynamicFieldItems)";
		log.debug(LOCATION + ":: BEGIN : table:" + table);
		try {
			String symbol = dynamicFieldItems.getSymbol();
			symbol = (symbol == null ? "" : symbol.toUpperCase().trim());
			int size = (dynamicFieldItems == null ? 0 : dynamicFieldItems.size());
			if (size > 0 && symbol.length() > 0) {
				StringBuilder strSQL = new StringBuilder();
				strSQL.append(" UPDATE ").append(table).append(" SET ");
				int count = 0;
				for (String fileName : dynamicFieldItems.keySet()) {
					count++;
					strSQL.append(fileName).append("=:").append(fileName).append(count < size ? ", " : "");
				}
				strSQL.append(" WHERE UPPER(SYMBOL)='").append(symbol).append("'");

				H2DAOHelper.update(this.getDataSource(), strSQL.toString(), dynamicFieldItems);
			}
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param table
	 * @param dynamicFieldItems
	 * @throws SystemException
	 */
	public void updateFieldItems4IndustryItemCalcViewTempCode(String table, DynamicFieldItems<Double> dynamicFieldItems) throws SystemException {
		final String LOCATION = "updateFieldItems4IndustryItemCalcViewTempCode(table, DynamicFieldItems)";
		log.debug(LOCATION + ":: BEGIN : table:" + table);
		try {
			String industryCode = dynamicFieldItems.getIndustryCode();
			industryCode = (industryCode == null ? "" : industryCode.toUpperCase().trim());
			int size = (dynamicFieldItems == null ? 0 : dynamicFieldItems.size());
			if (size > 0 && industryCode.length() > 0) {
				StringBuilder strSQL = new StringBuilder();
				strSQL.append(" UPDATE ").append(table).append(" SET ");
				int count = 0;
				for (String fileName : dynamicFieldItems.keySet()) {
					count++;
					strSQL.append(fileName).append("=:").append(fileName).append(count < size ? ", " : "");
				}
				strSQL.append(" WHERE UPPER(INDUSTRY_CODE)='").append(industryCode).append("'");

				H2DAOHelper.update(this.getDataSource(), strSQL.toString(), dynamicFieldItems);
			}
			log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param itemCodeMapping
	 * @return
	 */
	private StringBuilder generateSelectSQL4Analysis(EDBItemCodeMapping itemCodeMapping) {
		StringBuilder strSQL = new StringBuilder("SELECT a.*");
		if (itemCodeMapping != null && itemCodeMapping.getListIndustryItemCode() != null && itemCodeMapping.getListIndustryItemCode().size() > 0) {
			for (String industryCode : itemCodeMapping.getListIndustryItemCode()) {
				strSQL.append(", b.").append(DynamicMetaTable.generateFieldName(industryCode));
			}
		}
		strSQL.append(" FROM COMPANY_ITEM_CALC_VIEW_TEMP a INNER JOIN INDUSTRY_ITEM_CALC_VIEW_TEMP  b ON a.INDUSTRY_CODE =  b.INDUSTRY_CODE ");
		return strSQL;
	}

	private StringBuilder generateSelectSQL4StockScreener(EDBItemCodeMapping itemCodeMapping, String locale) {
		StringBuilder strSQL = new StringBuilder("SELECT a.*");
		if (itemCodeMapping != null && itemCodeMapping.getListIndustryItemCode() != null && itemCodeMapping.getListIndustryItemCode().size() > 0) {
			for (String industryCode : itemCodeMapping.getListIndustryItemCode()) {
				strSQL.append(", b.").append(DynamicMetaTable.generateFieldName(industryCode));
			}
		}
		strSQL.append(", d.COMPANY_NAME, d.COMPANY_FULL_NAME, d.ABB_NAME, d.FIRST_TRADING_DATE, d.EXCHANGE_NAME").append(", d.INDUSTRY_GROUP_CODE, d.SECTOR_GROUP_CODE, e.ITEM_NAME, e.LOCALE").append(
				" FROM COMPANY_ITEM_CALC_VIEW_TEMP a ").append(" INNER JOIN INDUSTRY_ITEM_CALC_VIEW_TEMP b ON a.INDUSTRY_CODE =  b.INDUSTRY_CODE ").append(
				" INNER JOIN STOCK_EXCHANGE d ON a.SYMBOL = d.SYMBOL").append(
				" INNER JOIN ITEM_CODE_REF e ON a.INDUSTRY_CODE = e.ITEM_CODE and e.GROUP_CODE='SECTOR' and UPPER(LOCALE) = '" + locale.toUpperCase() + "'").append(" WHERE 1=1 ");

		return strSQL;
	}

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @param itemCodeMapping
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public void calcStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException {
		final String LOCATION = "calcStockScreener(SearchStockScreenerBean: " + searchStockScreenerBean + " )";
		log.debug(LOCATION + ":: BEGIN");
		StringBuffer sqlBuffer = new StringBuffer(" SELECT count(*) FROM COMPANY_ITEM_CALC_VIEW_TEMP a " + " INNER JOIN INDUSTRY_ITEM_CALC_VIEW_TEMP  b ON a.INDUSTRY_CODE =  b.INDUSTRY_CODE "
				+ " WHERE (1=1) ");

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String noSearch = "--";
			String IS_SELECTED = "1";
			boolean isFirst = true;
			boolean hasSearch4Group = false;
			int lengOriginSQL = sqlBuffer.length();
			/* ALL data */
			searchStockScreenerBean.setCnt_all_c(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			/* Search for asset class */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_4())) {
				// no search
				searchStockScreenerBean.setCnt_asset_class(noSearch);
			} else {
				sqlBuffer.append(" AND ( ");
				isFirst = true;
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 200000000000.00, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 200000000000.00, 500000000000.00, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 500000000000.00, 1000000000000.00, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 1000000000000.00, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
				searchStockScreenerBean.setCnt_asset_class(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Search for exchange code HASTC, HOSE, OTC */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getComponent_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getComponent_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getComponent_3())) {
				// No search
				searchStockScreenerBean.setCnt_component(noSearch);

			} else {
				sqlBuffer.append(" AND ( ");
				isFirst = true;
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HOSTC", params));
					sqlBuffer.append((" OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HOSE", params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HNX", params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "OTC", params));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
				searchStockScreenerBean.setCnt_component(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Search for Sector/Industry */
			if (Validation.isEmpty(searchStockScreenerBean.getSector())) {
				searchStockScreenerBean.setCnt_sector_industry(noSearch);
			} else {
				hasSearch4Group = true;
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.SECTOR_CODE.fieldName, searchStockScreenerBean.getSector(), params));

				if (!Validation.isEmpty(searchStockScreenerBean.getIndustry())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.INDUSTRY_CODE.fieldName, searchStockScreenerBean.getIndustry(), params));
				}
				searchStockScreenerBean.setCnt_sector_industry(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}
			/* Search for group 1: Market segment */
			if (hasSearch4Group) {
				searchStockScreenerBean.setCnt_g1_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_g1_c_result(noSearch);
			}

			hasSearch4Group = false;
			/*
			 * Search for Share Price
			 */
			if (!Validation.isNumber(searchStockScreenerBean.getShare_price_from()) && !Validation.isNumber(searchStockScreenerBean.getShare_price_to())) {
				searchStockScreenerBean.setCnt_share_price(noSearch);

			} else {
				hasSearch4Group = true;
				if (Validation.isNumber(searchStockScreenerBean.getShare_price_from())) {
					sqlBuffer.append(" AND "
							+ H2DBUtils.buildQuery(EDBConstants.MappingItems.CLOSE_PRICE.fieldName, Double.parseDouble(searchStockScreenerBean.getShare_price_from()), params,
									Condition.GREATER_THAN_OR_EQUALS));

				}
				if (Validation.isNumber(searchStockScreenerBean.getShare_price_to())) {
					sqlBuffer.append(" AND "
							+ H2DBUtils.buildQuery(EDBConstants.MappingItems.CLOSE_PRICE.fieldName, Double.parseDouble(searchStockScreenerBean.getShare_price_to()), params,
									Condition.LESS_THAN_OR_EQUALS));
				}
				searchStockScreenerBean.setCnt_share_price(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/*
			 * Search for Price change
			 */
			if (!Validation.isNumber(searchStockScreenerBean.getPrice_change_from())
					|| (!IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_2())
							&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_4()) && !IS_SELECTED
							.equals(searchStockScreenerBean.getPrice_change_5()))) {
				searchStockScreenerBean.setCnt_price_change(noSearch);

			} else {
				hasSearch4Group = true;
				double priceChange = Double.parseDouble(searchStockScreenerBean.getPrice_change_from()) / 100;
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_1())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._1_DAYS_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_2())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._5_DAYS_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_3())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_CHG_4_WEEKS.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_4())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._3_MO_PRICE_CHANGE.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_5())) {
					sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._52_WEEK_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
				}
				searchStockScreenerBean.setCnt_price_change(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			}

			/* Price Performance vs. VNIndex */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getOver_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getOver_2()) && !IS_SELECTED.equals(searchStockScreenerBean.getOver_3())) {
				searchStockScreenerBean.setCnt_price_performance(noSearch);
			} else if (!IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
				searchStockScreenerBean.setCnt_price_performance(noSearch);
			} else {
				hasSearch4Group = true;
				// over 1: Last 4 Weeks
				if (IS_SELECTED.equals(searchStockScreenerBean.getOver_1())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
						sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.4, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.4, -0.2, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.20, 0, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0, 0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0.20, 0.40, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}

				// over 2: Last 13 Weeks
				if (IS_SELECTED.equals(searchStockScreenerBean.getOver_2())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
						sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.40, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.40, -0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.20, 0, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0, 0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0.20, 0.40, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}

				// over 3: Last 52 Weeks
				if (IS_SELECTED.equals(searchStockScreenerBean.getOver_3())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
						sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.40, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.40, -0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.20, 0, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0, 0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0.20, 0.40, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}
				searchStockScreenerBean.setCnt_price_performance(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Search Average Volume */
			if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_from()) && Validation.isNumber(searchStockScreenerBean.getAverage_volume_to())) {
				hasSearch4Group = true;
				sqlBuffer.append(" AND "
						+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_from()), Double
								.parseDouble(searchStockScreenerBean.getAverage_volume_to()), params));
				searchStockScreenerBean.setCnt_average_volume(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			} else if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_from())) {
				hasSearch4Group = true;
				sqlBuffer.append(" AND "
						+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_from()), params,
								Condition.GREATER_THAN_OR_EQUALS));
				searchStockScreenerBean.setCnt_average_volume(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			} else if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_to())) {
				hasSearch4Group = true;
				sqlBuffer.append(" AND "
						+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_to()), params,
								Condition.LESS_THAN_OR_EQUALS));
				searchStockScreenerBean.setCnt_average_volume(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			} else {
				searchStockScreenerBean.setCnt_average_volume(noSearch);
			}
			// Cumulative for group 2
			if (hasSearch4Group) {
				searchStockScreenerBean.setCnt_g2_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_g2_c_result(noSearch);
			}

			hasSearch4Group = false;
			/* Fundamental */
			/* P/E Ratio */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_7())) {
				searchStockScreenerBean.setCnt_pe_ratio(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_5())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_1())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 0, 5, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 5, 10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 10, 15, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 15, 25, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 25, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_6())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.PE_RATIO.fieldName + " < " + EDBConstants.MappingItems.PRICE_EARNING_RATION.fieldName);
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_7())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.PE_RATIO.fieldName + " >= " + EDBConstants.MappingItems.PRICE_EARNING_RATION.fieldName);
					isFirst = false;
				}

				searchStockScreenerBean.setCnt_pe_ratio(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* PEG Ratio */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getPeg_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPeg_2())) {
				searchStockScreenerBean.setCnt_peg(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPeg_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.PEG_RATIO.fieldName, 1, params, Condition.LESS_THAN_OR_EQUALS));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPeg_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PEG_RATIO.fieldName, 1, params, Condition.GREATER_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_peg(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}
			/* Profit Margin(TTM) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_1())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_2())) {
				searchStockScreenerBean.setCnt_profit_margin(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_1()) || IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_3()) || IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_5())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_1())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0, 0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.05, 0.10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.10, 0.20, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.20, 0.50, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.50, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_1())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.PROFIT_MARGIN.fieldName + " < " + EDBConstants.MappingItems.NET_PROFIT_MARGIN_4_INDUSTRY.fieldName);
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_2())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.PROFIT_MARGIN.fieldName + " >= " + EDBConstants.MappingItems.NET_PROFIT_MARGIN_4_INDUSTRY.fieldName);
				}
				searchStockScreenerBean.setCnt_profit_margin(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			}

			/* Price/Sales Ratio(TTM) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_7())) {
				searchStockScreenerBean.setCnt_price_sale_ratio(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0, 0.1, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.1, 0.2, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.2, 0.5, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.5, 1, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 1, 5, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 5, 10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 10, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
				searchStockScreenerBean.setCnt_price_sale_ratio(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Price/Book Ratio(MRQ) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_5())) {
				searchStockScreenerBean.setCnt_price_book_ratio(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 0, 0.5, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 0.5, 1, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 1, 2, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 2, 3, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 3, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
				searchStockScreenerBean.setCnt_price_book_ratio(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Return on Equity(TTM)(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_1())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_2())) {
				searchStockScreenerBean.setCnt_return_equity(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_1()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_3()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_5())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_1())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0, 0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.05, 0.15, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.15, 0.25, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.25, params, Condition.GREATER_THAN));
						isFirst = false;
					}

					sqlBuffer.append(" ) ");
				}

				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_1())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROE.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.ROE_4_INDUSTRY.fieldName);
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_2())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROE.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.ROE_4_INDUSTRY.fieldName);
				}

				searchStockScreenerBean.setCnt_return_equity(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Return on Assets(TTM)(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_1())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_2())) {
				searchStockScreenerBean.setCnt_return_asset(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_1()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_3()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_5())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_1())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0, 0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.05, 0.15, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.15, 0.25, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.25, params, Condition.GREATER_THAN));
						isFirst = false;
					}

					sqlBuffer.append(" ) ");
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_1())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROA.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.ROA_4_INDUSTRY.fieldName);
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_2())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROA.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.ROA_4_INDUSTRY.fieldName);
				}

				searchStockScreenerBean.setCnt_return_asset(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}
			if (hasSearch4Group) {
				searchStockScreenerBean.setCnt_g3_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_g3_c_result(noSearch);
			}

			hasSearch4Group = false;
			/* EPS Growth Annual(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_7()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_8())) {
				searchStockScreenerBean.setCnt_eps_growth_annual(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.15, 0.25, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.25, 0.50, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.50, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, params, Condition.LESS_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_eps_growth_annual(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Revenue Growth Annual(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_7()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_8())) {
				searchStockScreenerBean.setCnt_revenue_growth_annual(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.15, 0.25, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.25, 0.50, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.50, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, params, Condition.LESS_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_revenue_growth_annual(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* EPS Growth(Quarterly)(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_7()) && !IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_8())) {
				searchStockScreenerBean.setCnt_eps_growth_quarterly(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.15, 0.25, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.25, 0.50, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.50, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, params, Condition.LESS_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_eps_growth_quarterly(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Revenue Growth (Quarterly)(%) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_7()) && !IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_8())) {
				searchStockScreenerBean.setCnt_revenue_growth_quarterly(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.15, 0.25, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.25, 0.50, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.50, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, params, Condition.LESS_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_revenue_growth_quarterly(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Dividend Growth 5 Year */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_5())) {
				searchStockScreenerBean.setCnt_dividend_growth_5_year(noSearch);
			} else {
				isFirst = true;
				hasSearch4Group = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_1())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 0, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 0, 5, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 5, 15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 15, 25, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 25, params, Condition.GREATER_THAN));
					isFirst = false;
				}

				sqlBuffer.append(" ) ");

				searchStockScreenerBean.setCnt_dividend_growth_5_year(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}

			/* Dividend Yield */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_2())) {
				searchStockScreenerBean.setCnt_dividend_yield(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_1()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_3()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_5()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_6())) {

					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_1())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 0, 2, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 2, 4, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 4, 6, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 6, 8, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 8, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}

				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_1())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.DIVIDENT_YEALD_4_INDUSTRY.fieldName);
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_2())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName + Condition.GREATER_THAN_OR_EQUALS.key
							+ EDBConstants.MappingItems.DIVIDENT_YEALD_4_INDUSTRY.fieldName);
				}
				searchStockScreenerBean.setCnt_dividend_yield(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			}
			if (hasSearch4Group) {
				searchStockScreenerBean.setCnt_g4_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_g4_c_result(noSearch);
			}

			hasSearch4Group = false;

			/* Technicals:: 52 Week High */
			if (Validation.isNumber(searchStockScreenerBean.get_52_week_high()) && IS_SELECTED.equals(searchStockScreenerBean.get_52_week_high_1())) {
				hasSearch4Group = true;
				sqlBuffer.append(" AND ").append(EDBConstants.MappingItems.CLOSE_PRICE.fieldName).append(" BETWEEN ").append(EDBConstants.MappingItems._52_WEEK_HIGH.fieldName).append(
						"*(100 - " + searchStockScreenerBean.get_52_week_high() + ")/100 ").append(" AND " + EDBConstants.MappingItems._52_WEEK_HIGH.fieldName).append(
						"*(100 + " + searchStockScreenerBean.get_52_week_high() + ")/100 ");

				searchStockScreenerBean.setCnt_52_week_high(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_52_week_high(noSearch);
			}

			/* 52 Week Low */
			if (Validation.isNumber(searchStockScreenerBean.get_52_week_low()) && IS_SELECTED.equals(searchStockScreenerBean.get_52_week_low_1())) {
				hasSearch4Group = true;
				sqlBuffer.append(" AND ").append(EDBConstants.MappingItems.CLOSE_PRICE.fieldName).append(" BETWEEN ").append(EDBConstants.MappingItems._52_WEEK_LOW.fieldName).append(
						"*(100 - " + searchStockScreenerBean.get_52_week_low() + ")/100 ").append(" AND " + EDBConstants.MappingItems._52_WEEK_LOW.fieldName).append(
						"*(100 + " + searchStockScreenerBean.get_52_week_low() + ")/100 ");

				searchStockScreenerBean.setCnt_52_week_low(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));

			} else {
				searchStockScreenerBean.setCnt_52_week_low(noSearch);
			}

			/* Price closes [Above/Below] simple moving average (SMA) */
			if (!IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_2())) {
				searchStockScreenerBean.setCnt_price_closes_sam(noSearch);
			} else if (!IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())
					&& !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7()) && !IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8())) {
				searchStockScreenerBean.setCnt_price_closes_sam(noSearch);
			} else {
				hasSearch4Group = true;
				// 13 Day SMA
				if (IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_1())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1())) {
						sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -15, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.15, -0.10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.10, -0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.05, 0, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0, 0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.05, 0.10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.10, 0.15, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.15, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}

				// 50 day SMA
				if (IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_2())) {
					isFirst = true;
					sqlBuffer.append(" AND ( ");
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1())) {
						sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.15, params, Condition.LESS_THAN));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.15, -0.10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.10, -0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.05, 0, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0, 0.05, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.05, 0.10, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.10, 0.15, params));
						isFirst = false;
					}
					if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8())) {
						sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.15, params, Condition.GREATER_THAN));
						isFirst = false;
					}
					sqlBuffer.append(" ) ");
				}

				searchStockScreenerBean.setCnt_price_closes_sam(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}
			/* 13 day SMA is [Above/Below] its 50 day SMA */
			if (!IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_1()) && !IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_2())) {
				searchStockScreenerBean.setCnt_13_day_sma(noSearch);
			} else {
				hasSearch4Group = true;
				if (IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_1())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.SMA_13.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.SMA_50.fieldName);
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_2())) {
					sqlBuffer.append(" AND " + EDBConstants.MappingItems.SMA_13.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.SMA_50.fieldName);
				}
				searchStockScreenerBean.setCnt_13_day_sma(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			}
			if (hasSearch4Group) {
				searchStockScreenerBean.setCnt_g5_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_g5_c_result(noSearch);
			}

			if (lengOriginSQL != sqlBuffer.length()) {
				searchStockScreenerBean.setCnt_all_c_result(String.valueOf(H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params)));
			} else {
				searchStockScreenerBean.setCnt_all_c_result("0");
			}

			log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(LOCATION + ":: END" + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private StringBuilder createQuery(SearchStockScreenerBean searchStockScreenerBean, Map<String, Object> params) throws Exception {
		StringBuilder sqlBuffer = new StringBuilder();
		String IS_SELECTED = "1";
		boolean isFirst = true;

		/* Search for asset class */
		if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_1()) || IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_3()) || IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_4())) {

			sqlBuffer.append(" AND ( ");
			isFirst = true;
			if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 200000000000.00, params, Condition.LESS_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 200000000000.00, 500000000000.00, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 500000000000.00, 1000000000000.00, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getAsset_class_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.MARKET_CAP.fieldName, 1000000000000.00, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Search for exchange code HASTC, HOSE, OTC */
		if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_1()) || IS_SELECTED.equals(searchStockScreenerBean.getComponent_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getComponent_3())) {

			sqlBuffer.append(" AND ( ");
			isFirst = true;
			if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HOSTC", params));
				sqlBuffer.append((" OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HOSE", params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "HNX", params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getComponent_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "OTC", params));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Search for Sector/Industry */
		if (!Validation.isEmpty(searchStockScreenerBean.getSector())) {
			sqlBuffer.append(" AND " + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.SECTOR_CODE.fieldName, searchStockScreenerBean.getSector(), params));

			if (!Validation.isEmpty(searchStockScreenerBean.getIndustry())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery("a." + EDBConstants.MappingItems.INDUSTRY_CODE.fieldName, searchStockScreenerBean.getIndustry(), params));
			}
		}

		/*
		 * Search for Share Price
		 */
		if (Validation.isNumber(searchStockScreenerBean.getShare_price_from()) || Validation.isNumber(searchStockScreenerBean.getShare_price_to())) {

			if (Validation.isNumber(searchStockScreenerBean.getShare_price_from())) {
				sqlBuffer.append(" AND "
						+ H2DBUtils.buildQuery(EDBConstants.MappingItems.CLOSE_PRICE.fieldName, Double.parseDouble(searchStockScreenerBean.getShare_price_from()), params,
								Condition.GREATER_THAN_OR_EQUALS));

			}
			if (Validation.isNumber(searchStockScreenerBean.getShare_price_to())) {
				sqlBuffer
						.append(" AND "
								+ H2DBUtils.buildQuery(EDBConstants.MappingItems.CLOSE_PRICE.fieldName, Double.parseDouble(searchStockScreenerBean.getShare_price_to()), params,
										Condition.LESS_THAN_OR_EQUALS));
			}
		}

		/*
		 * Search for Price change
		 */
		if (Validation.isNumber(searchStockScreenerBean.getPrice_change_from())
				&& (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_4()) || IS_SELECTED
						.equals(searchStockScreenerBean.getPrice_change_5()))) {

			double priceChange = Double.parseDouble(searchStockScreenerBean.getPrice_change_from()) / 100;
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_1())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._1_DAYS_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_2())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._5_DAYS_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_3())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_CHG_4_WEEKS.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_4())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._3_MO_PRICE_CHANGE.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_change_5())) {
				sqlBuffer.append(" AND " + H2DBUtils.buildQuery(EDBConstants.MappingItems._52_WEEK_CHG.fieldName, priceChange, params, Condition.GREATER_THAN_OR_EQUALS));
			}

		}

		/* Price Performance vs. VNIndex */
		if ((IS_SELECTED.equals(searchStockScreenerBean.getOver_1()) || IS_SELECTED.equals(searchStockScreenerBean.getOver_2()) || IS_SELECTED.equals(searchStockScreenerBean.getOver_3()))
				&& (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6()))) {
			// over 1: Last 4 Weeks
			if (IS_SELECTED.equals(searchStockScreenerBean.getOver_1())) {
				isFirst = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.4, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.4, -0.2, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, -0.20, 0, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0, 0.20, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0.20, 0.40, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_4_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
			}

			// over 2: Last 13 Weeks
			if (IS_SELECTED.equals(searchStockScreenerBean.getOver_2())) {
				isFirst = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.40, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.40, -0.20, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, -0.20, 0, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0, 0.20, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0.20, 0.40, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_13_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
			}

			// over 3: Last 52 Weeks
			if (IS_SELECTED.equals(searchStockScreenerBean.getOver_3())) {
				isFirst = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.40, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.40, -0.20, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, -0.20, 0, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0, 0.20, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0.20, 0.40, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_performance_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.STOCK_PRICE_VS_INDEX_52_WEEKS.fieldName, 0.40, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
			}
		}

		/* Search Average Volume */
		if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_from()) && Validation.isNumber(searchStockScreenerBean.getAverage_volume_to())) {
			sqlBuffer.append(" AND "
					+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_from()), Double
							.parseDouble(searchStockScreenerBean.getAverage_volume_to()), params));

		} else if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_from())) {
			sqlBuffer.append(" AND "
					+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_from()), params,
							Condition.GREATER_THAN_OR_EQUALS));

		} else if (Validation.isNumber(searchStockScreenerBean.getAverage_volume_to())) {
			sqlBuffer.append(" AND "
					+ H2DBUtils.buildQuery(EDBConstants.MappingItems.AVG_VOLUME_10_DAYS.fieldName, Double.parseDouble(searchStockScreenerBean.getAverage_volume_to()), params,
							Condition.LESS_THAN_OR_EQUALS));

		}

		/* Fundamental */
		/* P/E Ratio */
		if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_2()) || IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_3())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_4()) || IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_5())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 0, 5, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 5, 10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 10, 15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 15, 25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PE_RATIO.fieldName, 25, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_6())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.PE_RATIO.fieldName + " < " + EDBConstants.MappingItems.PRICE_EARNING_RATION.fieldName);
			isFirst = false;
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getPe_ratio_7())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.PE_RATIO.fieldName + " >= " + EDBConstants.MappingItems.PRICE_EARNING_RATION.fieldName);
			isFirst = false;
		}

		/* PEG Ratio */
		if (IS_SELECTED.equals(searchStockScreenerBean.getPeg_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPeg_2())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getPeg_1())) {
				sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.PEG_RATIO.fieldName, 1, params, Condition.LESS_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPeg_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PEG_RATIO.fieldName, 1, params, Condition.GREATER_THAN));
				isFirst = false;
			}

			sqlBuffer.append(" ) ");

		}
		/* Profit Margin(TTM) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_1()) || IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_3()) || IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_5())) {

			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.05, 0.10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.10, 0.20, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.20, 0.50, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PROFIT_MARGIN.fieldName, 0.50, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_1())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.PROFIT_MARGIN.fieldName + " < " + EDBConstants.MappingItems.NET_PROFIT_MARGIN_4_INDUSTRY.fieldName);
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getProfit_margin_and_2())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.PROFIT_MARGIN.fieldName + " >= " + EDBConstants.MappingItems.NET_PROFIT_MARGIN_4_INDUSTRY.fieldName);
		}

		/* Price/Sales Ratio(TTM) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_5()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_6())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_7())) {

			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0, 0.1, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.1, 0.2, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.2, 0.5, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 0.5, 1, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 1, 5, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 5, 10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_sale_ratio_7())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_SALE_RATIO.fieldName, 10, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Price/Book Ratio(MRQ) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_5())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 0, 0.5, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 0.5, 1, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 1, 2, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 2, 3, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_book_ratio_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.PRICE_BOOK_RATIO.fieldName, 3, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Return on Equity(TTM)(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_1()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_3()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_5())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.05, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROE.fieldName, 0.25, params, Condition.GREATER_THAN));
				isFirst = false;
			}

			sqlBuffer.append(" ) ");
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_1())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROE.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.ROE_4_INDUSTRY.fieldName);
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_equity_and_2())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROE.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.ROE_4_INDUSTRY.fieldName);
		}

		/* Return on Assets(TTM)(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_1()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_3()) || IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_5())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.05, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.ROA.fieldName, 0.25, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_1())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROA.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.ROA_4_INDUSTRY.fieldName);
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getReturn_asset_and_2())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.ROA.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.ROA_4_INDUSTRY.fieldName);
		}

		/* EPS Growth Annual(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_1()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_3()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_5()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_6())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_7()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_8())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.05, 0.10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.10, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.25, 0.50, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0.50, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_7())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_annual_8())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_MANUAL.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}

			sqlBuffer.append(" ) ");

		}

		/* Revenue Growth Annual(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_1()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_3()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_5()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_6())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_7()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_8())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.05, 0.10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.10, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.25, 0.50, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0.50, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_7())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_annual_8())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_MANUAL.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* EPS Growth(Quarterly)(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_1()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_3()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_5()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_6())
				|| IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_7()) || IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_8())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.05, 0.10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.10, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.25, 0.50, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0.50, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_7())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getEps_growth_quarterly_8())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.EPS_GROWTH_QUARTERLY.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Revenue Growth (Quarterly)(%) */
		if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_1()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_3()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_5()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_6())
				|| IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_7()) || IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_8())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, 0.05, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.05, 0.10, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.10, 0.15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.15, 0.25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.25, 0.50, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0.50, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_7())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getRevenue_growth_quarterly_8())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.REVERNUE_GROWTH_QUARTERLY.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		/* Dividend Growth 5 Year */
		if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_1()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_3()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_5())) {
			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 0, params, Condition.LESS_THAN));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 0, 5, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 5, 15, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 15, 25, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_growth_5_year_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_GROWTH_5_YEARS.fieldName, 25, params, Condition.GREATER_THAN));
				isFirst = false;
			}

			sqlBuffer.append(" ) ");

		}

		/* Dividend Yield */
		if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_1()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_2())
				|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_3()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_4())
				|| IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_5()) || IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_6())) {

			isFirst = true;
			sqlBuffer.append(" AND ( ");
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_1())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 0, params, Condition.GREATER_THAN_OR_EQUALS));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_2())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 0, 2, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_3())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 2, 4, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_4())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 4, 6, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_5())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 6, 8, params));
				isFirst = false;
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_6())) {
				sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName, 8, params, Condition.GREATER_THAN));
				isFirst = false;
			}
			sqlBuffer.append(" ) ");
		}

		if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_1())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.DIVIDENT_YEALD_4_INDUSTRY.fieldName);
		}
		if (IS_SELECTED.equals(searchStockScreenerBean.getDividend_yield_and_2())) {
			sqlBuffer.append(" AND " + EDBConstants.MappingItems.DIVIDENT_YEALD.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.DIVIDENT_YEALD_4_INDUSTRY.fieldName);
		}

		/* 52 Week High */
		if (Validation.isNumber(searchStockScreenerBean.get_52_week_high()) && IS_SELECTED.equals(searchStockScreenerBean.get_52_week_high_1())) {
			sqlBuffer.append(" AND ").append(EDBConstants.MappingItems.CLOSE_PRICE.fieldName).append(" BETWEEN ").append(EDBConstants.MappingItems._52_WEEK_HIGH.fieldName).append(
					"*(100 - " + searchStockScreenerBean.get_52_week_high() + ")/100 ").append(" AND " + EDBConstants.MappingItems._52_WEEK_HIGH.fieldName).append(
					"*(100 + " + searchStockScreenerBean.get_52_week_high() + ")/100 ");

		}

		/* 52 Week Low */
		if (Validation.isNumber(searchStockScreenerBean.get_52_week_low()) && IS_SELECTED.equals(searchStockScreenerBean.get_52_week_low_1())) {
			sqlBuffer.append(" AND ").append(EDBConstants.MappingItems.CLOSE_PRICE.fieldName).append(" BETWEEN ").append(EDBConstants.MappingItems._52_WEEK_LOW.fieldName).append(
					"*(100 - " + searchStockScreenerBean.get_52_week_low() + ")/100 ").append(" AND " + EDBConstants.MappingItems._52_WEEK_LOW.fieldName).append(
					"*(100 + " + searchStockScreenerBean.get_52_week_low() + ")/100 ");

		}

		/* Price closes [Above/Below] simple moving average (SMA) */
		if ((IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_1()) || IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_2()))
				&& (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())
						|| IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7()) || IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8()))) {

			// 13 Day SMA
			if (IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_1())) {
				isFirst = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -15, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.15, -0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.10, -0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, -0.05, 0, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_13_DAY.fieldName, 0.15, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
			}

			// 50 day SMA
			if (IS_SELECTED.equals(searchStockScreenerBean.getAbove_below_2())) {
				isFirst = true;
				sqlBuffer.append(" AND ( ");
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_1())) {
					sqlBuffer.append(H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.15, params, Condition.LESS_THAN));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_2())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.15, -0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_3())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.10, -0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_4())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, -0.05, 0, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_5())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0, 0.05, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_6())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.05, 0.10, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_7())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.10, 0.15, params));
					isFirst = false;
				}
				if (IS_SELECTED.equals(searchStockScreenerBean.getPrice_closes_sam_8())) {
					sqlBuffer.append((isFirst ? "" : " OR ") + H2DBUtils.buildQuery(EDBConstants.MappingItems.VS_SMA_50_DAY.fieldName, 0.15, params, Condition.GREATER_THAN));
					isFirst = false;
				}
				sqlBuffer.append(" ) ");
			}

		}
		/* 13 day SMA is [Above/Below] its 50 day SMA */
		if (IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_1()) || IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_2())) {

			if (IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_1())) {
				sqlBuffer.append(" AND " + EDBConstants.MappingItems.SMA_13.fieldName + Condition.GREATER_THAN_OR_EQUALS.key + EDBConstants.MappingItems.SMA_50.fieldName);
			}
			if (IS_SELECTED.equals(searchStockScreenerBean.get_13_day_sma_2())) {
				sqlBuffer.append(" AND " + EDBConstants.MappingItems.SMA_13.fieldName + Condition.LESS_THAN.key + EDBConstants.MappingItems.SMA_50.fieldName);
			}
		}

		return sqlBuffer;
	}

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @return
	 * @throws SystemException
	 */
	public int countStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws SystemException {
		final String LOCATION = "search(searchStockScreenerBean " + searchStockScreenerBean + ")";
		log.debug(LOCATION + ":: BEGIN");
		StringBuffer sqlBuffer = new StringBuffer(" SELECT count(*) FROM COMPANY_ITEM_CALC_VIEW_TEMP a " + " INNER JOIN INDUSTRY_ITEM_CALC_VIEW_TEMP  b ON a.INDUSTRY_CODE =  b.INDUSTRY_CODE "
				+ " WHERE (1=1) ");
		try {
			int result = 0;
			if (searchStockScreenerBean != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				sqlBuffer.append(this.createQuery(searchStockScreenerBean, params));

				result = H2DAOHelper.query4Int(this.getDataSource(), sqlBuffer.toString(), params);
			}
			log.debug(LOCATION + ":: END:: return: ");
			return result;
		} catch (SystemException syse) {
			log.error(LOCATION + ":: END:: SQL : " + sqlBuffer.toString());
			throw syse;
		} catch (Exception e) {
			log.error(LOCATION + ":: END:: SQL : " + sqlBuffer.toString());
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param symbols
	 * @return
	 */
	public SearchResult<AnalysisIndexingBean> search(SearchStockScreenerBean searchStockScreenerBean, PagingInfo pagingInfo, EDBItemCodeMapping itemCodeMapping) throws SystemException {
		final String LOCATION = "search(searchStockScreenerBean " + searchStockScreenerBean + " , pagingInfo " + pagingInfo + " ,itemCodeMapping " + itemCodeMapping + ")";
		log.debug(LOCATION + ":: BEGIN");
		StringBuilder sqlBuffer = this.generateSelectSQL4StockScreener(itemCodeMapping, searchStockScreenerBean.getLocale());
		try {
			SearchResult<AnalysisIndexingBean> result = null;
			if (searchStockScreenerBean != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				sqlBuffer.append(this.createQuery(searchStockScreenerBean, params));

				// Order by
				if (Validation.isEmpty(searchStockScreenerBean.getSortField())) {
					sqlBuffer.append(" ORDER BY SYMBOL ");
				} else {
					sqlBuffer.append(" ORDER BY " + searchStockScreenerBean.getSortField().trim()).append(" ");
				}
				if (!Validation.isEmpty(searchStockScreenerBean.getSortOrder())) {
					sqlBuffer.append(searchStockScreenerBean.getSortOrder());
				}
				sqlBuffer.append(" NULLS LAST ");

				result = H2DAOHelper.query(this.getDataSource(), sqlBuffer.toString(), params, analysisIndexing4StockRowMapper, pagingInfo);
			}
			log.debug(LOCATION + ":: END:: return: ");
			return (result == null ? new SearchResult<AnalysisIndexingBean>() : result);
		} catch (SystemException syse) {
			log.error(LOCATION + ":: END:: SQL : " + sqlBuffer.toString());
			throw syse;
		} catch (Exception e) {
			log.error(LOCATION + ":: END:: SQL : " + sqlBuffer.toString());
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param symbols
	 * @return
	 */
	public SearchResult<AnalysisIndexingBean> searchAnalysisBySymbol(String[] symbols, PagingInfo pagingInfo, EDBItemCodeMapping itemCodeMapping) throws SystemException {
		final String LOCATION = "searchAnalysisBySymbol(symbols)";
		log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<AnalysisIndexingBean> result = null;
			if (symbols != null && symbols.length > 0) {
				Map<String, Object> params = new HashMap<String, Object>();

				StringBuilder strSQL = this.generateSelectSQL4Analysis(itemCodeMapping);
				strSQL.append(" WHERE (1=1) ");
				for (int i = 0; i < symbols.length; i++) {
					strSQL.append(" AND (").append(H2DBUtils.buildQuery(EDBConstants.MappingItems.SYMBOL.fieldName, symbols, SearchOption.NONE, params)).append(")");
				}

				result = H2DAOHelper.query(this.getDataSource(), strSQL.toString(), params, analysisIndexingRowMapper, pagingInfo);
			}
			log.debug(LOCATION + ":: END");
			return (result == null ? new SearchResult<AnalysisIndexingBean>() : result);
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}
}
