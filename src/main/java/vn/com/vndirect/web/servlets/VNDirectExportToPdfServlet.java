package vn.com.vndirect.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vn.com.vndirect.business.IAccountManager;
import vn.com.vndirect.business.WebReportManager;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.report.PersonalAccountExt;
import vn.com.web.commons.utility.SpringUtils;

@SuppressWarnings("unchecked")
public class VNDirectExportToPdfServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542083113830613965L;
	private static Logger logger = Logger.getLogger(VNDirectExportToPdfServlet.class);

	public VNDirectExportToPdfServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String LOCATION = "::doPost()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");

		String accountID = VNDirectWebUtilities.cleanString(request.getParameter("ID_KEY"));
		String reportKey = VNDirectWebUtilities.cleanString(request.getParameter("REPORT_KEY"));
		OnlineAccount onlineAccount = new OnlineAccount();

		Map<String, String> onlineAccountMap = new HashMap<String, String>();

		try {
			if (accountID.trim().length() > 0) {
				onlineAccount = new OnlineAccount();
				onlineAccount.setOnlineAccountId(new Long(accountID));
				onlineAccount.setReportKey(reportKey);

				onlineAccountMap = this.retrievePendingAccount(request, onlineAccount);
			} else {
				HttpSession session = (HttpSession) request.getSession();
				onlineAccountMap = (Map<String, String>) session.getAttribute("ACCOUNT_DATA");
				onlineAccountMap = onlineAccountMap == null ? new HashMap<String, String>() : onlineAccountMap;
			}

			String pdfType = VNDirectWebUtilities.cleanString(request.getParameter("PDF_TYPE"));
			if ("1".equals(pdfType) || "4".equals(pdfType)) {
				// create Personal pdf
				createPdf1(request, response, onlineAccountMap, pdfType);
			} else if ("2".equals(pdfType) || "5".equals(pdfType)) {
				// create tel1 pdf
				createPdf2(request, response, onlineAccountMap, pdfType);
			} else if ("3".equals(pdfType) || "6".equals(pdfType)) {
				// create prime account service pdf
				createPdf3(request, response, onlineAccountMap, pdfType);
			} else if ("7".equals(pdfType)) {
				// create trading grant power pdf
				createPdf4(request, response, onlineAccountMap, pdfType);
			}
		} catch (Exception e) {
			logger.error(LOCATION + ":: " + e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
	}

	/**
	 * 
	 * @param request
	 * @param onlineAccount
	 * @return
	 */
	private Map<String, String> retrievePendingAccount(HttpServletRequest request, OnlineAccount onlineAccount) {
		final String LOCATION = "::retrievePendingAccount()";
		Map<String, String> result = null;
		try {
			result = this.getAccountManager().retrievePendingAccount(onlineAccount);
		} catch (Exception e) {
			logger.error(LOCATION + e);
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	private WebReportManager getVNDirectReportManager() throws ServletException {
		try {
			// return (IReportManager) SpringUtils.getBean("VNDirectReportManager");
			return (WebReportManager) SpringUtils.getBean("WebReportManager");
		} catch (Exception e) {
			throw new ServletException("getVNDirectReportManager()", e);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param onlineAccount
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 */
	private void createPdf1(HttpServletRequest request, HttpServletResponse response, Map<String, String> onlineAccountMap, String pdfType) throws ServletException, IOException {
		final String LOCATION = "::createPdf1()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		try {
			PersonalAccountExt pa = new PersonalAccountExt();
			this.populateData(pa, onlineAccountMap);
			WebReportManager reportManager = this.getVNDirectReportManager();

			byte[] out = reportManager.openPersonalAccount(pa);

			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "1".equals(pdfType) ? "inline; fileName=open_account_form.pdf" : "attachment; fileName=tele_online_form.pdf");
			response.setContentLength(out.length);
			outputStream.write(out, 0, out.length);
			outputStream.flush();
			outputStream.close();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":END");
		} catch (Exception e) {
			logger.error(LOCATION + ":" + e);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param onlineAccount
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createPdf2(HttpServletRequest request, HttpServletResponse response, Map<String, String> onlineAccountMap, String pdfType) throws ServletException, IOException {
		final String LOCATION = "::createPdf2()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		try {
			WebReportManager reportManager = this.getVNDirectReportManager();

			// String fullName = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME);
			// String idNumber = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
			// String remoteTradingEmail = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.REMOTE_TRADING_EMAIL);
			// String delegateFullname = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DELEGATE_FULLNAME);
			// String delegateIdNumber = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DELEGATE_ID_NUMBER);
			// String tradingPhoneNumber = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRADING_PHONE_NUMBER);
			// String mobilePhone = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
			// String homePhone = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE);
			// String tradingPassConfirm = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRADING_PASS_CONFIRM);
			//
			// TeleAccount data = new TeleAccount(fullName, idNumber, remoteTradingEmail, "", "", delegateFullname, delegateIdNumber);
			// if ("1".equals(tradingPhoneNumber)) {
			// data.setPhoneNumber1(mobilePhone);
			// data.setPhoneNumber2(homePhone);
			// }
			// data.setPhoneOption(tradingPhoneNumber);
			// data.setPassword(tradingPassConfirm);

			PersonalAccountExt pa = new PersonalAccountExt();
			this.populateData2(pa, onlineAccountMap);

			byte[] out = reportManager.openRegOnlineTeleAccount(pa);

			ServletOutputStream outputStream = response.getOutputStream();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "2".equals(pdfType) ? "inline; fileName=register_account_service.pdf" : "attachment; fileName=register_account_service.pdf");
			response.setContentLength(out.length);
			outputStream.write(out, 0, out.length);
			outputStream.flush();
			outputStream.close();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":END");
		} catch (Exception e) {
			logger.error(LOCATION + ":" + e);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param onlineAccountMap
	 * @param pdfType
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createPdf3(HttpServletRequest request, HttpServletResponse response, Map<String, String> onlineAccountMap, String pdfType) throws ServletException, IOException {
		final String LOCATION = "::createPdf3()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		try {
			PersonalAccountExt pa = new PersonalAccountExt();
			this.populateData3(pa, onlineAccountMap);
			WebReportManager reportManager = this.getVNDirectReportManager();

			byte[] out = reportManager.registerPrimeAccountService(pa);

			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "1".equals(pdfType) ? "inline; fileName=register_prime_account_service.pdf" : "attachment; fileName=register_prime_account_service.pdf");
			response.setContentLength(out.length);
			outputStream.write(out, 0, out.length);
			outputStream.flush();
			outputStream.close();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":END");
		} catch (Exception e) {
			logger.error(LOCATION + ":" + e);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param onlineAccountMap
	 * @param pdfType
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createPdf4(HttpServletRequest request, HttpServletResponse response, Map<String, String> onlineAccountMap, String pdfType) throws ServletException, IOException {
		final String LOCATION = "::createPdf4()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		try {
			PersonalAccountExt pa = new PersonalAccountExt();
			this.populateData4(pa, onlineAccountMap);
			WebReportManager reportManager = this.getVNDirectReportManager();

			byte[] out = reportManager.grantPowerTrading(pa);

			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "1".equals(pdfType) ? "inline; fileName=trading_grant_power.pdf" : "attachment; fileName=trading_grant_power.pdf");
			response.setContentLength(out.length);
			outputStream.write(out, 0, out.length);
			outputStream.flush();
			outputStream.close();
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":END");
		} catch (Exception e) {
			logger.error(LOCATION + ":" + e);
		}
	}

	/**
	 * 
	 * @param pa
	 * @param onlineAccount
	 * @throws Exception
	 * @throws Exception
	 */
	private void populateData(PersonalAccountExt pa, Map<String, String> onlineAccountMap) throws Exception {
		final String LOCATION = "::populateData()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		String dot = " .";
		String value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME);
		pa.setFullname(value + dot);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
		pa.setBirthDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SEX);
		pa.setSex("MALE".equalsIgnoreCase(value) ? "M" : "F");
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.NATIONALITY);
		if ("VNM".equalsIgnoreCase(value))
			pa.setNationality("Viá»‡t Nam");
		else
			pa.setNationality(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS);
		pa.setContactAddress(value + dot);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MARITAL_STATUS);
		pa.setMarried("MARRIED".equals(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
		pa.setIdNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
		pa.setReceiveDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE);
		pa.setReceivedAddress(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_EXPIRE_DATE);
		pa.setExpiredDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE);
		pa.setFixPhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
		pa.setMobilePhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.EMAIL);
		pa.setEmail(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TAX_NUMBER);
		pa.setTaxNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FAX);
		pa.setFax(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRANSACTION_TYPE);
		pa.setAccountType(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_REMOTE_TRADING);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setOnlineTrading("X");
			// pa.setOwnerTrading("X");
		}
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_TEL_TRADING);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setTeleTrading("X");
			// pa.setOwnerTrading2("X");
		}

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_OTHER_COMPANY_ACCOUNT);
		pa.setIsOtherCompanyAccount(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.OTHER_COMPANY_ACCOUNT_NAME);
		pa.setOtherCompanyAccountName(VNDirectWebUtilities.cleanString(value) + dot);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRADING_PHONE_NUMBER);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setTradingPhoneNumber_YES("X");
			pa.setFixPhone_2(pa.getFixPhone());
			pa.setMobilePhone_2(pa.getMobilePhone());
		} else {
			pa.setTradingPhoneNumber_NO("X");
		}

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER);
		pa.setBankNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME);
		pa.setBankName(VNDirectWebUtilities.cleanString(value) + dot);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER);
		pa.setBankManager(VNDirectWebUtilities.cleanString(value) + dot);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER2);
		pa.setBankNumber2(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME2);
		pa.setBankName2(VNDirectWebUtilities.cleanString(value) + dot);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER2);
		pa.setBankManager2(VNDirectWebUtilities.cleanString(value) + dot);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NAME);
		pa.setCorrespondenceAccountName(VNDirectWebUtilities.cleanString(value) + dot);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NUMBER);
		pa.setCorrespondenceAccountNumber(VNDirectWebUtilities.cleanString(value));

		if ("X".equalsIgnoreCase(pa.getMarried())) {
			value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_NAME);
			pa.setFullnameSecond(VNDirectWebUtilities.cleanString(value) + dot);
			value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_COMPANY);
			pa.setCompanyNameSecond(VNDirectWebUtilities.cleanString(value) + dot);
			value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SPOUSE_POSITION);
			pa.setWorkPositionSecond(VNDirectWebUtilities.cleanString(value) + dot);
		}

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.PUBLIC_MANAGE_COMPANY);
		pa.setOrganization1(VNDirectWebUtilities.cleanString(value) + dot);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.PUBLIC_COMPANY_HOLDING);
		pa.setOrganization2(VNDirectWebUtilities.cleanString(value) + dot);

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
	}

	/**
	 * @param pa
	 * @param onlineAccountMap
	 * @param request
	 * @throws Exception
	 */
	private void populateData2(PersonalAccountExt pa, Map<String, String> onlineAccountMap) throws Exception {
		final String LOCATION = "::populateData2()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		String value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME);
		pa.setFullname(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
		pa.setBirthDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SEX);
		pa.setSex("MALE".equalsIgnoreCase(value) ? "M" : "F");
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.NATIONALITY);
		if ("VNM".equalsIgnoreCase(value))
			pa.setNationality("Viá»‡t Nam");
		else
			pa.setNationality(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS);
		pa.setContactAddress(value);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MARITAL_STATUS);
		pa.setMarried("MARRIED".equals(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
		pa.setIdNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
		pa.setReceiveDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE);
		pa.setReceivedAddress(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_EXPIRE_DATE);
		pa.setExpiredDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE);
		pa.setFixPhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
		pa.setMobilePhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.EMAIL);
		pa.setEmail(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TAX_NUMBER);
		pa.setTaxNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FAX);
		pa.setFax(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRANSACTION_TYPE);
		pa.setAccountType(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_FLOOR_TRADING);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setFloorTrading("X");
		}
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_REMOTE_TRADING);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setOnlineTrading("X");
			// pa.setOwnerTrading("X");
		}
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_TEL_TRADING);
		if ("1".equals(VNDirectWebUtilities.cleanString(value))) {
			pa.setTeleTrading("X");
			// pa.setOwnerTrading2("X");
		}

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STATEMENT_CYCLE);
		pa.setStatementCycle(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STATEMENT_METHOD);
		pa.setStatementMethod(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.IS_OTHER_COMPANY_ACCOUNT);
		pa.setIsOtherCompanyAccount(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.OTHER_COMPANY_ACCOUNT_NAME);
		pa.setOtherCompanyAccountName(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ENTER_MARKET_YEAR);
		pa.setEnterMarketYear(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_KNOWLEDGE);
		pa.setInvestmentKnowledge(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_EXPERIENCE);
		pa.setInvestmentExperience(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_AIM);
		pa.setInvestmentAim(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_CAPITAL);
		pa.setInvestmentCapital(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SERVICE_PACK);
		pa.setServicePack(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.TRADING_PHONE_NUMBER);
		if ("1".equals(VNDirectWebUtilities.cleanString(value)))
			pa.setTradingPhoneNumber_YES("X");
		else
			pa.setTradingPhoneNumber_NO("X");

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER);
		pa.setBankNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME);
		pa.setBankName(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER);
		pa.setBankManager(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER2);
		pa.setBankNumber2(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME2);
		pa.setBankName2(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER2);
		pa.setBankManager2(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NAME);
		pa.setCorrespondenceAccountName(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.CORRESPONDENCE_ACCOUNT_NUMBER);
		pa.setCorrespondenceAccountNumber(VNDirectWebUtilities.cleanString(value));

		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.EDUCATION_LVL);
		// if ("HIGH_SCHOOL".equals(value)) {
		// pa.setEducationLevel("H");
		// } else if ("UNIVERSITY".equals(value)) {
		// pa.setEducationLevel("G");
		// } else if ("POST-GRADUATE".equals(value)) {
		// pa.setEducationLevel("P");
		// } else if ("OTHER".equals(value)) {
		// pa.setEducationLevel("O");
		// }

		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.COMPANY);
		// pa.setCompanyName(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.POSITION);
		// pa.setWorkPosition(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.COMPANY_PHONE);
		// pa.setCompanyPhone(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.COMPANY_FAX);
		// pa.setCompanyFax(VNDirectWebUtilities.cleanString(value));
		//
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NAME);
		// pa.setAtBank(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_NUMBER);
		// pa.setAccountNumber(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.BANK_MANAGER);
		// pa.setAccountOwner(VNDirectWebUtilities.cleanString(value));
		//
		// // will change
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_INCOME);
		// pa.setIncomePurposeInvest(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_LONG_TERM);
		// pa.setLongTermPurposeInvest(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_NOMAL_TERM);
		// pa.setMidTermPurposeInvest(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_OBJECT_SHORT_TERM);
		// pa.setShortTermPurposeInvest(VNDirectWebUtilities.cleanString(value));
		//		
		// // will change
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_LOW);
		// pa.setBelowAcceptedLevel(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_NOMAL);
		// pa.setMiddleAcceptedLevel(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_RISK_HIGH);
		// pa.setHighAcceptedLevel(VNDirectWebUtilities.cleanString(value));
		//
		// // will change
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ASSET_SHORT_TERM);
		// pa.setAssetShortTerm(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ASSET_FIXED);
		// pa.setAssetFix(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ASSET_CURRENT);
		// pa.setAssetNetIncome(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ASSET_MONTHLY);
		// pa.setAssetTotalIncome(VNDirectWebUtilities.cleanString(value));
		//		
		//		
		//
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ISDELEGATE_ACCOUNT);
		// pa.setAuthorizedAccount("1".equals(VNDirectWebUtilities.cleanString(value)));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DELEGATE_NAME);
		// pa.setAuthorizedName(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DELEGATE_PHONE);
		// pa.setAuthorizedPhone(VNDirectWebUtilities.cleanString(value));
		//		
		//		
		//		
		// // will change
		//		
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ORDER_RESULT_METHOD);
		// pa.setOrderResultMethod(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ORDER_RESULT_MOBILE_PHONE);
		// pa.setOrderResultMobilePhone(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.NOTIFICATION_METHOD);
		// pa.setNotificationMethod(VNDirectWebUtilities.cleanString(value));
		// if ("1".equals(value)) {
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.NOTIFICATION_MOBILE_PHONE);
		// pa.setNotificationMobilePhone(VNDirectWebUtilities.cleanString(value));
		// }

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
	}

	/**
	 * @param pa
	 * @param onlineAccountMap
	 * @throws Exception
	 */
	private void populateData3(PersonalAccountExt pa, Map<String, String> onlineAccountMap) throws Exception {
		final String LOCATION = "::populateData3()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		String value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME);
		pa.setFullname(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
		pa.setBirthDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.SEX);
		pa.setSex("MALE".equalsIgnoreCase(value) ? "M" : "F");
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.NATIONALITY);
		if ("VNM".equalsIgnoreCase(value))
			pa.setNationality("Viá»‡t Nam");
		else
			pa.setNationality(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS);
		pa.setContactAddress(value);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MARITAL_STATUS);
		pa.setMarried("MARRIED".equals(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
		pa.setIdNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
		pa.setReceiveDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE);
		pa.setReceivedAddress(VNDirectWebUtilities.cleanString(value));
		// value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_EXPIRE_DATE);
		// pa.setExpiredDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.HOME_PHONE);
		pa.setFixPhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
		pa.setMobilePhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.EMAIL);
		pa.setEmail(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_AIM);
		pa.setInvestmentAim(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.INVESTMENT_CAPITAL);
		pa.setInvestmentCapital(VNDirectWebUtilities.cleanString(value));

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
	}

	/**
	 * @param pa
	 * @param onlineAccountMap
	 * @throws Exception
	 */
	private void populateData4(PersonalAccountExt pa, Map<String, String> onlineAccountMap) throws Exception {
		final String LOCATION = "::populateData4()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		String value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.FULLNAME);
		pa.setFullname(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.DATE_OF_BIRTH);
		pa.setBirthDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.STREET_ADDRESS);
		pa.setContactAddress(value);

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_NUMBER);
		pa.setIdNumber(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE_DATE);
		pa.setReceiveDate(value);
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.ID_ISSUE);
		pa.setReceivedAddress(VNDirectWebUtilities.cleanString(value));

		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.MOBILE_PHONE);
		pa.setMobilePhone(VNDirectWebUtilities.cleanString(value));
		value = onlineAccountMap.get(DBConstants.ONLINE_ACCOUNT.EMAIL);
		pa.setEmail(VNDirectWebUtilities.cleanString(value));

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END");
	}

	/**
	 * 
	 * @return
	 */
	private IAccountManager getAccountManager() throws ServletException {
		try {
			return (IAccountManager) SpringUtils.getBean(Constants.SpringBeanNames.ACCOUNT_MANAGER);
		} catch (Exception e) {
			throw new ServletException("getTradingManager()", e);
		}
	}
}
