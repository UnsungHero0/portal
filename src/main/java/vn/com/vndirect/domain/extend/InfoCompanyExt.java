package vn.com.vndirect.domain.extend;

import java.io.Serializable;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;

public class InfoCompanyExt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -100959081578930222L;
	private IfoCompanyCalcView infoCompany = new IfoCompanyCalcView();
	private SecInfo secInfo = new SecInfo();

	/**
	 * @return the infoCompany
	 */
	public IfoCompanyCalcView getInfoCompany() {
		return infoCompany;
	}

	/**
	 * @param infoCompany
	 *            the infoCompany to set
	 */
	public void setInfoCompany(IfoCompanyCalcView infoCompany) {
		this.infoCompany = infoCompany;
	}

	/**
	 * @return the secInfo
	 */
	public SecInfo getSecInfo() {
		return secInfo;
	}

	/**
	 * @param secInfo
	 *            the secInfo to set
	 */
	public void setSecInfo(SecInfo secInfo) {
		this.secInfo = secInfo;
	}

	public Double getCurrentIndex() {
		if (VNDirectWebUtilities.getOTCExchange().equals(infoCompany.getExchangCode())) {
			return infoCompany.getClosePrice();
		} else {
			return secInfo.getCurrentIndex();
		}
	}

}
