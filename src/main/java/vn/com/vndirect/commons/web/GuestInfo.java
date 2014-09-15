/**
 * 
 */
package vn.com.vndirect.commons.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tungnq.nguyen
 * 
 */
public class GuestInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = -3325840021356724121L;

	private String remoteAddress;
	private String sessionId;
	private boolean isNewSession = true;

	/**
	 * 
	 * @param sessionId
	 */
	public GuestInfo(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 
	 * @param request
	 */
	public void setInfo(HttpServletRequest request) {
		this.remoteAddress = request.getRemoteAddr();

		HttpSession session = request.getSession(false);
		this.sessionId = (session != null) ? session.getId() : null;

		isNewSession = false;
	}

	/**
	 * @return the remoteAddress
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((remoteAddress == null) ? 0 : remoteAddress.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GuestInfo))
			return false;
		final GuestInfo other = (GuestInfo) obj;
		if (remoteAddress == null) {
			if (other.remoteAddress != null)
				return false;
		} else if (!remoteAddress.equals(other.remoteAddress))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	/**
	 * @return the isNewSession
	 */
	public boolean isNewSession() {
		return isNewSession;
	}

	public String toString() {
		return "GuestInfo-[" + "sessionId: " + sessionId + ", remoteAddress: " + remoteAddress + ", isNewSession: "
		        + isNewSession + "]";
	}
}
