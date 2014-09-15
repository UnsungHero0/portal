package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.FinfoDBManager;
import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.business.IAuditManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.InfoCompanyExt;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.struts2.listedmarket.SnapshotModel;
import vn.com.vndirect.wsclient.audit.AuditSymbol;
import vn.com.vndirect.wsclient.audit.CreateAuditSymbolRequest;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class SnapshotAJAXAction extends ActionSupport implements ModelDriven<SnapshotModel> {
	private static final long serialVersionUID = -6186050509925411224L;
	private static Logger logger = Logger.getLogger(SnapshotAJAXAction.class);

	private SnapshotModel model = new SnapshotModel();

	@Autowired
	private IQuotesManager quotesManager;
	
	@Autowired
	private FinfoDBManager finfoDBManager;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;
	private IAuditManager auditManager;

	public SnapshotModel getModel() {
		return model;
	}

	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	public void setFinfoDBManager(FinfoDBManager finfoDBManager) {
		this.finfoDBManager = finfoDBManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public void setAuditManager(IAuditManager auditManager) {
		this.auditManager = auditManager;
	}

	public String executeSearchRelatedCompany() throws FunctionalException, SystemException {
		final String LOCATION = "executeSearchRelatedCompany()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			// get company info from session
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			// Related Company
			String usLocale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
			// IfoCompanyIndustryView ifoCompanyIndustryView =
			// currentComp.getIfoCompanyIndustryView(usLocale);
			// model.setIfoCompanyIndustryView(ifoCompanyIndustryView);

			if (currentComp == null) {
				throw new NullPointerException("Current company null");
			}
			// Search list of companies
			
			IfoCompanyCalcView searchObj = new IfoCompanyCalcView();
			searchObj.setSecCode(currentComp.getSymbol());

			searchObj.setLocale(usLocale);
			searchObj = analysisToolsManager.getIfoCompanyCalcViewBySymbol(searchObj);

			PagingInfo pagingInfo = model.getPagingInfo();
			pagingInfo
					.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.NUMBER_OF_RELATED_COMPANIES)));
			// model.setIfoCompanyCalcView(searchObj);

			SearchResult result = finfoDBManager.getListCompaniesByIndustry(searchObj, pagingInfo);

			pagingInfo.setTotal(result.getPaging().getTotal());
			model.setPagingInfo(pagingInfo);

			if (result != null) {
				// +++ Get intraday value
				List<String> listSymbolSearch = new ArrayList<String>();
				List<InfoCompanyExt> listQuote = new ArrayList<InfoCompanyExt>();
				InfoCompanyExt infoCompanyExtTemp;
				IfoCompanyCalcView ifoCompanyCalcViewTemp;
				for (Object element : result) {
					ifoCompanyCalcViewTemp = (IfoCompanyCalcView) element;
					listSymbolSearch.add(ifoCompanyCalcViewTemp.getSecCode());

					infoCompanyExtTemp = new InfoCompanyExt();
					infoCompanyExtTemp.setInfoCompany(ifoCompanyCalcViewTemp);
					listQuote.add(infoCompanyExtTemp);
				}
				IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();
				intradayPriceSearch.setListSymbols(listSymbolSearch.toArray(new String[listSymbolSearch.size()]));
				IntradayPriceSearchResult searchIntradayPriceResult = quotesManager.searchIntradayPrice(intradayPriceSearch);
				if (searchIntradayPriceResult != null && searchIntradayPriceResult.getListSecInfo() != null) {
					SecInfo[] secInfos = searchIntradayPriceResult.getListSecInfo();
					List<InfoCompanyExt> listQuoteExt = new ArrayList<InfoCompanyExt>();
					for (InfoCompanyExt infoCompanyExt : listQuote) {
						for (SecInfo secInfo : secInfos) {
							if (secInfos != null && secInfo.getCode().equals(infoCompanyExt.getInfoCompany().getSecCode())) {
								infoCompanyExt.setSecInfo(secInfo);
								break;
							}
						}
						listQuoteExt.add(infoCompanyExt);
					}
					model.setListQuote(listQuoteExt);
				} else {
					model.setListQuote(listQuote);
				}
			}

			// For Audit Symbol
			if (logger.isDebugEnabled())
				logger.debug("------->::: BEGIN AUDIT");
			AuditSymbol auditSymbol = new AuditSymbol();
			auditSymbol.setSymbol(currentComp.getSymbol());
			Long onlineUserId = SessionManager.OnlineUsers.getOnlineUserId();
			if (onlineUserId != null) {
				auditSymbol.setOnlineUserId(onlineUserId);
			}
			String auditType = ServerConfig.getOnlineValue(Constants.IServerConfig.Audit.SEARCH_SYMBOL_TYPE);
			CreateAuditSymbolRequest createAuditSymbolRequest = new CreateAuditSymbolRequest();

			createAuditSymbolRequest.setAuditSymbol(auditSymbol);
			Integer auditTypeValue = VNDirectWebUtilities.getInteger(VNDirectWebUtilities.string2Integer(auditType));
			auditSymbol.setAuditType(auditTypeValue);
			auditSymbol.setAuditedDate(DateUtils.getCalendar(DateUtils.getCurrentDate()));

			auditManager.createAuditSymbol(createAuditSymbolRequest);
			if (logger.isDebugEnabled())
				logger.debug("------->::: END AUDIT");
			// END AUDIT SYMBOL

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
