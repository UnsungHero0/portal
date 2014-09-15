package vn.com.vndirect.domain.struts2.chart;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.chartapi.ChartAPIModel;
import vn.com.vndirect.domain.extend.IfoIndexCalc;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings("serial")
public class ChartDataAJAXModel extends BaseModel {
	/*
	 * idxCode is used to keep symbol, sector, industry code
	 */
	private String idxCode;
	private String strFromDate;
	private String strToDate;
	private int pm;

	private ChartAPIModel indexChartApi = new ChartAPIModel();

	private SearchResult<IfoSecPriceView> lstSecPrice;
	private SearchResult<IfoIndexCalc> lstIndexCalc;

	/**
	 * @return the idxCode
	 */
	public String getIdxCode() {
		return idxCode;
	}

	/**
	 * @param idxCode
	 *            the idxCode to set
	 */
	public void setIdxCode(String idxCode) {
		this.idxCode = idxCode;
	}

	/**
	 * @return the strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}

	/**
	 * @param strFromDate
	 *            the strFromDate to set
	 */
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	/**
	 * @return the strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}

	/**
	 * @param strToDate
	 *            the strToDate to set
	 */
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	/**
	 * @return the lstSecPrice
	 */
	public SearchResult<IfoSecPriceView> getLstSecPrice() {
		return lstSecPrice;
	}

	/**
	 * @param lstSecPrice
	 *            the lstSecPrice to set
	 */
	public void setLstSecPrice(SearchResult<IfoSecPriceView> lstSecPrice) {
		this.lstSecPrice = lstSecPrice;
	}

	/**
	 * @return the lstIndexCalc
	 */
	public SearchResult<IfoIndexCalc> getLstIndexCalc() {
		return lstIndexCalc;
	}

	/**
	 * @param lstIndexCalc
	 *            the lstIndexCalc to set
	 */
	public void setLstIndexCalc(SearchResult<IfoIndexCalc> lstIndexCalc) {
		this.lstIndexCalc = lstIndexCalc;
	}

	/**
	 * @return the pm
	 */
	public int getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(int pm) {
		this.pm = pm;
	}

	/**
	 * @return the chartApi
	 */
	public ChartAPIModel getIndexChartApi() {
		return indexChartApi;
	}

	/**
	 *
	 */
	public void calcIndexChartApi() {
		if (lstIndexCalc != null && lstIndexCalc.size() > 0) {
			indexChartApi = (indexChartApi == null ? new ChartAPIModel() : indexChartApi);
			for (IfoIndexCalc idxCalc : lstIndexCalc) {
				indexChartApi.add(idxCalc);
			}
			indexChartApi.calcData();
		} else {
			indexChartApi.setDefaultValue();
		}
	}

}
