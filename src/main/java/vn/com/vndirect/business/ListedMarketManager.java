package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.dao.impl.IfoBalancesheetViewDAO;
import vn.com.vndirect.dao.impl.IfoCashflowViewDAO;
import vn.com.vndirect.dao.impl.IfoCompanyCalcViewDAO;
import vn.com.vndirect.dao.impl.IfoDocumentDAO;
import vn.com.vndirect.dao.impl.IfoForeignTradingViewDAO;
import vn.com.vndirect.dao.impl.IfoIncomeViewDAO;
import vn.com.vndirect.dao.impl.IfoMarketCalendarViewDAO;
import vn.com.vndirect.dao.impl.IfoSecPriceViewDAO;
import vn.com.vndirect.dao.impl.IfoTradingStatisticsViewDAO;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.IfoDocument;
import vn.com.vndirect.domain.IfoForeignTradingView;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.IfoSecPriceView;
import vn.com.vndirect.domain.IfoSecPriceViewId;
import vn.com.vndirect.domain.IfoTradingStatisticsView;
import vn.com.vndirect.domain.IfoTradingStatisticsViewId;
import vn.com.vndirect.domain.extend.FinanceReportForQuote;
import vn.com.vndirect.domain.extend.IfoBalanceSheetSearch;
import vn.com.vndirect.domain.extend.IfoBalancesheetViewExt;
import vn.com.vndirect.domain.extend.IfoCashflowViewExt;
import vn.com.vndirect.domain.extend.IfoIncomeViewExt;
import vn.com.vndirect.domain.extend.IfoSecPriceViewExt;
import vn.com.vndirect.domain.extend.IfoTradingStatisticsViewExt;
import vn.com.vndirect.domain.extend.SearchIfoDocument;
import vn.com.vndirect.domain.extend.SearchMarketStatisticsView;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListedMarketManager extends BaseManager implements IListedMarketManager {

	private static Logger logger = Logger.getLogger(ListedMarketManager.class);
	
	@Autowired
	private IfoMarketCalendarViewDAO ifoMarketCalendarViewDAO;
	
	@Autowired
	private IfoCompanyCalcViewDAO ifoCompanyCalcViewDAO;
	
	@Autowired
	private IfoDocumentDAO ifoDocumentDAO;
	
	@Autowired
	private IfoBalancesheetViewDAO ifoBalancesheetViewDAO;
	
	@Autowired
	private IfoIncomeViewDAO ifoIncomeViewDAO;
	
	@Autowired
	private IfoCashflowViewDAO ifoCashflowViewDAO;
	
	@Autowired
	private IfoSecPriceViewDAO ifoSecPriceViewDAO;
	
	@Autowired
	private IfoTradingStatisticsViewDAO ifoTradingStatisticsViewDAO;
	
	@Autowired
	private IfoForeignTradingViewDAO ifoForeignTradingViewDAO;

	// +++++ ===== Define type of search's object for financial report =====
	// +++++
	final static String BALANCESHEET_VIEW = "BALANCESHEET_VIEW";
	final static String INCOME_VIEW = "INCOME_VIEW";
	final static String CASHFLOW_VIEW = "CASHFLOW_VIEW";

	/**
	 * 
	 * @param ifoMarketCalendarViewDAO
	 */
	public void setIfoMarketCalendarViewDAO(IfoMarketCalendarViewDAO ifoMarketCalendarViewDAO) {
		this.ifoMarketCalendarViewDAO = ifoMarketCalendarViewDAO;
	}

	/**
	 * @param ifoCompanyCalcViewDAO
	 *            the ifoCompanyCalcViewDAO to set
	 */
	public void setIfoCompanyCalcViewDAO(IfoCompanyCalcViewDAO ifoCompanyCalcViewDAO) {
	}

	/**
	 * @param ifoDocumentDAO
	 *            the ifoDocumentDAO to set
	 */
	public void setIfoDocumentDAO(IfoDocumentDAO ifoDocumentDAO) {
		this.ifoDocumentDAO = ifoDocumentDAO;
	}

	/**
	 * @param ifoBalancesheetViewDAO
	 *            the ifoBalancesheetViewDAO to set
	 */
	public void setIfoBalancesheetViewDAO(IfoBalancesheetViewDAO ifoBalancesheetViewDAO) {
		this.ifoBalancesheetViewDAO = ifoBalancesheetViewDAO;
	}

	/**
	 * @param ifoIncomeViewDAO
	 *            the ifoIncomeViewDAO to set
	 */
	public void setIfoIncomeViewDAO(IfoIncomeViewDAO ifoIncomeViewDAO) {
		this.ifoIncomeViewDAO = ifoIncomeViewDAO;
	}

	/**
	 * @param ifoCashflowViewDAO
	 *            the ifoCashflowViewDAO to set
	 */
	public void setIfoCashflowViewDAO(IfoCashflowViewDAO ifoCashflowViewDAO) {
		this.ifoCashflowViewDAO = ifoCashflowViewDAO;
	}

	/**
	 * @param ifoSecPriceViewDAO
	 *            the ifoSecPriceViewDAO to set
	 */
	public void setIfoSecPriceViewDAO(IfoSecPriceViewDAO ifoSecPriceViewDAO) {
		this.ifoSecPriceViewDAO = ifoSecPriceViewDAO;
	}

	/**
	 * @param ifoTradingStatisticsViewDAO
	 *            the ifoTradingStatisticsViewDAO to set
	 */
	public void setIfoTradingStatisticsViewDAO(IfoTradingStatisticsViewDAO ifoTradingStatisticsViewDAO) {
		this.ifoTradingStatisticsViewDAO = ifoTradingStatisticsViewDAO;
	}

	/**
	 * @param ifoForeignTradingViewDAO
	 *            the ifoForeignTradingViewDAO to set
	 */
	public void setIfoForeignTradingViewDAO(IfoForeignTradingViewDAO ifoForeignTradingViewDAO) {
		this.ifoForeignTradingViewDAO = ifoForeignTradingViewDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchIfoMarketCalendar
	 * (vn.com.vndirect.domain.IfoMarketCalendarView)
	 */
	public SearchResult<IfoMarketCalendarView> searchIfoMarketCalendar(IfoMarketCalendarView ifoMarketCalendar,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchIfoMarketCalendar(ifoMarketCalendar:" + ifoMarketCalendar + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (ifoMarketCalendar == null) {
			throw new SystemException(LOCATION, "ifoMarketCalendar object is NULL");
		}
		try {

			SearchResult<IfoMarketCalendarView> result = ifoMarketCalendarViewDAO.searchIfoMarketCalendar(ifoMarketCalendar,
			        pagingInfo);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getRightsDateByMonth(vn
	 * .com.vndirect.domain.IfoMarketCalendarView)
	 */
	public Collection getRightsDateByMonth(IfoMarketCalendarView searchObjTemp) throws FunctionalException, SystemException {
		final String LOCATION = "getRightsDateByMonth(ifoMarketCalendar:" + searchObjTemp + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (searchObjTemp == null) {
			throw new SystemException(LOCATION, "searchObjTemp object is NULL");
		}
		try {
			Collection result = ifoMarketCalendarViewDAO.getRightsDateByMonth(searchObjTemp);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getEventDateByMonth(vn.
	 * com.vndirect.domain.IfoMarketCalendarView)
	 */
	public Collection getEventDateByMonth(IfoMarketCalendarView searchObjTemp) throws FunctionalException, SystemException {
		final String LOCATION = "getEventDateByMonth(ifoMarketCalendar:" + searchObjTemp + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (searchObjTemp == null) {
			throw new SystemException(LOCATION, "searchObjTemp object is NULL");
		}
		try {
			Collection result = ifoMarketCalendarViewDAO.getEventDateByMonth(searchObjTemp);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getIfoCompanyCalcViewBySymbol
	 * (vn.com.vndirect.domain.IfoCompanyCalcView)
	 */
	public IfoCompanyCalcView getIfoCompanyCalcViewBySymbol(IfoCompanyCalcView ifoCompanyCalcView) throws FunctionalException,
	        SystemException {
		final String LOCATION = "getIfoCompanyCalcViewBySymbol(ifoCompanyCalcView:" + ifoCompanyCalcView + ")";
		logger.debug(LOCATION + ":: BEGIN");
		if (ifoCompanyCalcView == null) {
			throw new FunctionalException(LOCATION, "ifoCompanyCalcView is NULL...");
		}
		try {
			IfoCompanyCalcView result = ifoCompanyCalcViewDAO.getIfoCompanyCalcViewBySymbol(ifoCompanyCalcView);
			logger.debug(LOCATION + ":: END");
			return result;
		} catch (FunctionalException fex) {
			throw fex;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchSSCFilling(vn.com
	 * .vndirect.domain.extend.SearchIfoDocument)
	 */
	public SearchResult<IfoDocument> searchSSCFilling(SearchIfoDocument ifoDocument, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException {
		final String LOCATION = "searchSSCFilling(ifoDocument:" + ifoDocument + ")";
		logger.debug(LOCATION + ":: BEGIN");
		if (ifoDocument == null) {
			throw new SystemException(LOCATION, "ifoDocument object is NULL");
		}
		try {
			SearchResult<IfoDocument> result = ifoDocumentDAO.searchSSCFilling(ifoDocument, pagingInfo);
			logger.debug("ifoDocument.getPagingIndex():" + ifoDocument.getPagingIndex());
			logger.debug(LOCATION + ":: END");
			return result;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getBalancesheetViewInfo
	 * (vn.com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List getBalancesheetViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "getBalancesheetViewInfo(IfoBalanceSheetSearch:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null || searchObject.getReportType() == null
		        || searchObject.getMoneyRate() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List searchResults = ifoBalancesheetViewDAO.findByCurrentCompany(searchObject);

			// reStructure finance data
			List results = this.reStructureFinanceReport(searchResults, searchObject.getReportType(),
			        searchObject.getMoneyRate(), BALANCESHEET_VIEW);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/**
	 * @param searchResults
	 * @param reportType
	 * @param moneyRate
	 * @param objectSearch
	 * @return
	 * @throws SystemException
	 */
	private List reStructureFinanceReport(List searchResults, String reportType, String moneyRate, String objectSearch)
	        throws SystemException {
		final String LOCATION = "reStructureFinanceReport()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		List results = new ArrayList();
		try {
			moneyRate = moneyRate.replaceAll(",", "");
			moneyRate = (moneyRate == null || moneyRate.trim().length() == 0 ? "1" : moneyRate);
			double divide = (new Double(moneyRate)).doubleValue();
			if (divide == 0)
				divide = 1;
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: divide: " + divide);

			FinanceReportForQuote elementResult = new FinanceReportForQuote();

			Long displayOrder = new Long(0);
			int count = 0;
			if (INCOME_VIEW.equalsIgnoreCase(objectSearch)) {
				IfoIncomeViewExt elementSearch;

				for (Iterator iter = searchResults.iterator(); iter.hasNext();) {
					elementSearch = (IfoIncomeViewExt) iter.next();

					if (!displayOrder.equals(elementSearch.getId().getDisplayOrder())) {
						results.add(elementResult);
						elementResult = new FinanceReportForQuote();

						elementResult.setCompanyId(elementSearch.getId().getCompanyId());
						elementResult.setReportType(elementSearch.getId().getReportType());
						elementResult.setStrFiscalDate1(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
						        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
						elementResult.setCurrencyCode(elementSearch.getId().getCurrencyCode());
						elementResult.setItemName(elementSearch.getId().getItemName());
						if (elementSearch.getStrNumericValue() == null
						        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
							elementResult.setStrNumericValue1(Constants.NULL_VALUE);
						} else {
							elementResult.setStrNumericValue1(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
							        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
						}
						elementResult.setDisplayOrder(elementSearch.getId().getDisplayOrder());
						elementResult.setDisplayLevel(VNDirectWebUtilities.getIntValuePositive(elementSearch.getId()
						        .getDisplayLevel()));
						elementResult.setLocale(elementSearch.getId().getLocale());

						displayOrder = elementSearch.getId().getDisplayOrder();
						count = 1;
					} else {
						if (count == 1) {
							elementResult.setStrFiscalDate2(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue2(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue2(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 2;
						} else if (count == 2) {
							elementResult.setStrFiscalDate3(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue3(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue3(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 3;
						} else if (count == 3) {
							elementResult.setStrFiscalDate4(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue4(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue4(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 4;
						} else if (count == 4) {
							elementResult.setStrFiscalDate5(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue5(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue5(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 0;
						}
					}
				}
				results.add(elementResult);

			} else if (BALANCESHEET_VIEW.equalsIgnoreCase(objectSearch)) {
				IfoBalancesheetViewExt elementSearch = new IfoBalancesheetViewExt();
				for (int i = 0; i < searchResults.size(); i++) {
					elementSearch = (IfoBalancesheetViewExt) searchResults.get(i);
					if (!displayOrder.equals(elementSearch.getId().getDisplayOrder())) {
						results.add(elementResult);
						elementResult = new FinanceReportForQuote();

						elementResult.setCompanyId(elementSearch.getId().getCompanyId());
						elementResult.setReportType(elementSearch.getId().getReportType());
						elementResult.setStrFiscalDate1(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
						        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
						elementResult.setCurrencyCode(elementSearch.getId().getCurrencyCode());
						elementResult.setItemName(elementSearch.getId().getItemName());
						if (elementSearch.getStrNumericValue() == null
						        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
							elementResult.setStrNumericValue1(Constants.NULL_VALUE);
						} else {
							elementResult.setStrNumericValue1(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
							        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
						}
						elementResult.setDisplayOrder(elementSearch.getId().getDisplayOrder());
						elementResult.setDisplayLevel(VNDirectWebUtilities.getIntValuePositive(elementSearch.getId()
						        .getDisplayLevel()));
						elementResult.setLocale(elementSearch.getId().getLocale());

						displayOrder = elementSearch.getId().getDisplayOrder();
						count = 1;
					} else {
						if (count == 1) {
							elementResult.setStrFiscalDate2(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue2(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue2(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 2;
						} else if (count == 2) {
							elementResult.setStrFiscalDate3(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue3(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue3(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 3;
						} else if (count == 3) {
							elementResult.setStrFiscalDate4(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue4(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue4(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 4;
						} else if (count == 4) {
							elementResult.setStrFiscalDate5(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue5(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue5(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 0;
						}
					}
				}
				results.add(elementResult);
				FinanceReportForQuote elementNext;
				for (int i = 0; i < results.size() - 1; i++) {
					elementResult = (FinanceReportForQuote) results.get(i);
					elementNext = (FinanceReportForQuote) results.get(i + 1);
					if (elementResult.getDisplayLevel() + 1 == elementNext.getDisplayLevel()) {
						elementResult.setHasChild(true);

					}
				}
			} else if (CASHFLOW_VIEW.equalsIgnoreCase(objectSearch)) {
				IfoCashflowViewExt elementSearch;

				for (Iterator iter = searchResults.iterator(); iter.hasNext();) {
					elementSearch = (IfoCashflowViewExt) iter.next();

					if (!displayOrder.equals(elementSearch.getId().getDisplayOrder())) {
						results.add(elementResult);
						elementResult = new FinanceReportForQuote();

						elementResult.setCompanyId(elementSearch.getId().getCompanyId());
						elementResult.setReportType(elementSearch.getId().getReportType());
						elementResult.setStrFiscalDate1(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
						        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
						elementResult.setCurrencyCode(elementSearch.getId().getCurrencyCode());
						elementResult.setItemName(elementSearch.getId().getItemName());
						if (elementSearch.getStrNumericValue() == null
						        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
							elementResult.setStrNumericValue1(Constants.NULL_VALUE);
						} else {
							elementResult.setStrNumericValue1(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
							        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
						}
						elementResult.setDisplayOrder(elementSearch.getId().getDisplayOrder());
						elementResult.setDisplayLevel(VNDirectWebUtilities.getIntValuePositive(elementSearch.getId()
						        .getDisplayLevel()));
						elementResult.setLocale(elementSearch.getId().getLocale());

						displayOrder = elementSearch.getId().getDisplayOrder();
						count = 1;
					} else {
						if (count == 1) {
							elementResult.setStrFiscalDate2(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue2(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue2(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 2;
						} else if (count == 2) {
							elementResult.setStrFiscalDate3(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue3(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue3(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 3;
						} else if (count == 3) {
							elementResult.setStrFiscalDate4(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue4(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue4(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 4;
						} else if (count == 4) {
							elementResult.setStrFiscalDate5(VNDirectDateUtils.dateToString(elementSearch.getId().getFiscalDate(),
							        VNDirectDateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1), reportType);
							if (elementSearch.getStrNumericValue() == null
							        || elementSearch.getStrNumericValue().trim().equalsIgnoreCase("")) {
								elementResult.setStrNumericValue5(Constants.NULL_VALUE);
							} else {
								elementResult.setStrNumericValue5(VNDirectWebUtilities.getStrDoubleWithScale((new Double(
								        elementSearch.getStrNumericValue())).doubleValue() / (divide), 0));
							}
							count = 0;
						}
					}
				}

				results.add(elementResult);
			}

			if (results.size() > 0) {
				results.remove(0);
			}

		} catch (SystemException sysex) {
			throw new SystemException(LOCATION + " reStructureFinanceReport error ", sysex);
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#fiscalYearListBalancesheet
	 * (vn.com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List fiscalYearListBalancesheet(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "fiscalYearListBalancesheet(IfoBalanceSheetSearch:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List results = ifoBalancesheetViewDAO.fiscalYearList(searchObject);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#fiscalQuarterBalancesheet
	 * (vn.com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public String fiscalQuarterBalancesheet(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "fiscalQuarterBalancesheet(IfoBalanceSheetSearch:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			String results = ifoBalancesheetViewDAO.fiscalQuarter(searchObject);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#fiscalYearListIncomeStatement
	 * (vn.com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List fiscalYearListIncomeStatement(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "fiscalYearListIncomeStatement(IfoBalanceSheetSearch:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List results = ifoIncomeViewDAO.fiscalYearList(searchObject);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getIncomeViewInfo(vn.com
	 * .vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List getIncomeViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "getIncomeViewInfo(criteriaObject:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null || searchObject.getReportType() == null
		        || searchObject.getMoneyRate() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {

			List searchResults = ifoIncomeViewDAO.findByCurrentCompany(searchObject);

			// reStructure finance data
			List results = this.reStructureFinanceReport(searchResults, searchObject.getReportType(),
			        searchObject.getMoneyRate(), INCOME_VIEW);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#fiscalYearListCashFlow(
	 * vn.com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List fiscalYearListCashFlow(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "fiscalYearListCashFlow(IfoBalanceSheetSearch:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List results = ifoCashflowViewDAO.fiscalYearList(searchObject);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#getCashflowViewInfo(vn.
	 * com.vndirect.domain.extend.IfoBalanceSheetSearch)
	 */
	public List getCashflowViewInfo(IfoBalanceSheetSearch searchObject) throws FunctionalException, SystemException {
		final String LOCATION = "getCashflowViewInfo(criteriaObject:" + searchObject + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchObject == null || searchObject.getCompanyId() == null || searchObject.getReportType() == null
		        || searchObject.getMoneyRate() == null) {
			throw new SystemException(LOCATION, "criteriaObject is NULL or EMPTY...");
		}
		try {
			List searchResults = ifoCashflowViewDAO.findByCurrentCompany(searchObject);

			// reStructure finance data
			List results = this.reStructureFinanceReport(searchResults, searchObject.getReportType(),
			        searchObject.getMoneyRate(), CASHFLOW_VIEW);

			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":: END");
			return results;
		} catch (SystemException sysex) {
			throw sysex;
		} catch (Exception ex) {
			logger.error(LOCATION, ex);
			throw new SystemException(LOCATION, ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchHistoricalPrice(vn
	 * .com.vndirect.domain.extend.SearchIfoSecPriceView)
	 */
	public SearchResult<IfoSecPriceView> searchHistoricalPrice(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchForeignTrading(searchIfoSecPriceView:" + searchMarketStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoSecPriceView is NULL...");
		}

		SearchResult<IfoSecPriceView> searchResult = ifoSecPriceViewDAO.searchHistoricalPrice(searchMarketStatisticsView,
		        pagingInfo);

		double chgIndex, pctIndex;
		IfoSecPriceView sec1, sec2;

		if (searchResult != null) {
			int size = searchResult.size();
			for (int i = 0; i < size - 1; i++) {
				sec1 = (IfoSecPriceView) searchResult.get(i);
				sec2 = (IfoSecPriceView) searchResult.get(i + 1);
				if (VNDirectWebUtilities.getHASTCExchange().equals(sec1.getId().getExchangeCode())) {
					chgIndex = (VNDirectWebUtilities.getDoubleValue(sec1.getId().getAveragePrice()) - VNDirectWebUtilities
					        .getDoubleValue(sec2.getId().getAveragePrice()));
					pctIndex = (VNDirectWebUtilities.getDoubleValue(sec2.getId().getAveragePrice()) == 0 ? 0
					        : (chgIndex / VNDirectWebUtilities.getDoubleValue(sec2.getId().getAveragePrice())) * 100);
				} else {
					chgIndex = (VNDirectWebUtilities.getDoubleValue(sec1.getId().getClosePrice()) - VNDirectWebUtilities
					        .getDoubleValue(sec2.getId().getClosePrice()));
					pctIndex = (VNDirectWebUtilities.getDoubleValue(sec2.getId().getClosePrice()) == 0 ? 0
					        : (chgIndex / VNDirectWebUtilities.getDoubleValue(sec2.getId().getClosePrice())) * 100);
				}
				sec1.setChgIndex(chgIndex);
				sec1.setPctIndex(pctIndex);
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return (searchResult == null ? new SearchResult<IfoSecPriceView>() : searchResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchTradingStatistics4Symbol
	 * (vn.com.vndirect.domain.extend.SearchIfoTradingStatisticsView)
	 */
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics4Symbol(
	        SearchMarketStatisticsView searchMarketStatisticsView, PagingInfo pagingInfo) throws FunctionalException,
	        SystemException {
		final String LOCATION = "searchTradingStatistics4Symbol(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchMarketStatisticsView == null || searchMarketStatisticsView.getSymbol() == null
		        || searchMarketStatisticsView.getSymbol().trim().length() == 0) {
			throw new SystemException(LOCATION, "searchMarketStatisticsView is NULL...");
		}

		SearchResult<IfoTradingStatisticsView> result = ifoTradingStatisticsViewDAO.searchTradingStatistics4Symbol(
		        searchMarketStatisticsView, pagingInfo);
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return (result == null ? new SearchResult<IfoTradingStatisticsView>() : result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchForeignTrading4Symbol
	 * (vn.com.vndirect.domain.extend.SearchMarketStatisticsView,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<IfoForeignTradingView> searchForeignTrading4Symbol(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchForeignTrading4Symbol(searchMarketStatisticsView:" + searchMarketStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchMarketStatisticsView == null || searchMarketStatisticsView.getSymbol() == null
		        || searchMarketStatisticsView.getSymbol().trim().length() == 0) {
			throw new SystemException(LOCATION, "searchIfoForeignTradingView is NULL...");
		}

		SearchResult<IfoForeignTradingView> result = ifoForeignTradingViewDAO.searchForeignTrading4Symbol(
		        searchMarketStatisticsView, pagingInfo);
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchTradingStatistics
	 * (vn.com.vndirect.domain.extend.SearchMarketStatisticsView,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<IfoTradingStatisticsView> searchTradingStatistics(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchTradingStatistics(searchIfoTradingStatisticsView:" + searchMarketStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoTradingStatisticsView is NULL...");
		}

		SearchResult<IfoTradingStatisticsView> result = ifoTradingStatisticsViewDAO.searchTradingStatistics(
		        searchMarketStatisticsView, pagingInfo);
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#searchForeignTrading(vn
	 * .com.vndirect.domain.extend.SearchMarketStatisticsView,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<IfoForeignTradingView> searchForeignTrading(SearchMarketStatisticsView searchMarketStatisticsView,
	        PagingInfo pagingInfo) throws FunctionalException, SystemException {
		final String LOCATION = "searchForeignTrading(searchIfoForeignTradingView:" + searchMarketStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchMarketStatisticsView == null) {
			throw new SystemException(LOCATION, "searchMarketStatisticsView is NULL...");
		}

		SearchResult<IfoForeignTradingView> result = ifoForeignTradingViewDAO.searchForeignTrading(searchMarketStatisticsView,
		        pagingInfo);
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#reportHistoricalPrice(vn
	 * .com.vndirect.domain.extend.SearchMarketStatisticsView)
	 */
	public SearchResult<IfoSecPriceViewExt> reportHistoricalPrice(SearchMarketStatisticsView searchIfoSecPriceView)
	        throws FunctionalException, SystemException {
		final String LOCATION = "reportHistoricalPrice(searchIfoSecPriceView:" + searchIfoSecPriceView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoSecPriceView == null) {
			throw new SystemException(LOCATION, "searchIfoSecPriceView is NULL...");
		}

		SearchResult<IfoSecPriceView> searchResult = ifoSecPriceViewDAO.reportHistoricalPrice(searchIfoSecPriceView);

		double chgIndex, pctIndex;
		IfoSecPriceView sec1, sec2;

		if (searchResult != null && searchResult.size() > 0) {
			int size = searchResult.size();
			for (int i = 0; i < size - 1; i++) {
				sec1 = (IfoSecPriceView) searchResult.get(i);
				sec2 = (IfoSecPriceView) searchResult.get(i + 1);
				chgIndex = (VNDirectWebUtilities.getDoubleValue(sec1.getId().getClosePrice()) - VNDirectWebUtilities
				        .getDoubleValue(sec2.getId().getClosePrice()));
				pctIndex = (VNDirectWebUtilities.getDoubleValue(sec2.getId().getClosePrice()) == 0 ? 0
				        : (chgIndex / VNDirectWebUtilities.getDoubleValue(sec2.getId().getClosePrice())) * 100);

				sec1.setChgIndex(chgIndex);
				sec1.setPctIndex(pctIndex);
			}
		}

		SearchResult<IfoSecPriceViewExt> reportRows = new SearchResult<IfoSecPriceViewExt>();
		if (searchResult != null && searchResult.size() > 0) {
			int size = searchResult.size();
			IfoSecPriceViewExt ifoSecPriceViewExt;
			for (int i = 0; i < size; i++) {
				IfoSecPriceView ifoSecPriceView = (IfoSecPriceView) searchResult.get(i);
				IfoSecPriceViewId ifoSecPriceViewId = ifoSecPriceView.getId();

				if (ifoSecPriceViewId != null) {
					ifoSecPriceViewExt = new IfoSecPriceViewExt();

					ifoSecPriceViewExt.setExchangeCode(ifoSecPriceViewId.getExchangeCode());
					ifoSecPriceViewExt.setSymbol(ifoSecPriceViewId.getSymbol());

					try {
						ifoSecPriceViewExt.setStrTransDate(DateUtils.dateToString(ifoSecPriceViewId.getTransDate(),
						        DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
					} catch (Exception e) {
						ifoSecPriceViewExt.setStrTransDate("");
					}
					ifoSecPriceViewExt.setStrOpenPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getOpenPrice(), 2));
					ifoSecPriceViewExt.setStrHighPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getHighPrice(), 2));
					ifoSecPriceViewExt.setStrLowPrice(VNDirectWebUtilities.getStrDoubleWithScale(ifoSecPriceViewId.getLowPrice(),
					        2));
					ifoSecPriceViewExt.setStrClosePrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getClosePrice(), 2));
					ifoSecPriceViewExt.setStrAveragePrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAveragePrice(), 2));
					ifoSecPriceViewExt.setStrVolume(VNDirectWebUtilities.getStrDoubleWithScale(ifoSecPriceViewId.getVolume(), 2));
					ifoSecPriceViewExt.setStrAdOpenPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAdOpenPrice(), 2));
					ifoSecPriceViewExt.setStrAdClosePrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAdClosePrice(), 2));
					ifoSecPriceViewExt.setStrAdHighPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAdHighPrice(), 2));
					ifoSecPriceViewExt.setStrAdLowPrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAdLowPrice(), 2));
					ifoSecPriceViewExt.setStrAdAveragePrice(VNDirectWebUtilities.getStrDoubleWithScale(
					        ifoSecPriceViewId.getAdAveragePrice(), 2));

					ifoSecPriceViewExt
					        .setStrChgIndex(VNDirectWebUtilities.getStrDoubleWithScale(ifoSecPriceView.getChgIndex(), 2));
					ifoSecPriceViewExt.setStrPctIndex(VNDirectWebUtilities.getStrDoubleWithScale2(ifoSecPriceView.getPctIndex()));

					reportRows.add(ifoSecPriceViewExt);
				}
			}
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END --- reportRows: " + reportRows.size());
		return reportRows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IListedMarketManager#reportTradingStatistics
	 * (vn.com.vndirect.domain.extend.SearchMarketStatisticsView)
	 */
	public SearchResult<IfoTradingStatisticsViewExt> reportTradingStatistics(
	        SearchMarketStatisticsView searchIfoTradingStatisticsView) throws FunctionalException, SystemException {
		final String LOCATION = "reportTradingStatistics(searchIfoTradingStatisticsView:" + searchIfoTradingStatisticsView + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		if (searchIfoTradingStatisticsView == null) {
			throw new SystemException(LOCATION, "searchIfoTradingStatisticsView is NULL...");
		}

		SearchResult<IfoTradingStatisticsView> searchResult = ifoTradingStatisticsViewDAO
		        .reportTradingStatistics(searchIfoTradingStatisticsView);

		SearchResult<IfoTradingStatisticsViewExt> reportRows = new SearchResult<IfoTradingStatisticsViewExt>();
		if (searchResult != null && searchResult.size() > 0) {
			int size = searchResult.size();
			IfoTradingStatisticsViewExt ifoTradingStatisticsViewExt;
			for (int i = 0; i < size; i++) {
				IfoTradingStatisticsView ifoTradingStatisticsView = (IfoTradingStatisticsView) searchResult.get(i);
				IfoTradingStatisticsViewId ifoTradingStatisticsViewId = ifoTradingStatisticsView.getId();

				if (ifoTradingStatisticsViewId != null) {
					ifoTradingStatisticsViewExt = new IfoTradingStatisticsViewExt();

					ifoTradingStatisticsViewExt.setFloorCode(ifoTradingStatisticsViewId.getFloorCode());
					ifoTradingStatisticsViewExt.setSecCode(ifoTradingStatisticsViewId.getSecCode());

					try {
						ifoTradingStatisticsViewExt.setStrTransDate(DateUtils.dateToString(
						        ifoTradingStatisticsViewId.getTransDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
					} catch (Exception e) {
						ifoTradingStatisticsViewExt.setStrTransDate("");
					}
					ifoTradingStatisticsViewExt.setStrOpenPrice(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getOpenPrice()));
					ifoTradingStatisticsViewExt.setStrHighPrice(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getHighPrice()));
					ifoTradingStatisticsViewExt.setStrLowPrice(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getLowPrice()));
					ifoTradingStatisticsViewExt.setStrClosePrice(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getClosePrice()));
					ifoTradingStatisticsViewExt.setStrEveragePrice(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getEveragePrice()));
					ifoTradingStatisticsViewExt.setStrBidOrder(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getBidOrder()));
					ifoTradingStatisticsViewExt.setStrBidVolumn(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getBidVolumn()));
					ifoTradingStatisticsViewExt.setStrOfferOrder(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getOfferOrder()));
					ifoTradingStatisticsViewExt.setStrOfferVolumn(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getOfferVolumn()));
					ifoTradingStatisticsViewExt.setStrTotalVolumn(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getTotalVolumn()));
					ifoTradingStatisticsViewExt.setStrTotalValue(VNDirectWebUtilities
					        .getStrDoubleWithScale2(ifoTradingStatisticsViewId.getTotalValue()));

					long netChange = VNDirectWebUtilities.getLongValue(ifoTradingStatisticsViewId.getClosePrice())
					        - VNDirectWebUtilities.getLongValue(ifoTradingStatisticsViewId.getOpenPrice());
					ifoTradingStatisticsViewExt.setStrNetChange(VNDirectWebUtilities.getStrLong(netChange));
					long change = VNDirectWebUtilities.getLongValue(ifoTradingStatisticsViewId.getBidVolumn())
					        - VNDirectWebUtilities.getLongValue(ifoTradingStatisticsViewId.getOfferVolumn());
					ifoTradingStatisticsViewExt.setStrChange(VNDirectWebUtilities.getStrLong(change));

					reportRows.add(ifoTradingStatisticsViewExt);
				}
			}
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return reportRows;
	}

}