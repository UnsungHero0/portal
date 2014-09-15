package vn.com.vndirect.domain.struts2.chart;

import java.util.Iterator;
import java.util.List;

import vn.com.vndirect.commons.jfreechart.ChartObject;
import vn.com.vndirect.commons.jfreechart.TAChartInfo;
import vn.com.vndirect.commons.jfreechart.beans.CompetitorInfo;
import vn.com.vndirect.commons.jfreechart.beans.IndicatorInfo;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoExchangeCode;

@SuppressWarnings( { "unchecked", "serial" })
public class TechnicalChartModel extends BaseModel {
	private ChartObject taChart;
	private TAChartInfo chartInfo;
	private String chartURL;
	private List<IfoExchangeCode> listIfoExchange;

	public List<IfoExchangeCode> getListIfoExchange() {
		return listIfoExchange;
	}

	public void setListIfoExchange(List<IfoExchangeCode> listIfoExchange) {
		this.listIfoExchange = listIfoExchange;
	}

	public String getChartURL() {
		return chartURL;
	}

	public void setChartURL(String chartURL) {
		this.chartURL = chartURL;
	}

	/**
	 * @return the taChart
	 */
	public ChartObject getTaChart() {
		return taChart;
	}

	/**
	 * @param taChart
	 *            the taChart to set
	 */
	public void setTaChart(ChartObject taChart) {
		this.taChart = taChart;
	}

	/**
	 * @return the chartInfo
	 */
	public TAChartInfo getChartInfo() {
		return chartInfo;
	}

	/**
	 * @param chartInfo
	 *            the chartInfo to set
	 */
	public void setChartInfo(TAChartInfo chartInfo) {
		this.chartInfo = chartInfo;
	}

	/**
	 * 
	 * @return
	 */
	public String getStrListIndicator() {
		StringBuffer strBuf = new StringBuffer();
		if (chartInfo != null && chartInfo.getListIndicator() != null) {
			Iterator iter = chartInfo.getListIndicator().iterator();
			IndicatorInfo objInfo;
			while (iter.hasNext()) {
				objInfo = (IndicatorInfo) iter.next();
				strBuf.append(strBuf.length() == 0 ? "" : "|").append(objInfo.getName());
			}
		}
		return strBuf.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getStrListCompetitor() {
		StringBuffer strBuf = new StringBuffer();
		if (chartInfo != null && chartInfo.getListCompetitor() != null) {
			Iterator iter = chartInfo.getListCompetitor().iterator();
			CompetitorInfo objInfo;
			while (iter.hasNext()) {
				objInfo = (CompetitorInfo) iter.next();
				strBuf.append(strBuf.length() == 0 ? "" : "|").append(objInfo.getSymbol());
			}
		}
		return strBuf.toString();
	}
}
