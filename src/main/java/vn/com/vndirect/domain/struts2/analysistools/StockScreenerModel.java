/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.StockScreenerBean;
import vn.com.vndirect.wsclient.tradingideas.SaveSearch;

/**
 * @author Huy
 * 
 */
public class StockScreenerModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5883091455415190760L;
	private SearchStockScreenerBean searchStockScreenerBean;// = new SearchStockScreenerBean();
	private IfoIndustryCalcView ifoIndustryCalcView;
	private List<IfoIndustryCalcView> ifoIndustryCalcViews;
	private List<IfoSectorCalcView> listSectorsName;
	private List<StockScreenerBean> listStockScreener;
	// A stock screener name
	private String name;
	private List<SaveSearch> criteria;

	// remove it
	private String status;
	private long saveSearchId;
	private String alert;
	private String sortOrder;
	private String sortField;

	/**
	 * @return the ifoIndustryCalcViews
	 */
	public List<IfoIndustryCalcView> getIfoIndustryCalcViews() {
		return ifoIndustryCalcViews;
	}

	/**
	 * @param ifoIndustryCalcViews
	 *            the ifoIndustryCalcViews to set
	 */
	public void setIfoIndustryCalcViews(List<IfoIndustryCalcView> ifoIndustryCalcViews) {
		this.ifoIndustryCalcViews = ifoIndustryCalcViews;
	}

	/**
	 * @return the ifoIndustryCalcView
	 */
	public IfoIndustryCalcView getIfoIndustryCalcView() {
		return ifoIndustryCalcView;
	}

	/**
	 * @param ifoIndustryCalcView
	 *            the ifoIndustryCalcView to set
	 */
	public void setIfoIndustryCalcView(IfoIndustryCalcView ifoIndustryCalcView) {
		this.ifoIndustryCalcView = ifoIndustryCalcView;
	}

	/**
	 * @return the searchStockScreenerBean
	 */
	public SearchStockScreenerBean getSearchStockScreenerBean() {
		return searchStockScreenerBean;
	}

	/**
	 * @param searchStockScreenerBean
	 *            the searchStockScreenerBean to set
	 */
	public void setSearchStockScreenerBean(SearchStockScreenerBean searchStockScreenerBean) {
		this.searchStockScreenerBean = searchStockScreenerBean;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the listSectorsName
	 */
	public List<IfoSectorCalcView> getListSectorsName() {
		return listSectorsName;
	}

	/**
	 * @param listSectorsName
	 *            the listSectorsName to set
	 */
	public void setListSectorsName(List<IfoSectorCalcView> listSectorsName) {
		this.listSectorsName = listSectorsName;
	}

	/**
	 * @return the saveSearchId
	 */
	public long getSaveSearchId() {
		return saveSearchId;
	}

	/**
	 * @param saveSearchId
	 *            the saveSearchId to set
	 */
	public void setSaveSearchId(long saveSearchId) {
		this.saveSearchId = saveSearchId;
	}

	/**
	 * @return the alert
	 */
	public String getAlert() {
		return alert;
	}

	/**
	 * @param alert
	 *            the alert to set
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 * @return the listStockScreener
	 */
	public List<StockScreenerBean> getListStockScreener() {
		return listStockScreener;
	}

	/**
	 * @param listStockScreener
	 *            the listStockScreener to set
	 */
	public void setListStockScreener(List<StockScreenerBean> listStockScreener) {
		this.listStockScreener = listStockScreener;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the criteria
	 */
	public List<SaveSearch> getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(List<SaveSearch> criteria) {
		this.criteria = criteria;
	}
}
