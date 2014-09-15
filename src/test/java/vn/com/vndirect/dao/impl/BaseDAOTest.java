package vn.com.vndirect.dao.impl;

import javax.servlet.ServletContext;

import org.junit.BeforeClass;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;

import vn.com.web.commons.servercfg.ServerConfig;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

/**
 * @author minh.nguyen
 * 
 */
public class BaseDAOTest {

	public static AtomikosNonXADataSourceBean dataSource;
	private static ServletContext servletContext;

	private static final String SERVER_CONFIG_PATH = "/WEB-INF/configs/server-config.xml";
	private static final String RESOURCE_BASE_PATH = "src/main/webapp";

	@BeforeClass
	public static void setUpBase() {
		if (dataSource == null) {
			dataSource = new AtomikosNonXADataSourceBean();
			dataSource.setUniqueResourceName("jdbc/vndirectwebtest");
			dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//			dataSource.setUser("ipaweb");
//			dataSource.setPassword("ipaweb12");
//			dataSource.setUrl("jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = 10.25.1.173)(PORT = 1521))    (ADDRESS = (PROTOCOL = TCP)(HOST = 10.25.1.174)(PORT = 1521))    (LOAD_BALANCE = yes)    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = FINFON)    )  )");
			dataSource.setUser("IPAWEBTEST");
			dataSource.setPassword("IPA");
			dataSource.setUrl("jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = 10.26.0.50)(PORT = 1521))    (LOAD_BALANCE = yes)    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = LGFINFON) ) )");
			
			initServerConfigResource();
		}
	}

	private static void initServerConfigResource() {
		servletContext = new MockServletContext(RESOURCE_BASE_PATH, new FileSystemResourceLoader());
		ServerConfig.setupOnlineResource(SERVER_CONFIG_PATH, servletContext);
	}
}
