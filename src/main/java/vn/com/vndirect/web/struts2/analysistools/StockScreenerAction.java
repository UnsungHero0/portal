/**
 * 
 */
package vn.com.vndirect.web.struts2.analysistools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.FinfoDBManager;
import vn.com.vndirect.business.IAnalysisToolsManager;
import vn.com.vndirect.commons.convert.BaseBeanConverter;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.StockScreenerBean;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.struts2.analysistools.StockScreenerModel;
import vn.com.vndirect.wsclient.AuthenticationHeader;
import vn.com.vndirect.wsclient.tradingideas.GetSaveSearchResult;
import vn.com.vndirect.wsclient.tradingideas.SaveSearch;
import vn.com.vndirect.wsclient.tradingideas.SearchSaveSearchResult;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Huy
 * 
 */
@SuppressWarnings("serial")
public class StockScreenerAction extends ActionSupport implements ModelDriven<StockScreenerModel>, Preparable {

	private static Logger logger = Logger.getLogger(StockScreenerAction.class);

	private StockScreenerModel model = new StockScreenerModel();
	
	@Autowired
	private IAnalysisToolsManager analysisToolsManager;
	
	@Autowired
	private FinfoDBManager finfoDBManager;
	
	// private Logger logger = Logger.getLogger(getClass());
	private final String STOCK_SCREENER_CRITERIA_KEY = "$STOCK_SCREENER_CRITERIA_KEY$";
	private Long onlineUserId;
	private AuthenticationHeader header;
	private SearchStockScreenerBean criteria;
	private String locale;

	private String stockScreenerListUrl;

	public void setStockScreenerListUrl(String stockScreenerListUrl) {
		this.stockScreenerListUrl = stockScreenerListUrl;
	}

	public String getStockScreenerListEncodedUrl() {
		// return Utilities.FormatURL.encodeURI(stockScreenerListUrl,
		// ServletActionContext.getRequest());
		return stockScreenerListUrl;
	}

	/**
	 * @param analysisToolsManager
	 *            the analysisToolsManager to set
	 */
	public void setAnalysisToolsManager(IAnalysisToolsManager analysisToolsManager) {
		this.analysisToolsManager = analysisToolsManager;
	}

	/**
	 * @param finfoDBManager
	 *            the finfoDBManager to set
	 */
	public void setFinfoDBManager(FinfoDBManager finfoDBManager) {
		this.finfoDBManager = finfoDBManager;
	}

	/**
	 * To display page in which all the fields stored in the current session
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewStockScreener() throws Exception {
		final String LOCATION = "viewStockScreener()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			IfoSectorCalcView searchObj = new IfoSectorCalcView();
			searchObj.setLocale(locale);
			List<IfoSectorCalcView> listSectorsName = analysisToolsManager.getListSectorsName(searchObj);
			listSectorsName = (listSectorsName == null) ? new ArrayList<IfoSectorCalcView>() : listSectorsName;
			model.setListSectorsName(listSectorsName);

			criteria = (SearchStockScreenerBean) model.getFromCache(STOCK_SCREENER_CRITERIA_KEY);
			model.setSearchStockScreenerBean(criteria);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();

		return SUCCESS;
	}

	/**
	 * To display page in which all the fields has saved value
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSavedData() throws Exception {
		final String LOCATION = "viewSavedData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			SaveSearch saveSearch = new SaveSearch();
			saveSearch.setOnlineUserId(onlineUserId);
			saveSearch.setSaveSearchId(model.getSaveSearchId());

			GetSaveSearchResult results = analysisToolsManager.getSaveSearch(header, saveSearch);
			criteria = SearchStockScreenerBean.getSearchStockScreenerBean(results.getSaveSearch());
			model.setSearchStockScreenerBean(criteria);

			IfoSectorCalcView searchObj = new IfoSectorCalcView();
			searchObj.setLocale(locale);
			List<IfoSectorCalcView> listSectorsName = analysisToolsManager.getListSectorsName(searchObj);
			listSectorsName = (listSectorsName == null) ? new ArrayList<IfoSectorCalcView>() : listSectorsName;
			model.setListSectorsName(listSectorsName);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	/**
	 * To display page in which all the summary fields has saved value
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSavedSummaryData() throws Exception {
		final String LOCATION = "viewSavedSummaryData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			SaveSearch saveSearch = new SaveSearch();
			saveSearch.setOnlineUserId(onlineUserId);
			saveSearch.setSaveSearchId(model.getSaveSearchId());

			GetSaveSearchResult results = analysisToolsManager.getSaveSearch(header, saveSearch);
			criteria = SearchStockScreenerBean.getSearchStockScreenerBean(results.getSaveSearch());
			model.setSearchStockScreenerBean(criteria);

			doPreviewData(criteria, (PagingInfo) null);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.stockFilterResult"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// seo
		doSEOpage();

		return SUCCESS;
	}

	/**
	 * To display preview data in a sorted order
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSortedData() throws Exception {
		final String LOCATION = "viewSortedData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			PagingInfo pagingInfo = model.getPagingInfo();
			criteria = (SearchStockScreenerBean) model.getFromCache(STOCK_SCREENER_CRITERIA_KEY);
			if (criteria != null) {
				criteria.setSortField(model.getSortField());
				criteria.setSortOrder(model.getSortOrder());

				doPreviewData(criteria, pagingInfo);
			} else {
				addActionError("Unable to get data!");
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * No needed ???-> move to screen summary screen which has sorting and
	 * paging function
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSummaryData() throws Exception {
		final String LOCATION = "viewSummaryData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		/*
		 * SearchStockScreenerBean criteria =
		 * model.getSearchStockScreenerBean();
		 * model.addToCache(STOCK_SCREENER_CRITERIA_KEY, criteria);
		 * ActionContext
		 * .getContext().getSession().put(STOCK_SCREENER_CRITERIA_KEY,
		 * criteria);
		 */
		/*
		 * OnlineUser onlineUser =
		 * SessionManager.OnlineUsers.getCurrentOnlineUser(); if (onlineUser ==
		 * null || (ServerConfig.getOnlineValue(
		 * Constants.ServerConfig.DataRef.ItemCodes
		 * .OnlineUserStatus.ONLINE_FREE_REGISTER)
		 * .equalsIgnoreCase(onlineUser.getStatus())&& bean.getCriteriaCnt()
		 * >2)) { model.setAlert("1"); return INPUT; }
		 */

