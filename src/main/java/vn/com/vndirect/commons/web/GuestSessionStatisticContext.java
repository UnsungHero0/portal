package vn.com.vndirect.commons.web;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class GuestSessionStatisticContext implements InitializingBean, DisposableBean {

	/**
     * 
     */
	private Map<String, GuestInfo> mapGuests = new Hashtable<String, GuestInfo>();

	/**
	 * 
	 * @param sessionId
	 */
	public void addNewSession(String sessionId) {
		if (sessionId != null) {
			mapGuests.put(sessionId, new GuestInfo(sessionId));
		}
	}

	/**
	 * 
	 * @param sessionId
	 */
	public void remove(String sessionId) {
		if (sessionId != null) {
			mapGuests.remove(sessionId);
		}
	}

	/**
	 * 
	 * @param request
	 */
	public void update(HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession(false);
			GuestInfo info = mapGuests.get(session.getId());
			if (info != null) {
				info.setInfo(request);
			}
		}
	}

	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public GuestInfo get(String sessionId) {
		if (sessionId != null) {
			return mapGuests.get(sessionId);
		}
		return null;
	}

	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public boolean isNewSession(String sessionId) {
		boolean rs = false;
		if (sessionId != null) {
			GuestInfo info = mapGuests.get(sessionId);
			if (info != null) {
				return info.isNewSession();
			}
		}
		return rs;
	}

	/**
	 * 
	 * @return
	 */
	public int totalGuest() {
		return mapGuests.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	public void destroy() throws Exception {
		if (mapGuests != null) {
			mapGuests.clear();
		}
	}
}
