/**
 * 
 */
package vn.com.vndirect.web.struts2.listedmarket;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.domain.Vote;
import vn.com.vndirect.domain.struts2.listedmarket.VoteAJAXModel;
import vn.com.web.commons.captcha.CaptchaEngineProxy;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;
import vn.com.web.commons.utility.Validation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 25, 2010 11:38:12 AM
 * 
 */
@SuppressWarnings("serial")
public class VoteAJAXAction extends ActionSupport implements ModelDriven<VoteAJAXModel> {
	private static Logger logger = Logger.getLogger(VoteAJAXAction.class);
	private VoteAJAXModel model = new VoteAJAXModel();
	
	@Autowired
	private ICommonManager commonManager;

	/**
	 * @return the model
	 */
	public VoteAJAXModel getModel() {
		return model;
	}

	/**
	 * @param commonManager
	 *            the commonManager to set
	 */
	public void setCommonManager(ICommonManager commonManager) {
		this.commonManager = commonManager;
	}

	/**
	 * Get question for vote
	 * 
	 * @return
	 * @throws SystemException
	 */
	public String executeGetVote() throws SystemException {
		final String LOCATION = "executeGetVote()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			model.setSearchResult(commonManager.getVoteQuestion());

			// total vote
			long totalVote = 0;
			for (Vote vote : model.getSearchResult()) {
				totalVote += vote.getTotal();
			}

			// calculate rateVote
			for (Vote vote : model.getSearchResult()) {
				vote.setRateVote(totalVote == 0 ? (double) 0 : (double) vote.getTotal() * 100 / totalVote);
			}
			model.setTotalVote(totalVote);
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
	 * insert vote of user to database
	 * 
	 * @return
	 * @throws SystemException
	 */
	public String executeSubmitVote() throws SystemException {
		final String LOCATION = "executeGetVote()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		/*
		 * check valid captchar if OK , submit vote and get result else show error message
		 */
		Map<String, Object> session = ActionContext.getContext().getSession();
		String imageCaptChar = (String) session.get(CaptchaEngineProxy.PARAM_CAPTCHA_SCODE);
		if (!Validation.isEmpty(model.getCaptchar()) && model.getCaptchar().equalsIgnoreCase(imageCaptChar)) {
			commonManager.submitVote(model.getVote().getVoteId());
			model.setSearchResult(commonManager.getVoteQuestion());

			// total vote
			long totalVote = 0;
			for (Vote vote : model.getSearchResult()) {
				totalVote += vote.getTotal();
			}

			// calculate rateVote
			for (Vote vote : model.getSearchResult()) {
				vote.setRateVote(totalVote == 0 ? (double) 0 : (double) vote.getTotal() * 100 / totalVote);
			}
			model.setTotalVote(totalVote);
			model.setMessage("");
		} else {
			model.setMessage(getText("leftmenu.message.listedmarket.vote.captchar.wrong"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

}
