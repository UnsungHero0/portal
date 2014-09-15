package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IListedMarketManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.IfoMarketCalendarView;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.struts2.listedmarket.MarketCalendarModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class MarketCalendarAction extends ActionSupport implements ModelDriven<MarketCalendarModel> {

	private static final long serialVersionUID = 982199569762445881L;
	private static Logger logger = Logger.getLogger(MarketCalendarAction.class);

	private MarketCalendarModel model = new MarketCalendarModel();
	
	@Autowired
	private IListedMarketManager listedMarketManager;

	public void setModel(MarketCalendarModel model) {
		this.model = model;
	}

	public MarketCalendarModel getModel() {
		return model;
	}

	public void setListedMarketManager(IListedMarketManager listedMarketManager) {
		this.listedMarketManager = listedMarketManager;
	}

	public String viewMarketCalendar() throws FunctionalException, SystemException {
		final String LOCATION = "viewMarketCalendar()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			IfoMarketCalendarView searchObj = model.getIfoMarketCalendar();
			Calendar calendar = Calendar.getInstance();

			if ("TO_DAY".equals(searchObj.getSearchTypeOfDate())) {
				searchObj.setRightsDate(DateUtils.getCurrentDate());
				model.setSrtRightsDate(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD));
				model.setRightsDateStr(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
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
			try {
				searchObj.setRightsDate(DateUtils.stringToDate(model.getSrtRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD));
				model.setRightsDateStr(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			} catch (Exception e) {
			}

			searchObj.setLocal(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			PagingInfo pagingInfo = model.getPagingInfo();
			pagingInfo.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_PER_LIST)));

			if (searchObj.getSymbol() == null && model.getSymbol() != null) {
				searchObj.setSymbol(model.getSymbol().toUpperCase());
				model.setSymbol(null);
			}

			SearchResult<IfoMarketCalendarView> result = listedMarketManager.searchIfoMarketCalendar(searchObj, pagingInfo);

			if (result == null || result.size() == 0) {
				this.addActionError(getText("web.label.messages.norecord"));
			}

			if (searchObj.getRightsDate() == null) {
				calendar.setTime(DateUtils.getCurrentDate());
			} else {
				calendar.setTime(searchObj.getRightsDate());
			}

			Date currentTime = calendar.getTime();

			IfoMarketCalendarView searchObjTemp = new IfoMarketCalendarView();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
			searchObjTemp.setFromRightsDate(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
			searchObjTemp.setToRightsDate(calendar.getTime());

			Collection listEvents = listedMarketManager.getRightsDateByMonth(searchObjTemp);

			calendar.setTime(currentTime);
			StringBuffer strListRightsDate = new StringBuffer("{");
			strListRightsDate.append(calendar.get(Calendar.YEAR)).append("." + calendar.get(Calendar.MONTH));
			strListRightsDate.append(" : ");
			if (listEvents != null && listEvents.size() > 0) {
				List listSpecialDay = new ArrayList();
				Iterator iter = listEvents.iterator();
				while (iter.hasNext()) {
					calendar.setTime((Date) iter.next());
					listSpecialDay.add(new Integer(calendar.get(Calendar.DAY_OF_MONTH)));
				}
				strListRightsDate.append(listSpecialDay.toString());
			} else {
				strListRightsDate.append("[]");
			}
			strListRightsDate.append("}");

			model.setEventDay(strListRightsDate.toString());
			logger.debug("result:" + result);
			request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setPagingInfo((PagingInfo) result.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.news.marketCalendar"));
		breadcrumbs.add(this.getText("home.topMenu.analysis"));
		breadcrumbs.add("ttptRoot");
		breadcrumbs.add(this.getText("home.topMenu.analysis.news"));
		breadcrumbs.add("/tin-trong-nuoc.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
	}

	public String viewMarketCalendarVndirect() throws FunctionalException, SystemException {
		final String LOCATION = "viewMarketCalendar()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		// set symbol VND
		model.setSymbol("VND");

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			IfoMarketCalendarView searchObj = model.getIfoMarketCalendar();
			Calendar calendar = Calendar.getInstance();

			if ("TO_DAY".equals(searchObj.getSearchTypeOfDate())) {
				searchObj.setRightsDate(DateUtils.getCurrentDate());
				model.setSrtRightsDate(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD));
				model.setRightsDateStr(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
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
			try {
				searchObj.setRightsDate(DateUtils.stringToDate(model.getSrtRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD));
				model.setRightsDateStr(DateUtils.dateToString(searchObj.getRightsDate(),
						DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY));
			} catch (Exception e) {
			}

			searchObj.setLocal(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			PagingInfo pagingInfo = model.getPagingInfo();
			pagingInfo.setOffset(Integer.parseInt(ServerConfig.getOnlineValue(Constants.IServerConfig.Paging.ITEMS_PER_LIST)));

			if (searchObj.getSymbol() == null && model.getSymbol() != null) {
				searchObj.setSymbol(model.getSymbol().toUpperCase());
				model.setSymbol(null);
			}

			SearchResult<IfoMarketCalendarView> result = listedMarketManager.searchIfoMarketCalendar(searchObj, pagingInfo);

			if (result == null || result.size() == 0) {
				this.addActionError(getText("web.label.messages.norecord"));
			}

			if (searchObj.getRightsDate() == null) {
				calendar.setTime(DateUtils.getCurrentDate());
			} else {
				calendar.setTime(searchObj.getRightsDate());
			}

			Date currentTime = calendar.getTime();

			IfoMarketCalendarView searchObjTemp = new IfoMarketCalendarView();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
			searchObjTemp.setFromRightsDate(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
			searchObjTemp.setToRightsDate(calendar.getTime());

			Collection listEvents = listedMarketManager.getRightsDateByMonth(searchObjTemp);

			calendar.setTime(currentTime);
			StringBuffer strListRightsDate = new StringBuffer("{");
			strListRightsDate.append(calendar.get(Calendar.YEAR)).append("." + calendar.get(Calendar.MONTH));
			strListRightsDate.append(" : ");
			if (listEvents != null && listEvents.size() > 0) {
				List listSpecialDay = new ArrayList();
				Iterator iter = listEvents.iterator();
				while (iter.hasNext()) {
					calendar.setTime((Date) iter.next());
					listSpecialDay.add(new Integer(calendar.get(Calendar.DAY_OF_MONTH)));
				}
				strListRightsDate.append(listSpecialDay.toString());
			} else {
				strListRightsDate.append("[]");
			}
			strListRightsDate.append("}");

			model.setEventDay(strListRightsDate.toString());
			logger.debug("result:" + result);
			request.setAttribute(Constants.Paging.DEFAULT_KEY, result);
			model.setPagingInfo((PagingInfo) result.getPagingInfo());
			model.setSearchResult(result);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}

		// breadcrumbs
		ArrayList<String> breadcrumbs = new ArrayList<String>();
		breadcrumbs.add(this.getText("analysis.news.marketCalendar"));
		breadcrumbs.add(this.getText("br.vnd"));
		breadcrumbs.add("ttvndRoot");
		breadcrumbs.add(this.getText("br.ir"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
		breadcrumbs.add(this.getText("br.ir.news"));
		breadcrumbs.add("/quan-he-co-dong-vndirect/cong-bo-thong-tin.shtml");
		model.setBreadcrumbs(breadcrumbs);

		doSEOpage();
		return SUCCESS;
	}

	private void doSEOpage() {
		model.setPageTitle(this.getText("analysis.news.calendar.title"));
		model.setPageDescription(this.getText("analysis.news.description"));
		model.setPageKeywords(this.getText("analysis.news.calendar.keyWords"));
	}
}
