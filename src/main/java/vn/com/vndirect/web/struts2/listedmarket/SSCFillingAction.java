package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.listedmarket.SSCFillingModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class SSCFillingAction extends ActionSupport implements ModelDriven<SSCFillingModel>, Preparable {

	private static Logger logger = Logger.getLogger(SSCFillingAction.class);

	private SSCFillingModel model = new SSCFillingModel();

	@Autowired
	private INewsInfoManager newsInfoManager;
	
	@Autowired
	private IQuotesManager quotesManager;

	// private IListedMarketManager listedMarketManager;

	/**
	 * @return the model
	 */
	public SSCFillingModel getModel() {
		return model;
	}

	/**
	 * @param listedMarketManager
	 *            the listedMarketManager to set
	 */
	/*
	 * public void setListedMarketManager(IListedMarketManager
	 * listedMarketManager) { this.listedMarketManager = listedMarketManager; }
	 */

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

	private String locale;
	private String status;

	/**
	 * Setting the manager for NewsOnline
	 * 
	 * @param newsInfoManager
	 */
	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	public void prepare() throws Exception {
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		status = Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED;

	}

	public String viewSSCFilling() throws Exception {
		final String LOCATION = "viewSSCFilling()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
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
			breadcrumbs.add(this.getText("analysis.stockInfo.company.publishedDocument"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.company"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setLocale(locale);
			searchObj.setStatus(status);
			searchObj.setNewsTypeList(types);
			searchObj.setListSymbols(new String[] { currentComp.getSymbol() });

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

			if (result == null || result.size() == 0) {
				this.addActionError(getText("web.label.messages.norecord"));
			}

			request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setPagingInfo((PagingInfo) news.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public String viewIRSSCFilling() throws Exception {
		final String LOCATION = "viewIRSSCFilling()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			CurrentCompanyForQuote quote;
			String symbolCompany = model.getSymbol();
			if (StringUtils.isEmpty(symbolCompany)) {
				symbolCompany = SessionManager.getSymbolCompany();
			}
			quote = quotesManager.quickSearchQuotes(symbolCompany,
					I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (quote == null) {
				return ERROR;
			}
			SessionManager.setSymbolCompany(quote.getSymbol());

			model.setPageTitle(this.getText("home.ir.financials.report.title"));
			model.setPageDescription(this.getText("home.ir.financials.report.desc"));
			model.setPageKeywords(this.getText("home.ir.financials.report.key"));

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("br.ir.finance.report"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("br.ir.finance"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-ke-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setLocale(locale);
			searchObj.setStatus(status);
			searchObj.setNewsTypeList(types);
			searchObj.setListSymbols(new String[] { quote.getSymbol() });

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

			if (result == null || result.size() == 0) {
				this.addActionError(getText("web.label.messages.norecord"));
			}

			request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setPagingInfo((PagingInfo) news.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}
}
