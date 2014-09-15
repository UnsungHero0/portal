package vn.com.vndirect.web.struts2.chart;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IChartManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.jfreechart.ChartConstants;
import vn.com.vndirect.commons.jfreechart.ChartObject;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.vndirect.commons.jfreechart.beans.CompetitorInfo;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.TechnicalAnalysisChart;
import vn.com.vndirect.domain.struts2.chart.TechnicalChartModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ModelDriven;

public class TechnicalChartAction extends BaseChartActionSupport implements ModelDriven<TechnicalChartModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3036644658638153588L;

	private final static Logger logger = Logger.getLogger(FlashChartAPIAction.class);

	private final static String TECHNICAL_ANALYSIS_CHART_INFO = "%TECHNICAL_ANALYSIS_CHART_INFO%";

	@Autowired
	private IChartManager chartManager;
	
	@Autowired
	private static IQuotesManager quotesManager;

	private TechnicalChartModel model = new TechnicalChartModel();

	/**
	 * Write image to IF/FF
	 */
	private int length;
	private InputStream inputStream;

	public InputStream getInputStream() throws Exception {
		return inputStream;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @param chartManager
	 *            the chartManager to set
	 */
	public void setChartManager(IChartManager chartManager) {
		this.chartManager = chartManager;
	}

	public TechnicalChartModel getModel() {
		return this.model;
	}

	public String getChartStream() throws Exception {
		String cacheKey = request.getParameter("cacheKey");
		ChartObject chart = chartManager.getChartFromCacheKey(cacheKey);

		if (chart != null && chart.getBytes() != null) {
			this.inputStream = new ByteArrayInputStream(chart.getBytes());
			this.length = chart.getBytes().length;
		} else {
			inputStream = new ByteArrayInputStream(new byte[0]);
			this.length = 0;
		}
		return SUCCESS;
	}

	public String getChart() throws Exception {
		try {
			SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(request);
			TAChartInfo chartInfo = this.getTAChartInfo(request);

			logger.debug("chartInfo 1:" + chartInfo);
			String userAction = this.getUserAction();

			if (TAConstants.UserAction.CHART_SCALE.equalsIgnoreCase(userAction)) {

			} else if (TAConstants.UserAction.LINE_TYPE.equalsIgnoreCase(userAction)) {

			} else if (TAConstants.UserAction.INDICATOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processIndicator(chartInfo, request);

			} else if (TAConstants.UserAction.COMPETITOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processComperitor(chartInfo, request);

			} else if (TAConstants.UserAction.ADD_REMOVE_SYMBOL_COMPETITOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processAddRemoveSymbolComperitor(chartInfo, request);
			}

			TechnicalAnalysisChart taChart = new TechnicalAnalysisChart();
			chartInfo.setHight(ChartConstants.ChartSetting.TA_MAIN_CHART_HEIGHT);
			chartInfo.setLegend(true);
			ChartObject chart = chartManager.generateChartForTA(chartInfo, searchIfoSecPrice, taChart, chartInfo.hashCode());

			logger.debug("chartInfo 2:" + chartInfo);
			logger.debug("chart:" + chart);

			// update to model
			model.setTaChart(chart);
			model.setChartInfo(chartInfo);
			String context = ServletActionContext.getServletContext().getContextPath();
			String str = Utilities.FormatURL.url(context, "/ajax/analysis/ViewClassicChart.shtml");

			// if(URLUtils.checkExt(str, ".shtml")) {
			// str = Utilities.FormatURL.encodeURI(str, request);
			// }

			if (chart != null && chart.getCacheKey() != null && chart.getBytes() != null) {
				model.setChartURL(str);
				// this.inputStream = new
				// ByteArrayInputStream(chart.getBytes());
				// this.length = chart.getBytes().length;
			} else {
				// inputStream = new ByteArrayInputStream(new byte[0]);
				// this.length = 0;
				model.setChartURL("");
			}
		} catch (Exception e) {
			Utilities.processErrors(this, e);
			return INPUT;
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String execute() throws Exception {
		final String LOCATION = "execute()";
		logger.debug(LOCATION + ":: BEGIN");
		try {
			SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(request);
			TAChartInfo chartInfo = this.getTAChartInfo(request);

			logger.debug("chartInfo 1:" + chartInfo);
			String userAction = this.getUserAction();

			if (TAConstants.UserAction.CHART_SCALE.equalsIgnoreCase(userAction)) {

			} else if (TAConstants.UserAction.LINE_TYPE.equalsIgnoreCase(userAction)) {

			} else if (TAConstants.UserAction.INDICATOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processIndicator(chartInfo, request);

			} else if (TAConstants.UserAction.COMPETITOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processComperitor(chartInfo, request);

			} else if (TAConstants.UserAction.ADD_REMOVE_SYMBOL_COMPETITOR.equalsIgnoreCase(userAction)) {
				chartInfo = this.processAddRemoveSymbolComperitor(chartInfo, request);
			}

			TechnicalAnalysisChart taChart = new TechnicalAnalysisChart();
			chartInfo.setHight(ChartConstants.ChartSetting.TA_MAIN_CHART_HEIGHT);
			chartInfo.setLegend(true);
			ChartObject chart = chartManager.generateChartForTA(chartInfo, searchIfoSecPrice, taChart, chartInfo.hashCode());

			logger.debug("chartInfo 2:" + chartInfo);
			logger.debug("chart:" + chart);

			// update to model
			model.setTaChart(chart);
			model.setChartInfo(chartInfo);

			if (chart != null && chart.getCacheKey() != null && chart.getBytes() != null) {
				this.inputStream = new ByteArrayInputStream(chart.getBytes());
				this.length = chart.getBytes().length;
			} else {
				inputStream = new ByteArrayInputStream(new byte[0]);
				this.length = 0;
			}
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new FunctionalException(LOCATION, e);
		}
		logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected TAChartInfo processIndicator(TAChartInfo chartInfo, HttpServletRequest request) {
		final String LOCATION = "processIndicator()";
		logger.debug(LOCATION + ":: BEGIN");
		chartInfo = (chartInfo == null ? this.getTAChartInfo(request) : chartInfo);
		String chartAction = this.getChartAction();
		String selectedIndicator = this.getString(TAConstants.ChartParams.SELECTED_INDICATOR);
		IndicatorInfo indicatorInfo = new IndicatorInfo(selectedIndicator);

		if (TAConstants.ChartAction.REMOVE.equalsIgnoreCase(chartAction)) {
			chartInfo.removeIndicator(indicatorInfo);

		} else if (TAConstants.ChartAction.DRAW.equalsIgnoreCase(chartAction)) {
			String[] params = TAConstants.IndicatorParams.getListParamsByIndicator(selectedIndicator);
			if (params != null) {
				indicatorInfo = this.populateIndicatorValues(indicatorInfo, params);
			}
			chartInfo.addIndicator(indicatorInfo);
		}
		logger.debug(LOCATION + ":: END");
		return chartInfo;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected TAChartInfo processComperitor(TAChartInfo chartInfo, HttpServletRequest request) {
		final String LOCATION = "processComperitor()";
		logger.debug(LOCATION + ":: BEGIN");
		chartInfo = (chartInfo == null ? this.getTAChartInfo(request) : chartInfo);
		String exchangeCode = this.getString(TAConstants.ChartParams.SELECTED_EXCHANGE_CODE);
		String selectedIndicator = this.getString(TAConstants.ChartParams.SELECTED_COMPETITOR);
		CompetitorInfo competitorInfo = new CompetitorInfo(selectedIndicator, exchangeCode);

		String chartAction = this.getChartAction();

		if (TAConstants.ChartAction.REMOVE.equalsIgnoreCase(chartAction)) {
			chartInfo.removeCompatitor(competitorInfo);

		} else if (TAConstants.ChartAction.DRAW.equalsIgnoreCase(chartAction)) {
			chartInfo.addCompatitor(competitorInfo);
		}
		logger.debug(LOCATION + ":: END");
		return chartInfo;
	}

	/**
	 * 
	 * @param chartInfo
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected TAChartInfo processAddRemoveSymbolComperitor(TAChartInfo chartInfo, HttpServletRequest request) {
		final String LOCATION = "processAddRemoveSymbolComperitor()";
		logger.debug(LOCATION + ":: BEGIN");
		chartInfo = (chartInfo == null ? this.getTAChartInfo(request) : chartInfo);
		String removedIndicator = this.getString(TAConstants.ChartParams.REMOVEWD_COMPETITOR);
		String selectedIndicator = this.getString(TAConstants.ChartParams.SELECTED_COMPETITOR);
		String exchangeCode = StringUtils.EMPTY;
		CurrentCompanyForQuote currentCompanyForQuote = null;
		try {
			currentCompanyForQuote = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
		} catch (FunctionalException e) {
			logger.error(LOCATION, e);
		} catch (SystemException e) {
			logger.error(LOCATION, e);
		}
		exchangeCode = currentCompanyForQuote==null? StringUtils.EMPTY : currentCompanyForQuote.getCurrentExchangeCode();

		StringTokenizer token = null;
		// process selectedIndicators
		logger.debug("+++++> selectedIndicator: " + selectedIndicator);
		if (selectedIndicator != null && selectedIndicator.length() > 0) {
			token = new StringTokenizer(selectedIndicator, "|");
			while (token.hasMoreTokens()) {
				chartInfo.addCompatitor(new CompetitorInfo(token.nextToken(), exchangeCode));
			}
		}
		// process removedIndicators
		logger.debug("+++++> removedIndicator: " + removedIndicator);
		if (removedIndicator != null && removedIndicator.length() > 0) {
			token = new StringTokenizer(removedIndicator, "|");
			while (token.hasMoreTokens()) {
				chartInfo.removeCompatitor(new CompetitorInfo(token.nextToken(), exchangeCode));
			}
		}

		logger.debug(LOCATION + ":: END");
		return chartInfo;
	}

	/**
	 * 
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	private TAChartInfo getTAChartInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		TAChartInfo chartInfo = (TAChartInfo) session.getAttribute(TECHNICAL_ANALYSIS_CHART_INFO);
		if (chartInfo == null) {
			chartInfo = resetTAChartInfo(request);
		}

		chartInfo.setLegend(true);

		int linerWidth = this.getInt("width");
		linerWidth = (linerWidth < 100 ? 760 : linerWidth);

		int linerHight = this.getInt("hight");
		linerHight = (linerHight < 100 ? 350 : linerHight);

		int dataType = this.getInt("dataType");
		dataType = (dataType == TAChartInfo.NORMAL_DATA_TYPE ? TAChartInfo.NORMAL_DATA_TYPE : TAChartInfo.ADJUST_DATA_TYPE);
		chartInfo.setDataType(dataType);

		// generate LinerChart
		chartInfo.setWidth(linerWidth);
		chartInfo.setHight(linerHight);

		chartInfo.setLineType(this.getString("lineType"));

		chartInfo.setChartScale(this.getString("chartScale"));
		return chartInfo;
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
					logger.debug("Exception: convert double | key:" + key);
				}
			}
		}
		return indicator;
	}

	/**
	 * 
	 * @param session
	 * @return
	 */
	public static TAChartInfo resetTAChartInfo(HttpServletRequest request) {
		final String LOCATION = "resetTAChartInfo()";
		HttpSession session = request.getSession();
		TAChartInfo info = (TAChartInfo) session.getAttribute(TECHNICAL_ANALYSIS_CHART_INFO);
		if (info == null) {
			info = new TAChartInfo();
			session.setAttribute(TECHNICAL_ANALYSIS_CHART_INFO, info);
		}
		info.setSymbol(SessionManager.getSymbolCompany());
		CurrentCompanyForQuote currentCompanyForQuote = null;
		 
		try {
			currentCompanyForQuote = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
		} catch (FunctionalException e) {
			logger.error(LOCATION, e);
		} catch (SystemException e) {
			logger.error(LOCATION, e);
		}
		
		String exchangeCode = currentCompanyForQuote==null? StringUtils.EMPTY : currentCompanyForQuote.getCurrentExchangeCode();
		info.setExchangeCode(exchangeCode);

		info.addIndicator(new IndicatorInfo(TAConstants.Indicator.ChartVolumeIndicator));

		// IndicatorInfo indiInfo = new
		// IndicatorInfo(TAConstants.Indicator.ChartSMAIndicator);
		// indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_1, 5);
		// indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_2, 15);
		// indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_3, 25);
		// info.addIndicator(indiInfo);

		info.setLegend(true);

		return info;
	}

	/**
	 * 
	 * @param request
	 * @param ajaxRequest
	 * @return
	 */
	protected SearchIfoSecPrice getSearchIfoSecPrice(HttpServletRequest request) {
		final String LOCATION = "getSearchIfoSecPrice()";
		String date = this.getString(TAConstants.ChartParams.DATE);
		String format = this.getString(TAConstants.ChartParams.FORMAT);
		String symbol = this.getString(TAConstants.ChartParams.SYMBOL);
		String exchangeCode = this.getString(TAConstants.ChartParams.EXCHANGE_CODE);

		if (symbol == null || symbol.trim().length() == 0) {
			symbol = SessionManager.getSymbolCompany();
		}
		if (exchangeCode == null || exchangeCode.trim().length() == 0) {
			CurrentCompanyForQuote currentCompanyForQuote = null;
			try {
				currentCompanyForQuote = quotesManager.quickSearchQuotes(symbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			} catch (FunctionalException e) {
				logger.error(LOCATION, e);
			} catch (SystemException e) {
				logger.error(LOCATION, e);
			}
			
			exchangeCode = currentCompanyForQuote==null? null : currentCompanyForQuote.getCurrentExchangeCode();
		}

		int priorDay = this.getInt(TAConstants.ChartParams.PRIOR_DAY);
		int priorMonth = this.getInt(TAConstants.ChartParams.PRIOR_MONTH);
		int priorYear = this.getInt(TAConstants.ChartParams.PRIOR_YEAR);

		Calendar currentDate = Calendar.getInstance();
		Date d = null;
		try {
			d = DateUtils.stringToDate(date, format);
		} catch (Exception e) {
			logger.debug("Exception: convert date:" + date + ", format:" + format);
		}
		currentDate.setTime(d == null ? new Date() : d);

		SearchIfoSecPrice searchIfoSecPrice = new SearchIfoSecPrice(symbol, exchangeCode, currentDate.getTime());

		int offsetItem = 0;

		if (priorDay < 0) {
			searchIfoSecPrice.addStartDate(Calendar.DAY_OF_MONTH, priorDay);
			offsetItem += Math.abs(priorDay);
		}

		if (priorMonth < 0) {
			searchIfoSecPrice.addStartDate(Calendar.MONTH, priorMonth);
			offsetItem += 30 * Math.abs(priorMonth);
		}

		if (priorYear < 0) {
			searchIfoSecPrice.addStartDate(Calendar.YEAR, priorYear);
			offsetItem += 365 * Math.abs(priorYear);
		}

		searchIfoSecPrice.setOffsetItems(offsetItem);

		return searchIfoSecPrice;
	}

	public String searchSymbol() {
		try {
			model.setListIfoExchange(quotesManager.getAllIfoExchange());
		} catch (Exception e) {
			Utilities.processErrors(this, e);
			return INPUT;
		}

		return SUCCESS;
	}
}
