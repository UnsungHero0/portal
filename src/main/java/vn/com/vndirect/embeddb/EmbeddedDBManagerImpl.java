/**
 * 
 */
package vn.com.vndirect.embeddb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.basicanalysis.BasicAnalysisUtils;
import vn.com.vndirect.business.FinfoDBManager;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.SearchAnalysisIndexingBean;
import vn.com.vndirect.domain.SearchStockExchangeIndexingBean;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.embeddb.CompanyCalcView;
import vn.com.vndirect.domain.embeddb.CompanyItemCalcView;
import vn.com.vndirect.domain.embeddb.DbEmbedConfig;
import vn.com.vndirect.domain.embeddb.DynamicFieldItems;
import vn.com.vndirect.domain.embeddb.DynamicMetaTable;
import vn.com.vndirect.domain.embeddb.IndustryCalcView;
import vn.com.vndirect.domain.embeddb.IndustryItemCalcView;
import vn.com.vndirect.domain.embeddb.ItemCodeRef;
import vn.com.vndirect.domain.embeddb.SearchStockExchange;
import vn.com.vndirect.domain.embeddb.SecPriceView;
import vn.com.vndirect.domain.embeddb.SectorCalcView;
import vn.com.vndirect.domain.embeddb.StockExchange;
import vn.com.vndirect.embeddb.dao.CompanyCalcViewDAO;
import vn.com.vndirect.embeddb.dao.CompanyItemCalcViewDAO;
import vn.com.vndirect.embeddb.dao.DbEmbedConfigDAO;
import vn.com.vndirect.embeddb.dao.DynamicTableDAO;
import vn.com.vndirect.embeddb.dao.IfoDBLoaderDAO;
import vn.com.vndirect.embeddb.dao.IndustryCalcViewDAO;
import vn.com.vndirect.embeddb.dao.IndustryItemCalcViewDAO;
import vn.com.vndirect.embeddb.dao.ItemCodeRefDAO;
import vn.com.vndirect.embeddb.dao.RunDBScriptDAO;
import vn.com.vndirect.embeddb.dao.SecLastPriceViewDAO;
import vn.com.vndirect.embeddb.dao.SectorCalcViewDAO;
import vn.com.vndirect.embeddb.dao.StockExchangeDAO;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Blue9Frog
 * 
 */
@Component
public class EmbeddedDBManagerImpl implements EmbeddedDBManager, FinfoDBManager {
	private static final Log log = LogFactory.getLog(EmbeddedDBManagerImpl.class);
	
	@Autowired
	private EmbeddedDBParam embeddedDBParam;
	
	@Autowired
	private EDBItemCodeMapping dbItemCodeMapping;

	@Autowired
	private RunDBScriptDAO runDBScriptDAO;
	
	@Autowired
	private CompanyItemCalcViewDAO companyItemCalcViewDAO;
	
	@Autowired
	private IndustryItemCalcViewDAO industryItemCalcViewDAO;

	@Autowired
	private IndustryCalcViewDAO industryCalcViewDAO;
	
	@Autowired
	private ItemCodeRefDAO itemCodeRefDAO;
	
	@Autowired
	private SectorCalcViewDAO sectorCalcViewDAO;
	
	@Autowired
	private CompanyCalcViewDAO companyCalcViewDAO;
	
	@Autowired
	private StockExchangeDAO stockExchangeDAO;
	
	@Autowired
	private DynamicTableDAO dynamicTableDAO;
	
	@Autowired
	private DbEmbedConfigDAO dbEmbedConfigDAO;

	@Autowired
	private SecLastPriceViewDAO secLastPriceViewDAO;

	@Autowired
	private IfoDBLoaderDAO ifoDBLoaderDAO;
	
	/**
	 * @param dbItemCodeMapping
	 *            the dbItemCodeMapping to set
	 */
	public void setDbItemCodeMapping(EDBItemCodeMapping dbItemCodeMapping) {
		this.dbItemCodeMapping = dbItemCodeMapping;
	}

	/**
	 * @param runDBScriptDAO
	 *            the runDBScriptDAO to set
	 */
	public void setRunDBScriptDAO(RunDBScriptDAO runDBScriptDAO) {
		this.runDBScriptDAO = runDBScriptDAO;
	}

	/**
	 * @param embeddedDBParam
	 *            the embeddedDBParam to set
	 */
	public void setEmbeddedDBParam(EmbeddedDBParam embeddedDBParam) {
		this.embeddedDBParam = embeddedDBParam;
	}

