/**
 * 
 */
package vn.com.vndirect.domain;

import java.io.Serializable;
import java.util.Date;

import vn.com.vndirect.wsclient.osc.WpTopic;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author Huy
 * 
 */
public class Topic extends WpTopic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5221898974542302524L;

	private SearchResult<WpTopicQuestion> searchResult;
	private Date effDate;
	private Date creDate;
	private Date modiDate;

	/**
	 * @return the effDate
	 */
	public Date getEffDate() {
		return effDate;
	}

	/**
	 * @param effDate
	 *            the effDate to set
	 */
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	/**
	 * @return the creDate
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            the creDate to set
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return the modiDate
	 */
	public Date getModiDate() {
		return modiDate;
	}

	/**
	 * @param modiDate
	 *            the modiDate to set
	 */
	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	/**
	 * @return the searchResult
	 */
	public SearchResult<WpTopicQuestion> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult
	 *            the searchResult to set
	 */
	public void setSearchResult(SearchResult<WpTopicQuestion> searchResult) {
		this.searchResult = searchResult;
	}
}
