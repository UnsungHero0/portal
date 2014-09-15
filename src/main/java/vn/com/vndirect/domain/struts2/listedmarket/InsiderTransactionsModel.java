package vn.com.vndirect.domain.struts2.listedmarket;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoCompanyNameViewId;
import vn.com.vndirect.domain.IfoInsiderTransactionView;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 15, 2010 11:58:37 AM
 */
@SuppressWarnings("serial")
public class InsiderTransactionsModel extends BaseModel {

	private List<IfoInsiderTransactionView> ifoInsiderTransactionViewList;
	private IfoCompanyNameViewId ifoCompanyNameViewId;

	public IfoCompanyNameViewId getIfoCompanyNameViewId() {
		return ifoCompanyNameViewId;
	}

	public void setIfoCompanyNameViewId(IfoCompanyNameViewId ifoCompanyNameViewId) {
		this.ifoCompanyNameViewId = ifoCompanyNameViewId;
	}

	public List<IfoInsiderTransactionView> getIfoInsiderTransactionViewList() {
		return ifoInsiderTransactionViewList;
	}

	public void setIfoInsiderTransactionViewList(List<IfoInsiderTransactionView> ifoInsiderTransactionViewList) {
		this.ifoInsiderTransactionViewList = ifoInsiderTransactionViewList;
	}
}
