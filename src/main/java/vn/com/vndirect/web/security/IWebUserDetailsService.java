/**
 * 
 */
package vn.com.vndirect.web.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author tungnq.nguyen
 * 
 */
public interface IWebUserDetailsService extends UserDetailsService {
	/**
	 * 
	 * @param userName
	 */
	public void reloadUserByUsername(String userName);
}
