package vn.com.vndirect.domain.struts2.newsinfo;

import vn.com.web.commons.domain.db.PagingInfo;


public class NewsMappingModel {
	private long totalPage;
	private int offset;

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}


	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public NewsMappingModel(long totalPage, int offset) {
		super();
		this.totalPage = totalPage;
		this.offset = offset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offset;
		result = prime * result + (int) (totalPage ^ (totalPage >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsMappingModel other = (NewsMappingModel) obj;
		if (offset != other.offset)
			return false;
		if (totalPage != other.totalPage)
			return false;
		return true;
	}

}
