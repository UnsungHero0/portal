/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 5, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.vndirect.commons.flashchart.beans.FlashEventBean;
import vn.com.vndirect.commons.flashchart.beans.FlashPriceBean;
import vn.com.vndirect.commons.flashchart.beans.FlashPriceBeanComparator;
import vn.com.vndirect.commons.jfreechart.ChartConstants;
import vn.com.vndirect.commons.jfreechart.ChartInfo;
import vn.com.vndirect.commons.jfreechart.ChartObject;
import vn.com.vndirect.commons.jfreechart.ChartsManager;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.vndirect.commons.jfreechart.analysis.competitor.CompetitorHelper;
import vn.com.vndirect.commons.jfreechart.axis.AxisFactory;
import vn.com.vndirect.commons.jfreechart.beans.ChartBean;
import vn.com.vndirect.commons.jfreechart.beans.ChartBeanDateComparator;
import vn.com.vndirect.commons.jfreechart.beans.ChartBeans;
import vn.com.vndirect.commons.jfreechart.beans.CompetitorInfo;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.commons.jfreechart.beans.LayerChartBean;
import vn.com.vndirect.commons.jfreechart.dataset.DatasetFactory;
import vn.com.vndirect.commons.jfreechart.renderer.RendererFactory;
import vn.com.vndirect.commons.utility.OSCacheUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.impl.IfoBreakdownViewDAO;
import vn.com.vndirect.dao.impl.IfoMarketCalendarViewDAO;
import vn.com.vndirect.dao.impl.IfoSecPriceViewDAO;
import vn.com.vndirect.domain.IfoBreakdownView;
import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.MatchOrder;
import vn.com.vndirect.domain.MatchOrderId;
import vn.com.vndirect.domain.extend.SearchIfoMarketCalendar;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.TechnicalAnalysisChart;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.opensymphony.oscache.base.NeedsRefreshException;

