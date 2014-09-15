package vn.com.vndirect.web.struts2.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.vndirect.domain.struts2.home.IRSnapshotModel;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class IRSnapshotAjaxAction extends ActionSupport implements ModelDriven<IRSnapshotModel> {
	private static final String EVENT_NEWSTYPE = "Event";

	private static final String MAC_VN_NEWSTYPE = "MacVN";

	private final static Logger logger = Logger.getLogger(IRSnapshotAjaxAction.class);

	private IRSnapshotModel model = new IRSnapshotModel();

	private static final String DISCLOSURE_NEWSTYPE = "Disclousure";
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

	@Autowired
	private IQuotesManager quotesManager;
	
	@Autowired
	private INewsInfoManager newsInfoManager;

	public String getCompanyQuote() throws Exception {
		final String LOCATION = "getCompanyQuote";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			currentComp.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			final SecuritiesInfoForQuote secuInfoForQuote = quotesManager.getCompanySnapshotHighLights(
					SessionManager.OnlineUsers.getVNDSAuthenticationHeader(), currentComp);
			model.setSecuritiesInfoForQuote(secuInfoForQuote);

			final IfoCompanySnapshotViewExt ifoComSnapshotViewExt = quotesManager.getCompanySnapshotInfo(currentComp);
			model.setIfoCompanySnapshotViewExt(ifoComSnapshotViewExt);
		} catch (Exception e) {
			logger.error("ajax/irSnapshot/getCompanyQuote exception");
		}

		return SUCCESS;
	}

	public String getMostNews() throws Exception {
		final String LOCATION = "getMostNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			currentComp.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			final SearchIfoNews searchObj = new SearchIfoNews();
			// searchObj.setNewsType(DISCLOSURE_NEWSTYPE);
			ArrayList<String> types = new ArrayList<String>();
			types.add(DISCLOSURE_NEWSTYPE);
			types.add(MAC_VN_NEWSTYPE);
			searchObj.setNewsTypeList(types);
			searchObj.setOrderByDate(true);
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setCompanyId(currentComp.getCompanyId());
			searchObj.setListSymbols(new String[] { "VND" });

			// most viewed. get only 5 news
			final SearchResult<SearchIfoNews> mostViewResult = newsInfoManager.getMostViewedNews(searchObj, new PagingInfo(8));
			for (int i = 0; i < mostViewResult.size(); i++) {
				SearchIfoNews ifoNews = mostViewResult.get(i);
				if(StringUtils.isEmpty(ifoNews.getNewsHeader()) || StringUtils.isEmpty(ifoNews.getNewsType())){
					mostViewResult.remove(i);
					continue;
				}
				if (ifoNews.getNewsType().equals(DISCLOSURE_NEWSTYPE)) {
					mostViewResult.get(i).setUrlDetail(
							NewsUrlUtility.createUrl("quan-he-co-dong-vndirect", ifoNews.getNewsType(), ifoNews.getNewsHeader(),
									ifoNews.getNewsId(), ""));
				} else if (ifoNews.getNewsType().equals(MAC_VN_NEWSTYPE)) {
					mostViewResult.get(i).setUrlDetail(
							NewsUrlUtility.createUrl("quan-he-co-dong-vndirect", EVENT_NEWSTYPE, ifoNews.getNewsHeader(),
									ifoNews.getNewsId(), ""));
				}
			}
			model.setMostViewResult(mostViewResult);
		} catch (Exception e) {
			logger.error("ajax/irSnapshot/getMostNews exception");
		}

		return SUCCESS;
	}

	public String getReports() throws Exception {
		final String LOCATION = "getMostNews";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		try {
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			currentComp.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			final SearchIfoNews searchObj = new SearchIfoNews();
			searchObj.setNewsTypeList(types);
			searchObj.setOrderByDate(true);
			searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchObj.setListSymbols(new String[] { "VND" });
			searchObj.setCompanyId(currentComp.getCompanyId());

			SearchResult<SearchIfoNews> news = newsInfoManager.getReportAnalysis(searchObj, new PagingInfo(8));

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
			model.setIfoDocumentResult(result);
		} catch (Exception e) {
			logger.error("ajax/irSnapshot/getReports exception");
		}

		return SUCCESS;
	}

	public String executeCheckWhereToGetPriceData() throws Exception {
		final String LOCATION = "executeCheckWhereToGetPriceData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			model.setIsDataFromDatabase(VNDirectWebUtilities.isDataFromDatabase());

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
		} catch (Exception e) {
			Utilities.processErrors(this, e);
			logger.error(LOCATION, e);
		}

		return SUCCESS;
	}

	public String executeGetIntradayPrice() {
		final String LOCATION = "execute()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + "BEGIN");
		try {
			String symbolQoute = model.getSymbol();
			if (symbolQoute.length() > 0) {
				final IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();
				intradayPriceSearch.setListSymbols(new String[] { symbolQoute });
				final IntradayPriceSearchResult searchIntradayPriceResult = quotesManager
						.searchIntradayPrice(intradayPriceSearch);
				if (searchIntradayPriceResult != null && searchIntradayPriceResult.getListSecInfo() != null
						&& searchIntradayPriceResult.getListSecInfo().length > 0) {
					final SecInfo secInfo = searchIntradayPriceResult.getListSecInfo()[0];
					model.setSecInfo(secInfo);
				}
			}
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + "END");
			return SUCCESS;
		} catch (Exception e) {
			Utilities.processErrors(this, e);
			return SUCCESS;
		}
	}

	public IRSnapshotModel getModel() {
		return this.model;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}
}
