package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

import vn.com.vndirect.dao.IRegistStockPickDAO;
import vn.com.vndirect.dao.IStockPickDAO;
import vn.com.vndirect.dao.impl.RegistStockPickDAO;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

import com.google.code.ssm.api.ReadThroughAssignCache;

public class StockPickManager extends BaseManager implements IStockPickManager {
	private static final Logger _LOGGER = Logger.getLogger(StockPickManager.class);
	
	private static final int EXPIRATION_TIME_FOR_CACHE = 600;
	
	private IStockPickDAO stockPickDAO;

	private IRegistStockPickDAO registStockPickDAO;

	@Override
	public SearchResult<SearchIfoNews> getAllNewsByDate(Date date, PagingInfo pagingInfo) throws FunctionalException,
			SystemException {

		if (date == null) {
			throw new FunctionalException("date is null ..");
		}

		return stockPickDAO.getAllNewsByDate(date, pagingInfo);
	}

	public void setRegistStockPickDAO(IRegistStockPickDAO registStockPickDAO) {
		this.registStockPickDAO = registStockPickDAO;
	}

	public void setStockPickDAO(IStockPickDAO stockPickDAO) {
		this.stockPickDAO = stockPickDAO;
	}

	@Override
	public SearchResult<Date> getNewsestApprovedStockPickReportDate(PagingInfo pagingInfo) throws SystemException {
		return stockPickDAO.getStockPickReportDates(pagingInfo, null, false);
	}

	@Override
	public List<Date> getRelatedReportDates(PagingInfo pagingInfo, Date toDate) throws SystemException {
		toDate = convertDateToSuitWithDatabase(toDate);

		SearchResult<Date> result = stockPickDAO.getStockPickReportDates(pagingInfo, toDate, false);
		List<Date> dates = new ArrayList<Date>();
		if (result != null && result.size() > 0) {
			for (Date date : result) {
				date = convertDateToSuitWithWeb(date);
				dates.add(date);
			}
		}

		return dates;
	}

	/**
	 * convert year from 20xx to 00xx to suit with db
	 */
	private Date convertDateToSuitWithDatabase(Date date) throws SystemException {
		String s = DateUtils.dateToString(date, "dd-MMM-yy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 2000);

		return cal.getTime();
	}

	private Date convertDateToSuitWithWeb(Date date) throws SystemException {
		String s = DateUtils.dateToString(date, "dd-MMM-yy");

		return DateUtils.stringToDate(s, "dd-MMM-yy");
	}

	@Override
	public List<Date> getNewerReportDates(PagingInfo pagingInfo, Date fromDate) throws SystemException {
		fromDate = convertDateToSuitWithDatabase(fromDate);

		SearchResult<Date> result = stockPickDAO.getStockPickReportDates(pagingInfo, fromDate, true);
		List<Date> dates = new ArrayList<Date>();
		if (result != null && result.size() > 0) {
			for (Date date : result) {
				date = convertDateToSuitWithWeb(date);
				dates.add(date);
			}
		}

		return dates;
	}

	@Override
	public SearchIfoNews getNewsById(String newsId) throws FunctionalException, SystemException {

		if (StringUtils.isEmpty(newsId) || !StringUtils.isNumeric(newsId)) {
			throw new FunctionalException("must not empty or  must be a number id ..");
		}

		return stockPickDAO.getNewsById(Long.valueOf(newsId));
	}

	@Override
	public boolean saveRegistCustomer(String cusId) throws SystemException {
		final String location = "saveRegistCustomer(cusID:" + cusId + ")";
		_LOGGER.debug(location + ":: BEGIN");
		
		boolean res = registStockPickDAO.saveCustomerId(cusId);
		
		_LOGGER.debug(location + ":: END");
		return res;
	}

	@Override
	public boolean isExistedCustomer(final String cusId) throws SystemException {
		final String location = "isExistedCustomer(cusID:" + cusId + ")";
		_LOGGER.debug(location + ":: BEGIN");
		
		boolean res = registStockPickDAO.isExistedCustomer(cusId);
		
		_LOGGER.debug(location + ":: END");
		return res;
	}

	@Override
	public SearchResult<SearchIfoNews> getCommonNewsByDate(Date date, PagingInfo pagingInfo) throws FunctionalException,
			SystemException {
		if (date == null) {
			throw new FunctionalException("date is null ..");
		}

		return stockPickDAO.getCommonNewsByDate(date, pagingInfo);
	}
	
	@ReadThroughAssignCache(namespace = "StockPickManager.getListCustomerId@", assignedKey = "getListCustomerId$$$", expiration = EXPIRATION_TIME_FOR_CACHE)
	public Set<String> getListCustomerId() throws SystemException {
		final String location = "getListCustomerId()";
		_LOGGER.debug(location + ":: BEGIN");
		
		Set<String> res = new HashSet<String>(registStockPickDAO.getListCustomerId());
		
		_LOGGER.debug(location + ":: END");
		return res;
	}

	@Override
	public SearchIfoNews getDemoNews() throws FunctionalException, SystemException {
		String demoNewsId = stockPickDAO.getDemoStockPickNewsId();
		
		if(StringUtils.isEmpty(demoNewsId)){
			throw new FunctionalException("get empty demo news id ...");
		}
		
		return getNewsById(demoNewsId);
	}
	
}
