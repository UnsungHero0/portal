/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.commons.Code;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.struts2.analysistools.SectorOverviewModel;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Display information about sector, industry and company
 * 
 * @author Huy
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SectorOverviewAction extends ActionSupport implements ModelDriven<SectorOverviewModel>, Preparable {

	private static Logger logger = Logger.getLogger(SectorOverviewAction.class);

	private SectorOverviewModel model = new SectorOverviewModel();
	private String locale;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;

	/**
	 * Sort Field
	 */
	private String field;
	/**
	 * Sort order
	 */
	private String order;

	private String error;

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	private String sectorUrl;

	/**
	 * @param sectorUrl
	 *            the sectorUrl to set
	 */
	public void setSectorUrl(String sectorUrl) {
		this.sectorUrl = sectorUrl;
	}

	private String sectorDetailsUrl;

	/**
	 * @param sectorDetailsUrl
	 *            the sectorDetailsUrl to set
	 */
	public void setSectorDetailsUrl(String sectorDetailsUrl) {
		this.sectorDetailsUrl = sectorDetailsUrl;
	}

	public String getCompanyNotFoundUrl() {
		// return Utilities.FormatURL.encodeURI(sectorUrl,
		// ServletActionContext.getRequest());
		return sectorUrl;
	}

	public String getSectorDetailsEncodedUrl() {
		// return Utilities.FormatURL.encodeURI(sectorDetailsUrl,
		// ServletActionContext.getRequest());
		return sectorDetailsUrl;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(IAnalysisToolsManager manager) {
		this.analysisToolsManager = manager;
	}

	/**
	 * Display information of all the sectors
	 * 
	 * @return if no error occurs, "success" will be returned, otherwise "input"
	 * @throws Exception
	 */
	@Override
	public String execute() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if ("1".equals(error)) {
			addActionError(getText("web.label.messages.norecord"));
		}

		try {
			// load sectors
			model.setSectors(analysisToolsManager.getListOfSector(locale));
			// load top industries
			model.setTopIndustries(analysisToolsManager.getListOfNamedIndustries(10, locale));
			// load hot industries
			model.setHotIndustries(analysisToolsManager.getHotIndustries(5, locale));
			// load hot sectors
			model.setHotSectors(analysisToolsManager.getHotSectors(5, locale));
			// load 3 month high performance
			model.setThrMonHighPerfSectors(analysisToolsManager.getThrMonHighPerfSectors(3, locale));
			model.setThrMonHighPerfIndustries(analysisToolsManager.getThrMonHighPerfIndustries(3, locale));

		} catch (Exception e) {
			logger.error(LOCATION, e);
			// connect to web service fail
			addActionError(getText("system.error.sysex"));
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Sector.Overview"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.industyAnalysis"));
		breadcrumbs.add("/phan-tich-nganh/tong-quan-nganh.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
	}

	/**
	 * Display detail information of a sector
	 * 
	 * @return if no error occurs, "success" will be returned, otherwise "input"
	 * @throws Exception
	 */
	public String showSectorDetails() throws Exception {
		final String LOCATION = "showSectorDetails()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			// redisplay information about sector
			if (!StringUtils.isEmpty(model.getSectorCode())) {

				// load sectors
				model.setSectors(analysisToolsManager.getListOfSector(locale));

				model.setSector(analysisToolsManager.getSector(locale, model.getSectorCode()));
				// if data is available
				if (!MapUtils.isEmpty(model.getSector())) {

					// load industry sub-category
					IfoIndustryCalcView industry = new IfoIndustryCalcView();
					industry.setSectorCode(model.getSectorCode());
					industry.setLocale(locale);
					model.setSubIndustries(analysisToolsManager.getListIndustries(industry));

					// load industries highlight
					model.setIndustriesHighlight(analysisToolsManager.getListOfIndustry(locale, model.getSectorCode()));

					// breadcrumbs
					ArrayList<String> breadcrumbs = new ArrayList<String>();

					breadcrumbs.add(this.getText("analysis.sectorDetail"));
					breadcrumbs.add(this.getText("home.topMenu.analysis"));
					breadcrumbs.add("ttptRoot");
					breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Sector.Overview"));
					breadcrumbs.add("/phan-tich-nganh/tong-quan-nganh.shtml");
					model.setBreadcrumbs(breadcrumbs);

					model.setPageTitle(this.getText("analysis.sectorDetail.title"));
					model.setPageDescription(this.getText("analysis.sectorOverview.desc"));
					model.setPageKeywords(this.getText("analysis.sectorOverview.keywords"));

					return SUCCESS;
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		return INPUT;
	}

	/**
	 * Display information of an industry which contains a company that users
	 * want to look up
	 * 
	 * @return if no error occurs, "success" will be returned, otherwise "input"
	 * @throws Exception
	 */
	public String showIndustryDetails() throws Exception {
		final String LOCATION = "showIndustryDetails()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		String symbol = model.getSymbol();
		String sectorCode = model.getSectorCode();
		String industryCode = model.getIndustryCode();

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.industryDetail"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Sector.Overview"));
		breadcrumbs.add("/phan-tich-nganh/tong-quan-nganh.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// seo
		model.setPageTitle(this.getText("analysis.industryDetail.title"));
		model.setPageDescription(this.getText("analysis.sectorOverview.desc"));
		model.setPageKeywords(this.getText("analysis.sectorOverview.keywords"));

		try {
			if (StringUtils.isEmpty(symbol)) {
				if (!StringUtils.isEmpty(sectorCode)) {
					// move to sector details screen
					if (!StringUtils.isEmpty(industryCode)) {
						// load sector
						model.setSector(analysisToolsManager.getSector(locale, sectorCode));
						if (MapUtils.isEmpty(model.getSector())) {
							return INPUT;
						}

						// load industry
						model.setIndustry(analysisToolsManager.getIndustry(locale, industryCode));
						if (MapUtils.isEmpty(model.getIndustry())) {
							return INPUT;
						}

						// load companies
						model.setCompanies(analysisToolsManager.getListOfCompaniesWS(industryCode, Code.INDUSTRY, 10));

						// move to industry details screen
						return "industry";
					} else {
						return "sector";
					}
				} else {
					return INPUT;
				}
			} else {
				IfoCompanyCalcView company = analysisToolsManager.getCompanyEDB(model.getSymbol());

				if (company != null) {
					if (!StringUtils.isEmpty(sectorCode) && !sectorCode.equals(company.getSectorCode())) {
						return INPUT;
					} else {
						// load sector
						model.setSector(analysisToolsManager.getSector(locale, company.getSectorCode()));
						if (MapUtils.isEmpty(model.getSector())) {
							return INPUT;
						}

						// load industry
						model.setIndustry(analysisToolsManager.getIndustry(locale, company.getIndustryCode()));
						if (MapUtils.isEmpty(model.getIndustry())) {
							return INPUT;
						}

						// load companies
						model.setCompanies(analysisToolsManager.getListOfCompaniesWS(company.getIndustryCode(), Code.INDUSTRY, 10));

						// move to industry details screen
						return "industry";
					}
				} else {
					return INPUT;
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return INPUT;
	}

	/**
	 * Ajax action which will grab information of all the companies belong to an
	 * industry
	 * 
	 * @return "success" will be returned
	 * @throws SystemException
	 */
	public String showCompaniesByIndustryCode() throws Exception {
		final String LOCATION = "showCompaniesByIndustryCode()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			// data from embed database
			SearchResult<IfoCompanyCalcView> edb = analysisToolsManager.getListOfCompaniesEDB(model.getIndustryCode(), Code.INDUSTRY,
			        model.getPagingInfo(), field, order);
			// do mapping
			setMappingData(edb);

			model.setCompaniesCombo(edb);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			// could not connect with web service ??
			addActionError(getText("system.error.connect", new String[] { e.getMessage() }));
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Ajax action which will grab information of all the companies belong to a
	 * sector
	 * 
	 * @return "success" will be returned
	 * @throws SystemException
	 */
	public String showCompaniesBySectorCode() throws Exception {
		final String LOCATION = "showCompaniesBySectorCode()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			// data from embed database
			SearchResult<IfoCompanyCalcView> edb = analysisToolsManager.getListOfCompaniesEDB(model.getSectorCode(), Code.SECTOR,
			        model.getPagingInfo(), field, order);
			// do mapping
			setMappingData(edb);

			model.setCompaniesCombo(edb);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			// could not connect with web service ??
			addActionError(getText("system.error.connect", new String[] { e.getMessage() }));
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Mapping data returned from web service and embed database
	 * 
	 * @param edb
	 *            data to map
	 * @throws Exception
	 */
	private void setMappingData(SearchResult<IfoCompanyCalcView> edb) throws Exception {
		// data from web service
		String[] symbols = new String[edb.size()];
		for (int i = 0; i < edb.size(); i++) {
			symbols[i] = edb.get(i).getSecCode();
		}
		final SecInfo[] ws = analysisToolsManager.getListOfCompaniesWS(symbols);

		// mapping between 2 collections
		CollectionUtils.transform(edb, new Transformer() {
			public Object transform(Object arg0) {
				IfoCompanyCalcView obj = (IfoCompanyCalcView) arg0;
				for (int i = 0; i < ws.length; i++) {
					if (obj.getSecCode().equals(ws[i].getCode())) {
						// set "last" and change(%)
						obj.setClosePrice(ws[i].getClosePrice());
						if (obj.getClosePrice().intValue() == 0)
							obj.setClosePrice(ws[i].getMatchPrice());
						if (obj.getClosePrice().intValue() == 0)
							obj.setClosePrice(ws[i].getBasicPrice());
						obj.setChgIndex(ws[i].getChgIndex());
						obj.setPctIndex(ws[i].getPctIndex());
						break;
					}
				}
				return obj;
			}
		});
	}

	/**
	 * Ajax action which will grab information of a top industry belong to a
	 * sector
	 * 
	 * @return "success" will be returned
	 * @throws SystemException
	 */
	public String showTopIndustry() throws SystemException {
		final String LOCATION = "showTopIndustry()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setTopIndustry(analysisToolsManager.getTopIndustry(model.getSectorCode(), locale));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * Ajax action which will grab information of a top company belong to a
	 * industry
	 * 
	 * @return "success" will be returned
	 * @throws SystemException
	 */
	public String showTopCompany() throws SystemException {
		final String LOCATION = "showTopCompany()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			model.setTopCompany(analysisToolsManager.getTopCompany(model.getIndustryCode()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public SectorOverviewModel getModel() {
		return model;
	}

	public void prepare() throws Exception {
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.sectorOverview.title"));
		model.setPageDescription(this.getText("analysis.sectorOverview.desc"));
		model.setPageKeywords(this.getText("analysis.sectorOverview.keywords"));
	}
}
