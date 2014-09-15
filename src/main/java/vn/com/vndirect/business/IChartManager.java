/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 5, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.vndirect.commons.jfreechart.ChartInfo;
import vn.com.vndirect.commons.jfreechart.ChartObject;
import vn.com.vndirect.commons.jfreechart.ChartsManager;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.dao.impl.IfoBreakdownViewDAO;
import vn.com.vndirect.dao.impl.IfoMarketCalendarViewDAO;
import vn.com.vndirect.dao.impl.IfoSecPriceViewDAO;
import vn.com.vndirect.domain.extend.SearchIfoMarketCalendar;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.TechnicalAnalysisChart;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author tungnq
 * 
 */
public interface IChartManager extends IBaseManager {

	/**
	 * @param chartManager
	 *            the chartManager to set
	 */
	public void setChartsManager(ChartsManager chartManager);

	public void setFlashChartsManager(vn.com.vndirect.commons.flashchart.ChartsManager flashChartsManager);

	/**
	 * @param ifoSecPriceViewDAO
	 *            the ifoSecPriceViewDAO to set
	 */
	public void setIfoSecPriceViewDAO(IfoSecPriceViewDAO ifoSecPriceViewDAO);

	/**
	 * @param ifoBreakdownViewDAO
	 *            the ifoBreakdownViewDAO to set
	 */
	public void setIfoBreakdownViewDAO(IfoBreakdownViewDAO ifoBreakdownViewDAO);

	/**
	 * 
	 * @param ifoMarketCalendarViewDAO
	 */
	public void setIfoMarketCalendarViewDAO(IfoMarketCalendarViewDAO ifoMarketCalendarViewDAO);

	// +++++++++++++ ============ / Support for Spring Mapping ============
	// ++++++++

	/**
	 * 
	 * @param chartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public ChartObject generateLinerChart(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
	        FunctionalException;

	/**
	 * 
	 * @param chartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public ChartObject generateVolumeChart(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
	        FunctionalException;

	/**
	 * 
	 * @param chartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public ChartObject generateChartForSnapshot(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
	        FunctionalException;

	/**
	 * 
	 * @param chartInfo
	 * @param companyId
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public ChartObject generatePieChart(ChartInfo chartInfo, long companyId) throws SystemException, FunctionalException;

	/**
	 * 
	 * 
	 * @param chartInfo
	 * @param searchIfoSecPrice
	 * @param taChart
	 * @param chartInfoHashcode is used for cache key only
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public ChartObject generateChartForTA(TAChartInfo taChartInfo, SearchIfoSecPrice searchIfoSecPrice,
			TechnicalAnalysisChart taChart, int chartInfoHashcode) throws SystemException, FunctionalException;

	/**
	 * 
	 * @param taChartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForFlashChart(TAChartInfo taChartInfo, SearchIfoSecPrice searchIfoSecPrice)
	        throws SystemException, FunctionalException;

	/**
	 * 
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForHistorical(SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
	        FunctionalException;

	/**
	 * 
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForIntrday(SearchIfoSecPrice searchIfoSecPrice) throws SystemException, FunctionalException;

	/**
	 * 
	 * @param ifoMarketCalendarView
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForHistoryEvents(SearchIfoMarketCalendar searchIfoMarketCalendar) throws SystemException,
	        FunctionalException;

	public ChartObject getChartFromCacheKey(String cacheKey) throws SystemException, FunctionalException;
}