/**
 * @author tungnq
 * 
 */
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ChartManager extends BaseManager implements IChartManager {
	private static Logger logger = Logger.getLogger(ChartManager.class);

	final static String STR_DATE_TIME_FORMAT_DDMMYYYY = "dd/MM/yyyy";

	// +++++++++++++ ============ Support for Spring Mapping ============

	@Autowired
	private IfoSecPriceViewDAO ifoSecPriceViewDAO;
	
	@Autowired
	private IfoBreakdownViewDAO ifoBreakdownViewDAO;
	
	@Autowired
	private IfoMarketCalendarViewDAO ifoMarketCalendarViewDAO;

	@Autowired
	private ChartsManager chartsManager;
	
	@Autowired
	private vn.com.vndirect.commons.flashchart.ChartsManager flashChartsManager;

	/**
	 * @param chartManager
	 *            the chartManager to set
	 */
	public void setChartsManager(ChartsManager chartManager) {
		this.chartsManager = chartManager;
	}

	public void setFlashChartsManager(vn.com.vndirect.commons.flashchart.ChartsManager flashChartsManager) {
		this.flashChartsManager = flashChartsManager;
	}

	/**
	 * @param ifoSecPriceViewDAO
	 *            the ifoSecPriceViewDAO to set
	 */
	public void setIfoSecPriceViewDAO(IfoSecPriceViewDAO ifoSecPriceViewDAO) {
		this.ifoSecPriceViewDAO = ifoSecPriceViewDAO;
	}

	/**
	 * @param ifoBreakdownViewDAO
	 *            the ifoBreakdownViewDAO to set
	 */
	public void setIfoBreakdownViewDAO(IfoBreakdownViewDAO ifoBreakdownViewDAO) {
		this.ifoBreakdownViewDAO = ifoBreakdownViewDAO;
	}

	/**
	 * 
	 * @param ifoMarketCalendarViewDAO
	 */
	public void setIfoMarketCalendarViewDAO(IfoMarketCalendarViewDAO ifoMarketCalendarViewDAO) {
		this.ifoMarketCalendarViewDAO = ifoMarketCalendarViewDAO;
	}

	// +++++++++++++ ============ / Support for Spring Mapping ============
	// ++++++++

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateLinerChart(vn.com.vndirect
	 * .commons.jfreechart.ChartInfo,
	 * vn.com.vndirect.domain.extend.SearchIfoSecPrice)
	 */
	public ChartObject generateLinerChart(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateLinerChart(chartInfo:" + chartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (chartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchIfoSecPrice, null);

			ChartObject chartObj = null;
			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				ChartBeans chartBeans = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, chartInfo);

				chartObj = chartsManager.createLinerChart(chartInfo, chartBeans);

			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return chartObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateVolumeChart(vn.com.vndirect
	 * .commons.jfreechart.ChartInfo,
	 * vn.com.vndirect.domain.extend.SearchIfoSecPrice)
	 */
	public ChartObject generateVolumeChart(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateVolumeChart(chartInfo:" + chartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (chartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			ChartObject chartObj = null;
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchIfoSecPrice, null);

			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				ChartBeans chartBeans = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, chartInfo);

				chartObj = chartsManager.createVolumeChart(chartInfo, chartBeans);
				chartObj.setSymbol(searchIfoSecPrice.getSymbol());
				chartObj.setExchangeCode(searchIfoSecPrice.getExchangeCode());
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return chartObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateChartForSnapshot(vn.com
	 * .vndirect.commons.jfreechart.ChartInfo,
	 * vn.com.vndirect.domain.extend.SearchIfoSecPrice)
	 */
	public ChartObject generateChartForSnapshot(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateChartForSnapshot(chartInfo:" + chartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice
				+ ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (chartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}
		ChartObject chartObject = null;
		// String cacheKey = "GenerateChartForSnapshot@" + (chartInfo.toString()
		// + searchIfoSecPrice.toString()).hashCode();
		String cacheKey = "GenerateChartForSnapshot@" + chartInfo.hashCode() + "." + searchIfoSecPrice.toString();
		// check in cache
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey);
			chartObject = (ChartObject) oscache.getFromCache(cacheKey, OSCacheUtils.getChartRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey + ", err:" + nre.getMessage());
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey + ", err:" + nre.getMessage());
			boolean updated = false;
			try {
				// Get Static file content
				chartObject = this.generateChartForSnapshot1(chartInfo, searchIfoSecPrice);

				if (chartObject != null && chartObject.hasImageContent()) {
					// Store in the cache
					oscache.putInCache(cacheKey, chartObject);
					if (logger.isDebugEnabled())
						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", chartObject:" + chartObject);

					updated = true;
				}
			} catch (Exception e) {
				updated = false;
				throw new SystemException(LOCATION, e);
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					oscache.cancelUpdate(cacheKey);
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return chartObject;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private ChartObject generateChartForSnapshot1(ChartInfo chartInfo, SearchIfoSecPrice searchIfoSecPrice)
			throws SystemException, FunctionalException {
		final String LOCATION = "generateChartForSnapshot1(chartInfo:" + chartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice
				+ ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (chartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			ChartObject chartObj = null;
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchIfoSecPrice, null);

			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				ChartBeans linerChart = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, chartInfo);

				ChartBeans volumeChart = null;
				if (searchIfoSecPrice.isVolumeChartInSnapshot()) {
					volumeChart = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, chartInfo);
				}

				chartObj = chartsManager.createChartForSnapshot(chartInfo, linerChart, volumeChart);
			}

			if (chartObj == null) {
				// +++ set dummy data
				chartObj = new ChartObject();
				chartObj.setBytes(new byte[0]);
			}

			chartObj.setSymbol(searchIfoSecPrice.getSymbol());
			chartObj.setExchangeCode(searchIfoSecPrice.getExchangeCode());

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return chartObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public ChartObject getChartFromCacheKey(String cacheKey) throws SystemException, FunctionalException {
		ChartObject chartObject = null;
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			chartObject = (ChartObject) oscache.getFromCache(cacheKey, OSCacheUtils.getTAChartRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			nre.printStackTrace();
		}
		return chartObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateChartForTA(vn.com.vndirect
	 * .commons.jfreechart.ChartInfo,
	 * vn.com.vndirect.domain.extend.SearchIfoSecPrice,
	 * vn.com.vndirect.domain.extend.TechnicalAnalysisChart)
	 */
//	public ChartObject generateChartForTA(TAChartInfo taChartInfo, SearchIfoSecPrice searchIfoSecPrice,
//			TechnicalAnalysisChart taChart) throws SystemException, FunctionalException {
//		final String LOCATION = "generateChartForTA(taChartInfo:" + taChartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice
//				+ ", taChart:" + taChart + ")";
//		if (logger.isDebugEnabled())
//			logger.debug(LOCATION + ":: BEGIN");
//
//		if (taChartInfo == null || searchIfoSecPrice == null) {
//			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
//		}
//
//		ChartObject chartObject = null;
//		String cacheKey = "GenerateChartForTA@" + taChartInfo.hashCodeTA() + "." + searchIfoSecPrice.hashCode();
//		cacheKey += (taChart == null ? "" : "." + taChart.hashCode());
//		// check in cache
//		//try {
//			// Get from the cache
//		//	if (logger.isDebugEnabled())
//				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
//			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
//			// cacheKey);
//			chartObject = (ChartObject) oscache.getFromCache(cacheKey, OSCacheUtils.getTAChartRefreshPeriod());
//		//} catch (NeedsRefreshException nre) {
//			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
//			// cacheKey + ", err:" + nre.getMessage());
//		//	if (logger.isDebugEnabled())
//		//		logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey + ", err:" + nre.getMessage());
//			boolean updated = false;
//			try {
//				// Get Static file content
//				chartObject = this.generateChartForTA1(taChartInfo, searchIfoSecPrice, taChart);
//				if (chartObject != null && chartObject.hasImageContent()) {
//
//				//	chartObject.setCacheKey(cacheKey);
//					// Store in the cache
//				//	oscache.putInCache(cacheKey, chartObject);
//					if (logger.isDebugEnabled())
//						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", chartObject:" + chartObject);
//
//					updated = true;
//				}
//			} catch (Exception e) {
//				updated = false;
//				e.printStackTrace();
//				throw new SystemException(LOCATION, e);
//			} finally {
//				if (!updated) {
//					// It is essential that cancelUpdate is called if the
//					// cached content could not be rebuilt
//					oscache.cancelUpdate(cacheKey);
//				}
//			}
//		//}
//		if (logger.isDebugEnabled())
//			logger.debug(LOCATION + ":: END");
//		return chartObject;
//	}

	/**
	 * 
	 * @param taChartInfo
	 * @param searchIfoSecPrice
	 * @param taChart
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	@ReadThroughSingleCache(namespace = "ChartManager.generateChartForTA@", expiration = 600)
	public ChartObject generateChartForTA(final TAChartInfo taChartInfo,
			@ParameterValueKeyProvider(order = 0) final SearchIfoSecPrice searchIfoSecPrice, final TechnicalAnalysisChart taChart,
			@ParameterValueKeyProvider(order = 1) final int chartInfoHashcode) throws SystemException, FunctionalException {
		final String LOCATION = "generateChartForTA(taChartInfo:" + taChartInfo + ", searchIfoSecPrice:" + searchIfoSecPrice
				+ ", taChart:" + taChart + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (taChartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchIfoSecPrice, null);
			ChartObject chartObj = null;
			String _symbol = searchIfoSecPrice.getSymbol();
			String _exchange = searchIfoSecPrice.getExchangeCode();

			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				ChartBeans chartBeans = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, taChartInfo);

				// +++ sort by ASC
				ChartBeanDateComparator chartBeanDateComparator = new ChartBeanDateComparator(ChartBeanDateComparator.ASC);
				Collections.sort(chartBeans, chartBeanDateComparator);
				// logger.debug("+++++:: chartBeans:" +
				// chartBeans.toChartBeansString());
				// ---

				// taChartInfo.setLegend(true);

				String axisClazz = AxisFactory.getTypeByScalar(taChartInfo.getChartScale());
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: axisClazz:" + axisClazz);

				List<LayerChartBean> listLayerChart = new ArrayList<LayerChartBean>();

				LayerChartBean mainLayer = new LayerChartBean(axisClazz);
				listLayerChart.add(mainLayer);

				// add main chart
				ChartBeans mainChart = (ChartBeans) chartBeans.clone();
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: mainChart:" + mainChart);
				// logger.debug("+++++:: mainChart:" +
				// mainChart.toChartBeansString());

				mainChart.setChartTitle(searchIfoSecPrice.getSymbol());
				mainChart.setDatasetClazz(DatasetFactory.getTypeByLineType(taChartInfo.getLineType()));
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: mainChart.getDatasetClazz():" + mainChart.getDatasetClazz());

				mainChart.setRendererClazz(RendererFactory.getTypeByLineType(taChartInfo.getLineType()));
				if (logger.isDebugEnabled())
					logger.debug(LOCATION + ":: mainChart.getRendererClazz():" + mainChart.getRendererClazz());

				mainLayer.addItem(mainChart);

				if (taChartInfo.getListCompetitor() != null && taChartInfo.getListCompetitor().size() > 0) {
					// +++ get first ChartItem
					if (mainChart != null && mainChart.size() > 0) {
						mainLayer.setFirstChartBean(mainChart.getItem(0));
						if (logger.isDebugEnabled())
							logger.debug(LOCATION + ":: mainLayer.getFirstChartBean():" + mainLayer.getFirstChartBean());
					}
					// --- get first ChartItem
					// process Competitor
					Iterator iterCompetitor = taChartInfo.getListCompetitor().iterator();
					CompetitorInfo competitorInfo;
					ChartBeans competitorChartBeans;
					while (iterCompetitor.hasNext()) {
						competitorInfo = (CompetitorInfo) iterCompetitor.next();
						searchIfoSecPrice.setSymbol(competitorInfo.getSymbol());
						searchIfoSecPrice.setExchangeCode(competitorInfo.getExchangeCode());
						if (logger.isDebugEnabled())
							logger.debug(LOCATION + ":: competitorInfo.getSymbol():" + competitorInfo.getSymbol() + " - "
									+ competitorInfo.getExchangeCode());
						listIfoSecPriceView = ifoSecPriceViewDAO.findStockPrices(searchIfoSecPrice, null);
						competitorChartBeans = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, taChartInfo);

						// +++ sort by ASC
						Collections.sort(competitorChartBeans, chartBeanDateComparator);
						// logger.debug("+++++:: competitorChartBeans:" +
						// competitorChartBeans.toChartBeansString());
						// ---

						competitorChartBeans.setChartTitle(competitorInfo.getSymbol());
						competitorChartBeans.setDatasetClazz(DatasetFactory.Type.CLOSE_PRICE_DATASET);
						competitorChartBeans.setRendererClazz(RendererFactory.Type.DEFAULT_LINES_RENDERER);

						// count Disproportion of close Price
						competitorInfo.setPercentDisparityValue(CompetitorHelper.percentDisparity(mainChart,
								competitorChartBeans, chartBeanDateComparator.isSortByASC()));
						// count Disproportion of close Price

						competitorChartBeans.setCompetitorInfo(competitorInfo);
						mainLayer.addItem(competitorChartBeans);
					}
				}

				// add Indicators
				Iterator iterIndicator = taChartInfo.getListIndicator().iterator();
				IndicatorInfo indicatorInfo;
				ChartBeans chartBeansTmp;

				int countLayer = 0;
				while (iterIndicator.hasNext()) {
					indicatorInfo = (IndicatorInfo) iterIndicator.next();

					chartBeansTmp = (ChartBeans) chartBeans.clone();
					chartBeansTmp.setChartTitle(indicatorInfo.getName());
					chartBeansTmp.setIndicatorInfo(indicatorInfo);

					if (TAConstants.Indicator.isOnSubLayer(indicatorInfo.getName())) {
						listLayerChart.add(new LayerChartBean(new ChartBeans[] { chartBeansTmp },
								ChartConstants.ChartSetting.SUB_WEIGHT, AxisFactory.Type.NUMBER_AXIS));

						countLayer++;
					} else {
						mainLayer.add(chartBeansTmp);
					}
				}

				mainLayer.setWeight(ChartConstants.ChartSetting.MAIN_WEIGHT);
				// taChartInfo.setHight(ChartConstants.ChartSetting.TA_MAIN_CHART_HEIGHT
				// + ChartConstants.ChartSetting.TA_SUB_CHART_HEIGHT *
				// countLayer);
				int hightSubChart = (int) (taChartInfo.getHight() / ChartConstants.ChartSetting.MAIN_WEIGHT);
				taChartInfo.setHight(taChartInfo.getHight() + hightSubChart * countLayer);

				chartObj = chartsManager.createTechnicalChart(taChartInfo,
						(LayerChartBean[]) listLayerChart.toArray(new LayerChartBean[listLayerChart.size()]));

			}

			if (chartObj == null) {
				// set dummy data
				chartObj = new ChartObject();
				chartObj.setBytes(new byte[0]);
			}
			chartObj.setSymbol(_symbol);
			chartObj.setExchangeCode(_exchange);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return chartObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateDataForFlashChart(vn.com
	 * .vndirect.commons.jfreechart.TAChartInfo,
	 * vn.com.vndirect.domain.extend.SearchIfoSecPrice)
	 */
	public FlashChartBean generateDataForFlashChart(TAChartInfo taChartInfo, SearchIfoSecPrice searchIfoSecPrice)
			throws SystemException, FunctionalException {
		final String LOCATION = "generateDataForFlashChart(taChartInfo:" + taChartInfo + ", searchIfoSecPrice:"
				+ searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (taChartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		FlashChartBean flashChartBean = null;
		String cacheKey = "generateDataForFlashChart@" + taChartInfo.hashCodeTA() + "." + searchIfoSecPrice.hashCode();

		// check in cache
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey);
			flashChartBean = (FlashChartBean) oscache.getFromCache(cacheKey, OSCacheUtils.getFlashTAChartRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey + ", err:" + nre.getMessage());
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey + ", err:" + nre.getMessage());
			boolean updated = false;
			try {
				// Get Static file content
				flashChartBean = this.generateDataForFlashChart1(taChartInfo, searchIfoSecPrice);

				if (flashChartBean != null) {
					// +++ sort result
					flashChartBean.sortPrices(new FlashPriceBeanComparator(FlashPriceBeanComparator.ASC));
					// --- sort result

					// Store in the cache
					oscache.putInCache(cacheKey, flashChartBean);
					if (logger.isDebugEnabled())
						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", flashChartBean:" + flashChartBean);

					updated = true;
				}
			} catch (Exception e) {
				updated = false;
				throw new SystemException(LOCATION, e);
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					oscache.cancelUpdate(cacheKey);
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return flashChartBean;
	}

	/**
	 * 
	 * @param taChartInfo
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private FlashChartBean generateDataForFlashChart1(TAChartInfo taChartInfo, SearchIfoSecPrice searchIfoSecPrice)
			throws SystemException, FunctionalException {
		final String LOCATION = "generateDataForFlashChart1(taChartInfo:" + taChartInfo + ", searchIfoSecPrice:"
				+ searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (taChartInfo == null || searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			searchIfoSecPrice.setTradingTimeSortBy(SearchIfoSecPrice.SORT_ASC);
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPricesByPeriod(searchIfoSecPrice, null,
					VNDirectWebUtilities.isChartDynamicPriceTime());

			FlashChartBean chartObj = null;

			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				ChartBeans chartBeans = this.convertIfoSecPriceView2ChartBean(listIfoSecPriceView, taChartInfo);
				chartBeans.setIndicatorInfo((IndicatorInfo) taChartInfo.getListIndicator().iterator().next());

				chartObj = flashChartsManager.createTechnicalChartFlashChartBean(chartBeans);
				chartObj.setStatus(FlashChartBean.OK);
				chartObj.setErrorMessage("");
				chartObj.setSymbol(searchIfoSecPrice.getSymbol());
				chartObj.setIndicator(chartBeans.getIndicatorInfo().getName());
				chartObj.setPriodicity("???");
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return (chartObj == null ? new FlashChartBean() : chartObj);
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateDataForHistorical(vn.com
	 * .vndirect.domain.extend.SearchIfoSecPrice)
	 */
	public FlashChartBean generateDataForHistorical(SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateDataForHistorical(searchIfoSecPrice:" + searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		FlashChartBean flashChartBean = null;
		String cacheKey = "generateDataForHistorical@" + searchIfoSecPrice.hashCode();

		// check in cache
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey);
			flashChartBean = (FlashChartBean) oscache.getFromCache(cacheKey, OSCacheUtils.getFlashTAChartRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey + ", err:" + nre.getMessage());
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey + ", err:" + nre.getMessage());
			boolean updated = false;
			try {
				// Get Static file content
				flashChartBean = this.generateDataForHistorical1(searchIfoSecPrice);

				if (flashChartBean != null) {
					// +++ sort result
					flashChartBean.sortPrices(new FlashPriceBeanComparator(FlashPriceBeanComparator.ASC));
					// --- sort result

					// Store in the cache
					oscache.putInCache(cacheKey, flashChartBean);
					if (logger.isDebugEnabled())
						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", flashChartBean:" + flashChartBean);

					updated = true;
				}
			} catch (Exception e) {
				updated = false;
				throw new SystemException(LOCATION, e);
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					oscache.cancelUpdate(cacheKey);
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return flashChartBean;
	}

	/**
	 * 
	 * @param searchIfoSecPrice
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForHistorical1(SearchIfoSecPrice searchIfoSecPrice) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateDataForHistorical1(searchIfoSecPrice:" + searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			Collection listIfoSecPriceView = ifoSecPriceViewDAO.findStockPricesByPeriod(searchIfoSecPrice, null,
					VNDirectWebUtilities.isChartDynamicPriceTime());

			FlashChartBean chartObj = new FlashChartBean();

			if (listIfoSecPriceView != null && listIfoSecPriceView.size() > 0) {
				Collection priceList = new ArrayList();
				IfoSecPriceView ifoSecPriceView;

				for (Iterator iterator = listIfoSecPriceView.iterator(); iterator.hasNext();) {
					FlashPriceBean fPrice = new FlashPriceBean();
					ifoSecPriceView = (IfoSecPriceView) iterator.next();

					Date transDate = ifoSecPriceView.getId().getTransDate();

					if (transDate != null) {
						fPrice.setTradingDate(DateFormatUtils.format(transDate, STR_DATE_TIME_FORMAT_DDMMYYYY));
					}
					fPrice.setTransDate(transDate);

					/*
					 * fPrice.setClose(ifoSecPriceView.getId().getClosePrice());
					 * fPrice.setHigh(ifoSecPriceView.getId().getHighPrice());
					 * fPrice.setLow(ifoSecPriceView.getId().getLowPrice());
					 * fPrice.setOpen(ifoSecPriceView.getId().getOpenPrice());
					 */

					fPrice.setClose(ifoSecPriceView.getId().getAdClosePrice());
					fPrice.setHigh(ifoSecPriceView.getId().getAdHighPrice());
					fPrice.setLow(ifoSecPriceView.getId().getAdLowPrice());
					fPrice.setOpen(ifoSecPriceView.getId().getAdOpenPrice());

					fPrice.setVolume(ifoSecPriceView.getId().getVolume());
					fPrice.setEvent(ifoSecPriceView.getId().getRightsType());

					priceList.add(fPrice);
				}
				chartObj.setStatus(FlashChartBean.OK);
				chartObj.setErrorMessage("");
				chartObj.setSymbol(searchIfoSecPrice.getSymbol());
				chartObj.putPriceList(TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE, priceList);
				chartObj.setPriodicity("???");
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return (chartObj == null ? new FlashChartBean() : chartObj);
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateDataForIntrday(vn.com.vndirect
	 * .domain.extend.SearchIfoSecPrice)
	 */
	public FlashChartBean generateDataForIntrday(SearchIfoSecPrice searchIfoSecPrice) throws SystemException, FunctionalException {
		final String LOCATION = "generateDataForIntrday(searchIfoSecPrice:" + searchIfoSecPrice + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoSecPrice == null) {
			throw new FunctionalException(LOCATION, "chartInfo or searchIfoSecPrice object is NULL or EMPTY ...");
		}

		try {
			Collection listMatchOrder = ifoSecPriceViewDAO.findIntradayStockPrices(searchIfoSecPrice);

			FlashChartBean chartObj = new FlashChartBean();

			if (listMatchOrder != null && listMatchOrder.size() > 0) {
				Collection priceList = new ArrayList();
				MatchOrder matchOrder;
				MatchOrderId matchOrderId;

				for (Iterator iterator = listMatchOrder.iterator(); iterator.hasNext();) {
					FlashPriceBean fPrice = new FlashPriceBean();
					matchOrder = (MatchOrder) iterator.next();
					matchOrderId = matchOrder.getId();

					Date tradingDate = matchOrderId.getTradingDate();

					if (tradingDate != null) {
						fPrice.setTradingDate(DateFormatUtils.format(tradingDate, STR_DATE_TIME_FORMAT_DDMMYYYY) + " "
								+ matchOrderId.getTime());
					}
					fPrice.setTransDate(tradingDate);
					fPrice.setClose(matchOrderId.getMatchPrice());
					fPrice.setVolume(matchOrderId.getMatchQuantity());

					priceList.add(fPrice);
				}
				chartObj.setStatus(FlashChartBean.OK);
				chartObj.setErrorMessage("");
				chartObj.setSymbol(searchIfoSecPrice.getSymbol());
				chartObj.putPriceList(TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE, priceList);
				chartObj.setPriodicity("???");
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return (chartObj == null ? new FlashChartBean() : chartObj);
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generatePieChart(vn.com.vndirect
	 * .commons.jfreechart.ChartInfo, long)
	 */
	public ChartObject generatePieChart(ChartInfo chartInfo, long companyId) throws SystemException, FunctionalException {
		final String LOCATION = "generatePieChart(chartInfo:" + chartInfo + ", companyId:" + companyId + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (chartInfo == null || companyId < 1) {
			throw new FunctionalException(LOCATION, "chartInfo or CompanyId object is NULL or EMPTY ...");
		}
		ChartObject chartObject = null;
		// String cacheKey = "GeneratePieChart@" + (chartInfo.toString() +
		// companyId).hashCode();
		String cacheKey = "GeneratePieChart@" + chartInfo.toString().hashCode() + "." + companyId;
		// check in cache
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			chartObject = (ChartObject) oscache.getFromCache(cacheKey, OSCacheUtils.getChartRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				// Get Static file content
				chartObject = this.generatePieChart1(chartInfo, companyId);

				if (chartObject != null) {
					// Store in the cache
					oscache.putInCache(cacheKey, chartObject);
					if (logger.isDebugEnabled())
						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", chartObject:" + chartObject);

					updated = true;
				}
			} catch (Exception e) {
				updated = false;
				throw new SystemException(LOCATION, e);
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					oscache.cancelUpdate(cacheKey);
				}
			}
		}
		//
		return chartObject;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param companyId
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	private ChartObject generatePieChart1(ChartInfo chartInfo, long companyId) throws SystemException, FunctionalException {
		final String LOCATION = "generatePieChart1(chartInfo:" + chartInfo + ", companyId:" + companyId + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (companyId < 1) {
			throw new FunctionalException(LOCATION, "Can not find company ...");
		}

		try {
			ChartObject chartObj = null;
			IfoBreakdownView ifoBreakdownView = ifoBreakdownViewDAO.findByCompanyId(companyId);

			if (ifoBreakdownView != null && ifoBreakdownView.getId() != null) {
				IfoBreakdownViewId ifoBreakdownViewId = ifoBreakdownView.getId();
				double total = 0;
				try {
					total = ifoBreakdownViewId.getStateOwnership().doubleValue()
							+ ifoBreakdownViewId.getForeignOwnership().doubleValue()
							+ ifoBreakdownViewId.getOther().doubleValue();
				} catch (Exception e) {
					total = 0;
				}
				if (total > 0) {
					ChartBeans chartBeans = new ChartBeans();
					chartBeans.addItem(new ChartBean(ifoBreakdownViewId.getStateOwnership(), "SO"));
					chartBeans.addItem(new ChartBean(ifoBreakdownViewId.getForeignOwnership(), "FO"));
					chartBeans.addItem(new ChartBean(ifoBreakdownViewId.getOther(), "Other"));

					chartObj = chartsManager.createPieChart(chartInfo, chartBeans);
					chartObj.setCompanyId(companyId);
				}

			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return chartObj;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/**
	 * 
	 * @param listIfoSecPriceView
	 * @return
	 * @throws SystemException
	 */
	private ChartBeans convertIfoSecPriceView2ChartBean(Collection listIfoSecPriceView, ChartInfo chartInfo)
			throws SystemException {
		final String LOCATION = "convertIfoSecPriceView2ChartBean(listIfoSecPriceView.size():"
				+ (listIfoSecPriceView == null ? 0 : listIfoSecPriceView.size()) + ")";
		try {
			ChartBeans chartBeans = new ChartBeans();
			IfoSecPriceView ifoSecPriceView;
			ChartBean chartBean;
			Iterator iter = listIfoSecPriceView.iterator();
			while (iter.hasNext()) {
				ifoSecPriceView = (IfoSecPriceView) iter.next();
				chartBean = new ChartBean();
				if (chartInfo != null && chartInfo.getDataType() == ChartInfo.NORMAL_DATA_TYPE) {
					chartBean.setClosePrice(ifoSecPriceView.getId().getClosePrice());
					chartBean.setHighPrice(ifoSecPriceView.getId().getHighPrice());
					chartBean.setLowPrice(ifoSecPriceView.getId().getLowPrice());
					chartBean.setOpenPrice(ifoSecPriceView.getId().getOpenPrice());
				} else {
					chartBean.setClosePrice(ifoSecPriceView.getId().getAdClosePrice());
					chartBean.setHighPrice(ifoSecPriceView.getId().getAdHighPrice());
					chartBean.setLowPrice(ifoSecPriceView.getId().getAdLowPrice());
					chartBean.setOpenPrice(ifoSecPriceView.getId().getAdOpenPrice());
				}
				chartBean.setVolume(ifoSecPriceView.getId().getVolume());
				chartBean.setTime(ifoSecPriceView.getId().getTransDate());

				chartBeans.addItem(chartBean);
			}
			return chartBeans;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ChartManager#generateDataForHistoryEvents(vn
	 * .com.vndirect.domain.extend.SearchIfoMarketCalendar)
	 */
	public FlashChartBean generateDataForHistoryEvents(SearchIfoMarketCalendar searchIfoMarketCalendar) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateDataForHistoryEvents(searchIfoMarketCalendar:" + searchIfoMarketCalendar + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoMarketCalendar == null) {
			throw new FunctionalException(LOCATION, "ifoMarketCalendarView object is NULL or EMPTY ...");
		}

		FlashChartBean flashChartBean = null;
		String cacheKey = "generateDataForHistoryEvents@" + searchIfoMarketCalendar.hashCode();

		// check in cache
		try {
			// Get from the cache
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey);
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey);
			flashChartBean = (FlashChartBean) oscache.getFromCache(cacheKey, OSCacheUtils.getFlashEventsRefreshPeriod());
		} catch (NeedsRefreshException nre) {
			// System.out.println("--->>> oscache.getFromCache: cacheKey:" +
			// cacheKey + ", err:" + nre.getMessage());
			if (logger.isDebugEnabled())
				logger.debug("--->>> oscache.getFromCache: cacheKey:" + cacheKey + ", err:" + nre.getMessage());
			boolean updated = false;
			try {
				// Get Static file content
				flashChartBean = this.generateDataForHistoryEvents1(searchIfoMarketCalendar);

				if (flashChartBean != null) {
					// +++ sort result
					flashChartBean.sortPrices(new FlashPriceBeanComparator(FlashPriceBeanComparator.ASC));
					// --- sort result

					// Store in the cache
					oscache.putInCache(cacheKey, flashChartBean);
					if (logger.isDebugEnabled())
						logger.debug("--->>> oscache.putInCache: cacheKey:" + cacheKey + ", flashChartBean:" + flashChartBean);

					updated = true;
				}
			} catch (Exception e) {
				updated = false;
				throw new SystemException(LOCATION, e);
			} finally {
				if (!updated) {
					// It is essential that cancelUpdate is called if the
					// cached content could not be rebuilt
					oscache.cancelUpdate(cacheKey);
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return flashChartBean;
	}

	/**
	 * 
	 * @param searchIfoMarketCalendar
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public FlashChartBean generateDataForHistoryEvents1(SearchIfoMarketCalendar searchIfoMarketCalendar) throws SystemException,
			FunctionalException {
		final String LOCATION = "generateDataForHistoryEvents1(ifoMarketCalendarView:" + searchIfoMarketCalendar + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoMarketCalendar == null) {
			throw new FunctionalException(LOCATION, "ifoMarketCalendarView object is NULL or EMPTY ...");
		}

		try {
			Collection listIfoMarketCalendarView = ifoMarketCalendarViewDAO.searchSplitAndDividend(searchIfoMarketCalendar);

			FlashChartBean chartObj = new FlashChartBean();

			if (listIfoMarketCalendarView != null && listIfoMarketCalendarView.size() > 0) {
				List eventList = new ArrayList();
				IfoMarketCalendarView temp;

				for (Iterator iterator = listIfoMarketCalendarView.iterator(); iterator.hasNext();) {
					FlashEventBean fEvent = new FlashEventBean();
					temp = (IfoMarketCalendarView) iterator.next();

					Date transDate = temp.getRegisterDate();

					if (transDate != null) {
						fEvent.setTradingDate(DateFormatUtils.format(transDate, STR_DATE_TIME_FORMAT_DDMMYYYY));
					}

					fEvent.setType(temp.getEventType());
					fEvent.setDescription(temp.getEventDesc());

					eventList.add(fEvent);
				}
				chartObj.setStatus(FlashChartBean.OK);
				chartObj.setErrorMessage("");
				chartObj.setSymbol(searchIfoMarketCalendar.getSymbol());
				chartObj.setEventList(eventList);
				chartObj.setPriodicity("???");
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return (chartObj == null ? new FlashChartBean() : chartObj);
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}
}
