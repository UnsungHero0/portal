/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.analysistools.MacroReportNewsModel;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Huy
 * 
 */
public class MacroReportNewsAction extends ActionSupport implements ModelDriven<MacroReportNewsModel>, Preparable {
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(MacroReportNewsAction.class);
	private static final long serialVersionUID = -4601070055870654807L;
	private MacroReportNewsModel model = new MacroReportNewsModel();

	private List<String> types = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2849252542930397016L;
		{
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_RESEARCH_OTHER));
		}
	};

	private String locale;
	private String status;

	@Autowired
	private INewsInfoManager newsInfoManager;

	/**
	 * Setting the manager for NewsOnline
	 * 
	 * @param newsInfoManager
	 */
	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	/**
	 * Display the report news
	 * 
	 * @return success
	 * @throws Exception
	 */
	public String displayMacroReportNews() throws Exception {
		final String LOCATION = "displayMacroReportNews()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setLocale(locale);
			searchObj.setStatus(status);
			searchObj.setNewsTypeList(types);
			if (!StringUtils.isEmpty(model.getSymbol())) {
				searchObj.setListSymbols(new String[] { model.getSymbol() });
			}
			model.setSearchResult(newsInfoManager.getReportAnalysis(searchObj, model.getPagingInfo()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public MacroReportNewsModel getModel() {
		return model;
	}

	public void prepare() throws Exception {
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;

	}
}
