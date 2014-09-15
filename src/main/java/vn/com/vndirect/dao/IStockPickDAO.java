package vn.com.vndirect.dao;

import java.util.Date;

import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

public interface IStockPickDAO {
	SearchResult<SearchIfoNews> getAllNewsByDate(Date date, PagingInfo pagingInfo) throws SystemException;
	
	SearchResult<SearchIfoNews> getCommonNewsByDate(Date date, PagingInfo pagingInfo) throws SystemException;
	
	SearchIfoNews getNewsById(Long newsId) throws SystemException;
	
	SearchResult<Date> getStockPickReportDates(PagingInfo pagingInfo, Date date, boolean isNewer) throws SystemException;
	
	String getDemoStockPickNewsId() throws SystemException;
}
