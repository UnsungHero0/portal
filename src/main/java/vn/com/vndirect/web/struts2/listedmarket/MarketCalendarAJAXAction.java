package vn.com.vndirect.web.struts2.listedmarket;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.struts2.listedmarket.MarketCalendarAJAXModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarketCalendarAJAXAction extends ActionSupport implements ModelDriven<MarketCalendarAJAXModel> {

	private static final long serialVersionUID = -4138364523805795347L;

	private static Logger logger = Logger.getLogger(MarketCalendarAJAXAction.class);

	private MarketCalendarAJAXModel model = new MarketCalendarAJAXModel();
	
	@Autowired
	private IListedMarketManager listedMarketManager;

	public MarketCalendarAJAXModel getModel() {
		return model;
	}

	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	/**
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String executeGetMarketCalendar() throws FunctionalException, SystemException {
		final String LOCATION = "executeGetMarketCalendar()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			IfoMarketCalendarView searchObj = new IfoMarketCalendarView();
			String temp;
			temp = model.getSymbol();
			if (temp != null && temp.trim().length() > 0) {
				searchObj.setSymbol(temp.trim());
			}
			temp = model.getSearchTypeOfDate();
			if (temp != null && temp.trim().length() > 0) {
				searchObj.setSearchTypeOfDate(temp.trim());
			}
			temp = model.getEventType();
			if (temp != null && temp.trim().length() > 0) {
				searchObj.setEventType(temp.trim());
			}
			temp = model.getStrRightsDate();

			Calendar calendar = Calendar.getInstance();
			if ("TO_DAY".equals(searchObj.getSearchTypeOfDate())) {
				searchObj.setRightsDate(DateUtils.getCurrentDate());
				model.setStrRightsDate(DateUtils.dateToString(searchObj.getRightsDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD));
			} else if ("THIS_WEEK".equals(searchObj.getSearchTypeOfDate())) {
				calendar.setTime(DateUtils.getCurrentDate());
				calendar.set(Calendar.DAY_OF_WEEK, calendar.getMinimum(Calendar.DAY_OF_WEEK));
				searchObj.setFromRightsDate(new Date(calendar.getTimeInMillis()));

				calendar.set(Calendar.DAY_OF_WEEK, calendar.getMaximum(Calendar.DAY_OF_WEEK));
				searchObj.setToRightsDate(new Date(calendar.getTimeInMillis()));
			} else if ("THIS_MONTH".equals(searchObj.getSearchTypeOfDate())) {
				calendar.setTime(DateUtils.getCurrentDate());
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
				searchObj.setFromRightsDate(new Date(calendar.getTimeInMillis()));

				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
				searchObj.setToRightsDate(new Date(calendar.getTimeInMillis()));
			}
			searchObj.setLocal(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			PagingInfo pagingInfo = model.getPagingInfo();

			SearchResult<IfoMarketCalendarView> result = listedMarketManager.searchIfoMarketCalendar(searchObj, pagingInfo);

			request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setPagingInfo((PagingInfo)result.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}
}
