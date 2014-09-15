package vn.com.vndirect.web.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vn.com.vndirect.business.IAuditManager;
import vn.com.vndirect.domain.struts2.listedmarket.MostPopularSymbolAJAXModel;
import vn.com.vndirect.wsclient.PagingInfo;
import vn.com.vndirect.wsclient.audit.AuditSymbol;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolRequest;
import vn.com.vndirect.wsclient.audit.SearchMostPopularSymbolResult;
import vn.com.vndirect.wsclient.audit.SecInfo;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.Arrays;

/**
 * @author thang.nguyen
 * @version 1.0
 * @created 16-Jun-2008 10:13:05 PM
 */
@SuppressWarnings("serial")
public class MostPopularSymbolAJAXAction extends ActionSupport implements ModelDriven<MostPopularSymbolAJAXModel> {

	private static Logger logger = Logger.getLogger(MostPopularSymbolAJAXAction.class);
	private MostPopularSymbolAJAXModel model = new MostPopularSymbolAJAXModel();
	private IAuditManager auditManager;

	/**
	 * @return the model
	 */
	public MostPopularSymbolAJAXModel getModel() {
		return model;
	}

	public void setAuditManager(IAuditManager auditManager) {
		this.auditManager = auditManager;
	}

	public String executeMostPopularSymbol() throws FunctionalException, SystemException {
		final String LOCATION = "executeMostPopularSymbol()";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			SearchMostPopularSymbolRequest searchMostPopularSymbolRequest = new SearchMostPopularSymbolRequest();
			PagingInfo pagingInfo = new PagingInfo();
			pagingInfo.setOffsetNumber(model.getPagingInfo().getOffset());
			searchMostPopularSymbolRequest.setPagingInfo(pagingInfo);
			searchMostPopularSymbolRequest.setSearchIn(model.getSearchIn());
			// Get data using webservice
			SearchMostPopularSymbolResult searchMostPopularSymbolResult = auditManager.searchMostPopularSymbol(searchMostPopularSymbolRequest);
			if (searchMostPopularSymbolResult != null && searchMostPopularSymbolResult.getListSecInfo() != null) {
				List<SecInfo> listSecInfo = new ArrayList<SecInfo>();
				SecInfo[] secInfos = searchMostPopularSymbolResult.getListSecInfo();
				if (secInfos != null)
					listSecInfo = (List<SecInfo>) Arrays.asList(secInfos);

				model.setListQuote(listSecInfo);

				List<AuditSymbol> listAuditSymbol = new ArrayList<AuditSymbol>();
				AuditSymbol[] auditSymbols = searchMostPopularSymbolResult.getListAuditSymbol();
				if (auditSymbols != null)
					listAuditSymbol = Arrays.asList(auditSymbols);

				model.setListAuditSymbol(listAuditSymbol);
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