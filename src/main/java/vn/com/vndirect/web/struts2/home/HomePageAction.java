/**
 * 
 */
package vn.com.vndirect.web.struts2.home;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.struts2.osc.HomePageModel;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@ResultPath(value = "/")
@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ModelDriven<HomePageModel> {
	private static Logger logger = Logger.getLogger(HomePageAction.class);

	private HomePageModel model = new HomePageModel();

	public HomePageModel getModel() {
		return model;
	}

	@Autowired
	private IQuotesManager quotesManager;

	@Action(value = "home*", results = { @Result(name = "success", location = "HomePage", type = "tiles") })
	public String viewHomePage() {
		final String LOCATION = "viewHomePage";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// set page title, desc, keywords
		model.setPageTitle(this.getText("home.title"));
		model.setPageDescription(this.getText("home.desc"));
		model.setPageKeywords(this.getText("home.keywords"));

		// set breadcrumb
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.home"));
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}
}
