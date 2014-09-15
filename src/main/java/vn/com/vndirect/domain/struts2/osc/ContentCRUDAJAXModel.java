/**
 * 
 */
package vn.com.vndirect.domain.struts2.osc;

import java.util.List;
import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.osc.WpProduct;
import vn.com.vndirect.wsclient.osc.WpProductGroup;
import vn.com.vndirect.wsclient.osc.WpSubject;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("serial")
public class ContentCRUDAJAXModel extends BaseModel {

	// IN
	private WpSubject wpSubject;
	private WpProductGroup wpProductGroup;
	private String subjectIds;
	private String productGroupId;

	private WpProduct product = new WpProduct();
	// OUT
	List<WpSubject> wpsubjectList;
	private List<WpProductGroup> wpproductgroupList;
	private List<WpProduct> wpproductList;
	private List<WpProduct> products;

	/**
	 * @return the wpSubject
	 */
	public WpSubject getWpSubject() {
		return wpSubject;
	}

	/**
	 * @param wpSubject
	 *            the wpSubject to set
	 */
	public void setWpSubject(WpSubject wpSubject) {
		this.wpSubject = wpSubject;
	}

	/**
	 * @return the wpsubjectList
	 */
	public List<WpSubject> getWpsubjectList() {
		return wpsubjectList;
	}

	/**
	 * @param wpsubjectList
	 *            the wpsubjectList to set
	 */
	public void setWpsubjectList(List<WpSubject> wpsubjectList) {
		this.wpsubjectList = wpsubjectList;
	}

	/**
	 * @return the wpproductgroupList
	 */
	public List<WpProductGroup> getWpproductgroupList() {
		return wpproductgroupList;
	}

	public WpProduct getProduct() {
		return product;
	}

	public void setProduct(WpProduct product) {
		this.product = product;
	}

	/**
	 * @param wpproductgroupList
	 *            the wpproductgroupList to set
	 */
	public void setWpproductgroupList(List<WpProductGroup> wpproductgroupList) {
		this.wpproductgroupList = wpproductgroupList;
	}

	/**
	 * @return the wpproductList
	 */
	public List<WpProduct> getWpproductList() {
		return wpproductList;
	}

	/**
	 * @param wpproductList
	 *            the wpproductList to set
	 */
	public void setWpproductList(List<WpProduct> wpproductList) {
		this.wpproductList = wpproductList;
	}

	/**
	 * @return the wpProductGroup
	 */
	public WpProductGroup getWpProductGroup() {
		return wpProductGroup;
	}

	/**
	 * @param wpProductGroup
	 *            the wpProductGroup to set
	 */
	public void setWpProductGroup(WpProductGroup wpProductGroup) {
		this.wpProductGroup = wpProductGroup;
	}

	/**
	 * @return the subjectIds
	 */
	public String getSubjectIds() {
		return subjectIds;
	}

	/**
	 * @param subjectIds
	 *            the subjectIds to set
	 */
	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	/**
	 * @return the productGroupId
	 */
	public String getProductGroupId() {
		return productGroupId;
	}

	/**
	 * @param productGroupId
	 *            the productGroupId to set
	 */
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**
	 * @return the products
	 */
	public List<WpProduct> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<WpProduct> products) {
		this.products = products;
	}
}
