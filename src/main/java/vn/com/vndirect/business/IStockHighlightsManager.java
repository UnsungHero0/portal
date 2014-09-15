package vn.com.vndirect.business;

import java.util.ArrayList;

import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public interface IStockHighlightsManager extends IBaseManager {

	SearchResult<SearchIfoNews> getStockHighlightsReports(SearchIfoNews searchObj, PagingInfo pagingInfo, boolean isOrderByHit,
			boolean isGetSymbol, Integer sysdateFrom) throws SystemException;

	SearchResult<SearchIfoNews> getRelatedReports(SearchIfoNews searchObj, PagingInfo pagingInfo, Integer sysdateFrom)
			throws SystemException;

	SearchResult<SearchIfoNews> getOutstandingSymbols(SearchIfoNews searchObj, PagingInfo pagingInfo, Integer sysdateFrom)
			throws SystemException;

	ArrayList<String> getSymbolsListHaveReports(PagingInfo pagingInfo) throws SystemException;
	
	SearchIfoNews getNews(Long newsId) throws SystemException;
}