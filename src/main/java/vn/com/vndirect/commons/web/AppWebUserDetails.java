/**
 * 
 */
package vn.com.vndirect.commons.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class AppWebUserDetails extends WebUserDetails {

	public AppWebUserDetails(long userId, Map<String, Serializable> attrs,
			String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(userId, attrs, username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}


	public interface Keys {
		String USER_ATTR = "USER";
	}
    
	/* (non-Javadoc)
	 * @see vn.com.web.commons.domain.security.WebUserDetails#getUserId()
	 */
	@Override
	public long getUserId() {
		return super.getUserId();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */

	/* 
	 * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication. true if the user's credentials are
     * valid (ie non-expired), false if no longer valid (ie expired)
     * 
     * (non-Javadoc)
     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}

}
