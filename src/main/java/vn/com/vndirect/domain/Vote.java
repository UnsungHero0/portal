/**
 * 
 */
package vn.com.vndirect.domain;

import java.util.Date;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 25, 2010 2:09:20 PM
 * 
 */
@SuppressWarnings("serial")
public class Vote extends BaseBean {
	private Long voteId;
	private String voteName;
	private Long total;
	private Boolean isDeleted;
	private String createBy;
	private String modifyBy;
	private Date createDate;
	private Date modifyDate;
	private Double rateVote;

	/**
	 * @return the rateVote
	 */
	public Double getRateVote() {
		return rateVote;
	}

	/**
	 * @param rateVote
	 *            the rateVote to set
	 */
	public void setRateVote(Double rateVote) {
		this.rateVote = rateVote;
	}

	/**
	 * @return the voteId
	 */
	public Long getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the voteName
	 */
	public String getVoteName() {
		return voteName;
	}

	/**
	 * @param voteName
	 *            the voteName to set
	 */
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy
	 *            the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
