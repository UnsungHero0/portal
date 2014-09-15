package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.Map;

import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;

/**
 * @author tungnq.nguyen
 * @version 1.0
 * @created 10-Mar-2008 10:27:51 AM
 */
@SuppressWarnings("serial")
public class StockWizardBean implements Serializable, Cloneable {

	private StockExchangeIndexingBean stockExchangeIndexingBean = new StockExchangeIndexingBean();
	private IfoCompanyProfileViewId ifoCompanyProfileView = new IfoCompanyProfileViewId();
	private AnalysisIndexingBean analysisIndexingBean = new AnalysisIndexingBean();
	private Map<String, AnalysisCachingValueInfo> mapValues;

	public StockWizardBean() {
	}

	/**
	 * @return the ifoCompanyProfileView
	 */
	public IfoCompanyProfileViewId getIfoCompanyProfileView() {
		return ifoCompanyProfileView;
	}

	/**
	 * @param ifoCompanyProfileView
	 *            the ifoCompanyProfileView to set
	 */
	public void setIfoCompanyProfileView(IfoCompanyProfileViewId ifoCompanyProfileView) {
		this.ifoCompanyProfileView = ifoCompanyProfileView;
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

	public Map<String, AnalysisCachingValueInfo> getMapValues() {
		return mapValues;
	}

	public void setMapValues(Map<String, AnalysisCachingValueInfo> mapValues) {
		this.mapValues = mapValues;
	}

}