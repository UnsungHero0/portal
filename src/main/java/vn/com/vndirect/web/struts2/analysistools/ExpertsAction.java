/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.rmi.RemoteException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.struts2.analysistools.ExpertsModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Apr 12, 2010 11:26:27 AM
 * 
 */
@SuppressWarnings("serial")
public class ExpertsAction extends ActionSupport implements ModelDriven<ExpertsModel> {
	private static Logger logger = Logger.getLogger(ExpertsAction.class);
	private ExpertsModel model = new ExpertsModel();

	@Autowired
	private IOnlineServiceManager onlineServiceManager;

	/**
	 * @return the model
	 */
	public ExpertsModel getModel() {
		return model;
	}

	/**
	 * show experts page
	 * 
	 * @return
	 * @throws Exception
	 * @throws RemoteException
	 */
	public String showInitPage() throws RemoteException, Exception {
		final String LOCATION = "showInitPage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * get experts identified
	 * 
	 * @return
	 * @throws Exception
	 * @throws RemoteException
	 */
	public String executeViewExpextContent() throws RemoteException, Exception {
		final String LOCATION = "executeViewExpextContent()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			// set value for subject
			WpSubject subject = new WpSubject();
			WpProduct product = new WpProduct();
			product.setProductCode(model.getProductCode());
			subject.setWpProduct(product);
			subject.setLanguage(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			SearchResult<WpSubject> searchResult = onlineServiceManager.getSubjects(subject, model.getPagingInfo());
			model.setPagingInfo((PagingInfo) searchResult.getPaging());
			model.setSubjectList(searchResult);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * get information about subject
	 * 
	 * @return
	 * @throws Exception
	 * @throws RemoteException
	 */
	public String executeViewExpertDetail() throws FunctionalException, Exception {
		final String LOCATION = "executeViewExpertDetail()";
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

	public void setOnlineServiceManager(IOnlineServiceManager onlineServiceManager) {
		this.onlineServiceManager = onlineServiceManager;
	}
}
