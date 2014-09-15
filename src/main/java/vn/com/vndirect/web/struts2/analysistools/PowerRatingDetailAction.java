package vn.com.vndirect.web.struts2.analysistools;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.domain.struts2.analysistools.PowerRatingModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class PowerRatingDetailAction extends ActionSupport implements ModelDriven<PowerRatingModel> {
	private Logger logger = Logger.getLogger(getClass());
	private PowerRatingModel model = new PowerRatingModel();
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;

	/**
	 * @param analysisToolsManager
	 *            the analysisToolsManager to set
	 */
	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	public PowerRatingModel getModel() {
		return model;
	}

	public String execute() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setListOfPrs(analysisToolsManager.getListOfPrs());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}
}
