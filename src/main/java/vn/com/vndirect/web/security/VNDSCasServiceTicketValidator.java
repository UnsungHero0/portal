package vn.com.vndirect.web.security;

/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/index.html
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.web.commons.urlmapping.ExternalUrlMapping;

/**
 * Implementation of the TicketValidator that will validate Service Tickets in
 * compliance with the CAS 2.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
@SuppressWarnings("unchecked")
public class VNDSCasServiceTicketValidator extends Cas20ServiceTicketValidator implements InitializingBean, ServletContextAware {
	final static Logger logger = Logger.getLogger(VNDSCasServiceTicketValidator.class);

	@SuppressWarnings("unused")
	private ServletContext sctx;

	private List<String> lstExternalServices = new ArrayList<String>();
	private ExternalUrlMapping externalServices;

	public VNDSCasServiceTicketValidator(String casServerUrlPrefix) {
		super(casServerUrlPrefix);
	}

	/**
	 * @return the externalServices
	 */
	public ExternalUrlMapping getExternalServices() {
		return externalServices;
	}

	/**
	 * @param externalServices
	 *            the externalServices to set
	 */
	public void setExternalServices(ExternalUrlMapping externalServices) {
		this.externalServices = externalServices;
	}

	public void setServletContext(ServletContext _sctx) {
		sctx = _sctx;
	}

	/**
	 * 
	 * @param lstExternalServices
	 */
	public void setLstExternalServices(List<String> lstExternalServices) {
		this.lstExternalServices = lstExternalServices;
	}

	public void afterPropertiesSet() throws Exception {
		lstExternalServices = (lstExternalServices == null ? new ArrayList<String>() : lstExternalServices);

		if (externalServices != null && externalServices.getMapingUri() != null) {
			for (String uri : externalServices.getMapingUri().values()) {
				if (uri != null && !lstExternalServices.contains(uri)) {
					lstExternalServices.add(uri);
				}
			}
			System.out.println("VNDSCasServiceTicketValidator:: lstExternalServices - " + lstExternalServices);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jasig.cas.client.validation.Cas20ServiceTicketValidator#
	 * customParseResponse(java.lang.String,
	 * org.jasig.cas.client.validation.Assertion)
	 */
	protected void customParseResponse(String response, Assertion assertion) throws TicketValidationException {
		if (response == null) {
			return;
		}
		// +++ process vndsCas information
		VNDSCasUser vndsCasUser = new VNDSCasUser();
		String name, value;

		if (assertion.getAttributes().size() > 0) {
			// process
			Map mapAttr = assertion.getAttributes();
			for (Object key : mapAttr.keySet()) {
				value = (String) mapAttr.get(key);
				vndsCasUser.put((String) key, value);
			}
		}
		try {
			Document doc = null;
			Element root = null;
			Element attrsElm = null;

			SAXBuilder builder = new SAXBuilder();
			builder.setIgnoringElementContentWhitespace(true);

			if (logger.isDebugEnabled()) {
				logger.debug("customParseResponse: " + response);
			}

			doc = builder.build(new StringReader(response));

			if (doc != null)
				root = doc.getRootElement();

			if (root != null)
				attrsElm = root.getChild("attributes", Namespace.getNamespace("http://www.yale.edu/tp/cas"));

			if (attrsElm != null) {

				List<Element> attrElmList = attrsElm.getChildren();

				for (Element attrElm : attrElmList) {
					name = attrElm.getChild("name", Namespace.getNamespace("http://www.yale.edu/tp/cas")).getValue();
					value = attrElm.getChild("value", Namespace.getNamespace("http://www.yale.edu/tp/cas")).getValue();
					assertion.getAttributes().put(name, value);

					vndsCasUser.put(name, value);
				}

				if (logger.isDebugEnabled())
					logger.debug("vndsCasUser: " + vndsCasUser);

				// +++ add cas user info to app context & session
				AppContext appCtx = AppContextHolder.get();
				if (appCtx != null && appCtx.getRequest() != null) {
					appCtx.addAttr(VNDSCasUser.CAS_USER_APP_CTX_KEY, vndsCasUser);
					AppContextHolder.set(appCtx, appCtx.getRequest(), true);
				} else {
					if (logger.isDebugEnabled())
						logger.debug("----->>>>> appCtx...." + appCtx);
				}
			}

			// //grand proxy ticket for services
			// this.grandProxyTicketForServices(assertion);

		} catch (JDOMException e) {
			if (logger.isInfoEnabled())
				logger.info("customParseResponse::", e);
			throw new TicketValidationException("Unable to parse CAS server response");
		} catch (IOException e) {
			if (logger.isInfoEnabled())
				logger.info("customParseResponse::", e);
			throw new TicketValidationException("Unable to read CAS server response");
		} catch (Exception e) {
			if (logger.isInfoEnabled())
				logger.info("customParseResponse::", e);
			throw new TicketValidationException(e);
		}
	}

	// /**
	// *
	// */
	// private void grandProxyTicketForServices(Assertion assertion) {
	// if(lstExternalServices != null && lstExternalServices.size() > 0) {
	// for (String externalService : lstExternalServices) {
	// try {
	// assertion.getPrincipal().getProxyTicketFor(externalService);
	// } catch (Exception e) {
	// logger.error("grandProxyTicketForServices:: externalService=" +
	// externalService, e);
	// }
	// }
	// }
	// }
}
