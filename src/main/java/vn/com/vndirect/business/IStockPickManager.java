package vn.com.vndirect.business;

import java.util.Date;
import java.util.List;
import java.util.Set;

import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

public interface IStockPickManager {
	SearchResult<SearchIfoNews> getAllNewsByDate(Date date, PagingInfo pagingInfo) throws FunctionalException, SystemException;

	SearchResult<SearchIfoNews> getCommonNewsByDate(Date date, PagingInfo pagingInfo) throws FunctionalException, SystemException;

	SearchIfoNews getNewsById(String newsId) throws FunctionalException, SystemException;

	SearchIfoNews getDemoNews() throws FunctionalException, SystemException;

	SearchResult<Date> getNewsestApprovedStockPickReportDate(PagingInfo pagingInfo) throws SystemException;

	List<Date> getRelatedReportDates(PagingInfo pagingInfo, Date toDate) throws SystemException;

	List<Date> getNewerReportDates(PagingInfo pagingInfo, Date fromDate) throws SystemException;

	boolean saveRegistCustomer(String cusId) throws SystemException;

	boolean isExistedCustomer(String cusId) throws SystemException;

	Set<String> getListCustomerId() throws SystemException;

}
