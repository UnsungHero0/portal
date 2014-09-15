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

public class CheckRedirectUrlFilter implements Filter {
	private static Logger logger = Logger.getLogger(CheckRedirectUrlFilter.class);
	private CheckRedirectUrlFilterEntryPoint checkRedirectUrlFilterEntryPoint = null;

	/**
	 * 
	 * @return
	 */
	private CheckRedirectUrlFilterEntryPoint getCheckRedirectUrlFilterEntryPoint() {
		if (checkRedirectUrlFilterEntryPoint == null) {
			try {
				checkRedirectUrlFilterEntryPoint = (CheckRedirectUrlFilterEntryPoint) SpringUtils.getBean("CheckRedirectUrlFilterEntryPoint");
			} catch (Exception e) {
				if (logger.isDebugEnabled())
					logger.debug("getCheckRedirectUrlFilterEntryPoint()", e);
			}
		}
		return checkRedirectUrlFilterEntryPoint;
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
		CheckRedirectUrlFilterEntryPoint _checkRedirectUrlFilterEntryPoint = this.getCheckRedirectUrlFilterEntryPoint();

		if (_checkRedirectUrlFilterEntryPoint != null) {
			_checkRedirectUrlFilterEntryPoint.doFilter(req, res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}
}
