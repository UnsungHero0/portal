package vn.com.vndirect.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;
import edu.emory.mathcs.backport.java.util.Collections;

public class StockPickDAOTest extends BaseDAOTest {

	private StockPickDAO dao;
	
	@Before
	public void setUp() {
		dao = new StockPickDAO();
		dao.setDataSource(dataSource);
	}
	
	@Test
	@Ignore
	public void test3() throws Exception{
		//String s = "28/02/0014";
		String s = "28-FEB-14";
		Date d = DateUtils.stringToDate(s, "dd/MM/00yy");
		
		SearchResult<Date> result = dao.getStockPickReportDates(new PagingInfo(), d, false);
		for(Date date : result){
			System.out.println(convertDateToSuitWithWeb(date));
		}
		Assert.assertNotNull(result);
	}

	@Test
	@Ignore
	public void test2() throws Exception {
		SearchResult<Date> result = dao.getStockPickReportDates(new PagingInfo(), null, false);
		Collections.sort(result);
		
		result = filterDates(result);
		System.out.println(result);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	@Ignore
	public void testGetAllNewsByDate() throws Exception {
		SearchResult<SearchIfoNews> result = dao.getAllNewsByDate(Calendar.getInstance().getTime(), new PagingInfo());
		
		Assert.assertNotNull(result);
	}
	
	@Test
	@Ignore
	public void testGetCommonNewsByDate() throws Exception {
		SearchResult<SearchIfoNews> result = dao.getCommonNewsByDate(Calendar.getInstance().getTime(), new PagingInfo());
		
		Assert.assertNotNull(result);
	}
	
	private SearchResult<Date> filterDates(SearchResult<Date> dates) throws SystemException {
		if (dates.size() > 0) {
			if (isAfterToday(dates.get(dates.size() - 1))) {
				dates.remove(dates.size() - 1);
				filterDates(dates);
			} else {
				return dates;
			}
		}

		return dates;
	}

	private boolean isAfterToday(Date date) throws SystemException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar today = Calendar.getInstance();

		return (cal.get(Calendar.YEAR) + 2000) == today.get(Calendar.YEAR)
				&& cal.get(Calendar.DAY_OF_YEAR) > today.get(Calendar.DAY_OF_YEAR);

	}
	
	private Date convertDateToSuitWithWeb(Date date) throws SystemException {
		String s = DateUtils.dateToString(date, "dd-MMM-yy");

		return DateUtils.stringToDate(s, "dd-MMM-yy");
	}
	
	@After
	public void teardown(){
		try {
			dao.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
