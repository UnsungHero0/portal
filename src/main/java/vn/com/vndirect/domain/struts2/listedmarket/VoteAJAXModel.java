/**
 * 
 */
package vn.com.vndirect.domain.struts2.listedmarket;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.Vote;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 25, 2010 11:39:11 AM
 * 
 */
@SuppressWarnings("serial")
public class VoteAJAXModel extends BaseModel {
	private SearchResult<Vote> searchResult;
	private Vote vote;
	private String captchar;
	private String message;
	private Long totalVote;

	/**
	 * @return the totalVote
	 */
	public Long getTotalVote() {
		return totalVote;
	}

	/**
	 * @param totalVote
	 *            the totalVote to set
	 */
	public void setTotalVote(Long totalVote) {
		this.totalVote = totalVote;
	}

	/**
	 * @return the vote
	 */
	public Vote getVote() {
		return vote;
	}

	/**
	 * @param vote
	 *            the vote to set
	 */
	public void setVote(Vote vote) {
		this.vote = vote;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the captchar
	 */
	public String getCaptchar() {
		return captchar;
	}

	/**
	 * @param captchar
	 *            the captchar to set
	 */
	public void setCaptchar(String captchar) {
		this.captchar = captchar;
	}

	/**
	 * @return the searchResult
	 */
	public SearchResult<Vote> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult
	 *            the searchResult to set
	 */
	public void setSearchResult(SearchResult<Vote> searchResult) {
		this.searchResult = searchResult;
	}

}
