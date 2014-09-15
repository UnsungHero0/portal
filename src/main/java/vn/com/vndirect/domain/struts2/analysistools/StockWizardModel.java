/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.embeddb.EDBItemCodeMapping;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.web.commons.utility.SpringUtils;

/**
 * @author Huy
 * 
 */
public class StockWizardModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5316072409114619609L;
	private String symbol;
	private AnalysisIndexingBean analysisIndexingBean;
	private IfoCompanyProfileViewId ifoCompanyProfileViewId;

	private String symbol1;
	private String symbol2;

	private IfoCompanyProfileViewId ifoCompanyProfileViewId1;
	private IfoCompanyProfileViewId ifoCompanyProfileViewId2;

	private AnalysisIndexingBean analysisIndexingBean1;
	private AnalysisIndexingBean analysisIndexingBean2;
	private int page = 1;
	private double f1000030;
	private double f1000031;
	private double f1000033;

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	 * @return the ifoCompanyProfileViewId
	 */
	public IfoCompanyProfileViewId getIfoCompanyProfileViewId() {
		return ifoCompanyProfileViewId;
	}

	/**
	 * @param ifoCompanyProfileViewId
	 *            the ifoCompanyProfileViewId to set
	 */
	public void setIfoCompanyProfileViewId(IfoCompanyProfileViewId ifoCompanyProfileViewId) {
		this.ifoCompanyProfileViewId = ifoCompanyProfileViewId;
	}

	/**
	 * @return the symbol1
	 */
	public String getSymbol1() {
		return symbol1;
	}

	/**
	 * @param symbol1
	 *            the symbol1 to set
	 */
	public void setSymbol1(String symbol1) {
		this.symbol1 = symbol1;
	}

	/**
	 * @return the symbol2
	 */
	public String getSymbol2() {
		return symbol2;
	}

	/**
	 * @param symbol2
	 *            the symbol2 to set
	 */
	public void setSymbol2(String symbol2) {
		this.symbol2 = symbol2;
	}

	/**
	 * @return the ifoCompanyProfileViewId1
	 */
	public IfoCompanyProfileViewId getIfoCompanyProfileViewId1() {
		return ifoCompanyProfileViewId1;
	}

	/**
	 * @param ifoCompanyProfileViewId1
	 *            the ifoCompanyProfileViewId1 to set
	 */
	public void setIfoCompanyProfileViewId1(IfoCompanyProfileViewId ifoCompanyProfileViewId1) {
		this.ifoCompanyProfileViewId1 = ifoCompanyProfileViewId1;
	}

	/**
	 * @return the ifoCompanyProfileViewId2
	 */
	public IfoCompanyProfileViewId getIfoCompanyProfileViewId2() {
		return ifoCompanyProfileViewId2;
	}

	/**
	 * @param ifoCompanyProfileViewId2
	 *            the ifoCompanyProfileViewId2 to set
	 */
	public void setIfoCompanyProfileViewId2(IfoCompanyProfileViewId ifoCompanyProfileViewId2) {
		this.ifoCompanyProfileViewId2 = ifoCompanyProfileViewId2;
	}

	/**
	 * @return the analysisIndexingBean1
	 */
	public AnalysisIndexingBean getAnalysisIndexingBean1() {
		return analysisIndexingBean1;
	}

	/**
	 * @param analysisIndexingBean1
	 *            the analysisIndexingBean1 to set
	 */
	public void setAnalysisIndexingBean1(AnalysisIndexingBean analysisIndexingBean1) {
		this.analysisIndexingBean1 = analysisIndexingBean1;
	}

	/**
	 * @return the analysisIndexingBean2
	 */
	public AnalysisIndexingBean getAnalysisIndexingBean2() {
		return analysisIndexingBean2;
	}

	/**
	 * @param analysisIndexingBean2
	 *            the analysisIndexingBean2 to set
	 */
	public void setAnalysisIndexingBean2(AnalysisIndexingBean analysisIndexingBean2) {
		this.analysisIndexingBean2 = analysisIndexingBean2;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStrFormatDisplay() {
		try {
			EDBItemCodeMapping itemCodeMapping = (EDBItemCodeMapping) SpringUtils.getBean("EDBItemCodeMapping");
			String itemCode = itemCodeMapping.getItemCode("sma_50");
			double sma50 = analysisIndexingBean.getValue(itemCode).getNumericValue();

			itemCode = itemCodeMapping.getItemCode("sma_200");
			double sma200 = analysisIndexingBean.getValue(itemCode).getNumericValue();

			double closePrice = Double.parseDouble(analysisIndexingBean.getIntraday());

			itemCode = itemCodeMapping.getItemCode("52_week_high");
			double _52_week_high = analysisIndexingBean.getValue(itemCode).getNumericValue();

			itemCode = itemCodeMapping.getItemCode("52_week_low");
			double _52_week_low = analysisIndexingBean.getValue(itemCode).getNumericValue();

			if (sma50 > sma200) {
				if (_52_week_high > closePrice && closePrice > sma50) {
					return 1;
				} else if (_52_week_low < closePrice && closePrice < sma200) {
					return 2;
				} else if (sma200 <= closePrice && closePrice <= sma50) {
					return 3;
				} else if (closePrice == _52_week_high) {
					return 4;
				} else if (closePrice == _52_week_low) {
					return 5;
				}
			} else if (sma50 < sma200) {
				if (_52_week_high > closePrice && closePrice > sma200) {
					return 6;
				} else if (_52_week_low < closePrice && closePrice < sma50) {
					return 7;
				} else if (sma50 <= closePrice && closePrice <= sma200) {
					return 8;
				} else if (closePrice == _52_week_high) {
					return 9;
				} else if (closePrice == _52_week_low) {
					return 10;
				}
			} else {
				return 11;
			}
		} catch (Exception e) {
		}
		return 0;
	}

	public double getF1000030() {
		return f1000030;
	}

	public void setF1000030(double f1000030) {
		this.f1000030 = f1000030;
	}

	public double getF1000031() {
		return f1000031;
	}

	public void setF1000031(double f1000031) {
		this.f1000031 = f1000031;
	}

	public double getF1000033() {
		return f1000033;
	}

	public void setF1000033(double f1000033) {
		this.f1000033 = f1000033;
	}
}
