/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE        AUTHOR                DESCRIPTION
| ------------------------------------------------------------------------
| 02/01/2010  Nguyen Minh Duc       First Creation
 *---------------------------------------------------------------------------*/
package vn.com.vndirect.business;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.ehcache.Ehcache;
import vn.com.vndirect.boproxyclient.authenticationservice.AuthenticateServiceLocator;
import vn.com.vndirect.boproxyclient.authenticationservice.AuthenticateServiceSoap_PortType;
import vn.com.vndirect.boproxyclient.corebank.BankingServiceLocator;
import vn.com.vndirect.boproxyclient.corebank.BankingServiceSoap_PortType;
import vn.com.vndirect.boproxyclient.customerservice.CustomerServiceLocator;
import vn.com.vndirect.boproxyclient.customerservice.CustomerServiceSoap_PortType;
import vn.com.vndirect.boproxyclient.onlinetrading.OnlineTradingServiceLocator;
import vn.com.vndirect.boproxyclient.onlinetrading.OnlineTradingServiceSoap_PortType;
import vn.com.vndirect.boproxyclient.webagentservice.WebAgentServiceLocator;
import vn.com.vndirect.boproxyclient.webagentservice.WebAgentServiceSoap_PortType;
import vn.com.vndirect.crmclient.leadContract.ServiceLocator;
import vn.com.vndirect.crmclient.leadContract.ServiceSoap_PortType;
import vn.com.vndirect.domain.crm.Header;
import vn.com.vndirect.webservice.WebServiceLocator;
import vn.com.vndirect.wsclient.audit.IAuditServiceLocator;
import vn.com.vndirect.wsclient.audit.IAuditServicePortType;
import vn.com.vndirect.wsclient.newsreader.INewsReaderServiceLocator;
import vn.com.vndirect.wsclient.newsreader.INewsReaderServicePortType;
import vn.com.vndirect.wsclient.onlineuser.IOnlineUserServiceLocator;
import vn.com.vndirect.wsclient.onlineuser.IOnlineUserServicePortType;
import vn.com.vndirect.wsclient.openaccount.IOpenAccountServiceLocator;
import vn.com.vndirect.wsclient.openaccount.IOpenAccountServicePortType;
import vn.com.vndirect.wsclient.streamquotes.IStreamQuotesServiceLocator;
import vn.com.vndirect.wsclient.streamquotes.IStreamQuotesServicePortType;
import vn.com.vndirect.wsclient.tradingideas.ITradingIdeasServiceLocator;
import vn.com.vndirect.wsclient.tradingideas.ITradingIdeasServicePortType;
import vn.com.vndirect.wsutility.AuthenticationHelper;

import com.homedirect.proxy.PaydirectWebservice.OnlineWebservice_PortType;
import com.homedirect.proxy.PaydirectWebservice.OnlineWebservice_ServiceLocator;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @author Duc Nguyen
 * 
 */
public class BaseManager implements IBaseManager {

	@Autowired
	private WebServiceLocator serviceLocator;
	
	private AuthenticationHelper authenticationHelper;
	private Header header;

	/**
	 * Using to caching chart image
	 */
	@Autowired
	protected GeneralCacheAdministrator oscache;

	/**
	 * @param oscache
	 *            the oscache to set
	 */
	public void setOscache(GeneralCacheAdministrator oscache) {
		this.oscache = oscache;
	}

