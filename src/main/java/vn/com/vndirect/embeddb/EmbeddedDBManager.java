package vn.com.vndirect.embeddb;

import java.util.List;

import vn.com.vndirect.domain.embeddb.CompanyItemCalcView;
import vn.com.vndirect.domain.embeddb.IndustryItemCalcView;
import vn.com.vndirect.domain.embeddb.SearchStockExchange;
import vn.com.vndirect.domain.embeddb.StockExchange;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface EmbeddedDBManager {
	/**
	 * 
	 * @return
	 */
	public boolean checkInitDB();

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitEmbeddedDB() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createTempTables() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createDB() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void cleanDB() throws SystemException, FunctionalException;

	/**
	 * 
	 * @param table
	 * @throws SystemException
	 */
	public void cleanTable(String table) throws SystemException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitStockExchange() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitItemCodeRef() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitIndustryItemCal() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitCompanyItemCal() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitCompanyCal() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitIndustryCal() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitSectorCal() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createCompanyItemCalTempTable() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createIndustryCalTempTable() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void createInitSecLatestPrice() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void updateInitIndustryCalTempTable() throws SystemException, FunctionalException;

	/**
	 * 
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void updateInitCompanyCalTempTable() throws SystemException, FunctionalException;

	/**
	 * 
	 * @param listCompanyItemCalcView
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void updateInitCompanyCalTempTable(List<CompanyItemCalcView> listCompanyItemCalcView) throws SystemException, FunctionalException;

	/**
	 * 
	 * @param listIndustryItemCalcView
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public void updateInitIndustryCalTempTable(List<IndustryItemCalcView> listIndustryItemCalcView) throws SystemException, FunctionalException;

	/**
	 * 
	 * @param searchStockExchange
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<StockExchange> searchStockExchange(SearchStockExchange searchStockExchange, PagingInfo pagingInfo) throws FunctionalException, SystemException;
}
