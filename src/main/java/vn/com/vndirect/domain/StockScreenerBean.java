package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.Comparator;

import vn.com.vndirect.commons.utility.AnalysisIndexOptions;
import vn.com.vndirect.embeddb.EDBConstants;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;

/**
 * @author tungnq.nguyen
 * @version 1.0
 * @created 10-Mar-2008 2:21:40 PM
 */

@SuppressWarnings("unchecked")
public class StockScreenerBean implements Serializable, Cloneable, Comparator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3381926504375283143L;
	public static int ASC = 1;
	public static int DESC = 0;
	private StockExchangeIndexingBean stockExchangeIndexingBean;
	private AnalysisIndexingBean analysisIndexingBean;
	private int sortOrder;
	private String sortField;
	private String local;
	private String industryName;

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public StockScreenerBean() {
	}

	public StockScreenerBean(AnalysisIndexingBean analysisIndexingBean) {
		this.analysisIndexingBean = analysisIndexingBean;
	}

	/**
	 * 
	 * @return
	 */
	public String getSymbol() {
		if (stockExchangeIndexingBean != null) {
			return stockExchangeIndexingBean.getSymbol();
		} else if (analysisIndexingBean != null) {
			return analysisIndexingBean.getSymbol();
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public long getCompanyId() {
		if (stockExchangeIndexingBean != null) {
			return stockExchangeIndexingBean.getCompanyId();
		} else if (analysisIndexingBean != null) {
			return analysisIndexingBean.getCompanyId();
		}
		return 0;
	}

	/* Keep intraday price of Symbol */
	private SecInfo secInfo;

	/**
	 * @return the stockExchangeIndexingBean
	 */
	public StockExchangeIndexingBean getStockExchangeIndexingBean() {
		return stockExchangeIndexingBean;
	}

	/**
	 * @param stockExchangeIndexingBean
	 *            the stockExchangeIndexingBean to set
	 */
	public void setStockExchangeIndexingBean(StockExchangeIndexingBean stockExchangeIndexingBean) {
		this.stockExchangeIndexingBean = stockExchangeIndexingBean;
	}

	/**
	 * @return the analysisIndexingBean
	 */
	public AnalysisIndexingBean getAnalysisIndexingBean() {
		return analysisIndexingBean;
	}

	/**
	 * @param analysisIndexingBean
	 *            the analysisIndexingBean to set
	 */
	public void setAnalysisIndexingBean(AnalysisIndexingBean analysisIndexingBean) {
		this.analysisIndexingBean = analysisIndexingBean;
	}

	/**
	 * @return the secInfo
	 */
	public SecInfo getSecInfo() {
		return secInfo;
	}

	/**
	 * @param secInfo
	 *            the secInfo to set
	 */
	public void setSecInfo(SecInfo secInfo) {
		this.secInfo = secInfo;
	}

	public Object getBySortKey() {
		try {
			if ("COMPANY".equals(sortField)) {
				if ("vn".equals(local)) {
					return stockExchangeIndexingBean.getIfoCompanyNameView().getId().getCompanyName();
				} else {
					return stockExchangeIndexingBean.getIfoCompanyNameView().getId().getCompanyFullName();
				}
			} else if ("INDUSTRY".equals(sortField)) {
				return this.industryName;
			} else if ("CLOSE_PRICE".equals(sortField)) {
				return secInfo == null ? Double.valueOf(1.0) : secInfo.getClosePrice();
			} else if (EDBConstants.MappingItems._52_WEEK_HIGH.fieldName.equals(sortField)) {
				if (analysisIndexingBean.getValue(EDBConstants.MappingItems._52_WEEK_HIGH.fieldName) == null || secInfo.getClosePrice() == 0) {
					return Double.valueOf(1.0);
				} else {
					Double _52_week_high = analysisIndexingBean.getValue(EDBConstants.MappingItems._52_WEEK_HIGH.fieldName).getNumericValue();
					return (secInfo.getClosePrice() - _52_week_high) / _52_week_high;
				}
			} else if (EDBConstants.MappingItems._52_WEEK_LOW.fieldName.equals(sortField)) {
				if (analysisIndexingBean.getValue(EDBConstants.MappingItems._52_WEEK_LOW.fieldName) == null || secInfo.getClosePrice() == 0) {
					return Double.valueOf(1.0);
				} else {
					Double _52_week_low = analysisIndexingBean.getValue(EDBConstants.MappingItems._52_WEEK_LOW.fieldName).getNumericValue();
					return (secInfo.getClosePrice() - _52_week_low) / _52_week_low;
				}
			} else {
				return analysisIndexingBean.getValue(sortField) == null ? Double.valueOf(1.0) : analysisIndexingBean.getValue(sortField).getNumericValue();
			}
		} catch (Exception e) {
			return Double.valueOf(1.0);
		}
	}

	public int compare(Object arg0, Object arg1) {
		if (arg1 == null)
			return this.sortOrder == ASC ? 1 : -1;
		;
		StockScreenerBean obj1 = (StockScreenerBean) arg0;
		StockScreenerBean obj2 = (StockScreenerBean) arg1;
		return compareByItemCode(obj1, obj2);
	}

	/**
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private int compareByItemCode(StockScreenerBean obj1, StockScreenerBean obj2) {
		Object valueObj1 = obj1.getBySortKey();
		Object valueObj2 = obj2.getBySortKey();
		if (valueObj1 instanceof Double) {
			if (((Double) valueObj1).equals((Double) valueObj2)) {
				return 0;
			} else if ((Double) valueObj1 > (Double) valueObj2) {
				return this.sortOrder == ASC ? 1 : -1;
			} else {
				return this.sortOrder == ASC ? -1 : 1;
			}
		} else {
			return this.sortOrder == ASC ? ((String) valueObj1).compareTo((String) valueObj2) : -1 * ((String) valueObj1).compareTo((String) valueObj2);
		}
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getFiveDaysChg() {
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "5_days_chg", true, true).getFormattedData();
	}

	public String getPriceChgFourWeeks() {
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "price_chg_4_weeks", true, true).getFormattedData();
	}

	public String getFiftyTwoWeekChg() {
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "52_week_chg", true, true).getFormattedData();
	}

	public String getMarketCap() {
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "market_cap", false, false, "###,##0.##").getFormattedData();
	}

	public String getStockPriceVsIndex4Weeks() {
		// showNumber="true" itemName="stock_price_vs_index_4_weeks" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "stock_price_vs_index_4_weeks", true, true).getFormattedData();
	}

	public String getStockPriceVsIndex13Weeks() {
		// showNumber="true" itemName="stock_price_vs_index_13_weeks" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "stock_price_vs_index_13_weeks", true, true).getFormattedData();
	}

	public String getStockPriceVsIndex52Weeks() {
		// showNumber="true" itemName="stock_price_vs_index_52_weeks" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "stock_price_vs_index_52_weeks", true, true).getFormattedData();
	}

	public String getBeta() {
		// showNumber="true" itemName="beta" showArrow="false"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "beta", false, false).getFormattedData();
	}

	public String getPeRatio() {
		// showNumber ="true" itemName="pe_ratio"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "pe_ratio", false, false).getFormattedData();
	}

	public String getPriceSaleRatio() {
		// showNumber ="true" itemName="price_sale_ratio"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "price_sale_ratio", false, false).getFormattedData();
	}

	public String getPriceBookRatio() {
		// showNumber ="true" itemName="price_book_ratio"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "price_book_ratio", false, false).getFormattedData();
	}

	public String getPegRatio() {
		// showNumber ="true" itemName="peg_ratio"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "peg_ratio", false, false).getFormattedData();
	}

	public String getProfitMargin() {
		// showNumber ="true" itemName="profit_margin" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "profit_margin", false, true).getFormattedData();
	}

	public String getRoe() {
		// showNumber ="true" itemName="roe" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "roe", false, true).getFormattedData();
	}

	public String getRoa() {
		// showNumber ="true" itemName="roa" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "roa", false, true).getFormattedData();
	}

	public String getEpsGrowthManual() {
		// showNumber ="true" itemName="eps_growth_manual" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "eps_growth_manual", false, true).getFormattedData();
	}

	public String getRevernueGrowthManual() {
		// showNumber ="true" itemName="revernue_growth_manual" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "revernue_growth_manual", false, true).getFormattedData();
	}

	public String getEpsGrowthQuarterly() {
		// showNumber ="true" itemName="eps_growth_quarterly" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "eps_growth_quarterly", false, true).getFormattedData();
	}

	public String getRevernueGrowthQuarterly() {
		// showNumber ="true" itemName="revernue_growth_quarterly" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "revernue_growth_quarterly", false, true).getFormattedData();
	}

	public String getDividentYeald() {
		// showNumber ="true" itemName="divident_yeald" showPercent="false"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "divident_yeald", false, false).getFormattedData();
	}

	public String getDividentGrowth5Years() {
		// showNumber ="true" itemName="divident_growth_5_years" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "divident_growth_5_years", false, true).getFormattedData();
	}

	public String getVsSma13Day() {
		// showNumber="true" itemName="vs_sma_13_day" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "vs_sma_13_day", true, true).getFormattedData();
	}

	public String getVsSma50Day() {
		// showNumber="true" itemName="vs_sma_50_day" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), true, "vs_sma_50_day", true, true).getFormattedData();
	}

	public String getPctBelow52WkHigh() {
		// getPctBelow52WkHigh="true" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), false, null, true, true, null, true, false).getFormattedData();
	}

	public String getPctBelow52WkLow() {
		// getPctBelow52WkLow="true" showArrow="true" showPercent="true"
		return new AnalysisIndexOptions(getAnalysisIndexingBean(), false, null, true, true, null, false, true).getFormattedData();
	}

}