package vn.com.vndirect.dao.impl;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import vn.com.web.commons.exception.SystemException;

public class RegistStockPickDAOTest extends BaseDAOTest {

	private RegistStockPickDAO registStockPickDAO;
	
	@Before
	public void setUp(){
		registStockPickDAO = new RegistStockPickDAO();
		registStockPickDAO.setDataSource(dataSource);
	}
	
	@Test
	@Ignore
	public void testSaveCustomerID() {
		try {
			String cusID = UUID.randomUUID().toString();
			Assert.assertTrue(registStockPickDAO.saveCustomerId(cusID));
			boolean existed = registStockPickDAO.isExistedCustomer(cusID);
			Assert.assertTrue(existed);
		} catch (SystemException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testGetListCustomerId() {
		try {
			Assert.assertNotNull(registStockPickDAO.getListCustomerId());
		} catch (SystemException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testIsExistedCustomer() {
		try {
			String cusID = UUID.randomUUID().toString();
			boolean existed = registStockPickDAO.isExistedCustomer(cusID);
			Assert.assertFalse(existed);
		} catch (SystemException e) {
			Assert.fail(e.getMessage());
		}
	}
}