	/**
	 * @param stockExchangeDAO
	 *            the stockExchangeDAO to set
	 */
	public void setStockExchangeDAO(StockExchangeDAO stockExchangeDAO) {
		this.stockExchangeDAO = stockExchangeDAO;
	}

	/**
	 * @param itemCodeRefDAO
	 *            the itemCodeRefDAO to set
	 */
	public void setItemCodeRefDAO(ItemCodeRefDAO itemCodeRefDAO) {
		this.itemCodeRefDAO = itemCodeRefDAO;
	}

	/**
	 * @param companyItemCalcViewDAO
	 *            the companyItemCalcViewDAO to set
	 */
	public void setCompanyItemCalcViewDAO(CompanyItemCalcViewDAO companyItemCalcViewDAO) {
		this.companyItemCalcViewDAO = companyItemCalcViewDAO;
	}

	/**
	 * @param industryCalcViewDAO
	 *            the industryCalcViewDAO to set
	 */
	public void setIndustryCalcViewDAO(IndustryCalcViewDAO industryCalcViewDAO) {
		this.industryCalcViewDAO = industryCalcViewDAO;
	}

	/**
	 * @param sectorCalcViewDAO
	 *            the sectorCalcViewDAO to set
	 */
	public void setSectorCalcViewDAO(SectorCalcViewDAO sectorCalcViewDAO) {
		this.sectorCalcViewDAO = sectorCalcViewDAO;
	}

	/**
	 * @param ifoDBLoaderDAO
	 *            the ifoDBLoaderDAO to set
	 */
	public void setIfoDBLoaderDAO(IfoDBLoaderDAO ifoDBLoaderDAO) {
		this.ifoDBLoaderDAO = ifoDBLoaderDAO;
	}

	/**
	 * @param companyCalcViewDAO
	 *            the companyCalcViewDAO to set
	 */
	public void setCompanyCalcViewDAO(CompanyCalcViewDAO companyCalcViewDAO) {
		this.companyCalcViewDAO = companyCalcViewDAO;
	}

	/**
	 * @param dynamicTableDAO
	 *            the dynamicTableDAO to set
	 */
	public void setDynamicTableDAO(DynamicTableDAO dynamicTableDAO) {
		this.dynamicTableDAO = dynamicTableDAO;
	}

	/**
	 * @param dbEmbedConfigDAO
	 *            the dbEmbedConfigDAO to set
	 */
	public void setDbEmbedConfigDAO(DbEmbedConfigDAO dbEmbedConfigDAO) {
		this.dbEmbedConfigDAO = dbEmbedConfigDAO;
	}

	/**
	 * @param industryItemCalcViewDAO
	 *            the industryItemCalcViewDAO to set
	 */
	public void setIndustryItemCalcViewDAO(IndustryItemCalcViewDAO industryItemCalcViewDAO) {
		this.industryItemCalcViewDAO = industryItemCalcViewDAO;
	}

