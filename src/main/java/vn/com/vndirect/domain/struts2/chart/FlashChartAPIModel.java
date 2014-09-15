package vn.com.vndirect.domain.struts2.chart;

import vn.com.vndirect.domain.BaseModel;

@SuppressWarnings("serial")
public class FlashChartAPIModel extends BaseModel {
	// -------- out put content
	private String chartXMLContent;

	public String getChartXMLContent() {
		return chartXMLContent;
	}

	public void setChartXMLContent(String chartXMLContent) {
		this.chartXMLContent = chartXMLContent;
	}
}
