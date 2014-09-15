/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.FinfoDBManager;
import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.domain.struts2.analysistools.StockScreenerModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Huy
 * 
 */
public class StockScreenerAJAXAction extends ActionSupport implements ModelDriven<StockScreenerModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7630383361881450247L;
	private Logger logger = Logger.getLogger(getClass());
	private StockScreenerModel model = new StockScreenerModel();

	// +++++ ============ Define Spring Mapping ===============
	@Autowired
	private FinfoDBManager finfoDBManager;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;

	/**
	 * @param analysisToolsManager
	 *            the analysisToolsManager to set
	 */
	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	/**
	 * 
	 * @param finfoDBManager
	 */
	public void setFinfoDBManager(FinfoDBManager finfoDBManager) {
		this.finfoDBManager = finfoDBManager;
	}

	@Override
	public String execute() throws Exception {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setSearchStockScreenerBean(finfoDBManager.calcStockScreener(model.getSearchStockScreenerBean()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Get list of industries code
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getListOfIndustries() throws Exception {
		final String LOCATION = "process()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setIfoIndustryCalcViews(analysisToolsManager.getListIndustryName(model.getIfoIndustryCalcView()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public StockScreenerModel getModel() {
		return model;
	}
}
