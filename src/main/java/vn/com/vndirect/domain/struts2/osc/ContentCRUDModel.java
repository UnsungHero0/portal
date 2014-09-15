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
public class ContentCRUDModel extends BaseModel {
	private List<WpProductGroup> groups;
	private List<WpProduct> products;
	private WpSubject wpSubject;
	private WpProduct product = new WpProduct();

	/**
	 * The subjectId for update
	 */
	private Long subjectId = new Long(0);

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
	 * Retrieving the Subject Id
	 * 
	 * @return
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * Setting subjectId
	 * 
	 * @param subjectId
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public List<WpProductGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<WpProductGroup> groups) {
		this.groups = groups;
	}

	public List<WpProduct> getProducts() {
		return products;
	}

	public void setProducts(List<WpProduct> products) {
		this.products = products;
	}

	public WpProduct getProduct() {
		return product;
	}

	public void setProduct(WpProduct product) {
		this.product = product;
	}

}
