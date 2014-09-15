/**
 * 
 */
package vn.com.vndirect.domain;

import org.apache.commons.lang.StringUtils;

/**
 * @author Huy
 * 
 */
public class IfoSectorCalcView extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2878537641627014388L;

	protected String sectorCode;

	protected String sectorName;

	protected String pe;

	protected String pb;

	protected String scopeMaketCap;

	protected String scopeAsset;

	protected String scopeEquity;

	protected String growthAsset;

	protected String growthRevenue;

	protected String roa;

	protected String roe;

	protected String profitMargin;

	protected String debtEquity;

	protected String currentRatio;

	protected String ebitda;

	protected String locale;

	protected String sortField;

	protected String itemCode;

	protected Double numericValue;

	protected String order;

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the numericValue
	 */
	public Double getNumericValue() {
		return numericValue;
	}

	/**
	 * @param numericValue
	 *            the numericValue to set
	 */
	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	public String getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}

	public String getDebtEquity() {
		return debtEquity;
	}

	public void setDebtEquity(String debtEquity) {
		this.debtEquity = debtEquity;
	}

	public String getEbitda() {
		return ebitda;
	}

	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}

	public String getGrowthAsset() {
		return growthAsset;
	}

	public void setGrowthAsset(String growthAsset) {
		this.growthAsset = growthAsset;
	}

	public String getGrowthRevenue() {
		return growthRevenue;
	}

	public void setGrowthRevenue(String growthRevenue) {
		this.growthRevenue = growthRevenue;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(String profitMargin) {
		this.profitMargin = profitMargin;
	}

	public String getRoa() {
		return roa;
	}

	public void setRoa(String roa) {
		this.roa = roa;
	}

	public String getRoe() {
		return roe;
	}

	public void setRoe(String roe) {
		this.roe = roe;
	}

	public String getScopeAsset() {
		return scopeAsset;
	}

	public void setScopeAsset(String scopeAsset) {
		this.scopeAsset = scopeAsset;
	}

	public String getScopeEquity() {
		return scopeEquity;
	}

	public void setScopeEquity(String scopeEquity) {
		this.scopeEquity = scopeEquity;
	}

	public String getScopeMaketCap() {
		return scopeMaketCap;
	}

	public void setScopeMaketCap(String scopeMaketCap) {
		this.scopeMaketCap = scopeMaketCap;
	}

	public String getSectorCode() {
		return StringUtils.trim(sectorCode);
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
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
		result = prime * result + ((this.growthAsset == null) ? 0 : this.growthAsset.hashCode());
		result = prime * result + ((this.growthRevenue == null) ? 0 : this.growthRevenue.hashCode());
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
		if (!(obj instanceof IfoSectorCalcView))
			return false;
		final IfoSectorCalcView other = (IfoSectorCalcView) obj;
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

	/**
	 * @return the growthAssetExt
	 */
	public double getGrowthAssetExt() {
		try {
			return Double.parseDouble(this.getGrowthAsset()) * 100;
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * @return the growthRevenueExt
	 */
	public double getGrowthRevenueExt() {
		try {
			return Double.parseDouble(this.getGrowthRevenue()) * 100;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return the roaExt
	 */
	public double getRoaExt() {
		try {
			return Double.parseDouble(this.getRoa()) * 100;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return the roeExt
	 */
	public double getRoeExt() {
		try {
			return Double.parseDouble(this.getRoe()) * 100;
		} catch (Exception e) {
			return 0;
		}
	}
}
