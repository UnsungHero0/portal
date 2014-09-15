/**
 * 
 */
package vn.com.vndirect.business;

import java.util.Date;

import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Duc Nguyen
 * 
 */
public interface INewsInfoManager extends IBaseManager {

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> searchNewsIfo(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws FunctionalException,
			SystemException;
	/**
	 * 
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> searchNewsIfoHomePage(PagingInfo pagingInfo, int dayExpand) throws SystemException;
	
	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> getMostViewedNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<IfoNews> searchNews(IfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	IfoNews getCommentsMarketNews(IfoNews ifoNews) throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> searchNewsIPO(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> searchStockNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> searchExpertNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> searchMartketNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> searchCenterAnalysisOfVNDirect(SearchIfoNews ifoNews, PagingInfo pagingInfo)
			throws SystemException;

	/**
	 * 
	 * @param ifoNews
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public IfoNews getIfoNewsById(IfoNews ifoNews) throws SystemException;

	/**
	 * Get all the reports that its new_type = VNDS_STRATEGIC || VNDS_COMPANY ||
	 * VNDS_QUARTER || VNDS_RISKALERT || VNDS_QUICKREPORT || VNDS_STASTIC
	 * 
	 * @param locale
	 *            the locale (vn, jp, en)
	 * @param status
	 *            the status field
	 * @param pagingInfo
	 *            the object which support paging
	 * @return The result
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getAttachments(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * Get most viewed reports that its new_type = VNDS_STRATEGIC ||
	 * VNDS_COMPANY || VNDS_QUARTER || VNDS_RISKALERT || VNDS_QUICKREPORT ||
	 * VNDS_STASTIC
	 * 
	 * @param locale
	 *            the locale (vn, jp, en)
	 * @param status
	 *            the status field
	 * @param pagingInfo
	 *            the object which support paging
	 * @return The result
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getMostViewedAttachments(SearchIfoNews ifoNews, PagingInfo pagingInfo)
			throws SystemException;

	/**
	 * Get related video news
	 * 
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getRelatedVideoNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws SystemException;

	/**
	 * Get all the reports that its new_type and date
	 * 
	 * @param newsType
	 *            the type of the news
	 * @param date
	 *            the date
	 * @param locale
	 *            the locale (vn, jp, en)
	 * @param status
	 *            the status field
	 * @param pagingInfo
	 *            the object which support paging
	 * @return The result
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoNews> getMarketNews(String newsType, Date date, String locale, String status, PagingInfo pagingInfo)
			throws SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getSectorNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws FunctionalException,
			SystemException;

	/**
	 * @param ifoNews
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<SearchIfoNews> getIndustryNews(SearchIfoNews ifoNews, PagingInfo pagingInfo) throws FunctionalException,
			SystemException;

	/**
	 * Get the report of all the company for analysis
	 * 
	 * @param searchObj
	 *            search object
	 * @param pagingInfo
	 *            paging object
	 * @return list of the report
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> getReportAnalysis(SearchIfoNews searchObj, PagingInfo pagingInfo) throws SystemException;
	
	/**
	 * @param searchObj
	 * @param pagingInfo
	 * @param isOrderByHit
	 * @return
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> getReportAnalysisWithOrderByHit(SearchIfoNews searchObj, PagingInfo pagingInfo, Boolean isOrderByHit) throws SystemException;
	
	/**
	 * Get all the news included its attachments
	 * 
	 * @param searchObj
	 *            search object
	 * @param pagingInfo
	 *            paging object
	 * @param date
	 * @return list of the report
	 * @throws SystemException
	 */
	SearchResult<SearchIfoNews> getMartketNews(SearchIfoNews searchObj, Date date, PagingInfo pagingInfo) throws SystemException;

	SearchResult<SearchIfoNews> getDailyReportNews(SearchIfoNews searchObj, Date date, PagingInfo pagingInfo)
			throws SystemException;

	/**
	 * Get a latest news
	 * 
	 * @param searchObj
	 *            search object
	 * @return a news
	 * @throws SystemException
	 */
	SearchIfoNews getLatestMartketNews(SearchIfoNews searchObj) throws SystemException;

	/**
	 * Get an attachment object by its id
	 * 
	 * @param searchObj
	 * @return
	 * @throws SystemException
	 */
	public SearchIfoNews getAttachmentById(Long id) throws SystemException;

	/**
	 * @param id
	 * @throws Exception
	 */
	public void updateNewsHit(Long id) throws Exception;

	/**
	 * Update hit field in ifo_attachments table
	 * 
	 * @param id
	 * @throws SystemException
	 */
	public void updateAttachmentsHit(Long id) throws Exception;

	SearchResult<SearchIfoNews> getOtherRelateNews(Long newsId, String newsType, String status, String locale,
			PagingInfo pagingInfo, String symbol) throws Exception;

	SearchResult<SearchIfoNews> getOtherRelateInDayNews(Long relateNewsId, String newsType, String status, String locale,
			PagingInfo pagingInfo, String symbol) throws Exception;
	void processNewsForView(SearchResult<SearchIfoNews> ifoNews);
	
	void createNewsUrl(SearchResult<SearchIfoNews> ifoNewsResult);
	
	void createNewsUrlWithPageUrl(SearchResult<SearchIfoNews> ifoNewsResult, String pageUrl);
}