/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Nov 18, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.boproxyclient.AuthenticationHeader;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.web.security.VNDSCasUser;
import vn.com.web.commons.filemngt.FilePathBean;
import vn.com.web.commons.servercfg.ServerConfig;
import vn.com.web.commons.utility.URLUtils;

/**
 * @author tungnq
 * 
 */
public class UtilityFunctionsTag {
	/**
	 * 
	 * @return
	 */
	public static boolean hasTradingOnlineUser() {
		try {
			AuthenticationHeader boAuthHeader = SessionManager.OnlineUsers.getBOAuthenticationHeader();
			VNDSCasUser onlineUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			return ((onlineUser != null && boAuthHeader != null && boAuthHeader.getSessionId() != null));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean hasOnlineUser() {
		try {
			VNDSCasUser onlineUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			return ((onlineUser != null));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isFreeOnlineUser() {
		try {
			VNDSCasUser onlineUser = SessionManager.OnlineUsers.getCurrentOnlineUser();
			String freeRegStatus = ServerConfig.getOnlineValue(Constants.IServerConfig.DataRef.ItemCodes.OnlineUserStatus.ONLINE_FREE_REGISTER);
			return (freeRegStatus != null && onlineUser != null && freeRegStatus.equalsIgnoreCase(onlineUser.getStatus()));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getServiceInfoCodes() {
		try {
			VNDSCasUser onlineUser = SessionManager.OnlineUsers.getCurrentOnlineUser();

			return onlineUser == null ? "" : onlineUser.getServices();
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public static String getUserOnlineName() {
		try {
			return SessionManager.OnlineUsers.getFullName();
		} catch (Exception e) {
			return "???";
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getRequestUri() {
		try {
			return (String) ServletActionContext.getRequest().getAttribute(Constants.RequestKeys.REQUEST_URL);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * This method is used to get url requested: ../../online/brokerage/VNMarket.do
	 * 
	 * @return
	 */
	public static String getRequestUrl() {
		return (String) ServletActionContext.getRequest().getAttribute(Constants.RequestKeys.REQUEST_ACTION_FORM_URL);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean greaterThanZezo(double value) {
		return (value > 0);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean lessThanZezo(double value) {
		return (value < 0);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String formatCurrency(double value) {
		try {
			return VNDirectWebUtilities.getStrDoubleWithScale(value, 0);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String formatCurrencyScale2(double value) {
		try {
			return VNDirectWebUtilities.getStrDoubleWithScale2(value);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static boolean isHASTCIndex() {
		return isSymbol(VNDirectWebUtilities.getHASTCIndex());
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static boolean isHOSTCIndex() {
		return isSymbol(VNDirectWebUtilities.getHOSTCIndex());
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isSymbol(String symbol) {
		try {
			String currentSymbol = SessionManager.getSymbolCompany();
			return symbol.equalsIgnoreCase(currentSymbol);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param pageContext
	 * @param symbol
	 * @return
	 */
	public static boolean isLanguage(String lang) {
		try {
			lang = (lang == null ? "" : lang.trim());
			// FIXME: add locale here
			String locale = "VN";// I18NUtility.getCurrentLocale(pageContext.getSession());

			return lang.equalsIgnoreCase(locale);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isVNLanguage() {
		return isLanguage("vn");
	}

	/**
	 * 
	 * @param currentCompanyForQuote
	 * @return
	 */
	public static boolean isTradingTime(PageContext pageContext) {
		try {
			String key = Constants.RequestKeys.CURRENT_COMPANY_FOR_QUOTES;
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

			CurrentCompanyForQuote currentCompanyForQuote = (CurrentCompanyForQuote) request.getAttribute(key);
			if (currentCompanyForQuote == null) {
				currentCompanyForQuote = (CurrentCompanyForQuote) request.getSession().getAttribute(key);
			}
			return VNDirectWebUtilities.isOpen(currentCompanyForQuote.getCurrentExchangeCode());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	/*
	 * public static boolean isDeploymentModelTradingOnline(PageContext pageContext) { return VNDirectWebUtilities.isDeploymentModelTradingOnline(pageContext.getSession()); }
	 *//**
	 * 
	 * @param pageContext
	 * @return
	 */
	/*
	 * public static boolean isDeploymentModelWebAgent(PageContext pageContext) { return VNDirectWebUtilities.isDeploymentModelWebAgent(pageContext.getSession()); }
	 */

	/**
	 * 
	 * @param pageContext
	 * @return
	 */
	public static String getVeriSignUrl(PageContext pageContext) {
		try {
			String url = ServerConfig.getOnlineValue(Constants.IServerConfig.VERISIGN_URL);
			if (url != null) {
				String serverNameKey = "{server_name}";
				FilePathBean serverInfo = URLUtils.getServerInfo((HttpServletRequest) pageContext.getRequest());
				url = VNDirectWebUtilities.replaceString(url, serverNameKey, serverInfo.getHost());
			}
			return url;
		} catch (Exception e) {
			return "#";
		}
	}
}
