package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.dao.impl.IStockHighlightsDAO;
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
public class StockHighlightsManager extends BaseManager implements IStockHighlightsManager {
	private static Logger logger = Logger.getLogger(StockHighlightsManager.class);

	private static final int EXPIRATION_TIME_FOR_CACHE = 600;
	private static final String END_DEBUG_LOG_STR = ":: END";
	private static final String BEGIN_DEBUG_LOG_STR = ":: BEGIN";

	@Autowired
	private IStockHighlightsDAO stockHighlightsDAO;

	@ReadThroughSingleCache(namespace = "StockHighlightsManager.getStockHighlightsReports@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getStockHighlightsReports(
			@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo,
			@ParameterValueKeyProvider(order = 2) final boolean isOrderByHit,
			@ParameterValueKeyProvider(order = 3) final boolean isGetSymbol,
			@ParameterValueKeyProvider(order = 4) final Integer sysdateFrom) throws SystemException {

		final String location = "StockHighlightsManager.getStockHighlightsReports()";
		logger.debug(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<SearchIfoNews> result = stockHighlightsDAO.getReports(searchObj, pagingInfo, isOrderByHit,
					isGetSymbol, sysdateFrom);

			logger.debug(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	public void setStockHighlightsDAO(IStockHighlightsDAO stockHighlightsDAO) {
		this.stockHighlightsDAO = stockHighlightsDAO;
	}

	@ReadThroughSingleCache(namespace = "StockHighlightsManager.getRelatedReports@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<SearchIfoNews> getRelatedReports(@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo,
			@ParameterValueKeyProvider(order = 2) final Integer sysdateFrom) throws SystemException {

		final String location = "StockHighlightsManager.getRelatedReports()";
		logger.debug(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<SearchIfoNews> result = stockHighlightsDAO.getRelatedReports(searchObj, pagingInfo, sysdateFrom);

			logger.debug(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	// @ReadThroughSingleCache(namespace =
	// "StockHighlightsManager.getOutstandingSymbols@", expiration =
	// EXPIRATION_TIME_FOR_CACHE)
	@Deprecated
	public SearchResult<SearchIfoNews> getOutstandingSymbols(@ParameterValueKeyProvider(order = 0) final SearchIfoNews searchObj,
			@ParameterValueKeyProvider(order = 1) final PagingInfo pagingInfo,
			@ParameterValueKeyProvider(order = 2) final Integer sysdateFrom) throws SystemException {

		final String location = "StockHighlightsManager.getOutstandingSymbols()";
		logger.debug(location + BEGIN_DEBUG_LOG_STR);

		try {
			final SearchResult<SearchIfoNews> result = stockHighlightsDAO.getOutstandingSymbols(searchObj, pagingInfo,
					sysdateFrom);

			logger.debug(location + END_DEBUG_LOG_STR);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "StockHighlightsManager.getSymbolsListHaveReports@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public ArrayList<String> getSymbolsListHaveReports(@ParameterValueKeyProvider(order = 0) final PagingInfo pagingInfo)
			throws SystemException {
		final String location = "StockHighlightsManager.getSymbolsListHaveReports(pagingInfo:" + pagingInfo + ")";

		try {
			final SearchResult<String> searchResult = stockHighlightsDAO.getSymbolsListHaveReports(pagingInfo);

			ArrayList<String> result = new ArrayList<String>();
			for (String s : searchResult) {
				result.add(s);
			}

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}

	@ReadThroughSingleCache(namespace = "StockHighlightsManager.getNews@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchIfoNews getNews(@ParameterValueKeyProvider(order = 0) final Long newsId) throws SystemException {
		final String location = "StockHighlightsManager.getNews(newsId:" + newsId + ")";

		try {
			final SearchIfoNews result = stockHighlightsDAO.getNews(newsId);

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}
}