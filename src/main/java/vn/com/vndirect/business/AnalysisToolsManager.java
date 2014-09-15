package vn.com.vndirect.business;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.ssm.api.ReadThroughSingleCache;

import vn.com.vndirect.commons.Code;
import vn.com.vndirect.commons.convert.BaseBeanConverter;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.vndsservice.VNDSServiceUtils;
import vn.com.vndirect.dao.impl.IfoCompanyCalcViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyProfileViewDAO;
import vn.com.vndirect.dao.impl.IfoIndustryCalcViewDAO;
import vn.com.vndirect.dao.impl.IfoPowerRatingViewDAO;
import vn.com.vndirect.dao.impl.IfoSectorCalcViewDAO;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoCompanyProfileView;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.domain.IfoIndustryCalcView;
import vn.com.vndirect.domain.IfoPowerRatingBean;
import vn.com.vndirect.domain.IfoSectorCalcView;
import vn.com.vndirect.domain.SearchAnalysisIndexingBean;
import vn.com.vndirect.domain.SearchStockExchangeIndexingBean;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.domain.StockScreenerBean;
import vn.com.vndirect.domain.StockWizardBean;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.embeddb.dao.CompanyCalcViewDAO;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;
import vn.com.vndirect.wsclient.AuthenticationHeader;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearch;
import vn.com.vndirect.wsclient.streamquotes.IntradayPriceSearchResult;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;
import vn.com.vndirect.wsclient.tradingideas.CreateOrUpdateSaveSearch;
import vn.com.vndirect.wsclient.tradingideas.CreateOrUpdateSaveSearchResult;
import vn.com.vndirect.wsclient.tradingideas.DeleteSaveSearch;
import vn.com.vndirect.wsclient.tradingideas.DeleteSaveSearchResult;
import vn.com.vndirect.wsclient.tradingideas.GetSaveSearch;
import vn.com.vndirect.wsclient.tradingideas.GetSaveSearchResult;
import vn.com.vndirect.wsclient.tradingideas.ITradingIdeasService;
import vn.com.vndirect.wsclient.tradingideas.ITradingIdeasServicePortType;
import vn.com.vndirect.wsclient.tradingideas.SaveSearch;
import vn.com.vndirect.wsclient.tradingideas.SearchSaveSearch;
import vn.com.vndirect.wsclient.tradingideas.SearchSaveSearchResult;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AnalysisToolsManager extends BaseManager implements IAnalysisToolsManager {
	private static final int EXPIRATION_TIME_FOR_CACHE = 3600;
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private IfoCompanyCalcViewDAO ifoCompanyCalcViewDAO;

	@Autowired
	private IfoIndustryCalcViewDAO ifoIndustryCalcViewDAO;

	@Autowired
	private IfoSectorCalcViewDAO ifoSectorCalcViewDAO;

	@Autowired
	private IfoCompanyProfileViewDAO ifoCompanyProfileViewDAO;

	@Autowired
	private IfoPowerRatingViewDAO ifoPowerRatingViewDAO;

	@SuppressWarnings("unused")
	private ITradingIdeasService tradingIdeasService;

	@Autowired
	private FinfoDBManager finfoDBManager;

	@Autowired
	private CompanyCalcViewDAO companyCalcViewDAO;

	/**
	 * @param companyCalcViewDAO
	 *            the companyCalcViewDAO to set
	 */
	public void setCompanyCalcViewDAO(CompanyCalcViewDAO companyCalcViewDAO) {
		this.companyCalcViewDAO = companyCalcViewDAO;
	}

	/**
	 * @param ifoCompanyProfileViewDAO
	 *            the ifoCompanyProfileViewDAO to set
	 */
	public void setIfoCompanyProfileViewDAO(IfoCompanyProfileViewDAO ifoCompanyProfileViewDAO) {
		this.ifoCompanyProfileViewDAO = ifoCompanyProfileViewDAO;
	}

	/**
	 * 
	 * @param finfoDBManager
	 */
	public void setFinfoDBManager(FinfoDBManager finfoDBManager) {
		this.finfoDBManager = finfoDBManager;
	}

	/**
	 * @param tradingIdeasService
	 *            the tradingIdeasService to set
	 */
	public void setTradingIdeasService(ITradingIdeasService tradingIdeasService) {
		this.tradingIdeasService = tradingIdeasService;
	}

	/**
	 * @param ifoCompanyCalcViewDAO
	 *            the ifoCompanyCalcViewDAO to set
	 */
	public void setIfoCompanyCalcViewDAO(IfoCompanyCalcViewDAO ifoCompanyCalcViewDAO) {
		this.ifoCompanyCalcViewDAO = ifoCompanyCalcViewDAO;
	}

	/**
	 * @param ifoIndustryCalcViewDAO
	 *            the ifoIndustryCalcViewDAO to set
	 */
	public void setIfoIndustryCalcViewDAO(IfoIndustryCalcViewDAO ifoIndustryCalcViewDAO) {
		this.ifoIndustryCalcViewDAO = ifoIndustryCalcViewDAO;
	}

	/**
	 * @param ifoSectorCalcViewDAO
	 *            the ifoSectorCalcViewDAO to set
	 */
	public void setIfoSectorCalcViewDAO(IfoSectorCalcViewDAO ifoSectorCalcViewDAO) {
		this.ifoSectorCalcViewDAO = ifoSectorCalcViewDAO;
	}

	public void setIfoPowerRatingViewDAO(IfoPowerRatingViewDAO ifoPowerRatingViewDAO) {
		this.ifoPowerRatingViewDAO = ifoPowerRatingViewDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListSectors(vn.com.
	 * vndirect.domain.struts2.analysistools.IfoSectorCalcView)
	 */
	public SearchResult getListSectors(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException, SystemException {
		final String LOCATION = "getListSectors(ifoSectorCalcView:" + ifoSectorCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoSectorCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}
		try {
			SearchResult result = ifoSectorCalcViewDAO.getListSectors(ifoSectorCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getIfoSectorCalcViewByCode
	 * (vn.com.vndirect.domain.struts2.analysistools.IfoSectorCalcView)
	 */
	public IfoSectorCalcView getIfoSectorCalcViewByCode(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
			SystemException {
		final String LOCATION = "getIfoSectorCalcViewByCode(ifoSectorCalcView:" + ifoSectorCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoSectorCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}
		try {
			IfoSectorCalcView result = ifoSectorCalcViewDAO.getIfoSectorCalcViewByCode(ifoSectorCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListIndustries(vn.com
	 * .vndirect.domain.struts2.analysistools.IfoIndustryCalcView)
	 */
	public SearchResult getListIndustries(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException, SystemException {
		final String LOCATION = "getListIndustries(ifoCompanyCalcView:" + ifoIndustryCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		try {
			SearchResult result = ifoIndustryCalcViewDAO.getListIndustries(ifoIndustryCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListCompanies(vn.com
	 * .vndirect.domain.struts2.analysistools.IfoCompanyCalcView)
	 */
	public SearchResult getListCompanies(IfoCompanyCalcView ifoCompanyCalcView) throws FunctionalException, SystemException {
		final String LOCATION = "getListCompanies(ifoCompanyCalcView:" + ifoCompanyCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		try {
			SearchResult result = ifoCompanyCalcViewDAO.getListCompanies(ifoCompanyCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getIfoCompanyCalcViewBySymbol
	 * (vn.com.vndirect.domain.struts2.analysistools.IfoCompanyCalcView)
	 */
	public IfoCompanyCalcView getIfoCompanyCalcViewBySymbol(IfoCompanyCalcView searchObj) throws FunctionalException,
			SystemException {
		final String LOCATION = "getIfoCompanyCalcViewBySymbol(ifoCompanyCalcView:" + searchObj + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (searchObj == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		try {
			IfoCompanyCalcView result = ifoCompanyCalcViewDAO.getIfoCompanyCalcViewBySymbol(searchObj);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getIfoIndustryCalcViewByCode
	 * (vn.com.vndirect.domain.struts2.analysistools.IfoIndustryCalcView)
	 */
	public IfoIndustryCalcView getIfoIndustryCalcViewByCode(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
			SystemException {
		final String LOCATION = "getIfoIndustryCalcViewByCode(ifoIndustryCalcView:" + ifoIndustryCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		try {
			IfoIndustryCalcView result = ifoIndustryCalcViewDAO.getIfoIndustryCalcViewByCode(ifoIndustryCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getSectorIndustries(vn
	 * .com.vndirect.domain.struts2.analysistools.IfoIndustryCalcView)
	 */
	public SearchResult getSectorIndustries(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException, SystemException {
		final String LOCATION = "getSectorIndustries(ifoCompanyCalcView:" + ifoIndustryCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}
		try {
			SearchResult result = ifoIndustryCalcViewDAO.getSectorIndustries(ifoIndustryCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.TradingIdeasManager#getListSectorsName(vn.com
	 * .vndirect.domain.IfoSectorCalcView)
	 */
	public List<IfoSectorCalcView> getListSectorsName(IfoSectorCalcView ifoSectorCalcView) throws FunctionalException,
			SystemException {
		final String LOCATION = "getListSectorsName(ifoSectorCalcView:" + ifoSectorCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (ifoSectorCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoSectorCalcView is NULL...");
		}

		try {
			List<IfoSectorCalcView> result = ifoSectorCalcViewDAO.getListSectorsName(ifoSectorCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	public GetSaveSearchResult getSaveSearch(AuthenticationHeader header, SaveSearch saveSearch) throws FunctionalException,
			SystemException {
		final String LOCATION = "getSaveSearch(header, saveSearch)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (saveSearch == null) {
			throw new FunctionalException(LOCATION, "saveSearch is NULL...");
		}
		try {
			ITradingIdeasServicePortType client = getIdeasServicePortType();

			GetSaveSearch getSaveSearch = new GetSaveSearch();
			getSaveSearch.setSaveSearch(saveSearch);
			GetSaveSearchResult results = client.getSaveSearch(header, getSaveSearch);
			VNDSServiceUtils.processMessageStatus(results == null ? null : results.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListIndustryName(vn
	 * .com.vndirect.domain.IfoIndustryCalcView)
	 */
	public List<IfoIndustryCalcView> getListIndustryName(IfoIndustryCalcView ifoIndustryCalcView) throws FunctionalException,
			SystemException {
		final String LOCATION = "getListIndustryName(ifoIndustryCalcView:" + ifoIndustryCalcView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoIndustryCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoIndustryCalcView is NULL...");
		}

		try {
			List<IfoIndustryCalcView> result = ifoIndustryCalcViewDAO.getListIndustryName(ifoIndustryCalcView);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END - " + result.size());
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#search(vn.com.vndirect
	 * .domain.SearchStockScreenerBean, vn.com.vndirect.domain.PagingInfo)
	 */
	public SearchResult<StockScreenerBean> search(SearchStockScreenerBean searchStockScreenerBean, PagingInfo pagingInfo)
			throws FunctionalException, SystemException {
		final String LOCATION = "search(searchStockScreenerBean:" + searchStockScreenerBean + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			// ++++ search AnalysisIndexingBean for StockScreener
			SearchResult<AnalysisIndexingBean> searchResult = this.finfoDBManager.search(searchStockScreenerBean, pagingInfo);
			// List<AnalysisIndexingBean> analysisIndexingBeans =
			// searchResult.getResult();

			List<StockScreenerBean> stockScreenerBeans = new ArrayList<StockScreenerBean>(searchResult.size());

			StockScreenerBean stockScreenerBean;
			for (AnalysisIndexingBean analysisIndexingBean : searchResult) {
				stockScreenerBean = new StockScreenerBean(analysisIndexingBean);
				stockScreenerBeans.add(stockScreenerBean);

			}
			// ---
			SearchResult<StockScreenerBean> results = new SearchResult<StockScreenerBean>();
			results.setResult(stockScreenerBeans);
			results.setPaging(searchResult.getPaging());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END - result: " + results);
			return results;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.TradingIdeasManager#createOrUpdateSaveSearch
	 * (vn.com.clientservices.domain.AuthenticationHeader, java.lang.Long,
	 * vn.com.vndirect.lucene.finfodb.beans.SearchStockScreenerBean)
	 */
	public void createOrUpdateSaveSearch(AuthenticationHeader header, Long onlineUserId,
			SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException {
		final String LOCATION = "createOrUpdateSaveSearch(header, onlineUserId, searchStockScreenerBean)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (searchStockScreenerBean == null) {
			throw new FunctionalException(LOCATION, "searchStockScreenerBean is NULL...");
		}
		try {

			ITradingIdeasServicePortType client = getIdeasServicePortType();
			CreateOrUpdateSaveSearch createOrUpdateSaveSearch = new CreateOrUpdateSaveSearch();

			SaveSearch saveSearch = searchStockScreenerBean.getSaveSearch();
			saveSearch.setOnlineUserId(onlineUserId);
			createOrUpdateSaveSearch.setSaveSearch(saveSearch);

			CreateOrUpdateSaveSearchResult results = client.createOrUpdateSaveSearch(header, createOrUpdateSaveSearch);

			VNDSServiceUtils.processMessageStatus(results == null ? null : results.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.TradingIdeasManager#searchSaveSearch(vn.com.
	 * clientservices.domain.AuthenticationHeader,
	 * vn.com.clientservices.domain.savesearch.SaveSearch)
	 */
	public SearchSaveSearchResult searchSaveSearch(AuthenticationHeader header, SaveSearch saveSearch, PagingInfo pagingInfo)
			throws FunctionalException, SystemException {
		final String LOCATION = "searchSaveSearch(header, saveSearch)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (saveSearch == null) {
			throw new FunctionalException(LOCATION, "saveSearch is NULL...");
		}
		try {
			ITradingIdeasServicePortType client = getIdeasServicePortType();

			SearchSaveSearch searchSaveSearch = new SearchSaveSearch();
			searchSaveSearch.setSaveSearch(saveSearch);

			// pagingInfo = pagingInfo == null ? new PagingInfo() : pagingInfo;
			searchSaveSearch.setPagingInfo(BaseBeanConverter.convertToWebServPaging(pagingInfo));

			SearchSaveSearchResult results = client.searchSaveSearch(header, searchSaveSearch);
			VNDSServiceUtils.processMessageStatus(results == null ? null : results.getMsgStatus());

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.TradingIdeasManager#deleteSaveSearch(vn.com.
	 * clientservices.domain.AuthenticationHeader,
	 * vn.com.clientservices.domain.savesearch.SaveSearch)
	 */
	public void deleteSaveSearch(AuthenticationHeader header, SaveSearch saveSearch) throws FunctionalException, SystemException {
		final String LOCATION = "deleteSaveSearch(header, saveSearch)";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (saveSearch == null) {
			throw new FunctionalException(LOCATION, "saveSearch is NULL...");
		}
		try {
			ITradingIdeasServicePortType client = getIdeasServicePortType();
			DeleteSaveSearch deleteSaveSearch = new DeleteSaveSearch();
			deleteSaveSearch.setSaveSearch(saveSearch);
			DeleteSaveSearchResult results = client.deleteSaveSearch(header, deleteSaveSearch);
			VNDSServiceUtils.processMessageStatus(results == null ? null : results.getMsgStatus());
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
		} catch (RemoteException rex) {
			throw new FunctionalException(LOCATION, rex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { rex.getMessage() });
		} catch (ServiceException srvex) {
			throw new FunctionalException(LOCATION, srvex.getMessage(), Constants.ErrorKeys.Trading.REMOTE_ERROR,
					new String[] { srvex.getMessage() });
		} catch (FunctionalException fex) {
			throw fex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new FunctionalException(LOCATION, Constants.ErrorKeys.Commons.SYSTEM_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getStockWizardBean(java
	 * .lang.String[], java.lang.String, boolean)
	 */
	public StockWizardBean[] getStockWizardBean(String[] symbols, String locale, boolean isCompare) throws FunctionalException,
			SystemException {
		final String LOCATION = "getStockWizardBean(symbols " + symbols + ", locale " + locale + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			List<StockWizardBean> result = new ArrayList<StockWizardBean>();

			// +++ search StockExchangeIndexingBean
			List<StockExchangeIndexingBean> stockExchangeIndexingBeans = this.finfoDBManager
					.search(new SearchStockExchangeIndexingBean(symbols));
			Map<Long, StockExchangeIndexingBean> mapStockExchange = new HashMap<Long, StockExchangeIndexingBean>();
			for (StockExchangeIndexingBean stockExchangeIndexingBean : stockExchangeIndexingBeans) {
				mapStockExchange.put(new Long(stockExchangeIndexingBean.getCompanyId()), stockExchangeIndexingBean);
			}
			// ---

			SearchResult<AnalysisIndexingBean> searchResult = this.finfoDBManager.search(new SearchAnalysisIndexingBean(symbols),
					new PagingInfo(0, Integer.MAX_VALUE));

			if (searchResult != null && !searchResult.isEmpty()) {
				// List<AnalysisIndexingBean> analysisIndexingBeans =
				// searchResult.getResult();
				IfoCompanyProfileViewId ifoCompanyProfileViewId = new IfoCompanyProfileViewId();
				IfoCompanyProfileView ifoCompanyProfileView = null;
				StockWizardBean stockWizardBean;
				Map<String, AnalysisCachingValueInfo> mapValues;
				for (AnalysisIndexingBean analysisIndexingBean : searchResult) {
					stockWizardBean = new StockWizardBean();
					stockWizardBean.setAnalysisIndexingBean(analysisIndexingBean);

					// +++ get company name
					stockWizardBean.setStockExchangeIndexingBean(mapStockExchange.get(new Long(analysisIndexingBean
							.getCompanyId())));
					// ---

					// +++ get profile
					ifoCompanyProfileViewId.setCompanyId(analysisIndexingBean.getCompanyId());
					ifoCompanyProfileViewId.setLocale(locale);
					ifoCompanyProfileView = ifoCompanyProfileViewDAO.findByCompanyId(ifoCompanyProfileViewId);
					if (ifoCompanyProfileView != null && ifoCompanyProfileView.getId() != null) {
						stockWizardBean.setIfoCompanyProfileView(ifoCompanyProfileView.getId());
					}

					if (!isCompare) {
						mapValues = ifoIndustryCalcViewDAO.getIndustryCalc(analysisIndexingBean.getCompanyId(), locale);
						stockWizardBean.setMapValues(mapValues);
					}

					// +++++++
					ifoCompanyProfileView = null;
					result.add(stockWizardBean);
				}
			}
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result.toArray(new StockWizardBean[result.size()]);
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfNamedIndustries
	 * (int, java.lang.String)
	 */
	public SearchResult<IfoIndustryCalcView> getListOfNamedIndustries(int limit, String locale) throws FunctionalException,
			SystemException {
		return ifoIndustryCalcViewDAO.getListOfNamedIndustries(limit, locale);
	}

	public SearchResult<IfoCompanyCalcView> getCompaniesBySectorCode(IfoCompanyCalcView company, PagingInfo pagingInfo)
			throws FunctionalException, SystemException {
		return ifoCompanyCalcViewDAO.getCompaniesBySectorCode(company, pagingInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfSector(java.lang
	 * .String)
	 */
	public List<Map<String, IfoSectorCalcView>> getListOfSector(String locale) throws SystemException {
		return sortByTodayChg(ifoSectorCalcViewDAO.getListOfSector(locale));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.IAnalysisToolsManager#getHotIndustries(int,
	 * java.lang.String)
	 */
	public List<IfoIndustryCalcView> getHotIndustries(int limit, String locale) throws SystemException {
		return ifoIndustryCalcViewDAO.getHotIndustries(limit, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.IAnalysisToolsManager#getHotSectors(int,
	 * java.lang.String)
	 */
	public <T extends IfoSectorCalcView> List<T> getHotSectors(int limit, String locale) throws SystemException {
		final String LOCATION = "getHotSectors(limit:" + limit + ", locale:" + locale + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		List<T> hotSectors = ifoSectorCalcViewDAO.getHotSectors(limit, locale);

		List<IfoIndustryCalcView> industries = null;
		IfoIndustryCalcView t;
		for (int i = 0; i < hotSectors.size(); i++) {
			t = (IfoIndustryCalcView) hotSectors.get(i);

			industries = ifoIndustryCalcViewDAO.getIndustriesForHotSector(t.getSectorCode(), 1, locale);
			if (industries != null && industries.size() > 0) {
				t.setIndustryCode(industries.get(0).getIndustryCode());
				t.setIndustryName(industries.get(0).getIndustryName());
			}
		}
		return hotSectors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.IAnalysisToolsManager#
	 * getThreeMonthHighPerformSectors(int, java.lang.String)
	 */
	public List<IfoSectorCalcView> getThrMonHighPerfSectors(int limit, String locale) throws SystemException {
		return ifoSectorCalcViewDAO.getThrMonHighPerfSectors(limit, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getThrMonHighPerfIndustries
	 * (int, java.lang.String)
	 */
	public List<IfoIndustryCalcView> getThrMonHighPerfIndustries(int limit, String locale) throws SystemException {
		return ifoIndustryCalcViewDAO.getThrMonHighPerfIndustries(limit, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfIndustry(java
	 * .lang.String, java.lang.String)
	 */
	public List<Map<String, IfoIndustryCalcView>> getListOfIndustry(String locale, String sectorCode) throws SystemException {
		return sortByTodayChg(ifoIndustryCalcViewDAO.getListOfIndustry(locale, sectorCode));
	}

	/**
	 * Apply a descending sort by today'change
	 * 
	 * @param <T>
	 * @param input
	 * @return sort list
	 */
	private <T extends IfoSectorCalcView> List<Map<String, T>> sortByTodayChg(List<Map<String, T>> input) {
		// applying a sort by today'change (item_code = 1000028)
		Collections.sort(input, new Comparator<Map<String, T>>() {
			public int compare(Map<String, T> o1, Map<String, T> o2) {
				T num1 = o1.get("1000028");
				T num2 = o2.get("1000028");
				if (num1 == null || num2 == null)
					return -1;
				double n = o1.get("1000028").getNumericValue() - o2.get("1000028").getNumericValue();
				// descending sort
				return -(n > 0 ? 1 : (n < 0) ? -1 : 0);
			}
		});
		return input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfCompaniesWS(java
	 * .lang.String, vn.com.vndirect.commons.Code, int)
	 */
	public SecInfo[] getListOfCompaniesWS(String code, Code type, int limit) throws Exception {
		// get symbols in embed database
		String[] symbols = companyCalcViewDAO.getListOfSymbols(code, type, limit);
		SecInfo[] result = getListOfCompaniesWS(symbols);
		Arrays.sort(result, new Comparator<SecInfo>() {
			public int compare(SecInfo o1, SecInfo o2) {
				double n = o1.getChgIndex() - o2.getChgIndex();
				// descending sort
				return -(n > 0 ? 1 : (n < 0) ? -1 : 0);
			}
		});
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfCompaniesEDB(
	 * java.lang.String, vn.com.vndirect.commons.Code,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<IfoCompanyCalcView> getListOfCompaniesEDB(String code, Code type, PagingInfo pagingInfo, String field,
			String order) throws SystemException {
		return companyCalcViewDAO.getListOfCompanies(code, type, pagingInfo, field, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getListOfCompaniesWS(java
	 * .lang.String[])
	 */
	public SecInfo[] getListOfCompaniesWS(String[] symbols) throws Exception {
		// get companies information via web service
		IntradayPriceSearch search = new IntradayPriceSearch();
		search.setListSymbols(symbols);
		IntradayPriceSearchResult result = getIStreamQuotesServicePortType().searchIntradayPrice(getVndsAuthenticationHeader(),
				search);
		VNDSServiceUtils.processMessageStatus(result == null ? null : result.getMsgStatus());

		// extract the result
		return result.getListSecInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getTopIndustry(java.lang
	 * .String, java.lang.String)
	 */
	public IfoIndustryCalcView getTopIndustry(String code, String locale) throws SystemException {
		return ifoIndustryCalcViewDAO.getTopIndustry(code, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getTopCompany(java.lang
	 * .String)
	 */
	public IfoCompanyCalcView getTopCompany(String code) throws SystemException {
		return companyCalcViewDAO.getTopCompanyEDB(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getIndustry(java.lang.
	 * String, java.lang.String)
	 */
	public Map<String, IfoIndustryCalcView> getIndustry(String locale, String industryCode) throws SystemException {
		return ifoIndustryCalcViewDAO.getIndustry(locale, industryCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getSector(java.lang.String
	 * , java.lang.String)
	 */
	public Map<String, IfoSectorCalcView> getSector(String locale, String sectorCode) throws SystemException {
		return ifoSectorCalcViewDAO.getSector(locale, sectorCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IAnalysisToolsManager#getCompanyEDB(java.lang
	 * .String)
	 */
	public IfoCompanyCalcView getCompanyEDB(String symbol) throws SystemException {
		return companyCalcViewDAO.getCompanyEDB(symbol);
	}

	@ReadThroughSingleCache(namespace = "AnalysisToolsManager.getListOfPrs@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public List<IfoPowerRatingBean> getListOfPrs() throws SystemException {
		final String location = "getListOfPrs()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}

		try {
			List<IfoPowerRatingBean> result = ifoPowerRatingViewDAO.getListOfPrs();

			if (logger.isDebugEnabled()) {
				logger.debug(location + ":: END");
			}

			return result;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

}