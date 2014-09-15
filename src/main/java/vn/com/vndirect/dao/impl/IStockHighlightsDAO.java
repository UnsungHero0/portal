package vn.com.vndirect.dao.impl;

import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public interface IStockHighlightsDAO {
	SearchResult getReports(SearchIfoNews ifoNews, PagingInfo pagingInfo, boolean isOrderByHit, boolean isGetSymbol,
			Integer sysdateFrom) throws SystemException;

	SearchResult getRelatedReports(SearchIfoNews ifoNews, PagingInfo pagingInfo, Integer sysdateFrom) throws SystemException;

	SearchResult getOutstandingSymbols(SearchIfoNews ifoNews, PagingInfo pagingInfo, Integer sysdateFrom) throws SystemException;
	
	SearchResult getSymbolsListHaveReports(PagingInfo pagingInfo) throws SystemException;
	
	SearchIfoNews getNews(Long newsId) throws SystemException;
}
