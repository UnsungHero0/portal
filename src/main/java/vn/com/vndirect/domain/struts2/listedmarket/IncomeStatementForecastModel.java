package vn.com.vndirect.domain.struts2.listedmarket;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoEstimateView;

@SuppressWarnings( { "unchecked", "serial" })
public class IncomeStatementForecastModel extends BaseModel {
	/**
	 * 
	 */
	private IfoEstimateView ifoEstimateView = new IfoEstimateView();
	private String symbol;
	private List listYear = new ArrayList();
	private String searchDate;
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getFiscalDate() {
		return ifoEstimateView.getFiscalDate();
	}

	public String getFiscalDateStr() {
		try {
			return VNDirectDateUtils.dateToString(ifoEstimateView.getFiscalDate(), VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
		} catch (Exception e) {
			return "";
		}
	}

	public int getFiscalYear() {
		Calendar cal = Calendar.getInstance();
		if (ifoEstimateView.getFiscalDate() != null) {
			cal.setTimeInMillis(ifoEstimateView.getFiscalDate().getTime());
		} else {
			cal.setTimeInMillis(System.currentTimeMillis());
		}
		return cal.get(Calendar.YEAR);
	}

	public IfoEstimateView getIfoEstimateView() {
		return ifoEstimateView;
	}

	public void setIfoEstimateView(IfoEstimateView ifoEstimateView) {
		this.ifoEstimateView = ifoEstimateView;
	}

	public void setYearList(List listYear) {
		this.listYear = listYear;

	}

	public List getListYear() {
		return listYear;
	}

	public void setListYear(List listYear) {
		this.listYear = listYear;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

}
