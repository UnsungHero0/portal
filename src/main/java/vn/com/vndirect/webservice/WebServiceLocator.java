/**
 * 
 */
package vn.com.vndirect.webservice;

import java.util.Map;

import com.homedirect.proxy.PaydirectWebservice.OnlineWebservice_ServiceLocator;

import vn.com.vndirect.boproxyclient.authenticationservice.AuthenticateServiceLocator;
import vn.com.vndirect.boproxyclient.corebank.BankingServiceLocator;
import vn.com.vndirect.boproxyclient.customerservice.CustomerServiceLocator;
import vn.com.vndirect.boproxyclient.onlinetrading.OnlineTradingServiceLocator;
import vn.com.vndirect.boproxyclient.webagentservice.WebAgentServiceLocator;
import vn.com.vndirect.crmclient.leadContract.ServiceLocator;
import vn.com.vndirect.wsclient.audit.IAuditServiceLocator;
import vn.com.vndirect.wsclient.newsreader.INewsReaderServiceLocator;
import vn.com.vndirect.wsclient.onlineuser.IOnlineUserServiceLocator;
import vn.com.vndirect.wsclient.openaccount.IOpenAccountServiceLocator;
import vn.com.vndirect.wsclient.streamquotes.IStreamQuotesServiceLocator;
import vn.com.vndirect.wsclient.tradingideas.ITradingIdeasServiceLocator;

/**
 * @author Duc Nguyen
 * 
 */
public class WebServiceLocator {

	private final String TIMEOUT_VNDS = "TIMEOUT_VNDS";
	private final String TIMEOUT_BPS = "TIMEOUT_BPS";
	private final String TIMEOUT_CRM = "TIMEOUT_CRM";

	private final String STREAM_QUOTES_WS_URL = "STREAM_QUOTES_WS_URL";
	private final String ONLINE_USER_WS_URL = "ONLINE_USER_WS_URL";
	// private final String ONLINE_ACCOUNT_WS_URL = "ONLINE_ACCOUNT_WS_URL";
	// private final String PORTFOLIO_WS_URL = "PORTFOLIO_WS_URL";
	// private final String OTC_WS_URL = "OTC_WS_URL";
	// private final String WEB_AGENT_USER_WS_URL = "WEB_AGENT_USER_WS_URL";
	// private final String ALERT_WS_URL = "ALERT_WS_URL";
	private final String AUDIT_WS_URL = "AUDIT_WS_URL";
	// private final String REF_PROGRAM_WS_URL = "REF_PROGRAM_WS_URL";
	private final String NEWS_READER_WS_URL = "NEWS_READER_WS_URL";
	private final String TRADING_IDEAS_WS_URL = "TRADING_IDEAS_WS_URL";
	// private final String OSC_WS_URL = "OSC_WS_URL";
	private final String OPEN_ACCOUNT_WS_URL = "OPEN_ACCOUNT_WS_URL";

	private final String BO_ONLINE_TRADING_WS_URL = "BO_ONLINE_TRADING_WS_URL";
	private final String BO_AUTHENTICATE_WS_URL = "BO_AUTHENTICATE_WS_URL";
	private final String BO_CUSTOMER_WS_URL = "BO_CUSTOMER_WS_URL";
	private final String BO_WEB_AGENT_WS_URL = "BO_WEB_AGENT_WS_URL";
	private final String BO_CORE_BANK_WS_URL = "BO_CORE_BANK_WS_URL";

	private final String CRM_CREATE_CONTRACT_WS_URL = "CRM_CREATE_CONTRACT_WS_URL";
	private final String PAYDIRECT_BANKSERVICE_WS_URL = "PAYDIRECT_BANKSERVICE_WS_URL";
	private Map<String, String> wsurl;

	private Map<String, Integer> wstimeout;

	/**
	 * @param wsurl
	 *            the wsurl to set
	 */
	public void setWsurl(Map<String, String> wsurl) {
		this.wsurl = wsurl;
	}

	/**
	 * @param wstimeout
	 *            the wstimeout to set
	 */
	public void setWstimeout(Map<String, Integer> wstimeout) {
		this.wstimeout = wstimeout;
	}

	/* ################### WEB SERVICE LOCATOR ####################### */

