package vn.com.vndirect.web.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.proxy.Cas20ProxyRetriever;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyRetriever;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.AbstractUrlBasedTicketValidator;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.jasig.cas.client.validation.TicketValidationException;

@SuppressWarnings("unchecked")
public class Cas20ServiceTicketValidator extends AbstractUrlBasedTicketValidator {
	final static Logger logger = Logger.getLogger(Cas20ServiceTicketValidator.class);

	/** The CAS 2.0 protocol proxy callback url. */
	private String proxyCallbackUrl;

	/** The storage location of the proxy granting tickets. */
	private ProxyGrantingTicketStorage proxyGrantingTicketStorage;

	/** Implementation of the proxy retriever. */
	private ProxyRetriever proxyRetriever;

	/**
	 * Constructs an instance of the CAS 2.0 Service Ticket Validator with the supplied CAS server url prefix.
	 * 
	 * @param casServerUrlPrefix
	 *            the CAS Server URL prefix.
	 */
	public Cas20ServiceTicketValidator(final String casServerUrlPrefix) {
		super(casServerUrlPrefix);
		this.proxyRetriever = new Cas20ProxyRetriever(casServerUrlPrefix);
	}

	/**
	 * Retrieves the response from the server by opening a connection and merely reading the response.
	 */
	protected final String retrieveResponseFromServer(final URL validationUrl, final String ticket) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) validationUrl.openConnection();

			final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line;
			final StringBuffer stringBuffer = new StringBuffer(255);

			synchronized (stringBuffer) {
				while ((line = in.readLine()) != null) {
					stringBuffer.append(line);
					stringBuffer.append("\n");
				}
				return stringBuffer.toString();
			}
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * Adds the pgtUrl to the list of parameters to pass to the CAS server.
	 * 
	 * @param urlParameters
	 *            the Map containing the existing parameters to send to the server.
	 */
	protected final void populateUrlAttributeMap(final Map urlParameters) {
		urlParameters.put("pgtUrl", encodeUrl(this.proxyCallbackUrl));
	}

	protected String getUrlSuffix() {
		return "serviceValidate";
	}

	protected final Assertion parseResponseFromServer(final String response) throws TicketValidationException {
		if (logger.isDebugEnabled()) {
			logger.debug("parseResponseFromServer:: " + response);
		}
		final String error = XmlUtils.getTextForElement(response, "authenticationFailure");

		if (CommonUtils.isNotBlank(error)) {
			throw new TicketValidationException(error);
		}

		final String principal = XmlUtils.getTextForElement(response, "user");
		final String proxyGrantingTicketIou = XmlUtils.getTextForElement(response, "proxyGrantingTicket");
		final String proxyGrantingTicket = this.proxyGrantingTicketStorage != null ? this.proxyGrantingTicketStorage.retrieve(proxyGrantingTicketIou) : null;

		if (CommonUtils.isEmpty(principal)) {
			throw new TicketValidationException("No principal was found in the response from the CAS server.");
		}

		final Assertion assertion;
		final Map attributes = extractCustomAttributes(response);
		if (CommonUtils.isNotBlank(proxyGrantingTicket)) {
			final AttributePrincipal attributePrincipal = new AttributePrincipalImpl(principal, attributes, proxyGrantingTicket, this.proxyRetriever);
			assertion = new AssertionImpl(attributePrincipal);
		} else {
			assertion = new AssertionImpl(new AttributePrincipalImpl(principal, attributes));
		}

		customParseResponse(response, assertion);

		return assertion;
	}

	protected Map extractCustomAttributes(final String xml) {
		final int pos1 = xml.indexOf("<cas:attributes>");
		final int pos2 = xml.indexOf("</cas:attributes>");

		if (pos1 == -1) {
			return Collections.EMPTY_MAP;
		}

		final String attributesText = xml.substring(pos1 + 16, pos2);

		final Map attributes = new HashMap();
		final BufferedReader br = new BufferedReader(new StringReader(attributesText));

		String line;
		final List attributeNames = new ArrayList();
		try {
			while ((line = br.readLine()) != null) {
				final String trimmedLine = line.trim();
				if (trimmedLine.length() > 0) {
					final int leftPos = trimmedLine.indexOf(":");
					final int rightPos = trimmedLine.indexOf(">");
					attributeNames.add(trimmedLine.substring(leftPos + 1, rightPos));
				}
			}
			br.close();
		} catch (final IOException e) {
			// ignore
		}

		for (final Iterator iter = attributeNames.iterator(); iter.hasNext();) {
			final String name = (String) iter.next();
			attributes.put(name, XmlUtils.getTextForElement(xml, name));
		}

		return attributes;
	}

	/**
	 * Template method if additional custom parsing (such as Proxying) needs to be done.
	 * 
	 * @param response
	 *            the original response from the CAS server.
	 * @param assertion
	 *            the partially constructed assertion.
	 * @throws TicketValidationException
	 *             if there is a problem constructing the Assertion.
	 */
	protected void customParseResponse(final String response, final Assertion assertion) throws TicketValidationException {
		// nothing to do
	}

	public final void setProxyCallbackUrl(final String proxyCallbackUrl) {
		this.proxyCallbackUrl = proxyCallbackUrl;
	}

	public final void setProxyGrantingTicketStorage(final ProxyGrantingTicketStorage proxyGrantingTicketStorage) {
		this.proxyGrantingTicketStorage = proxyGrantingTicketStorage;
	}

	public final void setProxyRetriever(final ProxyRetriever proxyRetriever) {
		this.proxyRetriever = proxyRetriever;
	}
}