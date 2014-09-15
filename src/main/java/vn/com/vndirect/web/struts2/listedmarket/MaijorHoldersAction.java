package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoMaijorHolderView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.struts2.listedmarket.MaijorHoldersModel;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 12, 2010 10:17:27 AM
 */
@SuppressWarnings("serial")
public class MaijorHoldersAction extends ActionSupport implements ModelDriven<MaijorHoldersModel> {
	private static Logger logger = Logger.getLogger(MaijorHoldersAction.class);

	/* data model */
	private MaijorHoldersModel model = new MaijorHoldersModel();

	@Autowired
	private IQuotesManager quotesManager;

	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	/**
	 * @return the model
	 */
	public MaijorHoldersModel getModel() {
		return model;
	}

	@SuppressWarnings("unchecked")
	public String viewMaijorHolders() throws FunctionalException, SystemException {
		final String LOCATION = "viewMaijorHolders()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			String symbol = model.getSymbol();
			CurrentCompanyForQuote currentComp;
			if (StringUtils.isEmpty(symbol)) {
				symbol = SessionManager.getSymbolCompany();
			} 
			if(symbol== null){
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
			breadcrumbs.add(this.getText("web.button.MaijorHoldersAction.MaijorHolder"));
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

			if (ifoCompanyNameViewId != null && ifoCompanyNameViewId.getCompanyId() != null) {
				ifoCompanyNameViewId.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				Map<String, Object> majorHoldersMap = quotesManager.getMajorHolders(ifoCompanyNameViewId);
				model.setIfoMaijorHolderViewList((List<IfoMaijorHolderView>) majorHoldersMap.get(Constants.MAJOR_HOLDERS_ITEMS.MAJOR_HOLDERS));
				model.setIfoBreakdownViewId((IfoBreakdownViewId) majorHoldersMap.get(Constants.MAJOR_HOLDERS_ITEMS.BREAK_DOWN));
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public String viewCompanyBreakdown() throws FunctionalException, SystemException {
		final String LOCATION = "viewMaijorHolders()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			IfoCompanyNameViewId ifoCompanyNameViewId = model.getIfoCompanyNameViewId();
			CurrentCompanyForQuote currentCompanyForQuote = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
				ifoCompanyNameViewId = new IfoCompanyNameViewId(currentCompanyForQuote.getCompanyId());
			}

			IfoBreakdownViewId breakdownView = null;
			if (ifoCompanyNameViewId != null && ifoCompanyNameViewId.getCompanyId() != null) {
				ifoCompanyNameViewId.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				breakdownView = quotesManager.getCompanyBreakdown(ifoCompanyNameViewId);
			}

			if (breakdownView != null) {
				// Double internalManagement = breakdownView.getInternalManagement();
				// Double strategicInvestor = breakdownView.getStrategicInvestor();
				Double stateOwnership = breakdownView.getStateOwnership();
				stateOwnership = (stateOwnership == null || stateOwnership < 0 || stateOwnership > 101 ? Double.valueOf(0) : stateOwnership);

				Double foreignOwnership = breakdownView.getForeignOwnership();
				foreignOwnership = (foreignOwnership == null || foreignOwnership < 0 || stateOwnership > 101 ? Double.valueOf(0) : foreignOwnership);

				Double other = breakdownView.getOther();
				other = (other == null || other < 0 || other > 101 ? Double.valueOf(0) : other);

				// +++ check %
				breakdownView.setStateOwnership(stateOwnership);
				breakdownView.setForeignOwnership(foreignOwnership);
				breakdownView.setOther(other);
				// ---
				model.setIfoBreakdownViewId(breakdownView);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String viewIRSIMajorHolders() throws FunctionalException, SystemException {
		final String LOCATION = "viewIRSIMajorHolders()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			CurrentCompanyForQuote currentComp = quotesManager.quickSearchQuotes("VND", I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			SessionManager.setSymbolCompany(currentComp.getSymbol());

			// do SEO on-page
			model.setPageTitle(this.getText("home.ir.si.ownerShip"));
			// TODO

			// breadcrumbs
			ArrayList<String> breadcrumbs = new ArrayList<String>();
			breadcrumbs.add(this.getText("web.button.MaijorHoldersAction.MaijorHolder"));
			breadcrumbs.add(this.getText("br.vnd"));
			breadcrumbs.add("ttvndRoot");
			breadcrumbs.add(this.getText("br.ir"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/tong-quan.shtml");
			breadcrumbs.add(this.getText("home.topMenu.analysis.stockInfo"));
			breadcrumbs.add("/quan-he-co-dong-vndirect/thong-tin-co-ban.shtml");
			model.setBreadcrumbs(breadcrumbs);

			IfoCompanyNameViewId ifoCompanyNameViewId = model.getIfoCompanyNameViewId();

			if (ifoCompanyNameViewId == null || ifoCompanyNameViewId.getCompanyId() == null) {
				ifoCompanyNameViewId = new IfoCompanyNameViewId(currentComp.getCompanyId());
			}

			if (ifoCompanyNameViewId != null && ifoCompanyNameViewId.getCompanyId() != null) {
				ifoCompanyNameViewId.setLocale(I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
				Map<String, Object> majorHoldersMap = quotesManager.getMajorHolders(ifoCompanyNameViewId);
				model.setIfoMaijorHolderViewList((List<IfoMaijorHolderView>) majorHoldersMap.get(Constants.MAJOR_HOLDERS_ITEMS.MAJOR_HOLDERS));
				model.setIfoBreakdownViewId((IfoBreakdownViewId) majorHoldersMap.get(Constants.MAJOR_HOLDERS_ITEMS.BREAK_DOWN));
			}
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