	/**
	 * @return
	 * @throws Exception
	 */
	public IStreamQuotesServiceLocator retrieveStreamQuotesServiceLocator() throws Exception {
		IStreamQuotesServiceLocator serLocator = new IStreamQuotesServiceLocator();
		serLocator.setIStreamQuotesServiceHttpPortEndpointAddress(wsurl.get(STREAM_QUOTES_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public IOnlineUserServiceLocator retrieveOnlineUserServiceLocator() throws Exception {
		IOnlineUserServiceLocator serLocator = new IOnlineUserServiceLocator();
		serLocator.setIOnlineUserServiceHttpPortEndpointAddress(wsurl.get(ONLINE_USER_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IOnlineAccountServiceLocator retrieveOnlineAccountServiceLocator() throws Exception { IOnlineAccountServiceLocator serLocator = new IOnlineAccountServiceLocator();
	 * serLocator.setIOnlineAccountServiceHttpPortEndpointAddress(wsurl.get(ONLINE_ACCOUNT_WS_URL)); return serLocator; }
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	public IOpenAccountServiceLocator retrieveOpenAccountServiceLocator() throws Exception {
		IOpenAccountServiceLocator serLocator = new IOpenAccountServiceLocator();
		serLocator.setIOpenAccountServiceHttpPortEndpointAddress(wsurl.get(OPEN_ACCOUNT_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IPortfoliosServiceLocator retrievePortfoliosServiceLocator() throws Exception { IPortfoliosServiceLocator serLocator = new IPortfoliosServiceLocator();
	 * serLocator.setIPortfoliosServiceHttpPortEndpointAddress(wsurl.get(PORTFOLIO_WS_URL)); return serLocator; }
	 *//**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IOTCServiceLocator retrieveOtcServiceLocator() throws Exception { IOTCServiceLocator serLocator = new IOTCServiceLocator();
	 * serLocator.setIOTCServiceHttpPortEndpointAddress(wsurl.get(OTC_WS_URL)); return serLocator; }
	 *//**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IWebAgentUserServiceLocator retrieveWebAgentUserServiceLocator() throws Exception { IWebAgentUserServiceLocator serLocator = new IWebAgentUserServiceLocator();
	 * serLocator.setIWebAgentUserServiceHttpPortEndpointAddress(wsurl.get(WEB_AGENT_USER_WS_URL)); return serLocator; }
	 *//**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IAlertServiceLocator retrieveAlertServiceLocator() throws Exception { IAlertServiceLocator serLocator = new IAlertServiceLocator();
	 * serLocator.setIAlertServiceHttpPortEndpointAddress(wsurl.get(ALERT_WS_URL)); return serLocator; }
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	public IAuditServiceLocator retrieveAuditServiceLocator() throws Exception {
		IAuditServiceLocator serLocator = new IAuditServiceLocator();
		serLocator.setIAuditServiceHttpPortEndpointAddress(wsurl.get(AUDIT_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IRefProgramServiceLocator retrieveRefProgramServiceLocator() throws Exception { IRefProgramServiceLocator serLocator = new IRefProgramServiceLocator();
	 * serLocator.setIRefProgramServiceHttpPortEndpointAddress(wsurl.get(REF_PROGRAM_WS_URL)); return serLocator; }
	 *//**
	 * @return
	 * @throws Exception
	 */
	public INewsReaderServiceLocator retrieveNewsReaderServiceLocator() throws Exception {
		INewsReaderServiceLocator serLocator = new INewsReaderServiceLocator();
		serLocator.setINewsReaderServiceHttpPortEndpointAddress(wsurl.get(NEWS_READER_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ITradingIdeasServiceLocator retrieveIdeasServiceLocator() throws Exception {
		ITradingIdeasServiceLocator serLocator = new ITradingIdeasServiceLocator();
		serLocator.setITradingIdeasServiceHttpPortEndpointAddress(wsurl.get(TRADING_IDEAS_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_VNDS).intValue());
		return serLocator;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/*
	 * public IOSCServiceLocator retrieveOscServiceLocator() throws Exception { IOSCServiceLocator serLocator = new IOSCServiceLocator();
	 * serLocator.setIOSCServiceHttpPortEndpointAddress(wsurl.get(OSC_WS_URL)); return serLocator; }
	 */

	/* ################# BO SERVICE LOCATOR ##################### */

	/**
	 * @return
	 * @throws Exception
	 */
	public OnlineTradingServiceLocator retrieveOnlineTradingServiceLocator() throws Exception {
		OnlineTradingServiceLocator serLocator = new OnlineTradingServiceLocator();
		serLocator.setOnlineTradingServiceSoapEndpointAddress(wsurl.get(BO_ONLINE_TRADING_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_BPS).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public OnlineTradingServiceLocator retrieveOnlineTradingServiceLocator12() throws Exception {
	// OnlineTradingServiceLocator serLocator = new OnlineTradingServiceLocator();
	// serLocator.setOnlineTradingServiceSoap12EndpointAddress(wsurl.get(BO_ONLINE_TRADING_WS_URL));
	// return serLocator;
	// }

	/**
	 * @return
	 * @throws Exception
	 */
	public AuthenticateServiceLocator retrieveAuthenticateServiceLocator() throws Exception {
		AuthenticateServiceLocator serLocator = new AuthenticateServiceLocator();
		serLocator.setAuthenticateServiceSoapEndpointAddress(wsurl.get(BO_AUTHENTICATE_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_BPS).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public AuthenticateServiceLocator retrieveAuthenticateServiceLocator12() throws Exception {
	// AuthenticateServiceLocator serLocator = new AuthenticateServiceLocator();
	// serLocator.setAuthenticateServiceSoap12EndpointAddress(wsurl.get(BO_AUTHENTICATE_WS_URL));
	// return serLocator;
	// }

	/**
	 * @return
	 * @throws Exception
	 */
	public CustomerServiceLocator retrieveCustomerServiceLocator() throws Exception {
		CustomerServiceLocator serLocator = new CustomerServiceLocator();
		serLocator.setCustomerServiceSoapEndpointAddress(wsurl.get(BO_CUSTOMER_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_BPS).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public CustomerServiceLocator retrieveCustomerServiceLocator12() throws Exception {
	// CustomerServiceLocator serLocator = new CustomerServiceLocator();
	// serLocator.setCustomerServiceSoap12EndpointAddress(wsurl.get(BO_CUSTOMER_WS_URL));
	// return serLocator;
	// }

	/**
	 * @return
	 * @throws Exception
	 */
	public WebAgentServiceLocator retrieveWebAgentServiceLocator() throws Exception {
		WebAgentServiceLocator serLocator = new WebAgentServiceLocator();
		serLocator.setWebAgentServiceSoapEndpointAddress(wsurl.get(BO_WEB_AGENT_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_BPS).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public WebAgentServiceLocator retrieveAgentServiceLocator12() throws Exception {
	// WebAgentServiceLocator serLocator = new WebAgentServiceLocator();
	// serLocator.setWebAgentServiceSoap12EndpointAddress(wsurl.get(BO_WEB_AGENT_WS_URL));
	// return serLocator;
	// }

	/**
	 * @return
	 * @throws Exception
	 */
	public BankingServiceLocator retrieveBankingServiceLocator() throws Exception {
		BankingServiceLocator serLocator = new BankingServiceLocator();
		serLocator.setBankingServiceSoapEndpointAddress(wsurl.get(BO_CORE_BANK_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_BPS).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public BankingServiceLocator retrieveBankingServiceLocator12() throws Exception {
	// BankingServiceLocator serLocator = new BankingServiceLocator();
	// serLocator.setBankingServiceSoap12EndpointAddress(wsurl.get(BO_CORE_BANK_WS_URL));
	// return serLocator;
	// }

	/* ################# CRM SERVICE LOCATOR ##################### */

	/**
	 * @return
	 * @throws Exception
	 */
	public ServiceLocator retrieveServiceLocator() throws Exception {
		ServiceLocator serLocator = new ServiceLocator();
		serLocator.setServiceSoapEndpointAddress(wsurl.get(CRM_CREATE_CONTRACT_WS_URL));
		serLocator.setTimeout(wstimeout.get(TIMEOUT_CRM).intValue());
		return serLocator;
	}

	// /**
	// * @return
	// * @throws Exception
	// */
	// public ServiceLocator retrieveServiceLocator12() throws Exception {
	// ServiceLocator serLocator = new ServiceLocator();
	// serLocator.setServiceSoap12EndpointAddress(wsurl.get(CRM_CREATE_CONTRACT_WS_URL));
	// return serLocator;
	// }
	
	public OnlineWebservice_ServiceLocator getOnlineWebservice_ServiceLocator() throws Exception{
		OnlineWebservice_ServiceLocator serLocator = new OnlineWebservice_ServiceLocator();
		serLocator.setOnlineWebservicePortEndpointAddress(wsurl.get(PAYDIRECT_BANKSERVICE_WS_URL));
		return serLocator;
	}

}
