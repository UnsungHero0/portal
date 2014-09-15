/**
 * 
 */
package vn.com.vndirect.business;

import java.util.List;
import java.util.Map;

import vn.com.vndirect.commons.Code;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoPowerRatingBean;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.StockScreenerBean;
import vn.com.vndirect.domain.StockWizardBean;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.wsclient.AuthenticationHeader;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.vndirect.wsclient.tradingideas.GetSaveSearchResult;
import vn.com.vndirect.wsclient.tradingideas.SaveSearch;
import vn.com.vndirect.wsclient.tradingideas.SearchSaveSearchResult;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Duc Nguyen
 * 
 */
public interface IAnalysisToolsManager extends IBaseManager {
	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return SearchResult contains list of IfoSectorCalcView object
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoSectorCalcView> getListSectors(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoSectorCalcView getIfoSectorCalcViewByCode(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return SearchResult contains list of IfoIndustryCalcView object
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getListIndustries(IfoIndustryCalcView ifoIndustryCalcView)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoCompanyCalcView
	 * @return SearchResult contains list of IfoCompanyCalcView object
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListCompanies(IfoCompanyCalcView ifoCompanyCalcView) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoIndustryCalcView getIfoIndustryCalcViewByCode(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param searchObj
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getIfoCompanyCalcViewBySymbol(IfoCompanyCalcView searchObj) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return SearchResult contains list of IfoIndustryCalcView object
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getSectorIndustries(IfoIndustryCalcView ifoIndustryCalcView)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param ifoSectorCalcView
	 * @return List of String objects
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoSectorCalcView> getListSectorsName(IfoSectorCalcView searchObj) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param saveSearch
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public GetSaveSearchResult getSaveSearch(AuthenticationHeader header, SaveSearch saveSearch) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param ifoIndustryCalcView
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<IfoIndustryCalcView> getListIndustryName(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @param pagingInfo
	 * @return SearchResult contains list of StockScreenerBean objects
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<StockScreenerBean> search(SearchStockScreenerBean searchStockScreenerBean, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param onlineUserId
	 * @param searchStockScreenerBean
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public void createOrUpdateSaveSearch(AuthenticationHeader header, Long onlineUserId,
	        SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param saveSearch
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchSaveSearchResult searchSaveSearch(AuthenticationHeader header, SaveSearch saveSearch, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param header
	 * @param saveSearch
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public void deleteSaveSearch(AuthenticationHeader header, SaveSearch saveSearch) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param symbol
	 * @param locale
	 * @param isCompare
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public StockWizardBean[] getStockWizardBean(String[] symbols, String locale, boolean isCompare) throws FunctionalException,
	        SystemException;

	/**
	 * Get the limited list of industries
	 * 
	 * @param limit
	 *            number of industries to get
	 * @param locale
	 *            language code
	 * @return list of industries
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoIndustryCalcView> getListOfNamedIndustries(int limit, String locale) throws FunctionalException,
	        SystemException;

	/**
	 * Get all the company by sector code
	 * 
	 * @param company
	 * @param pagingInfo
	 * @return list of companies
	 */
	public SearchResult<IfoCompanyCalcView> getCompaniesBySectorCode(IfoCompanyCalcView company, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * Get all of sector for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of the list sector
	 */
	public List<Map<String, IfoSectorCalcView>> getListOfSector(String locale) throws SystemException;

	/**
	 * Get a list of hot sectors
	 * 
	 * @param limit
	 *            number of sectors
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of sectors
	 * @throws SystemException
	 */
	public <T extends IfoSectorCalcView> List<T> getHotSectors(int limit, String locale) throws SystemException;

	/**
	 * Get sectors which are high performance in three month
	 * 
	 * @param limit
	 *            number of sectors
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of sectors
	 * @throws SystemException
	 */
	public List<IfoSectorCalcView> getThrMonHighPerfSectors(int limit, String locale) throws SystemException;

	/**
	 * Get a list of hot industries
	 * 
	 * @param limit
	 *            number of industries
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of industries
	 * @throws SystemException
	 */
	public <T extends IfoIndustryCalcView> List<T> getHotIndustries(int limit, String locale) throws SystemException;

	/**
	 * Get industries which are high performance in three month
	 * 
	 * @param limit
	 *            number of industries
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of industries
	 * @throws SystemException
	 */
	public List<IfoIndustryCalcView> getThrMonHighPerfIndustries(int limit, String locale) throws SystemException;

	/**
	 * Get all of industry for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @param sectorCode
	 *            Sector code
	 * @return list of the list sector
	 */
	public List<Map<String, IfoIndustryCalcView>> getListOfIndustry(String locale, String sectorCode) throws SystemException;

	/**
	 * Get all the companies from web service
	 * 
	 * @param code
	 *            the code
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param limit
	 * @return list of companies
	 * @throws Exception
	 */
	SecInfo[] getListOfCompaniesWS(String code, Code type, int limit) throws Exception;

	/**
	 * Get all the companies from web service
	 * 
	 * @param code
	 *            the code
	 * @return list of companies
	 * @throws Exception
	 */
	SecInfo[] getListOfCompaniesWS(String[] symbols) throws Exception;

	/**
	 * Get list of companies by code from embed database
	 * 
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param code
	 *            the code
	 * @return list of companies
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListOfCompaniesEDB(String code, Code type, PagingInfo pagingInfo, String field,
	        String order) throws SystemException;

	/**
	 * Get top industry(The hottest industry that has the highest price after 5
	 * days) by sector code from database
	 * 
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param code
	 *            the sector code
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @return list of companies
	 * @throws SystemException
	 */
	public IfoIndustryCalcView getTopIndustry(String code, String locale) throws SystemException;

	/**
	 * Get top company(The hottest company that has the highest price after 5
	 * days) by industry code from embed database
	 * 
	 * @param type
	 *            <code>vn.com.vndirect.commons.Code</code>
	 * @param code
	 *            the sector code
	 * @return list of companies
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getTopCompany(String code) throws SystemException;

	/**
	 * Get a sector for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @param sectorCode
	 * @return map of a sector that contains item code(1000017, 1000028,
	 *         1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public Map<String, IfoSectorCalcView> getSector(String locale, String sectorCode) throws SystemException;

	/**
	 * Get a industry for overview
	 * 
	 * @param locale
	 *            [VN, EN_GB, jp]
	 * @param industryCode
	 * @return map of a industry that contains item code(1000017, 1000028,
	 *         1000029, 1000030, 1000031, 1000032) as a key
	 * @throws SystemException
	 */
	public Map<String, IfoIndustryCalcView> getIndustry(String locale, String industryCode) throws SystemException;

	/**
	 * Get industry_code and sector_code of a company by its symbol
	 * 
	 * @param symbol
	 *            symbol of a company
	 * @return a Company object if data found otherwise <code>null</code>
	 * @throws SystemException
	 */
	public IfoCompanyCalcView getCompanyEDB(String symbol) throws SystemException;

	/**
	 * Get top ten company having highest PR up to now
	 * 
	 * @param
	 * @return a list of PowerRatingBean
	 * @throws SystemException
	 */
	/*
	 * public List<IfoPowerRatingBean> getListOfTopTenPr() throws
	 * SystemException, FunctionalException;
	 * 
	 * public List<IfoPowerRatingBean> getListOfBottomTenPr() throws
	 * SystemException, FunctionalException;
	 * 
	 * public List<IfoPowerRatingBean> getListOfUpgradedTopTenPr() throws
	 * SystemException, FunctionalException;
	 * 
	 * public List<IfoPowerRatingBean> getListOfDowngradedTenPr() throws
	 * SystemException, FunctionalException;
	 * 
	 * public List<IfoPowerRatingBean> getListOfGeneralMarketPr()throws
	 * SystemException, FunctionalException;
	 * 
	 * public int getRatedCodeNumber()throws SystemException,
	 * FunctionalException;
	 * 
	 * public double getAvgGeneralMarketPr()throws SystemException,
	 * FunctionalException;
	 * 
	 * public List<IfoPowerRatingBean> getListOfFourDayPr() throws
	 * SystemException, FunctionalException;
	 */

	public List<IfoPowerRatingBean> getListOfPrs() throws SystemException;
}