package vn.com.vndirect.business;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import vn.com.web.commons.exception.SystemException;

public class StockPickManagerTest extends AbstractManagerTest{

	private IStockPickManager stockPickManager;
	
	@Before
	public void setUp(){
		stockPickManager = (IStockPickManager)applicationContext.getBean("StockPickManager");
	}
	
	@Test
	public void testSaveSuccessfulCustomer() {
		String cusId = UUID.randomUUID().toString();
		try {
			Assert.assertTrue(stockPickManager.saveRegistCustomer(cusId));
		} catch (SystemException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testCheckExistedCutomerExpectedFalse(){
		String cusId = UUID.randomUUID().toString();
		try {
			Assert.assertFalse(stockPickManager.isExistedCustomer(cusId));
		} catch (SystemException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetListCustomerId() throws SystemException{
		Assert.assertNotNull(stockPickManager.getListCustomerId());

	}
}
