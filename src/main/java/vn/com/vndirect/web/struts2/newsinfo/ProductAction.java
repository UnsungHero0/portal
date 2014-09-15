package vn.com.vndirect.web.struts2.newsinfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.struts2.osc.ProductCRUDModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Feb 9, 2010 10:52:30 AM
 */
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<ProductCRUDModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(ProductAction.class);

	/* data model */
	private ProductCRUDModel model = new ProductCRUDModel();

	@Autowired
	private IOnlineServiceManager onlineServiceManager;

	/**
	 * @return the model
	 */
	public ProductCRUDModel getModel() {
		return model;
	}

	/**
	 * @param onlineServiceManager
	 *            the onlineServiceManager to set
	 */
	public void setOnlineServiceManager(IOnlineServiceManager onlineServiceManager) {
		this.onlineServiceManager = onlineServiceManager;
	}

	// public String executeGetProductsByProductGroupId() {
	// final String LOCATION = "getProductsByGroupId()";
	// if (logger.isDebugEnabled()) {
	// logger.debug(LOCATION + ":: BEGIN");
	// }
	//		
	// if (model.getProductGroupId() != null) {
	// try {
	// // set PagingInfo
	// // model.setProducts(onlineServiceManager.getProductsByProductGroupId(model.getProductGroupId(), model.getPagingInfo()));
	// } catch (Exception e) {
	// this.addActionError(getText("web.error.OSCAction.getProductsByProductGroupId.exception"));
	// logger.error(e.getMessage());
	// }
	// }
	//		
	// if (logger.isDebugEnabled()) {
	// logger.debug(LOCATION + ":: END");
	// }
	// return SUCCESS;
	// }
	//	
	public String executeGetProductDetail() {
		final String LOCATION = "executeGetProductDetail()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		WpProduct wpProduct = model.getWpProduct();
		try {
			if (wpProduct != null && StringUtils.isNotBlank(wpProduct.getProductCode())) {
				// +++ get product detail
				wpProduct.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				WpProduct productResult = onlineServiceManager.getProductByCode(wpProduct);

				model.setWpProduct(productResult);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String executeGetDocumentProduct() {
		final String LOCATION = "executeGetDocumentProduct()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			WpDocument wpDocument = model.getWpDocument();
			wpDocument.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			model.setWpDocumentList(onlineServiceManager.searchDocumentProduct(wpDocument, null));

		} catch (Exception e) {
			this.addActionError(getText("web.error.OSCAction.getProductDetails.exception"));
			logger.error(e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

}
