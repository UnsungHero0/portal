/**
 * 
 */
package vn.com.vndirect.web.security;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author tungnq.nguyen
 * 
 */
@SuppressWarnings("serial")
public class VNDSCasUser extends HashMap<String, Serializable> {
	public static String CAS_USER_APP_CTX_KEY = "_CAS_USER_APP_CTX_KEY_";

	public static String CAS_IDG_APP_CTX_KEY = "_CAS_IDG_APP_CTX_KEY_";

	public interface Status {
		public String PROCESS_STATUS_PARAM = "_vnds_status";

		public String ONLINE_PROCESS_STATUS = "_online";
		public String AGENT_PROCESS_STATUS = "_agent";
		public String IDG_PROCESS_STATUS = "_idg";
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public interface ServiceParamName {
		String SSO_SEC = "_vnds_s";
		String SSO_DATA = "_vnds_d";
		String AUTH_FOR = "_vnds_a";
		String DEVICE = "_vnds_dv";
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public interface AuthFor {
		String AGENT = "agent";
		String ONLINE = "online";
		String STAFF = "staff";
		String IDG = "idg";
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public interface Device {
		String WEB = "web";
		String MOBILE = "mobile";
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public enum Online {
		FULLNAME("fullName"), STATUS("status"), USER_ID("userId"), USER_NAME("userName"), BO_CUSTOMER_ID("boCustomerId"),

		EMAIL("email"),

		IDG_ID("idgId"), USER_TYPE("userType"), SERVICES("services");

		public final String key;

		Online(String _key) {
			this.key = _key;
		}
	}

	/**
	 * 
	 * @author Blue9Frog
	 * 
	 */
	public enum Agent {
		FULLNAME("fullName"), STATUS("status"), USER_ID("userId"), USER_NAME("userName"), BO_CUSTOMER_ID("boCustomerId"), SERVICES("services"),

		EMAIL("email"),

		IDG_ID("idgId"), USER_TYPE("userType"),

		BO_BRANK_CODE("boBrankCode"), BO_CARE_BY_ID("boCarebyId"), BO_PASSWORD("boPassword"), BO_REFERENCE_ID("boReferenceId"), BO_TYPE("boType"), BO_USER_CODE("boUserCode"), BO_USERNAME("boUserName");

		public final String key;

		Agent(String _key) {
			this.key = _key;
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getStr(String key) {
		if (key != null) {
			Object obj = this.get(key);
			if (obj != null & obj instanceof String) {
				return (String) obj;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */
	public String getStr(String key1, String key2) {
		String str = this.getStr(key1);
		if (str == null) {
			str = this.getStr(key2);
		}
		return str;
	}

	/**
	 * 
	 * @return
	 */
	public Long getUserId() {
		try {
			String str = getStr(Online.USER_ID.key, Agent.USER_ID.key);
			return new Long(str.trim());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return getStr(Online.USER_NAME.key, Agent.USER_NAME.key);
	}

	public String getFullName() {
		return getStr(Online.FULLNAME.key, Agent.FULLNAME.key);
	}

	public String getStatus() {
		return getStr(Online.STATUS.key, Agent.STATUS.key);
	}

	public String getBOCustId() {
		return getStr(Online.BO_CUSTOMER_ID.key, Agent.BO_CUSTOMER_ID.key);
	}

	public String getServices() {
		return getStr(Online.SERVICES.key, Agent.SERVICES.key);
	}

	public String getEmail() {
		return getStr(Online.EMAIL.key, Agent.EMAIL.key);
	}

	public String getIdgId() {
		return getStr(Online.IDG_ID.key, Agent.IDG_ID.key);
	}

	public String getUserType() {
		return getStr(Online.USER_TYPE.key, Agent.USER_TYPE.key);
	}

	public String getBOBankCode() {
		return getStr(Agent.BO_BRANK_CODE.key);
	}

	public String getBOCareById() {
		return getStr(Agent.BO_CARE_BY_ID.key);
	}

	public String getBOPassword() {
		return getStr(Agent.BO_PASSWORD.key);
	}

	public String getBORefenceId() {
		return getStr(Agent.BO_REFERENCE_ID.key);
	}

	public String getBOType() {
		return getStr(Agent.BO_TYPE.key);
	}

	public String getBOUserCode() {
		return getStr(Agent.BO_USER_CODE.key);
	}

	public String getBOUserName() {
		return getStr(Agent.BO_USERNAME.key);
	}
}
