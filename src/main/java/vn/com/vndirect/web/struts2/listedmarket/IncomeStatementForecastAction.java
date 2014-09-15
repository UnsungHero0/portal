package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoEstimateView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.listedmarket.IncomeStatementForecastModel;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class IncomeStatementForecastAction extends ActionSupport implements ModelDriven<IncomeStatementForecastModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3400756931532989263L;

	private static Logger logger = Logger.getLogger(IncomeStatementForecastAction.class);

	private IncomeStatementForecastModel model = new IncomeStatementForecastModel();

	@Autowired
	private IQuotesManager quotesManager;

	public static final String LIST_YEAR_KEY = "$LIST_INCOME_YEAR_KEY$";

	public IQuotesManager getQuotesManager() {
		return quotesManager;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @return the model
	 */
	public IncomeStatementForecastModel getModel() {
		return model;
	}

	public String viewIncomeStatementForecast() throws Exception {
		final String LOCATION = "viewIncomeStatementForecast()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::BEGIN");
		}

		try {
			CurrentCompanyForQuote currentComp;
			String symbolCompany = model.getSymbol();
			if (StringUtils.isEmpty(symbolCompany)) {
				symbolCompany= SessionManager.getSymbolCompany();
			} 
			if(symbolCompany == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbolCompany, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (currentComp == null) {
				return ERROR;
			}
			SessionManager.setSymbolCompany(currentComp.getSymbol());
			// do SEO on-page
			String title = "VNDIRECT-" + this.getText("analysis.stockInfo.company.snapshot") + " | ";
			if (I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()).equalsIgnoreCase("VN")) {
				title += currentComp.getCompanyName();
			} else {
				title += currentComp.getCompanyFullName();
			}
			title += "-" + currentComp.getSymbol();
			model.setPageTitle(title);
			model.setPageDescription(this.getText("analysis.stockInfo.desc") + currentComp.getSymbol());
			String[] keywords = this.getText("analysis.stockInfo.keywords").trim().split(",");
			String dynamicKeyword = "";
			for (int i = 0; i < keywords.length; i++) {
				dynamicKeyword += keywords[i] + " " + currentComp.getSymbol();
				if (i != keywords.length - 1) {
					dynamicKeyword += ",";
				}
			}
			model.setPageKeywords(dynamicKeyword);

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("analysis.stockInfo.company.forecast"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.company"));
			breadcrumbs.add("tong-quan/" + model.getSymbol().toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			HttpServletRequest request = ServletActionContext.getRequest();
			IfoEstimateView ifoEstimateView = new IfoEstimateView();
			ifoEstimateView.setLocale(I18NUtility.getCurrentLocale(request.getSession()));
			ifoEstimateView.setCompanyId(currentComp.getCompanyId());
			List listYear = (List) model.getFromCache(LIST_YEAR_KEY);
			if (listYear == null) {
				listYear = quotesManager.fiscalYearList(ifoEstimateView);
			}
			//Set searchDate to null by default if searchDate is not in list of year.
			String searchDate = model.getSearchDate();
			if(listYear.contains(searchDate)){
				ifoEstimateView.setSearchDate(searchDate);
			} else {
				ifoEstimateView.setSearchDate(null);
			}
			
			model.addToCache(LIST_YEAR_KEY, listYear);
			model.setYearList(listYear);
			if ((listYear.size() > 0) && (ifoEstimateView.getSearchDate() == null)) {
				// Date selectDate = new Date(DateUtils.stringToDate((String)listYear.get(0), "yyyy").getTime());
				// model.getIfoEstimateView().setFiscalDate(selectDate);
				if (ifoEstimateView.getSearchDate() == null) {
					ifoEstimateView.setSearchDate((String) listYear.get(0));
					searchDate = (String) listYear.get(0);
				} else
					ifoEstimateView.setSearchDate(searchDate);
			}
			// ifoEstimateView.setSearchDate(model.getIfoEstimateView().getSearchDate());

			ifoEstimateView = quotesManager.getIfoEstimateView(ifoEstimateView);
			model.setIfoEstimateView(ifoEstimateView);
			model.setSymbol(currentComp.getSymbol());
			model.getIfoEstimateView().setSearchDate(searchDate);
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + "::END");
		}
		return SUCCESS;
	}

}
