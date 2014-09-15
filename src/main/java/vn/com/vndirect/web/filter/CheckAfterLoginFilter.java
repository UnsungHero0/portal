package vn.com.vndirect.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import vn.com.web.commons.utility.SpringUtils;

public class CheckAfterLoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(CheckAfterLoginFilter.class);
	private CheckAfterLoginFilterEntryPoint checkAfterLoginFilterEntryPoint = null;

	/**
	 * 
	 * @return
	 */
	private CheckAfterLoginFilterEntryPoint getCheckAfterLoginFilterEntryPoint() {
		if (checkAfterLoginFilterEntryPoint == null) {
			try {
				checkAfterLoginFilterEntryPoint = (CheckAfterLoginFilterEntryPoint) SpringUtils.getBean("CheckAfterLoginFilterEntryPoint");
			} catch (Exception e) {
				if (logger.isDebugEnabled())
					logger.debug("getCheckRedirectUrlFilterEntryPoint()", e);
			}
		}
		return checkAfterLoginFilterEntryPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		CheckAfterLoginFilterEntryPoint _checkAfterLoginFilterEntryPoint = this.getCheckAfterLoginFilterEntryPoint();

		if (_checkAfterLoginFilterEntryPoint != null) {
			_checkAfterLoginFilterEntryPoint.doFilter(req, res, chain);
		} else {
			chain.doFilter(req, res);
		}

	}
}
