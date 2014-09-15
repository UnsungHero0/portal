package vn.com.vndirect.web.struts2.portlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.struts2.portlet.MarketOverviewAJAXModel;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MarketOverviewAJAXAction extends ActionSupport implements ModelDriven<MarketOverviewAJAXModel> {

	/* class logger */
	private static Logger logger = Logger.getLogger(MarketOverviewAJAXAction.class);

	/* data model */
	private MarketOverviewAJAXModel model = new MarketOverviewAJAXModel();

	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public MarketOverviewAJAXModel getModel() {
		return model;
	}

	private String hastcIndex = VNDirectWebUtilities.getHASTCIndex();
	private String hostcIndex = VNDirectWebUtilities.getHOSTCIndex();
	private String vn30Index = VNDirectWebUtilities.getVN30Index();
	private String hnx30Index = VNDirectWebUtilities.getHNX30Index();
	private String upComIndex = VNDirectWebUtilities.getUPCOMIndex();

	private String[] hastcFloors = VNDirectWebUtilities.getHASTCFloorCode();
	private String[] hostcFloors = VNDirectWebUtilities.getHOSTCFloorCode();
	private String[] vn30Floors = VNDirectWebUtilities.getVN30FloorCode();
	private String[] hnx30Floors = VNDirectWebUtilities.getHNX30FloorCode();
	private String[] upComFloors = VNDirectWebUtilities.getUPCOMFloorCode();

	/**
	 * Getting the news base on the criteria from model.
	 */
	public String executeMarketOverviewHome() throws FunctionalException, SystemException {
		final String LOCATION = "executeMarketOverviewHome";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			// +++ Get intraday value
			List<String> listSymbolSearch = new ArrayList<String>();
			listSymbolSearch.add(hastcIndex);
			listSymbolSearch.add(hostcIndex);
			listSymbolSearch.add(vn30Index);
			listSymbolSearch.add(hnx30Index);
			listSymbolSearch.add(upComIndex);

			Arrays.sort(hastcFloors);
			Arrays.sort(hostcFloors);
			Arrays.sort(vn30Floors);
			Arrays.sort(hnx30Floors);
			Arrays.sort(upComFloors);

			IntradayPriceSearch intradayPriceSearch = new IntradayPriceSearch();

			intradayPriceSearch.setListSymbols(listSymbolSearch.toArray(new String[listSymbolSearch.size()]));
			// String symbol;
			// for(int i = 0; i < size; i++) {
			// symbol = intradayPriceSearch.getListSymbols()[i];
			// }

			IntradayPriceSearchResult searchIntradayPriceResult = quotesManager.searchIntradayPrice(intradayPriceSearch);
			if (searchIntradayPriceResult != null && searchIntradayPriceResult.getListMarketInfo() != null) {
				MarketInfo[] marketInfos = searchIntradayPriceResult.getListMarketInfo();

				for (MarketInfo marketInfo : marketInfos) {
					if (Arrays.binarySearch(hastcFloors, marketInfo.getFloorCode()) > -1) {
						// Add for HASTC
						model.setHastcMarket(marketInfo);
					} else if (Arrays.binarySearch(hostcFloors, marketInfo.getFloorCode()) > -1) {
						// Add for HOSTC
						model.setHostcMarket(marketInfo);

					} else if (Arrays.binarySearch(vn30Floors, marketInfo.getFloorCode()) > -1) {
						// Add for VN30
						model.setVn30Market(marketInfo);

					} else if (Arrays.binarySearch(hnx30Floors, marketInfo.getFloorCode()) > -1) {
						// Add for HNX30
						model.setHnx30Market(marketInfo);

					} else if (Arrays.binarySearch(upComFloors, marketInfo.getFloorCode()) > -1) {
						model.setUpComMarket(marketInfo);
					}
				} // for (MarketInfo marketInfo : marketInfos)
			}

			// +++ add current date
			String currentDate = VNDirectDateUtils.dateToString(VNDirectDateUtils.getCurrentDate(),
			        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT);
			model.setCurrentDate(currentDate);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

	/**
	 * Getting the news base on the criteria from model.
	 */
	@Deprecated
	public String executeGetWorldMarketOverviewHome() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetWorldMarketOverviewHome";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}
		// +++ add current date
		String currentDate = VNDirectDateUtils.dateToString(VNDirectDateUtils.getCurrentDate(),
		        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT);
		model.setCurrentDate(currentDate);
		logger.debug(LOCATION + ":: END");
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		return SUCCESS;
	}

}
