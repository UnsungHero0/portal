/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Sep 9, 2007  tungnq.nguyen     First generate code
 *--------------------------------------------------------------------------*/
package vn.com.vndirect.web.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import vn.com.vndirect.business.IChartManager;
import vn.com.vndirect.commons.jfreechart.ChartObject;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.TAConstants;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.TechnicalAnalysisChart;
import vn.com.web.commons.utility.SpringUtils;

/**
 * @author tungnq.nguyen
 * 
 *         Request object contains the flowing parameter:
 * @param: s: is symbol of a company, for ex: BBC. "HASTC" for HASTC, "VNIndex" for HOSTC
 * @param: e: is exchange code: HASTC/HOSTC, default value is empty (optional)
 * @param: sc: is scale (LINEAR|LOG), default value is "LINEAR" (optional)
 * @param: t: is chartType value (LINE|OHLC|CAND), default value is "LINE" (optional)
 * @param: l: is displaying legend of chart or not (true|false), default value is "true" (optional)
 * @param: vi: is display VolumeIndicator or not (true | false), default value is "true" (optional)
 * @param: pd: is priorDay, default value is "0" (optional)
 * @param: pm: priorMonth, default value is "-1" (one month) if all value of "pd", "pm" & "py" are "0" (optional)
 * @param: py: priorYear, default value is "0" (optional)
 * @param: h: is height of chart; height = ( height < 100 ? 150 : height)
 * @param: w: is width of chart; width = (width < 100 ? 250 : width)
 * @param: wh: is width & height of chart with the flowing value: +> s: small (250px x 150px) +> m: medium (500px x 300px) +> l: large (750px x 410px) if "wh" is in URL then "w" & "h" params will be
 *         ignored.
 * 
 *         Example URL: http://localhost:8888/vndirect/viewchart.tachart?s=BBC http://localhost:8888/vndirect/viewchart.tachart?s=BBC&wh=m
 *         http://localhost:8888/vndirect/viewchart.tachart?s=BBC&wh=m&pm=-4 http://localhost:8888/vndirect/viewchart.tachart?s=BBC&e=HOSTC&w=500&h=300&pm=-4
 *         http://localhost:8888/vndirect/viewchart.tachart?s=BBC&e=HOSTC&wh=f&pm=-4 http://localhost:8888/vndirect/viewchart.tachart?s=BBC&e=HOSTC&wh=f&l=false&vi=false&pm=-4
 */