	/**
	 * @param secLastPriceViewDAO
	 *            the secLastPriceViewDAO to set
	 */
	public void setSecLastPriceViewDAO(SecLastPriceViewDAO secLastPriceViewDAO) {
		this.secLastPriceViewDAO = secLastPriceViewDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitEmbeddedDB()
	 */
	public void createInitEmbeddedDB() throws SystemException, FunctionalException {
		final String LOCATION = "createInitEmbeddedDB()";
		long lStart = (new Date()).getTime();
		long lTemp1 = lStart;
		long lTemp2;

		if (checkInitDB()) {
			if (log.isDebugEnabled())
				log.debug(LOCATION + " :: embedded db already init...... ");
			return;
		}

		// ++++++++++++++++++++++++++++++++++++++
		if (embeddedDBParam.isRebuild()) {
			if (log.isDebugEnabled())
				log.debug(LOCATION + " :: createDB...... " + new Date());
			this.createDB();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createDB: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitStockExchange...... " + new Date());
			this.createInitStockExchange();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitStockExchange: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitSecLatestPrice...... " + new Date());
			this.createInitSecLatestPrice();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitSecLatestPrice: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitItemCodeRef...... " + new Date());
			this.createInitItemCodeRef();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitItemCodeRef: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitSectorCal...... " + new Date());
			this.createInitSectorCal();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitSectorCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitIndustryCal...... " + new Date());
			this.createInitIndustryCal();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitIndustryCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitCompanyCal...... " + new Date());
			this.createInitCompanyCal();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitCompanyCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitCompanyItemCal...... " + new Date());
			this.createInitCompanyItemCal();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitCompanyItemCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			if (log.isDebugEnabled())
				log.debug("createInitIndustryItemCal...... " + new Date());
			this.createInitIndustryItemCal();

			lTemp2 = (new Date()).getTime();
			if (log.isDebugEnabled())
				log.debug("--> createInitIndustryItemCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
		}

		// ++++++++++++++++++++++++++++++++++++++
		if (log.isDebugEnabled())
			log.debug("createTempTables...... " + new Date());
		this.createTempTables();

		lTemp2 = (new Date()).getTime();
		if (log.isDebugEnabled())
			log.debug("--> createTempTables: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
		lTemp1 = lTemp2;
		// ++++++++++++++++++++++++++++++++++++++

		long lEnd = (new Date()).getTime();
		if (log.isDebugEnabled())
			log.debug("Total Time:...... " + ((lEnd - lStart) / 1000) + " s");
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkInitDB() {
		final String LOCATION = "checkInitDB()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		boolean rs = false;
		try {
			boolean hasTable = runDBScriptDAO.hasTableInDB(EDBConstants.Tables.DB_EMBED_CONFIG);
			if (hasTable) {
				DbEmbedConfig embedConfig = new DbEmbedConfig();
				embedConfig.setGroupCode(EDBConstants.DBEmbedConfig.GROUP_CODE.SYS_CFG);
				embedConfig.setItemCode(EDBConstants.DBEmbedConfig.ITEM_CODE.INIT_DB);

				List<DbEmbedConfig> listEmbedConfig = dbEmbedConfigDAO.getDbEmbedConfig(embedConfig);
				if (listEmbedConfig != null && listEmbedConfig.size() > 0) {
					rs = VNDirectDateUtils.isToday(listEmbedConfig.get(0).getDateValue());
				}
			}
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
		}
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: END - rs:" + rs);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createTempTables()
	 */
	public void createTempTables() throws SystemException, FunctionalException {
		final String LOCATION = "createTempTables()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			this.createIndustryCalTempTable();

			// +++
			this.updateInitIndustryCalTempTable(industryItemCalcViewDAO.getAllIndustryItemCalcView());
			// ---

			this.createCompanyItemCalTempTable();

			// +++
			this.updateInitCompanyCalTempTable(companyItemCalcViewDAO.getAllCompanyItemCalcView());
			// ---

			// +++ caculate pct of 52 week low/high
			this.updatePtc52Week();

			// +++ caculate vs.13 days/vs.50 days
			this.updateVsSMA();
			// ---

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#cleanTable(java.lang.String)
	 */
	public void cleanTable(String table) throws SystemException {
		final String LOCATION = "cleanTable(table)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN - table:" + table);
		try {
			runDBScriptDAO.processScript("DELETE FROM " + table);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createCompanyItemCalTempTable()
	 */
	public void createCompanyItemCalTempTable() throws SystemException, FunctionalException {
		final String LOCATION = "createCompanyItemCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			/*
			 * String table = EDBConstants.Tables.Temp.COMPANY_ITEM_CALC_VIEW; runDBScriptDAO.processDropTable(table);
			 * 
			 * DynamicMetaTable fields = new DynamicMetaTable(table);
			 * 
			 * List<String> listCompItemCode = dbItemCodeMapping.getListCompanyItemCode(); if(listCompItemCode != null && listCompItemCode.size() > 0) { for (String itemCode : listCompItemCode) {
			 * if(itemCode != null && itemCode.length() > 0) { fields.put(DynamicMetaTable.generateFieldName(itemCode), "NUMBER(30,2) NULL DEFAULT 0"); } } } else { String locale =
			 * embeddedDBParam.getDefaultLang(); List<ItemCodeRef> listItemCodeRef = itemCodeRefDAO.searchByGroupCode(EDBConstants.GROUP_CODE.COMPANY_ITEM_CALC_VIEW, locale); for (ItemCodeRef
			 * itemCodeRef : listItemCodeRef) { if(itemCodeRef.getItemCode() != null && itemCodeRef.getItemCode().length() > 0) {
			 * fields.put(DynamicMetaTable.generateFieldName(itemCodeRef.getItemCode()), "NUMBER(30,2) NULL DEFAULT 0"); } } } fields.put(EDBConstants.MappingItems.COMPANY_ID.fieldName, "NUMBER(10)");
			 * fields.put(EDBConstants.MappingItems.SYMBOL.fieldName, "VARCHAR(20)"); fields.put(EDBConstants.MappingItems.INDUSTRY_CODE.fieldName, "VARCHAR(20)");
			 * fields.put(EDBConstants.MappingItems.SECTOR_CODE.fieldName, "VARCHAR(20)"); fields.put(EDBConstants.MappingItems.EXCHANGE_CODE.fieldName, "VARCHAR(20)");
			 * 
			 * List<String> options = new ArrayList<String>(); options.add("PRIMARY KEY (`" + EDBConstants.MappingItems.SYMBOL.fieldName + "`)");
			 * 
			 * runDBScriptDAO.processCreateTable(table, fields, options);
			 * 
			 * //+++ keep table field dynamicMetaTableHolder.put(table, fields);
			 */

			// +++ init symbol
			dynamicTableDAO.insertInit4CompanyItemCalcViewTemp();
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createIndustryCalTempTable()
	 */
	public void createIndustryCalTempTable() throws SystemException, FunctionalException {
		final String LOCATION = "createIndustryCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			/*
			 * String table = EDBConstants.Tables.Temp.INDUSTRY_ITEM_CALC_VIEW; runDBScriptDAO.processDropTable(table);
			 * 
			 * DynamicMetaTable fields = new DynamicMetaTable(table);
			 * 
			 * List<String> listAnalysisItemCode = dbItemCodeMapping.getListIndustryItemCode(); if(listAnalysisItemCode != null && listAnalysisItemCode.size() > 0) { for (String itemCode :
			 * listAnalysisItemCode) { if(itemCode != null && itemCode.length() > 0) { fields.put(DynamicMetaTable.generateFieldName(itemCode), "NUMBER(30,2) NULL DEFAULT 0"); } } } else { String
			 * locale = embeddedDBParam.getDefaultLang(); List<ItemCodeRef> listItemCodeRef = itemCodeRefDAO.searchByGroupCode(EDBConstants.GROUP_CODE.INDUSTRY_ITEM_CALC_VIEW, locale); for
			 * (ItemCodeRef itemCodeRef : listItemCodeRef) { if(itemCodeRef.getItemCode() != null && itemCodeRef.getItemCode().length() > 0) {
			 * fields.put(DynamicMetaTable.generateFieldName(itemCodeRef.getItemCode()), "NUMBER(30,2) NULL DEFAULT 0"); } } }
			 * 
			 * fields.put(EDBConstants.MappingItems.INDUSTRY_CODE.fieldName, "VARCHAR(20)");
			 * 
			 * List<String> options = new ArrayList<String>(); options.add("PRIMARY KEY (`" + EDBConstants.MappingItems.INDUSTRY_CODE.fieldName + "`)");
			 * 
			 * runDBScriptDAO.processCreateTable(table, fields, options);
			 * 
			 * //+++ keep table field dynamicMetaTableHolder.put(table, fields);
			 */

			// +++ init symbol
			dynamicTableDAO.insertInit4IndustryItemCalcViewTempCode();
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private void updatePtc52Week() throws SystemException, FunctionalException {
		final String LOCATION = "createIndustryCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			// +++ init symbol
			String sqlWeekLow = "update company_item_calc_view_temp  set pct_above_52_week_low =( close_price -f51002)/f51002*100 where f51002>0";
			String sqlWeekHigh = "update company_item_calc_view_temp  set pct_below_52_week_high =( close_price -f51001)/f51001*100 where f51001>0";
			runDBScriptDAO.processScript(sqlWeekLow);
			runDBScriptDAO.processScript(sqlWeekHigh);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private void updateVsSMA() throws SystemException, FunctionalException {
		final String LOCATION = "createIndustryCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			// +++ init symbol
			String sqlvs_sma_13_day = "update company_item_calc_view_temp set vs_sma_13_day = (close_price -f1000011)/f1000011 where f1000011>0";
			String sqlvs_sma_50_day = "update company_item_calc_view_temp set vs_sma_50_day = (close_price -f1000012)/f1000012 where f1000012>0";
			runDBScriptDAO.processScript(sqlvs_sma_13_day);
			runDBScriptDAO.processScript(sqlvs_sma_50_day);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#updateInitCompanyCalTempTable()
	 */
	public void updateInitCompanyCalTempTable() throws SystemException, FunctionalException {
		final String LOCATION = "updateInitCompanyCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			this.updateInitCompanyCalTempTable(companyItemCalcViewDAO.getAllCompanyItemCalcView());
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#updateInitCompanyCalTempTable(java.util.List)
	 */
	public void updateInitCompanyCalTempTable(List<CompanyItemCalcView> listCompanyItemCalcView) throws SystemException, FunctionalException {
		final String LOCATION = "updateInitCompanyCalTempTable(listCompanyItemCalcView)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		if (listCompanyItemCalcView == null || listCompanyItemCalcView.size() == 0) {
			return;
		}

		try {
			String table = EDBConstants.Tables.Temp.COMPANY_ITEM_CALC_VIEW;
			// DynamicMetaTable dynamicMetaTable = dynamicMetaTableHolder.get(table);
			//                        
			// if(dynamicMetaTable == null) {
			// return;
			// }

			DynamicFieldItems<Double> dynamicFields = null;
			String symbol = null;

			Map<String, DynamicFieldItems<Double>> symbolFieldValues = new HashMap<String, DynamicFieldItems<Double>>();

			for (CompanyItemCalcView companyItemCalcView : listCompanyItemCalcView) {
				if (companyItemCalcView.getSymbol() != null) {
					if (!companyItemCalcView.getSymbol().equalsIgnoreCase(symbol)) {
						symbol = companyItemCalcView.getSymbol();
						dynamicFields = symbolFieldValues.get(symbol);
						if (dynamicFields == null) {
							dynamicFields = new DynamicFieldItems<Double>(symbol);
							symbolFieldValues.put(symbol, dynamicFields);
						}
					}
					if (dynamicFields != null) {
						dynamicFields.put(DynamicMetaTable.generateFieldName(companyItemCalcView.getItemCode()), companyItemCalcView.getNumericValue());
					}
				}
			}

			// +++ update data
			for (DynamicFieldItems<Double> dynamicFieldItems : symbolFieldValues.values()) {
				dynamicTableDAO.updateFieldItems4CompanyItemCalcViewTemp(table, dynamicFieldItems);
			}

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#updateInitIndustryCalTempTable()
	 */
	public void updateInitIndustryCalTempTable() throws SystemException, FunctionalException {
		final String LOCATION = "updateInitIndustryCalTempTable()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			this.updateInitIndustryCalTempTable(industryItemCalcViewDAO.getAllIndustryItemCalcView());
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException syse) {
			throw syse;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#updateInitIndustryCalTempTable(java.util.List)
	 */
	public void updateInitIndustryCalTempTable(List<IndustryItemCalcView> listIndustryItemCalcView) throws SystemException, FunctionalException {
		final String LOCATION = "updateInitIndustryCalTempTable(listIndustryItemCalcView)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");

		if (listIndustryItemCalcView == null || listIndustryItemCalcView.size() == 0) {
			return;
		}

		try {
			String table = EDBConstants.Tables.Temp.INDUSTRY_ITEM_CALC_VIEW;
			/*
			 * DynamicMetaTable dynamicMetaTable = dynamicMetaTableHolder.get(table);
			 * 
			 * if(dynamicMetaTable == null) { return; }
			 */

			DynamicFieldItems<Double> dynamicFields = null;
			String industryCode = null;

			Map<String, DynamicFieldItems<Double>> symbolFieldValues = new HashMap<String, DynamicFieldItems<Double>>();

			for (IndustryItemCalcView industryItemCalcView : listIndustryItemCalcView) {
				if (industryItemCalcView.getIndustryCode() != null) {
					if (!industryItemCalcView.getIndustryCode().equalsIgnoreCase(industryCode)) {
						industryCode = industryItemCalcView.getIndustryCode();
						dynamicFields = symbolFieldValues.get(industryCode);
						if (dynamicFields == null) {
							dynamicFields = new DynamicFieldItems<Double>();
							dynamicFields.setIndustryCode(industryCode);
							symbolFieldValues.put(industryCode, dynamicFields);
						}
					}

					if (dynamicFields != null) {
						dynamicFields.put(DynamicMetaTable.generateFieldName(industryItemCalcView.getItemCode()), industryItemCalcView.getNumericValue());
					}
				}
			}

			// +++ update data
			for (DynamicFieldItems<Double> dynamicFieldItems : symbolFieldValues.values()) {
				dynamicTableDAO.updateFieldItems4IndustryItemCalcViewTempCode(table, dynamicFieldItems);
			}

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createDB()
	 */
	public void createDB() throws SystemException, FunctionalException {
		final String LOCATION = "createDB()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String sqlScriptPath = embeddedDBParam.getInitDBScriptPath();
			String scriptOption = embeddedDBParam.getInitOption();
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: sqlScriptPath:" + sqlScriptPath);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: scriptOption:" + scriptOption);
			scriptOption = (scriptOption == null ? "" : scriptOption.trim());
			runDBScriptDAO.processRunscript(sqlScriptPath, scriptOption);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#cleanDB()
	 */
	public void cleanDB() throws SystemException, FunctionalException {
		final String LOCATION = "cleanDB()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String sqlScriptPath = embeddedDBParam.getCleanDBScriptPath();
			String scriptOption = embeddedDBParam.getInitOption();
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: sqlScriptPath:" + sqlScriptPath);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: scriptOption:" + scriptOption);
			scriptOption = (scriptOption == null ? "" : scriptOption.trim());
			runDBScriptDAO.processRunscript(sqlScriptPath, scriptOption);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitStockExchange()
	 */
	public void createInitStockExchange() throws SystemException, FunctionalException {
		final String LOCATION = "createInitStockExchange()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			List<StockExchange> listStockExchange = ifoDBLoaderDAO.getAllStockExchange();
			if (listStockExchange != null && listStockExchange.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listStockExchange sie:" + listStockExchange.size());
				Map<String, StockExchange> listAllSymbol = new HashMap<String, StockExchange>();
				for (StockExchange stockExchange : listStockExchange) {
					stockExchangeDAO.insert(stockExchange);
					listAllSymbol.put(stockExchange.getSymbol().toUpperCase(), stockExchange);
				}
				BasicAnalysisUtils.setListAllSymbol(listAllSymbol);
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitItemCodeRef()
	 */
	public void createInitItemCodeRef() throws SystemException, FunctionalException {
		final String LOCATION = "createInitItemCodeRef()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			List<ItemCodeRef> listItemCodeRef = null;

			// +++ 1: Load Industry ItemNames
			listItemCodeRef = ifoDBLoaderDAO.getAllItemCodeRef4IndustryItem();
			if (listItemCodeRef != null && listItemCodeRef.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: Industry Item ItemCodeRef.size():" + listItemCodeRef.size());
				for (ItemCodeRef itemCodeRef : listItemCodeRef) {
					if (checkItemCodeRef(itemCodeRef)) {
						itemCodeRef.setGroupCode(EDBConstants.GROUP_CODE.INDUSTRY_ITEM_CALC_VIEW);
						itemCodeRefDAO.insert(itemCodeRef);
					}
				}
			}

			// +++ 2: Load Company ItemNames
			listItemCodeRef = ifoDBLoaderDAO.getAllItemCodeRef4CompanyItem();
			if (listItemCodeRef != null && listItemCodeRef.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: Company ItemCodeRef.size():" + listItemCodeRef.size());
				for (ItemCodeRef itemCodeRef : listItemCodeRef) {
					if (checkItemCodeRef(itemCodeRef)) {
						itemCodeRef.setGroupCode(EDBConstants.GROUP_CODE.COMPANY_ITEM_CALC_VIEW);
						itemCodeRefDAO.insert(itemCodeRef);
					}
				}
			}

			// +++ 3: Load Industry ItemNames
			listItemCodeRef = ifoDBLoaderDAO.getAllItemCodeRef4Industry();
			if (listItemCodeRef != null && listItemCodeRef.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: Sector ItemCodeRef.size():" + listItemCodeRef.size());
				for (ItemCodeRef itemCodeRef : listItemCodeRef) {
					if (checkItemCodeRef(itemCodeRef)) {
						itemCodeRef.setGroupCode(EDBConstants.GROUP_CODE.SECTOR_CALC_VIEW);
						itemCodeRefDAO.insert(itemCodeRef);
					}
				}
			}

			// +++ 4: Load Sector ItemNames
			listItemCodeRef = ifoDBLoaderDAO.getAllItemCodeRef4Sector();
			if (listItemCodeRef != null && listItemCodeRef.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: Industry ItemCodeRef.size():" + listItemCodeRef.size());
				for (ItemCodeRef itemCodeRef : listItemCodeRef) {
					if (checkItemCodeRef(itemCodeRef)) {
						itemCodeRef.setGroupCode(EDBConstants.GROUP_CODE.INDUSTRY_CALC_VIEW);
						itemCodeRefDAO.insert(itemCodeRef);
					}
				}
			}

			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private boolean checkItemCodeRef(ItemCodeRef bean) {
		return (bean != null && bean.getItemCode() != null && bean.getItemName() != null && bean.getLocale() != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitIndustryItemCal()
	 */
	public void createInitIndustryItemCal() throws SystemException, FunctionalException {
		final String LOCATION = "createInitIndustryItemCal()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String locale = embeddedDBParam.getDefaultLang();
			List<IndustryItemCalcView> listIndustryItemCalcView = ifoDBLoaderDAO.loadIndustryItemCalWithoutName(locale, dbItemCodeMapping.getListIndustryItemCode());
			if (listIndustryItemCalcView != null && listIndustryItemCalcView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listIndustryItemCalcView size:" + listIndustryItemCalcView.size());
				for (IndustryItemCalcView industryItemCalcView : listIndustryItemCalcView) {
					if (industryItemCalcView.getIndustryCode() != null) {
						industryItemCalcViewDAO.insert(industryItemCalcView);
					}
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.embeddb.EmbeddedDBManager#createInitSecLatestPrice()
	 */
	public void createInitSecLatestPrice() throws SystemException, FunctionalException {
		final String LOCATION = "createInitSecLatestPrice()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			List<SecPriceView> listSecPriceView = ifoDBLoaderDAO.loadSecLatestPrice();
			if (listSecPriceView != null && listSecPriceView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listSecPriceView sie:" + listSecPriceView.size());
				for (SecPriceView secPriceView : listSecPriceView) {
					secLastPriceViewDAO.insert(secPriceView);
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitCompanyItemCal()
	 */
	public void createInitCompanyItemCal() throws SystemException, FunctionalException {
		final String LOCATION = "createInitCompanyItemCal()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String locale = embeddedDBParam.getDefaultLang();
			List<CompanyItemCalcView> listCompanyItemCalcView = ifoDBLoaderDAO.loadCompanyItemCalWithoutName(locale, dbItemCodeMapping.getListCompanyItemCode());
			if (listCompanyItemCalcView != null && listCompanyItemCalcView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listCompanyItemCalcView size:" + listCompanyItemCalcView.size());
				for (CompanyItemCalcView companyItemCalcView : listCompanyItemCalcView) {
					if (companyItemCalcView.getSymbol() != null) {
						companyItemCalcViewDAO.insert(companyItemCalcView);
					}
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitIndustryCal()
	 */
	public void createInitIndustryCal() throws SystemException, FunctionalException {
		final String LOCATION = "createInitIndustryCal()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String locale = embeddedDBParam.getDefaultLang();
			List<IndustryCalcView> listIndustryCalcView = ifoDBLoaderDAO.loadIndustrysWithoutName(locale);
			if (listIndustryCalcView != null && listIndustryCalcView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listIndustryCalcView size:" + listIndustryCalcView.size());
				for (IndustryCalcView industryCalcView : listIndustryCalcView) {
					if (industryCalcView.getSectorCode() != null && industryCalcView.getIndustryCode() != null) {
						industryCalcViewDAO.insert(industryCalcView);
					}
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitSectorCal()
	 */
	public void createInitSectorCal() throws SystemException, FunctionalException {
		final String LOCATION = "createInitSectorCal()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String locale = embeddedDBParam.getDefaultLang();
			List<SectorCalcView> listSectorCalcView = ifoDBLoaderDAO.loadSectorsWithoutName(locale);
			if (listSectorCalcView != null && listSectorCalcView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listSectorCalcView size:" + listSectorCalcView.size());
				for (SectorCalcView sectorCalcView : listSectorCalcView) {
					if (sectorCalcView.getSectorCode() != null) {
						sectorCalcViewDAO.insert(sectorCalcView);
					}
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.db.h2.EmbeddedDBManager#createInitCompanyCal()
	 */
	public void createInitCompanyCal() throws SystemException, FunctionalException {
		final String LOCATION = "createInitCompanyCal()";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			String locale = embeddedDBParam.getDefaultLang();
			List<CompanyCalcView> listCompanyCalcView = ifoDBLoaderDAO.loadCompanyCalWithoutName(locale);
			if (listCompanyCalcView != null && listCompanyCalcView.size() > 0) {
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: listCompanyCalcView size:" + listCompanyCalcView.size());
				for (CompanyCalcView companyCalcView : listCompanyCalcView) {
					if (companyCalcView.getSectorCode() != null && companyCalcView.getIndustryCode() != null && companyCalcView.getSecCode() != null) {
						companyCalcViewDAO.insert(companyCalcView);
					}
				}
			}
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	// ********************************************* FOR QUERY ********************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#initFinfoDBCachingData(boolean)
	 */
	public void initFinfoDBCachingData(boolean isRefresh) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#count(vn.com.vndirect.lucene.finfodb.beans.SearchStockScreenerBean, int)
	 */
	public int count(SearchStockScreenerBean searchStockScreenerBean, int countType) throws FunctionalException, SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#calcStockScreener(vn.com.vndirect.lucene.finfodb.beans.SearchStockScreenerBean)
	 */
	public SearchStockScreenerBean calcStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException {
		final String LOCATION = "calcStockScreener(SearchStockScreenerBean:: " + searchStockScreenerBean + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			dynamicTableDAO.calcStockScreener(searchStockScreenerBean);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return searchStockScreenerBean;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#getListCompaniesByIndustry(vn.com.vndirect.domain.IfoCompanyCalcView, vn.com.vndirect.domain.PagingInfo)
	 */
	public SearchResult<IfoCompanyCalcView> getListCompaniesByIndustry(IfoCompanyCalcView searchObj, PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "getListCompaniesWithPaging(IfoCompanyCalcView:: " + searchObj + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<IfoCompanyCalcView> results = companyCalcViewDAO.getListCompaniesByIndustry(searchObj, pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#countStockScreener(vn.com.vndirect.lucene.finfodb.beans.SearchStockScreenerBean)
	 */
	public int countStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException {
		final String LOCATION = "countStockScreener(SearchStockScreenerBean:: " + searchStockScreenerBean + ")";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			int count = dynamicTableDAO.countStockScreener(searchStockScreenerBean);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return count;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#search(vn.com.vndirect.lucene.finfodb.beans.SearchStockScreenerBean, vn.com.vndirect.lucene.core.PagingInfo)
	 */
	public SearchResult<AnalysisIndexingBean> search(SearchStockScreenerBean searchStockScreenerBean, PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "search(SearchStockScreenerBean, pagingInfo)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<AnalysisIndexingBean> result = dynamicTableDAO.search(searchStockScreenerBean, pagingInfo, dbItemCodeMapping);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#search(vn.com.vndirect.lucene.finfodb.beans.SearchStockExchangeIndexingBean)
	 */
	public List<StockExchangeIndexingBean> search(SearchStockExchangeIndexingBean searchStockExchangeIndexingBean) throws FunctionalException, SystemException {
		return search(searchStockExchangeIndexingBean, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#search(vn.com.vndirect.lucene.finfodb.beans.SearchAnalysisIndexingBean, vn.com.vndirect.lucene.core.PagingInfo)
	 */
	public SearchResult<AnalysisIndexingBean> search(SearchAnalysisIndexingBean searchAnalysisIndexingBean, PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "search(searchAnalysisIndexingBean, pagingInfo)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			SearchResult<AnalysisIndexingBean> result = dynamicTableDAO.searchAnalysisBySymbol(searchAnalysisIndexingBean.getSymbols(), pagingInfo, dbItemCodeMapping);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.FinfoDBManager#search(vn.com.vndirect.lucene.finfodb.beans.SearchStockExchangeIndexingBean, vn.com.vndirect.lucene.core.PagingInfo)
	 */
	public List<StockExchangeIndexingBean> search(SearchStockExchangeIndexingBean searchStockExchangeIndexingBean, PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "search(searchStockExchangeIndexingBean, pagingInfo)";
		if (log.isDebugEnabled())
			log.debug(LOCATION + ":: BEGIN");
		try {
			List<StockExchangeIndexingBean> result = stockExchangeDAO.search(searchStockExchangeIndexingBean, pagingInfo);
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: END");
			return result;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}

	public SearchResult<StockExchange> searchStockExchange(SearchStockExchange searchStockExchange, vn.com.web.commons.domain.db.PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchStockExchange(searchStockExchange, pagingInfo)";
		if (log.isDebugEnabled())
			if (log.isDebugEnabled())
				log.debug(LOCATION + ":: BEGIN");
		try {
			vn.com.web.commons.domain.db.SearchResult<StockExchange> result = null;
			if (searchStockExchange != null) {
				pagingInfo = (pagingInfo == null ? new PagingInfo() : pagingInfo);
				result = stockExchangeDAO.search(searchStockExchange, pagingInfo);
			}
			if (log.isDebugEnabled())
				if (log.isDebugEnabled())
					log.debug(LOCATION + ":: END - size: " + (result == null ? 0 : result.size()));
			return result;
		} catch (SystemException sys) {
			throw sys;
		} catch (Exception e) {
			log.error(LOCATION + ":Exception " + e);
			throw new SystemException(LOCATION, e);
		}
	}
}
