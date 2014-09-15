/**
 * 
 */
package vn.com.vndirect.domain;

import org.apache.commons.lang.StringUtils;

/**
 * @author Huy
 * 
 */
public class IfoIndustryCalcView extends IfoSectorCalcView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3723094001479375513L;
	private String industryCode;
	private String industryName;
	private String firstChar;
	private String companyId;
	private Double income;
	private Double marketCap;

	/**
	 * @return the marketCap
	 */
	public Double getMarketCap() {
		return marketCap;
	}

	/**
	 * @param marketCap
	 *            the marketCap to set
	 */
	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	/**
	 * @return the income
	 */
	public Double getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(Double income) {
		this.income = income;
	}

	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return StringUtils.trim(industryCode);
	}

	/**
	 * @param industryCode
	 *            the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	/**
	 * @return the industryName
	 */
	public String getIndustryName() {
		return industryName;
	}

	/**
	 * @param industryName
	 *            the industryName to set
	 */
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	/**
	 * @return the firstChar
	 */
	public String getFirstChar() {
		return firstChar;
	}

	/**
	 * @param firstChar
	 *            the firstChar to set
	 */
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.currentRatio == null) ? 0 : this.currentRatio.hashCode());
		result = prime * result + ((this.debtEquity == null) ? 0 : this.debtEquity.hashCode());
		result = prime * result + ((this.ebitda == null) ? 0 : this.ebitda.hashCode());
		result = prime * result + ((this.firstChar == null) ? 0 : this.firstChar.hashCode());
		result = prime * result + ((this.growthAsset == null) ? 0 : this.growthAsset.hashCode());
		result = prime * result + ((this.growthRevenue == null) ? 0 : this.growthRevenue.hashCode());
		result = prime * result + ((this.industryCode == null) ? 0 : this.industryCode.hashCode());
		result = prime * result + ((this.industryName == null) ? 0 : this.industryName.hashCode());
		result = prime * result + ((this.locale == null) ? 0 : this.locale.hashCode());
		result = prime * result + ((this.pb == null) ? 0 : this.pb.hashCode());
		result = prime * result + ((this.pe == null) ? 0 : this.pe.hashCode());
		result = prime * result + ((this.profitMargin == null) ? 0 : this.profitMargin.hashCode());
		result = prime * result + ((this.roa == null) ? 0 : this.roa.hashCode());
		result = prime * result + ((this.roe == null) ? 0 : this.roe.hashCode());
		result = prime * result + ((this.scopeAsset == null) ? 0 : this.scopeAsset.hashCode());
		result = prime * result + ((this.scopeEquity == null) ? 0 : this.scopeEquity.hashCode());
		result = prime * result + ((this.scopeMaketCap == null) ? 0 : this.scopeMaketCap.hashCode());
		result = prime * result + ((this.sectorCode == null) ? 0 : this.sectorCode.hashCode());
		result = prime * result + ((this.sectorName == null) ? 0 : this.sectorName.hashCode());
		result = prime * result + ((this.sortField == null) ? 0 : this.sortField.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof IfoIndustryCalcView))
			return false;
		final IfoIndustryCalcView other = (IfoIndustryCalcView) obj;
		if (this.currentRatio == null) {
			if (other.currentRatio != null)
				return false;
		} else if (!this.currentRatio.equals(other.currentRatio))
			return false;
		if (this.debtEquity == null) {
			if (other.debtEquity != null)
				return false;
		} else if (!this.debtEquity.equals(other.debtEquity))
			return false;
		if (this.ebitda == null) {
			if (other.ebitda != null)
				return false;
		} else if (!this.ebitda.equals(other.ebitda))
			return false;
		if (this.firstChar == null) {
			if (other.firstChar != null)
				return false;
		} else if (!this.firstChar.equals(other.firstChar))
			return false;
		if (this.growthAsset == null) {
			if (other.growthAsset != null)
				return false;
		} else if (!this.growthAsset.equals(other.growthAsset))
			return false;
		if (this.growthRevenue == null) {
			if (other.growthRevenue != null)
				return false;
		} else if (!this.growthRevenue.equals(other.growthRevenue))
			return false;
		if (this.industryCode == null) {
			if (other.industryCode != null)
				return false;
		} else if (!this.industryCode.equals(other.industryCode))
			return false;
		if (this.industryName == null) {
			if (other.industryName != null)
				return false;
		} else if (!this.industryName.equals(other.industryName))
			return false;
		if (this.locale == null) {
			if (other.locale != null)
				return false;
		} else if (!this.locale.equals(other.locale))
			return false;
		if (this.pb == null) {
			if (other.pb != null)
				return false;
		} else if (!this.pb.equals(other.pb))
			return false;
		if (this.pe == null) {
			if (other.pe != null)
				return false;
		} else if (!this.pe.equals(other.pe))
			return false;
		if (this.profitMargin == null) {
			if (other.profitMargin != null)
				return false;
		} else if (!this.profitMargin.equals(other.profitMargin))
			return false;
		if (this.roa == null) {
			if (other.roa != null)
				return false;
		} else if (!this.roa.equals(other.roa))
			return false;
		if (this.roe == null) {
			if (other.roe != null)
				return false;
		} else if (!this.roe.equals(other.roe))
			return false;
		if (this.scopeAsset == null) {
			if (other.scopeAsset != null)
				return false;
		} else if (!this.scopeAsset.equals(other.scopeAsset))
			return false;
		if (this.scopeEquity == null) {
			if (other.scopeEquity != null)
				return false;
		} else if (!this.scopeEquity.equals(other.scopeEquity))
			return false;
		if (this.scopeMaketCap == null) {
			if (other.scopeMaketCap != null)
				return false;
		} else if (!this.scopeMaketCap.equals(other.scopeMaketCap))
			return false;
		if (this.sectorCode == null) {
			if (other.sectorCode != null)
				return false;
		} else if (!this.sectorCode.equals(other.sectorCode))
			return false;
		if (this.sectorName == null) {
			if (other.sectorName != null)
				return false;
		} else if (!this.sectorName.equals(other.sectorName))
			return false;
		if (this.sortField == null) {
			if (other.sortField != null)
				return false;
		} else if (!this.sortField.equals(other.sortField))
			return false;
		return true;
	}
}