public class TAChartServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5734128283913114326L;
	private static Logger logger = Logger.getLogger(TAChartServlet.class);
	private IChartManager chartManager;

	private String CHART_MANAGER_MAPPING = "TAChartManager";
	private String DEDAULT_IMG = "/images/chart/default.ta.chart.jpg";

	/**
     *
     */
	public TAChartServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
     *
     */
	public void init() throws ServletException {
		// Put your code here
		String str;
		str = this.getInitParameter("chartManagerKey");
		str = (str == null ? "" : str.trim());
		if (str.length() > 0) {
			CHART_MANAGER_MAPPING = str;
		}

		str = this.getInitParameter("defaultImg");
		str = (str == null ? "" : str.trim());
		if (str.length() > 0) {
			DEDAULT_IMG = str;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream bytesOut = null;

		try {
			if (chartManager == null) {
				chartManager = (IChartManager) SpringUtils.getBean(CHART_MANAGER_MAPPING);
			}

			SearchIfoSecPrice searchIfoSecPrice = this.getSearchIfoSecPrice(request);
			TAChartInfo chartInfo = this.getTAChartInfo(request);
			logger.warn("+++>>> " + chartInfo);

			final ChartObject chart = chartManager.generateChartForTA(chartInfo, searchIfoSecPrice, new TechnicalAnalysisChart(), chartInfo.hashCode());

			byte[] byteschart = null;
			if (chart != null && chart.getBytes() != null && chart.getBytes().length != 0) {
				byteschart = chart.getBytes();
				bytesOut = response.getOutputStream();
				if (chart != null) {
					bytesOut.write(byteschart);
				}
				// else {
				// bytesOut.write(new byte[0]);
				// }
			} else {
				request.getRequestDispatcher(DEDAULT_IMG).forward(request, response);
			}
		} catch (Exception e) {
			logger.warn("TAChartServlet ", e);
		} finally {
			try {
				if (bytesOut != null) {
					bytesOut.close();
					bytesOut = null;
				}
			} catch (IOException ioe) {
				logger.error("IOException : " + ioe.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param request
	 *            contains the flowing parameter: - symbol: BBC - s - exchange: HASTC/HOSTC - e - height: 300 - h - width: 150 - w - scale: LINEAR|LOG - sc : default value LINEAR - chartType:
	 *            LINE|OHLC|CAND - t: default value LINE
	 * @return
	 */
	private TAChartInfo getTAChartInfo(HttpServletRequest request) {
		TAChartInfo taChartInfo = new TAChartInfo();
		// get symbol
		taChartInfo.setSymbol(this.getString(request, "s"));
		// get exchange code
		taChartInfo.setExchangeCode(this.getString(request, "e"));

		taChartInfo.setLegend(!"false".equalsIgnoreCase(this.getString(request, "l")));

		this.populateWithHight(taChartInfo, request);

		taChartInfo.setLineType(this.getLineType(this.getString(request, "t")));
		taChartInfo.setChartScale(this.getChartScale(this.getString(request, "sc")));

		// add default
		if (!"false".equalsIgnoreCase(this.getString(request, "vi"))) {
			taChartInfo.addIndicator(new IndicatorInfo(TAConstants.Indicator.ChartVolumeIndicator));
		}
		return taChartInfo;
	}

	/**
	 * 
	 * @param lineType
	 *            - symbol: BBC - s - exchange: HASTC/HOSTC - e - pd: priorDay - pm: priorMonth - py: priorYear
	 * @return
	 */
	private String getLineType(String lineType) {
		lineType = (lineType == null ? "" : lineType.toUpperCase());
		if (TAConstants.LineType.LINE.equals(lineType) || TAConstants.LineType.OHLC.equals(lineType) || TAConstants.LineType.CANDLESTICK.equals(lineType)) {
			return lineType;
		} else {
			return TAConstants.LineType.LINE;
		}
	}

	/**
	 * 
	 * @param chartScale
	 * @return
	 */
	private String getChartScale(String chartScale) {
		chartScale = (chartScale == null ? "" : chartScale.toUpperCase());
		if (TAConstants.ChartScalar.LINEAR.equals(chartScale) || TAConstants.ChartScalar.LOGARITHMIC.equals(chartScale)) {
			return chartScale;
		} else {
			return TAConstants.ChartScalar.LINEAR;
		}
	}

	/*
	 * - height: 300 - h - width: 150 - w - wh: with + height with the flowing value: +> s: small (250px x 150px) +> m: medium (500px x 300px) +> l: large (750px x 410px)
	 */
	private void populateWithHight(TAChartInfo taChartInfo, HttpServletRequest request) {
		if (taChartInfo != null) {
			int width, hight;
			String wh = this.getString(request, "wh");
			if ("s".equalsIgnoreCase(wh)) {
				width = 250;
				hight = 150;
			} else if ("m".equalsIgnoreCase(wh)) {
				width = 500;
				hight = 300;
			} else if ("f".equalsIgnoreCase(wh)) {
				width = 750;
				hight = 410;
			} else {
				width = this.getInt(request, "w");
				width = (width < 100 ? 250 : width);

				hight = this.getInt(request, "h");
				hight = (hight < 100 ? 150 : hight);
			}
			// generate LinerChart
			taChartInfo.setWidth(width);
			taChartInfo.setHight(hight);
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	protected SearchIfoSecPrice getSearchIfoSecPrice(HttpServletRequest request) {
		// get symbol
		String symbol = this.getString(request, "s");
		// get exchange code
		String exchangeCode = this.getString(request, "e");

		int priorDay = this.getInt(request, "pd");
		int priorMonth = this.getInt(request, "pm");
		int priorYear = this.getInt(request, "py");
		Calendar currentDate = Calendar.getInstance();

		SearchIfoSecPrice searchIfoSecPrice = new SearchIfoSecPrice(symbol, exchangeCode, currentDate.getTime());
		if ((priorDay + priorMonth + priorYear) == 0) {
			priorMonth = -3;
		}

		if (priorDay != 0) {
			searchIfoSecPrice.addStartDate(Calendar.DAY_OF_MONTH, priorDay < 0 ? priorDay : (-1 * priorDay));
		}

		if (priorMonth != 0) {
			searchIfoSecPrice.addStartDate(Calendar.MONTH, priorMonth < 0 ? priorMonth : (-1 * priorMonth));
		}

		if (priorYear != 0) {
			searchIfoSecPrice.addStartDate(Calendar.YEAR, priorYear < 0 ? priorYear : (-1 * priorYear));
		}
		return searchIfoSecPrice;
	}

	/**
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	protected int getInt(HttpServletRequest request, String key) {
		int value = 0;
		try {
			value = Integer.parseInt(request.getParameter(key));
		} catch (Exception e) {
			logger.debug(key + ":" + e);
		}
		return value;
	}

	/**
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	protected String getString(HttpServletRequest request, String key) {
		try {
			String str = request.getParameter(key);
			return str == null ? null : str.trim();
		} catch (Exception e) {
			logger.debug(key + ":" + e);
		}
		return "";
	}
}
