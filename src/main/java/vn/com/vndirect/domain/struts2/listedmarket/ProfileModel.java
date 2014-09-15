package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompany;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoCompanyProfileViewId;
import vn.com.vndirect.domain.IfoSecIndexViewId;

@SuppressWarnings( { "unchecked", "serial" })
public class ProfileModel extends BaseModel {

	/**
	 * 
	 */

	private IfoCompany ifoCompany;
	private IfoCompanyProfileViewId ifoCompanyProfileViewId;
	private IfoCompanyNameViewId ifoCompanyNameViewId;
	private List officersViewIdList;
	private IfoSecIndexViewId ifoSecIndexViewId;
	private String companyName;
	private String symbol;

	public IfoCompany getIfoCompany() {
		return ifoCompany;
	}

	public void setIfoCompany(IfoCompany ifoCompany) {
		this.ifoCompany = ifoCompany;
	}

	public IfoCompanyNameViewId getIfoCompanyNameViewId() {
		return ifoCompanyNameViewId;
	}

	public void setIfoCompanyNameViewId(IfoCompanyNameViewId ifoCompanyNameViewId) {
		this.ifoCompanyNameViewId = ifoCompanyNameViewId;
	}

	public List getOfficersViewIdList() {
		return officersViewIdList;
	}

	public void setOfficersViewIdList(List officersViewIdList) {
		this.officersViewIdList = officersViewIdList;
	}

	public IfoCompanyProfileViewId getIfoCompanyProfileViewId() {
		return ifoCompanyProfileViewId;
	}

	public void setIfoCompanyProfileViewId(IfoCompanyProfileViewId ifoCompanyProfileViewId) {
		this.ifoCompanyProfileViewId = ifoCompanyProfileViewId;
	}

	public IfoSecIndexViewId getIfoSecIndexViewId() {
		return ifoSecIndexViewId;
	}

	public void setIfoSecIndexViewId(IfoSecIndexViewId ifoSecIndexViewId) {
		this.ifoSecIndexViewId = ifoSecIndexViewId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
