package vn.com.vndirect.web.struts2.chart;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IChartManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.commons.flashchart.FlashChartUtils;
import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.vndirect.commons.flashchart.beans.FlashPriceBeanComparator;
import vn.com.vndirect.commons.jfreechart.ChartInfo;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.extend.SearchIfoMarketCalendar;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.SearchSymbol;
import vn.com.vndirect.domain.struts2.chart.FlashChartAPIModel;
import vn.com.vndirect.events.schemas.EventsDocument.Events;
import vn.com.vndirect.prices.schemas.OnlineDocument;
import vn.com.vndirect.prices.schemas.DatasetsDocument.Datasets;
import vn.com.vndirect.prices.schemas.OnlineDocument.Online;
import vn.com.vndirect.prices.schemas.PricesDocument.Prices;
import vn.com.vndirect.symbols.schemas.SymbolDocument.Symbol;
import vn.com.vndirect.symbols.schemas.SymbolsDocument.Symbols;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FlashChartAPIAction extends BaseChartActionSupport implements ModelDriven<FlashChartAPIModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1955008570594998267L;

	private final static Logger logger = Logger.getLogger(FlashChartAPIAction.class);

	@Autowired
	private IChartManager chartManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	private FlashChartAPIModel model = new FlashChartAPIModel();

	/**
	 * @param chartManager
	 *            the chartManager to set
	 */
	public void setChartManager(IChartManager chartManager) {
		this.chartManager = chartManager;
	}

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(QuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public FlashChartAPIModel getModel() {
		return this.model;
	}

	/**
	 * 
	 * @return
	 */
	public String executeXMLAPI() throws Exception {
		final String LOCATION = "executeXMLAPI()";

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			String rs = doCheckData();
			if (rs != null) {
				return rs;
			}

			SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(request);
			TAChartInfo chartInfo = this.getTAChartInfo();

			if (logger.isDebugEnabled())
				logger.debug("chartInfo 1:" + chartInfo);

			chartInfo = this.processIndicator(chartInfo);

			chartInfo.setLegend(true);

			if (logger.isDebugEnabled())
				logger.debug("chartInfo 2:" + chartInfo);

			String method = getReqParamValue(TAConstants.FlashChartParams.METHOD);

			if (TAConstants.FlashChartParams.FlashChartParamValues.SEARCH_SYMBOL.equalsIgnoreCase(method)) {
				SearchSymbol searchObj = new SearchSymbol();
				String exchangeCode = this.getString(TAConstants.FlashChartParams.MARKET);
				if (Constants.ShortExchangeCode.HA.equalsIgnoreCase(exchangeCode)) {
					searchObj.addExchangeCode(VNDirectWebUtilities.getHASTCExchange());
				} else if (Constants.ShortExchangeCode.HO.equalsIgnoreCase(exchangeCode)) {
					searchObj.addExchangeCode(VNDirectWebUtilities.getHOSTCExchange());
				}
				searchObj.setNumberItem(Integer.MAX_VALUE);
				searchObj.setCompanyName("");

				SearchResult result = quotesManager.searchSymbol(searchObj, new PagingInfo(Integer.MAX_VALUE - 10));
				FlashChartBean flashChartBean = new FlashChartBean();

				flashChartBean.setPriodicity(getReqParamValue(TAConstants.FlashChartParams.PERIODICITY));
				flashChartBean.setMarket(exchangeCode);
				flashChartBean.setStatus(FlashChartBean.OK);
				flashChartBean.setSymbolList(result);

				model.setChartXMLContent(this.getXMLDataForSymbols(flashChartBean));

			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method)) {

				String startDate = getReqParamValue(TAConstants.FlashChartParams.START_DATE);

				if (startDate != null && startDate.length() > 0) {
					searchIfoSecPrice
					        .setStartDate(DateUtils.parseDate(startDate, new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
				} else {
					searchIfoSecPrice.setStartDate(this.getCurrentDateDefault());
				}

				String endDate = getReqParamValue(TAConstants.FlashChartParams.END_DATE);

				if (endDate != null && endDate.length() > 0) {
					searchIfoSecPrice.setEndDate(DateUtils.parseDate(endDate, new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
				} else {
					searchIfoSecPrice.setEndDate(getCurrentDateDefault());
				}

				FlashChartBean flashChartBean = chartManager.generateDataForIntrday(searchIfoSecPrice);

				// +++ sort result
				flashChartBean.sortPrices(new FlashPriceBeanComparator(FlashPriceBeanComparator.ASC));
				// --- sort result

				flashChartBean.setPriodicity(getReqParamValue(TAConstants.FlashChartParams.PERIODICITY));
				flashChartBean.setMarket(getReqParamValue(TAConstants.FlashChartParams.MARKET));
				flashChartBean.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
				flashChartBean.setStatus(FlashChartBean.OK);

				model.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)) {
				FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);

				flashChartBean.setPriodicity(getReqParamValue(TAConstants.FlashChartParams.PERIODICITY));
				flashChartBean.setMarket(getReqParamValue(TAConstants.FlashChartParams.MARKET));
				flashChartBean.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
				flashChartBean.setStatus(FlashChartBean.OK);

				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)) {
				chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
				FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);

				flashChartBean.setPriodicity(getReqParamValue(TAConstants.FlashChartParams.PERIODICITY));
				flashChartBean.setIndicator(getReqParamValue(TAConstants.FlashChartParams.INDICATOR_TYPE));
				flashChartBean.setMarket(getReqParamValue(TAConstants.FlashChartParams.MARKET));
				flashChartBean.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
				flashChartBean.setStatus(FlashChartBean.OK);

				model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS.equalsIgnoreCase(method)) {

				SearchIfoMarketCalendar searchIfoMarketCalendar = new SearchIfoMarketCalendar();
				String symbol = getReqParamValue(TAConstants.FlashChartParams.SYMBOL);
				searchIfoMarketCalendar.setSymbol(symbol);
				String priodicity = getReqParamValue(TAConstants.FlashChartParams.PERIODICITY);
				String locale = getReqParamValue(TAConstants.FlashChartParams.LANG);
				searchIfoMarketCalendar.setLang(locale);
				String startDate = getReqParamValue(TAConstants.FlashChartParams.START_DATE);

				if (startDate != null && startDate.length() > 0) {
					searchIfoMarketCalendar.setStartDate(DateUtils.parseDate(startDate,
					        new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
				} else {
					searchIfoMarketCalendar.setStartDate(this.getStartDateDefault());
				}

				String endDate = getReqParamValue(TAConstants.FlashChartParams.END_DATE);

				if (endDate != null && endDate.length() > 0) {
					searchIfoMarketCalendar.setEndDate(DateUtils.parseDate(endDate,
					        new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
				} else {
					searchIfoMarketCalendar.setEndDate(getCurrentDateDefault());
				}

				FlashChartBean flashChartBean = chartManager.generateDataForHistoryEvents(searchIfoMarketCalendar);

				flashChartBean.setPriodicity(priodicity);
				flashChartBean.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
				flashChartBean.setStatus(FlashChartBean.OK);

				model.setChartXMLContent(this.getXMLDataForHistoryEvents(flashChartBean));
			}

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return SUCCESS;
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.error(LOCATION, e);
			return doHandleFuncEx(e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 */
	protected String doCheckData() {
		String method = getReqParamValue(TAConstants.FlashChartParams.METHOD);

		FlashChartBean flashChartBean = new FlashChartBean();

		String symbol = getReqParamValue(TAConstants.FlashChartParams.SYMBOL);
		symbol = symbol == null ? "" : symbol;
		flashChartBean.setSymbol(symbol);

		String indicator = getReqParamValue(TAConstants.FlashChartParams.INDICATOR_TYPE);
		indicator = indicator == null ? "" : indicator;
		flashChartBean.setIndicator(indicator);

		String periodicity = getReqParamValue(TAConstants.FlashChartParams.PERIODICITY);
		periodicity = periodicity == null ? "DAY" : periodicity;
		flashChartBean.setPriodicity(periodicity);

		String market = getReqParamValue(TAConstants.FlashChartParams.MARKET);
		market = market == null ? "" : market;
		flashChartBean.setMarket(market);

		// validate for method
		if ((method == null)
		        || (!TAConstants.FlashChartParams.FlashChartParamValues.SEARCH_SYMBOL.equalsIgnoreCase(method)
		                && !TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)
		                && !TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)
		                && !TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method) && !TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS
		                    .equalsIgnoreCase(method))) {

			String error = getText(Constants.ErrorKeys.FLASH.INVALID_METHOD, "Invalid Method...");
			flashChartBean.setStatus(FlashChartBean.ERROR);
			flashChartBean.setErrorMessage(error);
			model.setChartXMLContent(this.getXMLDataForSymbols(flashChartBean));

			return INPUT;
		}

		// validate periodicity
		if (!TAConstants.FlashChartParams.FlashChartParamValues.DAY_PERIODICITY.equalsIgnoreCase(periodicity)
		        && !TAConstants.FlashChartParams.FlashChartParamValues.MONTH_PERIODICITY.equalsIgnoreCase(periodicity)
		        && !TAConstants.FlashChartParams.FlashChartParamValues.YEAR_PERIODICITY.equalsIgnoreCase(periodicity)) {

			String error = getText(Constants.ErrorKeys.FLASH.INVALID_PERIODICITY, "INVALID PERIODICITY...");
			flashChartBean.setStatus(FlashChartBean.ERROR);
			flashChartBean.setErrorMessage(error);

			if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoryEvents(flashChartBean));
			}

			return INPUT;
		}

		// validate startDate
		String startDate = getReqParamValue(TAConstants.FlashChartParams.START_DATE);

		try {
			if (startDate != null && startDate.trim().length() > 0) {
				DateUtils.parseDate(startDate, new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY });
			}
		} catch (Exception ex) {
			String error = getText(Constants.ErrorKeys.FLASH.INVALID_DATEFORMAT, "INVALID_DATEFORMAT...");
			flashChartBean.setStatus(FlashChartBean.ERROR);
			flashChartBean.setErrorMessage(error);

			if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoryEvents(flashChartBean));
			}

			return INPUT;
		}

		// validate endDate
		String endDate = getReqParamValue(TAConstants.FlashChartParams.END_DATE);

		try {
			if (endDate != null && endDate.trim().length() > 0) {
				DateUtils.parseDate(endDate, new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY });
			}
		} catch (Exception ex) {
			String error = getText(Constants.ErrorKeys.FLASH.INVALID_DATEFORMAT, "INVALID_DATEFORMAT...");
			flashChartBean.setStatus(FlashChartBean.ERROR);
			flashChartBean.setErrorMessage(error);

			if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));
			} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS.equalsIgnoreCase(method)) {
				model.setChartXMLContent(this.getXMLDataForHistoryEvents(flashChartBean));
			}

			return INPUT;
		}

		if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equalsIgnoreCase(method)) {
			// vndirectForm.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equalsIgnoreCase(method)) {
			// validate symbol
			if (symbol == null || symbol.trim().length() == 0) {
				String error = getText(Constants.ErrorKeys.FLASH.INVALID_SYMBOL, "INVALID_SYMBOL...");
				flashChartBean.setStatus(FlashChartBean.ERROR);
				flashChartBean.setErrorMessage(error);
				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));

				return INPUT;
			}
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equalsIgnoreCase(method)) {
			// validate symbol
			if (symbol == null || symbol.trim().length() == 0) {
				String error = getText(Constants.ErrorKeys.FLASH.INVALID_SYMBOL, "INVALID_SYMBOL...");
				flashChartBean.setStatus(FlashChartBean.ERROR);
				flashChartBean.setErrorMessage(error);

				model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));

				return INPUT;
			}

			String fullIndicator = "Chart" + indicator + "Indicator";
			String[] params = TAConstants.IndicatorParams.getListParamsByIndicator(fullIndicator);

			// validate indicator
			if (params == null) {
				String error = getText(Constants.ErrorKeys.FLASH.INVALID_INDICATOR, "INVALID_INDICATOR...");
				flashChartBean.setStatus(FlashChartBean.ERROR);
				flashChartBean.setErrorMessage(error);

				model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));

				return INPUT;
			}

			// TODO validate params
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_EVENTS.equalsIgnoreCase(method)) {
			// validate symbol
			if (symbol == null || symbol.trim().length() == 0) {
				String error = getText(Constants.ErrorKeys.FLASH.INVALID_SYMBOL, "INVALID_SYMBOL...");
				flashChartBean.setStatus(FlashChartBean.ERROR);
				flashChartBean.setErrorMessage(error);

				model.setChartXMLContent(this.getXMLDataForHistoryEvents(flashChartBean));

				return INPUT;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected TAChartInfo processIndicator(TAChartInfo chartInfo) {
		final String LOCATION = "processIndicator()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		chartInfo = (chartInfo == null ? this.getTAChartInfo() : chartInfo);

		String selectedIndicator = "Chart" + getReqParamValue(TAConstants.FlashChartParams.INDICATOR_TYPE) + "Indicator";
		IndicatorInfo indicatorInfo = new IndicatorInfo(selectedIndicator);

		String[] params = TAConstants.IndicatorParams.getListParamsByIndicator(selectedIndicator);
		if (params != null) {
			indicatorInfo = this.populateIndicatorValues(indicatorInfo, params);
		}
		chartInfo.addIndicator(indicatorInfo);

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return chartInfo;
	}

	/**
	 * 
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	private TAChartInfo getTAChartInfo() {
		TAChartInfo info = new TAChartInfo();

		info.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
		info.setExchangeCode(getReqParamValue(TAConstants.ChartParams.EXCHANGE_CODE));

		String indicatorType = getReqParamValue(TAConstants.FlashChartParams.INDICATOR_TYPE);
		info.addIndicator(new IndicatorInfo("Chart" + indicatorType + "Indicator"));
		info.setDataType(ChartInfo.NORMAL_DATA_TYPE);

		info.setLegend(true);

		return info;

	}

	/**
	 * 
	 * @param indicator
	 * @param ajaxRequest
	 * @param listDoubleParams
	 * @return
	 */
	public IndicatorInfo populateIndicatorValues(IndicatorInfo indicator, String[] listDoubleParams) {
		indicator = (indicator == null ? new IndicatorInfo() : indicator);
		int i, size;
		String key = null;
		// process listDoubleParams
		if (listDoubleParams != null && listDoubleParams.length > 0) {
			size = listDoubleParams.length;
			for (i = 0; i < size; i++) {
				try {
					key = listDoubleParams[i];
					indicator.addParam(key, Double.parseDouble(this.getString(key)));
				} catch (Exception e) {
					if (logger.isDebugEnabled())
						logger.debug("Exception: convert double | key:" + key);
				}
			}
		}
		return indicator;
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String getChartXMLDataForTAValues(FlashChartBean bean) {
		if (logger.isDebugEnabled())
			logger.debug("+++ flashChartBean:" + bean);

		HashMap ns = new HashMap();
		ns.put("http://schemas.prices.vndirect.com.vn/", "");
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveSuggestedPrefixes(ns);
		OnlineDocument onlineDoc = OnlineDocument.Factory.newInstance();

		onlineDoc.addNewOnline();
		Online online = onlineDoc.getOnline();
		online.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
		online.setErrorMessage(bean.getErrorMessage() == null ? "" : bean.getErrorMessage());

		// add dataset
		Datasets datasets = online.addNewDatasets();
		datasets.setSymbol(bean.getSymbol());
		datasets.setPeriodicity(bean.getPriodicity());
		datasets.setIndicator(bean.getIndicator());

		// set price for BBands
		if (TAConstants.Indicator.ChartBBandsIndicator.equals(bean.getFullIndicator())) {
			// datasets.addNewPrices();
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.BBands.PERIOD);

			FlashChartUtils.setHLValuesForPrices(prices, priceList);

		} else if (TAConstants.Indicator.ChartEMAIndicator.equals(bean.getFullIndicator())) {
			// set price for EMA
			Prices prices;
			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.EMA.PERIOD_1);

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.EMA.PERIOD_1);

			FlashChartUtils.setCValuesForPrices(prices, priceList);

			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.EMA.PERIOD_2);

			priceList = bean.getPriceList(TAConstants.IndicatorParams.EMA.PERIOD_2);

			FlashChartUtils.setCValuesForPrices(prices, priceList);

			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.EMA.PERIOD_3);

			priceList = bean.getPriceList(TAConstants.IndicatorParams.EMA.PERIOD_3);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartFastStochastricIndicator.equals(bean.getFullIndicator())) {
			// set price for FS
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.FS.K_PERIOD);

			FlashChartUtils.setCHValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartMACDIndicator.equals(bean.getFullIndicator())) {
			// set price for MACD
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.MACD.FAST_PERIOD);

			FlashChartUtils.setCHValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartMFIIndicator.equals(bean.getFullIndicator())) {
			// set price for MFI
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.MFI.PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartPSARIndicator.equals(bean.getFullIndicator())) {
			// set price for PSAR
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.PSAR.MAX_STEP_PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartROCIndicator.equals(bean.getFullIndicator())) {
			// set price for ROC
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.ROC.PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartRSIIndicator.equals(bean.getFullIndicator())) {
			// set price for RSI
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.RSI.PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartSlowStochastricIndicator.equals(bean.getFullIndicator())) {
			// set price for SS
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.SS.K_PERIOD);

			FlashChartUtils.setCHValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartSMAIndicator.equals(bean.getFullIndicator())) {
			// set price for SMA
			Prices prices;
			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.SMA.PERIOD_1);

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.SMA.PERIOD_1);

			FlashChartUtils.setCValuesForPrices(prices, priceList);

			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.SMA.PERIOD_2);

			priceList = bean.getPriceList(TAConstants.IndicatorParams.SMA.PERIOD_2);

			FlashChartUtils.setCValuesForPrices(prices, priceList);

			prices = datasets.addNewPrices();
			prices.setName(TAConstants.IndicatorParams.SMA.PERIOD_3);

			priceList = bean.getPriceList(TAConstants.IndicatorParams.SMA.PERIOD_3);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartVolumeIndicator.equals(bean.getFullIndicator())) {

			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList("Volume");

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartVolumeMAIndicator.equals(bean.getFullIndicator())) {
			// set price for VMA
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.VolumeMA.MA_PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		} else if (TAConstants.Indicator.ChartWilliamsRIndicator.equals(bean.getFullIndicator())) {
			// set price for WRI
			Prices prices;
			prices = datasets.addNewPrices();

			Collection priceList = bean.getPriceList(TAConstants.IndicatorParams.WilliamsR.MA_PERIOD);

			FlashChartUtils.setCValuesForPrices(prices, priceList);
		}

		return onlineDoc.xmlText(xmlOptions);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String getXMLDataForSymbols(FlashChartBean bean) {
		if (logger.isDebugEnabled())
			logger.debug("+++ flashChartBean:" + bean);

		HashMap ns = new HashMap();
		ns.put("http://schemas.symbols.vndirect.com.vn/", "");
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveSuggestedPrefixes(ns);
		vn.com.vndirect.symbols.schemas.OnlineDocument onlineDoc = vn.com.vndirect.symbols.schemas.OnlineDocument.Factory
		        .newInstance();

		onlineDoc.addNewOnline();
		vn.com.vndirect.symbols.schemas.OnlineDocument.Online online = onlineDoc.getOnline();
		online.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
		online.setErrorMessage(bean.getErrorMessage() == null ? "" : bean.getErrorMessage());

		// add dataset
		vn.com.vndirect.symbols.schemas.DatasetsDocument.Datasets datasets = online.addNewDatasets();
		datasets.setMarket(bean.getMarket() == null ? "" : bean.getMarket());

		Symbols symbols = datasets.addNewSymbols();
		List<IfoCompanyNameView> symbolList = (List<IfoCompanyNameView>) bean.getSymbolList();

		if (symbolList != null && symbolList.size() > 0) {
			Symbol symbol;
			for (IfoCompanyNameView ifoCompanyNameView : symbolList) {
				if (ifoCompanyNameView != null && ifoCompanyNameView.getId() != null) {
					symbol = symbols.addNewSymbol();
					symbol.setTicket(ifoCompanyNameView.getId().getSymbol());
					symbol.setName(symbol.getTicket() + " - " + ifoCompanyNameView.getId().getAbbName());
				}
			}
		}
		return onlineDoc.xmlText(xmlOptions);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String getXMLDataForHistoricalIntraday(FlashChartBean bean) {
		HashMap ns = new HashMap();
		ns.put("http://schemas.prices.vndirect.com.vn/", "");
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveSuggestedPrefixes(ns);
		OnlineDocument onlineDoc = OnlineDocument.Factory.newInstance();

		onlineDoc.addNewOnline();
		Online online = onlineDoc.getOnline();
		online.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
		online.setErrorMessage(bean.getErrorMessage() == null ? "" : bean.getErrorMessage());

		// add dataset
		Datasets datasets = online.addNewDatasets();
		datasets.setSymbol(bean.getSymbol());

		Prices prices;
		prices = datasets.addNewPrices();

		Collection priceList = bean.getPriceList(TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE);

		FlashChartUtils.setCVolValuesForPrices(prices, priceList);

		return onlineDoc.xmlText(xmlOptions);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String getXMLDataForHistorical(FlashChartBean bean) {
		if (logger.isDebugEnabled())
			logger.debug("+++ flashChartBean:" + bean);
		HashMap ns = new HashMap();
		ns.put("http://schemas.prices.vndirect.com.vn/", "");
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveSuggestedPrefixes(ns);
		OnlineDocument onlineDoc = OnlineDocument.Factory.newInstance();

		onlineDoc.addNewOnline();
		Online online = onlineDoc.getOnline();
		online.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
		online.setErrorMessage(bean.getErrorMessage() == null ? "" : bean.getErrorMessage());

		// add dataset
		Datasets datasets = online.addNewDatasets();
		datasets.setSymbol(bean.getSymbol());

		Prices prices;
		prices = datasets.addNewPrices();

		Collection priceList = bean.getPriceList(TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE);

		FlashChartUtils.setAllValuesForPrices(prices, priceList);

		return onlineDoc.xmlText(xmlOptions);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String getXMLDataForHistoryEvents(FlashChartBean bean) {
		if (logger.isDebugEnabled())
			logger.debug("+++ flashChartBean:" + bean);
		HashMap ns = new HashMap();
		ns.put("http://schemas.events.vndirect.com.vn/", "");
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveSuggestedPrefixes(ns);
		vn.com.vndirect.events.schemas.OnlineDocument onlineDoc = vn.com.vndirect.events.schemas.OnlineDocument.Factory
		        .newInstance();

		onlineDoc.addNewOnline();
		vn.com.vndirect.events.schemas.OnlineDocument.Online online = onlineDoc.getOnline();
		online.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
		online.setErrorMessage(bean.getErrorMessage() == null ? "" : bean.getErrorMessage());

		// add dataset
		vn.com.vndirect.events.schemas.DatasetsDocument.Datasets datasets = online.addNewDatasets();
		datasets.setSymbol(bean.getSymbol());

		Events events;
		events = datasets.addNewEvents();

		Collection eventList = bean.getEventList();

		FlashChartUtils.setAllValuesForEvents(events, eventList);

		return onlineDoc.xmlText(xmlOptions);
	}

	/**
	 * 
	 * @return
	 * @throws VNDirectException
	 */
	protected String doHandleFuncEx(String errMsg) {
		if (logger.isDebugEnabled())
			logger.debug("doHandleFuncEx():: BEGIN");
		FlashChartBean flashChartBean = new FlashChartBean();

		flashChartBean.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
		flashChartBean.setIndicator(getReqParamValue(TAConstants.FlashChartParams.INDICATOR_TYPE));
		flashChartBean.setPriodicity(getReqParamValue(TAConstants.FlashChartParams.PERIODICITY));
		flashChartBean.setMarket(getReqParamValue(TAConstants.FlashChartParams.MARKET));
		flashChartBean.setStatus(FlashChartBean.ERROR);
		flashChartBean.setErrorMessage(errMsg);

		String method = getReqParamValue(TAConstants.FlashChartParams.METHOD);

		if (TAConstants.FlashChartParams.FlashChartParamValues.SEARCH_SYMBOL.equals(method)) {
			model.setChartXMLContent(this.getXMLDataForSymbols(flashChartBean));
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_INTRADAY_PRICE.equals(method)) {
			model.setChartXMLContent(this.getXMLDataForHistoricalIntraday(flashChartBean));
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_HIST_PRICE.equals(method)) {
			model.setChartXMLContent(this.getXMLDataForHistorical(flashChartBean));
		} else if (TAConstants.FlashChartParams.FlashChartParamValues.GET_TA_VALUE.equals(method)) {
			model.setChartXMLContent(this.getChartXMLDataForTAValues(flashChartBean));
		}
		if (logger.isDebugEnabled())
			logger.debug("beforeHandleFuncEx():: END");
		return INPUT;
	}

	/**
	 * 
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected SearchIfoSecPrice getSearchIfoSecPrice(HttpServletRequest request) {

		SearchIfoSecPrice searchIfoSecPrice = new SearchIfoSecPrice();

		searchIfoSecPrice.setSymbol(getReqParamValue(TAConstants.FlashChartParams.SYMBOL));
		String date = null;
		try {
			date = getReqParamValue(TAConstants.FlashChartParams.START_DATE);
			if (date != null && date.length() > 0) {
				searchIfoSecPrice.setStartDate(org.apache.commons.lang.time.DateUtils.parseDate(date,
				        new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
			} else {
				searchIfoSecPrice.setStartDate(this.getStartDateDefault());
			}
		} catch (Exception ex) {
			searchIfoSecPrice.setStartDate(this.getStartDateDefault());
			if (logger.isDebugEnabled())
				logger.debug("parseDate(START_DATE)" + ex);
		}
		try {
			date = getReqParamValue(TAConstants.FlashChartParams.END_DATE);
			if (date != null && date.length() > 0) {
				searchIfoSecPrice.setEndDate(org.apache.commons.lang.time.DateUtils.parseDate(date,
				        new String[] { STR_DATE_TIME_FORMAT_DDMMYYYY }));
			} else {
				searchIfoSecPrice.setEndDate(getCurrentDateDefault());
			}
		} catch (Exception ex) {
			searchIfoSecPrice.setEndDate(getCurrentDateDefault());
			if (logger.isDebugEnabled())
				logger.debug("parseDate(END_DATE)" + ex);
		}
		return searchIfoSecPrice;
	}
}
