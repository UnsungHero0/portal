package vn.com.vndirect.web.struts2.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.business.ICommonManager;
import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.NewsUrlUtility;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.common.SitemapModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class SitemapAction extends ActionSupport implements ModelDriven<SitemapModel> {
	private SitemapModel model = new SitemapModel();
	
	@Autowired
	private INewsInfoManager newsInfoManager;
	
	@Autowired
	private ICommonManager commonManager;
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;

	@SuppressWarnings("unchecked")
	public String executeSitemap() throws Exception {
		String locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
		Date currentDate = new Date();
		model.setCurrentDate(currentDate);

		SearchResult<SearchIfoNews> result = null;
		SearchIfoNews searchObj = null;
		final PagingInfo pagingInfo = new PagingInfo(5000);

		// Tin Vndirect
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_NEWS));
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setVndirectNewsSearchResult(result);

		// QHCD - CBTT
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS));
		searchObj.setListSymbols(new String[] { "VND" });
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrlWithPageUrl(result, "quan-he-co-dong-vndirect");
		model.setIrDisclosureNewsSearchResult(result);

		// QHCD - Event
		searchObj = getCommonSearchObjForSiteMap();
		Collection newsTypeList = new ArrayList();
		newsTypeList.add(Constants.IServerConfig.DataRef.ItemCodes.NewsType.EVENT);
		newsTypeList.add(Constants.IServerConfig.DataRef.ItemCodes.NewsType.NEWS);
		searchObj.setNewsTypeList(newsTypeList);
		searchObj.setListSymbols(new String[] { "VND" });
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrlWithPageUrl(result, "quan-he-co-dong-vndirect");
		model.setIrCompanyEventsNewsSearchResult(result);

		// Tin trong nuoc
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType("MacVN");
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setMacVnNewsSearchResult(result);

		// Tin quoc te
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType("MacWorld");
		// FIXME : NullPointerException with data in three month
		searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -1), "dd/MM/yyyy"));
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setWorldNewsSearchResult(result);

		// Nhan dinh thi truong
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType("DailyReport");
		result = newsInfoManager.getDailyReportNews(searchObj, null, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setDailyReportSearchResult(result);

		// Y kien chuyen gia
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType("Expert");
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setExpertNewsSearchResult(result);

		// Cong bo thong tin
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType("Disclousure");
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrl(result);
		model.setDisclosureNewsSearchResult(result);

		// Stock list
		SearchResult<String> stockList = commonManager.getAllStockCode();
		model.setStockList(stockList);

		// List TTCP > CBTT
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsType(ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.NewsType.IPO_NEWS));
		// get news in one month
		searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -1), "dd/MM/yyyy"));
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrlWithPageUrl(result, "thong-tin-co-phieu");
		model.setSiDisclosureNewsSearchResult(result);

		// List TTCP > Event
		searchObj = getCommonSearchObjForSiteMap();
		searchObj.setNewsTypeList(newsTypeList);
		// get news in one month
		searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -1), "dd/MM/yyyy"));
		result = newsInfoManager.searchNewsIfo(searchObj, pagingInfo);
		newsInfoManager.createNewsUrlWithPageUrl(result, "thong-tin-co-phieu");
		model.setSiCompanyEventsNewsSearchResult(result);

		// Sectors
		List<String> sectorList;
		List<String> industryList;
		List<List<String>> sectorsAndIndustries;
		List<List<List<String>>> listSectorsAndIndustries = new ArrayList<List<List<String>>>();

		List<Map<String, IfoSectorCalcView>> sectors = analysisToolsManager.getListOfSector(locale);
		if (sectors != null && sectors.size() > 0) {
			String sectorCode;
			for (Map<String, IfoSectorCalcView> map : sectors) {
				sectorCode = map.entrySet().iterator().next().getValue().getSectorCode();
				sectorList = new ArrayList<String>();
				sectorList.add(sectorCode);

				List<Map<String, IfoIndustryCalcView>> industries = analysisToolsManager.getListOfIndustry(locale, sectorCode);
				industryList = new ArrayList<String>();
				if (industries != null && industries.size() > 0) {
					for (Map<String, IfoIndustryCalcView> industryMap : industries) {
						industryList.add(industryMap.entrySet().iterator().next().getValue().getIndustryCode());
					}
				}

				sectorsAndIndustries = new ArrayList<List<String>>();
				sectorsAndIndustries.add(sectorList);
				sectorsAndIndustries.add(industryList);
				listSectorsAndIndustries.add(sectorsAndIndustries);
			}
		}
		model.setListSectorsAndIndustries(listSectorsAndIndustries);

		return SUCCESS;
	}

	public SitemapModel getModel() {
		return model;
	}

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	private SearchIfoNews getCommonSearchObjForSiteMap() throws SystemException {
		final SearchIfoNews searchObj = new SearchIfoNews();
		searchObj.setOrderByDate(true);
		searchObj.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
		searchObj.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
		searchObj.setStrFromNewsDate(DateUtils.dateToString(DateUtils.addMonth(DateUtils.getCurrentDate(), -3), "dd/MM/yyyy"));

		return searchObj;
	}

	public void setCommonManager(ICommonManager commonManager) {
		this.commonManager = commonManager;
	}

	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}
}
