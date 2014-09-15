package vn.com.vndirect.domain.struts2.common;

import vn.com.vndirect.domain.BaseModel;

@SuppressWarnings("serial")
public class EmptyModel extends BaseModel {
	private Boolean isActiveSession;

	public Boolean getIsActiveSession() {
		return isActiveSession;
	}

	public void setIsActiveSession(Boolean isActiveSession) {
		this.isActiveSession = isActiveSession;
	}
}
