/**
 * 
 */
package vn.com.vndirect.domain.struts2.osc;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("serial")
public class ProductCRUDModel extends BaseModel {

	private WpProduct wpProduct;
	private WpDocument wpDocument;
	private List<WpDocument> wpDocumentList;

	/**
	 * @return the wpProduct
	 */
	public WpProduct getWpProduct() {
		return wpProduct;
	}

	/**
	 * @param wpProduct
	 *            the wpProduct to set
	 */
	public void setWpProduct(WpProduct wpProduct) {
		this.wpProduct = wpProduct;
	}

	/**
	 * @return the wpDocument
	 */
	public WpDocument getWpDocument() {
		return wpDocument;
	}

	/**
	 * @param wpDocument
	 *            the wpDocument to set
	 */
	public void setWpDocument(WpDocument wpDocument) {
		this.wpDocument = wpDocument;
	}

	/**
	 * @return the wpDocumentList
	 */
	public List<WpDocument> getWpDocumentList() {
		return wpDocumentList;
	}

	/**
	 * @param wpDocumentList
	 *            the wpDocumentList to set
	 */
	public void setWpDocumentList(List<WpDocument> wpDocumentList) {
		this.wpDocumentList = wpDocumentList;
	}

}
