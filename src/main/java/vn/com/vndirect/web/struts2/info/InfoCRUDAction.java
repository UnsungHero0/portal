package vn.com.vndirect.web.struts2.info;

import org.apache.log4j.Logger;

import vn.com.vndirect.domain.struts2.info.InfoCRUDModel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class InfoCRUDAction extends ActionSupport implements ModelDriven<InfoCRUDModel> {
	private static Logger logger = Logger.getLogger(InfoCRUDAction.class);

	private InfoCRUDModel model = new InfoCRUDModel();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public InfoCRUDModel getModel() {
		return model;
	}

	public String viewVisionPage() {
		final String LOCATION = "viewVisionPage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewValuePage() {
		final String LOCATION = "viewValuePage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewTeamPage() {
		final String LOCATION = "viewTeamPage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewCareerPage() {
		final String LOCATION = "viewCareerPage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

}
