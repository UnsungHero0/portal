package vn.com.vndirect.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.security.cas.authentication.CasAuthenticationToken;

import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.SpringBeans;
import vn.com.web.commons.urlmapping.ExternalUrlMapping;
import vn.com.web.commons.utility.Utilities;

public class URLMappingTag extends TagSupport implements IWebTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6508976593056741750L;

	private static Logger logger = Logger.getLogger(URLTag.class);

	private final String removeParams = "code;ticket;";

	protected String var;
	protected String scope = REQUEST;
	protected String value;

	protected boolean includeReqParams;

	protected boolean rewrite = true;

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var
	 *            the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return the rewrite
	 */
	public boolean getRewrite() {
		return rewrite;
	}

	/**
	 * @param rewrite
	 *            the rewrite to set
	 */
	public void setRewrite(boolean rewrite) {
		this.rewrite = rewrite;
	}

	/**
	 * @return the includeReqParams
	 */
	public boolean getIncludeReqParams() {
		return includeReqParams;
	}

	/**
	 * @param includeReqParams
	 *            the includeReqParams to set
	 */
	public void setIncludeReqParams(boolean includeReqParams) {
		this.includeReqParams = includeReqParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";

		HttpServletRequest req = (HttpServletRequest) this.pageContext.getRequest();

		String str = process(value, req, includeReqParams, removeParams);
		// if(rewrite && str != null && URLUtils.checkExt(str, ".shtml")) {
		// str = Utilities.FormatURL.encodeURI(str, req);
		// }
		try {
			str = (str == null ? "" : str);
			var = (var == null ? "" : var);

			if (var.length() > 0) {
				if (PAGE.equalsIgnoreCase(scope)) {
					pageContext.setAttribute(var, str);
				} else if (REQUEST.equalsIgnoreCase(scope)) {
					pageContext.getRequest().setAttribute(var, str);
				} else {
					pageContext.setAttribute(var, str);
				}
			} else {
				pageContext.getOut().print(str);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * 
	 * @param mappingCode
	 * @param req
	 * @param includeReqParams
	 * @param removeParams
	 * @return
	 */
	/**
	 * 
	 * @param mappingCode
	 * @param req
	 * @param includeReqParams
	 * @param removeParams
	 * @return
	 */
	public static String process(String mappingCode, HttpServletRequest req, boolean includeReqParams, String removeParams) {
		final String LOCATION = "process()";
		String str = null;
		try {
			str = (mappingCode == null ? "" : mappingCode);
			ExternalUrlMapping urlMapping = SpringBeans.getExternalUrlMapping();

			if (urlMapping != null) {
				str = urlMapping.getUri(mappingCode, false);
				if (includeReqParams && str != null) {
					Map<String, String> params = new HashMap<String, String>();
					String strKey;
					for (Object key : req.getParameterMap().keySet()) {
						strKey = (String) key;
						if (removeParams.indexOf(strKey + ";") < 0) {
							params.put(strKey, req.getParameter(strKey));
						}
					}
					str = Utilities.FormatURL.addPram(str, params);
				}

				String languageCode = I18NUtility.getCurrentLocale(req.getSession());
				if (logger.isDebugEnabled()) {
					logger.debug(LOCATION + ":: languageCode: " + languageCode);
				}
//				if (StringUtils.isNotBlank(languageCode))
//					str = Utilities.FormatURL.addParam(str, "locale", languageCode);

				// ++++ process ticket
				try {
					final String REQUEST_SEC_TICKET = "ticket";
					String reqTicket = req.getParameter(REQUEST_SEC_TICKET);
					if (reqTicket != null && "true".equalsIgnoreCase(reqTicket.trim())) {
						ExternalUrlMapping ticketMapping = SpringBeans.getTicketProxyUrlMapping();

						String ticketMappingCode = ticketMapping.findCode(str);
						if (logger.isDebugEnabled()) {
							logger.debug(LOCATION + ":: ticketMappingCode: " + ticketMappingCode + ", for url: " + str);
						}
						if (ticketMappingCode != null) {
							String service = ticketMapping.getUri(ticketMappingCode, false);
							if (logger.isDebugEnabled()) {
								logger.debug(LOCATION + ":: ticketMappingCode: " + ticketMappingCode + ", service:" + service);
							}
							String objTicket = null;
							CasAuthenticationToken token = (CasAuthenticationToken) req.getUserPrincipal();
							try {
								if (token != null && token != null && service != null) {
									objTicket = token.getAssertion().getPrincipal().getProxyTicketFor(service);
									if (logger.isDebugEnabled()) {
										logger.debug(LOCATION + ":: newTicket: " + objTicket + ", service: " + service + ", token:" + token);
									}
								}
							} catch (Exception e) {
								if (logger.isDebugEnabled()) {
									logger.debug(LOCATION + " :: getProxyTicketFor " + e.getMessage());
								}
							}
							if (objTicket != null) {
								str = Utilities.FormatURL.addParam(str, REQUEST_SEC_TICKET, objTicket);
							} else {
								// +++ get current ticket
								objTicket = (String) (token == null ? null : token.getCredentials());
								if (objTicket != null) {
									str = Utilities.FormatURL.addParam(str, REQUEST_SEC_TICKET, objTicket.toString());
								}
							}
						}
					}
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						logger.debug(LOCATION + " :: process ticket " + e.getMessage());
					}
				}
				// ----
			}
			// str = (str == null ? mappingCode : str);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION, e);
			}
		}
		return str;
	}
}
