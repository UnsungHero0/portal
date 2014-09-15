package vn.com.vndirect.commons.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import vn.com.vndirect.boproxyclient.AuthenticationHeader;
import vn.com.vndirect.boproxyclient.onlinetrading.Account;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.i18n.Language;
import vn.com.vndirect.commons.web.AppContext;
import vn.com.vndirect.commons.web.AppContextHolder;
import vn.com.vndirect.domain.OnlineUser;
import vn.com.vndirect.domain.ServiceInfo;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.web.security.VNDSCasUser;
import vn.com.vndirect.wsutility.AuthenticationHelper;

@SuppressWarnings("unchecked")
public class SessionManager {
	/**
	 * 
	 * @return
	 */
	public static boolean isAuthenticated() {
		boolean result = false;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof AnonymousAuthenticationToken) {
				// not authenticated
			} else if (authentication instanceof Authentication) {
				result = true;
			}
		}
		return result;
	}

	public static void clearSession(HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession();

			/* get current language */
			Language curLang = I18NUtility.getCurrentLanguage(session);

			/* current deployment model */
			// String INSTANCE_FOR =
			// VNDirectWebUtilities.getDeploymentModel(session);

			// destroy current session
			// ServletActionContext.getRequest().getSession().invalidate();
			ServletActionContext.getContext().getSession().clear();
			session.invalidate();
			SecurityContextHolder.clearContext();
			session = request.getSession(true);
			//ServletActionContext.getContext().getSession().remove("");
			// create new session & set current language into
			I18NUtility.setCurrentLanguageByCode(curLang.getCode(), session);
			
			// VNDirectWebUtilities.setDeploymentModel(request.getSession(),
			// INSTANCE_FOR);
		}
	}

	/**
	 * 
	 * @author tungnq.nguyen
	 * 
	 */
	public abstract static class OnlineUsers {
		public static final String BO_AUTHENTIFATION_HEADER_KEY = "SessionManager.OnlineUsers.$BO_AUTHENTIFATION_HEADER_KEY$";
		public static final String LIST_ACCOUNTS_KEY = "SessionManager.OnlineUsers.$LIST_ACCOUNTS_KEY$";
		public static final String CURRENT_ACCOUNTS_KEY = "SessionManager.OnlineUsers.$CURRENT_ACCOUNTS_KEY$";

		public static final String VNDS_AUTHENTIFATION_HEADER_KEY = "SessionManager.OnlineUsers.$VNDS_AUTHENTIFATION_HEADER_KEY$";

		public static final String ONLINE_FLAG_KEY = "SessionManager.OnlineUsers.$ONLINE_FLAG_KEY$";
		public static final String SERVICE_INFO_CODES_KEY = "SessionManager.OnlineUsers.$SERVICE_INFO_CODES_KEY$";

		public static final String QOUTE_LIST_KEY = "SessionManager.OnlineUsers.$QOUTE_LIST_KEY$";

		/**
		 * 
		 * @return
		 */
		public static void setOnlineFlagOn() {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if (session != null) {
				ServletActionContext.getRequest().getSession().setAttribute(ONLINE_FLAG_KEY, "TRUE");
			}
		}

		/**
		 *
		 */
		public static void setOnlineFlagOff() {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if (session != null) {
				session.removeAttribute(ONLINE_FLAG_KEY);
			}
		}

		/**
		 * 
		 * @return
		 */
		public static boolean checkOnlineFlag() {
			HttpSession session = ServletActionContext.getRequest().getSession();
			return (session != null && session.getAttribute(ONLINE_FLAG_KEY) != null);
		}

		/**
		 * 
		 * @param listAcc
		 *            list of Account object
		 */
		public static void setListAccounts(List<Account> listAcc) {
			ServletActionContext.getRequest().getSession()
			        .setAttribute(LIST_ACCOUNTS_KEY, listAcc == null ? new ArrayList<Account>() : listAcc);
		}

		/**
		 * 
		 * @return
		 */
		public static List<Account> getListAccounts() {
			return (List<Account>) ServletActionContext.getRequest().getSession().getAttribute(LIST_ACCOUNTS_KEY);
		}

		/**
		 * 
		 * @param listQoutes
		 */
		public static void setListQoutes(List<String> listQoutes) {
			ServletActionContext.getRequest().getSession().setAttribute(QOUTE_LIST_KEY, listQoutes);
		}

		/**
		 * 
		 * @return
		 */
		public static List<String> getListQoutes() {
			return (List<String>) ServletActionContext.getRequest().getSession().getAttribute(QOUTE_LIST_KEY);
		}

		/**
		 * 
		 * @param accountNum
		 */
		public static void setCurrentAccountNumber(String accountNumber) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if (accountNumber != null) {
				session.setAttribute(CURRENT_ACCOUNTS_KEY, accountNumber);
			} else {
				session.removeAttribute(CURRENT_ACCOUNTS_KEY);
			}
		}

		/**
		 *
		 */
		public static String getCurrentAccountNumber() {
			Account currAcc = getCurrentAccount();
			return (currAcc == null ? null : currAcc.getAccountNumber());
		}

		/**
		 * 
		 * @return
		 */
		public static boolean hasCurrentAccountNubmer() {
			String accountNumber = getCurrentAccountNumber();
			return (accountNumber != null && accountNumber.length() > 0);
		}

		/**
		 * 
		 * @return
		 */
		public static Account getCurrentAccount() {
			String accountNumber = (String) ServletActionContext.getRequest().getSession().getAttribute(CURRENT_ACCOUNTS_KEY);
			Account account = getAccountByAccNumber(accountNumber);
			if (account == null) {
				// ++ get default account
				List<Account> listAcc = getListAccounts();
				return (listAcc == null || listAcc.size() == 0 ? null : listAcc.get(0));
			} else {
				return account;
			}
		}

		/**
		 * get account object by account number
		 * 
		 * @param accNumber
		 * @return
		 */
		public static Account getAccountByAccNumber(String accNumber) {
			if (accNumber != null) {
				List<Account> listAccounts = getListAccounts();
				if (listAccounts != null) {
					for (Account account : listAccounts) {
						if (account != null && accNumber.equalsIgnoreCase(account.getAccountNumber())) {
							return account;
						}
					}
				}// END: if(listAccounts != null)
			}
			return null;
		}

		/**
		 * 
		 * @param authHeader
		 */
		public static void setBOAuthenticationHeader(AuthenticationHeader authHeader) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if (authHeader != null) {
				session.setAttribute(BO_AUTHENTIFATION_HEADER_KEY, authHeader);
			} else {
				session.removeAttribute(BO_AUTHENTIFATION_HEADER_KEY);
			}
		}

		/**
		 * 
		 * @return
		 */
		public static AuthenticationHeader getBOAuthenticationHeader() {
			AuthenticationHeader header = (AuthenticationHeader) ServletActionContext.getRequest().getSession()
			        .getAttribute(BO_AUTHENTIFATION_HEADER_KEY);
			return header;
		}

		/**
		 * 
		 * @param session
		 * @return
		 */
		public static vn.com.vndirect.wsclient.AuthenticationHeader getVNDSAuthenticationHeader() {
			vn.com.vndirect.wsclient.AuthenticationHeader header = (vn.com.vndirect.wsclient.AuthenticationHeader) ServletActionContext
			        .getRequest().getSession().getAttribute(VNDS_AUTHENTIFATION_HEADER_KEY);
			return (header == null ? AuthenticationHelper.getVndsAuthHeader() : header);
		}

		/**
		 * 
		 * @param authHeader
		 */
		public static void setVNDSAuthenticationHeader(vn.com.vndirect.wsclient.AuthenticationHeader authHeader) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if (authHeader != null) {
				session.setAttribute(VNDS_AUTHENTIFATION_HEADER_KEY, authHeader);
			} else {
				session.removeAttribute(VNDS_AUTHENTIFATION_HEADER_KEY);
			}
		}

		/**
		 * 
		 * @param vndsCasUser
		 * @return
		 */
		public static OnlineUser convert(VNDSCasUser vndsCasUser) {
			OnlineUser onlineUser = null;
			if (vndsCasUser != null) {
				/*
				 * FULLNAME ("fullName"), STATUS ("status"), USER_ID ("userId"),
				 * USER_NAME ("userName"), BO_CUSTOMER_ID ("boCustomerId"),
				 * 
				 * EMAIL ("email"),
				 * 
				 * IDG_ID ("idgId"), USER_TYPE ("userType"), SERVICES
				 * ("services");
				 */
				onlineUser = new OnlineUser();
				onlineUser.setFullName(vndsCasUser.getFullName());
				onlineUser.setStatus(vndsCasUser.getStatus());
				onlineUser.setUserName(vndsCasUser.getUsername());
				onlineUser.setOnlineUserId(vndsCasUser.getUserId());
				onlineUser.setCustomerId(vndsCasUser.getBOCustId());
				onlineUser.setEmail(vndsCasUser.getEmail());
				onlineUser.setIdgId(vndsCasUser.getIdgId());

				if (vndsCasUser.getServices() != null) {
					StringTokenizer strToken = new StringTokenizer(vndsCasUser.getServices(), "[,; ]");
					List<ServiceInfo> lstServiceInfo = new ArrayList<ServiceInfo>();
					ServiceInfo srvInfo;

					String srvCode;
					while (strToken.hasMoreElements()) {
						srvCode = strToken.nextToken();
						srvInfo = new ServiceInfo();
						srvInfo.setServiceCode(srvCode);
						srvInfo.setServiceDesc(srvCode);
						srvInfo.setServiceInfoId(Long.valueOf(1));
					}
					onlineUser.setServiceInfo(lstServiceInfo.toArray(new ServiceInfo[lstServiceInfo.size()]));
				}
			}
			return onlineUser;
		}

		/**
		 * 
		 * @param session
		 * @return
		 */
		public static VNDSCasUser getCurrentOnlineUser() {
			return getVNDSCasUser();
		}

		/**
		 * 
		 * @return
		 */
		private static VNDSCasUser getVNDSCasUser() {
			AppContext appContext = AppContextHolder.get();
			return (appContext == null ? null : (VNDSCasUser) appContext.getAttr(VNDSCasUser.CAS_USER_APP_CTX_KEY));
		}

		/**
		 * 
		 * @return
		 */
		public static String getUserName() {
			VNDSCasUser vndsUser = getVNDSCasUser();
			return (vndsUser == null ? "Nobody..." : vndsUser.getUsername());
		}

		/**
		 * 
		 * @return
		 */
		public static String getFullName() {
			VNDSCasUser vndsUser = getVNDSCasUser();
			return (vndsUser == null ? "..." : vndsUser.getFullName());
		}

		/**
		 * 
		 * @return
		 */
		public static Long getOnlineUserId() {
			VNDSCasUser vndsUser = getVNDSCasUser();
			return (vndsUser == null ? 0 : vndsUser.getUserId());
		}

		/**
		 * 
		 * @return
		 */
		public static String getIdentityGuardId() {
			VNDSCasUser vndsUser = getVNDSCasUser();
			return (vndsUser == null ? "" : vndsUser.getIdgId());
		}

		/**
		 * 
		 * @return
		 */
		public static String getBOCustomerId() {
			VNDSCasUser vndsUser = getVNDSCasUser();
			return (vndsUser == null ? "" : vndsUser.getBOCustId());
		}
	}

	/**
	 * Store symbol of company in session 
	 */
	public static void setSymbolCompany(String symbol) {
		ServletActionContext.getRequest().getSession()
		        .setAttribute("symbolCompany", symbol);
	}

	/**
	 * 
	 * @return
	 */
	public static String getSymbolCompany() {
		return (String) (ServletActionContext.getRequest().getSession().getAttribute("symbolCompany"));
	}
	/**
	 * This class is used to manage CurrentCompanyForQuote object for Quotes
	 * module.
	 * 
	 * @author tungnq
	 * 
	 */
