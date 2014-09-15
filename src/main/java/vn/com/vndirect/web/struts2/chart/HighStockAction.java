package vn.com.vndirect.web.struts2.chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vn.com.vndirect.business.IChartManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.flashchart.beans.FlashChartBean;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.jfreechart.ChartInfo;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class HighStockAction extends ActionSupport implements ModelDriven<HighStockModel> {
	private static final String VND_SYMBOL = "VND";

	private static final String VN_LOCALE = "VN";

	private static final String UPCOM_SYMBOL = "UPCOM";

	private static final String HNX30_SYMBOL = "HNX30";

	private static final String HNX_SYMBOL = "HNX";

	private static final String VN30_SYMBOL = "VN30";

	private static final String VNINDEX_SYMBOL = "VNINDEX";

	private final static Logger logger = Logger.getLogger(HighStockAction.class);

	private static final String MFI_INDICATOR = "ChartMFIIndicator";
	private static final String B_BANDS_INDICATOR = "ChartBBandsIndicator";
	private static final String SMA_INDICATOR = "ChartSMAIndicator";
	private static final String EMA_INDICATOR = "ChartEMAIndicator";
	private static final String MACD_INDICATOR = "ChartMACDIndicator";
	private static final String PSAR_INDICATOR = "ChartPSARIndicator";
	private static final String ROC_INDICATOR = "ChartROCIndicator";
	private static final String RSI_INDICATOR = "ChartRSIIndicator";
	private static final String SLOW_STOCHASTIC_INDICATOR = "ChartSlowStochastricIndicator";
	private static final String FAST_STOCHASTIC_INDICATOR = "ChartFastStochastricIndicator";
	private static final String VOLUME_MA_INDICATOR = "ChartVolumeMAIndicator";
	private static final String WILLIAMSR_INDICATOR = "ChartWilliamsRIndicator";

	private HighStockModel model = new HighStockModel();

	// SMA params
	private String smaPeriod1;
	private String smaPeriod2;
	private String smaPeriod3;
	// EMA params
	private String emaPeriod1;
	private String emaPeriod2;
	private String emaPeriod3;
	// BBands params
	private String bBandsPeriod;
	private String bBandsDeviation;
	// MFI params
	private String mfiPeriod;
	// MACD params
	private String slowPeriod;
	private String fastPeriod;
	private String signalPeriod;
	// PSAR params
	private String stepPeriod;
	private String maxStepPeriod;
	// ROC params
	private String rocPeriod;
	// RSI params
	private String rsiPeriod;
	// SS params
	private String kPeriod;
	private String dPeriod;
	// FS params
	private String fskPeriod;
	private String fsdPeriod;
	// Volume MA
	private String vmaPeriod;
	// Wiliams R
	private String wrPeriod;

	@Autowired
	private IHighStockManager highStockManager;

	@Autowired
	private IChartManager chartManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * init method
	 */
	public String getHistoricalPrice() throws Exception {
		final String LOCATION = "getHistoricalPrice";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp;
			String symbolCompany = StringUtils.EMPTY;
			symbolCompany = model.getSymbol();
			if (!StringUtils.isNotEmpty(symbolCompany)) {
				symbolCompany = SessionManager.getSymbolCompany();
			}
			if(symbolCompany == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbolCompany, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentComp == null) {
				return ERROR;
			}
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			// do SEO on-page
			String title = "VNDIRECT-" + this.getText("analysis.stockInfo.company.snapshot") + " | ";
			if (I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()).equalsIgnoreCase(VN_LOCALE)) {
				title += currentComp.getCompanyName();
			} else {
				title += currentComp.getCompanyFullName();
			}
			title += "-" + currentComp.getSymbol();
			model.setPageTitle(title);
			model.setPageDescription(this.getText("analysis.stockInfo.desc") + currentComp.getSymbol());
			String[] keywords = this.getText("analysis.stockInfo.keywords").trim().split(",");
			String dynamicKeyword = "";
			for (int i = 0; i < keywords.length; i++) {
				dynamicKeyword += keywords[i] + " " + currentComp.getSymbol();
				if (i != keywords.length - 1) {
					dynamicKeyword += ",";
				}
			}
			model.setPageKeywords(dynamicKeyword);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("analysis.stockInfo.charts"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			String symbol = StringUtils.isNotEmpty(model.getSymbol()) ? model.getSymbol().toUpperCase() : currentComp.getSymbol()
					.toUpperCase();
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(symbol, null);
			final FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateHistoricalPrice(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
			model.setSymbol(symbol);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getHistoricalPriceAjax() throws Exception {
		final String LOCATION = "getHistoricalPriceAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final String symbol = model.getSymbol().toUpperCase();
			if (VNINDEX_SYMBOL.equals(symbol) || VN30_SYMBOL.equals(symbol) || HNX_SYMBOL.equals(symbol)
					|| HNX30_SYMBOL.equals(symbol) || UPCOM_SYMBOL.equals(symbol)) {
				final CurrentCompanyForQuote companyForQuote = quotesManager.quickSearchQuotes(symbol,
						I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				if (companyForQuote != null) {
					SessionManager.setSymbolCompany(companyForQuote.getSymbol());
				}
			}
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(symbol, null);
			final FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateHistoricalPrice(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	/**
	 * get data from last [x] month
	 */
	public String getLimitHistoricalPriceAjax() throws Exception {
		final String LOCATION = "getLimitHistoricalPriceAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final String symbol = model.getSymbol().toUpperCase();
			if (VNINDEX_SYMBOL.equals(symbol) || VN30_SYMBOL.equals(symbol) || HNX_SYMBOL.equals(symbol)
					|| HNX30_SYMBOL.equals(symbol) || UPCOM_SYMBOL.equals(symbol)) {
				final CurrentCompanyForQuote companyForQuote = quotesManager.quickSearchQuotes(symbol,
						I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				if (companyForQuote != null) {
					SessionManager.setSymbolCompany(companyForQuote.getSymbol());
				}
			}
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MONTH, model.getLimitMonths());
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(symbol, now);
			final FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateHistoricalPrice(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getSMAAjax() throws Exception {
		final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

		final IndicatorInfo indicatorInfo = new IndicatorInfo(SMA_INDICATOR);
		if (StringUtils.isNotEmpty(smaPeriod1)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_1, Integer.parseInt(smaPeriod1));
		}
		if (StringUtils.isNotEmpty(smaPeriod2)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_2, Integer.parseInt(smaPeriod2));
		}
		if (StringUtils.isNotEmpty(smaPeriod3)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_3, Integer.parseInt(smaPeriod3));
		}
		final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
		indicatorInfos.add(indicatorInfo);

		final TAChartInfo chartInfo = new TAChartInfo();
		chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setLegend(true);
		chartInfo.setListIndicator(indicatorInfos);
		HighStock highStock = null;
		final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
		if (flashChartBean != null) {
			highStock = highStockManager.generateSMA(flashChartBean);
		}
		if (highStock != null) {
			model.setHighStock(highStock);
		}

		return SUCCESS;
	}

	public String getEMAAjax() throws Exception {
		final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

		final IndicatorInfo indicatorInfo = new IndicatorInfo(EMA_INDICATOR);
		if (StringUtils.isNotEmpty(emaPeriod1)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.EMA.PERIOD_1, Integer.parseInt(emaPeriod1));
		}
		if (StringUtils.isNotEmpty(emaPeriod2)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.EMA.PERIOD_2, Integer.parseInt(emaPeriod2));
		}
		if (StringUtils.isNotEmpty(emaPeriod3)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.EMA.PERIOD_3, Integer.parseInt(emaPeriod3));
		}
		final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
		indicatorInfos.add(indicatorInfo);

		final TAChartInfo chartInfo = new TAChartInfo();
		chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setLegend(true);
		chartInfo.setListIndicator(indicatorInfos);

		final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
		HighStock highStock = null;
		if (flashChartBean != null) {
			highStock = highStockManager.generateEMA(flashChartBean);
		}
		if (highStock != null) {
			model.setHighStock(highStock);
		}

		return SUCCESS;
	}

	public String getBBandsAjax() throws Exception {
		final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

		final IndicatorInfo indicatorInfo = new IndicatorInfo(B_BANDS_INDICATOR);
		if (StringUtils.isNotEmpty(bBandsPeriod)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.BBands.PERIOD, Integer.parseInt(bBandsPeriod));
		}
		if (StringUtils.isNotEmpty(bBandsDeviation)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.BBands.DEVIATION, Integer.parseInt(bBandsDeviation));
		}
		final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
		indicatorInfos.add(indicatorInfo);

		final TAChartInfo chartInfo = new TAChartInfo();
		chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setLegend(true);
		chartInfo.setListIndicator(indicatorInfos);

		final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
		HighStock highStock = null;
		if (flashChartBean != null) {
			highStock = highStockManager.generateBBands(flashChartBean);
		}
		if (highStock != null) {
			model.setHighStock(highStock);
		}

		return SUCCESS;
	}

	public String getMFIAjax() throws Exception {
		final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

		final IndicatorInfo indicatorInfo = new IndicatorInfo(MFI_INDICATOR);
		if (StringUtils.isNotEmpty(mfiPeriod)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.MFI.PERIOD, Integer.parseInt(mfiPeriod));
		}
		final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
		indicatorInfos.add(indicatorInfo);

		final TAChartInfo chartInfo = new TAChartInfo();
		chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setLegend(true);
		chartInfo.setListIndicator(indicatorInfos);

		final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
		HighStock highStock = null;
		if (flashChartBean != null) {
			highStock = highStockManager.generateMFI(flashChartBean);
		}
		if (highStock != null) {
			model.setHighStock(highStock);
		}

		return SUCCESS;
	}

	public String getMACDAjax() throws Exception {
		final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

		final IndicatorInfo indicatorInfo = new IndicatorInfo(MACD_INDICATOR);
		if (StringUtils.isNotEmpty(slowPeriod)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.MACD.SLOW_PERIOD, Integer.parseInt(slowPeriod));
		}
		if (StringUtils.isNotEmpty(fastPeriod)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.MACD.FAST_PERIOD, Integer.parseInt(fastPeriod));
		}
		if (StringUtils.isNotEmpty(signalPeriod)) {
			indicatorInfo.addParam(TAConstants.IndicatorParams.MACD.SIGNAL_PERIOD, Integer.parseInt(signalPeriod));
		}
		final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
		indicatorInfos.add(indicatorInfo);

		final TAChartInfo chartInfo = new TAChartInfo();
		chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setLegend(true);
		chartInfo.setListIndicator(indicatorInfos);

		final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
		HighStock highStock = null;
		if (flashChartBean != null) {
			highStock = highStockManager.generateMACD(flashChartBean);
		}

		if (highStock != null) {
			model.setHighStock(highStock);
		}

		return SUCCESS;
	}

	public String getPSARAjax() throws Exception {
		final String LOCATION = "getPSARAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(PSAR_INDICATOR);
			if (StringUtils.isNotEmpty(stepPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.PSAR.STEP_PERIOD, Double.parseDouble(stepPeriod));
			}
			if (StringUtils.isNotEmpty(maxStepPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.PSAR.MAX_STEP_PERIOD, Double.parseDouble(maxStepPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generatePSAR(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getROCAjax() throws Exception {
		final String LOCATION = "getROCAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(ROC_INDICATOR);
			if (StringUtils.isNotEmpty(rocPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.ROC.PERIOD, Integer.parseInt(rocPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateROC(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getRSIAjax() throws Exception {
		final String LOCATION = "getRSIAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(RSI_INDICATOR);
			if (StringUtils.isNotEmpty(rsiPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.RSI.PERIOD, Integer.parseInt(rsiPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateRSI(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	/**
	 * Slow stochastic
	 */
	public String getSSAjax() throws Exception {
		final String LOCATION = "getSSAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(SLOW_STOCHASTIC_INDICATOR);
			if (StringUtils.isNotEmpty(kPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.SS.K_PERIOD, Integer.parseInt(kPeriod));
			}
			if (StringUtils.isNotEmpty(dPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.SS.D_PERIOD, Integer.parseInt(dPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateSS(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	/**
	 * Fast stochastic
	 */
	public String getFSAjax() throws Exception {
		final String LOCATION = "getFSAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(FAST_STOCHASTIC_INDICATOR);
			if (StringUtils.isNotEmpty(fskPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.FS.K_PERIOD, Integer.parseInt(fskPeriod));
			}
			if (StringUtils.isNotEmpty(fsdPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.FS.D_PERIOD, Integer.parseInt(fsdPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateFS(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getVolumeMAAjax() throws Exception {
		final String LOCATION = "getVolumeMAAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(VOLUME_MA_INDICATOR);
			if (StringUtils.isNotEmpty(vmaPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.VolumeMA.MA_PERIOD, Integer.parseInt(vmaPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateVolumeMA(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String getWRAjax() throws Exception {
		final String LOCATION = "getWRAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(model.getSymbol(), null);

			final IndicatorInfo indicatorInfo = new IndicatorInfo(WILLIAMSR_INDICATOR);
			if (StringUtils.isNotEmpty(wrPeriod)) {
				indicatorInfo.addParam(TAConstants.IndicatorParams.WilliamsR.MA_PERIOD, Integer.parseInt(wrPeriod));
			}
			final List<IndicatorInfo> indicatorInfos = new ArrayList<IndicatorInfo>();
			indicatorInfos.add(indicatorInfo);

			final TAChartInfo chartInfo = new TAChartInfo();
			chartInfo.setDataType(ChartInfo.ADJUST_DATA_TYPE);
			chartInfo.setLegend(true);
			chartInfo.setListIndicator(indicatorInfos);

			final FlashChartBean flashChartBean = chartManager.generateDataForFlashChart(chartInfo, searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateWR(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public HighStockModel getModel() {
		return this.model;
	}

	/**
	 * @param startDate
	 *            if startDate null, getStartDateDefault() [1-1-2000]
	 */
	private SearchIfoSecPrice getSearchIfoSecPrice(String symbol, Calendar startDate) {
		SearchIfoSecPrice searchIfoSecPrice = new SearchIfoSecPrice();
		searchIfoSecPrice.setSymbol(symbol);
		if (startDate != null) {
			searchIfoSecPrice.setStartDate(startDate.getTime());
		} else {
			searchIfoSecPrice.setStartDate(this.getStartDateDefault());
		}
		searchIfoSecPrice.setEndDate(this.getCurrentDateDefault());
		if (VNINDEX_SYMBOL.equalsIgnoreCase(symbol)) {
			searchIfoSecPrice.setFloorCode(ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HCM_STC_INDEX));
		} else if (VN30_SYMBOL.equalsIgnoreCase(symbol)) {
			searchIfoSecPrice.setFloorCode(ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.VN30_INDEX));
		} else if (HNX_SYMBOL.equalsIgnoreCase(symbol)) {
			searchIfoSecPrice
					.setFloorCode(ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HANOI_STC_INDEX));
		} else if (HNX30_SYMBOL.equalsIgnoreCase(symbol)) {
			searchIfoSecPrice.setFloorCode(ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.HNX30_INDEX));
		} else if (UPCOM_SYMBOL.equalsIgnoreCase(symbol)) {
			searchIfoSecPrice.setFloorCode(ServerConfig.getOnlineValue(Constants.IServerConfig.VNMarket.FloorCode.UPCOM_INDEX));
		}

		return searchIfoSecPrice;
	}

	private Date getStartDateDefault() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2000);

		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		return cal1.getTime();
	}

	private Date getCurrentDateDefault() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		return cal1.getTime();
	}

	public void setHighStockManager(IHighStockManager highStockManager) {
		this.highStockManager = highStockManager;
	}

	public void setChartManager(IChartManager chartManager) {
		this.chartManager = chartManager;
	}

	public String getSmaPeriod1() {
		return smaPeriod1;
	}

	public void setSmaPeriod1(String smaPeriod1) {
		this.smaPeriod1 = smaPeriod1;
	}

	public String getSmaPeriod2() {
		return smaPeriod2;
	}

	public void setSmaPeriod2(String smaPeriod2) {
		this.smaPeriod2 = smaPeriod2;
	}

	public String getSmaPeriod3() {
		return smaPeriod3;
	}

	public void setSmaPeriod3(String smaPeriod3) {
		this.smaPeriod3 = smaPeriod3;
	}

	public String getEmaPeriod1() {
		return emaPeriod1;
	}

	public void setEmaPeriod1(String emaPeriod1) {
		this.emaPeriod1 = emaPeriod1;
	}

	public String getEmaPeriod2() {
		return emaPeriod2;
	}

	public void setEmaPeriod2(String emaPeriod2) {
		this.emaPeriod2 = emaPeriod2;
	}

	public String getEmaPeriod3() {
		return emaPeriod3;
	}

	public void setEmaPeriod3(String emaPeriod3) {
		this.emaPeriod3 = emaPeriod3;
	}

	public String getbBandsPeriod() {
		return bBandsPeriod;
	}

	public void setbBandsPeriod(String bBandsPeriod) {
		this.bBandsPeriod = bBandsPeriod;
	}

	public String getbBandsDeviation() {
		return bBandsDeviation;
	}

	public void setbBandsDeviation(String bBandsDeviation) {
		this.bBandsDeviation = bBandsDeviation;
	}

	public String getMfiPeriod() {
		return mfiPeriod;
	}

	public void setMfiPeriod(String mfiPeriod) {
		this.mfiPeriod = mfiPeriod;
	}

	public String getSlowPeriod() {
		return slowPeriod;
	}

	public void setSlowPeriod(String slowPeriod) {
		this.slowPeriod = slowPeriod;
	}

	public String getFastPeriod() {
		return fastPeriod;
	}

	public void setFastPeriod(String fastPeriod) {
		this.fastPeriod = fastPeriod;
	}

	public String getSignalPeriod() {
		return signalPeriod;
	}

	public void setSignalPeriod(String signalPeriod) {
		this.signalPeriod = signalPeriod;
	}

	public String getStepPeriod() {
		return stepPeriod;
	}

	public void setStepPeriod(String stepPeriod) {
		this.stepPeriod = stepPeriod;
	}

	public String getMaxStepPeriod() {
		return maxStepPeriod;
	}

	public void setMaxStepPeriod(String maxStepPeriod) {
		this.maxStepPeriod = maxStepPeriod;
	}

	public String getRocPeriod() {
		return rocPeriod;
	}

	public void setRocPeriod(String rocPeriod) {
		this.rocPeriod = rocPeriod;
	}

	public String getRsiPeriod() {
		return rsiPeriod;
	}

	public void setRsiPeriod(String rsiPeriod) {
		this.rsiPeriod = rsiPeriod;
	}

	public String getkPeriod() {
		return kPeriod;
	}

	public void setkPeriod(String kPeriod) {
		this.kPeriod = kPeriod;
	}

	public String getdPeriod() {
		return dPeriod;
	}

	public void setdPeriod(String dPeriod) {
		this.dPeriod = dPeriod;
	}

	public String getFskPeriod() {
		return fskPeriod;
	}

	public void setFskPeriod(String fskPeriod) {
		this.fskPeriod = fskPeriod;
	}

	public String getFsdPeriod() {
		return fsdPeriod;
	}

	public void setFsdPeriod(String fsdPeriod) {
		this.fsdPeriod = fsdPeriod;
	}

	public String getVmaPeriod() {
		return vmaPeriod;
	}

	public void setVmaPeriod(String vmaPeriod) {
		this.vmaPeriod = vmaPeriod;
	}

	public String getWrPeriod() {
		return wrPeriod;
	}

	public void setWrPeriod(String wrPeriod) {
		this.wrPeriod = wrPeriod;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public String initRTChart() throws Exception {
		final String LOCATION = "initRTChart";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			String symbol = StringUtils.isEmpty(SessionManager.getSymbolCompany()) ? VNINDEX_SYMBOL : SessionManager.getSymbolCompany();
			final CurrentCompanyForQuote companyForQuote = quotesManager.quickSearchQuotes(symbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if(companyForQuote == null){
				logger.error(LOCATION + "::CurrentCompanyForQuote is null ....");
				return ERROR;
			}
			
			SessionManager.setSymbolCompany(companyForQuote.getSymbol());

			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(symbol, null);
			final FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateHistoricalPrice(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Technical.Charts"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
			breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
			model.setBreadcrumbs(breadcrumbs);

			model.setSymbol(symbol);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		doSEOpageSymbol(model.getSymbol());

		return SUCCESS;
	}

	public String getRTMarketViewAjax() throws Exception {
		final String LOCATION = "getRTMarketViewAjax";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(model.getSymbol(), locale);
			SessionManager.setSymbolCompany(currentComp.getSymbol());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	public String initIrSiChart() throws Exception {
		final String LOCATION = "initIrSiChart";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			final CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(VND_SYMBOL,
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.chart"));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("analysis.stockInfo.charts"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			final SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(currentComp.getSymbol(), null);
			final FlashChartBean flashChartBean = chartManager.generateDataForHistorical(searchIfoSecPrice);
			HighStock highStock = null;
			if (flashChartBean != null) {
				highStock = highStockManager.generateHistoricalPrice(flashChartBean);
			}
			if (highStock != null) {
				model.setHighStock(highStock);
			}
			model.setSymbol(currentComp.getSymbol());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return SUCCESS;
	}

	private void doSEOpageSymbol(String symbol) {
		model.setPageTitle(this.getText("analysis.chart.title") + " " + symbol);
		model.setPageDescription(this.getText("analysis.chart.des") + " " + symbol);
		model.setPageKeywords(this.getText("analysis.chart.keywords") + " " + symbol);
	}
}
