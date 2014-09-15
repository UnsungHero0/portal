package vn.com.vndirect.web.struts2.stockHighlights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IStockHighlightsManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.stockHighlights.StockHighlightsModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author minh.nguyen
 * 
 */
public class StockHighlightsAction extends ActionSupport implements ModelDriven<StockHighlightsModel> {

	private static final int STOCK_HIGHLIGHT_DEFAULT_OFFSET = 10;
	private static final int STOCK_HIGHLIGHT_VIP_OFFSET = 25;
	private static final int STOCK_HIGHLIGHT_LIST_SYMBOL_HAS_REPORTS = 25;
	private static final int RELATED_REPORTS_OFFSET = 6;

	private static Logger logger = Logger.getLogger(StockHighlightsAction.class);

	private StockHighlightsModel model = new StockHighlightsModel();

	@Autowired
	private INewsInfoManager newsInfoManager;

	@Autowired
	private IStockHighlightsManager stockHighlightsManager;

	public String initializePage() {
		
		model.setPageTitle(this.getText("StockHighlights.Page.Title"));
		model.setPageDescription(this.getText("StockHighlights.Page.Desc"));
		model.setPageKeywords(this.getText("StockHighlights.Page.Key"));
		
		return SUCCESS;
	}
	
	public String getListSymbolsHaveReportsAjax() {
		try {
			// to get newest report only
			model.getPagingInfo().setOffset(STOCK_HIGHLIGHT_LIST_SYMBOL_HAS_REPORTS);

			// call service
			ArrayList<String> symbolsHaveReports = stockHighlightsManager.getSymbolsListHaveReports(model.getPagingInfo());

			String strFreeSymbols[] = Constants.IServerConfig.STOCK_HIGHLIGHTS_FREE_REPORTS.split(",");
			ArrayList<String> symbolsFreeReports = new ArrayList<String>();
			for (String s : strFreeSymbols) {
				symbolsFreeReports.add(s);
			}
			model.setListSymbolsFreeReports(symbolsFreeReports);
			model.setListSymbolsHaveReports(symbolsHaveReports);
		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return SUCCESS;
	}

	public String viewReportDetail() {
		
		model.setPageTitle(this.getText("StockHighlights.Page.Title"));
		model.setPageDescription(this.getText("StockHighlights.Page.Desc"));
		model.setPageKeywords(this.getText("StockHighlights.Page.Key"));

		SearchIfoNews ifoNews = null;

		String symbol = model.getSymbol();
		if (StringUtils.isNotEmpty(symbol)) {
			ifoNews = getNewsestReport(symbol);
		} else {
			ifoNews = getReportByNewsId(model.getNewsId());
		}

		try {
			if (ifoNews != null) {
				model.setSearchIfoNews(ifoNews);

				// increase news hit
				newsInfoManager.updateNewsHit(ifoNews.getNewsId());
				
				model.setPageTitle(this.getText("StockHighlights.Page.Title") + " - " + ifoNews.getNewsHeader());
			}
		} catch (Exception e) {
			logger.error("StockHighlightsAction.viewReportDetail:" + e);
		}

		return SUCCESS;
	}
	
	public String viewFreeReportDetail() {
		SearchIfoNews ifoNews = null;
		
		model.setPageTitle(this.getText("StockHighlights.Page.Title"));
		model.setPageDescription(this.getText("StockHighlights.Page.Desc"));
		model.setPageKeywords(this.getText("StockHighlights.Page.Key"));

		String symbol = model.getSymbol();
		if (StringUtils.isNotEmpty(symbol)) {
			String freeSymbolReport = Constants.IServerConfig.STOCK_HIGHLIGHTS_FREE_REPORTS;
			if(freeSymbolReport.contains(symbol.toUpperCase())){
				ifoNews = getNewsestReport(symbol);
			}
		}

		try {
			if (ifoNews != null) {
				model.setSearchIfoNews(ifoNews);

				// increase news hit
				newsInfoManager.updateNewsHit(ifoNews.getNewsId());
				
				model.setPageTitle(this.getText("StockHighlights.Page.Title") + " - " + ifoNews.getNewsHeader());
			}
		} catch (Exception e) {
			logger.error("StockHighlightsAction.viewReportDetail:" + e);
		}

		return SUCCESS;
	}

	private SearchIfoNews getNewsestReport(String symbol) {
		SearchIfoNews result = null;

		final SearchIfoNews searchObj = buildCommonSearchObj();
		if (StringUtils.isNotEmpty(model.getSymbol())) {
			searchObj.setListSymbols(new String[] { symbol.toUpperCase() });
		}

		try {
			// to get newest report only
			model.getPagingInfo().setOffset(1);

			// call service
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getStockHighlightsReports(searchObj,
					model.getPagingInfo(), false, true, Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			if (searchResult.size() > 0) {
				result = searchResult.get(0);
			}
		} catch (SystemException e) {
			logger.error(e);
		}

		return result;
	}

	private SearchIfoNews getReportByNewsId(String strNewsId) {
		SearchIfoNews result = null;

		if (StringUtils.isNotEmpty(strNewsId)) {
			try {
				Long newsId = Long.valueOf(strNewsId);
				result = stockHighlightsManager.getNews(newsId);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	/**
	 * Lấy báo cáo mới nhất theo công ty
	 */
	@Deprecated
	public String getNewestCompanyReportAjax() {

		String location = "StockHighlightsAction.getStockHighLightsPage()";
		logger.debug(location + " :: BEGIN");

		final SearchIfoNews searchObj = buildCommonSearchObj();
		if (StringUtils.isNotEmpty(model.getSymbol())) {
			searchObj.setListSymbols(new String[] { model.getSymbol().toUpperCase() });
		}

		try {
			// to get newest report only
			model.getPagingInfo().setOffset(1);

			// call service
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getStockHighlightsReports(searchObj,
					model.getPagingInfo(), false, true, Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			model.setSearchIfoNewsResult(searchResult != null ? searchResult : new SearchResult<SearchIfoNews>());
		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	/**
	 * Lấy danh sách toàn bộ báo cáo mới nhất
	 */
	public String getNewestReportsAjax() {

		String location = "StockHighlightsAction.getNewestReportsAjax()";
		logger.debug(location + " :: BEGIN");

		final SearchIfoNews searchObj = buildCommonSearchObj();

		try {
			model.getPagingInfo().setOffset(STOCK_HIGHLIGHT_DEFAULT_OFFSET);
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getStockHighlightsReports(searchObj,
					model.getPagingInfo(), false, false, Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			if (searchResult != null && searchResult.size() > 0) {
				model.setSearchIfoNewsResult(searchResult);
				model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	/**
	 * Lấy danh sách báo cáo xem nhiều.
	 * 
	 * Hiện tại chưa sử dụng.
	 */
	@Deprecated
	public String getMostViewReportsAjax() {

		String location = "StockHighlightsAction.getMostViewReportsAjax()";
		logger.debug(location + " :: BEGIN");

		final SearchIfoNews searchObj = buildCommonSearchObj();

		try {
			model.getPagingInfo().setOffset(STOCK_HIGHLIGHT_DEFAULT_OFFSET);
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getStockHighlightsReports(searchObj,
					model.getPagingInfo(), true, false, Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			if (searchResult != null && searchResult.size() > 0) {
				model.setSearchIfoNewsResult(searchResult);
				model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	/**
	 * Lấy danh sách báo cáo nổi bật.
	 * 
	 * Tạm thời không dùng.
	 */
	@Deprecated
	public String getOutstandingReportsAjax() {

		String location = "StockHighlightsAction.getOutstandingReportsAjax()";
		logger.debug(location + " :: BEGIN");

		final SearchIfoNews searchObj = buildCommonSearchObj();

		try {
			model.getPagingInfo().setOffset(STOCK_HIGHLIGHT_VIP_OFFSET);
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getOutstandingSymbols(searchObj,
					model.getPagingInfo(), Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			if (searchResult != null && searchResult.size() > 0) {
				model.setSearchIfoNewsResult(searchResult);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	@Deprecated
	public String increaseAttachmentsNewsHit() {

		String location = "StockHighlightsAction.increaseAttachmentNewsHit()";
		logger.debug(location + " :: BEGIN");

		try {
			newsInfoManager.updateNewsHit(Long.valueOf(model.getNewsId()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	public String getRelatedReportsAJAX() {

		String location = "StockHighlightsAction.getRelatedReports()";
		logger.debug(location + " :: BEGIN");

		if (StringUtils.isEmpty(model.getNewsId())) {
			return ERROR;
		}

		final SearchIfoNews searchObj = buildCommonSearchObj();
		searchObj.setNewsId(Long.valueOf(model.getNewsId()));

		try {
			model.getPagingInfo().setOffset(RELATED_REPORTS_OFFSET);
			// call service
			SearchResult<SearchIfoNews> searchResult = stockHighlightsManager.getRelatedReports(searchObj, model.getPagingInfo(),
					Constants.IServerConfig.STOCK_HIGHLIGHTS_SYSDATE_FROM);
			if (searchResult != null && searchResult.size() > 0) {
				model.setSearchIfoNewsResult(searchResult);
				model.setPagingInfo((PagingInfo) searchResult.getPagingInfo());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug(location + " :: END");

		return SUCCESS;
	}

	private SearchIfoNews buildCommonSearchObj() {
		SearchIfoNews searchObj = new SearchIfoNews();
		searchObj.setOrderByDate(true);
		searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
		searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		searchObj.setNewsType(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_STOCK_HIGHLIGHTS);

		return searchObj;
	}

	@Override
	public StockHighlightsModel getModel() {
		return model;
	}
}