		try {
			criteria = model.getSearchStockScreenerBean();
			if (criteria == null) {
				return INPUT;
			}
			doPreviewData(criteria, null);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.stockFilterResult"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
	}

	/**
	 * 
	 * @param criteria
	 * @throws NumberFormatException
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	private void doPreviewData(SearchStockScreenerBean criteria, PagingInfo pagingInfo) throws NumberFormatException,
	        FunctionalException, SystemException {
		if (pagingInfo == null) {
			pagingInfo = new PagingInfo();
		}

		SearchResult<StockScreenerBean> result = analysisToolsManager.search(criteria, pagingInfo);
		model.setListStockScreener(result);
		model.setPagingInfo((PagingInfo)result.getPaging());
		model.addToCache(STOCK_SCREENER_CRITERIA_KEY, criteria);
	}

	/**
	 * To save a stock screener criteria into database
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveStockScreener() throws Exception {
		final String LOCATION = "saveStockScreener()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		// should we use annotation instead of this ??? :-)
		try {
			if (StringUtils.isBlank(model.getName())) {
				addActionError(getText("ErrorMessage.Home.OnlineAccount.fields-required",
				        new String[] { getText("web.label.Result_View.SaveSearchName") }));
				return INPUT;
			} else {
				criteria = (SearchStockScreenerBean) model.getFromCache(STOCK_SCREENER_CRITERIA_KEY);
				criteria.setSaveSearchName(model.getName());
				analysisToolsManager.createOrUpdateSaveSearch(header, onlineUserId, criteria);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public class SaveSearchExt extends SaveSearch {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3425321855449858668L;

		public String getNumberOfMatchedItem() throws Exception {
			return getText("web.label.Result_View.TotalCurrentMatchs",
			        new String[] { finfoDBManager.countStockScreener(SearchStockScreenerBean.getSearchStockScreenerBean(this))
			                + "" });
		}

		public Date getRegisterDated() {
			return getRegisterDate().getTime();
		}
	}

	/**
	 * move to list of stock screener
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listOfStockScreener() {
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.saveFilterResult"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		// seo
		model.setPageTitle(this.getText("analysis.saveFilterResult.title"));
		model.setPageDescription(this.getText("analysis.saveFilterResult.desc"));
		model.setPageKeywords(this.getText("analysis.saveFilterResult.keywords"));

		return SUCCESS;
	}

	/**
	 * To display list of stock screener
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewListOfStockScreener() throws Exception {
		final String LOCATION = "viewListOfStockScreener()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		SaveSearch saveSearch = new SaveSearch();
		saveSearch.setOnlineUserId(onlineUserId);
		try {
			SearchSaveSearchResult result = analysisToolsManager.searchSaveSearch(header, saveSearch, model.getPagingInfo());

			List<SaveSearch> original = Arrays.asList(result.getSaveSearchList());
			CollectionUtils.transform(original, new Transformer() {
				public Object transform(Object arg0) {
					SaveSearchExt ext = new SaveSearchExt();
					BeanUtils.copyProperties(arg0, ext);
					return ext;
				}
			});
			model.setCriteria(original);
			model.setPagingInfo(BaseBeanConverter.convertToWebAppPaging(result.getPagingInfo(), model.getPagingInfo()
			        .getIndexPage()));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			addActionError(getText("system.error.connect", new String[] { e.getMessage() }));
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * To delete criteria
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		final String LOCATION = "delete()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			SaveSearch saveSearch = new SaveSearch();
			saveSearch.setOnlineUserId(onlineUserId);
			saveSearch.setSaveSearchId(model.getSaveSearchId());

			analysisToolsManager.deleteSaveSearch(header, saveSearch);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	public StockScreenerModel getModel() {
		return model;
	}

	public String viewInputData() throws Exception {
		final String LOCATION = "viewInputData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			criteria = (SearchStockScreenerBean) model.getFromCache(STOCK_SCREENER_CRITERIA_KEY);
			model.setSearchStockScreenerBean(criteria);
			model.setName(criteria.getSaveSearchName());
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");

		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.inputFilterResult"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.analysisTool"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		breadcrumbs.add(this.getText("leftmenu.label.analysisTool.Refine.StockScreener"));
		breadcrumbs.add("/cong-cu-phan-tich-chung-khoan/sang-loc-co-phieu.shtml");
		model.setBreadcrumbs(breadcrumbs);

		return SUCCESS;
	}

	public void prepare() throws Exception {
		onlineUserId = SessionManager.OnlineUsers.getOnlineUserId();//
		onlineUserId = onlineUserId == null ? 106l : onlineUserId;//
		header = SessionManager.OnlineUsers.getVNDSAuthenticationHeader();//
		locale = I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession());
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.stockCcreener.title"));
		model.setPageDescription(this.getText("analysis.stockCcreener.desc"));
		model.setPageKeywords(this.getText("analysis.stockCcreener.keywords"));
	}
}
