package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.audit.AuditSymbol;
import vn.com.vndirect.wsclient.audit.SecInfo;

/**
 * @author thang.nguyen
 * @version 1.0
 * @created 16-Jun-2008 10:14:38 PM
 */
@SuppressWarnings("serial")
public class MostPopularSymbolAJAXModel extends BaseModel {
	private List<SecInfo> listQuote = new ArrayList<SecInfo>();
	private List<AuditSymbol> listAuditSymbol = new ArrayList<AuditSymbol>();
	private Integer searchIn;

	/**
	 * @return the searchIn
	 */
	public Integer getSearchIn() {
		return searchIn;
	}

	/**
	 * @param searchIn
	 *            the searchIn to set
	 */
	public void setSearchIn(Integer searchIn) {
		this.searchIn = searchIn;
	}

	public List<SecInfo> getListQuote() {
		return listQuote;
	}

	public void setListQuote(List<SecInfo> listQuote) {
		this.listQuote = listQuote;
	}

	public List<AuditSymbol> getListAuditSymbol() {
		return listAuditSymbol;
	}

	public void setListAuditSymbol(List<AuditSymbol> listAuditSymbol) {
		this.listAuditSymbol = listAuditSymbol;
	}
}