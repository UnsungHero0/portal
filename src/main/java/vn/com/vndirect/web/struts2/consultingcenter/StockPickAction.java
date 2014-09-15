package vn.com.vndirect.web.struts2.consultingcenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.business.INewsInfoManager;
import vn.com.vndirect.business.IStockPickManager;
import vn.com.vndirect.business.ITradingManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.extend.PagingInfo;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.vndirect.domain.struts2.consultingcenter.StockPickModel;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author minh.nguyen
 * 
 */
public class StockPickAction extends ActionSupport implements ModelDriven<StockPickModel> {
	private static final String INTRO = "intro";

	private static final String DA_ACCOUNT_TYPE_PREFIX = "1";

	private static Logger logger = Logger.getLogger(StockPickAction.class);

	private StockPickModel model = new StockPickModel();

	private IStockPickManager stockPickManager;
	private INewsInfoManager newsInfoManager;
	private ITradingManager tradingManager;

	public String initializePage() {
		logger.debug("initializePage()");

		try {
			model.setPageTitle(this.getText("StockPick.Page.Title"));
			model.setPageDescription(this.getText("StockPick.Page.Desc"));
			model.setPageKeywords(this.getText("StockPick.Page.Key"));

			if(!isViewable()){
				return INTRO;
			}

			String strDate = model.getDate();
			Date date = null;
			if (StringUtils.isNotEmpty(strDate)) {
				date = DateUtils.stringToDate(strDate, "yyyyMMdd");
			} else {
				SearchResult<Date> newsestReportDates = stockPickManager.getNewsestApprovedStockPickReportDate(new PagingInfo(1));
				if (newsestReportDates != null && newsestReportDates.size() > 0) {
					if (newsestReportDates.size() > 0) {
						date = newsestReportDates.get(0);
					}
				}
			}

			processData(date);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}
	
	private boolean isViewable() throws SystemException{
		String customerId = SessionManager.OnlineUsers.getCurrentOnlineUser().getBOCustId();
		
		return isDAUser(customerId) || isRegistered(customerId); 
	}

	/**
	 * get demo news
	 */
	public String getReportBodyOnly() throws Exception {
		try {
			SearchIfoNews ifoNews = stockPickManager.getDemoNews();
			if (ifoNews != null) {
				ifoNews.setStrNewsDate(DateUtils.dateToString(ifoNews.getNewsDate(), "dd/MM/yyyy"));
				model.setIfoNews(ifoNews);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}

	public String getRelatedReportDates() throws Exception {
		logger.debug("getRelatedReportDates()");

		try {
			String strDate = model.getDate();
			Date date = null;
			if (StringUtils.isNotEmpty(strDate)) {
				try {
					date = DateUtils.stringToDate(strDate, "dd/MM/yyyy");
				} catch (Exception e) {
					date = Calendar.getInstance().getTime();
				}
			} else {
				date = Calendar.getInstance().getTime();
			}

			final List<Date> relatedReportDates = stockPickManager.getRelatedReportDates(new PagingInfo(5), date);
			List<String> datesByyyyymmddFormat = new ArrayList<String>();
			List<String> datesByddMMyyyyFormat = new ArrayList<String>();
			if (relatedReportDates != null && relatedReportDates.size() > 0) {
				for (Date d : relatedReportDates) {
					datesByddMMyyyyFormat.add(DateUtils.dateToString(d, "dd/MM/yyyy"));
					datesByyyyymmddFormat.add(DateUtils.dateToString(d, "yyyyMMdd"));
				}
				model.setDatesByddMMyyyyFormat(datesByddMMyyyyFormat);
				model.setDatesByyyyymmddFormat(datesByyyyymmddFormat);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}

	public String getNewerReportDates() throws Exception {
		logger.debug("getNewerReportDates()");

		try {
			String strDate = model.getDate();
			Date date = null;
			if (StringUtils.isNotEmpty(strDate)) {
				try {
					date = DateUtils.stringToDate(strDate, "dd/MM/yyyy");
				} catch (Exception e) {
					date = Calendar.getInstance().getTime();
				}
			} else {
				date = Calendar.getInstance().getTime();
			}

			final List<Date> relatedReportDates = stockPickManager.getNewerReportDates(new PagingInfo(5), date);
			List<String> datesByyyyymmddFormat = new ArrayList<String>();
			List<String> datesByddMMyyyyFormat = new ArrayList<String>();
			if (relatedReportDates != null && relatedReportDates.size() > 0) {
				for (Date d : relatedReportDates) {
					datesByddMMyyyyFormat.add(DateUtils.dateToString(d, "dd/MM/yyyy"));
					datesByyyyymmddFormat.add(DateUtils.dateToString(d, "yyyyMMdd"));
				}
				model.setDatesByddMMyyyyFormat(datesByddMMyyyyFormat);
				model.setDatesByyyyymmddFormat(datesByyyyymmddFormat);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}

	public String getNewsestMarketNews() throws Exception {
		logger.debug("getNewsestMarketNews()");

		try {
			final SearchIfoNews searchIfoNews = new SearchIfoNews();
			searchIfoNews.setStatus(Constants.IServerConfig.DataRef.ItemCodes.NewsStatus.APPROVED);
			searchIfoNews.setNewsType(Constants.IServerConfig.DataRef.ItemCodes.NewsType.VNDS_DAILY_NEWS);
			searchIfoNews.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			final SearchResult<SearchIfoNews> result = newsInfoManager.getMartketNews(searchIfoNews, null, new PagingInfo(1));
			if (result != null && result.size() > 0) {
				SearchIfoNews news = result.get(0);
				news.setStrNewsDate(DateUtils.dateToString(result.get(0).getNewsDate(), "dd/MM/yyyy"));

				model.setMarketNews(news);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}

	/**
	 * check all accounts of user
	 */
	private boolean isDAUser(String customerId) {
		try {
			List<Account> accounts = tradingManager.getAccountRecords(customerId);
			if (accounts != null && accounts.size() > 0) {
				for (Account account : accounts) {
					if (account.getContractType().startsWith(DA_ACCOUNT_TYPE_PREFIX)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return false;
	}

	private boolean isRegistered(String customerId) throws SystemException {
		return stockPickManager.getListCustomerId().contains(customerId);
	}
	
	@Override
	public StockPickModel getModel() {
		return model;
	}

	public void setStockPickManager(IStockPickManager stockPickManager) {
		this.stockPickManager = stockPickManager;
	}

	private void processData(Date date) throws Exception {
		if (date == null) {
			throw new FunctionalException("date is null .... ");
		}

		SearchResult<SearchIfoNews> searchIfoNews = stockPickManager.getAllNewsByDate(date, new PagingInfo());
		if(searchIfoNews == null || searchIfoNews.size() == 0){
			searchIfoNews = stockPickManager.getCommonNewsByDate(date, new PagingInfo());
			model.setCommonNews(true);
		}

		if (searchIfoNews != null && searchIfoNews.size() > 0) {
			model.setSearchIfoNews(searchIfoNews);

			// get strDate
			String strDate = DateUtils.dateToString(date, "dd/MM/yy");
			date = DateUtils.stringToDate(strDate, "dd/MM/yy");
			model.setDate(DateUtils.dateToString(date, "dd/MM/yyyy"));
		}
	}

	public void setNewsInfoManager(INewsInfoManager newsInfoManager) {
		this.newsInfoManager = newsInfoManager;
	}

	public void setTradingManager(ITradingManager tradingManager) {
		this.tradingManager = tradingManager;
	}
	
	public String registerCustomer() {
		logger.debug("registerCustomer()");
		try {
			String customerId = SessionManager.OnlineUsers.getCurrentOnlineUser().getBOCustId();
			if (StringUtils.isNotEmpty(customerId) && !stockPickManager.isExistedCustomer(customerId)) {
				stockPickManager.saveRegistCustomer(customerId);
				model.setRegisterStatus(getText("StockPick.Register.Success"));
			} else {
				model.setRegisterStatus(getText("StockPick.Register.UnSuccess"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.setRegisterStatus("System Error");
		}
		return SUCCESS;
	}
}
