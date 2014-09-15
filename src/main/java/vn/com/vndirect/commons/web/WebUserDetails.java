package vn.com.vndirect.commons.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUserDetails extends User implements UserDetails {

	private final Map<String, Serializable> attrs = new HashMap<String, Serializable>();
	
	private long userId;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @throws IllegalArgumentException
	 */
	public WebUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param attrs
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @throws IllegalArgumentException
	 */
	public WebUserDetails(long userId, Map<String, Serializable> attrs, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userId = userId;
		if(attrs != null) {
			this.attrs.putAll(attrs);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public final boolean addAttr(String key, Serializable value) {
		if(key != null && value != null) {
			attrs.put(key, value);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public final Serializable getAttr(String key) {
		if(key != null) {
			return attrs.get(key);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Set<String> getAttrKeys() {
		return attrs.keySet();
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}	
}
