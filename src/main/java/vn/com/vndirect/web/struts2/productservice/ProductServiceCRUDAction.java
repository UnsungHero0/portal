package vn.com.vndirect.web.struts2.productservice;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.domain.struts2.productservice.ProductServiceCRUDModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ProductServiceCRUDAction extends ActionSupport implements ModelDriven<ProductServiceCRUDModel> {
	private static Logger logger = Logger.getLogger(ProductServiceCRUDAction.class);

	private ProductServiceCRUDModel model = new ProductServiceCRUDModel();

	private IOnlineServiceManager onlineServiceManager;

	/**
	 * @param onlineServiceManager
	 *            the onlineServiceManager to set
	 */
	public void setOnlineServiceManager(IOnlineServiceManager onlineServiceManager) {
		this.onlineServiceManager = onlineServiceManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public ProductServiceCRUDModel getModel() {
		return model;
	}

	public String viewTradingServicePage() {
		final String LOCATION = "viewTradingServicePage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewInfoServicePage() {
		final String LOCATION = "viewInfoServicePage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewSpecialProductPage() {
		final String LOCATION = "viewSpecialProductPage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewTradingGuidePage() {
		final String LOCATION = "viewTradingGuidePage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewContentDetail() {
		final String LOCATION = "viewContentDetail()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			WpSubject subject = new WpSubject();
			subject.setSubjectCode(model.getSubjectCode());
			if (StringUtils.isBlank(model.getSubjectCode()))
				subject.setSubjectId(model.getSubjectId());
			subject.setLanguage(I18NUtility.getCurrentLocale());

			model.setSubject(onlineServiceManager.getSubject(subject));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}
}
