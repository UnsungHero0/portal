package vn.com.vndirect.domain.extend;

public class PagingInfo extends vn.com.web.commons.domain.db.PagingInfo {
	
	public PagingInfo() {
	}

	/**
	 * @param offset
	 */
	public PagingInfo(int offset) {
		this.setOffset(offset);
	}

	/**
	 * @param offset
	 * @param indexPage
	 */
	public PagingInfo(int offset, int indexPage) {
		this.setOffset(offset);
		this.setIndexPage(indexPage);
	}

	public String toString() {
		return "PagingInfo[index:" + getIndex() + ",offset:" + getOffset() + ",total:" + getTotal() + ",indexPage:"
				+ getIndexPage() + ",totalPage:" + getTotalPage() + "]";
	}

}
