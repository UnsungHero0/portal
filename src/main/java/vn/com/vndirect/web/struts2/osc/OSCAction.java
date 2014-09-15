package vn.com.vndirect.web.struts2.osc;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IOnlineServiceManager;
import vn.com.vndirect.domain.struts2.osc.OSCModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author NguyenDucQuang
 * @version 1.0
 * @created Feb 5, 2010 2:46:40 PM
 * 
 */
@SuppressWarnings("serial")
public class OSCAction extends ActionSupport implements ModelDriven<OSCModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(OSCAction.class);

	/* data model */
	private OSCModel model = new OSCModel();

	@Autowired
	private IOnlineServiceManager onlineServiceManager;

	/**
	 * @param onlineServiceManager
	 *            the onlineServiceManager to set
	 */
	public void setOnlineServiceManager(IOnlineServiceManager onlineServiceManager) {
		this.onlineServiceManager = onlineServiceManager;
	}

	/**
	 * @return the model
	 */
	public OSCModel getModel() {
		return model;
	}

	/**
	 * view online service center screen
	 * 
	 * @return
	 */
	public String viewOSCAfterLogin() {
		final String LOCATION = "viewOSCAfterLogin()";

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}

		return SUCCESS;
	}

	public String viewFreeAfterLogin() throws Exception {
		final String LOCATION = "viewFreeAfterLogin";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("user.inforProfile"));
		breadcrumbs.add(this.getText("user.myPage"));
		breadcrumbs.add("#");
		breadcrumbs.add(this.getText("user.inforManage"));
		breadcrumbs.add("/thong-tin-co-ban-tai-khoan-mien-phi.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// seopage
		model.setPageTitle(this.getText("user.inforProfileFree.title"));
		model.setPageDescription(this.getText("user.inforProfileFree.desc"));
		model.setPageKeywords(this.getText("user.inforProfileFree.keywords"));

		return SUCCESS;
	}

	/**
	 * get data form database by subjectId and display on screen
	 * 
	 * @return
	 */
	public String viewContentDetail() {
		final String LOCATION = "viewContentDetail()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		if (model.getSubjectId() != null && model.getSubjectId().longValue() > 0) {
			try {
				// model.setSubject(onlineServiceManager.getSubjectById(model.getSubjectId()));
			} catch (Exception e) {
				this.addActionError(getText("web.error.OSCAction.viewContentDetail.subjectNoExisting"));
				logger.error(e.getMessage());
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * Display ask expert screen
	 * 
	 * @return
	 * @throws Exception
	 */
	public String displayAskExpert() throws Exception {
		final String LOCATION = "displayAskExpert()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			model.setTopic(onlineServiceManager.getTopicById(model.getTopicId()));
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
	 * Ajax action to grab question of a topic
	 * 
	 * @return
	 */
	public String showQuestion() throws Exception {
		final String LOCATION = "showQuestion()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			model.setQuestions(onlineServiceManager.getQuestionByTopicId(model.getTopicId(), model.getPagingInfo()));
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
	 * Ajax action to save question into database
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveQuestion() throws Exception {
		final String LOCATION = "saveQuestion()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			model.getQuestion().setCreatedBy(Utilities.getCurrentLoginUserName());
			onlineServiceManager.saveQuestion(model.getQuestion());
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
