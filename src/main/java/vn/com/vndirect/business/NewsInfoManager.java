package vn.com.vndirect.business;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.dao.impl.IfoCategoryNewsDAO;
import vn.com.vndirect.dao.impl.IfoNewsDAO;
import vn.com.vndirect.dao.impl.NewsAttachmentsDAO;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.NewsAttachments;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.wsclient.newsreader.AddAttachmentsHitRequest;
import vn.com.vndirect.wsclient.newsreader.AddAttachmentsHitResult;
import vn.com.vndirect.wsclient.newsreader.AddNewsHitRequest;
import vn.com.vndirect.wsclient.newsreader.AddNewsHitResult;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class NewsInfoManager extends BaseManager implements INewsInfoManager {
	private static Logger logger = Logger.getLogger(NewsInfoManager.class);

	private static final int EXPIRATION_TIME_FOR_CACHE = 600;
	private static final String END_DEBUG_LOG_STR = ":: END";
	private static final String BEGIN_DEBUG_LOG_STR = ":: BEGIN";
	private final int limitWords = 15;
	@Autowired
	private IfoNewsDAO ifoNewsDAO;
	
	@Autowired
	private NewsAttachmentsDAO newsAttachmentsDAO;
	
	@Autowired
	private IfoCategoryNewsDAO ifoCategoryNewsDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchNewsIfo(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchNewsIfo@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchNewsIfo(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchNewsIfo(ifoNews:" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchNewsIfo(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}
	
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchNewsIfoHomePage@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchNewsIfoHomePage(
			@ParameterValueKeyProvider(order=0) PagingInfo pagingInfo, @ParameterValueKeyProvider(order=1) int dayExpand) throws SystemException {
		final String location = "searchNewsIfoHomePage";

		try {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchNewsIfoHomePage(pagingInfo, dayExpand);
				return result;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getMostViewedNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getMostViewedNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getMostViewedNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getMostViewedNews(SearchIfoNews ifoNews)::" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				SearchResult<SearchIfoNews> result = ifoNewsDAO.getMostViewedNews(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchNews(vn.com.vndirect.
	 * domain.IfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<IfoNews> searchNews(@ParameterValueKeyProvider(order = 0) final IfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchNews(ifoNews)";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<IfoNews> result = ifoNewsDAO.searchNews(ifoNews, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getCommentsMarketNews(vn.com
	 * .vndirect.domain.IfoNews)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getCommentsMarketNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public IfoNews getCommentsMarketNews(@ParameterValueKeyProvider final IfoNews ifoNews) throws SystemException {
		final String location = "getCommentsMarketNews(ifoNews:" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			final IfoNews result = ifoNewsDAO.getCommentsMarketNews(ifoNews);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchNewsIPO@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchNewsIPO(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchNewsIPO(SearchIfoNews ifoNews)::" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchNewsIfo(ifoNews, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchStockNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchStockNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchStockNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchStockNews(SearchIfoNews ifoNews)::" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchStockNews(ifoNews, pagingInfo);
			final Map<Long, String> newsTypeMap = ifoNewsDAO.getNewsTypeMap(result, ifoNews);
			for (SearchIfoNews searchIfoNews : result) {
				searchIfoNews.setNewsType(newsTypeMap.get(searchIfoNews.getNewsId()));
			}

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchExpertNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchExpertNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchExpertNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchExpertNews(SearchIfoNews ifoNews)::" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				SearchResult<SearchIfoNews> result = ifoNewsDAO.searchNewsIfo(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getIfoNewsById(vn.com.vndirect
	 * .domain.IfoNews)
	 */
	public IfoNews getIfoNewsById(final IfoNews ifoNews) throws SystemException {
		final String location = "getIfoNewsById(ifoNews:" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final IfoNews rs = ifoNewsDAO.findById(ifoNews.getNewsId(), ifoNews.getNewsType());
				final Collection listCategory = ifoCategoryNewsDAO.selectCategory(ifoNews.getNewsId());
				rs.setIfoCategoryNews(listCategory);
				final List listAttachments = newsAttachmentsDAO.getNewsAttachments(ifoNews);
				rs.setAttachmentNews(listAttachments);

				log(location + END_DEBUG_LOG_STR);

				return rs;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}

		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchMartketNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchMartketNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchMartketNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchMartketNews(SearchIfoNews:" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchMartketNews(ifoNews, pagingInfo);
				log(location + "searchMartketNews_result" + result.size());
				IfoNews tempNews;
				List<NewsAttachments> attachments;
				for (int i = 0; i < result.size(); i++) {
					tempNews = (IfoNews) result.get(i);
					attachments = newsAttachmentsDAO.getNewsAttachments(tempNews);
					tempNews.setAttachmentNews(attachments);
				}
				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#searchCenterAnalysisOfVNDirect
	 * (vn.com.vndirect.domain.extend.SearchIfoNews,
	 * vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.searchCenterAnalysisOfVNDirect@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> searchCenterAnalysisOfVNDirect(
			@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "searchCenterAnalysisOfVNDirect(SearchIfoNews:" + ifoNews + ")";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.searchCenterAnalysisOfVNDirect(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getAttachments(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getAttachments@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getAttachments(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getAttachments()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.getAttachments(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getMostViewedAttachments(vn
	 * .com.vndirect.domain.extend.SearchIfoNews,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getMostViewedAttachments@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getMostViewedAttachments(
			@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getMostViewedAttachments(SearchIfoNews ifoNews)::" + ifoNews + " , pagingInfo:: " + pagingInfo
				+ " )";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.getMostViewedAttachments(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}

		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getRelatedVideoNews(vn.com.
	 * vndirect.domain.extend.SearchIfoNews,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getRelatedVideoNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getRelatedVideoNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getRelatedVideoNews(SearchIfoNews ifoNews)::" + ifoNews + " , pagingInfo:: " + pagingInfo + " )";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			if (ifoNews != null) {
				final SearchResult<SearchIfoNews> result = ifoNewsDAO.getRelatedVideoNews(ifoNews, pagingInfo);

				log(location + END_DEBUG_LOG_STR);

				return result;
			} else {
				throw new SystemException(location, "ifoNews object is NULL");
			}

		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getMarketNews(java.lang.String,
	 * java.util.Date, java.lang.String, java.lang.String,
	 * vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getMarketNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<IfoNews> getMarketNews(@ParameterValueKeyProvider(order = 0) final String newsType,
			@ParameterValueKeyProvider(order = 1) final Date date, @ParameterValueKeyProvider(order = 2) final String locale,
			@ParameterValueKeyProvider(order = 3) final String status,
			@ParameterValueKeyProvider(order = 4) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getMarketNews(newsType ::" + newsType + " , date:: " + date + " , locale:: " + locale
				+ " , status:: " + status + " , pagingInfo:: " + pagingInfo + " )";
		log(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<IfoNews> result = ifoNewsDAO.getMarketNews(newsType, date, locale, status, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getSectorNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.web.commons.domain.db.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getSectorNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getSectorNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getSectorNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getNewsBySector(ifoNews, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getIndustryNews(vn.com.vndirect
	 * .domain.extend.SearchIfoNews, vn.com.web.commons.domain.db.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getIndustryNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getIndustryNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews ifoNews,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getIndustryNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getNewsByIndustry(ifoNews, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "NewsInfoManager.getReportAnalysis@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getReportAnalysis(@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo) throws SystemException {
		
		return getReportAnalysisWithOrderByHit(searchObj, pagingInfo, false);
	}
	
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getReportAnalysisWithOrderByCondition@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getReportAnalysisWithOrderByHit(@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo, @ParameterValueKeyProvider(order = 2) final Boolean isOrderByHit) throws SystemException {
		final String location = "getReportAnalysisWithOrderByCondition()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getReportAnalysisWithOrderByCondition(searchObj, pagingInfo, isOrderByHit);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	public SearchIfoNews getLatestMartketNews(SearchIfoNews searchObj) throws SystemException {
		final String location = "getLatestMartketNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			SearchIfoNews result = ifoNewsDAO.getLatestMartketNews(searchObj);
			if (result != null && result.getNewsId() != null) {
				List<NewsAttachments> att = newsAttachmentsDAO.getNewsAttachments(result);
				result.setAttachmentNews(att);
			}
			log(location + END_DEBUG_LOG_STR);
			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	public SearchResult<SearchIfoNews> getMartketNews(final SearchIfoNews searchObj, final Date date, final PagingInfo pagingInfo)
			throws SystemException {
		final String location = "getMartketNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getMarketNews(searchObj, date, pagingInfo);
			Map<Long, List<NewsAttachments>> attachmentMap = null;
			if (!result.isEmpty()) {
				attachmentMap = newsAttachmentsDAO.getAttachmentsMap(result);
			}
			if (attachmentMap != null && !attachmentMap.isEmpty()) {
				for (SearchIfoNews searchIfoNews : result) {
					if (attachmentMap.get(searchIfoNews.getNewsId()) != null) {
						searchIfoNews.setAttachmentNews(attachmentMap.get(searchIfoNews.getNewsId()));
					}
				}
			}
			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "NewsInfoManager.getAttachmentById@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchIfoNews getAttachmentById(@ParameterValueKeyProvider(order = 0) final Long id) throws SystemException {
		final String location = "getAttachmentById()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchIfoNews result = ifoNewsDAO.getAttachmentById(id);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#updateNewsHit(java.lang.Long)
	 */
	public void updateNewsHit(final Long newsId) throws Exception {
		final String location = "updateNewsHit()";
		log(location + BEGIN_DEBUG_LOG_STR);

		if (newsId == null || newsId.longValue() == 0) {
			throw new SystemException(location, " newsId is NULL or equal zero...");
		}

		final AddNewsHitRequest request = new AddNewsHitRequest();
		request.setNewsId(newsId);
		final AddNewsHitResult result = getINewsReaderServicePortType().increaseNewsHit(getVndsAuthenticationHeader(), request);
		VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

		log(location + END_DEBUG_LOG_STR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#updateAttachmentsHit(java.lang
	 * .Long)
	 */
	public void updateAttachmentsHit(final Long newsId) throws Exception {
		final String location = "updateAttachmentsHit()";
		log(location + BEGIN_DEBUG_LOG_STR);

		if (newsId == null || newsId.longValue() == 0) {
			throw new SystemException(location, " newsId is NULL or equal zero...");
		}

		final AddAttachmentsHitRequest request = new AddAttachmentsHitRequest();
		request.setAttachmentId(newsId);
		final AddAttachmentsHitResult result = getINewsReaderServicePortType().increaseAttachmentsHit(
				getVndsAuthenticationHeader(), request);
		VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

		log(location + END_DEBUG_LOG_STR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.INewsInfoManager#getOtherRelateNews(java.lang
	 * .Long, java.lang.String, java.lang.String, java.lang.String,
	 * vn.com.vndirect.domain.extend.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "NewsInfoManager.getOtherRelateNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getOtherRelateNews(@ParameterValueKeyProvider(order = 0) final Long newsId,
			@ParameterValueKeyProvider(order = 1) final String newsType,
			@ParameterValueKeyProvider(order = 2) final String status, 
			@ParameterValueKeyProvider(order = 3) final String locale,
			@ParameterValueKeyProvider(order = 4) final PagingInfo pagingInfo,
			@ParameterValueKeyProvider(order = 5) final String symbol) throws Exception {
		final String location = "getOtherRelateNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO
					.getOtherRelateNews(newsId, newsType, status, locale, pagingInfo, symbol);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "NewsInfoManager.getOtherRelateInDayNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getOtherRelateInDayNews(
			@ParameterValueKeyProvider(order = 0) final Long relateNewsId,
			@ParameterValueKeyProvider(order = 1) final String newsType,
			@ParameterValueKeyProvider(order = 2) final String status, 
			@ParameterValueKeyProvider(order = 3) final String locale,
			@ParameterValueKeyProvider(order = 4) final PagingInfo pagingInfo,
			@ParameterValueKeyProvider(order = 5) final String symbol
			) throws Exception {
		final String location = "getOtherRelateInDayNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getOtherNewerRelateNews(relateNewsId, newsType, status, locale,
					pagingInfo, symbol);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "NewsInfoManager.getDailyReportNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getDailyReportNews(@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final Date date,
			@ParameterValueKeyProvider(order = 2) final PagingInfo pagingInfo) throws SystemException {
		final String location = "getDailyReportNews()";
		log(location + BEGIN_DEBUG_LOG_STR);
		try {
			final SearchResult<SearchIfoNews> result = ifoNewsDAO.getMarketNews(searchObj, date, pagingInfo);

			log(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	public IfoCategoryNewsDAO getIfoCategoryNewsDAO() {
		return ifoCategoryNewsDAO;
	}

	public void setIfoCategoryNewsDAO(IfoCategoryNewsDAO ifoCategoryNewsDAO) {
		this.ifoCategoryNewsDAO = ifoCategoryNewsDAO;
	}

	public NewsAttachmentsDAO getNewsAttachmentsDAO() {
		return newsAttachmentsDAO;
	}

	public void setNewsAttachmentsDAO(NewsAttachmentsDAO newsAttachmentsDAO) {
		this.newsAttachmentsDAO = newsAttachmentsDAO;
	}

	public IfoNewsDAO getIfoNewsDAO() {
		return ifoNewsDAO;
	}

	public void setIfoNewsDAO(IfoNewsDAO ifoNewsDAO) {
		this.ifoNewsDAO = ifoNewsDAO;
	}

	private void log(String str) {
		if (logger.isDebugEnabled()) {
			logger.debug(str);
		}
	}

	@Override
	public void processNewsForView(SearchResult<SearchIfoNews> result) {
		boolean isSplited = false;
		for (int i = 0; i < result.size(); i++) {
			//remove news if header or type of news is empty or null
			if(StringUtils.isNotEmpty(result.get(i).getNewsHeader()) && StringUtils.isNotEmpty(result.get(i).getNewsType())){
				result.get(i).setUrlDetail(
					NewsUrlUtility.createUrl(result.get(i).getNewsType(), result.get(i).getNewsHeader(), result.get(i).getNewsId(), ""));
				// Split title of news containts 15 words
				String titleNews = result.get(i).getNewsHeader();
				String[] itemTitle = titleNews.split(" ");
				StringBuilder splitTitle = new StringBuilder();
				for (int j = 0; j < itemTitle.length; j++) {
					// Get title contains 15 words.
					if (j > limitWords) {
						isSplited = true;
						break;
					}
					splitTitle.append(itemTitle[j] + " ");
				}
				if (isSplited) {
					splitTitle.append("...");
				}
				result.get(i).setNewsHeader(splitTitle.toString());
			}
			else {
				result.remove(i);
			}
		}

	}
	
	@Override
	public void createNewsUrl(SearchResult<SearchIfoNews> ifoNewsResult){
		for (int i = 0; i < ifoNewsResult.size(); i++) {
			if(StringUtils.isEmpty(ifoNewsResult.get(i).getNewsHeader()) || StringUtils.isEmpty(ifoNewsResult.get(i).getNewsType())) {
				ifoNewsResult.remove(i);
			} else {
				ifoNewsResult.get(i).setUrlDetail(
					NewsUrlUtility.createUrl(ifoNewsResult.get(i).getNewsType(), ifoNewsResult.get(i).getNewsHeader(), ifoNewsResult.get(i).getNewsId(), ""));
			}
		}
	}

	@Override
	public void createNewsUrlWithPageUrl(SearchResult<SearchIfoNews> ifoNewsResult, String pageUrl) {
		for (int i = 0; i < ifoNewsResult.size(); i++) {
			if(StringUtils.isEmpty(ifoNewsResult.get(i).getNewsHeader()) || StringUtils.isEmpty(ifoNewsResult.get(i).getNewsType())) {
				ifoNewsResult.remove(i);
			} else {
				ifoNewsResult.get(i).setUrlDetail(
					NewsUrlUtility.createUrl(pageUrl, ifoNewsResult.get(i).getNewsType(), ifoNewsResult.get(i).getNewsHeader(), ifoNewsResult.get(i).getNewsId(), ""));
			}
		}
		
	}
	
	
}