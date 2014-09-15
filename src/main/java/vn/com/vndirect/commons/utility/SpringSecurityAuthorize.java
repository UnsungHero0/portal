/**
 *
 */
package vn.com.vndirect.commons.utility;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author tung.nguyen
 * 
 */
@SuppressWarnings("deprecation")
public abstract class SpringSecurityAuthorize {
	/**
	 * 
	 * @param ifAllGranted
	 * @return
	 */
	public static boolean authzIfAllGranted(String ifAllGranted) {
		return authz(ifAllGranted, null, null);
	}

	/**
	 * 
	 * @param ifAnyGranted
	 * @return
	 */
	public static boolean authzIfAnyGranted(String ifAnyGranted) {
		return authz(null, ifAnyGranted, null);
	}

	/**
	 * 
	 * @param ifNotGranted
	 * @return
	 */
	public static boolean authzIfNotGranted(String ifNotGranted) {
		return authz(null, null, ifNotGranted);
	}

	/**
	 * 
	 * @param ifAllGranted
	 * @param ifAnyGranted
	 * @param ifNotGranted
	 * @return
	 */
	public static boolean authz(String ifAllGranted, String ifAnyGranted, String ifNotGranted) {
		try {
			ifAllGranted = (ifAllGranted == null ? "" : ifAllGranted.trim());
			ifAnyGranted = (ifAnyGranted == null ? "" : ifAnyGranted.trim());
			ifNotGranted = (ifNotGranted == null ? "" : ifNotGranted.trim());

			if (ifAllGranted.length() == 0 && ifAnyGranted.length() == 0 && ifNotGranted.length() == 0) {
				return false;
			}
			final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();

			// System.out.println("granted:" + granted.size() + " - " +
			// granted);

			if (ifNotGranted.length() > 0) {
				Set<GrantedAuthority> grantedCopy = retainAll(granted, parseAuthoritiesString(ifNotGranted));
				if (!grantedCopy.isEmpty()) {
					return false;
				}
			}
			if (ifAllGranted.length() > 0) {
				if (!granted.containsAll(parseAuthoritiesString(ifAllGranted))) {
					return false;
				}
			}

			if (ifAnyGranted.length() > 0) {
				Set<GrantedAuthority> grantedCopy = retainAll(granted, parseAuthoritiesString(ifAnyGranted));
				if (grantedCopy.isEmpty()) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isAuth() {
		try {
			Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
			return (currentUser != null);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 
	 * @param granted
	 * @return
	 */
	private static Set<String> authoritiesToRoles(Collection<? extends GrantedAuthority> granted) {
		Set<String> target = new HashSet<String>();

		for (GrantedAuthority authority : granted) {
			if (null == authority.getAuthority()) {
				throw new IllegalArgumentException(
				        "Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process "
				                + authority.toString());
			}

			target.add(authority.getAuthority());
		}

		return target;
	}

	/**
	 * 
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		if (null == currentUser) {
			return Collections.emptyList();
		}
		Collection<? extends GrantedAuthority> roles = currentUser.getAuthorities();
		return roles;
	}

	/**
	 * 
	 * @param authorizationsString
	 * @return
	 */
	private static Set<GrantedAuthority> parseAuthoritiesString(String authorizationsString) {
		final Set<GrantedAuthority> requiredAuthorities = new HashSet<GrantedAuthority>();
		requiredAuthorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(authorizationsString));

		return requiredAuthorities;
	}

	/**
	 * Find the common authorities between the current authentication's
	 * {@link GrantedAuthority} and the ones that have been specified in the
	 * tag's ifAny, ifNot or ifAllGranted attributes.
	 * <p>
	 * We need to manually iterate over both collections, because the granted
	 * authorities might not implement {@link Object#equals(Object)} and
	 * {@link Object#hashCode()} in the same way as {@link GrantedAuthorityImpl}
	 * , thereby invalidating {@link Collection#retainAll(java.util.Collection)}
	 * results.
	 * </p>
	 * <p>
	 * <strong>CAVEAT</strong>: This method <strong>will not</strong> work if
	 * the granted authorities returns a <code>null</code> string as the return
	 * value of {@link GrantedAuthority#getAuthority()}.
	 * </p>
	 * 
	 * @param granted
	 *            The authorities granted by the authentication. May be any
	 *            implementation of {@link GrantedAuthority} that does
	 *            <strong>not</strong> return <code>null</code> from
	 *            {@link GrantedAuthority#getAuthority()}.
	 * @param required
	 *            A {@link Set} of {@link GrantedAuthorityImpl}s that have been
	 *            built using ifAny, ifAll or ifNotGranted.
	 * 
	 * @return A set containing only the common authorities between
	 *         <var>granted</var> and <var>required</var>.
	 * 
	 */
	private static Set<GrantedAuthority> retainAll(final Collection<? extends GrantedAuthority> granted,
	        final Set<GrantedAuthority> required) {
		Set<String> grantedRoles = authoritiesToRoles(granted);
		Set<String> requiredRoles = authoritiesToRoles(required);
		grantedRoles.retainAll(requiredRoles);

		return rolesToAuthorities(grantedRoles, granted);
	}

	/**
	 * 
	 * @param grantedRoles
	 * @param granted
	 * @return
	 */
	private static Set<GrantedAuthority> rolesToAuthorities(Set<String> grantedRoles, Collection<? extends GrantedAuthority> granted) {
		Set<GrantedAuthority> target = new HashSet<GrantedAuthority>();

		for (String role : grantedRoles) {
			for (GrantedAuthority authority : granted) {
				if (authority.getAuthority().equals(role)) {
					target.add(authority);

					break;
				}
			}
		}

		return target;
	}
}
