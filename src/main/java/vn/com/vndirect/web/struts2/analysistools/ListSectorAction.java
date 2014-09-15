/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.struts2.analysistools.ListSectorModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Action class is used to display Industry Center page
 * 
 * @author Huy
 * 
 */
@SuppressWarnings("serial")
public class ListSectorAction extends ActionSupport implements ModelDriven<ListSectorModel>, Preparable {

	private ListSectorModel model = new ListSectorModel();
	private Logger logger = Logger.getLogger(getClass());
	private String locale;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;
	

	/**
	 * @param analysisToolsManager
	 *            the analysisToolsManager to set
	 */
	public String executeListSectorActionEmpty() {
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Sector.Index"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.industyAnalysis"));
		breadcrumbs.add("/phan-tich-nganh/tong-quan-nganh.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage("setAnalysisToolsManager");
		return SUCCESS;
	}

	/**
	 * Process when user click on sector link
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executeSearchSector() throws Exception {
		final String LOCATION = "executeSearchSector()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			doSEOpage("setAnalysisToolsManager");
			model.getIfoSectorCalcView().setLocale(locale);
			model.setSearchResult(analysisToolsManager.getListSectors(model.getIfoSectorCalcView()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		return SUCCESS;
	}

	/**
	 * Process when user click on industry link
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executeSearchIndustry() throws Exception {
		final String LOCATION = "executeSearchIndustry()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.getIfoIndustryCalcView().setLocale(locale);
			model.setIfoSectorCalcView(analysisToolsManager.getIfoSectorCalcViewByCode(model.getIfoIndustryCalcView()));
			model.setSearchResult(analysisToolsManager.getListIndustries(model.getIfoIndustryCalcView()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Process when user click on company link
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String executeSearchCompany() throws Exception {
		final String LOCATION = "processSearchCompany()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.getIfoCompanyCalcView().setLocale(locale);
			model.setIfoSectorCalcView(analysisToolsManager.getIfoSectorCalcViewByCode(model.getIfoCompanyCalcView()));
			model.setIfoIndustryCalcView(analysisToolsManager.getIfoIndustryCalcViewByCode(model.getIfoCompanyCalcView()));
			model.setSearchResult(analysisToolsManager.getListCompanies(model.getIfoCompanyCalcView()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Process when user click on "view sector" button
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String executeSearchBySymbol() throws Exception {
		final String LOCATION = "processSearchBySymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			IfoCompanyCalcView obj = model.getIfoCompanyCalcView();
			obj.setLocale(locale);

			if (ListSectorModel.FROM_VALUE.equalsIgnoreCase(model.getFrom())) {
				obj.setSecCode(SessionManager.getSymbolCompany());
			}

			obj = analysisToolsManager.getIfoCompanyCalcViewBySymbol(obj);
			if (StringUtils.isEmpty(obj.getSecCode())) {
				addActionError(getText("web.label.messages.norecord"));
				return INPUT;
			}

			model.setIfoSectorCalcView(analysisToolsManager.getIfoSectorCalcViewByCode(obj));
			model.setIfoIndustryCalcView(analysisToolsManager.getIfoIndustryCalcViewByCode(obj));
			// searchObj.setSecCode(null); ????????
			SearchResult<IfoCompanyCalcView> result = analysisToolsManager.getListCompanies(obj);

			if (logger.isDebugEnabled())
				logger.debug("result:" + result);
			model.setSearchResult(result);
			model.setIfoCompanyCalcView(obj);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Process when user enter "INDUSTRY INDEX" page fix vietpn
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String executeSearchSectorIndustries() throws Exception {
		final String LOCATION = "processSearchSectorIndustries";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.getIfoIndustryCalcView().setLocale(locale);
			SearchResult<IfoIndustryCalcView> result = analysisToolsManager.getSectorIndustries(model.getIfoIndustryCalcView());
			if (result == null || result.size() == 0) {
				// FIXME: add action error here
				// this.addMessage(request, new
				// ActionMessage(Constants.MessageKeys.Commons.NO_RECORD_FOUND));
			} else {
				int size = result.size();
				model.setIndustries1(result.subList(0, size / 3 + 1));
				if (size > 1) {
					model.setIndustries2(result.subList(size / 3 + 1, size / 3 * 2 + 1));
					model.setIndustries3(result.subList(size / 3 * 2 + 2, size));
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Sector.Classification"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.industyAnalysis"));
		breadcrumbs.add("/phan-tich-nganh/tong-quan-nganh.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage("executeSearchSectorIndustries");
		return SUCCESS;
	}

	public ListSectorModel getModel() {
		return model;
	}

	public void prepare() throws Exception {
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
	}

	private void doSEOpage(String method) {
		if (method.equals("setAnalysisToolsManager")) {
			model.setPageTitle(this.getText("analysis.listSectorView.title"));
			model.setPageDescription(this.getText("analysis.listSectorView.desc"));
			model.setPageKeywords(this.getText("analysis.listSectorView.keywords"));
		} else if (method.equals("executeSearchSectorIndustries")) {
			model.setPageTitle(this.getText("analysis.listSectorIndex.title"));
			model.setPageDescription(this.getText("analysis.listSectorIndex.desc"));
			model.setPageKeywords(this.getText("analysis.listSectorIndex.keywords"));
		}
	}
}
