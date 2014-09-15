package vn.com.vndirect.business;

import java.util.List;
import java.util.Map;

import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.vndirect.domain.IfoCompany;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoEstimateView;
import vn.com.vndirect.domain.IfoExchangeCode;
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoSectorCode;
import vn.com.vndirect.domain.IfoSectorCodeId;
import vn.com.vndirect.domain.extend.CompanySummary;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.IfoCompanySnapshotViewExt;
import vn.com.vndirect.domain.extend.IfoIndexCalc;
import vn.com.vndirect.domain.extend.MarketOption;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.extend.SearchSymbol;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.vndirect.wsclient.AuthenticationHeader;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@SuppressWarnings("rawtypes")
public interface IQuotesManager extends IBaseManager {

	/**
	 * 
	 * @param header
	 * @param intradayPriceSearch
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IntradayPriceSearchResult searchIntradayPrice(IntradayPriceSearch intradayPriceSearch) throws FunctionalException,
	        SystemException;

	/**
	 * @param header
	 * @param criteriaObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SecuritiesInfoForQuote getCompanySnapshotHighLights(AuthenticationHeader header, CurrentCompanyForQuote criteriaObject)
	        throws FunctionalException, SystemException;

	/**
	 * @param header
	 * @param criteriaObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SecuritiesInfoForQuote getCompanySnapshotHighLightsForChart(AuthenticationHeader header,
	        CurrentCompanyForQuote criteriaObject) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param criteriaObject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoCompanySnapshotViewExt getCompanySnapshotInfo(CurrentCompanyForQuote criteriaObject) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public CurrentCompanyForQuote getCurrentCompanyForQuote(IfoCompanyNameViewId ifoCompanyNameViewId)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoCompany
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Map getCompanyProfile(IfoCompany ifoCompany) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoCompanyNameViewId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Map getKeyStatic(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoEstimateView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoEstimateView getIfoEstimateView(IfoEstimateView ifoEstimateView) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param intradayPriceSearch
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IntradayPriceSearchResult searchIntradayPrice(AuthenticationHeader header, IntradayPriceSearch intradayPriceSearch)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoCompanyNameViewId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoBreakdownViewId getCompanyBreakdown(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoCompanyNameViewId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public Map<String, Object> getMajorHolders(IfoCompanyNameViewId ifoCompanyNameViewId) throws FunctionalException,
	        SystemException;

	/**
	 * @param symbol
	 * @param locale
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public CurrentCompanyForQuote quickSearchQuotes(String symbol, String locale) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoInsiderTransactionViewId
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoInsiderTransactionView> getInsiderTransactions(IfoCompanyNameViewId ifoCompanyNameViewId)
	        throws FunctionalException, SystemException;

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoExchangeCode> getAllIfoExchange() throws FunctionalException, SystemException;

	/**
	 * @param searchObj
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoSectorCode> getAllIfoSectorCode(IfoSectorCodeId searchObj) throws FunctionalException, SystemException;

	/**
	 * @param searchObj
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult searchSymbol(SearchSymbol searchObj, PagingInfo pagingInfo) throws FunctionalException, SystemException;

	/**
	 * @param marketOption
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	public Map<String, List<CompanySummary>> getMostActiveCompany(MarketOption marketOption) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param marketOption
	 * @return The flowing object - MarketInfo object of HASTC with key
	 *         Constants.MarketSummary.HASTC_MARKET_INFO - List of
	 *         CompanySummarry object of HASTC with key
	 *         Constants.MarketSummary.LIST_HASTC_COMPANY_SUMMARY - MarketInfo
	 *         object of HOSTC with key
	 *         Constants.MarketSummary.HOSTC_MARKET_INFO - List of
	 *         CompanySummarry object of HOSTC with key
	 *         Constants.MarketSummary.LIST_HOSTC_COMPANY_SUMMARY - MarketInfo
	 *         object of OTC with key Constants.MarketSummary.OTC_MARKET_INFO -
	 *         List of CompanySummarry object of OTC with key
	 *         Constants.MarketSummary.LIST_OTC_COMPANY_SUMMARY
	 * @throws SystemException
	 */
	public Map<String, Object> getMarketOverview(MarketOption marketOption) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param symbol
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	//public CurrentCompanyForQuote quickSearchQuotes(String symbol, String locale) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObj
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 * @throws FunctionalException
	 */
	SearchResult<IfoSecPriceView> searchStockPrices(SearchIfoSecPrice searchObj, PagingInfo pagingInfo) throws SystemException,
	        FunctionalException;

	/**
	 * 
	 * @param ifoIndexCalc
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndexCalc> searchIndexOfSectorAndIndustry(IfoIndexCalc ifoIndexCalc) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoEstimateView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List fiscalYearList(IfoEstimateView ifoEstimateView) throws FunctionalException, SystemException;
}
