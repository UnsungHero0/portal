/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE          AUTHOR     DESCRIPTION
 | ------------------------------------------------
 | Sep 10, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.Date;

import vn.com.vndirect.domain.IfoDocument;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author tungnq
 * 
 */
public class SearchIfoDocument extends IfoDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7471548377817330471L;

	private String strFromReleaseDate;

	private String strToReleaseDate;

	private String strSymbol;

	private Long companyId;

	public Date getFromReleaseDate() {
		Date fromDate = null;
		try {
			fromDate = DateUtils.stringToDate(this.strFromReleaseDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD);
		} catch (Exception e) {
		}
		return fromDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getStrFromReleaseDate() {
		return strFromReleaseDate;
	}

	public void setStrFromReleaseDate(String strFromReleaseDate) {
		this.strFromReleaseDate = strFromReleaseDate;
	}

	public String getStrSymbol() {
		return strSymbol;
	}

	public void setStrSymbol(String strSymbol) {
		this.strSymbol = strSymbol;
	}

	public String getStrToReleaseDate() {
		return strToReleaseDate;
	}

	public void setStrToReleaseDate(String strToReleaseDate) {
		this.strToReleaseDate = strToReleaseDate;
	}

	public Date getToReleaseDate() {
		Date toDate = null;
		try {
			toDate = DateUtils.stringToDate(this.strToReleaseDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD);
		} catch (Exception e) {
		}
		return toDate;
	}

}
