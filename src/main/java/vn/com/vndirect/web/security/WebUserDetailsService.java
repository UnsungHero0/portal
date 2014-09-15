/**
 *
 */
package vn.com.vndirect.web.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.com.vndirect.business.IUserManager;
import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.vndirect.commons.web.AppWebUserDetails;
import vn.com.vndirect.commons.web.WebUserDetails;

/**
 * @author tungnq.nguyen
 * 
 */
public class WebUserDetailsService implements IWebUserDetailsService, InitializingBean {
	private static Logger logger = Logger.getLogger(WebUserDetailsService.class);

	private static final String PREFIX_ROLE = "ROLE_";

	private IUserManager userManager;
	private List<String> ignoreUsers;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		final String LOCATION = "loadUserByUsername(userName:" + userName + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		if (this.ignoreUsers != null) {
			for (String ignoreUser : this.ignoreUsers) {
				if (ignoreUser.equalsIgnoreCase(userName)) {
					return null;
				}
			}
		}

		WebUserDetails user = null;

		try {
			user = (WebUserDetails) userManager.readFromCache(userName, null);
			if (user == null) {
				AppContext appContext = AppContextHolder.get();

				if (logger.isDebugEnabled())
					logger.debug(LOCATION + "---->>>> appContext:getMapAttr::"
							+ (appContext == null ? "" : appContext.getMapAttr()));

				VNDSCasUser vndsCasUser = (appContext == null ? null : (VNDSCasUser) appContext
						.getAttr(VNDSCasUser.CAS_USER_APP_CTX_KEY));

				if (vndsCasUser != null) {
					Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
					dbAuthsSet.add(new GrantedAuthorityImpl("ROLE_GUEST"));
					// +++ process user information
					String strStatus = null;
					String strOnlineServices = null;
					String authFor = appContext.getAttrStr(VNDSCasUser.ServiceParamName.AUTH_FOR);
					if (VNDSCasUser.AuthFor.AGENT.equalsIgnoreCase(authFor)) {
						strOnlineServices = vndsCasUser.getStr(VNDSCasUser.Agent.SERVICES.key);
						strStatus = vndsCasUser.getStr(VNDSCasUser.Agent.STATUS.key);
					} else {
						strOnlineServices = vndsCasUser.getStr(VNDSCasUser.Online.SERVICES.key);
						strStatus = vndsCasUser.getStr(VNDSCasUser.Online.STATUS.key);
					}

					if (logger.isDebugEnabled())
						logger.debug(LOCATION + " - strStatus:" + strStatus);
					if (strStatus != null) {
						if (strStatus.startsWith(PREFIX_ROLE)) {
							dbAuthsSet.add(new GrantedAuthorityImpl(strStatus));
						} else {
							dbAuthsSet.add(new GrantedAuthorityImpl(PREFIX_ROLE + strStatus));
						}
					}

					if (logger.isDebugEnabled())
						logger.debug(LOCATION + " - strOnlineServices:" + strOnlineServices);

					if (strOnlineServices != null) {
						StringTokenizer strToken = new StringTokenizer(strOnlineServices, "[,] ");
						String strService;
						while (strToken.hasMoreTokens()) {
							strService = strToken.nextToken().toUpperCase();
							if (strService.startsWith(PREFIX_ROLE)) {
								dbAuthsSet.add(new GrantedAuthorityImpl(strService));
							} else {
								dbAuthsSet.add(new GrantedAuthorityImpl(PREFIX_ROLE + strService));
							}
						}
					}
					// ---

					//GrantedAuthority[] arrayAuths = dbAuthsSet.toArray(new GrantedAuthority[dbAuthsSet.size()]);
					user = new AppWebUserDetails(1, appContext.getMapAttr(), userName, "", true, true, true, true, dbAuthsSet);

					if (logger.isDebugEnabled())
						logger.debug(LOCATION + "------->>> mapAttr:" + appContext.getMapAttr());
				} else {
					if (logger.isDebugEnabled())
						logger.debug(LOCATION + "------->>> appContext.getRequest(): NULL.......");
				}

				if (user != null) {
					userManager.writeToCache(userName, user);
				}
			}
			if (user == null) {
				throw new UsernameNotFoundException("User not found!!!");
			}
		} catch (UsernameNotFoundException ufe) {
			throw ufe;
		} catch (Exception e) {
			if (logger.isInfoEnabled())
				logger.info(LOCATION + "::", e);
			throw new UsernameNotFoundException("User not found!!!", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return user;
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
	 * @see
	 * vn.com.web.vnds.web.security.IWebUserDetailsService#reloadUserByUsername
	 * (java.lang.String)
	 */
	public void reloadUserByUsername(String userName) {
		final String LOCATION = "reloadUserByUsername(userName:" + userName + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}
		try {
			WebUserDetails user = (WebUserDetails) userManager.readFromCache(userName, null);
			if (user != null) {
				// user = userManager.getUserByUsername(userName);
				// TODO: get userbyName
				if (user != null) {
					userManager.writeToCache(userName, user);
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
	}

	/**
	 * @param ignoreUsers
	 *            the ignoreUsers to set
	 */
	public void setIgnoreUsers(List<String> ignoreUsers) {
		this.ignoreUsers = ignoreUsers;
	}

	/**
	 * @param userManager
	 */
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

}
