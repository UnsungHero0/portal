/**
 * 
 */
package vn.com.vndirect.business;

import java.util.Collection;
import java.util.List;

import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.IfoForeignTradingView;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoTradingStatisticsView;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.extend.IfoSecPriceViewExt;
import vn.com.vndirect.domain.extend.IfoTradingStatisticsViewExt;
import vn.com.vndirect.domain.extend.SearchIfoDocument;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("rawtypes")
public interface IListedMarketManager extends IBaseManager {

	/**
	 * @param searchObj
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<IfoMarketCalendarView> searchIfoMarketCalendar(IfoMarketCalendarView searchObj, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObjTemp
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	Collection getRightsDateByMonth(IfoMarketCalendarView searchObjTemp) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObjTemp
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */

	Collection getEventDateByMonth(IfoMarketCalendarView searchObjTemp) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getIfoCompanyCalcViewBySymbol(IfoCompanyCalcView searchObj) throws FunctionalException,
	        SystemException;

	/**
	 * @param ifoDocument
	 * @param pagingInfo
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<IfoDocument> searchSSCFilling(SearchIfoDocument ifoDocument, PagingInfo pagingInfo) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List getBalancesheetViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List fiscalYearListBalancesheet(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String fiscalQuarterBalancesheet(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List fiscalYearListIncomeStatement(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List getIncomeViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List fiscalYearListCashFlow(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * @param searchObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List getCashflowViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException;

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoSecPriceView> searchHistoricalPrice(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException;

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics4Symbol(
	        SearchMarketStatisticsView searchMarketStatisticsView, PagingInfo pagingInfo) throws FunctionalException,
	        SystemException;

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoForeignTradingView> searchForeignTrading4Symbol(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException;

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException;

	/**
	 * @param searchMarketStatisticsView
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoForeignTradingView> searchForeignTrading(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchIfoSecPriceView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoSecPriceViewExt> reportHistoricalPrice(SearchMarketStatisticsView searchIfoSecPriceView)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchIfoTradingStatisticsView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoTradingStatisticsViewExt> reportTradingStatistics(
	        SearchMarketStatisticsView searchIfoTradingStatisticsView) throws FunctionalException, SystemException;

}