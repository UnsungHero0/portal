/**
 * 
 */
package vn.com.vndirect.commons.usercounter;

import java.io.Serializable;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class WebStatistics implements Serializable {
	private long totalActiveSession;
	private long totalOnlineUser;
	private long totalAgentUser;
	private long totalFreeUser;

	public void increaseActiveSession() {
		totalActiveSession++;
	}

	public void decreaseActiveSession() {
		totalActiveSession--;
		totalActiveSession = (totalActiveSession < 0 ? 0 : totalActiveSession);
	}

	public void increaseOnlineUser() {
		totalOnlineUser++;
	}

	public void decreaseOnlineUser() {
		totalOnlineUser--;
		totalOnlineUser = (totalOnlineUser < 0 ? 0 : totalOnlineUser);
	}

	public void increaseAgentUser() {
		totalAgentUser++;
	}

	public void decreaseAgentUser() {
		totalAgentUser--;
		totalAgentUser = (totalAgentUser < 0 ? 0 : totalAgentUser);
	}

	public void increaseFreeUser() {
		totalFreeUser++;
	}

	public void decreaseFreeUser() {
		totalFreeUser--;
		totalFreeUser = (totalFreeUser < 0 ? 0 : totalFreeUser);
	}

	/**
	 * @return the totalActiveSession
	 */
	public long getTotalActiveSession() {
		return totalActiveSession;
	}

	/**
	 * @param totalActiveSession
	 *            the totalActiveSession to set
	 */
	public void setTotalActiveSession(long totalActiveSession) {
		this.totalActiveSession = totalActiveSession;
	}

	/**
	 * @return the totalOnlineUser
	 */
	public long getTotalOnlineUser() {
		return totalOnlineUser;
	}

	/**
	 * @param totalOnlineUser
	 *            the totalOnlineUser to set
	 */
	public void setTotalOnlineUser(long totalOnlineUser) {
		this.totalOnlineUser = totalOnlineUser;
	}

	/**
	 * @return the totalAgentUser
	 */
	public long getTotalAgentUser() {
		return totalAgentUser;
	}

	/**
	 * @param totalAgentUser
	 *            the totalAgentUser to set
	 */
	public void setTotalAgentUser(long totalAgentUser) {
		this.totalAgentUser = totalAgentUser;
	}

	/**
	 * @return the totalFreeUser
	 */
	public long getTotalFreeUser() {
		return totalFreeUser;
	}

	/**
	 * @param totalFreeUser
	 *            the totalFreeUser to set
	 */
	public void setTotalFreeUser(long totalFreeUser) {
		this.totalFreeUser = totalFreeUser;
	}

	/**
	 * 
	 */
	public String toString() {
		return "WebStatistics-[" + "totalActiveSession:" + totalActiveSession + ", totalOnlineUser:" + totalOnlineUser
		        + ", totalAgentUser:" + totalAgentUser + ", totalFreeUser:" + totalFreeUser + "]";
	}

}
