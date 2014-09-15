package vn.com.vndirect.domain.struts2.portlet;

import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoNews;

@SuppressWarnings("serial")

public class LastestMarketNewsAJAXModel extends BaseModel {
	private IfoNews ifoNews;
	private String urlDetail = "";

	public IfoNews getIfoNews() {
		return ifoNews;
	}

	public void setIfoNews(IfoNews ifoNews) {
		this.ifoNews = ifoNews;
		// set detailURL
		urlDetail = NewsUrlUtility.createUrl("DailyReport", ifoNews.getNewsHeader(), ifoNews.getNewsId(), "");
	}

	public String getUrlDetail() {
		return urlDetail;
	}
}
