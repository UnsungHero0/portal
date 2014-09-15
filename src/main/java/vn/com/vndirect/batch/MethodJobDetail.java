/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 7, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.batch;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.embeddb.EmbeddedDBManager;
import vn.com.vndirect.embeddb.EmbeddedDBParam;

/**
 * @author tungnq.nguyen
 * 
 */
public class MethodJobDetail {
	private static Logger logger = Logger.getLogger(MethodJobDetail.class);

	@Autowired
	private EmbeddedDBManager embeddedDBManager;
	
	@Autowired
	private EmbeddedDBParam embeddedDBParam;

	/**
	 * @param embeddedDBManager
	 *            the embeddedDBManager to set
	 */
	public void setEmbeddedDBManager(EmbeddedDBManager embeddedDBManager) {
		this.embeddedDBManager = embeddedDBManager;
	}

	/**
	 * @param embeddedDBParam
	 *            the embeddedDBParam to set
	 */
	public void setEmbeddedDBParam(EmbeddedDBParam embeddedDBParam) {
		this.embeddedDBParam = embeddedDBParam;
	}

	/**
	 * init FinfoDB caching data
	 */
	public void initFinfoDBCachingData() {
		final String LOCATION = "createInitEmbeddedDB()";
		try {
			if (!embeddedDBParam.isRebuild()) {
				logger.debug(LOCATION + " :: embedded db - REBUILD - FALSE ...... ");
				return;
			}
			long lStart = (new Date()).getTime();
			long lTemp1 = lStart;
			long lTemp2;

			if (embeddedDBManager.checkInitDB()) {
				logger.debug(LOCATION + " :: embedded db already init...... ");
				return;
			}

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug(LOCATION + " :: createDB...... " + new Date());
			System.out.print(LOCATION + " :: createDB...... " + new Date());
			embeddedDBManager.createDB();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createDB: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitStockExchange...... " + new Date());
			embeddedDBManager.createInitStockExchange();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitStockExchange: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitSecLatestPrice...... " + new Date());
			embeddedDBManager.createInitSecLatestPrice();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitSecLatestPrice: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitItemCodeRef...... " + new Date());
			embeddedDBManager.createInitItemCodeRef();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitItemCodeRef: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitSectorCal...... " + new Date());
			embeddedDBManager.createInitSectorCal();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitSectorCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitIndustryCal...... " + new Date());
			embeddedDBManager.createInitIndustryCal();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitIndustryCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitCompanyCal...... " + new Date());
			embeddedDBManager.createInitCompanyCal();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitCompanyCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitCompanyItemCal...... " + new Date());
			embeddedDBManager.createInitCompanyItemCal();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitCompanyItemCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createInitIndustryItemCal...... " + new Date());
			embeddedDBManager.createInitIndustryItemCal();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createInitIndustryItemCal: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;

			// ++++++++++++++++++++++++++++++++++++++

			// ++++++++++++++++++++++++++++++++++++++
			logger.debug("createTempTables...... " + new Date());
			embeddedDBManager.createTempTables();

			lTemp2 = (new Date()).getTime();
			logger.debug("--> createTempTables: Total Time:...... " + ((lTemp2 - lTemp1) / 1000) + " s");
			lTemp1 = lTemp2;
			// ++++++++++++++++++++++++++++++++++++++

			long lEnd = (new Date()).getTime();
			logger.debug("Total Time:...... " + ((lEnd - lStart) / 1000) + " s");
			System.out.print("Total Time:...... " + ((lEnd - lStart) / 1000) + " s");
		} catch (Exception e) {
			logger.error("initFinfoDBCachingData()", e);
		}
	}

	/**
	 * refresh FinfoDB caching data
	 */
	public void refreshFinfoDBCachingData() {
		try {
			// TODO:
		} catch (Exception e) {
			logger.error("refreshFinfoDBCachingData()", e);
		}
	}

}
