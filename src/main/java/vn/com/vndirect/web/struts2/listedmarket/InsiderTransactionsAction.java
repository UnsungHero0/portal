/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Sep 23, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoInsiderTransactionView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.listedmarket.InsiderTransactionsModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 15, 2010 11:29:47 AM
 */
@SuppressWarnings("serial")
public class InsiderTransactionsAction extends ActionSupport implements ModelDriven<InsiderTransactionsModel> {
	private static Logger logger = Logger.getLogger(InsiderTransactionsAction.class);
	private InsiderTransactionsModel model = new InsiderTransactionsModel();
	
	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @return the model
	 */
	public InsiderTransactionsModel getModel() {
		return model;
	}

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public String viewInsiderTransAction() throws FunctionalException, SystemException {
		final String LOCATION = "viewInsiderTransAction()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			CurrentCompanyForQuote currentComp;
			String symbol = model.getSymbol();
			if (StringUtils.isEmpty(symbol)) {
				symbol = SessionManager.getSymbolCompany();
			}
			if(symbol == null){
				return ERROR;
			}
			currentComp = quotesManager.quickSearchQuotes(symbol, I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
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
			breadcrumbs.add(this.getText("web.button.MaijorHoldersAction.InsiderTrans"));
			breadcrumbs.add(this.getText("home.topMenu.analysis"));
			breadcrumbs.add("ttptRoot");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("tong-quan/" + symbol.toLowerCase() + ".shtml");
			breadcrumbs.add(this.getText("analysis.stockInfo.ownership"));
			breadcrumbs.add("co-dong-chinh/" + symbol.toLowerCase() + ".shtml");
			model.setBreadcrumbs(breadcrumbs);

			IfoCompanyNameViewId ifoCompanyNameViewId = model.getIfoCompanyNameViewId();

			if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
				ifoCompanyNameViewId = new IfoCompanyNameViewId(currentComp.getCompanyId());
			}

			ifoCompanyNameViewId.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			List<IfoInsiderTransactionView> ifoInsiderTransactionViewList = quotesManager.getInsiderTransactions(ifoCompanyNameViewId);
			model.setIfoInsiderTransactionViewList(ifoInsiderTransactionViewList);
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
