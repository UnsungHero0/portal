package vn.com.vndirect.domain.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import vn.com.vndirect.domain.BaseBean;
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoStockExchangeView;
import vn.com.vndirect.domain.IfoStockExchangeViewId;

@SuppressWarnings( { "unchecked", "serial" })
public class CurrentCompanyForQuote extends BaseBean {
	/**
	 *
	 */

	private Long companyId;
	private String symbol;
	private String companyName;
	private String companyFullName;
	private Date firstTradingDate;

	private boolean isExchange = false;

	private String locale;

	private List listIfoCompanyIndustryView;

	private List listExchangeCode = new ArrayList();
	private String currentExchangeCode;

	private Map mapExchange = new Hashtable();

	/**
	 * 
	 * @param symbol
	 */
	public CurrentCompanyForQuote(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * 
	 * @param ifoCompanyNameView
	 * @param listIfoStockExchange
	 * @throws Exception
	 */
	public CurrentCompanyForQuote(IfoCompanyNameView ifoCompanyNameView, Collection listIfoStockExchange) throws Exception {
		IfoCompanyNameViewId com = ifoCompanyNameView.getId();
		this.companyId = com.getCompanyId();
		this.symbol = com.getSymbol();
		this.companyName = com.getCompanyName();
		this.companyFullName = com.getCompanyFullName();

		this.firstTradingDate = com.getFirstTradingDate();

		//
		Iterator iter = listIfoStockExchange.iterator();
		IfoStockExchangeViewId stock;
		while (iter.hasNext()) {
			stock = ((IfoStockExchangeView) iter.next()).getId();
			if (stock != null && stock.getExchangeCode() != null) {
				mapExchange.put(stock.getExchangeCode(), stock.getExchangeName() == null ? stock.getExchangeCode() : stock.getExchangeName());
				listExchangeCode.add(stock.getExchangeCode());
			}
		}

		if (listExchangeCode.size() > 0) {
			Collections.sort(listExchangeCode);
			currentExchangeCode = (String) listExchangeCode.get(0);
		} else {
			currentExchangeCode = null;
		}
	}

	/**
	 * @return the companyFullName
	 */
	public String getCompanyFullName() {
		return companyFullName == null ? "" : companyFullName;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName == null ? "" : companyName;
	}

	/**
	 * @return the currentExchangeCode
	 */
	public String getCurrentExchangeCode() {
		return currentExchangeCode == null ? "" : currentExchangeCode;
	}

	/**
	 * @return the listExchangeCode
	 */
	public List getListExchangeCode() {
		return listExchangeCode;
	}

	/**
	 * 
	 * @param exchangeCode
	 * @return
	 */
	public String getExchangeName(String exchangeCode) {
		return (exchangeCode == null ? "" : (String) this.mapExchange.get(exchangeCode));
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param currentExchangeCode
	 *            the currentExchangeCode to set
	 */
	public void setCurrentExchangeCode(String currentExchangeCode) {
		if (currentExchangeCode != null) {
			int i = Collections.binarySearch(this.listExchangeCode, currentExchangeCode);
			if (i > -1) {
				this.currentExchangeCode = currentExchangeCode;
			}
		} else {
			if (listExchangeCode.size() > 0) {
				this.currentExchangeCode = (String) this.listExchangeCode.get(0);
			} else {
				this.currentExchangeCode = null;
			}
		}
	}

	/**
	 * @return the listIfoCompanyIndustryView
	 */
	public List getListIfoCompanyIndustryView() {
		return listIfoCompanyIndustryView;
	}

	/**
	 * @param listIfoCompanyIndustryView
	 *            the listIfoCompanyIndustryView to set
	 */
	public void setListIfoCompanyIndustryView(List listIfoCompanyIndustryView) {
		this.listIfoCompanyIndustryView = listIfoCompanyIndustryView;
	}

	/**
	 * 
	 * @param local
	 * @return
	 */
	public IfoCompanyIndustryView getIfoCompanyIndustryView(String local) {
		IfoCompanyIndustryView obj = null;
		if (listIfoCompanyIndustryView != null && listIfoCompanyIndustryView.size() > 0) {
			local = (local == null ? "" : local.trim());
			if (local.length() > 0) {
				int i, size = listIfoCompanyIndustryView.size();
				for (i = 0; i < size; i++) {
					obj = (IfoCompanyIndustryView) listIfoCompanyIndustryView.get(i);
					if (obj != null && obj.getId() != null && local.equalsIgnoreCase(obj.getId().getLocale())) {
						return obj;
					}
				}
			}
			obj = (obj != null ? obj : (IfoCompanyIndustryView) listIfoCompanyIndustryView.get(0));
		}
		return obj;
	}

	public String toString() {
		return "CurrentCompanyForQuote-[" + " companyId:" + companyId + ", symbol:" + symbol + ", companyName:" + companyName + ", companyFullName:" + companyFullName + ", currentExchangeCode:"
				+ currentExchangeCode + "]";
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the firstTradingDate
	 */
	public Date getFirstTradingDate() {
		return firstTradingDate;
	}

	/**
	 * @param firstTradingDate
	 *            the firstTradingDate to set
	 */
	public void setFirstTradingDate(Date firstTradingDate) {
		this.firstTradingDate = firstTradingDate;
	}

	// Tinh tao ra
	/**
	 * @param companyId
	 *            the companyId to set Tinhtx tao ra de test
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @param symbol
	 *            the symbol to set Tinhtx tao ra de test
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @param companyName
	 *            the companyName to set Tinhtx tao ra de test
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param companyFullName
	 *            the companyFullName to set Tinhtx tao ra de test
	 */
	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	/**
	 * @return the isExchange
	 */
	public boolean isExchange() {
		return isExchange;
	}

	/**
	 * @param isExchange
	 *            the isExchange to set
	 */
	public void setExchange(boolean isExchange) {
		this.isExchange = isExchange;
	}
}