//	public abstract static class IfoCompanyForQuote {
//		// //+++++++++ Support for Quotes module
//		public static String CURRENT_COMPANY_FOR_QUOTE = "$CURRENT_COMPANY_FOR_QUOTE$";
//
//		/**
//		 * Call from Strust2.x Action
//		 */
//		public static boolean setObject(CurrentCompanyForQuote currentCompanyForQuote) {
//			return setObject(currentCompanyForQuote, ServletActionContext.getRequest());
//		}
//
//		/**
//		 * Set CurrentCompanyForQuote current selected company.
//		 * 
//		 * @param ifoCompany
//		 */
//		public static boolean setObject(CurrentCompanyForQuote currentCompanyForQuote, HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			if (currentCompanyForQuote == null || session == null) {
//				session.removeAttribute(CURRENT_COMPANY_FOR_QUOTE);
//				return false;
//			}
//			if (currentCompanyForQuote != null) {
//				session.setAttribute(CURRENT_COMPANY_FOR_QUOTE, currentCompanyForQuote);
//			}
//
//			return true;
//		}
//
//		/**
//		 * Get current selected CurrentCompanyForQuote object Call from
//		 * Strust2.x Action
//		 * 
//		 * @return
//		 */
//		public static CurrentCompanyForQuote getObject() {
//			return getObject(ServletActionContext.getRequest());
//		}
//
//		public static CurrentCompanyForQuote getObject(HttpServletRequest request) {
//			HttpSession session = request == null ? null : request.getSession();
//			if (session == null) {
//				return null;
//			}
//			return (CurrentCompanyForQuote) session.getAttribute(CURRENT_COMPANY_FOR_QUOTE);
//		}
//
//		/**
//		 * get symbol code of selected company
//		 * 
//		 * @return
//		 */
//		public static String getSymbol() {
//			return getSymbol(ServletActionContext.getRequest());
//		}
//
//		/**
//		 * 
//		 * @param request
//		 * @return
//		 */
//		public static String getSymbol(HttpServletRequest request) {
//			CurrentCompanyForQuote currentObj = getObject(request);
//			return (currentObj == null ? null : currentObj.getSymbol());
//		}
//
//		/**
//		 * get companyId code of selected company
//		 * 
//		 * @return
//		 */
//		public static Long getCompanyId() {
//			CurrentCompanyForQuote currentObj = getObject();
//			return (currentObj == null ? null : currentObj.getCompanyId());
//		}
//
//		/**
//		 * get current exchange code of selected company
//		 * 
//		 * @return
//		 */
//		public static String getCurrentExchangeCode() {
//			CurrentCompanyForQuote currentObj = getObject();
//			return (currentObj == null ? null : currentObj.getCurrentExchangeCode());
//		}
//
//		/**
//		 * 
//		 * @param request
//		 * @return
//		 */
//		public static String getCurrentExchangeCode(HttpServletRequest request) {
//			CurrentCompanyForQuote currentObj = getObject(request);
//			return (currentObj == null ? null : currentObj.getCurrentExchangeCode());
//		}
//	}

	/**
	 * 
	 * @author tungnq
	 * 
	 */
	// public abstract static class CurrentTAChartInfo {
	// private final static String TECHNICAL_ANALYSIS_CHART_INFO =
	// "%TECHNICAL_ANALYSIS_CHART_INFO%";
	//
	// /**
	// *
	// * @param session
	// * @return
	// */
	// public static TAChartInfo getTAChartInfo(HttpSession session) {
	// TAChartInfo info =
	// (TAChartInfo)session.getAttribute(TECHNICAL_ANALYSIS_CHART_INFO);
	// if(info == null) {
	// info = resetTAChartInfo(session);
	// }
	// return info;
	// }
	//
	// /**
	// *
	// * @param session
	// * @return
	// */
	// public static TAChartInfo resetTAChartInfo(HttpSession session) {
	// TAChartInfo info =
	// (TAChartInfo)session.getAttribute(TECHNICAL_ANALYSIS_CHART_INFO);
	// if(info == null) {
	// info = new TAChartInfo();
	// session.setAttribute(TECHNICAL_ANALYSIS_CHART_INFO, info);
	// }
	// info.setSymbol(IfoCompanyForQuote.getSymbol(session));
	// info.setExchangeCode(IfoCompanyForQuote.getCurrentExchangeCode(session));
	//
	// info.addIndicator(new
	// IndicatorInfo(TAConstants.Indicator.ChartVolumeIndicator));
	//
	// // IndicatorInfo indiInfo = new
	// IndicatorInfo(TAConstants.Indicator.ChartSMAIndicator);
	// // indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_1, 5);
	// // indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_2, 15);
	// // indiInfo.addParam(TAConstants.IndicatorParams.SMA.PERIOD_3, 25);
	// // info.addIndicator(indiInfo);
	//
	// info.setLegend(true);
	//
	// return info;
	// }
	//
	// /**
	// *
	// * @param session
	// */
	// public static void removeTAChartInfo(HttpSession session) {
	// session.removeAttribute(TECHNICAL_ANALYSIS_CHART_INFO);
	// }
	// }
}