/**
 * 
 */
package vn.com.vndirect.domain.struts2.osc;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.osc.WpProduct;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("serial")
public class ProductCRUDAJAXModel extends BaseModel {

	private WpProduct product;
	private List<WpProduct> products;
	private String productGroupId;

	/*
	 * id in the format 1,2,3
	 */
	private String productIds;

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

	/**
	 * @return the product
	 */
	public WpProduct getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(WpProduct product) {
		this.product = product;
	}

	/**
	 * @return the productIds
	 */
	public String getProductIds() {
		return productIds;
	}

	/**
	 * @param productIds
	 *            the productIds to set
	 */
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}

}
