package vn.com.vndirect.business;

import org.junit.BeforeClass;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class AbstractManagerTest {

	private static final String CONFIG_LOCATIONS = "classpath:spring-test-config.xml";

	protected static ConfigurableWebApplicationContext applicationContext;

	@BeforeClass
	public static void init() {
		if (applicationContext == null) {
			try {
				applicationContext = new XmlWebApplicationContext();
				applicationContext.setConfigLocations(new String[] { CONFIG_LOCATIONS });
				applicationContext.setServletContext(new MockServletContext("src/main/webapp", new FileSystemResourceLoader()));
				applicationContext.refresh();
				applicationContext.registerShutdownHook();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("---- Init applicationContext ----");
		}
	}
}
