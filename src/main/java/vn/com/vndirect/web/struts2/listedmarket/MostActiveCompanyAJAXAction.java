package vn.com.vndirect.web.struts2.listedmarket;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.extend.CompanySummary;
import vn.com.vndirect.domain.struts2.listedmarket.MostActiveCompanyAJAXModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 19, 2010 3:35:04 PM
 */
@SuppressWarnings("serial")
public class MostActiveCompanyAJAXAction extends ActionSupport implements ModelDriven<MostActiveCompanyAJAXModel> {
	private static Logger logger = Logger.getLogger(MostActiveCompanyAJAXAction.class);
	private MostActiveCompanyAJAXModel model = new MostActiveCompanyAJAXModel();
	
	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @return the model
	 */
	public MostActiveCompanyAJAXModel getModel() {
		return model;
	}

	/**
	 * @param quotesManager
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public String executeGetMostActiveCompany() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetMarketOverview()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			Map<String, List<CompanySummary>> mapMarketSummary = quotesManager.getMostActiveCompany(model.getMarketOption());

			List<CompanySummary> listCompanySummary;

			// get HASTC Company Summary
			listCompanySummary = mapMarketSummary.get(Constants.MarketSummary.LIST_HASTC_COMPANY_SUMMARY);
			model.setListHastcSummary(listCompanySummary);

			// get HOSTC Company Summary
			listCompanySummary = mapMarketSummary.get(Constants.MarketSummary.LIST_HOSTC_COMPANY_SUMMARY);
			model.setListHostcSummary(listCompanySummary);

			// get UPCOM Company Summary
			listCompanySummary = mapMarketSummary.get(Constants.MarketSummary.LIST_UPCOM_COMPANY_SUMMARY);
			model.setListUpComSummary(listCompanySummary);
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
