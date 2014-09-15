package vn.com.vndirect.web.struts2.listedmarket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.domain.struts2.listedmarket.TradingStatisticModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class TradingStatisticAction extends ActionSupport implements ModelDriven<TradingStatisticModel> {
	/* class logger */
	private static Logger logger = Logger.getLogger(TradingStatisticAction.class);

	/* data model */
	private TradingStatisticModel model = new TradingStatisticModel();

	public TradingStatisticModel getModel() {
		return model;
	}

	@Autowired
	private IQuotesManager quotesManager;

	public IQuotesManager getQuotesManager() {
		return quotesManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * Handling when user click on left menu.
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeNavigate() throws FunctionalException, SystemException {
		final String LOCATION = "executeNavigate";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	/**
	 * The user wants to search Trading statistic
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeSearch() throws FunctionalException, SystemException {
		final String LOCATION = "executeSearch";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	/**
	 * The user wants the download trading statistic info.
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeDownload() throws FunctionalException, SystemException {
		final String LOCATION = "executeDownload";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}
}