	/**
	 * @param serviceLocator
	 */
	public void setServiceLocator(WebServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	/**
	 * @param authenticationHelper
	 *            the authenticationHelper to set
	 */
	public void setAuthenticationHelper(AuthenticationHelper authenticationHelper) {
		this.authenticationHelper = authenticationHelper;
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	@SuppressWarnings("static-access")
	protected vn.com.vndirect.wsclient.AuthenticationHeader getVndsAuthenticationHeader() throws Exception {
		return authenticationHelper.getVndsAuthHeader();
	}

	@SuppressWarnings("static-access")
	protected vn.com.vndirect.boproxyclient.AuthenticationHeader getBoAuthenticationHeader() throws Exception {
		return authenticationHelper.getBoProxyAuthHeader();
	}
	
	@SuppressWarnings("static-access")
	protected vn.com.vndirect.homedirectclient.AuthenticationHeader getHomedirectAuthHeader() throws Exception {
		return authenticationHelper.getHomedirectAuthHeader();
	}

	/* ################### WEB SERVICE PORT TYPE ####################### */

	/**
	 * @return
	 * @throws Exception
	 */
	protected IStreamQuotesServicePortType getIStreamQuotesServicePortType() throws Exception {
		IStreamQuotesServiceLocator serLocator = serviceLocator.retrieveStreamQuotesServiceLocator();
		return serLocator.getIStreamQuotesServiceHttpPort();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected IOnlineUserServicePortType getIOnlineUserServicePortType() throws Exception {
		IOnlineUserServiceLocator serLocator = serviceLocator.retrieveOnlineUserServiceLocator();
		return serLocator.getIOnlineUserServiceHttpPort();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected IOpenAccountServicePortType getIOpenAccountServicePortType() throws Exception {
		IOpenAccountServiceLocator serLocator = serviceLocator.retrieveOpenAccountServiceLocator();
		return serLocator.getIOpenAccountServiceHttpPort();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected IAuditServicePortType getIAuditServicePortType() throws Exception {
		IAuditServiceLocator serLocator = serviceLocator.retrieveAuditServiceLocator();
		return serLocator.getIAuditServiceHttpPort();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected INewsReaderServicePortType getINewsReaderServicePortType() throws Exception {
		INewsReaderServiceLocator serLocator = serviceLocator.retrieveNewsReaderServiceLocator();
		return serLocator.getINewsReaderServiceHttpPort();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected ITradingIdeasServicePortType getIdeasServicePortType() throws Exception {
		ITradingIdeasServiceLocator serLocator = serviceLocator.retrieveIdeasServiceLocator();
		return serLocator.getITradingIdeasServiceHttpPort();
	}

	/* ################# BO SERVICE PORT TYPE ##################### */

	/**
	 * @return
	 * @throws Exception
	 */
	protected OnlineTradingServiceSoap_PortType getOnlineTradingServicePortType() throws Exception {
		OnlineTradingServiceLocator serLocator = serviceLocator.retrieveOnlineTradingServiceLocator();
		return serLocator.getOnlineTradingServiceSoap();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected AuthenticateServiceSoap_PortType getAuthenticateServiceSoapPortType() throws Exception {
		AuthenticateServiceLocator serLocator = serviceLocator.retrieveAuthenticateServiceLocator();
		return serLocator.getAuthenticateServiceSoap();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected CustomerServiceSoap_PortType getCustomerServicePortType() throws Exception {
		CustomerServiceLocator serLocator = serviceLocator.retrieveCustomerServiceLocator();
		return serLocator.getCustomerServiceSoap();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected WebAgentServiceSoap_PortType getWebAgentServicePortType() throws Exception {
		WebAgentServiceLocator serLocator = serviceLocator.retrieveWebAgentServiceLocator();
		return serLocator.getWebAgentServiceSoap();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	protected BankingServiceSoap_PortType getBankingServicePortType() throws Exception {
		BankingServiceLocator serLocator = serviceLocator.retrieveBankingServiceLocator();
		return serLocator.getBankingServiceSoap();
	}

	/* ################# CRM SERVICE PORT TYPE ##################### */

	protected ServiceSoap_PortType getLeadContractServicePortType() throws Exception {
		ServiceLocator serLocator = serviceLocator.retrieveServiceLocator();
		return serLocator.getServiceSoap();
	}
	
	protected OnlineWebservice_PortType getPayDirectBankingService() throws Exception{
		OnlineWebservice_ServiceLocator onlineWebservice_ServiceLocator = serviceLocator.getOnlineWebservice_ServiceLocator();
		return onlineWebservice_ServiceLocator.getOnlineWebservicePort();
	}
	
	protected void initPolicy(){
		System.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					System.out.println("Warning: URL Host: " + hostname + " vs. "
							+ session.getPeerHost());
					return true;
				}
			};
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
