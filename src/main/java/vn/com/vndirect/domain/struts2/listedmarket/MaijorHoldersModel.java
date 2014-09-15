package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoBreakdownViewId;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoMaijorHolderView;

@SuppressWarnings("serial")
public class MaijorHoldersModel extends BaseModel {

	private List<IfoMaijorHolderView> ifoMaijorHolderViewList;
	private IfoBreakdownViewId ifoBreakdownViewId;
	private IfoCompanyNameViewId ifoCompanyNameViewId;

	public IfoBreakdownViewId getIfoBreakdownViewId() {
		return ifoBreakdownViewId;
	}

	public void setIfoBreakdownViewId(IfoBreakdownViewId ifoBreakdownViewId) {
		this.ifoBreakdownViewId = ifoBreakdownViewId;
	}

	public IfoCompanyNameViewId getIfoCompanyNameViewId() {
		return ifoCompanyNameViewId;
	}

	public void setIfoCompanyNameViewId(IfoCompanyNameViewId ifoCompanyNameViewId) {
		this.ifoCompanyNameViewId = ifoCompanyNameViewId;
	}

	public List<IfoMaijorHolderView> getIfoMaijorHolderViewList() {
		return ifoMaijorHolderViewList;
	}

	public void setIfoMaijorHolderViewList(List<IfoMaijorHolderView> ifoMaijorHolderViewList) {
		this.ifoMaijorHolderViewList = ifoMaijorHolderViewList;
	}

}
