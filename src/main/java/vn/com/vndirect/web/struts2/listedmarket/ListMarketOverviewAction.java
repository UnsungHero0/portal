package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.struts2.listedmarket.ListMarketOverviewModel;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ListMarketOverviewAction extends ActionSupport implements ModelDriven<ListMarketOverviewModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(ListMarketOverviewAction.class);

	/* data model */
	private ListMarketOverviewModel model = new ListMarketOverviewModel();

	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public ListMarketOverviewModel getModel() {
		return model;
	}

	public String viewListedMarketPage() throws FunctionalException, SystemException {
		final String LOCATION = "viewListedMarketPage";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			// get information for LastestMarketNews Chart
			Map<String, Object> mapMarketSummary;

			mapMarketSummary = quotesManager.getMarketOverview(model.getMarketOption());

			MarketInfo marketInfo;

			// get HASTC Company Summary
			marketInfo = (MarketInfo) mapMarketSummary.get(Constants.MarketSummary.HASTC_MARKET_INFO);
			model.setHastcMarketInfo(marketInfo);

			// get HOSTC Company Summary
			marketInfo = (MarketInfo) mapMarketSummary.get(Constants.MarketSummary.HOSTC_MARKET_INFO);
			model.setHostcMarketInfo(marketInfo);

			// get VN30 Company Summary
			marketInfo = (MarketInfo) mapMarketSummary.get(Constants.MarketSummary.VN30_MARKET_INFO);
			if (marketInfo == null) {
				marketInfo = new MarketInfo();
			}
			model.setVn30MarketInfo(marketInfo);

			// get HNX30 Company Summary
			marketInfo = (MarketInfo) mapMarketSummary.get(Constants.MarketSummary.HNX30_MARKET_INFO);
			if (marketInfo == null) {
				marketInfo = new MarketInfo();
			}
			model.setHnx30MarketInfo(marketInfo);

			// get UPCOM Company Summary
			marketInfo = (MarketInfo) mapMarketSummary.get(Constants.MarketSummary.UPCOM_MARKET_INFO);
			model.setUpComMarketInfo(marketInfo);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		
		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();		
		breadcrumbs.add(this.getText("home.topMenu.analysis.marketOverview"));			
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.marketStatistics"));
		breadcrumbs.add("/thong-ke-thi-truong-chung-khoan/tong-quan.shtml");
		model.setBreadcrumbs(breadcrumbs);
		
		doSEOpage();
		
		return SUCCESS;
	}
	
	private void doSEOpage() {	    
		model.setPageTitle(this.getText("analysis.marketOverview.title"));
		model.setPageDescription(this.getText("analysis.marketOverview.description"));
		model.setPageKeywords(this.getText("analysis.marketOverview.keyWords"));
	}
}
