package vn.com.vndirect.domain.struts2.home.history;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;

@SuppressWarnings("serial")
public class HistoryModel extends BaseModel {
	private String productCode;
	private String subjectCode;
	private WpProduct wpProduct;
	private WpSubject wpSubject;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public WpProduct getWpProduct() {
		return wpProduct;
	}

	public void setWpProduct(WpProduct wpProduct) {
		this.wpProduct = wpProduct;
	}

	public WpSubject getWpSubject() {
		return wpSubject;
	}

	public void setWpSubject(WpSubject wpSubject) {
		this.wpSubject = wpSubject;
	}
}
