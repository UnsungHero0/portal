/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 6, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.lucene.finfodb.beans;

import java.io.Serializable;
import java.util.Date;

import vn.com.vndirect.domain.IfoCompanyNameView;
import vn.com.vndirect.domain.IfoStockExchangeView;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class StockExchangeIndexingBean implements Serializable {
	private IfoStockExchangeView ifoStockExchangeView;
	private IfoCompanyNameView ifoCompanyNameView;
	private String sectorGroupCode;
	private String industryGroupCode;
	private String sectorCode;
	private String industryCode;

	/**
	 * 
	 * @return
	 */
	public String getExchangeCode() {
		return (hasStockExchangeIfo() ? ifoStockExchangeView.getId().getExchangeCode() : null);
	}

	public String getExchangeName() {
		return (hasStockExchangeIfo() ? ifoStockExchangeView.getId().getExchangeName() : "");
	}

	/**
	 * 
	 * @return
	 */
	public String getSymbol() {
		return (hasStockExchangeIfo() ? ifoStockExchangeView.getId().getSymbol() : null);
	}

	/**
	 * 
	 * @return
	 */
	public long getCompanyId() {
		return (hasCompanyInfo() ? ifoCompanyNameView.getId().getCompanyId().longValue() : 0);
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasCompanyInfo() {
		return (ifoCompanyNameView != null && ifoCompanyNameView.getId() != null && ifoCompanyNameView.getId().getCompanyId() != null);
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasStockExchangeIfo() {
		return (ifoStockExchangeView != null && ifoStockExchangeView.getId() != null);
	}

	/**
	 * @return the ifoStockExchangeView
	 */
	public IfoStockExchangeView getIfoStockExchangeView() {
		return this.ifoStockExchangeView;
	}

	/**
	 * @param ifoStockExchangeView
	 *            the ifoStockExchangeView to set
	 */
	public void setIfoStockExchangeView(IfoStockExchangeView ifoStockExchangeView) {
		this.ifoStockExchangeView = ifoStockExchangeView;
	}

	/**
	 * @return the ifoCompanyNameView
	 */
	public IfoCompanyNameView getIfoCompanyNameView() {
		return this.ifoCompanyNameView;
	}

	/**
	 * @param ifoCompanyNameView
	 *            the ifoCompanyNameView to set
	 */
	public void setIfoCompanyNameView(IfoCompanyNameView ifoCompanyNameView) {
		this.ifoCompanyNameView = ifoCompanyNameView;
	}

	/**
	 * @return the sectorGroupCode
	 */
	public String getSectorGroupCode() {
		return this.sectorGroupCode;
	}

	/**
	 * @param sectorGroupCode
	 *            the sectorGroupCode to set
	 */
	public void setSectorGroupCode(String sectorGroupCode) {
		this.sectorGroupCode = sectorGroupCode;
	}

	/**
	 * @return the industryGroupCode
	 */
	public String getIndustryGroupCode() {
		return this.industryGroupCode;
	}

	/**
	 * @param industryGroupCode
	 *            the industryGroupCode to set
	 */
	public void setIndustryGroupCode(String industryGroupCode) {
		this.industryGroupCode = industryGroupCode;
	}

	/**
	 * @return the sectorCode
	 */
	public String getSectorCode() {
		return this.sectorCode;
	}

	/**
	 * @param sectorCode
	 *            the sectorCode to set
	 */
	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return this.industryCode;
	}

	/**
	 * @param industryCode
	 *            the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getCompanyName() {
		return (hasCompanyInfo() ? ifoCompanyNameView.getId().getCompanyName() : "");
	}

	public String getCompanyFullName() {
		return (hasCompanyInfo() ? ifoCompanyNameView.getId().getCompanyFullName() : "");
	}

	public String getAbbName() {
		return (hasCompanyInfo() ? ifoCompanyNameView.getId().getAbbName() : "");
	}

	public Date getFirstTradingDate() {
		return (hasCompanyInfo() ? ifoCompanyNameView.getId().getFirstTradingDate() : null);
	}
}
