package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.struts2.listedmarket.SnapshotModel;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class SnapshotAction extends ActionSupport implements ModelDriven<SnapshotModel> {
	private static Logger logger = Logger.getLogger(SnapshotAction.class);

	private SnapshotModel model = new SnapshotModel();

	@Autowired
	private IQuotesManager quotesManager;
	// private FinfoDBManager finfoDBManager;
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;

	public SnapshotModel getModel() {
		return model;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	public String getStockInformation() throws FunctionalException, SystemException {
		final String LOCATION = "getStockInformation()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			CurrentCompanyForQuote currentComp;
			String symbolCompany = model.getSymbol();
			if (StringUtils.isEmpty(symbolCompany)) {
				symbolCompany = SessionManager.getSymbolCompany();
			}
			if(symbolCompany == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbolCompany,
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentComp == null) {
				return ERROR;
			}
			
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			model.setExchangeCode(currentComp.getCurrentExchangeCode());

			// do SEO on-page
			String title = "VNDIRECT-" + this.getText("analysis.stockInfo.company.snapshot") + " | ";
			if (I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()).equalsIgnoreCase("VN")) {
				title += currentComp.getCompanyName();
			} else {
				title += currentComp.getCompanyFullName();
			}
			title += "-" + currentComp.getSymbol();
			model.setPageTitle(title);
			model.setPageDescription(this.getText("analysis.stockInfo.desc") + currentComp.getSymbol());
			String[] keywords = this.getText("analysis.stockInfo.keywords").trim().split(",");
			String dynamicKeyword = "";
			for (int i = 0; i < keywords.length; i++) {
				dynamicKeyword += keywords[i] + " " + currentComp.getSymbol();
				if (i != keywords.length - 1) {
					dynamicKeyword += ",";
				}
			}
			model.setPageKeywords(dynamicKeyword);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("analysis.stockInfo.company.snapshot"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.company"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			SessionManager.setSymbolCompany(currentComp.getSymbol());
			model.setSymbol(currentComp.getSymbol());

			// get CompanySnapshotView info
			currentComp.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			IfoCompanySnapshotViewExt ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(currentComp);
			ifoComSnapshotViewExt = ifoComSnapshotViewExt == null ? new IfoCompanySnapshotViewExt() : ifoComSnapshotViewExt;

			// format company overview --> replace \n by <br>
			if (ifoComSnapshotViewExt != null && ifoComSnapshotViewExt.getId() != null) {
				String compOverview = ifoComSnapshotViewExt.getId().getCompanyOverview();
				compOverview = VNDirectWebUtilities.replaceString(compOverview, "\n", "<br/>");
				ifoComSnapshotViewExt.getId().setCompanyOverview(compOverview);
			}
			model.setIfoComSnapshotViewExt(ifoComSnapshotViewExt);

			// Related Company
			String usLocale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			String nextIndex = "1";
			IfoCompanyIndustryView ifoCompanyIndustryView = currentComp.getIfoCompanyIndustryView(usLocale);
			model.setIfoCompanyIndustryView(ifoCompanyIndustryView);

			// Search list of companies
			IfoCompanyCalcView searchObj = new IfoCompanyCalcView();
			searchObj.setSecCode(currentComp.getSymbol());

			searchObj.setLocale(usLocale);
			searchObj = analysisToolsManager.getIfoCompanyCalcViewBySymbol(searchObj);
			int offsetNumber = Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.NUMBER_OF_RELATED_COMPANIES));
			PagingInfo pagingInfo = new PagingInfo();
			pagingInfo.setOffset(offsetNumber);
			if (nextIndex != null && nextIndex != "") {
				pagingInfo.setIndexPage(Integer.parseInt(nextIndex));
			}

			searchObj.setNumberItem(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.NUMBER_OF_RELATED_COMPANIES)));
			model.setIfoCompanyCalcView(searchObj);

			// SearchResult result = finfoDBManager.getListCompaniesByIndustry(searchObj, pagingInfo);
			// model.setSearchResult(result);

		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String getIRStockInformation() throws FunctionalException, SystemException {
		final String LOCATION = "getIRStockInformation()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			final CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			currentComp.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			model.setSymbol(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.basicInfo"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("ir.si.thongtincoban"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			// get CompanySnapshotView info
			IfoCompanySnapshotViewExt ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(currentComp);
			ifoComSnapshotViewExt = ifoComSnapshotViewExt == null ? new IfoCompanySnapshotViewExt() : ifoComSnapshotViewExt;
			// format company overview --> replace \n by <br>
			if (ifoComSnapshotViewExt != null && ifoComSnapshotViewExt.getId() != null) {
				String compOverview = ifoComSnapshotViewExt.getId().getCompanyOverview();
				compOverview = VNDirectWebUtilities.replaceString(compOverview, "\n", "<br/>");
				ifoComSnapshotViewExt.getId().setCompanyOverview(compOverview);
			}
			model.setIfoComSnapshotViewExt(ifoComSnapshotViewExt);

			// get Insider transactions
			final IfoCompanyNameViewId ifoCompanyNameViewId = new IfoCompanyNameViewId(currentComp.getCompanyId());
			ifoCompanyNameViewId.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			List<IfoInsiderTransactionView> ifoInsiderTransactionViewList = quotesManager.getInsiderTransactions(ifoCompanyNameViewId);
			model.setIfoInsiderTransactionViewList(ifoInsiderTransactionViewList);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}
}
