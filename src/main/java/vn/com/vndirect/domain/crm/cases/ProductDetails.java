package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ProductDetails", propOrder = { "productCode", "productName" })
public class ProductDetails {
	private String productCode;
	private String productName;

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

}
