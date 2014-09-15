package vn.com.vndirect.web.struts2.holderrelation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.vndirect.domain.struts2.holderrelation.HolderRelationModel;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class ShareHolderRelationAction extends ActionSupport implements ModelDriven<HolderRelationModel> {
	private static Logger logger = Logger.getLogger(ShareHolderRelationAction.class);
	private HolderRelationModel model = new HolderRelationModel();
	
	@Autowired
	private INewsInfoManager newsInfoManager;
	
	private List<String> types = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2849252542930397016L;
		{
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_ANNOUNCEMENT));
			add(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_FINANCIAL_STATEMENT));
		}
	};

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @return the model
	 */
	public HolderRelationModel getModel() {
		return model;
	}

	public String viewHomePage() throws RemoteException, Exception {
		final String LOCATION = "viewHomePage()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		try {
			String currentSymbol = "VND";
			CurrentCompanyForQuote currentCompanyForQuote = quotesManager.quickSearchQuotes(currentSymbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentCompanyForQuote.getSymbol());

			String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			String status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;

			model.setCompanyInfo(currentCompanyForQuote);
			model.setSymbol(currentCompanyForQuote.getSymbol());

			// get CompanySnapshotView info
			currentCompanyForQuote.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			IfoCompanySnapshotViewExt ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(currentCompanyForQuote);
			ifoComSnapshotViewExt = ifoComSnapshotViewExt == null ? new IfoCompanySnapshotViewExt() : ifoComSnapshotViewExt;

			// format company overview --> replace \n by <br>
			if (ifoComSnapshotViewExt != null && ifoComSnapshotViewExt.getId() != null) {
				String compOverview = ifoComSnapshotViewExt.getId().getCompanyOverview();
				compOverview = VNDirectWebUtilities.replaceString(compOverview, "\n", "<br/>");
				ifoComSnapshotViewExt.getId().setCompanyOverview(compOverview);
			}
			model.setIfoComSnapshotViewExt(ifoComSnapshotViewExt);

			SecuritiesInfoForQuote secuInfoForQuote = null;
			secuInfoForQuote = quotesManager.getCompanySnapshotHighLights(SessionManager.OnlineUsers.getVNDSAuthenticationHeader(), currentCompanyForQuote);
			model.setSecuInfoForQuote(secuInfoForQuote);

			SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setLocale(locale);
			searchObj.setStatus(status);
			searchObj.setNewsTypeList(types);
			searchObj.setListSymbols(new String[] { currentCompanyForQuote.getSymbol() });

			PagingInfo pagingInfo = model.getPagingInfo();
			pagingInfo.setOffset(5);

			SearchResult<SearchIfoNews> news = newsInfoManager.getReportAnalysis(searchObj, model.getPagingInfo());

			SearchResult<IfoDocument> result = new SearchResult<IfoDocument>();
			for (SearchIfoNews elem : news) {
				IfoDocument doc = new IfoDocument();
				doc.setTitle(elem.getNewsHeader());
				doc.setContributor(elem.getNewsResource());// "CONTRIBUTOR"
				doc.setReleasedDate(elem.getNewsDate());
				doc.setAbstract_(elem.getNewsAbstract());
				doc.setFileName(elem.getAttachmentNews().get(0).getFileName());
				doc.setFilePath(elem.getAttachmentNews().get(0).getOriginalLink());

				result.add(doc);
			}
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRAnnualReport() throws RemoteException, Exception {
		final String LOCATION = "viewIRAnnualReport()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// doSEO
		model.setPageTitle(this.getText("home.ir.financials.annualReport.title"));
		model.setPageDescription(this.getText("home.ir.financials.annualReport.desc"));
		model.setPageKeywords(this.getText("home.ir.financials.annualReport.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.finance.annual"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.finance"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewAnnualReport() throws RemoteException, Exception {
		final String LOCATION = "viewAnnualReport()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// doSEO
		if (StringUtils.isNotEmpty(model.getPageTitle())) {
			model.setPageTitle(this.getText(model.getPageTitle()));
		}
		if (StringUtils.isNotEmpty(model.getPageDescription())) {
			model.setPageDescription(this.getText(model.getPageDescription()));
		}
		if (StringUtils.isNotEmpty(model.getPageKeywords())) {
			model.setPageKeywords(this.getText(model.getPageKeywords()));
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRRegulation() throws RemoteException, Exception {
		final String LOCATION = "viewRegulation()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		// doSEO
		model.setPageTitle(this.getText("home.ir.managements.regulations.title"));
		model.setPageDescription(this.getText("home.ir.managements.regulations.desc"));
		model.setPageKeywords(this.getText("home.ir.managements.regulations.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.management.regulations"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.management"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRAskAndAnswer() throws RemoteException, Exception {
		final String LOCATION = "viewAskAndAnswer()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// doSEO
		model.setPageTitle(this.getText("home.ir.qa.title"));
		model.setPageDescription(this.getText("home.ir.qa.desc"));
		model.setPageKeywords(this.getText("home.ir.qa.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.contact"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRInspectionCommittee() throws RemoteException, Exception {
		final String LOCATION = "viewInspectionCommittee()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		// doSEO
		model.setPageTitle(this.getText("home.ir.managements.inspection.title"));
		model.setPageDescription(this.getText("home.ir.managements.inspection.desc"));
		model.setPageKeywords(this.getText("home.ir.managements.inspection.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.management.inspection"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.management"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRBoardOfDirectors() throws RemoteException, Exception {
		final String LOCATION = "viewBoardOfDirectors()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		// doSEO
		model.setPageTitle(this.getText("home.ir.managements.boards.title"));
		model.setPageDescription(this.getText("home.ir.managements.boards.desc"));
		model.setPageKeywords(this.getText("home.ir.managements.boards.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.management.board"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.management"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	public String viewIRGovernanceCommittee() throws RemoteException, Exception {
		final String LOCATION = "viewGovernanceCommittee()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		// doSEO
		model.setPageTitle(this.getText("home.ir.managements.governance.title"));
		model.setPageDescription(this.getText("home.ir.managements.governance.desc"));
		model.setPageKeywords(this.getText("home.ir.managements.governance.key"));

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("br.ir.management.governance"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.management"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/dieu-le-cong-ty.shtml");
		model.setBreadcrumbs(breadcrumbs);

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}
}