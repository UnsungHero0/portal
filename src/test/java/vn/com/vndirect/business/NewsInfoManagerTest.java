package vn.com.vndirect.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;

public class NewsInfoManagerTest extends AbstractManagerTest {

	private static final String STOCK_HIGHLIGHTS_NEWS_TYPE = "StockHighlights";
	private static final String DISCLOUSURE_NEWS_TYPE = "Disclousure";
	private static final Long VND_COMPANY_ID = 2094L;
	private static final String NEWS_INFO_MANAGER_BEAN_NAME = "NewsInfoManager";
	private static final int DEFAULT_PAGING_INFO_OFFSET = 10;

	private MockHttpServletRequest mockRequest;

	private INewsInfoManager newsInfoManager;

	private PagingInfo pagingInfo;
	private SearchIfoNews searchObj;
	private SearchResult<SearchIfoNews> searchResult;

	@Before
	@Ignore
	public void setUp() throws SystemException {
		newsInfoManager = (INewsInfoManager) applicationContext.getBean(NEWS_INFO_MANAGER_BEAN_NAME);

		pagingInfo = new PagingInfo(DEFAULT_PAGING_INFO_OFFSET);

		mockRequest = new MockHttpServletRequest();
		searchObj = new SearchIfoNews();
		searchObj.setLocale(I18NUtility.getCurrentLocale(mockRequest.getSession()));
		searchObj.setOrderByDate(true);
		searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		searchObj.setCompanyId(VND_COMPANY_ID);
	}

	@Test
	@Ignore
	public void testInitNewsInfoManagerSuccess() {
		assertNotNull(newsInfoManager);
	}

	@Test
	@Ignore
	public void testGetNewsWithNewsTypeIsDisclousureSuccess() throws SystemException, FunctionalException {
		searchObj.setNewsType(DISCLOUSURE_NEWS_TYPE);
		searchResult = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		
		assertNotNull("SearchResult is not null", searchResult);
	}

	@Test
	@Ignore
	public void testGetNewsWithNewsTypeIsDisclousureNotEmpty() throws SystemException, FunctionalException {
		searchObj.setNewsType(DISCLOUSURE_NEWS_TYPE);
		searchResult = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		
		assertTrue("SearchResult is not empty", searchResult.size() > 0);
	}

	@Test
	@Ignore
	public void testGetNewsWithNewsTypeIsDisclousureSuccessAndNewsTypeOfResultIsDisclousure() throws SystemException,
			FunctionalException {
		searchObj.setNewsType(DISCLOUSURE_NEWS_TYPE);
		searchResult = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		
		assertEquals("Newstype of result is Disclousure",DISCLOUSURE_NEWS_TYPE, searchResult.get(0).getNewsType());
	}
	
	@Test
	@Ignore
	public void testGetStockHighlightsSuccess() throws SystemException, FunctionalException {
		List<String> types = new ArrayList<String>();
		types.add(STOCK_HIGHLIGHTS_NEWS_TYPE);
		searchObj.setNewsTypeList(types);
		searchResult = newsInfoManager.getReportAnalysis(searchObj, pagingInfo);
		
		assertNotNull("SearchResult is not null", searchResult);
	}
	
	@Test
	@Ignore
	public void testGetStockHighlightsNotEmpty() throws SystemException, FunctionalException {
		List<String> types = new ArrayList<String>();
		types.add(STOCK_HIGHLIGHTS_NEWS_TYPE);
		searchObj.setNewsTypeList(types);
		searchResult = newsInfoManager.getReportAnalysis(searchObj, pagingInfo);
		
		assertTrue("SearchResult is not empty", searchResult.size() > 0);
	}

	@After
	public void tearDown() {
		searchResult = null;
		newsInfoManager = null;
		pagingInfo = null;
		searchObj = null;
	}
}
