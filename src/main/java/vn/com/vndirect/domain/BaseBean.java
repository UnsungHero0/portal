package vn.com.vndirect.domain;

import java.io.Serializable;
import vn.com.vndirect.commons.utility.Constants;

@SuppressWarnings("serial")
public class BaseBean implements Serializable, Cloneable {
	private String strCreatedDate;
	private String strCreatedDateFrom;
	private String strCreatedDateTo;

	private String strModifiedBy;
	private String strModifiedDateFrom;
	private String strModifiedDateTo;

	// ++++ support paging
	private int pagingIndex = 0;
	private boolean usingPaging = true;
	private int numberItem = Constants.Paging.NUMBER_ITEMS;

	public int getPagingIndex() {
		return pagingIndex;
	}

	public void setPagingIndex(int pagingIndex) {
		this.pagingIndex = pagingIndex;
	}

	public boolean isUsingPaging() {
		return usingPaging;
	}

	public void setUsingPaging(boolean usingPaging) {
		this.usingPaging = usingPaging;
	}

	// ---

	private String strCreatedBy;

	public String getStrCreatedBy() {
		return strCreatedBy;
	}

	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}

	public String getStrCreatedDate() {
		return strCreatedDate;
	}

	public void setStrCreatedDate(String strCreatedDate) {
		this.strCreatedDate = strCreatedDate;
	}

	public String getStrCreatedDateFrom() {
		return strCreatedDateFrom;
	}

	public void setStrCreatedDateFrom(String strCreatedDateFrom) {
		this.strCreatedDateFrom = strCreatedDateFrom;
	}

	public String getStrCreatedDateTo() {
		return strCreatedDateTo;
	}

	public void setStrCreatedDateTo(String strCreatedDateTo) {
		this.strCreatedDateTo = strCreatedDateTo;
	}

	public String getStrModifiedBy() {
		return strModifiedBy;
	}

	public void setStrModifiedBy(String strModifiedBy) {
		this.strModifiedBy = strModifiedBy;
	}

	public String getStrModifiedDateFrom() {
		return strModifiedDateFrom;
	}

	public void setStrModifiedDateFrom(String strModifiedDateFrom) {
		this.strModifiedDateFrom = strModifiedDateFrom;
	}

	public String getStrModifiedDateTo() {
		return strModifiedDateTo;
	}

	public void setStrModifiedDateTo(String strModifiedDateTo) {
		this.strModifiedDateTo = strModifiedDateTo;
	}

	/**
	 * @return the numberItem
	 */
	public int getNumberItem() {
		return numberItem;
	}

	/**
	 * @param numberItem
	 *            the numberItem to set
	 */
	public void setNumberItem(int numberItem) {
		this.numberItem = numberItem;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFirstPagingIndex() {
		return (this.pagingIndex == 0 || this.pagingIndex == 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.numberItem;
		result = prime * result + this.pagingIndex;
		result = prime * result + ((this.strCreatedBy == null) ? 0 : this.strCreatedBy.hashCode());
		result = prime * result + ((this.strCreatedDate == null) ? 0 : this.strCreatedDate.hashCode());
		result = prime * result + ((this.strCreatedDateFrom == null) ? 0 : this.strCreatedDateFrom.hashCode());
		result = prime * result + ((this.strCreatedDateTo == null) ? 0 : this.strCreatedDateTo.hashCode());
		result = prime * result + ((this.strModifiedBy == null) ? 0 : this.strModifiedBy.hashCode());
		result = prime * result + ((this.strModifiedDateFrom == null) ? 0 : this.strModifiedDateFrom.hashCode());
		result = prime * result + ((this.strModifiedDateTo == null) ? 0 : this.strModifiedDateTo.hashCode());
		result = prime * result + (this.usingPaging ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseBean))
			return false;
		final BaseBean other = (BaseBean) obj;
		if (this.numberItem != other.numberItem)
			return false;
		if (this.pagingIndex != other.pagingIndex)
			return false;
		if (this.strCreatedBy == null) {
			if (other.strCreatedBy != null)
				return false;
		} else if (!this.strCreatedBy.equals(other.strCreatedBy))
			return false;
		if (this.strCreatedDate == null) {
			if (other.strCreatedDate != null)
				return false;
		} else if (!this.strCreatedDate.equals(other.strCreatedDate))
			return false;
		if (this.strCreatedDateFrom == null) {
			if (other.strCreatedDateFrom != null)
				return false;
		} else if (!this.strCreatedDateFrom.equals(other.strCreatedDateFrom))
			return false;
		if (this.strCreatedDateTo == null) {
			if (other.strCreatedDateTo != null)
				return false;
		} else if (!this.strCreatedDateTo.equals(other.strCreatedDateTo))
			return false;
		if (this.strModifiedBy == null) {
			if (other.strModifiedBy != null)
				return false;
		} else if (!this.strModifiedBy.equals(other.strModifiedBy))
			return false;
		if (this.strModifiedDateFrom == null) {
			if (other.strModifiedDateFrom != null)
				return false;
		} else if (!this.strModifiedDateFrom.equals(other.strModifiedDateFrom))
			return false;
		if (this.strModifiedDateTo == null) {
			if (other.strModifiedDateTo != null)
				return false;
		} else if (!this.strModifiedDateTo.equals(other.strModifiedDateTo))
			return false;
		if (this.usingPaging != other.usingPaging)
			return false;
		return true;
	}
}