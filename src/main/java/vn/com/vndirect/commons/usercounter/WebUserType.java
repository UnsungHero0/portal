package vn.com.vndirect.commons.usercounter;

import java.io.Serializable;

public enum WebUserType implements Serializable {
	GUEST(0), FREE_USER(1), ONLINE_USER(2), AGENT_USER(3);

	private WebUserType(int _key) {
		this.key = _key;
	}

	public int key;

}
