package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import vn.com.vndirect.domain.OnlineAccount;
import vn.com.vndirect.domain.crm.leadcontract.ConvertLeadToCustomerResult;
import vn.com.vndirect.service.crm.ICRMServiceManager;
import vn.com.web.commons.exception.FunctionalException;

public class TestCRMService extends AbstractManagerTest{

	private ICRMServiceManager crmServiceManager;
	@Before
	public void setUp(){
		crmServiceManager = (ICRMServiceManager) applicationContext.getBean("CRMServiceManager");
	}
	
	@Test
	@Ignore
	public void testGenerateXmlInput() {
		try {
			String xml = crmServiceManager.getCrmXmlInputCreateLead(getMockAccount(""));
			Assert.assertNotNull(xml);
//			System.out.println(xml);
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void TestCreateLeadId(){
		String leadId = null;
		leadId = crmServiceManager.createLead(getMockAccount(""));
		Assert.assertNotNull(leadId);
//		System.out.println(leadId);
	}
	
	@Ignore
	@Test
	public void TestConvertLeadAndSyncBo(){
		String leadId = crmServiceManager.createLead(getMockAccount("6868681"));
		try {
			ConvertLeadToCustomerResult result = crmServiceManager.convertLeadToCustomerAndSynToBo(leadId);
			Assert.assertEquals("0", result.getStatus());
		} catch (FunctionalException e) {
			Assert.assertFalse(e.getMessage(), false);
		}
	}
	
	private OnlineAccount getMockAccount(String idNumber){
		OnlineAccount onlineAccount = new OnlineAccount();
		onlineAccount.setName("Minh_10_02_" + idNumber);
		onlineAccount.setLastName("To Quang");
		onlineAccount.setAddress("Ha Noi");
		onlineAccount.setSex("Male");
		onlineAccount.setBirthday("08/10/1990");
		onlineAccount.setEmail("minhtq_90@gmail.com");
		onlineAccount.setIdentityDate("14/04/2004");
		//Change id number
		onlineAccount.setIdentityNo("12345678" + idNumber);
		onlineAccount.setIdentityType("001");
		onlineAccount.setBankAccountNo("21331232123");
		onlineAccount.setBankAccountName("QUANG MINH");
		onlineAccount.setBankCode("101");
		onlineAccount.setBankName("");
		onlineAccount.setBankBranchCode("01101011");
		onlineAccount.setBankBranchName("");
		onlineAccount.setMarried("Single");
		onlineAccount.setMobilePhoneNo("0987654321");
		List<String> tradingMethods = new ArrayList<String>();
		tradingMethods.add("1");
		tradingMethods.add("2");
		onlineAccount.setTradingMethods(tradingMethods);
		onlineAccount.setNational("VNM");
		onlineAccount.setTradingPhone("01235454878");
		List<String> mobilePassword = new ArrayList<String>();
		mobilePassword.add("1");
		mobilePassword.add("2");
		mobilePassword.add("3");
		mobilePassword.add("4");
		onlineAccount.setMobilePassword(mobilePassword);
		onlineAccount.setProvince("a0b9489a-69f4-df11-bfba-005056967999");
		return onlineAccount;
	}
}
